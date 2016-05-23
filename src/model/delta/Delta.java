package model.delta;

import model.Game;
import model.untransformed.common.TurnPhaseType;

public abstract class Delta {
	
	protected TurnPhaseType phase;
	
	public Delta(TurnPhaseType phase) {
		this.phase = phase;
	}
	
	public abstract void doDelta(Game context);
	
}
