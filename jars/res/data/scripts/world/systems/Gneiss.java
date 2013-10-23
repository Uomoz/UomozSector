package data.scripts.world.systems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import com.fs.starfarer.api.campaign.OrbitAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.impl.campaign.CoreCampaignPluginImpl;
import data.scripts.UsSData;
import data.scripts.uss.EliteSpawnPoint;
import data.scripts.uss.FactionSpawnPoint;
import data.scripts.uss.UsSUtils;
import java.awt.Color;

@SuppressWarnings("unchecked")
public class Gneiss {

   public void generate(SectorAPI sector) {
   
    		StarSystemAPI system = sector.createStarSystem("Gneiss");
                system.getLocation().set(4200, -6200);
		system.setBackgroundTextureFilename("graphics/BR/backgrounds/obsidianBG.jpg");	
		
		PlanetAPI br = system.initStar("star_brstar", 450f);
		PlanetAPI blackrock = system.addPlanet(br, "Blackrock", "br_blackrockplanet", 300, 140, 3400, 80);
		PlanetAPI lodestone = system.addPlanet(blackrock, "Lodestone", "br_lodestone", 30, 50, 500, 25);
		PlanetAPI creir = system.addPlanet(br, "Creir", "toxic", 100, 130, 4800, 100);
		PlanetAPI lydia = system.addPlanet(creir, "Lydia", "barren", 40, 60, 800, 30);
		PlanetAPI nanoplanet = system.addPlanet(br, "Verge", "br_nanoplanet", 230, 340, 8500, 250);
		PlanetAPI preclusion = system.addPlanet(nanoplanet, "Preclusion", "cryovolcanic", 100, 30, 700, 80);		

		system.addAsteroidBelt(br, 50, 1600, 255, 40, 80);
		system.addAsteroidBelt(br, 70, 5600, 128, 40, 100);
		system.addAsteroidBelt(nanoplanet, 70, 900, 128, 20, 40);		

		system.addRingBand(nanoplanet, "misc", "rings1", 256f, 2, Color.white, 256f, 360, 40f);
		system.addRingBand(nanoplanet, "misc", "rings1", 256f, 2, Color.white, 256f, 370, 60f);
		system.addRingBand(nanoplanet, "misc", "rings1", 256f, 2, Color.white, 256f, 380, 80f);		
                
                SectorEntityToken BRstation = system.addOrbitalStation(lodestone, 270, 100, 80, "Orbital Yard", "blackrock_driveyards");
		initBRCargo(BRstation);
                
                JumpPointAPI jumpPoint = Global.getFactory().createJumpPoint("Jump Point Alpha");
		OrbitAPI orbit = Global.getFactory().createCircularOrbit(lodestone, 0, 500, 30);
		jumpPoint.setOrbit(orbit);
		jumpPoint.setRelatedPlanet(lodestone);
		jumpPoint.setStandardWormholeToHyperspaceVisual();
		system.addEntity(jumpPoint);
                
                system.autogenerateHyperspaceJumpPoints(true, true);
                
                SectorEntityToken pirateBase = system.addOrbitalStation(creir, 45, 300, 50, "Hidden Mining Base", "pirates");
                initPirateBaseCargo(pirateBase);
                SectorEntityToken indStation = system.addOrbitalStation(blackrock, 45, 240, 50, "Gneiss Trade Hub", "independent");
                UsSUtils.addRandomStuffToStation(sector, indStation.getCargo());
                
                FactionSpawnPoint BRdef = new FactionSpawnPoint(sector, system, 1, 1, BRstation, "blackrock_driveyards", "scout", "Sovereignity Fleet", "defend", 150, 200, UsSData.Variants_BR,
                        UsSData.BR_CS, UsSData.BR_C, UsSData.BR_D, UsSData.BR_F, UsSData.BR_W, UsSData.BR_TR, "brdy_karkinos_Hull",
                        0f,75f,75f,50f,5f,
                        15, 25, "technology", "leadership", "combat");
		system.addScript(BRdef);
                
		FactionSpawnPoint BRpat = new FactionSpawnPoint(sector, system, 1, 4, BRstation, "blackrock_driveyards", "scout", "Security Patrol", "patrol", 50, 90, UsSData.Variants_BR,
                        UsSData.BR_CS, UsSData.BR_C, UsSData.BR_D, UsSData.BR_F, UsSData.BR_W, UsSData.BR_TR, "brdy_nevermore_Hull",
                        0f,50f,75f,33f,0f,
                        10, 15, "technology", "combat", "leadership");
		system.addScript(BRpat);
                
                FactionSpawnPoint BRrec = new FactionSpawnPoint(sector, system, 1, 4, BRstation, "blackrock_driveyards", "scout", "Scout", "raid", 10, 50, UsSData.Variants_BR,
                        UsSData.BR_CS, UsSData.BR_C, UsSData.BR_D, UsSData.BR_F, UsSData.BR_W, UsSData.BR_TR, "brdy_desdinova_Hull",
                        0f,0f,75f,33f,0f,
                        5, 10, "technology", "combat", "leadership");
		system.addScript(BRrec);
                                
                //---------------------------------------------------
                
                EliteSpawnPoint cyc = new EliteSpawnPoint(sector, system, 1, 1, BRstation, "blackrock_driveyards", "scout", "Cycerin", "hunter", "station", 120, UsSData.All_Variants,
                        UsSData.BR_CS, UsSData.BR_C, UsSData.BR_D, UsSData.BR_F, UsSData.BR_W, UsSData.BR_TR, "brdy_stormcrow_Hull",
                        0f,25f,50f,33f,0f,
                        "combat", "leadership", "technology");
		system.addScript(cyc);
                
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
                
                FactionSpawnPoint miner = new FactionSpawnPoint(sector, system, 1, 1, indStation, "independent", "miner", "Miner", "mine", 20, 35, UsSData.Variants_IND,
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
                
                //---------------------------------------------------
                
                FactionSpawnPoint PIRsmallRaiders = new FactionSpawnPoint(sector, system, 1, 6, pirateBase, "pirates", "scout", "Scavengers", "raid", 0, 20, UsSData.Variants_PIR,
                        UsSData.PPBR_CS, UsSData.PPBR_C, UsSData.PPBR_D, UsSData.PPBR_F, UsSData.PPBR_W, UsSData.PP_B, "",
                        0f,0f,50f,25f,33f,
                        1, 5, "combat", "leadership", "technology");
		system.addScript(PIRsmallRaiders);
                
                FactionSpawnPoint PIRmediumRaiders = new FactionSpawnPoint(sector, system, 1, 4, pirateBase, "pirates", "scout", "Raiders", "raid", 30, 50, UsSData.Variants_PIR,
                        UsSData.PPBR_CS, UsSData.PPBR_C, UsSData.PPBR_D, UsSData.PPBR_F, UsSData.PPBR_W, UsSData.PPBR_CRR, "",
                        0f,25f,75f,10f,33f,
                        5, 10, "combat", "leadership", "technology");
		system.addScript(PIRmediumRaiders);
                
                FactionSpawnPoint PIRdefense = new FactionSpawnPoint(sector, system, 1, 2, pirateBase, "pirates", "scout", "Armada", "defend", 60, 120, UsSData.Variants_PIR,
                        UsSData.PPBR_CS, UsSData.PPBR_C, UsSData.PPBR_D, UsSData.PPBR_F, UsSData.PPBR_W, UsSData.PPBR_CRR, "",
                        25f,50f,75f,33f,0f,
                        10, 15, "combat", "leadership", "technology");
		system.addScript(PIRdefense);
                
                //--------------------------------------------------- 
                
                EliteSpawnPoint PIRlead = new EliteSpawnPoint(sector, system, 1, 3, pirateBase, "pirates", "scout", "merc", "raider", "friendly", 150, UsSData.All_Variants,
                        UsSData.ALL_CS_FINAL, UsSData.ALL_C_FINAL, UsSData.PPBR_D, UsSData.ALL_F_FINAL, UsSData.ALL_W_FINAL, UsSData.CIV_LG, "",
                        25f,50f,50f,33f,0f,
                        "combat", "technology", "leadership");
		system.addScript(PIRlead);
		
                //---------------------------------------------------                
                
		sector.registerPlugin(new CoreCampaignPluginImpl());
    }
   
   	private void initBRCargo(SectorEntityToken station) {
		CargoAPI BRcargo = station.getCargo();

		BRcargo.addWeapons("brdy_ac", 10);
                BRcargo.addWeapons("brdy_ag", 10);		
                BRcargo.addWeapons("achilles_mrm", 5);
                BRcargo.addWeapons("achilles_mrm", 5);					
                BRcargo.addWeapons("achillespod", 1);
                BRcargo.addWeapons("brdy_volley", 4);
                BRcargo.addWeapons("brdy_quill", 6);
                BRcargo.addWeapons("brdy_solenoid", 6);			
                BRcargo.addWeapons("brburst", 2);	
		BRcargo.addWeapons("brdy_argussmall", 5);
		BRcargo.addWeapons("brdy_dualac", 3);
		BRcargo.addWeapons("brdy_squallbattery", 1);
		BRcargo.addWeapons("brdy_fury", 6);
		BRcargo.addWeapons("brdy_2xfury", 2);				
		BRcargo.addWeapons("brdy_plasma", 2);
		BRcargo.addWeapons("br_pde", 2);
		BRcargo.addWeapons("br_fpde", 1);
		BRcargo.addWeapons("brdy_linear", 3);		
		BRcargo.addWeapons("brdy_solenoidlarge", 1);		
		BRcargo.addWeapons("brvulcan", 15);
		BRcargo.addWeapons("brdy_galecannon", 2);				
		BRcargo.addWeapons("br_iwbattery", 5);			
		BRcargo.addWeapons("brdy_squallgun", 4);
				
		
		BRcargo.addCrew(CargoAPI.CrewXPLevel.ELITE, 10);
		BRcargo.addCrew(CargoAPI.CrewXPLevel.VETERAN, 50);
		BRcargo.addCrew(CargoAPI.CrewXPLevel.REGULAR, 500);
		BRcargo.addMarines(75);
		BRcargo.addSupplies(645);
		BRcargo.addFuel(100);

		BRcargo.addMothballedShip(FleetMemberType.SHIP, "brdy_nevermore_Hull", null);
		BRcargo.addMothballedShip(FleetMemberType.SHIP, "brdy_scarab_Hull", null);
		BRcargo.addMothballedShip(FleetMemberType.SHIP, "brdy_locust_Hull", null);
                BRcargo.addMothballedShip(FleetMemberType.SHIP, "brdy_locust_Hull", null);
		BRcargo.addMothballedShip(FleetMemberType.SHIP, "brdy_scarab_Hull", null);
		BRcargo.addMothballedShip(FleetMemberType.SHIP, "brdy_mantis_Hull", null);
		BRcargo.addMothballedShip(FleetMemberType.SHIP, "brdy_desdinova_Hull", null);
		BRcargo.addMothballedShip(FleetMemberType.SHIP, "brdy_cetonia_Hull", null);		
		BRcargo.addMothballedShip(FleetMemberType.SHIP, "brdy_cetonia_Hull", null);
		BRcargo.addMothballedShip(FleetMemberType.SHIP, "brdy_typheus_Hull", null);		
		BRcargo.addMothballedShip(FleetMemberType.SHIP, "brdy_revenant_Hull", null);
		BRcargo.addMothballedShip(FleetMemberType.SHIP, "brdy_gonodactylus_Hull", null);
		BRcargo.addMothballedShip(FleetMemberType.SHIP, "brdy_gonodactylus_Hull", null);
		BRcargo.addMothballedShip(FleetMemberType.SHIP, "brdy_kurmaraja_Hull", null);
		BRcargo.addMothballedShip(FleetMemberType.SHIP, "brdy_stenos_Hull", null);
		BRcargo.addMothballedShip(FleetMemberType.SHIP, "brdy_robberfly_Hull", null);
		BRcargo.addMothballedShip(FleetMemberType.SHIP, "brdy_robberfly_Hull", null);		

		BRcargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "brdy_krait_wing", null);
		BRcargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "brdy_krait_wing", null);
		BRcargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "brdy_krait_wing", null);
		BRcargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "brdy_serket_wing", null);
		BRcargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "brdy_squilla_wing", null);
                BRcargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "brdy_squilla_wing", null);
		
	}
        
        private void initPirateBaseCargo(SectorEntityToken station) {
		CargoAPI cargo = station.getCargo();
                
		// cargo.addCrew(CrewXPLevel.ELITE, 25);
		cargo.addCrew(CargoAPI.CrewXPLevel.VETERAN, 20);
		cargo.addCrew(CargoAPI.CrewXPLevel.REGULAR, 50);
		cargo.addCrew(CargoAPI.CrewXPLevel.GREEN, 100);
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