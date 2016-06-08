package br.com.produban.openbus.hypervisor.api;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import com.vmware.vim25.InvalidLocaleFaultMsg;
import com.vmware.vim25.InvalidLoginFaultMsg;
import com.vmware.vim25.ManagedObjectReference;
import com.vmware.vim25.RuntimeFaultFaultMsg;
import com.vmware.vim25.ServiceContent;
import com.vmware.vim25.VimPortType;
import com.vmware.vim25.VimService;

import br.com.produban.openbus.hypervisor.security.Credentials;
import br.com.produban.openbus.hypervisor.security.DisableSecurity;

public class Connection {

	private static final boolean MAINTAIN_SESSION = true;

	private static final String SERVICE_INSTANCE = "ServiceInstance";

	private ServiceContent serviceContent;

	private VimPortType vimPort;

	private Credentials credentials;
	
	private ManagedObjectReference perfManager;

	private ManagedObjectReference propertyCollector;
	
	public Connection(Credentials credentials) throws RuntimeFaultFaultMsg, KeyManagementException, NoSuchAlgorithmException {
		DisableSecurity.trustEveryone();
		
		this.credentials = credentials;
		this.vimPort = new VimService().getVimPort();

		Map<String, Object> ctxt = ((BindingProvider) vimPort).getRequestContext();
		ctxt.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, credentials.getUrl());
		ctxt.put(BindingProvider.SESSION_MAINTAIN_PROPERTY, MAINTAIN_SESSION);

		ManagedObjectReference serviceInstace = new ManagedObjectReference();
		serviceInstace.setType(SERVICE_INSTANCE);
		serviceInstace.setValue(SERVICE_INSTANCE);

		this.serviceContent = vimPort.retrieveServiceContent(serviceInstace);
		this.perfManager = serviceContent.getPerfManager();
		this.propertyCollector = serviceContent.getPropertyCollector();
	}

	public void login() throws InvalidLocaleFaultMsg, InvalidLoginFaultMsg, RuntimeFaultFaultMsg, KeyManagementException, NoSuchAlgorithmException {
		vimPort.login(serviceContent.getSessionManager(), credentials.getUser(), credentials.getPassword(), null);
	}

	public void logout() throws InvalidLocaleFaultMsg, InvalidLoginFaultMsg, RuntimeFaultFaultMsg {
		vimPort.logout(serviceContent.getSessionManager());
	}

	public ServiceContent getServiceContent() {
		return serviceContent;
	}

	public VimPortType getVimPort() {
		return vimPort;
	}

	public ManagedObjectReference getPerfManager() {
		return perfManager;
	}

	public ManagedObjectReference getPropertyCollector() {
		return propertyCollector;
	}
	
}
