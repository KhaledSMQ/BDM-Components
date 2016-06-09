package br.com.produban.openbus.hypervisor.collector;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.produban.openbus.hypervisor.api.Connection;
import br.com.produban.openbus.hypervisor.api.HostReferenceFinder;
import br.com.produban.openbus.hypervisor.config.ApplicationConstants;
import br.com.produban.openbus.ingestor.OpenbusDataIngestion;

import com.vmware.vim25.HostConnectInfo;
import com.vmware.vim25.HostDatastoreConnectInfo;
import com.vmware.vim25.ManagedObjectReference;

@Service
public class HypervisorStorageCollector {

	private static final Logger LOGGER = LoggerFactory.getLogger(HypervisorStorageCollector.class);

	private static final double TB_CONVERSION = 1_099_511_627_776.0;

	@Autowired
	private OpenbusDataIngestion ingestor;

	@Autowired
	@Qualifier("hypervisorSchema")
	private Schema hypervisorSchema;

	public void collect(Connection connection, String ipAddress) throws Exception {

		LOGGER.info("Collecting storage stats metrics from hostsname=" + ipAddress);

		Map<String, ManagedObjectReference> hosts = new HostReferenceFinder(connection).findAllHost();

		for (Entry<String, ManagedObjectReference> host : hosts.entrySet()) {

			HostConnectInfo hostConnectInfo = connection.getVimPort().queryHostConnectionInfo(host.getValue());

			for (HostDatastoreConnectInfo info : hostConnectInfo.getDatastore()) {

				GenericRecord capacityRecord = new GenericData.Record(hypervisorSchema);
				capacityRecord.put("vcenter", ipAddress);
				capacityRecord.put("hostname", host.getKey());
				capacityRecord.put("fqdn", host.getKey());
				capacityRecord.put("source", info.getSummary().getName());
				capacityRecord.put("metric", MetricName.DATASTORE_CAPACITY.getKey());
				capacityRecord.put("value", String.valueOf(convertCapacityIntoTerabyte(info)));
				capacityRecord.put("timestamp", String.valueOf(System.currentTimeMillis()));
				capacityRecord.put("extraInfo", new HashMap<String, String>());
				ingestor.ingest(ApplicationConstants.HYPERVISOR_TOOL_NAME, capacityRecord);

				GenericRecord freeSpaceRecord = new GenericData.Record(hypervisorSchema);
				freeSpaceRecord.put("vcenter", ipAddress);
				freeSpaceRecord.put("hostname", host.getKey());
				freeSpaceRecord.put("fqdn", host.getKey());
				freeSpaceRecord.put("source", info.getSummary().getName());
				freeSpaceRecord.put("metric", MetricName.DATASTORE_FREE_SPACE.getKey());
				freeSpaceRecord.put("value", convertFreeStorageInPercentage(info));
				freeSpaceRecord.put("timestamp", String.valueOf(System.currentTimeMillis()));
				freeSpaceRecord.put("extraInfo", new HashMap<String, String>());
				ingestor.ingest(ApplicationConstants.HYPERVISOR_TOOL_NAME, freeSpaceRecord);

				GenericRecord ussageRecord = new GenericData.Record(hypervisorSchema);
				ussageRecord.put("vcenter", ipAddress);
				ussageRecord.put("hostname", host.getKey());
				ussageRecord.put("fqdn", host.getKey());
				ussageRecord.put("source", info.getSummary().getName());
				ussageRecord.put("metric", MetricName.DATASTORE_USAGE.getKey());
				ussageRecord.put("value", convertStorageUsageInPercentage(info));
				ussageRecord.put("timestamp", String.valueOf(System.currentTimeMillis()));
				ussageRecord.put("extraInfo", new HashMap<String, String>());

				LOGGER.info("Sending storage stats metrics from hosts=" + host.getKey());

				ingestor.ingest(ApplicationConstants.HYPERVISOR_TOOL_NAME, ussageRecord);

			}
		}
	}

	private double convertCapacityIntoTerabyte(HostDatastoreConnectInfo info) {
		return info.getSummary().getCapacity() / TB_CONVERSION;
	}

	public String convertStorageUsageInPercentage(HostDatastoreConnectInfo info) {

		long usage = info.getSummary().getCapacity() - info.getSummary().getFreeSpace();
		double usagePercent = ((double) usage * 100) / (double) info.getSummary().getCapacity();

		if (Double.isNaN(usagePercent))
			usagePercent = 0;

		return new DecimalFormat(ApplicationConstants.ROUND_METRIC_FORMAT).format(usagePercent);
	}

	public String convertFreeStorageInPercentage(HostDatastoreConnectInfo info) {

		double freeSpacePercent = ((double) info.getSummary().getFreeSpace() * 100) / (double) info.getSummary().getCapacity();

		if (Double.isNaN(freeSpacePercent))
			freeSpacePercent = 0;

		return new DecimalFormat(ApplicationConstants.ROUND_METRIC_FORMAT).format(freeSpacePercent);
	}
}
