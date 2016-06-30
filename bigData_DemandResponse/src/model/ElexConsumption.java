package model;

import java.util.List;

public class ElexConsumption {
	
	private String facility;
	private String fans;
	private String cooling;
	private String heating;
	private String interiorLight;
	private String interiorEquipment;
	private String timeStamp;
	private String stateCode;
	private String buildingCode;
	private String regionCode;
	
	public ElexConsumption(List<String> cols,String stateCode,String buildingCode,String regionCode) {
		String code = cols.get(0).trim();
		this.stateCode = stateCode.trim();
		this.regionCode = regionCode.trim();
		this.buildingCode=buildingCode.trim();
		this.timeStamp=cols.get(1).trim();
		this.facility=cols.get(2).trim();
		this.fans=cols.get(3).trim();
		this.cooling=cols.get(4).trim();
		this.heating=cols.get(9).trim();
		this.interiorLight=cols.get(6).trim();
		this.interiorEquipment=cols.get(7).trim();
	}

	
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public String getFans() {
		return fans;
	}
	public void setFans(String fans) {
		this.fans = fans;
	}
	public String getCooling() {
		return cooling;
	}
	public void setCooling(String cooling) {
		this.cooling = cooling;
	}
	public String getHeating() {
		return heating;
	}
	public void setHeating(String heating) {
		this.heating = heating;
	}
	public String getInteriorLight() {
		return interiorLight;
	}
	public void setInteriorLight(String interiorLight) {
		this.interiorLight = interiorLight;
	}
	public String getInteriorEquipment() {
		return interiorEquipment;
	}
	public void setInteriorEquipment(String interiorEquipment) {
		this.interiorEquipment = interiorEquipment;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getBuildingCode() {
		return buildingCode;
	}
	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	
	
	
	
	
	

}
