package br.com.produban.openbus.integration;

import br.com.produban.openbus.model.Request;
import br.com.produban.openbus.model.avro.ZabbixAgentData;
import com.github.terma.javaniotcpproxy.TcpProxyConfig;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.List;

public class ZabbixListenerProcessor implements ListenerProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(ZabbixListenerProcessor.class);

    private final IntegrationQueue<ZabbixAgentData> zabbixIntegrationQueue;
    private final TcpProxyConfig config;

    public ZabbixListenerProcessor(IntegrationQueue<ZabbixAgentData> zabbixIntegrationQueue,
                                  TcpProxyConfig config) {
        this.zabbixIntegrationQueue = zabbixIntegrationQueue;
        this.config = config;
    }

    @Override
    public void compute() {
        List<ZabbixAgentData> discoveryData = zabbixIntegrationQueue.getAll();
        if (discoveryData != null && !discoveryData.isEmpty()) {
            LOGGER.info("Pushing "+discoveryData.size()+" discovery objects to Zabbix");
            String agentDataJson = buildJson(discoveryData);
            pushToZabbix(agentDataJson);
        }
    }

    private void pushToZabbix(String agentDataJson) {
        LOGGER.debug("Sending discovery data to Zabbix as follows: " + agentDataJson);
        try {
            Socket serverSocket = new Socket(config.getRemoteHost(),config.getRemotePort());
            Writer writer = new OutputStreamWriter(serverSocket.getOutputStream());
            writer.write(agentDataJson);
            writer.flush();
            writer.close();
            serverSocket.close();
        } catch (IOException e) {
            LOGGER.error("Fail to push discovery data to Zabbix [host="+config.getRemoteHost()+"|port="+config.getRemotePort()+"]",e);
        }
    }

    private String buildJson(List<ZabbixAgentData> data) {
        Request request = new Request();
        request.setRequest("agent data");
        request.setData(data);
        return new GsonBuilder().create().toJson(request,Request.class);
    }
}
