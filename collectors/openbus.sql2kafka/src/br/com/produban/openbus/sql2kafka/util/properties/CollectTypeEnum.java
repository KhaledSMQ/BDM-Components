package br.com.produban.openbus.sql2kafka.util.properties;

public enum CollectTypeEnum {
	DUMP("DUMP"), DELTATIME("DELTATIME"), SEQUENCE("SEQUENCE");
	
	private String description;
	
	CollectTypeEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
