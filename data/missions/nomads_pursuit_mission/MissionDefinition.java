package data.missions.nomads_pursuit_mission;

import com.fs.starfarer.api.fleet.FleetGoal;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.mission.FleetSide;
import com.fs.starfarer.api.mission.MissionDefinitionAPI;
import com.fs.starfarer.api.mission.MissionDefinitionPlugin;

public class MissionDefinition implements MissionDefinitionPlugin {

	public void defineMission(MissionDefinitionAPI api) {

		// Set up the fleets
		api.initFleet(FleetSide.PLAYER, "NA", FleetGoal.ATTACK, false);
		api.setFleetTagline(FleetSide.PLAYER, "Nomad Roadrunner Pursuit Squad");
		
		api.initFleet(FleetSide.ENEMY, "ISS", FleetGoal.ESCAPE, true);
		api.setFleetTagline(FleetSide.ENEMY, "Independent Trader Convoy");

		api.setHyperspaceMode( true );
		
		// Set up the player's fleet
		api.addToFleet(FleetSide.PLAYER, "nom_roadrunner_pursuit", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.PLAYER, "nom_roadrunner_pursuit", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.PLAYER, "nom_roadrunner_pursuit", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.PLAYER, "nom_roadrunner_pursuit", FleetMemberType.SHIP, true);

		// Set up the enemy fleet
		api.addToFleet(FleetSide.ENEMY, "atlas_Standard", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.ENEMY, "tarsus_Standard", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.ENEMY, "tarsus_Standard", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.ENEMY, "tarsus_Standard", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.ENEMY, "buffalo_Standard", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.ENEMY, "buffalo_Standard", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.ENEMY, "buffalo_Standard", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.ENEMY, "buffalo_Standard", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.ENEMY, "valkyrie_Elite", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.ENEMY, "valkyrie_Elite", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.ENEMY, "valkyrie_Elite", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.ENEMY, "valkyrie_Elite", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.ENEMY, "prometheus_Super", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.ENEMY, "phaeton_Standard", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.ENEMY, "phaeton_Standard", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.ENEMY, "dram_Light", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.ENEMY, "dram_Light", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.ENEMY, "dram_Light", FleetMemberType.SHIP, true);
		
		// Set up the map.
		float width = 16000f;
		float height = 30000f;
		api.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);
		
		float minX = -width/2;
		float minY = -height/2;
		
		
		for (int i = 0; i < 50; i++) {
			float x = (float) Math.random() * width - width/2;
			float y = (float) Math.random() * height - height/2;
			float radius = 100f + (float) Math.random() * 400f; 
			api.addNebula(x, y, radius);
		}
		
		// Add objectives
		api.addObjective(minX + width * 0.25f, minY + height * 0.25f, "nav_buoy");
		api.addObjective(minX + width * 0.5f, minY + height * 0.5f, "sensor_array");
		
		// // Add a randomized asteroid field, maybe
		// if ((float) Math.random() > 0.5f) {
		// 	api.addAsteroidField(10f + (float) Math.random() * (width - 20f), 10f + (float) Math.random() * (height - 20f), (float) Math.random() * 360f, (float) Math.random() * 1000 + 1000,
		// 						 20f, 70f, (int) ((float) Math.random() * 100 + 50));
		// }
	}

}






