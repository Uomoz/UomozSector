package data.scripts.uss;

import com.fs.starfarer.api.PluginPick;
import com.fs.starfarer.api.campaign.BaseCampaignPlugin;
import com.fs.starfarer.api.campaign.BattleAutoresolverPlugin;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.OrbitalStationAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import data.scripts.UsSBattleAutoresolverPluginImpl;
import data.scripts.UsSFleetInteractionDialogPluginImpl;
import data.scripts.UsSOrbitalStationInteractionDialogPluginImpl;

public class UsSCoreCampaignPlugin extends BaseCampaignPlugin {

    @Override
	public String getId() {
		return null;
	}
	
    @Override
	public boolean isTransient() {
		return false;
	}

    @Override
	public PluginPick<InteractionDialogPlugin> pickInteractionDialogPlugin(SectorEntityToken interactionTarget) {
		if (interactionTarget instanceof CampaignFleetAPI) {
			return new PluginPick<InteractionDialogPlugin>(new UsSFleetInteractionDialogPluginImpl(), PickPriority.MOD_GENERAL);
		}
		if (interactionTarget instanceof OrbitalStationAPI) {
			return new PluginPick<InteractionDialogPlugin>(new UsSOrbitalStationInteractionDialogPluginImpl(), PickPriority.MOD_GENERAL);
		}
		return null;
	}
	
	
	public PluginPick<BattleAutoresolverPlugin> pickBattleAutoresolverPlugin(SectorEntityToken one, SectorEntityToken two) {
		if (one instanceof CampaignFleetAPI && two instanceof CampaignFleetAPI) {
			return new PluginPick<BattleAutoresolverPlugin>(
							new UsSBattleAutoresolverPluginImpl((CampaignFleetAPI) one, (CampaignFleetAPI) two),
							PickPriority.MOD_GENERAL
					   );
		}
		return null;
	}
}




