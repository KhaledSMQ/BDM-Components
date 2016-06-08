package br.com.produban.openbus.introscope;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.produban.openbus.introscope.util.properties.ResourceUtils;
import br.com.produban.openbus.introscope.util.properties.ResourceUtils.Constants;


public class IntroscopeCollector {

	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private ResourceUtils resourceUtils;

	public ResourceUtils getResourceUtils() {
		return resourceUtils;
	}

	public boolean initializeAgent(String properfiesFile) {
		try {
			resourceUtils = new ResourceUtils(properfiesFile);
			LOG.info("Agent Names: " + resourceUtils.getProperty(Constants.INTROSCOPE_AGENT_NAMES));
			return true;
		} catch (Exception e) {
			LOG.error("Error initializing agent.", e);
			return false;
		}

	}

}
