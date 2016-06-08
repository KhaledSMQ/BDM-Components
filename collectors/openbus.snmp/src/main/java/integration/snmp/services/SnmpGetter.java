package integration.snmp.services;

import com.typesafe.config.*;
import exceptions.*;
import execute.*;
import integration.snmp.model.*;
import org.slf4j.*;
import org.springframework.beans.factory.config.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;
import transform.*;

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SnmpGetter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SnmpGetter.class);
    private static Pattern pattern = Pattern.compile(ConfigFactory.load().getString("regex.exceptions").replace(",", "|"));

    public List<SnmpModel> getMetricsFor(String ip, List<? extends Config> queryList) throws ResponseParsingException, IOException {
        List<SnmpModel> resultList = new ArrayList<>();

        LOGGER.debug(MessageFormat.format("IP: {0} - Query list: {1}", ip, queryList));

        Map<String,String> infoMap = new HashMap<>();

        for (Config query : queryList) {
            try {
                Snmpwalk info = new Snmpwalk(ip, query.getString("oid"));
                String file = info.doSnmpwalk();

                Scanner sc = new Scanner(file);

                boolean isHeader = query.hasPath("header");

                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] lf = line.split("=");

                    if (lf.length == 2) {
                        String identifier = resolveData(lf[0],false); //pattern.matcher(lf[0]).find()?resolveOid(lf[0]):resolveOid(lf[0].substring(0, lf[0].lastIndexOf(".")));
                        String index = resolveData(lf[0], true);      //pattern.matcher(lf[0]).find()? "0" :lf[0].substring(lf[0].lastIndexOf("."));
                        String value = (lf[1].startsWith("1.3.6") ? resolveOid(lf[1]) : lf[1]);

                        if (identifier != null && value != null) {
                            if (isHeader) {
                                infoMap.put(identifier, value);
                            } else {
                                SnmpModel model = new SnmpModel();
                                model.setInfo(infoMap);

                                model.setId(index.replace(".", ""));
                                model.setPropertyName(identifier);
                                model.setPropertyValue(value);

                                resultList.add(model);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw new ResponseParsingException(
                        MessageFormat.format("Fail to parse response from ip {0}",ip),e);
            }
        }
        return resultList;
    }

    private String resolveOid(String oid) throws IOException, ResponseParsingException {
        String mib = Decoder.getInstance().getPropKey(oid);
        if (mib == null) {
            LOGGER.warn("Oid {} is missing!", oid);
            return null;
        }
        else return mib;
    }

    private String resolveData(String oid, Boolean index) throws IOException, ResponseParsingException {
        String result;
        if (index) {
            result = pattern.matcher(oid).find()? "0": oid.substring(oid.lastIndexOf("."));
        } else {
            result = pattern.matcher(oid).find()?resolveOid(oid):resolveOid(oid.substring(0, oid.lastIndexOf(".")));
        }
        return result;
    }
}