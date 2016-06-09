/**
 * SystemPSMetricType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class SystemPSMetricType implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected SystemPSMetricType(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _PS_INDEX = "PS_INDEX";
    public static final String _PS_STATE = "PS_STATE";
    public static final String _PS_INPUT_STATE = "PS_INPUT_STATE";
    public static final String _PS_OUTPUT_STATE = "PS_OUTPUT_STATE";
    public static final String _PS_FAN_STATE = "PS_FAN_STATE";
    public static final SystemPSMetricType PS_INDEX = new SystemPSMetricType(_PS_INDEX);
    public static final SystemPSMetricType PS_STATE = new SystemPSMetricType(_PS_STATE);
    public static final SystemPSMetricType PS_INPUT_STATE = new SystemPSMetricType(_PS_INPUT_STATE);
    public static final SystemPSMetricType PS_OUTPUT_STATE = new SystemPSMetricType(_PS_OUTPUT_STATE);
    public static final SystemPSMetricType PS_FAN_STATE = new SystemPSMetricType(_PS_FAN_STATE);
    public String getValue() { return _value_;}
    public static SystemPSMetricType fromValue(String value)
          throws IllegalArgumentException {
        SystemPSMetricType enumeration = (SystemPSMetricType)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static SystemPSMetricType fromString(String value)
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
        new org.apache.axis.description.TypeDesc(SystemPSMetricType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "System.PSMetricType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
