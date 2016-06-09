package br.com.produban.openbus.mqmonitor.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.produban.openbus.mqmonitor.model.MQChannel;
import br.com.produban.openbus.mqmonitor.model.MQListener;
import br.com.produban.openbus.mqmonitor.model.MQQueue;
import br.com.produban.openbus.mqmonitor.model.MQQueueManager;

import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.headers.MQDataException;
import com.ibm.mq.headers.pcf.PCFException;
import com.ibm.mq.headers.pcf.PCFMessage;

@Component
public class MQQueueManagerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MQQueueManagerService.class);

	@Autowired
	private MQQueueService mqQueueService;

	@Autowired
	private MQChannelService mqChannelService;

	@Autowired
	private MQListenerService mqListenerService;

	public List<MQQueueManager> getQueueManagers(MQAgent mqAgent) throws PCFException, MQDataException, IOException {

		List<MQQueueManager> managers = new ArrayList<MQQueueManager>();

		PCFMessage request = new PCFMessage(MQConstants.MQCMD_INQUIRE_Q_MGR);
		PCFMessage[] response = mqAgent.agent.send(request);

		for (PCFMessage msg : response) {

			MQQueueManager manager = new MQQueueManager();

			String name = ((String) msg.getParameterValue(MQConstants.MQCA_Q_MGR_NAME)).trim();

			manager.setQueueManager(name);

			String queueVersion = formatVersion(String.valueOf((Integer) msg.getParameterValue(MQConstants.MQIA_COMMAND_LEVEL)));
			manager.setQueueVersion(queueVersion);

			List<MQQueue> activeQueues = mqQueueService.getQueues(mqAgent);
			manager.setQueues(activeQueues);

			List<MQChannel> channels = mqChannelService.getChannels(mqAgent);
			manager.setChannels(channels);

			managers.add(manager);
		}
		return managers;
	}

	public MQListener getListener(MQAgent mqAgent) throws Exception {
		return mqListenerService.getListener(mqAgent);
	}

	private String formatVersion(String version) {

		String formatedVersion = "";
		if (version != null && version.length() > 1) {
			try {
				for (int i = 0; i < version.length(); i++) {
					if ((i + 1) < version.length())
						formatedVersion = formatedVersion + version.charAt(i) + ".";
					else
						formatedVersion = formatedVersion + version.charAt(i);

				}
			} catch (Exception e) {
				LOGGER.error("Error formating MQ version: " + version);
			}
		} else
			formatedVersion = version;
		return formatedVersion;
	}

}
