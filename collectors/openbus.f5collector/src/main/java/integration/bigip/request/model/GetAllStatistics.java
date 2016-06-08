package integration.bigip.request.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "get_all_statistics", namespace = "urn:iControl:LocalLB/PoolMember")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetAllStatistics {

    @XmlElement(name = "pool_names")
    PoolNames poolNames;

    public PoolNames getPoolNames() {
        return poolNames;
    }

    public void setPoolNames(PoolNames poolNames) {
        this.poolNames = poolNames;
    }
}
