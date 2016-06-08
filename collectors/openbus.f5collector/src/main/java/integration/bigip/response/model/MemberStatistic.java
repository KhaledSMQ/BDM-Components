package integration.bigip.response.model;

public class MemberStatistic {

    private String metricName;
    private String metricHighValue;
    private String metricLowValue;

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getMetricHighValue() {
        return metricHighValue;
    }

    public void setMetricHighValue(String metricHighValue) {
        this.metricHighValue = metricHighValue;
    }

    public String getMetricLowValue() {
        return metricLowValue;
    }

    public void setMetricLowValue(String metricLowValue) {
        this.metricLowValue = metricLowValue;
    }

    @Override
    public String toString() {
        return "MemberStatistic{" +
                "metricName='" + metricName + '\'' +
                ", metricHighValue='" + metricHighValue + '\'' +
                ", metricLowValue='" + metricLowValue + '\'' +
                '}';
    }
}
