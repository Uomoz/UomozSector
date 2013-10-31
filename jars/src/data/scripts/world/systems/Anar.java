package data.scripts.world.systems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.InteractionDialogImageVisual;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoAPI.CrewXPLevel;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import com.fs.starfarer.api.campaign.OrbitAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import data.scripts.UsSData;
import data.scripts.UsSUtils;
import data.scripts.uss.EliteSpawnPoint;
import data.scripts.uss.FactionSpawnPoint;
import java.awt.Color;

@SuppressWarnings("unchecked")
public class Anar { //implements SectorGeneratorPlugin {

	public void generate(SectorAPI sector) {
	    
                StarSystemAPI system = sector.createStarSystem("Anar");
		system.getLocation().set(-4000, 6000);
		system.setBackgroundTextureFilename("graphics/backgrounds/anarbg.jpg");
		
		PlanetAPI anar = system.initStar("star_yellow", 500f);
		
		PlanetAPI anarI = system.addPlanet(anar, "Lumen", "barren", 155, 55, 1000, 53);
		
		PlanetAPI anarII = system.addPlanet(anar, "Wallow", "toxic", 20, 180, 2250, 222);
		
		PlanetAPI anarIII = system.addPlanet(anar, "Euripides", "planet_euripides", 245, 160, 3750, 381);
		PlanetAPI anarIIIA = system.addPlanet(anarIII, "Aeschylus", "cryovolcanic", 235, 40, 500, 62);
		system.addRingBand(anarIII, "misc", "rings1", 128f, 2, Color.white, 128f, 550, 40f);
		system.addRingBand(anarIII, "misc", "rings1", 128f, 4, Color.white, 128f, 650, 60f);
		system.addRingBand(anarIII, "misc", "rings1", 256f, 6, Color.white, 256f, 700, 80f);
		
		PlanetAPI anarIV = system.addPlanet(anar, "Calleach", "ice_giant", 235, 300, 8000, 766);
		system.addRingBand(anarIV, "misc", "rings1", 256f, 3, Color.white, 256f, 1450, 40f);
		system.addRingBand(anarIV, "misc", "rings1", 128f, 4, Color.white, 128f, 1550, 60f);
		system.addRingBand(anarIV, "misc", "rings1", 128f, 2, Color.white, 128f, 1550, 80f);
		system.addRingBand(anarIV, "misc", "rings1", 128f, 1, Color.white, 128f, 1600, 120f);
		PlanetAPI anarIVA = system.addPlanet(anarIV, "Cinderbox", "lava", 300, 60, 800, 88);
		PlanetAPI anarIVB = system.addPlanet(anarIV, "Melancholia", "terran", 240, 120, 1200, 246);
		PlanetAPI anarIVC = system.addPlanet(anarIV, "Theramin", "cryovolcanic", 200, 80, 1500, 492);
		
		system.addAsteroidBelt(anar, 600, 10000, 1400, 600, 400);
		
		anarIII.setCustomDescriptionId("planet_euripides");
                anarIII.setCustomInteractionDialogImageVisual(new InteractionDialogImageVisual("illustrations", "nanshe_desert", 400, 400));

		SectorEntityToken shadowPower = system.addOrbitalStation(anarI, 155, 200, 53, "Lumen Derelict Solar Plant", "neutral");
		shadowPower.getCargo().setFreeTransfer(true);
		SectorEntityToken shadowShipyards = system.addOrbitalStation(anarIII, 45, 400, 50, "Impresario Shipyards", "shadow_industry");
		SectorEntityToken shadowResearchBase = system.addOrbitalStation(anarIVB, 45, 300, 50, "Gravitas Research Post", "pirates");
                
		initShadowShipyardsCargo(sector, shadowShipyards);
		initPirateBaseCargo(sector, shadowResearchBase);
		
		JumpPointAPI jumpPoint = Global.getFactory().createJumpPoint("Jump Point Alpha");
		OrbitAPI orbit = Global.getFactory().createCircularOrbit(anarIII, 0, 500, 30);
		jumpPoint.setOrbit(orbit);
		jumpPoint.setRelatedPlanet(anarIII);
		
		jumpPoint.setStandardWormholeToHyperspaceVisual();
		system.addEntity(jumpPoint);
		
		system.autogenerateHyperspaceJumpPoints(true, true);
                
                SectorEntityToken indStation = system.addOrbitalStation(anarII, 45, 280, 50, "Anar Trade Hub", "independent");
                UsSUtils.addRandomStuffToStation(sector, indStation.getCargo());
		
                FactionSpawnPoint SHIdef = new FactionSpawnPoint(sector, system, 1, 2, shadowShipyards, "shadow_industry", "scout", "SDF", "defend", 120, 180, UsSData.Variants_SHI,
                        UsSData.SHI_CS, UsSData.SHI_C, UsSData.SHI_D, UsSData.SHI_F, UsSData.SHI_W, UsSData.SHI_TR, "ms_mimir_Hull",
                        0f,75f,66f,50f,5f,
                        15, 25, "technology", "leadership", "combat");
		system.addScript(SHIdef);
                
		FactionSpawnPoint SHIpat = new FactionSpawnPoint(sector, system, 1, 3, shadowShipyards, "shadow_industry", "scout", "Patrol", "patrol", 50, 90, UsSData.Variants_SHI,
                        UsSData.SHI_CS, UsSData.SHI_C, UsSData.SHI_D, UsSData.SHI_F, UsSData.SHI_W, UsSData.SHI_TR, "",
                        0f,50f,66f,33f,0f,
                        10, 15, "technology", "combat", "leadership");
		system.addScript(SHIpat);
                
                FactionSpawnPoint SHIrec = new FactionSpawnPoint(sector, system, 1, 4, shadowShipyards, "shadow_industry", "scout", "Recon", "raid", 10, 50, UsSData.Variants_SHI,
                        UsSData.SHI_CS, UsSData.SHI_C, UsSData.SHI_D, UsSData.SHI_F, UsSData.SHI_W, UsSData.SHI_TR, "",
                        0f,0f,66f,33f,0f,
                        5, 10, "technology", "combat", "leadership");
		system.addScript(SHIrec);
                
                //---------------------------------------------------
                
                EliteSpawnPoint SHIlead = new EliteSpawnPoint(sector, system, 1, 1, shadowShipyards, "shadow_industry", "scout", "merc", "hunter", "station", 150, UsSData.All_Variants,
                        UsSData.ALL_CS_FINAL, UsSData.ALL_C_FINAL, UsSData.SHI_D, UsSData.ALL_F_FINAL, UsSData.ALL_W_FINAL, UsSData.CIV_LG, "",
                        25f,50f,50f,33f,0f,
                        "combat", "technology", "leadership");
		system.addScript(SHIlead);
                
                //---------------------------------------------------
                
                FactionSpawnPoint PIRsmallRaiders = new FactionSpawnPoint(sector, system, 1, 6, shadowResearchBase, "pirates", "scout", "Scavengers", "raid", 0, 15, UsSData.Variants_PIR,
                        UsSData.PPSHI_CS, UsSData.PPSHI_C, UsSData.PPSHI_D, UsSData.PPSHI_F, UsSData.PPSHI_W, UsSData.PP_B, "",
                        0f,0f,50f,25f,33f,
                        1, 5, "combat", "leadership", "technology");
		system.addScript(PIRsmallRaiders);
                
                FactionSpawnPoint PIRmediumRaiders = new FactionSpawnPoint(sector, system, 1, 4, shadowResearchBase, "pirates", "scout", "Raiders", "raid", 30, 50, UsSData.Variants_PIR,
                        UsSData.PPSHI_CS, UsSData.PPSHI_C, UsSData.PPSHI_D, UsSData.PPSHI_F, UsSData.PPSHI_W, UsSData.PPSHI_CRR, "",
                        0f,25f,50f,50f,0f,
                        5, 10, "combat", "leadership", "technology");
		system.addScript(PIRmediumRaiders);
                
                FactionSpawnPoint PIRdefense = new FactionSpawnPoint(sector, system, 1, 2, shadowResearchBase, "pirates", "scout", "Armada", "defend", 60, 120, UsSData.Variants_PIR,
                        UsSData.PPSHI_CS, UsSData.PP_C, UsSData.PPSHI_D, UsSData.PPSHI_F, UsSData.PPSHI_W, UsSData.PPSHI_CRR, "",
                        25f,50f,50f,33f,0f,
                        10, 15, "combat", "leadership", "technology");
		system.addScript(PIRdefense);
                
                FactionSpawnPoint plunder = new FactionSpawnPoint(sector, system, 1, 1, shadowResearchBase, "pirates", "scout", "Plunder Fleet", "trade", 20, 80, UsSData.Variants_PIR,
                        UsSData.PPSHI_CS, UsSData.PP_C, UsSData.PPSHI_D, UsSData.PPSHI_F, UsSData.PPSHI_W, UsSData.PPSHI_TR, "",
                        0f,25f,50f,25f,50f,
                        5, 10, "combat", "leadership", "technology");
		system.addScript(plunder);
                
                //---------------------------------------------------
                
                EliteSpawnPoint PIRlead = new EliteSpawnPoint(sector, system, 1, 3, shadowResearchBase, "pirates", "scout", "merc", "raider", "friendly", 150, UsSData.All_Variants,
                        UsSData.ALL_CS_FINAL, UsSData.ALL_C_FINAL, UsSData.PPSHI_D, UsSData.ALL_F_FINAL, UsSData.ALL_W_FINAL, UsSData.CIV_LG, "",
                        25f,50f,50f,33f,0f,
                        "combat", "technology", "leadership");
		system.addScript(PIRlead);
                
                //---------------------------------------------------
                
                FactionSpawnPoint trader = new FactionSpawnPoint(sector, system, 1, 3, indStation, "independent", "miner", "Trader", "trade", 0, 10, UsSData.Variants_IND,
                        UsSData.IND_CS, UsSData.IND_C, UsSData.IND_D, UsSData.IND_F, UsSData.CIV_W, UsSData.IND_TR, "",
                        0f,0f,0f,0f,100f,
                        1, 5, "leadership", "technology", "combat");
		system.addScript(trader);
                
                FactionSpawnPoint trader2 = new FactionSpawnPoint(sector, system, 1, 1, indStation, "independent", "miner", "Trader", "trade", 15, 35, UsSData.Variants_IND,
                        UsSData.IND_CS, UsSData.IND_C, UsSData.IND_D, UsSData.IND_F, UsSData.IND_W, UsSData.IND_TR, "",
                        0f,0f,50f,25f,50f,
                        3, 8, "technology", "leadership", "combat");
		system.addScript(trader2);
                
                FactionSpawnPoint trader3 = new FactionSpawnPoint(sector, system, 1, 1, indStation, "independent", "miner", "Trader", "trade", 50, 80, UsSData.Variants_IND,
                        UsSData.IND_CS, UsSData.IND_C, UsSData.IND_D, UsSData.IND_F, UsSData.IND_W, UsSData.IND_TR, "",
                        0f,25f,50f,25f,50f,
                        5, 10, "technology", "leadership", "combat");
		system.addScript(trader3);
                
                FactionSpawnPoint miner = new FactionSpawnPoint(sector, system, 1, 1, indStation, "independent", "miner", "Miner", "mine", 0, 35, UsSData.Variants_IND,
                        UsSData.IND_CS, UsSData.IND_C, UsSData.IND_D, UsSData.IND_F, UsSData.CIV_W, UsSData.IND_TR, "",
                        0f,0f,0f,50f,50f,
                        3, 5, "technology", "leadership", "combat");
		system.addScript(miner);
                
                //---------------------------------------------------

                EliteSpawnPoint indeMerc = new EliteSpawnPoint(sector, system, 1, 2, indStation, "independent", "miner", "merc", "defender", "neutral", 150, UsSData.All_Variants,
                        UsSData.ALL_CS_FINAL, UsSData.ALL_C_FINAL, UsSData.ALL_D_FINAL, UsSData.ALL_F_FINAL, UsSData.ALL_W_FINAL, UsSData.CIV_LG, "",
                        25f,50f,50f,33f,0f,
                        "combat", "technology", "leadership");
		system.addScript(indeMerc);
	}
	
