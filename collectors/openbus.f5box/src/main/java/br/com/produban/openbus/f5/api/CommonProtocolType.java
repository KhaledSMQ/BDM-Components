/**
 * CommonProtocolType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class CommonProtocolType implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected CommonProtocolType(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _PROTOCOL_ANY = "PROTOCOL_ANY";
    public static final String _PROTOCOL_IPV6 = "PROTOCOL_IPV6";
    public static final String _PROTOCOL_ROUTING = "PROTOCOL_ROUTING";
    public static final String _PROTOCOL_NONE = "PROTOCOL_NONE";
    public static final String _PROTOCOL_FRAGMENT = "PROTOCOL_FRAGMENT";
    public static final String _PROTOCOL_DSTOPTS = "PROTOCOL_DSTOPTS";
    public static final String _PROTOCOL_TCP = "PROTOCOL_TCP";
    public static final String _PROTOCOL_UDP = "PROTOCOL_UDP";
    public static final String _PROTOCOL_ICMP = "PROTOCOL_ICMP";
    public static final String _PROTOCOL_ICMPV6 = "PROTOCOL_ICMPV6";
    public static final String _PROTOCOL_OSPF = "PROTOCOL_OSPF";
    public static final String _PROTOCOL_SCTP = "PROTOCOL_SCTP";
    public static final String _PROTOCOL_UNKNOWN = "PROTOCOL_UNKNOWN";
    public static final CommonProtocolType PROTOCOL_ANY = new CommonProtocolType(_PROTOCOL_ANY);
    public static final CommonProtocolType PROTOCOL_IPV6 = new CommonProtocolType(_PROTOCOL_IPV6);
    public static final CommonProtocolType PROTOCOL_ROUTING = new CommonProtocolType(_PROTOCOL_ROUTING);
    public static final CommonProtocolType PROTOCOL_NONE = new CommonProtocolType(_PROTOCOL_NONE);
    public static final CommonProtocolType PROTOCOL_FRAGMENT = new CommonProtocolType(_PROTOCOL_FRAGMENT);
    public static final CommonProtocolType PROTOCOL_DSTOPTS = new CommonProtocolType(_PROTOCOL_DSTOPTS);
    public static final CommonProtocolType PROTOCOL_TCP = new CommonProtocolType(_PROTOCOL_TCP);
    public static final CommonProtocolType PROTOCOL_UDP = new CommonProtocolType(_PROTOCOL_UDP);
    public static final CommonProtocolType PROTOCOL_ICMP = new CommonProtocolType(_PROTOCOL_ICMP);
    public static final CommonProtocolType PROTOCOL_ICMPV6 = new CommonProtocolType(_PROTOCOL_ICMPV6);
    public static final CommonProtocolType PROTOCOL_OSPF = new CommonProtocolType(_PROTOCOL_OSPF);
    public static final CommonProtocolType PROTOCOL_SCTP = new CommonProtocolType(_PROTOCOL_SCTP);
    public static final CommonProtocolType PROTOCOL_UNKNOWN = new CommonProtocolType(_PROTOCOL_UNKNOWN);
    public String getValue() { return _value_;}
    public static CommonProtocolType fromValue(String value)
          throws IllegalArgumentException {
        CommonProtocolType enumeration = (CommonProtocolType)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static CommonProtocolType fromString(String value)
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
        new org.apache.axis.description.TypeDesc(CommonProtocolType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "Common.ProtocolType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
