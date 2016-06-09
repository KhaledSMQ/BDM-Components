/**
 * SystemStatisticsVirtualCompressionStatisticEntry.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class SystemStatisticsVirtualCompressionStatisticEntry  implements java.io.Serializable {
    private String device_name;

    private CommonStatistic[] statistics;

    public SystemStatisticsVirtualCompressionStatisticEntry() {
    }

    public SystemStatisticsVirtualCompressionStatisticEntry(
           String device_name,
           CommonStatistic[] statistics) {
           this.device_name = device_name;
           this.statistics = statistics;
    }


    /**
     * Gets the device_name value for this SystemStatisticsVirtualCompressionStatisticEntry.
     * 
     * @return device_name
     */
    public String getDevice_name() {
        return device_name;
    }


    /**
     * Sets the device_name value for this SystemStatisticsVirtualCompressionStatisticEntry.
     * 
     * @param device_name
     */
    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }


    /**
     * Gets the statistics value for this SystemStatisticsVirtualCompressionStatisticEntry.
     * 
     * @return statistics
     */
    public CommonStatistic[] getStatistics() {
        return statistics;
    }


    /**
     * Sets the statistics value for this SystemStatisticsVirtualCompressionStatisticEntry.
     * 
     * @param statistics
     */
    public void setStatistics(CommonStatistic[] statistics) {
        this.statistics = statistics;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SystemStatisticsVirtualCompressionStatisticEntry)) return false;
        SystemStatisticsVirtualCompressionStatisticEntry other = (SystemStatisticsVirtualCompressionStatisticEntry) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.device_name==null && other.getDevice_name()==null) || 
             (this.device_name!=null &&
              this.device_name.equals(other.getDevice_name()))) &&
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
        if (getDevice_name() != null) {
            _hashCode += getDevice_name().hashCode();
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
        new org.apache.axis.description.TypeDesc(SystemStatisticsVirtualCompressionStatisticEntry.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "System.Statistics.VirtualCompressionStatisticEntry"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("device_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "device_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
