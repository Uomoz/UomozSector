package data.hullmods;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;

public class TheNomadsNaeranFluxShunts extends BaseHullMod
{
	@Override
	public void applyEffectsBeforeShipCreation( ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id )
	{
		float base = stats.getFluxCapacity().getBaseValue();
		stats.getFluxCapacity().modifyFlat( id, (250.0f * base) - base );
	}

	@Override
	public boolean isApplicableToShip( ShipAPI ship )
	{
		// restricted to Nomad ships.
		// (it's a hack to work around an undesirable OP-based AI behavior)
		return ship.getHullSpec().getHullId().startsWith( "nom_" );
	}
}
