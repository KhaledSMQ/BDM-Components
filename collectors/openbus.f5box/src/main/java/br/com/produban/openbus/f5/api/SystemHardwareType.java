/**
 * SystemHardwareType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class SystemHardwareType implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected SystemHardwareType(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _HARDWARE_SWITCH = "HARDWARE_SWITCH";
    public static final String _HARDWARE_COMPRESSION = "HARDWARE_COMPRESSION";
    public static final String _HARDWARE_ENCRYPTION = "HARDWARE_ENCRYPTION";
    public static final String _HARDWARE_NETWORK = "HARDWARE_NETWORK";
    public static final String _HARDWARE_PIC = "HARDWARE_PIC";
    public static final String _HARDWARE_CHASSIS = "HARDWARE_CHASSIS";
    public static final String _HARDWARE_MEZZANINE = "HARDWARE_MEZZANINE";
    public static final String _HARDWARE_BASE_BOARD = "HARDWARE_BASE_BOARD";
    public static final String _HARDWARE_PSU = "HARDWARE_PSU";
    public static final SystemHardwareType HARDWARE_SWITCH = new SystemHardwareType(_HARDWARE_SWITCH);
    public static final SystemHardwareType HARDWARE_COMPRESSION = new SystemHardwareType(_HARDWARE_COMPRESSION);
    public static final SystemHardwareType HARDWARE_ENCRYPTION = new SystemHardwareType(_HARDWARE_ENCRYPTION);
    public static final SystemHardwareType HARDWARE_NETWORK = new SystemHardwareType(_HARDWARE_NETWORK);
    public static final SystemHardwareType HARDWARE_PIC = new SystemHardwareType(_HARDWARE_PIC);
    public static final SystemHardwareType HARDWARE_CHASSIS = new SystemHardwareType(_HARDWARE_CHASSIS);
    public static final SystemHardwareType HARDWARE_MEZZANINE = new SystemHardwareType(_HARDWARE_MEZZANINE);
    public static final SystemHardwareType HARDWARE_BASE_BOARD = new SystemHardwareType(_HARDWARE_BASE_BOARD);
    public static final SystemHardwareType HARDWARE_PSU = new SystemHardwareType(_HARDWARE_PSU);
    public String getValue() { return _value_;}
    public static SystemHardwareType fromValue(String value)
          throws IllegalArgumentException {
        SystemHardwareType enumeration = (SystemHardwareType)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static SystemHardwareType fromString(String value)
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
        new org.apache.axis.description.TypeDesc(SystemHardwareType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "System.HardwareType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
