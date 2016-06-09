/**
 * LocalLBVirtualServer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public interface LocalLBVirtualServer extends javax.xml.rpc.Service {

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
    public String getLocalLBVirtualServerPortAddress();

    public LocalLBVirtualServerPortType getLocalLBVirtualServerPort() throws javax.xml.rpc.ServiceException;

    public LocalLBVirtualServerPortType getLocalLBVirtualServerPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
