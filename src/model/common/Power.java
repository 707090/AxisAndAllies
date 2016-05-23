package model.common;

import java.util.List;

import model.map.Area;
import model.untransformed.common.PowerType;

public class Power {

	private PowerType base;
	private String name;
	private int money;
	private List<Area> controlledAreas;
	
	public Power(PowerType powerType) {
		this.base = powerType;
		this.name = powerType.value();
	}

	public PowerType getBase() {
		return base;
	}

	public void setBase(PowerType base) {
		this.base = base;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public List<Area> getControlledAreas() {
		return controlledAreas;
	}
	
}
