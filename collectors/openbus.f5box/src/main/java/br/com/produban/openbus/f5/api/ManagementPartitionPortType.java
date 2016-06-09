/**
 * ManagementPartitionPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public interface ManagementPartitionPortType extends java.rmi.Remote {

    /**
     * Gets a list of all AuthZ partitions.
     */
    public ManagementPartitionAuthZPartition[] get_partition_list() throws java.rmi.RemoteException;

    /**
     * Creates the specified AuthZ partitions.
     */
    public void create_partition(ManagementPartitionAuthZPartition[] partitions) throws java.rmi.RemoteException;

    /**
     * Deletes the specified AuthZ partitions.
     *  It's recommended that if a partition to be deleted is also the current
     * active partition, the user should change the active partition to something
     * else first (using set_active_partition).  Otherwise, in the event
     * that the 
     *  partition to be deleted is the current active partition, iControl
     * will 
     *  automatically default the new active partition to the "Common" partition.
     */
    public void delete_partition(String[] partition_names) throws java.rmi.RemoteException;

    /**
     * Deletes all AuthZ partitions, except for the "Common" partition.
     */
    public void delete_all_partitions() throws java.rmi.RemoteException;

    /**
     * This method is deprecated; please use
     *  System::Session::set_active_folder instead.
     * 
     *  Sets the active partition for the current user. The active partition
     * is the administrative partition where all configuration will take
     * place
     *  until a new active partition is selected. Each user can only be in
     * one
     *  active partition at any given time. By default, if not explicitly
     * set,
     *  the active partition for a user will be "Common".
     *  
     *  Note: Please note that this method is deprecated. If you
     *  set the partition to "[All]", this maps to the
     *  System::Session interface method set_active_folder("/") and
     *  in this case this method automatically performs
     *  set_recursive_query_state(STATE_ENABLED) as well. The
     *  method uses set_active_folder, so if the partition you
     *  specify maps to no existing top-level folder it will tell
     *  you the folder doesn't exist and behave in all respects as
     *  set_active_folder does.
     */
    public void set_active_partition(String active_partition) throws java.rmi.RemoteException;

    /**
     * Gets the active partition for the current user. The active
     * partition
     *  is the administrative partition where all configuration will take
     * place
     *  until a new active partition is selected. Each user can only be in
     * one
     *  active partition at any given time. By default, if not explicitly
     * set,
     *  the active partition for a user will be "Common".
     */
    public String get_active_partition() throws java.rmi.RemoteException;

    /**
     * Sets the default route domain for a set of partitions.
     *  This is the route domain that is associated with IP
     *  addresses that are created in the partition.
     * 
     *  (Please note that the system does not supply recommended
     *  replacement functionality for the methods
     *  {set,get}_default_route_domain yet. Also, the default route
     *  domain is keyed by numeric id here rather than string
     *  (string, as in RouteDomainV2). The methods here are
     *  deprecated but provided for completeness; as soon as
     *  replacement functionality exists they may become
     *  nonfunctional earlier than normal.)
     */
    public void set_default_route_domain(String[] partitions, long[] route_domains) throws java.rmi.RemoteException;

    /**
     * Gets the default route domain for a set of partitions.
     */
    public long[] get_default_route_domain(String[] partitions) throws java.rmi.RemoteException;

    /**
     * This function is supplied for completeness but is
     *  deprecated; please use set_description on a top-level
     *  folder instead (see Management::Folder).
     * 
     *  Sets the description for a set of partitions. This is an arbitrary
     * field which can be used for any purpose.
     */
    public void set_description(String[] partitions, String[] descriptions) throws java.rmi.RemoteException;

    /**
     * Gets the description for a set of partitions.
     */
    public String[] get_description(String[] partitions) throws java.rmi.RemoteException;

    /**
     * Gets the version information for this interface.
     */
    public String get_version() throws java.rmi.RemoteException;
}
