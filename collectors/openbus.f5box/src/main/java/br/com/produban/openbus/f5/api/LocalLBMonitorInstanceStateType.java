/**
 * LocalLBMonitorInstanceStateType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class LocalLBMonitorInstanceStateType implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected LocalLBMonitorInstanceStateType(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _INSTANCE_STATE_UNCHECKED = "INSTANCE_STATE_UNCHECKED";
    public static final String _INSTANCE_STATE_CHECKING = "INSTANCE_STATE_CHECKING";
    public static final String _INSTANCE_STATE_UP = "INSTANCE_STATE_UP";
    public static final String _INSTANCE_STATE_DOWN = "INSTANCE_STATE_DOWN";
    public static final String _INSTANCE_STATE_FORCED_DOWN = "INSTANCE_STATE_FORCED_DOWN";
    public static final String _INSTANCE_STATE_DISABLED = "INSTANCE_STATE_DISABLED";
    public static final String _INSTANCE_STATE_DOWN_BY_IRULE = "INSTANCE_STATE_DOWN_BY_IRULE";
    public static final String _INSTANCE_STATE_DOWN_WAIT_FOR_MANUAL_RESUME = "INSTANCE_STATE_DOWN_WAIT_FOR_MANUAL_RESUME";
    public static final LocalLBMonitorInstanceStateType INSTANCE_STATE_UNCHECKED = new LocalLBMonitorInstanceStateType(_INSTANCE_STATE_UNCHECKED);
    public static final LocalLBMonitorInstanceStateType INSTANCE_STATE_CHECKING = new LocalLBMonitorInstanceStateType(_INSTANCE_STATE_CHECKING);
    public static final LocalLBMonitorInstanceStateType INSTANCE_STATE_UP = new LocalLBMonitorInstanceStateType(_INSTANCE_STATE_UP);
    public static final LocalLBMonitorInstanceStateType INSTANCE_STATE_DOWN = new LocalLBMonitorInstanceStateType(_INSTANCE_STATE_DOWN);
    public static final LocalLBMonitorInstanceStateType INSTANCE_STATE_FORCED_DOWN = new LocalLBMonitorInstanceStateType(_INSTANCE_STATE_FORCED_DOWN);
    public static final LocalLBMonitorInstanceStateType INSTANCE_STATE_DISABLED = new LocalLBMonitorInstanceStateType(_INSTANCE_STATE_DISABLED);
    public static final LocalLBMonitorInstanceStateType INSTANCE_STATE_DOWN_BY_IRULE = new LocalLBMonitorInstanceStateType(_INSTANCE_STATE_DOWN_BY_IRULE);
    public static final LocalLBMonitorInstanceStateType INSTANCE_STATE_DOWN_WAIT_FOR_MANUAL_RESUME = new LocalLBMonitorInstanceStateType(_INSTANCE_STATE_DOWN_WAIT_FOR_MANUAL_RESUME);
    public String getValue() { return _value_;}
    public static LocalLBMonitorInstanceStateType fromValue(String value)
          throws IllegalArgumentException {
        LocalLBMonitorInstanceStateType enumeration = (LocalLBMonitorInstanceStateType)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static LocalLBMonitorInstanceStateType fromString(String value)
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
        new org.apache.axis.description.TypeDesc(LocalLBMonitorInstanceStateType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.MonitorInstanceStateType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
