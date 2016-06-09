/**
 * SystemStatisticsGtmIQueryStatisticEntry_v2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class SystemStatisticsGtmIQueryStatisticEntry_v2  implements java.io.Serializable {
    private String ip_address;

    private SystemStatisticsGtmIQueryState connection_state;

    private String server;

    private String data_center;

    private long configuration_time;

    private long cert_expire_time;

    private CommonStatistic[] statistics;

    public SystemStatisticsGtmIQueryStatisticEntry_v2() {
    }

    public SystemStatisticsGtmIQueryStatisticEntry_v2(
           String ip_address,
           SystemStatisticsGtmIQueryState connection_state,
           String server,
           String data_center,
           long configuration_time,
           long cert_expire_time,
           CommonStatistic[] statistics) {
           this.ip_address = ip_address;
           this.connection_state = connection_state;
           this.server = server;
           this.data_center = data_center;
           this.configuration_time = configuration_time;
           this.cert_expire_time = cert_expire_time;
           this.statistics = statistics;
    }


    /**
     * Gets the ip_address value for this SystemStatisticsGtmIQueryStatisticEntry_v2.
     * 
     * @return ip_address
     */
    public String getIp_address() {
        return ip_address;
    }


    /**
     * Sets the ip_address value for this SystemStatisticsGtmIQueryStatisticEntry_v2.
     * 
     * @param ip_address
     */
    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }


    /**
     * Gets the connection_state value for this SystemStatisticsGtmIQueryStatisticEntry_v2.
     * 
     * @return connection_state
     */
    public SystemStatisticsGtmIQueryState getConnection_state() {
        return connection_state;
    }


    /**
     * Sets the connection_state value for this SystemStatisticsGtmIQueryStatisticEntry_v2.
     * 
     * @param connection_state
     */
    public void setConnection_state(SystemStatisticsGtmIQueryState connection_state) {
        this.connection_state = connection_state;
    }


    /**
     * Gets the server value for this SystemStatisticsGtmIQueryStatisticEntry_v2.
     * 
     * @return server
     */
    public String getServer() {
        return server;
    }


    /**
     * Sets the server value for this SystemStatisticsGtmIQueryStatisticEntry_v2.
     * 
     * @param server
     */
    public void setServer(String server) {
        this.server = server;
    }


    /**
     * Gets the data_center value for this SystemStatisticsGtmIQueryStatisticEntry_v2.
     * 
     * @return data_center
     */
    public String getData_center() {
        return data_center;
    }


    /**
     * Sets the data_center value for this SystemStatisticsGtmIQueryStatisticEntry_v2.
     * 
     * @param data_center
     */
    public void setData_center(String data_center) {
        this.data_center = data_center;
    }


    /**
     * Gets the configuration_time value for this SystemStatisticsGtmIQueryStatisticEntry_v2.
     * 
     * @return configuration_time
     */
    public long getConfiguration_time() {
        return configuration_time;
    }


    /**
     * Sets the configuration_time value for this SystemStatisticsGtmIQueryStatisticEntry_v2.
     * 
     * @param configuration_time
     */
    public void setConfiguration_time(long configuration_time) {
        this.configuration_time = configuration_time;
    }


    /**
     * Gets the cert_expire_time value for this SystemStatisticsGtmIQueryStatisticEntry_v2.
     * 
     * @return cert_expire_time
     */
    public long getCert_expire_time() {
        return cert_expire_time;
    }


    /**
     * Sets the cert_expire_time value for this SystemStatisticsGtmIQueryStatisticEntry_v2.
     * 
     * @param cert_expire_time
     */
    public void setCert_expire_time(long cert_expire_time) {
        this.cert_expire_time = cert_expire_time;
    }


    /**
     * Gets the statistics value for this SystemStatisticsGtmIQueryStatisticEntry_v2.
     * 
     * @return statistics
     */
    public CommonStatistic[] getStatistics() {
        return statistics;
    }


    /**
     * Sets the statistics value for this SystemStatisticsGtmIQueryStatisticEntry_v2.
     * 
     * @param statistics
     */
    public void setStatistics(CommonStatistic[] statistics) {
        this.statistics = statistics;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SystemStatisticsGtmIQueryStatisticEntry_v2)) return false;
        SystemStatisticsGtmIQueryStatisticEntry_v2 other = (SystemStatisticsGtmIQueryStatisticEntry_v2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ip_address==null && other.getIp_address()==null) || 
             (this.ip_address!=null &&
              this.ip_address.equals(other.getIp_address()))) &&
            ((this.connection_state==null && other.getConnection_state()==null) || 
             (this.connection_state!=null &&
              this.connection_state.equals(other.getConnection_state()))) &&
            ((this.server==null && other.getServer()==null) || 
             (this.server!=null &&
              this.server.equals(other.getServer()))) &&
            ((this.data_center==null && other.getData_center()==null) || 
             (this.data_center!=null &&
              this.data_center.equals(other.getData_center()))) &&
            this.configuration_time == other.getConfiguration_time() &&
            this.cert_expire_time == other.getCert_expire_time() &&
            ((this.statistics==null && other.getStatistics()==null) || 
             (this.statistics!=null &&
              java.util.Arrays.equals(this.statistics, other.getStatistics())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getIp_address() != null) {
            _hashCode += getIp_address().hashCode();
        }
        if (getConnection_state() != null) {
            _hashCode += getConnection_state().hashCode();
        }
        if (getServer() != null) {
            _hashCode += getServer().hashCode();
        }
        if (getData_center() != null) {
            _hashCode += getData_center().hashCode();
        }
        _hashCode += new Long(getConfiguration_time()).hashCode();
        _hashCode += new Long(getCert_expire_time()).hashCode();
        if (getStatistics() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStatistics());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getStatistics(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SystemStatisticsGtmIQueryStatisticEntry_v2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "System.Statistics.GtmIQueryStatisticEntry_v2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ip_address");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ip_address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("connection_state");
        elemField.setXmlName(new javax.xml.namespace.QName("", "connection_state"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:iControl", "System.Statistics.GtmIQueryState"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("server");
        elemField.setXmlName(new javax.xml.namespace.QName("", "server"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("data_center");
        elemField.setXmlName(new javax.xml.namespace.QName("", "data_center"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("configuration_time");
        elemField.setXmlName(new javax.xml.namespace.QName("", "configuration_time"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cert_expire_time");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cert_expire_time"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statistics");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statistics"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:iControl", "Common.Statistic"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