	private void initShadowShipyardsCargo(SectorAPI sector, SectorEntityToken station) {
		CargoAPI cargo = station.getCargo();	    
	    
		cargo.addCrew(CrewXPLevel.VETERAN, 100);
		cargo.addCrew(CrewXPLevel.REGULAR, 700);
		cargo.addMarines(500);
		cargo.addSupplies(1000);
		cargo.addFuel(1000);
		
		cargo.addWeapons("ms_pdcepc", 3);
		cargo.addWeapons("ms_shrike_single", 3);
		cargo.addWeapons("ms_shrike_rack", 2);
		cargo.addWeapons("pulselaser", 1);
		cargo.addWeapons("ms_scattercepc", 1);
		cargo.addWeapons("ms_mcepc", 1);
		cargo.addWeapons("ms_blackcap_3x", 3);
		cargo.addWeapons("ms_blackcap_6x", 2);
		cargo.addWeapons("taclaser", 3);
		cargo.addWeapons("lightag", 3);
		cargo.addWeapons("ms_cepc", 3);
		cargo.addWeapons("sabot", 3);
		cargo.addWeapons("harpoon", 1);
		cargo.addWeapons("heatseeker", 2);
	
		cargo.addMothballedShip(FleetMemberType.SHIP, "ms_charybdis_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "ms_elysium_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "ms_morningstar_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "ms_solidarity_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "ms_sargasso_Hull", "SYS Om'Nom'Nomh");
		cargo.addMothballedShip(FleetMemberType.SHIP, "ms_enlil_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "ms_enlil_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "ms_inanna_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "ms_inanna_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "ms_seski_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "ms_seski_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "ms_shamash_Hull", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "ms_skinwalker_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "ms_skinwalker_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "ms_skinwalker_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "ms_neriad_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "ms_neriad_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "ms_neriad_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "ms_raksasha_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "ms_raksasha_wing", null);
		
	}
    
    private void initPirateBaseCargo(SectorAPI sector, SectorEntityToken station) {
		CargoAPI cargo = station.getCargo();
                
		// cargo.addCrew(CrewXPLevel.ELITE, 25);
		cargo.addCrew(CrewXPLevel.VETERAN, 20);
		cargo.addCrew(CrewXPLevel.REGULAR, 50);
		cargo.addCrew(CrewXPLevel.GREEN, 100);
		cargo.addMarines(50);
		cargo.addSupplies(100);
		cargo.addFuel(10);
		
		//strike
		cargo.addWeapons("bomb", 15);
		cargo.addWeapons("typhoon", 5);
		
		//PD
		cargo.addWeapons("clusterbomb", 10);
		cargo.addWeapons("flak", 10);
		cargo.addWeapons("irpulse", 10);
		cargo.addWeapons("swarmer", 10);

		//support
		cargo.addWeapons("fragbomb", 10);
		cargo.addWeapons("heatseeker", 5);
		cargo.addWeapons("harpoon", 5);
		
		cargo.addWeapons("sabot", 5);
		cargo.addWeapons("annihilator", 5);
		cargo.addWeapons("lightdualmg", 10);
		cargo.addWeapons("lightdualac", 10);
		cargo.addWeapons("lightneedler", 10);
		cargo.addWeapons("heavymg", 10);
		cargo.addWeapons("heavymauler", 5);
		cargo.addWeapons("salamanderpod", 5);
		
		cargo.addWeapons("hveldriver", 5);

		//assault
		cargo.addWeapons("lightag", 10);
		cargo.addWeapons("chaingun", 5);
		
		//cargo.addMothballedShip(FleetMemberType.SHIP, "wolf_Hull", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "broadsword_wing", null);
		//cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "broadsword_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "piranha_wing", null);
		//cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "piranha_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "talon_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "talon_wing", null);
		//cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "thunder_wing", null);
		//cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "thunder_wing", null);
		//cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "gladius_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "warthog_wing", null);
		//cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "warthog_wing", null);
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "hound_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "lasher_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "buffalo2_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "ms_potniaBis_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "ms_potniaBis_Hull", null);
		//cargo.addMothballedShip(FleetMemberType.SHIP, "buffalo2_Hull", null);
		//cargo.addMothballedShip(FleetMemberType.SHIP, "condor_Hull", null);
		//cargo.addMothballedShip(FleetMemberType.SHIP, "tarsus_Hull", null);
		//cargo.addMothballedShip(FleetMemberType.SHIP, "tarsus_Hull", null);
		//cargo.addMothballedShip(FleetMemberType.SHIP, "gemini_Hull", null);


		//cargo.addMothballedShip(FleetMemberType.SHIP, "venture_Hull", null);
		//cargo.addMothballedShip(FleetMemberType.SHIP, "dominator_Hull", null);
		//cargo.addMothballedShip(FleetMemberType.SHIP, "conquest_Hull", null);
	}
	

}
