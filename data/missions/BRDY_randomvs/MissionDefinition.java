package data.missions.BRDY_randomvs;

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
				api.addToFleet(side, "nevermore_advanced", FleetMemberType.SHIP, true);
				makeFlagship = false;
			}
		}
	}
	
	public void defineMission(MissionDefinitionAPI api) {

		addShip1("brdy_krait_wing", 12);
		addShip1("brdy_stenos_exploration", 6);		
		addShip1("brdy_locust_patrol", 14);
		addShip1("brdy_typheus_support", 8);
		addShip1("nevermore_assault", 0);
		addShip1("brdy_revenant_carrier", 5);
		addShip1("brdy_kurmaraja_elite", 5);
		addShip1("scarab_attack", 11);
		addShip1("scarab_closesupport", 11);
		addShip1("gonodactylus_CS", 14);
		addShip1("gonodactylus_assault", 8);
		addShip1("desdinova_assault", 5);
		addShip1("brdy_revenant_carrier", 4);
		addShip1("gonodactylus_assault", 6);
		addShip1("brdy_mantis_strike", 6);
		addShip1("scarab_attack", 5);
		addShip1("scarab_closesupport", 6);
		addShip1("desdinova_HK", 4);
		addShip1("desdinova_assault", 3);
		addShip1("brdy_krait_wing", 19);
		addShip1("brdy_robberfly_light", 20);
		addShip1("brdy_robberfly_cs", 12);
		addShip1("brdy_serket_wing", 3);
		addShip1("brdy_serket_wing", 13);
		addShip1("brdy_squilla_wing", 15);
		addShip1("brdy_mantis_strike", 12);
		addShip1("brdy_karkinos_prototype", 3);				
		
		
		
		addShip2("doom_Strike", 3);
		addShip2("shade_Assault", 7);
		addShip2("afflictor_Strike", 7);
		addShip2("hyperion_Attack", 3);
		addShip2("hyperion_Strike", 3);
		addShip2("onslaught_Standard", 3);
		addShip2("onslaught_Outdated", 3);
		addShip2("onslaught_Elite", 1);
		addShip2("astral_Elite", 3);
		addShip2("paragon_Elite", 1);
		addShip2("odyssey_Balanced", 2);
		addShip2("conquest_Elite", 3);
		addShip2("eagle_Assault", 5);
		addShip2("falcon_Attack", 5);
		addShip2("venture_Balanced", 5);
		addShip2("apogee_Balanced", 5);
		addShip2("aurora_Balanced", 5);
		addShip2("aurora_Balanced", 5);
		addShip2("dominator_Assault", 5);
		addShip2("dominator_Support", 5);
		addShip2("medusa_Attack", 5);
		addShip2("condor_Strike", 15);
		addShip2("condor_FS", 15);
		addShip2("enforcer_Assault", 15);
		addShip2("enforcer_CS", 15);
		addShip2("hammerhead_Balanced", 10);
		addShip2("hammerhead_Elite", 5);
		addShip2("sunder_CS", 10);
		addShip2("gemini_Standard", 8);
		addShip2("buffalo2_FS", 20);
		addShip2("lasher_CS", 20);
		addShip2("lasher_Standard", 20);
		addShip2("hound_Assault", 15);
		addShip2("tempest_Attack", 15);
		addShip2("brawler_Assault", 15);
		addShip2("brawler_Elite", 11);		
		addShip2("wolf_CS", 2);
		addShip2("hyperion_Strike", 1);
		addShip2("vigilance_Standard", 10);
		addShip2("vigilance_FS", 15);
		addShip2("tempest_Attack", 2);
		addShip2("brawler_Assault", 10);
		addShip2("piranha_wing", 15);
		addShip2("talon_wing", 20);
		addShip2("broadsword_wing", 10);
		addShip2("mining_drone_wing", 10);
		addShip2("wasp_wing", 10);
		addShip2("xyphos_wing", 10);
		addShip2("thunder_wing", 10);
		addShip2("dagger_wing", 10);
		addShip2("thunder_wing", 5);
		addShip2("gladius_wing", 15);
		addShip2("warthog_wing", 5);	
		
		// Set up the fleets so we can add ships and fighter wings to them.
		// In this scenario, the fleets are attacking each other, but
		// in other scenarios, a fleet may be defending or trying to escape
		api.initFleet(FleetSide.PLAYER, "BRS", FleetGoal.ATTACK, false, 5);
		api.initFleet(FleetSide.ENEMY, "ISS", FleetGoal.ATTACK, true, 5);

		// Set a small blurb for each fleet that shows up on the mission detail and
		// mission results screens to identify each side.
		api.setFleetTagline(FleetSide.PLAYER, "Blackrock Fleet");
		api.setFleetTagline(FleetSide.ENEMY, "Enemy fleet");
		
		// These show up as items in the bulleted list under 
		// "Tactical Objectives" on the mission detail screen
		api.addBriefingItem("Defeat the enemy fleet.");
		
		// Set up the fleets
		generateFleet(50 + (int)((float) Math.random() * 30), FleetSide.PLAYER, ships1, api);
		generateFleet(70 + (int)((float) Math.random() * 40), FleetSide.ENEMY, ships2, api);
		
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
