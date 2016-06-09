package br.com.openbus.infoblox.snmp;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.CommunityTarget;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TreeEvent;
import org.snmp4j.util.TreeUtils;

public class SNMPConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(SNMPConnector.class);

    private String address;
    private String communityString;

    public SNMPConnector(String address, String communityString) {
	this.address = address;
	this.communityString = communityString;
    }

    @SuppressWarnings("rawtypes")
    public String getOIDValues(OID objectIdentifier) throws IOException {

	String results = "";
	Snmp snmp = null;
	TransportMapping transport = null;
	try {

	    Address targetAddress = GenericAddress.parse(String.format("udp:%s/161", address));
	    transport = new DefaultUdpTransportMapping();
	    snmp = new Snmp(transport);
	    transport.listen();

	    CommunityTarget target = new CommunityTarget();
	    target.setCommunity(new OctetString(communityString));
	    target.setAddress(targetAddress);
	    target.setRetries(3);
	    target.setTimeout(1000 * 3);
	    target.setVersion(SnmpConstants.version2c);

	    TreeUtils treeUtils = new TreeUtils(snmp, new DefaultPDUFactory());

	    List<TreeEvent> events = treeUtils.getSubtree(target, objectIdentifier);
	    if (events == null || events.size() == 0) {
		return null;
	    }

	    for (TreeEvent event : events) {

		if (event.isError()) {

		    LOGGER.error(String.format("OID has an error : member %s, OID %s", address, objectIdentifier.toString()));
		    
		} else {

		    VariableBinding[] varBindings = event.getVariableBindings();
		    for (VariableBinding varBinding : varBindings) {
			varBinding.toValueString();

			results += (varBinding.getOid().toString() + "=" + varBinding.getVariable().toString()) + "\n";
		    }
		}
	    }

	} finally {

	    if (snmp != null)
		snmp.close();
	    if (transport != null)
		transport.close();
	}

	return results;
    }

}
