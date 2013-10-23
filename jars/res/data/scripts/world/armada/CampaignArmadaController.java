package data.scripts.world.armada;
import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignClockAPI;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.FleetAssignment;
import com.fs.starfarer.api.campaign.JumpPointAPI.JumpDestination;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.mission.FleetSide;
import data.scripts.world.armada.CampaignArmadaWaypointController.CampaignArmadaWaypoint;
import data.scripts.world.armada.api.CampaignArmadaAPI;
import data.scripts.world.armada.api.CampaignArmadaEscortFleetPositionerAPI;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.lwjgl.util.vector.Vector2f;


@SuppressWarnings("unchecked")
public class CampaignArmadaController implements EveryFrameScript, CampaignArmadaAPI
{
	// current star system
	private SectorAPI sector;
	private LocationAPI spawn_system;
	private SectorEntityToken spawn_location;

	// basic behavior options
	private String faction_id;
	private String leader_fleet_id;
	private String vip_ship_id;
	private int escort_fleet_count;
	private String[] escort_fleet_composition_pool;
	private int[] escort_fleet_composition_weights;
	private CampaignArmadaEscortFleetPositionerAPI escort_positioner;
	private int dead_time_days;
	
	private CampaignClockAPI clock;
	
	private float fleet_ticks;
	private final float OFFSCREEN_ESCORT_FLEET_UPDATE_MIN_SEC = 2.0f;

	// Refers to the "Armada Leader" - this fleet is the one issued the waypoint
	//  movement orders, and the armada will do its best to protect this fleet.
	//  the leader fleet will travel unwaveringly from waypoint to waypoint
	//  upon entering the system, and the last waypoint will lead it back out of
	//  the system.
	private CampaignFleetAPI leader_fleet = null;
	// Collection of non-leader fleets intended to provide a massive escort for
	//  the fleet leader.
	private CampaignFleetAPI[] escort_fleets = null;
	// 
	private CampaignArmadaWaypointController waypoint_controller = null;
	
	// mini-state machine defs
	private final static int NON_EXISTENT           = 10;
	private final static int JOURNEYING_LIKE_A_BOSS = 20;

	private int state = NON_EXISTENT;
	// used for measuring idle time; defaults to very low value so armada will spawn immediately
	private long last_state_change_timestamp = Long.MIN_VALUE;
	

	// Constructor also initializes the spawning system and begins spawning fleets
	//  Spawning is immediate and automatic
    //  escort pool and weights assumed to be non-null, non-empty and of equal length
	public CampaignArmadaController( 
		String faction_id,
		String leader_fleet_id,
		String vip_ship_id,
		SectorAPI sector,
		SectorEntityToken spawn_location,
		int escort_fleet_count,
		String[] escort_fleet_composition_pool,
		int[] escort_fleet_composition_weights, // must total 1000
		CampaignArmadaEscortFleetPositionerAPI escort_positioner,
		int waypoints_per_system_minimum,
		int waypoints_per_system_maximum,
		int dead_time_days )
	{
		// setup behaviors; these are not modified by the controller
		this.faction_id = faction_id;
		this.leader_fleet_id = leader_fleet_id;
		this.vip_ship_id = vip_ship_id;
		this.sector = sector;
		this.spawn_location = spawn_location;
		this.escort_fleet_count = escort_fleet_count;
		this.escort_fleet_composition_pool = escort_fleet_composition_pool;
		this.escort_fleet_composition_weights = escort_fleet_composition_weights;
		this.escort_positioner = escort_positioner;
		this.dead_time_days = dead_time_days;
		
		this.waypoint_controller = new CampaignArmadaWaypointController(
			sector, this, waypoints_per_system_minimum, waypoints_per_system_maximum );
		
		this.spawn_system = spawn_location.getContainingLocation();
		this.clock = sector.getClock();
	}
	
	// API methods
	public CampaignFleetAPI getLeaderFleet() { return leader_fleet; }
	public CampaignFleetAPI[] getEscortFleets() { return escort_fleets; }
	
