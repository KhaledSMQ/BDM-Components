/**
 * SystemStatisticsLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class SystemStatisticsLocator extends org.apache.axis.client.Service implements SystemStatistics {

/**
 * The Statistics interface enables you to get information on various
 * system statistics.
 * 
 *  This interface does not support transactions.
 */

    public SystemStatisticsLocator() {
    }


    public SystemStatisticsLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SystemStatisticsLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SystemStatisticsPort
    private String SystemStatisticsPort_address = "https://f5.test:443/iControl/iControlPortal.cgi";

    public String getSystemStatisticsPortAddress() {
        return SystemStatisticsPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String SystemStatisticsPortWSDDServiceName = "System.StatisticsPort";

    public String getSystemStatisticsPortWSDDServiceName() {
        return SystemStatisticsPortWSDDServiceName;
    }

    public void setSystemStatisticsPortWSDDServiceName(String name) {
        SystemStatisticsPortWSDDServiceName = name;
    }

    public SystemStatisticsPortType getSystemStatisticsPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SystemStatisticsPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSystemStatisticsPort(endpoint);
    }

    public SystemStatisticsPortType getSystemStatisticsPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            SystemStatisticsBindingStub _stub = new SystemStatisticsBindingStub(portAddress, this);
            _stub.setPortName(getSystemStatisticsPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSystemStatisticsPortEndpointAddress(String address) {
        SystemStatisticsPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (SystemStatisticsPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                SystemStatisticsBindingStub _stub = new SystemStatisticsBindingStub(new java.net.URL(SystemStatisticsPort_address), this);
                _stub.setPortName(getSystemStatisticsPortWSDDServiceName());
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
        if ("System.StatisticsPort".equals(inputPortName)) {
            return getSystemStatisticsPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:iControl", "System.Statistics");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:iControl", "System.StatisticsPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("SystemStatisticsPort".equals(portName)) {
            setSystemStatisticsPortEndpointAddress(address);
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
