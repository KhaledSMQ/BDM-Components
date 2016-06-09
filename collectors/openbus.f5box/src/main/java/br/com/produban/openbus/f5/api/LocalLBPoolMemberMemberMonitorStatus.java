/**
 * LocalLBPoolMemberMemberMonitorStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class LocalLBPoolMemberMemberMonitorStatus  implements java.io.Serializable {
    private CommonIPPortDefinition member;

    private LocalLBMonitorStatus monitor_status;

    public LocalLBPoolMemberMemberMonitorStatus() {
    }

    public LocalLBPoolMemberMemberMonitorStatus(
           CommonIPPortDefinition member,
           LocalLBMonitorStatus monitor_status) {
           this.member = member;
           this.monitor_status = monitor_status;
    }


    /**
     * Gets the member value for this LocalLBPoolMemberMemberMonitorStatus.
     * 
     * @return member
     */
    public CommonIPPortDefinition getMember() {
        return member;
    }


    /**
     * Sets the member value for this LocalLBPoolMemberMemberMonitorStatus.
     * 
     * @param member
     */
    public void setMember(CommonIPPortDefinition member) {
        this.member = member;
    }


    /**
     * Gets the monitor_status value for this LocalLBPoolMemberMemberMonitorStatus.
     * 
     * @return monitor_status
     */
    public LocalLBMonitorStatus getMonitor_status() {
        return monitor_status;
    }


    /**
     * Sets the monitor_status value for this LocalLBPoolMemberMemberMonitorStatus.
     * 
     * @param monitor_status
     */
    public void setMonitor_status(LocalLBMonitorStatus monitor_status) {
        this.monitor_status = monitor_status;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof LocalLBPoolMemberMemberMonitorStatus)) return false;
        LocalLBPoolMemberMemberMonitorStatus other = (LocalLBPoolMemberMemberMonitorStatus) obj;
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
            ((this.monitor_status==null && other.getMonitor_status()==null) || 
             (this.monitor_status!=null &&
              this.monitor_status.equals(other.getMonitor_status())));
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
        if (getMonitor_status() != null) {
            _hashCode += getMonitor_status().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LocalLBPoolMemberMemberMonitorStatus.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.PoolMember.MemberMonitorStatus"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("member");
        elemField.setXmlName(new javax.xml.namespace.QName("", "member"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:iControl", "Common.IPPortDefinition"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("monitor_status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "monitor_status"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.MonitorStatus"));
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
