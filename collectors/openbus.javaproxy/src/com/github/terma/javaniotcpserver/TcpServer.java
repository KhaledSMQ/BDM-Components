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

import br.com.produban.openbus.integration.IntegrationQueue;
import com.github.terma.javaniotcpproxy.TcpProxyConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Simple TCP server based on NIO.
 * <p/>
 * Server use workers for process incoming client connections.
 * <p/>
 * Worker is thread, it waits on own selector {@link java.nio.channels.Selector}
 * <p/>
 * Only one worker processes accept for incoming client connection, after
 * that this worker uses @{link TcpServerHandlerFactory} for create
 * handler @{link TcpServerHandler} and add it to not started handlers queue. All workers
 * have access to this queue.
 * <p/>
 * Worker has next lifecycle: try to get one not started handler from queue
 * if it exists register it, then wait on selector with timeout, get IO events
 * for each event get attached handler from key and process it.
 * After that worker returns to step with queue.
 *
 * @see TcpServerConfig
 * @see TcpServerHandler
 * @see TcpServerHandlerFactory
 */
public class TcpServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(TcpServer.class);

    private final TcpServerConfig config;
    private final String name;
    private final IntegrationQueue integrationQueue;

    private Queue<TcpServerHandler> handlers;
    private TcpServerWorker[] workers;
    private Thread idleSocketChecker;

    /**
     * @param config - config
     * @param integrationQueue
     * @throws IllegalArgumentException - when worker count < 1
     */
    public TcpServer(final TcpServerConfig config, final IntegrationQueue integrationQueue) {
        this.integrationQueue = integrationQueue;
        if (config == null)
            throw new NullPointerException("Please specify config! Thx!");

        this.config = config;
        name = "TcpServer on port " + config.getPort();
        this.idleSocketChecker = new Thread(new IdleClientChecker());
        this.idleSocketChecker.start();
    }

    /**
     * This method starts waiting incoming connections for proxy to remote host.
     * Method return control when all worker will be started, it isn't block.
     *
     * @throws UnsupportedOperationException - if you try to start already started connector
     */
    public void start() {
        if (workers != null) throw new UnsupportedOperationException("Please shutdown connector!");

        LOGGER.info("Starting " + name + " with " + config.getWorkerCount() + " workers");

        handlers = new ConcurrentLinkedQueue<>();
        handlers.add(new TcpServerAcceptor(config, handlers));

        workers = new TcpServerWorker[config.getWorkerCount()];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new TcpServerWorker(handlers);
            workers[i].setName("TcpServerWorker #" + i);
        }

        for (final Thread worker : workers) worker.start();

        LOGGER.info(name + " started");
    }

    /**
     * Shutdown connector.
     * <p/>
     * This method wait when all resources will be closed.
     * You can call this method any time.
     * No problem and exceptions if you try to shutdown connector twice without start.
     */
    public void shutdown() {
        if (workers == null) {
            LOGGER.info(name + " already been shutdown");
            return;
        }

        this.idleSocketChecker.interrupt();

        LOGGER.info("Starting shutdown " + name);

        for (final Thread worker : workers) {
            worker.interrupt();
            try {
                worker.join();
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
        workers = null;

        TcpServerHandler handler;
        while ((handler = handlers.poll()) != null) handler.destroy();
        while (integrationQueue.getSize() > 0) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                LOGGER.error("Fail to wait for integration queue to be drained", e);
            }
        }
        handlers = null;

        LOGGER.info(name + " was shutdown");
    }

    private void checkIdleClients() {

        for (TcpServerWorker worker : workers) {

            Iterator<Map.Entry<TcpProxyConnector, Long>> iter = worker.getClients().entrySet().iterator();
            while (iter.hasNext()) {
                try {
                    Map.Entry<TcpProxyConnector, Long> entry = iter.next();
                    TcpProxyConnector client = entry.getKey();
                    if (!client.isOpen()) {
                        LOGGER.info("[IdleClientChecker] Client is already closed. Removing...");
                        iter.remove();
                    } else {
                        long mills = entry.getValue();
                        double idlenessInSeconds = (System.currentTimeMillis() - mills) / 1000D;
                        if (idlenessInSeconds >= config.getIdleClientTimeout()) {
                        /* key is idle for */
                            LOGGER.info("[IdleClientChecker] Client is idle for " + Math.round(idlenessInSeconds) + ", closing...");
                            client.destroy();
                            iter.remove();
                        }
                    }
                } catch (Exception e) {
                    LOGGER.error("[IdleClientChecker] ", e);
                }
            }
            LOGGER.info("[checkIdleClients] # of clients connected on {} : {}",worker.getName(),worker.getClients().size());
        }
    }

    private class IdleClientChecker implements Runnable {
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    /* Wait for 10 Seconds */
                    Thread.sleep(config.getIdlenessTickInterval() * 1000);
                    checkIdleClients();
                }
            } catch (InterruptedException e) {
                LOGGER.warn("[IdleClientChecker]<run> IdleClientChecker thread stopped", e);
            }
        }
    }
}
