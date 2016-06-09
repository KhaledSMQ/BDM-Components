/**
 * LocalLBSessionStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class LocalLBSessionStatus implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected LocalLBSessionStatus(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _SESSION_STATUS_ENABLED = "SESSION_STATUS_ENABLED";
    public static final String _SESSION_STATUS_DISABLED = "SESSION_STATUS_DISABLED";
    public static final String _SESSION_STATUS_FORCED_DISABLED = "SESSION_STATUS_FORCED_DISABLED";
    public static final String _SESSION_STATUS_ADDRESS_DISABLED = "SESSION_STATUS_ADDRESS_DISABLED";
    public static final LocalLBSessionStatus SESSION_STATUS_ENABLED = new LocalLBSessionStatus(_SESSION_STATUS_ENABLED);
    public static final LocalLBSessionStatus SESSION_STATUS_DISABLED = new LocalLBSessionStatus(_SESSION_STATUS_DISABLED);
    public static final LocalLBSessionStatus SESSION_STATUS_FORCED_DISABLED = new LocalLBSessionStatus(_SESSION_STATUS_FORCED_DISABLED);
    public static final LocalLBSessionStatus SESSION_STATUS_ADDRESS_DISABLED = new LocalLBSessionStatus(_SESSION_STATUS_ADDRESS_DISABLED);
    public String getValue() { return _value_;}
    public static LocalLBSessionStatus fromValue(String value)
          throws IllegalArgumentException {
        LocalLBSessionStatus enumeration = (LocalLBSessionStatus)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static LocalLBSessionStatus fromString(String value)
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
        new org.apache.axis.description.TypeDesc(LocalLBSessionStatus.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.SessionStatus"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
