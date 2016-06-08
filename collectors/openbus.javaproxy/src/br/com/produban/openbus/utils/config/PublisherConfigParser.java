package br.com.produban.openbus.utils.config;

import java.util.Properties;

public class PublisherConfigParser {

	private static final String LISTENIG_THREADS = "listening.threads";
	private static final String INTEGRATION_TIER_BROKER_LIST = "integration.tier.broker.list";
    private static final String TOOL_NAME = "tool.name";
	
	public static PublisherConfig parse(Properties properties) {
		final int listeningThreads = findIntegerProperty(properties, LISTENIG_THREADS);
		final String integrationTierBrokerList = findProperty(properties, INTEGRATION_TIER_BROKER_LIST);
        final String toolName = findProperty(properties, TOOL_NAME);

		return new PublisherConfig(listeningThreads, integrationTierBrokerList, toolName);
	}
	
    private static int findIntegerProperty(Properties properties, String key) {
        final String value = findProperty(properties, key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "Invalid integer " + key + " = " + value, exception);
        }
    }
	
    private static String findProperty(Properties properties, String key) {
        final String value = properties.getProperty(key);
        if (value == null) throw new IllegalArgumentException("Please specify " + key);
        return value;
    }

}
