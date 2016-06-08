package br.com.produban.openbus.integration;

import br.com.produban.openbus.model.avro.ZabbixAgentData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

public class KafkaIntegrationListener implements Runnable {

	private final static Logger LOGGER = LoggerFactory.getLogger(KafkaIntegrationListener.class);
	
	private final IntegrationQueue<ByteBuffer> kafkaIntegrationQueue;
    private final IntegrationQueue<ZabbixAgentData> zabbixIntegrationQueue;
	private final String topicName;

	public KafkaIntegrationListener(IntegrationQueue<ByteBuffer> kafkaIntegrationQueue, IntegrationQueue<ZabbixAgentData> zabbixIntegrationQueue, String topicName) {
		this.kafkaIntegrationQueue = kafkaIntegrationQueue;
        this.zabbixIntegrationQueue = zabbixIntegrationQueue;
		this.topicName = topicName;
	}

	@Override
	public void run() {
		KafkaListenerProcessor kafkaListenerProcessor = new KafkaListenerProcessor(kafkaIntegrationQueue, zabbixIntegrationQueue, topicName);

		while (!Thread.interrupted()) {
			try {
				kafkaListenerProcessor.compute();
				Thread.sleep(100);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}

	}
}
