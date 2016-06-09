/**
 * LocalLBPoolMemberPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public interface LocalLBPoolMemberPortType extends java.rmi.Remote {

    /**
     * Gets the object statuses for all members of the specified pools.
     */
    public LocalLBPoolMemberMemberObjectStatus[][] get_object_status(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the connection limits for the specified pool members.
     */
    public void set_connection_limit(String[] pool_names, LocalLBPoolMemberMemberConnectionLimit[][] limits) throws java.rmi.RemoteException;

    /**
     * Gets the connection limits for all members in the specified
     * pools.
     */
    public LocalLBPoolMemberMemberConnectionLimit[][] get_connection_limit(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the ratios for the specified pool members.
     */
    public void set_ratio(String[] pool_names, LocalLBPoolMemberMemberRatio[][] ratios) throws java.rmi.RemoteException;

    /**
     * Gets the ratios for all members in the specified pools.
     */
    public LocalLBPoolMemberMemberRatio[][] get_ratio(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the priorities for the specified pool members.
     */
    public void set_priority(String[] pool_names, LocalLBPoolMemberMemberPriority[][] priorities) throws java.rmi.RemoteException;

    /**
     * Gets the priorities for all members in the specified pools.
     */
    public LocalLBPoolMemberMemberPriority[][] get_priority(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the dynamic ratios for the specified pool members.
     */
    public void set_dynamic_ratio(String[] pool_names, LocalLBPoolMemberMemberDynamicRatio[][] dynamic_ratios) throws java.rmi.RemoteException;

    /**
     * Gets the dynamic ratios for all members of the specified pools.
     */
    public LocalLBPoolMemberMemberDynamicRatio[][] get_dynamic_ratio(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the session states for the specified pool members. If
     * session state is enabled or true,
     *  this means that new sessions will be allowed to be established with
     * the pool members.
     */
    public void set_session_enabled_state(String[] pool_names, LocalLBPoolMemberMemberSessionState[][] session_states) throws java.rmi.RemoteException;

    /**
     * Note: This function has been deprecated. Please use get_session_status.
     * 
     *  Gets the session states for all members of the specified pools.
     */
    public LocalLBPoolMemberMemberSessionState[][] get_session_enabled_state(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Gets the session status for all members of the specified pools.
     */
    public LocalLBPoolMemberMemberSessionStatus[][] get_session_status(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the monitor/availability states for the specified pool
     * members.
     */
    public void set_monitor_state(String[] pool_names, LocalLBPoolMemberMemberMonitorState[][] monitor_states) throws java.rmi.RemoteException;

    /**
     * Gets the monitor/availability status for all members of the
     * specified pools.
     */
    public LocalLBPoolMemberMemberMonitorStatus[][] get_monitor_status(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets/creates the monitor associations for the specified pool
     * members. This basically creates the monitor 
     *  associations between a pool member and a monitor rule.
     */
    public void set_monitor_association(String[] pool_names, LocalLBPoolMemberMemberMonitorAssociation[][] monitor_associations) throws java.rmi.RemoteException;

    /**
     * Gets the monitor associations used by the specified pool members,
     * i.e. the monitor rules used by the pool members.
     */
    public LocalLBPoolMemberMemberMonitorAssociation[][] get_monitor_association(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Removes the monitor associations for the specified pool members.
     * Depending on the monitor association removal rule
     *  specified, this basically deletes any explicit monitor associations
     * between a pool member and a monitor rule and thus
     *  causing the pool member to use the default monitor association of
     * its parent pool, or this will delete any monitor 
     *  association for the pool members altogether, i.e. the specified pool
     * members will no longer be monitored.
     */
    public void remove_monitor_association(String[] pool_names, LocalLBPoolMemberMemberMonitorAssociationRemoval[][] monitor_associations) throws java.rmi.RemoteException;

    /**
     * Gets the monitor instance information for the pool members
     * in the specified pools.
     */
    public LocalLBPoolMemberMemberMonitorInstanceState[][] get_monitor_instance(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Resets the statistics for the specified set of pool members.
     */
    public void reset_statistics(String[] pool_names, CommonIPPortDefinition[][] members) throws java.rmi.RemoteException;

    /**
     * Gets the statistics for all pool members of the specified pools.
     */
    public LocalLBPoolMemberMemberStatistics[] get_all_statistics(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Gets the statistics for the specified set of pool members.
     */
    public LocalLBPoolMemberMemberStatistics[] get_statistics(String[] pool_names, CommonIPPortDefinition[][] members) throws java.rmi.RemoteException;

    /**
     * Gets the version information for this interface.
     */
    public String get_version() throws java.rmi.RemoteException;
}
