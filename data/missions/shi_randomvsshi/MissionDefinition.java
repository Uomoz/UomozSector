package data.missions.shi_randomvsshi;

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
				api.addToFleet(side, "eagle_Assault", FleetMemberType.SHIP, true);
				makeFlagship = false;
			}
		}
	}
	
	public void defineMission(MissionDefinitionAPI api) {
	
		addShip1("doom_Strike", 3);
		addShip1("shade_Assault", 7);
		addShip1("afflictor_Strike", 7);
		addShip1("hyperion_Attack", 3);
		addShip1("hyperion_Strike", 3);
		addShip1("onslaught_Standard", 3);
		addShip1("onslaught_Outdated", 3);
		addShip1("onslaught_Elite", 1);
		addShip1("astral_Elite", 3);
		addShip1("paragon_Elite", 1);
		addShip1("odyssey_Balanced", 2);
		addShip1("conquest_Elite", 3);
		addShip1("eagle_Assault", 0);
		addShip1("falcon_Attack", 5);
		addShip1("venture_Balanced", 5);
		addShip1("apogee_Balanced", 5);
		addShip1("aurora_Balanced", 5);
		addShip1("aurora_Balanced", 5);
		addShip1("dominator_Assault", 5);
		addShip1("dominator_Support", 5);
		addShip1("medusa_Attack", 5);
		addShip1("condor_Strike", 15);
		addShip1("condor_FS", 15);
		addShip1("enforcer_Assault", 15);
		addShip1("enforcer_CS", 15);
		addShip1("hammerhead_Balanced", 10);
		addShip1("hammerhead_Elite", 5);
		addShip1("sunder_CS", 10);
		addShip1("gemini_Standard", 8);
		addShip1("buffalo2_FS", 20);
		addShip1("lasher_CS", 20);
		addShip1("lasher_Standard", 20);
		addShip1("hound_Assault", 15);
		addShip1("tempest_Attack", 15);
		addShip1("brawler_Assault", 15);
		addShip1("wolf_CS", 2);
		addShip1("hyperion_Strike", 1);
		addShip1("vigilance_Standard", 10);
		addShip1("vigilance_FS", 15);
		addShip1("tempest_Attack", 2);
		addShip1("brawler_Assault", 10);
		addShip1("piranha_wing", 15);
		addShip1("talon_wing", 20);
		addShip1("broadsword_wing", 10);
		addShip1("mining_drone_wing", 10);
		addShip1("wasp_wing", 10);
		addShip1("xyphos_wing", 10);
		addShip1("thunder_wing", 10);
		addShip1("dagger_wing", 10);
		addShip1("thunder_wing", 5);
		addShip1("gladius_wing", 15);
		addShip1("warthog_wing", 5);	
		
		addShip2("ms_skinwalker_wing", 12);
		addShip2("ms_morningstar_Standard", 6);		
		addShip2("ms_enlil_Attack", 14);
		addShip2("ms_elysium_Standard", 9);
		addShip2("ms_charybdis_Standard", 0);
		addShip2("ms_elysium_Strike", 5);
		addShip2("ms_scylla_Standard", 7);
		addShip2("ms_mimir_CS", 5);
		addShip2("ms_enlil_Standard", 11);
		addShip2("ms_enlil_AF", 11);
		addShip2("ms_tartarus_Standard", 14);
		addShip2("ms_tartarus_CS", 8);
		addShip2("ms_morningstar_PD", 5);
		addShip2("ms_charybdis_PD", 4);
		addShip2("ms_sargasso_Standard", 6);
		addShip2("ms_seski_Standard", 6);
		addShip2("ms_seski_BR", 5);
		addShip2("ms_inanna_Assault", 6);
		addShip2("ms_morningstar_Strike", 4);
		addShip2("ms_sargasso_Balanced", 3);
		addShip2("ms_skinwalker_wing", 19);
		addShip2("ms_enlil_Standard", 20);
		addShip2("ms_shamash_Attack", 12);
		addShip2("ms_scylla_Beam", 4);
		addShip2("ms_neriad_wing", 3);
		addShip2("ms_neriad_wing", 13);
		addShip2("ms_raksasha_wing", 15);
		addShip2("ms_enlil_Strike", 12);
		
		

		
		// Set up the fleets so we can add ships and fighter wings to them.
		// In this scenario, the fleets are attacking each other, but
		// in other scenarios, a fleet may be defending or trying to escape
		api.initFleet(FleetSide.PLAYER, "ISS", FleetGoal.ATTACK, false, 5);
		api.initFleet(FleetSide.ENEMY, "SYS", FleetGoal.ATTACK, true, 5);

		// Set a small blurb for each fleet that shows up on the mission detail and
		// mission results screens to identify each side.
		api.setFleetTagline(FleetSide.PLAYER, "Shadowyards Fleet");
		api.setFleetTagline(FleetSide.ENEMY, "Enemy fleet");
		
		// These show up as items in the bulleted list under 
		// "Tactical Objectives" on the mission detail screen
		api.addBriefingItem("Defeat the enemy fleet.");
		
		// Set up the fleets
		generateFleet(50 + (int)((float) Math.random() * 30), FleetSide.PLAYER, ships1, api);
		generateFleet(60 + (int)((float) Math.random() * 40), FleetSide.ENEMY, ships2, api);
		
		// Set up the map.
		float width = 24000f;
		float height = 18000f;
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
