package model.placement;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Multimap;

import model.common.MilitaryUnitType;
import model.untransformed.common.PowerType;

public class MilitaryInstallment {

	public static class TroopGroup {
		
		private MilitaryUnitType type;
		private int size;
		
	}
	
	private final PowerType owner;
	private Map<MilitaryUnitType, Integer> troops;
	private Multimap<MilitaryUnitType, MilitaryInstallment> cargo;
	
	public MilitaryInstallment(PowerType owner) {
		this.owner = owner;
		troops = new HashMap<MilitaryUnitType, Integer>();
	}
	
	public PowerType getOwner() {
		return owner;
	}
	
	public Integer get(MilitaryUnitType type) {
		Integer number = troops.get(type);
		return number == null ? 0 : number;
	}
	
	public Integer put(MilitaryUnitType type, Integer size) {
		return troops.put(type, size);
	}
	
	public Collection<MilitaryInstallment> getCargo(MilitaryUnitType type) {
		return cargo.get(type);
	}
	
	public void putCargo(MilitaryUnitType type, MilitaryInstallment cargo) {
		this.cargo.put(type, cargo);
	}
	
}
