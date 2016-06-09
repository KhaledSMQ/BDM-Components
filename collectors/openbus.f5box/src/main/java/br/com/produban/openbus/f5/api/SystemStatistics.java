/**
 * SystemStatistics.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public interface SystemStatistics extends javax.xml.rpc.Service {

/**
 * The Statistics interface enables you to get information on various
 * system statistics.
 * 
 *  This interface does not support transactions.
 */
    public String getSystemStatisticsPortAddress();

    public SystemStatisticsPortType getSystemStatisticsPort() throws javax.xml.rpc.ServiceException;

    public SystemStatisticsPortType getSystemStatisticsPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
