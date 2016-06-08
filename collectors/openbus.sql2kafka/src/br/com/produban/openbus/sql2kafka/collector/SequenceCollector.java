package br.com.produban.openbus.sql2kafka.collector;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.produban.openbus.sql2kafka.util.properties.Constants;

public class SequenceCollector extends CollectorJob {

	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void prepareQuery(VelocityContext velocityContext) {
		String lastOffset = getLastPersistedOffset();
		
		String offset;
		if (lastOffset != null) {
			offset = lastOffset;
		} else if (!collectProperties.getInitialValue().isEmpty()) {
			offset = collectProperties.getInitialValue();
		} else {
			offset = "0";
		}
		
		velocityContext.put(Constants.SQL_QUERY_PARAMETER_OFFSET, offset);
	}

	@Override
	protected void postQuery(ResultSet resultSet) {
		try {
			Long offset = 0L;
			while (resultSet.next()) {
				long resultOffset = resultSet.getLong(collectProperties.getOffsetColumnName());
				if (resultOffset > offset) {
					offset = resultOffset;
				}
			}
			if (offset != 0L)
				persistLastOffset(offset.toString());

		} catch (SQLException e) {
			LOG.error("Error while executing post query logic. Probably your query do not return any column called: "
					+ collectProperties.getOffsetColumnName(), e);
		}
	}

}
