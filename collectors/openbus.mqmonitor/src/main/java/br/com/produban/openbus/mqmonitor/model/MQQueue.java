package br.com.produban.openbus.mqmonitor.model;

public class MQQueue {

	private String queueName;
	private Integer queueSize;

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public Integer getQueueSize() {
		return queueSize;
	}

	public void setQueueSize(Integer queueSize) {
		this.queueSize = queueSize;
	}

}
