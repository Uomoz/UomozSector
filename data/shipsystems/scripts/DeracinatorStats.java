package data.shipsystems.scripts;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.CombatEntityAPI;
import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;
import java.awt.Color;
import java.util.Iterator;
import org.apache.log4j.Level;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.combat.CombatUtils;
import org.lwjgl.util.vector.Vector2f;

// Phase teleporter system, with some extras
public class DeracinatorStats implements ShipSystemStatsScript

{
    // "Inhale" effect constants
    private static final int MAX_PARTICLES_PER_FRAME = 7; // Based on charge level
    private static final float PARTICLE_RADIUS = 300f;
    private static final float PARTICLE_SIZE = 6f;
    private static final Color PARTICLE_COLOR = new Color(155, 240, 200);
    private static final float PARTICLE_OPACITY = 0.85f;
    private static final String CHARGEUP_SOUND = "system_deracinatorcharge";	
    // Explosion effect constants
    private static final Color EXPLOSION_COLOR = new Color(55, 160, 88);
    private static final float EXPLOSION_RADIUS = 1200f;
    private static final float EXPLOSION_DAMAGE_AMOUNT = 550f;
    private static final DamageType EXPLOSION_DAMAGE_TYPE = DamageType.ENERGY;
    private static final float EXPLOSION_PUSH_RADIUS = 1200f;
    private static final float EXPLOSION_PUSH_FORCE = 1900f;
    private static final float STRENGTH_VS_FIGHTER = 1.3f;
    private static final float STRENGTH_VS_FRIGATE = 1f;
    private static final float STRENGTH_VS_DESTROYER = .9f;
    private static final float STRENGTH_VS_CRUISER = .7f;
    private static final float STRENGTH_VS_CAPITAL = .4f;
    private static final String EXPLOSION_SOUND = "luciferdriveactivate";	
    // Local variables, don't touch these
    private boolean isActive = false;

    @Override
    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel)
    {
        // instanceof also acts as a null check
        if (!(stats.getEntity() instanceof ShipAPI))
        {
            return;
        }

        ShipAPI ship = (ShipAPI) stats.getEntity();
        // Chargeup, show particle inhalation effect
        if (state == State.IN)
        {
            // Everything in this block is only done once per chargeup
            if (!isActive)
            {
                Global.getLogger(DeracinatorStats.class).log(Level.INFO,
                        "Started State.IN");
                isActive = true;
            Global.getSoundPlayer().playSound(CHARGEUP_SOUND, 1f, 1f, ship.getLocation(), ship.getVelocity());					
            }		

            // Exact amount per second doesn't matter since it's purely decorative
            Vector2f particlePos, particleVel;
            int numParticlesThisFrame = Math.round(effectLevel * MAX_PARTICLES_PER_FRAME);
            for (int x = 0; x < numParticlesThisFrame; x++)
            {
                particlePos = MathUtils.getRandomPointOnCircumference(
                        ship.getLocation(), PARTICLE_RADIUS);
                particleVel = Vector2f.sub(ship.getLocation(), particlePos, null);
                Global.getCombatEngine().addSmokeParticle(
                        particlePos, particleVel, PARTICLE_SIZE,
                        PARTICLE_OPACITY, 1f, PARTICLE_COLOR);
            }
        }
        // Cooldown, explode once system is finished
        else if (state == State.OUT)
        {
            // Everything in this section is only done once per cooldown
            if (isActive)
            {
                CombatEngineAPI engine = Global.getCombatEngine();
                engine.spawnExplosion(ship.getLocation(), ship.getVelocity(),
                        EXPLOSION_COLOR, EXPLOSION_RADIUS, 1f);
                engine.spawnExplosion(ship.getLocation(), ship.getVelocity(),
                        EXPLOSION_COLOR, EXPLOSION_RADIUS / 2f, 1f);

				Global.getSoundPlayer().playSound(EXPLOSION_SOUND, 1f, 1f, ship.getLocation(), ship.getVelocity());									

                CombatEntityAPI tmp;
                Vector2f dir;
                float force, damage, mod;
                for (Iterator pushed = CombatUtils.getEntitiesWithinRange(
                        ship.getLocation(), EXPLOSION_PUSH_RADIUS).iterator(); pushed.hasNext();)
                {
                    tmp = (CombatEntityAPI) pushed.next();

                    if (tmp == ship)
                    {
                        continue;
                    }

                    mod = 1f - (MathUtils.getDistance(ship, tmp) / EXPLOSION_PUSH_RADIUS);
                    force = EXPLOSION_PUSH_FORCE * mod;
                    damage = EXPLOSION_DAMAGE_AMOUNT * mod;

                    if (tmp instanceof ShipAPI)
                    {
                        ship = (ShipAPI) tmp;

                        // Modify push strength based on ship class
                        if (ship.getHullSize() == ShipAPI.HullSize.FIGHTER)
                        {
                            force *= STRENGTH_VS_FIGHTER;
                            damage /= STRENGTH_VS_FIGHTER;
                        }
                        else if (ship.getHullSize() == ShipAPI.HullSize.FRIGATE)
                        {
                            force *= STRENGTH_VS_FRIGATE;
                            damage /= STRENGTH_VS_FRIGATE;
                        }
                        else if (ship.getHullSize() == ShipAPI.HullSize.DESTROYER)
                        {
                            force *= STRENGTH_VS_DESTROYER;
                            damage /= STRENGTH_VS_DESTROYER;
                        }
                        else if (ship.getHullSize() == ShipAPI.HullSize.CRUISER)
                        {
                            force *= STRENGTH_VS_CRUISER;
                            damage /= STRENGTH_VS_CRUISER;
                        }
                        else if (ship.getHullSize() == ShipAPI.HullSize.CAPITAL_SHIP)
                        {
                            force *= STRENGTH_VS_CAPITAL;
                            damage /= STRENGTH_VS_CAPITAL;
                        }

                        if (ship.getShield() != null && ship.getShield().isOn()
                                && ship.getShield().isWithinArc(ship.getLocation()))
                        {
                            ship.getFluxTracker().increaseFlux(damage * 2, true);
                        }
                        else
                        {
                            for (int x = 0; x < 5; x++)
                            {
                                engine.spawnEmpArc(ship,
                                        MathUtils.getRandomPointInCircle(
                                        ship.getLocation(), ship.getCollisionRadius()),
                                        ship, ship, EXPLOSION_DAMAGE_TYPE, damage / 10,
                                        damage / 5, EXPLOSION_PUSH_RADIUS, null, 2f,
                                        EXPLOSION_COLOR, EXPLOSION_COLOR);
                            }
                        }
                    }

                    force = Math.min(force / (tmp.getMass() / 1000), EXPLOSION_PUSH_FORCE * 1.5f);
                    dir = (Vector2f) MathUtils.getDirectionalVector(ship, tmp).scale(force);
                    Vector2f.add(tmp.getVelocity(), dir, tmp.getVelocity());
                }

                isActive = false;
            }
        }
    }

    @Override
    public void unapply(MutableShipStatsAPI stats, String id)
    {
    }

    @Override
    public StatusData getStatusData(int index, State state, float effectLevel)
    {
        if (state == State.IN)
        {
            if (index == 0)
            {
                return new StatusData("charging scalar deracinator", false);

            }
            else if (index == 1)
            {
                return new StatusData("weapons and shields inoperable", true);
            }
        }

        return null;
    }
}
