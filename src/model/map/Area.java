package model.map;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;

public abstract class Area {

	public static abstract class AreaBuilder<T extends Area> implements Supplier<Area> {
		
		protected List<Supplier<Area>> connections;
		protected T build;
		
		private boolean connectionsResolved;
		
		protected AreaBuilder() {
			connections = new LinkedList<Supplier<Area>>();
			connectionsResolved = false;
		}
		
		public AreaBuilder<T> with(Supplier<Area> area) {
			connections.add(area);
			return this;
		}
		
		public AreaBuilder<T> with(Collection<Supplier<Area>> areas) {
			connections.addAll(areas);
			return this;
		}
		
		public List<Supplier<Area>> getConnections() {
			return connections;
		}
		
		public final T build() {
			if (build == null) {
				build = _build();
			}
			return build;
		}
		
		protected abstract T _build();
		
		public void resolveConnections() {
			if (get() != null && !connectionsResolved) {
				LinkedList<Area> resolvedConnections = new LinkedList<Area>();
				for (Supplier<Area> supplier : connections) {
					resolvedConnections.add(supplier.get());
				}
				get().connections = ImmutableList.copyOf(resolvedConnections);
				connectionsResolved = true;
			}
		}
		
		@Override
		public final Area get() {
			return build;
		}
		
	}
	
	protected ImmutableList<Area> connections;
	
	protected Area(AreaBuilder<?> builder) {
		
	}
	
	public ImmutableList<Area> getConnections() {
		return connections;
	}
	
}
