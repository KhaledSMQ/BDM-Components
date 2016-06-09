/**
 * CommonPoolProfileType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class CommonPoolProfileType implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected CommonPoolProfileType(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _POOL_PROFILE_UNKNOWN = "POOL_PROFILE_UNKNOWN";
    public static final String _POOL_PROFILE_IPIP = "POOL_PROFILE_IPIP";
    public static final String _POOL_PROFILE_GRE = "POOL_PROFILE_GRE";
    public static final CommonPoolProfileType POOL_PROFILE_UNKNOWN = new CommonPoolProfileType(_POOL_PROFILE_UNKNOWN);
    public static final CommonPoolProfileType POOL_PROFILE_IPIP = new CommonPoolProfileType(_POOL_PROFILE_IPIP);
    public static final CommonPoolProfileType POOL_PROFILE_GRE = new CommonPoolProfileType(_POOL_PROFILE_GRE);
    public String getValue() { return _value_;}
    public static CommonPoolProfileType fromValue(String value)
          throws IllegalArgumentException {
        CommonPoolProfileType enumeration = (CommonPoolProfileType)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static CommonPoolProfileType fromString(String value)
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
        new org.apache.axis.description.TypeDesc(CommonPoolProfileType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "Common.PoolProfileType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
