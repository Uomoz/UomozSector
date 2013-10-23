package data.hullmods;

import com.fs.starfarer.api.combat.ShipAPI;

public class TheNomadsLogisticalDistributionDrones extends BaseHullMod
{
	@Override
	public boolean isApplicableToShip( ShipAPI ship )
	{
		// Oasis only in reality
		return ship.getHullSpec().getHullId().equals( "nom_oasis" );
	}
}
