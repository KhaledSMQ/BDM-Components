/**
 * LocalLBMonitorRuleType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class LocalLBMonitorRuleType implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected LocalLBMonitorRuleType(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _MONITOR_RULE_TYPE_UNDEFINED = "MONITOR_RULE_TYPE_UNDEFINED";
    public static final String _MONITOR_RULE_TYPE_NONE = "MONITOR_RULE_TYPE_NONE";
    public static final String _MONITOR_RULE_TYPE_SINGLE = "MONITOR_RULE_TYPE_SINGLE";
    public static final String _MONITOR_RULE_TYPE_AND_LIST = "MONITOR_RULE_TYPE_AND_LIST";
    public static final String _MONITOR_RULE_TYPE_M_OF_N = "MONITOR_RULE_TYPE_M_OF_N";
    public static final LocalLBMonitorRuleType MONITOR_RULE_TYPE_UNDEFINED = new LocalLBMonitorRuleType(_MONITOR_RULE_TYPE_UNDEFINED);
    public static final LocalLBMonitorRuleType MONITOR_RULE_TYPE_NONE = new LocalLBMonitorRuleType(_MONITOR_RULE_TYPE_NONE);
    public static final LocalLBMonitorRuleType MONITOR_RULE_TYPE_SINGLE = new LocalLBMonitorRuleType(_MONITOR_RULE_TYPE_SINGLE);
    public static final LocalLBMonitorRuleType MONITOR_RULE_TYPE_AND_LIST = new LocalLBMonitorRuleType(_MONITOR_RULE_TYPE_AND_LIST);
    public static final LocalLBMonitorRuleType MONITOR_RULE_TYPE_M_OF_N = new LocalLBMonitorRuleType(_MONITOR_RULE_TYPE_M_OF_N);
    public String getValue() { return _value_;}
    public static LocalLBMonitorRuleType fromValue(String value)
          throws IllegalArgumentException {
        LocalLBMonitorRuleType enumeration = (LocalLBMonitorRuleType)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static LocalLBMonitorRuleType fromString(String value)
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
        new org.apache.axis.description.TypeDesc(LocalLBMonitorRuleType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.MonitorRuleType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
