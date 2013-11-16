package data.scripts.world.systems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoAPI.CrewXPLevel;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.OrbitAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import data.scripts.uss.FactionSpawnPoint;
import data.scripts.uss.UsSData;
import data.scripts.uss.UsSUtils;
import java.awt.Color;

@SuppressWarnings("unchecked")
public class Mirage {

	public void generate(SectorAPI sector) {
		
		StarSystemAPI system = sector.createStarSystem("Mirage");
		system.getLocation().set(-2623, 10823);
		system.setBackgroundTextureFilename("khs_graphics/backgrounds/background4.jpg");
		
		LocationAPI hyper = Global.getSector().getHyperspace();
		
		PlanetAPI sun = system.initStar("khs_star_yellow_white", 750f);
		system.setLightColor(new Color(255, 255, 230)); 
		
		SectorEntityToken sun2 = system.addPlanet(sun, "Mirage Beta", "khs_star_yellow_white", 225, 600, 1500, 90);
		SectorEntityToken mirageI = system.addPlanet(sun, "Mirage I", "lava", 0, 215, 3756, 88);
		SectorEntityToken mirageII = system.addPlanet(sun, "Mirage II", "rocky_unstable", 60, 290, 5032, 183);
		SectorEntityToken mirageIII = system.addPlanet(sun, "Mirage III", "jungle", 120, 330, 7343, 365);
                SectorEntityToken mirageIIIA = system.addPlanet(mirageIII, "Mirage IIIA", "desert", 0, 90, 840, 26);
		SectorEntityToken mirageIIIB = system.addPlanet(mirageIII, "Mirage IIIB", "barren", 45, 80, 1314, 28);
		SectorEntityToken mirageIV = system.addPlanet(sun, "Mirage IV", "toxic", 180, 310, 9325, 604);
		SectorEntityToken mirageIVA = system.addPlanet(mirageIV, "Mirage IVA", "toxic", 180, 140, 720, 35);
		SectorEntityToken mirageV = system.addPlanet(sun, "Mirage V", "ice_giant", 240, 600, 11624, 778);
		SectorEntityToken mirageVA = system.addPlanet(mirageV, "Mirage VA", "cryovolcanic", 200, 80, 885, 12);
		SectorEntityToken mirageVB = system.addPlanet(mirageV, "Mirage VB", "rocky_ice", 60, 90, 1352, 28);
		SectorEntityToken mirageVC = system.addPlanet(mirageV, "Mirage VC", "barren", 75, 120, 1724, 35);
		SectorEntityToken mirageVD = system.addPlanet(mirageV, "Mirage VD", "cryovolcanic", 220, 100, 1992, 36);
		SectorEntityToken mirageVE = system.addPlanet(mirageV, "Mirage VE", "frozen", 250, 90, 2285, 40);
		SectorEntityToken mirageVI = system.addPlanet(sun, "Mirage VI", "rocky_ice", 300, 130, 13561, 250);
		SectorEntityToken mirageVIA = system.addPlanet(mirageVI, "Mirage VIA", "frozen", 0, 90, 332, 30);
		
		system.addRingBand(mirageV, "misc", "rings1", 256f, 3, Color.yellow, 256f, 800, 70f);
		system.addRingBand(mirageV, "misc", "rings1", 256f, 3, Color.yellow, 256f, 850, 90f);
		system.addRingBand(mirageV, "misc", "rings1", 256f, 3, Color.white, 256f, 900, 110f);
		system.addRingBand(mirageV, "misc", "rings1", 256f, 0, Color.white, 256f, 1200, 50f);
		system.addRingBand(mirageV, "misc", "rings1", 256f, 0, Color.white, 256f, 1150, 50f);
		system.addRingBand(mirageV, "misc", "rings1", 256f, 1, Color.white, 256f, 1200, 70f);
		system.addRingBand(mirageV, "misc", "rings1", 256f, 0, Color.white, 256f, 1200, 80f);
		system.addRingBand(mirageV, "misc", "rings1", 256f, 0, Color.white, 256f, 1250, 90f);
		system.addRingBand(mirageV, "misc", "rings1", 256f, 3, Color.yellow, 256f, 1500, 70f);
		system.addRingBand(mirageV, "misc", "rings1", 256f, 3, Color.yellow, 256f, 1550, 90f);
		system.addRingBand(mirageV, "misc", "rings1", 256f, 3, Color.white, 256f, 1600, 110f);
		system.addRingBand(mirageV, "misc", "rings1", 256f, 0, Color.white, 256f, 1900, 50f);
		system.addRingBand(mirageV, "misc", "rings1", 256f, 0, Color.white, 256f, 1850, 50f);
		system.addRingBand(mirageV, "misc", "rings1", 256f, 1, Color.white, 256f, 1900, 70f);
		system.addRingBand(mirageV, "misc", "rings1", 256f, 0, Color.white, 256f, 1900, 80f);
		system.addRingBand(mirageV, "misc", "rings1", 256f, 0, Color.white, 256f, 1950, 90f);
		
		
		system.addRingBand(mirageIV, "misc", "rings1", 256f, 2, Color.green, 256f, 315, 115f);
		system.addRingBand(mirageIV, "misc", "rings1", 256f, 2, Color.green, 256f, 320, 125f);
		system.addRingBand(mirageIVA, "misc", "rings1", 256f, 2, Color.green, 256f, 145, 115f);
		system.addRingBand(mirageIVA, "misc", "rings1", 256f, 2, Color.green, 256f, 150, 125f);
		
		JumpPointAPI jumpPointA = Global.getFactory().createJumpPoint("Jump Point Alpha");
		OrbitAPI orbitjpa = Global.getFactory().createCircularOrbit(mirageI, 0, 830, 88);
		jumpPointA.setOrbit(orbitjpa);
		jumpPointA.setRelatedPlanet(mirageI);
		jumpPointA.setStandardWormholeToHyperspaceVisual();
		system.addEntity(jumpPointA);
		
		JumpPointAPI jumpPointB = Global.getFactory().createJumpPoint("Jump Point Beta");
		OrbitAPI orbitjpb = Global.getFactory().createCircularOrbit(mirageIII, 120, 1660, 365);
		jumpPointB.setOrbit(orbitjpb);
		jumpPointB.setRelatedPlanet(mirageIII);
		jumpPointB.setStandardWormholeToHyperspaceVisual();
		system.addEntity(jumpPointB);
		
		system.autogenerateHyperspaceJumpPoints(true, true);
		
		SectorEntityToken rebelstation1 = system.addOrbitalStation(mirageII, 65, 500, 50, "Mining Station", "insurgency");
		SectorEntityToken hegstation = system.addOrbitalStation(mirageIII, 90, 900, 50, "Forward Operating Base", "hegemony");
		SectorEntityToken medinastation = system.addOrbitalStation(mirageV, 45, 720, 12, "Ice Mining Hub", "regime");
		SectorEntityToken neutralStation = system.addOrbitalStation(mirageVE, 65, 200, 26, "Abandoned Materials Processing Plant", "neutral");
		SectorEntityToken rebelstation2 = system.addOrbitalStation(mirageVI, 65, 600, 50, "Fortified Station", "insurgency");
                SectorEntityToken jpstation = system.addOrbitalStation(mirageIV, 90, 550, 12, "Salvage Plant", "junk_pirates");
                SectorEntityToken shadowShipyards = system.addOrbitalStation(mirageI, 225, 475, 90, "Trading Post", "shadow_industry");
                
                neutralStation.getCargo().setFreeTransfer(true);
                
                FactionSpawnPoint KADsdf = new FactionSpawnPoint(sector, system, 1, 1, medinastation, "regime", "scout", "Cathedral Fleet", "defend", 200, 280, UsSData.Variants_KAD,
                        UsSData.KAD_CS, UsSData.KAD_C, UsSData.KAD_D, UsSData.KAD_F, UsSData.KAD_W, UsSData.KAD_LG, "", UsSData.KAD_SW_FINAL, UsSData.KAD_MW_FINAL, UsSData.KAD_LW_FINAL,
                        25f,50f,66f,5f,5f,
                        20, 30, "leadership", "combat", "technology");
		system.addScript(KADsdf);
                
                FactionSpawnPoint KADsecpatrol = new FactionSpawnPoint(sector, system, 1, 2, medinastation, "regime", "scout", "Crusade Fleet", "secpatrol", 70, 120, UsSData.Variants_KAD,
                        UsSData.KAD_CS, UsSData.KAD_C, UsSData.KAD_D, UsSData.KAD_F, UsSData.KAD_W, UsSData.KAD_LG, "", UsSData.KAD_SW_FINAL, UsSData.KAD_MW_FINAL, UsSData.KAD_LW_FINAL,
                        25f,75f,75f,5f,5f,
                        15, 20, "combat", "leadership", "technology");
		system.addScript(KADsecpatrol);
                
                FactionSpawnPoint KADpatrol = new FactionSpawnPoint(sector, system, 1, 3, medinastation, "regime", "scout", "Evangelists", "patrol", 60, 90, UsSData.Variants_KAD,
                        UsSData.KAD_CS, UsSData.KAD_C, UsSData.KAD_D, UsSData.KAD_F, UsSData.KAD_W, UsSData.KAD_LG, "", UsSData.KAD_SW_FINAL, UsSData.KAD_MW_FINAL, UsSData.KAD_LW_FINAL,
                        25f,75f,75f,5f,5f,
                        15, 20, "combat", "leadership", "technology");
		system.addScript(KADpatrol);
                
                FactionSpawnPoint trader = new FactionSpawnPoint(sector, system, 1, 1, medinastation, "regime", "scout", "Trader", "trade", 20, 80, UsSData.Variants_KAD,
                        UsSData.KAD_CS, UsSData.KAD_C, UsSData.KAD_D, UsSData.KAD_F, UsSData.KAD_W, UsSData.KAD_TR, "", UsSData.KAD_SW_FINAL, UsSData.KAD_MW_FINAL, UsSData.KAD_LW_FINAL,
                        0f,25f,50f,25f,50f,
                        5, 10, "technology", "leadership", "combat");
		system.addScript(trader);  
		
                //---------------------------------------------------
                
		FactionSpawnPoint KADINpatrol = new FactionSpawnPoint(sector, system, 1, 3, rebelstation1, "insurgency", "scout", "Partisans", "jihad_regime", 30, 150, UsSData.Variants_INS,
                        UsSData.INS_CS, UsSData.INS_C, UsSData.INS_D, UsSData.INS_F, UsSData.INS_W, UsSData.KAD_LG, "", UsSData.KAD_SW_FINAL, UsSData.KAD_MW_FINAL, UsSData.KAD_LW_FINAL,
                        25f,75f,75f,5f,5f,
                        15, 20, "combat", "leadership", "technology");
		system.addScript(KADINpatrol);
                
                FactionSpawnPoint KADINpatrol2 = new FactionSpawnPoint(sector, system, 1, 3, rebelstation2, "insurgency", "scout", "Partisans", "jihad_regime", 30, 150, UsSData.Variants_INS,
                        UsSData.INS_CS, UsSData.INS_C, UsSData.INS_D, UsSData.INS_F, UsSData.INS_W, UsSData.KAD_LG, "", UsSData.KAD_SW_FINAL, UsSData.KAD_MW_FINAL, UsSData.KAD_LW_FINAL,
                        25f,75f,75f,5f,5f,
                        15, 20, "combat", "leadership", "technology");
		system.addScript(KADINpatrol2);
                
                FactionSpawnPoint KADINboss = new FactionSpawnPoint(sector, system, 35, 1, rebelstation1, "insurgency", "scout", "Rogue Caliphate", "jihad_regime", 250, 350, UsSData.Variants_INS,
                        UsSData.INS_CS, UsSData.INS_C, UsSData.INS_D, UsSData.INS_F, UsSData.INS_W, UsSData.KAD_LG, "", UsSData.KAD_SW_FINAL, UsSData.KAD_MW_FINAL, UsSData.KAD_LW_FINAL,
                        25f,50f,66f,5f,5f,
                        25, 30, "leadership", "combat", "technology");
		system.addScript(KADINboss);
		
                //---------------------------------------------------
                
//                EliteSpawnPoint inshero = new EliteSpawnPoint(sector, system, 1, 1, rebelstation1, "insurgency", "scout", "merc", "defender", "friendly", 150, UsSData.All_Variants,
//                        UsSData.ALL_CS_FINAL, UsSData.ALL_C_FINAL, UsSData.INS_D, UsSData.ALL_F_FINAL, UsSData.ALL_W_FINAL, UsSData.CIV_LG, "",
//                        25f,50f,75f,33f,0f,
//                        "combat", "technology", "leadership");
//                system.addScript(inshero);
               
                //---------------------------------------------------
                
                FactionSpawnPoint HEGsdf = new FactionSpawnPoint(sector, system, 1, 1, hegstation, "hegemony", "patrol", "SDF", "defend", 200, 300, UsSData.Variants_HE,
                        UsSData.HE_CS, UsSData.HE_C, UsSData.HE_D, UsSData.HE_F, UsSData.HE_W, UsSData.CIV_LG, "onslaught_Hull", UsSData.LT_SW_FINAL, UsSData.LT_MW_FINAL, UsSData.LT_LW_FINAL,
                        25f,50f,66f,5f,5f,
                        20, 30, "leadership", "combat", "technology");
		system.addScript(HEGsdf);
                
                FactionSpawnPoint HEGpatrol = new FactionSpawnPoint(sector, system, 1, 3, hegstation, "hegemony", "patrol", "Patrol", "patrol", 25, 75, UsSData.Variants_HE,
                        UsSData.HE_CS, UsSData.HE_C, UsSData.HE_D, UsSData.HE_F, UsSData.HE_W, UsSData.HE_CRR, "enforcer_Hull", UsSData.LT_SW_FINAL, UsSData.LT_MW_FINAL, UsSData.LT_LW_FINAL,
                        0f,50f,66f,10f,0f,
                        5, 15, "combat", "leadership", "technology");
		system.addScript(HEGpatrol);
                
                FactionSpawnPoint HEGsecpatrol = new FactionSpawnPoint(sector, system, 1, 2, hegstation, "hegemony", "patrol", "Sector Patrol", "secpatrol", 70, 120, UsSData.Variants_HE,
                        UsSData.HE_CS, UsSData.HE_C, UsSData.HE_D, UsSData.HE_F, UsSData.HE_W, UsSData.CIV_LG, "enforcer_Hull", UsSData.LT_SW_FINAL, UsSData.LT_MW_FINAL, UsSData.LT_LW_FINAL,
                        25f,75f,75f,5f,5f,
                        15, 20, "combat", "leadership", "technology");
		system.addScript(HEGsecpatrol);
                
                FactionSpawnPoint HEtrader = new FactionSpawnPoint(sector, system, 1, 1, hegstation, "hegemony", "patrol", "Trader", "trade", 20, 80, UsSData.Variants_HE,
                        UsSData.HE_CS, UsSData.HE_C, UsSData.HE_D, UsSData.HE_F, UsSData.HE_W, UsSData.HE_TR, "enforcer_Hull", UsSData.LT_SW_FINAL, UsSData.LT_MW_FINAL, UsSData.LT_LW_FINAL,
                        0f,25f,50f,25f,50f,
                        5, 10, "technology", "leadership", "combat");
		system.addScript(HEtrader);
                
                //---------------------------------------------------
                
                FactionSpawnPoint JPsmallRaiders = new FactionSpawnPoint(sector, system, 1, 3, jpstation, "junk_pirates", "scout", "Salvage Team", "raid", 0, 25, UsSData.Variants_JP,
                        UsSData.JP_CS, UsSData.JP_C, UsSData.JP_D, UsSData.JP_F, UsSData.JP_W, UsSData.JP_TR, "", UsSData.JP_SW_FINAL, UsSData.JP_MW_FINAL, UsSData.JP_LW_FINAL,
                        0f,0f,40f,25f,0f,
                        3, 8, "combat", "leadership", "technology");
		system.addScript(JPsmallRaiders);
                
                FactionSpawnPoint JPdefense = new FactionSpawnPoint(sector, system, 1, 1, jpstation, "junk_pirates", "scout", "Retained Fleet", "defend", 80, 120, UsSData.Variants_JP,
                        UsSData.JP_CS, UsSData.JP_C, UsSData.JP_D, UsSData.JP_F, UsSData.JP_W, UsSData.JP_TR, "", UsSData.JP_SW_FINAL, UsSData.JP_MW_FINAL, UsSData.JP_LW_FINAL,
                        25f,50f,66f,33f,0f,
                        13, 18, "combat", "leadership", "technology");
		system.addScript(JPdefense);
                
                FactionSpawnPoint JPmediumRaiders = new FactionSpawnPoint(sector, system, 1, 2, jpstation, "junk_pirates", "scout", "Salvage Expedition", "raid", 35, 60, UsSData.Variants_JP,
                        UsSData.JP_CS, UsSData.JP_C, UsSData.JP_D, UsSData.JP_F, UsSData.JP_W, UsSData.JP_TR, "", UsSData.JP_SW_FINAL, UsSData.JP_MW_FINAL, UsSData.JP_LW_FINAL,
                        10f,40f,66f,25f,0f,
                        8, 13, "combat", "leadership", "technology");
		system.addScript(JPmediumRaiders);
                
                FactionSpawnPoint JPminer = new FactionSpawnPoint(sector, system, 1, 1, jpstation, "junk_pirates", "scout", "Miner", "mine", 40, 70, UsSData.Variants_JP,
                        UsSData.JP_CS, UsSData.JP_C, UsSData.JP_D, UsSData.JP_F, UsSData.JP_W, UsSData.JP_TR, "junk_pirates_the_reaper_Hull", UsSData.JP_SW_FINAL, UsSData.JP_MW_FINAL, UsSData.JP_LW_FINAL,
                        0f,0f,50f,0f,25f,
                        3, 5, "leadership", "combat", "combat");
		system.addScript(JPminer);
                
                FactionSpawnPoint JPtrader = new FactionSpawnPoint(sector, system, 1, 1, jpstation, "junk_pirates", "scout", "Plunder Fleet", "trade", 20, 80, UsSData.Variants_JP,
                        UsSData.JP_CS, UsSData.JP_C, UsSData.JP_D, UsSData.JP_F, UsSData.JP_W, UsSData.JP_TR, "", UsSData.JP_SW_FINAL, UsSData.JP_MW_FINAL, UsSData.JP_LW_FINAL,
                        0f,25f,50f,25f,50f,
                        5, 10, "technology", "leadership", "combat");
		system.addScript(JPtrader);
                
                //---------------------------------------------------
                
                FactionSpawnPoint SHIdef = new FactionSpawnPoint(sector, system, 1, 1, shadowShipyards, "shadow_industry", "scout", "SDF", "defend", 120, 180, UsSData.Variants_SHI,
                        UsSData.SHI_CS, UsSData.SHI_C, UsSData.SHI_D, UsSData.SHI_F, UsSData.SHI_W, UsSData.SHI_TR, "ms_mimir_Hull", UsSData.SHI_SW_FINAL, UsSData.SHI_MW_FINAL, UsSData.SHI_LW_FINAL,
                        0f,75f,66f,50f,5f,
                        15, 25, "technology", "leadership", "combat");
		system.addScript(SHIdef);
                
		FactionSpawnPoint SHIpat = new FactionSpawnPoint(sector, system, 1, 2, shadowShipyards, "shadow_industry", "scout", "Patrol", "patrol", 50, 90, UsSData.Variants_SHI,
                        UsSData.SHI_CS, UsSData.SHI_C, UsSData.SHI_D, UsSData.SHI_F, UsSData.SHI_W, UsSData.SHI_TR, "", UsSData.SHI_SW_FINAL, UsSData.SHI_MW_FINAL, UsSData.SHI_LW_FINAL,
                        0f,50f,66f,33f,0f,
                        10, 15, "technology", "combat", "leadership");
		system.addScript(SHIpat);
                
                FactionSpawnPoint SHIrec = new FactionSpawnPoint(sector, system, 1, 3, shadowShipyards, "shadow_industry", "scout", "Recon", "raid", 10, 50, UsSData.Variants_SHI,
                        UsSData.SHI_CS, UsSData.SHI_C, UsSData.SHI_D, UsSData.SHI_F, UsSData.SHI_W, UsSData.SHI_TR, "", UsSData.SHI_SW_FINAL, UsSData.SHI_MW_FINAL, UsSData.SHI_LW_FINAL,
                        0f,0f,66f,33f,0f,
                        5, 10, "technology", "combat", "leadership");
		system.addScript(SHIrec);
                
                FactionSpawnPoint SHItrader = new FactionSpawnPoint(sector, system, 1, 1, shadowShipyards, "shadow_industry", "scout", "Trader", "trade", 20, 80, UsSData.Variants_SHI,
                        UsSData.SHI_CS, UsSData.SHI_C, UsSData.SHI_D, UsSData.SHI_F, UsSData.SHI_W, UsSData.SHI_TR, "", UsSData.SHI_SW_FINAL, UsSData.SHI_MW_FINAL, UsSData.SHI_LW_FINAL,
                        0f,25f,50f,25f,50f,
                        5, 10, "technology", "leadership", "combat");
		system.addScript(SHItrader);  
                                
                //---------------------------------------------------
                
		CargoAPI cargo = medinastation.getCargo();
		cargo.addWeapons("khs_kinetic_violator", 4);
		cargo.addWeapons("khs_assault_violator", 4);
		cargo.addWeapons("khs_kinetic_penetrator", 7);
		cargo.addWeapons("khs_assault_penetrator", 7);
		cargo.addWeapons("khs_volley_cannon", 10);
		cargo.addWeapons("khs_sling", 10);
		cargo.addWeapons("khs_partisan", 6);
		cargo.addWeapons("khs_javelin_vlrms", 7);
		cargo.addWeapons("khs_lance_vlrms", 6);
		cargo.addWeapons("khs_martyrcannon", 7);
		cargo.addCrew(CrewXPLevel.REGULAR, 400);
		cargo.addCrew(CrewXPLevel.GREEN, 800);
		cargo.addSupplies(800);
		cargo.addFuel(200);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_mutt_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_mutt_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_jackal_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_hyena_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_hyena_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_buzzard_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_camel_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_vulture_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_falchion_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_leviathan_Hull", null);
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
		
		CargoAPI cargoH = hegstation.getCargo();
		cargoH.addWeapons("bomb", 10);
		cargoH.addWeapons("reaper", 5);
		cargoH.addWeapons("lightac", 10);
		cargoH.addWeapons("lightmg", 20);
		cargoH.addWeapons("annihilator", 5);
		cargoH.addWeapons("taclaser", 5);
		cargoH.addWeapons("harpoon_single", 5); //medium
		cargoH.addWeapons("lightmortar", 20);
		cargoH.addWeapons("mininglaser", 10);
		cargoH.addWeapons("pdlaser", 10);
		cargoH.addWeapons("mark9", 1); //large
		cargoH.addCrew(CrewXPLevel.REGULAR, 30);
		cargoH.addCrew(CrewXPLevel.GREEN, 500);
		cargoH.addMarines(100);
		cargoH.addSupplies(630);
		cargoH.addFuel(500);
		cargoH.addMothballedShip(FleetMemberType.SHIP, "hound_Hull", null);
		cargoH.addMothballedShip(FleetMemberType.SHIP, "lasher_Hull", null);
		cargoH.addMothballedShip(FleetMemberType.SHIP, "dram_Hull", null);
		cargoH.addMothballedShip(FleetMemberType.SHIP, "enforcer_Hull", null);
		cargoH.addMothballedShip(FleetMemberType.SHIP, "condor_Hull", null);
		cargoH.addMothballedShip(FleetMemberType.SHIP, "falcon_Hull", null);
		cargoH.addMothballedShip(FleetMemberType.SHIP, "dominator_Hull", null);
		cargoH.addMothballedShip(FleetMemberType.SHIP, "venture_Hull", null);
		cargoH.addMothballedShip(FleetMemberType.SHIP, "atlas_Hull", null);
		cargoH.addMothballedShip(FleetMemberType.SHIP, "mule_Hull", null);
		cargoH.addMothballedShip(FleetMemberType.FIGHTER_WING, "talon_wing", null);
		cargoH.addMothballedShip(FleetMemberType.FIGHTER_WING, "talon_wing", null);
		
		CargoAPI cargoR1 = rebelstation1.getCargo();
		cargoR1.addWeapons("bomb", 25);
		cargoR1.addWeapons("reaper", 12);
		cargoR1.addWeapons("lightac", 25);
		cargoR1.addWeapons("lightmg", 40);
		cargoR1.addWeapons("annihilator", 10);
		cargoR1.addWeapons("taclaser", 10);
		cargoR1.addWeapons("harpoon_single", 12); //medium
		cargoR1.addWeapons("lightmortar", 40);
		cargoR1.addWeapons("miningblaster", 1); //medium
		cargoR1.addWeapons("swarmer", 5);
		cargoR1.addWeapons("mininglaser", 25);
		cargoR1.addWeapons("pdlaser", 25);
		cargoR1.addWeapons("flak", 5); //medium
		cargoR1.addWeapons("shredder", 5); //medium
		cargoR1.addWeapons("annihilatorpod", 1); //medium
		cargoR1.addWeapons("pilum", 2); //medium
		cargoR1.addWeapons("mark9", 2); //large
		cargoR1.addCrew(CrewXPLevel.REGULAR, 30);
		cargoR1.addCrew(CrewXPLevel.GREEN, 500);
		cargoR1.addMarines(100);
		cargoR1.addSupplies(630);
		cargoR1.addFuel(500);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "vigilance_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "hound_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "lasher_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "brawler_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "enforcer_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "dominator_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.FIGHTER_WING, "talon_wing", null);
		cargoR1.addMothballedShip(FleetMemberType.FIGHTER_WING, "talon_wing", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "khs_mutt_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "khs_mutt_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "khs_jackal_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "khs_hyena_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "khs_hyena_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "khs_buzzard_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "khs_vulture_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "khs_falchion_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "khs_leviathan_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "khs_mistral_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "khs_mistral_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "khs_sirocco_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "khs_sirocco_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "khs_djinn_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "khs_djinn_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "khs_dolphin_Hull", null);
		cargoR1.addMothballedShip(FleetMemberType.SHIP, "khs_dolphin_Hull", null);
		
		CargoAPI cargoR2 = rebelstation2.getCargo();
		cargoR2.addWeapons("bomb", 25);
		cargoR2.addWeapons("reaper", 12);
		cargoR2.addWeapons("lightac", 25);
		cargoR2.addWeapons("lightmg", 40);
		cargoR2.addWeapons("annihilator", 10);
		cargoR2.addWeapons("taclaser", 10);
		cargoR2.addWeapons("harpoon_single", 12); //medium
		cargoR2.addWeapons("lightmortar", 40);
		cargoR2.addWeapons("miningblaster", 1); //medium
		cargoR2.addWeapons("swarmer", 5);
		cargoR2.addWeapons("mininglaser", 25);
		cargoR2.addWeapons("pdlaser", 25);
		cargoR2.addWeapons("flak", 5); //medium
		cargoR2.addWeapons("shredder", 5); //medium
		cargoR2.addWeapons("annihilatorpod", 1); //medium
		cargoR2.addWeapons("pilum", 2); //medium
		cargoR2.addWeapons("mark9", 2); //large
		cargoR2.addCrew(CrewXPLevel.REGULAR, 30);
		cargoR2.addCrew(CrewXPLevel.GREEN, 500);
		cargoR2.addMarines(100);
		cargoR2.addSupplies(630);
		cargoR2.addFuel(500);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "vigilance_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "hound_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "lasher_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "brawler_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "enforcer_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "dominator_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.FIGHTER_WING, "talon_wing", null);
		cargoR2.addMothballedShip(FleetMemberType.FIGHTER_WING, "talon_wing", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "khs_mutt_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "khs_mutt_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "khs_jackal_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "khs_hyena_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "khs_hyena_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "khs_buzzard_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "khs_vulture_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "khs_falchion_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "khs_leviathan_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "khs_mistral_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "khs_mistral_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "khs_sirocco_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "khs_sirocco_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "khs_djinn_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "khs_djinn_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "khs_dolphin_Hull", null);
		cargoR2.addMothballedShip(FleetMemberType.SHIP, "khs_dolphin_Hull", null);
                
                CargoAPI JPcargo = jpstation.getCargo();
                for (int i = 0; i < 20; i++ ) {
                    UsSUtils.addFactionWeapon(JPcargo, UsSData.JP_SW_FINAL, UsSData.JP_MW_FINAL, UsSData.JP_LW_FINAL);
                }
		JPcargo.addCrew(CrewXPLevel.VETERAN, 20);
		JPcargo.addCrew(CrewXPLevel.REGULAR, 120);
		JPcargo.addMarines(100);
		JPcargo.addSupplies(1000);
		JPcargo.addFuel(500);
		JPcargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_clam_Hull"));
		JPcargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_langoustine_Hull"));
		JPcargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_octopus_Hull"));
		JPcargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_sickle_Hull"));
		JPcargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_scythe_Hull"));
		JPcargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_goat_Hull"));
		JPcargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_hammer_Hull"));
		JPcargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_stoatA_Hull"));
		JPcargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_stoatB_Hull"));
		JPcargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_boxenstein_Hull"));
		JPcargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, "junk_pirates_spike_wing"));
		JPcargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, "junk_pirates_splinter_wing"));
		JPcargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, "junk_pirates_shard_wing"));
		JPcargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, "junk_pirates_spike_wing"));
		JPcargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, "junk_pirates_cleat_wing"));
		JPcargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_dugong_Hull"));
                
                CargoAPI SHIcargo = shadowShipyards.getCargo();
                SHIcargo.addCrew(CrewXPLevel.VETERAN, 100);
                SHIcargo.addCrew(CrewXPLevel.REGULAR, 700);
                SHIcargo.addMarines(500);
                SHIcargo.addSupplies(50);
                for (int i = 0; i < 20; i++ ) {
                    UsSUtils.addFactionWeapon(SHIcargo, UsSData.SHI_SW_FINAL, UsSData.SHI_MW_FINAL, UsSData.SHI_LW_FINAL);
                }
                SHIcargo.addMothballedShip(FleetMemberType.SHIP, "ms_charybdis_Hull", null);
                SHIcargo.addMothballedShip(FleetMemberType.SHIP, "ms_elysium_Hull", null);
                SHIcargo.addMothballedShip(FleetMemberType.SHIP, "ms_morningstar_Hull", null);
                SHIcargo.addMothballedShip(FleetMemberType.SHIP, "ms_solidarity_Hull", null);
                SHIcargo.addMothballedShip(FleetMemberType.SHIP, "ms_sargasso_Hull", null);
                SHIcargo.addMothballedShip(FleetMemberType.SHIP, "ms_enlil_Hull", null);
                SHIcargo.addMothballedShip(FleetMemberType.SHIP, "ms_enlil_Hull", null);
                SHIcargo.addMothballedShip(FleetMemberType.SHIP, "ms_inanna_Hull", null);
                SHIcargo.addMothballedShip(FleetMemberType.SHIP, "ms_inanna_Hull", null);
                SHIcargo.addMothballedShip(FleetMemberType.SHIP, "ms_seski_Hull", null);
                SHIcargo.addMothballedShip(FleetMemberType.SHIP, "ms_seski_Hull", null);
                SHIcargo.addMothballedShip(FleetMemberType.SHIP, "ms_shamash_Hull", null);
                SHIcargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "ms_skinwalker_wing", null);
                SHIcargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "ms_neriad_wing", null);
                SHIcargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "ms_raksasha_wing", null);
                SHIcargo.addMothballedShip(FleetMemberType.FIGHTER_WING, "ms_raksasha_wing", null);
        }	
}















