package data.missions.stricken;

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
		api.initFleet(FleetSide.PLAYER, "HSS", FleetGoal.ATTACK, false);
		api.initFleet(FleetSide.ENEMY, "SYS", FleetGoal.ATTACK, true);

		// Set a small blurb for each fleet that shows up on the mission detail and
		// mission results screens to identify each side.
		api.setFleetTagline(FleetSide.PLAYER, "Hegemony Patrol");
		api.setFleetTagline(FleetSide.ENEMY, "Shadowyards Security Group");
		
		// These show up as items in the bulleted list under 
		// "Tactical Objectives" on the mission detail screen
		api.addBriefingItem("Destroy the enemy");
		
		// Set up the player's fleet.  Variant names come from the
		// files in data/variants and data/variants/fighters
		api.addToFleet(FleetSide.PLAYER, "hammerhead_Balanced", FleetMemberType.SHIP, "HSS Pursuant", true);
		api.addToFleet(FleetSide.PLAYER, "condor_Strike", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "brawler_Assault", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "brawler_Assault", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "lasher_CS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "hound_Assault", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "hound_Assault", FleetMemberType.SHIP, false);
		
		api.addToFleet(FleetSide.PLAYER, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		//api.addToFleet(FleetSide.PLAYER, "longbow_wing", FleetMemberType.FIGHTER_WING, false);
		//api.addToFleet(FleetSide.PLAYER, "longbow_wing", FleetMemberType.FIGHTER_WING, false);
		//api.addToFleet(FleetSide.PLAYER, "longbow_wing", FleetMemberType.FIGHTER_WING, false);
		
		// Mark both ships as essential - losing either one results
		// in mission failure. Could also be set on an enemy ship,
		// in which case destroying it would result in a win.
		
		// Set up the enemy fleet.
		// Numbers are about even but their ships should be better.
		api.addToFleet(FleetSide.ENEMY, "ms_morningstar_CS", FleetMemberType.SHIP, "SYS Hypnotized", false);
		api.addToFleet(FleetSide.ENEMY, "ms_sargasso_Balanced", FleetMemberType.SHIP, "SYS Stand In", false);
		api.addToFleet(FleetSide.ENEMY, "ms_enlil_Attack", FleetMemberType.SHIP, "SYS Incongrous", false);
		api.addToFleet(FleetSide.ENEMY, "ms_enlil_Attack", FleetMemberType.SHIP, "SYS Hob Knobber", false);
		api.addToFleet(FleetSide.ENEMY, "ms_seski_Standard", FleetMemberType.SHIP, "SYS Severely Sarcastic", false);
		api.addToFleet(FleetSide.ENEMY, "ms_seski_BR", FleetMemberType.SHIP, "SYS Catch Me", false);
		api.addToFleet(FleetSide.ENEMY, "ms_seski_Attack", FleetMemberType.SHIP, "SYS Unhappenstance", false);
		
		api.addToFleet(FleetSide.ENEMY, "ms_skinwalker_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "ms_skinwalker_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "ms_neriad_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "ms_neriad_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "ms_neriad_wing", FleetMemberType.FIGHTER_WING, false);
		
		
		
		//api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false).getCaptain().setPersonality("suicidal");
		
		// Set up the map.
		// 12000x8000 is actually somewhat small, making for a faster-paced mission.
		float width = 22000f;
		float height = 18000f;
		api.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);
		
		float minX = -width/2;
		float minY = -height/2;
		
		// All the addXXX methods take a pair of coordinates followed by data for
		// whatever object is being added.
		
		// Add two big nebula clouds
		api.addNebula(minX + width * 0.4f, minY + height * 0.3f, 2000);
		api.addNebula(minX + width * 0.25f, minY + height * 0.7f, 1000);
		
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
		api.addObjective(minX + width * 0.8f, minY + height * 0.4f, "nav_buoy");
		api.addObjective(minX + width * 0.2f, minY + height * 0.6f, "nav_buoy");
		api.addObjective(minX + width * 0.5f, minY + height * 0.5f, "comm_relay");
		
		// Add an asteroid field going diagonally across the
		// battlefield, 2000 pixels wide, with a maximum of 
		// 100 asteroids in it.
		// 20-70 is the range of asteroid speeds.
		
		// Add some planets.  These are defined in data/config/planets.json.
	}

}
