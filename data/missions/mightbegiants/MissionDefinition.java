package data.missions.mightbegiants;

import com.fs.starfarer.api.campaign.CargoAPI.CrewXPLevel;
import com.fs.starfarer.api.fleet.FleetGoal;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
//import com.fs.starfarer.api.fleet.FleetMemberStatusAPI;
//import com.fs.starfarer.api.fleet.RepairTrackerAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.mission.FleetSide;
import com.fs.starfarer.api.mission.MissionDefinitionAPI;
import com.fs.starfarer.api.mission.MissionDefinitionPlugin;

public class MissionDefinition implements MissionDefinitionPlugin {

	public void defineMission(MissionDefinitionAPI api) {

		api.initFleet(FleetSide.PLAYER, "SYS", FleetGoal.ESCAPE, false, 5);
		api.initFleet(FleetSide.ENEMY, "HSS", FleetGoal.ATTACK, true, 5);

		api.setFleetTagline(FleetSide.PLAYER, "SYS Task Force");
		api.setFleetTagline(FleetSide.ENEMY, "Hegemony Reserve Fleet");
		
		api.addBriefingItem("Deter the pursuing Hegemony Fleet");
		api.addBriefingItem("SYS Exclamation Point must survive");
		
		//The player fleet could fight off the pursuing hegemony, but they're too busted up.
		api.addToFleet(FleetSide.PLAYER, "ms_elysium_CS", FleetMemberType.SHIP, "SYS Exclamation Point", true, CrewXPLevel.VETERAN);//, setCR(58));
		api.addToFleet(FleetSide.PLAYER, "ms_elysium_Standard", FleetMemberType.SHIP, "SYS What Ails Ya", true, CrewXPLevel.ELITE);//, applyFractionDamage(7), setCR(64));
		//api.addToFleet(FleetSide.PLAYER, "ms_charybdis_Standard", FleetMemberType.SHIP, "SYS Briefcase of Bees", true, applyFractionDamage(68), setCR(0));
		//api.addToFleet(FleetSide.PLAYER, "ms_tartarus_Standard", FleetMemberType.SHIP, "SYS Unsolitary Confinement", true, applyFractionDamage(74), setCR(0));
		//api.addToFleet(FleetSide.PLAYER, "ms_morningstar_Standard", FleetMemberType.SHIP, "SYS Alfalfa Strike", true, applyFractionDamage(32), setCR(42));
		//api.addToFleet(FleetSide.PLAYER, "ms_morningstar_Standard", FleetMemberType.SHIP, "SYS Murder of Crows", true, applyFractionDamage(92), setCR(0));
		api.addToFleet(FleetSide.PLAYER, "ms_sargasso_Standard", FleetMemberType.SHIP, "SYS Long in Tooth", true);
		//api.addToFleet(FleetSide.PLAYER, "ms_solidarity_Standard", FleetMemberType.SHIP, "SYS Sulla", true);
		//api.addToFleet(FleetSide.PLAYER, "ms_enlil_Standard", FleetMemberType.SHIP, "SYS Here and There", true, applyFractionDamage(8), setCR(48));
		//api.addToFleet(FleetSide.PLAYER, "ms_enlil_Standard", FleetMemberType.SHIP, "SYS Ballistic Buddy", true, setCR(48));
		//api.addToFleet(FleetSide.PLAYER, "ms_seski_Standard", FleetMemberType.SHIP, "SYS Tenebrious", true, applyFractionDamage(24), setCR(32));
		
		api.addToFleet(FleetSide.PLAYER, "ms_skinwalker_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.ELITE);//, setCR(54));
		api.addToFleet(FleetSide.PLAYER, "ms_raksasha_wing", FleetMemberType.FIGHTER_WING, false, CrewXPLevel.VETERAN);//, setCR(49));
		api.addToFleet(FleetSide.PLAYER, "ms_neriad_wing", FleetMemberType.FIGHTER_WING, false);//, setCR(58));
		api.addToFleet(FleetSide.PLAYER, "ms_neriad_wing", FleetMemberType.FIGHTER_WING, false);//, CrewXPLevel.VETERAN, setCR(52));
		
		api.defeatOnShipLoss("SYS Exclamation Point");

		// Set up the enemy fleet.
		// while really not much stronger, the actual fleet is overall in much better shape. Have fun defending 0% CR ships I guess.
		api.addToFleet(FleetSide.ENEMY, "dominator_Assault", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "enforcer_Elite", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "enforcer_Balanced", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "condor_Strike", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "lasher_CS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "hound_Assault", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "hound_Assault", FleetMemberType.SHIP, false);

		
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
		
		api.addNebula(minX + width * 0.75f, minY + height * 0.4f, 3000);
		
		for (int i = 0; i < 5; i++) {
			float x = (float) Math.random() * width - width/2;
			float y = (float) Math.random() * height - height/2;
			float radius = 100f + (float) Math.random() * 400f; 
			api.addNebula(x, y, radius);
		}
		
		api.addObjective(minX + width * 0.5f, minY + height * 0.75f, 
						 "nav_buoy");
		api.addObjective(minX + width * 0.5f, minY + height * 0.25f, 
						 "sensor_array");
		
		api.addAsteroidField(minY, minY, 45, 2000f,
								20f, 70f, 100);

		api.addPlanet(minX + width * 0.55f, minY + height * 0.25f, 200f, "cryovolcanic", 200f);
	}

}
