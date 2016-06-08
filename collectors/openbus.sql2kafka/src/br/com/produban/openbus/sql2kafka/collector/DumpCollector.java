package br.com.produban.openbus.sql2kafka.collector;

import java.sql.ResultSet;

import org.apache.velocity.VelocityContext;

public class DumpCollector extends CollectorJob {

	@Override
	protected void prepareQuery(VelocityContext velocityContext) {
	}

	@Override
	protected void postQuery(ResultSet resultSet) {
	}

}
