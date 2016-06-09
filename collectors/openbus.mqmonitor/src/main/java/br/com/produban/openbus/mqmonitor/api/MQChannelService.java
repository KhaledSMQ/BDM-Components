package br.com.produban.openbus.mqmonitor.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.produban.openbus.mqmonitor.model.MQChannel;

import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.headers.MQDataException;
import com.ibm.mq.headers.pcf.PCFException;
import com.ibm.mq.headers.pcf.PCFMessage;

@Service
public class MQChannelService {

	private static final String ALL_CHANNELS = "*";

	public List<MQChannel> getChannels(MQAgent commonMethods) throws PCFException, MQDataException, IOException {

		List<MQChannel> channels = new ArrayList<MQChannel>();

		PCFMessage request = new PCFMessage(MQConstants.MQCMD_INQUIRE_CHANNEL_STATUS);
		request.addParameter(MQConstants.MQCACH_CHANNEL_NAME, ALL_CHANNELS);

		PCFMessage[] response = commonMethods.agent.send(request);

		for (PCFMessage msg : response) {

			String name = (String) msg.getParameterValue(MQConstants.MQCACH_CHANNEL_NAME);

			if (name == null)
				continue;

			MQChannel channel = new MQChannel();

			channel.setChannelName(name.trim());

			Integer status = (Integer) msg.getParameterValue(MQConstants.MQIACH_CHANNEL_STATUS);
			channel.setChannelStatusId(status);
			channel.setChannelStatus(StatusMap.getChannelStatusByValue(status));
			Integer type = (Integer) msg.getParameterValue(MQConstants.MQIACH_CHANNEL_TYPE);
			channel.setChannelType(StatusMap.getChannelTypeByValue(type));

			channels.add(channel);
		}

		return channels;
	}
}
