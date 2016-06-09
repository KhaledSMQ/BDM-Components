package br.com.produban.openbus.ping.collector;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.produban.openbus.ingestor.OpenbusDataIngestion;
import br.com.produban.openbus.security.exceptions.BusinessException;

@Service
public class PingCollectorService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PingCollectorService.class);

	@Autowired
	private OpenbusDataIngestion ingestor;

	@Autowired
	private Schema pingSchema;

	@Value("${ping.timeout}")
	private Integer pingTimeout;

	@Value("${ping.retry}")
	private Integer pingRetry;

	@Value("${ping.mode}")
	private String pingMode;
	
	@Async
	public void collectByIp(String ip) {
		boolean isRecheable = pingMode.equals("cmd") ? cmdPing(ip) : inetPing(ip);

		GenericRecord pingRecord = new GenericData.Record(pingSchema);
		pingRecord.put("hostname", ip);
		pingRecord.put("fqdn", ip);
		pingRecord.put("value", isRecheable ? 1 : 0);
		pingRecord.put("timestamp", String.valueOf(System.currentTimeMillis()));
		pingRecord.put("extraInfo", new HashMap<String, String>());

		try {
			ingestor.ingest("Ping", pingRecord);
		} catch (BusinessException e) {
			LOGGER.error("Error Sending to ingestor Ip= " + ip, e);
		}
	}

	private boolean inetPing(String ip) {

		int i = 0;
		while (i < pingRetry) {
			
			boolean isRecheable = false;

			try {
				InetAddress inetAddress = InetAddress.getByName(ip);
				isRecheable = inetAddress.isReachable(pingTimeout);
			} catch (Exception e) {
				LOGGER.error("Error collecting Ip= " + ip + " retry=" + (i + 1), e);
			}

			if (isRecheable) {
				return true;
			}
			i++;
		}
		return false;

	}

	private boolean cmdPing(String ip) {
		int i = 0;
		while (i < pingRetry) {
			try {
				Process p = Runtime.getRuntime().exec("ping " + ip + " -w " + pingTimeout + " -c 1");
				int code = p.waitFor();
				if (code == 0) {
					return true;
				}
			} catch (IOException | InterruptedException e) {
				LOGGER.error(
						"Error collecting Ip= " + ip + " retry=" + (i + 1), e);
			}
			i++;
		}
		return false;

	}
}
