/**
 * SystemChassisSlotState.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class SystemChassisSlotState implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected SystemChassisSlotState(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _CHASSIS_SLOT_STATE_UNKNOWN = "CHASSIS_SLOT_STATE_UNKNOWN";
    public static final String _CHASSIS_SLOT_STATE_FAILED = "CHASSIS_SLOT_STATE_FAILED";
    public static final String _CHASSIS_SLOT_STATE_EMPTY = "CHASSIS_SLOT_STATE_EMPTY";
    public static final String _CHASSIS_SLOT_STATE_PRIMING = "CHASSIS_SLOT_STATE_PRIMING";
    public static final String _CHASSIS_SLOT_STATE_OK = "CHASSIS_SLOT_STATE_OK";
    public static final SystemChassisSlotState CHASSIS_SLOT_STATE_UNKNOWN = new SystemChassisSlotState(_CHASSIS_SLOT_STATE_UNKNOWN);
    public static final SystemChassisSlotState CHASSIS_SLOT_STATE_FAILED = new SystemChassisSlotState(_CHASSIS_SLOT_STATE_FAILED);
    public static final SystemChassisSlotState CHASSIS_SLOT_STATE_EMPTY = new SystemChassisSlotState(_CHASSIS_SLOT_STATE_EMPTY);
    public static final SystemChassisSlotState CHASSIS_SLOT_STATE_PRIMING = new SystemChassisSlotState(_CHASSIS_SLOT_STATE_PRIMING);
    public static final SystemChassisSlotState CHASSIS_SLOT_STATE_OK = new SystemChassisSlotState(_CHASSIS_SLOT_STATE_OK);
    public String getValue() { return _value_;}
    public static SystemChassisSlotState fromValue(String value)
          throws IllegalArgumentException {
        SystemChassisSlotState enumeration = (SystemChassisSlotState)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static SystemChassisSlotState fromString(String value)
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
        new org.apache.axis.description.TypeDesc(SystemChassisSlotState.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "System.ChassisSlotState"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
