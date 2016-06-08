package br.com.produban.openbus.model;

import java.util.List;

import br.com.produban.openbus.model.avro.ZabbixAgentData;

public class Request {

	private String request;
	private String host;
	private String host_metadata;
	private String ip;
	private String port;
	private List<ZabbixAgentData> data;
	private String clock;
	private String ns;

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getHost_metadata() {
		return host_metadata;
	}

	public void setHost_metadata(String host_metadata) {
		this.host_metadata = host_metadata;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public List<ZabbixAgentData> getData() {
		return data;
	}

	public void setData(List<ZabbixAgentData> data) {
		this.data = data;
	}

	public String getClock() {
		return clock;
	}

	public void setClock(String clock) {
		this.clock = clock;
	}

	public String getNs() {
		return ns;
	}

	public void setNs(String ns) {
		this.ns = ns;
	}

	@Override
	public String toString() {
		return "Request [request=" + request + ", host=" + host + ", host_metadata=" + host_metadata + ", ip=" + ip
				+ ", port=" + port + ", data=" + data + ", clock=" + clock + ", ns=" + ns + "]";
	}

}
