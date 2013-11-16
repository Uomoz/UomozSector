package data.scripts.uss;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.FleetDataAPI;
import com.fs.starfarer.api.campaign.FleetEncounterContextPlugin.DataForEncounterSide;
import com.fs.starfarer.api.campaign.FleetEncounterContextPlugin.FleetMemberData;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import data.scripts.uss.UsSData.BpData;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.lazywizard.lazylib.CollectionUtils;
import org.lwjgl.util.vector.Vector2f;

public class UsSUtils
{
    private static final Random rng = new Random();
    
        private UsSUtils(){
                
        }
        
        public static void RemoveFleetMembers(CampaignFleetAPI fleet)    {
        
            List<FleetMemberAPI> initialFleetMembers = fleet.getFleetData().getMembersListCopy();
            for(int i = 0; i < initialFleetMembers.size(); i++) {
                fleet.getFleetData().removeFleetMember(initialFleetMembers.get(i));
            }        
        }
        
        public static CampaignFleetAPI getHostileFleet (CampaignFleetAPI fleet)    {
            List fleetsList = fleet.getContainingLocation().getFleets();
            Iterator iter = fleetsList.iterator();
            CampaignFleetAPI iterFleet;
            CampaignFleetAPI targetFleet = null;

            while (iter.hasNext())
            {
                iterFleet = (CampaignFleetAPI) iter.next();
                if ((iterFleet.getFaction().getRelationship(fleet.getFaction().getId()) < 0)
                        && ((iterFleet.getFullName().contains("[xXx]")) || (iterFleet.isPlayerFleet()))
                        && (iterFleet.getFleetPoints() <= fleet.getFleetPoints())) {
                    targetFleet = iterFleet;
                } else {
                    continue;
                }
            }
        return targetFleet;
        }
        
