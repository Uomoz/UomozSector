package data.missions.unleashed;

import com.fs.starfarer.api.campaign.CargoAPI.CrewXPLevel;
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
		api.initFleet(FleetSide.PLAYER, "PSS", FleetGoal.ATTACK, false, 5);
		api.initFleet(FleetSide.ENEMY, "HSS", FleetGoal.ATTACK, true);

		// Set a small blurb for each fleet that shows up on the mission detail and
		// mission results screens to identify each side.
		api.setFleetTagline(FleetSide.PLAYER, "P.A.C.K. Special Envoy");
		api.setFleetTagline(FleetSide.ENEMY, "Unknown Hostile Fleet");
		
		// These show up as items in the bulleted list under 
		// "Tactical Objectives" on the mission detail screen
		api.addBriefingItem("Defeat all enemy forces");
		api.addBriefingItem("Your flagship must survive");
		
		// Set up the player's fleet.  Variant names come from the
		// files in data/variants and data/variants/fighters
		api.addToFleet(FleetSide.PLAYER, "pack_ridgeback_x_Standard", FleetMemberType.SHIP, "PSS Rover", true, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "pack_BRT_Guard", FleetMemberType.SHIP, false);
		//api.addToFleet(FleetSide.PLAYER, "pack_ridgeback_x_Standard", FleetMemberType.SHIP, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "pack_wirefox_Standard", FleetMemberType.SHIP, false);
		//api.addToFleet(FleetSide.PLAYER, "pack_wirefox_Standard", FleetMemberType.SHIP, false, CrewXPLevel.VETERAN);
		//api.addToFleet(FleetSide.PLAYER, "pack_wirefox_Standard", FleetMemberType.SHIP, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "pack_bedlington_Standard", FleetMemberType.SHIP, false);
		//api.addToFleet(FleetSide.PLAYER, "pack_bedlington_Standard", FleetMemberType.SHIP, false, CrewXPLevel.VETERAN);
		//api.addToFleet(FleetSide.PLAYER, "pack_bedlington_Standard", FleetMemberType.SHIP, false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "pack_pitbull_Standard", FleetMemberType.SHIP, false);
		//api.addToFleet(FleetSide.PLAYER, "pack_pitbull_Standard", FleetMemberType.SHIP, false, CrewXPLevel.VETERAN);

		api.defeatOnShipLoss("PSS Rover");
		
		// Set up the enemy fleet.
		api.addToFleet(FleetSide.ENEMY, "mule_Standard", FleetMemberType.SHIP, false);
		//api.addToFleet(FleetSide.ENEMY, "tarsus_Standard", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "buffalo2_FS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "broadsword_wing", FleetMemberType.FIGHTER_WING, false);
		
		
		api.addToFleet(FleetSide.ENEMY, "enforcer_Assault", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "lasher_CS", FleetMemberType.SHIP, false);
		//api.addToFleet(FleetSide.ENEMY, "lasher_CS", FleetMemberType.SHIP, false);
		//api.addToFleet(FleetSide.ENEMY, "condor_FS", FleetMemberType.SHIP, false);
		
		//api.addToFleet(FleetSide.ENEMY, "broadsword_wing", FleetMemberType.FIGHTER_WING, false);
		//api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		
		// Set up the map.
		float width = 12000f;
		float height = 8000f;
		api.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);
		
		float minX = -width/2;
		float minY = -height/2;
		
		api.addNebula(minX + width * 0.5f - 300, minY + height * 0.5f, 1000);
		api.addNebula(minX + width * 0.5f + 300, minY + height * 0.5f, 1000);
		
		for (int i = 0; i < 5; i++) {
			float x = (float) Math.random() * width - width/2;
			float y = (float) Math.random() * height - height/2;
			float radius = 100f + (float) Math.random() * 400f; 
			api.addNebula(x, y, radius);
		}
		
////		// Add objectives.
//		api.addObjective(minX + width * 0.75f, minY + height * 0.5f, 
//						 "sensor_array");
//		api.addObjective(minX + width * 0.25f, minY + height * 0.5f, 
//						 "nav_buoy");
//		
		// Add an asteroid field
		api.addAsteroidField(minX + width/2f, minY + height/2f, 0, 8000f,
								20f, 70f, 100);
		
	}

}




