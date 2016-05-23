package model.map;

import java.util.ArrayList;
import java.util.List;

public class Map {
	
	private List<Territory> territory;
	private List<SeaZone> seaZones;
	
	public Map() {
		territory = new ArrayList<Territory>();
		seaZones = new ArrayList<SeaZone>();
	}
	
	public List<Territory> getTerritories() {
		return territory;
	}
	
	public List<SeaZone> getSeaZones() {
		return seaZones;
	}

}
