package br.com.produban.openbus.cmdbsummary.configuration;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.rest.SpringCypherRestGraphDatabase;

@Configuration
public class CmdbNeo4jConfiguration extends Neo4jConfiguration {
	
	@Bean
	public GraphDatabaseService graphDatabaseService(@Value("${collector.cmdbsummary.neo4j.server}") String url, 
			@Value("${collector.cmdbsummary.neo4j.user}") String user, 
			@Value("${collector.cmdbsummary.neo4j.password}") String password) {
		return new SpringCypherRestGraphDatabase(url, user, password);
	}	

}
