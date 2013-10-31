package data.scripts.world.armada;
import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.campaign.CampaignClockAPI;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoAPI.CrewXPLevel;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import data.scripts.world.armada.api.CampaignArmadaAPI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CampaignArmadaResourceSharingController implements EveryFrameScript
{
	private SectorAPI sector;
	private CampaignArmadaAPI armada;
	
	private float fleet_risk_threshold_days_worth_of_supplies;
	private float fleet_risk_threshold_extra_crew_percentage;
	private float fleet_risk_threshold_lightyears_worth_of_fuel;
	private float fleet_abundance_threshold_days_worth_of_supplies;
	private float fleet_abundance_threshold_extra_crew_percentage;
	private float fleet_abundance_threshold_lightyears_worth_of_fuel;

	private CampaignClockAPI clock;
	
	private long last_resource_distribution_check_timestamp = Long.MIN_VALUE;
	
	
	private final boolean VERBOSE_LOGGING = false;
	private final boolean EASY_CHEAT_MODE = true;
	
	public CampaignArmadaResourceSharingController( 
		SectorAPI sector, 
		CampaignArmadaAPI armada,
		float fleet_risk_threshold_days_worth_of_supplies, 
		float fleet_risk_threshold_extra_crew_percentage, 
		float fleet_risk_threshold_lightyears_worth_of_fuel,
		float fleet_abundance_threshold_days_worth_of_supplies,
		float fleet_abundance_threshold_extra_crew_percentage,
		float fleet_abundance_threshold_lightyears_worth_of_fuel )
	{
		this.sector = sector;
		this.armada = armada;
		
		this.fleet_risk_threshold_days_worth_of_supplies = fleet_risk_threshold_days_worth_of_supplies;
		this.fleet_risk_threshold_extra_crew_percentage = fleet_risk_threshold_extra_crew_percentage;
		this.fleet_risk_threshold_lightyears_worth_of_fuel = fleet_risk_threshold_lightyears_worth_of_fuel;
		this.fleet_abundance_threshold_days_worth_of_supplies = fleet_abundance_threshold_days_worth_of_supplies;
		this.fleet_abundance_threshold_extra_crew_percentage = fleet_abundance_threshold_extra_crew_percentage;
		this.fleet_abundance_threshold_lightyears_worth_of_fuel = fleet_abundance_threshold_lightyears_worth_of_fuel;
		
		this.clock = sector.getClock();
	}

	public void advance( float amount )
	{
		if( armada == null || armada.getLeaderFleet() == null )
			return;
		
		if( clock.getElapsedDaysSince( last_resource_distribution_check_timestamp )
		    <= 1.0f )
		{
			return;
		}
		last_resource_distribution_check_timestamp = clock.getTimestamp();

		if( EASY_CHEAT_MODE ) // create resources out of thin air as needed
			cheat_resources();
		else // no cheating! move resources around between fleets
			auto_redistribute_resources();
		
		if( VERBOSE_LOGGING )
			log_stats_verbose();
	}

	private void auto_redistribute_resources()
	{
		CampaignFleetAPI leader_fleet = armada.getLeaderFleet();
		
		// if the leader fleet is destroyed, abort
		if( !leader_fleet.isAlive() )
			return;
		// if less than one fleets remain, abort
		if( count_alive_fleets() < 1 )
			return; // no one to share with
	
		// search for fleets that are:
		// * at risk of an accident due to low supplies, or
		// * have undeployable ships due to low ship CR
		List jeopardized_fleets = get_jeopardized_fleets();
		if( jeopardized_fleets.size() <= 0 )
			return; // no one needs anything
		// then, calculate the needed resources to restore those fleets to desired levels.
		float needed_crew =     0.0f;
		float needed_supplies = 0.0f;
		float needed_fuel =     0.0f;
		for( int i = 0; i < jeopardized_fleets.size(); ++i )
		{
			CampaignFleetAPI fleet = (CampaignFleetAPI)jeopardized_fleets.get( i );
			needed_crew +=     calculate_needed_crew( fleet );
			needed_supplies += calculate_needed_supplies( fleet );
			needed_fuel +=     calculate_needed_fuel( fleet );
		}
		
		List generous_fleets = get_generous_fleets();
		if( generous_fleets.size() <= 0 )
			return; // no one has anything extra available
		// then, check if the required resources exist within all of the non-at-risk fleets as a group
		float available_crew =     0.0f;
		float available_supplies = 0.0f;
		float available_fuel =     0.0f;
		for( int i = 0; i < generous_fleets.size(); ++i )
		{
			CampaignFleetAPI fleet = (CampaignFleetAPI)generous_fleets.get( i );
			available_crew +=     calculate_noncritical_crew( fleet );
			available_supplies += calculate_noncritical_supplies( fleet );
			available_fuel +=     calculate_noncritical_fuel( fleet );
		}
		
		// if they do, transfer resources.
		//   all source fleets contribute a number of resources. the actual number will vary such that donated percentages of total fleet resources are equal
		if( available_crew > 0     && needed_crew > 0 )
		{
			redistribute_crew( available_crew, generous_fleets, needed_crew, jeopardized_fleets );
		}
		if( available_supplies > 0 && needed_supplies > 0 )
		{
			redistribute_supplies( available_supplies, generous_fleets, needed_supplies, jeopardized_fleets );
		}
		if( available_fuel > 0     && needed_fuel > 0 )
		{
			redistribute_fuel( available_fuel, generous_fleets, needed_fuel, jeopardized_fleets );
		}		
	}
	
	private int count_alive_fleets()
	{
		CampaignFleetAPI leader_fleet = armada.getLeaderFleet();
		CampaignFleetAPI[] escort_fleets = armada.getEscortFleets();
		
		int count = 0;
		for( int i = 0; i < escort_fleets.length; ++i )
			if( escort_fleets[i].isAlive() )
				++count;
		if( leader_fleet.isAlive() )
			++count;
		return count;
	}
	
	private List get_jeopardized_fleets()
	{
		CampaignFleetAPI leader_fleet = armada.getLeaderFleet();
		CampaignFleetAPI[] escort_fleets = armada.getEscortFleets();

		ArrayList list = new ArrayList( escort_fleets.length );
		for( int i = 0; i < escort_fleets.length; ++i )
		{
			if( !escort_fleets[i].isAlive() )
				continue;
			if( calculate_needed_crew( escort_fleets[i] ) > 0
			||  calculate_needed_supplies( escort_fleets[i] ) > 0 )
			{
				list.add( escort_fleets[i] );
			}
			// TODO: other resources
		}
		if( leader_fleet.isAlive() 
		&&( calculate_needed_crew( leader_fleet ) > 0 
		||  calculate_needed_supplies( leader_fleet ) > 0 ))
		{
			list.add( leader_fleet );
		}
		return list;
	}
	
	private List get_generous_fleets()
	{
		CampaignFleetAPI leader_fleet = armada.getLeaderFleet();
		CampaignFleetAPI[] escort_fleets = armada.getEscortFleets();

		ArrayList list = new ArrayList( escort_fleets.length );
		// for each fleet, that fleet is at risk if: ...
		for( int i = 0; i < escort_fleets.length; ++i )
		{
			if( !escort_fleets[i].isAlive() )
				continue;
			// if an escort fleet needs supplies
			if( calculate_noncritical_crew( escort_fleets[i] ) > 0 
			||  calculate_noncritical_supplies( escort_fleets[i] ) > 0 )
			{
				list.add( escort_fleets[i] );
			}
			// TODO: other resources
		}
		if( leader_fleet.isAlive()
		&&( calculate_noncritical_crew( leader_fleet ) > 0 
		||  calculate_noncritical_supplies( leader_fleet ) > 0 ))
		{
			list.add( leader_fleet );
		}
		return list;
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
	
	private float calculate_needed_crew( CampaignFleetAPI fleet )
	{
		float[] range = calculate_fleet_crew_requirement_range( fleet );
		return ((range[0] + fleet_risk_threshold_extra_crew_percentage * range[0])
		  - (float)fleet.getCargo().getTotalCrew() );
	}
	
	private float calculate_needed_supplies( CampaignFleetAPI fleet )
	{
		return ((fleet_risk_threshold_days_worth_of_supplies * fleet.getTotalSupplyCostPerDay())
		  - fleet.getCargo().getSupplies());
	}
	
	private float calculate_needed_fuel( CampaignFleetAPI fleet )
	{
		return ((fleet_risk_threshold_lightyears_worth_of_fuel * fleet.getLogistics().getFuelCostPerLightYear() )
		  - fleet.getCargo().getFuel());
	}
	
	
	private float calculate_noncritical_crew( CampaignFleetAPI fleet )
	{
		float[] range = calculate_fleet_crew_requirement_range( fleet );
		return ((float)fleet.getCargo().getTotalCrew()
		  - (range[0] + fleet_abundance_threshold_extra_crew_percentage * range[0]));
	}
	
	private float calculate_noncritical_supplies( CampaignFleetAPI fleet )
	{
		return (fleet.getCargo().getSupplies()
		  - (fleet_abundance_threshold_days_worth_of_supplies * fleet.getTotalSupplyCostPerDay()));
	}
	
	private float calculate_noncritical_fuel( CampaignFleetAPI fleet )
	{
		return (fleet.getCargo().getFuel()
		  - (fleet_abundance_threshold_lightyears_worth_of_fuel * fleet.getLogistics().getFuelCostPerLightYear() ));
	}
	
	private void redistribute_crew( float available_crew, List donor_fleets, float needed_crew, List benefactor_fleets )
	{
		float take_ratio;
		int take_amount;
		int take_GREEN = 0;
		int take_REGULAR = 0;
		int take_VETERAN = 0;
		int take_ELITE = 0;
		  
		float give_ratio;
		int give_GREEN;
		int give_REGULAR;
		int give_VETERAN;
		int give_ELITE;
		
		CampaignFleetAPI fleet;
		CargoAPI cargo;
		int available_GREEN;
		int available_REGULAR;
		int available_VETERAN;
		int available_ELITE;
		
		if( available_crew > needed_crew )
		{
			take_ratio = needed_crew / available_crew;
			give_ratio = 1;
		}
		else if( available_crew < needed_crew )
		{
			take_ratio = 1;
			give_ratio = available_crew / needed_crew;
		}
		else // available_crew == needed_crew 
		{
			take_ratio = 1;
			give_ratio = 1;
		}
		
		for( int i = 0; i < donor_fleets.size(); ++i )
		{
			fleet = (CampaignFleetAPI)donor_fleets.get( i );
			cargo = fleet.getCargo();
			////////////////
			// take crew from donor
			take_amount = (int)Math.floor( take_ratio * calculate_noncritical_crew( fleet ));
			// try take green
			available_GREEN = cargo.getCrew( CrewXPLevel.GREEN );
			if( Math.min( take_amount, available_GREEN ) > 0 )
			{
				take_GREEN = Math.min( take_amount, available_GREEN );
				cargo.removeCrew( CrewXPLevel.GREEN, take_GREEN );
				take_amount -= take_GREEN;
			}
			// try take regular
			available_REGULAR = cargo.getCrew( CrewXPLevel.REGULAR );
			if( Math.min( take_amount, available_REGULAR ) > 0 )
			{
				take_REGULAR = Math.min( take_amount, available_REGULAR );
				cargo.removeCrew( CrewXPLevel.REGULAR, take_REGULAR );
				take_amount -= take_REGULAR;
			}
			// try take veteran
			available_VETERAN = cargo.getCrew( CrewXPLevel.VETERAN );
			if( Math.min( take_amount, available_VETERAN ) > 0 )
			{
				take_VETERAN = Math.min( take_amount, available_VETERAN );
				cargo.removeCrew( CrewXPLevel.VETERAN, take_VETERAN );
				take_amount -= take_VETERAN;
			}
			// try take elite
			available_ELITE = cargo.getCrew( CrewXPLevel.ELITE );
			if( Math.min( take_amount, available_ELITE ) > 0 )
			{
				take_ELITE = Math.min( take_amount, available_ELITE );
				cargo.removeCrew( CrewXPLevel.ELITE, take_ELITE );
				take_amount -= take_ELITE;
			}
		}
		// TODO: this could either vaporize crew or create crew out of thin air; I choose to go with the latter for now
		//   but at some point it needs to use a "crew-remainder-coralling" pass and pickup the spares and put them somewhere
		for( int i = 0; i < benefactor_fleets.size(); ++i )
		{
			fleet = (CampaignFleetAPI)benefactor_fleets.get( i );
			cargo = fleet.getCargo();
			//////////////////
			// give crew to donor
			// always give an equal distribution of crew by experience level to all donors
			give_GREEN = (int)Math.ceil( give_ratio * take_GREEN );
			cargo.addCrew( CrewXPLevel.GREEN, give_GREEN );
			//
			give_REGULAR = (int)Math.ceil( give_ratio * take_REGULAR );
			cargo.addCrew( CrewXPLevel.REGULAR, give_REGULAR );
			//
			give_VETERAN = (int)Math.ceil( give_ratio * take_VETERAN );
			cargo.addCrew( CrewXPLevel.VETERAN, give_VETERAN );
			//
			give_ELITE = (int)Math.ceil( give_ratio * take_ELITE );
			cargo.addCrew( CrewXPLevel.ELITE, give_ELITE );
		}		
	}
	

	private void redistribute_supplies( float available_supplies, List donor_fleets, float needed_supplies, List benefactor_fleets )
	{
		float take_ratio;
		float take_amount;
		float give_ratio;
		float give_amount;
		CampaignFleetAPI fleet;
		CargoAPI cargo;
		
		if( available_supplies > needed_supplies )
		{
			take_ratio = needed_supplies / available_supplies;
			give_ratio = 1;
		}
		else if( available_supplies < needed_supplies )
		{
			take_ratio = 1;
			give_ratio = available_supplies / needed_supplies;
		}
		else // available_supplies == needed_supplies 
		{
			take_ratio = 1;
			give_ratio = 1;
		}
		
		for( int i = 0; i < donor_fleets.size(); ++i )
		{
			fleet = (CampaignFleetAPI)donor_fleets.get( i );
			cargo = fleet.getCargo();
			///
			take_amount = take_ratio * calculate_noncritical_supplies( fleet );
			cargo.removeSupplies( take_amount );
		}
		for( int i = 0; i < benefactor_fleets.size(); ++i )
		{
			fleet = (CampaignFleetAPI)benefactor_fleets.get( i );
			cargo = fleet.getCargo();
			///
			give_amount = give_ratio * calculate_needed_supplies( fleet );
			cargo.addSupplies( give_amount );
		}
	}

	private void redistribute_fuel( float available_fuel, List donor_fleets, float needed_fuel, List benefactor_fleets )
	{
		float take_ratio;
		float take_amount;
		float give_ratio;
		float give_amount;
		CampaignFleetAPI fleet;
		CargoAPI cargo;
		
		if( available_fuel > needed_fuel )
		{
			take_ratio = needed_fuel / available_fuel;
			give_ratio = 1;
		}
		else if( available_fuel < needed_fuel )
		{
			take_ratio = 1;
			give_ratio = available_fuel / needed_fuel;
		}
		else // available_fuel == needed_fuel 
		{
			take_ratio = 1;
			give_ratio = 1;
		}
		
		for( int i = 0; i < donor_fleets.size(); ++i )
		{
			fleet = (CampaignFleetAPI)donor_fleets.get( i );
			cargo = fleet.getCargo();
			///
			take_amount = take_ratio * calculate_noncritical_fuel( fleet );
			cargo.removeFuel( take_amount );
		}
		for( int i = 0; i < benefactor_fleets.size(); ++i )
		{
			fleet = (CampaignFleetAPI)benefactor_fleets.get( i );
			cargo = fleet.getCargo();
			///
			give_amount = give_ratio * calculate_needed_fuel( fleet );
			cargo.addFuel( give_amount );
		}
	}
	
	// not used unless cheater flag is set
	private void cheat_resources()
	{
		CampaignFleetAPI[] escort_fleets = armada.getEscortFleets();
		int cheated_fleets = 0;
		float cheated_crew = 0;
		float cheated_supplies = 0.0f;
		float cheated_fuel = 0.0f;
		float cheat_factor = 2.0f; // how much to actually give a fleet that needs more
		for( int i = 0; i < escort_fleets.length; ++i )
		{
			CampaignFleetAPI fleet = escort_fleets[i];
			float needed_crew = calculate_needed_crew( fleet ) * cheat_factor;
			if( needed_crew > 0 )
			{
				fleet.getCargo().addCrew( CrewXPLevel.GREEN, (int)Math.ceil( needed_crew ));
				cheated_crew += (int)Math.ceil( needed_crew );
			}
			float needed_supplies = calculate_needed_supplies( fleet ) * cheat_factor;
			if( needed_supplies > 0 )
			{
				fleet.getCargo().addSupplies( needed_supplies );
				cheated_supplies += needed_supplies;
			}
			float needed_fuel = calculate_needed_fuel( fleet ) * cheat_factor;
			if( needed_fuel > 0 )
			{
				fleet.getCargo().addFuel( needed_fuel );
				cheated_fuel += needed_fuel;
			}
			if( needed_crew > 0 || needed_supplies > 0 || needed_fuel > 0 )
			{
				++cheated_fleets;
			}
		}
	}
	
	private void log_stats_verbose()
	{
		CampaignFleetAPI[] escort_fleets = armada.getEscortFleets();
		StringBuffer sb = new StringBuffer("--- daily at-a-glance resource-sharing-controller stats ---");
		for( int i = 0; i < escort_fleets.length; ++i )
		{
			CampaignFleetAPI ef = escort_fleets[i];
			sb.append("\n")
			  .append(String.format("%1$30s", new Object[]{ ef.getName() }))
			  .append(" has ")
			  .append(String.format("%1$50s", new Object[]{ (ef.getCargo().getTotalCrew()+ef.getCargo().getMarines())+"/"+ef.getCargo().getMaxPersonnel()+" personnel" }))
			  .append(String.format("%1$50s", new Object[]{ ef.getCargo().getSupplies()+"/"+ef.getCargo().getMaxCapacity()+" supplies" }))
			  .append(String.format("%1$50s", new Object[]{ ef.getCargo().getFuel()+"/"+ef.getCargo().getMaxFuel()+" fuel" }));
		}
	}
	

	public boolean isDone()
	{
		return false; // never done
	}

	public boolean runWhilePaused()
	{
		return false; // do not do this
	}

}
