/**
 * CommonMetadataPersistence.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class CommonMetadataPersistence implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected CommonMetadataPersistence(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _METADATA_UNKNOWN = "METADATA_UNKNOWN";
    public static final String _METADATA_EPHEMERAL = "METADATA_EPHEMERAL";
    public static final String _METADATA_PERSISTENT = "METADATA_PERSISTENT";
    public static final CommonMetadataPersistence METADATA_UNKNOWN = new CommonMetadataPersistence(_METADATA_UNKNOWN);
    public static final CommonMetadataPersistence METADATA_EPHEMERAL = new CommonMetadataPersistence(_METADATA_EPHEMERAL);
    public static final CommonMetadataPersistence METADATA_PERSISTENT = new CommonMetadataPersistence(_METADATA_PERSISTENT);
    public String getValue() { return _value_;}
    public static CommonMetadataPersistence fromValue(String value)
          throws IllegalArgumentException {
        CommonMetadataPersistence enumeration = (CommonMetadataPersistence)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static CommonMetadataPersistence fromString(String value)
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
        new org.apache.axis.description.TypeDesc(CommonMetadataPersistence.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "Common.MetadataPersistence"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
