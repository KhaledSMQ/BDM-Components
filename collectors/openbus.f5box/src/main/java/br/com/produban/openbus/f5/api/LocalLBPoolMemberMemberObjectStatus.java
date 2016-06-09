/**
 * LocalLBPoolMemberMemberObjectStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class LocalLBPoolMemberMemberObjectStatus  implements java.io.Serializable {
    private CommonIPPortDefinition member;

    private LocalLBObjectStatus object_status;

    public LocalLBPoolMemberMemberObjectStatus() {
    }

    public LocalLBPoolMemberMemberObjectStatus(
           CommonIPPortDefinition member,
           LocalLBObjectStatus object_status) {
           this.member = member;
           this.object_status = object_status;
    }


    /**
     * Gets the member value for this LocalLBPoolMemberMemberObjectStatus.
     * 
     * @return member
     */
    public CommonIPPortDefinition getMember() {
        return member;
    }


    /**
     * Sets the member value for this LocalLBPoolMemberMemberObjectStatus.
     * 
     * @param member
     */
    public void setMember(CommonIPPortDefinition member) {
        this.member = member;
    }


    /**
     * Gets the object_status value for this LocalLBPoolMemberMemberObjectStatus.
     * 
     * @return object_status
     */
    public LocalLBObjectStatus getObject_status() {
        return object_status;
    }


    /**
     * Sets the object_status value for this LocalLBPoolMemberMemberObjectStatus.
     * 
     * @param object_status
     */
    public void setObject_status(LocalLBObjectStatus object_status) {
        this.object_status = object_status;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof LocalLBPoolMemberMemberObjectStatus)) return false;
        LocalLBPoolMemberMemberObjectStatus other = (LocalLBPoolMemberMemberObjectStatus) obj;
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
            ((this.object_status==null && other.getObject_status()==null) || 
             (this.object_status!=null &&
              this.object_status.equals(other.getObject_status())));
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
        if (getObject_status() != null) {
            _hashCode += getObject_status().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LocalLBPoolMemberMemberObjectStatus.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.PoolMember.MemberObjectStatus"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("member");
        elemField.setXmlName(new javax.xml.namespace.QName("", "member"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:iControl", "Common.IPPortDefinition"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("object_status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "object_status"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.ObjectStatus"));
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
