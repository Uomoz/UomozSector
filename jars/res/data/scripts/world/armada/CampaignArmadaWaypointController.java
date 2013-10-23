package data.scripts.world.armada;
import com.fs.starfarer.api.Script;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.FleetAssignment;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import data.scripts.world.armada.api.CampaignArmadaAPI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CampaignArmadaWaypointController implements Script
{
	public CampaignArmadaWaypoint current_waypoint = null;
	
	private SectorAPI sector;
	
	private CampaignArmadaAPI armada;
	private Queue waypoints = new LinkedList();
	private int min_trip_length;
	private int max_trip_length;
	
	public boolean stalled = false; // set when no waypoints could be found anywhere (un-bloody-likely)
	
	public CampaignArmadaWaypointController(
		SectorAPI sector,
		CampaignArmadaAPI armada,
		int min_trip_length,
		int max_trip_length )
	{
		this.sector = sector;
		this.armada = armada;
		this.min_trip_length = min_trip_length;
		this.max_trip_length = max_trip_length;
	}
	
	public void run() // used for fleet assignment oncomplete
	{
		current_waypoint = null;
		CampaignFleetAPI fleet = armada.getLeaderFleet();
		if( fleet == null )
		{
			// leader fleet is gone, armada is no longer tracking one (waiting to spawn, prolly)
			waypoints.clear();
			stalled = true;
			return;
		}
		if( waypoints.isEmpty() )
		{
			generate_waypoints( fleet );
		}
		CampaignArmadaWaypoint waypoint = (CampaignArmadaWaypoint) waypoints.poll();
		if( waypoint == null )
		{
			stalled = true;
			return;
		}
		fleet.addAssignment(
			waypoint.fleet_assignment,
			waypoint.target,
			waypoint.duration_in_days,
			this );
		current_waypoint = waypoint;
	}
	
	private void generate_waypoints( CampaignFleetAPI fleet )
	{
		// decide on maximum trip length (number of waypoints in a system)
		int trip_length = 0;
		// pick a system other than the current
		List locations = sector.getStarSystems();
		locations.add( sector.getHyperspace() );
		Collections.shuffle( locations );
		locations.remove( fleet.getContainingLocation() );
		LocationAPI location = (LocationAPI)locations.get( 0 );
		if( location == sector.getHyperspace() )
			trip_length = 1;
		else // StarSystem
			trip_length = (int)(min_trip_length + Math.random() * (max_trip_length - min_trip_length + 1));
		// build a pool of potential waypoints from the entities in the chosen system
		List waypoint_pool = new ArrayList();
		if( location instanceof StarSystemAPI )
			waypoint_pool.add( ((StarSystemAPI)location).getStar() );
		waypoint_pool.addAll( location.getPlanets() );
		waypoint_pool.addAll( location.getOrbitalStations() );
		waypoint_pool.addAll( location.getEntities( JumpPointAPI.class ));
//		waypoint_pool.addAll( system.getAsteroids() ); // need to reduce the weight of these, somehow
		if( waypoint_pool.isEmpty() )
		{
			return;
		}
		// randomize pool order
		Collections.shuffle( waypoint_pool );
		for( Iterator i = waypoint_pool.iterator(); i.hasNext(); )
		{
			SectorEntityToken entity_token = (SectorEntityToken)i.next();
			if( entity_token == null )
				continue;
			waypoints.add( new CampaignArmadaWaypoint(
				FleetAssignment.GO_TO_LOCATION,
				entity_token,
			    Float.MAX_VALUE ));
		}
	}

	public class CampaignArmadaWaypoint
	{
		public FleetAssignment fleet_assignment;
		public SectorEntityToken target;
		public float duration_in_days;
		
		public CampaignArmadaWaypoint(
			FleetAssignment fleet_assignment,
			SectorEntityToken target,
			float duration_in_days )
		{
			this.fleet_assignment = fleet_assignment;
			this.target = target;
			this.duration_in_days = duration_in_days;
		}
	}
}
