package data.missions.misdirected;

import com.fs.starfarer.api.campaign.CargoAPI.CrewXPLevel;
import com.fs.starfarer.api.fleet.FleetGoal;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.mission.FleetSide;
import com.fs.starfarer.api.mission.MissionDefinitionAPI;
import com.fs.starfarer.api.mission.MissionDefinitionPlugin;

public class MissionDefinition implements MissionDefinitionPlugin {

	public void defineMission(MissionDefinitionAPI api) {

		api.initFleet(FleetSide.PLAYER, "HSS", FleetGoal.ATTACK, false);
		api.initFleet(FleetSide.ENEMY, "SYS", FleetGoal.ATTACK, true);

		api.setFleetTagline(FleetSide.PLAYER, "HSS Algiers and Escorts");
		api.setFleetTagline(FleetSide.ENEMY, "SYS Task Force");
		
		//Player Mission briefing bullet points.
		api.addBriefingItem("Evade or destroy enemy forces");
		api.addBriefingItem("The HSS Algiers must survive");

		//First, the unfortunate Algiers and its nonexistant escort.
		api.addToFleet(FleetSide.PLAYER, "onslaught_Elite", FleetMemberType.SHIP, "HSS Algiers", true, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "condor_Strike", FleetMemberType.SHIP, "HSS Holistic IX", false).getCaptain().setPersonality("cautious");
		api.addToFleet(FleetSide.PLAYER, "lasher_CS", FleetMemberType.SHIP, "HSS Halyard", true);
		api.addToFleet(FleetSide.PLAYER, "lasher_CS", FleetMemberType.SHIP, "HSS Dao", true);
		
		api.addToFleet(FleetSide.PLAYER, "broadsword_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		
		api.defeatOnShipLoss("HSS Algiers");

		// Shadowyards attack group.
		api.addToFleet(FleetSide.ENEMY, "ms_charybdis_Standard", FleetMemberType.SHIP, false).getCaptain().setPersonality("steady");
		api.addToFleet(FleetSide.ENEMY, "ms_elysium_Strike", FleetMemberType.SHIP, false).getCaptain().setPersonality("fearless");
		api.addToFleet(FleetSide.ENEMY, "ms_elysium_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality("cautious");
		api.addToFleet(FleetSide.ENEMY, "ms_morningstar_Strike", FleetMemberType.SHIP, false).getCaptain().setPersonality("aggressive");
		api.addToFleet(FleetSide.ENEMY, "ms_sargasso_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality("steady");
		api.addToFleet(FleetSide.ENEMY, "ms_shamash_Attack", FleetMemberType.SHIP, false).getCaptain().setPersonality("fearless");		
		api.addToFleet(FleetSide.ENEMY, "ms_enlil_CS", FleetMemberType.SHIP, false).getCaptain().setPersonality("cautious");		
		api.addToFleet(FleetSide.ENEMY, "ms_enlil_Standard", FleetMemberType.SHIP, false).getCaptain().setPersonality("aggressive");
		api.addToFleet(FleetSide.ENEMY, "ms_inanna_CS", FleetMemberType.SHIP, false).getCaptain().setPersonality("steady");
	
		api.addToFleet(FleetSide.ENEMY, "ms_skinwalker_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "ms_skinwalker_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "ms_neriad_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "ms_neriad_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "ms_neriad_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "ms_raksasha_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "ms_raksasha_wing", FleetMemberType.FIGHTER_WING, false);
		
		
		float width = 18000f;
		float height = 24000f;
		api.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);
		
		float minX = -width/2;
		float minY = -height/2;
		
		api.addNebula(minX + width * 0.85f, minY + height * 0.6f, 2000);
		api.addNebula(minX + width * 0.4f, minY + height * 0.8f, 3000);
		api.addNebula(minX + width * 0.15f, minY + height * 0.3f, 1400);
		
		for (int i = 0; i < 5; i++) {
			float x = (float) Math.random() * width - width/2;
			float y = (float) Math.random() * height - height/2;
			float radius = 100f + (float) Math.random() * 400f; 
			api.addNebula(x, y, radius);
		}
		
		api.addObjective(minX + width * 0.75f, minY + height * 0.8f, 
						 "nav_buoy");
		api.addObjective(minX + width * 0.25f, minY + height * 0.8f, 
						 "sensor_array");
		api.addObjective(minX + width * 0.5f, minY + height * 0.5f, 
						 "comm_relay");
		api.addObjective(minX + width * 0.5f, minY + height * 0.2f, 
						 "sensor_array");

		api.addPlanet(minX + width * 0.35f, minY + height * 0.65f, 200f, "lava", 200f);
		api.addPlanet(minX + width * 0.8f, minY + height * 0.3f, 200f, "ice_giant", 200f);
	}

}
