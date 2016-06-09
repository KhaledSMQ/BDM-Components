/**
 * LocalLBVirtualServerLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class LocalLBVirtualServerLocator extends org.apache.axis.client.Service implements LocalLBVirtualServer {

/**
 * The VirtualServer interface enables you to work with the
 *  states, statistics, limits, availability, and settings of a
 *  local load balancer's virtual servers.  For example, you can
 *  use the Virtual Server interface to create a virtual server
 *  from a specified pool or rule or to delete a virtual server
 *  from a specified pool.
 * 
 *  Note that the source and destination addresses in the firewall
 *  methods (get_fw_rule and so on) are type Common::NetAddress, a
 *  type which allows one to specify a prefix length after the
 *  address, e.g., "10.1.1.0/24". The source address in
 *  set_source_address and so on is type Common::NetAddress as
 *  well.
 */

    public LocalLBVirtualServerLocator() {
    }


    public LocalLBVirtualServerLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public LocalLBVirtualServerLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LocalLBVirtualServerPort
    private String LocalLBVirtualServerPort_address = "https://f5.test:443/iControl/iControlPortal.cgi";

    public String getLocalLBVirtualServerPortAddress() {
        return LocalLBVirtualServerPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String LocalLBVirtualServerPortWSDDServiceName = "LocalLB.VirtualServerPort";

    public String getLocalLBVirtualServerPortWSDDServiceName() {
        return LocalLBVirtualServerPortWSDDServiceName;
    }

    public void setLocalLBVirtualServerPortWSDDServiceName(String name) {
        LocalLBVirtualServerPortWSDDServiceName = name;
    }

    public LocalLBVirtualServerPortType getLocalLBVirtualServerPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LocalLBVirtualServerPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLocalLBVirtualServerPort(endpoint);
    }

    public LocalLBVirtualServerPortType getLocalLBVirtualServerPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            LocalLBVirtualServerBindingStub _stub = new LocalLBVirtualServerBindingStub(portAddress, this);
            _stub.setPortName(getLocalLBVirtualServerPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLocalLBVirtualServerPortEndpointAddress(String address) {
        LocalLBVirtualServerPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (LocalLBVirtualServerPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                LocalLBVirtualServerBindingStub _stub = new LocalLBVirtualServerBindingStub(new java.net.URL(LocalLBVirtualServerPort_address), this);
                _stub.setPortName(getLocalLBVirtualServerPortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("LocalLB.VirtualServerPort".equals(inputPortName)) {
            return getLocalLBVirtualServerPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:iControl", "LocalLB.VirtualServer");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:iControl", "LocalLB.VirtualServerPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("LocalLBVirtualServerPort".equals(portName)) {
            setLocalLBVirtualServerPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
