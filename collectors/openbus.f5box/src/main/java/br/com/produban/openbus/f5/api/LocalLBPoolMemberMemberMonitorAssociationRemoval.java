/**
 * LocalLBPoolMemberMemberMonitorAssociationRemoval.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class LocalLBPoolMemberMemberMonitorAssociationRemoval  implements java.io.Serializable {
    private LocalLBMonitorIPPort member;

    private LocalLBMonitorAssociationRemovalRule removal_rule;

    public LocalLBPoolMemberMemberMonitorAssociationRemoval() {
    }

    public LocalLBPoolMemberMemberMonitorAssociationRemoval(
           LocalLBMonitorIPPort member,
           LocalLBMonitorAssociationRemovalRule removal_rule) {
           this.member = member;
           this.removal_rule = removal_rule;
    }


    /**
     * Gets the member value for this LocalLBPoolMemberMemberMonitorAssociationRemoval.
     * 
     * @return member
     */
    public LocalLBMonitorIPPort getMember() {
        return member;
    }


    /**
     * Sets the member value for this LocalLBPoolMemberMemberMonitorAssociationRemoval.
     * 
     * @param member
     */
    public void setMember(LocalLBMonitorIPPort member) {
        this.member = member;
    }


    /**
     * Gets the removal_rule value for this LocalLBPoolMemberMemberMonitorAssociationRemoval.
     * 
     * @return removal_rule
     */
    public LocalLBMonitorAssociationRemovalRule getRemoval_rule() {
        return removal_rule;
    }


    /**
     * Sets the removal_rule value for this LocalLBPoolMemberMemberMonitorAssociationRemoval.
     * 
     * @param removal_rule
     */
    public void setRemoval_rule(LocalLBMonitorAssociationRemovalRule removal_rule) {
        this.removal_rule = removal_rule;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof LocalLBPoolMemberMemberMonitorAssociationRemoval)) return false;
        LocalLBPoolMemberMemberMonitorAssociationRemoval other = (LocalLBPoolMemberMemberMonitorAssociationRemoval) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.member==null && other.getMember()==null) || 
             (this.member!=null &&
              this.member.equals(other.getMember()))) &&
            ((this.removal_rule==null && other.getRemoval_rule()==null) || 
             (this.removal_rule!=null &&
              this.removal_rule.equals(other.getRemoval_rule())));
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
        if (getMember() != null) {
            _hashCode += getMember().hashCode();
        }
        if (getRemoval_rule() != null) {
            _hashCode += getRemoval_rule().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LocalLBPoolMemberMemberMonitorAssociationRemoval.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.PoolMember.MemberMonitorAssociationRemoval"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("member");
        elemField.setXmlName(new javax.xml.namespace.QName("", "member"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.MonitorIPPort"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("removal_rule");
        elemField.setXmlName(new javax.xml.namespace.QName("", "removal_rule"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.MonitorAssociationRemovalRule"));
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
