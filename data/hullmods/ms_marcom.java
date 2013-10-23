package data.hullmods;

import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class ms_marcom extends BaseHullMod {

	public static final float MARCOM_EFFECT = 20f;
        public static String shipId = "ms_potnia";

    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableCharacterStatsAPI stats, String id, MutableShipStatsAPI hull, ShipAPI ship) { 
        ShipAPI this_ship = (ShipAPI) hull.getEntity();
        
        if (ship == this_ship) {
		stats.getMarineEffectivnessMult().modifyPercent(id, MARCOM_EFFECT);        
            }
	}
	
    public String getDescriptionParam(int index, HullSize hullSize) {
		//if (index == 0) return "" + (int) CASUALTY_REDUCTION;
		if (index == 0) return Float.toString(MARCOM_EFFECT);
		return null;
	}
	
    public boolean isApplicableToShip(ShipAPI ship) {
		return (ship.getHullSpec().getHullId().equals(shipId));
	}
}
