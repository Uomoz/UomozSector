package data.scripts.plugins;

import com.fs.starfarer.api.AnimationAPI;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.CombatEnginePlugin;
import com.fs.starfarer.api.combat.EveryFrameCombatPlugin;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipEngineControllerAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class TheNomadsRoadrunnerEngineFXPlugin implements CombatEnginePlugin, EveryFrameCombatPlugin
{
	private CombatEngineAPI engine;
	private HashMap tracker = new HashMap();
	private float accumulator = 0.0f;
	private static final float MIN_EXPENSIVE_UPDATE_DELAY_SEC = 1.0f;
	
	private static final float ENGINE_MIN_FPS__FRAME_DELAY = 1f / 10f;
	private static final float ENGINE_MAX_FPS__FRAME_DELAY = 1f / 100f;
	private static final float SPEED_DETECT_MIN = 10f;
	private static final float SPEED_DETECT_MAX = 230f;
	
	////
	
	public class EngineFX
	{
		ShipAPI ship;
		ShipEngineControllerAPI engine;
		AnimationAPI engine_anim;
		float accumulator = 0.0f;
		
		public EngineFX(
		  ShipAPI ship,
		  ShipEngineControllerAPI engine,
		  AnimationAPI engine_anim )
		{
			this.ship = ship;
			this.engine = engine;
			this.engine_anim = engine_anim;
		}

		public void advance( float amount )
		{
			accumulator += amount;
			float frame_delay = ENGINE_MIN_FPS__FRAME_DELAY;
			if( engine.isAccelerating() )
			{
				float speed = ship.getVelocity().length();
				float pct = 0f;
				if( speed >= SPEED_DETECT_MIN && speed <= SPEED_DETECT_MAX  )
					pct = (speed - SPEED_DETECT_MIN) / SPEED_DETECT_MAX;
				else if( speed >= SPEED_DETECT_MIN )
					pct = 1f;
				//else
				//  pct = 0f;
				frame_delay = ENGINE_MAX_FPS__FRAME_DELAY + ((1 - pct) * (ENGINE_MIN_FPS__FRAME_DELAY - ENGINE_MAX_FPS__FRAME_DELAY));
			}
			if( accumulator >= frame_delay )
			{
				accumulator -= frame_delay;
				next_frame();
			}
		}
		
		public void next_frame()
		{
			int f = engine_anim.getFrame() + 1;
			if( f >= engine_anim.getNumFrames() )
				f = 0;
			engine_anim.setFrame( f );
		}
	}
	
	////
	
	public void init( CombatEngineAPI engine )
	{
		this.engine = engine;
	}

	public void advance( float amount, List events )
	{
		if( engine.isPaused() )
			return;
		accumulator += amount;
		if( accumulator < MIN_EXPENSIVE_UPDATE_DELAY_SEC )
		{
			do_cheap_update( amount );
		}
		else // accumulator >= MIN_SEARCH_DELAY_SEC
		{
			accumulator -= MIN_EXPENSIVE_UPDATE_DELAY_SEC;
			do_expensive_update();
			do_cheap_update( amount );
		}
	}
	
	public void do_expensive_update()
	{
		for( Iterator s = engine.getShips().iterator(); s.hasNext(); )
		{
			ShipAPI ship = (ShipAPI) s.next();
			if( tracker.containsKey( ship ))
				continue; // no need to re-add
			if( ship == null || ship.isHulk() || !"nom_roadrunner".equals( ship.getHullSpec().getHullId() ))
				continue;
			WeaponAPI engine_fx = get_weapon_by_slot_name( ship, "engine_fx" );
			if( engine_fx == null )
				continue;
			AnimationAPI anim = engine_fx.getAnimation();
			if( anim == null )
				continue;
			tracker.put( ship, new EngineFX( ship, ship.getEngineController(), anim ));
		}
	}
	
	public void do_cheap_update( float amount )
	{
		for( Iterator e = tracker.entrySet().iterator(); e.hasNext(); )
		{
			Entry entry = (Entry) e.next();
			ShipAPI ship = (ShipAPI) entry.getKey();
			if( ship.isHulk() )
			{
				e.remove();
				continue;
			}
			EngineFX fx = (EngineFX) entry.getValue();
			fx.advance( amount );
		}
	}
	
	public WeaponAPI get_weapon_by_slot_name( ShipAPI ship, String slot_name )
	{
		for( Iterator w = ship.getAllWeapons().iterator(); w.hasNext(); )
		{
			WeaponAPI weapon = (WeaponAPI) w.next();
			if( slot_name.equals( weapon.getSlot().getId() ))
			{
				return weapon;
			}
		}
		return null;
	}
}
