package model.transform;

import model.map.Area;
import model.placement.Placement;
import model.untransformed.placement.MilitaryPresenceType;
import model.untransformed.placement.PlacementType;

public class PlacementMapper implements Mapper<PlacementType, Placement> {
	
	private MapMapper mapper;
	
	public PlacementMapper(MapMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public Placement mapType(PlacementType type) {
		Placement placement = new Placement();
		
		for (MilitaryPresenceType militaryPresenceType : type.getMilitaryPresence()) {
			Area area = mapper.areaById.get(militaryPresenceType.getAreaRef());
			placement.getPlacements().put(area, new MilitaryPresenceMapper().mapType(militaryPresenceType));
		}
		
		return placement;
	}

	@Override
	public PlacementType unmapType(Placement type) {
		PlacementType placement = new PlacementType();
		
		for (Area area : type.getPlacements().keySet()) {
			MilitaryPresenceType presenceType = new MilitaryPresenceMapper().unmapType(type.getPlacements().get(area));
			presenceType.setAreaRef(mapper.unwrappedByAreas.get(area).getId());
			placement.getMilitaryPresence().add(presenceType);
		}
		
		return placement;
	}
}
