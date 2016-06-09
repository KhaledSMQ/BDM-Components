/**
 * CommonFirewallRulePlacement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class CommonFirewallRulePlacement  implements java.io.Serializable {
    private CommonFirewallRulePlacementType type;

    private String target;

    private long order;

    public CommonFirewallRulePlacement() {
    }

    public CommonFirewallRulePlacement(
           CommonFirewallRulePlacementType type,
           String target,
           long order) {
           this.type = type;
           this.target = target;
           this.order = order;
    }


    /**
     * Gets the type value for this CommonFirewallRulePlacement.
     * 
     * @return type
     */
    public CommonFirewallRulePlacementType getType() {
        return type;
    }


    /**
     * Sets the type value for this CommonFirewallRulePlacement.
     * 
     * @param type
     */
    public void setType(CommonFirewallRulePlacementType type) {
        this.type = type;
    }


    /**
     * Gets the target value for this CommonFirewallRulePlacement.
     * 
     * @return target
     */
    public String getTarget() {
        return target;
    }


    /**
     * Sets the target value for this CommonFirewallRulePlacement.
     * 
     * @param target
     */
    public void setTarget(String target) {
        this.target = target;
    }


    /**
     * Gets the order value for this CommonFirewallRulePlacement.
     * 
     * @return order
     */
    public long getOrder() {
        return order;
    }


    /**
     * Sets the order value for this CommonFirewallRulePlacement.
     * 
     * @param order
     */
    public void setOrder(long order) {
        this.order = order;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CommonFirewallRulePlacement)) return false;
        CommonFirewallRulePlacement other = (CommonFirewallRulePlacement) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.target==null && other.getTarget()==null) || 
             (this.target!=null &&
              this.target.equals(other.getTarget()))) &&
            this.order == other.getOrder();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getTarget() != null) {
            _hashCode += getTarget().hashCode();
        }
        _hashCode += new Long(getOrder()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CommonFirewallRulePlacement.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "Common.FirewallRulePlacement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:iControl", "Common.FirewallRulePlacementType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("target");
        elemField.setXmlName(new javax.xml.namespace.QName("", "target"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("order");
        elemField.setXmlName(new javax.xml.namespace.QName("", "order"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
