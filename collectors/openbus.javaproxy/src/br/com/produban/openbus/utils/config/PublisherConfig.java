package br.com.produban.openbus.utils.config;

public class PublisherConfig {
	private final int listeningThreads;
	private final String integrationBrokerList;
	private final String toolName;

	public PublisherConfig(int listeningThreads, String integrationBrokerList, String toolName) {
		super();
		this.listeningThreads = listeningThreads;
		this.integrationBrokerList = integrationBrokerList;
		this.toolName = toolName;
	}

	public int getListeningThreads() {
		return listeningThreads;
	}

	public String getIntegrationBrokerList() {
		return integrationBrokerList;
	}

	public String getToolName() {
		return toolName;
	}
}
