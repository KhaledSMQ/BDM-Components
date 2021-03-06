package integration.bigip.request.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "get_destination", namespace = "urn:iControl:LocalLB/VirtualServer")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetDestination {

    @XmlElement(name = "virtual_servers")
    VirtualServers virtualServers;

    public VirtualServers getVirtualServers() {
        return virtualServers;
    }

    public void setVirtualServers(VirtualServers virtualServers) {
        this.virtualServers = virtualServers;
    }
}
