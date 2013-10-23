package data.scripts.weapons;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.EveryFrameWeaponEffectPlugin;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import data.scripts.uss.UsSUtils;
import java.awt.Color;
import java.util.Hashtable;
import org.lwjgl.util.vector.Vector2f;

public class TheNomadsSolarCrucibleChargeupEffect implements EveryFrameWeaponEffectPlugin
{
	private static final float OFFSET = 72f;
    private static final Color MUZZLE_FLASH_COLOR = new Color( 240,98,98, 255 );
    private static final Color PARTICLE_COLOR = new Color( 240,98,98, 120 );
	private static final float FIRING_DURATION = 2.05f; // weapon_data.csv: (chargeup + chargedown)
    
	private Hashtable progress = new Hashtable();
	

    @Override
    public void advance( float amount, CombatEngineAPI engine, WeaponAPI weapon )
    {
        if( engine.isPaused() )
            return;
		////
        if( weapon.isFiring() )
        {
			float elapsed = 0f;
			Float memo = (Float) progress.get( weapon );
			if( memo != null )
				elapsed += memo.floatValue() + amount;
			progress.put( weapon, Float.valueOf( elapsed ));
			
			Vector2f weapon_location = weapon.getLocation();
			ShipAPI ship = weapon.getShip();
			// explosion (frame 0 only)
			if( elapsed == 0.0f )
			{
				Vector2f explosion_offset = UsSUtils.translate_polar( weapon_location, OFFSET + ((0.25f * 150f) - 2f), ship.getFacing() );
				engine.spawnExplosion( explosion_offset, ship.getVelocity(), MUZZLE_FLASH_COLOR, 150f, 0.2f );
			}
            // particles
            Vector2f particle_offset = UsSUtils.translate_polar( weapon_location, OFFSET, ship.getFacing() );
            float size, speed, angle;
            Vector2f velocity;
			// more particles to start with, fewer later on
			int particle_count_this_frame = (int)(2f * (FIRING_DURATION - elapsed));
            for (int x = 0; x < particle_count_this_frame; x++)
            {
				size = UsSUtils.get_random( 1f, 10f );
                speed = UsSUtils.get_random( 200f, 800f );
                angle = ship.getFacing() + UsSUtils.get_random( -20f, 20f );
                velocity = UsSUtils.translate_polar( ship.getVelocity(), speed, angle );
                engine.addHitParticle( particle_offset, velocity, size, 1.0f, 0.25f, PARTICLE_COLOR );
            }
        }
		else // !weapon.isFiring()
            progress.remove( weapon );
    }
}
