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

package com.github.terma.javaniotcpserver;

import com.github.terma.javaniotcpproxy.TcpProxyConnector;
import com.github.terma.javaniotcpproxy.TcpProxyConnectorEventCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TcpServerWorker extends Thread {

    private final static long SELECTOR_TIMEOUT = 100L;
    private final static Logger LOGGER = LoggerFactory.getLogger(TcpServerWorker.class);

    private final Queue<TcpServerHandler> handlers;
    private Selector selector;

    private ConcurrentHashMap<TcpProxyConnector, Long> clients = new ConcurrentHashMap<>();

    public TcpServerWorker(final Queue<TcpServerHandler> handlers) {
        super();
        this.handlers = handlers;
    }

    @Override
    public void run() {
        try {
            selector = Selector.open();

            while (!Thread.interrupted()) {
                TcpServerHandler newHandler = handlers.poll();
                if (newHandler != null) {
                    newHandler.register(selector);
                    if (TcpProxyConnector.class.isInstance(newHandler))
                        ((TcpProxyConnector)newHandler).applyOnDestroyCallback(new TcpProxyConnectorEventCallback() {
                            @Override
                            public void run(TcpProxyConnector connector) {
                                clients.remove(connector);
                            }
                        });
                }

                int readyCount = selector.select(SELECTOR_TIMEOUT);

                if (readyCount > 0) {
                    final Set<SelectionKey> keys = selector.selectedKeys();
                    for (final SelectionKey key : keys) {
                        final TcpServerHandler handler = (TcpServerHandler) key.attachment();

                        if (TcpProxyConnector.class.isInstance(handler))
                            clients.put((TcpProxyConnector) handler, System.currentTimeMillis());

                        handler.process(key);
                    }
                    keys.clear();
                }
            }
        } catch (final IOException exception) {
            LOGGER.error("Problem with selector, worker will be stopped!", exception);
        } finally {
            if (selector != null) {
                try {
                    selector.close();
                } catch (IOException exception) {
                    LOGGER.warn("Could not close selector properly.", exception);
                }
            }
        }
    }

    public ConcurrentHashMap<TcpProxyConnector, Long> getClients() {
        return clients;
    }
}
