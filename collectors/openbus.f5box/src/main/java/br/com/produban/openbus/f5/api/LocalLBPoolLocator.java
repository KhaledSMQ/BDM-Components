/**
 * LocalLBPoolLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class LocalLBPoolLocator extends org.apache.axis.client.Service implements LocalLBPool {

/**
 * The Pool interface enables you to work with attributes, and statistics
 * for pools.  
 *  You can also use this interface to create pools, add members to a
 * pool, delete members from a pool, 
 *  find out the load balancing mode for a pool, and set the load balancing
 * mode for a pool.
 */

    public LocalLBPoolLocator() {
    }


    public LocalLBPoolLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public LocalLBPoolLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LocalLBPoolPort
    private String LocalLBPoolPort_address = "https://f5.test:443/iControl/iControlPortal.cgi";

    public String getLocalLBPoolPortAddress() {
        return LocalLBPoolPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String LocalLBPoolPortWSDDServiceName = "LocalLB.PoolPort";

    public String getLocalLBPoolPortWSDDServiceName() {
        return LocalLBPoolPortWSDDServiceName;
    }

    public void setLocalLBPoolPortWSDDServiceName(String name) {
        LocalLBPoolPortWSDDServiceName = name;
    }

    public LocalLBPoolPortType getLocalLBPoolPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LocalLBPoolPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLocalLBPoolPort(endpoint);
    }

    public LocalLBPoolPortType getLocalLBPoolPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            LocalLBPoolBindingStub _stub = new LocalLBPoolBindingStub(portAddress, this);
            _stub.setPortName(getLocalLBPoolPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLocalLBPoolPortEndpointAddress(String address) {
        LocalLBPoolPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (LocalLBPoolPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                LocalLBPoolBindingStub _stub = new LocalLBPoolBindingStub(new java.net.URL(LocalLBPoolPort_address), this);
                _stub.setPortName(getLocalLBPoolPortWSDDServiceName());
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
        if ("LocalLB.PoolPort".equals(inputPortName)) {
            return getLocalLBPoolPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:iControl", "LocalLB.Pool");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:iControl", "LocalLB.PoolPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("LocalLBPoolPort".equals(portName)) {
            setLocalLBPoolPortEndpointAddress(address);
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
