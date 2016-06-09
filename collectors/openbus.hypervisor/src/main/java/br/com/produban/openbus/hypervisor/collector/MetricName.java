package br.com.produban.openbus.hypervisor.collector;

public enum MetricName {

	DATASTORE_USAGE("datastore.usage"),
	DATASTORE_FREE_SPACE("datastore.free.space"),
	DATASTORE_CAPACITY("datastore.capacity"),
	
	POWER_STATE("power.state"),
	
	CONNECTION_STATE("connection.state");
	private String key;

	private MetricName(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
