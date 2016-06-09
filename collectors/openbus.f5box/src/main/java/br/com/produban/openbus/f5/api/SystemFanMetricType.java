/**
 * SystemFanMetricType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class SystemFanMetricType implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected SystemFanMetricType(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _FAN_INDEX = "FAN_INDEX";
    public static final String _FAN_STATE = "FAN_STATE";
    public static final String _FAN_SPEED = "FAN_SPEED";
    public static final SystemFanMetricType FAN_INDEX = new SystemFanMetricType(_FAN_INDEX);
    public static final SystemFanMetricType FAN_STATE = new SystemFanMetricType(_FAN_STATE);
    public static final SystemFanMetricType FAN_SPEED = new SystemFanMetricType(_FAN_SPEED);
    public String getValue() { return _value_;}
    public static SystemFanMetricType fromValue(String value)
          throws IllegalArgumentException {
        SystemFanMetricType enumeration = (SystemFanMetricType)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static SystemFanMetricType fromString(String value)
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
        new org.apache.axis.description.TypeDesc(SystemFanMetricType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "System.FanMetricType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
