package data.scripts.uss;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CoreInteractionListener;
import com.fs.starfarer.api.campaign.CoreUITabId;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.FleetMemberPickerListener;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.OptionPanelAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.TextPanelAPI;
import com.fs.starfarer.api.campaign.VisualPanelAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.ui.ValueDisplayMode;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.lwjgl.input.Keyboard;

public class UsSOrbitalStationInteractionDialogPluginImpl implements InteractionDialogPlugin, CoreInteractionListener {
    

	private static enum OptionId {
		INIT,
		INIT_NO_TEXT,
		TRADE_CARGO,
		TRADE_SHIPS,
		REFIT,
		REPAIR_ALL,
                LEAVE,
                CRAFT,
                SHOW_BPS,
                MANU_SELECT,
                SELECTOR_MANU,
                PRODUCTION,
                IDENTIFY,
                DEPOT,
                RETRIEVE,
                AUTH,
	}
	
	private InteractionDialogAPI dialog;
	private TextPanelAPI textPanel;
	private OptionPanelAPI options;
	private VisualPanelAPI visual;
	
	private CampaignFleetAPI playerFleet;
	private SectorEntityToken station;
        private FactionAPI player;
        private FleetMemberAPI picked;
        private CargoAPI privatestash;
        private UsSFactoryManager stationFM;
        
        List<FleetMemberAPI> members = new ArrayList<FleetMemberAPI>();
        Map blueprintsDataMap = (HashMap) Global.getSector().getPersistentData().get("UsSblueprintsData");
        Map stationsDataMap = (HashMap) Global.getSector().getPersistentData().get("UsSstationData");
        List manuBpList = new ArrayList();
        int selectedManu = 0;
        int bpToIdentify;
	
	private static final Color HIGHLIGHT_COLOR = Global.getSettings().getColor("buttonShortcut");
	
	public void init(InteractionDialogAPI dialog) {
		this.dialog = dialog;
		textPanel = dialog.getTextPanel();
		options = dialog.getOptionPanel();
		visual = dialog.getVisualPanel();

		playerFleet = Global.getSector().getPlayerFleet();
		station = (SectorEntityToken) dialog.getInteractionTarget();
		player = Global.getSector().getFaction("player");
                
		visual.setVisualFade(0.25f, 0.25f);
	
		dialog.setOptionOnEscape("Leave", OptionId.LEAVE);
		optionSelected(null, OptionId.INIT);
	}
	
	private EngagementResultAPI lastResult = null;
	public void backFromEngagement(EngagementResultAPI result) {
		// no combat here, so this won't get called
	}
	
	public void optionSelected(String text, Object optionData) {
		if (optionData == null) return;
		
		OptionId option = (OptionId) optionData;
		
		if (text != null) {
			textPanel.addParagraph(text, Global.getSettings().getColor("buttonText"));
		}
		
		switch (option) {
		case INIT:
			if ((station.getFaction().getRelationship(player.getId()) == 1) || (station.getFaction().getId().equals("neutral"))) {
                            addText(getString("approach"));
                        } else if ((player.getRelationship(station.getFaction().getId()) >= 0) && (!station.getFaction().getId().equals("independent"))) {
                            addText("The station is for faction use only.");
                        } else if ((player.getRelationship(station.getFaction().getId()) >= 0) && (station.getFaction().getId().equals("independent"))) {
                            addText(getString("approach"));
                        } else {
                            addText("The station weapons activate.");
                        }
		case INIT_NO_TEXT:
			createInitialOptions();
			if  (station.getCustomInteractionDialogImageVisual() != null) {
				visual.showImageVisual(station.getCustomInteractionDialogImageVisual());
			} else if (station.getFaction().isNeutralFaction()) {
				visual.showImagePortion("illustrations", "space_wreckage", 800, 800, 0, 0, 400, 400);
                        } else if (station.getFaction().getRelationship(playerFleet.getFaction().getId()) < 0) {
				visual.showImagePortion("illustrations", "fly_away", 640, 400, 0, 0, 640, 400);
                        } else {
				visual.showImagePortion("illustrations", "hound_hangar", 800, 800, 0, 0, 400, 400);
                        }
			break;
		case TRADE_CARGO:
			addText(getString("tradeCargo"));
			options.clearOptions();
			visual.showCore(CoreUITabId.CARGO, station, station.getFaction().isNeutralFaction(), this);
			break;
		case TRADE_SHIPS:
			addText(getString("tradeShips"));
			options.clearOptions();
			visual.showCore(CoreUITabId.FLEET, station, station.getFaction().isNeutralFaction(), this);
			break;
		case REFIT:
			addText(getString("refit"));
			options.clearOptions();
			visual.showCore(CoreUITabId.REFIT, station, station.getFaction().isNeutralFaction(), this);
			break;
		case REPAIR_ALL:
			performRepairs();
			createInitialOptions();
			break;
                case CRAFT:
                        addText("You visit the station's autofactory");
                        createBasicCraftOptions();
                        break;
                case SHOW_BPS:
                        createProductionPickDialogue();
                        break;
                case MANU_SELECT:
                        manuSelect();
                        break;
                case PRODUCTION:
                        factoryManager();
                        break;
                case IDENTIFY:
                        identifyMenu();
                        break;
                case DEPOT:
                        addText("You visit your personal storage on the station");
                        options.clearOptions();
			visual.showLoot("Your personal storage", privatestash, this);
                        break;
                case RETRIEVE:
                        createRetrievePickDialogue();
                        break;
                case AUTH:
                        cargoPurchase();
                        createInitialOptions();
                        break;
		case LEAVE:
			Global.getSector().setPaused(false);
			dialog.dismiss();
			break;
		}
	}
	
