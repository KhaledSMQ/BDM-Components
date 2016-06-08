package br.com.produban.openbus.cmdbsummary.collector;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;

import br.com.produban.openbus.cmdbsummary.configuration.CmdbSummaryConfiguration;
import br.com.produban.openbus.ingestor.OpenbusDataIngestion;
import br.com.produban.openbus.security.exceptions.BusinessException;

@Service
public class CmdbSummaryService {
	
	private static final Logger logger = LoggerFactory.getLogger(CmdbSummaryService.class);
	
	@Autowired
	private Neo4jTemplate neo4jTemplate; 
	
	@Autowired
	private OpenbusDataIngestion ingestor;

	@Autowired
	private Schema logSchema;
	
	@Autowired
	private CmdbSummaryConfiguration cmdbSummaryConfiguration;
	
	private GenericRecord createGenericRecord(Map<String, Object> map, String type) throws BusinessException {
		GenericRecord record = null;
		try {
			record = new GenericData.Record(logSchema);
			record.put("name", map.get("name"));
			record.put("totalSummary", new Long(map.get("value") + ""));
			record.put("type", type);
			record.put("vdc", map.get("vdc") == null ? "" : map.get("vdc"));
			record.put("timestamp", new Date().getTime() + "");
			record.put("extraInfo", new HashMap<String, String>());
			logger.info("***** [GENERICRECORD] - " + record);
		} catch (Exception ex) {
			logger.info("***** [ERROR_CREATE_GENERICRECORD] - " + ex.getMessage(), ex);
			throw new BusinessException(ex.getMessage());
		}
		return record;
	}
	
	private List<GenericRecord> runQueryCypher(String query, String type) throws BusinessException {
		List<GenericRecord> genericRecords = new ArrayList<>();
		
		logger.info("***** [NEO4J QUERY] - " + query);
		
		Result<Map<String, Object>> result = neo4jTemplate.query(query, new HashMap<String, Object>());
		
		for (Map<String, Object> map : result) {
			genericRecords.add(createGenericRecord(map, type));
		}
		
		return genericRecords;
	}
	
	public void sendIngestor(GenericRecord genericRecord) throws BusinessException {
		ingestor.ingest("CmdbSummary", genericRecord);		
	}
	
	public void sendIngestor(List<GenericRecord> genericRecords) throws BusinessException {
		for (GenericRecord genericRecord : genericRecords) {
			ingestor.ingest("CmdbSummary", genericRecord);
		}
	}
	
	public List<GenericRecord> getTotalPlatformByVdc() throws BusinessException {
		logger.info("***** [CMDB] - Get TotalPlatformByVdc Summary");
		String query = cmdbSummaryConfiguration.getConfig().getString("logicalServer.totalPlatformByVdc.query");
		return runQueryCypher(query, CmdbSummaryEnum.TOTAL_PLATFORM_BY_VDC.getValue());
	}
	
	public List<GenericRecord> getTotalPlatformByVdcDeployed() throws BusinessException {
		logger.info("***** [CMDB] - Get TotalPlatformByVdcDeployed Summary");
		String query = cmdbSummaryConfiguration.getConfig().getString("logicalServer.totalPlatformByVdcDeployed.query");
		return runQueryCypher(query, CmdbSummaryEnum.TOTAL_PLATFORM_BY_VDC_DEPLOYED.getValue());
	}
	
	public List<GenericRecord> getTotalPlatformByVdcBeingAssembled() throws BusinessException {
		logger.info("***** [CMDB] - Get TotalPlatformByVdcBeingAssembled Summary");
		String query = cmdbSummaryConfiguration.getConfig().getString("logicalServer.totalPlatformByVdcBeingAssembled.query");
		return runQueryCypher(query, CmdbSummaryEnum.TOTAL_PLATFORM_BY_VDC_BEING_ASSEMBLED.getValue());
	}	
	
	public List<GenericRecord> getTotalPlatformByVdcDown() throws BusinessException {
		logger.info("***** [CMDB] - Get TotalPlatformByVdcDown Summary");
		String query = cmdbSummaryConfiguration.getConfig().getString("logicalServer.totalPlatformByVdcDown.query");
		return runQueryCypher(query, CmdbSummaryEnum.TOTAL_PLATFORM_BY_VDC_DOWN.getValue());
	}	
	
	public List<GenericRecord> getTotalPlatformByVdcReserved() throws BusinessException {
		logger.info("***** [CMDB] - Get TotalPlatformByVdcReserved Summary");
		String query = cmdbSummaryConfiguration.getConfig().getString("logicalServer.totalPlatformByVdcReserved.query");
		return runQueryCypher(query, CmdbSummaryEnum.TOTAL_PLATFORM_BY_VDC_RESERVERD.getValue());
	}	
	
	
}
