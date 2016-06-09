/**
 * ManagementKeyCertificateSecurityType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class ManagementKeyCertificateSecurityType implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected ManagementKeyCertificateSecurityType(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _STYPE_NORMAL = "STYPE_NORMAL";
    public static final String _STYPE_FIPS = "STYPE_FIPS";
    public static final String _STYPE_PASSWORD = "STYPE_PASSWORD";
    public static final ManagementKeyCertificateSecurityType STYPE_NORMAL = new ManagementKeyCertificateSecurityType(_STYPE_NORMAL);
    public static final ManagementKeyCertificateSecurityType STYPE_FIPS = new ManagementKeyCertificateSecurityType(_STYPE_FIPS);
    public static final ManagementKeyCertificateSecurityType STYPE_PASSWORD = new ManagementKeyCertificateSecurityType(_STYPE_PASSWORD);
    public String getValue() { return _value_;}
    public static ManagementKeyCertificateSecurityType fromValue(String value)
          throws IllegalArgumentException {
        ManagementKeyCertificateSecurityType enumeration = (ManagementKeyCertificateSecurityType)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static ManagementKeyCertificateSecurityType fromString(String value)
          throws IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public String toString() { return _value_;}
    public Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ManagementKeyCertificateSecurityType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "Management.KeyCertificate.SecurityType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
