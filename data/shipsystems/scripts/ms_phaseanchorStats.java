package data.shipsystems.scripts;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;

public class ms_phaseanchorStats implements ShipSystemStatsScript {

	@Override
        public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
		if (state == ShipSystemStatsScript.State.OUT) {
			stats.getMaxSpeed().unmodify(id); // to slow down ship to its regular top speed while powering drive down
			stats.getMaxTurnRate().unmodify(id);
		} else {
			stats.getAcceleration().modifyFlat(id, 50f * effectLevel);
			stats.getDeceleration().modifyFlat(id, 50f * effectLevel);
			
			stats.getTurnAcceleration().modifyFlat(id, 30f * effectLevel);
			stats.getMaxTurnRate().modifyFlat(id, 30f);
			
			stats.getMaxSpeed().modifyFlat(id, 50f * effectLevel);
		}
	}
        
        @Override
	public void unapply(MutableShipStatsAPI stats, String id) {
		stats.getMaxSpeed().unmodify(id);
		stats.getMaxTurnRate().unmodify(id);
		stats.getTurnAcceleration().unmodify(id);
		stats.getAcceleration().unmodify(id);
		stats.getDeceleration().unmodify(id);
	}
	
        @Override
	public StatusData getStatusData(int index, State state, float effectLevel) {
		if (index == 0) {
			return new StatusData("phase systems online", false);
		}
		return null;
	}
}
