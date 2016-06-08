package br.com.produban.openbus.sql2kafka.collector.store;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.Durability;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;

public class StatePersister {

	private Logger LOG = LoggerFactory.getLogger(StatePersister.class);

	private Database db;
	private Environment dbEnv;

	private static final String ENCODING = "UTF-8";

	public StatePersister(String collectorName) {
		EnvironmentConfig envConfig = new EnvironmentConfig();
		envConfig.setAllowCreate(true);
		envConfig.setDurability(Durability.COMMIT_NO_SYNC);
		dbEnv = new Environment(new File(System.getProperty("user.dir") + "/db/" + collectorName), envConfig);
		DatabaseConfig dbconf = new DatabaseConfig();
		dbconf.setAllowCreate(true);
		dbconf.setSortedDuplicates(false);
		dbconf.setDeferredWrite(true);
		db = dbEnv.openDatabase(null, "sql2kafkaDB_state", dbconf);
	}

	public boolean save(String key, String data) {
		try {
			DatabaseEntry dataValue = new DatabaseEntry(data.getBytes(ENCODING));
			DatabaseEntry keyValue = new DatabaseEntry(key.getBytes(ENCODING));
			if (db.put(null, keyValue, dataValue) == OperationStatus.SUCCESS) {
				db.sync();
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			LOG.error("Error saving state.", e);
		}
		return false;
	}

	public String get(String key) {
		String foundData = null;
		try {
			DatabaseEntry searchEntry = new DatabaseEntry();
			DatabaseEntry keyValue = new DatabaseEntry(key.getBytes(ENCODING));
			db.get(null, keyValue, searchEntry, LockMode.READ_COMMITTED);
			byte[] data = searchEntry.getData();
			if (data != null)
				foundData = new String(data, ENCODING);
		} catch (UnsupportedEncodingException e) {
			LOG.error("Error retrieving state.", e);
		}
		return foundData;
	}

	public void close() {
		db.close();
		dbEnv.close();
	}
}
