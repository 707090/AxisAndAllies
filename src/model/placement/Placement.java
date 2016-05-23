package model.placement;

import java.util.HashMap;
import java.util.Map;

import model.map.Area;

public class Placement {

	private Map<Area, MilitaryPresence> placements;
	
	public Placement() {
		placements = new HashMap<Area, MilitaryPresence>();
	}

	public Map<Area, MilitaryPresence> getPlacements() {
		return placements;
	}
	
}
