package database.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_VIRTUAL_SERVERS")
@SequenceGenerator(name = "seq_vs",
                   sequenceName = "SEQ_VS")
@NamedQueries({
        @NamedQuery(name = VirtualServer.VS_GET_BY_BALANCER_WITH_POOLS,
                    query = "select v from VirtualServer v left join fetch v.pools where v.balancerIp = :balancer"),
        @NamedQuery(name = VirtualServer.VS_DEL_ALL_BY_BALANCER,
                    query = "delete from VirtualServer v where v.balancerIp = :balancer")
})
public class VirtualServer {

    public static final String VS_DEL_ALL_BY_BALANCER = "VS_GET_ALL_BY_BALANCER";
    public static final String VS_GET_BY_BALANCER_WITH_POOLS = "VS_GET_BY_BALANCER_WITH_POOLS";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vs")
    private Long id;
    @Column(name = "BALANCER_IP")
    private String balancerIp;
    @Column(name = "VS_NAME")
    private String name;
    @Column(name = "VS_DESTINATION")
    private String destination;

    @OneToMany(mappedBy = "virtualServer",
               cascade = {CascadeType.ALL},
               orphanRemoval = true)
    private List<Pool> pools = new ArrayList<>();

    public VirtualServer() {
    }

    public VirtualServer(String balancerIp, String name, String destination) {
        this.balancerIp = balancerIp;
        this.name = name;
        this.destination = destination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBalancerIp() {
        return balancerIp;
    }

    public void setBalancerIp(String balancerIp) {
        this.balancerIp = balancerIp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<Pool> getPools() {
        return pools;
    }

    public void setPools(List<Pool> pools) {
        this.pools = pools;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VirtualServer)) return false;
        VirtualServer server = (VirtualServer) o;
        return Objects.equals(getBalancerIp(), server.getBalancerIp()) &&
                Objects.equals(getName(), server.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBalancerIp(), getName());
    }

    @Override
    public String toString() {
        return "VirtualServer{" +
                "balancerIp='" + balancerIp + '\'' +
                ", name='" + name + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
