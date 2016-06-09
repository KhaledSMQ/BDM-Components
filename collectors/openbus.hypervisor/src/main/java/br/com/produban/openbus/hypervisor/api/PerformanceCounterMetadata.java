package br.com.produban.openbus.hypervisor.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.ws.soap.SOAPFaultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vmware.vim25.ArrayOfPerfCounterInfo;
import com.vmware.vim25.DynamicProperty;
import com.vmware.vim25.InvalidPropertyFaultMsg;
import com.vmware.vim25.ObjectContent;
import com.vmware.vim25.ObjectSpec;
import com.vmware.vim25.PerfCounterInfo;
import com.vmware.vim25.PropertyFilterSpec;
import com.vmware.vim25.PropertySpec;
import com.vmware.vim25.RetrieveOptions;
import com.vmware.vim25.RetrieveResult;
import com.vmware.vim25.RuntimeFaultFaultMsg;

import br.com.produban.openbus.hypervisor.collector.HypervisorMetricsCollector;

public class PerformanceCounterMetadata {

	private static final Logger LOGGER = LoggerFactory.getLogger(HypervisorMetricsCollector.class);
	
	private HashMap<String, Integer> countersIdMap;
	private HashMap<Integer, PerfCounterInfo> countersInfoMap;
	private Connection connection;

	public PerformanceCounterMetadata(Connection connection) throws InvalidPropertyFaultMsg, RuntimeFaultFaultMsg {
		this.countersIdMap = new HashMap<String, Integer>();
		this.countersInfoMap = new HashMap<Integer, PerfCounterInfo>();
		this.connection = connection;
		fill();

	}

	private void fill() throws InvalidPropertyFaultMsg, RuntimeFaultFaultMsg {

		List<PerfCounterInfo> perfCounters = getPerfCounters(connection);

		for (PerfCounterInfo perfCounter : perfCounters) {

			Integer counterId = new Integer(perfCounter.getKey());

			countersInfoMap.put(counterId, perfCounter);

			String counterGroup = perfCounter.getGroupInfo().getKey();
			String counterName = perfCounter.getNameInfo().getKey();
			String counterRollupType = perfCounter.getRollupType().toString();
			String keyCounterName = counterGroup + "." + counterName + "." + counterRollupType.toLowerCase();

			countersIdMap.put(keyCounterName, counterId);
		}
	}

	private List<PerfCounterInfo> getPerfCounters(Connection connection) {
		List<PerfCounterInfo> pciArr = null;

		try {
			PropertySpec propertySpec = new PropertySpec();
			propertySpec.setAll(Boolean.FALSE);
			propertySpec.getPathSet().add("perfCounter");
			propertySpec.setType("PerformanceManager");
			List<PropertySpec> propertySpecs = new ArrayList<PropertySpec>();
			propertySpecs.add(propertySpec);

			ObjectSpec objectSpec = new ObjectSpec();
			objectSpec.setObj(connection.getPerfManager());
			List<ObjectSpec> objectSpecs = new ArrayList<ObjectSpec>();
			objectSpecs.add(objectSpec);

			PropertyFilterSpec propertyFilterSpec = new PropertyFilterSpec();
			propertyFilterSpec.getPropSet().add(propertySpec);
			propertyFilterSpec.getObjectSet().add(objectSpec);

			List<PropertyFilterSpec> propertyFilterSpecs = new ArrayList<PropertyFilterSpec>();
			propertyFilterSpecs.add(propertyFilterSpec);

			List<PropertyFilterSpec> listpfs = new ArrayList<PropertyFilterSpec>(1);
			listpfs.add(propertyFilterSpec);
			List<ObjectContent> listobjcont = retrievePropertiesAllObjects(listpfs, connection);

			if (listobjcont != null) {
				for (ObjectContent oc : listobjcont) {
					List<DynamicProperty> dps = oc.getPropSet();
					if (dps != null) {
						for (DynamicProperty dp : dps) {
							List<PerfCounterInfo> pcinfolist = ((ArrayOfPerfCounterInfo) dp.getVal())
									.getPerfCounterInfo();
							pciArr = pcinfolist;
						}
					}
				}
			}
		} catch (SOAPFaultException e) {
			LOGGER.error("Wrong soap wsdl :", e);
		} catch (Exception e) {
			LOGGER.error("Error retrieving performance counters :", e);
		}
		return pciArr;
	}

	private List<ObjectContent> retrievePropertiesAllObjects(List<PropertyFilterSpec> listpfs, Connection connection) {

		RetrieveOptions propObjectRetrieveOpts = new RetrieveOptions();

		List<ObjectContent> listobjcontent = new ArrayList<ObjectContent>();

		try {
			RetrieveResult rslts = connection.getVimPort().retrievePropertiesEx(connection.getPropertyCollector(),
					listpfs, propObjectRetrieveOpts);
			if (rslts != null && rslts.getObjects() != null && !rslts.getObjects().isEmpty()) {
				listobjcontent.addAll(rslts.getObjects());
			}
			String token = null;
			if (rslts != null && rslts.getToken() != null) {
				token = rslts.getToken();
			}
			while (token != null && !token.isEmpty()) {
				rslts = connection.getVimPort().continueRetrievePropertiesEx(connection.getPropertyCollector(), token);
				token = null;
				if (rslts != null) {
					token = rslts.getToken();
					if (rslts.getObjects() != null && !rslts.getObjects().isEmpty()) {
						listobjcontent.addAll(rslts.getObjects());
					}
				}
			}
		} catch (SOAPFaultException e) {
			LOGGER.error("Wrong soap wsdl :", e);
		} catch (Exception e) {
			LOGGER.error(" : Failed Getting Contents", e);
		}

		return listobjcontent;
	}

	public HashMap<String, Integer> getCountersIdMap() {
		return countersIdMap;
	}

	public HashMap<Integer, PerfCounterInfo> getCountersInfoMap() {
		return countersInfoMap;
	}
}
