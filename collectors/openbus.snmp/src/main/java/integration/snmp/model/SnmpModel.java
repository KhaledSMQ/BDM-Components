package integration.snmp.model;

import java.util.Map;

public class SnmpModel {
    private String id;
    private String propertyName;
    private String propertyValue;

    private Map<String,String> info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "SnmpModel{" +
                "id='" + id + '\'' +
                ", propertyName='" + propertyName + '\'' +
                ", propertyValue='" + propertyValue + '\'' +
                ", info=" + info +
                '}';
    }
}
