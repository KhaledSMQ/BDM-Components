/**
 * LocalLBPoolMember.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public interface LocalLBPoolMember extends javax.xml.rpc.Service {

/**
 * *IMPORTANT* This entire interface has been deprecated (as of
 *  11.0.0).  The functionality has been moved into the Pool
 *  interface.
 * 
 *  The PoolMember interface enables you to work with the pool members
 * and their settings, and statistics.
 */
    public String getLocalLBPoolMemberPortAddress();

    public LocalLBPoolMemberPortType getLocalLBPoolMemberPort() throws javax.xml.rpc.ServiceException;

    public LocalLBPoolMemberPortType getLocalLBPoolMemberPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
