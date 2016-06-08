package br.com.produban.openbus.hypervisor.collector;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//https://www.vmware.com/support/developer/converter-sdk/conv61_apireference/vim.PerformanceManager.MetricId.html

@Configuration
public class HypervisorMetricsConfiguration {

	private static final int KEY_INDEX = 0;

	private static final int VALUE_INDEX = 1;

	private static final String AGGREGATED_PREFIX = "aggr";

	private static final String ALL_INSTANCES = "*";

	private static final String AGGREGATED = "";

	private static final Pattern SPLIT_PATTERN = Pattern.compile(":");

	@Value("${hypervisor.metrics}")
	private String[] metrics;

	@Bean
	public Map<String, String> metricsInstanceMap() {
		Map<String, String> metricInstanceMap = new HashMap<String, String>();

		for (String metric : metrics) {
			String[] keyValue = SPLIT_PATTERN.split(metric);
			String value = keyValue[VALUE_INDEX].equals(AGGREGATED_PREFIX) ? AGGREGATED : ALL_INSTANCES;
			metricInstanceMap.put(keyValue[KEY_INDEX], value);
		}

		return metricInstanceMap;
	}

}