	private void performRepairs() {
		addText(getString("repair"));
		float supplies = playerFleet.getCargo().getSupplies();
		float needed = playerFleet.getLogistics().getTotalRepairSupplyCost();
		
		textPanel.highlightLastInLastPara("" + (int) needed, HIGHLIGHT_COLOR);
		
		for (FleetMemberAPI member : playerFleet.getFleetData().getMembersListCopy()) {
                    member.getStatus().repairFully();
                    float max = member.getRepairTracker().getMaxCR();
                    float curr = member.getRepairTracker().getBaseCR();
                    if (max > curr) {
                        member.getRepairTracker().applyCREvent(max - curr, "Repaired at station");
                    }
		}
		if (needed > 0) {
			playerFleet.getCargo().removeSupplies(needed);
		}
	}
	
	private void createInitialOptions() {
		options.clearOptions();
		if (station.getFaction().isNeutralFaction()) {
                    options.addOption("Stash cargo or personnel in the station", OptionId.TRADE_CARGO);
                    options.setShortcut(OptionId.TRADE_CARGO, Keyboard.KEY_I, false, false, false, true);
                    options.addOption("Hide or retrieve ships in the station", OptionId.TRADE_SHIPS);
                    options.setShortcut(OptionId.TRADE_SHIPS, Keyboard.KEY_F, false, false, false, true);
		} else if ((player.getRelationship(station.getFaction().getId()) >= 0) && ((station.getFaction().getRelationship(playerFleet.getFaction().getId()) == 1) || (station.getFaction().getId().equals("independent")))) {
                    options.addOption("Trade, or hire personnel", OptionId.TRADE_CARGO);
                    options.setShortcut(OptionId.TRADE_CARGO, Keyboard.KEY_I, false, false, false, true);
                    options.addOption("Buy or sell ships", OptionId.TRADE_SHIPS, null);
                    options.setShortcut(OptionId.TRADE_SHIPS, Keyboard.KEY_F, false, false, false, true);
                    options.addOption("Make use of the dockyard's refitting facilities", OptionId.REFIT);
                    options.setShortcut(OptionId.REFIT, Keyboard.KEY_R, false, false, false, true);
                    float needed = playerFleet.getLogistics().getTotalRepairSupplyCost();
                    float supplies = playerFleet.getCargo().getSupplies();
                    options.addOption("Repair your ships at the station's dockyard", OptionId.REPAIR_ALL);
                    options.setShortcut(OptionId.REPAIR_ALL, Keyboard.KEY_A, false, false, false, true);
                    if (needed <= 0) {
                            options.setEnabled(OptionId.REPAIR_ALL, false);
                            options.setTooltip(OptionId.REPAIR_ALL, getString("repairTooltipAlreadyRepaired"));
                    } else if (supplies < needed) {
                            options.setEnabled(OptionId.REPAIR_ALL, false);
                            options.setTooltip(OptionId.REPAIR_ALL, getString("repairTooltipNotEnough"));
                            options.setTooltipHighlightColors(OptionId.REPAIR_ALL, HIGHLIGHT_COLOR, HIGHLIGHT_COLOR);
                            options.setTooltipHighlights(OptionId.REPAIR_ALL, "" + (int) needed, "" + (int) supplies);
                    } else {
                            options.setTooltip(OptionId.REPAIR_ALL, getString("repairTooltip"));
                            options.setTooltipHighlightColors(OptionId.REPAIR_ALL, HIGHLIGHT_COLOR, HIGHLIGHT_COLOR);
                            options.setTooltipHighlights(OptionId.REPAIR_ALL, "" + (int) needed, "" + (int) supplies);
                    }
                    
                    UsSData.StationData stationData = (UsSData.StationData) stationsDataMap.get(station.getFullName());
                    if (stationData == null) {
                        stationData = new UsSData.StationData();
                        stationsDataMap.put(station.getFullName(), stationData);
                        options.addOption("Purchase personal storage and Factory usage authorization for 5000 credits", OptionId.AUTH);
                        options.setShortcut(OptionId.AUTH, Keyboard.KEY_C, false, false, false, true);
                    } else 
                    if (stationData.privatePersonalStorage == false) {
                        options.addOption("Purchase personal storage and Factory usage authorization for 5000 credits", OptionId.AUTH);
                        options.setShortcut(OptionId.AUTH, Keyboard.KEY_C, false, false, false, true);
                    } else 
                    if (stationData.privatePersonalStorage == true) {
                        options.addOption("Manage personal storage and factory usage", OptionId.CRAFT);
                        options.setTooltip(OptionId.CRAFT, "Allows the management of personal storage and blueprints analysis/production");
                        options.setShortcut(OptionId.CRAFT, Keyboard.KEY_C, false, false, false, true);
                    }
		}
		options.addOption("Leave", OptionId.LEAVE);
	}
        
