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
import data.scripts.UsSData;
import data.scripts.uss.EliteSpawnPoint;
import data.scripts.uss.FactionSpawnPoint;
import data.scripts.uss.UsSUtils;
import java.awt.Color;

public class Breh_Inni {

	public void generate(SectorAPI sector) {
		
		StarSystemAPI system = sector.createStarSystem("Breh'Inni");
        
        system.setBackgroundTextureFilename("graphics/backgrounds/background4.jpg");
        
		// create the star and generate the hyperspace anchor for this system
		PlanetAPI star = system.initStar("star_red", // id in planets.json
										 800f, 		// radius (in pixels at default zoom)
										 1000, 7500);   // location in hyperspace
		
		system.setLightColor(new Color(255, 220, 180)); // light color in entire system, affects all entities
		
        
		PlanetAPI bh1 = system.addPlanet(star, "Breh'Inni I", "rocky_metallic", 90, 120, 1500, 60);
		PlanetAPI bh2 = system.addPlanet(star, "Breh'Inni II", "gas_giant", 120, 240, 3000, 110);
		PlanetAPI bh3 = system.addPlanet(star, "Breh'Inni III", "rocky_ice", 30, 150, 4000, 130);
		PlanetAPI bh4 = system.addPlanet(star, "Breh'Inni IV", "gas_giant", 270, 260, 7000, 220);
		PlanetAPI bh5 = system.addPlanet(star, "Breh'Inni V", "rocky_ice", 290, 120, 8500, 340);
		PlanetAPI bh6 = system.addPlanet(star, "Breh'Inni VI", "gas_giant", 280, 400, 11000, 550);
		
		bh1.setCustomDescriptionId("planet_brehinniI");
		bh2.setCustomDescriptionId("planet_brehinniII");
		bh3.setCustomDescriptionId("planet_brehinniIII");
		bh4.setCustomDescriptionId("planet_brehinniIV");
		bh5.setCustomDescriptionId("planet_brehinniV");
		bh6.setCustomDescriptionId("planet_brehinniVI");
		
		bh2.getSpec().setPlanetColor(new Color(195,195,215,255));
		bh2.getSpec().setAtmosphereColor(new Color(160,110,45,140));
		bh2.getSpec().setCloudColor(new Color(255,164,96,200));
		bh2.getSpec().setTilt(15);
		bh2.applySpecChanges();
        
		bh4.getSpec().setPlanetColor(new Color(175,135,245,255));
		bh4.getSpec().setAtmosphereColor(new Color(160,110,45,140));
		bh4.getSpec().setCloudColor(new Color(195,195,215,200));
		bh4.getSpec().setTilt(20);
		bh4.applySpecChanges();
        
                bh6.getSpec().setPlanetColor(new Color(215,255,190,255));
		bh6.getSpec().setAtmosphereColor(new Color(160,110,45,140));
		bh6.getSpec().setCloudColor(new Color(175,135,245,200));
		bh6.getSpec().setTilt(10);
		bh6.applySpecChanges();
        
                system.addRingBand(bh2, "misc", "rings1", 256f, 2, Color.white, 256f, 260, 40f);
		system.addRingBand(bh2, "misc", "rings1", 256f, 2, Color.white, 256f, 270, 60f);
        
        
        
		JumpPointAPI jumpPoint = Global.getFactory().createJumpPoint("Jump Point Alpha");
		OrbitAPI orbit = Global.getFactory().createCircularOrbit(bh3, 0, 500, 30);
		jumpPoint.setOrbit(orbit);
		jumpPoint.setRelatedPlanet(bh3);
		jumpPoint.setStandardWormholeToHyperspaceVisual();
		system.addEntity(jumpPoint);        

                system.autogenerateHyperspaceJumpPoints(true, true);

		// SectorEntityToken centre = system.getEntityByName("star_yellow");

		system.addAsteroidBelt(bh4, 25, 600, 128, 40, 80);

                SectorEntityToken station = system.addOrbitalStation(bh4, 45, 300, 50, "Listening Post", "junk_pirates");
		initStationCargo(station);
                
                SectorEntityToken indStation = system.addOrbitalStation(bh2, 45, 340, 50, "Breh'Inni Trade Hub", "independent");
                UsSUtils.addRandomStuffToStation(sector, indStation.getCargo());
                
                FactionSpawnPoint JPsmallRaiders = new FactionSpawnPoint(sector, system, 1, 5, station, "junk_pirates", "scout", "Salvage Team", "raid", 0, 25, UsSData.Variants_JP,
                        UsSData.JP_CS, UsSData.JP_C, UsSData.JP_D, UsSData.JP_F, UsSData.JP_W, UsSData.JP_TR, "",
                        0f,0f,40f,25f,0f,
                        3, 8, "combat", "leadership", "technology");
		system.addScript(JPsmallRaiders);
                
                FactionSpawnPoint JPdefense = new FactionSpawnPoint(sector, system, 1, 3, station, "junk_pirates", "scout", "Retained Fleet", "defend", 80, 120, UsSData.Variants_JP,
                        UsSData.JP_CS, UsSData.JP_C, UsSData.JP_D, UsSData.JP_F, UsSData.JP_W, UsSData.JP_TR, "",
                        25f,50f,66f,33f,0f,
                        13, 18, "combat", "leadership", "technology");
		system.addScript(JPdefense);
                
                FactionSpawnPoint JPmediumRaiders = new FactionSpawnPoint(sector, system, 1, 5, station, "junk_pirates", "scout", "Salvage Expedition", "raid", 35, 60, UsSData.Variants_JP,
                        UsSData.JP_CS, UsSData.JP_C, UsSData.JP_D, UsSData.JP_F, UsSData.JP_W, UsSData.JP_TR, "",
                        10f,40f,66f,25f,0f,
                        8, 13, "combat", "leadership", "technology");
		system.addScript(JPmediumRaiders);
                
                FactionSpawnPoint JPminer = new FactionSpawnPoint(sector, system, 1, 2, station, "junk_pirates", "scout", "Miner", "mine", 40, 70, UsSData.Variants_JP,
                        UsSData.JP_CS, UsSData.JP_C, UsSData.JP_D, UsSData.JP_F, UsSData.JP_W, UsSData.JP_TR, "junk_pirates_the_reaper_Hull",
                        0f,0f,50f,0f,25f,
                        3, 5, "leadership", "combat", "combat");
		system.addScript(JPminer);
                
                //---------------------------------------------------
                
                EliteSpawnPoint jpboss = new EliteSpawnPoint(sector, system, 1, 1, station, "junk_pirates", "scout", "Mendonca", "hunter", "station", 150, UsSData.All_Variants,
                        UsSData.ALL_CS_FINAL, UsSData.ALL_C_FINAL, UsSData.ALL_D_FINAL, UsSData.ALL_F_FINAL, UsSData.ALL_W_FINAL, UsSData.CIV_LG, "",
                        25f,50f,75f,33f,0f,
                        "combat", "technology", "leadership");
		system.addScript(jpboss);
                
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
                        25f,50f,75f,33f,0f,
                        "combat", "technology", "leadership");
		system.addScript(indeMerc);
                
    }
    
    private void initStationCargo(SectorEntityToken station) {
        
		CargoAPI cargo = station.getCargo();
                
                for (int i = 0; i < 5; i++ ) {
                    UsSUtils.addFactionWeapon(cargo, UsSData.MT_SW_FINAL, UsSData.MT_MW_FINAL, UsSData.MT_LW_FINAL);
                }  
        
		cargo.addWeapons("pdburst", 1);
		cargo.addWeapons("junk_pirates_cutlass", 3);
		cargo.addWeapons("sabot", 5);
                cargo.addWeapons("harpoonpod", 2);
		cargo.addWeapons("swarmer", 5);
		cargo.addWeapons("junk_pirates_scatterpd", 3);
		cargo.addWeapons("junk_pirates_lexcimer", 2);
		cargo.addWeapons("pdlaser", 4);
		cargo.addWeapons("taclaser", 5);
		cargo.addWeapons("reaper", 5);
                cargo.addWeapons("lightag", 3);
                cargo.addWeapons("heavyblaster", 1);
		cargo.addWeapons("junk_pirates_grapeshot", 1);
		cargo.addWeapons("junk_pirates_grapeshot_m", 2);
		cargo.addWeapons("junk_pirates_grapeshot_s", 10);
        
		cargo.addCrew(CrewXPLevel.VETERAN, 20);
		cargo.addCrew(CrewXPLevel.REGULAR, 120);
		cargo.addMarines(100);
		cargo.addSupplies(1000);
		cargo.addFuel(500);

		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_clam_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_langoustine_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_boxer_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_octopus_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_sickle_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_sickle_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_scythe_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_goat_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_hammer_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_hammer_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_stoatA_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_stoatA_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_stoatB_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_stoatB_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_boxenstein_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, "junk_pirates_spike_wing"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, "junk_pirates_spike_wing"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, "junk_pirates_splinter_wing"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, "junk_pirates_shard_wing"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, "junk_pirates_shard_wing"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, "junk_pirates_spike_wing"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, "junk_pirates_cleat_wing"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "junk_pirates_dugong_Hull"));
	}
}















