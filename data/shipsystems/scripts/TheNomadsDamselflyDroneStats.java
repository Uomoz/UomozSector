package data.shipsystems.scripts;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;

public class TheNomadsDamselflyDroneStats implements ShipSystemStatsScript
{
	public static final float ENGINE_TOPSPEED_PERCENT = 10f;
	
	public void apply(MutableShipStatsAPI stats, String id, ShipSystemStatsScript.State state, float effectLevel)
	{
		float engineTopSpeedPercent = ENGINE_TOPSPEED_PERCENT * effectLevel;
		
		stats.getMaxSpeed().modifyPercent(id, engineTopSpeedPercent);
	}
	public void unapply(MutableShipStatsAPI stats, String id)
	{
		stats.getMaxSpeed().unmodify(id);
	}
	
	public ShipSystemStatsScript.StatusData getStatusData(int index, ShipSystemStatsScript.State state, float effectLevel)
	{
		float engineTopSpeedPercent = ENGINE_TOPSPEED_PERCENT * effectLevel;
		if (index == 0) {
			return new ShipSystemStatsScript.StatusData("max speed +" + (int) engineTopSpeedPercent + "%", false);
		} else if (index == 1) {
			return null;
		} else if (index == 2) {
			return new ShipSystemStatsScript.StatusData("max speed +" + (int) engineTopSpeedPercent + "%", false);
		}
		return null;
	}
}
