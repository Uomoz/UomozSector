package data.hullmods;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;

public class TheNomadsNaeranFluxShunts extends BaseHullMod
{
	@Override
	public void applyEffectsBeforeShipCreation( ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id )
	{
		// bring flux capacity to actual desired level
		float base = stats.getFluxCapacity().getBaseValue();
		stats.getFluxCapacity().modifyFlat( id, (25.0f * base) - base );
		// fix complete auto-aiming uselessness problem of all maser weapons
		stats.getAutofireAimAccuracy().modifyFlat( id, 0.5f );
		// Ordnance Expert 10 (Optimized Assembly Perk) - hack
		
	}
	
	@Override
	public boolean isApplicableToShip( ShipAPI ship )
	{
		// restricted to Nomad ships.
		// (it's a hack to work around an undesirable OP-based AI behavior)
		return ship.getHullSpec().getHullId().startsWith( "nom_" );
	}
}
