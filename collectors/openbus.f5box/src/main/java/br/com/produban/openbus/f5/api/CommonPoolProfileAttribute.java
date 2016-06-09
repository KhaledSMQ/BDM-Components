/**
 * CommonPoolProfileAttribute.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class CommonPoolProfileAttribute  implements java.io.Serializable {
    private CommonPoolProfileType profile_type;

    private String profile_name;

    public CommonPoolProfileAttribute() {
    }

    public CommonPoolProfileAttribute(
           CommonPoolProfileType profile_type,
           String profile_name) {
           this.profile_type = profile_type;
           this.profile_name = profile_name;
    }


    /**
     * Gets the profile_type value for this CommonPoolProfileAttribute.
     * 
     * @return profile_type
     */
    public CommonPoolProfileType getProfile_type() {
        return profile_type;
    }


    /**
     * Sets the profile_type value for this CommonPoolProfileAttribute.
     * 
     * @param profile_type
     */
    public void setProfile_type(CommonPoolProfileType profile_type) {
        this.profile_type = profile_type;
    }


    /**
     * Gets the profile_name value for this CommonPoolProfileAttribute.
     * 
     * @return profile_name
     */
    public String getProfile_name() {
        return profile_name;
    }


    /**
     * Sets the profile_name value for this CommonPoolProfileAttribute.
     * 
     * @param profile_name
     */
    public void setProfile_name(String profile_name) {
        this.profile_name = profile_name;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CommonPoolProfileAttribute)) return false;
        CommonPoolProfileAttribute other = (CommonPoolProfileAttribute) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.profile_type==null && other.getProfile_type()==null) || 
             (this.profile_type!=null &&
              this.profile_type.equals(other.getProfile_type()))) &&
            ((this.profile_name==null && other.getProfile_name()==null) || 
             (this.profile_name!=null &&
              this.profile_name.equals(other.getProfile_name())));
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
        if (getProfile_type() != null) {
            _hashCode += getProfile_type().hashCode();
        }
        if (getProfile_name() != null) {
            _hashCode += getProfile_name().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CommonPoolProfileAttribute.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "Common.PoolProfileAttribute"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profile_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "profile_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:iControl", "Common.PoolProfileType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profile_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "profile_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
