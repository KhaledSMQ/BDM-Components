package integration.bigip.response.model;

public class VirtualServer {

    private String bigIp;
    private String name;
    private String destination;
    private String defaultPoolName;

    public VirtualServer() {
    }

    public VirtualServer(String bigIp, String name, String destination, String defaultPoolName) {
        this.bigIp = bigIp;
        this.name = name;
        this.destination = destination;
        this.defaultPoolName = defaultPoolName;
    }

    public String getBigIp() {
        return bigIp;
    }

    public void setBigIp(String bigIp) {
        this.bigIp = bigIp;
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

    public String getDefaultPoolName() {
        return defaultPoolName;
    }

    public void setDefaultPoolName(String defaultPoolName) {
        this.defaultPoolName = defaultPoolName;
    }
}
