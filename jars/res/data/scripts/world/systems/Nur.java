package data.scripts.world.systems;
import com.fs.starfarer.api.FactoryAPI;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoAPI.CrewXPLevel;
import com.fs.starfarer.api.campaign.FleetDataAPI;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import com.fs.starfarer.api.campaign.JumpPointAPI.JumpDestination;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.OrbitAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.SectorGeneratorPlugin;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import data.scripts.world.armada.CampaignArmadaController;
import data.scripts.world.armada.CampaignArmadaController.CampaignArmadaControllerEvent;
import data.scripts.world.armada.CampaignArmadaController.CampaignArmadaControllerEventListener;
import data.scripts.world.armada.CampaignArmadaFormationOrbit;
import data.scripts.world.armada.CampaignArmadaResourceSharingController;
import data.scripts.world.armada.api.CampaignArmadaEscortFleetPositionerAPI;
import java.awt.Color;
import java.util.Iterator;
import org.lwjgl.util.vector.Vector2f;

@SuppressWarnings( "unchecked" )
public class Nur implements SectorGeneratorPlugin, CampaignArmadaControllerEventListener
{
	private FactoryAPI factory;
	
	private SectorAPI sector;
	private LocationAPI hyper;
	
	private StarSystemAPI system;
	private SectorEntityToken system_center_of_mass;
	private PlanetAPI star_A;
	private PlanetAPI star_B;
	private PlanetAPI planet_I;
	private PlanetAPI planet_I__moon_a;
	private PlanetAPI planet_I__moon_b;
	private PlanetAPI planet_I__moon_c;
	private PlanetAPI planet_I__moon_d;
	private PlanetAPI planet_I__moon_e;
	private PlanetAPI planet_I__moon_f;
	
	private SectorEntityToken station;
	
	public void generate( SectorAPI sector )
	{
		// globals
		this.sector = sector;
		factory = Global.getFactory();
		hyper = sector.getHyperspace();
		
		//// check to prevent multiple runs, yet allow retroactive continuity
		//// does not work
		//if( sector.getStarSystem( "Nur" ) != null )
		//	return; // already created Nur
		
		// system
		system = sector.createStarSystem( "Nur" );
        //system.setBackgroundTextureFilename( "graphics/nom/backgrounds/background_nur.jpg" ); // doesn't look good
		system.setLightColor( new Color( 185, 185, 240 )); // light color in entire system, affects all entities
		system.getLocation().set( 18000f, -900f );
		
		// stars, planets, moons, jump points
		init_celestial_bodies( system );
		
		// spawners and other fleet related scripts
		init_scripts( sector, system );
	}
	
	private static final float star_jump_dist_factor_min = 0.8f;
	private static final float star_jump_dist_factor_max = 1.2f;
	
