package br.com.produban.openbus.rack.collector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.avro.Schema;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.gson.Gson;

import br.com.produban.openbus.ingestor.OpenbusDataIngestion;

public class RackServiceTest {

	@InjectMocks
	RackService rackService;

	@Mock
	private OpenbusDataIngestion ingestor;

	@Mock
	private Schema rackSchema;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Ignore
	@Test
	public void sendToIngestorTest() throws Exception{
		ClassLoader classLoader = getClass().getClassLoader();
		List<Map<String, Object>> values = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		String novo = IOUtils.toString(classLoader.getResourceAsStream("arquivo.json"));
		values = gson.fromJson(novo, values.getClass());		
		rackService.sendToIngestor(values.get(0));
	}
	
	@Test
	public void jsonToJavaDataTest(){
		
	}
	
	
}
