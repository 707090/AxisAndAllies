package model.placement;

import java.util.HashMap;
import java.util.Map;

import model.untransformed.common.PowerType;

public class MilitaryPresence {
	
	private Map<PowerType, MilitaryInstallment> military;

	public MilitaryPresence() {
		military = new HashMap<PowerType, MilitaryInstallment>();
	}

	public Map<PowerType, MilitaryInstallment> getMilitary() {
		return military;
	}

}
