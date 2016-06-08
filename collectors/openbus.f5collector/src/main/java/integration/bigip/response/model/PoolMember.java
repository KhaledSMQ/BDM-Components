package integration.bigip.response.model;

import java.util.ArrayList;
import java.util.List;

public class PoolMember {

    private String memberIp;
    private String memberPort;
    private List<MemberStatistic> metrics = new ArrayList<>();

    public String getMemberIp() {
        return memberIp;
    }

    public void setMemberIp(String memberIp) {
        this.memberIp = memberIp;
    }

    public String getMemberPort() {
        return memberPort;
    }

    public void setMemberPort(String memberPort) {
        this.memberPort = memberPort;
    }

    public List<MemberStatistic> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<MemberStatistic> metrics) {
        this.metrics = metrics;
    }

    @Override
    public String toString() {
        return "PoolMember{" +
                "memberIp='" + memberIp + '\'' +
                ", memberPort='" + memberPort + '\'' +
                ", metrics=" + metrics +
                '}';
    }
}
