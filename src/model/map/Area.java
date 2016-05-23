package model.map;

import java.util.ArrayList;
import java.util.List;

import model.untransformed.map.AreaType;

public abstract class Area {

	protected List<Area> connections;
	
	protected Area(AreaType base) {
		connections = new ArrayList<Area>();
	}
	
	public List<Area> getConnections() {
		return connections;
	}
	
}
