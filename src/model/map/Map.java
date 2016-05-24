package model.map;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class Map {
	
	public static class MapBuilder {
		
		private List<Territory> territories;
		private List<SeaZone> seaZones;
		
		public MapBuilder() {
			territories = new LinkedList<Territory>();
			seaZones = new LinkedList<SeaZone>();
		}
		
		public MapBuilder with(Area area) {
			if (area instanceof Territory) {
				territories.add((Territory) area);
			} else if (area instanceof SeaZone) {
				seaZones.add((SeaZone) area);
			}
			return this;
		}
		
		public MapBuilder with(Collection<Area> areas) {
			for (Area area : areas) {
				with(area);
			}
			return this;
		}
		
		public Map build() {
			return new Map(this);
		}
		
	}
	
	private final ImmutableList<Territory> territories;
	private final ImmutableList<SeaZone> seaZones;
	
	private Map(MapBuilder builder) {
		this.territories = ImmutableList.copyOf(builder.territories);
		this.seaZones = ImmutableList.copyOf(builder.seaZones);
	}
	
	public ImmutableList<Territory> getTerritories() {
		return territories;
	}
	
	public ImmutableList<SeaZone> getSeaZones() {
		return seaZones;
	}
	
}
