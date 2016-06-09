/**
 * SystemPlatformFans.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class SystemPlatformFans  implements java.io.Serializable {
    private SystemFanMetric[][] fans;

    private CommonTimeStamp time_stamp;

    public SystemPlatformFans() {
    }

    public SystemPlatformFans(
           SystemFanMetric[][] fans,
           CommonTimeStamp time_stamp) {
           this.fans = fans;
           this.time_stamp = time_stamp;
    }


    /**
     * Gets the fans value for this SystemPlatformFans.
     * 
     * @return fans
     */
    public SystemFanMetric[][] getFans() {
        return fans;
    }


    /**
     * Sets the fans value for this SystemPlatformFans.
     * 
     * @param fans
     */
    public void setFans(SystemFanMetric[][] fans) {
        this.fans = fans;
    }


    /**
     * Gets the time_stamp value for this SystemPlatformFans.
     * 
     * @return time_stamp
     */
    public CommonTimeStamp getTime_stamp() {
        return time_stamp;
    }


    /**
     * Sets the time_stamp value for this SystemPlatformFans.
     * 
     * @param time_stamp
     */
    public void setTime_stamp(CommonTimeStamp time_stamp) {
        this.time_stamp = time_stamp;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SystemPlatformFans)) return false;
        SystemPlatformFans other = (SystemPlatformFans) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fans==null && other.getFans()==null) || 
             (this.fans!=null &&
              java.util.Arrays.equals(this.fans, other.getFans()))) &&
            ((this.time_stamp==null && other.getTime_stamp()==null) || 
             (this.time_stamp!=null &&
              this.time_stamp.equals(other.getTime_stamp())));
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
        if (getFans() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFans());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getFans(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTime_stamp() != null) {
            _hashCode += getTime_stamp().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SystemPlatformFans.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "System.PlatformFans"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fans");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fans"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:iControl", "System.FanMetricSequence"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("time_stamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "time_stamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:iControl", "Common.TimeStamp"));
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
