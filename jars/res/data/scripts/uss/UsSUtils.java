package data.scripts.uss;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.FleetDataAPI;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import data.scripts.UsSData;
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
        
        public static void addCargoToTraders(SectorAPI sector, CampaignFleetAPI fleet) {
                int fp = fleet.getFleetPoints();
                float rnd = (float) Math.random();
                String weaponId;
                int tradeCargo = fp/15;
                float factor;
                for (int i = 0; i < (int) (tradeCargo); i++) {
			if ((fp > 30) && (rnd > 0.85f)) {
                            weaponId = getRandomListMember(UsSData.ALL_LW_FINAL);
                            factor = 1f;
                        }   else if ((fp > 15) && (rnd > 0.50f)) { 
                            weaponId = getRandomListMember(UsSData.ALL_MW_FINAL);
                            factor = 2f;
                        }   else {
                            weaponId = getRandomListMember(UsSData.ALL_SW_FINAL);
                            factor = 3f;
                        }
			int quantity = (int)((Math.random() * 2f + 1f) * factor);
                        fleet.getCargo().addWeapons(weaponId, quantity);
                }
                if ((fp > 60) && (Math.random() > 0.5f)) {
                            String shipId = (String) CollectionUtils.weightedRandom(UsSData.ALL_CS_FINAL);
                            addMothballedShip(fleet, shipId);
                            tradeCargo -=4;
                }   if ((fp > 30) && (Math.random() > 0.5f) && (tradeCargo > 0)) { 
                            String shipId = (String) CollectionUtils.weightedRandom(UsSData.ALL_C_FINAL);
                            addMothballedShip(fleet, shipId);
                            tradeCargo -=2;
                }   if ((fp > 15) && (Math.random() > 0.5f) && (tradeCargo > 0)) { 
                            String shipId = (String) CollectionUtils.weightedRandom(UsSData.ALL_D_FINAL);
                            addMothballedShip(fleet, shipId);
                            tradeCargo -=1;
                }   if ((Math.random() > 0.33f) && (tradeCargo > 0)) {
                            String shipId = (String) CollectionUtils.weightedRandom(UsSData.ALL_F_FINAL);
                            addMothballedShip(fleet, shipId);
                            tradeCargo -=0.75f;
                }   if ((Math.random() > 0.33f) && (tradeCargo > 0)) {
                            String shipId = (String) CollectionUtils.weightedRandom(UsSData.ALL_W_FINAL);
                            addMothballedWing(fleet, shipId);
                            tradeCargo -=0.5f;
                }   if ((Math.random() > 0.33f) && (tradeCargo > 0)) {
                            String shipId = (String) CollectionUtils.weightedRandom(UsSData.ALL_F_FINAL);
                            addMothballedShip(fleet, shipId);
                            tradeCargo -=0.5f;
                }   if ((Math.random() > 0.33f) && (tradeCargo > 0)) {
                            String shipId = (String) CollectionUtils.weightedRandom(UsSData.ALL_F_FINAL);
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
                if (Math.random() > 0.30f) {
                    if ((station.getFaction().getId().matches("independents")) && (station.getCargo().getStacksCopy().size() < 14)) {
                        addFactionWeapon(station.getCargo(), UsSData.ALL_SW_FINAL, UsSData.ALL_MW_FINAL, UsSData.ALL_LW_FINAL);
                    }
                    if (station.getFaction().getId().matches("hegemony")) {
                        addFactionWeapon(station.getCargo(), UsSData.LT_SW_FINAL, UsSData.LT_MW_FINAL, UsSData.LT_LW_FINAL);
                    }
                    if (station.getFaction().getId().matches("pirates")) {
                        addFactionWeapon(station.getCargo(), UsSData.LT_SW_FINAL, UsSData.LT_MW_FINAL, UsSData.LT_LW_FINAL);
                    }
                    if (station.getFaction().getId().matches("tritachyon")) {
                        addFactionWeapon(station.getCargo(), UsSData.HT_SW_FINAL, UsSData.HT_MW_FINAL, UsSData.HT_LW_FINAL);
                    }
                    if (station.getFaction().getId().matches("sindrian_diktat")) {
                        addFactionWeapon(station.getCargo(), UsSData.MT_SW_FINAL, UsSData.MT_MW_FINAL, UsSData.MT_LW_FINAL);
                    }
                    if (station.getFaction().getId().matches("junk_pirates")) {
                        addFactionWeapon(station.getCargo(), UsSData.JP_SW_FINAL, UsSData.JP_MW_FINAL, UsSData.JP_LW_FINAL);
                    }
                    if (station.getFaction().getId().matches("blackrock_driveyards")) {
                        addFactionWeapon(station.getCargo(), UsSData.BR_SW_FINAL, UsSData.BR_MW_FINAL, UsSData.BR_LW_FINAL);
                    }
                    if (station.getFaction().getId().matches("shadow_industry")) {
                        addFactionWeapon(station.getCargo(), UsSData.SHI_SW_FINAL, UsSData.SHI_MW_FINAL, UsSData.SHI_LW_FINAL);
                    }
                    if (station.getFaction().getId().matches("shadow_industry")) {
                        addFactionWeapon(station.getCargo(), UsSData.PCK_SW_FINAL, UsSData.PCK_MW_FINAL, UsSData.PCK_LW_FINAL);
                    }
                }
                if (Math.random() > 0.30f) {
                    if ((station.getFaction().getId().matches("independents")) && (station.getCargo().getMothballedShips().getMembersListCopy().size() < 8)) {
                        addFactionShip(station.getCargo(), UsSData.ALL_W_FINAL, UsSData.ALL_F_FINAL, UsSData.ALL_D_FINAL, UsSData.ALL_C_FINAL, UsSData.ALL_CS_FINAL);
                    }
                    if (station.getFaction().getId().matches("hegemony")) {
                        addFactionShip(station.getCargo(), UsSData.HE_W, UsSData.HE_F, UsSData.HE_D, UsSData.HE_C, UsSData.HE_CS);
                    }
                    if (station.getFaction().getId().matches("pirates")) {
                        addFactionShip(station.getCargo(), UsSData.PP_W, UsSData.PP_F, UsSData.PP_D, UsSData.PP_C, UsSData.PP_CS);
                    }
                    if (station.getFaction().getId().matches("tritachyon")) {
                        addFactionShip(station.getCargo(), UsSData.TT_W, UsSData.TT_F, UsSData.TT_D, UsSData.TT_C, UsSData.TT_CS);
                    }
                    if (station.getFaction().getId().matches("sindrian_diktat")) {
                        addFactionShip(station.getCargo(), UsSData.SIN_W, UsSData.SIN_F, UsSData.SIN_D, UsSData.SIN_C, UsSData.SIN_CS);
                    }
                    if (station.getFaction().getId().matches("junk_pirates")) {
                        addFactionShip(station.getCargo(), UsSData.JP_W, UsSData.JP_F, UsSData.JP_D, UsSData.JP_C, UsSData.JP_CS);
                    }
                    if (station.getFaction().getId().matches("blackrock_driveyards")) {
                        addFactionShip(station.getCargo(), UsSData.BR_W, UsSData.BR_F, UsSData.BR_D, UsSData.BR_C, UsSData.BR_CS);
                    }
                    if (station.getFaction().getId().matches("shadow_industry")) {
                        addFactionShip(station.getCargo(), UsSData.SHI_W, UsSData.SHI_F, UsSData.SHI_D, UsSData.SHI_C, UsSData.SHI_CS);
                    }
                    if (station.getFaction().getId().matches("pack")) {
                        addFactionShip(station.getCargo(), UsSData.PCK_W, UsSData.PCK_F, UsSData.PCK_D, UsSData.PCK_C, UsSData.PCK_CS);
                    }
                    if (station.getFaction().getId().matches("nomads")) {
                        addFactionShip(station.getCargo(), UsSData.NOM_W, UsSData.NOM_F, UsSData.NOM_D, UsSData.NOM_C, UsSData.NOM_CS);
                    }
                }
                if (!station.getFaction().getId().matches("neutral")) {
                        if ((Math.random() > 0.50f) && (station.getCargo().getMothballedShips().getMembersListCopy().size() > 8)) {
                            removeRandomShip(station.getCargo());
                        }
                        if ((Math.random() > 0.50f) && (station.getCargo().getMothballedShips().getMembersListCopy().size() > 20)) {
                            removeRandomShip(station.getCargo());
                        }
                        if ((Math.random() > 0.50f) && (station.getCargo().getStacksCopy().size() > 14)) {
                            removeRandomWeapon(station.getCargo());
                        }
                        if ((Math.random() > 0.50f) && (station.getCargo().getStacksCopy().size() > 25)) {
                            removeRandomWeapon(station.getCargo());
                        }
                        resourceFlucuation(station.getCargo());
                    }
                }
            }            
	}
        
        public static void resourceFlucuation(CargoAPI cargo) {
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
                        }
                        if (cargo.getSupplies() > 500) {
                            cargo.removeCrew(CargoAPI.CrewXPLevel.GREEN, (int)(Math.random() * 250));
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
        
        public static SectorEntityToken getRandomStationByRelationship(CampaignFleetAPI fleet, String stationType) {
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
                }
                if (stationType.equals("neutral")) {
                    station = getNeutralStationinSystem(system, fleet);
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
        
        public static SectorEntityToken getRandomAsteroid(LocationAPI system){
                SectorEntityToken asteroid = (SectorEntityToken) system.getAsteroids().get((int) (system.getAsteroids().size() * Math.random()));
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
                return new Vector2f((float) cos(radians) * radius + (center == null ? 0f : center.x),
                    (float) sin(radians) * radius + (center == null ? 0f : center.y));
        }		

        public static float get_angle( Vector2f vector ) {
            return (float)Math.toDegrees( Math.atan2( vector.y, vector.x ));
        }

        public static float get_angle( Vector2f from, Vector2f to ) {
            return get_angle( new Vector2f(
                      to.x - from.x,
                      to.y - from.y ));
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
            }
            if (Global.getSector().getPersistentData().get("UsSPlayerId") == "Nomad") {
                        player.setRelationship("nomads", 1);
                        player.setRelationship("junk_pirates", -1);
                        player.setRelationship("pirates", -1);
                        player.setRelationship("blackrock_driveyards", -1);
                        player.setRelationship("shadow_industry", -1);   
                        player.setRelationship("pack", -1);
                        player.setRelationship("hegemony", -1);
                        player.setRelationship("tritachyon", -1);
                        player.setRelationship("sindrian_diktat", -1);
            }
            if (Global.getSector().getPersistentData().get("UsSPlayerId") == "nomads") {
                Global.getSector().setRespawnLocation(Global.getSector().getHyperspace());
                Global.getSector().getRespawnCoordinates().set(18250, -800);
            } else {
                Global.getSector().setRespawnLocation(Global.getSector().getCurrentLocation());
                Global.getSector().getRespawnCoordinates().set(Global.getSector().getPlayerFleet().getLocation().getX(), Global.getSector().getPlayerFleet().getLocation().getY()); 
            }
        }
        
        public static void FillFleet(CampaignFleetAPI fleet, int AP) {
            fleet.getCargo().clear();
            int max_crew = 0;
            for (int i = 0; i < fleet.getFleetData().getMembersListCopy().size(); i++) {
            FleetMemberAPI ship = (FleetMemberAPI)fleet.getFleetData().getMembersListCopy().get(i);
            max_crew += ship.getMaxCrew();
            }
            if (AP <= 5) {
                if (!fleet.getName().endsWith("]")) {
                    fleet.setName(fleet.getName() + " [|]");
                }
                fleet.getCargo().addCrew(CargoAPI.CrewXPLevel.GREEN, (max_crew/5)*3);
            } else if (AP <= 10) {
                if (!fleet.getName().endsWith("]")) {
                    fleet.setName(fleet.getName() + " [||]");
                }
                fleet.getCargo().addCrew(CargoAPI.CrewXPLevel.REGULAR, (max_crew/5)*2);
                fleet.getCargo().addCrew(CargoAPI.CrewXPLevel.GREEN, (max_crew/5)*1);
            } else if (AP <= 15) {
                if (!fleet.getName().endsWith("]")) {
                    fleet.setName(fleet.getName() + " [|||]");
                }
                fleet.getCargo().addCrew(CargoAPI.CrewXPLevel.REGULAR, (max_crew/5)*2);
                fleet.getCargo().addCrew(CargoAPI.CrewXPLevel.VETERAN, (max_crew/5)*1);
            } else if (AP <= 20) {
                if (!fleet.getName().endsWith("]")) {
                    fleet.setName(fleet.getName() + " [V]");
                }
                fleet.getCargo().addCrew(CargoAPI.CrewXPLevel.VETERAN, (max_crew/5)*3);
            } else if (AP <= 25) {
                if (!fleet.getName().endsWith("]")) {
                    fleet.setName(fleet.getName() + " [vVv]");
                }
                fleet.getCargo().addCrew(CargoAPI.CrewXPLevel.VETERAN, (max_crew/5)*2);
                fleet.getCargo().addCrew(CargoAPI.CrewXPLevel.ELITE, (max_crew/5)*1);
            } else if (AP <= 30) {
                if (!fleet.getName().endsWith("]")) {
                    fleet.setName(fleet.getName() + " [*V*]");
                }
                fleet.getCargo().addCrew(CargoAPI.CrewXPLevel.ELITE, (max_crew/5)*3);
            }
            fleet.getCargo().addMarines(max_crew/10);
        }
}

    
