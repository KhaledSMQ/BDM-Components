/**
 * SystemCPUMetricType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class SystemCPUMetricType implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected SystemCPUMetricType(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _CPU_INDEX = "CPU_INDEX";
    public static final String _CPU_TEMPERATURE = "CPU_TEMPERATURE";
    public static final String _CPU_FAN_SPEED = "CPU_FAN_SPEED";
    public static final SystemCPUMetricType CPU_INDEX = new SystemCPUMetricType(_CPU_INDEX);
    public static final SystemCPUMetricType CPU_TEMPERATURE = new SystemCPUMetricType(_CPU_TEMPERATURE);
    public static final SystemCPUMetricType CPU_FAN_SPEED = new SystemCPUMetricType(_CPU_FAN_SPEED);
    public String getValue() { return _value_;}
    public static SystemCPUMetricType fromValue(String value)
          throws IllegalArgumentException {
        SystemCPUMetricType enumeration = (SystemCPUMetricType)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static SystemCPUMetricType fromString(String value)
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
        new org.apache.axis.description.TypeDesc(SystemCPUMetricType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "System.CPUMetricType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
