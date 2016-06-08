package br.com.produban.openbus.hypervisor.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vmware.vim25.DynamicProperty;
import com.vmware.vim25.InvalidPropertyFaultMsg;
import com.vmware.vim25.ManagedObjectReference;
import com.vmware.vim25.ObjectContent;
import com.vmware.vim25.PropertyFilterSpec;
import com.vmware.vim25.RetrieveOptions;
import com.vmware.vim25.RetrieveResult;
import com.vmware.vim25.RuntimeFaultFaultMsg;

import br.com.produban.openbus.hypervisor.api.builders.ObjectSpecBuilder;
import br.com.produban.openbus.hypervisor.api.builders.PropertyFilterSpecBuilder;
import br.com.produban.openbus.hypervisor.api.builders.PropertySpecBuilder;
import br.com.produban.openbus.hypervisor.api.builders.TraversalSpecBuilder;

public class HostReferenceFinder {
	
	private Connection connection;

	public HostReferenceFinder(Connection connection) {
		this.connection = connection;
	}

	public Map<String, ManagedObjectReference> findAllHost()
			throws RuntimeFaultFaultMsg, InvalidPropertyFaultMsg {
		return findAllHosts(connection.getServiceContent().getRootFolder(), "HostSystem", new RetrieveOptions());
	}

	private Map<String, ManagedObjectReference> findAllHosts(final ManagedObjectReference folder,
			final String morefType, final RetrieveOptions retrieveOptions)
					throws RuntimeFaultFaultMsg, InvalidPropertyFaultMsg {
		final PropertyFilterSpec[] propertyFilterSpecs = propertyFilterSpecs(folder, morefType, "name");

		RetrieveResult results = connection.getVimPort().retrievePropertiesEx(connection.getPropertyCollector(), Arrays.asList(propertyFilterSpecs),
				retrieveOptions);

		final Map<String, ManagedObjectReference> tgtMoref = new HashMap<String, ManagedObjectReference>();
		
		while (results != null && !results.getObjects().isEmpty()) {
			resultsToTgtMorefMap(results, tgtMoref);
			final String token = results.getToken();
			results = (token != null) ?  connection.getVimPort().continueRetrievePropertiesEx(connection.getPropertyCollector(), token) : null;
		}

		return tgtMoref;
	}
    
	private PropertyFilterSpec[] propertyFilterSpecs(
            ManagedObjectReference container,
            String morefType,
            String morefProperties
    ) throws RuntimeFaultFaultMsg {

        ManagedObjectReference viewManager = connection.getServiceContent().getViewManager();
        ManagedObjectReference containerView =
        		connection.getVimPort().createContainerView(viewManager, container,
                        Arrays.asList(morefType), true);

        return new PropertyFilterSpec[]{
                new PropertyFilterSpecBuilder()
                        .propSet(
                                new PropertySpecBuilder()
                                        .all(Boolean.FALSE)
                                        .type(morefType)
                                        .pathSet(morefProperties)
                        )
                        .objectSet(
                                new ObjectSpecBuilder()
                                        .obj(containerView)
                                        .skip(Boolean.TRUE)
                                        .selectSet(
                                                new TraversalSpecBuilder()
                                                        .name("view")
                                                        .path("view")
                                                        .skip(false)
                                                        .type("ContainerView")
                                        )
                        )
        };
	}

	private static void resultsToTgtMorefMap(RetrieveResult results, Map<String, ManagedObjectReference> tgtMoref) {
		List<ObjectContent> oCont = (results != null) ? results.getObjects() : null;

		if (oCont != null) {
			for (ObjectContent oc : oCont) {
				ManagedObjectReference mr = oc.getObj();
				String entityNm = null;
				List<DynamicProperty> dps = oc.getPropSet();
				if (dps != null) {
					for (DynamicProperty dp : dps) {
						entityNm = (String) dp.getVal();
					}
				}
				tgtMoref.put(entityNm, mr);
			}
		}
	}

}
