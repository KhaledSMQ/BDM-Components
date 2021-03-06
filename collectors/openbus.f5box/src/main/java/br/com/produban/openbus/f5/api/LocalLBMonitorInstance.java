/**
 * LocalLBMonitorInstance.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class LocalLBMonitorInstance  implements java.io.Serializable {
    private String template_name;

    private LocalLBMonitorIPPort instance_definition;

    public LocalLBMonitorInstance() {
    }

    public LocalLBMonitorInstance(
           String template_name,
           LocalLBMonitorIPPort instance_definition) {
           this.template_name = template_name;
           this.instance_definition = instance_definition;
    }


    /**
     * Gets the template_name value for this LocalLBMonitorInstance.
     * 
     * @return template_name
     */
    public String getTemplate_name() {
        return template_name;
    }


    /**
     * Sets the template_name value for this LocalLBMonitorInstance.
     * 
     * @param template_name
     */
    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }


    /**
     * Gets the instance_definition value for this LocalLBMonitorInstance.
     * 
     * @return instance_definition
     */
    public LocalLBMonitorIPPort getInstance_definition() {
        return instance_definition;
    }


    /**
     * Sets the instance_definition value for this LocalLBMonitorInstance.
     * 
     * @param instance_definition
     */
    public void setInstance_definition(LocalLBMonitorIPPort instance_definition) {
        this.instance_definition = instance_definition;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof LocalLBMonitorInstance)) return false;
        LocalLBMonitorInstance other = (LocalLBMonitorInstance) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.template_name==null && other.getTemplate_name()==null) || 
             (this.template_name!=null &&
              this.template_name.equals(other.getTemplate_name()))) &&
            ((this.instance_definition==null && other.getInstance_definition()==null) || 
             (this.instance_definition!=null &&
              this.instance_definition.equals(other.getInstance_definition())));
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
        if (getTemplate_name() != null) {
            _hashCode += getTemplate_name().hashCode();
        }
        if (getInstance_definition() != null) {
            _hashCode += getInstance_definition().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LocalLBMonitorInstance.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.MonitorInstance"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("template_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "template_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instance_definition");
        elemField.setXmlName(new javax.xml.namespace.QName("", "instance_definition"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:iControl", "LocalLB.MonitorIPPort"));
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
