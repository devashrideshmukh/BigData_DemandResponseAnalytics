package model;

import java.util.ArrayList;
import java.util.List;

public class Consumption {
	
	private String facility;
	private String fans;
	private String cooling;
	private String heating;
	private String interiorLight;
	private String interiorEquipment;
	
	
	public Consumption(List<String> cols) {
		this.facility=cols.get(1);
		this.fans=cols.get(2);
		this.cooling=cols.get(3);
		this.heating=cols.get(8);
		this.interiorLight=cols.get(5);
		this.interiorEquipment=cols.get(6);
		
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
	
	
	
	
}
