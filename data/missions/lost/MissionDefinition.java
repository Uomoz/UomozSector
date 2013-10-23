package data.missions.lost;

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
		api.setFleetTagline(FleetSide.PLAYER, "The RNS Abalone and the RNS Whelk");
		api.setFleetTagline(FleetSide.ENEMY, "Cult of Lud forces");
		
		// These show up as items in the bulleted list under 
		// "Tactical Objectives" on the mission detail screen
		api.addBriefingItem("Raid the Cult of Lud for their fuel");
		api.addBriefingItem("The RNS Abalone must survive");
		
		// Set up the player's fleet.  Variant names come from the
		// files in data/variants and data/variants/fighters
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_langoustine_Standard", FleetMemberType.SHIP, "RNS Abalone", true);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_sickle_Pointdefense", FleetMemberType.SHIP, "RNS Whelk", false);
		//api.addToFleet(FleetSide.PLAYER, "junk_pirates_shard_wing", FleetMemberType.FIGHTER_WING, false);
		//api.addToFleet(FleetSide.PLAYER, "junk_pirates_shard_wing", FleetMemberType.FIGHTER_WING, false);
		// api.addToFleet(FleetSide.PLAYER, "junk_pirates_octopus_Standard", FleetMemberType.SHIP, false);
		
		// Mark both ships as essential - losing either one results
		// in mission failure. Could also be set on an enemy ship,
		// in which case destroying it would result in a win.
		api.defeatOnShipLoss("RNS Abalone");
		// api.defeatOnShipLoss("RNS Beeswax");
		
		// Set up the enemy fleet.
		// It's got more ships than the player's, but they're not as strong.
		// api.addToFleet(FleetSide.ENEMY, "hammerhead_Balanced", FleetMemberType.SHIP, "ISS Black Star", false);
		api.addToFleet(FleetSide.ENEMY, "condor_Strike", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "hound_Assault", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "hound_Assault", FleetMemberType.SHIP, false);
		// api.addToFleet(FleetSide.ENEMY, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "broadsword_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);

		
		
		// Set up the map.
		// 12000x8000 is actually somewhat small, making for a faster-paced mission.
		float width = 6000f;
		float height = 6000f;
		api.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);
		
		float minX = -width/2;
		float minY = -height/2;
		
		// And a few random ones to spice up the playing field.
		// A similar approach can be used to randomize everything
		// else, including fleet composition.
		
		api.addNebula(minX + width * 0.4f, minY + height * 0.4f, 1200);
		api.addNebula(minX + width * 0.5f, minY + height * 0.5f, 1100);
		api.addNebula(minX + width * 0.6f, minY + height * 0.6f, 1000);
		
		// Add objectives. These can be captured by each side
		// and provide stat bonuses and extra command points to
		// bring in reinforcements.
		// Reinforcements only matter for large fleets - in this
		// case, assuming a 100 command point battle size,
		// both fleets will be able to deploy fully right away.
		api.addObjective(minX + width * 0.25f, minY + height * 0.25f, "sensor_array");
		api.addObjective(minX + width * 0.75f, minY + height * 0.75f, "nav_buoy");
		//api.addObjective(minX + width * 0.4f, minY + height * 0.5f, "sensor_array");
		//api.addObjective(minX + width * 0.6f, minY + height * 0.5f, "nav_buoy");
		
		// Add an asteroid field going diagonally across the
		// battlefield, 2000 pixels wide, with a maximum of 
		// 100 asteroids in it.
		// 20-70 is the range of asteroid speeds.
		//api.addAsteroidField(minX, minY, 45, 3000f,
		//						20f, 70f, 200);
		
		// Add some planets.  These are defined in data/config/planets.json.
		api.addPlanet(minX + width * 0.6f, minY + height * 0.25f, 300f, "desert", 300f);
	}

}
