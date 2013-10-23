package data.scripts.plugins;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.EveryFrameWeaponEffectPlugin;
import com.fs.starfarer.api.combat.WeaponAPI;

public class TheNomadsHabitatRingRotationEffect implements EveryFrameWeaponEffectPlugin
{
	private float direction = 1.0f; // always counter-clockwise
	private float degrees_per_second = 2.4f; // 144 degrees per minute, 2.5 minutes per full rotation
	private float angle = 0.0f; // habitat ring is completely independent of ship
	
	public void advance( float amount, CombatEngineAPI engine, WeaponAPI weapon )
	{
		if( engine.isPaused() ) return;
		if( weapon.getShip().isHulk() ) return;
		
		angle = normalizeAngle( angle + (amount * direction * degrees_per_second) );
		// habitat ring rotates at a fixed rate
		weapon.setCurrAngle( angle );
	}
	
	public static float normalizeAngle( float angleDeg )
	{
		return (angleDeg % 360f + 360f) % 360f;
	}
}
