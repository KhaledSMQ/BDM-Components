/**
 * SystemSystemInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public interface SystemSystemInfo extends javax.xml.rpc.Service {

/**
 * The SystemInfo interface enables you to query identifying attributes
 * of the system.
 */
    public String getSystemSystemInfoPortAddress();

    public SystemSystemInfoPortType getSystemSystemInfoPort() throws javax.xml.rpc.ServiceException;

    public SystemSystemInfoPortType getSystemSystemInfoPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
