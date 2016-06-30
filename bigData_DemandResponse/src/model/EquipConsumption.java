package model;

import java.util.List;

public class EquipConsumption {
	
	private String month;
	private String total;
	private String facility;
	private String fans;
	private String cooling;
	private String heating;
	private String interiorLight;
	private String interiorEquipment;
	private String buildingCode;
	
	
	public EquipConsumption(List<String> cols) {
		this.month = cols.get(0).trim();
		this.total=cols.get(1).trim();
		this.facility=cols.get(2).trim();
		this.fans=cols.get(3).trim();
		this.cooling=cols.get(4).trim();
		this.heating=cols.get(5).trim();
		this.interiorLight=cols.get(6).trim();
		this.interiorEquipment=cols.get(7).trim();
		this.buildingCode=cols.get(8).trim();
	}


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public String getTotal() {
		return total;
	}


	public void setTotal(String total) {
		this.total = total;
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


	public String getBuildingCode() {
		return buildingCode;
	}


	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}
	
	

}
