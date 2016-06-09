package br.com.produban.openbus.mqmonitor.model;

public class MQListener {

	private String listenerName;
	private String listenerStatus;
	private String listenerPort;

	public String getListenerName() {
		return listenerName;
	}

	public void setListenerName(String listenerName) {
		this.listenerName = listenerName;
	}

	public String getListenerStatus() {
		return listenerStatus;
	}

	public void setListenerStatus(String listenerStatus) {
		this.listenerStatus = listenerStatus;
	}

	public String getListenerPort() {
		return listenerPort;
	}

	public void setListenerPort(String listenerPort) {
		this.listenerPort = listenerPort;
	}

}
