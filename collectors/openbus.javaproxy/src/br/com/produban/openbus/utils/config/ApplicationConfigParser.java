package br.com.produban.openbus.utils.config;

import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;

import com.typesafe.config.Config;

public class ApplicationConfigParser {

	public static final String APP_CONFIG_ROOT = "openbus";
	public static final String APP_CONFIG_KAFKA = "kafkaConfig";
	public static final String APP_CONFIG_REDIS = "redisConfig";

	public static ApplicationConfig parser(Config config) throws IOException {

		Config kafkaConf = config.getConfig(APP_CONFIG_ROOT).getConfig(APP_CONFIG_KAFKA);
		Config jedisConf = config.getConfig(APP_CONFIG_ROOT).getConfig(APP_CONFIG_REDIS);

		final Schema schema = loadSchema(config);
		final String redisHost = jedisConf.getString("host");
		final int redisPort = jedisConf.getInt("port");
		final String redisUserInfo = jedisConf.getString("userInfo");
		final String kafkaBrokerList = kafkaConf.getString("brokerList");
		final String kafkaSuccessTopic = kafkaConf.getString("successTopic");
		final String kafkaFailureTopic = kafkaConf.getString("failureTopic");
		final boolean kafkaRequiredAcks = kafkaConf.getBoolean("requiredAcks");
		final boolean kafkaIsAsync = kafkaConf.getBoolean("isAsync");
		final int kafkaBatchNumMessages = kafkaConf.getInt("batchNumMessages");

		return new ApplicationConfig(schema, redisHost, redisPort, redisUserInfo, kafkaBrokerList, kafkaSuccessTopic,
				kafkaFailureTopic, kafkaRequiredAcks, kafkaIsAsync, kafkaBatchNumMessages);
	}

	private static Schema loadSchema(Config config) throws IOException {
		String filePath = config.getConfig(APP_CONFIG_ROOT).getString("schemaFile");

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

		return new Schema.Parser().parse(file);
	}
}
