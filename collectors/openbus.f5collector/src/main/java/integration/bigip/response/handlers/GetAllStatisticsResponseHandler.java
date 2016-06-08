package integration.bigip.response.handlers;

import integration.bigip.response.model.Pool;
import integration.bigip.response.model.PoolMember;
import integration.bigip.response.model.MemberStatistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public class GetAllStatisticsResponseHandler extends DefaultHandler {

    private final List<String> statisticWhiteList;
    private List<String> pools;

    private List<Pool> response = new ArrayList<>();

    private List<PoolMember> poolMemberList;
    private PoolMember poolMember = null;
    private MemberStatistic metric = null;

    boolean memberStatisticsInProgress = false;
    boolean memberAddressReached = false;
    boolean memberPortReached = false;
    boolean memberStatisticReached = false;
    boolean memberHighValueReached = false;
    boolean memberLowValueReached = false;

    private int currentPoolIndex = 0;

    public GetAllStatisticsResponseHandler(List<String> pools, List<String> statisticWhiteList) {
        this.statisticWhiteList = statisticWhiteList;
        this.pools = pools;
    }

    public List<Pool> getResponse() {
        return response;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        switch (qName) {
            case "member" : {
                poolMember = new PoolMember();
                break;
            }
            case "statistics" : {
                if (attributes.getValue("A:arrayType").contains("Common.Statistic")) {
                    memberStatisticsInProgress = true;
                }
                if (attributes.getValue("A:arrayType").contains("MemberStatisticEntry")) {
                    poolMemberList = new ArrayList<>();
                }
                break;
            }
            case "time_stamp" : {
                if (attributes.getValue("s:type").contains("Common.TimeStamp")) {
                    response.add(new Pool(pools.get(currentPoolIndex), poolMemberList));
                    poolMemberList = null;
                    currentPoolIndex++;
                }
                break;
            }
            case "address" :
                memberAddressReached = true;
                break;
            case "port" :
                memberPortReached = true;
                break;
            case "type" :
                memberStatisticReached = true;
                break;
            case "high" :
                memberHighValueReached = true;
                break;
            case "low" :
                memberLowValueReached = true;
                break;
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "statistics" : {
                if (memberStatisticsInProgress) {
                    poolMemberList.add(poolMember);
                    poolMember = null;
                    memberStatisticsInProgress = false;
                }
                break;
            }
            case "address" :
                memberAddressReached = false;
                break;
            case "port" :
                memberPortReached = false;
                break;
            case "high" :
                memberHighValueReached = false;
                break;
            case "low" :
                memberLowValueReached = false;
                break;
            case "value" : {
                if (memberStatisticReached) {
                    memberStatisticReached = false;

                    poolMember.getMetrics().add(metric);

                    metric = null;
                }
            }
        }
    }


    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (memberAddressReached) {
            poolMember.setMemberIp(String.copyValueOf(ch,start,length).trim());
        } else if (memberPortReached) {
            poolMember.setMemberPort(String.copyValueOf(ch,start,length).trim());
        } else if (memberStatisticReached && metric == null) {
            String metricName = String.copyValueOf(ch,start,length).trim();
            if (statisticWhiteList.contains(metricName)) {
                metric = new MemberStatistic();
                metric.setMetricName(metricName);
            } else
                memberStatisticReached = false;
        } else if (memberStatisticReached && memberHighValueReached) {
            metric.setMetricHighValue(String.copyValueOf(ch,start,length).trim());
        } else if (memberStatisticReached && memberLowValueReached) {
            metric.setMetricLowValue(String.copyValueOf(ch,start,length).trim());
        }
    }
}
