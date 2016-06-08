package br.com.produban.openbus.sql2kafka.collector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.produban.openbus.sql2kafka.util.properties.Constants;

public class DeltaTimeCollector extends CollectorJob {

	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void prepareQuery(VelocityContext velocityContext) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(collectProperties.getTimeDateFormat());

		try {
			String jobPreviousFireTime = getLastPersistedOffset();
			Date jobFireTime = jobExecutionContext.getFireTime();

			Date startTime = null;
			if (jobPreviousFireTime != null) {
				startTime = plusOne(dateFormat.parse(jobPreviousFireTime));
			} else if (!collectProperties.getInitialValue().isEmpty()) {
				startTime = dateFormat.parse(collectProperties.getInitialValue());
			} else {
				startTime = new Date(0L);
			}

			// Waits one minute
			Date stopTime = minusOneMinute(jobFireTime);

			velocityContext.put(Constants.SQL_QUERY_PARAMETER_START_TIME, dateFormat.format(startTime));
			velocityContext.put(Constants.SQL_QUERY_PARAMETER_STOP_TIME, dateFormat.format(stopTime));

		} catch (ParseException e) {
			LOG.error("Error parsing initial date.", e);
		}
	}

	@Override
	protected void postQuery(ResultSet resultSet) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(collectProperties.getTimeDateFormat());

		try {
			Date lastTime = new Date(0L);
			while (resultSet.next()) {
				Date resultOffset = dateFormat.parse(resultSet.getString(collectProperties.getOffsetColumnName()));
				if (resultOffset.after(lastTime)) {
					lastTime = resultOffset;
				}
			}
			if (lastTime.compareTo(new Date(0L)) != 0)
				persistLastOffset(dateFormat.format(lastTime));

		} catch (SQLException e) {
			LOG.error("Error while executing post query logic. Probably your query do not return any column called: "
					+ collectProperties.getOffsetColumnName(), e);
		} catch (ParseException e) {
			LOG.error("Error parsing last collected date.", e);
		}
	}

	private Date plusOne(Date date) {
		String dateFormat = collectProperties.getTimeDateFormat();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (dateFormat.contains("S"))
			calendar.add(Calendar.MILLISECOND, 1);
		else if (dateFormat.contains("s"))
			calendar.add(Calendar.SECOND, 1);
		else //if (dateFormat.contains("m"))
			calendar.add(Calendar.MINUTE, 1);
		return calendar.getTime();
	}

	private Date minusOneMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, -1);
		return calendar.getTime();
	}

}
