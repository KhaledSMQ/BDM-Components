package integration.kafka;

import br.com.produban.openbus.model.avro.BigIp;
import database.model.VirtualServer;
import integration.bigip.response.model.MemberStatistic;
import integration.bigip.response.model.Pool;
import integration.bigip.response.model.PoolMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class KafkaMessage {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessage.class);

    private Pool pool;
    private VirtualServer virtualServer;
    private String tool;

    public KafkaMessage() {
    }

    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    public VirtualServer getVirtualServer() {
        return virtualServer;
    }

    public void setVirtualServer(VirtualServer virtualServer) {
        this.virtualServer = virtualServer;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public List<BigIp> toAvroList() {
        if (virtualServer==null)
            throw new IllegalStateException("Virtual server address cannot be null!");
        if (pool==null)
            throw new IllegalStateException("Pool server address cannot be null!");

        List<BigIp> list = new ArrayList<>();

        for (PoolMember member : pool.getMembers()) {
            if (member.getMetrics().isEmpty())
                LOGGER.warn("Member [{}:{}] has no metrics collected!",member.getMemberIp(),member.getMemberPort());
            else {
                for (MemberStatistic statistic : member.getMetrics()) {
                    list.add(buildAvro(member, statistic));
                }
            }
        }

        return list;
    }

    private BigIp buildAvro(PoolMember member, MemberStatistic statistic) {

        String memberIp = member.getMemberIp().contains("%") ? member.getMemberIp().split("%")[0] : member.getMemberIp();

        BigIp avro = new BigIp();
        avro.setTimestamp(String.valueOf(System.currentTimeMillis()));
        avro.setLoadBalancer(virtualServer.getBalancerIp());
        avro.setVirtualServerName(virtualServer.getName());
        avro.setVirtualServerDestination(virtualServer.getDestination());
        avro.setPoolName(pool.getName());
        avro.setMemberAddress(memberIp);
        avro.setMemberPort(member.getMemberPort());
        avro.setMetricName(statistic.getMetricName());
        avro.setValue(statistic.getMetricLowValue());

        return avro;
    }
}
