package br.com.produban.openbus.hypervisor.collector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.produban.openbus.hypervisor.api.Connection;
import br.com.produban.openbus.hypervisor.api.HostReferenceFinder;
import br.com.produban.openbus.hypervisor.api.PerformanceCounterMetadata;
import br.com.produban.openbus.hypervisor.api.PerformanceMetricsLoader;
import br.com.produban.openbus.hypervisor.api.PerformanceQuery;
import br.com.produban.openbus.hypervisor.config.ApplicationConstants;
import br.com.produban.openbus.hypervisor.security.Credentials;
import br.com.produban.openbus.ingestor.OpenbusDataIngestion;

import com.vmware.vim25.ManagedObjectReference;
import com.vmware.vim25.PerfCounterInfo;
import com.vmware.vim25.PerfEntityMetricBase;
import com.vmware.vim25.PerfEntityMetricCSV;
import com.vmware.vim25.PerfMetricId;
import com.vmware.vim25.PerfMetricSeriesCSV;
import com.vmware.vim25.PerfQuerySpec;
import com.vmware.vim25.RuntimeFaultFaultMsg;

@Service
public class HypervisorMetricsCollector {

	private static final Logger LOGGER = LoggerFactory.getLogger(HypervisorMetricsCollector.class);

	@Autowired
	private Credentials credentials;

	@Autowired
	private PerformanceMetricsLoader metricsLoader;

	@Autowired
	private PerformanceQuery performanceQueryGenerator;

	@Autowired
	private OpenbusDataIngestion ingestor;

	@Autowired
	private HypervisorStorageCollector hypervisorStorageCollector;
	
	@Autowired
	private HypervisorPowerStateCollector hypervisorPowerStateCollector;
	
	@Autowired
	private HypervisorConnectionStatusCollector hypervisorConnectionStatusCollector;
	
	@Autowired
	@Qualifier("hypervisorSchema")
	private Schema hypervisorSchema;

	@Value("${vcenter.url}")
	private String vcenterBaseURL;

	private String ipAddress;

	@Async
	public void collectMetricsByIpAddress(String ipAddress) {
		collectMetrics(ipAddress);
	}

	public void collectMetrics(String ipAddress) {

		this.ipAddress = ipAddress;

		Connection connection = null;
		String vcenterUrl = String.format(vcenterBaseURL, ipAddress);

		try {

			connection = new Connection(credentials, vcenterUrl);
			connection.login();

			collectPerformanceMetricsFromHosts(connection);
			hypervisorConnectionStatusCollector.collect(connection, ipAddress);
			hypervisorPowerStateCollector.collect(connection, ipAddress);
			hypervisorStorageCollector.collect(connection, ipAddress);

		} catch (Exception e) {
			LOGGER.error("Error connecting to hypervisor " + vcenterUrl, e);
		} finally {
			if (connection != null && connection.getServiceContent() != null) {
				try {
					connection.getVimPort().logout(connection.getServiceContent().getSessionManager());
				} catch (RuntimeFaultFaultMsg e) {
					LOGGER.error("Error at logout server " + vcenterUrl, e);
				}
			}
		}
	}

	private void collectPerformanceMetricsFromHosts(Connection connection) throws Exception {

		LOGGER.info("Collecting performance metrics");

		PerformanceCounterMetadata performanceMetadata = new PerformanceCounterMetadata(connection);

		List<PerfMetricId> metrics = metricsLoader.getMetrics(performanceMetadata.getCountersIdMap());

		Map<String, ManagedObjectReference> hosts = new HostReferenceFinder(connection).findAllHost();

		for (Entry<String, ManagedObjectReference> host : hosts.entrySet()) {

			List<PerfQuerySpec> pqsList = performanceQueryGenerator.queryForMetrics(metrics, host.getValue());

			List<PerfEntityMetricBase> statisticsMetricsByHost = connection.getVimPort().queryPerf(connection.getPerfManager(), pqsList);

			for (PerfEntityMetricBase hostStatisticMetrics : statisticsMetricsByHost) {

				sendToIngestor(hostStatisticMetrics, performanceMetadata, host.getKey(), ipAddress);
			}
		}
	}

	public void sendToIngestor(PerfEntityMetricBase hostStatisticMetrics, PerformanceCounterMetadata performanceMetadata, String hostname, String ipAddress) throws Exception {

		PerfEntityMetricCSV csvMetric = (PerfEntityMetricCSV) hostStatisticMetrics;

		List<PerfMetricSeriesCSV> metricsValues = csvMetric.getValue();

		if (metricsValues.isEmpty()) {
			LOGGER.warn("No metrics Available for vcenter=" + ipAddress + " hostname=" + hostname);
			return;
		}
		
		LOGGER.info("Sending metrics to ingestor [size= " + metricsValues.size() + "]");
		
		for (PerfMetricSeriesCSV csv : metricsValues) {

			PerfCounterInfo pci = performanceMetadata.getCountersInfoMap().get(csv.getId().getCounterId());

			String metric = pci.getGroupInfo().getKey() + "." + pci.getNameInfo().getKey() + "." + pci.getRollupType();
			GenericRecord record = new GenericData.Record(hypervisorSchema);
			record.put("vcenter", ipAddress);
			record.put("hostname", hostname);
			record.put("fqdn", hostname);
			record.put("source", csv.getId().getInstance());
			record.put("metric", metric);
			record.put("value", csv.getValue());
			record.put("timestamp", String.valueOf(System.currentTimeMillis()));
			record.put("extraInfo", new HashMap<String, String>());

			ingestor.ingest(ApplicationConstants.HYPERVISOR_TOOL_NAME, record);
		}
	}

}
