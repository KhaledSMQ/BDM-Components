/**
 * LocalLBVirtualServerSourceAddressTranslationType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class LocalLBVirtualServerSourceAddressTranslationType implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected LocalLBVirtualServerSourceAddressTranslationType(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _SRC_TRANS_UNKNOWN = "SRC_TRANS_UNKNOWN";
    public static final String _SRC_TRANS_NONE = "SRC_TRANS_NONE";
    public static final String _SRC_TRANS_AUTOMAP = "SRC_TRANS_AUTOMAP";
    public static final String _SRC_TRANS_SNATPOOL = "SRC_TRANS_SNATPOOL";
    public static final String _SRC_TRANS_LSNPOOL = "SRC_TRANS_LSNPOOL";
    public static final LocalLBVirtualServerSourceAddressTranslationType SRC_TRANS_UNKNOWN = new LocalLBVirtualServerSourceAddressTranslationType(_SRC_TRANS_UNKNOWN);
    public static final LocalLBVirtualServerSourceAddressTranslationType SRC_TRANS_NONE = new LocalLBVirtualServerSourceAddressTranslationType(_SRC_TRANS_NONE);
    public static final LocalLBVirtualServerSourceAddressTranslationType SRC_TRANS_AUTOMAP = new LocalLBVirtualServerSourceAddressTranslationType(_SRC_TRANS_AUTOMAP);
    public static final LocalLBVirtualServerSourceAddressTranslationType SRC_TRANS_SNATPOOL = new LocalLBVirtualServerSourceAddressTranslationType(_SRC_TRANS_SNATPOOL);
    public static final LocalLBVirtualServerSourceAddressTranslationType SRC_TRANS_LSNPOOL = new LocalLBVirtualServerSourceAddressTranslationType(_SRC_TRANS_LSNPOOL);
    public String getValue() { return _value_;}
    public static LocalLBVirtualServerSourceAddressTranslationType fromValue(String value)
          throws IllegalArgumentException {
        LocalLBVirtualServerSourceAddressTranslationType enumeration = (LocalLBVirtualServerSourceAddressTranslationType)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static LocalLBVirtualServerSourceAddressTranslationType fromString(String value)
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
        new org.apache.axis.description.TypeDesc(LocalLBVirtualServerSourceAddressTranslationType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.VirtualServer.SourceAddressTranslationType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
