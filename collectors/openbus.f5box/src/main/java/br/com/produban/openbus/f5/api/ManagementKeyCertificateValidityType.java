/**
 * ManagementKeyCertificateValidityType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class ManagementKeyCertificateValidityType implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected ManagementKeyCertificateValidityType(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _VTYPE_CERTIFICATE_VALID = "VTYPE_CERTIFICATE_VALID";
    public static final String _VTYPE_CERTIFICATE_EXPIRED = "VTYPE_CERTIFICATE_EXPIRED";
    public static final String _VTYPE_CERTIFICATE_WILL_EXPIRE = "VTYPE_CERTIFICATE_WILL_EXPIRE";
    public static final String _VTYPE_CERTIFICATE_INVALID = "VTYPE_CERTIFICATE_INVALID";
    public static final ManagementKeyCertificateValidityType VTYPE_CERTIFICATE_VALID = new ManagementKeyCertificateValidityType(_VTYPE_CERTIFICATE_VALID);
    public static final ManagementKeyCertificateValidityType VTYPE_CERTIFICATE_EXPIRED = new ManagementKeyCertificateValidityType(_VTYPE_CERTIFICATE_EXPIRED);
    public static final ManagementKeyCertificateValidityType VTYPE_CERTIFICATE_WILL_EXPIRE = new ManagementKeyCertificateValidityType(_VTYPE_CERTIFICATE_WILL_EXPIRE);
    public static final ManagementKeyCertificateValidityType VTYPE_CERTIFICATE_INVALID = new ManagementKeyCertificateValidityType(_VTYPE_CERTIFICATE_INVALID);
    public String getValue() { return _value_;}
    public static ManagementKeyCertificateValidityType fromValue(String value)
          throws IllegalArgumentException {
        ManagementKeyCertificateValidityType enumeration = (ManagementKeyCertificateValidityType)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static ManagementKeyCertificateValidityType fromString(String value)
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
        new org.apache.axis.description.TypeDesc(ManagementKeyCertificateValidityType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "Management.KeyCertificate.ValidityType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
