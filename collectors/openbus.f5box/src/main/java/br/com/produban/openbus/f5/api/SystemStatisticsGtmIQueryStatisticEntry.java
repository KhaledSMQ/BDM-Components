/**
 * SystemStatisticsGtmIQueryStatisticEntry.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class SystemStatisticsGtmIQueryStatisticEntry  implements java.io.Serializable {
    private String ip_address;

    private SystemStatisticsGtmIQueryState connection_state;

    private CommonStatistic[] statistics;

    public SystemStatisticsGtmIQueryStatisticEntry() {
    }

    public SystemStatisticsGtmIQueryStatisticEntry(
           String ip_address,
           SystemStatisticsGtmIQueryState connection_state,
           CommonStatistic[] statistics) {
           this.ip_address = ip_address;
           this.connection_state = connection_state;
           this.statistics = statistics;
    }


    /**
     * Gets the ip_address value for this SystemStatisticsGtmIQueryStatisticEntry.
     * 
     * @return ip_address
     */
    public String getIp_address() {
        return ip_address;
    }


    /**
     * Sets the ip_address value for this SystemStatisticsGtmIQueryStatisticEntry.
     * 
     * @param ip_address
     */
    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }


    /**
     * Gets the connection_state value for this SystemStatisticsGtmIQueryStatisticEntry.
     * 
     * @return connection_state
     */
    public SystemStatisticsGtmIQueryState getConnection_state() {
        return connection_state;
    }


    /**
     * Sets the connection_state value for this SystemStatisticsGtmIQueryStatisticEntry.
     * 
     * @param connection_state
     */
    public void setConnection_state(SystemStatisticsGtmIQueryState connection_state) {
        this.connection_state = connection_state;
    }


    /**
     * Gets the statistics value for this SystemStatisticsGtmIQueryStatisticEntry.
     * 
     * @return statistics
     */
    public CommonStatistic[] getStatistics() {
        return statistics;
    }


    /**
     * Sets the statistics value for this SystemStatisticsGtmIQueryStatisticEntry.
     * 
     * @param statistics
     */
    public void setStatistics(CommonStatistic[] statistics) {
        this.statistics = statistics;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SystemStatisticsGtmIQueryStatisticEntry)) return false;
        SystemStatisticsGtmIQueryStatisticEntry other = (SystemStatisticsGtmIQueryStatisticEntry) obj;
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
        new org.apache.axis.description.TypeDesc(SystemStatisticsGtmIQueryStatisticEntry.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "System.Statistics.GtmIQueryStatisticEntry"));
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
