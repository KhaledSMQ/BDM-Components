package database.repository;

import database.model.VirtualServer;

import java.util.List;

public interface VirtualServerRepository {

    void insertAll(List<VirtualServer> virtualServers);
    void deleteAllFrom(String balancerIp);
    List<VirtualServer> getAllFrom(String balancerIp);

}