        private void cargoPurchase() {
            if (playerFleet.getCargo().getCredits().get() > 5000f) {
                playerFleet.getCargo().getCredits().subtract(5000);
                addText("You now have your personal storage and blueprints analysis/production allowance");
                UsSData.StationData stationData = (UsSData.StationData) stationsDataMap.get(station.getFullName());
                stationData.privatePersonalStorage = true;
            } else {
                addText("You cannot afford that");
            }
        }
        
        private void createBasicCraftOptions() {
		options.clearOptions();	
                
                privatestash = (CargoAPI) Global.getSector().getPersistentData().get(station.getFullName() + " UsSprivateStash");
                if (privatestash == null) {
                    privatestash = Global.getFactory().createCargo(false);
                    privatestash.setFreeTransfer(true);
                    privatestash.initMothballedShips("neutral");
                    Global.getSector().getPersistentData().put(station.getFullName() + " UsSprivateStash", privatestash);
//                    privatestash.addItems(CargoAPI.CargoItemType.RESOURCES, "LTscraps", 1000);
//                    privatestash.addItems(CargoAPI.CargoItemType.RESOURCES, "LTarmor", 1000);
//                    privatestash.addItems(CargoAPI.CargoItemType.RESOURCES, "LTengines", 1000);
//                    privatestash.addItems(CargoAPI.CargoItemType.RESOURCES, "LTflux", 1000);
//                    privatestash.addItems(CargoAPI.CargoItemType.RESOURCES, "electronics", 1000);
                }
                stationFM = (UsSFactoryManager) Global.getSector().getPersistentData().get(station.getFullName() + " UsSfactoryManager");
                if (stationFM == null) {
                    stationFM = new UsSFactoryManager();
                    Global.getSector().addScript(stationFM);
                    Global.getSector().getPersistentData().put(station.getFullName() + " UsSfactoryManager", stationFM);
                }
                   
                
                if (!blueprintsDataMap.isEmpty()) {
                    bpToIdentify = 0;
                    for (Iterator iter = blueprintsDataMap.keySet().iterator(); iter.hasNext();) {
                        String factionName = (String) iter.next();
                        UsSData.BpData factionBpData = (UsSData.BpData) blueprintsDataMap.get(factionName);
                        bpToIdentify += factionBpData.bpcount;
                        if (!manuBpList.contains(factionName)) {
                            manuBpList.add(factionName);
                        }
                    }
                    if (bpToIdentify > 0) addText("You have " + bpToIdentify + " unidentified Blueprints");
                }
                
                options.addOption("Visit your personal storage" , OptionId.DEPOT, Color.WHITE, "Shows personal storage on this station");   
                if (privatestash.getMothballedShips().getMembersListCopy().size() > 0) options.addOption("Retrieve completed ships/wings" , OptionId.RETRIEVE, Color.WHITE, "Let you take any ship you produced on this station");  
                visual.showImagePortion("illustrations", "cargo_loading", 400, 400, 0, 0, 400, 400);
                if (bpToIdentify > 0) options.addOption("Identify new [" + bpToIdentify + "] blueprints", OptionId.IDENTIFY, Color.YELLOW, "Opens the blueprint identification menu");
                if (manuBpList.size() > 0) options.addOption("Browse manufacturer's blueprints [ " + manuBpList.get((int) (options.getSelectorValue(OptionId.SELECTOR_MANU) + 0.5f)) + " ]" , OptionId.SHOW_BPS, Color.GREEN, "Shows current manufacturer blueprints");
//                options.addOption("Start building a " + member.getVariant().getFullDesignationWithHullName(), OptionId.SHOW_BP, "Begin the construction of a " + member.getShipName() + ".");
//                options.addOption("Next blueprint", OptionId.SHOW_BP, Color.CYAN, "Shows " + member.getShipName() + " stats");
                if (manuBpList.size() > 1) options.addOption("Select another manufacturer", OptionId.MANU_SELECT, Color.CYAN, "Shows all manufacturers you have blueprints for");
		options.addOption("Leave", OptionId.INIT_NO_TEXT);
	}
        
