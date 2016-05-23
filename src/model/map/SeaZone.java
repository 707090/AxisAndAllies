package model.map;

import model.untransformed.map.SeaZoneType;

public class SeaZone extends Area {

	private int zoneNumber;
	
	public SeaZone(SeaZoneType base) {
		super(base);
		this.zoneNumber = base.getZoneNumber();
	}

	public int getZoneNumber() {
		return zoneNumber;
	}

	public void setZoneNumber(int zoneNumber) {
		this.zoneNumber = zoneNumber;
	}
	
	@Override
	public String toString() {
		return "Zone " + zoneNumber;
	}
	
}
