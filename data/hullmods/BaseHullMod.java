package data.hullmods;

import com.fs.starfarer.api.combat.HullModEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class BaseHullMod implements HullModEffect {

	public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
	}

	public void applyEffectsBeforeShipCreation(HullSize hullSize,
											   MutableShipStatsAPI stats, String id) {
	}

	public String getDescriptionParam(int index, HullSize hullSize) {
		return null;
	}

	public boolean isApplicableToShip(ShipAPI ship) {
		return true;
	}

	
	public void advanceInCampaign(FleetMemberAPI member, float amount) {
		
	}
	
	public void advanceInCombat(ShipAPI ship, float amount) {
		
	}
}
