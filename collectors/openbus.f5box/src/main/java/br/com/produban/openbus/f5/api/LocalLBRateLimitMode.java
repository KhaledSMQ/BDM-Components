/**
 * LocalLBRateLimitMode.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class LocalLBRateLimitMode implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected LocalLBRateLimitMode(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _RATE_LIMIT_MODE_UNKNOWN = "RATE_LIMIT_MODE_UNKNOWN";
    public static final String _RATE_LIMIT_MODE_OBJECT = "RATE_LIMIT_MODE_OBJECT";
    public static final String _RATE_LIMIT_MODE_OBJECT_SRC = "RATE_LIMIT_MODE_OBJECT_SRC";
    public static final String _RATE_LIMIT_MODE_OBJECT_DST = "RATE_LIMIT_MODE_OBJECT_DST";
    public static final String _RATE_LIMIT_MODE_OBJECT_SRC_DST = "RATE_LIMIT_MODE_OBJECT_SRC_DST";
    public static final String _RATE_LIMIT_MODE_SRC = "RATE_LIMIT_MODE_SRC";
    public static final String _RATE_LIMIT_MODE_DST = "RATE_LIMIT_MODE_DST";
    public static final String _RATE_LIMIT_MODE_SRC_DST = "RATE_LIMIT_MODE_SRC_DST";
    public static final LocalLBRateLimitMode RATE_LIMIT_MODE_UNKNOWN = new LocalLBRateLimitMode(_RATE_LIMIT_MODE_UNKNOWN);
    public static final LocalLBRateLimitMode RATE_LIMIT_MODE_OBJECT = new LocalLBRateLimitMode(_RATE_LIMIT_MODE_OBJECT);
    public static final LocalLBRateLimitMode RATE_LIMIT_MODE_OBJECT_SRC = new LocalLBRateLimitMode(_RATE_LIMIT_MODE_OBJECT_SRC);
    public static final LocalLBRateLimitMode RATE_LIMIT_MODE_OBJECT_DST = new LocalLBRateLimitMode(_RATE_LIMIT_MODE_OBJECT_DST);
    public static final LocalLBRateLimitMode RATE_LIMIT_MODE_OBJECT_SRC_DST = new LocalLBRateLimitMode(_RATE_LIMIT_MODE_OBJECT_SRC_DST);
    public static final LocalLBRateLimitMode RATE_LIMIT_MODE_SRC = new LocalLBRateLimitMode(_RATE_LIMIT_MODE_SRC);
    public static final LocalLBRateLimitMode RATE_LIMIT_MODE_DST = new LocalLBRateLimitMode(_RATE_LIMIT_MODE_DST);
    public static final LocalLBRateLimitMode RATE_LIMIT_MODE_SRC_DST = new LocalLBRateLimitMode(_RATE_LIMIT_MODE_SRC_DST);
    public String getValue() { return _value_;}
    public static LocalLBRateLimitMode fromValue(String value)
          throws IllegalArgumentException {
        LocalLBRateLimitMode enumeration = (LocalLBRateLimitMode)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static LocalLBRateLimitMode fromString(String value)
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
        new org.apache.axis.description.TypeDesc(LocalLBRateLimitMode.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.RateLimitMode"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
