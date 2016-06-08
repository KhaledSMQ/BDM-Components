package br.com.produban.openbus.sql2kafka.util.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.produban.openbus.sql2kafka.util.security.IllegalConfigurationException;

public class ResourceUtils {

	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	private Sql2KafkaProperties sql2KafkaProperties = new Sql2KafkaProperties();

	private ConcurrentHashMap<String, CollectProperties> collectMap = new ConcurrentHashMap<>();
	private Set<String> collectNames = new HashSet<String>();

	public ResourceUtils(String propertiesDir, String collectorName) {
		Properties sql2kafkaProperties = new Properties();
		Properties collectProperties = new Properties();

		FileInputStream in = null;
		try {
			in = new FileInputStream(new File(propertiesDir + File.separator + "sql2kafka.properties"));
			sql2kafkaProperties.load(in);
			in = new FileInputStream(new File(propertiesDir + File.separator + "collect.properties"));
			collectProperties.load(in);
		} catch (FileNotFoundException e) {
			LOG.error("Error loading properties. Make sure the specified directory contains both propertiy files: "
					+ " \n - collect.properties \n - sql2kafka.properties", e);
		} catch (Exception e) {
			LOG.error("Error loading properties.", e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				LOG.error("Error closing properties.", e);
			}
		}
		sql2KafkaProperties.setCollectorName(collectorName);
		loadSql2KafkaProperties(sql2kafkaProperties);
		
		collectNames = loadCollectProperties(collectProperties);
	}

	private void loadSql2KafkaProperties(Properties properties) {
		sql2KafkaProperties.setCollectorListResultsExit(properties.getProperty(Constants.COLLECTOR_LIST_METRICS_EXIT));
		sql2KafkaProperties.setKafkaBrokerList(properties.getProperty(Constants.KAFKA_BROKER_LIST));
		sql2KafkaProperties.setKafkaSuccessTopic(properties.getProperty(Constants.KAFKA_TOPIC_SUCCESS));
		sql2KafkaProperties.setKafkaFailureTopic(properties.getProperty(Constants.KAFKA_TOPIC_FAILURE));
		sql2KafkaProperties.setIngestorSchemaFile(properties.getProperty(Constants.INGESTOR_SCHEMA_FILE));

		if("true".equals(properties.getProperty(Constants.KAFKA_REQUIRED_ACKS))){
			sql2KafkaProperties.setKafkaRequiredAcks(true);	
		}
		else {
			sql2KafkaProperties.setKafkaRequiredAcks(false);
		}
		
		if("true".equals(properties.getProperty(Constants.KAFKA_ASYNC))){
			sql2KafkaProperties.setKafkaAsync(true);	
		}
		else {
			sql2KafkaProperties.setKafkaAsync(false);
		}
		
		sql2KafkaProperties
				.setKafkaBatchNumMessages(Integer.parseInt(properties.getProperty(Constants.KAFKA_BATCH_NUM_MESSAGES)));

		validateSql2KafkaProperties(sql2KafkaProperties);
	}

	@SuppressWarnings("unchecked")
	private Set<String> loadCollectProperties(Properties properties) {
		final Set<String> collectNames = new HashSet<String>();
		final List<String> propertySet = (List<String>) Collections.list(properties.propertyNames());
		for (final String propertyName : propertySet) {
			final int dotIndex = propertyName.indexOf('.');
			if (dotIndex == -1)
				throw new IllegalConfigurationException("Invalid property " + propertyName
						+ " should be <collect name>.<property name>");

			collectNames.add(propertyName.substring(0, dotIndex));
		}
		if (collectNames.isEmpty())
			throw new IllegalConfigurationException("Please specify at least one collect info.");

		for (final String collectName : collectNames) {
			CollectProperties collectProperties = new CollectProperties();
			collectProperties.setJdbcConnectionString(properties.getProperty(collectName + "."
					+ Constants.COLLECT_JDBC_CONNECTION_STRING));
			collectProperties.setJdbcConnectionUser(properties.getProperty(collectName + "."
					+ Constants.COLLECT_JDBC_CONNECTION_USER));
			collectProperties.setJdbcConnectionPassword(properties.getProperty(collectName + "."
					+ Constants.COLLECT_JDBC_CONNECTION_PASSWORD));
			collectProperties.setJdbcDriverClass(properties.getProperty(collectName + "."
					+ Constants.COLLECT_JDBC_DRIVER_CLASS));
			collectProperties.setType(properties.getProperty(collectName + "." + Constants.COLLECT_TYPE));
			collectProperties.setTimeDateFormat(properties.getProperty(collectName + "."
					+ Constants.COLLECT_TIME_DATE_FORMAT));
			collectProperties.setInitialValue(properties.getProperty(collectName + "."
					+ Constants.COLLECT_INITIAL_VALUE));
			collectProperties.setOffsetColumnName(properties.getProperty(collectName + "."
					+ Constants.COLLECT_OFFSET_COLUMN_NAME));
			collectProperties.setSqlQueryFile(properties.getProperty(collectName + "."
					+ Constants.COLLECT_SQL_QUERY_FILE));
			collectProperties.setAvroSchemaClass(properties.getProperty(collectName + "."
					+ Constants.COLLECT_AVRO_SCHEMA_CLASS));
            collectProperties.setTool(properties.getProperty(collectName + "." + Constants.COLLECT_TOOL));
			collectProperties.setCronEveryMin(properties.getProperty(collectName + "."
                    + Constants.COLLECT_CRON_EVERY_MIN));

			validateCollectProperties(collectProperties);

			collectMap.put(collectName, collectProperties);
		}
		return collectNames;
	}

