package br.com.produban.openbus.hypervisor.collector;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.produban.openbus.hypervisor.api.Connection;
import br.com.produban.openbus.hypervisor.security.Credentials;
import br.com.produban.openbus.ingestor.OpenbusDataIngestion;

import com.vmware.vim25.Event;
import com.vmware.vim25.EventEx;
import com.vmware.vim25.EventFilterSpec;
import com.vmware.vim25.EventFilterSpecByTime;
import com.vmware.vim25.KeyAnyValue;
import com.vmware.vim25.RuntimeFaultFaultMsg;

@Service
public class HypervisorEventsCollector {

	private static final Logger LOGGER = LoggerFactory.getLogger(HypervisorEventsCollector.class);

	@Autowired
	private Credentials credentials;

	@Autowired
	private OpenbusDataIngestion ingestor;

	@Autowired
	@Qualifier("eventSchema")
	private Schema eventSchema;

	@Value("${vcenter.url}")
	private String vcenterBaseURL;

	@Value("${vcenter.events.history.offset}")
	private int minutesRefresh;

	@Async
	public void collectEventsByIpAddress(String ipAddress) {

		collectEvents(ipAddress);
	}

	private void collectEvents(String ipAddress) {

		Connection connection = null;
		String vcenterUrl = String.format(vcenterBaseURL, ipAddress);

		try {

			connection = new Connection(credentials, vcenterUrl);
			connection.login();

			collectEventsAndSendToIngestor(ipAddress, connection);

		} catch (Exception e) {
			LOGGER.error("error in : " + ipAddress);
			LOGGER.error("error stack: " + e);
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

	private void collectEventsAndSendToIngestor(String ipAddress, Connection connection) {

		LOGGER.info("Collecting events in " + ipAddress);

		try {

			EventFilterSpec filterSpec = new EventFilterSpec();
			filterSpec.setTime(getEventFilterSpecByTime());

			List<Event> events = connection.getVimPort().queryEvents(connection.getServiceContent().getEventManager(), filterSpec);

			String[] allowedEvents = { "esx.problem.storage.apd.start", "com.vmware.vc.ha.VmRestartedByHAEvent", "esx.problem.net.vmnic.linkstate.down", "esx.problem.vmfs.heartbeat.timedout" };

			for (Event event : events) {

				if (event instanceof EventEx) {

					EventEx e = (EventEx) event;

					for (String allowedEvent : allowedEvents) {

						if (allowedEvent.equals(e.getEventTypeId())) {

							GenericRecord record = new GenericData.Record(eventSchema);

							record.put("vcenter", ipAddress);
							record.put("hostname", event.getHost().getName());
							record.put("datacenter", event.getDatacenter() != null ? event.getDatacenter().getName() : "");
							record.put("datastore", event.getDs() != null ? event.getDs().getName() : "");
							record.put("cluster", event.getComputeResource() != null ? event.getComputeResource().getName() : "");
							record.put("vm", event.getVm() != null ? event.getVm().getName() : "");
							record.put("timestamp", String.valueOf(System.currentTimeMillis()));

							if ("esx.problem.storage.apd.start".equals(e.getEventTypeId())) {

								record.put("metric", "apd.device.entered");
								record.put("value", String.valueOf(1));

								HashMap<String, String> extraInfo = new HashMap<String, String>();
								extraInfo.put("event.timestamp", String.valueOf(e.getCreatedTime().toGregorianCalendar().getTimeInMillis()));

								if (!CollectionUtils.isEmpty(e.getArguments())) {

									for (KeyAnyValue val : e.getArguments()) {
										extraInfo.put(String.valueOf(val.getKey()), val.getValue().toString());
									}
								}
								record.put("extraInfo", extraInfo);

								ingestor.ingest("HypervisorEvent", record);

							} else if ("com.vmware.vc.ha.VmRestartedByHAEvent".equals(e.getEventTypeId())) {

								record.put("metric", "vm.restarted");
								record.put("value", String.valueOf(0));

								HashMap<String, String> extraInfo = new HashMap<String, String>();
								extraInfo.put("event.timestamp", String.valueOf(e.getCreatedTime().toGregorianCalendar().getTimeInMillis()));

								record.put("extraInfo", extraInfo);

								ingestor.ingest("HypervisorEvent", record);

							} else if ("esx.problem.net.vmnic.linkstate.down".equals(e.getEventTypeId()) && "HostSystem".equals(e.getObjectType())) {

								record.put("metric", "network.connectivity.lost");
								record.put("value", String.valueOf(0));

								HashMap<String, String> extraInfo = new HashMap<String, String>();
								extraInfo.put("event.timestamp", String.valueOf(e.getCreatedTime().toGregorianCalendar().getTimeInMillis()));

								record.put("extraInfo", new HashMap<String, String>());

								if (!CollectionUtils.isEmpty(e.getArguments())) {

									for (KeyAnyValue val : e.getArguments()) {
										extraInfo.put(String.valueOf(val.getKey()), val.getValue().toString());
									}
								}
								record.put("extraInfo", extraInfo);

								ingestor.ingest("HypervisorEvent", record);

							} else if ("esx.problem.vmfs.heartbeat.timedout".equals(e.getEventTypeId()) && "Datastore".equals(e.getObjectType())) {

								record.put("metric", "storage.connectivity.lost");
								record.put("value", String.valueOf(0));

								HashMap<String, String> extraInfo = new HashMap<>();
								extraInfo.put("event.timestamp", String.valueOf(e.getCreatedTime().toGregorianCalendar().getTimeInMillis()));

								record.put("extraInfo", extraInfo);

								ingestor.ingest("HypervisorEvent", record);
							}
						}
					}

				}
			}

		} catch (Exception e) {
			LOGGER.error("error: " + e);
		}
	}

	private EventFilterSpecByTime getEventFilterSpecByTime() throws DatatypeConfigurationException {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, (minutesRefresh * -1));

		GregorianCalendar gCal = new GregorianCalendar();
		gCal.setTime(cal.getTime());

		XMLGregorianCalendar xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCal);

		EventFilterSpecByTime filterByTime = new EventFilterSpecByTime();
		filterByTime.setBeginTime(xmlCal);

		return filterByTime;
	}

}
