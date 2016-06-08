package br.com.produban.openbus.sql2kafka.util.properties;

public class CollectProperties {

	private String jdbcConnectionString;
	private String jdbcConnectionUser;
	private String jdbcConnectionPassword;
	private String jdbcDriverClass;
	private String type;
	private String timeDateFormat;
	private String initialValue;
	private String offsetColumnName;
	private String sqlQueryFile;
	private String avroSchemaClass;
	private String tool;
	private String cronEveryMin;

	public String getJdbcConnectionString() {
		return jdbcConnectionString;
	}

	public void setJdbcConnectionString(String jdbcConnectionString) {
		this.jdbcConnectionString = jdbcConnectionString;
	}

	public String getJdbcConnectionUser() {
		return jdbcConnectionUser;
	}

	public void setJdbcConnectionUser(String jdbcConnectionUser) {
		this.jdbcConnectionUser = jdbcConnectionUser;
	}

	public String getJdbcConnectionPassword() {
		return jdbcConnectionPassword;
	}

	public void setJdbcConnectionPassword(String jdbcConnectionPassword) {
		this.jdbcConnectionPassword = jdbcConnectionPassword;
	}

	public String getJdbcDriverClass() {
		return jdbcDriverClass;
	}

	public void setJdbcDriverClass(String jdbcDriverClass) {
		this.jdbcDriverClass = jdbcDriverClass;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTimeDateFormat() {
		return timeDateFormat;
	}

	public void setTimeDateFormat(String timeDateFormat) {
		this.timeDateFormat = timeDateFormat;
	}

	public String getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(String initialValue) {
		this.initialValue = initialValue;
	}

	public String getOffsetColumnName() {
		return offsetColumnName;
	}

	public void setOffsetColumnName(String offsetColumnName) {
		this.offsetColumnName = offsetColumnName;
	}

	public String getSqlQueryFile() {
		return sqlQueryFile;
	}

	public void setSqlQueryFile(String sqlQueryFile) {
		this.sqlQueryFile = sqlQueryFile;
	}

	public String getAvroSchemaClass() {
		return avroSchemaClass;
	}

	public void setAvroSchemaClass(String avroSchemaClass) {
		this.avroSchemaClass = avroSchemaClass;
	}

	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}

	public String getCronEveryMin() {
		return cronEveryMin;
	}

	public void setCronEveryMin(String cronEveryMin) {
		this.cronEveryMin = cronEveryMin;
	}

}
