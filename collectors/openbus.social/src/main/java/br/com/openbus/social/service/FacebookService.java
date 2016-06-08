package br.com.openbus.social.service;

import java.io.IOException;
import java.util.HashMap;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.openbus.social.integration.FacebookIntegrationService;
import br.com.produban.openbus.ingestor.OpenbusDataIngestion;
import facebook4j.User;

@Component
public class FacebookService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FacebookService.class);

	@Autowired
	private OpenbusDataIngestion ingestor;

	@Autowired
	private FacebookIntegrationService facebookIntegrationService;

	@Autowired
	private Schema facebookSchema;

	@Async
	public void sendToIngestor(String token) {
		try {
			User user = facebookIntegrationService.getUserByToken(token);

			if (isValidFacebookUser(token, user)) {
				GenericRecord userRecord = createUserRecord(user);
				ingestor.ingest("Social", userRecord);
			}
		} catch (Exception e) {
			LOGGER.error("Error processing Token= " + token, e);
		}
	}

	public boolean isValidFacebookUser(String token, User user) {
		if (user == null) {
			LOGGER.warn("Facebook User without information for token=" + token);
			return false;
		} else if (user != null && user.getEmail() == null) {
			LOGGER.warn("Facebook user without email for token=" + token);
			return false;
		} else {
			return true;
		}
	}

	private GenericRecord createUserRecord(User user) throws IOException {
		GenericRecord record = new GenericData.Record(facebookSchema);

		record.put("socialId", user.getId());
		record.put("socialName", "Facebook");
		record.put("email", user.getEmail());
		record.put("name", user.getName());
		record.put("firstName", user.getFirstName());
		record.put("lastName", user.getLastName());

		if (user.getAgeRange() != null)
			record.put("ageRange", user.getAgeRange().getMin() + "-" + user.getAgeRange().getMax());

		record.put("link", (user.getLink() != null ? user.getLink().toString() : null));
		record.put("gender", user.getGender());
		record.put("timestamp", String.valueOf(System.currentTimeMillis()));
		record.put("extraInfo", new HashMap<String, String>());

		return record;
	}

}
