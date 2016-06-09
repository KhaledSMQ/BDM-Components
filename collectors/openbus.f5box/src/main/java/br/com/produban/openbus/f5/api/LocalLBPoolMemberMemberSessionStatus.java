/**
 * LocalLBPoolMemberMemberSessionStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class LocalLBPoolMemberMemberSessionStatus  implements java.io.Serializable {
    private CommonIPPortDefinition member;

    private LocalLBSessionStatus session_status;

    public LocalLBPoolMemberMemberSessionStatus() {
    }

    public LocalLBPoolMemberMemberSessionStatus(
           CommonIPPortDefinition member,
           LocalLBSessionStatus session_status) {
           this.member = member;
           this.session_status = session_status;
    }


    /**
     * Gets the member value for this LocalLBPoolMemberMemberSessionStatus.
     * 
     * @return member
     */
    public CommonIPPortDefinition getMember() {
        return member;
    }


    /**
     * Sets the member value for this LocalLBPoolMemberMemberSessionStatus.
     * 
     * @param member
     */
    public void setMember(CommonIPPortDefinition member) {
        this.member = member;
    }


    /**
     * Gets the session_status value for this LocalLBPoolMemberMemberSessionStatus.
     * 
     * @return session_status
     */
    public LocalLBSessionStatus getSession_status() {
        return session_status;
    }


    /**
     * Sets the session_status value for this LocalLBPoolMemberMemberSessionStatus.
     * 
     * @param session_status
     */
    public void setSession_status(LocalLBSessionStatus session_status) {
        this.session_status = session_status;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof LocalLBPoolMemberMemberSessionStatus)) return false;
        LocalLBPoolMemberMemberSessionStatus other = (LocalLBPoolMemberMemberSessionStatus) obj;
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
            ((this.session_status==null && other.getSession_status()==null) || 
             (this.session_status!=null &&
              this.session_status.equals(other.getSession_status())));
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
        if (getSession_status() != null) {
            _hashCode += getSession_status().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LocalLBPoolMemberMemberSessionStatus.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.PoolMember.MemberSessionStatus"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("member");
        elemField.setXmlName(new javax.xml.namespace.QName("", "member"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:iControl", "Common.IPPortDefinition"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("session_status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "session_status"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.SessionStatus"));
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
