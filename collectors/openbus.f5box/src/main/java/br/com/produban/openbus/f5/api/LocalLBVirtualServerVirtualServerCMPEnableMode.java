/**
 * LocalLBVirtualServerVirtualServerCMPEnableMode.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class LocalLBVirtualServerVirtualServerCMPEnableMode implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected LocalLBVirtualServerVirtualServerCMPEnableMode(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _RESOURCE_TYPE_CMP_ENABLE_ALL = "RESOURCE_TYPE_CMP_ENABLE_ALL";
    public static final String _RESOURCE_TYPE_CMP_ENABLE_SINGLE = "RESOURCE_TYPE_CMP_ENABLE_SINGLE";
    public static final String _RESOURCE_TYPE_CMP_ENABLE_GROUP = "RESOURCE_TYPE_CMP_ENABLE_GROUP";
    public static final String _RESOURCE_TYPE_CMP_ENABLE_UNKNOWN = "RESOURCE_TYPE_CMP_ENABLE_UNKNOWN";
    public static final LocalLBVirtualServerVirtualServerCMPEnableMode RESOURCE_TYPE_CMP_ENABLE_ALL = new LocalLBVirtualServerVirtualServerCMPEnableMode(_RESOURCE_TYPE_CMP_ENABLE_ALL);
    public static final LocalLBVirtualServerVirtualServerCMPEnableMode RESOURCE_TYPE_CMP_ENABLE_SINGLE = new LocalLBVirtualServerVirtualServerCMPEnableMode(_RESOURCE_TYPE_CMP_ENABLE_SINGLE);
    public static final LocalLBVirtualServerVirtualServerCMPEnableMode RESOURCE_TYPE_CMP_ENABLE_GROUP = new LocalLBVirtualServerVirtualServerCMPEnableMode(_RESOURCE_TYPE_CMP_ENABLE_GROUP);
    public static final LocalLBVirtualServerVirtualServerCMPEnableMode RESOURCE_TYPE_CMP_ENABLE_UNKNOWN = new LocalLBVirtualServerVirtualServerCMPEnableMode(_RESOURCE_TYPE_CMP_ENABLE_UNKNOWN);
    public String getValue() { return _value_;}
    public static LocalLBVirtualServerVirtualServerCMPEnableMode fromValue(String value)
          throws IllegalArgumentException {
        LocalLBVirtualServerVirtualServerCMPEnableMode enumeration = (LocalLBVirtualServerVirtualServerCMPEnableMode)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static LocalLBVirtualServerVirtualServerCMPEnableMode fromString(String value)
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
        new org.apache.axis.description.TypeDesc(LocalLBVirtualServerVirtualServerCMPEnableMode.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.VirtualServer.VirtualServerCMPEnableMode"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
