package br.com.produban.openbus.sql2kafka.collector;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.produban.openbus.sql2kafka.collector.store.StatePersister;
import br.com.produban.openbus.sql2kafka.parser.ParserResults;
import br.com.produban.openbus.sql2kafka.queue.SendQueue;
import br.com.produban.openbus.sql2kafka.util.database.ConnectionFactory;
import br.com.produban.openbus.sql2kafka.util.properties.CollectProperties;
import br.com.produban.openbus.sql2kafka.util.properties.Sql2KafkaProperties;

public abstract class CollectorJob implements Job {

	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	protected Sql2KafkaProperties sql2KafkaProperties;
	protected CollectProperties collectProperties;
	protected SendQueue queues;
	protected StatePersister statePersister;

	protected JobExecutionContext jobExecutionContext;

	private Template template;
	private VelocityContext velocityContext;

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		try {
			sql2KafkaProperties = (Sql2KafkaProperties) jobExecutionContext.getJobDetail().getJobDataMap()
					.get("sql2KafkaProperties");
			collectProperties = (CollectProperties) jobExecutionContext.getJobDetail().getJobDataMap()
					.get("collectProperties");
			queues = (SendQueue) jobExecutionContext.getJobDetail().getJobDataMap().get("sendQueue");
			statePersister = (StatePersister) jobExecutionContext.getJobDetail().getJobDataMap().get("statePersister");

			this.jobExecutionContext = jobExecutionContext;

			Connection connection = null;
			ResultSet resultSet = null;

			if (template == null) {
				initializeTemplate();
			}
			if (velocityContext == null) {
				velocityContext = new VelocityContext();
			}

			try {
				connection = ConnectionFactory.getConnection(collectProperties);
				
				if (connection == null) {
					LOG.error("Connection is null. Check the route to the specified server.");
					return;
				}
				
				prepareQuery(velocityContext);

				StringWriter writer = new StringWriter();
				template.merge(velocityContext, writer);

				String query = writer.toString();

				if (query != null) {
					resultSet = select(connection, query);
				}

				List<SpecificRecordBase> specificRecordBaseList = ParserResults.parse(resultSet, collectProperties, sql2KafkaProperties);
				
				if (queues.saveSpecificRecordBaseList(specificRecordBaseList)) {
					resultSet.beforeFirst();
					postQuery(resultSet);					
				}
				
			} catch (Exception e) {
				LOG.error("Error collecting metrics.", e);
			} finally {
				try {
					if (resultSet != null)
						resultSet.close();
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					LOG.error("Error closing resultset/connection.", e);
				}
			}

		} catch (Exception e) {
			LOG.error("Error in executor.", e);
			System.exit(0);
		}
	}

	private void initializeTemplate() {
		VelocityEngine velocityEngine = new VelocityEngine();

		Properties p = new Properties();
		p.setProperty("resource.loader", "file");
		p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
		p.setProperty("file.resource.loader.path", "/");
		p.setProperty("file.resource.loader.cache", "true");
		p.setProperty("file.resource.loader.modificationCheckInterval", "0");

		velocityEngine.init(p);

		template = velocityEngine.getTemplate(collectProperties.getSqlQueryFile());
	}

	private ResultSet select(Connection connection, String selectStatement) {

		Statement statement = null;

		ResultSet resultSet = null;
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			LOG.info("Running query:\n" + selectStatement);

			resultSet = statement.executeQuery(selectStatement);

			if (resultSet.last()) {
				LOG.info("Query fetched: " + resultSet.getRow() + " results.");
				resultSet.beforeFirst();
			}

		} catch (SQLException e) {
			LOG.error("Error selecting metrics.", e);
		} finally {
			// try {
			// statement.close();
			// } catch (SQLException e) {
			// LOG.error("Error closing connection", e);
			// }
		}

		return resultSet;
	}

	protected String getLastPersistedOffset() {
		return statePersister.get(jobExecutionContext.getJobDetail().getKey().getName() + ".STATE_PERSISTER");
	}

	protected void persistLastOffset(String lastOffset) {
		statePersister.save(jobExecutionContext.getJobDetail().getKey().getName() + ".STATE_PERSISTER", lastOffset);
	}

	protected abstract void prepareQuery(VelocityContext velocityContext);

	protected abstract void postQuery(ResultSet resultSet);

}
