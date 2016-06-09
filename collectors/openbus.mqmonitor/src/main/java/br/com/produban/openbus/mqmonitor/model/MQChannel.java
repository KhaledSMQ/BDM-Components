package br.com.produban.openbus.mqmonitor.model;

public class MQChannel {

	private String channelName;
	private String channelStatus;
	private Integer channelStatusId;
	private String channelType;

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelStatus() {
		return channelStatus;
	}

	public void setChannelStatus(String channelStatus) {
		this.channelStatus = channelStatus;
	}

	public Integer getChannelStatusId() {
		return channelStatusId;
	}

	public void setChannelStatusId(Integer channelStatusId) {
		this.channelStatusId = channelStatusId;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

}
