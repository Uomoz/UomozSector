package data.scripts.world.armada.api;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;

public interface CampaignArmadaAPI
{
	CampaignFleetAPI getLeaderFleet();
	CampaignFleetAPI[] getEscortFleets();
	
	CampaignArmadaEscortFleetPositionerAPI getEscortFleetPositioner();
}

