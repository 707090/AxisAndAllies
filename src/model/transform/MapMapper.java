package model.transform;

import java.util.HashMap;
import java.util.function.Supplier;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import model.map.Area;
import model.map.Area.AreaBuilder;
import model.map.Map;
import model.map.Map.MapBuilder;
import model.map.SeaZone;
import model.map.SeaZone.SeaZoneBuilder;
import model.map.Territory;
import model.map.Territory.TerritoryBuilder;
import model.untransformed.map.AreaType;
import model.untransformed.map.MapType;
import model.untransformed.map.SeaZoneType;
import model.untransformed.map.TerritoryType;

public class MapMapper implements Mapper<MapType, Map> {
	
	java.util.Map<Integer, Area> areaById;
	
	public Map mapType(MapType base) {
		MapBuilder map = new MapBuilder();
		areaById = new HashMap<Integer, Area>(base.getTerritory().size() + base.getSeaZone().size());
		
		java.util.Map<Integer, AreaBuilder<?>> builders = new HashMap<Integer, AreaBuilder<?>>(base.getTerritory().size() + base.getSeaZone().size());
		
		//Create new territories/sea zones and map every area according by its ID
		for (TerritoryType territoryType : base.getTerritory()) {
			builders.put(territoryType.getId(), new TerritoryBuilder()
													.withName(territoryType.getName())
													.withValue(territoryType.getValue())
													.withDefaultOwner(territoryType.getDefaultOwner()));
		}
		for (SeaZoneType seaZoneType : base.getSeaZone()) {
			builders.put(seaZoneType.getId(), new SeaZoneBuilder()
													.withZoneNumber(seaZoneType.getZoneNumber()));
		}

		//Resolve the IDs for all the areas
		for (TerritoryType territoryType : base.getTerritory()) {
			AreaBuilder<?> needingReferencesResolved = (AreaBuilder<?>) builders.get(territoryType.getId());
			for (Integer areaId : territoryType.getConnection()) {
				needingReferencesResolved.with(builders.get(areaId));
			}
		}
		for (SeaZoneType seaZoneType : base.getSeaZone()) {
			AreaBuilder<?> needingReferencesResolved = (AreaBuilder<?>) builders.get(seaZoneType.getId());
			for (Integer areaId : seaZoneType.getConnection()) {
				needingReferencesResolved.with(builders.get(areaId));
			}
		}
		
		//Denormalize connections for easy references
		for (AreaBuilder<?> builder : builders.values()) {
			for(Supplier<Area> connection: builder.getConnections()) {
				if (connection instanceof AreaBuilder<?>) {
					if(!((AreaBuilder<?>) connection).getConnections().contains(builder)) {
						((AreaBuilder<?>) connection).with(builder);
					}
				}
			}
		}
		
		//Build all of the area objects
		for (AreaBuilder<?> builder : builders.values()) {
			builder.build();
		}
		
		//Resolve all of the area objects builder references
		for (java.util.Map.Entry<Integer, AreaBuilder<?>> entry : builders.entrySet()) {
			entry.getValue().resolveConnections();
			areaById.put(entry.getKey(), entry.getValue().get());
		}
		
		//Add all the new Territories and SeaZones to the map
		map.with(areaById.values());
		return map.build();
	}
	
	java.util.Map<Area, AreaType> unwrappedByAreas;
	private int id = 0;

	public MapType unmapType(Map map) {
		MapType base = new MapType();

		// Create new unwrapped territories/seazones and map every area to its ID
		unwrappedByAreas = new HashMap<Area, AreaType>();
		for (Territory territory : map.getTerritories()) {
			TerritoryType territoryType = new TerritoryType();
			territoryType.setName(territory.getName());
			territoryType.setValue(territory.getValue());
			territoryType.setDefaultOwner(territory.getDefaultOwner());
			territoryType.setId(id++);
			base.getTerritory().add(territoryType);

			unwrappedByAreas.put(territory, territoryType);
		}
		for (SeaZone seaZone : map.getSeaZones()) {
			SeaZoneType seaZoneType = new SeaZoneType();
			seaZoneType.setZoneNumber(seaZone.getZoneNumber());
			seaZoneType.setId(id++);
			base.getSeaZone().add(seaZoneType);

			unwrappedByAreas.put(seaZone, seaZoneType);
		}

		// Un-resolve the IDs
		for (Territory territory : map.getTerritories()) {
			TerritoryType territoryType = (TerritoryType) unwrappedByAreas.get(territory);
			for (Area connection : territory.getConnections()) {
				territoryType.getConnection().add(unwrappedByAreas.get(connection).getId());
			}
		}
		for (SeaZone seaZone : map.getSeaZones()) {
			SeaZoneType seaZoneType = (SeaZoneType) unwrappedByAreas.get(seaZone);
			for (Area connection : seaZone.getConnections()) {
				seaZoneType.getConnection().add(unwrappedByAreas.get(connection).getId());
			}
		}

		// Normalize the connections
		Multimap<Area, Area> connections = ArrayListMultimap.create();
		for (Territory territory : map.getTerritories()) {
			for (Area connection : territory.getConnections()) {
				if (!connections.get(connection).contains(territory)) {
					connections.put(territory, connection);
				} else {
					TerritoryType territoryType = (TerritoryType) unwrappedByAreas.get(territory);
					territoryType.getConnection().remove(unwrappedByAreas.get(connection).getId());
				}
			}
		}
		for (SeaZone seaZone : map.getSeaZones()) {
			for (Area connection : seaZone.getConnections()) {
				if (!connections.get(connection).contains(seaZone)) {
					connections.put(seaZone, connection);
				} else {
					SeaZoneType seaZoneType = (SeaZoneType) unwrappedByAreas.get(seaZone);
					seaZoneType.getConnection().remove(unwrappedByAreas.get(connection).getId());
				}
			}
		}

		return base;
	}

	
}
