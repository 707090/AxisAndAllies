package model.map;

import model.untransformed.common.PowerType;
import model.untransformed.map.TerritoryType;

public final class Territory extends Area {

	private String name;
	private int value;
	private PowerType defaultOwner;
	
	public Territory(TerritoryType base) {
		super(base);
		this.name = base.getName();
		this.value = base.getValue();
		this.defaultOwner = base.getDefaultOwner();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public PowerType getDefaultOwner() {
		return defaultOwner;
	}

	public void setDefaultOwner(PowerType defaultOwner) {
		this.defaultOwner = defaultOwner;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
