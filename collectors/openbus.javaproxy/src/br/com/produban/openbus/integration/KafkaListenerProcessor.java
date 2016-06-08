package br.com.produban.openbus.integration;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.terma.javaniotcpproxy.TcpProxyRunner;

import br.com.openbus.publisher.kafka.KafkaAvroPublisher;
import br.com.produban.openbus.ingestor.OpenbusDataIngestion;
import br.com.produban.openbus.ingestor.OpenbusIngestion;
import br.com.produban.openbus.model.PreRequest;
import br.com.produban.openbus.model.Request;
import br.com.produban.openbus.model.avro.ZabbixAgentData;
import br.com.produban.openbus.security.exceptions.BusinessException;
import br.com.produban.openbus.utils.MessageTransformer;
import redis.clients.jedis.JedisPool;

public class KafkaListenerProcessor implements ListenerProcessor {

	private final static Logger LOGGER = LoggerFactory.getLogger(KafkaListenerProcessor.class);

	private final JedisPool jedisPool;
	private final KafkaAvroPublisher<GenericRecord> publisher;
	
	private final IntegrationQueue<ByteBuffer> kafkaIntegrationQueue;
	private final IntegrationQueue<ZabbixAgentData> zabbixIntegrationQueue;

	private final String resolvedTool;

	private OpenbusIngestion ingestion;

	@SuppressWarnings("unchecked")
	public KafkaListenerProcessor(IntegrationQueue<ByteBuffer> kafkaIntegrationQueue,
			IntegrationQueue<ZabbixAgentData> zabbixIntegrationQueue,
			String topicName) {

		this.jedisPool = new JedisPool(loadJedisURI());
		
		LOGGER.debug("Jedis ping");
		LOGGER.debug(this.jedisPool.getResource().ping());
		
		this.publisher = new KafkaAvroPublisher<GenericRecord>(
				TcpProxyRunner.getApplicationConfig().getKafkaBrokerList(),
				TcpProxyRunner.getApplicationConfig().isKafkaRequiredAcks(), 
				TcpProxyRunner.getApplicationConfig().isKafkaIsAsync(), 
				TcpProxyRunner.getApplicationConfig().getKafkaBatchNumMessages());

		this.kafkaIntegrationQueue = kafkaIntegrationQueue;
		this.zabbixIntegrationQueue = zabbixIntegrationQueue;
		this.resolvedTool = TcpProxyRunner.getPublisherConfig().getToolName();

		LOGGER.debug("Iniciando instancia do ingestor.");
		
		ingestion = OpenbusDataIngestion.OpenbusDataIngestionBuilder.aIngestionConfiguration()
				.withKafkaAvroPublisher(publisher)
				.withJedisPool(jedisPool)
				.withSuccessTopic(TcpProxyRunner.getApplicationConfig().getKafkaSuccessTopic())
				.withFailureTopic(TcpProxyRunner.getApplicationConfig().getKafkaFailureTopic()).build();
	}

	@Override
	public void compute() {
		LinkedList<ByteBuffer> list = kafkaIntegrationQueue.getAll(100);
		
		for (ByteBuffer byteBuffer : list) {

			PreRequest preRequest;
			preRequest = MessageTransformer.parseByteBuffer(byteBuffer);

			if (preRequest == null) {
				continue;
			}

			LOGGER.debug("Final Message:  '" + preRequest.getRequest() + "'");
			Request request = MessageTransformer.parseStringToRequest(preRequest.getRequest());

			if (request != null) {

				GenericRecord record = new GenericData.Record(TcpProxyRunner.getApplicationConfig().getSchema());

				if (request.getRequest().equals("agent data")) {
					for (ZabbixAgentData data : request.getData()) {
						if (data.getValue().contains("{#")) {
							if (LOGGER.isDebugEnabled()){
								LOGGER.debug("This data is going to Zabbix: " + data);
							}

							zabbixIntegrationQueue.insert(data);

						} else {
							if (LOGGER.isDebugEnabled()){
								LOGGER.debug("This data is going to ingestor: " + data);
							}

							if(data.getHost().contains(".")){
								record.put("hostname", data.getHost() != null ? data.getHost().substring(0, data.getHost().indexOf(".")).toUpperCase() : null);
							}
							else {
								record.put("hostname", data.getHost().toUpperCase());
							}
							record.put("metric", data.getKey());
							record.put("value", data.getValue());
							record.put("timestamp", new Date(new Long(data.getClock()) * 1000L).getTime() + "");
							record.put("extraInfo", new HashMap<String, String>());

							try {
								ingestion.ingest(this.resolvedTool, record);
							} catch (BusinessException e) {
								LOGGER.error(e.getMessage());
							}
						}
					}
				} else if (request.getRequest().equals("sender data")) {
					for (ZabbixAgentData agentData : request.getData()) {
						if (agentData.getClock() == null || agentData.getNs() == null) {

							if(agentData.getHost().contains(".")){
								record.put("hostname", agentData.getHost() != null ? agentData.getHost().substring(0, agentData.getHost().indexOf(".")).toUpperCase() : null);
							}
							else {
								record.put("hostname", agentData.getHost().toUpperCase());
							}
							record.put("metric", agentData.getKey());
							record.put("value", agentData.getValue());
							record.put("timestamp", System.currentTimeMillis() + "");
							record.put("extraInfo", new HashMap<String, String>());

						}

						try {
							ingestion.ingest(this.resolvedTool, record);
						} catch (BusinessException e) {
							LOGGER.error(e.getMessage());
						}
					}
				}
			}
		}
	}
	
	private URI loadJedisURI() {
		URI uri = null;
		try {
			uri = new URI("redis", TcpProxyRunner.getApplicationConfig().getRedisUserInfo(),
					TcpProxyRunner.getApplicationConfig().getRedisHost(),
					TcpProxyRunner.getApplicationConfig().getRedisPort(), String.format("/%s", 1), null, null);
		} 
		catch (URISyntaxException e) {
			LOGGER.error("", e);
		}
		
		return uri;
	}
}