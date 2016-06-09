/**
 * LocalLBVirtualServerVirtualServerType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class LocalLBVirtualServerVirtualServerType implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected LocalLBVirtualServerVirtualServerType(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _RESOURCE_TYPE_POOL = "RESOURCE_TYPE_POOL";
    public static final String _RESOURCE_TYPE_IP_FORWARDING = "RESOURCE_TYPE_IP_FORWARDING";
    public static final String _RESOURCE_TYPE_L2_FORWARDING = "RESOURCE_TYPE_L2_FORWARDING";
    public static final String _RESOURCE_TYPE_REJECT = "RESOURCE_TYPE_REJECT";
    public static final String _RESOURCE_TYPE_FAST_L4 = "RESOURCE_TYPE_FAST_L4";
    public static final String _RESOURCE_TYPE_FAST_HTTP = "RESOURCE_TYPE_FAST_HTTP";
    public static final String _RESOURCE_TYPE_STATELESS = "RESOURCE_TYPE_STATELESS";
    public static final String _RESOURCE_TYPE_DHCP_RELAY = "RESOURCE_TYPE_DHCP_RELAY";
    public static final String _RESOURCE_TYPE_UNKNOWN = "RESOURCE_TYPE_UNKNOWN";
    public static final String _RESOURCE_TYPE_INTERNAL = "RESOURCE_TYPE_INTERNAL";
    public static final LocalLBVirtualServerVirtualServerType RESOURCE_TYPE_POOL = new LocalLBVirtualServerVirtualServerType(_RESOURCE_TYPE_POOL);
    public static final LocalLBVirtualServerVirtualServerType RESOURCE_TYPE_IP_FORWARDING = new LocalLBVirtualServerVirtualServerType(_RESOURCE_TYPE_IP_FORWARDING);
    public static final LocalLBVirtualServerVirtualServerType RESOURCE_TYPE_L2_FORWARDING = new LocalLBVirtualServerVirtualServerType(_RESOURCE_TYPE_L2_FORWARDING);
    public static final LocalLBVirtualServerVirtualServerType RESOURCE_TYPE_REJECT = new LocalLBVirtualServerVirtualServerType(_RESOURCE_TYPE_REJECT);
    public static final LocalLBVirtualServerVirtualServerType RESOURCE_TYPE_FAST_L4 = new LocalLBVirtualServerVirtualServerType(_RESOURCE_TYPE_FAST_L4);
    public static final LocalLBVirtualServerVirtualServerType RESOURCE_TYPE_FAST_HTTP = new LocalLBVirtualServerVirtualServerType(_RESOURCE_TYPE_FAST_HTTP);
    public static final LocalLBVirtualServerVirtualServerType RESOURCE_TYPE_STATELESS = new LocalLBVirtualServerVirtualServerType(_RESOURCE_TYPE_STATELESS);
    public static final LocalLBVirtualServerVirtualServerType RESOURCE_TYPE_DHCP_RELAY = new LocalLBVirtualServerVirtualServerType(_RESOURCE_TYPE_DHCP_RELAY);
    public static final LocalLBVirtualServerVirtualServerType RESOURCE_TYPE_UNKNOWN = new LocalLBVirtualServerVirtualServerType(_RESOURCE_TYPE_UNKNOWN);
    public static final LocalLBVirtualServerVirtualServerType RESOURCE_TYPE_INTERNAL = new LocalLBVirtualServerVirtualServerType(_RESOURCE_TYPE_INTERNAL);
    public String getValue() { return _value_;}
    public static LocalLBVirtualServerVirtualServerType fromValue(String value)
          throws IllegalArgumentException {
        LocalLBVirtualServerVirtualServerType enumeration = (LocalLBVirtualServerVirtualServerType)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static LocalLBVirtualServerVirtualServerType fromString(String value)
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
        new org.apache.axis.description.TypeDesc(LocalLBVirtualServerVirtualServerType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.VirtualServer.VirtualServerType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
