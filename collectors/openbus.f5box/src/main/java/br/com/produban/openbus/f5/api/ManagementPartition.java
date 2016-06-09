/**
 * ManagementPartition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public interface ManagementPartition extends javax.xml.rpc.Service {

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
    public String getManagementPartitionPortAddress();

    public ManagementPartitionPortType getManagementPartitionPort() throws javax.xml.rpc.ServiceException;

    public ManagementPartitionPortType getManagementPartitionPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
