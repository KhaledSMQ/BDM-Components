package br.com.produban.openbus.utils;

import br.com.produban.openbus.model.PreRequest;
import br.com.produban.openbus.model.Request;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class MessageTransformer {

	private final static Logger LOGGER = LoggerFactory.getLogger(MessageTransformer.class);
	
	private static int index = 4;
	private static String prefixZBXD = "ZBXD";
	private static byte typeByte = 0x01;

	private static Request parseRequest(String input) {
		Request request = null;
		
		try {
			request = new Gson().fromJson(input, Request.class);
		} catch (Exception exception) {
			LOGGER.error(input, exception);
			return null;
		}
		return request;		
	}

	public static PreRequest parseByteBuffer(ByteBuffer byteBuffer) {
		LOGGER.debug("Parsing new Message");
		Charset charset = Charset.forName("ISO-8859-1");
		CharsetDecoder decoder = charset.newDecoder();
		CharBuffer charBuffer = null;

		try {
			byte[] pref = new byte[4];
			byteBuffer.get(pref, 0, 4);
			if ((new String(pref, "UTF-8")).equals(prefixZBXD)) {
                LOGGER.debug("\tPrefix is: " + prefixZBXD);
				if (typeByte == byteBuffer.get(index)) {
					LOGGER.debug("\tType Byte is: " + typeByte);
					if (byteBuffer.limit() - byteBuffer.position() < 8) {
						LOGGER.error("\tMessage is shorter than expected.");
						return null;
					}

					byteBuffer.position(13);
				} else {
					LOGGER.debug("\tType byte is: " + byteBuffer.get(index));
					return null;
				}

			} else {
				LOGGER.debug("No prefix ZBXD.");
				if (byteBuffer.get(0) == 0x7b) {
					LOGGER.debug("\tNo prefix at all.");
					byteBuffer.position(0);
				} else {
					LOGGER.debug("\tPrefix should have an 8-byte DataLen.");
					if (byteBuffer.limit() - byteBuffer.position() < 8) {
						LOGGER.error("\tMessage is shorter than expected.");
						return null;
					}

					byteBuffer.position(8);
				}
				
			}
			charBuffer = decoder.decode(byteBuffer);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}

		String json = charBuffer.toString();
		LOGGER.debug("Main ByteBuffer: '" + json + "'");

		return new PreRequest(json);
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

	public static Request parseStringToRequest(String json) {
		Request request = null;

		if (!json.isEmpty()) {
			try {
				request = MessageTransformer.parseRequest(json.trim());
			} catch (JsonSyntaxException e) {
				LOGGER.error(e.getMessage(), e);
				return null;
			} catch (StringIndexOutOfBoundsException e) {
				LOGGER.error(e.getMessage(), e);
				return null;
			}

		}
		return request;
	}

}