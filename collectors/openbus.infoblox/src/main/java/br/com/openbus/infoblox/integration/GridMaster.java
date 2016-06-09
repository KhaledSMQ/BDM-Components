package br.com.openbus.infoblox.integration;

public class GridMaster  {

    private String hostname;
    private String ipAddress;
    private String username;
    private String password;

    public String getIpAddress() {

	return ipAddress;
    }

    public void setIpAddress(String ipAddress) {

	this.ipAddress = ipAddress;
    }

    public String getUsername() {

	return username;
    }

    public void setUsername(String username) {

	this.username = username;
    }

    public String getPassword() {

	return password;
    }

    public void setPassword(String password) {

	this.password = password;
    }

    
    public String getHostname() {
    
        return hostname;
    }

    
    public void setHostname(String hostname) {
    
        this.hostname = hostname;
    }
    
    

}
