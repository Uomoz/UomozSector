package data.missions.overdrive;

import com.fs.starfarer.api.combat.BattleObjectiveAPI;
import com.fs.starfarer.api.fleet.FleetGoal;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.mission.FleetSide;
import com.fs.starfarer.api.mission.MissionDefinitionAPI;
import com.fs.starfarer.api.mission.MissionDefinitionPlugin;

public class MissionDefinition implements MissionDefinitionPlugin {

	public void defineMission(MissionDefinitionAPI api) {

		// Set up the fleets so we can add ships and fighter wings to them.
		// In this scenario, the fleets are attacking each other, but
		// in other scenarios, a fleet may be defending or trying to escape
		api.initFleet(FleetSide.PLAYER, "RNS", FleetGoal.ATTACK, false);
		api.initFleet(FleetSide.ENEMY, "HSS", FleetGoal.ATTACK, true);

		// Set a small blurb for each fleet that shows up on the mission detail and
		// mission results screens to identify each side.
		api.setFleetTagline(FleetSide.PLAYER, "The RNS Dripfeed, Wednesday night, girls out ...");
		api.setFleetTagline(FleetSide.ENEMY, "Hegemony Cannon Fodder");
		
		// These show up as items in the bulleted list under 
		// "Tactical Objectives" on the mission detail screen
		api.addBriefingItem("See how fast you can go!");
		api.addBriefingItem("And kill the Hegemony bastards!");
		api.addBriefingItem("Press 'B' to activate bass-cannon");
		
		// api.addToFleet(FleetSide.PLAYER, "onslaught_Reconditioned", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_onslaught_overdrive_Standard", FleetMemberType.SHIP, "RNS Dripfeed", true);
		// api.addToFleet(FleetSide.PLAYER, "junk_pirates_octopus_Standard", FleetMemberType.SHIP, false);
		// api.addToFleet(FleetSide.PLAYER, "junk_pirates_dugong_Support", FleetMemberType.SHIP, "RNS Dreamboat", true);
		// api.addToFleet(FleetSide.PLAYER, "junk_pirates_sickle_Standard", FleetMemberType.SHIP, false);
		// api.addToFleet(FleetSide.PLAYER, "junk_pirates_sickle_Strike", FleetMemberType.SHIP, false);
		// api.addToFleet(FleetSide.PLAYER, "hound_Assault", FleetMemberType.SHIP, false);
		// api.addToFleet(FleetSide.PLAYER, "junk_pirates_hammer_Assault", FleetMemberType.SHIP, false);
		// api.addToFleet(FleetSide.PLAYER, "junk_pirates_hammer_Strike", FleetMemberType.SHIP, false);		
		// api.addToFleet(FleetSide.PLAYER, "junk_pirates_spike_wing", FleetMemberType.FIGHTER_WING, false);
		// api.addToFleet(FleetSide.PLAYER, "broadsword_wing", FleetMemberType.FIGHTER_WING, false);
		// api.addToFleet(FleetSide.PLAYER, "junk_pirates_spike_wing", FleetMemberType.FIGHTER_WING, false);
		// api.addToFleet(FleetSide.PLAYER, "junk_pirates_spike_wing", FleetMemberType.FIGHTER_WING, false);
		// api.addToFleet(FleetSide.PLAYER, "junk_pirates_spike_wing", FleetMemberType.FIGHTER_WING, false);
		// api.addToFleet(FleetSide.PLAYER, "junk_pirates_cleat_wing", FleetMemberType.FIGHTER_WING, false);
		// api.addToFleet(FleetSide.PLAYER, "junk_pirates_cleat_wing", FleetMemberType.FIGHTER_WING, false);

		// Mark both ships as essential - losing either one results
		// in mission failure. Could also be set on an enemy ship,
		// in which case destroying it would result in a win.
		api.defeatOnShipLoss("RNS Dripfeed");
		
		// Set up the player's fleet.  Variant names come from the
		// files in data/variants and data/variants/fighters
		// api.addToFleet(FleetSide.ENEMY, "onslaught_Standard", FleetMemberType.SHIP, "HSS Spaceboy", false);
		api.addToFleet(FleetSide.ENEMY, "hammerhead_Balanced", FleetMemberType.SHIP, false);
		// api.addToFleet(FleetSide.ENEMY, "junk_pirates_dugong_Standard", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		// api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		// api.addToFleet(FleetSide.ENEMY, "broadsword_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "broadsword_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "enforcer_CS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "enforcer_CS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "lasher_CS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "lasher_CS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "hound_Assault", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "tarsus_Standard", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "tarsus_Standard", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "condor_Strike", FleetMemberType.SHIP, false);
		// api.addToFleet(FleetSide.ENEMY, "condor_Strike", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "mining_drone_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "mining_drone_wing", FleetMemberType.FIGHTER_WING, false);
		//api.addToFleet(FleetSide.ENEMY, "mining_drone_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "longbow_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "longbow_wing", FleetMemberType.FIGHTER_WING, false);
		//api.addToFleet(FleetSide.ENEMY, "longbow_wing", FleetMemberType.FIGHTER_WING, false);
		//api.addToFleet(FleetSide.ENEMY, "longbow_wing", FleetMemberType.FIGHTER_WING, false);	

		//api.defeatOnShipLoss("HSS Achilles");		
		
		// Set up the map.
		// 12000x8000 is actually somewhat small, making for a faster-paced mission.
		float width = 16000f;
		float height =16000f;
		api.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);
		
		float minX = -width/2;
		float minY = -height/2;
		
		// All the addXXX methods take a pair of coordinates followed by data for
		// whatever object is being added.
		
		// Add two big nebula clouds
		//api.addNebula(minX + width * 0.6f, minY + height * 0.4f, 2000);
		//api.addNebula(minX + width * 0.4f, minY + height * 0.6f, 2000);
		
		// And a few random ones to spice up the playing field.
		// A similar approach can be used to randomize everything
		// else, including fleet composition.
		//for (int i = 0; i < 5; i++) {
		//	float x = (float) Math.random() * width - width/2;
		//	float y = (float) Math.random() * height - height/2;
		//	float radius = 100f + (float) Math.random() * 400f; 
		//	api.addNebula(x, y, radius);
		//}
		
		// Add objectives. These can be captured by each side
		// and provide stat bonuses and extra command points to
		// bring in reinforcements.
		// Reinforcements only matter for large fleets - in this
		// case, assuming a 100 command point battle size,
		// both fleets will be able to deploy fully right away.
		//api.addObjective(minX + width * 0.25f, minY + height * 0.5f, 
		//				 "sensor_array", BattleObjectiveAPI.Importance.NORMAL);
		//api.addObjective(minX + width * 0.5f, minY + height * 0.5f, 
		//				 "nav_buoy", BattleObjectiveAPI.Importance.NORMAL);
		//api.addObjective(minX + width * 0.40f, minY + height * 0.6f, 
		//				 "comm_relay", BattleObjectiveAPI.Importance.NORMAL);
		//api.addObjective(minX + width * 0.60f, minY + height * 0.4f, 
		//				 "sensor_array", BattleObjectiveAPI.Importance.NORMAL);
		
		// Add an asteroid field going diagonally across the
		// battlefield, 2000 pixels wide, with a maximum of 
		// 100 asteroids in it.
		// 20-70 is the range of asteroid speeds.
		// api.addAsteroidField(width, height, 135, 3000f,
		// 						20f, 70f, 200);
		
		// Add some planets.  These are defined in data/config/planets.json.
		//api.addPlanet(minX + width * 0.55f, minY + height * 0.25f, 200f, "cryovolcanic", 200f);
	}

}
