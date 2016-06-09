/**
 * SystemProductInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public class SystemProductInformation  implements java.io.Serializable {
    private String product_code;

    private String product_version;

    private String package_version;

    private String package_edition;

    private String[] product_features;

    public SystemProductInformation() {
    }

    public SystemProductInformation(
           String product_code,
           String product_version,
           String package_version,
           String package_edition,
           String[] product_features) {
           this.product_code = product_code;
           this.product_version = product_version;
           this.package_version = package_version;
           this.package_edition = package_edition;
           this.product_features = product_features;
    }


    /**
     * Gets the product_code value for this SystemProductInformation.
     * 
     * @return product_code
     */
    public String getProduct_code() {
        return product_code;
    }


    /**
     * Sets the product_code value for this SystemProductInformation.
     * 
     * @param product_code
     */
    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }


    /**
     * Gets the product_version value for this SystemProductInformation.
     * 
     * @return product_version
     */
    public String getProduct_version() {
        return product_version;
    }


    /**
     * Sets the product_version value for this SystemProductInformation.
     * 
     * @param product_version
     */
    public void setProduct_version(String product_version) {
        this.product_version = product_version;
    }


    /**
     * Gets the package_version value for this SystemProductInformation.
     * 
     * @return package_version
     */
    public String getPackage_version() {
        return package_version;
    }


    /**
     * Sets the package_version value for this SystemProductInformation.
     * 
     * @param package_version
     */
    public void setPackage_version(String package_version) {
        this.package_version = package_version;
    }


    /**
     * Gets the package_edition value for this SystemProductInformation.
     * 
     * @return package_edition
     */
    public String getPackage_edition() {
        return package_edition;
    }


    /**
     * Sets the package_edition value for this SystemProductInformation.
     * 
     * @param package_edition
     */
    public void setPackage_edition(String package_edition) {
        this.package_edition = package_edition;
    }


    /**
     * Gets the product_features value for this SystemProductInformation.
     * 
     * @return product_features
     */
    public String[] getProduct_features() {
        return product_features;
    }


    /**
     * Sets the product_features value for this SystemProductInformation.
     * 
     * @param product_features
     */
    public void setProduct_features(String[] product_features) {
        this.product_features = product_features;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SystemProductInformation)) return false;
        SystemProductInformation other = (SystemProductInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.product_code==null && other.getProduct_code()==null) || 
             (this.product_code!=null &&
              this.product_code.equals(other.getProduct_code()))) &&
            ((this.product_version==null && other.getProduct_version()==null) || 
             (this.product_version!=null &&
              this.product_version.equals(other.getProduct_version()))) &&
            ((this.package_version==null && other.getPackage_version()==null) || 
             (this.package_version!=null &&
              this.package_version.equals(other.getPackage_version()))) &&
            ((this.package_edition==null && other.getPackage_edition()==null) || 
             (this.package_edition!=null &&
              this.package_edition.equals(other.getPackage_edition()))) &&
            ((this.product_features==null && other.getProduct_features()==null) || 
             (this.product_features!=null &&
              java.util.Arrays.equals(this.product_features, other.getProduct_features())));
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
        if (getProduct_code() != null) {
            _hashCode += getProduct_code().hashCode();
        }
        if (getProduct_version() != null) {
            _hashCode += getProduct_version().hashCode();
        }
        if (getPackage_version() != null) {
            _hashCode += getPackage_version().hashCode();
        }
        if (getPackage_edition() != null) {
            _hashCode += getPackage_edition().hashCode();
        }
        if (getProduct_features() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProduct_features());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getProduct_features(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SystemProductInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iControl", "System.ProductInformation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("product_code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "product_code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("product_version");
        elemField.setXmlName(new javax.xml.namespace.QName("", "product_version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("package_version");
        elemField.setXmlName(new javax.xml.namespace.QName("", "package_version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("package_edition");
        elemField.setXmlName(new javax.xml.namespace.QName("", "package_edition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("product_features");
        elemField.setXmlName(new javax.xml.namespace.QName("", "product_features"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
