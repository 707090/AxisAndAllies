package model.transform;

import java.util.HashMap;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import model.map.Area;
import model.map.Map;
import model.map.Map.MapBuilder;
import model.map.SeaZone;
import model.map.Territory;
import model.untransformed.map.AreaType;
import model.untransformed.map.MapType;
import model.untransformed.map.SeaZoneType;
import model.untransformed.map.TerritoryType;

public class MapMapper implements Mapper<MapType, Map> {
	
	java.util.Map<Integer, Area> areaById;
	
	public Map mapType(MapType base) {
		MapBuilder map = new MapBuilder();
		areaById = new HashMap<Integer, Area>(base.getTerritory().size());
		
		//Create new territories/sea zones and map every area according by its ID
		base.getTerritory().forEach(unwrappedTerritory -> {
			areaById.put(unwrappedTerritory.getId(), new Territory(unwrappedTerritory));
		});
		base.getSeaZone().forEach(unwrappedSeaZone -> {
			areaById.put(unwrappedSeaZone.getId(), new SeaZone(unwrappedSeaZone));
		});

		//Resolve the IDs for all the areas
		base.getTerritory().forEach(unwrappedTerritory -> {
			Territory needingReferencesResolved = (Territory) areaById.get(unwrappedTerritory.getId());
			unwrappedTerritory.getConnection().forEach(areaId -> {
				needingReferencesResolved.getConnections().add(areaById.get(areaId));
			});
		});
		base.getSeaZone().forEach(unwrappedSeaZone -> {
			SeaZone needingReferencesResolved = (SeaZone) areaById.get(unwrappedSeaZone.getId());
			unwrappedSeaZone.getConnection().forEach(areaId -> {
				needingReferencesResolved.getConnections().add(areaById.get(areaId));
			});
		});
		
		//Denormalize connections for easy references
		areaById.values().forEach(area -> {
			for(Area connection: area.getConnections()) {
				if(!connection.getConnections().contains(area)) {
					connection.getConnections().add(area);
				}
			}
		});
		
		//Add all the new Territories and SeaZones to the map
		map.with(areaById.values());
		return map.build();
	}
	
	java.util.Map<Area, AreaType> unwrappedByAreas;
	private int id = 0;

	public MapType unmapType(Map wrapped) {
		MapType base = new MapType();

		// Create new unwrapped territories/seazones and map every area to its ID
		unwrappedByAreas = new HashMap<Area, AreaType>();
		wrapped.getTerritories().forEach(territory -> {
			TerritoryType unwrapped = new TerritoryType();
			unwrapped.setName(territory.getName());
			unwrapped.setValue(territory.getValue());
			unwrapped.setDefaultOwner(territory.getDefaultOwner());
			unwrapped.setId(id++);
			base.getTerritory().add(unwrapped);

			unwrappedByAreas.put(territory, unwrapped);
		});
		wrapped.getSeaZones().forEach(seaZone -> {
			SeaZoneType unwrapped = new SeaZoneType();
			unwrapped.setZoneNumber(seaZone.getZoneNumber());
			unwrapped.setId(id++);
			base.getSeaZone().add(unwrapped);

			unwrappedByAreas.put(seaZone, unwrapped);
		});

		// Un-resolve the IDs
		wrapped.getTerritories().forEach(territory -> {
			TerritoryType unwrapped = (TerritoryType) unwrappedByAreas.get(territory);
			territory.getConnections().forEach(connection -> {
				unwrapped.getConnection().add(unwrappedByAreas.get(connection).getId());
			});
		});
		wrapped.getSeaZones().forEach(seaZone -> {
			SeaZoneType unwrapped = (SeaZoneType) unwrappedByAreas.get(seaZone);
			seaZone.getConnections().forEach(connection -> {
				unwrapped.getConnection().add(unwrappedByAreas.get(connection).getId());
			});
		});

		// Normalize the connections
		Multimap<Area, Area> connections = ArrayListMultimap.create();
		wrapped.getTerritories().forEach(territory -> {
			territory.getConnections().forEach(connection -> {
				if (!connections.get(connection).contains(territory)) {
					connections.put(territory, connection);
				} else {
					TerritoryType unwrapped = (TerritoryType) unwrappedByAreas.get(territory);
					unwrapped.getConnection().remove(unwrappedByAreas.get(connection).getId());
				}
			});
		});
		wrapped.getSeaZones().forEach(seaZone -> {
			seaZone.getConnections().forEach(connection -> {
				if (!connections.get(connection).contains(seaZone)) {
					connections.put(seaZone, connection);
				} else {
					TerritoryType unwrapped = (TerritoryType) unwrappedByAreas.get(seaZone);
					unwrapped.getConnection().remove(unwrappedByAreas.get(connection).getId());
				}
			});
		});

		return base;
	}

	
}
