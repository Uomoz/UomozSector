package data.shipsystems.scripts;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;

public class PackEngineDivertStats implements ShipSystemStatsScript {

	public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
        stats.getMaxSpeed().modifyMult(id, 1.6f * effectLevel);
        stats.getAcceleration().modifyMult(id, 1.6f * effectLevel);
        stats.getDeceleration().modifyMult(id, 1.6f * effectLevel);
        stats.getTurnAcceleration().modifyMult(id, 1.6f * effectLevel);
        stats.getMaxTurnRate().modifyMult(id, 1.6f * effectLevel);
        //stats.getShieldTurnRateMult().modifyMult(id, 1.2f);
		//stats.getShieldDamageTakenMult().modifyMult(id, 0.6f);
	}
	public void unapply(MutableShipStatsAPI stats, String id) {
		//stats.getShieldTurnRateMult().unmodify(id);
		//stats.getShieldDamageTakenMult().unmodify(id);
		stats.getMaxSpeed().unmodify(id);
		stats.getAcceleration().unmodify(id);
		stats.getDeceleration().unmodify(id);
		stats.getTurnAcceleration().unmodify(id);
		stats.getMaxTurnRate().unmodify(id);
	}
	
	public StatusData getStatusData(int index, State state, float effectLevel) {
		if (index == 0) {
			return new StatusData("systems diverted to engines", false);
		} else if (index == 1) {
			return new StatusData("shield inoperative", false);
		}
//		else if (index == 1) {
//			return new StatusData("shield upkeep reduced to 0", false);
//		} else if (index == 2) {
//			return new StatusData("shield upkeep reduced to 0", false);
//		}
		return null;
	}
}
