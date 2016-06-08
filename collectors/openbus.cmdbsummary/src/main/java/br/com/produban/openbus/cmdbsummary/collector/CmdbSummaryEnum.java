package br.com.produban.openbus.cmdbsummary.collector;

public enum CmdbSummaryEnum {
	
	TOTAL_PLATFORM_BY_VDC("totalPlatformByVdc"),
	TOTAL_PLATFORM_BY_VDC_DEPLOYED("totalPlatformByVdcDeployed"),
	TOTAL_PLATFORM_BY_VDC_BEING_ASSEMBLED("totalPlatformByVdcBeingAssembled"),
	TOTAL_PLATFORM_BY_VDC_DOWN("totalPlatformByVdcDown"),
	TOTAL_PLATFORM_BY_VDC_RESERVERD("totalPlatformByVdcReserved");
	
	private String value;
	
	private CmdbSummaryEnum(String value) {
		this.value =  value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
