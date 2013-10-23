package data.scripts.weapons;

import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.EveryFrameWeaponEffectPlugin;
import com.fs.starfarer.api.combat.WeaponAPI;
import com.fs.starfarer.api.combat.ShipAPI;

public class AltLensFlareEffect implements EveryFrameWeaponEffectPlugin {

	private float currDir = Math.signum((float) Math.random() - 1f);
	
	public void advance(float amount, CombatEngineAPI engine, WeaponAPI weapon) {
        weapon.getSprite().setAdditiveBlend();		
		if (engine.isPaused()) return;
		if (weapon.getShip().isHulk()) return;
		
		float curr = weapon.getCurrAngle();		
		curr += currDir * amount * 36f;
		float arc = weapon.getArc();		
		weapon.setCurrAngle(curr);	
	}	
	
	public static float normalizeAngle(float angleDeg) {
		return (angleDeg % 360f + 360f) % 360f;
	}
}