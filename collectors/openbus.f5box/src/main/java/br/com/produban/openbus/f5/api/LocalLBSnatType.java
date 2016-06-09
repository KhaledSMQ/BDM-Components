/**
 * LocalLBSnatType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class LocalLBSnatType implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected LocalLBSnatType(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _SNAT_TYPE_NONE = "SNAT_TYPE_NONE";
    public static final String _SNAT_TYPE_TRANSLATION_ADDRESS = "SNAT_TYPE_TRANSLATION_ADDRESS";
    public static final String _SNAT_TYPE_SNATPOOL = "SNAT_TYPE_SNATPOOL";
    public static final String _SNAT_TYPE_AUTOMAP = "SNAT_TYPE_AUTOMAP";
    public static final String _SNAT_TYPE_UNKNOWN = "SNAT_TYPE_UNKNOWN";
    public static final LocalLBSnatType SNAT_TYPE_NONE = new LocalLBSnatType(_SNAT_TYPE_NONE);
    public static final LocalLBSnatType SNAT_TYPE_TRANSLATION_ADDRESS = new LocalLBSnatType(_SNAT_TYPE_TRANSLATION_ADDRESS);
    public static final LocalLBSnatType SNAT_TYPE_SNATPOOL = new LocalLBSnatType(_SNAT_TYPE_SNATPOOL);
    public static final LocalLBSnatType SNAT_TYPE_AUTOMAP = new LocalLBSnatType(_SNAT_TYPE_AUTOMAP);
    public static final LocalLBSnatType SNAT_TYPE_UNKNOWN = new LocalLBSnatType(_SNAT_TYPE_UNKNOWN);
    public String getValue() { return _value_;}
    public static LocalLBSnatType fromValue(String value)
          throws IllegalArgumentException {
        LocalLBSnatType enumeration = (LocalLBSnatType)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static LocalLBSnatType fromString(String value)
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
        new org.apache.axis.description.TypeDesc(LocalLBSnatType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.SnatType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
