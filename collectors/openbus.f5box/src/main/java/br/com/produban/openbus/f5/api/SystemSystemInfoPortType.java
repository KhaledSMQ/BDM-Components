/**
 * SystemSystemInfoPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public interface SystemSystemInfoPortType extends java.rmi.Remote {

    /**
     * This method has been deprecated; use get_host_statistics and
     * related methods instead.
     * 
     *  Gets the memory information for this system.
     */
    public SystemMemoryUsageInformation get_memory_usage_information() throws java.rmi.RemoteException;

    /**
     * Gets the system-identifying attributes of the operating system.
     */
    public SystemSystemInformation get_system_information() throws java.rmi.RemoteException;

    /**
     * Gets the F5 marketing name for this platform
     */
    public String get_marketing_name() throws java.rmi.RemoteException;

    /**
     * Gets the system's base MAC address
     */
    public String get_base_mac_address() throws java.rmi.RemoteException;

    /**
     * Gets the PVA version
     */
    public String get_pva_version() throws java.rmi.RemoteException;

    /**
     * Gets the information for the hardware in the system.  This
     * includes
     *  information about CPUs, compression and encryption acceleration
     *  hardware, etc.
     */
    public SystemHardwareInformation[] get_hardware_information() throws java.rmi.RemoteException;

    /**
     * Gets a globally unique identifier for the system.
     */
    public String get_globally_unique_identifier() throws java.rmi.RemoteException;

    /**
     * Gets a list of attributes of installed product.
     */
    public SystemProductInformation get_product_information() throws java.rmi.RemoteException;

    /**
     * Gets the local time zone information.
     */
    public CommonTimeZone get_time_zone() throws java.rmi.RemoteException;

    /**
     * Gets the system time in UTC.
     */
    public CommonTimeStamp get_time() throws java.rmi.RemoteException;

    /**
     * Gets the unique identifier for the system.
     */
    public String get_system_id() throws java.rmi.RemoteException;

    /**
     * Sets the unique identifier for the configsync or sync group
     * of which this device is a member.
     */
    public void set_group_id(String group_id) throws java.rmi.RemoteException;

    /**
     * Gets the unique identifier for the configsync or sync group
     * of which this device is a member.
     */
    public String get_group_id() throws java.rmi.RemoteException;

    /**
     * Gets the current chassis temperatures.
     */
    public SystemPlatformTemperatures get_temperature_metrics() throws java.rmi.RemoteException;

    /**
     * Gets the CPU metrics for the CPU(s) on the platform.
     */
    public SystemPlatformCPUs get_cpu_metrics() throws java.rmi.RemoteException;

    /**
     * Gets the Fan metrics for the Fan(s) on the platform.
     */
    public SystemPlatformFans get_fan_metrics() throws java.rmi.RemoteException;

    /**
     * Gets the Power Supply metrics for the Power Supplies on the
     * platform.
     */
    public SystemPlatformPowerSupplies get_power_supply_metrics() throws java.rmi.RemoteException;

    /**
     * Gets the temperatures recorded by the blade sensors
     */
    public SystemBladeTemperature[] get_blade_temperature() throws java.rmi.RemoteException;

    /**
     * Gets the disk usage information for this system.
     */
    public SystemDiskUsageInformation get_disk_usage_information() throws java.rmi.RemoteException;

    /**
     * This method has been deprecated; use get_cpu_usage_extended_information
     * and
     *  related methods instead.
     * 
     *  Gets the CPU usage information for this system.
     */
    public SystemCPUUsageInformation get_cpu_usage_information() throws java.rmi.RemoteException;

    /**
     * Gets the CPU usage extended information for this system by
     * host ID for all hosts.
     */
    public SystemCPUUsageExtendedInformation get_all_cpu_usage_extended_information() throws java.rmi.RemoteException;

    /**
     * Gets the CPU usage extended information for this system by
     * host ID.
     */
    public SystemCPUUsageExtendedInformation get_cpu_usage_extended_information(String[] host_ids) throws java.rmi.RemoteException;

    /**
     * Gets the global CPU usage extended information for this system.
     * 
     *  This gets one set of combined ("rolled up") statistics for
     *  all hosts.  It is not to be confused with
     *  get_all_cpu_usage_extended_information, which gets all
     *  individual cpu usage statistics, one for each host.
     */
    public SystemGlobalCPUUsageExtendedInformation get_global_cpu_usage_extended_information() throws java.rmi.RemoteException;

    /**
     * Gets the number of seconds the device has been running.
     */
    public long get_uptime() throws java.rmi.RemoteException;

    /**
     * Note: this method has been deprecated as changes in the
     *  system have rendered it problematic.
     *  It was intended for internal f5 use and should not be used
     *  by customers.
     * 
     *  Gets connection information (see ConnectionInformation for
     *  more info).
     * 
     *  As of 11.1.0, a connection type of CONNECTION_TYPE_SELF
     *  causes the method to return a local address of localhost.
     */
    public SystemConnectionInformation get_connection_information() throws java.rmi.RemoteException;

    /**
     * Note: this method is intended for internal f5 use and
     *  should not be used by customers.
     * 
     *  Gets the connection type.
     */
    public SystemConnectionType get_connection_type() throws java.rmi.RemoteException;

    /**
     * Gets information regarding the slots in a chassis and the
     *  the blades in the slots (see ChassisSlot for more
     *  information).
     * 
     *  Note: This method returns useful information only on a
     *  chassis; if you call it on a system that is not a chassis,
     *  the returned sequence will be empty.
     */
    public SystemChassisSlot[] get_chassis_slot_information() throws java.rmi.RemoteException;

    /**
     * Attempts to acquire lock with specified name for specified
     * number of seconds. 
     *  These locks can be used to synchronize work of multiple clients working
     * with this
     *  device. This call returns immediately.
     */
    public boolean acquire_lock(String lock_name, long duration_sec, String comment) throws java.rmi.RemoteException;

    /**
     * Attempts to release lock with specified name. These locks can
     * be used to synchronize
     *  work of multiple clients working with this device. This call returns
     * immediately.
     *  Nothing will happen if specified lock doesn't exist.
     */
    public void release_lock(String lock_name) throws java.rmi.RemoteException;

    /**
     * Lists all names of currently acquired locks.
     */
    public String[] get_lock_list() throws java.rmi.RemoteException;

    /**
     * Gets lock statuses of specified locks
     */
    public SystemLockStatus[] get_lock_status(String[] lock_names) throws java.rmi.RemoteException;

    /**
     * Gets the version information for this interface.
     */
    public String get_version() throws java.rmi.RemoteException;
}
