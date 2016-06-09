/**
 * LocalLBVirtualServerPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public interface LocalLBVirtualServerPortType extends java.rmi.Remote {

    /**
     * Gets a list of virtual servers.
     */
    public String[] get_list() throws java.rmi.RemoteException;

    /**
     * Gets the status of each of the specified virtual servers.
     */
    public LocalLBObjectStatus[] get_object_status(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Creates or updates virtual servers from the specified resources.
     * Takes additional, optional parameters 
     *  that enable you to override the default optional values.
     */
    public void create(CommonVirtualServerDefinition[] definitions, String[] wildmasks, LocalLBVirtualServerVirtualServerResource[] resources, LocalLBVirtualServerVirtualServerProfile[][] profiles) throws java.rmi.RemoteException;

    /**
     * Deletes the specified virtual servers.
     */
    public void delete_virtual_server(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Deletes all virtual servers.
     */
    public void delete_all_virtual_servers() throws java.rmi.RemoteException;

    /**
     * Sets the types for the specified virtual servers.
     */
    public void set_type(String[] virtual_servers, LocalLBVirtualServerVirtualServerType[] types) throws java.rmi.RemoteException;

    /**
     * Gets the types of the specified virtual servers.
     */
    public LocalLBVirtualServerVirtualServerType[] get_type(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Gets the CMP enable modes from the specified virtual servers.
     * This is read-only, as the modes are set according to the system and
     * configuration, and influenced by the desired CMP enabled state.
     */
    public LocalLBVirtualServerVirtualServerCMPEnableMode[] get_cmp_enable_mode(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * This method has been deprecated due to changing the virtual
     * address
     *  key.  Please use set_destination_v2 in its stead.
     * 
     *  Sets the destination IP and port for the specified virtual servers.
     */
    public void set_destination(String[] virtual_servers, CommonIPPortDefinition[] destinations) throws java.rmi.RemoteException;

    /**
     * This method has been deprecated due to changing the virtual
     * address
     *  key.  Please use set_destination_v2 in its stead.
     * 
     *  Gets the destination IP and port of the specified virtual servers.
     */
    public CommonIPPortDefinition[] get_destination(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the bandwidth controller policy for the specified virtual
     * servers.
     */
    public void set_bw_controller_policy(String[] virtual_servers, String[] policies) throws java.rmi.RemoteException;

    /**
     * Gets the bandwidth controller policy of the specified virtual
     * servers.
     */
    public String[] get_bw_controller_policy(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the destination virtual address and port for the specified
     * virtual servers.
     */
    public void set_destination_v2(String[] virtual_servers, CommonAddressPort[] destinations) throws java.rmi.RemoteException;

    /**
     * Gets the destination virtual address and port of the specified
     * virtual servers.
     */
    public CommonAddressPort[] get_destination_v2(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the wildmasks for the specified virtual servers.
     */
    public void set_wildmask(String[] virtual_servers, String[] wildmasks) throws java.rmi.RemoteException;

    /**
     * Gets the wildmasks for the specified virtual servers.
     */
    public String[] get_wildmask(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the source addresses for the specified virtual
     *  servers. This specifies an IP address or network from which
     *  the virtual server will accept traffic.
     */
    public void set_source_address(String[] virtual_servers, String[] addresses) throws java.rmi.RemoteException;

    /**
     * Gets the source address for the specified virtual servers.
     */
    public String[] get_source_address(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the protocols supported by the specified virtual servers,
     * one of IP, TCP or UDP.
     */
    public void set_protocol(String[] virtual_servers, CommonProtocolType[] protocols) throws java.rmi.RemoteException;

    /**
     * Gets the protocols supported by the specified virtual servers.
     */
    public CommonProtocolType[] get_protocol(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the enabled state of the specified virtual servers.
     */
    public void set_enabled_state(String[] virtual_servers, CommonEnabledState[] states) throws java.rmi.RemoteException;

    /**
     * Gets the enabled state of the specified virtual server.
     */
    public CommonEnabledState[] get_enabled_state(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the connection limits of the specified virtual servers.
     */
    public void set_connection_limit(String[] virtual_servers, CommonULong64[] limits) throws java.rmi.RemoteException;

    /**
     * Gets the connection limits for the specified virtual servers.
     */
    public CommonULong64[] get_connection_limit(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the rate limits of the specified virtual servers.
     */
    public void set_rate_limit(String[] virtual_servers, long[] limits) throws java.rmi.RemoteException;

    /**
     * Gets the rate limits for the specified virtual servers.
     */
    public long[] get_rate_limit(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the rate limit modes of the specified virtual servers.
     * 
     *   Indicates whether the rate limit is applied per virtual object,
     * per source
     *   address, per destination address, or some combination thereof. 
     * The default
     *   value is 'RATE_LIMIT_MODE_OBJECT', which does not use the source
     * or destination address as part of the key.
     */
    public void set_rate_limit_mode(String[] virtual_servers, LocalLBRateLimitMode[] modes) throws java.rmi.RemoteException;

    /**
     * Gets the rate limit modes for the specified virtual servers.
     */
    public LocalLBRateLimitMode[] get_rate_limit_mode(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the rate limit source masks of the specified virtual servers.
     * 
     *   The mask is applied against the source address when the mode
     *   specifies using the source address as part of the rate limiting
     *   categorization.  Setting this value to '0' is the equivalent of
     *   using the entire IP address.
     */
    public void set_rate_limit_source_mask(String[] virtual_servers, long[] masks) throws java.rmi.RemoteException;

    /**
     * Gets the rate limit source masks for the specified virtual
     * servers.
     */
    public long[] get_rate_limit_source_mask(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the rate limit destination masks of the specified virtual
     * servers.
     *  
     *   The mask is applied against the destination address when the mode
     * specifies using the source address as part of the rate limiting
     *   categorization.  Setting this value to '0' is the equivalent of
     *   using the entire IP address.
     */
    public void set_rate_limit_destination_mask(String[] virtual_servers, long[] masks) throws java.rmi.RemoteException;

    /**
     * Gets the rate limit destination masks for the specified virtual
     * servers.
     */
    public long[] get_rate_limit_destination_mask(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the rate classes that will be used to rate limit the traffic.
     */
    public void set_rate_class(String[] virtual_servers, String[] rate_classes) throws java.rmi.RemoteException;

    /**
     * Gets the rate classes that will be used to rate limit the traffic.
     */
    public String[] get_rate_class(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the mirror connection states for the specified virtual
     * servers.
     */
    public void set_connection_mirror_state(String[] virtual_servers, CommonEnabledState[] states) throws java.rmi.RemoteException;

    /**
     * Gets the mirror connection states for the specified virtual
     * servers.
     */
    public CommonEnabledState[] get_connection_mirror_state(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the port translation states for the specified virtual
     * servers.  Enables or disables port translation.
     */
    public void set_translate_port_state(String[] virtual_servers, CommonEnabledState[] states) throws java.rmi.RemoteException;

    /**
     * Gets the port translation states for the specified virtual
     * servers.  Enables or disables port translation.
     */
    public CommonEnabledState[] get_translate_port_state(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the address translation states for the specified virtual
     * servers.  Enables or disables address translation.
     */
    public void set_translate_address_state(String[] virtual_servers, CommonEnabledState[] states) throws java.rmi.RemoteException;

    /**
     * Gets the address translation states for the specified virtual
     * servers.  Enables or disables address translation.
     */
    public CommonEnabledState[] get_translate_address_state(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the NAT64 translation states for the specified virtual
     * servers.
     *  
     *  When this setting is enabled, the system translates IPv6 to
     *  IPv4 by chopping off the first 96 bits of the incoming
     *  destination address. The remaining 32 bits are kept as an IPv4
     *  address and used as the translation destination.
     */
    public void set_nat64_state(String[] virtual_servers, CommonEnabledState[] states) throws java.rmi.RemoteException;

    /**
     * Gets the NAT64 translation states for the specified virtual
     * servers.
     */
    public CommonEnabledState[] get_nat64_state(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the source port behavior for the specified virtual servers.
     */
    public void set_source_port_behavior(String[] virtual_servers, CommonSourcePortBehavior[] source_port_behaviors) throws java.rmi.RemoteException;

    /**
     * Gets the source port behavior for the specified virtual servers.
     */
    public CommonSourcePortBehavior[] get_source_port_behavior(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the CMP enabled states for the specified virtual servers.
     * Enables or disables CMP.
     *  Note that this setting can influence the CMP enable mode set automatically
     * by the system
     *  and configuration. You can not always enable CMP, but you can always
     * disable it.
     */
    public void set_cmp_enabled_state(String[] virtual_servers, CommonEnabledState[] states) throws java.rmi.RemoteException;

    /**
     * Gets the CMP enabled states for the specified virtual servers.
     * Enables or disables CMP.
     *  Note that this setting can influence the CMP enable mode set automatically
     * by the system
     *  and configuration. You can not always enable CMP, but you can always
     * disable it.
     */
    public CommonEnabledState[] get_cmp_enabled_state(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the last hop pools for the specified virtual servers.
     */
    public void set_last_hop_pool(String[] virtual_servers, String[] last_hop_pools) throws java.rmi.RemoteException;

    /**
     * Gets the last hop pools for the specified virtual servers.
     */
    public String[] get_last_hop_pool(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Gets the actual hardware acceleration modes for the specified
     * virtual servers.
     */
    public LocalLBHardwareAccelerationMode[] get_actual_hardware_acceleration(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * This method has been deprecated due to adding large scale NAT
     * as an
     *  address translation method (SNAT is no longer the only way to
     *  translate.)  Please use get_source_address_translation_type in its
     * stead.
     * 
     *  Gets the SNAT type for the specified virtual servers.
     */
    public LocalLBSnatType[] get_snat_type(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * This method has been deprecated due to adding large scale NAT
     * as an
     *  address translation method (SNAT is no longer the only way to
     *  translate.)  Please use set_source_address_translation_none in its
     * stead.
     *  
     *  Sets the state to use no SNATs for the specified virtual servers.
     */
    public void set_snat_none(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * This method has been deprecated due to adding large scale NAT
     * as an
     *  address translation method (SNAT is no longer the only way to
     *  translate.)  Please use set_source_address_translation_automap in
     * its stead.
     *  
     *  Sets the SNAT automap state for the specified virtual servers.
     */
    public void set_snat_automap(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * This method has been deprecated due to adding large scale NAT
     * as an
     *  address translation method (SNAT is no longer the only way to
     *  translate.)  Please use set_source_address_translation_snat_pool
     * in its stead.
     *  
     *  Sets the SNAT pools to be used in iSNAT configurations for the specified
     * virtual servers.
     */
    public void set_snat_pool(String[] virtual_servers, String[] snatpools) throws java.rmi.RemoteException;

    /**
     * This method has been deprecated due to adding large scale NAT
     * as an
     *  address translation method (SNAT is no longer the only way to
     *  translate.)  Please use get_source_address_translation_snat_pool
     * in its stead.
     *  
     *  Gets the SNAT pools to be used in iSNAT configurations for the specified
     * virtual servers.
     */
    public String[] get_snat_pool(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Gets the Source Address Translation type for the specified
     * virtual servers.
     */
    public LocalLBVirtualServerSourceAddressTranslationType[] get_source_address_translation_type(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the state to use no translation for the specified virtual
     * servers.
     */
    public void set_source_address_translation_none(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the source address translation to automap (i.e. use self
     * IP addresses) for the specified virtual servers.
     */
    public void set_source_address_translation_automap(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the translation of the specified virtual servers to use
     * SNAT pools.
     */
    public void set_source_address_translation_snat_pool(String[] virtual_servers, String[] pools) throws java.rmi.RemoteException;

    /**
     * Gets the SNAT pools, if any, associated with the specified
     * virtual servers.
     */
    public String[] get_source_address_translation_snat_pool(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the translation of the specified virtual servers to use
     * Large Scale NAT pools.
     */
    public void set_source_address_translation_lsn_pool(String[] virtual_servers, String[] pools) throws java.rmi.RemoteException;

    /**
     * Gets the LSN pools, if any, associated with the specified virtual
     * servers.
     */
    public String[] get_source_address_translation_lsn_pool(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the default pool names for the specified virtual servers.
     */
    public void set_default_pool_name(String[] virtual_servers, String[] default_pools) throws java.rmi.RemoteException;

    /**
     * Gets the default pool names for the specified virtual servers.
     */
    public String[] get_default_pool_name(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the persistence profiles to use for fallback persistence
     * for the specified virtual servers.
     */
    public void set_fallback_persistence_profile(String[] virtual_servers, String[] profile_names) throws java.rmi.RemoteException;

    /**
     * Gets the persistence profiles to use for fallback persistence
     * for the specified virtual servers.
     */
    public String[] get_fallback_persistence_profile(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Gets the lists of VLANs on which access to the specified Virtual
     * Servers are enabled/disabled.
     */
    public CommonVLANFilterList[] get_vlan(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets VLANs to the list of VLANs on which access to the specified
     * Virtual Servers are enabled/disabled.
     */
    public void set_vlan(String[] virtual_servers, CommonVLANFilterList[] vlans) throws java.rmi.RemoteException;

    /**
     * Gets the lists of profiles the specified virtual servers are
     * associated with.
     */
    public LocalLBVirtualServerVirtualServerProfileAttribute[][] get_profile(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Adds/associates profiles to the specified virtual servers.
     */
    public void add_profile(String[] virtual_servers, LocalLBVirtualServerVirtualServerProfile[][] profiles) throws java.rmi.RemoteException;

    /**
     * Removes profiles from the specified virtual servers.
     */
    public void remove_profile(String[] virtual_servers, LocalLBVirtualServerVirtualServerProfile[][] profiles) throws java.rmi.RemoteException;

    /**
     * Removes all profiles from the specified virtual servers.
     */
    public void remove_all_profiles(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Gets the lists of persistence profiles the specified virtual
     * servers are associated with.
     */
    public LocalLBVirtualServerVirtualServerPersistence[][] get_persistence_profile(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Adds/associates persistence profiles to the specified virtual
     * servers.
     */
    public void add_persistence_profile(String[] virtual_servers, LocalLBVirtualServerVirtualServerPersistence[][] profiles) throws java.rmi.RemoteException;

    /**
     * Removes persistence profiles from the specified virtual servers.
     */
    public void remove_persistence_profile(String[] virtual_servers, LocalLBVirtualServerVirtualServerPersistence[][] profiles) throws java.rmi.RemoteException;

    /**
     * Removes all persistence profiles from the specified virtual
     * servers.
     */
    public void remove_all_persistence_profiles(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Gets the lists of authentication profiles the specified virtual
     * servers are associated with.
     */
    public LocalLBVirtualServerVirtualServerAuthentication[][] get_authentication_profile(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Adds/associates authentication profiles to the specified virtual
     * servers.
     */
    public void add_authentication_profile(String[] virtual_servers, LocalLBVirtualServerVirtualServerAuthentication[][] profiles) throws java.rmi.RemoteException;

    /**
     * Removes authentication profiles from the specified virtual
     * servers.
     */
    public void remove_authentication_profile(String[] virtual_servers, LocalLBVirtualServerVirtualServerAuthentication[][] profiles) throws java.rmi.RemoteException;

    /**
     * Removes all authentication profiles from the specified virtual
     * servers.
     */
    public void remove_all_authentication_profiles(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Gets the lists of rules the specified virtual servers are associated
     * with.
     *  If a specified virtual server is not associated with any rule, then
     * the list
     *  of rules for that virtual server will be empty.
     */
    public LocalLBVirtualServerVirtualServerRule[][] get_rule(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Adds/associates rules to the specified virtual servers.
     */
    public void add_rule(String[] virtual_servers, LocalLBVirtualServerVirtualServerRule[][] rules) throws java.rmi.RemoteException;

    /**
     * Removes rules from the specified virtual servers.
     */
    public void remove_rule(String[] virtual_servers, LocalLBVirtualServerVirtualServerRule[][] rules) throws java.rmi.RemoteException;

    /**
     * Removes all rules from the specified virtual servers.
     */
    public void remove_all_rules(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Gets the lists of related rules the specified virtual servers
     * are associated with.
     *  If a specified virtual server is not associated with any rule, then
     * the list
     *  of rules for that virtual server will be empty.
     *  Related rules run only on the associated channel (such as the FTP
     * data channel).
     */
    public LocalLBVirtualServerVirtualServerRule[][] get_related_rule(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Adds/associates related rules to the specified virtual servers.
     */
    public void add_related_rule(String[] virtual_servers, LocalLBVirtualServerVirtualServerRule[][] rules) throws java.rmi.RemoteException;

    /**
     * Removes rules from the specified virtual servers.
     */
    public void remove_related_rule(String[] virtual_servers, LocalLBVirtualServerVirtualServerRule[][] rules) throws java.rmi.RemoteException;

    /**
     * Removes all rules from the specified virtual servers.
     */
    public void remove_all_related_rules(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Gets the lists of clone pools the specified virtual servers
     * are associated with.
     */
    public LocalLBVirtualServerVirtualServerClonePool[][] get_clone_pool(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Adds/associates clone pools to the specified virtual servers.
     */
    public void add_clone_pool(String[] virtual_servers, LocalLBVirtualServerVirtualServerClonePool[][] clone_pools) throws java.rmi.RemoteException;

    /**
     * Removes clone pools from the specified virtual servers.
     */
    public void remove_clone_pool(String[] virtual_servers, LocalLBVirtualServerVirtualServerClonePool[][] clone_pools) throws java.rmi.RemoteException;

    /**
     * Removes all clone pools from the specified virtual servers.
     */
    public void remove_all_clone_pools(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Gets the statistics for all the virtual servers.
     */
    public LocalLBVirtualServerVirtualServerStatistics get_all_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the statistics for the specified virtual servers.
     */
    public LocalLBVirtualServerVirtualServerStatistics get_statistics(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Resets the statistics for the specified virtual servers.
     */
    public void reset_statistics(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Gets the persistence records based on the specified persistent
     * modes for the specified virtual servers.
     */
    public LocalLBPersistenceRecord[][] get_persistence_record(String[] virtual_servers, LocalLBPersistenceMode[] persistence_modes) throws java.rmi.RemoteException;

    /**
     * Deletes the persistence records based on the specified persistent
     * modes for the specified virtual servers.
     */
    public void delete_persistence_record(String[] virtual_servers, LocalLBPersistenceMode[] persistence_modes) throws java.rmi.RemoteException;

    /**
     * Gets the lists of HTTP class profiles the specified virtual
     * servers are associated with.
     *  If a specified virtual server is not associated with any HTTP class
     * profile, then the list
     *  of HTTP class profiles for that virtual server will be empty.
     */
    public LocalLBVirtualServerVirtualServerHttpClass[][] get_httpclass_profile(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Adds/associates HTTP class profiles to the specified virtual
     * servers.
     */
    public void add_httpclass_profile(String[] virtual_servers, LocalLBVirtualServerVirtualServerHttpClass[][] profiles) throws java.rmi.RemoteException;

    /**
     * Removes HTTP class profiles from the specified virtual servers.
     */
    public void remove_httpclass_profile(String[] virtual_servers, LocalLBVirtualServerVirtualServerHttpClass[][] profiles) throws java.rmi.RemoteException;

    /**
     * Removes all HTTP class profiles from the specified virtual
     * servers.
     */
    public void remove_all_httpclass_profiles(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the gtm score for the specified virtual servers.
     */
    public void set_gtm_score(String[] virtual_servers, CommonULong64[] scores) throws java.rmi.RemoteException;

    /**
     * Returns the gtm scores for the specified virtual servers.
     */
    public CommonULong64[] get_gtm_score(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Returns the module scores for the specified virtual servers.
     */
    public LocalLBVirtualServerVirtualServerModuleScore[][] get_module_score(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the description for a set of virtual servers.
     * 
     *  This is an arbitrary field which can be used for any purpose.
     */
    public void set_description(String[] virtual_servers, String[] descriptions) throws java.rmi.RemoteException;

    /**
     * Gets the descriptions for a set of virtual servers.
     */
    public String[] get_description(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Gets the user metadata for the specified virtual servers.
     *  User metadata, also metadata for short, is mainly a name/value
     *  pair that is associated with a metadata capable config (MCC) object.
     * Some exmaples of MCC objects are ltm pool and virtual server.
     *  The combination of the MCC object name and the metadata name
     *  uniguely identify an instance of the metadata. One MCC object
     *  may have multiple instances of metadata associated with it.
     *  In addition to name/value pair, metadata also has a persistence
     *  attribute. See Common::MetadataPersistence for its definition.
     */
    public String[][] get_metadata(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Adds the metadata for the specified virtual servers.
     *  For definition of the metadata, refer to the get_metadata method
     *  description.
     */
    public void add_metadata(String[] virtual_servers, String[][] names, String[][] values) throws java.rmi.RemoteException;

    /**
     * Removes the metadata for the specified virtual servers and
     * names.
     *  For definition of the metadata, refer to the get_metadata method
     *  description.
     */
    public void remove_metadata(String[] virtual_servers, String[][] names) throws java.rmi.RemoteException;

    /**
     * Removes all the metadata instances for the specified virtual
     * servers.
     */
    public void remove_all_metadata(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the values for the specified metadata.
     *  For definition of the metadata, refer to the get_metadata method
     *  description.
     */
    public void set_metadata_value(String[] virtual_servers, String[][] names, String[][] values) throws java.rmi.RemoteException;

    /**
     * Gets the metadata values for the specified metadata.
     *  For definition of the metadata, refer to the get_metadata method
     *  description.
     */
    public String[][] get_metadata_value(String[] virtual_servers, String[][] names) throws java.rmi.RemoteException;

    /**
     * Sets the descriptions for the specified metadata.
     *  For definition of the metadata, refer to the get_metadata method
     *  description.
     */
    public void set_metadata_description(String[] virtual_servers, String[][] names, String[][] descriptions) throws java.rmi.RemoteException;

    /**
     * Gets the metadata descriptions for the specified metadata.
     *  For definition of the metadata, refer to the get_metadata method
     *  description.
     */
    public String[][] get_metadata_description(String[] virtual_servers, String[][] names) throws java.rmi.RemoteException;

    /**
     * Sets the persistence for the specified metadata.
     *  For definition of the metadata, refer to the get_metadata method
     *  description.
     */
    public void set_metadata_persistence(String[] virtual_servers, String[][] names, CommonMetadataPersistence[][] values) throws java.rmi.RemoteException;

    /**
     * Gets the persistence for the specified metadata.
     *  For definition of the metadata, refer to the get_metadata method
     *  description.
     */
    public CommonMetadataPersistence[][] get_metadata_persistence(String[] virtual_servers, String[][] names) throws java.rmi.RemoteException;

    /**
     * Gets the lists of Security log profiles the specified virtual
     * servers are associated with.
     */
    public String[][] get_security_log_profile(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Adds/associates Security log profiles to the specified virtual
     * servers.
     *  Please see the LogProfile interface in the Security module for more
     * information.
     */
    public void add_security_log_profile(String[] virtual_servers, String[][] profiles) throws java.rmi.RemoteException;

    /**
     * Removes Security log profiles from the specified virtual servers.
     */
    public void remove_security_log_profile(String[] virtual_servers, String[][] profiles) throws java.rmi.RemoteException;

    /**
     * Removes all Security log profiles from the specified virtual
     * servers.
     */
    public void remove_all_security_log_profiles(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Gets the firewall rules for the specified virtual servers.
     */
    public String[][] get_fw_rule(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Adds firewall rules to the specified virtual servers.
     * 
     *  Note that the abilities to add more than one rule or,
     *  especially, to add partial rules and to build them up
     *  introduce a need for best practices: (1) introduce the rule
     *  or rules initially disabled (using the states parameter)
     *  and enable them (or set them as scheduled) as a whole when
     *  you have them complete or (2) use transactions (see
     *  System::Session::start_transaction) to avoid accidentally
     *  putting partial rules or incomplete rule sets into place.
     */
    public void add_fw_rule(String[] virtual_servers, String[][] rules, CommonFirewallRulePlacement[][] placements, CommonFirewallRuleAction[][] actions, CommonFirewallRuleState[][] states) throws java.rmi.RemoteException;

    /**
     * Adds firewall rules to the specified virtual servers, having
     * each of those rules point at a rule list. This method is
     *  intended as a convenience to prevent you from having to add
     *  firewall rules as a transaction.
     * 
     *  See the Security::FirewallRuleList interface for more
     *  information on rule lists.
     */
    public void add_fw_rule_with_rule_list(String[] virtual_servers, String[][] rules, CommonFirewallRulePlacement[][] placements, String[][] lists, CommonFirewallRuleState[][] states) throws java.rmi.RemoteException;

    /**
     * Removes firewall rules from the specified virtual servers.
     */
    public void remove_fw_rule(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Removes all firewall rules from the specified virtual servers.
     */
    public void remove_all_fw_rules(String[] virtual_servers) throws java.rmi.RemoteException;

    /**
     * Sets the state for the specified firewall rules.
     * 
     *  You can add a rule as enabled or disabled initially, build
     *  it up, then enable it.  You can temporarily disable a rule
     *  with no other effect on it, so that it can be enabled
     *  easily later without having to rebuild it.  You can use the
     *  state of FW_RULE_STATE_SCHEDULED to enable scheduling for the
     *  rule. See add_fw_rule for more information.
     */
    public void set_fw_rule_state(String[] virtual_servers, String[][] rules, CommonFirewallRuleState[][] states) throws java.rmi.RemoteException;

    /**
     * Gets the state for the specified firewall rules.
     */
    public CommonFirewallRuleState[][] get_fw_rule_state(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Sets the order (numerically) for the specified firewall rules.
     * 
     *  Two rules can't have the same order, so one must manage
     *  order carefully if using numeric order to arrange firewall
     *  rules. See add_fw_rule for more information.
     */
    public void set_fw_rule_order(String[] virtual_servers, String[][] rules, long[][] orders) throws java.rmi.RemoteException;

    /**
     * Gets the order (numerically) for the specified firewall rules.
     */
    public long[][] get_fw_rule_order(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Gets source address lists for the specified firewall rules.
     */
    public String[][][] get_fw_rule_source_address_list(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Adds source address lists to the specified firewall rules.
     * 
     *  See the Security::FirewallAddressList interface for more
     *  information on address lists.
     */
    public void add_fw_rule_source_address_list(String[] virtual_servers, String[][] rules, String[][][] lists) throws java.rmi.RemoteException;

    /**
     * Removes source address lists from the specified firewall rules.
     */
    public void remove_fw_rule_source_address_list(String[] virtual_servers, String[][] rules, String[][][] lists) throws java.rmi.RemoteException;

    /**
     * Removes all source address lists from the specified firewall
     * rules.
     */
    public void remove_all_fw_rule_source_address_lists(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Gets (inlined) source addresses for the specified firewall
     * rules.
     */
    public String[][][] get_fw_rule_source_address(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Adds (inlined) source addresses to the specified firewall rules.
     */
    public void add_fw_rule_source_address(String[] virtual_servers, String[][] rules, String[][][] addresses) throws java.rmi.RemoteException;

    /**
     * Removes (inlined) source addresses from the specified firewall
     * rules.
     */
    public void remove_fw_rule_source_address(String[] virtual_servers, String[][] rules, String[][][] addresses) throws java.rmi.RemoteException;

    /**
     * Removes all (inlined) source addresses from the specified firewall
     * rules.
     */
    public void remove_all_fw_rule_source_addresses(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Sets the description for the specified firewall rules' source
     * addresses.
     * 
     *  This is an arbitrary field which can be used for any purpose.
     */
    public void set_fw_rule_source_address_description(String[] virtual_servers, String[][] rules, String[][][] addresses, String[][][] descriptions) throws java.rmi.RemoteException;

    /**
     * Gets the descriptions for the specified firewall rules' source
     * addresses.
     */
    public String[][][] get_fw_rule_source_address_description(String[] virtual_servers, String[][] rules, String[][][] addresses) throws java.rmi.RemoteException;

    /**
     * Gets destination address lists for the specified firewall rules.
     */
    public String[][][] get_fw_rule_destination_address_list(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Adds destination address lists to the specified firewall rules.
     * 
     *  See the Security::FirewallAddressList interface for more
     *  information on address lists.
     */
    public void add_fw_rule_destination_address_list(String[] virtual_servers, String[][] rules, String[][][] lists) throws java.rmi.RemoteException;

    /**
     * Removes destination address lists from the specified firewall
     * rules.
     */
    public void remove_fw_rule_destination_address_list(String[] virtual_servers, String[][] rules, String[][][] lists) throws java.rmi.RemoteException;

    /**
     * Removes all destination address lists from the specified firewall
     * rules.
     */
    public void remove_all_fw_rule_destination_address_lists(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Gets (inlined) destination addresses for the specified firewall
     * rules.
     */
    public String[][][] get_fw_rule_destination_address(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Adds (inlined) destination addresses to the specified firewall
     * rules.
     */
    public void add_fw_rule_destination_address(String[] virtual_servers, String[][] rules, String[][][] addresses) throws java.rmi.RemoteException;

    /**
     * Removes (inlined) destination addresses from the specified
     * firewall rules.
     */
    public void remove_fw_rule_destination_address(String[] virtual_servers, String[][] rules, String[][][] addresses) throws java.rmi.RemoteException;

    /**
     * Removes all (inlined) destination addresses from the specified
     * firewall rules.
     */
    public void remove_all_fw_rule_destination_addresses(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Sets the description for the specified firewall rules' destination
     * addresses.
     * 
     *  This is an arbitrary field which can be used for any purpose.
     */
    public void set_fw_rule_destination_address_description(String[] virtual_servers, String[][] rules, String[][][] addresses, String[][][] descriptions) throws java.rmi.RemoteException;

    /**
     * Gets the descriptions for the specified firewall rules' destination
     * addresses.
     */
    public String[][][] get_fw_rule_destination_address_description(String[] virtual_servers, String[][] rules, String[][][] addresses) throws java.rmi.RemoteException;

    /**
     * Gets source port lists for the specified firewall rules.
     */
    public String[][][] get_fw_rule_source_port_list(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Adds source port lists to the specified firewall rules.
     * 
     *  See the Security::FirewallPortList interface for more
     *  information on port lists.
     */
    public void add_fw_rule_source_port_list(String[] virtual_servers, String[][] rules, String[][][] lists) throws java.rmi.RemoteException;

    /**
     * Removes source port lists from the specified firewall rules.
     */
    public void remove_fw_rule_source_port_list(String[] virtual_servers, String[][] rules, String[][][] lists) throws java.rmi.RemoteException;

    /**
     * Removes all source port lists from the specified firewall rules.
     */
    public void remove_all_fw_rule_source_port_lists(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Gets (inlined) source ports for the specified firewall rules.
     */
    public CommonPortRange[][][] get_fw_rule_source_port(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Adds (inlined) source ports to the specified firewall rules.
     */
    public void add_fw_rule_source_port(String[] virtual_servers, String[][] rules, CommonPortRange[][][] ports) throws java.rmi.RemoteException;

    /**
     * Removes (inlined) source ports from the specified firewall
     * rules.
     */
    public void remove_fw_rule_source_port(String[] virtual_servers, String[][] rules, CommonPortRange[][][] ports) throws java.rmi.RemoteException;

    /**
     * Removes all (inlined) source ports from the specified firewall
     * rules.
     */
    public void remove_all_fw_rule_source_ports(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Sets the description for the specified firewall rules' (inlined)
     * source ports.
     * 
     *  This is an arbitrary field which can be used for any purpose.
     */
    public void set_fw_rule_source_port_description(String[] virtual_servers, String[][] rules, CommonPortRange[][][] ports, String[][][] descriptions) throws java.rmi.RemoteException;

    /**
     * Gets the descriptions for the specified firewall rules' (inlined)
     * source ports.
     */
    public String[][][] get_fw_rule_source_port_description(String[] virtual_servers, String[][] rules, CommonPortRange[][][] ports) throws java.rmi.RemoteException;

    /**
     * Gets destination port lists for the specified firewall rules.
     */
    public String[][][] get_fw_rule_destination_port_list(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Adds destination port lists to the specified firewall rules.
     * 
     *  See the Security::FirewallPortList interface for more
     *  information on port lists.
     */
    public void add_fw_rule_destination_port_list(String[] virtual_servers, String[][] rules, String[][][] lists) throws java.rmi.RemoteException;

    /**
     * Removes destination port lists from the specified firewall
     * rules.
     */
    public void remove_fw_rule_destination_port_list(String[] virtual_servers, String[][] rules, String[][][] lists) throws java.rmi.RemoteException;

    /**
     * Removes all destination port lists from the specified firewall
     * rules.
     */
    public void remove_all_fw_rule_destination_port_lists(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Gets (inlined) destination ports for the specified firewall
     * rules.
     */
    public CommonPortRange[][][] get_fw_rule_destination_port(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Adds (inlined) destination ports to the specified firewall
     * rules.
     */
    public void add_fw_rule_destination_port(String[] virtual_servers, String[][] rules, CommonPortRange[][][] ports) throws java.rmi.RemoteException;

    /**
     * Removes (inlined) destination ports from the specified firewall
     * rules.
     */
    public void remove_fw_rule_destination_port(String[] virtual_servers, String[][] rules, CommonPortRange[][][] ports) throws java.rmi.RemoteException;

    /**
     * Removes all (inlined) destination ports from the specified
     * firewall rules.
     */
    public void remove_all_fw_rule_destination_ports(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Sets the description for the specified firewall rules' (inlined)
     * destination ports.
     * 
     *  This is an arbitrary field which can be used for any purpose.
     */
    public void set_fw_rule_destination_port_description(String[] virtual_servers, String[][] rules, CommonPortRange[][][] ports, String[][][] descriptions) throws java.rmi.RemoteException;

    /**
     * Gets the descriptions for the specified firewall rules' (inlined)
     * destination ports.
     */
    public String[][][] get_fw_rule_destination_port_description(String[] virtual_servers, String[][] rules, CommonPortRange[][][] ports) throws java.rmi.RemoteException;

    /**
     * Gets (inlined) ICMP type/code values for the specified firewall
     * rules.
     *  A value of 255 for either ICMP type or code is a wildcard value.
     */
    public CommonFirewallRuleICMPTypeCode[][][] get_fw_rule_icmp_typecode(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Adds (inlined) ICMP type/code values to the specified firewall
     * rules.
     */
    public void add_fw_rule_icmp_typecode(String[] virtual_servers, String[][] rules, CommonFirewallRuleICMPTypeCode[][][] typecodes) throws java.rmi.RemoteException;

    /**
     * Removes (inlined) ICMP type/code values from the specified
     * firewall rules.
     */
    public void remove_fw_rule_icmp_typecode(String[] virtual_servers, String[][] rules, CommonFirewallRuleICMPTypeCode[][][] typecodes) throws java.rmi.RemoteException;

    /**
     * Removes all (inlined) ICMP type/code values from the specified
     * firewall rules.
     */
    public void remove_all_fw_rule_icmp_typecodes(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Sets the description for the specified firewall rules' (inlined)
     * ICMP type/code values.
     * 
     *  This is an arbitrary field which can be used for any purpose.
     */
    public void set_fw_rule_icmp_typecode_description(String[] virtual_servers, String[][] rules, CommonFirewallRuleICMPTypeCode[][][] typecodes, String[][][] descriptions) throws java.rmi.RemoteException;

    /**
     * Gets the descriptions for the specified firewall rules' (inlined)
     * ICMP type/code values.
     */
    public String[][][] get_fw_rule_icmp_typecode_description(String[] virtual_servers, String[][] rules, CommonFirewallRuleICMPTypeCode[][][] typecodes) throws java.rmi.RemoteException;

    /**
     * Gets source VLANs for the specified firewall rules.
     */
    public String[][][] get_fw_rule_source_vlan(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Adds source VLANs to the specified firewall rules.
     */
    public void add_fw_rule_source_vlan(String[] virtual_servers, String[][] rules, String[][][] vlans) throws java.rmi.RemoteException;

    /**
     * Removes source VLANs from the specified firewall rules.
     */
    public void remove_fw_rule_source_vlan(String[] virtual_servers, String[][] rules, String[][][] vlans) throws java.rmi.RemoteException;

    /**
     * Removes all source VLANs from the specified firewall rules.
     */
    public void remove_all_fw_rule_source_vlans(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Sets a weekly schedule for the specified firewall rules.
     *  See Security::FirewallWeeklySchedule for how to create and
     *  manipulate weekly schedules.
     */
    public void set_fw_rule_weekly_schedule(String[] virtual_servers, String[][] rules, String[][] schedules) throws java.rmi.RemoteException;

    /**
     * Gets a weekly schedule for the specified firewall rules.
     */
    public String[][] get_fw_rule_weekly_schedule(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Sets the (IP) protocol for the specified firewall rules.
     * 
     *  Note: if the protocol is not one of the supported standard
     *  protocols, use set_fw_rule_protocol_numeric.
     */
    public void set_fw_rule_protocol(String[] virtual_servers, String[][] rules, CommonProtocolType[][] protocols) throws java.rmi.RemoteException;

    /**
     * Gets the (IP) protocol for the specified firewall rules.
     */
    public CommonProtocolType[][] get_fw_rule_protocol(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Sets the IP protocol (numerically) for the specified firewall
     * rules.
     */
    public void set_fw_rule_protocol_numeric(String[] virtual_servers, String[][] rules, long[][] protocols) throws java.rmi.RemoteException;

    /**
     * Gets the IP protocol (numerically) for the specified firewall
     * rules.
     */
    public long[][] get_fw_rule_protocol_numeric(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Sets the action for the specified firewall rules.
     */
    public void set_fw_rule_action(String[] virtual_servers, String[][] rules, CommonFirewallRuleAction[][] actions) throws java.rmi.RemoteException;

    /**
     * Gets the action for the specified firewall rules.
     */
    public CommonFirewallRuleAction[][] get_fw_rule_action(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Sets the rule list for the specified firewall rules.
     * 
     *  If a list is specified then the system will validate that
     *  no other properties were specified in the current
     *  transaction, and will clear all other match criteria fields
     *  (src, dst, ip protocol, et cetera).
     *  
     *  The empty string means no rule list.
     */
    public void set_fw_rule_rule_list(String[] virtual_servers, String[][] rules, String[][] lists) throws java.rmi.RemoteException;

    /**
     * Gets the rule list for the specified firewall rules.
     */
    public String[][] get_fw_rule_rule_list(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Sets the description for the specified firewall rules.
     * 
     *  This is an arbitrary field which can be used for any purpose.
     */
    public void set_fw_rule_description(String[] virtual_servers, String[][] rules, String[][] descriptions) throws java.rmi.RemoteException;

    /**
     * Gets the descriptions for the specified firewall rules.
     */
    public String[][] get_fw_rule_description(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Sets the logging property for the specified firewall rules.
     * Specifies whether the security software should write a log entry 
     *  for all packets that match this rule. You must also enable network
     * 
     *  filter logging in the "security log profile" component for this 
     *  option to have any effect. Note that the security software always
     * 
     *  increments the statistics counter when a packet matches a rule,
     *  no matter how you set this option.
     */
    public void set_fw_rule_log_state(String[] virtual_servers, String[][] rules, CommonEnabledState[][] states) throws java.rmi.RemoteException;

    /**
     * Gets the logging property for the specified firewall rules.
     */
    public CommonEnabledState[][] get_fw_rule_log_state(String[] virtual_servers, String[][] rules) throws java.rmi.RemoteException;

    /**
     * Gets the version information for this interface.
     */
    public String get_version() throws java.rmi.RemoteException;
}
