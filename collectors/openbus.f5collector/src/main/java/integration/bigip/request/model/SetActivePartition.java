package integration.bigip.request.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "set_active_partition", namespace = "urn:iControl:Management/Partition")
@XmlAccessorType(XmlAccessType.FIELD)
public class SetActivePartition {

    @XmlElement(name = "active_partition")
    private String activePartition;

    public String getActivePartition() {
        return activePartition;
    }

    public void setActivePartition(String activePartition) {
        this.activePartition = activePartition;
    }
}