	private void init_celestial_bodies( StarSystemAPI system )
	{
		// stars, planets and moons
		system_center_of_mass = system.createToken( 0f, 0f );
		star_A = system.addPlanet( system_center_of_mass, "Nur-A", "star_blue", 90f, 1000f, 1500f, 30f );
 		star_B = system.addPlanet( system_center_of_mass, "Nur-B", "star_red", 270f, 300f, 600f, 30f );
		planet_I = system.addPlanet( system_center_of_mass, "Naera", "desert", 45f, 300f, 8000f, 199f );
		planet_I__moon_a = system.addPlanet( planet_I, "Ixaith", "rocky_unstable", 0f, 60f, 800f, 67f );
		planet_I__moon_b = system.addPlanet( planet_I, "Ushaise", "rocky_ice", 45f, 45f, 1000f, 120f );
		planet_I__moon_c = system.addPlanet( planet_I, "Riaze", "barren", 90f, 100f, 1200f, 130f );
		planet_I__moon_d = system.addPlanet( planet_I, "Riaze-Tremn", "frozen", 135f, 35f, 1500f, 132f );
		planet_I__moon_e = system.addPlanet( planet_I, "Eufariz", "frozen", 180f, 65f, 1750f, 200f );
		planet_I__moon_f = system.addPlanet( planet_I, "Thumn", "rocky_ice", 225f, 100f, 2000f, 362f );
		
		// specs
		planet_I.getSpec().setAtmosphereColor( new Color( 160,110,45, 140 ));
		planet_I.getSpec().setCloudColor( new Color( 255,255,255, 23 ));
		planet_I.getSpec().setTilt( 15 );
		planet_I.applySpecChanges();
		
		// stations
		station = system.addOrbitalStation( planet_I__moon_e, 180f, 300f, 50, "Naeran Orbital Storage & Resupply", "nomads" );
		
		// rings & bands
		system.addRingBand( planet_I, "misc", "rings1", 256f, 0, Color.white, 256f, 630f, 30f );
		
		// jump points
		JumpPointAPI star_A_jump_point = init_star_gravitywell_jump_point( system, system_center_of_mass, star_A, 
		  star_jump_dist_factor_min, star_jump_dist_factor_max );
		system.setHyperspaceAnchor( star_A_jump_point );
		
		init_star_gravitywell_jump_point( system, system_center_of_mass, star_B,
		  star_jump_dist_factor_min, star_jump_dist_factor_max );
		
		init_jump_anchor_near_planet( system, system_center_of_mass, planet_I, "Jump Point Alpha", 0f, 500f, 30f );
		// TODO: EveryFrameScript to update hyperspace anchors (not sure if base game is doing this yet)
		
		init_station_cargo( station );
		
		// descriptions
		planet_I.setCustomDescriptionId("nom_planet_naera");
	}
	
	private void init_scripts( SectorAPI sector, StarSystemAPI system )
	{
		// armada formation type
		CampaignArmadaEscortFleetPositionerAPI armada_formation =
			new CampaignArmadaFormationOrbit(
				sector,
				300.0f, // orbitRadius
				1.0f, // orbitDirection
				0.8f // orbitPeriodDays
			);
		
		// armada composition data (references faction definition data)
		String[] escort_pool = { 
			"scout", 
			"longRangeScout", 
			"battleGroup", 
			"assassin", 
			"royalGuard", 
			"jihadFleet", 
			"carrierGroup"
		};
		int[] escort_weights = {    
			220,
			200,
			230,
			185,
			175,
			125,
			100
		};
		// armada waypoint controller script
		CampaignArmadaController nomad_armada =
			new CampaignArmadaController(
				"nomads", // faction
				"colonyFleet", // leader/VIP fleet
				"nom_oasis",
				sector, // global sector api
				planet_I__moon_f,
				8, // escort_fleet_count
				escort_pool,
				escort_weights,
				armada_formation,
				1, // waypoint_per_trip_minimum
				6, // waypoint_per_trip_maximum
				30 // dead_time_days
			);
		sector.addScript( nomad_armada );
		nomad_armada.addListener( this );
		
		// armada resource pooling script
		CampaignArmadaResourceSharingController armada_resource_pool = 
			new CampaignArmadaResourceSharingController( 
				sector, 
				nomad_armada,
				3.0f, // 3 days at fleet's current usage (whatever it happens to be)
				0.10f, // skeleton crew requirement, plus 10%
				3.0f, // 5 light-years worth of fuel at fleet's current fuel consumption rate
				12.0f, // 12 days at fleet's current usage (whatever it happens to be)
				0.50f, // skeleton crew requirement, plus 25%
				20.0f // 15 light-years worth of fuel at fleet's current fuel consumption rate
			);
		sector.addScript( armada_resource_pool );
	}	
	
