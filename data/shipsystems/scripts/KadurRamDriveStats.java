package data.shipsystems.scripts;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipEngineControllerAPI.ShipEngineAPI;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;
import java.util.Iterator;

public class KadurRamDriveStats implements ShipSystemStatsScript {

	private int first = 0;

	public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
		if (state == ShipSystemStatsScript.State.OUT) {
			stats.getMaxSpeed().unmodify(id); // to slow down ship to its regular top speed while powering drive down
			stats.getAcceleration().unmodify(id);
			stats.getArmorDamageTakenMult().unmodify(id);
		} else {
			stats.getMaxSpeed().modifyFlat(id, 1750f * effectLevel);
			stats.getAcceleration().modifyFlat(id, 1750f * effectLevel);
			stats.getArmorDamageTakenMult().modifyMult(id, 0.2f);
		}
	}
	public void unapply(MutableShipStatsAPI stats, String id) {
		ShipAPI ship = (ShipAPI) stats.getEntity();
        if (ship == null)
        {
            return;
        }
		stats.getMaxSpeed().unmodify(id);
		stats.getMaxTurnRate().unmodify(id);
		stats.getTurnAcceleration().unmodify(id);
		stats.getAcceleration().unmodify(id);
		stats.getDeceleration().unmodify(id);
		stats.getArmorDamageTakenMult().unmodify(id);
		if (first > 0)
		{
		ship.getFluxTracker().forceOverload(1f);
		for (Iterator engines = ship.getEngineController().getShipEngines().iterator(); engines.hasNext();)
			{
			ShipEngineAPI toDisable2 = (ShipEngineAPI) engines.next();
			toDisable2.disable();
			}
		} else {
		first++;
		}
	}
	
	public StatusData getStatusData(int index, State state, float effectLevel) {
		if (index == 0) {
			return new StatusData("damn the torpedoes, full speed ahead!", false);
		}
		return null;
	}
}