	public CampaignArmadaEscortFleetPositionerAPI getEscortFleetPositioner() { return escort_positioner; }
	
	
	private void change_state( int new_state )
	{
		state = new_state;
		last_state_change_timestamp = clock.getTimestamp();
	}
	
	@Override
	public void advance( float amount )
	{
		switch( state )
		{ 
			////////////////////////////////////////
			case NON_EXISTENT:
				float days_dead = clock.getElapsedDaysSince( last_state_change_timestamp );
				if( days_dead >= dead_time_days )
				{
					// create & spawn leader fleet
					leader_fleet = create_leader_fleet();
					spawn_system.spawnFleet( spawn_location, 0, 0, leader_fleet );
					// create & spawn escort fleets
					escort_fleets = create_escort_fleets();
					for( int i = 0; i < escort_fleets.length; ++i )
						spawn_system.spawnFleet( spawn_location, 0, 0, escort_fleets[i] );
					escort_positioner.set_armada( this );
					waypoint_controller.run();
					change_state( JOURNEYING_LIKE_A_BOSS );
					notifyListeners( "JOURNEYING_LIKE_A_BOSS" );
				}
				break;
			
			////////////////////////////////////////
			case JOURNEYING_LIKE_A_BOSS:
				boolean OK = check_leader();
				if( !OK )
				{
					scatter_fleets();
					leader_fleet = null;
					escort_fleets = null;
					change_state( NON_EXISTENT );
					notifyListeners( "NON_EXISTENT" );
					break;
				}
				update_fleets( amount );
				break;
		}
	}
	
	private CampaignFleetAPI create_leader_fleet()
	{
		CampaignFleetAPI fleet = sector.createFleet( faction_id, leader_fleet_id );
		fleet.getCommanderStats().setAptitudeLevel( "leadership", 10.0f );
		fleet.getCommanderStats().setSkillLevel( "fleet_logistics", 10.0f );
		fleet.setPreferredResupplyLocation( leader_fleet ); // LAWL
		//fleet.getAI();
		
		return fleet;
	}
	
	private CampaignFleetAPI[] create_escort_fleets()
	{
		CampaignFleetAPI[] escort_fleets = new CampaignFleetAPI[escort_fleet_count];
		for( int i = 0; i < escort_fleets.length; ++i )
		{
			String fleet_id = weighted_string_pick( 
				escort_fleet_composition_pool,
				escort_fleet_composition_weights );
			escort_fleets[i] = sector.createFleet( faction_id, fleet_id );
			escort_fleets[i].getCommanderStats().setAptitudeLevel( "leadership", 10.0f );
			escort_fleets[i].getCommanderStats().setSkillLevel( "fleet_logistics", 10.0f );
			if( leader_fleet != null )
				escort_fleets[i].setPreferredResupplyLocation( leader_fleet );
		}
		return escort_fleets;
	}
	
	private boolean check_leader()
	{
		return (leader_fleet != null && leader_fleet.isAlive()
		  && (vip_ship_id == null || find_vip_ship( leader_fleet )));
	}
	
	private boolean find_vip_ship( CampaignFleetAPI fleet )
	{
		if( vip_ship_id == null )
			return false;
		for( Iterator i = fleet.getFleetData().getMembersInPriorityOrder().iterator(); i.hasNext(); )
		{
			FleetMemberAPI ship = (FleetMemberAPI)i.next();
			if( vip_ship_id.equals( ship.getHullId() ))
				return true;
		}
		return false;
	}
	
