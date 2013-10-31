package data.missions.nomads_demo;

import com.fs.starfarer.api.fleet.FleetGoal;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.mission.FleetSide;
import com.fs.starfarer.api.mission.MissionDefinitionAPI;
import com.fs.starfarer.api.mission.MissionDefinitionPlugin;

public class MissionDefinition implements MissionDefinitionPlugin
{
	public void defineMission(MissionDefinitionAPI api)
	{
		// Set up the fleets
		api.initFleet(FleetSide.PLAYER, "NA", FleetGoal.ATTACK, false);
		api.setFleetTagline(FleetSide.PLAYER, "The Nomads (Demo Fleet)");
		
		api.initFleet(FleetSide.ENEMY, "ISS", FleetGoal.ATTACK, true);
		api.setFleetTagline(FleetSide.ENEMY, "Demo Forces");

		// Set up the player's fleet
		api.addToFleet(FleetSide.PLAYER, "nom_oasis_standard", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.PLAYER, "nom_gila_monster_antibattleship", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.PLAYER, "nom_sandstorm_assault", FleetMemberType.SHIP, true); 
		api.addToFleet(FleetSide.PLAYER, "nom_rattlesnake_assault", FleetMemberType.SHIP, true); 
		api.addToFleet(FleetSide.PLAYER, "nom_scorpion_assault", FleetMemberType.SHIP, true); 
		api.addToFleet(FleetSide.PLAYER, "nom_komodo_mk2_assault", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.PLAYER, "nom_komodo_assault", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.PLAYER, "nom_roadrunner_pursuit", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.PLAYER, "nom_flycatcher_carrier", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.PLAYER, "nom_death_bloom_strike", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.PLAYER, "nom_yellowjacket_sniper", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.PLAYER, "nom_wurm_assault", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.PLAYER, "nom_fang_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "nom_toad_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "nom_iguana_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "nom_scarab_wing", FleetMemberType.FIGHTER_WING, false);

		// Set up the enemy fleet
		api.addToFleet(FleetSide.ENEMY, "onslaught_Outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "dominator_Assault", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "hammerhead_Balanced", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "hammerhead_Elite", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "hound_Assault", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "hound_Assault", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "lasher_CS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "lasher_CS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "wasp_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		
		// Set up the map.
		float width = 12000f;
		float height = 16000f;
		api.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);
	}

}






