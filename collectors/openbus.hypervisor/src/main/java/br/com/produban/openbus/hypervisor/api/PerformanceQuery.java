package br.com.produban.openbus.hypervisor.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.vmware.vim25.ManagedObjectReference;
import com.vmware.vim25.PerfMetricId;
import com.vmware.vim25.PerfQuerySpec;

@Component
public class PerformanceQuery {

	private static final int INTERVAL_IN_SECONDS = 20;

	private static final String CSV_FORMAT = "csv";

	private static final int LATEST_METRIC = 1;

	public List<PerfQuerySpec> queryForMetrics(List<PerfMetricId> perfMetricIds, ManagedObjectReference hostEntity) {

		PerfQuerySpec query = new PerfQuerySpec();
		query.setEntity(hostEntity);
		query.setIntervalId(INTERVAL_IN_SECONDS);
		query.setMaxSample(LATEST_METRIC);
		query.setFormat(CSV_FORMAT);

		query.getMetricId().addAll(perfMetricIds);

		List<PerfQuerySpec> queries = new ArrayList<PerfQuerySpec>();
		queries.add(query);

		return queries;
	}
}
