package data.missions.BRDY_hegemony;

import com.fs.starfarer.api.campaign.CargoAPI.CrewXPLevel;
import com.fs.starfarer.api.fleet.FleetGoal;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.mission.FleetSide;
import com.fs.starfarer.api.mission.MissionDefinitionAPI;
import com.fs.starfarer.api.mission.MissionDefinitionPlugin;

public class MissionDefinition implements MissionDefinitionPlugin {

	public void defineMission(MissionDefinitionAPI api) {

		// Set up the fleets
		api.initFleet(FleetSide.PLAYER, "BRS", FleetGoal.ATTACK, false);
		api.initFleet(FleetSide.ENEMY, "HSS", FleetGoal.ATTACK, true);

		// Set a blurb for each fleet
		api.setFleetTagline(FleetSide.PLAYER, "Blackrock Drive Yards Escort");
		api.setFleetTagline(FleetSide.ENEMY, "Hegemony Strike Force");

		// These show up as items in the bulleted list under
		// "Tactical Objectives" on the mission detail screen
		api.addBriefingItem("Disable, destroy or drive off all enemy vessels.");

		// Set up the player's fleet
		api.addToFleet(FleetSide.PLAYER, "desdinova_assault", FleetMemberType.SHIP, "BRS Ajakutty", true);
		api.addToFleet(FleetSide.PLAYER, "brdy_mantis_strike", FleetMemberType.SHIP, "BRS Jashugan", false);
		api.addToFleet(FleetSide.PLAYER, "brdy_revenant_carrier", FleetMemberType.SHIP, "BRS Rockeye", false);
		api.addToFleet(FleetSide.PLAYER, "brdy_locust_patrol", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "brdy_serket_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "brdy_squilla_wing", FleetMemberType.FIGHTER_WING, false);


		// Set up the enemy fleet
		api.addToFleet(FleetSide.ENEMY, "thunder_wing", FleetMemberType.FIGHTER_WING, false);
	    api.addToFleet(FleetSide.ENEMY, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "onslaught_Outdated", FleetMemberType.SHIP, "HSS Rajah II", false);
		api.addToFleet(FleetSide.ENEMY, "venture_Balanced", FleetMemberType.SHIP, false);
        api.addToFleet(FleetSide.ENEMY, "enforcer_CS", FleetMemberType.SHIP, false);

		// Set up the map.
		float width = 12000f;
		float height = 8000f;
		api.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);

		float minX = -width/2;
		float minY = -height/2;

		for (int i = 0; i < 100; i++) {
			float x = (float) Math.random() * width - width/2;
			float y = (float) Math.random() * height - height/4;

			if (x > -1000 && x < 1500 && y < -1000) continue;
			float radius = 200f + (float) Math.random() * 900f;
			api.addNebula(x, y, radius);
		}


		//api.addNebula(minX + width * 0.5f, minY + height * 0.5f, 20000);

		api.addObjective(minX + width * 0.7f, minY + height * 0.65f, "nav_buoy");
		api.addObjective(minX + width * 0.5f, minY + height * 0.35f, "nav_buoy");
		api.addObjective(minX + width * 0.2f, minY + height * 0.6f, "sensor_array");

	}

}

