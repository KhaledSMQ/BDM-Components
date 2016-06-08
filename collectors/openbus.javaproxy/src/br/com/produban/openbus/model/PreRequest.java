package br.com.produban.openbus.model;


public class PreRequest {

	private String request;

	public PreRequest(String request) {
		this.request = request;
	}
	
	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}
	
	@Override
	public String toString() {
		return "Request [data=" + request + "]";
	}

}
