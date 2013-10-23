package data.missions.culmination;

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
		api.initFleet(FleetSide.ENEMY, "ISS", FleetGoal.ATTACK, true);

		// Set a small blurb for each fleet that shows up on the mission detail and
		// mission results screens to identify each side.
		api.setFleetTagline(FleetSide.PLAYER, "Junk Pirates makeshift personnel and cargo fleet");
		api.setFleetTagline(FleetSide.ENEMY, "Mercenary attack fleet");
		
		// These show up as items in the bulleted list under 
		// "Tactical Objectives" on the mission detail screen
		api.addBriefingItem("Counter-attack the mercenary fleet - The base at Breh'inni 2 has to be protected");
		api.addBriefingItem("You're fleet isn't expecting this level of combat, use each ship wisely, and carefully");
		
		// Set up the player's fleet.  Variant names come from the
		// files in data/variants and data/variants/fighters
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_goat_Standard", FleetMemberType.SHIP, "RNS Devon Malcolm", true);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_scythe_Standard", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_scythe_Assault", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_clam_Standard", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_clam_CS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_sickle_Pointdefense", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_sickle_Pointdefense", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_sickle_Standard", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_boxenstein_Support", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_sickle_Standard", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_octopus_Standard", FleetMemberType.SHIP, false);
		//api.addToFleet(FleetSide.PLAYER, "junk_pirates_octopus_Standard", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_spike_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_spike_wing", FleetMemberType.FIGHTER_WING, false);
		//api.addToFleet(FleetSide.PLAYER, "junk_pirates_spike_wing", FleetMemberType.FIGHTER_WING, false);
		//api.addToFleet(FleetSide.PLAYER, "junk_pirates_spike_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_cleat_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_cleat_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_shard_wing", FleetMemberType.FIGHTER_WING, false);
		
		
		// Set up the enemy fleet.
		// An elite mercenary fleet
		api.addToFleet(FleetSide.ENEMY, "wasp_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "wasp_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "venture_Balanced", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "odyssey_Balanced", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "hammerhead_Balanced", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "lasher_CS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "lasher_CS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "lasher_CS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "broadsword_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "broadsword_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "tempest_Attack", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "tempest_Attack", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "vigilance_FS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "vigilance_FS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "medusa_CS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "medusa_Attack", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "wolf_PD", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "wolf_CS", FleetMemberType.SHIP, false);
		
		// Set up the map.
		// 12000x8000 is actually somewhat small, making for a faster-paced mission.
		float width = 20000f;
		float height = 20000f;
		api.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);
		
		float minX = -width/2;
		float minY = -height/2;
		
		// And a few random ones to spice up the playing field.
		// A similar approach can be used to randomize everything
		// else, including fleet composition.
		for (int i = 0; i < 6; i++) {
			float x = (float) Math.random() * width - width/2;
			float y = (float) Math.random() * height - height/2;
			float radius = 80f + (float) Math.random() * 100f; 
			api.addNebula(x, y, radius);
		}
		
		// Add objectives. These can be captured by each side
		// and provide stat bonuses and extra command points to
		// bring in reinforcements.
		// Reinforcements only matter for large fleets - in this
		// case, assuming a 100 command point battle size,
		// both fleets will be able to deploy fully right away.
		api.addObjective(minX + width * 0.6f, minY + height * 0.38f, 
						 "sensor_array", BattleObjectiveAPI.Importance.NORMAL);
		api.addObjective(minX + width * 0.5f, minY + height * 0.62f, 
						 "nav_buoy", BattleObjectiveAPI.Importance.NORMAL);
		api.addObjective(minX + width * 0.27f, minY + height * 0.5f, 
						 "comm_relay", BattleObjectiveAPI.Importance.NORMAL);
		api.addObjective(minX + width * 0.73f, minY + height * 0.5f, 
						 "comm_relay", BattleObjectiveAPI.Importance.NORMAL);
		
		// Add an asteroid field going diagonally across the
		// battlefield, 2000 pixels wide, with a maximum of 
		// 100 asteroids in it.
		// 20-70 is the range of asteroid speeds.
		// api.addAsteroidField(minX, minY, 45, 3000f,
		//						20f, 70f, 200);
		
		// Add some planets.  These are defined in data/config/planets.json.
		// api.addPlanet(minX + width * 0.75f, minY + height * 0.75f, 300f, "cryovolcanic", 300f);
	}

}
