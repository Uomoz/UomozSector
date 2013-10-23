package data.missions.randomvsjunk;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.fleet.FleetGoal;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.mission.FleetSide;
import com.fs.starfarer.api.mission.MissionDefinitionAPI;
import com.fs.starfarer.api.mission.MissionDefinitionPlugin;

public class MissionDefinition implements MissionDefinitionPlugin {

	private List ships1 = new ArrayList();
	private List ships2 = new ArrayList();
	private void addShip1(String variant, int weight) {
		for (int i = 0; i < weight; i++) {
			ships1.add(variant);
		}
	}
	private void addShip2(String variant, int weight) {
		for (int i = 0; i < weight; i++) {
			ships2.add(variant);
		}
	}
	
	
	private void generateFleet(int maxFP, FleetSide side, List ships, MissionDefinitionAPI api) {
		int currFP = 0;
		boolean makeFlagship = side == FleetSide.PLAYER;
		while (true) {
			int index = (int)(Math.random() * ships.size());
			String id = (String) ships.get(index);
			currFP += api.getFleetPointCost(id);
			if (currFP > maxFP) {
				return;
			}
			
			if (id.endsWith("_wing")) {
				api.addToFleet(side, id, FleetMemberType.FIGHTER_WING, false);
			} else {
				api.addToFleet(side, id, FleetMemberType.SHIP, false);
			}
			if (makeFlagship) {
				api.addToFleet(side, "medusa_Attack", FleetMemberType.SHIP, true);
				makeFlagship = false;
			}
		}
	}
	
	public void defineMission(MissionDefinitionAPI api) {

		addShip2("junk_pirates_hammer_Strike", 12);
		addShip2("junk_pirates_hammer_Assault", 14);
		addShip1("junk_pirates_turbot_Strike", 4);
		addShip1("junk_pirates_turbot_Assault", 3);
		addShip2("junk_pirates_sickle_Strike", 10);
		addShip2("junk_pirates_orca_Standard", 2);
		addShip2("junk_pirates_orca_Assault", 1);
		addShip2("junk_pirates_sickle_Standard", 11);
		addShip2("junk_pirates_clam_Standard", 11);
		addShip2("junk_pirates_clam_CS", 9);
		addShip2("junk_pirates_sickle_Pointdefense", 14);
		addShip2("junk_pirates_octopus_Standard", 8);
		addShip2("junk_pirates_dugong_Standard", 3);
		addShip2("junk_pirates_dugong_Support", 3);
		addShip2("junk_pirates_boxer_Fighter", 6);
		addShip2("junk_pirates_boxer_Standard", 6);
		addShip2("junk_pirates_boxenstein_Slugger", 3);
		addShip2("junk_pirates_boxenstein_Support", 3);
		addShip2("junk_pirates_cleat_wing", 20);
		addShip2("junk_pirates_spike_wing", 40);
		addShip2("junk_pirates_langoustine_Strike", 6);
		addShip2("junk_pirates_langoustine_Standard", 6);
		addShip2("junk_pirates_shard_wing", 15);

		
		addShip1("onslaught_Standard", 1);
		addShip1("onslaught_Outdated", 0);
		addShip1("venture_Balanced", 4);
		addShip1("medusa_Attack", 0);
		addShip1("aurora_Balanced", 0);
		addShip1("dominator_Assault", 5);
		addShip1("dominator_Support", 5);
		addShip1("condor_Strike", 5);
		addShip1("condor_FS", 8);
		addShip1("lasher_CS", 10);
		addShip1("hound_Assault", 15);
		addShip1("enforcer_Assault", 5);
		addShip1("hammerhead_Balanced", 2);
		addShip1("hammerhead_Elite", 2);
		addShip1("astral_Elite", 0);
		addShip1("conquest_Elite", 0);
		addShip1("eagle_Assault", 3);
		addShip1("eagle_Balanced", 2);
		addShip1("falcon_CS", 4);
		addShip1("sunder_CS", 7);
		addShip1("gemini_Standard", 2);
		addShip1("wolf_CS", 8);
		addShip1("hyperion_Strike", 1);
		addShip1("vigilance_Strike", 4);
		addShip1("vigilance_FS", 4);
		addShip1("tempest_Attack", 4);
		addShip1("brawler_Assault", 10);
		addShip1("piranha_wing", 10);
		addShip1("talon_wing", 15);
		addShip1("broadsword_wing", 5);
		addShip1("mining_drone_wing", 10);
		addShip1("wasp_wing", 2);
		addShip1("xyphos_wing", 2);
		addShip1("longbow_wing", 10);
		addShip1("dagger_wing", 1);
		addShip1("thunder_wing", 0);
		addShip1("gladius_wing", 0);
		addShip1("warthog_wing", 8);		
		
		// Set up the fleets so we can add ships and fighter wings to them.
		// In this scenario, the fleets are attacking each other, but
		// in other scenarios, a fleet may be defending or trying to escape
		api.initFleet(FleetSide.PLAYER, "ISS", FleetGoal.ATTACK, false, 5);
		api.initFleet(FleetSide.ENEMY, "ISS", FleetGoal.ATTACK, true, 5);

		// Set a small blurb for each fleet that shows up on the mission detail and
		// mission results screens to identify each side.
		api.setFleetTagline(FleetSide.PLAYER, "Your forces");
		api.setFleetTagline(FleetSide.ENEMY, "Enemy forces");
		
		// These show up as items in the bulleted list under 
		// "Tactical Objectives" on the mission detail screen
		api.addBriefingItem("Defeat all enemy forces");
		
		// Set up the fleets
		generateFleet(65 + (int)((float) Math.random() * 30), FleetSide.PLAYER, ships1, api);
		generateFleet(55 + (int)((float) Math.random() * 40), FleetSide.ENEMY, ships2, api);
		
		// Set up the map.
		float width = 18000f;
		float height = 12000f;
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
		api.addObjective(minX + width * 0.75f, minY + height * 0.25f, "comm_relay");
		api.addObjective(minX + width * 0.75f, minY + height * 0.75f, "nav_buoy");
		api.addObjective(minX + width * 0.25f, minY + height * 0.75f, "comm_relay");
		api.addObjective(minX + width * 0.5f, minY + height * 0.5f, "sensor_array");
		
		// Add a randomized asteroid field, maybe
//		if ((float) Math.random() > 0.5f) {
//			api.addAsteroidField(10f + (float) Math.random() * (width - 20f), 10f + (float) Math.random() * (height - 20f), (float) Math.random() * 360f, (float) Math.random() * 1000 + 1000,
//								 20f, 70f, (int) ((float) Math.random() * 100 + 50));
//		}
	}

}