        private void createProductionPickDialogue() {
            UsSData.BpData factionBpData = (UsSData.BpData) blueprintsDataMap.get(manuBpList.get((int) (options.getSelectorValue(OptionId.SELECTOR_MANU)  + 0.5f)));
            FleetMemberAPI member;
            CampaignFleetAPI tempFleet = Global.getSector().createFleet("pirates", "scout");
            tempFleet.getFleetData().removeFleetMember(tempFleet.getFleetData().getMembersListCopy().get(0));
            for (Iterator it = factionBpData.knownBps.iterator(); it.hasNext();) {
                String variantId = (String) it.next();
                if (variantId.endsWith("_wing")) {
                    member = Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, variantId);
                } else {
                    member = Global.getFactory().createFleetMember(FleetMemberType.SHIP, variantId);
                }
                tempFleet.getCargo().addCrew(CargoAPI.CrewXPLevel.GREEN, (int) (member.getMaxCrew()));                
                member.getStatus().repairFully();
                float max = member.getRepairTracker().getMaxCR();
                float curr = member.getRepairTracker().getBaseCR();
                if (max > curr) {
                    member.getRepairTracker().applyCREvent(max - curr, "Repaired at station");
                }
                tempFleet.getFleetData().addFleetMember(member);
            }
            members = tempFleet.getFleetData().getMembersListCopy();
            
//          for (FleetMemberAPI member : playerFleet.getFleetData().getMembersListCopy()) {
//                  if (member.isFighterWing()) continue;
//                  //if (!member.canBeDeployedForCombat()) continue;
//                  members.add(member);
//          }
            
