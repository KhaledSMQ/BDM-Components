package br.com.produban.openbus.rack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.produban.openbus.rack.collector.RackService;

@RestController
public class RackApplicationController {
	
	private static final Logger logger = LoggerFactory.getLogger(RackApplicationController.class);
	
	@Autowired
	private RackService rackService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody ResponseEntity<String> rackInformation(@RequestBody String body) throws Exception {
		logger.info("***** [BODY] - " + body);
		
		try {
			if (body == null || body.trim().equals("")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.contentType(MediaType.APPLICATION_JSON)
						.body("{'message':'Body is null or empty'}");
			}
			
			List<Map<String, Object>> values = new ArrayList<Map<String, Object>>();
			Gson gson = new Gson();
			values = gson.fromJson(body, values.getClass());		
			
			for (Map<String, Object> map : values) {
				if (map != null) {
					logger.info("***** [RACK] - " + map);
					rackService.sendToIngestor(map);
				}
			}
			
			logger.info("***** [TOTAL ELEMENTS] - " + values.size());
		} catch (Exception ex) {
			logger.error("***** [ERROR] - " + ex.getMessage(), ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.contentType(MediaType.APPLICATION_JSON)
					.body("{'message': 'Exception occour:" + ex.getMessage() + "'}");						
		}
		
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body("{'status':'OK'}");
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> jsonToJavaData(String json) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		
		Gson gson = new Gson();
		listMap = gson.fromJson(json, listMap.getClass());
		return listMap;
	}	

}
