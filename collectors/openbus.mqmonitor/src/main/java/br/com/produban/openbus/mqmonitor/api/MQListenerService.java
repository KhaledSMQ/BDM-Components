package br.com.produban.openbus.mqmonitor.api;

import org.springframework.stereotype.Service;

import br.com.produban.openbus.mqmonitor.model.MQListener;

import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.headers.pcf.PCFMessage;

@Service
public class MQListenerService {

	private static final int FIRST_ELEMENT = 0;

	private static final String ALL_LISTENERS = "*";

	public MQListener getListener(MQAgent mqAgent) throws Exception {

		int[] pcfAttrs = new int[] { MQConstants.MQIACF_ALL };

		PCFMessage request = new PCFMessage(MQConstants.MQCMD_INQUIRE_LISTENER_STATUS);
		request.addParameter(MQConstants.MQCACH_LISTENER_NAME, ALL_LISTENERS);
		request.addParameter(MQConstants.MQIACF_LISTENER_STATUS_ATTRS, pcfAttrs);

		PCFMessage[] response = mqAgent.agent.send(request);

		if (response.length > 1) {
			throw new Exception("Listener must contain only one element for hostname=" + mqAgent.host + " port=" + mqAgent.port);
		}

		PCFMessage listenerMessage = response[FIRST_ELEMENT];

		MQListener listener = new MQListener();
		listener.setListenerName((String) listenerMessage.getParameterValue(MQConstants.MQCACH_LISTENER_NAME));
		listener.setListenerPort(String.valueOf((Integer) listenerMessage.getParameterValue(MQConstants.MQIACH_PORT)));
		listener.setListenerStatus(String.valueOf((Integer) listenerMessage.getParameterValue(MQConstants.MQIACH_LISTENER_STATUS)));

		return listener;
	}
}
