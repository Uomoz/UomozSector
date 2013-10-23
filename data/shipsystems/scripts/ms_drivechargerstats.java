package data.shipsystems.scripts;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;

public class ms_drivechargerstats implements ShipSystemStatsScript {

    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
		if (state == ShipSystemStatsScript.State.OUT) {
			stats.getMaxSpeed().modifyPercent(id, 200f * effectLevel); // give the ship a big speed boost while releasing the excess drive plasma
			stats.getMaxTurnRate().unmodify(id);
		} else {
			stats.getMaxSpeed().modifyFlat(id, 100f * effectLevel);
			stats.getAcceleration().modifyFlat(id, 200f * effectLevel);
			stats.getDeceleration().modifyFlat(id, 90f * effectLevel);
			stats.getTurnAcceleration().modifyFlat(id, 45f * effectLevel);
			stats.getTurnAcceleration().modifyPercent(id, 200f * effectLevel);
			stats.getMaxTurnRate().modifyFlat(id, 20f * effectLevel);
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
			return new StatusData("lateral thrusters online", false);
		} else if (index == 1) {
			return new StatusData("releasing stored drive plasma", false);
		}
		return null;
	}
}
