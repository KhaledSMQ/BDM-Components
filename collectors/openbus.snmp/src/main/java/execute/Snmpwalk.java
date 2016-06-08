package execute;

import org.slf4j.*;
import org.snmp4j.*;
import org.snmp4j.mp.*;
import org.snmp4j.smi.*;
import org.snmp4j.transport.*;
import org.snmp4j.util.*;

import java.io.*;
import java.util.*;

public class Snmpwalk {

    private static final Logger LOGGER = LoggerFactory.getLogger(Snmpwalk.class);

    public String targetAddr;
    public String oidStr;
    public String commStr;
    private int snmpVersion;
    private String portNum;
    private String results = "";
    private static String identifier;
    private static String index;
    private static String value;
    private static String usage = "Usage: Snmpwalk IP OID COMMUNITY";

    public Snmpwalk() {
        // Set default value.
        targetAddr = null;
        oidStr = null;
        commStr = "bel2ptv7";

        snmpVersion = SnmpConstants.version2c;
        portNum = "161";
    }

    public Snmpwalk(String targetAddr, String oidStr, String commStr) {
        this.targetAddr = targetAddr;
        this.oidStr = oidStr;
        this.commStr = commStr;
        this.snmpVersion = SnmpConstants.version2c;
        this.portNum = "161";
    }

    public Snmpwalk(String targetAddr, String oidStr) {
        this.targetAddr = targetAddr;
        this.oidStr = oidStr;
        this.commStr = "bel2ptv7";
        this.snmpVersion = SnmpConstants.version2c;
        this.portNum = "161";
    }

    public Snmpwalk(HashMap<String, String> values) {
        targetAddr = values.get("ipaddr");
        oidStr = values.get("oid");
        commStr = values.get("communityString");
        int tempVersion = Integer.parseInt(values.get("version"));
        if (tempVersion == 1) {
            snmpVersion = SnmpConstants.version1;
        }
        if (tempVersion == 2) {
            snmpVersion = SnmpConstants.version2c;
        }
        if (tempVersion == 3) {
            snmpVersion = SnmpConstants.version3;
        }
        portNum = "161";
    }

    public String doSnmpwalk() throws IOException {
        Snmp snmp = null;
        TransportMapping transport = null;
        try {
            Address targetAddress = GenericAddress.parse("udp:" + targetAddr + "/" + portNum);
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            transport.listen();

            // setting up target
            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new OctetString(commStr));
            target.setAddress(targetAddress);
            target.setRetries(3);
            target.setTimeout(1000 * 3);
            target.setVersion(snmpVersion);

            OID oid = new OID(oidStr);

            TreeUtils treeUtils = new TreeUtils(snmp, new DefaultPDUFactory());
            List<TreeEvent> events = treeUtils.getSubtree(target, oid);
            if (events == null || events.size() == 0) {
                return null;
            }

            // Get snmpwalk result.
            for (TreeEvent event : events) {
                if (event.isError()) {
                    LOGGER.error("OID ({}) on server {} has an error: {}", oidStr,targetAddr, event.getErrorMessage());
                } else {
                    VariableBinding[] varBindings = event.getVariableBindings();
                    if (varBindings == null || varBindings.length == 0) {
                        //System.out.println("VarBinding: No result returned.");
                    }
                    for (VariableBinding varBinding : varBindings) {
                        varBinding.toValueString();
                        results += (varBinding.getOid().toString() + "=" + varBinding.getVariable().toString()) + "\n";
                    }
                }
            }
        } finally {
            if (snmp!=null) snmp.close();
            if (transport!=null) transport.close();
        }

        return results;
    }

}