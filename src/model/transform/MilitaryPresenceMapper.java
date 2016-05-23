package model.transform;

import model.common.MilitaryUnitType;
import model.placement.MilitaryInstallment;
import model.placement.MilitaryPresence;
import model.untransformed.placement.MilitaryInstallmentType;
import model.untransformed.placement.MilitaryPresenceType;
import model.untransformed.placement.TransportCargoType;
import model.untransformed.placement.TroopGroupType;

public class MilitaryPresenceMapper implements Mapper<MilitaryPresenceType, MilitaryPresence> {

	public class MilitaryInstallmentMapper implements Mapper<MilitaryInstallmentType, MilitaryInstallment> {

		@Override
		public MilitaryInstallment mapType(MilitaryInstallmentType type) {
			MilitaryInstallment installment = new MilitaryInstallment(type.getOwner());
			for (TroopGroupType group : type.getTroopGroup()) {
				MilitaryUnitType unitType = MilitaryUnitType.valueOf(group.getType());
				installment.put(unitType, group.getSize());
				if (group instanceof TransportCargoType) {
					for (MilitaryInstallmentType cargoType : ((TransportCargoType) group).getCargo()) {
						installment.putCargo(unitType, mapType(cargoType));
					}
				}
			}
			return installment;
		}
		
		@Override
		public MilitaryInstallmentType unmapType(MilitaryInstallment type) {
			MilitaryInstallmentType installment = new MilitaryInstallmentType();
			installment.setOwner(type.getOwner());
			for (MilitaryUnitType unitType : MilitaryUnitType.values()) {
				int size = type.get(unitType);
				if (size > 0) {
					TroopGroupType group = unitType.isContainer() ? new TransportCargoType() : new TroopGroupType();
					group.setType(unitType.getName());
					group.setSize(size);
					if (unitType.isContainer()) {
						for (MilitaryInstallment cargo : type.getCargo(unitType)) {
							((TransportCargoType) group).getCargo().add(unmapType(cargo));
						}
					}
					installment.getTroopGroup().add(group);
				}
			}
			return installment;
		}
		
	}
	
	@Override
	public MilitaryPresence mapType(MilitaryPresenceType type) {
		MilitaryPresence presence = new MilitaryPresence();
		for (MilitaryInstallmentType installment : type.getMilitaryInstallment()) {
			presence.getMilitary().put(installment.getOwner(), new MilitaryInstallmentMapper().mapType(installment));
		}
		return presence;
	}
	
	@Override
	public MilitaryPresenceType unmapType(MilitaryPresence type) {
		MilitaryPresenceType presence = new MilitaryPresenceType();
		for (MilitaryInstallment installment : type.getMilitary().values()) {
			presence.getMilitaryInstallment().add(new MilitaryInstallmentMapper().unmapType(installment));
		}
		return presence;
	}
	
}