        public static void RandomizeAndSortAndFill(CampaignFleetAPI fleet, Map Variants, int AP)    {
        
        // A temporary list to store re-ordered members in
        List<FleetMemberAPI> tempMemberList = new ArrayList<FleetMemberAPI>();

        // local reference
        List<FleetMemberAPI> initialFleetMembers = fleet.getFleetData().getMembersListCopy();
        
        fleet.getCargo().clear();

        // Put a reference to fleet members into the temp list in the order we want them
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < initialFleetMembers.size(); j++)
            {
                if(i == 0 && initialFleetMembers.get(j).isCapital()) {
                    tempMemberList.add(initialFleetMembers.get(j));
                }

                if(i == 1 && initialFleetMembers.get(j).isCruiser()) {
                    tempMemberList.add(initialFleetMembers.get(j));
                }

                if(i == 2 && initialFleetMembers.get(j).isDestroyer()) {
                    tempMemberList.add(initialFleetMembers.get(j));
                }

                if(i == 3 && initialFleetMembers.get(j).isFrigate()) {
                    tempMemberList.add(initialFleetMembers.get(j));
                }

                if(i == 4 && initialFleetMembers.get(j).isFighterWing()) {
                    tempMemberList.add(initialFleetMembers.get(j));
                }
            }
        }

        // Remove all members from fleet
        for(int i = 0; i < initialFleetMembers.size(); i++) {
            fleet.getFleetData().removeFleetMember(initialFleetMembers.get(i));
        }

        // Re-add members to fleet from temp list
        for(int i = 0; i < tempMemberList.size(); i++)  {
            fleet.getFleetData().addFleetMember(tempMemberList.get(i));
                    tempMemberList.get(i).getStatus().repairFully();
                    float max = tempMemberList.get(i).getRepairTracker().getMaxCR();
                    float curr = tempMemberList.get(i).getRepairTracker().getBaseCR();
                    if (max > curr) {
			tempMemberList.get(i).getRepairTracker().applyCREvent(max - curr, "Repaired at station");
               	    }
            if ((!tempMemberList.get(i).isFighterWing()) && (tempMemberList.get(i).getSpecId().endsWith("_Hull"))) {
                randomizeShip(tempMemberList.get(i), fleet.getFleetData(), Variants);
            }
        }
        int addedCrew = (int) (fleet.getCargo().getMaxPersonnel() - fleet.getCargo().getTotalCrew());
        
        if (AP <= 5) {
                if (!fleet.getName().endsWith("]")) {
                    fleet.setName(fleet.getName() + " [|]");
                }
                fleet.getCargo().addCrew(CargoAPI.CrewXPLevel.GREEN, (addedCrew/5)*3);
            } else if (AP <= 10) {
                if (!fleet.getName().endsWith("]")) {
                    fleet.setName(fleet.getName() + " [||]");
                }
                fleet.getCargo().addCrew(CargoAPI.CrewXPLevel.REGULAR, (addedCrew/5)*2);
                fleet.getCargo().addCrew(CargoAPI.CrewXPLevel.GREEN, (addedCrew/5)*1);
            } else if (AP <= 15) {
                if (!fleet.getName().endsWith("]")) {
                    fleet.setName(fleet.getName() + " [|||]");
                }
                fleet.getCargo().addCrew(CargoAPI.CrewXPLevel.REGULAR, (addedCrew/5)*2);
                fleet.getCargo().addCrew(CargoAPI.CrewXPLevel.VETERAN, (addedCrew/5)*1);
            } else if (AP <= 20) {
                if (!fleet.getName().endsWith("]")) {
                    fleet.setName(fleet.getName() + " [V]");
                }
                fleet.getCargo().addCrew(CargoAPI.CrewXPLevel.VETERAN, (addedCrew/5)*3);
            } else if (AP <= 25) {
                if (!fleet.getName().endsWith("]")) {
                    fleet.setName(fleet.getName() + " [vVv]");
                }
                fleet.getCargo().addCrew(CargoAPI.CrewXPLevel.VETERAN, (addedCrew/5)*2);
                fleet.getCargo().addCrew(CargoAPI.CrewXPLevel.ELITE, (addedCrew/5)*1);
            } else if (AP <= 30) {
                if (!fleet.getName().endsWith("]")) {
                    fleet.setName(fleet.getName() + " [*V*]");
                }
                fleet.getCargo().addCrew(CargoAPI.CrewXPLevel.ELITE, (addedCrew/5)*3);
            }
            fleet.getCargo().addMarines(addedCrew/10);
        }
            
        public static void SetLevel(CampaignFleetAPI fleet, int AP, String focus1, String focus2, String focus3) {
                int Extra_SP, Half_Extra_SP;
                MutableCharacterStatsAPI Boss = fleet.getCommanderStats();

                for (int i = 0; i < AP; i++) {
                    Map aptitudes_list = new HashMap();
                    if (Boss.getAptitudeLevel(focus1) < 10) { aptitudes_list.put(focus1, 3f);}
                    if (Boss.getAptitudeLevel(focus2) < 10) { aptitudes_list.put(focus2, 2f);}
                    if (Boss.getAptitudeLevel(focus3) < 10) { aptitudes_list.put(focus3, 1f);}
                    String aptitude_pick = (String) CollectionUtils.weightedRandom(aptitudes_list);
                    Boss.increaseAptitude(aptitude_pick);
                }

                if (Boss.getAptitudeLevel("leadership") > 7) {
                    Extra_SP = (int) (((Boss.getAptitudeLevel("leadership") - 7) * 4) - 2);
                } else {Extra_SP = 0;}

                Half_Extra_SP = (int) Extra_SP/2;

                for (int i = 0; i < ((Boss.getAptitudeLevel("combat") * 4) + Half_Extra_SP); i++) {
                    Map skills_list = new HashMap();
                    if (Boss.getSkillLevel("missile_specialization") < 10) {skills_list.put("missile_specialization", 1f);}
                    if (Boss.getSkillLevel("ordnance_expert") < 10) {skills_list.put("ordnance_expert", 1f);}
                    if (Boss.getSkillLevel("damage_control") < 10) {skills_list.put("damage_control", 1f);}
                    if (Boss.getSkillLevel("target_analysis") < 10) {skills_list.put("target_analysis", 1f);}
                    if (Boss.getSkillLevel("evasive_action") < 10) {skills_list.put("evasive_action", 1f);}
                    if (Boss.getSkillLevel("helmsmanship") < 10) {skills_list.put("helmsmanship", 1f);}
                    if (Boss.getSkillLevel("flux_modulation") < 10) {skills_list.put("flux_modulation", 1f);}
                    String skill_pick = (String) CollectionUtils.weightedRandom(skills_list);
                    if (!skill_pick.isEmpty()) {
                        Boss.increaseSkill(skill_pick);
                    }
                    }
                for (int i = 0; i < ((Boss.getAptitudeLevel("leadership") * 4) - Extra_SP); i++) {
                    Map skills_list = new HashMap();
                    if (Boss.getSkillLevel("advanced_tactics") < 10) {skills_list.put("advanced_tactics", 1f);}
                    if (Boss.getSkillLevel("command_experience") < 10) {skills_list.put("command_experience", 1f);}
                    if (Boss.getSkillLevel("fleet_logistics") < 10) {skills_list.put("fleet_logistics", 1f);}
                    String skill_pick = (String) CollectionUtils.weightedRandom(skills_list);
                    if (!skill_pick.isEmpty()) {
                        Boss.increaseSkill(skill_pick);
                    }
                    }
                for (int i = 0; i < ((Boss.getAptitudeLevel("technology") * 4) + Half_Extra_SP); i++) {
                    Map skills_list = new HashMap();
                    if (Boss.getSkillLevel("gunnery_implants") < 10) {skills_list.put("gunnery_implants", 1f);}
                    if (Boss.getSkillLevel("applied_physics") < 10) {skills_list.put("applied_physics", 1f);}
                    if (Boss.getSkillLevel("flux_dynamics") < 10) {skills_list.put("flux_dynamics", 1f);}
                    if (Boss.getSkillLevel("computer_systems") < 10) {skills_list.put("computer_systems", 1f);}
                    if (Boss.getSkillLevel("construction") < 10) {skills_list.put("construction", 1f);}
                    if (Boss.getSkillLevel("mechanical_engineering") < 10) {skills_list.put("mechanical_engineering", 1f);}
                    if (Boss.getSkillLevel("field_repairs") < 10) {skills_list.put("field_repairs", 1f);}
                    if (Boss.getSkillLevel("navigation") < 10) {skills_list.put("navigation", 1f);}
                    String skill_pick = (String) CollectionUtils.weightedRandom(skills_list);
                    if (!skill_pick.isEmpty()) {
                        Boss.increaseSkill(skill_pick);
                    }
                }
        }
    
        public static void LevelUp(CampaignFleetAPI fleet, String focus1, String focus2, String focus3) {
                int Extra_SP = 0;
                boolean extra = false;
                MutableCharacterStatsAPI Boss = fleet.getCommanderStats();

                Map aptitudes_list = new HashMap();
                if (Boss.getAptitudeLevel(focus1) < 10) { aptitudes_list.put(focus1, 3f);}
                if (Boss.getAptitudeLevel(focus2) < 10) { aptitudes_list.put(focus2, 2f);}
                if (Boss.getAptitudeLevel(focus3) < 10) { aptitudes_list.put(focus3, 1f);}
                String aptitude_pick = (String) CollectionUtils.weightedRandom(aptitudes_list);
                if (!aptitudes_list.isEmpty()) Boss.increaseAptitude(aptitude_pick);

                if (Boss.getAptitudeLevel("leadership") == 8) {
                    Extra_SP = 3;
                    extra = true;
                }
                if (Boss.getAptitudeLevel("leadership") == 9) {
                    Extra_SP = 2;
                    extra = true;
                } 
                if (Boss.getAptitudeLevel("leadership") == 10) {
                    Extra_SP = 2;
                    extra = true;
                }
                else {Extra_SP = 1;}

                if ((aptitude_pick.equals("combat")) || extra) {
                    for (int i = 0; i < (4 - Extra_SP); i++) {
                        Map skills_list = new HashMap();
                        if (Boss.getSkillLevel("missile_specialization") < 10) {skills_list.put("missile_specialization", 1f);}
                        if (Boss.getSkillLevel("ordnance_expert") < 10) {skills_list.put("ordnance_expert", 1f);}
                        if (Boss.getSkillLevel("damage_control") < 10) {skills_list.put("damage_control", 1f);}
                        if (Boss.getSkillLevel("target_analysis") < 10) {skills_list.put("target_analysis", 1f);}
                        if (Boss.getSkillLevel("evasive_action") < 10) {skills_list.put("evasive_action", 1f);}
                        if (Boss.getSkillLevel("helmsmanship") < 10) {skills_list.put("helmsmanship", 1f);}
                        if (Boss.getSkillLevel("flux_modulation") < 10) {skills_list.put("flux_modulation", 1f);}
                        String skill_pick = (String) CollectionUtils.weightedRandom(skills_list);
                        if (!skills_list.isEmpty()) {
                            Boss.increaseSkill(skill_pick);
                        }
                    }
                }
                if ((aptitude_pick.equals("leadership")) && (Boss.getAptitudeLevel("leadership") < 9)){
                    for (int i = 0; i < 4 - Extra_SP + 1; i++) {
                        Map skills_list = new HashMap();
                        if (Boss.getSkillLevel("advanced_tactics") < 10) {skills_list.put("advanced_tactics", 1f);}
                        if (Boss.getSkillLevel("command_experience") < 10) {skills_list.put("command_experience", 1f);}
                        if (Boss.getSkillLevel("fleet_logistics") < 10) {skills_list.put("fleet_logistics", 1f);}
                        String skill_pick = (String) CollectionUtils.weightedRandom(skills_list);
                        if (!skills_list.isEmpty()) {
                            Boss.increaseSkill(skill_pick);
                        }
                        }
                }
                if ((aptitude_pick.equals("technology")) || extra) {
                    for (int i = 0; i < (4 - Extra_SP); i++) {
                        Map skills_list = new HashMap();
                        if (Boss.getSkillLevel("gunnery_implants") < 10) {skills_list.put("gunnery_implants", 1f);}
                        if (Boss.getSkillLevel("applied_physics") < 10) {skills_list.put("applied_physics", 1f);}
                        if (Boss.getSkillLevel("flux_dynamics") < 10) {skills_list.put("flux_dynamics", 1f);}
                        if (Boss.getSkillLevel("computer_systems") < 10) {skills_list.put("computer_systems", 1f);}
                        if (Boss.getSkillLevel("construction") < 10) {skills_list.put("construction", 1f);}
                        if (Boss.getSkillLevel("mechanical_engineering") < 10) {skills_list.put("mechanical_engineering", 1f);}
                        if (Boss.getSkillLevel("field_repairs") < 10) {skills_list.put("field_repairs", 1f);}
                        if (Boss.getSkillLevel("navigation") < 10) {skills_list.put("navigation", 1f);}
                        String skill_pick = (String) CollectionUtils.weightedRandom(skills_list);
                        if (!skills_list.isEmpty()) {
                            Boss.increaseSkill(skill_pick);
                        }
                    }
                }
        }
        
        public static void addCargoToTraders(SectorAPI sector, CampaignFleetAPI fleet, Map W, Map F, Map D, Map C, Map CS, List SW, List MW, List LW) {
                int fp = fleet.getFleetPoints();
                float rnd = (float) Math.random();
                String weaponId;
                int tradeCargo = fp/15;
                float factor;
                for (int i = 0; i < (int) (tradeCargo); i++) {
			if ((fp > 30) && (rnd > 0.85f)) {
                            weaponId = getRandomListMember(LW);
                            factor = 1f;
                        }   else if ((fp > 15) && (rnd > 0.50f)) { 
                            weaponId = getRandomListMember(MW);
                            factor = 2f;
                        }   else {
                            weaponId = getRandomListMember(SW);
                            factor = 3f;
                        }
			int quantity = (int)((Math.random() * 2f + 1f) * factor);
                        fleet.getCargo().addWeapons(weaponId, quantity);
                }
                if ((fp > 60) && (Math.random() > 0.5f)) {
                            String shipId = (String) CollectionUtils.weightedRandom(CS);
                            addMothballedShip(fleet, shipId);
                            tradeCargo -=4;
                }   if ((fp > 30) && (Math.random() > 0.5f) && (tradeCargo > 0)) { 
                            String shipId = (String) CollectionUtils.weightedRandom(C);
                            addMothballedShip(fleet, shipId);
                            tradeCargo -=2;
                }   if ((fp > 15) && (Math.random() > 0.5f) && (tradeCargo > 0)) { 
                            String shipId = (String) CollectionUtils.weightedRandom(D);
                            addMothballedShip(fleet, shipId);
                            tradeCargo -=1;
                }   if ((Math.random() > 0.33f) && (tradeCargo > 0)) {
                            String shipId = (String) CollectionUtils.weightedRandom(F);
                            addMothballedShip(fleet, shipId);
                            tradeCargo -=0.75f;
                }   if ((Math.random() > 0.33f) && (tradeCargo > 0)) {
                            String shipId = (String) CollectionUtils.weightedRandom(W);
                            addMothballedWing(fleet, shipId);
                            tradeCargo -=0.5f;
                }   if ((Math.random() > 0.33f) && (tradeCargo > 0)) {
                            String shipId = (String) CollectionUtils.weightedRandom(F);
                            addMothballedShip(fleet, shipId);
                            tradeCargo -=0.5f;
                }   if ((Math.random() > 0.33f) && (tradeCargo > 0)) {
                            String shipId = (String) CollectionUtils.weightedRandom(F);
                            addMothballedShip(fleet, shipId);
                            tradeCargo -=0.5f;
                }
	}
        
        public static void addRandomStuffToStation(SectorAPI sector, CargoAPI cargo) {
                cargo.addFuel(200);
                cargo.addSupplies(200);
                cargo.addCrew(CargoAPI.CrewXPLevel.GREEN, 200);
                cargo.addMarines(25);
                int count = (int) (Math.random() * 10f + 5f);
                int count2 = (int) (Math.random() * 10f + 5f);
		for (int i = 0; i < count; i++) {
                        float rnd = (float) Math.random();
                        String weaponId;
                        float factor;
			if (rnd > 0.5f) { 
                            weaponId = getRandomListMember(UsSData.ALL_SW_FINAL);
                            factor = 3f;
                        }   else if (rnd > 0.15f) { 
                            weaponId = getRandomListMember(UsSData.ALL_MW_FINAL);
                            factor = 2f;
                        }   else { 
                            weaponId = getRandomListMember(UsSData.ALL_LW_FINAL);
                            factor = 1f;
                        }
			int quantity = (int)((Math.random() * 2f + 3f) * factor);
                        cargo.addWeapons(weaponId, quantity);
		}
                for (int i = 0; i < count2; i++) {
                        float rnd = (float) Math.random();
                        if (rnd > 0.90f) {
                                String shipId = (String) CollectionUtils.weightedRandom(UsSData.ALL_CS_FINAL);
                                cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, shipId));
                        }   else if (rnd > 0.75f) {
                                String shipId = (String) CollectionUtils.weightedRandom(UsSData.ALL_C_FINAL);
                                cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, shipId));
                        }   else if (rnd > 0.55f) {
                                String shipId = (String) CollectionUtils.weightedRandom(UsSData.ALL_D_FINAL);
                                cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, shipId));
                        }   else if (rnd > 0.20f) {
                                String shipId = (String) CollectionUtils.weightedRandom(UsSData.ALL_F_FINAL);
                                cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, shipId));
                        }   else  {
                                String shipId = (String) CollectionUtils.weightedRandom(UsSData.ALL_W_FINAL);
                                cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, shipId));
                        }
		}
	}
        
        public static void stationDynamics() {
            for (int i = 0; i < Global.getSector().getStarSystems().size(); i++) {
            StarSystemAPI system = (StarSystemAPI) Global.getSector().getStarSystems().get(i);
            for (int g =0; g < system.getOrbitalStations().size(); g++) {
                SectorEntityToken station = (SectorEntityToken) system.getOrbitalStations().get(g);
                if (Math.random() > 0.40f) {
                    for (Iterator it = UsSData.factions.iterator(); it.hasNext();) {
                        String faction = (String) it.next();
                        if (station.getFaction().getId().matches(faction)) {
                            UsSDataGrabber.setSource(faction);
                            addFactionWeapon(station.getCargo(), UsSDataGrabber.small_w, UsSDataGrabber.medium_w, UsSDataGrabber.large_w);
                        }
                    }
                }
                if (Math.random() > 0.40f) {
                    for (Iterator it = UsSData.factions.iterator(); it.hasNext();) {
                        String faction = (String) it.next();
                        if ((station.getFaction().getId().matches(faction)) && (station.getCargo().getMothballedShips().getMembersListCopy().size() < 10)) {
                            UsSDataGrabber.setSource(faction);
                            addFactionShip(station.getCargo(), UsSDataGrabber.Twings, UsSDataGrabber.Tfrigates, UsSDataGrabber.Tdestroyers, UsSDataGrabber.Tcruisers, UsSDataGrabber.Tcapitals);
                        }
                    }
                }
                if (!station.getFaction().getId().matches("neutral")) {
                    int numweaps = weaponsStacks(station.getCargo());
                        if ((Math.random() > 0.50f) && (station.getCargo().getMothballedShips().getMembersListCopy().size() > 5)) {
                            removeRandomShip(station.getCargo());
                        }
                        if ((Math.random() > 0.50f) && (station.getCargo().getMothballedShips().getMembersListCopy().size() > 15)) {
                            removeRandomShip(station.getCargo());
                        }
                        if ((Math.random() > 0.50f) && (numweaps > 10)) {
                            removeRandomWeapon(station.getCargo());
                        }
                        if ((Math.random() > 0.50f) && (numweaps > 15)) {
                            removeRandomWeapon(station.getCargo());
                        }
                        resourceFluctuation(station.getCargo());
                    }
                }
            }            
	}
        
        public static int weaponsStacks(CargoAPI cargo) {
            List cargoList = cargo.getStacksCopy();
            int num = 0;
            for (int i = 0; i < cargoList.size(); i++ ){
                if (!((CargoStackAPI)cargoList.get(i)).isWeaponStack()) {
                continue;
                }
            }
            return num;
        }
        
        public static void resourceFluctuation(CargoAPI cargo) {
            cargo.addSupplies((int)(Math.random() * 300));
            if (cargo.getSupplies() > 1000) {
                cargo.removeSupplies((int)(Math.random() * 250));
            }
            if (cargo.getSupplies() > 3000) {
                cargo.removeSupplies((int)(Math.random() * 800));
            }

            cargo.addFuel((int)(Math.random() * 150));
            if (cargo.getFuel() > 300) {
                cargo.removeFuel((int)(Math.random() * 125));
            }
            if (cargo.getFuel() > 1000) {
                cargo.removeFuel((int)(Math.random() * 500));
            }

            cargo.addCrew(CargoAPI.CrewXPLevel.GREEN, (int)(Math.random() * 75));
            if (cargo.getCrew(CargoAPI.CrewXPLevel.GREEN) > 300) {
                cargo.removeCrew(CargoAPI.CrewXPLevel.GREEN, (int)(Math.random() * 66));
                cargo.addCrew(CargoAPI.CrewXPLevel.REGULAR, (int)(Math.random() * 25));
                cargo.addCrew(CargoAPI.CrewXPLevel.VETERAN, (int)(Math.random() * 10));
                cargo.addCrew(CargoAPI.CrewXPLevel.ELITE, (int)(Math.random() * 2));
            }
            if (cargo.getCrew(CargoAPI.CrewXPLevel.GREEN) > 500) {
                cargo.removeCrew(CargoAPI.CrewXPLevel.GREEN, (int)(Math.random() * 250));
            }
            
            for (CargoStackAPI stack : cargo.getStacksCopy()) {
                if (stack.isNull()) continue;
                if (stack.isSupplyStack() || stack.isFuelStack() || stack.isCrewStack() || stack.isMarineStack() || stack.isWeaponStack()) continue;
                if (stack.getSize() > 500) {
                    cargo.removeItems(CargoAPI.CargoItemType.RESOURCES, stack, (int)(Math.random() * 200));
                }
            }
	}
        
        public static void addRandomWeapon(CargoAPI cargo) {
		List weaponIds = Global.getSector().getAllWeaponIds();
			String weaponId = (String) weaponIds.get((int) (weaponIds.size() * Math.random()));
			int quantity = (int)(Math.random() * 2f + 3f);
			cargo.addWeapons(weaponId, quantity);		
	}
        
        public static void addFactionWeapon(CargoAPI cargo, List smallWeapons, List mediumWeapons, List largeWeapons) {
                float rnd = (float) Math.random();
                String weaponId;
                float factor;
			if (rnd > 0.5f) {
                            weaponId = getRandomListMember(smallWeapons);
                            factor = 3f;
                        }   else if (rnd > 0.15f) { 
                            weaponId = getRandomListMember(mediumWeapons);
                            factor = 2f;
                        }   else {
                            weaponId = getRandomListMember(largeWeapons);
                            factor = 1f;
                        }
			int quantity = (int)((Math.random() * 2f + 3f) * factor);
                        cargo.addWeapons(weaponId, quantity);
	}
        
        public static void addFactionShip(CargoAPI cargo, Map W, Map F, Map D, Map C, Map CS) {
                float rnd = (float) Math.random();
                        if ((rnd > 0.90f) && (CS.size() > 0)) {
                                String shipId = (String) CollectionUtils.weightedRandom(CS);
                                cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, shipId));
                        }   else if ((rnd > 0.75f) && (C.size() > 0)) {
                                String shipId = (String) CollectionUtils.weightedRandom(C);
                                cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, shipId));
                        }   else if ((rnd > 0.55f) && (D.size() > 0)) {
                                String shipId = (String) CollectionUtils.weightedRandom(D);
                                cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, shipId));
                        }   else if ((rnd > 0.20f) && (F.size() > 0)) {
                                String shipId = (String) CollectionUtils.weightedRandom(F);
                                cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.SHIP, shipId));
                        }   else if (W.size() > 0)  {
                                String shipId = (String) CollectionUtils.weightedRandom(W);
                                cargo.getMothballedShips().addFleetMember(Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, shipId));
                        }
	}

        public static void removeRandomWeapon(CargoAPI cargo) {
                List cargoList = cargo.getStacksCopy();
                Collections.shuffle(cargoList);
                String breakWep;
		for (int i = 0; i < cargoList.size(); i++ ){
			if (!((CargoStackAPI)cargoList.get(i)).isWeaponStack()) {
                        continue;
                    }
                        float factor;
                        breakWep = (String) ((CargoStackAPI)cargoList.get(i)).getData();
                        if (UsSData.ALL_LW_FINAL.contains(breakWep)) {
                            factor = 3;
                        }   else if (UsSData.ALL_MW_FINAL.contains(breakWep)) {
                            factor = 2;
                        }   else {
                            factor = 1;
                        }
                                int quantity = (int)((Math.random() * 2f + 3f) * factor);
				cargo.removeWeapons(breakWep, quantity);
				return;
		}
	}
        
        public static void removeRandomShip(CargoAPI cargo) {
                List cargoList = cargo.getMothballedShips().getMembersListCopy();
                if (!cargoList.isEmpty()) {
                        Collections.shuffle(cargoList);
                        FleetMemberAPI breakShip = (FleetMemberAPI) cargoList.get(0);
                        if (breakShip.getHullId().equals("nom_oasis")) {
                        return;
                    }
                        cargo.getMothballedShips().removeFleetMember(breakShip);
                }
	}
        
        public static void addMothballedShip(CampaignFleetAPI fleet, String shipId) {
                FleetMemberAPI member = Global.getFactory().createFleetMember(FleetMemberType.SHIP, shipId);
                fleet.getFleetData().addFleetMember(member);
                member.getRepairTracker().setMothballed(true);
        }
        
        public static void addMothballedWing(CampaignFleetAPI fleet, String shipId) {
                FleetMemberAPI member = Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, shipId);
                fleet.getFleetData().addFleetMember(member);
                member.getRepairTracker().setMothballed(true);
        }
    
        public static StarSystemAPI getRandomSystem(SectorAPI sector){
                StarSystemAPI rnd_system = (StarSystemAPI) sector.getStarSystems().get((int) (sector.getStarSystems().size() * Math.random()));
                return rnd_system;
        }
        
        public static SectorEntityToken getRandomStation(SectorAPI sector, StarSystemAPI system){
                SectorEntityToken station = (SectorEntityToken) system.getOrbitalStations().get((int) (system.getOrbitalStations().size() * Math.random()));
                return station;
        }
        
        public static SectorEntityToken getRandomStationByParam(CampaignFleetAPI fleet, String stationType) {
            List systems = Global.getSector().getStarSystems();
            Collections.shuffle(systems);
            Iterator iter = systems.iterator();
            StarSystemAPI system;
            SectorEntityToken friendStation = null;

            while (iter.hasNext())
            {
                system = (StarSystemAPI) iter.next();
                SectorEntityToken station = null;
                if (stationType.equals("friendly")) {
                    station = getFriendlyStationinSystem(system, fleet);
                } else
                if (stationType.equals("neutral")) {
                    station = getNeutralStationinSystem(system, fleet);
                } else {
                    station = getFactionStationinSystem(system, fleet, stationType);
                }
                if (station == null)    {
                    continue;
                } else {
                    friendStation = station;
                }
            }
        return friendStation;
        }
        
        public static SectorEntityToken getNeutralStationinSystem(StarSystemAPI system, CampaignFleetAPI fleet){
                List stations = system.getOrbitalStations();
                SectorEntityToken friendStation = null;
                for (int i = 0; i < stations.size(); i = i + 1)  {
                    SectorEntityToken station = (SectorEntityToken)stations.get(i);
                    if ((station.getFaction().getRelationship(fleet.getFaction().getId()) == 0) && (!station.getFaction().getId().equals("neutral"))) {
                        friendStation = station;
                    } else {
                        continue;
                    }
                }
        return friendStation;
        }
        
        public static SectorEntityToken getFriendlyStationinSystem(StarSystemAPI system, CampaignFleetAPI fleet){
                List stations = system.getOrbitalStations();
                SectorEntityToken friendStation = null;
                for (int i = 0; i < stations.size(); i = i + 1)  {
                    SectorEntityToken station = (SectorEntityToken)stations.get(i);
                    if ((station.getFaction().getId().equals(fleet.getFaction().getId())) || (station.getFaction().getRelationship(fleet.getFaction().getId()) == 1)) {
                        friendStation = station;
                    } else {
                        continue;
                    }
                }
        return friendStation;
        }
        
        public static SectorEntityToken getFactionStationinSystem(StarSystemAPI system, CampaignFleetAPI fleet, String stationType){
                List stations = system.getOrbitalStations();
                SectorEntityToken friendStation = null;
                for (int i = 0; i < stations.size(); i = i + 1)  {
                    SectorEntityToken station = (SectorEntityToken)stations.get(i);
                    if (station.getFaction().getId().equals(stationType)) {
                        friendStation = station;
                    } else {
                        continue;
                    }
                }
        return friendStation;
        }
        
        public static SectorEntityToken getRandomAsteroid(LocationAPI system){
                SectorEntityToken asteroid = null;
                if (!system.getAsteroids().isEmpty()) {
                    asteroid = (SectorEntityToken) system.getAsteroids().get((int) (system.getAsteroids().size() * Math.random()));
                } else asteroid = null;
        return asteroid;
        }
        
        public static String getRandomMapMember(Map map){
                String temp = (String) CollectionUtils.weightedRandom(map);
                return temp;
        }
        
        public static String getRandomArrayMember(String [] array){
                String ship = (String)  array[(int) (array.length * Math.random())];
                return ship;
        }
        
        public static String getRandomListMember(List list){
                String ship = (String)  list.get((int) (list.size() * Math.random()));
                return ship;
        }
        
        public static String getRandomVariant(String hullType, Map Variants) {
        if (!Variants.keySet().contains(hullType))
        {
            return hullType + "_Hull";
        }
        List tmp = (List) Variants.get(hullType);
        return (String) tmp.get(rng.nextInt(tmp.size()));
        }

        public static void randomizeFleet(CampaignFleetAPI fleet, Map Variants) {
            FleetDataAPI data = fleet.getFleetData();
            List members = data.getMembersListCopy();
            Iterator iter = members.iterator();
            FleetMemberAPI ship;

            while (iter.hasNext())
            {
                ship = (FleetMemberAPI) iter.next();

                if (ship.isFighterWing())
                {
                    continue;
                }
                if (ship.getSpecId().endsWith("_Hull"))                                 
                {
                    randomizeShip(ship, data, Variants);
                }
            }
        }

        public static void randomizeShip(FleetMemberAPI ship, FleetDataAPI data, Map Variants) {
            if (ship == null || data == null)
            {
                return;
            }

            String hullType = ship.getSpecId();
            hullType = hullType.substring(0, hullType.lastIndexOf("_"));
            if (!Variants.keySet().contains(hullType))
            {
                return;
            }

            String variant = getRandomVariant(hullType, Variants);
            FleetMemberAPI newShip;
            newShip = Global.getFactory().createFleetMember(
            FleetMemberType.SHIP, variant);

            if (newShip != null)
            {
                data.removeFleetMember(ship);
                data.addFleetMember(newShip);                
            }
        }
	
	public static Vector2f translate_polar( Vector2f center, float radius, float angle ){
            float radians = (float)Math.toRadians( angle );
            return new Vector2f(
            (float) cos(radians) * radius + (center == null ? 0f : center.x),
            (float) sin(radians) * radius + (center == null ? 0f : center.y)
                    );
        }	

        public static float get_angle( Vector2f vector ){
            return (float)Math.toDegrees( Math.atan2( vector.y, vector.x ));
        }

        public static float get_angle( Vector2f from, Vector2f to ){
            return get_angle( new Vector2f(
                      to.x - from.x,
                      to.y - from.y ));
        }
	
	public static float get_distance( Vector2f A, Vector2f B ){
		Vector2f result = new Vector2f();
		Vector2f.sub( B, A, result );
		return result.length();
	}
	
	public static float get_distance_squared( Vector2f A, Vector2f B ){
		Vector2f result = new Vector2f( B.x - A.x, B.y - A.y );
		return result.lengthSquared();
	}
        
        private static double reduceSinAngle(double radians) {
		double orig = radians;
		radians %= Math.PI * 2.0; // put us in -2PI to +2PI space
		if (Math.abs(radians) > Math.PI) { // put us in -PI to +PI space
			radians = radians - (Math.PI * 2.0);
		}
		if (Math.abs(radians) > Math.PI / 2) {// put us in -PI/2 to +PI/2 space
			radians = Math.PI - radians;
		}

		return radians;
	}
        
	public static double sin(double radians) {
		radians = reduceSinAngle(radians); // limits angle to between -PI/2 and +PI/2
		if (Math.abs(radians) <= Math.PI / 4) {
			return Math.sin(radians);
		} else {
			return Math.cos(Math.PI / 2 - radians);
		}
	}
        
	public static double cos(double radians) {
		return sin(radians + Math.PI / 2);
	}

        public static float get_random( float low, float high ) {
                    return rng.nextFloat() * (high - low) + low;
            }
    
        public static void setStart() {
            Map persistentBpMap = new HashMap();
            Map stations = new HashMap();
            Global.getSector().getPersistentData().put("UsSblueprintsData", persistentBpMap);	
            Global.getSector().getPersistentData().put("UsSstationData", stations);
            
//            BpData factionBpData = (BpData) persistentBpMap.get("Hegemony");
//            if (factionBpData == null) {
//                factionBpData = new UsSData.BpData();
//                persistentBpMap.put("Hegemony", factionBpData);
//                factionBpData.bpcount = 5;
//                factionBpData.capitals = 2;
//                factionBpData.wings = 2;
//                factionBpData.frigates = 1;
//                factionBpData.factionId = "hegemony";
//            }
//            BpData factionBpData2 = (BpData) persistentBpMap.get("Tritachyon");
//            if (factionBpData2 == null) {
//                factionBpData2 = new UsSData.BpData();
//                persistentBpMap.put("Tritachyon", factionBpData2);
//                factionBpData2.bpcount = 2;
//                factionBpData2.cruisers = 2;
//                factionBpData2.factionId = "tritachyon";
//            }                
              
            FactionAPI player = Global.getSector().getFaction("player");
            Global.getSector().getPersistentData().put("UsSPlayerId", UsSData.player_id);
            Global.getSector().getCampaignUI().addMessage("Enabled Player Id: " + Global.getSector().getPersistentData().get("UsSPlayerId"), Color.WHITE, "" + Global.getSector().getPersistentData().get("UsSPlayerId"), Color.GREEN);
            
            if (Global.getSector().getPersistentData().get("UsSPlayerId") == "Hegemony Enforcer") {
                        player.setRelationship("hegemony", 1);
                        player.setRelationship("shadow_industry", -1);   
                        player.setRelationship("blackrock_driveyards", -1);
                        player.setRelationship("pack", -1);
                        player.setRelationship("tritachyon", -1);
                        player.setRelationship("nomads", -1);
            }
            if (Global.getSector().getPersistentData().get("UsSPlayerId") == "Diktat Lion") {
                        player.setRelationship("sindrian_diktat", 1);  
                        player.setRelationship("shadow_industry", -1);   
                        player.setRelationship("blackrock_driveyards", -1);
                        player.setRelationship("pack", -1);
                        player.setRelationship("tritachyon", -1);
                        player.setRelationship("nomads", -1);
            }
            if (Global.getSector().getPersistentData().get("UsSPlayerId") == "Pack Wolf") {
                        player.setRelationship("pack", 1);
                        player.setRelationship("shadow_industry", -1);   
                        player.setRelationship("blackrock_driveyards", -1);
                        player.setRelationship("hegemony", -1);
                        player.setRelationship("tritachyon", -1);
                        player.setRelationship("sindrian_diktat", -1);
                        player.setRelationship("nomads", -1);
            }
            if (Global.getSector().getPersistentData().get("UsSPlayerId") == "Tritachyon Corporate") {
                        player.setRelationship("tritachyon", 1);
                        player.setRelationship("blackrock_driveyards", -1);
                        player.setRelationship("hegemony", -1);
                        player.setRelationship("sindrian_diktat", -1);
                        player.setRelationship("pack", -1);
                        player.setRelationship("nomads", -1);
            }
            if (Global.getSector().getPersistentData().get("UsSPlayerId") == "Blackrock Corporate") {
                        player.setRelationship("blackrock_driveyards", 1);
                        player.setRelationship("shadow_industry", -1);   
                        player.setRelationship("pack", -1);
                        player.setRelationship("hegemony", -1);
                        player.setRelationship("tritachyon", -1);
                        player.setRelationship("sindrian_diktat", -1);
                        player.setRelationship("nomads", -1);
            }
            if (Global.getSector().getPersistentData().get("UsSPlayerId") == "Shadow Corporate") {
                        player.setRelationship("shadow_industry", 1);
                        player.setRelationship("blackrock_driveyards", -1);
                        player.setRelationship("hegemony", -1);
                        player.setRelationship("sindrian_diktat", -1);
                        player.setRelationship("pack", -1);
                        player.setRelationship("nomads", -1);
            }
            if (Global.getSector().getPersistentData().get("UsSPlayerId") == "Pirate") {
                        player.setRelationship("junk_pirates", 1);
                        player.setRelationship("pirates", 1);
                        player.setRelationship("blackrock_driveyards", -1);
                        player.setRelationship("shadow_industry", -1);   
                        player.setRelationship("pack", -1);
                        player.setRelationship("hegemony", -1);
                        player.setRelationship("tritachyon", -1);
                        player.setRelationship("sindrian_diktat", -1);
                        player.setRelationship("independent", -1);
                        player.setRelationship("nomads", -1);
                        player.setRelationship("insurgency", 0);
            }
            if (Global.getSector().getPersistentData().get("UsSPlayerId") == "Nomad") {
                        player.setRelationship("nomads", 1);
                        player.setRelationship("blackrock_driveyards", -1);
                        player.setRelationship("shadow_industry", -1);   
                        player.setRelationship("pack", -1);
                        player.setRelationship("hegemony", -1);
                        player.setRelationship("tritachyon", -1);
                        player.setRelationship("sindrian_diktat", -1);
            }
            if (Global.getSector().getPersistentData().get("UsSPlayerId") == "Voice of Qamar") {
                        player.setRelationship("insurgency", 1);
                        player.setRelationship("junk_pirates", 0);
                        player.setRelationship("pirates", 0);
                        player.setRelationship("blackrock_driveyards", -1);
                        player.setRelationship("shadow_industry", -1);   
                        player.setRelationship("pack", -1);
                        player.setRelationship("hegemony", -1);
                        player.setRelationship("tritachyon", -1);
                        player.setRelationship("sindrian_diktat", -1);
                        player.setRelationship("independent", -1);
                        player.setRelationship("nomads", -1);
                        player.setRelationship("regime", -1);
            }
            if (Global.getSector().getPersistentData().get("UsSPlayerId") == "Voice of Kadur") {
                        player.setRelationship("regime", 1);
                        player.setRelationship("pack", -1);
                        player.setRelationship("hegemony", -1);
                        player.setRelationship("tritachyon", -1);
                        player.setRelationship("sindrian_diktat", -1);
                        player.setRelationship("nomads", -1);
                        player.setRelationship("insurgency", -1);
            }
            Global.getSector().setRespawnLocation(Global.getSector().getCurrentLocation());
            Global.getSector().getRespawnCoordinates().set(Global.getSector().getPlayerFleet().getLocation().getX(), Global.getSector().getPlayerFleet().getLocation().getY());
        }
        
        public static void Loot(FleetMemberData data, CargoAPI loot, float mult) {
            String memberId;
            int scraps = (int) ((data.getMember().getFleetPointCost()*3.5f * mult) /2);
            if (data.getMember().isFighterWing()) {
                memberId = data.getMember().getHullId().concat("_wing");
            } else {
                memberId = data.getMember().getHullId().concat("_Hull");
            }
                    
            for (String tech : UsSData.techsStrings) {
                UsSDataGrabber.setSource(tech);
                if (UsSDataGrabber.Tall.keySet().contains(memberId)) {
                    loot.addItems(CargoAPI.CargoItemType.RESOURCES, UsSDataGrabber.scrapId, (int) (scraps * Math.random()/calcTechFactor(UsSDataGrabber.scrapId)));
                    loot.addItems(CargoAPI.CargoItemType.RESOURCES, UsSDataGrabber.armorId, (int) (scraps * Math.random()/calcTechFactor(UsSDataGrabber.armorId)));
                    loot.addItems(CargoAPI.CargoItemType.RESOURCES, UsSDataGrabber.enginesId, (int) (scraps * Math.random()/calcTechFactor(UsSDataGrabber.enginesId)));
                    loot.addItems(CargoAPI.CargoItemType.RESOURCES, UsSDataGrabber.fluxId, (int) (scraps * Math.random()/calcTechFactor(UsSDataGrabber.fluxId)));
                }
            }
        }
        
        public static int calcTechFactor(String Id) {
            if (Id.startsWith("LT")) {
                return 2;
            } else
            if (Id.startsWith("MT")) {
                return 4;
            } else
            if (Id.startsWith("HT")) {
                return 8;
            } else
            if ((Id.startsWith("BR")) || (Id.startsWith("SHI")) || (Id.startsWith("PCK")) || (Id.startsWith("JP")) || (Id.startsWith("KAD")) || (Id.startsWith("NOM"))){
                return 20;
            }
        return 0;
        }
        
        public static boolean doHaveEnoughResourcesForProduction(FleetMemberAPI member, CargoAPI cargo) {
            String memberId = member.getSpecId();
            boolean boolres1 = false;
            boolean boolres2 = false;
            boolean boolres3 = false;
            boolean boolres4 = false;
            boolean boolelec = false;
            String res1 = null, res2 = null, res3 = null, res4 = null;
            int res1q = 1000, res2q = 1000, res3q = 1000, res4q = 1000;
            int factor = (int) (member.getFleetPointCost()*3.5f);
            for (String tech : UsSData.techsStrings) {
                UsSDataGrabber.setSource(tech);
                if (UsSDataGrabber.Tall.keySet().contains(memberId)) {
                    res1 = UsSDataGrabber.scrapId;
                    res1q = factor/calcTechFactor(UsSDataGrabber.scrapId);
                    res2 = UsSDataGrabber.armorId;
                    res2q = factor/calcTechFactor(UsSDataGrabber.armorId);    
                    res3 = UsSDataGrabber.enginesId;
                    res3q = factor/calcTechFactor(UsSDataGrabber.enginesId);
                    res4 = UsSDataGrabber.fluxId;
                    res4q = factor/calcTechFactor(UsSDataGrabber.fluxId); 
                }
            }
            for (CargoStackAPI stack : cargo.getStacksCopy()) {
			if (stack.isNull()) continue;
			if (stack.isSupplyStack() || stack.isFuelStack() || stack.isCrewStack() || stack.isMarineStack() || stack.isWeaponStack()) continue;
                        if ((stack.getData().equals(res1)) && (stack.getSize() >= res1q)) {
                            boolres1 = true;
                        }
                        if ((stack.getData().equals(res2)) && (stack.getSize() >= res2q)) {
                            boolres2 = true;
                        }
                        if ((stack.getData().equals(res3)) && (stack.getSize() >= res3q)) {
                            boolres3 = true;
                        }
                        if ((stack.getData().equals(res4)) && (stack.getSize() >= res4q)) {
                            boolres4 = true;
                        }
                        if ((stack.getData().equals("electronics")) && (stack.getSize() >= (int) (member.getFleetPointCost()*5f))) {
                            boolelec = true;
                        }
                        continue;
		}
            if ((boolres1 == true) && (boolres2 == true) && (boolres3 == true) && (boolres4 == true) && (boolelec == true)) {
                return true;
            } else {
                return false;
            }
        }
        
        public static void BuildShip(FleetMemberAPI member, CargoAPI cargo) {
            String memberId = member.getSpecId();
            String res1 = null, res2 = null, res3 = null, res4 = null;
            int res1q = 0, res2q = 0, res3q = 0, res4q = 0;
            int factor = (int) (member.getFleetPointCost()*3.5f);
            for (String tech : UsSData.techsStrings) {
                UsSDataGrabber.setSource(tech);
                if (UsSDataGrabber.Tall.keySet().contains(memberId)) {
                    res1 = UsSDataGrabber.scrapId;
                    res1q = factor/calcTechFactor(UsSDataGrabber.scrapId);
                    res2 = UsSDataGrabber.armorId;
                    res2q = factor/calcTechFactor(UsSDataGrabber.armorId);    
                    res3 = UsSDataGrabber.enginesId;
                    res3q = factor/calcTechFactor(UsSDataGrabber.enginesId);
                    res4 = UsSDataGrabber.fluxId;
                    res4q = factor/calcTechFactor(UsSDataGrabber.fluxId); 
                }
            }
            cargo.removeItems(CargoAPI.CargoItemType.RESOURCES, res1, res1q);
            cargo.removeItems(CargoAPI.CargoItemType.RESOURCES, res2, res2q);
            cargo.removeItems(CargoAPI.CargoItemType.RESOURCES, res3, res3q);
            cargo.removeItems(CargoAPI.CargoItemType.RESOURCES, res4, res4q);
            cargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "electronics", (int) (member.getFleetPointCost()*5f));
        }
        
        public static void BlueprintGain(CargoAPI loot, DataForEncounterSide loser) {
            String factionName = loser.getFleet().getFaction().getDisplayName();
            String factionId = loser.getFleet().getFaction().getId();
            int FP = loser.getFleet().getFleetPoints();
            int quantity = (int) ((Math.random()) + (FP/50f));
            if (quantity > 0) {
                Map persistentBpMap = (HashMap) Global.getSector().getPersistentData().get("UsSblueprintsData");
                BpData factionBpData = (BpData) persistentBpMap.get(factionName);
                if (factionBpData == null) {
                    factionBpData = new UsSData.BpData();
                    persistentBpMap.put(factionName, factionBpData);
                    factionBpData.factionId = factionId;
                }

                List bpPickList = new ArrayList();

                for (int i = 0; i < quantity; i++) {
                    if (FP > 100) {
                        bpPickList.add("capital");   
                    }
                    if (FP > 50) {
                        bpPickList.add("cruiser"); 
                    }
                    if (FP > 25) {
                        bpPickList.add("destroyer"); 
                    }
                    bpPickList.add("wing"); 
                    bpPickList.add("frigate");
                    bpPickList.add("frigate");
                    String pick = UsSUtils.getRandomListMember(bpPickList);
                    if (pick.equals("capital")) {
                        factionBpData.capitals += 1;
//                        Global.getSector().getCampaignUI().addMessage("You found a capital Blueprint in the wreckages.");
                    }
                    if (pick.equals("cruiser")) {
                        factionBpData.cruisers += 1;
//                        Global.getSector().getCampaignUI().addMessage("You found a cruiser Blueprint in the wreckages.");
                    }
                    if (pick.equals("destroyer")) {
                        factionBpData.destroyers += 1;
//                        Global.getSector().getCampaignUI().addMessage("You found a destroyer Blueprint in the wreckages.");
                    }
                    if (pick.equals("wing")) {
                        factionBpData.wings += 1;
//                        Global.getSector().getCampaignUI().addMessage("You found a fighter Blueprint in the wreckages.");
                    }
                    if (pick.equals("frigate")) {
                        factionBpData.frigates += 1;
//                        Global.getSector().getCampaignUI().addMessage("You found a frigate Blueprint in the wreckages.");
                    }
                }
                factionBpData.bpcount += quantity;  
                Global.getSector().getCampaignUI().addMessage("You found " + quantity + " new Blueprints in the wreckages.");
            }
        }
        
        public static String shipCost(FleetMemberAPI member) {
            String memberId = member.getSpecId();
            int factor = (int) (member.getFleetPointCost()*3.5f);
            String cost = null; 
            String component1 = null; 
            String component2 = null; 
            String component3 = null; 
            String component4 = null; 
            String scraps = null;
            String manu = null;
            
            for (String tech : UsSData.techsStrings) {
                UsSDataGrabber.setSource(tech);
                if (UsSDataGrabber.Tall.keySet().contains(memberId)) { 
                    manu = UsSDataGrabber.techManufacturer;
                    component1 = (factor/calcTechFactor(UsSDataGrabber.scrapId)) + " " + getResourceName(UsSDataGrabber.scrapId) + ", ";
                    component2 = (factor/calcTechFactor(UsSDataGrabber.armorId)) + " " + getResourceName(UsSDataGrabber.armorId) + ", ";
                    component3 = (factor/calcTechFactor(UsSDataGrabber.enginesId)) + " " + getResourceName(UsSDataGrabber.enginesId) + ", ";
                    component4 = (factor/calcTechFactor(UsSDataGrabber.fluxId)) + " " + getResourceName(UsSDataGrabber.fluxId) + ", and ";
                }
            }
            scraps = (int) (member.getFleetPointCost()*5f) + " Electronics"; 
            cost = "Technology origin: " + manu
                    + ". Production requirements: " + component1 + component2 + component3 + component4 + scraps
                    + ". Production time: " + ((int) member.getFleetPointCost()/3) + " day/s";
            return cost;
        }
        
        private static String getResourceName(String Id) {
            if (Id.equals("LTscraps")) return "Mastery Epoch Scraps";
            if (Id.equals("LTarmor")) return "Mastery Epoch Armor Plates";
            if (Id.equals("LTengines")) return "Mastery Epoch Engine Parts";
            if (Id.equals("LTflux")) return "Mastery Epoch Flux Conduits";
            if (Id.equals("MTscraps")) return "Core Epoch Scraps";
            if (Id.equals("MTarmor")) return "Core Epoch Armor Plates";
            if (Id.equals("MTengines")) return "Core Epoch Engine Parts";
            if (Id.equals("MTflux")) return "Core Epoch Flux Conduits";
            if (Id.equals("HTscraps")) return "Expansion Epoch Scraps";
            if (Id.equals("HTarmor")) return "Expansion Epoch Armor Plates";
            if (Id.equals("HTengines")) return "Expansion Epoch Engine Parts";
            if (Id.equals("HTflux")) return "Expansion Epoch Flux Conduits";
            if (Id.equals("BRengines")) return "BlackRock Drive Core";
            if (Id.equals("SHIflux")) return "Shadow Flux Relay";
            if (Id.equals("PCKarmor")) return "Light Prismatic Alloy Plates";
            if (Id.equals("JPflux")) return "Junked Dynamic Flux Inverter Set";
            if (Id.equals("KADarmor")) return "SuperHeavy Diamonium Alloy Plates";
            if (Id.equals("NOMscraps")) return "Nurrian Polar-Shifted Metal Scraps";
            return null;
        }
}

    
