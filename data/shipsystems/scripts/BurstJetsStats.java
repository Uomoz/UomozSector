package data.shipsystems.scripts;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;

public class BurstJetsStats implements ShipSystemStatsScript {

	public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
		if (state == ShipSystemStatsScript.State.OUT) {
			stats.getMaxSpeed().modifyPercent(id, 100f * effectLevel); // to slow down ship to its regular top speed while powering drive down
			stats.getMaxTurnRate().modifyPercent(id, 100f * effectLevel);
			stats.getDeceleration().modifyPercent(id, 100f * effectLevel);					
		} else {
			stats.getMaxSpeed().modifyFlat(id, 130f * effectLevel);
			stats.getAcceleration().modifyFlat(id, 190f * effectLevel);
			stats.getDeceleration().modifyFlat(id, 120f * effectLevel);
			stats.getTurnAcceleration().modifyFlat(id, 90f * effectLevel);
			stats.getTurnAcceleration().modifyPercent(id, 200f * effectLevel);
			stats.getMaxTurnRate().modifyFlat(id, 40f * effectLevel);
			stats.getMaxTurnRate().modifyPercent(id, 100f * effectLevel);
		}
	}
	public void unapply(MutableShipStatsAPI stats, String id) {
		stats.getMaxSpeed().unmodify(id);
		stats.getMaxTurnRate().unmodify(id);
		stats.getTurnAcceleration().unmodify(id);
		stats.getAcceleration().unmodify(id);
		stats.getDeceleration().unmodify(id);
	}
	
	public StatusData getStatusData(int index, State state, float effectLevel) {
		if (index == 0) {
			return new StatusData("improved maneuverability", false);
		} else if (index == 1) {
			return new StatusData("increased top speed", false);
		}
		return null;
	}
}
