package br.com.produban.openbus.mqmonitor.api;

import java.util.HashMap;

import com.ibm.mq.constants.MQConstants;

public class StatusMap {

	private static final HashMap<Integer, String> CHANNEL_TYPES_MAP;

	private static final HashMap<Integer, String> CHANNEL_STATUS_MAP;

	private static final HashMap<Integer, String> LISTENER_STATUS_MAP;

	static {

		CHANNEL_TYPES_MAP = new HashMap<Integer, String>();
		CHANNEL_TYPES_MAP.put(MQConstants.MQCHT_CLUSSDR, "MQCHT_CLUSSDR");
		CHANNEL_TYPES_MAP.put(MQConstants.MQCHT_MQTT, "MQCHT_MQTT");
		CHANNEL_TYPES_MAP.put(MQConstants.MQCHT_RECEIVER, "MQCHT_RECEIVER");
		CHANNEL_TYPES_MAP.put(MQConstants.MQCHT_REQUESTER, "MQCHT_REQUESTER");
		CHANNEL_TYPES_MAP.put(MQConstants.MQCHT_SENDER, "MQCHT_SENDER");
		CHANNEL_TYPES_MAP.put(MQConstants.MQCHT_SERVER, "MQCHT_SERVER");
		CHANNEL_TYPES_MAP.put(MQConstants.MQCHT_SVRCONN, "MQCHT_SVRCONN");

		CHANNEL_STATUS_MAP = new HashMap<Integer, String>();
		CHANNEL_STATUS_MAP.put(MQConstants.MQCHS_BINDING, "MQCHS_BINDING");
		CHANNEL_STATUS_MAP.put(MQConstants.MQCHS_DISCONNECTED, "MQCHS_DISCONNECTED");
		CHANNEL_STATUS_MAP.put(MQConstants.MQCHS_INACTIVE, "MQCHS_INACTIVE");
		CHANNEL_STATUS_MAP.put(MQConstants.MQCHS_INITIALIZING, "MQCHS_INITIALIZING");
		CHANNEL_STATUS_MAP.put(MQConstants.MQCHS_PAUSED, "MQCHS_PAUSED");
		CHANNEL_STATUS_MAP.put(MQConstants.MQCHS_REQUESTING, "MQCHS_REQUESTING");
		CHANNEL_STATUS_MAP.put(MQConstants.MQCHS_RETRYING, "MQCHS_RETRYING");
		CHANNEL_STATUS_MAP.put(MQConstants.MQCHS_RUNNING, "MQCHS_RUNNING");
		CHANNEL_STATUS_MAP.put(MQConstants.MQCHS_STARTING, "MQCHS_STARTING");
		CHANNEL_STATUS_MAP.put(MQConstants.MQCHS_STOPPED, "MQCHS_STOPPED");
		CHANNEL_STATUS_MAP.put(MQConstants.MQCHS_STOPPING, "MQCHS_STOPPING");
		CHANNEL_STATUS_MAP.put(MQConstants.MQCHS_SWITCHING, "MQCHS_SWITCHING");

		LISTENER_STATUS_MAP = new HashMap<Integer, String>();
		LISTENER_STATUS_MAP.put(MQConstants.MQSVC_STATUS_RETRYING, "MQSVC_STATUS_RETRYING");
		LISTENER_STATUS_MAP.put(MQConstants.MQSVC_STATUS_RUNNING, "MQSVC_STATUS_RUNNING");
		LISTENER_STATUS_MAP.put(MQConstants.MQSVC_STATUS_STARTING, "MQSVC_STATUS_STARTING");
		LISTENER_STATUS_MAP.put(MQConstants.MQSVC_STATUS_STOPPED, "MQSVC_STATUS_STOPPED");
		LISTENER_STATUS_MAP.put(MQConstants.MQSVC_STATUS_STOPPING, "MQSVC_STATUS_STOPPING");
	}

	public static String getChannelTypeByValue(int channelConstantValue) {
		return CHANNEL_TYPES_MAP.get(channelConstantValue);
	}

	public static String getChannelStatusByValue(int channelConstantValue) {

		return CHANNEL_STATUS_MAP.get(channelConstantValue);
	}

	public static String getListenerStatusByValue(int listenerStatusConstantValue) {
		return LISTENER_STATUS_MAP.get(listenerStatusConstantValue);
	}

}