	private void validateCollectProperties(CollectProperties collectProperties) {
		Integer collectCron = Integer.valueOf(collectProperties.getCronEveryMin());

		if (collectCron < 0 || collectCron > 59) {
			throw new IllegalConfigurationException("Collect every minute must be 0-59, in a \"crontab\" behavior.");
		}

		String collectType = collectProperties.getType();
		Set<String> validTypes = new HashSet<String>();
		for (CollectTypeEnum type : CollectTypeEnum.values()) {
			validTypes.add(type.name());
		}
		if (!validTypes.contains(collectType)) {
			throw new IllegalConfigurationException("Collect type must be one of: "
					+ CollectTypeEnum.DUMP.getDescription() + ", " + CollectTypeEnum.DELTATIME.getDescription()
					+ " or " + CollectTypeEnum.SEQUENCE.getDescription() + ".");
		}

		File sqlQueryFile = new File(collectProperties.getSqlQueryFile());
		if (!sqlQueryFile.isFile()) {
			throw new IllegalConfigurationException("The sql query file must exists, and be a normal file.");
		} else if (!sqlQueryFile.canRead()) {
			throw new IllegalConfigurationException("The sql query file is not readable by the application.");
		}

		Scanner scanner = null;
		try {
			scanner = new Scanner(sqlQueryFile);
			boolean hasStartTime = false;
			boolean hasStopTime = false;
			boolean hasOffset = false;
			while (scanner.hasNextLine()) {
				final String lineFromFile = scanner.nextLine();
				if (lineFromFile.contains("$" + Constants.SQL_QUERY_PARAMETER_START_TIME)) {
					hasStartTime = true;
				}
				if (lineFromFile.contains("$" + Constants.SQL_QUERY_PARAMETER_STOP_TIME)) {
					hasStopTime = true;
				}
				if (lineFromFile.contains("$" + Constants.SQL_QUERY_PARAMETER_OFFSET)) {
					hasOffset = true;
				}
			}
			if (CollectTypeEnum.valueOf(collectType).equals(CollectTypeEnum.DELTATIME)
					&& (!hasStartTime || !hasStopTime)) {
				throw new IllegalConfigurationException("Collect type " + CollectTypeEnum.DELTATIME.getDescription()
						+ " must has $" + Constants.SQL_QUERY_PARAMETER_START_TIME + " and $"
						+ Constants.SQL_QUERY_PARAMETER_STOP_TIME + " parameters in its query file.");
			} else if (CollectTypeEnum.valueOf(collectType).equals(CollectTypeEnum.SEQUENCE) && !hasOffset) {
				throw new IllegalConfigurationException("Collect type " + CollectTypeEnum.SEQUENCE.getDescription()
						+ " must has $" + Constants.SQL_QUERY_PARAMETER_OFFSET + "  parameter in its query file.");
			}
		} catch (FileNotFoundException e) {
			throw new IllegalConfigurationException("The sql query file do not exist.", e);
		} finally {
			scanner.close();
		}

		try {
			Class.forName(collectProperties.getJdbcDriverClass());
		} catch (ClassNotFoundException e) {
			throw new IllegalConfigurationException("JDBC Driver class is not in the classpath.", e);
		}

		if (CollectTypeEnum.valueOf(collectType).equals(CollectTypeEnum.DELTATIME)
				&& collectProperties.getTimeDateFormat().isEmpty()) {
			throw new IllegalConfigurationException("If your collect type is "
					+ CollectTypeEnum.DELTATIME.getDescription()
					+ ", specify a collect.time.date.format using the SimpleDateFormat pattern.");
		}

		if ((CollectTypeEnum.valueOf(collectType).equals(CollectTypeEnum.DELTATIME) || CollectTypeEnum.valueOf(
				collectType).equals(CollectTypeEnum.SEQUENCE))
				&& collectProperties.getOffsetColumnName().isEmpty()) {
			throw new IllegalConfigurationException("If your collect type is "
					+ CollectTypeEnum.DELTATIME.getDescription() + " or " + CollectTypeEnum.SEQUENCE.getDescription()
					+ ", specify a collect.offset.column.name regarding the database column used as offset.");
		}

        if (collectProperties.getTool().isEmpty()) {
            throw new IllegalConfigurationException("Please, specify the collect tool as the indexing id.");
        }
	}

	private void validateSql2KafkaProperties(Sql2KafkaProperties sql2KafkaProperties) {
		String[] kafkaBrokers = sql2KafkaProperties.getKafkaBrokerList().split(",");
		if (kafkaBrokers.length == 1 && kafkaBrokers[0].length() == 0) {
			throw new IllegalConfigurationException("Please especify at least one kafka broker.");
		}
		for (String kafkaBroker : kafkaBrokers) {
			String[] address = kafkaBroker.split(":");
			if (address.length != 2) {
				throw new IllegalConfigurationException("Please especify each kafka broker with pattern <host>:<port>.");
			}
		}
		
		String collectorName = sql2KafkaProperties.getCollectorName();

		String userDir = System.getProperty("user.dir");
		File dbDir = new File(userDir + "/db/" + collectorName);
		if (!dbDir.exists() && !dbDir.isDirectory()) {
			LOG.info("Creating storage directory.");
			dbDir.mkdir();
		}
	}

	public CollectProperties getCollectProperties(String collectName) {
		return collectMap.get(collectName);
	}

	public Set<String> getCollectNames() {
		return collectNames;
	}

	public Sql2KafkaProperties getSql2KafkaProperties() {
		return sql2KafkaProperties;
	}

}
