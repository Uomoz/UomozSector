package data.hullmods;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class ms_enjector extends BaseHullMod {

	public static final float CASUALTY_REDUCTION = 66f;

    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getCrewLossMult().modifyMult(id, 1f - CASUALTY_REDUCTION * 0.01f);
	}
	
    public String getDescriptionParam(int index, HullSize hullSize) {
		//if (index == 0) return "" + (int) CASUALTY_REDUCTION;
		if (index == 0) return Float.toString(CASUALTY_REDUCTION);
		return null;
	}
	
	public boolean isApplicableToShip(ShipAPI ship) {
		return (ship.getHullSize() == HullSize.FIGHTER);
	}
}
