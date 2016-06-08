package database.repository;

import database.model.Pool;
import database.model.VirtualServer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class VirtualServerRepositoryImpl implements VirtualServerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void insertAll(List<VirtualServer> virtualServers) {
        for (VirtualServer virtualServer : virtualServers)
            em.persist(virtualServer);
    }

    @Override
    public List<VirtualServer> getAllFrom(String balancerIp) {
        TypedQuery<VirtualServer> getAll = em.createNamedQuery(VirtualServer.VS_GET_BY_BALANCER_WITH_POOLS, VirtualServer.class);
        getAll.setParameter("balancer", balancerIp);
        return getAll.getResultList();
    }

    @Override
    @Transactional
    public void deleteAllFrom(String balancerIp) {
        em.createNamedQuery(Pool.POOL_DEL_BY_BALANCER_IP)
          .setParameter("balancer",balancerIp)
          .executeUpdate();
        em.createNamedQuery(VirtualServer.VS_DEL_ALL_BY_BALANCER)
          .setParameter("balancer",balancerIp)
          .executeUpdate();
    }
}