            if (!members.isEmpty()) {
                dialog.showFleetMemberPickerDialog("Select a ship/wing to analyze", "Ok", "Cancel", 
                                3, 7, 58f, true, false, members,
                new FleetMemberPickerListener() {
                    public void pickedFleetMembers(List<FleetMemberAPI> members) {
                        if (members != null && !members.isEmpty()) {
                            picked = members.get(0);
                            shipAnalysis();
                        }
                    }
                    public void cancelledFleetMemberPicking() {
                        addText("No blueprint was selected!");
                        createBasicCraftOptions();
                    }
                });
            }
        }
        
        private void createRetrievePickDialogue() {
            privatestash.getMothballedShips().getMembersListCopy();
            CampaignFleetAPI tempFleet = Global.getSector().createFleet("pirates", "scout");
            tempFleet.getFleetData().removeFleetMember(tempFleet.getFleetData().getMembersListCopy().get(0));
            for (FleetMemberAPI member : privatestash.getMothballedShips().getMembersListCopy()) {
                tempFleet.getFleetData().addFleetMember(member);
                member.getRepairTracker().setMothballed(false);
                tempFleet.getCargo().addCrew(CargoAPI.CrewXPLevel.GREEN, (int) (member.getMaxCrew()));        
                member.getStatus().repairFully();
                float max = member.getRepairTracker().getMaxCR();
                float curr = member.getRepairTracker().getBaseCR();
                if (max > curr) {
                    member.getRepairTracker().applyCREvent(max - curr, "Repaired at station");
                }
            }
            members = tempFleet.getFleetData().getMembersListCopy();
            
            if (!members.isEmpty()) {
                dialog.showFleetMemberPickerDialog("Select ships/wings to launch", "Ok", "Cancel", 
                                3, 7, 58f, true, true, members,
                new FleetMemberPickerListener() {
                    public void pickedFleetMembers(List<FleetMemberAPI> members) {
                        if (members != null && !members.isEmpty()) {
                            for (FleetMemberAPI member : members) {
                                privatestash.getMothballedShips().removeFleetMember(member);
                                playerFleet.getFleetData().addFleetMember(member);
                                member.getStatus().repairFully();
                                float max = member.getRepairTracker().getMaxCR();
                                float curr = member.getRepairTracker().getBaseCR();
                                if (max > curr) {
                                    member.getRepairTracker().applyCREvent(max - curr, "Repaired at station");
                                }
                            }
                            createBasicCraftOptions();
                        }
                    }
                    public void cancelledFleetMemberPicking() {
                        addText("No ships/wings were retrieved!");
                        createBasicCraftOptions();
                    }
                });
            }
        }
        
        private void shipAnalysis() {
            options.clearOptions();	
            addText(UsSUtils.shipCost(picked));
            visual.showFleetMemberInfo(picked);
            options.addOption("Produce the ship/wing", OptionId.PRODUCTION, Color.YELLOW, "Consume the required resources from your storage on the station to produce the ship/wing, if they are available");
            options.addOption("Back", OptionId.CRAFT);
        }
        
        private void identifyMenu() {
            options.clearOptions();	
            identifyShip();
            String txt = "Unidentified blueprints remaining:";
                for (Iterator iter = blueprintsDataMap.keySet().iterator(); iter.hasNext();) {
                    String factionName = (String) iter.next();
                    UsSData.BpData factionBpData = (UsSData.BpData) blueprintsDataMap.get(factionName);
                    if (factionBpData.bpcount > 0) txt += " " + factionBpData.bpcount + " " + factionName + "'s,";
                }
                if (bpToIdentify == 0) txt += " None.";
                String replacetxt = txt.substring(0, txt.length() - 1);
                addText(replacetxt);
            if (bpToIdentify > 0) options.addOption("Identify another blueprint", OptionId.IDENTIFY, Color.YELLOW, "Identify another unidentified blueprint");
            options.addOption("Back", OptionId.CRAFT);
        }
         
        private void identifyShip() {
                for (Iterator iter = blueprintsDataMap.keySet().iterator(); iter.hasNext();) {
                    String factionName = (String) iter.next();
                    UsSData.BpData factionBpData = (UsSData.BpData) blueprintsDataMap.get(factionName);
                    if (factionBpData.bpcount > 0) {
                        String shipId = null;
                        FleetMemberAPI show = null;
                        List list = new ArrayList();
                        UsSDataGrabber.setSource(factionBpData.factionId);
                        if (factionBpData.frigates != 0) list.add("frigates");
                        if (factionBpData.destroyers != 0) list.add("destroyers");
                        if (factionBpData.cruisers != 0) list.add("cruisers");
                        if (factionBpData.capitals != 0) list.add("capitals");
                        if (factionBpData.wings != 0) list.add("wings");
                        String result = UsSUtils.getRandomListMember(list);
                        if (result.equals("frigates")) {
                            shipId = UsSUtils.getRandomMapMember(UsSDataGrabber.frigates);
                            show = Global.getFactory().createFleetMember(FleetMemberType.SHIP, shipId);
                            factionBpData.frigates -= 1;
                            dialog.flickerStatic(0f, 0.5f);
                        } else
                        if (result.equals("destroyers")) {
                            shipId = UsSUtils.getRandomMapMember(UsSDataGrabber.destroyers);
                            show = Global.getFactory().createFleetMember(FleetMemberType.SHIP, shipId);
                            factionBpData.destroyers -= 1;
                            dialog.flickerStatic(0f, 1f);
                        } else
                        if (result.equals("cruisers")) {
                            shipId = UsSUtils.getRandomMapMember(UsSDataGrabber.cruisers);
                            show = Global.getFactory().createFleetMember(FleetMemberType.SHIP, shipId);
                            factionBpData.cruisers -= 1;
                            dialog.flickerStatic(0f, 2f);
                        } else
                        if (result.equals("capitals")) {
                            shipId = UsSUtils.getRandomMapMember(UsSDataGrabber.capitals);
                            show = Global.getFactory().createFleetMember(FleetMemberType.SHIP, shipId);
                            factionBpData.capitals -= 1;
                            dialog.flickerStatic(0f, 3f);
                        } else
                        if (result.equals("wings")) {
                            shipId = UsSUtils.getRandomMapMember(UsSDataGrabber.wings);
                            show = Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, shipId);
                            factionBpData.wings -= 1;
                            dialog.flickerStatic(0f, 0.5f);
                        }
                        visual.showFleetMemberInfo(show);
                        if (factionBpData.knownBps.contains(shipId)) {
                            int sellCredits = show.getFleetPointCost() * 2000;
                            addText("You already possess the " + show.getVariant().getFullDesignationWithHullName() + " blueprint, you manage to sell it for " + sellCredits + " credits on the black market");
                            playerFleet.getCargo().getCredits().add(sellCredits);
                        } else {
                            factionBpData.knownBps.add(shipId);
                        }
                        
                        factionBpData.bpcount -= 1;
                        bpToIdentify -= 1;
                        return;
                    }
                }
        }
        
        private void manuSelect() {
            options.clearOptions();	
            addText("Choose a manufacturer selecting:");
            for(int i = 0; i < manuBpList.size(); i++) {
            addText("" + (i) + ". for " + manuBpList.get(i));
            }      
            
            options.addSelector("Select manufacturer", OptionId.SELECTOR_MANU, Color.MAGENTA, 300, 50, 0f, (float) (manuBpList.size() - 1f), ValueDisplayMode.VALUE, "Lets you choose one of the possible manufacturers from the list in the dialogue window");
            visual.showImagePortion("illustrations", "cargo_loading", 400, 400, 0, 0, 400, 400);
            options.addOption("Done", OptionId.CRAFT);
	}
        
        private void factoryManager() {
            if (UsSUtils.doHaveEnoughResourcesForProduction(picked, privatestash) == true) {
                if (stationFM.isProducing()) {
                    addText("Error! You have the required resources to produce the ship/wing but the factory is already producing a " + picked.getVariant().getFullDesignationWithHullName() + ".");
                } else {
                    UsSUtils.BuildShip(picked,privatestash);
                    stationFM.privatestash = privatestash;
                    stationFM.station = station;
                    stationFM.productionTimeLength = ((int) picked.getFleetPointCost()/3);
                    stationFM.shipId = picked.getSpecId();
                    addText("You have the required resources to produce the ship/wing, the factory will start the building a new " + picked.getVariant().getFullDesignationWithHullName() + " as soon as you leave the station");
                }
            } else {
                addText("Error! You do not have the required amount of resources in your storage!");
            }
        }
                
	private OptionId lastOptionMousedOver = null;
	public void optionMousedOver(String optionText, Object optionData) {

	}
	
	public void advance(float amount) {
		
	}
	
	private void addText(String text) {
		textPanel.addParagraph(text);
	}
	
	private void appendText(String text) {
		textPanel.appendToLastParagraph(" " + text);
	}
	
	private String getString(String id) {
		String str = Global.getSettings().getString("stationInteractionDialog", id);

		String fleetOrShip = "fleet";
		if (playerFleet.getFleetData().getMembersListCopy().size() == 1) {
			fleetOrShip = "ship";
			if (playerFleet.getFleetData().getMembersListCopy().get(0).isFighterWing()) {
				fleetOrShip = "fighter wing";
			}
		}
		str = str.replaceAll("\\$fleetOrShip", fleetOrShip);
		str = str.replaceAll("\\$stationName", station.getFullName());
		
		float needed = playerFleet.getLogistics().getTotalRepairSupplyCost();
		float supplies = playerFleet.getCargo().getSupplies();
		str = str.replaceAll("\\$supplies", "" + (int) supplies);
		str = str.replaceAll("\\$repairSupplyCost", "" + (int) needed);

		return str;
	}
	

	public Object getContext() {
		return null;
	}

	public void coreUIDismissed() {
		optionSelected(null, OptionId.INIT_NO_TEXT);
	}
}



