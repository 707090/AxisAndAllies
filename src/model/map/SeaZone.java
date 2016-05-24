package model.map;

public final class SeaZone extends Area {

	public static final class SeaZoneBuilder extends AreaBuilder<SeaZone> {
		
		private int zoneNumber;
		
		public SeaZoneBuilder() {
			zoneNumber = -1;
		}
		
		public SeaZoneBuilder withZoneNumber(int zoneNumber) {
			this.zoneNumber = zoneNumber;
			return this;
		}

		@Override
		public SeaZone _build() {
			return new SeaZone(this);
		}
		
	}
	
	private final int zoneNumber;
	
	public SeaZone(SeaZoneBuilder builder) {
		super(builder);
		this.zoneNumber = builder.zoneNumber;
	}

	public int getZoneNumber() {
		return zoneNumber;
	}
	
	@Override
	public String toString() {
		return "Zone " + zoneNumber;
	}
	
}
