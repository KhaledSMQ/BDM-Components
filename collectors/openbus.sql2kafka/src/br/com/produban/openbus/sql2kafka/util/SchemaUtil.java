package br.com.produban.openbus.sql2kafka.util;

import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchemaUtil {

	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	private Schema schema;
	
	private static String filePath;
	private static SchemaUtil instance;

	private SchemaUtil() {
	}

	public static synchronized SchemaUtil getInstance(String path) {
		if (instance == null){
			instance = new SchemaUtil();
			
			filePath = path;
		}

		return instance;
	}
	
	public Schema getSchema(){
		if(this.schema == null){
			loadSchema();
		}
		
		return this.schema;
	}

	private void loadSchema() {
		if(filePath == null){
			throw new IllegalStateException("Schema file is required");
		}
		else if(!filePath.endsWith(".avsc")){
			throw new IllegalStateException("Schema file is invalid");
		}

		File file = new File(filePath);

		if(!file.exists()){
			throw new IllegalStateException("File not found");
		}

		try{
			schema = new Schema.Parser().parse(file);
		}
		catch(IOException e){
			LOG.error("Invalid avsc file.", e);
		}
	}
}
