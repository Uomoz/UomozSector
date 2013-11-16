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

public class Canis {

	public void generate(SectorAPI sector) {
		
		StarSystemAPI system = sector.createStarSystem("Canis");
        
                system.setBackgroundTextureFilename("graphics/backgrounds/background4.jpg");
        
		// create the star and generate the hyperspace anchor for this system
		PlanetAPI star = system.initStar("star_red", // id in planets.json
										 600f, 		// radius (in pixels at default zoom)
										 -12500, -2500);   // location in hyperspace
		
		system.setLightColor(new Color(255, 220, 180)); // light color in entire system, affects all entities
		
        
		PlanetAPI canis1 = system.addPlanet(star, "Canis I", "rocky_metallic", 10, 120, 1500, 60);
		PlanetAPI canis2 = system.addPlanet(star, "Canis II", "rocky_metallic", 25, 140, 3000, 110);
		PlanetAPI canis3 = system.addPlanet(star, "Canis III", "gas_giant", 270, 250, 4000, 130);
		PlanetAPI canis4 = system.addPlanet(star, "Canis IV", "cryovolcanic", 240, 120, 9000, 260);
		
		canis1.setCustomDescriptionId("planet_canisI");
		canis2.setCustomDescriptionId("planet_canisII");
		canis3.setCustomDescriptionId("planet_canisIII");
		canis4.setCustomDescriptionId("planet_canisIV");
		
		canis3.getSpec().setPlanetColor(new Color(140,195,140,255));
		canis3.getSpec().setAtmosphereColor(new Color(120,225,45,140));
		canis3.getSpec().setCloudColor(new Color(160,255,96,200));
		canis3.getSpec().setTilt(10);
		canis3.applySpecChanges();
        
        
		JumpPointAPI jumpPoint = Global.getFactory().createJumpPoint("Jump Point Alpha");
        
		OrbitAPI orbit = Global.getFactory().createCircularOrbit(canis4, 0, 500, 30);
		jumpPoint.setOrbit(orbit);
		jumpPoint.setRelatedPlanet(canis4);
		jumpPoint.setStandardWormholeToHyperspaceVisual();
		system.addEntity(jumpPoint);        

		system.addAsteroidBelt(canis4, 25, 600, 128, 40, 80);

                SectorEntityToken station = system.addOrbitalStation(canis3, 45, 300, 50, "Kollective", "pack");
		initStationCargo(station);
        
		system.autogenerateHyperspaceJumpPoints(true, true);
                
                SectorEntityToken indStation = system.addOrbitalStation(canis2, 45, 240, 50, "Canis Trade Hub", "independent");
                UsSUtils.addRandomStuffToStation(sector, indStation.getCargo());

		FactionSpawnPoint trader = new FactionSpawnPoint(sector, system, 1, 2, indStation, "independent", "miner", "Trader", "trade", 0, 10, UsSData.Variants_IND,
                        UsSData.IND_CS, UsSData.IND_C, UsSData.IND_D, UsSData.IND_F, UsSData.CIV_W, UsSData.IND_TR, "", UsSData.ALL_SW_FINAL, UsSData.ALL_MW_FINAL, UsSData.ALL_LW_FINAL,
                        0f,0f,0f,0f,100f,
                        1, 5, "leadership", "technology", "combat");
		system.addScript(trader);
                
                FactionSpawnPoint trader2 = new FactionSpawnPoint(sector, system, 1, 1, indStation, "independent", "miner", "Trader", "trade", 15, 35, UsSData.Variants_IND,
                        UsSData.IND_CS, UsSData.IND_C, UsSData.IND_D, UsSData.IND_F, UsSData.IND_W, UsSData.IND_TR, "", UsSData.ALL_SW_FINAL, UsSData.ALL_MW_FINAL, UsSData.ALL_LW_FINAL,
                        0f,0f,50f,25f,50f,
                        3, 8, "technology", "leadership", "combat");
		system.addScript(trader2);
                
                FactionSpawnPoint trader3 = new FactionSpawnPoint(sector, system, 1, 1, indStation, "independent", "miner", "Trader", "trade", 50, 80, UsSData.Variants_IND,
                        UsSData.IND_CS, UsSData.IND_C, UsSData.IND_D, UsSData.IND_F, UsSData.IND_W, UsSData.IND_TR, "", UsSData.ALL_SW_FINAL, UsSData.ALL_MW_FINAL, UsSData.ALL_LW_FINAL,
                        0f,25f,50f,25f,50f,
                        5, 10, "technology", "leadership", "combat");
		system.addScript(trader3);
                
                FactionSpawnPoint miner = new FactionSpawnPoint(sector, system, 1, 1, indStation, "independent", "miner", "Miner", "mine", 20, 35, UsSData.Variants_IND,
                        UsSData.IND_CS, UsSData.IND_C, UsSData.IND_D, UsSData.IND_F, UsSData.CIV_W, UsSData.IND_TR, "", UsSData.ALL_SW_FINAL, UsSData.ALL_MW_FINAL, UsSData.ALL_LW_FINAL,
                        0f,0f,0f,50f,50f,
                        3, 5, "technology", "leadership", "combat");
		system.addScript(miner);
		
                //---------------------------------------------------

//                EliteSpawnPoint indeMerc = new EliteSpawnPoint(sector, system, 1, 1, indStation, "independent", "miner", "merc", "defender", "neutral", 150, UsSData.All_Variants,
//                        UsSData.ALL_CS_FINAL, UsSData.ALL_C_FINAL, UsSData.ALL_D_FINAL, UsSData.ALL_F_FINAL, UsSData.ALL_W_FINAL, UsSData.CIV_LG, "",
//                        25f,50f,50f,33f,0f,
//                        "combat", "technology", "leadership");
//		system.addScript(indeMerc);
                
                //---------------------------------------------------
                
                FactionSpawnPoint packFlank = new FactionSpawnPoint(sector, system, 1, 4, station, "pack", "miners", "Flank", "raid", 15, 35, UsSData.Variants_PCK,
                        UsSData.PCK_CS, UsSData.PCK_C, UsSData.PCK_D, UsSData.PCK_F, UsSData.PCK_W, UsSData.PCK_TR, "", UsSData.PCK_SW_FINAL, UsSData.PCK_MW_FINAL, UsSData.PCK_LW_FINAL,
                        0f,0f,50f,0f,0f,
                        3, 8, "combat", "technology", "leadership");
		system.addScript(packFlank);
                
                FactionSpawnPoint packArmada = new FactionSpawnPoint(sector, system, 1, 3, station, "pack", "miners", "Kollective Mass", "patrol", 50, 90, UsSData.Variants_PCK,
                        UsSData.PCK_CS, UsSData.PCK_C, UsSData.PCK_D, UsSData.PCK_F, UsSData.PCK_W, UsSData.PCK_TR, "", UsSData.PCK_SW_FINAL, UsSData.PCK_MW_FINAL, UsSData.PCK_LW_FINAL,
                        0f,0f,66f,0f,0f,
                        15, 20, "combat", "technology", "leadership");
		system.addScript(packArmada);
                
                FactionSpawnPoint packMiner = new FactionSpawnPoint(sector, system, 1, 2, station, "pack", "miners", "Miner", "mine", 30, 50, UsSData.Variants_PCK,
                        UsSData.PCK_CS, UsSData.PCK_C, UsSData.PCK_D, UsSData.PCK_F, UsSData.CIV_W, UsSData.PCK_TR, "", UsSData.PCK_SW_FINAL, UsSData.PCK_MW_FINAL, UsSData.PCK_LW_FINAL,
                        0f,0f,66f,25f,50f,
                        5, 15, "technology", "leadership", "combat");
		system.addScript(packMiner);
                
                //---------------------------------------------------
                
//                EliteSpawnPoint packboss = new EliteSpawnPoint(sector, system, 1, 2, station, "pack", "miners", "Wolf", "defender", "station", 150, UsSData.All_Variants,
//                        UsSData.ALL_CS_FINAL, UsSData.ALL_C_FINAL, UsSData.PCK_D, UsSData.ALL_F_FINAL, UsSData.ALL_W_FINAL, UsSData.CIV_LG, "",
//                        0f,0f,50f,33f,0f,
//                        "combat", "technology", "leadership");
//		system.addScript(packboss);
                
		
    }
    
    private void initStationCargo(SectorEntityToken station2) {
        
		CargoAPI cargo = station2.getCargo();
                
                for (int i = 0; i < 20; i++ ) {
                    UsSUtils.addFactionWeapon(cargo, UsSData.PCK_SW_FINAL, UsSData.PCK_MW_FINAL, UsSData.PCK_LW_FINAL);
                }        
        
		cargo.addCrew(CrewXPLevel.VETERAN, 5);
		cargo.addCrew(CrewXPLevel.REGULAR, 50);
		cargo.addCrew(CrewXPLevel.GREEN, 350);
		cargo.addMarines(25);
		cargo.addSupplies(1250);
		cargo.addFuel(250);
        
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "pack_bedlington_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "pack_bedlington_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "pack_bedlington_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "pack_BRT_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "pack_BRT_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "pack_pitbull_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "pack_pitbull_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "pack_ridgeback_x_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "pack_ridgeback_x_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "pack_wirefox_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "pack_wirefox_Hull"));   
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "pack_samoyed_Hull"));        
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "pack_samoyed_Hull"));        
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "pack_samoyed_Hull"));        
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "pack_samoyed_decoupled_Hull"));        
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "pack_komondor_Hull"));        
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "pack_ridgeback_Hull"));        
	}
}