	private JumpPointAPI init_star_gravitywell_jump_point( StarSystemAPI system, SectorEntityToken system_root, PlanetAPI star, float dist_ratio_min, float dist_ratio_max )
	{
		FactoryAPI factory = Global.getFactory();
		LocationAPI hyper = Global.getSector().getHyperspace();
		
		JumpPointAPI jump_point = factory.createJumpPoint( star.getFullName()+" Gravity Well" );
		JumpDestination destination = new JumpDestination( star, star.getFullName() );
		destination.setMinDistFromToken( dist_ratio_min * star.getRadius() );
		destination.setMaxDistFromToken( dist_ratio_max * star.getRadius() );
		jump_point.addDestination( destination );
		jump_point.setStandardWormholeToStarOrPlanetVisual( star );
		//jump_point.setDestinationVisual(null, null, system_root);
		hyper.addEntity( jump_point );
		
		update_hyperspace_jump_point_location( jump_point, system, system_root, star );
		return jump_point;
	}
	
	private JumpPointAPI init_jump_anchor_near_planet( StarSystemAPI system, SectorEntityToken system_root, PlanetAPI planet, String name, float angle, float orbitRadius, float orbitDays )
	{
		FactoryAPI factory = Global.getFactory();
		LocationAPI hyper = Global.getSector().getHyperspace();

		JumpPointAPI local_jump_point = factory.createJumpPoint( name );
		OrbitAPI orbit = factory.createCircularOrbit( planet, angle, orbitRadius, orbitDays );
		local_jump_point.setOrbit( orbit );
		local_jump_point.setStandardWormholeToHyperspaceVisual();
		system.addEntity( local_jump_point );
		
		JumpPointAPI hyperspace_jump_point = factory.createJumpPoint( system.getName()+", "+name );
		JumpDestination destination = new JumpDestination( local_jump_point, system.getName()+", "+name );
		hyperspace_jump_point.addDestination( destination );
		hyperspace_jump_point.setStandardWormholeToStarOrPlanetVisual( planet );
		hyper.addEntity( hyperspace_jump_point );
				
		JumpDestination hyperspace_destination = new JumpDestination( hyperspace_jump_point, "Hyperspace" );
		local_jump_point.addDestination( hyperspace_destination );
		
		update_hyperspace_jump_point_location( hyperspace_jump_point, system, system_root, local_jump_point );
		return hyperspace_jump_point;
	}
        
        private void init_station_cargo( SectorEntityToken station )
	{
		CargoAPI cargo = station.getCargo();
		FleetDataAPI station_ships = cargo.getMothballedShips();
                station_ships.addFleetMember( factory.createFleetMember( FleetMemberType.SHIP, "nom_gila_monster_antibattleship" ));
                station_ships.addFleetMember( factory.createFleetMember( FleetMemberType.SHIP, "nom_sandstorm_assault" ));
                station_ships.addFleetMember( factory.createFleetMember( FleetMemberType.SHIP, "nom_scorpion_assault" ));
                station_ships.addFleetMember( factory.createFleetMember( FleetMemberType.SHIP, "nom_komodo_mk2_assault" ));
                station_ships.addFleetMember( factory.createFleetMember( FleetMemberType.SHIP, "nom_komodo_assault" ));
                station_ships.addFleetMember( factory.createFleetMember( FleetMemberType.SHIP, "nom_komodo_assault" ));
                station_ships.addFleetMember( factory.createFleetMember( FleetMemberType.SHIP, "nom_flycatcher_carrier" ));
                station_ships.addFleetMember( factory.createFleetMember( FleetMemberType.SHIP, "nom_yellowjacket_sniper" ));
		station_ships.addFleetMember( factory.createFleetMember( FleetMemberType.SHIP, "nom_death_bloom_strike" ));
		station_ships.addFleetMember( factory.createFleetMember( FleetMemberType.SHIP, "nom_wurm_assault" ));
                station_ships.addFleetMember( factory.createFleetMember( FleetMemberType.SHIP, "nom_wurm_assault" ));
		station_ships.addFleetMember( factory.createFleetMember( FleetMemberType.SHIP, "nom_wurm_assault" ));
                station_ships.addFleetMember( factory.createFleetMember( FleetMemberType.FIGHTER_WING, "nom_iguana_wing" ));
		station_ships.addFleetMember( factory.createFleetMember( FleetMemberType.FIGHTER_WING, "nom_scarab_wing" ));
		station_ships.addFleetMember( factory.createFleetMember( FleetMemberType.FIGHTER_WING, "nom_iguana_wing" ));
		station_ships.addFleetMember( factory.createFleetMember( FleetMemberType.FIGHTER_WING, "nom_scarab_wing" ));
                station_ships.addFleetMember( factory.createFleetMember( FleetMemberType.FIGHTER_WING, "nom_toad_wing" ));
		cargo.addCrew( CrewXPLevel.ELITE, 20 );
		cargo.addCrew( CrewXPLevel.VETERAN, 100 );
		cargo.addCrew( CrewXPLevel.REGULAR, 1000 );
		cargo.addSupplies( 3000.0f );
		cargo.addFuel( 3000.0f );
	}
	
