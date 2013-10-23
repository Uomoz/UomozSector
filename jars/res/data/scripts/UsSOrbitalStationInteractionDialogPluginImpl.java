package data.scripts;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CoreInteractionListener;
import com.fs.starfarer.api.campaign.CoreUITabId;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.OptionPanelAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.TextPanelAPI;
import com.fs.starfarer.api.campaign.VisualPanelAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class UsSOrbitalStationInteractionDialogPluginImpl implements InteractionDialogPlugin, CoreInteractionListener {

	private static enum OptionId {
		INIT,
		INIT_NO_TEXT,
		TRADE_CARGO,
		TRADE_SHIPS,
		REFIT,
		REPAIR_ALL,
		LEAVE,
	}
	
	private InteractionDialogAPI dialog;
	private TextPanelAPI textPanel;
	private OptionPanelAPI options;
	private VisualPanelAPI visual;
	
	private CampaignFleetAPI playerFleet;
	private SectorEntityToken station;
        private FactionAPI player;
	
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
                        } else if ((player.getRelationship(station.getFaction().getId()) == 0) && (!station.getFaction().getId().equals("independent"))) {
                            addText("The Station is for faction use only.");
                        } else if ((player.getRelationship(station.getFaction().getId()) == 0) && (station.getFaction().getId().equals("independent"))) {
                            addText(getString("approach"));
                        } else {
                            addText("The Station weapons activate.");
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
		}
		
		options.addOption("Leave", OptionId.LEAVE);
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



