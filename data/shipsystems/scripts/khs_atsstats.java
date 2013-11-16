package data.shipsystems.scripts;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;

public class khs_atsstats implements ShipSystemStatsScript {

	public static final float TURN_BONUS = 4f;
	public static final float AFACC = 1f;
	
	public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
		float mult = TURN_BONUS * effectLevel;
		float mult2 = AFACC * effectLevel;
		stats.getWeaponTurnRateBonus().modifyMult(id, mult);
		stats.getAutofireAimAccuracy().modifyFlat(id, mult2);
	}
	public void unapply(MutableShipStatsAPI stats, String id) {
		stats.getWeaponTurnRateBonus().unmodify(id);
		stats.getAutofireAimAccuracy().unmodify(id);
	}
	
	public StatusData getStatusData(int index, State state, float effectLevel) {
		float mult = TURN_BONUS * effectLevel;
		float bonusPercent = (int) mult * 100f;
		float mult2 = AFACC * effectLevel;
		float bonusPercent2 = (int) mult2 * 100f;
		if (index == 0) {
			return new StatusData("turret rotation at " + (int) bonusPercent + "% speed", false);
		}
		if (index == 1) {
			return new StatusData("autofire accuracy increased", false);
		}
		return null;
	}
}
