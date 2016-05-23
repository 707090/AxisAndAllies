package model.common;

import model.untransformed.common.MilitaryAirUnitType;
import model.untransformed.common.MilitaryLandUnitType;
import model.untransformed.common.MilitarySeaUnitType;

public enum MilitaryUnitType {

	FIGHTER(MilitaryAirUnitType.FIGHTER, 10, 4, 3, 4),
	BOMBER(MilitaryAirUnitType.BOMBER, 12, 6, 4, 1),
	INFANTRY(MilitaryLandUnitType.INFANTRY, 3, 1, 1, 2),
	TANK(MilitaryLandUnitType.TANK, 6, 2, 3, 3),
	SUBMARINE(MilitarySeaUnitType.SUBMARINE, 6, 2, 2, 1),
	TRANSPORT(MilitarySeaUnitType.TRANSPORT, 7, 2, 0, 0, true),
	DESTROYER(MilitarySeaUnitType.DESTROYER, 8, 2, 2, 2),
	AIRCRAFT_CARRIER(MilitarySeaUnitType.AIRCRAFT_CARRIER, 12, 2, 1, 2, true),
	BATTLESHIP(MilitarySeaUnitType.BATTLESHIP, 16, 2, 4, 4);
	
	public enum UnitType { AIR, LAND, SEA };
	
	private UnitType unitType;
	private boolean isContainer;
	private String name;
	private int cost;
	private int move;
	private int attack;
	private int defense;
	
	private MilitaryUnitType(Object base, int cost, int move, int attack, int defense) {
		this(base, cost, move, attack, defense, false);
	}
	
	private MilitaryUnitType(Object base, int cost, int move, int attack, int defense, boolean isContainer) {
		if (base instanceof MilitaryAirUnitType) {
			unitType = UnitType.AIR;
			name = ((MilitaryAirUnitType) base).value();
		} else if (base instanceof MilitaryLandUnitType) {
			unitType = UnitType.LAND;
			name = ((MilitaryLandUnitType) base).value();
		} else if (base instanceof MilitarySeaUnitType) {
			unitType = UnitType.SEA;
			name = ((MilitarySeaUnitType) base).value();
		}
		this.cost = cost;
		this.move = move;
		this.attack = attack;
		this.defense = defense;
	}

	public UnitType getUnitType() {
		return unitType;
	}
	
	public boolean isContainer() {
		return isContainer;
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}

	public int getMove() {
		return move;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}
	
}
