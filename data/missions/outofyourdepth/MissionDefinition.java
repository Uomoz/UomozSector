package data.missions.outofyourdepth;

import com.fs.starfarer.api.campaign.CargoAPI.CrewXPLevel;
import com.fs.starfarer.api.fleet.FleetGoal;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.mission.FleetSide;
import com.fs.starfarer.api.mission.MissionDefinitionAPI;
import com.fs.starfarer.api.mission.MissionDefinitionPlugin;

public class MissionDefinition implements MissionDefinitionPlugin {

	public void defineMission(MissionDefinitionAPI api) {

		api.initFleet(FleetSide.PLAYER, "ISS", FleetGoal.ESCAPE, false, 5);
		api.initFleet(FleetSide.ENEMY, "", FleetGoal.ATTACK, true);

		api.setFleetTagline(FleetSide.PLAYER, "ISS Urchin, escorting merchant convoy");
		api.setFleetTagline(FleetSide.ENEMY, "Suspected Cult of Lud forces.");
		
		//Player Mission briefing bullet points.
		api.addBriefingItem("Evade enemy forces");
		api.addBriefingItem("At least 50% of the convoy must escape.");
		api.addBriefingItem("The ISS Urchin, ISS Iconoclat, and ISS Altair IV must survive");

		//First, the player side; a cargo convoy with heavy(?) escort.
		api.addToFleet(FleetSide.PLAYER, "ms_morningstar_Standard", FleetMemberType.SHIP, "ISS Urchin", true, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "hammerhead_Balanced", FleetMemberType.SHIP, "ISS Kestrel", false, CrewXPLevel.VETERAN);
		api.addToFleet(FleetSide.PLAYER, "gemini_Standard", FleetMemberType.SHIP, "ISS Iconoclast", false);
		api.addToFleet(FleetSide.PLAYER, "vigilance_FS", FleetMemberType.SHIP, "ISS Peltast", false);
		api.addToFleet(FleetSide.PLAYER, "buffalo_Standard", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "buffalo_Standard", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "buffalo_Standard", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "atlas_Standard", FleetMemberType.SHIP, "ISS Altair IV", false);
		api.addToFleet(FleetSide.PLAYER, "tarsus_Standard", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "tarsus_Standard", FleetMemberType.SHIP, false);

		api.addToFleet(FleetSide.PLAYER, "ms_skinwalker_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "ms_neriad_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "broadsword_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "mining_drone_wing", FleetMemberType.FIGHTER_WING, false);
		
		api.defeatOnShipLoss("ISS Urchin");
		api.defeatOnShipLoss("ISS Iconoclast");
		api.defeatOnShipLoss("ISS Altair IV");

		// Set up for the Cult of Lud assholes.
		api.addToFleet(FleetSide.ENEMY, "dominator_Outdated", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");
		api.addToFleet(FleetSide.ENEMY, "condor_Strike", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");
		api.addToFleet(FleetSide.ENEMY, "condor_Strike", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");
		api.addToFleet(FleetSide.ENEMY, "enforcer_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");		
		api.addToFleet(FleetSide.ENEMY, "lasher_CS", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");		
		api.addToFleet(FleetSide.ENEMY, "lasher_CS", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");	
		api.addToFleet(FleetSide.ENEMY, "lasher_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");	
		api.addToFleet(FleetSide.ENEMY, "hound_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");
		api.addToFleet(FleetSide.ENEMY, "hound_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality("suicidal");
	
		api.addToFleet(FleetSide.ENEMY, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "piranha_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "broadsword_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "talon_wing", FleetMemberType.FIGHTER_WING, false);
		
		
		float width = 16000f;
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
		
		api.addObjective(minX + width * 0.5f, minY + height * 0.8f, 
						 "nav_buoy");
		api.addObjective(minX + width * 0.25f, minY + height * 0.5f, 
						 "comm_relay");
		api.addObjective(minX + width * 0.75f, minY + height * 0.5f, 
						 "sensor_array");
		api.addObjective(minX + width * 0.5f, minY + height * 0.2f, 
						 "nav_buoy");
		
		api.addAsteroidField(minY, minY, 45, 2000f,
								20f, 70f, 100);

		api.addPlanet(minX + width * 0.55f, minY + height * 0.25f, 200f, "cryovolcanic", 200f);
	}

}
