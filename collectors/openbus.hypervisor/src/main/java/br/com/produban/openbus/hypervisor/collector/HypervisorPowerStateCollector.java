package br.com.produban.openbus.hypervisor.collector;

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
import br.com.produban.openbus.ingestor.OpenbusDataIngestion;

import com.vmware.vim25.HostConnectInfo;
import com.vmware.vim25.HostSystemPowerState;
import com.vmware.vim25.ManagedObjectReference;
import com.vmware.vim25.RuntimeFaultFaultMsg;

@Service
public class HypervisorPowerStateCollector {

	private static final Logger LOGGER = LoggerFactory.getLogger(HypervisorStorageCollector.class);

	@Autowired
	private OpenbusDataIngestion ingestor;

	@Autowired
	@Qualifier("hypervisorSchema")
	private Schema hypervisorSchema;

	public void collect(Connection connection, String ipAddress) throws Exception {

		LOGGER.info("Collecting power state metrics from hosts=" + ipAddress);

		Map<String, ManagedObjectReference> hosts = new HostReferenceFinder(connection).findAllHost();

		for (Entry<String, ManagedObjectReference> host : hosts.entrySet()) {

			int powerState = getPowerStateIntValue(host.getValue(), connection);

			GenericRecord hostConnRecord = new GenericData.Record(hypervisorSchema);
			hostConnRecord.put("vcenter", ipAddress);
			hostConnRecord.put("hostname", host.getKey());
			hostConnRecord.put("fqdn", host.getKey());
			hostConnRecord.put("source", " ");
			hostConnRecord.put("metric", MetricName.POWER_STATE.getKey());
			hostConnRecord.put("value", String.valueOf(powerState));
			hostConnRecord.put("timestamp", String.valueOf(System.currentTimeMillis()));
			hostConnRecord.put("extraInfo", new HashMap<String, String>());

			LOGGER.info("Sending power state metrics from hostname=" + host.getKey());

			ingestor.ingest("Hypervisor", hostConnRecord);
		}
	}

	public int getPowerStateIntValue(ManagedObjectReference host, Connection connection) throws RuntimeFaultFaultMsg {

		HostConnectInfo hostConnectInfo = connection.getVimPort().queryHostConnectionInfo(host);
		HostSystemPowerState state = hostConnectInfo.getHost().getRuntime().getPowerState();

		if (state == HostSystemPowerState.POWERED_ON)
			return 1;

		else if (state == HostSystemPowerState.POWERED_OFF)
			return 0;

		else if (state == HostSystemPowerState.STAND_BY)
			return 2;

		else if (state == HostSystemPowerState.UNKNOWN)
			return 3;

		else
			return -1;

	}
}
