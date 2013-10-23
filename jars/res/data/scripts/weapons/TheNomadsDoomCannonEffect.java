package data.scripts.weapons;

import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.combat.DamagingProjectileAPI;
import com.fs.starfarer.api.combat.EveryFrameWeaponEffectPlugin;
import com.fs.starfarer.api.combat.MissileAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import java.util.Iterator;
import java.util.List;
import org.lwjgl.util.vector.Vector2f;

public class TheNomadsDoomCannonEffect implements EveryFrameWeaponEffectPlugin
{
	private static final float MISSILE_DESTRUCTION_RADIUS = 30.0f;
	
	public void advance( float amount, CombatEngineAPI engine, WeaponAPI weapon )
	{
		if( engine.isPaused() )
			return;
		List projectiles = engine.getProjectiles();
		List missiles = engine.getMissiles();
		Vector2f result = new Vector2f();
		for( Iterator p = projectiles.iterator(); p.hasNext(); )
		{
			DamagingProjectileAPI proj = (DamagingProjectileAPI)p.next();
			if( ! "nom_doom_cannon_shot".equals( proj.getProjectileSpecId() ))
				continue;
			Vector2f p_loc = proj.getLocation();
			for( Iterator m = missiles.iterator(); m.hasNext(); )
			{
				MissileAPI missile = (MissileAPI)m.next();
				Vector2f m_loc = missile.getLocation();
				Vector2f.sub( m_loc, p_loc, result );
				float distance = result.length();
				if( distance < MISSILE_DESTRUCTION_RADIUS )
					engine.applyDamage( missile, m_loc, 99999.0f, DamageType.ENERGY, 0.0f, true, false, null );
			}
		}
	}
	
}
