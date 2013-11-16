package data.shipsystems.scripts;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipEngineControllerAPI.ShipEngineAPI;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;
import java.util.Iterator;

public class khs_wdrstats implements ShipSystemStatsScript {

	public static final float ROF_BONUS = 4f;
	public static final float FLUX_COST = 0.25f;
	private int first = 0;
	
	public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
		ShipAPI ship = (ShipAPI) stats.getEntity();
        if (ship == null)
        {
            return;
        }
		float mult = ROF_BONUS * effectLevel;
		float mult2 = FLUX_COST * effectLevel;
		stats.getBallisticRoFMult().modifyMult(id, mult);
		stats.getEnergyRoFMult().modifyMult(id, mult);
		stats.getMissileRoFMult().modifyMult(id, mult);
		stats.getBallisticWeaponFluxCostMod().modifyMult(id, mult2);
		stats.getEnergyWeaponFluxCostMod().modifyMult(id, mult2);
		stats.getMissileWeaponFluxCostMod().modifyMult(id, mult2);
		for (Iterator engines = ship.getEngineController().getShipEngines().iterator(); engines.hasNext();)
			{
			ShipEngineAPI toDisable2 = (ShipEngineAPI) engines.next();
			toDisable2.disable();
			}
	}
	public void unapply(MutableShipStatsAPI stats, String id) {
		ShipAPI ship = (ShipAPI) stats.getEntity();
        if (ship == null)
        {
            return;
        }
		stats.getBallisticRoFMult().unmodify(id);
		stats.getMissileRoFMult().unmodify(id);
		stats.getEnergyRoFMult().unmodify(id);
		stats.getBallisticWeaponFluxCostMod().unmodify(id);
		stats.getEnergyWeaponFluxCostMod().unmodify(id);
		stats.getMissileWeaponFluxCostMod().unmodify(id);
		stats.getEnergyWeaponFluxCostMod().unmodify(id);
		stats.getMissileWeaponFluxCostMod().unmodify(id);
		if (first > 0)
		{
		ship.getFluxTracker().forceOverload(1f);
			// for (Iterator weapons = ship.getAllWeapons().iterator(); weapons.hasNext();)
			// 		{
			// 			WeaponAPI toDisable = (WeaponAPI) weapons.next();
			// 			toDisable.disable();
			// 		}
		} else {
		first++;
		}
	}
	
	public StatusData getStatusData(int index, State state, float effectLevel) {
		float mult = ROF_BONUS * effectLevel;
		float bonusPercent = (int) mult * 100f;
		float mult2 = FLUX_COST * effectLevel;
		float bonusPercent2 = (int) mult2 * 100f;
		if (index == 0) {
			return new StatusData("rate of fire " + (int) bonusPercent + "%", false);
		}
		if (index == 1) {
			return new StatusData("weapon flux costs lowered", false);
		}
		if (index == 2) {
			return new StatusData("overload imminent", true);
		}
		return null;
	}
}
