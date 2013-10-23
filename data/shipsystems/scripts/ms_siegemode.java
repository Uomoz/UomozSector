package data.shipsystems.scripts;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.CombatEntityAPI;
import com.fs.starfarer.api.combat.ShieldAPI;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;

public class ms_siegemode implements ShipSystemStatsScript {
	private static final float RANGE_BONUS = 200f;
	private static final float COST_BONUS = -20f;
	private static final float SHIELD_BONUS = 10f;

    @Override
	public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
		CombatEntityAPI entity = stats.getEntity();
		if( (entity == null) || ! (entity instanceof ShipAPI) ) return;
		ShipAPI ship = (ShipAPI)entity;

		// boost turn rate - no need to fiddle with acceleration due to low top speed.
		stats.getTurnAcceleration().modifyFlat(id, 45f * effectLevel);
		stats.getMaxTurnRate().modifyFlat(id, 30f);
		
		stats.getEnergyWeaponRangeBonus().modifyFlat( id, RANGE_BONUS * effectLevel );
		stats.getEnergyWeaponFluxCostMod().modifyPercent( id, COST_BONUS * effectLevel );

		stats.getMaxSpeed().modifyPercent(id, -80f * effectLevel);
		stats.getZeroFluxSpeedBoost().modifyFlat(id, -50f * effectLevel); // this very quietly mostly-disables the zero flux speed boost.

		stats.getShieldAbsorptionMult().modifyPercent(id, -1 * effectLevel * SHIELD_BONUS );
	}
    @Override
	public void unapply(MutableShipStatsAPI stats, String id) {
		//ShipAPI ship = CombatUtils.getOwner( stats );
		stats.getMaxSpeed().unmodify(id);
		stats.getMaxTurnRate().unmodify(id);
		stats.getTurnAcceleration().unmodify(id);
		stats.getEnergyWeaponRangeBonus().unmodify(id);
		stats.getZeroFluxSpeedBoost().unmodify(id);
		stats.getShieldAbsorptionMult().unmodify(id);
	}
	
    @Override
	public StatusData getStatusData(int index, State state, float effectLevel) {
		if( index == 0 ) {
			return new StatusData( "engine power redirected", false);
		} else if( index == 1 ) {
			return new StatusData( "energy weapon range +" + (int)( RANGE_BONUS * effectLevel ), false );
		} else if( index == 2 ) {
			return new StatusData( "energy weapon flux costs " + (int)( COST_BONUS * effectLevel) + "%", false );
		} else if( index == 3 ) {
			return new StatusData( "damage to shields reduced by " + (int)( SHIELD_BONUS * effectLevel ) + "%", false );
		}
		return null;
	}
}
