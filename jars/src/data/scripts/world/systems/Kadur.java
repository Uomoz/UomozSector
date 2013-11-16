package data.scripts.world.systems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoAPI.CrewXPLevel;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import com.fs.starfarer.api.campaign.OrbitAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import data.scripts.uss.FactionSpawnPoint;
import data.scripts.uss.UsSData;
import java.awt.Color;

@SuppressWarnings("unchecked")
public class Kadur {

	public void generate(SectorAPI sector) {
		
		StarSystemAPI system = sector.createStarSystem("Kadur");
		system.getLocation().set(1332, 14495);
		system.setBackgroundTextureFilename("khs_graphics/backgrounds/background1.jpg");
		
		PlanetAPI sun = system.initStar("khs_star_blue", 850f);
		system.setLightColor(new Color(200, 200, 255)); 
		SectorEntityToken Backwater = system.addPlanet(sun, "Kadur", "arid", 90, 300, 3468, 220);
		SectorEntityToken Backwatermoon = system.addPlanet(sun, "Qamar", "desert", 240, 190, 8324, 335);
		
		system.addAsteroidBelt(sun, 200, 5736, 425, 200, 400);
		system.addAsteroidBelt(sun, 200, 5879, 425, 200, 400);
		system.addAsteroidBelt(sun, 200, 5987, 425, 200, 400);
		system.addAsteroidBelt(sun, 200, 6023, 425, 200, 400);
		system.addAsteroidBelt(sun, 200, 6112, 425, 200, 400);
		system.addAsteroidBelt(Backwater, 140, 736, 110, 10, 45);
		
		JumpPointAPI jumpPointA = Global.getFactory().createJumpPoint("Jump Point Alpha");
		OrbitAPI orbitjpa = Global.getFactory().createCircularOrbit(sun, 35, 2134, 220);
		jumpPointA.setOrbit(orbitjpa);
		jumpPointA.setRelatedPlanet(Backwater);
		jumpPointA.setStandardWormholeToHyperspaceVisual();
		system.addEntity(jumpPointA);
		
		JumpPointAPI jumpPointB = Global.getFactory().createJumpPoint("Jump Point Bravo");
		OrbitAPI orbitjpb = Global.getFactory().createCircularOrbit(sun, 240, 7024, 335);
		jumpPointB.setOrbit(orbitjpb);
		jumpPointB.setRelatedPlanet(Backwatermoon);
		jumpPointB.setStandardWormholeToHyperspaceVisual();
		system.addEntity(jumpPointB);
		
		system.autogenerateHyperspaceJumpPoints(true, true);
		
		SectorEntityToken zionstation = system.addOrbitalStation(Backwater, 65, 450, 50, "Orbital Fortress", "regime");
		SectorEntityToken meccastation = system.addOrbitalStation(Backwatermoon, 90, 300, 50, "Orbital Shipyard", "regime");
		
                FactionSpawnPoint KADsdf = new FactionSpawnPoint(sector, system, 1, 1, zionstation, "regime", "scout", "Cathedral Fleet", "defend", 200, 280, UsSData.Variants_KAD,
                        UsSData.KAD_CS, UsSData.KAD_C, UsSData.KAD_D, UsSData.KAD_F, UsSData.KAD_W, UsSData.KAD_LG, "", UsSData.KAD_SW_FINAL, UsSData.KAD_MW_FINAL, UsSData.KAD_LW_FINAL,
                        25f,50f,66f,5f,5f,
                        20, 30, "leadership", "combat", "technology");
		system.addScript(KADsdf);
                
                FactionSpawnPoint KADsdf2 = new FactionSpawnPoint(sector, system, 1, 1, meccastation, "regime", "scout", "Cathedral Fleet", "defend", 200, 280, UsSData.Variants_KAD,
                        UsSData.KAD_CS, UsSData.KAD_C, UsSData.KAD_D, UsSData.KAD_F, UsSData.KAD_W, UsSData.KAD_LG, "", UsSData.KAD_SW_FINAL, UsSData.KAD_MW_FINAL, UsSData.KAD_LW_FINAL,
                        25f,50f,66f,5f,5f,
                        20, 30, "leadership", "combat", "technology");
		system.addScript(KADsdf2);
                
                FactionSpawnPoint KADsecpatrol = new FactionSpawnPoint(sector, system, 1, 5, zionstation, "regime", "scout", "Crusade Fleet", "patrol", 70, 120, UsSData.Variants_KAD,
                        UsSData.KAD_CS, UsSData.KAD_C, UsSData.KAD_D, UsSData.KAD_F, UsSData.KAD_W, UsSData.KAD_LG, "", UsSData.KAD_SW_FINAL, UsSData.KAD_MW_FINAL, UsSData.KAD_LW_FINAL,
                        25f,75f,75f,5f,5f,
                        15, 20, "combat", "leadership", "technology");
		system.addScript(KADsecpatrol);
                
                FactionSpawnPoint KADpatrol = new FactionSpawnPoint(sector, system, 1, 5, zionstation, "regime", "scout", "Evangelists", "secpatrol", 60, 90, UsSData.Variants_KAD,
                        UsSData.KAD_CS, UsSData.KAD_C, UsSData.KAD_D, UsSData.KAD_F, UsSData.KAD_W, UsSData.KAD_LG, "", UsSData.KAD_SW_FINAL, UsSData.KAD_MW_FINAL, UsSData.KAD_LW_FINAL,
                        25f,75f,75f,5f,5f,
                        15, 20, "combat", "leadership", "technology");
		system.addScript(KADpatrol);
                
                FactionSpawnPoint KADboss = new FactionSpawnPoint(sector, system, 30, 1, zionstation, "regime", "scout", "Judgement Fleet", "raid", 250, 350, UsSData.Variants_KAD,
                        UsSData.KAD_CS, UsSData.KAD_C, UsSData.KAD_D, UsSData.KAD_F, UsSData.KAD_W, UsSData.KAD_LG, "", UsSData.KAD_SW_FINAL, UsSData.KAD_MW_FINAL, UsSData.KAD_LW_FINAL,
                        25f,50f,66f,5f,5f,
                        25, 30, "leadership", "combat", "technology");
		system.addScript(KADboss);
                
                FactionSpawnPoint trader = new FactionSpawnPoint(sector, system, 1, 1, meccastation, "regime", "scout", "Trader", "trade", 20, 80, UsSData.Variants_KAD,
                        UsSData.KAD_CS, UsSData.KAD_C, UsSData.KAD_D, UsSData.KAD_F, UsSData.KAD_W, UsSData.KAD_TR, "", UsSData.KAD_SW_FINAL, UsSData.KAD_MW_FINAL, UsSData.KAD_LW_FINAL,
                        0f,25f,50f,25f,50f,
                        5, 10, "technology", "leadership", "combat");
		system.addScript(trader);             
                
                //---------------------------------------------------
                
//                EliteSpawnPoint vayra = new EliteSpawnPoint(sector, system, 1, 1, zionstation, "regime", "scout", "Vayra the Purifier", "defender", "friendly", 150, UsSData.All_Variants,
//                        UsSData.ALL_CS_FINAL, UsSData.ALL_C_FINAL, UsSData.KAD_D, UsSData.ALL_F_FINAL, UsSData.ALL_W_FINAL, UsSData.CIV_LG, "",
//                        25f,50f,75f,33f,0f,
//                        "combat", "technology", "leadership");
//                system.addScript(vayra);
               
                //---------------------------------------------------
				
		CargoAPI cargo = meccastation.getCargo();
		cargo.addWeapons("khs_kinetic_violator", 7);
		cargo.addWeapons("khs_assault_violator", 7);
		cargo.addWeapons("khs_kinetic_penetrator", 10);
		cargo.addWeapons("khs_assault_penetrator", 10);
		cargo.addWeapons("khs_volley_cannon", 14);
		cargo.addWeapons("khs_sling", 14);
		cargo.addWeapons("khs_partisan", 12);
		cargo.addWeapons("khs_javelin_vlrms", 14);
		cargo.addWeapons("khs_lance_vlrms", 12);
		cargo.addWeapons("khs_martyrcannon", 14);
		cargo.addCrew(CrewXPLevel.REGULAR, 400);
		cargo.addCrew(CrewXPLevel.GREEN, 800);
		cargo.addSupplies(500);
		cargo.addFuel(50);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_mutt_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_mutt_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_jackal_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_hyena_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_hyena_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_camel_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_camel_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_buzzard_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_vulture_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_targe_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_rukh_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_caliph_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_golem_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_falchion_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_sphinx_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_hauberk_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_leviathan_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_leviathan_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_seraph_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_behemoth_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_mistral_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_mistral_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_sirocco_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_sirocco_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_djinn_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_djinn_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_dolphin_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_dolphin_Hull", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "khs_dirk_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "khs_dirk_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "khs_scimitar_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "khs_scimitar_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "khs_immortal_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "khs_phalanx_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "khs_myrmidon_wing", null);
				
		CargoAPI cargoR = zionstation.getCargo();
		cargoR.addWeapons("khs_kinetic_demolisher", 4);
		cargoR.addWeapons("khs_assault_demolisher", 4);
		cargoR.addWeapons("khs_kinetic_violator", 7);
		cargoR.addWeapons("khs_assault_violator", 7);
		cargoR.addWeapons("khs_partisan", 12);
		cargoR.addWeapons("khs_hoplite", 6);
		cargoR.addWeapons("khs_lance_vlrms", 12);
		cargoR.addWeapons("khs_francisca_vlrms", 6);
		cargoR.addWeapons("khs_strike_cannon", 14);
		cargoR.addCrew(CrewXPLevel.ELITE, 200);
		cargoR.addCrew(CrewXPLevel.VETERAN, 400);
		cargoR.addMarines(50);
		cargoR.addSupplies(500);
		cargoR.addFuel(50);
		cargoR.addMothballedShip(FleetMemberType.SHIP, "khs_jackal_Hull", null);
		cargoR.addMothballedShip(FleetMemberType.SHIP, "khs_hyena_Hull", null);
		cargoR.addMothballedShip(FleetMemberType.SHIP, "khs_hyena_Hull", null);
		cargoR.addMothballedShip(FleetMemberType.SHIP, "khs_buzzard_Hull", null);
		cargoR.addMothballedShip(FleetMemberType.SHIP, "khs_camel_Hull", null);
		cargoR.addMothballedShip(FleetMemberType.SHIP, "khs_vulture_Hull", null);
		cargoR.addMothballedShip(FleetMemberType.SHIP, "khs_targe_Hull", null);
		cargoR.addMothballedShip(FleetMemberType.SHIP, "khs_rukh_Hull", null);
		cargoR.addMothballedShip(FleetMemberType.SHIP, "khs_caliph_Hull", null);
		cargoR.addMothballedShip(FleetMemberType.SHIP, "khs_golem_Hull", null);
		cargoR.addMothballedShip(FleetMemberType.SHIP, "khs_falchion_Hull", null);
		cargoR.addMothballedShip(FleetMemberType.SHIP, "khs_falchion_Hull", null);
		cargoR.addMothballedShip(FleetMemberType.SHIP, "khs_sphinx_Hull", null);
		cargoR.addMothballedShip(FleetMemberType.SHIP, "khs_hauberk_Hull", null);
		cargoR.addMothballedShip(FleetMemberType.SHIP, "khs_seraph_Hull", null);
		cargoR.addMothballedShip(FleetMemberType.SHIP, "khs_behemoth_Hull", null);
		cargoR.addMothballedShip(FleetMemberType.SHIP, "khs_dolphin_Hull", null);
		cargoR.addMothballedShip(FleetMemberType.FIGHTER_WING, "khs_dervish_wing", null);
		cargoR.addMothballedShip(FleetMemberType.FIGHTER_WING, "khs_dirk_wing", null);
		cargoR.addMothballedShip(FleetMemberType.FIGHTER_WING, "khs_dirk_wing", null);
		cargoR.addMothballedShip(FleetMemberType.FIGHTER_WING, "khs_scimitar_wing", null);
		cargoR.addMothballedShip(FleetMemberType.FIGHTER_WING, "khs_scimitar_wing", null);
		cargoR.addMothballedShip(FleetMemberType.FIGHTER_WING, "khs_immortal_wing", null);
		cargoR.addMothballedShip(FleetMemberType.FIGHTER_WING, "khs_phalanx_wing", null);
		cargoR.addMothballedShip(FleetMemberType.FIGHTER_WING, "khs_myrmidon_wing", null);
	}
	
}















