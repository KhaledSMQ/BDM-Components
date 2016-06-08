package br.com.openbus.social.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.openbus.social.exception.FormException;
import br.com.openbus.social.service.FacebookService;

@RestController
@RequestMapping(path = "/facebook")
public class FacebookController {

	private static final Logger LOGGER =  LoggerFactory.getLogger(FacebookController.class);
	
	@Autowired
	private FacebookService facebookService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public void sendToIngestor(String token) throws FormException {

		if (StringUtils.isEmpty(token)) {
			LOGGER.error("Token is mandatory, " + token);
			throw new FormException("Token is mandatory");
		}

		facebookService.sendToIngestor(token);
	}

}
