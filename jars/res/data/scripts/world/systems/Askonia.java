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
import data.scripts.UsSData;
import data.scripts.uss.EliteSpawnPoint;
import data.scripts.uss.FactionSpawnPoint;
import data.scripts.uss.UsSUtils;
import java.awt.Color;
import java.util.List;

public class Askonia {

	public void generate(SectorAPI sector) {
		StarSystemAPI system = sector.createStarSystem("Askonia");
		LocationAPI hyper = Global.getSector().getHyperspace();
		
		system.setBackgroundTextureFilename("graphics/backgrounds/background4.jpg");
		PlanetAPI star = system.initStar("star_red", // id in planets.json
										 1000f, 		// radius (in pixels at default zoom)
										 4500, 3000);   // location in hyperspace
		
		system.setLightColor(new Color(255, 180, 180)); // light color in entire system, affects all entities
		

		/*
		 * addPlanet() parameters:
		 * 1. What the planet orbits (orbit is always circular)
		 * 2. Name
		 * 3. Planet type id in planets.json
		 * 4. Starting angle in orbit, i.e. 0 = to the right of the star
		 * 5. Planet radius, pixels at default zoom
		 * 6. Orbit radius, pixels at default zoom
		 * 7. Days it takes to complete an orbit. 1 day = 10 seconds.
		 */
		PlanetAPI a1 = system.addPlanet(star, "Sindria", "rocky_metallic", 0, 150, 2500, 100);
		PlanetAPI a2 = system.addPlanet(star, "Salus", "gas_giant", 230, 350, 7000, 250);
		
		PlanetAPI a21 = system.addPlanet(a2, "Cruor", "rocky_unstable", 45, 80, 800, 25);
		PlanetAPI a22 = system.addPlanet(a2, "Volturn", "water", 110, 120, 1400, 45);
		
		PlanetAPI a3 = system.addPlanet(star, "Umbra", "rocky_ice", 280, 150, 12000, 650);
		
		a1.setCustomDescriptionId("planet_sindria");
		a2.setCustomDescriptionId("planet_salus");
		a21.setCustomDescriptionId("planet_cruor");
		a22.setCustomDescriptionId("planet_volturn");
		a3.setCustomDescriptionId("planet_umbra");
		
		a2.getSpec().setPlanetColor(new Color(255,215,190,255));
		a2.getSpec().setAtmosphereColor(new Color(160,110,45,140));
		a2.getSpec().setCloudColor(new Color(255,164,96,200));
		a2.getSpec().setTilt(15);
		a2.applySpecChanges();
		
		/*
		 * addAsteroidBelt() parameters:
		 * 1. What the belt orbits
		 * 2. Number of asteroids
		 * 3. Orbit radius
		 * 4. Belt width
		 * 6/7. Range of days to complete one orbit. Value picked randomly for each asteroid. 
		 */
		system.addAsteroidBelt(a2, 50, 1100, 128, 40, 80);
		
		
		/*
		 * addRingBand() parameters:
		 * 1. What it orbits
		 * 2. Category under "graphics" in settings.json
		 * 3. Key in category
		 * 4. Width of band within the texture
		 * 5. Index of band
		 * 6. Color to apply to band
		 * 7. Width of band (in the game)
		 * 8. Orbit radius (of the middle of the band)
		 * 9. Orbital period, in days
		 */
		system.addRingBand(a2, "misc", "rings1", 256f, 2, Color.white, 256f, 1100, 40f);
		system.addRingBand(a2, "misc", "rings1", 256f, 2, Color.white, 256f, 1100, 60f);
		system.addRingBand(a2, "misc", "rings1", 256f, 2, Color.white, 256f, 1100, 80f);
		
		system.addRingBand(a2, "misc", "rings1", 256f, 3, Color.white, 256f, 1800, 70f);
		system.addRingBand(a2, "misc", "rings1", 256f, 3, Color.white, 256f, 1800, 90f);
		system.addRingBand(a2, "misc", "rings1", 256f, 3, Color.white, 256f, 1800, 110f);
		
		system.addRingBand(a2, "misc", "rings1", 256f, 0, Color.white, 256f, 2150, 50f);
		system.addRingBand(a2, "misc", "rings1", 256f, 0, Color.white, 256f, 2150, 70f);
		system.addRingBand(a2, "misc", "rings1", 256f, 0, Color.white, 256f, 2150, 80f);
		system.addRingBand(a2, "misc", "rings1", 256f, 1, Color.white, 256f, 2100, 90f);
		
		
		
		JumpPointAPI jumpPoint = Global.getFactory().createJumpPoint("Jump Point Alpha");
		OrbitAPI orbit = Global.getFactory().createCircularOrbit(a1, 0, 500, 30);
		jumpPoint.setOrbit(orbit);
		jumpPoint.setRelatedPlanet(a1);
		jumpPoint.setStandardWormholeToHyperspaceVisual();
		system.addEntity(jumpPoint);
		
		SectorEntityToken station = system.addOrbitalStation(a1, 45, 300, 50, "Command & Control", "sindrian_diktat");
                
		initStationCargo(station);
		
		// example of using custom visuals below
//		a1.setCustomInteractionDialogImageVisual(new InteractionDialogImageVisual("illustrations", "hull_breach", 800, 800));
//		jumpPoint.setCustomInteractionDialogImageVisual(new InteractionDialogImageVisual("illustrations", "space_wreckage", 1200, 1200));
//		station.setCustomInteractionDialogImageVisual(new InteractionDialogImageVisual("illustrations", "cargo_loading", 1200, 1200));
		
		// generates hyperspace destinations for in-system jump points
		system.autogenerateHyperspaceJumpPoints(true, true);
                
                
                SectorEntityToken indStation = system.addOrbitalStation(a22, 45, 220, 50, "Askonia Trade Hub", "independent");
                UsSUtils.addRandomStuffToStation(sector, indStation.getCargo());
		
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
                
                //---------------------------------------------------
                
                FactionSpawnPoint sinpat = new FactionSpawnPoint(sector, system, 1, 3, station, "sindrian_diktat", "patrol", "Patrol", "patrol", 40, 75, UsSData.Variants_IND,
                        UsSData.SIN_CS, UsSData.SIN_C, UsSData.SIN_D, UsSData.SIN_F, UsSData.SIN_W, UsSData.CIV_LG, "",
                        0f,50f,75f,0f,0f,
                        5, 15, "combat", "technology", "leadership");
		system.addScript(sinpat);
                
                FactionSpawnPoint singuard = new FactionSpawnPoint(sector, system, 1, 4, station, "sindrian_diktat", "patrol", "Lion's Guard", "secpatrol", 20, 50, UsSData.Variants_IND,
                        UsSData.SIN_CS, UsSData.SIN_C, UsSData.SIN_D, UsSData.SIN_F, UsSData.SIN_W, UsSData.CIV_LG, "",
                        0f,25f,75f,25f,0f,
                        5, 15, "combat", "technology", "leadership");
		system.addScript(singuard);
                
                FactionSpawnPoint singarrison = new FactionSpawnPoint(sector, system, 1, 2, station, "sindrian_diktat", "patrol", "Garrison Fleet", "defend", 80, 120, UsSData.Variants_IND,
                        UsSData.SIN_CS, UsSData.SIN_C, UsSData.SIN_D, UsSData.SIN_F, UsSData.SIN_W, UsSData.CIV_LG, "conquest_Hull",
                        0f,75f,75f,33f,5f,
                        15, 25, "combat", "technology", "leadership");
		system.addScript(singarrison);
                
                //---------------------------------------------------
                
                EliteSpawnPoint sinboss = new EliteSpawnPoint(sector, system, 1, 1, station, "sindrian_diktat", "patrol", "merc", "hunter", "friendly", 150, UsSData.All_Variants,
                        UsSData.ALL_CS_FINAL, UsSData.ALL_C_FINAL, UsSData.ALL_D_FINAL, UsSData.ALL_F_FINAL, UsSData.ALL_W_FINAL, UsSData.CIV_LG, "",
                        25f,50f,75f,33f,0f,
                        "combat", "technology", "leadership");
		system.addScript(sinboss);
                
	}
	
	
	private void initStationCargo(SectorEntityToken station) {
		CargoAPI cargo = station.getCargo();
                
                UsSUtils.addFactionWeapon(cargo, UsSData.MT_SW_FINAL, UsSData.MT_MW_FINAL, UsSData.MT_LW_FINAL);
		
		cargo.addCrew(CrewXPLevel.VETERAN, 20);
		cargo.addCrew(CrewXPLevel.REGULAR, 500);
		cargo.addMarines(200);
		cargo.addSupplies(1000);
		cargo.addFuel(500);
		
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "conquest_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "crig_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "crig_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "crig_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "ox_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "ox_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "ox_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "ox_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, "ox_Hull"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, "gladius_wing"));
		cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, "gladius_wing"));
	}	
}
