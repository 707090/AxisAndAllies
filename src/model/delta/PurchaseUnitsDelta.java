package model.delta;

import model.Game;
import model.placement.MilitaryInstallment;
import model.untransformed.common.TurnPhaseType;

public class PurchaseUnitsDelta extends Delta {

	public static final TurnPhaseType PHASE = TurnPhaseType.PURCHASE_UNITS;
	
	private MilitaryInstallment purchase;
	
	public PurchaseUnitsDelta() {
		super(PHASE);
	}

	@Override
	public void doDelta(Game context) {
		int money = context.players.get(0).getMoney();
	}

}
