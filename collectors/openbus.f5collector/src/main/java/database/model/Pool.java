package database.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TB_POOLS")
@SequenceGenerator(name = "seq_pool",
                   sequenceName = "SEQ_POOL")
@NamedQuery(name = Pool.POOL_DEL_BY_BALANCER_IP,
                   query = "delete from Pool p where p.virtualServer in " +
                           "(select v from VirtualServer v where v.balancerIp = :balancer)")
public class Pool {

    public static final String POOL_DEL_BY_BALANCER_IP = "POOL_DEL_BY_BALANCER_IP";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pool")
    private Long id;
    @Column(name = "POOL_NAME")
    private String name;
    @ManyToOne
    @JoinColumn(name = "VS_ID")
    private VirtualServer virtualServer;

    public Pool() {
    }

    public Pool(String name, VirtualServer virtualServer) {
        this.name = name;
        this.virtualServer = virtualServer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VirtualServer getVirtualServer() {
        return virtualServer;
    }

    public void setVirtualServer(VirtualServer virtualServer) {
        this.virtualServer = virtualServer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pool)) return false;
        Pool pool = (Pool) o;
        return Objects.equals(getName(), pool.getName()) &&
                Objects.equals(getVirtualServer(), pool.getVirtualServer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getVirtualServer());
    }
}
