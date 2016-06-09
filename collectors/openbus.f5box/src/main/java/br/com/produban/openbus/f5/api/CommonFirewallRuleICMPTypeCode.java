/**
 * CommonFirewallRuleICMPTypeCode.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class CommonFirewallRuleICMPTypeCode  implements java.io.Serializable {
    private long type;

    private long code;

    public CommonFirewallRuleICMPTypeCode() {
    }

    public CommonFirewallRuleICMPTypeCode(
           long type,
           long code) {
           this.type = type;
           this.code = code;
    }


    /**
     * Gets the type value for this CommonFirewallRuleICMPTypeCode.
     * 
     * @return type
     */
    public long getType() {
        return type;
    }


    /**
     * Sets the type value for this CommonFirewallRuleICMPTypeCode.
     * 
     * @param type
     */
    public void setType(long type) {
        this.type = type;
    }


    /**
     * Gets the code value for this CommonFirewallRuleICMPTypeCode.
     * 
     * @return code
     */
    public long getCode() {
        return code;
    }


    /**
     * Sets the code value for this CommonFirewallRuleICMPTypeCode.
     * 
     * @param code
     */
    public void setCode(long code) {
        this.code = code;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CommonFirewallRuleICMPTypeCode)) return false;
        CommonFirewallRuleICMPTypeCode other = (CommonFirewallRuleICMPTypeCode) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.type == other.getType() &&
            this.code == other.getCode();
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
        _hashCode += new Long(getType()).hashCode();
        _hashCode += new Long(getCode()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CommonFirewallRuleICMPTypeCode.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "Common.FirewallRuleICMPTypeCode"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
