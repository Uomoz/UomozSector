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
import data.scripts.uss.UsSUtils;
import java.awt.Color;

@SuppressWarnings("unchecked")
public class Oasis {

	public void generate(SectorAPI sector) {
		
		StarSystemAPI system = sector.createStarSystem("Oasis");
		system.getLocation().set(7121, 7723);
		system.setBackgroundTextureFilename("khs_graphics/backgrounds/background2.jpg");
		
		PlanetAPI sun = system.initStar("khs_star_brown", 550f);
		system.setLightColor(new Color(150, 90, 110)); 
		
		SectorEntityToken oasisI = system.addPlanet(sun, "Oasis I", "lava", 80, 190, 2553, 64);
		SectorEntityToken oasisII = system.addPlanet(sun, "Oasis II", "rocky_unstable", 150, 230, 4502, 173);
		SectorEntityToken oasisIII = system.addPlanet(sun, "Oasis III", "gas_giant", 220, 400, 6925, 302);
		SectorEntityToken oasisIIIA = system.addPlanet(oasisIII, "Oasis IIIA", "toxic", 200, 80, 550, 12);
		SectorEntityToken oasisIIIB = system.addPlanet(oasisIII, "Oasis IIIB", "barren", 60, 90, 750, 28);
		SectorEntityToken oasisIIIC = system.addPlanet(oasisIII, "Oasis IIIC", "rocky_metallic", 75, 120, 1525, 35);
		SectorEntityToken oasisIIID = system.addPlanet(oasisIII, "Oasis IIID", "frozen", 220, 100, 1766, 60);
		SectorEntityToken oasisIV = system.addPlanet(sun, "Oasis IV", "frozen", 270, 280, 8923, 468);
		SectorEntityToken oasisIVA = system.addPlanet(oasisIV, "Oasis IVA", "cryovolcanic", 110, 95, 600, 24);
		SectorEntityToken oasisV = system.addPlanet(sun, "Oasis V", "rocky_metallic", 330, 120, 10745, 604);
		
		system.addRingBand(oasisIII, "misc", "rings1", 256f, 2, Color.white, 256f, 900, 60f);
		system.addRingBand(oasisIII, "misc", "rings1", 256f, 2, Color.white, 256f, 900, 80f);
		system.addRingBand(oasisIII, "misc", "rings1", 256f, 3, Color.white, 256f, 1100, 70f);
		system.addRingBand(oasisIII, "misc", "rings1", 256f, 3, Color.white, 256f, 1100, 90f);
		system.addRingBand(oasisIII, "misc", "rings1", 256f, 2, Color.white, 256f, 900, 40f);
		system.addRingBand(oasisIII, "misc", "rings1", 256f, 3, Color.white, 256f, 1100, 110f);
		system.addRingBand(oasisIII, "misc", "rings1", 256f, 0, Color.white, 256f, 1350, 50f);
		system.addRingBand(oasisIII, "misc", "rings1", 256f, 1, Color.white, 256f, 1350, 70f);
		system.addRingBand(oasisIII, "misc", "rings1", 256f, 0, Color.white, 256f, 1350, 80f);
		system.addRingBand(oasisIII, "misc", "rings1", 256f, 0, Color.white, 256f, 1300, 90f);
		
		system.addRingBand(oasisII, "misc", "rings1", 256f, 2, Color.red, 256f, 225, 55f);
		system.addRingBand(oasisII, "misc", "rings1", 256f, 2, Color.red, 256f, 235, 65f);
		system.addRingBand(oasisII, "misc", "rings1", 256f, 2, Color.white, 256f, 230, 55f);
		system.addRingBand(oasisII, "misc", "rings1", 256f, 2, Color.white, 256f, 240, 65f);
		
		JumpPointAPI jumpPointA = Global.getFactory().createJumpPoint("Jump Point Alpha");
		OrbitAPI orbitjpa = Global.getFactory().createCircularOrbit(sun, 180, 3843, 190);
		jumpPointA.setOrbit(orbitjpa);
		jumpPointA.setRelatedPlanet(oasisII);
		jumpPointA.setStandardWormholeToHyperspaceVisual();
		system.addEntity(jumpPointA);
		
		system.autogenerateHyperspaceJumpPoints(true, true);
		
		SectorEntityToken jerusalemstation = system.addOrbitalStation(oasisV, 65, 300, 50, "Mining Outpost", "regime");
		SectorEntityToken ttstation = system.addOrbitalStation(oasisIII, 90, 1150, 50, "Corporate Staging Base", "tritachyon");
		SectorEntityToken piratestation = system.addOrbitalStation(oasisIV, 90, 720, 50, "Raider Base", "pirates");
		SectorEntityToken neutralStation = system.addOrbitalStation(oasisII, 45, 450, 50, "Abandoned System Defense Outpost", "neutral");
                SectorEntityToken BRstation = system.addOrbitalStation(sun, 120, 1225, 90, "Solar Research Yard", "blackrock_driveyards");
		neutralStation.getCargo().setFreeTransfer(true);
                
                FactionSpawnPoint KADsdf = new FactionSpawnPoint(sector, system, 1, 1, jerusalemstation, "regime", "scout", "Cathedral Fleet", "defend", 200, 280, UsSData.Variants_KAD,
                        UsSData.KAD_CS, UsSData.KAD_C, UsSData.KAD_D, UsSData.KAD_F, UsSData.KAD_W, UsSData.KAD_LG, "", UsSData.KAD_SW_FINAL, UsSData.KAD_MW_FINAL, UsSData.KAD_LW_FINAL,
                        25f,50f,66f,5f,5f,
                        20, 30, "leadership", "combat", "technology");
		system.addScript(KADsdf);
                
                FactionSpawnPoint KADpatrol = new FactionSpawnPoint(sector, system, 1, 3, jerusalemstation, "regime", "scout", "Evangelists", "patrol", 60, 90, UsSData.Variants_KAD,
                        UsSData.KAD_CS, UsSData.KAD_C, UsSData.KAD_D, UsSData.KAD_F, UsSData.KAD_W, UsSData.KAD_LG, "", UsSData.KAD_SW_FINAL, UsSData.KAD_MW_FINAL, UsSData.KAD_LW_FINAL,
                        25f,75f,75f,5f,5f,
                        15, 20, "combat", "leadership", "technology");
		system.addScript(KADpatrol);
                
                FactionSpawnPoint trader = new FactionSpawnPoint(sector, system, 1, 1, jerusalemstation, "regime", "scout", "Trader", "trade", 20, 80, UsSData.Variants_KAD,
                        UsSData.KAD_CS, UsSData.KAD_C, UsSData.KAD_D, UsSData.KAD_F, UsSData.KAD_W, UsSData.KAD_TR, "", UsSData.KAD_SW_FINAL, UsSData.KAD_MW_FINAL, UsSData.KAD_LW_FINAL,
                        0f,25f,50f,25f,50f,
                        5, 10, "technology", "leadership", "combat");
		system.addScript(trader);  
		
                //---------------------------------------------------
                
                FactionSpawnPoint PIRsmallRaiders = new FactionSpawnPoint(sector, system, 1, 6, piratestation, "pirates", "scout", "Scavengers", "raid", 0, 15, UsSData.Variants_PP,
                        UsSData.PP_CS, UsSData.PP_C, UsSData.PP_D, UsSData.PP_F, UsSData.PP_W, UsSData.PP_B, "", UsSData.LT_SW_FINAL, UsSData.LT_MW_FINAL, UsSData.LT_LW_FINAL,
                        0f,0f,50f,25f,33f,
                        1, 5, "combat", "leadership", "technology");
		system.addScript(PIRsmallRaiders);
                
                FactionSpawnPoint PIRmediumRaiders = new FactionSpawnPoint(sector, system, 1, 3, piratestation, "pirates", "scout", "Raiders", "raid", 30, 50, UsSData.Variants_PP,
                        UsSData.PP_CS, UsSData.PP_C, UsSData.PP_D, UsSData.PP_F, UsSData.PP_W, UsSData.PP_CRR, "", UsSData.LT_SW_FINAL, UsSData.LT_MW_FINAL, UsSData.LT_LW_FINAL,
                        0f,25f,50f,50f,0f,
                        5, 10, "combat", "leadership", "technology");
		system.addScript(PIRmediumRaiders);
                
                FactionSpawnPoint PIRdefense = new FactionSpawnPoint(sector, system, 1, 2, piratestation, "pirates", "scout", "Armada", "defend", 60, 120, UsSData.Variants_PP,
                        UsSData.PP_CS, UsSData.PP_C, UsSData.PP_D, UsSData.PP_F, UsSData.PP_W, UsSData.PP_CRR, "", UsSData.LT_SW_FINAL, UsSData.LT_MW_FINAL, UsSData.LT_LW_FINAL,
                        25f,50f,50f,33f,0f,
                        10, 15, "combat", "leadership", "technology");
		system.addScript(PIRdefense);
                                
                FactionSpawnPoint plunder = new FactionSpawnPoint(sector, system, 1, 1, piratestation, "pirates", "scout", "Plunder Fleet", "trade", 20, 80, UsSData.Variants_PP,
                        UsSData.PP_CS, UsSData.PP_C, UsSData.PP_D, UsSData.PP_F, UsSData.PP_W, UsSData.PP_TR, "", UsSData.LT_SW_FINAL, UsSData.LT_MW_FINAL, UsSData.LT_LW_FINAL,
                        0f,25f,50f,25f,50f,
                        5, 10, "technology", "leadership", "combat");
		system.addScript(plunder);
                
                //---------------------------------------------------
                
//                EliteSpawnPoint PIRlead = new EliteSpawnPoint(sector, system, 1, 2, piratestation, "pirates", "scout", "merc", "raider", "friendly", 150, UsSData.All_Variants,
//                        UsSData.ALL_CS_FINAL, UsSData.ALL_C_FINAL, UsSData.PP_D, UsSData.ALL_F_FINAL, UsSData.ALL_W_FINAL, UsSData.CIV_LG, "",
//                        25f,50f,50f,33f,0f,
//                        "combat", "technology", "leadership");
//		system.addScript(PIRlead);
                                
                //---------------------------------------------------
                
                FactionSpawnPoint TTscouts = new FactionSpawnPoint(sector, system, 1, 3, ttstation, "tritachyon", "scout", "Scout", "raid", 0, 20, UsSData.Variants_TT,
                        UsSData.TT_CS, UsSData.TT_C, UsSData.TT_D, UsSData.TT_F, UsSData.TT_W, UsSData.TT_CRR, "", UsSData.HT_SW_FINAL, UsSData.HT_MW_FINAL, UsSData.HT_LW_FINAL,
                        0f,0f,0f,50f,0f,
                        5, 10, "technology", "combat", "leadership");
		system.addScript(TTscouts);
                
                FactionSpawnPoint TTsecurity = new FactionSpawnPoint(sector, system, 1, 1, ttstation, "tritachyon", "scout", "Security Detachment", "defend", 70, 130, UsSData.Variants_TT,
                        UsSData.TT_CS, UsSData.TT_C, UsSData.TT_D, UsSData.TT_F, UsSData.TT_W, UsSData.CIV_LG, "astral_Hull", UsSData.HT_SW_FINAL, UsSData.HT_MW_FINAL, UsSData.HT_LW_FINAL,
                        25f,75f,60f,50f,5f,
                        20, 30, "technology", "combat", "leadership");
		system.addScript(TTsecurity);
                
                FactionSpawnPoint TTattack = new FactionSpawnPoint(sector, system, 1, 2, ttstation, "tritachyon", "scout", "Wing", "patrol", 30, 50, UsSData.Variants_TT,
                        UsSData.TT_CS, UsSData.TT_C, UsSData.TT_D, UsSData.TT_F, UsSData.TT_W, UsSData.TT_CRR, "", UsSData.HT_SW_FINAL, UsSData.HT_MW_FINAL, UsSData.HT_LW_FINAL,
                        0f,25f,75f,50f,0f,
                        10, 15, "technology", "combat", "leadership");
		system.addScript(TTattack);
                
                FactionSpawnPoint TTtrader = new FactionSpawnPoint(sector, system, 1, 1, ttstation, "tritachyon", "scout", "Trader", "trade", 20, 80, UsSData.Variants_TT,
                        UsSData.TT_CS, UsSData.TT_C, UsSData.TT_D, UsSData.TT_F, UsSData.TT_W, UsSData.HE_TR, "", UsSData.HT_SW_FINAL, UsSData.HT_MW_FINAL, UsSData.HT_LW_FINAL,
                        0f,25f,50f,25f,50f,
                        5, 10, "technology", "leadership", "combat");
		system.addScript(TTtrader); 
                
                //---------------------------------------------------
                
                FactionSpawnPoint BRdef = new FactionSpawnPoint(sector, system, 1, 1, BRstation, "blackrock_driveyards", "scout", "Sovereignity Fleet", "defend", 150, 200, UsSData.Variants_BR,
                        UsSData.BR_CS, UsSData.BR_C, UsSData.BR_D, UsSData.BR_F, UsSData.BR_W, UsSData.BR_TR, "brdy_karkinos_Hull", UsSData.BR_SW_FINAL, UsSData.BR_MW_FINAL, UsSData.BR_LW_FINAL,
                        0f,75f,75f,50f,5f,
                        15, 25, "technology", "leadership", "combat");
		system.addScript(BRdef);
                
		FactionSpawnPoint BRpat = new FactionSpawnPoint(sector, system, 1, 2, BRstation, "blackrock_driveyards", "scout", "Security Patrol", "patrol", 50, 90, UsSData.Variants_BR,
                        UsSData.BR_CS, UsSData.BR_C, UsSData.BR_D, UsSData.BR_F, UsSData.BR_W, UsSData.BR_TR, "brdy_nevermore_Hull", UsSData.BR_SW_FINAL, UsSData.BR_MW_FINAL, UsSData.BR_LW_FINAL,
                        0f,50f,75f,33f,0f,
                        10, 15, "technology", "combat", "leadership");
		system.addScript(BRpat);
                
                FactionSpawnPoint BRrec = new FactionSpawnPoint(sector, system, 1, 3, BRstation, "blackrock_driveyards", "scout", "Scout", "raid", 10, 50, UsSData.Variants_BR,
                        UsSData.BR_CS, UsSData.BR_C, UsSData.BR_D, UsSData.BR_F, UsSData.BR_W, UsSData.BR_TR, "brdy_desdinova_Hull", UsSData.BR_SW_FINAL, UsSData.BR_MW_FINAL, UsSData.BR_LW_FINAL,
                        0f,0f,75f,33f,0f,
                        5, 10, "technology", "combat", "leadership");
		system.addScript(BRrec);
                
                FactionSpawnPoint BRtrader = new FactionSpawnPoint(sector, system, 1, 1, BRstation, "blackrock_driveyards", "scout", "Trader", "trade", 20, 80, UsSData.Variants_BR,
                        UsSData.BR_CS, UsSData.BR_C, UsSData.BR_D, UsSData.BR_F, UsSData.BR_W, UsSData.BR_TR, "", UsSData.BR_SW_FINAL, UsSData.BR_MW_FINAL, UsSData.BR_LW_FINAL,
                        0f,25f,50f,25f,50f,
                        5, 10, "technology", "leadership", "combat");
		system.addScript(BRtrader); 
                                
                //---------------------------------------------------
				
		CargoAPI cargo = jerusalemstation.getCargo();
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
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_camel_Hull", null);
		cargo.addMothballedShip(FleetMemberType.SHIP, "khs_buzzard_Hull", null);
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

		CargoAPI cargoP = piratestation.getCargo();
		cargoP.addCrew(CrewXPLevel.VETERAN, 20);
		cargoP.addCrew(CrewXPLevel.REGULAR, 50);
		cargoP.addCrew(CrewXPLevel.GREEN, 100);
		cargoP.addMarines(50);
		cargoP.addSupplies(100);
		cargoP.addFuel(10);
		for (int i = 0; i < 20; i++ ) {
                    UsSUtils.addFactionWeapon(cargoP, UsSData.LT_SW_FINAL, UsSData.LT_MW_FINAL, UsSData.LT_LW_FINAL);
                }
		cargoP.addMothballedShip(FleetMemberType.FIGHTER_WING, "broadsword_wing", null);
		cargoP.addMothballedShip(FleetMemberType.FIGHTER_WING, "broadsword_wing", null);
		cargoP.addMothballedShip(FleetMemberType.FIGHTER_WING, "piranha_wing", null);
		cargoP.addMothballedShip(FleetMemberType.FIGHTER_WING, "piranha_wing", null);
		cargoP.addMothballedShip(FleetMemberType.FIGHTER_WING, "talon_wing", null);
		cargoP.addMothballedShip(FleetMemberType.FIGHTER_WING, "talon_wing", null);
		cargoP.addMothballedShip(FleetMemberType.SHIP, "buffalo2_Hull", null);
		cargoP.addMothballedShip(FleetMemberType.SHIP, "buffalo2_Hull", null);
		cargoP.addMothballedShip(FleetMemberType.SHIP, "condor_Hull", null);
		cargoP.addMothballedShip(FleetMemberType.SHIP, "tarsus_Hull", null);
		cargoP.addMothballedShip(FleetMemberType.SHIP, "tarsus_Hull", null);
		cargoP.addMothballedShip(FleetMemberType.SHIP, "gemini_Hull", null);
		cargoP.addMothballedShip(FleetMemberType.SHIP, "venture_Hull", null);
		cargoP.addMothballedShip(FleetMemberType.SHIP, "dominator_Hull", null);
		cargoP.addMothballedShip(FleetMemberType.SHIP, "conquest_Hull", null);
		
		CargoAPI cargoT = ttstation.getCargo();
		cargoT.addCrew(CrewXPLevel.ELITE, 10);
		cargoT.addCrew(CrewXPLevel.VETERAN, 10);
		cargoT.addCrew(CrewXPLevel.REGULAR, 50);
		cargoT.addMarines(75);
		cargoT.addSupplies(145);
		cargoT.addFuel(100);
		for (int i = 0; i < 20; i++ ) {
                    UsSUtils.addFactionWeapon(cargoT, UsSData.HT_SW_FINAL, UsSData.HT_MW_FINAL, UsSData.HT_LW_FINAL);
                }
		cargoT.addMothballedShip(FleetMemberType.FIGHTER_WING, "wasp_wing", null);
		cargoT.addMothballedShip(FleetMemberType.FIGHTER_WING, "wasp_wing", null);
		cargoT.addMothballedShip(FleetMemberType.FIGHTER_WING, "wasp_wing", null);
		cargoT.addMothballedShip(FleetMemberType.SHIP, "medusa_Hull", null);
		cargoT.addMothballedShip(FleetMemberType.SHIP, "medusa_Hull", null);
		cargoT.addMothballedShip(FleetMemberType.SHIP, "apogee_Hull", null);
		cargoT.addMothballedShip(FleetMemberType.SHIP, "tempest_Hull", null);
		cargoT.addMothballedShip(FleetMemberType.SHIP, "tempest_Hull", null);
		cargoT.addMothballedShip(FleetMemberType.SHIP, "buffalo_Hull", null);
		cargoT.addMothballedShip(FleetMemberType.SHIP, "buffalo_Hull", null);
		cargoT.addMothballedShip(FleetMemberType.SHIP, "omen_Hull", null);
		cargoT.addMothballedShip(FleetMemberType.SHIP, "omen_Hull", null);
		cargoT.addMothballedShip(FleetMemberType.SHIP, "doom_Hull", null);
		cargoT.addMothballedShip(FleetMemberType.SHIP, "afflictor_Hull", null);
		cargoT.addMothballedShip(FleetMemberType.SHIP, "shade_Hull", null);
                
                CargoAPI cargoBR = BRstation.getCargo();
                for (int i = 0; i < 20; i++ ) {
                    UsSUtils.addFactionWeapon(cargoBR, UsSData.BR_SW_FINAL, UsSData.BR_MW_FINAL, UsSData.BR_LW_FINAL);
                }
                cargoBR.addCrew(CargoAPI.CrewXPLevel.ELITE, 40);
                cargoBR.addCrew(CargoAPI.CrewXPLevel.VETERAN, 90);
                cargoBR.addMarines(75);
                cargoBR.addSupplies(645);
                cargoBR.addFuel(100);
                cargoBR.addMothballedShip(FleetMemberType.SHIP, "brdy_scarab_Hull", null);
                cargoBR.addMothballedShip(FleetMemberType.SHIP, "brdy_locust_Hull", null);
                cargoBR.addMothballedShip(FleetMemberType.SHIP, "brdy_locust_Hull", null);
                cargoBR.addMothballedShip(FleetMemberType.SHIP, "brdy_scarab_Hull", null);
                cargoBR.addMothballedShip(FleetMemberType.SHIP, "brdy_mantis_Hull", null);
                cargoBR.addMothballedShip(FleetMemberType.SHIP, "brdy_desdinova_Hull", null);
                cargoBR.addMothballedShip(FleetMemberType.SHIP, "brdy_cetonia_Hull", null);		
                cargoBR.addMothballedShip(FleetMemberType.SHIP, "brdy_cetonia_Hull", null);
                cargoBR.addMothballedShip(FleetMemberType.SHIP, "brdy_typheus_Hull", null);	
                cargoBR.addMothballedShip(FleetMemberType.SHIP, "brdy_gonodactylus_Hull", null);
                cargoBR.addMothballedShip(FleetMemberType.SHIP, "brdy_stenos_Hull", null);
                cargoBR.addMothballedShip(FleetMemberType.SHIP, "brdy_robberfly_Hull", null);
                cargoBR.addMothballedShip(FleetMemberType.SHIP, "brdy_robberfly_Hull", null);		
                cargoBR.addMothballedShip(FleetMemberType.FIGHTER_WING, "brdy_krait_wing", null);
                cargoBR.addMothballedShip(FleetMemberType.FIGHTER_WING, "brdy_krait_wing", null);
                cargoBR.addMothballedShip(FleetMemberType.FIGHTER_WING, "brdy_serket_wing", null);
                cargoBR.addMothballedShip(FleetMemberType.FIGHTER_WING, "brdy_squilla_wing", null);
	}
	
}















