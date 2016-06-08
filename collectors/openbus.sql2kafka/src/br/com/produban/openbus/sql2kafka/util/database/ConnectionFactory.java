package br.com.produban.openbus.sql2kafka.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.produban.openbus.sql2kafka.util.properties.CollectProperties;

public class ConnectionFactory {

	private static Logger LOG = LoggerFactory.getLogger(ConnectionFactory.class);

	private static ConcurrentHashMap<Long, Connection> connectionMap = new ConcurrentHashMap<Long, Connection>();

	public static Connection getConnection(CollectProperties collectProperties) {

		long id = getUniqId(collectProperties.getJdbcDriverClass(), collectProperties.getJdbcConnectionString());

		try {
			Connection connection = connectionMap.get(id);
			if (connection == null || !connection.isValid(5)) {
				String user = collectProperties.getJdbcConnectionUser();
				String password = collectProperties.getJdbcConnectionPassword();
				if (user.isEmpty() && password.isEmpty()) {
					connection = DriverManager.getConnection(collectProperties.getJdbcConnectionString());
				} else {
					connection = DriverManager.getConnection(collectProperties.getJdbcConnectionString(), user,
							password);
				}
				connection.setReadOnly(true);
				connectionMap.put(id, connection);
			}
			return connection;
		} catch (Exception e) {
			LOG.debug("Error in connection factory.", e);
		}
		return null;
	}

	private static long getUniqId(String driverClass, String jdbcConnectionString) {
		byte bytes[] = (driverClass + jdbcConnectionString).getBytes();
		Checksum checksum = new CRC32();
		checksum.update(bytes, 0, bytes.length);
		return checksum.getValue();
	}
}
