package data.missions.thebender;

import com.fs.starfarer.api.campaign.CargoAPI.CrewXPLevel;
import com.fs.starfarer.api.fleet.FleetGoal;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.mission.FleetSide;
import com.fs.starfarer.api.mission.MissionDefinitionAPI;
import com.fs.starfarer.api.mission.MissionDefinitionPlugin;

public class MissionDefinition implements MissionDefinitionPlugin {

	public void defineMission(MissionDefinitionAPI api) {

		api.initFleet(FleetSide.PLAYER, "ISS", FleetGoal.ATTACK, false);
		api.initFleet(FleetSide.ENEMY, "HIP", FleetGoal.ATTACK, true);

		api.setFleetTagline(FleetSide.PLAYER, "ISS Boozehound and fighter escorts.");
		api.setFleetTagline(FleetSide.ENEMY, "The Fuzz");
		
		api.addBriefingItem("Defeat all enemy forces");
		api.addBriefingItem("Prove your innocence");
		api.addBriefingItem("ISS Boozehound must survive");
		
		api.addToFleet(FleetSide.PLAYER, "ms_charybdis_Attack", FleetMemberType.SHIP, "ISS Boozehound", true, CrewXPLevel.VETERAN);
		
		api.addToFleet(FleetSide.PLAYER, "xyphos_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "xyphos_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "dagger_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "dagger_wing", FleetMemberType.FIGHTER_WING, false);
		
		api.defeatOnShipLoss("ISS Boozehound");

		// Set up the enemy fleet.
		// It's got more ships than the player's, but they're not as strong.
		api.addToFleet(FleetSide.ENEMY, "falcon_CS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "condor_Strike", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "brawler_Assault", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "brawler_Assault", FleetMemberType.SHIP, false);

		
		api.addToFleet(FleetSide.ENEMY, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "broadsword_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		
		
		float width = 18000f;
		float height = 14000f;
		api.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);
		
		float minX = -width/2;
		float minY = -height/2;
		
		api.addNebula(minX + width * 0.75f, minY + height * 0.5f, 2000);
		api.addNebula(minX + width * 0.25f, minY + height * 0.5f, 1400);
		
		for (int i = 0; i < 5; i++) {
			float x = (float) Math.random() * width - width/2;
			float y = (float) Math.random() * height - height/2;
			float radius = 100f + (float) Math.random() * 400f; 
			api.addNebula(x, y, radius);
		}
		
		api.addObjective(minX + width * 0.75f, minY + height * 0.5f, 
						 "sensor_array");
		api.addObjective(minX + width * 0.25f, minY + height * 0.5f, 
						 "nav_buoy");
		
		api.addAsteroidField(minY, minY, 45, 2000f,
								20f, 70f, 100);

		api.addPlanet(minX + width * 0.2f, minY + height * 0.8f, 320f, "star_yellow", 300f);
		api.addPlanet(minX + width * 0.8f, minY + height * 0.8f, 256f, "desert", 250f);
		api.addPlanet(minX + width * 0.55f, minY + height * 0.25f, 200f, "cryovolcanic", 200f);
	}

}
