package data.hullmods;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class BlackrockFlux extends BaseHullMod {

	public static final float VENT_RATE_BONUS = 100f;
	
	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getVentRateMult().modifyPercent(id, VENT_RATE_BONUS);
	}
	
	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + (int) VENT_RATE_BONUS;
		return null;
	}
	
    @Override
    public boolean isApplicableToShip(ShipAPI ship)
    {
        // Allows any ship with a neutrino hull id
        return ( ship.getHullSpec().getHullId().startsWith("brdy_") &&
    !ship.getVariant().getHullMods().contains("brfluxmod")); 
    }

}