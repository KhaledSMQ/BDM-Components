package br.com.openbus.infoblox.integration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.typesafe.config.Config;

import br.com.openbus.infoblox.snmp.mib.MIBParser;
import br.com.openbus.infoblox.snmp.mib.ObjectTypeDescription;
import net.percederberg.mibble.MibLoaderException;

@Configuration
public class InfobloxIntegration {

    @Autowired
    private Config config;

    @Bean
    public List<GridMaster> gridMasterList() {
	List<GridMaster> sourceList = new ArrayList<GridMaster>();
	List<? extends Config> configList = config.getConfigList("infoblox.gridMasters");
	
	for(Config value : configList){
	    GridMaster src = new GridMaster();
	    src.setIpAddress(value.getString("ipAddress"));
	    src.setUsername(value.getString("usr"));
	    src.setPassword(value.getString("pwd"));
	    sourceList.add(src);
	}
	return sourceList;
    }

    @Bean
    public List<String> objectypeMetrics() {

	return config.getStringList("infoblox.objtypesMetrics");
    }
    
    @Bean
    public String membersInfoPath() {
	return config.getString("infoblox.membersInfoPath");
    }
    
    @Bean
    public String snmpSettingsPath() {
	return config.getString("infoblox.snmpSettingsPath");
    }

    @Bean
    @SuppressWarnings("unchecked")
    public HashMap<String, ObjectTypeDescription> objectTypesByName()
	    throws FileNotFoundException, MibLoaderException, IOException {

	List<String> fileNames = config.getStringList("infoblox.mibFiles");

	HashMap<String, ObjectTypeDescription> map = new HashMap<String, ObjectTypeDescription>();
	for (String file : fileNames) {
	    MIBParser parser = new MIBParser(new File(file));
	    map.putAll(parser.extractMibDescriptionsByName());
	}

	return map;
    }
    
    @Bean
    @SuppressWarnings("unchecked")
    public HashMap<String, ObjectTypeDescription> objectTypesByIdentifier()
	    throws FileNotFoundException, MibLoaderException, IOException {

	List<String> fileNames = config.getStringList("infoblox.mibFiles");

	HashMap<String, ObjectTypeDescription> map = new HashMap<String, ObjectTypeDescription>();
	for (String file : fileNames) {
	    MIBParser parser = new MIBParser(new File(file));
	    map.putAll(parser.extractMibDescriptionsByOID());
	}

	return map;
    }

}
