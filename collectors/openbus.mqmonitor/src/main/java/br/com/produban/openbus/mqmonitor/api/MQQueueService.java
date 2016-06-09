package br.com.produban.openbus.mqmonitor.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.produban.openbus.mqmonitor.model.MQQueue;

import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.headers.MQDataException;
import com.ibm.mq.headers.pcf.PCFException;
import com.ibm.mq.headers.pcf.PCFMessage;

@Service
public class MQQueueService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MQQueueService.class);

	public List<MQQueue> getQueues(MQAgent commonMethods) throws PCFException, MQDataException, IOException {

		List<MQQueue> queues = new ArrayList<MQQueue>();

		PCFMessage request = new PCFMessage(MQConstants.MQCMD_INQUIRE_Q);
		request.addParameter(MQConstants.MQCA_Q_NAME, "*");
		request.addParameter(MQConstants.MQIA_Q_TYPE, MQConstants.MQQT_LOCAL);

		PCFMessage[] response = commonMethods.agent.send(request);

		for (PCFMessage msg : response) {

			String queueName = (String) msg.getParameterValue(MQConstants.MQCA_Q_NAME);

			if (queueName == null)
				continue;

			if (!msg.getParameterValue(MQConstants.MQCA_Q_NAME).toString().startsWith("SYSTEM")) {

				MQQueue queue = new MQQueue();

				String name = msg.getParameterValue(MQConstants.MQCA_Q_NAME).toString().trim();
				queue.setQueueName(name);

				if (msg.getParameterValue(MQConstants.MQIA_CURRENT_Q_DEPTH) == null) {

					LOGGER.warn("Queue Messages wrong value:" + msg.getParameterValue(MQConstants.MQIA_CURRENT_Q_DEPTH));
					queue.setQueueSize(0);

				} else {
					String currentMessageCount = msg.getParameterValue(MQConstants.MQIA_CURRENT_Q_DEPTH).toString().trim();
					queue.setQueueSize(Integer.parseInt(currentMessageCount));
				}

				queues.add(queue);
			}
		}

		return queues;
	}

}
