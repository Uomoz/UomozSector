package data.scripts.weapons;

import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.EveryFrameWeaponEffectPlugin;
import com.fs.starfarer.api.combat.WeaponAPI;
import java.awt.Color;
import org.lazywizard.lazylib.MathUtils;
import org.lwjgl.util.vector.Vector2f;

public class AMLanceChargeupEffect implements EveryFrameWeaponEffectPlugin
{
    // How far behind the weapon the backblast will spawn
    private static final float BACKBLAST_OFFSET = 1f;
    // The color of the backblast itself
    private static final Color BACKBLAST_EXPLOSION_COLOR = new Color(201,204,247,100);
    // The size of the backblast
    private static final float BACKBLAST_EXPLOSION_SIZE = 5f;
    // The color of the backblast's particle effects
    private static final Color BACKBLAST_PARTICLE_COLOR = new Color(211,255,255,100);
    // The size of the backblast's particle effects
    private static final float BACKBLAST_PARTICLE_SIZE = 5f;
    // The minimum speed of the backblast's particle effects
    private static final float BACKBLAST_PARTICLE_MIN_SPEED = 50f;
    // The maximum speed of the backblast's particle effects
    private static final float BACKBLAST_PARTICLE_MAX_SPEED = 150f;
    // How many particles will be spawned per backblast
    private static final int NUM_BACKBLAST_PARTICLES = 30;
    // Needed so there aren't multiple backblasts per shot
    private boolean isNewShot = true;

    @Override
    public void advance(float amount, CombatEngineAPI engine, WeaponAPI weapon)
    {
        // Don't bother with any checks if the game is paused
        if (engine.isPaused())
        {
            return;
        }

        // The first frame the weapon is firing, spawn the backblast
        if (weapon.isFiring() && isNewShot)
        {
            // Only one backblast (needed because cooldown counts as firing)
            isNewShot = false;

            // Find location where the backblast should spawn relative to weapon
            Vector2f offset = MathUtils.getPointOnCircumference(
                    weapon.getLocation(), BACKBLAST_OFFSET,
                    weapon.getShip().getFacing() + 1f);
            // Spawn the backblast explosion effect
            engine.spawnExplosion(offset, weapon.getShip().getVelocity(),
                    BACKBLAST_EXPLOSION_COLOR, BACKBLAST_EXPLOSION_SIZE, .8f);

            // Create the particle effects
            float speed, angle;
            Vector2f velocity;
            for (int x = 0; x < NUM_BACKBLAST_PARTICLES; x++)
            {
                // Randomize speed, angle, and velocity of each particle
                speed = MathUtils.getRandomNumberInRange(
                        BACKBLAST_PARTICLE_MIN_SPEED,
                        BACKBLAST_PARTICLE_MAX_SPEED);
                angle = weapon.getShip().getFacing()
                        + MathUtils.getRandomNumberInRange(160f, 200f);
                velocity = MathUtils.getPointOnCircumference(
                        weapon.getShip().getVelocity(), speed, angle);
                // Spawn the particle
                engine.addHitParticle(offset, velocity, BACKBLAST_PARTICLE_SIZE,
                        1f, .5f, BACKBLAST_PARTICLE_COLOR);
            }
        }
        // Check if the weapon is no longer firing
        else if (!weapon.isFiring())
        {
            isNewShot = true;
        }
    }
}
