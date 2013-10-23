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
import data.scripts.uss.EliteSpawnPoint;
import data.scripts.uss.FactionSpawnPoint;
import data.scripts.uss.UsSUtils;
import java.util.List;

@SuppressWarnings("unchecked")
public class Corvus {

	public void generate(SectorAPI sector) {
            
		StarSystemAPI system = sector.createStarSystem("Corvus");
		system.getLocation().set(1000, 1000);
		system.setBackgroundTextureFilename("graphics/backgrounds/background4.jpg");
		
		sector.setRespawnLocation(system);
		sector.getRespawnCoordinates().set(-2500, -3500);
		
		PlanetAPI star = system.initStar("star_yellow", 500f);
				
		SectorEntityToken corvusI = system.addPlanet(star, "Corvus I", "desert", 55, 150, 3000, 100);
		PlanetAPI corvusII = system.addPlanet(star, "Corvus II", "jungle", 235, 200, 4500, 200);
		
		system.addAsteroidBelt(star, 500, 5500, 1000, 150, 300);
		
		PlanetAPI corvusIII = system.addPlanet(star, "Corvus III", "gas_giant", 200, 300, 7500, 400);
		PlanetAPI corvusIIIA = system.addPlanet(corvusIII, "Corvus IIIA", "cryovolcanic", 235, 120, 800, 20);
		system.addAsteroidBelt(corvusIII, 50, 1000, 200, 10, 45);
		PlanetAPI corvusIIIB = system.addPlanet(corvusIII, "Corvus IIIB", "barren", 235, 100, 1300, 60);
		
		PlanetAPI corvusIV = system.addPlanet(star, "Corvus IV", "barren", 0, 100, 10000, 700);
		PlanetAPI corvusV = system.addPlanet(star, "Corvus V", "frozen", 330, 175, 12000, 500);
		
		SectorEntityToken hegemonyStation = system.addOrbitalStation(corvusII, 45, 300, 50, "Orbital Station", "hegemony");
		SectorEntityToken tritachyonStation = system.addOrbitalStation(corvusV, 45, 300, 50, "Corporate HQ", "tritachyon");
		SectorEntityToken pirateStation = system.addOrbitalStation(corvusIIIA, 45, 300, 50, "Hidden Base", "pirates");
                initOrbitalStationCargo(sector, hegemonyStation);
		initTriTachyonHQCargo(sector, tritachyonStation);
		initPirateBaseCargo(sector, pirateStation);

		tritachyonStation.setCustomInteractionDialogImageVisual(new InteractionDialogImageVisual("illustrations", "cargo_loading", 400, 400));
		
		JumpPointAPI jumpPoint = Global.getFactory().createJumpPoint("Jump Point Alpha");
		OrbitAPI orbit = Global.getFactory().createCircularOrbit(corvusII, 0, 500, 30);
		jumpPoint.setOrbit(orbit);
		jumpPoint.setRelatedPlanet(corvusII);
		
		jumpPoint.setStandardWormholeToHyperspaceVisual();
		system.addEntity(jumpPoint);
		
		system.autogenerateHyperspaceJumpPoints(true, true);
		
		SectorEntityToken neutralStation = system.addOrbitalStation(system.getEntityByName("Corvus I"), 45, 300, 50, "Abandoned Storage Facility", "neutral");
		neutralStation.getCargo().setFreeTransfer(true);
                
                SectorEntityToken indStation = system.addOrbitalStation(corvusIV, 45, 200, 50, "Corvus Trade Hub", "independent");
                UsSUtils.addRandomStuffToStation(sector, indStation.getCargo());
                
                FactionSpawnPoint HEGsdf = new FactionSpawnPoint(sector, system, 1, 1, hegemonyStation, "hegemony", "patrol", "SDF", "defend", 200, 300, UsSData.Variants_HEG,
                        UsSData.HE_CS, UsSData.HE_C, UsSData.HE_D, UsSData.HE_F, UsSData.HE_W, UsSData.CIV_LG, "onslaught_Hull",
                        33f,50f,66f,5f,5f,
                        20, 30, "leadership", "combat", "technology");
		system.addScript(HEGsdf);
                
                FactionSpawnPoint HEGpatrol = new FactionSpawnPoint(sector, system, 1, 8, hegemonyStation, "hegemony", "patrol", "Patrol", "patrol", 25, 75, UsSData.Variants_HEG,
                        UsSData.HE_CS, UsSData.HE_C, UsSData.HE_D, UsSData.HE_F, UsSData.HE_W, UsSData.HE_CRR, "enforcer_Hull",
                        0f,50f,66f,10f,0f,
                        5, 15, "combat", "leadership", "technology");
		system.addScript(HEGpatrol);
                
                FactionSpawnPoint HEGsecpatrol = new FactionSpawnPoint(sector, system, 1, 5, hegemonyStation, "hegemony", "patrol", "Sector Patrol", "secpatrol", 70, 120, UsSData.Variants_HEG,
                        UsSData.HE_CS, UsSData.HE_C, UsSData.HE_D, UsSData.HE_F, UsSData.HE_W, UsSData.CIV_LG, "enforcer_Hull",
                        25f,75f,75f,5f,5f,
                        15, 20, "combat", "leadership", "technology");
		system.addScript(HEGsecpatrol);
                
                //---------------------------------------------------
                
                EliteSpawnPoint HEboss = new EliteSpawnPoint(sector, system, 1, 1, hegemonyStation, "hegemony", "patrol", "merc", "defender", "friendly", 150, UsSData.All_Variants,
                        UsSData.ALL_CS_FINAL, UsSData.ALL_C_FINAL, UsSData.ALL_D_FINAL, UsSData.ALL_F_FINAL, UsSData.ALL_W_FINAL, UsSData.CIV_LG, "",
                        25f,50f,75f,33f,0f,
                        "combat", "technology", "leadership");
		system.addScript(HEboss);
                
                //---------------------------------------------------
                
                FactionSpawnPoint PIRsmallRaiders = new FactionSpawnPoint(sector, system, 1, 8, pirateStation, "pirates", "scout", "Scavengers", "raid", 0, 20, UsSData.Variants_PIR,
                        UsSData.PP_CS, UsSData.PP_C, UsSData.PP_D, UsSData.PP_F, UsSData.PP_W, UsSData.PP_B, "",
                        0f,0f,50f,25f,50f,
                        1, 5, "combat", "leadership", "technology");
		system.addScript(PIRsmallRaiders);
                
                FactionSpawnPoint PIRmediumRaiders = new FactionSpawnPoint(sector, system, 1, 3, pirateStation, "pirates", "scout", "Raiders", "raid", 30, 50, UsSData.Variants_PIR,
                        UsSData.PP_CS, UsSData.PP_C, UsSData.PP_D, UsSData.PP_F, UsSData.PP_W, UsSData.PP_CRR, "",
                        0f,25f,75f,10f,33f,
                        5, 10, "combat", "leadership", "technology");
		system.addScript(PIRmediumRaiders);
                
                FactionSpawnPoint PIRdefense = new FactionSpawnPoint(sector, system, 1, 3, pirateStation, "pirates", "scout", "Armada", "defend", 60, 120, UsSData.Variants_PIR,
                        UsSData.PP_CS, UsSData.PP_C, UsSData.PP_D, UsSData.PP_F, UsSData.PP_W, UsSData.PP_CRR, "",
                        25f,50f,75f,33f,0f,
                        10, 15, "combat", "leadership", "technology");
		system.addScript(PIRdefense);
                
                //---------------------------------------------------
                
                EliteSpawnPoint PIRlead = new EliteSpawnPoint(sector, system, 1, 3, pirateStation, "pirates", "scout", "merc", "raider", "friendly", 150, UsSData.All_Variants,
                        UsSData.ALL_CS_FINAL, UsSData.ALL_C_FINAL, UsSData.ALL_D_FINAL, UsSData.ALL_F_FINAL, UsSData.ALL_W_FINAL, UsSData.CIV_LG, "",
                        25f,50f,75f,33f,0f,
                        "combat", "technology", "leadership");
		system.addScript(PIRlead);
                                
                //---------------------------------------------------
                
                FactionSpawnPoint TTscouts = new FactionSpawnPoint(sector, system, 1, 5, tritachyonStation, "tritachyon", "scout", "Scout", "raid", 0, 20, UsSData.Variants_TT,
                        UsSData.TT_CS, UsSData.TT_C, UsSData.TT_D, UsSData.TT_F, UsSData.TT_W, UsSData.TT_CRR, "",
                        0f,0f,0f,50f,0f,
                        5, 10, "technology", "combat", "leadership");
		system.addScript(TTscouts);
                
                FactionSpawnPoint TTsecurity = new FactionSpawnPoint(sector, system, 1, 1, tritachyonStation, "tritachyon", "scout", "Security Detachment", "defend", 70, 130, UsSData.Variants_TT,
                        UsSData.TT_CS, UsSData.TT_C, UsSData.TT_D, UsSData.TT_F, UsSData.TT_W, UsSData.CIV_LG, "astral_Hull",
                        25f,75f,60f,50f,5f,
                        20, 30, "technology", "combat", "leadership");
		system.addScript(TTsecurity);
                
                FactionSpawnPoint TTattack = new FactionSpawnPoint(sector, system, 1, 3, tritachyonStation, "tritachyon", "scout", "Wing", "patrol", 30, 50, UsSData.Variants_TT,
                        UsSData.TT_CS, UsSData.TT_C, UsSData.TT_D, UsSData.TT_F, UsSData.TT_W, UsSData.TT_CRR, "",
                        0f,25f,75f,50f,0f,
                        10, 15, "technology", "combat", "leadership");
		system.addScript(TTattack);
                
                //---------------------------------------------------
                
                EliteSpawnPoint TTboss = new EliteSpawnPoint(sector, system, 1, 1, tritachyonStation, "tritachyon", "scout", "merc", "hunter", "station", 150, UsSData.All_Variants,
                        UsSData.ALL_CS_FINAL, UsSData.ALL_C_FINAL, UsSData.ALL_D_FINAL, UsSData.ALL_F_FINAL, UsSData.ALL_W_FINAL, UsSData.CIV_LG, "",
                        25f,50f,75f,33f,0f,
                        "combat", "technology", "leadership");
		system.addScript(TTboss);
                
                //---------------------------------------------------
                
                FactionSpawnPoint trader = new FactionSpawnPoint(sector, system, 1, 4, indStation, "independent", "miner", "Trader", "trade", 0, 10, UsSData.Variants_IND,
                        UsSData.IND_CS, UsSData.IND_C, UsSData.IND_D, UsSData.IND_F, UsSData.CIV_W, UsSData.IND_TR, "",
                        0f,0f,0f,0f,100f,
                        1, 5, "leadership", "technology", "combat");
		system.addScript(trader);
                
                FactionSpawnPoint trader2 = new FactionSpawnPoint(sector, system, 1, 2, indStation, "independent", "miner", "Trader", "trade", 15, 35, UsSData.Variants_IND,
                        UsSData.IND_CS, UsSData.IND_C, UsSData.IND_D, UsSData.IND_F, UsSData.IND_W, UsSData.IND_TR, "",
                        0f,0f,50f,25f,50f,
                        3, 8, "technology", "leadership", "combat");
		system.addScript(trader2);
                
                FactionSpawnPoint trader3 = new FactionSpawnPoint(sector, system, 1, 1, indStation, "independent", "miner", "Trader", "trade", 50, 80, UsSData.Variants_IND,
                        UsSData.IND_CS, UsSData.IND_C, UsSData.IND_D, UsSData.IND_F, UsSData.IND_W, UsSData.IND_TR, "",
                        0f,25f,50f,25f,50f,
                        5, 10, "technology", "leadership", "combat");
		system.addScript(trader3);
                
                FactionSpawnPoint miner = new FactionSpawnPoint(sector, system, 1, 2, indStation, "independent", "miner", "Miner", "mine", 25, 30, UsSData.Variants_IND,
                        UsSData.IND_CS, UsSData.IND_C, UsSData.IND_D, UsSData.IND_F, UsSData.CIV_W, UsSData.IND_TR, "",
                        0f,0f,0f,50f,50f,
                        3, 5, "technology", "leadership", "combat");
		system.addScript(miner);
                
                FactionSpawnPoint aspCourier = new FactionSpawnPoint(sector, system, 1, 3, indStation, "independent", "miner", "ASP Courier", "trade", 25, 40, UsSData.Variants_IND,
                        UsSData.IND_CS, UsSData.IND_C, UsSData.IND_D, UsSData.IND_F, UsSData.IND_W, UsSData.CIV_LG, "syndicate_asp_gigantophis_Hull",
                        0f,0f,50f,33f,50f,
                        5, 10, "leadership", "technology", "combat");
		system.addScript(aspCourier);
                
                //---------------------------------------------------

                EliteSpawnPoint indeMerc = new EliteSpawnPoint(sector, system, 1, 2, indStation, "independent", "miner", "merc", "defender", "neutral", 150, UsSData.All_Variants,
                        UsSData.ALL_CS_FINAL, UsSData.ALL_C_FINAL, UsSData.ALL_D_FINAL, UsSData.ALL_F_FINAL, UsSData.ALL_W_FINAL, UsSData.CIV_LG, "",
                        25f,50f,75f,33f,0f,
                        "combat", "technology", "leadership");
		system.addScript(indeMerc);
	}

