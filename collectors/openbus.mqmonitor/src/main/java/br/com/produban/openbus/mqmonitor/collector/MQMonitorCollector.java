package br.com.produban.openbus.mqmonitor.collector;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.produban.openbus.ingestor.OpenbusDataIngestion;
import br.com.produban.openbus.mqmonitor.api.MQServerMetricService;
import br.com.produban.openbus.mqmonitor.api.StatusMap;
import br.com.produban.openbus.mqmonitor.config.MQServerConfig;
import br.com.produban.openbus.mqmonitor.model.MQChannel;
import br.com.produban.openbus.mqmonitor.model.MQQueue;
import br.com.produban.openbus.mqmonitor.model.MQQueueManager;
import br.com.produban.openbus.mqmonitor.model.MQServer;
import br.com.produban.openbus.security.exceptions.BusinessException;

@Service
public class MQMonitorCollector {

	private static final Logger LOGGER = LoggerFactory.getLogger(MQMonitorCollector.class);

	private static final String MQ_MONITOR_CHANNEL_TOOL_NAME = "MQMonitorChannel";

	private static final String MQ_MONITOR_QUEUE_TOOL_NAME = "MQMonitorQueue";

	private static final String CHANNEL_STATUS_METRIC_NAME = "channel.status";

	private static final String QUEUE_SIZE_METRIC_NAME = "queue.size";

	@Resource
	@Qualifier("serverConfigList")
	private List<MQServerConfig> serverConfigList;

	@Autowired
	private OpenbusDataIngestion ingestor;

	@Autowired
	private MQServerMetricService mqServerMetricService;

	@Autowired
	private Schema mqMonitorChannelSchema;

	@Autowired
	private Schema mqMonitorQueueSchema;

	@Async
	public void collect(MQServerConfig cfg) {

		for (String port : cfg.getPorts()) {

			LOGGER.info("Collecting MQ metrics hostname=" + cfg.getHostname() + " port=" + port);

			try {

				MQServer mqServer = mqServerMetricService.getServerMetrics(cfg.getHostname(), port);
				sendToIngestor(mqServer);

			} catch (Exception e) {
				LOGGER.error("Error collecting metrics from hotname=" + cfg.getHostname() + " port=" + port, e);
			}
		}
	}

	private void sendToIngestor(MQServer mqServer) throws BusinessException {

		for (MQQueueManager queueManager : mqServer.getQueueManager()) {

			sendQueueToIngestor(mqServer, queueManager);
			sendChannelToIngestor(mqServer, queueManager);
		}
	}

	private void sendQueueToIngestor(MQServer mqServer, MQQueueManager queueManager) throws BusinessException {

		LOGGER.info("Send to Ingestor Queue size=" + queueManager.getQueues().size());

		for (MQQueue queue : queueManager.getQueues()) {
			GenericRecord record = createBaseRecord(mqServer, queueManager, mqMonitorQueueSchema);

			record.put("metricName", QUEUE_SIZE_METRIC_NAME);
			record.put("queueName", queue.getQueueName());
			record.put("value", queue.getQueueSize());
			record.put("extraInfo", new HashMap<>());

			ingestor.ingest(MQ_MONITOR_QUEUE_TOOL_NAME, record);
		}
	}

	private void sendChannelToIngestor(MQServer mqServer, MQQueueManager queueManager) throws BusinessException {

		LOGGER.info("Send to Ingestor Channel size=" + queueManager.getChannels().size());

		for (MQChannel channel : queueManager.getChannels()) {
			GenericRecord record = createBaseRecord(mqServer, queueManager, mqMonitorChannelSchema);

			record.put("channelType", channel.getChannelType());
			record.put("channelName", channel.getChannelName());
			record.put("metricName", CHANNEL_STATUS_METRIC_NAME);
			record.put("value", channel.getChannelStatusId());
			record.put("extraInfo", new HashMap<String, String>());

			ingestor.ingest(MQ_MONITOR_CHANNEL_TOOL_NAME, record);
		}
	}

	private GenericRecord createBaseRecord(MQServer mqServer, MQQueueManager queueManager, Schema schema) {
		GenericRecord record = new GenericData.Record(schema);

		record.put("hostname", mqServer.getHostName());
		record.put("ip", mqServer.getIp());
		record.put("timestamp", String.valueOf(System.currentTimeMillis()));
		record.put("listenerName", mqServer.getMqListener().getListenerName());
		record.put("listenerStatus", mqServer.getMqListener().getListenerStatus());
		record.put("listenerPort", mqServer.getMqListener().getListenerPort());
		record.put("queueManager", queueManager.getQueueManager());
		record.put("queueVersion", queueManager.getQueueVersion());
		return record;
	}

}
