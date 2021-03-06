/**
 * LocalLBVirtualServerVirtualServerProfile.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class LocalLBVirtualServerVirtualServerProfile  implements java.io.Serializable {
    private LocalLBProfileContextType profile_context;

    private String profile_name;

    public LocalLBVirtualServerVirtualServerProfile() {
    }

    public LocalLBVirtualServerVirtualServerProfile(
           LocalLBProfileContextType profile_context,
           String profile_name) {
           this.profile_context = profile_context;
           this.profile_name = profile_name;
    }


    /**
     * Gets the profile_context value for this LocalLBVirtualServerVirtualServerProfile.
     * 
     * @return profile_context
     */
    public LocalLBProfileContextType getProfile_context() {
        return profile_context;
    }


    /**
     * Sets the profile_context value for this LocalLBVirtualServerVirtualServerProfile.
     * 
     * @param profile_context
     */
    public void setProfile_context(LocalLBProfileContextType profile_context) {
        this.profile_context = profile_context;
    }


    /**
     * Gets the profile_name value for this LocalLBVirtualServerVirtualServerProfile.
     * 
     * @return profile_name
     */
    public String getProfile_name() {
        return profile_name;
    }


    /**
     * Sets the profile_name value for this LocalLBVirtualServerVirtualServerProfile.
     * 
     * @param profile_name
     */
    public void setProfile_name(String profile_name) {
        this.profile_name = profile_name;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof LocalLBVirtualServerVirtualServerProfile)) return false;
        LocalLBVirtualServerVirtualServerProfile other = (LocalLBVirtualServerVirtualServerProfile) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.profile_context==null && other.getProfile_context()==null) || 
             (this.profile_context!=null &&
              this.profile_context.equals(other.getProfile_context()))) &&
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
        if (getProfile_context() != null) {
            _hashCode += getProfile_context().hashCode();
        }
        if (getProfile_name() != null) {
            _hashCode += getProfile_name().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LocalLBVirtualServerVirtualServerProfile.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.VirtualServer.VirtualServerProfile"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profile_context");
        elemField.setXmlName(new javax.xml.namespace.QName("", "profile_context"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.ProfileContextType"));
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
