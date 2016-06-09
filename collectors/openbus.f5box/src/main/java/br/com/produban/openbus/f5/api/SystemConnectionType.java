/**
 * SystemConnectionType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class SystemConnectionType implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected SystemConnectionType(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _CONNECTION_TYPE_UNKNOWN = "CONNECTION_TYPE_UNKNOWN";
    public static final String _CONNECTION_TYPE_CLUSTER = "CONNECTION_TYPE_CLUSTER";
    public static final String _CONNECTION_TYPE_BLADE = "CONNECTION_TYPE_BLADE";
    public static final String _CONNECTION_TYPE_ADMIN = "CONNECTION_TYPE_ADMIN";
    public static final String _CONNECTION_TYPE_SELF = "CONNECTION_TYPE_SELF";
    public static final SystemConnectionType CONNECTION_TYPE_UNKNOWN = new SystemConnectionType(_CONNECTION_TYPE_UNKNOWN);
    public static final SystemConnectionType CONNECTION_TYPE_CLUSTER = new SystemConnectionType(_CONNECTION_TYPE_CLUSTER);
    public static final SystemConnectionType CONNECTION_TYPE_BLADE = new SystemConnectionType(_CONNECTION_TYPE_BLADE);
    public static final SystemConnectionType CONNECTION_TYPE_ADMIN = new SystemConnectionType(_CONNECTION_TYPE_ADMIN);
    public static final SystemConnectionType CONNECTION_TYPE_SELF = new SystemConnectionType(_CONNECTION_TYPE_SELF);
    public String getValue() { return _value_;}
    public static SystemConnectionType fromValue(String value)
          throws IllegalArgumentException {
        SystemConnectionType enumeration = (SystemConnectionType)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static SystemConnectionType fromString(String value)
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
        new org.apache.axis.description.TypeDesc(SystemConnectionType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "System.ConnectionType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