	private void update_fleets( float amount )
	{
		fleet_ticks += amount;
		
		CampaignArmadaWaypoint waypoint = waypoint_controller.current_waypoint;
		boolean leader_in_hyperspace_transition = leader_fleet.isInHyperspaceTransition();
		for( int i = 0; i < escort_fleets.length; ++i )
		{
			CampaignFleetAPI escort_fleet = escort_fleets[i];
			// in a hyperspace transition, allow to happen naturally if possible (sometimes it works, sometimes it doesn't)
			if( leader_in_hyperspace_transition && waypoint != null
			&&  !escort_fleet.isInHyperspaceTransition() )
			{
				escort_fleet.clearAssignments();
				sector.doHyperspaceTransition( escort_fleet, escort_fleet, 
					new JumpDestination( waypoint.target, "Jump" ));
			}
			// non-hyperspace transition: force follow (immediate)
			if( !leader_in_hyperspace_transition 
			&&  escort_fleet.getContainingLocation() != leader_fleet.getContainingLocation() )
			{
				escort_fleet.getContainingLocation().removeEntity( escort_fleet );
				leader_fleet.getContainingLocation().spawnFleet( leader_fleet, 0, 0, escort_fleet );
			}
		}
		// local position update (every frame, unless player is offscreen)
		if( leader_fleet.isInCurrentLocation()
		||  fleet_ticks >= OFFSCREEN_ESCORT_FLEET_UPDATE_MIN_SEC )
		{
			escort_positioner.update_escort_fleet_positions( amount );
		}
		// timer
		if( fleet_ticks >= OFFSCREEN_ESCORT_FLEET_UPDATE_MIN_SEC )
			fleet_ticks -= OFFSCREEN_ESCORT_FLEET_UPDATE_MIN_SEC;
	}
	
	private void scatter_fleets()
	{
		for( int i = 0; i < escort_fleets.length; ++i )
		{
			CampaignFleetAPI escort_fleet = escort_fleets[i];
			if( escort_fleet.isAlive() )
			{
				// kill everyone aaghhhh!
				escort_fleet.addAssignment( 
					FleetAssignment.RAID_SYSTEM,
					escort_fleet.getContainingLocation().createToken( 0, 0 ), 
					Float.MAX_VALUE );
			}
		}					
	}
	
	private String weighted_string_pick( String[] pool, int[] weights )
	{
		int len = Math.min( pool.length, weights.length );
		int sum = 0;
		for( int i = 0; i < len; ++i )
		{
			sum += weights[i];
		}
		int marker = (int)(Math.random() * (sum + 1)); // pick
		sum = 0;
		for( int i = 0; i < len; ++i )
		{
			sum += weights[i];
			if( marker <= sum )
			{
				return pool[i];
			}
		}
		return null;
	}
	
	Vector2f dist_result = new Vector2f();
	private float get_distance( SectorEntityToken t1, SectorEntityToken t2 )
	{
		Vector2f.sub( t1.getLocation(), t2.getLocation(), dist_result );
		return dist_result.length();
	}

	// API methods must be defined
	public boolean isDone()
	{
		return false; // never done
	}

	public boolean runWhilePaused()
	{
		return false; // do not do this
	}
	
	///// events
	
	public class CampaignArmadaControllerEvent
	{
		public String controller_state;
	}
	
	public interface CampaignArmadaControllerEventListener
	{
		public void handle_event( CampaignArmadaControllerEvent event );
	}
	
	private List _listeners = new LinkedList();
	
	public void addListener( CampaignArmadaControllerEventListener listener )
	{
		_listeners.add( listener );
		// send current state immediately
		CampaignArmadaControllerEvent event = new CampaignArmadaControllerEvent();
		switch( state ) { 
			case NON_EXISTENT:           event.controller_state = "NON_EXISTENT";           break;
			case JOURNEYING_LIKE_A_BOSS: event.controller_state = "JOURNEYING_LIKE_A_BOSS"; break;
		}
		listener.handle_event( event );
	}
	
	public void removeListener( CampaignArmadaControllerEventListener listener )
	{
		_listeners.remove( listener );
	}
	
	public void notifyListeners( String controller_state )
	{
		CampaignArmadaControllerEvent event = new CampaignArmadaControllerEvent();
		event.controller_state = controller_state;
		for( Iterator i = _listeners.iterator(); i.hasNext(); )
			((CampaignArmadaControllerEventListener)i.next()).handle_event( event );
	}
	
}

