/**
 * LocalLBPoolPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public interface LocalLBPoolPortType extends java.rmi.Remote {

    /**
     * Gets a list of all pools.
     */
    public String[] get_list() throws java.rmi.RemoteException;

    /**
     * Gets the statuses of the specified pools.
     */
    public LocalLBObjectStatus[] get_object_status(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * This method has been deprecated, due to the change in pool
     * member
     *  key definitions.  Please use create_v2.
     * 
     *  Creates a new pool.
     */
    public void create(String[] pool_names, LocalLBLBMethod[] lb_methods, CommonIPPortDefinition[][] members) throws java.rmi.RemoteException;

    /**
     * Creates a new pool.
     */
    public void create_v2(String[] pool_names, LocalLBLBMethod[] lb_methods, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Deletes the specified pools.
     */
    public void delete_pool(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Deletes all pools.
     */
    public void delete_all_pools() throws java.rmi.RemoteException;

    /**
     * This method has been deprecated, due to the change in pool
     * member
     *  key definitions.  Please use get_member_v2.
     * 
     *  Gets a list of pool members.
     */
    public CommonIPPortDefinition[][] get_member(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * This method has been deprecated, due to the change in pool
     * member
     *  key definitions.  Please use add_member_v2.
     * 
     *  Adds members to the specified pools.
     */
    public void add_member(String[] pool_names, CommonIPPortDefinition[][] members) throws java.rmi.RemoteException;

    /**
     * This method has been deprecated, due to the change in pool
     * member
     *  key definitions.  Please use remove_member_v2.
     * 
     *  Removes members from the specified pools.
     */
    public void remove_member(String[] pool_names, CommonIPPortDefinition[][] members) throws java.rmi.RemoteException;

    /**
     * Sets the load balancing methods for the specified pools.
     */
    public void set_lb_method(String[] pool_names, LocalLBLBMethod[] lb_methods) throws java.rmi.RemoteException;

    /**
     * Gets the load balancing methods for the specified pools.
     */
    public LocalLBLBMethod[] get_lb_method(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the minimum member counts that are required to be UP for
     * the specified pools.
     */
    public void set_minimum_up_member(String[] pool_names, long[] values) throws java.rmi.RemoteException;

    /**
     * Gets the minimum member counts that are required to be UP for
     * the specified pools.
     */
    public long[] get_minimum_up_member(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the states indicating that the feature that requires a
     * minimum number of 
     *  members to be UP is enabled/disabled for the specified pools.
     */
    public void set_minimum_up_member_enabled_state(String[] pool_names, CommonEnabledState[] states) throws java.rmi.RemoteException;

    /**
     * Gets the states indicating that the feature that requires a
     * minimum number of 
     *  members to be UP is enabled/disabled for the specified pools.
     */
    public CommonEnabledState[] get_minimum_up_member_enabled_state(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the actions to be taken if the minimum number of members
     * required to be UP
     *  for the specified pools is not met.
     */
    public void set_minimum_up_member_action(String[] pool_names, CommonHAAction[] actions) throws java.rmi.RemoteException;

    /**
     * Gets the actions to be taken if the minimum number of members
     * required to be UP
     *  for the specified pools is not met.
     */
    public CommonHAAction[] get_minimum_up_member_action(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the minimum active member counts for the specified pools.
     */
    public void set_minimum_active_member(String[] pool_names, long[] values) throws java.rmi.RemoteException;

    /**
     * Gets the minimum active member counts for the specified pools.
     */
    public long[] get_minimum_active_member(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * This method has been deprecated (immediately), due to introduction
     * of device names instead of unit IDs.  Please use
     *  set_gateway_failsafe_device.
     * 
     *  Sets the gateway failsafe unit IDs for the specified pools.
     */
    public void set_gateway_failsafe_unit_id(String[] pool_names, long[] unit_ids) throws java.rmi.RemoteException;

    /**
     * This method has been deprecated (immediately), due to introduction
     * of device names instead of unit IDs.  Please use
     *  get_gateway_failsafe_device.
     * 
     *  Gets the gateway failsafe unit IDs for the specified pools.
     */
    public long[] get_gateway_failsafe_unit_id(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the gateway failsafe devices for the specified pools.
     */
    public void set_gateway_failsafe_device(String[] pool_names, String[] devices) throws java.rmi.RemoteException;

    /**
     * Gets the gateway failsafe devices for the specified pools.
     */
    public String[] get_gateway_failsafe_device(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Gets the current active member counts for the specified pools.
     */
    public long[] get_active_member_count(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the states indicating whether SNATs are allowed for the
     * specified pools.
     */
    public void set_allow_snat_state(String[] pool_names, CommonEnabledState[] states) throws java.rmi.RemoteException;

    /**
     * Gets the states indicating whether SNATs are allowed for the
     * specified pools.
     */
    public CommonEnabledState[] get_allow_snat_state(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the states indicating whether NATs are allowed for the
     * specified pools.
     */
    public void set_allow_nat_state(String[] pool_names, CommonEnabledState[] states) throws java.rmi.RemoteException;

    /**
     * Gets the states indicating whether NATs are allowed for the
     * specified pools.
     */
    public CommonEnabledState[] get_allow_nat_state(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the simple timeouts for the specified pools.
     */
    public void set_simple_timeout(String[] pool_names, long[] simple_timeouts) throws java.rmi.RemoteException;

    /**
     * Gets the simple timeouts for the specified pools.
     */
    public long[] get_simple_timeout(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the IP ToS values for client traffic for the specified
     * pools.
     */
    public void set_client_ip_tos(String[] pool_names, long[] values) throws java.rmi.RemoteException;

    /**
     * Gets the IP ToS values for client traffic for the specified
     * pools.
     */
    public long[] get_client_ip_tos(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the IP ToS values for server traffic for the specified
     * pools.
     */
    public void set_server_ip_tos(String[] pool_names, long[] values) throws java.rmi.RemoteException;

    /**
     * Gets the IP ToS values for server traffic for the specified
     * pools.
     */
    public long[] get_server_ip_tos(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the link QoS values for client traffic for the specified
     * pools.
     */
    public void set_client_link_qos(String[] pool_names, long[] values) throws java.rmi.RemoteException;

    /**
     * Gets the link QoS values for client traffic for the specified
     * pools.
     */
    public long[] get_client_link_qos(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the link QoS values for server traffic for the specified
     * pools.
     */
    public void set_server_link_qos(String[] pool_names, long[] values) throws java.rmi.RemoteException;

    /**
     * Gets the link QoS values for server traffic for the specified
     * pools.
     */
    public long[] get_server_link_qos(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the ramp-up time (in seconds) to gradually ramp up the
     * load on newly added
     *  or freshly detected UP pool members.
     */
    public void set_slow_ramp_time(String[] pool_names, long[] values) throws java.rmi.RemoteException;

    /**
     * Gets the ramp-up time (in seconds) to gradually ramp up the
     * load on newly added
     *  or freshly detected UP pool members.
     */
    public long[] get_slow_ramp_time(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets/creates the monitor associations for the specified pools.
     * This basically creates the monitor associations
     *  between a pool and a monitor rule.
     */
    public void set_monitor_association(LocalLBPoolMonitorAssociation[] monitor_associations) throws java.rmi.RemoteException;

    /**
     * Gets the monitor associations for the specified pools, i.e.
     * the monitor rules used by the pools.
     */
    public LocalLBPoolMonitorAssociation[] get_monitor_association(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Removes the monitor associations for the specified pools. 
     * This basically deletes the monitor
     *  associations between a pool and a monitor rule, i.e. the specified
     * pools will no longer
     *  be monitored.
     */
    public void remove_monitor_association(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Gets the monitor instance information for the specified pools,
     * i.e. the monitor instance
     *  information for the pool members of the specified pools.
     */
    public LocalLBMonitorInstanceState[][] get_monitor_instance(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Gets the aggregate dynamic ratio values from all the members
     * of the pools.
     */
    public long[] get_aggregate_dynamic_ratio(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the action to take when the node goes down for the specified
     * pools.
     */
    public void set_action_on_service_down(String[] pool_names, LocalLBServiceDownAction[] actions) throws java.rmi.RemoteException;

    /**
     * Gets the action to take when the node goes down for the specified
     * pools.
     */
    public LocalLBServiceDownAction[] get_action_on_service_down(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Do not count the weight of persisted connections when performing
     * load balancing decisions for ratio-based algorithms.
     */
    public void set_ignore_persisted_weight_state(String[] pool_names, CommonEnabledState[] states) throws java.rmi.RemoteException;

    /**
     * Gets the states ignoring the weight of persistence entries
     * being
     *  used in load balancing decisions for the specified pools.
     */
    public CommonEnabledState[] get_ignore_persisted_weight_state(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the states enabling connection queuing when pool
     *  member or node connection limits are reached, for the
     *  specified pools.
     * 
     *  When queuing is not enabled, new connections are reset when
     *  connection limits are met.
     */
    public void set_queue_on_connection_limit_state(String[] pool_names, CommonEnabledState[] states) throws java.rmi.RemoteException;

    /**
     * Gets the states enabling connection queuing when pool
     *  member or node connection limits are reached, for the
     *  specified pools.
     */
    public CommonEnabledState[] get_queue_on_connection_limit_state(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the the maximum number of connections that may
     *  be simultaneously queued to go to any member of the
     *  specified pools.
     */
    public void set_queue_depth_limit(String[] pool_names, long[] values) throws java.rmi.RemoteException;

    /**
     * Gets the the maximum number of connections that may
     *  be simultaneously queued to go to any member of the
     *  specified pools.
     */
    public long[] get_queue_depth_limit(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the the maximum time a connection remains
     *  queued, for the specified pools.
     */
    public void set_queue_time_limit(String[] pool_names, long[] values) throws java.rmi.RemoteException;

    /**
     * Gets the the maximum time a connection remains
     *  queued, for the specified pools.
     */
    public long[] get_queue_time_limit(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the number of times the system tries to contact a pool
     * member after a passive failure, for the specified pools.
     * 
     *  A passive failure consists of a server-connect failure or a
     *  failure to receive a data response within a user-specified
     *  interval.
     */
    public void set_reselect_tries(String[] pool_names, long[] values) throws java.rmi.RemoteException;

    /**
     * Gets the number of times the system tries to contact a pool
     * member after a passive failure, for the specified pools.
     */
    public long[] get_reselect_tries(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the description for a set of pools.
     * 
     *  This is an arbitrary field which can be used for any purpose.
     */
    public void set_description(String[] pool_names, String[] descriptions) throws java.rmi.RemoteException;

    /**
     * Gets the descriptions for a set of pools.
     */
    public String[] get_description(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Resets the statistics for the specified pools.
     */
    public void reset_statistics(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Gets the statistics for all the pools.
     */
    public LocalLBPoolPoolStatistics get_all_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the statistics for the specified pools.
     */
    public LocalLBPoolPoolStatistics get_statistics(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Gets the persistence records based on the specified persistent
     * modes for the specified pools.
     */
    public LocalLBPersistenceRecord[][] get_persistence_record(String[] pool_names, LocalLBPersistenceMode[] persistence_modes) throws java.rmi.RemoteException;

    /**
     * Deletes the persistence records based on the specified persistent
     * modes for the specified pools.
     */
    public void delete_persistence_record(String[] pool_names, LocalLBPersistenceMode[] persistence_modes) throws java.rmi.RemoteException;

    /**
     * Gets the lists of profiles for the specified pools.
     */
    public CommonPoolProfileAttribute[][] get_profile(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Adds profiles to the specified pools.
     */
    public void add_profile(String[] pool_names, String[][] profiles) throws java.rmi.RemoteException;

    /**
     * Removes profiles from the specified pools.
     */
    public void remove_profile(String[] pool_names, String[][] profiles) throws java.rmi.RemoteException;

    /**
     * Removes all profiles from the specified pools.
     */
    public void remove_all_profiles(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Gets a list of pool members.
     */
    public CommonAddressPort[][] get_member_v2(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Gets the addresses for a set of pool members.
     */
    public String[][] get_member_address(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Adds members to the specified pools.
     */
    public void add_member_v2(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Removes members from the specified pools.
     */
    public void remove_member_v2(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Gets the object statuses for a set of pool members.
     */
    public LocalLBObjectStatus[][] get_member_object_status(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Sets the connection limits for a set of pool members.
     */
    public void set_member_connection_limit(String[] pool_names, CommonAddressPort[][] members, long[][] limits) throws java.rmi.RemoteException;

    /**
     * Gets the connection limits for a set of pool members.
     */
    public long[][] get_member_connection_limit(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Sets the rate limits for a set of pool members.
     */
    public void set_member_rate_limit(String[] pool_names, CommonAddressPort[][] members, long[][] limits) throws java.rmi.RemoteException;

    /**
     * Gets the rate limit for a set of pool members.
     */
    public long[][] get_member_rate_limit(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Sets the ratios for a set of pool members.
     */
    public void set_member_ratio(String[] pool_names, CommonAddressPort[][] members, long[][] ratios) throws java.rmi.RemoteException;

    /**
     * Gets the ratios for a set of pool members.
     */
    public long[][] get_member_ratio(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Sets the priorities for a set of pool members.
     */
    public void set_member_priority(String[] pool_names, CommonAddressPort[][] members, long[][] priorities) throws java.rmi.RemoteException;

    /**
     * Gets the priorities for a set of pool members.
     */
    public long[][] get_member_priority(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Sets the dynamic ratios for a set of pool members.
     */
    public void set_member_dynamic_ratio(String[] pool_names, CommonAddressPort[][] members, long[][] dynamic_ratios) throws java.rmi.RemoteException;

    /**
     * Gets the dynamic ratios for a set of pool members.
     */
    public long[][] get_member_dynamic_ratio(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Sets the session states for a set of pool members. If session
     * state is enabled or true,
     *  this means that new sessions will be allowed to be established with
     * the pool members.
     */
    public void set_member_session_enabled_state(String[] pool_names, CommonAddressPort[][] members, CommonEnabledState[][] session_states) throws java.rmi.RemoteException;

    /**
     * Gets the session status for a set of pool members.
     */
    public LocalLBSessionStatus[][] get_member_session_status(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Sets the monitor/availability states for the specified pool
     * members.
     */
    public void set_member_monitor_state(String[] pool_names, CommonAddressPort[][] members, CommonEnabledState[][] monitor_states) throws java.rmi.RemoteException;

    /**
     * Gets the monitor/availability status for the specified pool
     * members.
     */
    public LocalLBMonitorStatus[][] get_member_monitor_status(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Sets the monitor rule used to monitor the health of a set of
     * pool
     *  members.
     * 
     *  Monitoring can be turned off on a pool member by setting the
     *  monitor rule type to MONITOR_RULE_TYPE_NONE.
     */
    public void set_member_monitor_rule(String[] pool_names, CommonAddressPort[][] members, LocalLBMonitorRule[][] monitor_rules) throws java.rmi.RemoteException;

    /**
     * Gets the monitor rules used by the specified pool members.
     */
    public LocalLBMonitorRule[][] get_member_monitor_rule(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Gets the monitor instance information for the pool members
     * in the specified pools.
     */
    public LocalLBMonitorInstanceState[][] get_member_monitor_instance(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Sets the description for a set of pool members.
     * 
     *  This is an arbitrary field which can be used for any purpose.
     */
    public void set_member_description(String[] pool_names, CommonAddressPort[][] members, String[][] descriptions) throws java.rmi.RemoteException;

    /**
     * Gets the descriptions for a set of pool members.
     */
    public String[][] get_member_description(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Resets the statistics for a set of pool members.
     */
    public void reset_member_statistics(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Gets the statistics for all pool members of the specified pools.
     */
    public LocalLBPoolMemberStatistics[] get_all_member_statistics(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Gets the statistics for the specified set of pool members.
     */
    public LocalLBPoolMemberStatistics[] get_member_statistics(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Sets the state indicating that a pool member inherits
     *  profiles from the parent pool, for the specified set of
     *  pool members.
     * 
     *  Note: As of version 11.0.0, the system supports adding
     *  profiles to pool members only if the pool member does not
     *  inherit from the pool (so one must use
     *  set_member_inherit_profile_state to set a pool member not to
     *  inherit before using add_member_profile). In the future
     *  this constraint may be removed.
     */
    public void set_member_inherit_profile_state(String[] pool_names, CommonAddressPort[][] members, CommonEnabledState[][] states) throws java.rmi.RemoteException;

    /**
     * Gets the state indicating that a pool member inherits
     *  profiles from the parent pool, for the specified set of
     *  pool members.
     */
    public CommonEnabledState[][] get_member_inherit_profile_state(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Gets the lists of profiles for the specified pool members.
     */
    public CommonPoolProfileAttribute[][][] get_member_profile(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Adds profiles to the specified pool members.
     * 
     *  Note: As of version 11.0.0, the system supports adding
     *  profiles to pool members only if the pool member does not
     *  inherit from the pool (so one must use
     *  set_member_inherit_profile_state to set a pool member not to
     *  inherit before using add_member_profile. In the future this
     *  constraint may be removed.
     *  
     *  Note: As of version 11.0.0, the system allows just one
     *  profile per pool member. In the future this constraint may
     *  be removed.
     */
    public void add_member_profile(String[] pool_names, CommonAddressPort[][] members, String[][][] profiles) throws java.rmi.RemoteException;

    /**
     * Removes profiles from the specified pool members.
     */
    public void remove_member_profile(String[] pool_names, CommonAddressPort[][] members, String[][][] profiles) throws java.rmi.RemoteException;

    /**
     * Removes all profiles from the specified pool members.
     */
    public void remove_all_member_profiles(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Gets the user metadata for the specified pools.
     *  User metadata, also metadata for short, is mainly a name/value
     *  pair that is associated with a metadata capable config (MCC) object.
     * Some exmaples of MCC objects are ltm pool and virtual server.
     *  The combination of the MCC object name and the metadata name
     *  uniguely identify an instance of the metadata. One MCC object
     *  may have multiple instances of metadata associated with it.
     *  In addition to name/value pair, metadata also has a persistence
     *  attribute. See Common::MetadataPersistence for its definition.
     */
    public String[][] get_metadata(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Adds the metadata for the specified pools.
     *  For definition of the metadata, refer to the get_metadata method
     *  description.
     */
    public void add_metadata(String[] pool_names, String[][] names, String[][] values) throws java.rmi.RemoteException;

    /**
     * Removes the metadata for the specified pools and names.
     *  For definition of the metadata, refer to the get_metadata method
     *  description.
     */
    public void remove_metadata(String[] pool_names, String[][] names) throws java.rmi.RemoteException;

    /**
     * Removes all the metadata instances for the specified pools.
     */
    public void remove_all_metadata(String[] pool_names) throws java.rmi.RemoteException;

    /**
     * Sets the values for the specified metadata.
     *  For definition of the metadata, refer to the get_metadata method
     *  description.
     */
    public void set_metadata_value(String[] pool_names, String[][] names, String[][] values) throws java.rmi.RemoteException;

    /**
     * Gets the metadata values for the specified metadata.
     *  For definition of the metadata, refer to the get_metadata method
     *  description.
     */
    public String[][] get_metadata_value(String[] pool_names, String[][] names) throws java.rmi.RemoteException;

    /**
     * Sets the descriptions for the specified metadata.
     *  For definition of the metadata, refer to the get_metadata method
     *  description.
     */
    public void set_metadata_description(String[] pool_names, String[][] names, String[][] descriptions) throws java.rmi.RemoteException;

    /**
     * Gets the metadata descriptions for the specified metadata.
     *  For definition of the metadata, refer to the get_metadata method
     *  description.
     */
    public String[][] get_metadata_description(String[] pool_names, String[][] names) throws java.rmi.RemoteException;

    /**
     * Sets the persistence for the specified metadata.
     *  For definition of the metadata, refer to the get_metadata method
     *  description.
     */
    public void set_metadata_persistence(String[] pool_names, String[][] names, CommonMetadataPersistence[][] values) throws java.rmi.RemoteException;

    /**
     * Gets the persistence for the specified metadata.
     *  For definition of the metadata, refer to the get_metadata method
     *  description.
     */
    public CommonMetadataPersistence[][] get_metadata_persistence(String[] pool_names, String[][] names) throws java.rmi.RemoteException;

    /**
     * Gets the user metadata for the specified pool members.
     *  User metadata, also metadata for short, is mainly a name/value
     *  pair that is associated with a metadata capable config (MCC) object.
     * Some exmaples of MCC objects are ltm pool and virtual server.
     *  The combination of the MCC object name and the metadata name
     *  uniguely identify an instance of the metadata. One MCC object
     *  may have multiple instances of metadata associated with it.
     *  In addition to name/value pair, metadata also has a persistence
     *  attribute. See Common::MetadataPersistence for its definition.
     */
    public String[][][] get_member_metadata(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Adds the metadata for the specified pool members.
     *  For definition of the metadata, refer to the get_member_metadata
     * method
     *  description.
     */
    public void add_member_metadata(String[] pool_names, CommonAddressPort[][] members, String[][][] names, String[][][] values) throws java.rmi.RemoteException;

    /**
     * Removes the metadata for the specified pool members and names.
     * For definition of the metadata, refer to the get_member_metadata method
     * description.
     */
    public void remove_member_metadata(String[] pool_names, CommonAddressPort[][] members, String[][][] names) throws java.rmi.RemoteException;

    /**
     * Removes all the metadata instances for the specified pool members.
     */
    public void remove_all_member_metadata(String[] pool_names, CommonAddressPort[][] members) throws java.rmi.RemoteException;

    /**
     * Sets the values for the specified metadata.
     *  For definition of the metadata, refer to the get_member_metadata
     * method
     *  description.
     */
    public void set_member_metadata_value(String[] pool_names, CommonAddressPort[][] members, String[][][] names, String[][][] values) throws java.rmi.RemoteException;

    /**
     * Gets the metadata values for the specified metadata.
     *  For definition of the metadata, refer to the get_member_metadata
     * method
     *  description.
     */
    public String[][][] get_member_metadata_value(String[] pool_names, CommonAddressPort[][] members, String[][][] names) throws java.rmi.RemoteException;

    /**
     * Sets the descriptions for the specified metadata.
     *  For definition of the metadata, refer to the get_metadata method
     *  description.
     */
    public void set_member_metadata_description(String[] pool_names, CommonAddressPort[][] members, String[][][] names, String[][][] descriptions) throws java.rmi.RemoteException;

    /**
     * Gets the metadata descriptions for the specified metadata.
     *  For definition of the metadata, refer to the get_member_metadata
     * method
     *  description.
     */
    public String[][][] get_member_metadata_description(String[] pool_names, CommonAddressPort[][] members, String[][][] names) throws java.rmi.RemoteException;

    /**
     * Sets the persistence for the specified metadata.
     *  For definition of the metadata, refer to the get_member_metadata
     * method
     *  description.
     */
    public void set_member_metadata_persistence(String[] pool_names, CommonAddressPort[][] members, String[][][] names, CommonMetadataPersistence[][][] values) throws java.rmi.RemoteException;

    /**
     * Gets the persistence for the specified metadata.
     *  For definition of the metadata, refer to the get_member_metadata
     * method
     *  description.
     */
    public CommonMetadataPersistence[][][] get_member_metadata_persistence(String[] pool_names, CommonAddressPort[][] members, String[][][] names) throws java.rmi.RemoteException;

    /**
     * Gets the version information for this interface.
     */
    public String get_version() throws java.rmi.RemoteException;
}
