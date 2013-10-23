package data.hullmods;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import data.hullmods.base.BaseFleetEffectHullMod;
import java.util.Hashtable;


public class TheNomadsAutomatedNanobotFabricators extends BaseFleetEffectHullMod
{
	private Hashtable memo = new Hashtable();
	private final int CREDITS_idx = 0;
	private final int SUPPLIES_idx = 1;
	
	public void advanceInCampaign( FleetMemberAPI member, float amount )
	{
		CampaignFleetAPI fleet = findFleet( member );
		if( fleet == null )
			return; // if the fleet member exists in the campaign and this method is being called, this should never occur
		CargoAPI cargo = fleet.getCargo();
		float credits = cargo.getCredits().get();
		float supplies = cargo.getSupplies();
		float[] prev = (float[])memo.get( member );
		if( prev == null )
		{
			prev = new float[]{ credits, supplies };
			memo.put( member, prev );
			return; // can't do anything with this until next frame
		}
		if( credits == prev[CREDITS_idx] )
		{
			// only replace supplies if supplies were lost but credits remain unchanged
			if( supplies < prev[SUPPLIES_idx] )
			{
				cargo.addSupplies( prev[SUPPLIES_idx] - supplies );
				supplies = cargo.getSupplies();
			}
		}
		prev[CREDITS_idx] = credits;
		prev[SUPPLIES_idx] = supplies;
	}

	@Override
	public boolean isApplicableToShip( ShipAPI ship )
	{
		// Oasis only in reality
		return ship.getHullSpec().getHullId().equals( "nom_oasis" );
	}
}
