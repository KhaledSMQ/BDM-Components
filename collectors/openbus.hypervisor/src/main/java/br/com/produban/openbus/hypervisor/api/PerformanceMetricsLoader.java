package br.com.produban.openbus.hypervisor.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.vmware.vim25.PerfMetricId;

@Component
public class PerformanceMetricsLoader {

	@Resource
	@Qualifier("metricsInstanceMap")
	private Map<String, String> metricsInstanceMap;

	public List<PerfMetricId> getMetrics(HashMap<String, Integer> countersMap) {
		List<PerfMetricId> metrics = new ArrayList<PerfMetricId>();

		for (Entry<String, String> entry : metricsInstanceMap.entrySet()) {

			PerfMetricId metricId = new PerfMetricId();
			metricId.setCounterId(countersMap.get(entry.getKey()));
			metricId.setInstance(entry.getValue());
			metrics.add(metricId);
		}

		return metrics;
	}
}
