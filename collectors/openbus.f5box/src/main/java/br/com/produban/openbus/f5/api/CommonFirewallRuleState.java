/**
 * CommonFirewallRuleState.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class CommonFirewallRuleState implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected CommonFirewallRuleState(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _FW_RULE_STATE_UNKNOWN = "FW_RULE_STATE_UNKNOWN";
    public static final String _FW_RULE_STATE_DISABLED = "FW_RULE_STATE_DISABLED";
    public static final String _FW_RULE_STATE_ENABLED = "FW_RULE_STATE_ENABLED";
    public static final String _FW_RULE_STATE_SCHEDULED = "FW_RULE_STATE_SCHEDULED";
    public static final String _FW_RULE_STATE_INVALID = "FW_RULE_STATE_INVALID";
    public static final CommonFirewallRuleState FW_RULE_STATE_UNKNOWN = new CommonFirewallRuleState(_FW_RULE_STATE_UNKNOWN);
    public static final CommonFirewallRuleState FW_RULE_STATE_DISABLED = new CommonFirewallRuleState(_FW_RULE_STATE_DISABLED);
    public static final CommonFirewallRuleState FW_RULE_STATE_ENABLED = new CommonFirewallRuleState(_FW_RULE_STATE_ENABLED);
    public static final CommonFirewallRuleState FW_RULE_STATE_SCHEDULED = new CommonFirewallRuleState(_FW_RULE_STATE_SCHEDULED);
    public static final CommonFirewallRuleState FW_RULE_STATE_INVALID = new CommonFirewallRuleState(_FW_RULE_STATE_INVALID);
    public String getValue() { return _value_;}
    public static CommonFirewallRuleState fromValue(String value)
          throws IllegalArgumentException {
        CommonFirewallRuleState enumeration = (CommonFirewallRuleState)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static CommonFirewallRuleState fromString(String value)
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
        new org.apache.axis.description.TypeDesc(CommonFirewallRuleState.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "Common.FirewallRuleState"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
