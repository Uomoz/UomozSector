package data.scripts.uss;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.Script;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.FleetAssignment;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class FactionSpawnPoint extends GeneralFactionSpawnPoint {
    
        int FP, AP;
        CampaignFleetAPI fleet;

	public FactionSpawnPoint(SectorAPI sector, LocationAPI location, int daysInterval, int maxFleets, SectorEntityToken anchor, String faction, String rnd_faction_fleet, String fleet_name, String fleetType, int minFP, int maxFP, Map variants,
                        Map capitals, Map cruisers, Map destroyers, Map frigates, Map wings, Map specials, String baseship, List SW, List MW, List LW,
                        float CS_chance, float C_chance, float D_chance, float W_chance, float S_chance,
                        int minAP, int maxAP, String A_focus1, String A_focus2, String A_focus3) {
		super(sector, location, daysInterval, maxFleets, anchor, faction, rnd_faction_fleet, fleet_name, fleetType, minFP, maxFP, variants,
                        capitals, cruisers, destroyers, frigates, wings, specials, baseship, SW, MW, LW,
                        CS_chance, C_chance, D_chance, W_chance, S_chance,
                        minAP, maxAP, A_focus1, A_focus2, A_focus3);
	}

	@Override
	public CampaignFleetAPI spawnFleet() {
                
                fleet = getSector().createFleet(getFaction(), getRndFactionFleet());
		fleet.setName(getFleetName());
                
                UsSUtils.RemoveFleetMembers(fleet);
                
                FP = (getMinFP() + (int) (Math.random() * (getMaxFP() - getMinFP())));
                AP = (getMinAP() + (int) (Math.random() * (getMaxAP() - getMinAP())));
                
                UsSUtils.SetLevel(fleet, AP, getAptitudeFocus_1(), getAptitudeFocus_2(), getAptitudeFocus_3());
                
                if (getFlagship().endsWith("_Hull")) {
                    AddShip(getFlagship());
                }
                
                CreateGenericFleet();
                getLocation().spawnFleet(getAnchor(), 0, 0, fleet);
                UsSUtils.RandomizeAndSortAndFill(fleet, getVariants(), AP);
                
                initAI();
                
                return fleet;
	}
        
        public void initAI() {
                Script script = AI_Trade(fleet, getAnchor());
                Script script2 = AI_Mine(fleet);
                Script script3 = AI_Roam(fleet);
                
                fleet.setPreferredResupplyLocation(getAnchor());
                fleet.addAssignment(FleetAssignment.RESUPPLY, getAnchor(), 1000);
                
                if (getFleetType().equals("patrol")) {
                    if ((float) Math.random() > 0.66f) {
			fleet.addAssignment(FleetAssignment.PATROL_SYSTEM, ((StarSystemAPI)fleet.getContainingLocation()).getHyperspaceAnchor(), 1000);
                    } else {
			fleet.addAssignment(FleetAssignment.PATROL_SYSTEM, null, 1000);
                    }    
                }
                if (getFleetType().startsWith("jihad_")) {
                    fleet.addAssignment(FleetAssignment.ATTACK_LOCATION, UsSUtils.getRandomStationByParam(fleet, getFleetType().substring(6)), 1000);
                       
                }
                if (getFleetType().equals("raid")) {
                    if ((float) Math.random() > 0.3f) {
			fleet.addAssignment(FleetAssignment.RAID_SYSTEM, UsSUtils.getRandomSystem(Global.getSector()).getStar(), 1000);
                    } else {
			fleet.addAssignment(FleetAssignment.RAID_SYSTEM, null, 1000);
                    } 
                }
                if (getFleetType().equals("secpatrol")) {
                    if ((float) Math.random() > 0.33f) {
			fleet.addAssignment(FleetAssignment.PATROL_SYSTEM, UsSUtils.getRandomSystem(Global.getSector()).getStar(), 1000);
                    } else {
			fleet.addAssignment(FleetAssignment.PATROL_SYSTEM, UsSUtils.getRandomSystem(Global.getSector()).getHyperspaceAnchor(), 1000);
                    } 
                }
                if (getFleetType().equals("defend")) {
                    fleet.addAssignment(FleetAssignment.DEFEND_LOCATION, getAnchor(), 1000);
                }
                if (getFleetType().equals("trade")) {
                    fleet.addAssignment(FleetAssignment.GO_TO_LOCATION, getAnchor(), 1000, script);
                }
                if (getFleetType().equals("mine")) {
                    fleet.addAssignment(FleetAssignment.GO_TO_LOCATION, getAnchor(), 1000, script2);
                }
                if (getFleetType().equals("roam")) {
                    fleet.addAssignment(FleetAssignment.PATROL_SYSTEM, getAnchor(), (int) (20f * Math.random()), script3);
                }
        }
        
        public Script AI_Mine(final CampaignFleetAPI fleet) {
		return new Script() {
			public void run() {
                                Script script = AI_Mine(fleet);
                                int holdTime = (int) (6f * Math.random());
                                Script script2 = AI_Hold(fleet, holdTime);
                                SectorEntityToken asteroid = UsSUtils.getRandomAsteroid(getLocation());
                                if (asteroid != null) {
                                    fleet.addAssignment(FleetAssignment.GO_TO_LOCATION, asteroid, 1000, script2);
                                    fleet.addAssignment(FleetAssignment.DEFEND_LOCATION, asteroid, holdTime, script); 
                                } else {
                                    fleet.addAssignment(FleetAssignment.PATROL_SYSTEM, getAnchor(), holdTime);
                                }                                                                       
                        }
		};
	}
        
        public Script AI_Trade(final CampaignFleetAPI fleet, final SectorEntityToken depart_station) {
		return new Script() {
			public void run() {
                                SectorEntityToken target_station = UsSUtils.getRandomStationByParam(fleet, "friendly");
                                Script script = AI_Trade(fleet, target_station);
                                UsSUtils.addCargoToTraders(Global.getSector(), fleet, getWings(), getFrigates(), getDestroyers(), getCruisers(), getCapitals(), getSW(), getMW(), getLW());
                                fleet.addAssignment(FleetAssignment.DELIVER_RESOURCES, target_station, 1000);
                                fleet.addAssignment(FleetAssignment.RESUPPLY, target_station, 1000, script);
                        }
		};
	}
        
        public Script AI_Roam(final CampaignFleetAPI fleet) {
		return new Script() {
			public void run() {
                                Script script = AI_Roam(fleet);
                                SectorEntityToken station = UsSUtils.getRandomStationByParam(fleet, "friendly");
                                fleet.setPreferredResupplyLocation(station);
                                fleet.addAssignment(FleetAssignment.PATROL_SYSTEM, station, (int) (20f * Math.random()), script);
                                                                        
                        }
		};
	}
        
        public Script AI_Hold(final CampaignFleetAPI fleet, final int holdTime) {
		return new Script() {
			public void run() {
                                fleet.setNoEngaging(holdTime * 10);                                      
                        }
		};
	}
                
        public void CreateGenericFleet() {
                if          ((FP > 0)                                                               & (Math.random() < (getSpecialsChance()/100))   & (getSpecials().size() > 0))   
                    {   AddShip(UsSUtils.getRandomMapMember(getSpecials()));
                } else if   ((FP > (90 + (15 * Math.random()) - (getCapitalsChance() * 0.30f)))     & (Math.random() < (getCapitalsChance()/100))   & (getCapitals().size() > 0))   
                    {   AddShip(UsSUtils.getRandomMapMember(getCapitals()));
                } else if   ((FP > (60 + (10 * Math.random()) - (getCruisersChance() * 0.20f)))     & (Math.random() < (getCruisersChance()/100))   & (getCruisers().size() > 0))   
                    {   AddShip(UsSUtils.getRandomMapMember(getCruisers()));
                } else if   ((FP > (25 + (5 * Math.random()) -  (getDestroyersChance() * 0.10f)))   & (Math.random() < (getDestroyersChance()/100)) & (getDestroyers().size() > 0)) 
                    {   AddShip(UsSUtils.getRandomMapMember(getDestroyers()));
                } else if   ((FP > 0)                                                               & (Math.random() < (getWingsChance()/100))      & (getWings().size() > 0))      
                    {   AddShip(UsSUtils.getRandomMapMember(getWings()));
                } else if   ((FP > 0)                                                                                                               & (getFrigates().size() > 0))   
                    {   AddShip(UsSUtils.getRandomMapMember(getFrigates()));
                } else  {   return;}
                CreateGenericFleet();
        }
        
        private void AddShip(String ship) {
                FleetMemberAPI a = null;
                if (ship.endsWith("_wing")) {
                    a = Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, ship);
                } else {
                    a = Global.getFactory().createFleetMember(FleetMemberType.SHIP, ship);
                }
                fleet.getFleetData().addFleetMember(a);
                if (a.getNumFlightDecks() > 0) { AddShip(UsSUtils.getRandomMapMember(getWings())); AddShip(UsSUtils.getRandomMapMember(getWings()));}
                if (a.getNumFlightDecks() > 1) { AddShip(UsSUtils.getRandomMapMember(getWings()));}
                if (a.getNumFlightDecks() > 2) { AddShip(UsSUtils.getRandomMapMember(getWings())); AddShip(UsSUtils.getRandomMapMember(getWings()));}
                
                FP -= a.getFleetPointCost();
        }
        

}






