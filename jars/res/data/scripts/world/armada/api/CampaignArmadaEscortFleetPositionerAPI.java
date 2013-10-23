package data.scripts.world.armada.api;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;

//   "ORBIT"  params: orbit radius, orbit direction [CCW/CW], orbit period (days)
//   "COLUMN" params: separation distance, rank width number (width constant, files grow with number of escort fleets)
//   "LINE"   params: separation distance, number of files (number of files constant, rank width grows with number of escort fleets)
//   "SQUARE" params: separation distance
//   "WEDGE"  params: separation distance, offset angle
//   "ECHELON"params: separation distance, offset angle, variant [right/left]
//   "VEE"    params: separation distance, offset angle

public interface CampaignArmadaEscortFleetPositionerAPI
{
	void set_armada( CampaignArmadaAPI armada );
	void update_escort_fleet_positions( float amount );
}
