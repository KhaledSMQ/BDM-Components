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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vmware.vim25.ManagedObjectReference;
import com.vmware.vim25.PerfCounterInfo;
import com.vmware.vim25.PerfEntityMetricBase;
import com.vmware.vim25.PerfEntityMetricCSV;
import com.vmware.vim25.PerfMetricId;
import com.vmware.vim25.PerfMetricSeriesCSV;
import com.vmware.vim25.PerfQuerySpec;
import com.vmware.vim25.RuntimeFaultFaultMsg;

import br.com.produban.openbus.hypervisor.api.Connection;
import br.com.produban.openbus.hypervisor.api.HostReferenceFinder;
import br.com.produban.openbus.hypervisor.api.PerformanceCounterMetadata;
import br.com.produban.openbus.hypervisor.api.PerformanceMetricsLoader;
import br.com.produban.openbus.hypervisor.api.PerformanceQuery;
import br.com.produban.openbus.hypervisor.security.Credentials;
import br.com.produban.openbus.ingestor.OpenbusDataIngestion;

@Service
public class HypervisorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(HypervisorService.class);

	@Autowired
	private Credentials credentials;

	@Autowired
	private PerformanceMetricsLoader metricsLoader;

	@Autowired
	private PerformanceQuery performanceQueryGenerator;

	@Autowired
	private OpenbusDataIngestion ingestor;

	@Autowired
	private Schema hypervisorSchema;

	@Value("${vcenter.ip}")
	private String vCenter;

	public void collect() {

		Connection connection = null;

		try {

			connection = new Connection(credentials);

			connection.login();
			
			PerformanceCounterMetadata performanceMetadata = new PerformanceCounterMetadata(connection);

			List<PerfMetricId> metrics = metricsLoader.getMetrics(performanceMetadata.getCountersIdMap());

			Map<String, ManagedObjectReference> hosts = new HostReferenceFinder(connection).findAllHost();

			for (Entry<String, ManagedObjectReference> host : hosts.entrySet()) {
				
								
				List<PerfQuerySpec> pqsList = performanceQueryGenerator.queryForMetrics(metrics, host.getValue());
			
				List<PerfEntityMetricBase> statisticsMetricsByHost = connection.getVimPort()
						.queryPerf(connection.getPerfManager(), pqsList);

				for (PerfEntityMetricBase hostStatisticMetrics : statisticsMetricsByHost) {
					sendToIngestor(hostStatisticMetrics, performanceMetadata, host.getKey());
				}
			}

		} catch (Exception e) {
			LOGGER.error("Error connecting to hypervisor credential " + credentials.getUrl(), e);
		} finally {
			if (connection.getServiceContent() != null) {
				try {
					connection.getVimPort().logout(connection.getServiceContent().getSessionManager());
				} catch (RuntimeFaultFaultMsg e) {
					LOGGER.error("Error at logout server " + credentials.getUrl(), e);
				}
			}
		}
	}


	public void sendToIngestor(PerfEntityMetricBase hostStatisticMetrics, PerformanceCounterMetadata performanceMetadata,
			String hostname) throws Exception {

		PerfEntityMetricCSV csvMetric = (PerfEntityMetricCSV) hostStatisticMetrics;

		List<PerfMetricSeriesCSV> metricsValues = csvMetric.getValue();

		if (metricsValues.isEmpty()) {
			
			return;
		}

		//String timestamp = extractTimestamp(csvMetric.getSampleInfoCSV());

		for (PerfMetricSeriesCSV csv : metricsValues) {

			PerfCounterInfo pci = performanceMetadata.getCountersInfoMap().get(csv.getId().getCounterId());

			String metric = pci.getGroupInfo().getKey() + "." + pci.getNameInfo().getKey() + "." + pci.getRollupType();
			GenericRecord record = new GenericData.Record(hypervisorSchema);
			record.put("vcenter", vCenter);
			record.put("hostname", hostname);
			record.put("source", csv.getId().getInstance());
			record.put("metric", metric);
			record.put("value", csv.getValue());
			record.put("timestamp", String.valueOf(System.currentTimeMillis()));
			record.put("extraInfo", new HashMap<String, String>());
			
			ingestor.ingest("Hypervisor", record);
		}
	}

	/*public String extractTimestamp(String csvHeader) throws Exception {
		String splitedString = csvHeader.split(",")[1];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		long time = sdf.parse(splitedString).getTime();
		//Date fromGmt = new Date(sdf.parse(splitedString).getTime() + TimeZone.getDefault().getOffset(new Date().getTime()));
		return String.valueOf(ldt.toDate().getTime());
	}*/

}
