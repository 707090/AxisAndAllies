package model.transform;

import model.common.Power;
import model.map.Area;
import model.untransformed.common.PowerType;
import model.untransformed.power.PowersType;
import model.untransformed.power.PowersType.PowerHoldings;

public class PowerMapper implements Mapper<PowersType.PowerHoldings, Power> {

	private MapMapper mapper;
	
	public PowerMapper(MapMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public Power mapType(PowersType.PowerHoldings holdings) {
		Power toEdit = new Power(holdings.getPower());
		toEdit.setMoney(holdings.getMoney());
		for (Integer areaId : holdings.getControlledArea()) {
			Area controlled = mapper.areaById.get(areaId);
			if (controlled != null) {
				toEdit.getControlledAreas().add(controlled);
			}
		}
		return toEdit;
	}

	@Override
	public PowerHoldings unmapType(Power type) {
		PowersType.PowerHoldings powerHoldings = new PowersType.PowerHoldings();
		powerHoldings.setPower(PowerType.fromValue(type.getName()));
		powerHoldings.setMoney(type.getMoney());
		for (Area area : type.getControlledAreas()) {
			powerHoldings.getControlledArea().add(mapper.unwrappedByAreas.get(area).getId());
		}
		return powerHoldings;
	}
	
}
