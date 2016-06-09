package br.com.produban.openbus.mqmonitor.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class MQQueueManager implements Serializable {

	private static final long serialVersionUID = -4472777766436486495L;

	private String queueManager;
	private String queueVersion;

	private List<MQChannel> channels;

	private List<MQQueue> queues;

	public String getQueueManager() {
		return queueManager;
	}

	public void setQueueManager(String queueManager) {
		this.queueManager = queueManager;
	}

	public String getQueueVersion() {
		return queueVersion;
	}

	public void setQueueVersion(String queueVersion) {
		this.queueVersion = queueVersion;
	}

	public List<MQChannel> getChannels() {
		if (channels == null)
			return Collections.emptyList();
		return channels;
	}

	public void setChannels(List<MQChannel> channels) {
		this.channels = channels;
	}

	public List<MQQueue> getQueues() {
		if (queues == null)
			return Collections.emptyList();
		return queues;
	}

	public void setQueues(List<MQQueue> queues) {
		this.queues = queues;
	}

}
