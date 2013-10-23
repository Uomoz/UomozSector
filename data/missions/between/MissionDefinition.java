package data.missions.between;

import com.fs.starfarer.api.campaign.CargoAPI.CrewXPLevel;
import com.fs.starfarer.api.fleet.FleetGoal;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.mission.FleetSide;
import com.fs.starfarer.api.mission.MissionDefinitionAPI;
import com.fs.starfarer.api.mission.MissionDefinitionPlugin;

public class MissionDefinition implements MissionDefinitionPlugin {

	public void defineMission(MissionDefinitionAPI api) {

		
		// Set up the fleets so we can add ships and fighter wings to them.
		// In this scenario, the fleets are attacking each other, but
		// in other scenarios, a fleet may be defending or trying to escape
		api.initFleet(FleetSide.PLAYER, "SYS", FleetGoal.ATTACK, false);
		api.initFleet(FleetSide.ENEMY, "ISS", FleetGoal.ATTACK, true);

		// Set a small blurb for each fleet that shows up on the mission detail and
		// mission results screens to identify each side.
		api.setFleetTagline(FleetSide.PLAYER, "Shadowyards Patrol");
		api.setFleetTagline(FleetSide.ENEMY, "Pirate/Luddite Raiders");
		
		// These show up as items in the bulleted list under 
		// "Tactical Objectives" on the mission detail screen
		api.addBriefingItem("Drive off the enemy raiders.");
		
		// Set up the player's fleet.  Variant names come from the
		// files in data/variants and data/variants/fighters
		api.addToFleet(FleetSide.PLAYER, "ms_scylla_Standard", FleetMemberType.SHIP, "SYS Just Leaving", true, CrewXPLevel.VETERAN); //17
		api.addToFleet(FleetSide.PLAYER, "ms_charybdis_Balanced", FleetMemberType.SHIP, "SYS Wanders Like Thought", false, CrewXPLevel.VETERAN).getCaptain().setPersonality("steady"); //18
		api.addToFleet(FleetSide.PLAYER, "ms_enlil_Standard", FleetMemberType.SHIP, "SYS Syd Real", false, CrewXPLevel.ELITE).getCaptain().setPersonality("cautious"); //5
		api.addToFleet(FleetSide.PLAYER, "ms_seski_BR", FleetMemberType.SHIP, "SYS Sass Master", false, CrewXPLevel.ELITE).getCaptain().setPersonality("steady"); //4
		api.addToFleet(FleetSide.PLAYER, "ms_shamash_Attack", FleetMemberType.SHIP, "SYS Ball o' Fire", false, CrewXPLevel.ELITE).getCaptain().setPersonality("aggressive"); //7
		
		api.addToFleet(FleetSide.PLAYER, "ms_skinwalker_wing", FleetMemberType.FIGHTER_WING, false); //6
		api.addToFleet(FleetSide.PLAYER, "ms_skinwalker_wing", FleetMemberType.FIGHTER_WING, false); //6
		api.addToFleet(FleetSide.PLAYER, "ms_neriad_wing", FleetMemberType.FIGHTER_WING, false); //4
		api.addToFleet(FleetSide.PLAYER, "ms_raksasha_wing", FleetMemberType.FIGHTER_WING, false); //8
		
		//api.addToFleet(FleetSide.PLAYER, "mining_drone_wing", FleetMemberType.FIGHTER_WING, false);
		//api.addToFleet(FleetSide.PLAYER, "mining_drone_wing", FleetMemberType.FIGHTER_WING, false);
		//api.addToFleet(FleetSide.PLAYER, "mining_drone_wing", FleetMemberType.FIGHTER_WING, false);
		//api.addToFleet(FleetSide.PLAYER, "longbow_wing", FleetMemberType.FIGHTER_WING, false);
		//api.addToFleet(FleetSide.PLAYER, "longbow_wing", FleetMemberType.FIGHTER_WING, false);
		//api.addToFleet(FleetSide.PLAYER, "longbow_wing", FleetMemberType.FIGHTER_WING, false);
		//api.addToFleet(FleetSide.PLAYER, "longbow_wing", FleetMemberType.FIGHTER_WING, false);
		
		// Mark both ships as essential - losing either one results
		// in mission failure. Could also be set on an enemy ship,
		// in which case destroying it would result in a win.
		
		// Set up the enemy fleet.
		// The enemies ships are all pretty weak.  But there are quite a few of them.
		api.addToFleet(FleetSide.ENEMY, "dominator_Outdated", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");
		api.addToFleet(FleetSide.ENEMY, "condor_FS", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");
		api.addToFleet(FleetSide.ENEMY, "enforcer_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");
		api.addToFleet(FleetSide.ENEMY, "enforcer_Outdated", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");
		api.addToFleet(FleetSide.ENEMY, "enforcer_CS", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");
		api.addToFleet(FleetSide.ENEMY, "lasher_CS", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");
		api.addToFleet(FleetSide.ENEMY, "lasher_CS", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");
		api.addToFleet(FleetSide.ENEMY, "hound_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");
		api.addToFleet(FleetSide.ENEMY, "hound_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");
		api.addToFleet(FleetSide.ENEMY, "hound_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");
		api.addToFleet(FleetSide.ENEMY, "hound_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");
		
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		
		//api.addToFleet(FleetSide.ENEMY, "lasher_CS", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");
		//api.addToFleet(FleetSide.ENEMY, "hound_Assault", FleetMemberType.SHIP, false);
		//api.addToFleet(FleetSide.ENEMY, "broadsword_wing", FleetMemberType.FIGHTER_WING, false);
		
//		api.addToFleet(FleetSide.ENEMY, "mining_drone_wing", FleetMemberType.FIGHTER_WING, false).getCaptain().setPersonality("suicidal");
//		api.addToFleet(FleetSide.ENEMY, "mining_drone_wing", FleetMemberType.FIGHTER_WING, false).getCaptain().setPersonality("suicidal");
//		api.addToFleet(FleetSide.ENEMY, "mining_drone_wing", FleetMemberType.FIGHTER_WING, false).getCaptain().setPersonality("suicidal");
//		api.addToFleet(FleetSide.ENEMY, "mining_drone_wing", FleetMemberType.FIGHTER_WING, false).getCaptain().setPersonality("suicidal");
		
		
		
		//api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false).getCaptain().setPersonality("suicidal");
		
		// Set up the map.
		// 12000x8000 is actually somewhat small, making for a faster-paced mission.
		float width = 20000f;
		float height = 18000f;
		api.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);
		
		float minX = -width/2;
		float minY = -height/2;
		
		// All the addXXX methods take a pair of coordinates followed by data for
		// whatever object is being added.
		
		// Add a big nebula cloud
		api.addNebula(minX + width * 0.65f, minY + height * 0.45f, 4000);
		
		// And a few random ones to spice up the playing field.
		// A similar approach can be used to randomize everything
		// else, including fleet composition.
		for (int i = 0; i < 5; i++) {
			float x = (float) Math.random() * width - width/2;
			float y = (float) Math.random() * height - height/2;
			float radius = 100f + (float) Math.random() * 400f; 
			api.addNebula(x, y, radius);
		}
		
		// Add objectives. These can be captured by each side
		// and provide stat bonuses and extra command points to
		// bring in reinforcements.
		// Reinforcements only matter for large fleets - in this
		// case, assuming a 100 command point battle size,
		// both fleets will be able to deploy fully right away.
		api.addObjective(minX + width * 0.75f, minY + height * 0.25f, 
						 "sensor_array");
		api.addObjective(minX + width * 0.6f, minY + height * 0.7f, 
						 "nav_buoy");
		api.addObjective(minX + width * 0.25f, minY + height * 0.75f, 
						 "sensor_array");
		api.addObjective(minX + width * 0.4f, minY + height * 0.3f, 
						 "nav_buoy");
		
		// Add an asteroid field going diagonally across the
		// battlefield, 2000 pixels wide, with a maximum of 
		// 100 asteroids in it.
		// 20-70 is the range of asteroid speeds.
		api.addAsteroidField(minY, minY, 25, 3000f,
								20f, 70f, 120);
		
		// Add some planets.  These are defined in data/config/planets.json.
		api.addPlanet(minX + width * 0.55f, minY + height * 0.25f, 200f, "ice_giant", 200f);
		api.addPlanet(minX + width * 0.6f, minY + height * 0.3f, 75f, "lava", 200f);
	}

}
