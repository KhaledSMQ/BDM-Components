package br.com.produban.openbus.introscope.util.parser;

import br.com.produban.openbus.introscope.core.IntroscopeSimpleJob;
import br.com.produban.openbus.introscope.util.properties.ResourceUtils.Constants;
import br.com.produban.openbus.model.avro.Introscope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.LinkedBlockingQueue;

public class ParserMetricName extends IntroscopeSimpleJob {

	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	private Long formatTimestamp(String timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		sdf.setTimeZone(TimeZone.getDefault());
		Long tsLong = 0L;
		try {
			tsLong = sdf.parse(timestamp).getTime() / 1000;
		} catch (ParseException e) {
			LOG.error("Error parsing date.",e);
		}
		return tsLong;
	}

	public void execute() {
		LinkedBlockingQueue<ResultSet> inputResult = queues.getResultSets();
		LinkedBlockingQueue<Introscope> outputResult = queues.getOutputQueue();

		List<ResultSet> resultSets = new ArrayList<>();

		if (!inputResult.isEmpty()) {
			inputResult.drainTo(resultSets);

			try {
				for (ResultSet resultSet : resultSets) {

					HashMap<String, List<Introscope>> metricsToCalculate = new HashMap<>();

					while (resultSet.next()) {
						Introscope introscope = new Introscope();

						introscope.setHost(resultSet.getString("Host"));
						introscope.setDomain(resultSet.getString("Domain"));
						introscope.setResource(resultSet.getString("Resource").replaceAll("[\\-\\|\\@]", "."));
						introscope.setAgentName(resultSet.getString("AgentName"));
						introscope.setMetricName(resultSet.getString("MetricName"));
						introscope.setValue(resultSet.getString("Value"));

						Long timestamp = formatTimestamp(resultSet.getString("Actual_End_Timestamp"));
						introscope.setTimestamp(timestamp.toString());


						String uniqueKey = introscope.getHost() + introscope.getDomain()
								+ introscope.getAgentName() + introscope.getResource()
								+ introscope.getMetricName();

						if (!metricsToCalculate.containsKey(uniqueKey)) {
							ArrayList<Introscope> list = new ArrayList<>();
							list.add(introscope);
							metricsToCalculate.put(uniqueKey, list);
						} else {
							metricsToCalculate.get(uniqueKey).add(introscope);
						}

						if (resourceUtils.getProperty(Constants.INTROSCOPE_LIST_METRICS).equals("true")) {
							LOG.info("Hostname: " + introscope.getHost());
							LOG.info("Domain: " + introscope.getDomain());
							LOG.info("Resource: " + introscope.getResource());
							LOG.info("Agent: " + introscope.getAgentName());
							LOG.info("Metric: " + introscope.getMetricName());
							LOG.info("Value: " + introscope.getValue());
							LOG.info("Timestamp: " + introscope.getTimestamp() + "\n");
						}

					}

					if (resourceUtils.getProperty(Constants.INTROSCOPE_LIST_METRICS).equals("true")) {
						System.exit(0);
					}

					List<Introscope> metricsToSend = new ArrayList<>();

					for (String uniq : metricsToCalculate.keySet()) {
						List<Introscope> list = metricsToCalculate.get(uniq);
						String[] listAvg = resourceUtils.getProperty(Constants.INTROSCOPE_METRICS_AGGREGATOR_AVG)
								.split(";");
						String[] listSum = resourceUtils.getProperty(Constants.INTROSCOPE_METRICS_AGGREGATOR_SUM)
								.split(";");
						for (String metricAvg : listAvg) {
							if (uniq.contains(metricAvg)) {
								Long sum = 0L;
								for (Introscope introscope : list) {
									sum += Long.valueOf(introscope.getValue());
								}
								Long avg = sum / list.size();

								Introscope beanToSend = list.get(list.size()-1);
								beanToSend.setValue(avg.toString());

								metricsToSend.add(beanToSend);
							}
						}
						for (String metricSum : listSum) {
							if (uniq.contains(metricSum)) {
								Long sum = 0L;
								for (Introscope introscope : list) {
									sum += Long.valueOf(introscope.getValue());
								}
								Introscope beanToSend = list.get(list.size()-1);
								beanToSend.setValue(sum.toString());

								metricsToSend.add(beanToSend);
							}
						}

					}

					outputResult.addAll(metricsToSend);

				}

			} catch (SQLException e) {
				LOG.error("Error parsing metrics.", e);
			}

		}
	}
}
