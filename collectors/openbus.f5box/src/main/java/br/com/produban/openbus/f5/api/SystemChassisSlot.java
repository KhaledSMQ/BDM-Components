/**
 * SystemChassisSlot.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class SystemChassisSlot  implements java.io.Serializable {
    private long slot_id;

    private String serial_number;

    private boolean is_down;

    private SystemChassisSlotState state;

    public SystemChassisSlot() {
    }

    public SystemChassisSlot(
           long slot_id,
           String serial_number,
           boolean is_down,
           SystemChassisSlotState state) {
           this.slot_id = slot_id;
           this.serial_number = serial_number;
           this.is_down = is_down;
           this.state = state;
    }


    /**
     * Gets the slot_id value for this SystemChassisSlot.
     * 
     * @return slot_id
     */
    public long getSlot_id() {
        return slot_id;
    }


    /**
     * Sets the slot_id value for this SystemChassisSlot.
     * 
     * @param slot_id
     */
    public void setSlot_id(long slot_id) {
        this.slot_id = slot_id;
    }


    /**
     * Gets the serial_number value for this SystemChassisSlot.
     * 
     * @return serial_number
     */
    public String getSerial_number() {
        return serial_number;
    }


    /**
     * Sets the serial_number value for this SystemChassisSlot.
     * 
     * @param serial_number
     */
    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }


    /**
     * Gets the is_down value for this SystemChassisSlot.
     * 
     * @return is_down
     */
    public boolean isIs_down() {
        return is_down;
    }


    /**
     * Sets the is_down value for this SystemChassisSlot.
     * 
     * @param is_down
     */
    public void setIs_down(boolean is_down) {
        this.is_down = is_down;
    }


    /**
     * Gets the state value for this SystemChassisSlot.
     * 
     * @return state
     */
    public SystemChassisSlotState getState() {
        return state;
    }


    /**
     * Sets the state value for this SystemChassisSlot.
     * 
     * @param state
     */
    public void setState(SystemChassisSlotState state) {
        this.state = state;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SystemChassisSlot)) return false;
        SystemChassisSlot other = (SystemChassisSlot) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.slot_id == other.getSlot_id() &&
            ((this.serial_number==null && other.getSerial_number()==null) || 
             (this.serial_number!=null &&
              this.serial_number.equals(other.getSerial_number()))) &&
            this.is_down == other.isIs_down() &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState())));
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
        _hashCode += new Long(getSlot_id()).hashCode();
        if (getSerial_number() != null) {
            _hashCode += getSerial_number().hashCode();
        }
        _hashCode += (isIs_down() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SystemChassisSlot.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "System.ChassisSlot"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("slot_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "slot_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serial_number");
        elemField.setXmlName(new javax.xml.namespace.QName("", "serial_number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_down");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_down"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("", "state"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:iControl", "System.ChassisSlotState"));
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
