package data.hullmods;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoAPI.CrewXPLevel;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import data.hullmods.base.BaseFleetEffectHullMod;
import java.util.Iterator;
import java.util.List;


public class TheNomadsCloningVats extends BaseFleetEffectHullMod
{
	private float accumulator = 0.0f;
	
	private final float EXECUTION_PERIOD_DAYS = 1.5f; // how often in days to generate crew
	private final int CREW_ADD = 1; // number of crew to add each generation
	private final float CREW_RANGE_CAPACITY_MAX = 0.25f; // if crew is more than a quarter from "skeleton" level to "maximum", do not generate crew
	
	@Override
	public void advanceInCampaign( FleetMemberAPI member, float amount )
	{
		accumulator += amount;
		if( accumulator >= EXECUTION_PERIOD_DAYS )
			accumulator -= EXECUTION_PERIOD_DAYS;
		else
			return;
		
		CampaignFleetAPI fleet = findFleet( member );
		if( fleet == null )
			return; // this will only happen if there are fleets that I've not searched
		
		float[] crew_range = calculate_fleet_crew_requirement_range( fleet );
		CargoAPI cargo = fleet.getCargo();
		int crew = cargo.getTotalCrew();
		float max_crew = (crew_range[0] + CREW_RANGE_CAPACITY_MAX * (crew_range[1] - crew_range[0]) );
		
		if( crew <= max_crew )
		{
			fleet.getCargo().addCrew( CrewXPLevel.GREEN, CREW_ADD );
			//_.L("Cloning Vats created "+CREW_ADD+" crew");
		}
	}
	
	@Override
	public boolean isApplicableToShip( ShipAPI ship )
	{
		// Oasis only in reality
		return ship.getHullSpec().getHullId().equals( "nom_oasis" );
	}

	private float[] calculate_fleet_crew_requirement_range( CampaignFleetAPI fleet )
	{
		float[] range = { 0.0f, 0.0f };
		if( !fleet.isAlive() )
			return range;
		List members = fleet.getFleetData().getMembersInPriorityOrder();
		for( Iterator i = members.iterator(); i.hasNext(); )
		{
			FleetMemberAPI ship = (FleetMemberAPI)i.next();
			range[0] += ship.getMinCrew();
			range[1] += ship.getMaxCrew();
		}
		return range;
	}
	
}
