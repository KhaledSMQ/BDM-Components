package br.com.produban.openbus.mq.parser;

public enum QReplicatorData {
	
	  TIMESTAMP(19, "tclTimestamp")
	, TRANSACTION(20, "tclTransaction")
	, USER(21, "tclUser")
	, ACCOUNT(22, "tclAccount")
	, TERMINALACCOUNT(23, "tclTerminalAccount")
	, SYSTEMIDCICS(24, "tclSystemIdCICS")
	, DATORIGINALS(25, "tclDatOriginals")
	
	;
	
	private final int index;
	private final String name;
	
	private QReplicatorData(int index, String name) {
		this.index = index;
		this.name = name;
	}
	
	public int getIndex() {
		return index;
	}
	
	public String getName() {
		return name;
	}

}