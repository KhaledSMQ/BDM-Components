package br.com.openbus.infoblox.snmp.mib;

public class ObjectTypeDescription {

	private String description;
	private String objectIdentifier;
	private boolean isParent;

	public ObjectTypeDescription(String description, String objectIdentifier, boolean isParent) {
		super();
		this.description = description;
		this.isParent = isParent;
		this.objectIdentifier = objectIdentifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	
	public String getObjectIdentifier() {
	
	    return objectIdentifier;
	}
	
	public void setObjectIdentifier(String objectIdentifier) {
	
	    this.objectIdentifier = objectIdentifier;
	}

	
}
