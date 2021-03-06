/**
 * SystemStatisticsPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public interface SystemStatisticsPortType extends java.rmi.Remote {

    /**
     * Resets all statistics in the system, including all global statistics
     * as well as 
     *  all object-level statistics such as pool, virtual server statistics....
     */
    public void reset_all_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's global statistics.
     */
    public SystemStatisticsSystemStatistics get_global_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global statistics.
     */
    public void reset_global_statistics() throws java.rmi.RemoteException;

    /**
     * Gets a list of all PVAs.
     */
    public String[] get_list_of_pvas() throws java.rmi.RemoteException;

    /**
     * Gets all of the system's PVA statistics.
     */
    public SystemStatisticsPVAStatistics get_all_pva_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's PVA statistics (by PVA id).
     */
    public SystemStatisticsPVAStatistics get_pva_statistics(String[] pva_ids) throws java.rmi.RemoteException;

    /**
     * Gets the system's global PVA statistics.
     *  
     *  This gets one set of combined ("rolled up") statistics for
     *  all PVAs.  It is not to be confused with
     *  get_all_pva_statistics, which gets all individual PVA
     *  statistics, one for each PVA.
     */
    public SystemStatisticsGlobalPVAStatistics get_global_pva_statistics() throws java.rmi.RemoteException;

    /**
     * Gets a list of all TMOS processing agents.
     */
    public String[] get_list_of_tmms() throws java.rmi.RemoteException;

    /**
     * Gets all of the system's TMOS processing agent statistics.
     */
    public SystemStatisticsTMMStatistics get_all_tmm_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's TMOS processing agent statistics (by TMOS
     * processing agent id).
     */
    public SystemStatisticsTMMStatistics get_tmm_statistics(String[] tmm_ids) throws java.rmi.RemoteException;

    /**
     * Gets the system's global TMOS processing agent statistics.
     *  
     *  This gets one set of combined ("rolled up") statistics for
     *  all TMOS processing agents.  It is not to be confused with
     *  get_all_tmm_statistics, which gets all individual TMOS
     *  processing agent statistics, one for each TMOS processing
     *  agent.
     */
    public SystemStatisticsGlobalTMMStatistics get_global_tmm_statistics() throws java.rmi.RemoteException;

    /**
     * Gets a list of all hosts.
     */
    public String[] get_list_of_hosts() throws java.rmi.RemoteException;

    /**
     * Gets all of the system's host statistics.
     *  Note that CPU usage statistics by host are available but not retrieved
     * by this method.
     */
    public SystemStatisticsHostStatistics get_all_host_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's host statistics (by host id).
     *  Note that CPU usage statistics by host are available but not retrieved
     * by this method.
     */
    public SystemStatisticsHostStatistics get_host_statistics(String[] host_ids) throws java.rmi.RemoteException;

    /**
     * Gets the system's global host statistics.
     *  Note that global CPU usage statistics are available but not retrieved
     * by this method.
     * 
     *  This gets one set of combined ("rolled up") statistics for all hosts.
     * It is not
     *  to be confused with get_all_host_statistics, which gets all individual
     * host statistics,
     *  one for each host.
     */
    public SystemStatisticsGlobalHostStatistics get_global_host_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's global authentication statistics. These are
     * the roll-ups of all the individual 
     *  authentication profiles' statistics.
     */
    public SystemStatisticsSystemStatistics get_authentication_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global authentication statistics.
     */
    public void reset_authentication_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's global client SSL statistics. These are the
     * roll-ups of all the individual 
     *  client SSL profiles' statistics.
     */
    public SystemStatisticsSystemStatistics get_client_ssl_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global client SSL statistics.
     */
    public void reset_client_ssl_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's global OneConnect statistics. These are the
     * roll-ups of all the individual 
     *  OneConnect profiles' statistics.
     */
    public SystemStatisticsSystemStatistics get_oneconnect_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global OneConnect statistics.
     */
    public void reset_oneconnect_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's global FastL4 statistics. These are the roll-ups
     * of all the individual 
     *  FastL4 profiles' statistics.
     */
    public SystemStatisticsSystemStatistics get_fastl4_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global FastL4 statistics.
     */
    public void reset_fastl4_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's global FTP statistics. These are the roll-ups
     * of all the individual 
     *  FTP profiles' statistics.
     */
    public SystemStatisticsSystemStatistics get_ftp_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global FTP statistics.
     */
    public void reset_ftp_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's global HTTP statistics. These are the roll-ups
     * of all the individual 
     *  HTTP profiles' statistics.
     */
    public SystemStatisticsSystemStatistics get_http_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global HTTP statistics.
     */
    public void reset_http_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's global HTTP Compression statistics. These
     * are the roll-ups of all
     *  the individual HTTP Compression profiles' statistics.
     */
    public SystemStatisticsSystemStatistics get_httpcompression_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global HTTP Compression statistics.
     */
    public void reset_httpcompression_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's global Web Acceleration statistics. These
     * are the roll-ups of all
     *  the individual Web Acceleration profiles' statistics.
     */
    public SystemStatisticsSystemStatistics get_webacceleration_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global Web Acceleration statistics.
     */
    public void reset_webacceleration_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's ICMP statistics.
     */
    public SystemStatisticsSystemStatistics get_icmp_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's ICMP statistics.
     */
    public void reset_icmp_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's ICMPv6 statistics.
     */
    public SystemStatisticsSystemStatistics get_icmpv6_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's ICMPv6 statistics.
     */
    public void reset_icmpv6_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's IP statistics.
     */
    public SystemStatisticsSystemStatistics get_ip_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's IP statistics.
     */
    public void reset_ip_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's IPv6 statistics.
     */
    public SystemStatisticsSystemStatistics get_ipv6_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's IPv6 statistics.
     */
    public void reset_ipv6_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's global server SSL statistics. These are the
     * roll-ups of all the individual 
     *  server SSL profiles' statistics.
     */
    public SystemStatisticsSystemStatistics get_server_ssl_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global server SSL statistics.
     */
    public void reset_server_ssl_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's global stream statistics. These are the roll-ups
     * of all the individual 
     *  stream profiles' statistics.
     */
    public SystemStatisticsSystemStatistics get_stream_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global stream statistics.
     */
    public void reset_stream_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's global TCP statistics. These are the roll-ups
     * of all the individual 
     *  TCP profiles' statistics.
     */
    public SystemStatisticsSystemStatistics get_tcp_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global TCP statistics.
     */
    public void reset_tcp_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's global UDP statistics. These are the roll-ups
     * of all the individual 
     *  UDP profiles' statistics.
     */
    public SystemStatisticsSystemStatistics get_udp_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global UDP statistics.
     */
    public void reset_udp_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's global XML statistics. These are the roll-ups
     * of all the individual 
     *  XML profiles' statistics.
     */
    public SystemStatisticsSystemStatistics get_xml_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global XML statistics.
     */
    public void reset_xml_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's global Fast HTTP statistics. These are the
     * roll-ups of 
     *  all the individual Fast HTTP profiles' statistics.
     */
    public SystemStatisticsSystemStatistics get_fasthttp_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global Fast HTTP statistics.
     */
    public void reset_fasthttp_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's global IIOP statistics. These are the roll-ups
     * of all the individual 
     *  IIOP profiles' statistics.
     */
    public SystemStatisticsSystemStatistics get_iiop_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global IIOP statistics.
     */
    public void reset_iiop_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's global RADIUS statistics. These are the
     *  roll-ups of all the individual RADIUS profiles'
     *  statistics.
     */
    public SystemStatisticsSystemStatistics get_radius_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global RADIUS statistics.
     */
    public void reset_radius_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's global RTSP statistics. These are the roll-ups
     * of all the individual 
     *  RTSP profiles' statistics.
     */
    public SystemStatisticsSystemStatistics get_rtsp_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global RTSP statistics.
     */
    public void reset_rtsp_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's global Diameter statistics. These are the
     * roll-ups of all the individual Diameter profiles'
     *  statistics.
     */
    public SystemStatisticsSystemStatistics get_diameter_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global Diameter statistics.
     */
    public void reset_diameter_statistics() throws java.rmi.RemoteException;

    /**
     * This method has been deprecated, as it represents
     *  internal, implementation-specific details.
     * 
     *  Resets the statistics for the specified virtual compression devices.
     */
    public void reset_virtual_compression_statistics(String[] device_names) throws java.rmi.RemoteException;

    /**
     * This method has been deprecated, as it represents
     *  internal, implementation-specific details.
     * 
     *  Gets the statistics for all the virtual compression devices.
     */
    public SystemStatisticsVirtualCompressionStatistics get_all_virtual_compression_statistics() throws java.rmi.RemoteException;

    /**
     * This method has been deprecated, as it represents
     *  internal, implementation-specific details.
     * 
     *  Gets the statistics for the specified virtual compression devices.
     */
    public SystemStatisticsVirtualCompressionStatistics get_virtual_compression_statistics(String[] device_names) throws java.rmi.RemoteException;

    /**
     * Gets the list of available statistical graphs as seen in the
     * GUI's Performance Graph.
     */
    public SystemStatisticsPerformanceGraph[] get_performance_graph_list() throws java.rmi.RemoteException;

    /**
     * Gets the statistical data in CSV format for the specified graphs/tables.
     */
    public SystemStatisticsPerformanceGraphDataCSV[] get_performance_graph_csv_statistics(SystemStatisticsPerformanceStatisticQuery[] objects) throws java.rmi.RemoteException;

    /**
     * Gets the list of available statistical tables.
     */
    public SystemStatisticsPerformanceTable[] get_performance_table_list() throws java.rmi.RemoteException;

    /**
     * Gets the statistical data tables.
     */
    public SystemStatisticsPerformanceTableData[] get_performance_table_statistics(SystemStatisticsPerformanceStatisticQuery[] tables) throws java.rmi.RemoteException;

    /**
     * Gets the memory usage statistics used by GTM.
     */
    public SystemStatisticsSystemStatistics get_gtm_memory_usage_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the GTM global statistics. These are the roll-ups of all
     * the individual 
     *  wide IPs' and iQueries statistics.
     */
    public SystemStatisticsSystemStatistics get_gtm_global_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the GTM global statistics.
     */
    public void reset_gtm_global_statistics() throws java.rmi.RemoteException;

    /**
     * This method is deprecated; please use
     *  get_all_gtm_iquery_statistics_v2 in the future.
     * 
     *  Gets the statistics for all the iQueries.
     */
    public SystemStatisticsGtmIQueryStatistics get_all_gtm_iquery_statistics() throws java.rmi.RemoteException;

    /**
     * This method is deprecated; please use
     *  get_gtm_iquery_statistics_v2 in the future.
     * 
     *  Gets the statistics for the specified iQueries.
     */
    public SystemStatisticsGtmIQueryStatistics get_gtm_iquery_statistics(String[] addresses) throws java.rmi.RemoteException;

    /**
     * Gets the statistics for all the iQueries.
     */
    public SystemStatisticsGtmIQueryStatistics_v2 get_all_gtm_iquery_statistics_v2() throws java.rmi.RemoteException;

    /**
     * Gets the statistics for the specified iQueries.
     */
    public SystemStatisticsGtmIQueryStatistics_v2 get_gtm_iquery_statistics_v2(String[] addresses) throws java.rmi.RemoteException;

    /**
     * Resets the statistics for the specified iQueries.
     */
    public void reset_gtm_iquery_statistics(String[] addresses) throws java.rmi.RemoteException;

    /**
     * Gets the statistics for the specified paths (LDNSes and links).
     */
    public SystemStatisticsGtmPathStatistics get_gtm_path_statistics(String[] ldns_ips, SystemStatisticsGtmPathStatisticObjectID[] object_names) throws java.rmi.RemoteException;

    /**
     * Resets the statistics for the specified paths.
     */
    public void reset_gtm_path_statistics(String[] ldns_ips, SystemStatisticsGtmPathStatisticObjectID[] object_names) throws java.rmi.RemoteException;

    /**
     * Gets the statistics for the specified LDNSes.
     */
    public SystemStatisticsGtmLdnsStatistics get_gtm_ldns_statistics(String[] ldns_ips) throws java.rmi.RemoteException;

    /**
     * Resets the statistics for the specified LDNSes.
     */
    public void reset_gtm_ldns_statistics(String[] ldns_ips) throws java.rmi.RemoteException;

    /**
     * Gets the system's global DNS profile statistics. These are
     * the roll-ups of all the individual 
     *  DNS profiles' statistics.
     */
    public SystemStatisticsSystemStatistics get_dns_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global DNS statistics.
     */
    public void reset_dns_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the system's global SCTP statistics. These are the roll-ups
     * of all the individual 
     *  SCTP profiles' statistics.
     */
    public SystemStatisticsSystemStatistics get_sctp_statistics() throws java.rmi.RemoteException;

    /**
     * Resets the system's global SCTP statistics.
     */
    public void reset_sctp_statistics() throws java.rmi.RemoteException;

    /**
     * Gets the version information for this interface.
     */
    public String get_version() throws java.rmi.RemoteException;
}
