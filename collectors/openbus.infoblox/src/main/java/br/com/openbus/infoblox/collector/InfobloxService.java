package br.com.openbus.infoblox.collector;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.smi.OID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.typesafe.config.ConfigFactory;

import br.com.openbus.infoblox.SSLUtil;
import br.com.openbus.infoblox.integration.GridMaster;
import br.com.openbus.infoblox.snmp.SNMPConnector;
import br.com.openbus.infoblox.snmp.mib.ObjectTypeDescription;
import br.com.openbus.infoblox.wapi.response.GridMemberResponse;
import br.com.openbus.infoblox.wapi.response.ServiceStatus;
import br.com.openbus.infoblox.wapi.response.SnmpSettings;
import br.com.produban.openbus.ingestor.OpenbusDataIngestion;
import br.com.produban.openbus.security.exceptions.BusinessException;

@Service
public class InfobloxService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfobloxService.class);

    private static Pattern pattern = Pattern
	    .compile(ConfigFactory.load().getString("regex.exceptions").replace(",", "|"));
    
    @Resource
    @Qualifier("gridMasterList")
    private List<GridMaster> gridMasterList;

    @Resource
    @Qualifier("objectypeMetrics")
    private List<String> objectypeMetrics;

    @Resource
    @Qualifier("objectTypesByName")
    private HashMap<String, ObjectTypeDescription> objectTypesByName;

    @Resource
    @Qualifier("objectTypesByIdentifier")
    private HashMap<String, ObjectTypeDescription> objectTypesByIdentifier;

    @Autowired
    private OpenbusDataIngestion ingestor;
    
    @Autowired
    private InfobloxApiService apiService;

    @Autowired
    public Schema schema;

    public void collect() {

	SSLUtil.disableSslVerification();

	for (GridMaster master : gridMasterList) {

	    SnmpSettings snmpSettings = apiService.getSnmpSettings(master);
	    GridMemberResponse[] members = apiService.getGridMembers(master);

	    for (GridMemberResponse member : members) {

		member.setGridMaster(master.getIpAddress());

		ServiceStatus status = member.getNodeInfo().getNodeStatus();

		if (status != null) {

		    // It checks if the member is running and working
		    // So be able to send the member's information through SNMP
		    if (status.isRunning() && status.isWorking()) {

			SNMPConnector snmp = new SNMPConnector(member.getVipSettings().getAddress(),
				snmpSettings.getQueryCommunityString());

			// Iterates and gets info from each OID setted in the
			// 'infoblox.oids' property
			for (String objtypeName : objectypeMetrics) {

			    ObjectTypeDescription mibDescription = getObjecTypeDescriptionByName(objtypeName);

			    try {
				String values = snmp.getOIDValues(new OID(mibDescription.getObjectIdentifier()));

				// It process the returned values
				process(master, member, values);

			    } catch (IOException e) {
				LOGGER.error(e.getMessage());
			    } catch (BusinessException e) {
				LOGGER.error(e.getMessage());
			    }
			}
		    }
		}
	    }
	}
    }

    @SuppressWarnings("resource")
    private void process(GridMaster master, GridMemberResponse member, String values)
	    throws IOException, BusinessException {

	Scanner scanner = new Scanner(values);

	while (scanner.hasNext()) {

	    String line = scanner.nextLine();
	    String[] info = line.split("=");

	    if (info.length == 2) {

		String description = resolveData(info[0], false);
		String index = resolveData(info[0], true);
		String value = (info[1].startsWith("1.3.6")
			? getObjectTypeDescriptionByIdentifier(info[1]).getDescription() : info[1]);

		if (description != null && value != null) {

		    // It sends the record with Ingestor
		    GenericRecord record = new GenericData.Record(schema);
		    record.put("hostname", member.getHostname());
		    record.put("grid_master", member.getGridMaster());
		    record.put("metric", description);
		    record.put("index", index);
		    record.put("value", value);
		    record.put("timestamp", String.valueOf(System.currentTimeMillis()));
		    record.put("extraInfo", new HashMap<String, String>());

		    ingestor.ingest("Infoblox", record);
		}
	    }

	}
    }

    private String resolveData(String oid, Boolean hasIndex) {

	String result;
	if (hasIndex) {
	    result = pattern.matcher(oid).find() ? "0" : oid.substring(oid.lastIndexOf(".") + 1);
	} else {
	    result = pattern.matcher(oid).find() ? getObjectTypeDescriptionByIdentifier(oid).getDescription()
		    : getObjectTypeDescriptionByIdentifier(oid.substring(0, oid.lastIndexOf("."))).getDescription();
	}
	return result;
    }

    private ObjectTypeDescription getObjecTypeDescriptionByName(String name) {

	ObjectTypeDescription mib = objectTypesByName.get(name);
	if (mib == null) {
	    LOGGER.warn("Oid {} is missing!", name);
	    return null;
	} else
	    return mib;
    }

    private ObjectTypeDescription getObjectTypeDescriptionByIdentifier(String oid) {

	ObjectTypeDescription mib = objectTypesByIdentifier.get(oid);
	if (mib == null) {
	    LOGGER.warn("Oid {} is missing!", oid);
	    return null;
	} else
	    return mib;
    }
}
