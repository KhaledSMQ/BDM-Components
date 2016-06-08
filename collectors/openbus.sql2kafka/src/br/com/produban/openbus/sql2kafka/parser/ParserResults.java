package br.com.produban.openbus.sql2kafka.parser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.Schema.Field;
import org.apache.avro.specific.SpecificRecordBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.produban.openbus.sql2kafka.util.properties.CollectProperties;
import br.com.produban.openbus.sql2kafka.util.properties.Sql2KafkaProperties;

public class ParserResults {

	private static Logger LOG = LoggerFactory.getLogger(ParserResults.class);

	public static List<SpecificRecordBase> parse(ResultSet resultSet, CollectProperties collectProperties,
			Sql2KafkaProperties sql2KafkaProperties) {
		List<SpecificRecordBase> metricsToSend = new ArrayList<SpecificRecordBase>();
		try {
			 Class<?> schemaClass = Class.forName(
					collectProperties.getAvroSchemaClass());

			while (resultSet.next()) {
				SpecificRecordBase specificRecordBase = (SpecificRecordBase) schemaClass.newInstance();
				
				for (int c = 1; c <= resultSet.getMetaData().getColumnCount(); c++) {
					String fieldName = resultSet.getMetaData().getColumnName(c);
					if (specificRecordBase.getSchema().getField(fieldName) == null)
						continue;

					Object value = null;
					switch (specificRecordBase.getClass().getDeclaredField(fieldName).getType().getCanonicalName()) {
					case "java.lang.Boolean":
						value = resultSet.getBoolean(fieldName);
						break;
					case "java.lang.Integer":
						value = resultSet.getInt(fieldName);
						break;
					case "java.lang.Long":
						value = resultSet.getLong(fieldName);
						break;
					case "java.lang.Float":
						value = resultSet.getFloat(fieldName);
						break;
					case "java.lang.Double":
						value = resultSet.getDouble(fieldName);
						break;
					case "java.lang.Byte":
						value = resultSet.getByte(fieldName);
						break;
					case "java.lang.String":
						value = resultSet.getString(fieldName);
						break;
					default:
						value = resultSet.getString(fieldName);
						break;
					}

					specificRecordBase.put(fieldName, value);
				}
				
				if (sql2KafkaProperties.getCollectorListResultsExit().equals("true")) {
					for (Field field : specificRecordBase.getSchema().getFields())
						LOG.info(field.name() + ": " + specificRecordBase.get(field.pos()));
				}
				
				metricsToSend.add(specificRecordBase);
			}

			if (sql2KafkaProperties.getCollectorListResultsExit().equals("true")) {
				System.exit(0);
			}

		} catch (SQLException e) {
			LOG.error("Error parsing results.", e);
		} catch (InstantiationException e) {
			LOG.error("Error parsing results.", e);
		} catch (IllegalAccessException e) {
			LOG.error("Error parsing results.", e);
		} catch (ClassNotFoundException e) {
			LOG.error("Error parsing results.", e);
		} catch (SecurityException e) {
			LOG.error("Error parsing results.", e);
		} catch (NoSuchFieldException e) {
			LOG.error("Error parsing results.", e);
		}
		return metricsToSend;
	}
}
