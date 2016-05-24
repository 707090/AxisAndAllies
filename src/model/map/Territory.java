package model.map;

import model.untransformed.common.PowerType;

public final class Territory extends Area {

	public static final class TerritoryBuilder extends AreaBuilder<Territory> {

		private String name;
		private int value;
		private PowerType defaultOwner;
		
		public TerritoryBuilder() {
			name = "";
			value = -1;
			defaultOwner = PowerType.NEUTRAL;
		}
		
		public TerritoryBuilder withName(String name) {
			this.name = name;
			return this;
		}
		
		public TerritoryBuilder withValue(int value) {
			this.value = value;
			return this;
		}
		
		public TerritoryBuilder withDefaultOwner(PowerType defaultOwner) {
			this.defaultOwner = defaultOwner;
			return this;
		}
		
		@Override
		public Territory _build() {
			return new Territory(this);
		}
		
	}
	
	private final String name;
	private final int value;
	private final PowerType defaultOwner;
	
	private Territory(TerritoryBuilder builder) {
		super(builder);
		this.name = builder.name;
		this.value = builder.value;
		this.defaultOwner = builder.defaultOwner;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public PowerType getDefaultOwner() {
		return defaultOwner;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
