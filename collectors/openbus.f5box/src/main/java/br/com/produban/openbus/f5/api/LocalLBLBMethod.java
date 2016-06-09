/**
 * LocalLBLBMethod.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class LocalLBLBMethod implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected LocalLBLBMethod(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _LB_METHOD_ROUND_ROBIN = "LB_METHOD_ROUND_ROBIN";
    public static final String _LB_METHOD_RATIO_MEMBER = "LB_METHOD_RATIO_MEMBER";
    public static final String _LB_METHOD_LEAST_CONNECTION_MEMBER = "LB_METHOD_LEAST_CONNECTION_MEMBER";
    public static final String _LB_METHOD_OBSERVED_MEMBER = "LB_METHOD_OBSERVED_MEMBER";
    public static final String _LB_METHOD_PREDICTIVE_MEMBER = "LB_METHOD_PREDICTIVE_MEMBER";
    public static final String _LB_METHOD_RATIO_NODE_ADDRESS = "LB_METHOD_RATIO_NODE_ADDRESS";
    public static final String _LB_METHOD_LEAST_CONNECTION_NODE_ADDRESS = "LB_METHOD_LEAST_CONNECTION_NODE_ADDRESS";
    public static final String _LB_METHOD_FASTEST_NODE_ADDRESS = "LB_METHOD_FASTEST_NODE_ADDRESS";
    public static final String _LB_METHOD_OBSERVED_NODE_ADDRESS = "LB_METHOD_OBSERVED_NODE_ADDRESS";
    public static final String _LB_METHOD_PREDICTIVE_NODE_ADDRESS = "LB_METHOD_PREDICTIVE_NODE_ADDRESS";
    public static final String _LB_METHOD_DYNAMIC_RATIO = "LB_METHOD_DYNAMIC_RATIO";
    public static final String _LB_METHOD_FASTEST_APP_RESPONSE = "LB_METHOD_FASTEST_APP_RESPONSE";
    public static final String _LB_METHOD_LEAST_SESSIONS = "LB_METHOD_LEAST_SESSIONS";
    public static final String _LB_METHOD_DYNAMIC_RATIO_MEMBER = "LB_METHOD_DYNAMIC_RATIO_MEMBER";
    public static final String _LB_METHOD_L3_ADDR = "LB_METHOD_L3_ADDR";
    public static final String _LB_METHOD_UNKNOWN = "LB_METHOD_UNKNOWN";
    public static final String _LB_METHOD_WEIGHTED_LEAST_CONNECTION_MEMBER = "LB_METHOD_WEIGHTED_LEAST_CONNECTION_MEMBER";
    public static final String _LB_METHOD_WEIGHTED_LEAST_CONNECTION_NODE_ADDRESS = "LB_METHOD_WEIGHTED_LEAST_CONNECTION_NODE_ADDRESS";
    public static final String _LB_METHOD_RATIO_SESSION = "LB_METHOD_RATIO_SESSION";
    public static final String _LB_METHOD_RATIO_LEAST_CONNECTION_MEMBER = "LB_METHOD_RATIO_LEAST_CONNECTION_MEMBER";
    public static final String _LB_METHOD_RATIO_LEAST_CONNECTION_NODE_ADDRESS = "LB_METHOD_RATIO_LEAST_CONNECTION_NODE_ADDRESS";
    public static final LocalLBLBMethod LB_METHOD_ROUND_ROBIN = new LocalLBLBMethod(_LB_METHOD_ROUND_ROBIN);
    public static final LocalLBLBMethod LB_METHOD_RATIO_MEMBER = new LocalLBLBMethod(_LB_METHOD_RATIO_MEMBER);
    public static final LocalLBLBMethod LB_METHOD_LEAST_CONNECTION_MEMBER = new LocalLBLBMethod(_LB_METHOD_LEAST_CONNECTION_MEMBER);
    public static final LocalLBLBMethod LB_METHOD_OBSERVED_MEMBER = new LocalLBLBMethod(_LB_METHOD_OBSERVED_MEMBER);
    public static final LocalLBLBMethod LB_METHOD_PREDICTIVE_MEMBER = new LocalLBLBMethod(_LB_METHOD_PREDICTIVE_MEMBER);
    public static final LocalLBLBMethod LB_METHOD_RATIO_NODE_ADDRESS = new LocalLBLBMethod(_LB_METHOD_RATIO_NODE_ADDRESS);
    public static final LocalLBLBMethod LB_METHOD_LEAST_CONNECTION_NODE_ADDRESS = new LocalLBLBMethod(_LB_METHOD_LEAST_CONNECTION_NODE_ADDRESS);
    public static final LocalLBLBMethod LB_METHOD_FASTEST_NODE_ADDRESS = new LocalLBLBMethod(_LB_METHOD_FASTEST_NODE_ADDRESS);
    public static final LocalLBLBMethod LB_METHOD_OBSERVED_NODE_ADDRESS = new LocalLBLBMethod(_LB_METHOD_OBSERVED_NODE_ADDRESS);
    public static final LocalLBLBMethod LB_METHOD_PREDICTIVE_NODE_ADDRESS = new LocalLBLBMethod(_LB_METHOD_PREDICTIVE_NODE_ADDRESS);
    public static final LocalLBLBMethod LB_METHOD_DYNAMIC_RATIO = new LocalLBLBMethod(_LB_METHOD_DYNAMIC_RATIO);
    public static final LocalLBLBMethod LB_METHOD_FASTEST_APP_RESPONSE = new LocalLBLBMethod(_LB_METHOD_FASTEST_APP_RESPONSE);
    public static final LocalLBLBMethod LB_METHOD_LEAST_SESSIONS = new LocalLBLBMethod(_LB_METHOD_LEAST_SESSIONS);
    public static final LocalLBLBMethod LB_METHOD_DYNAMIC_RATIO_MEMBER = new LocalLBLBMethod(_LB_METHOD_DYNAMIC_RATIO_MEMBER);
    public static final LocalLBLBMethod LB_METHOD_L3_ADDR = new LocalLBLBMethod(_LB_METHOD_L3_ADDR);
    public static final LocalLBLBMethod LB_METHOD_UNKNOWN = new LocalLBLBMethod(_LB_METHOD_UNKNOWN);
    public static final LocalLBLBMethod LB_METHOD_WEIGHTED_LEAST_CONNECTION_MEMBER = new LocalLBLBMethod(_LB_METHOD_WEIGHTED_LEAST_CONNECTION_MEMBER);
    public static final LocalLBLBMethod LB_METHOD_WEIGHTED_LEAST_CONNECTION_NODE_ADDRESS = new LocalLBLBMethod(_LB_METHOD_WEIGHTED_LEAST_CONNECTION_NODE_ADDRESS);
    public static final LocalLBLBMethod LB_METHOD_RATIO_SESSION = new LocalLBLBMethod(_LB_METHOD_RATIO_SESSION);
    public static final LocalLBLBMethod LB_METHOD_RATIO_LEAST_CONNECTION_MEMBER = new LocalLBLBMethod(_LB_METHOD_RATIO_LEAST_CONNECTION_MEMBER);
    public static final LocalLBLBMethod LB_METHOD_RATIO_LEAST_CONNECTION_NODE_ADDRESS = new LocalLBLBMethod(_LB_METHOD_RATIO_LEAST_CONNECTION_NODE_ADDRESS);
    public String getValue() { return _value_;}
    public static LocalLBLBMethod fromValue(String value)
          throws IllegalArgumentException {
        LocalLBLBMethod enumeration = (LocalLBLBMethod)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static LocalLBLBMethod fromString(String value)
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
        new org.apache.axis.description.TypeDesc(LocalLBLBMethod.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.LBMethod"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
