package integration.bigip.request.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "pool_names")
@XmlAccessorType(XmlAccessType.FIELD)
public class PoolNames {

    @XmlElement(name = "item")
    private List<String> items = new ArrayList<>();

    public PoolNames() {
    }

    public PoolNames(List<String> items) {
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
