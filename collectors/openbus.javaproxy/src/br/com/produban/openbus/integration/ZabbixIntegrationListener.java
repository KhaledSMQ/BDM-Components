package br.com.produban.openbus.integration;

import br.com.produban.openbus.model.avro.ZabbixAgentData;
import com.github.terma.javaniotcpproxy.TcpProxyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZabbixIntegrationListener implements Runnable {

    private final static Logger LOGGER = LoggerFactory.getLogger(ZabbixListenerProcessor.class);

    private final IntegrationQueue<ZabbixAgentData> zabbixIntegrationQueue;
    private final TcpProxyConfig config;

    public ZabbixIntegrationListener(IntegrationQueue<ZabbixAgentData> zabbixIntegrationQueue,
                                     TcpProxyConfig config) {
        this.zabbixIntegrationQueue = zabbixIntegrationQueue;
        this.config = config;
    }

    @Override
    public void run() {
        ListenerProcessor zabbixListenerProcessor = new ZabbixListenerProcessor(zabbixIntegrationQueue,config);

        while (!Thread.interrupted()) {
            try {
                zabbixListenerProcessor.compute();
                Thread.sleep(1000);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

}
