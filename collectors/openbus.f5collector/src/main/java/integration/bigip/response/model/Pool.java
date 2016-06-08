package integration.bigip.response.model;

import java.util.ArrayList;
import java.util.List;

public class Pool {

    private String name;
    private List<PoolMember> members = new ArrayList<>();

    public Pool(String name, List<PoolMember> members) {
        this.name = name;
        this.members = members;
    }

    public String getName() {
        return name;
    }
    public List<PoolMember> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return "Pool{" +
                "name='" + name + '\'' +
                ", members=" + members +
                '}';
    }
}
