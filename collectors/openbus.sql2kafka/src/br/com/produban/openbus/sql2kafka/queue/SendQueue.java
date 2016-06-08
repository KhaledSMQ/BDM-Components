package br.com.produban.openbus.sql2kafka.queue;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sleepycat.je.*;
import org.apache.avro.specific.SpecificRecordBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.produban.openbus.sql2kafka.queue.serialization.Serializer;

public class SendQueue {

	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	private Database avroDB;
	private Environment dbEnv;
	private CursorConfig cursorConfig;
	private Serializer serializer;
	private String collectName;

	public SendQueue(String collectName, String collectorName, Serializer serializer) {
		EnvironmentConfig envConfig = new EnvironmentConfig();
		envConfig.setAllowCreate(true);
		envConfig.setDurability(Durability.COMMIT_NO_SYNC);
		dbEnv = new Environment(new File(System.getProperty("user.dir") + "/db/" + collectorName), envConfig);
		EnvironmentMutableConfig mutableConfig = dbEnv.getMutableConfig();
		mutableConfig.setConfigParam(EnvironmentConfig.LOG_FILE_MAX, Long.toString(1024 * 1024));
		dbEnv.setMutableConfig(mutableConfig);

		DatabaseConfig dbconf = new DatabaseConfig();
		dbconf.setAllowCreate(true);
		dbconf.setSortedDuplicates(false);
		dbconf.setDeferredWrite(true);
		avroDB = dbEnv.openDatabase(null, "sql2kafkaDB_avro." + collectName, dbconf);

		cursorConfig = new CursorConfig();
		cursorConfig.setNonSticky(true);
		cursorConfig.setReadCommitted(true);
		this.serializer = serializer;
		this.collectName = collectName;
	}

	public boolean saveSpecificRecordBaseList(List<SpecificRecordBase> specificRecordBaseList) {
		Cursor cursor = null;
		boolean status = true;
		try {
			DatabaseEntry data = new DatabaseEntry();
			DatabaseEntry key = new DatabaseEntry();
			cursor = avroDB.openCursor(null, cursorConfig);
			cursor.getLast(key, data, LockMode.RMW);

			BigInteger prevKeyValue;
			if (key.getData() == null) {
				prevKeyValue = BigInteger.valueOf(-1);
			} else {
				prevKeyValue = new BigInteger(key.getData());
			}
			BigInteger newKeyValue = prevKeyValue.add(BigInteger.ONE);
			
			Set<DatabaseEntry> compensateSet = new HashSet<>();
			for (SpecificRecordBase specificRecordBase : specificRecordBaseList) {
				data = new DatabaseEntry(serializer.serialize(specificRecordBase));
				key = new DatabaseEntry(newKeyValue.toByteArray());
				newKeyValue = newKeyValue.add(BigInteger.ONE);

				LOG.debug("Inserting key: " + Arrays.toString(key.getData()) + " for " + collectName);

				if (avroDB.put(null, key, data) != OperationStatus.SUCCESS) {
					status = false;
					compensateSet.add(key);
				}
			}
			
			for (DatabaseEntry compensateKey : compensateSet) {
				avroDB.delete(null, compensateKey);
			}

		} catch (UnsupportedEncodingException e) {
			LOG.error("Error saving records.", e);
		} catch (IOException e) {
			LOG.error("Error saving records.", e);
		} catch (DatabaseException e) {
			LOG.error("Error saving records.", e);
			status = false;
		}finally {
			if (cursor != null)
				cursor.close();
			avroDB.sync();
		}
		return status;
	}

	public List<SpecificRecordBase> getSpecificRecordBaseList() {
		List<SpecificRecordBase> specificRecordBaseList = new ArrayList<>();
		if (avroDB.count() == 0)
			return specificRecordBaseList;

		Cursor cursor = null;
		try {
			cursor = avroDB.openCursor(null, cursorConfig);

			DatabaseEntry data = new DatabaseEntry();
			DatabaseEntry key = new DatabaseEntry();

			while (cursor.getNext(key, data, LockMode.RMW) == OperationStatus.SUCCESS) {
				SpecificRecordBase specificRecordBase = serializer.deserialize(data.getData());
				if (specificRecordBase != null) {
					specificRecordBaseList.add(specificRecordBase);
					cursor.delete();
				}
			}

		} catch (UnsupportedEncodingException e) {
			LOG.error("Error retrieving records.", e);
		} catch (ClassNotFoundException e) {
			LOG.error("Error retrieving records.", e);
		} catch (IOException e) {
			LOG.error("Error retrieving records.", e);
		} finally {
			if (cursor != null) {
				cursor.close();
				avroDB.sync();
			}
		}
		return specificRecordBaseList;
	}

	public void close() {
		avroDB.close();
		dbEnv.close();
	}

}
