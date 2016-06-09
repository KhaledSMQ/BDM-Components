/**
 * ManagementPartitionLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class ManagementPartitionLocator extends org.apache.axis.client.Service implements ManagementPartition {

/**
 * This interface has been deprecated (as of 11.0.0).  Please use
 *  Management::Folder and System::Session in its stead.
 *  (Please note that the system does not supply recommended
 *  replacement functionality for the methods
 *  {set,get}_default_route_domain yet.)
 * 
 *  The Partition interface enables you to manage AuthZ Administrative
 * Partitions, as well
 *  as relationship between users, roles, and their associated partitions.
 */

    public ManagementPartitionLocator() {
    }


    public ManagementPartitionLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ManagementPartitionLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ManagementPartitionPort
    private String ManagementPartitionPort_address = "https://f5.test:443/iControl/iControlPortal.cgi";

    public String getManagementPartitionPortAddress() {
        return ManagementPartitionPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String ManagementPartitionPortWSDDServiceName = "Management.PartitionPort";

    public String getManagementPartitionPortWSDDServiceName() {
        return ManagementPartitionPortWSDDServiceName;
    }

    public void setManagementPartitionPortWSDDServiceName(String name) {
        ManagementPartitionPortWSDDServiceName = name;
    }

    public ManagementPartitionPortType getManagementPartitionPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ManagementPartitionPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getManagementPartitionPort(endpoint);
    }

    public ManagementPartitionPortType getManagementPartitionPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ManagementPartitionBindingStub _stub = new ManagementPartitionBindingStub(portAddress, this);
            _stub.setPortName(getManagementPartitionPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setManagementPartitionPortEndpointAddress(String address) {
        ManagementPartitionPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ManagementPartitionPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ManagementPartitionBindingStub _stub = new ManagementPartitionBindingStub(new java.net.URL(ManagementPartitionPort_address), this);
                _stub.setPortName(getManagementPartitionPortWSDDServiceName());
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
        if ("Management.PartitionPort".equals(inputPortName)) {
            return getManagementPartitionPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:iControl", "Management.Partition");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:iControl", "Management.PartitionPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("ManagementPartitionPort".equals(portName)) {
            setManagementPartitionPortEndpointAddress(address);
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
