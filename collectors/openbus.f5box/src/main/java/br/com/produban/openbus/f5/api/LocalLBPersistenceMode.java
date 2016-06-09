/**
 * LocalLBPersistenceMode.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class LocalLBPersistenceMode implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected LocalLBPersistenceMode(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _PERSISTENCE_MODE_NONE = "PERSISTENCE_MODE_NONE";
    public static final String _PERSISTENCE_MODE_SOURCE_ADDRESS_AFFINITY = "PERSISTENCE_MODE_SOURCE_ADDRESS_AFFINITY";
    public static final String _PERSISTENCE_MODE_DESTINATION_ADDRESS_AFFINITY = "PERSISTENCE_MODE_DESTINATION_ADDRESS_AFFINITY";
    public static final String _PERSISTENCE_MODE_COOKIE = "PERSISTENCE_MODE_COOKIE";
    public static final String _PERSISTENCE_MODE_MSRDP = "PERSISTENCE_MODE_MSRDP";
    public static final String _PERSISTENCE_MODE_SSL_SID = "PERSISTENCE_MODE_SSL_SID";
    public static final String _PERSISTENCE_MODE_SIP = "PERSISTENCE_MODE_SIP";
    public static final String _PERSISTENCE_MODE_UIE = "PERSISTENCE_MODE_UIE";
    public static final String _PERSISTENCE_MODE_HASH = "PERSISTENCE_MODE_HASH";
    public static final LocalLBPersistenceMode PERSISTENCE_MODE_NONE = new LocalLBPersistenceMode(_PERSISTENCE_MODE_NONE);
    public static final LocalLBPersistenceMode PERSISTENCE_MODE_SOURCE_ADDRESS_AFFINITY = new LocalLBPersistenceMode(_PERSISTENCE_MODE_SOURCE_ADDRESS_AFFINITY);
    public static final LocalLBPersistenceMode PERSISTENCE_MODE_DESTINATION_ADDRESS_AFFINITY = new LocalLBPersistenceMode(_PERSISTENCE_MODE_DESTINATION_ADDRESS_AFFINITY);
    public static final LocalLBPersistenceMode PERSISTENCE_MODE_COOKIE = new LocalLBPersistenceMode(_PERSISTENCE_MODE_COOKIE);
    public static final LocalLBPersistenceMode PERSISTENCE_MODE_MSRDP = new LocalLBPersistenceMode(_PERSISTENCE_MODE_MSRDP);
    public static final LocalLBPersistenceMode PERSISTENCE_MODE_SSL_SID = new LocalLBPersistenceMode(_PERSISTENCE_MODE_SSL_SID);
    public static final LocalLBPersistenceMode PERSISTENCE_MODE_SIP = new LocalLBPersistenceMode(_PERSISTENCE_MODE_SIP);
    public static final LocalLBPersistenceMode PERSISTENCE_MODE_UIE = new LocalLBPersistenceMode(_PERSISTENCE_MODE_UIE);
    public static final LocalLBPersistenceMode PERSISTENCE_MODE_HASH = new LocalLBPersistenceMode(_PERSISTENCE_MODE_HASH);
    public String getValue() { return _value_;}
    public static LocalLBPersistenceMode fromValue(String value)
          throws IllegalArgumentException {
        LocalLBPersistenceMode enumeration = (LocalLBPersistenceMode)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static LocalLBPersistenceMode fromString(String value)
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
        new org.apache.axis.description.TypeDesc(LocalLBPersistenceMode.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.PersistenceMode"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