	private void initOrbitalStationCargo(SectorAPI sector, SectorEntityToken station) {
		CargoAPI cargo = station.getCargo();

		List weaponIds = sector.getAllWeaponIds();
		// for (int i = 0; i < 10; i++) {
		// String weaponId = (String) weaponIds.get((int) (weaponIds.size() *
		// Math.random()));
		// int quantity = (int)(Math.random() * 7 + 3);
		// cargo.addWeapons(weaponId, quantity);
		// }

		// focused on weapons that are hard to get from looting
		// and present an upgrade path for the initial ships
		// cargo.addWeapons("heavymg", 5);
		
		//strike
		cargo.addWeapons("bomb", 25);
		cargo.addWeapons("reaper", 12);
		
		//Support
		cargo.addWeapons("lightac", 25);
		cargo.addWeapons("lightmg", 40);
		cargo.addWeapons("annihilator", 10);
		cargo.addWeapons("taclaser", 10);

		cargo.addWeapons("harpoon_single", 12); //medium

		//assault
		cargo.addWeapons("lightmortar", 40);
		cargo.addWeapons("miningblaster", 1); //medium
		
		//PD
		cargo.addWeapons("swarmer", 5);
		cargo.addWeapons("mininglaser", 25);
		cargo.addWeapons("pdlaser", 25);
		
		cargo.addWeapons("flak", 5); //medium
		cargo.addWeapons("shredder", 5); //medium
		cargo.addWeapons("annihilatorpod", 1); //medium
		cargo.addWeapons("pilum", 2); //medium
		cargo.addWeapons("mark9", 2); //large
		
		
//		cargo.addCrew(CrewXPLevel.ELITE, 25);
		// cargo.addCrew(CrewXPLevel.VETERAN, 200);
		cargo.addCrew(CrewXPLevel.REGULAR, 30);
		cargo.addCrew(CrewXPLevel.GREEN, 500);
		cargo.addMarines(100);
		cargo.addSupplies(630);
		cargo.addFuel(500);

	
		cargo.addMothballedShip(FleetMemberType.SHIP, "vigilance_Hull", null);
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "hound_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "lasher_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "brawler_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "dram_Hull", null);
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "enforcer_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "condor_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "hammerhead_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "sunder_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "valkyrie_Hull", null);
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "falcon_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "eagle_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "dominator_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "venture_Hull", null);
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "atlas_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "onslaught_Hull", null);	
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "mule_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "mule_Hull", null);
		
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "talon_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "talon_wing", null);
		
		

	}

	private void initTriTachyonHQCargo(SectorAPI sector, SectorEntityToken station) {
		CargoAPI cargo = station.getCargo();

		List weaponIds = sector.getAllWeaponIds();
		cargo.addCrew(CrewXPLevel.ELITE, 10);
		cargo.addCrew(CrewXPLevel.VETERAN, 10);
		cargo.addCrew(CrewXPLevel.REGULAR, 50);
		// cargo.addCrew(CrewXPLevel.GREEN, 1500);
		cargo.addMarines(75);
		cargo.addSupplies(145);
		cargo.addFuel(100);
		
		//strike
		cargo.addWeapons("amblaster", 5);
		cargo.addWeapons("atropos_single", 5);
		
		//support
		cargo.addWeapons("taclaser", 15);
		cargo.addWeapons("railgun", 5);
		cargo.addWeapons("harpoon", 5);
		
		cargo.addWeapons("pulselaser", 6);
		cargo.addWeapons("gravitonbeam", 10);
		cargo.addWeapons("heavyburst", 6);
		cargo.addWeapons("heavyblaster", 3);
		cargo.addWeapons("phasebeam", 3);
		cargo.addWeapons("harpoonpod", 5);
		cargo.addWeapons("sabotpod", 5);
		
		//PD
		cargo.addWeapons("vulcan", 6);
		cargo.addWeapons("lrpdlaser", 6);
		cargo.addWeapons("pdburst", 6);
		cargo.addWeapons("swarmer", 6);
		
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "wasp_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "wasp_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "wasp_wing", null);
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "hyperion_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "hyperion_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "medusa_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "medusa_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "apogee_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "tempest_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "tempest_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "tempest_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "buffalo_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "buffalo_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "omen_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "omen_Hull", null);
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "doom_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "doom_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "afflictor_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "afflictor_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "shade_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "shade_Hull", null);
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "odyssey_Hull", null);
	}

	private void initPirateBaseCargo(SectorAPI sector, SectorEntityToken station) {
		CargoAPI cargo = station.getCargo();

		List weaponIds = sector.getAllWeaponIds();
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
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "wolf_Hull", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "broadsword_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "broadsword_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "piranha_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "piranha_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "talon_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "talon_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "thunder_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "thunder_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "gladius_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "warthog_wing", null);
		cargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "warthog_wing", null);
		
		cargo.addMothballedShip(FleetMemberType.SHIP, "buffalo2_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "buffalo2_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "condor_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "tarsus_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "tarsus_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "gemini_Hull", null);


		cargo.addMothballedShip(FleetMemberType.SHIP, "venture_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "dominator_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "conquest_Hull", null);
	}
}
