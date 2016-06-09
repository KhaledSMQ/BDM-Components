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
import br.com.produban.openbus.hypervisor.config.ApplicationConstants;
import br.com.produban.openbus.ingestor.OpenbusDataIngestion;

import com.vmware.vim25.HostConnectInfo;
import com.vmware.vim25.HostSystemConnectionState;
import com.vmware.vim25.ManagedObjectReference;
import com.vmware.vim25.RuntimeFaultFaultMsg;

@Service
public class HypervisorConnectionStatusCollector {

	private static final Logger LOGGER = LoggerFactory.getLogger(HypervisorConnectionStatusCollector.class);

	@Autowired
	private OpenbusDataIngestion ingestor;

	@Autowired
	@Qualifier("hypervisorSchema")
	private Schema hypervisorSchema;

	public void collect(Connection connection, String ipAddress) throws Exception {

		LOGGER.info("Collecting connection status from hosts");

		Map<String, ManagedObjectReference> hosts = new HostReferenceFinder(connection).findAllHost();

		for (Entry<String, ManagedObjectReference> host : hosts.entrySet()) {

			int connectionState = getConnectionStateIntValue(host.getValue(), connection);

			GenericRecord hostConnRecord = new GenericData.Record(hypervisorSchema);
			hostConnRecord.put("vcenter", ipAddress);
			hostConnRecord.put("hostname", host.getKey());
			hostConnRecord.put("fqdn", host.getKey());
			hostConnRecord.put("source", " ");
			hostConnRecord.put("metric", MetricName.CONNECTION_STATE.getKey());
			hostConnRecord.put("value", String.valueOf(connectionState));
			hostConnRecord.put("timestamp", String.valueOf(System.currentTimeMillis()));
			hostConnRecord.put("extraInfo", new HashMap<String, String>());

			LOGGER.info("Sending connection status metrics from hostname=" + host.getKey());

			ingestor.ingest(ApplicationConstants.HYPERVISOR_TOOL_NAME, hostConnRecord);
		}
	}

	public int getConnectionStateIntValue(ManagedObjectReference host, Connection connection) throws RuntimeFaultFaultMsg {

		HostConnectInfo hostConnectInfo = connection.getVimPort().queryHostConnectionInfo(host);
		HostSystemConnectionState state = hostConnectInfo.getHost().getRuntime().getConnectionState();

		if (state == HostSystemConnectionState.CONNECTED)
			return 1;

		else if (state == HostSystemConnectionState.DISCONNECTED)
			return 0;

		else if (state == HostSystemConnectionState.NOT_RESPONDING)
			return 2;

		else
			return -1;
	}
}
