package br.com.produban.openbus.mqmonitor.config;

import java.util.List;

public class MQServerConfig {

	private String hostname;
	private List<String> ports;

	public String getHostname() {

		return hostname;
	}

	public void setHostname(String hostname) {

		this.hostname = hostname;
	}

	public List<String> getPorts() {

		return ports;
	}

	public void setPorts(List<String> ports) {

		this.ports = ports;
	}

}
