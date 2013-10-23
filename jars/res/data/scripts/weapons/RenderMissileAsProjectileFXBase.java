package data.scripts.weapons;

import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.CombatEntityAPI;
import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.combat.EveryFrameWeaponEffectPlugin;
import com.fs.starfarer.api.combat.MissileAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import java.util.Hashtable;
import java.util.Iterator;

public abstract class RenderMissileAsProjectileFXBase implements EveryFrameWeaponEffectPlugin
{
	protected Hashtable fx_table = new Hashtable();
	
	public String FX_WEAPON_ID()
	{
		return null;
	}
	
	public void advance( float amount, CombatEngineAPI engine, WeaponAPI weapon )
	{
		if( engine.isPaused() || FX_WEAPON_ID() == null )
			return;
		
		// for every missile launched from this weapon
		// spawn a special visual-only projectile which follows the missile around
		// and lets me create, essentially, a different-looking missile rendering effect
		// also, kill off fizzled torpedoes because they don't "coast" like regular missiles
		for( Iterator m = engine.getMissiles().iterator(); m.hasNext(); )
		{
			MissileAPI missile = (MissileAPI)m.next();
			if( weapon != missile.getWeapon() )
				continue;
			CombatEntityAPI fx = (CombatEntityAPI)fx_table.get( missile );
			if( fx == null )
			{
				fx = engine.spawnProjectile( 
				  weapon.getShip(), weapon, FX_WEAPON_ID(), 
				  missile.getLocation(), 0, weapon.getShip().getVelocity() );
				fx.setAngularVelocity( -25.0f + (float)Math.random() * 50.0f );
				fx_table.put( missile, fx );
			}
			else // faux_plasma already created
			{
				fx.getLocation().set( missile.getLocation() );
				fx.getVelocity().set( missile.getVelocity() );
			}
		}
		for( Iterator m = fx_table.keySet().iterator(); m.hasNext(); )
		{
			MissileAPI missile = (MissileAPI)m.next();
			CombatEntityAPI fx = (CombatEntityAPI)fx_table.get( missile );
			if( missile.isFizzling() 
			||  missile.isFading() 
			||  missile.getHitpoints() == 0.0f
			||  missile.didDamage()
			||  missile.getHullLevel() == 0.0f )
			{
				if( fx != null )
				{
					engine.applyDamage( fx, fx.getLocation(), 99999.0f, DamageType.ENERGY, 0.0f, true, false, null );
					m.remove();
				}
				engine.removeEntity( missile );
				continue; // skip rest
			}
		}
	}
}
