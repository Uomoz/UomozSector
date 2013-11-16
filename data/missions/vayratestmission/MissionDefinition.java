package data.missions.vayratestmission;

import java.util.ArrayList;
import java.util.List;

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
		api.initFleet(FleetSide.PLAYER, "HMCS", FleetGoal.ATTACK, false, 5);
		api.initFleet(FleetSide.ENEMY, "USS", FleetGoal.ATTACK, true, 5);
		
		// ships in fleets
		api.addToFleet(FleetSide.PLAYER, "khs_caliph_Assault", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.PLAYER, "khs_seraph_Command", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "khs_behemoth_Support", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "khs_sphinx_fastattack", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "khs_golem_escort", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "khs_leviathan_outdated", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "khs_rukh_Fast_Attack", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "khs_falchion_firesupport", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "khs_targe_Shield", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "khs_hauberk_EWAR", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "khs_djinn_Standard", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "khs_sirocco_Standard", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "khs_buzzard_Strike", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "khs_camel_Combat", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "khs_dolphin_Ramming", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "khs_hyena_Picket", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "khs_jackal_Scout", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "khs_mistral_Light", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "khs_mutt_Tramp", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "khs_vulture_Fighter_Support", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "khs_scimitar_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "khs_dervish_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "khs_dirk_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "khs_phalanx_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "khs_myrmidon_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "khs_immortal_wing", FleetMemberType.FIGHTER_WING, false);
		
		api.addToFleet(FleetSide.ENEMY, "onslaught_Elite", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "eagle_Balanced", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "dominator_Support", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "hammerhead_Elite", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "medusa_CS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "medusa_Attack", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "hammerhead_Elite", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "medusa_CS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "medusa_Attack", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "odyssey_Balanced", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "tempest_Attack", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "wolf_Starting", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "hound_Assault", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "lasher_PD", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "tempest_Attack", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "wolf_Starting", FleetMemberType.SHIP, false);
		
		api.addToFleet(FleetSide.ENEMY, "wasp_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "wasp_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "wasp_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "xyphos_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "trident_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "xyphos_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "trident_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "dagger_wing", FleetMemberType.FIGHTER_WING, false);

		// Set a small blurb for each fleet that shows up on the mission detail and
		// mission results screens to identify each side.
		api.setFleetTagline(FleetSide.PLAYER, "Her Majesty In Right Of Canada's First Royal Space Navy");
		api.setFleetTagline(FleetSide.ENEMY, "Imperial American State Solar Coast Guard");
		
		// These show up as items in the bulleted list under 
		// "Tactical Objectives" on the mission detail screen
		api.addBriefingItem("Kill them all");
		
		// Set up the map.
		float width = 35000f;
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
		api.addObjective(minX + width * 0.25f + 2000, minY + height * 0.25f + 2000, "nav_buoy");
		api.addObjective(minX + width * 0.75f - 2000, minY + height * 0.25f + 2000, "comm_relay");
		api.addObjective(minX + width * 0.75f - 2000, minY + height * 0.75f - 2000, "nav_buoy");
		api.addObjective(minX + width * 0.25f + 2000, minY + height * 0.75f - 2000, "comm_relay");
		api.addObjective(minX + width * 0.5f, minY + height * 0.5f, "sensor_array");
		
		String [] planets = {"desert", "arid"};
		String planet = planets[(int) (Math.random() * (double) planets.length)];
		float radius = 100f + (float) Math.random() * 150f;
		api.addPlanet(0, 0, radius, planet, 200f, true);
	}

}





