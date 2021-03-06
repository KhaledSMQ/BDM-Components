/**
 * CommonHAAction.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class CommonHAAction implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected CommonHAAction(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _HA_ACTION_NONE = "HA_ACTION_NONE";
    public static final String _HA_ACTION_REBOOT = "HA_ACTION_REBOOT";
    public static final String _HA_ACTION_RESTART = "HA_ACTION_RESTART";
    public static final String _HA_ACTION_FAILOVER = "HA_ACTION_FAILOVER";
    public static final String _HA_ACTION_FAILOVER_RESTART = "HA_ACTION_FAILOVER_RESTART";
    public static final String _HA_ACTION_GO_ACTIVE = "HA_ACTION_GO_ACTIVE";
    public static final String _HA_ACTION_RESTART_ALL = "HA_ACTION_RESTART_ALL";
    public static final String _HA_ACTION_FAILOVER_ABORT_TRAFFIC_MGT = "HA_ACTION_FAILOVER_ABORT_TRAFFIC_MGT";
    public static final String _HA_ACTION_GO_OFFLINE = "HA_ACTION_GO_OFFLINE";
    public static final String _HA_ACTION_GO_OFFLINE_RESTART = "HA_ACTION_GO_OFFLINE_RESTART";
    public static final String _HA_ACTION_GO_OFFLINE_ABORT_TM = "HA_ACTION_GO_OFFLINE_ABORT_TM";
    public static final String _HA_ACTION_GO_OFFLINE_DOWNLINKS = "HA_ACTION_GO_OFFLINE_DOWNLINKS";
    public static final String _HA_ACTION_GO_OFFLINE_DOWNLINKS_RESTART = "HA_ACTION_GO_OFFLINE_DOWNLINKS_RESTART";
    public static final CommonHAAction HA_ACTION_NONE = new CommonHAAction(_HA_ACTION_NONE);
    public static final CommonHAAction HA_ACTION_REBOOT = new CommonHAAction(_HA_ACTION_REBOOT);
    public static final CommonHAAction HA_ACTION_RESTART = new CommonHAAction(_HA_ACTION_RESTART);
    public static final CommonHAAction HA_ACTION_FAILOVER = new CommonHAAction(_HA_ACTION_FAILOVER);
    public static final CommonHAAction HA_ACTION_FAILOVER_RESTART = new CommonHAAction(_HA_ACTION_FAILOVER_RESTART);
    public static final CommonHAAction HA_ACTION_GO_ACTIVE = new CommonHAAction(_HA_ACTION_GO_ACTIVE);
    public static final CommonHAAction HA_ACTION_RESTART_ALL = new CommonHAAction(_HA_ACTION_RESTART_ALL);
    public static final CommonHAAction HA_ACTION_FAILOVER_ABORT_TRAFFIC_MGT = new CommonHAAction(_HA_ACTION_FAILOVER_ABORT_TRAFFIC_MGT);
    public static final CommonHAAction HA_ACTION_GO_OFFLINE = new CommonHAAction(_HA_ACTION_GO_OFFLINE);
    public static final CommonHAAction HA_ACTION_GO_OFFLINE_RESTART = new CommonHAAction(_HA_ACTION_GO_OFFLINE_RESTART);
    public static final CommonHAAction HA_ACTION_GO_OFFLINE_ABORT_TM = new CommonHAAction(_HA_ACTION_GO_OFFLINE_ABORT_TM);
    public static final CommonHAAction HA_ACTION_GO_OFFLINE_DOWNLINKS = new CommonHAAction(_HA_ACTION_GO_OFFLINE_DOWNLINKS);
    public static final CommonHAAction HA_ACTION_GO_OFFLINE_DOWNLINKS_RESTART = new CommonHAAction(_HA_ACTION_GO_OFFLINE_DOWNLINKS_RESTART);
    public String getValue() { return _value_;}
    public static CommonHAAction fromValue(String value)
          throws IllegalArgumentException {
        CommonHAAction enumeration = (CommonHAAction)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static CommonHAAction fromString(String value)
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
        new org.apache.axis.description.TypeDesc(CommonHAAction.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "Common.HAAction"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
