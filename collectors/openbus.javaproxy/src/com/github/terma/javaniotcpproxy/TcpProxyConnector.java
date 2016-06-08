/*
Copyright 2012 Artem Stasuk

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.github.terma.javaniotcpproxy;

import br.com.produban.openbus.integration.IntegrationQueue;
import br.com.produban.openbus.utils.ByteBufferUtils;
import com.github.terma.javaniotcpserver.TcpServerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TcpProxyConnector implements TcpServerHandler {

	private final static Logger LOGGER = LoggerFactory.getLogger(TcpProxyConnector.class);
    private static final int ZBXD_HEADER_SIZE = 13;
    private static byte[] ZBXD_SUCCESS_RESPONSE;

    static {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        String json = "{\n" +
                        "\"response\":\"success\", \n" +
                        "\"info\":\"\"" +
                      "}";

        int length = json.getBytes().length;

        try {
            out.write(new byte[] {
                    'Z', 'B', 'X', 'D',
                    '\1',
                    (byte)(length & 0xFF),
                    (byte)((length & 0x00FF) >> 8),
                    (byte)((length & 0x0000FF) >> 16),
                    (byte)((length & 0x000000FF) >> 24),
                    '\0','\0','\0','\0'});

            out.write(json.getBytes());
            ZBXD_SUCCESS_RESPONSE = out.toByteArray();
        } catch (IOException e) {
            LOGGER.error("Fail to generate success response",e);
        }
    }

    private final TcpProxyBuffer clientBuffer = new TcpProxyBuffer();
	private final TcpProxyBuffer serverBuffer = new TcpProxyBuffer();
	private final SocketChannel clientChannel;
    private SocketChannel serverChannel;

	private Selector selector;
	private TcpProxyConfig config;

	private final IntegrationQueue integrationQueue;

    private List<byte[]> partialArrays = new ArrayList<>();

    private List<ByteBuffer> receivedBuffers = new LinkedList<>();
	private int receivedBytes = 0;
    private int expectedMessageSize = 0;
    private int messageBytesReceived = 0;
    private static final int minimumSizeForParsing = 42;

    private boolean minimumSizeReceived = false;
    private boolean messageTypeVerified = false;
    private boolean activeCheckDetected = false;
    private boolean endOfMessage = false;
    private TcpProxyConnectorEventCallback onDestroyCallback;

    public TcpProxyConnector(SocketChannel clientChannel, TcpProxyConfig config, IntegrationQueue integrationQueue) {
		this.clientChannel = clientChannel;
		this.config = config;
		this.integrationQueue = integrationQueue;
	}

	public ByteBuffer readFromClient() throws IOException {
		ByteBuffer byteBuffer = serverBuffer.getByteBufferWriteFrom(clientChannel);

        if (byteBuffer != null) {

            byteBuffer = copyFrom(byteBuffer);
            byteBuffer.flip();

            if (!minimumSizeReceived) {
                if (LOGGER.isDebugEnabled())
                    LOGGER.debug("Connector: ["+this+"] - Minimum size not received yet. Applying partial read...");
                applyPartialRead(byteBuffer);
            }

            if (minimumSizeReceived) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Connector: ["+this+"] - Minimum size received!");
                }

                if (!messageTypeVerified) {
                    byteBuffer = joinPartialReads();
                    verifyMessageType(byteBuffer);
                    if (activeCheckDetected)
                        serverBuffer.fillBuffer(byteBuffer.array());
                }

                applyMessageSize(byteBuffer);

                if (activeCheckDetected) {
                    if (serverChannel == null)
                        openServerChannel();
                    if (serverBuffer.isReadyToRead())
                        register();
                } else
                    serverBuffer.reset();
            } else {
                serverBuffer.reset();
                return null;
            }
        }

		return byteBuffer;
	}

    private void verifyMessageType(ByteBuffer byteBuffer) throws IOException {
        String parsed = ByteBufferUtils.parseContent(byteBuffer, 0, 42);
        activeCheckDetected = parsed.contains("active checks");
        messageTypeVerified = true;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Connector: ["+this+"] - Verified message type as active check? ["+activeCheckDetected+"]");
        }
    }

    private ByteBuffer joinPartialReads() {
        ByteBuffer finalBuffer = ByteBuffer.allocate(receivedBytes);

        for (byte[] a : partialArrays) {
            if (a.length <= finalBuffer.remaining())
                finalBuffer.put(a);
            else
                finalBuffer.put(a,0,finalBuffer.remaining());
        }

        finalBuffer.flip();

        partialArrays = null;

        return finalBuffer;
    }

    private void applyPartialRead(ByteBuffer byteBuffer) {
        byte[] array = new byte[byteBuffer.limit()];
        byteBuffer.get(array);
        byteBuffer.rewind();
        partialArrays.add(array);
        receivedBytes += byteBuffer.limit();

        minimumSizeReceived = receivedBytes >= minimumSizeForParsing;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Connector: ["+this+"] - Bytes read till now: ["+receivedBytes+"]");
        }
    }

    private void applyMessageSize(ByteBuffer byteBuffer) throws IOException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Connector: ["+this+"] - Applying message size");
        }

        if (messageBytesReceived == 0) {
            expectedMessageSize = parseSize(byteBuffer,5);
            if (LOGGER.isDebugEnabled())
                LOGGER.debug("Connector: ["+this+"] - Expected message size: [" + expectedMessageSize + "]");
            messageBytesReceived += byteBuffer.limit() - ZBXD_HEADER_SIZE;
        }
        else
            messageBytesReceived += byteBuffer.limit();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Connector: ["+this+"] - Received message size till now: [" + messageBytesReceived + "]");
        }

        if (messageBytesReceived == expectedMessageSize) {
            endOfMessage = true;
        }

        receivedBytes += byteBuffer.limit();
    }

    public void readFromServer() throws IOException {
		clientBuffer.writeFrom(serverChannel);
		if (clientBuffer.isReadyToRead())
			register();
	}

	public void writeToClient() throws IOException {
		clientBuffer.writeTo(clientChannel);
        if (clientBuffer.isReadyToWrite())
            register();
	}

	public void writeToServer() throws IOException {
		serverBuffer.writeTo(serverChannel);
		if (serverBuffer.isReadyToWrite())
			register();
	}

	public void register() throws ClosedChannelException {
		int clientOps = 0;
		if (serverBuffer.isReadyToWrite())
			clientOps |= SelectionKey.OP_READ;
		if (clientBuffer.isReadyToRead())
			clientOps |= SelectionKey.OP_WRITE;
		clientChannel.register(selector, clientOps, this);

        if (serverChannel != null) {
            int serverOps = 0;
            if (clientBuffer.isReadyToWrite())
                serverOps |= SelectionKey.OP_READ;
            if (serverBuffer.isReadyToRead())
                serverOps |= SelectionKey.OP_WRITE;
            serverChannel.register(selector, serverOps, this);
        }
	}

	private static void closeQuietly(SocketChannel channel) {
		if (channel != null) {
			try {
                channel.close();
            } catch (IOException exception) {
				LOGGER.warn("Could not close channel properly.", exception);
			}
		}
	}

	@Override
	public void register(Selector selector) {
		this.selector = selector;

		try {
			clientChannel.configureBlocking(false);

//			openServerChannel();

			register();
		} catch (final IOException exception) {
			destroy();

			LOGGER.warn("Could not connect to " + config.getRemoteHost() + ":" + config.getRemotePort(),
					exception);
		} catch (final UnresolvedAddressException exception) {
			destroy();
			LOGGER.warn("Could not connect to unresolved " + config.getRemoteHost() + ":" + config.getRemotePort(),
					exception);
		}
	}

    private void openServerChannel() throws IOException {
        final InetSocketAddress socketAddress = new InetSocketAddress(config.getRemoteHost(),
                config.getRemotePort());
        serverChannel = SocketChannel.open();
        serverChannel.connect(socketAddress);
        serverChannel.configureBlocking(false);
    }

    @Override
	public void process(final SelectionKey key) {
		try {
			if (key.channel() == clientChannel) {
				if (key.isValid() && key.isReadable()) {
					ByteBuffer partialReadByteBuffer = readFromClient();

					if (partialReadByteBuffer != null) {

                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug("Connector: ["+this+"] - Partial message received: " + StandardCharsets.ISO_8859_1.newDecoder().decode(partialReadByteBuffer).toString());
                            partialReadByteBuffer.rewind();
                        }

                        if (!activeCheckDetected) {
                            receivedBuffers.add(partialReadByteBuffer);

                            if (endOfMessage) {
                                applyFullMessage();
                                prepareToSendSuccess();
                            }
                        }
					}
				}
				if (key.isValid() && key.isWritable()) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Connector: ["+this+"] - Client channel is writable. Writing...");
                    }
                    writeToClient();
                }
			}

			if (key.channel() == serverChannel) {
				if (key.isValid() && key.isReadable())
					readFromServer();
				if (key.isValid() && key.isWritable())
					writeToServer();
			}
		} catch (final ClosedChannelException exception) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Connector: ["+this+"] - ClosedChannelException detected! Closing channels...");
            }
            destroy();
		} catch (final IOException exception) {
			destroy();
			LOGGER.warn("Could not process.", exception);
		} catch (final Throwable exception) {
			destroy();
			LOGGER.error("Expection caught", exception);
		}
	}

    private void applyFullMessage() {
		if (!receivedBuffers.isEmpty()) {
            ByteBuffer integrationBuffer = ByteBuffer.allocate(receivedBytes);
			for (ByteBuffer b : receivedBuffers)
				integrationBuffer.put(b);

			integrationBuffer.flip();
			try {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Connector: ["+this+"] - Full message before integration: " + StandardCharsets.ISO_8859_1.newDecoder().decode(integrationBuffer).toString());
					integrationBuffer.rewind();
				}
				integrationQueue.insert(integrationBuffer);
			} catch (CharacterCodingException exception) {
				LOGGER.error("Error to decode final message as string",exception);
			}
		}
	}

    private void prepareToSendSuccess() {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Connector: ["+this+"] - Preparing client buffer to send success");
            }
            clientBuffer.fillBuffer(ZBXD_SUCCESS_RESPONSE);
            clientChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, this);
        } catch (IOException e) {
            LOGGER.error("Error to send final success response to client",e);
            destroy();
        }
    }

    private ByteBuffer copyFrom(ByteBuffer source) {
        ByteBuffer target = ByteBuffer.allocate(source.limit());
        target.put(source);
        return target;
    }

	private static int parseSize(ByteBuffer byteBuffer, int start) {
		int size = 0;
		byteBuffer.mark();
		for (int i = 3; i > -1; i--) {
			size = (size * 256) + (byteBuffer.get(start + i) & 0xFF);
		}
		byteBuffer.reset();
		return size;
	}

	@Override
	public void destroy() {
		closeQuietly(clientChannel);
		closeQuietly(serverChannel);
        onDestroyCallback.run(this);
	}

    public boolean isOpen() {
        return clientChannel.isOpen();
    }

    public void applyOnDestroyCallback(TcpProxyConnectorEventCallback callback) {
        onDestroyCallback = callback;
    }
}