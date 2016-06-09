package br.com.produban.openbus.mqmonitor.model;

import java.util.ArrayList;
import java.util.List;

public class MQServer {

	private String hostName;
	private String ip;
	private MQListener mqListener;
	private List<MQQueueManager> queueManager = new ArrayList<MQQueueManager>();

	public MQListener getMqListener() {
		return mqListener;
	}

	public void setMqListener(MQListener mqListener) {
		this.mqListener = mqListener;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public List<MQQueueManager> getQueueManager() {
		return queueManager;
	}

	public void setQueueManager(List<MQQueueManager> queueManager) {
		this.queueManager = queueManager;
	}

}
