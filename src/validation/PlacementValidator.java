package validation;

import java.util.Collection;
import java.util.Collections;
import java.util.function.BiFunction;

import model.common.MilitaryUnitType;
import model.common.MilitaryUnitType.UnitType;
import model.map.Area;
import model.map.SeaZone;
import model.map.Territory;
import model.placement.MilitaryInstallment;
import model.placement.MilitaryPresence;
import model.placement.Placement;

public class PlacementValidator implements Validator<Placement> {

	private static final BiFunction<Area, MilitaryPresence, Boolean> LAND_UNITS_ON_LAND = (area, militaryPresence) -> {
		if (!(area instanceof Territory)) {
			for (MilitaryInstallment militaryInstallment : militaryPresence.getMilitary().values()) {
				for (MilitaryUnitType unitType : MilitaryUnitType.values()) {
					if (militaryInstallment.get(unitType) > 0 && unitType.getUnitType() == UnitType.LAND) {
						return false;
					}
				}
			}
		}
		return true;
	};
	
	private static final BiFunction<Area, MilitaryPresence, Boolean> SEA_UNITS_ON_SEA = (area, militaryPresence) -> {
		if (!(area instanceof SeaZone)) {
			for (MilitaryInstallment militaryInstallment : militaryPresence.getMilitary().values()) {
				for (MilitaryUnitType unitType : MilitaryUnitType.values()) {
					if (militaryInstallment.get(unitType) > 0 && unitType.getUnitType() == UnitType.SEA) {
						return false;
					}
				}
			}
		}
		return true;
	};
	
	private static final BiFunction<Area, MilitaryPresence, Boolean> LAND_UNITS_ON_TRANSPORT = (area, militaryPresence) -> {
		if (!(area instanceof SeaZone)) {
			for (MilitaryInstallment militaryInstallment : militaryPresence.getMilitary().values()) {
				for (MilitaryUnitType unitType : MilitaryUnitType.values()) {
					if (militaryInstallment.get(unitType) > 0 && unitType == MilitaryUnitType.TRANSPORT) {
						Collection<MilitaryInstallment> tranportedCargo = militaryInstallment.getCargo(unitType);
						int totalSize = 0;
						
					}
				}
			}
		}
		return true;
	};
	
	
	
	@Override
	public Collection<Throwable> validate(Placement placement) {
		return Collections.emptyList();
	}
	
}
