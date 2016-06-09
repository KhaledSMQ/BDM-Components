package br.com.produban.openbus.mqmonitor.api;

import java.net.InetAddress;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.produban.openbus.mqmonitor.model.MQListener;
import br.com.produban.openbus.mqmonitor.model.MQQueueManager;
import br.com.produban.openbus.mqmonitor.model.MQServer;

@Service
public class MQServerMetricService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MQServerMetricService.class);

	@Autowired
	private MQQueueManagerService mqQueueManagerService;

	public MQServer getServerMetrics(String hostname, String port) throws Exception {

		MQAgent mqAgent = null;

		try {
			mqAgent = new MQAgent().createAgent(hostname, port);

			MQServer mqServer = new MQServer();

			InetAddress inet[] = InetAddress.getAllByName(hostname);
			mqServer.setHostName(inet[0].getHostName());
			mqServer.setIp(inet[0].getHostAddress());

			List<MQQueueManager> managers = mqQueueManagerService.getQueueManagers(mqAgent);
			mqServer.setQueueManager(managers);

			MQListener mqListener = mqQueueManagerService.getListener(mqAgent);

			mqServer.setMqListener(mqListener);
			return mqServer;

		} catch (Exception e) {
			LOGGER.error("Error at Agent Connecting for hostname=" + hostname + "port" + port, e);
			throw e;
		} finally {
			if (mqAgent != null) {
				mqAgent.DestroyAgent();
			}
		}
	}

}