	public void handle_event( CampaignArmadaControllerEvent event )
	{
		// Oasis is not in play; put it for sale at the station (yay!)
		if( "NON_EXISTENT".equals( event.controller_state ))
		{
			// add no more than one Oasis
			int count = 0; // first count oasis ships (player could have bought one previously and sold it back)
			FleetDataAPI station_ships = station.getCargo().getMothballedShips();
			for( Iterator i = station_ships.getMembersInPriorityOrder().iterator(); i.hasNext(); )
			{
				FleetMemberAPI ship = (FleetMemberAPI)i.next();
				if( "nom_oasis".equals( ship.getHullId() ))
					++count;
			}
			if( count == 0 )
			{
				station_ships.addFleetMember( factory.createFleetMember( FleetMemberType.SHIP, "nom_oasis_standard" ));
			}
		}
		// Oasis is in play; be patient! T_T
		else if( "JOURNEYING_LIKE_A_BOSS".equals( event.controller_state ))
		{
			// remove all Oasis hulls, there's only supposed to be one, and it's cruising around.
			FleetDataAPI station_ships = station.getCargo().getMothballedShips();
			for( Iterator i = station_ships.getMembersInPriorityOrder().iterator(); i.hasNext(); )
			{
				FleetMemberAPI ship = (FleetMemberAPI)i.next();
				if( "nom_oasis".equals( ship.getHullId() ))
				{
					station_ships.removeFleetMember( ship );
				}
			}
		}
	}
	
	
	// this ratio is an observed ratio between the distances from Corvus to its third planet
	// in hyperspace vs. normal space
	private static final float hyperspace_compression = 0.0612f; // (459f / 7500f) // in pixels at default zoom
	
	private void update_hyperspace_jump_point_location( JumpPointAPI jump_point, StarSystemAPI system, SectorEntityToken system_root, SectorEntityToken system_entity )
	{
		Vector2f system_location = new Vector2f( system.getLocation() );
		Vector2f local_entity_absolute_location = 
		  calculate_absolute_location( system_root, system_entity, hyperspace_compression );

		jump_point.getLocation().set( 
		  system_location.x + local_entity_absolute_location.x,
		  system_location.y + local_entity_absolute_location.y );
	}
	
	// resolves orbits of an entity until the given root object is encountered
	// if entity does not orbit around anything relative to the root, the result is undefined
	private Vector2f calculate_absolute_location( SectorEntityToken root, SectorEntityToken entity, float scale )
	{
		Vector2f location = new Vector2f();
		if( root == null || entity == null )
			return location;
		// loop through the orbital foci until the root is reached
		SectorEntityToken cursor = entity;
		while( cursor != null && cursor != root )
		{
			Vector2f cursor_location = cursor.getLocation();
			if( cursor_location == null )
				return location;
			location.translate( 
			  scale * cursor_location.x, 
			  scale * cursor_location.y );
			OrbitAPI orbit = cursor.getOrbit();
			if( orbit == null )
				return location;
			cursor = orbit.getFocus();
		}
		return location;
	}

}
