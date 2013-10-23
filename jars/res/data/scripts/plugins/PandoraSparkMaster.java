package data.scripts.plugins;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.CollisionClass;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.CombatEntityAPI;
import com.fs.starfarer.api.combat.DamagingProjectileAPI;
import com.fs.starfarer.api.combat.EveryFrameCombatPlugin;
import com.fs.starfarer.api.combat.MissileAPI;
import com.fs.starfarer.api.combat.OnHitEffectPlugin;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.lazywizard.lazylib.CollisionUtils;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.combat.CombatUtils;
import org.lwjgl.util.vector.Vector2f;

public class PandoraSparkMaster implements EveryFrameCombatPlugin, OnHitEffectPlugin
{
    // The projectile that spawns sparks on impact/when expired
    private static final String PANDORA_PROJ_ID = "ms_pandora_wave";
    // The ID of the weapon that fires the spark projectile (not the proj id!)
    // THIS MUST BE A MISSILE WEAPON FOR THIS CODE TO WORK!
    // Since the facing/velocity is set manually, only used to get damage and sprite
    private static final String SPARK_ID = "ms_spark";
    // The sound ID for when the sparks are created
    private static final String SPARK_BURST_SOUND = "explosion_from_damage";
    // What color should the spark burst be?
    private static final Color SPARK_BURST_COLOR = Color.CYAN;
    // The sound ID for when a spark explodes
    private static final String SPARK_EXPLOSION_SOUND = "explosion_missile";
    // What color should the spark explosion be?
    private static final Color SPARK_EXPLOSION_COLOR = Color.CYAN;
    // How many sparks should be produced per burst?
    private static final int NUM_SPARKS = 8;
    // How fast a spark should travel
    private static final float SPARK_SPEED = 200f;
    // How long should a spark be around before exploding?
    private static final float MIN_SPARK_LIFETIME = 4f;
    private static final float MAX_SPARK_LIFETIME = 6f;
    // How long a spark must exist before it can collide with somethign
    private static final float SPARK_COLLISION_WAIT = 1.0f;
    // How often should the spark change direction?
    private static final float MIN_SPARK_TURN_INTERVAL = 0.5f;
    private static final float MAX_SPARK_TURN_INTERVAL = 1.5f;
    // What is the max change in facing per turn?
    private static final float MAX_SPARK_FACING_CHANGE = 360f;
    private static final float HALF_SPARK_FACING_CHANGE = MAX_SPARK_FACING_CHANGE / 2;
    // How large the explosion and damage zone of a spark explosion is
    private static final float SPARK_EXPLOSION_SIZE = 275f;
    // Keeps track of all active sparks on the battle map
    private static final List sparks = new ArrayList(16);
    // For slight optimization purposes
    private static final Vector2f NULLVEL = new Vector2f(0, 0);
    // The current combat engine instance
    private static CombatEngineAPI engine;

    @Override
    public void onHit(DamagingProjectileAPI projectile, CombatEntityAPI target,
            Vector2f point, boolean shieldHit, CombatEngineAPI engine)
    {
        particleBurst(projectile);
    }

    public static void particleBurst(DamagingProjectileAPI proj)
    {
        // To avoid collision issues, remove projectile before spawning sparks
        // First, need to grab some variables from it
        ShipAPI ship = proj.getSource();
        WeaponAPI weapon = proj.getWeapon();
        Vector2f loc = proj.getLocation(), vel = proj.getVelocity();
        engine.removeEntity(proj);

        // Create the sparks and give them the source's position/velocity
        for (int x = 0; x < NUM_SPARKS; x++)
        {
            // Spawn the spark projectile and register it with the master
            sparks.add(new SparkData((MissileAPI) engine.spawnProjectile(ship,
                    weapon, SPARK_ID, loc, (float) x * (360f / (float) NUM_SPARKS), NULLVEL)));
        }

        // Spawn an explosion and play the sound
        engine.spawnExplosion(loc, vel, SPARK_BURST_COLOR, 7.5f, 1f);
        Global.getSoundPlayer().playSound(SPARK_BURST_SOUND, 1f, 1f, loc, vel);
    }

    @Override
    public void advance(float amount, List events)
    {
        if (engine.isPaused())
        {
            return;
        }

        // Check for Pandora shots that are expiring
        DamagingProjectileAPI proj;
        for (Iterator iter = engine.getProjectiles().iterator(); iter.hasNext();)
        {
            proj = (DamagingProjectileAPI) iter.next();
            if (PANDORA_PROJ_ID.equals(proj.getProjectileSpecId())
                    && (MathUtils.getDistanceSquared(proj.getLocation(),
                    proj.getWeapon().getLocation())
                    > (proj.getWeapon().getRange() * proj.getWeapon().getRange())))
            {
                particleBurst(proj);
            }
        }

        // Advance all active sparks, remove dead sparks
        SparkData spark;
        for (Iterator iter = sparks.iterator(); iter.hasNext();)
        {
            spark = (SparkData) iter.next();

            if (spark.advance(amount))
            {
                iter.remove();
            }
        }
    }

    @Override
    public void init(CombatEngineAPI engine)
    {
        // Do some cleanup at the start of each battle
        PandoraSparkMaster.engine = engine;
        sparks.clear();
    }

    private static final class SparkData
    {
        private boolean canCollide = false;
        private float lifetime = 0f, intendedFacing;
        private MissileAPI spark;
        private IntervalUtil nextTurn, explode;

        private SparkData(MissileAPI spark)
        {
            this.spark = spark;
            // We will handle collision checks manually
            spark.setCollisionClass(CollisionClass.NONE);
            // Decide what facing this spark should start with
            //spark.setFacing(360f * (float) Math.random());
            chooseNextFacing();
            // Decide when to perform the next facing change
            nextTurn = new IntervalUtil(MIN_SPARK_TURN_INTERVAL,
                    MAX_SPARK_TURN_INTERVAL);
            // Decide how long this spark will live
            explode = new IntervalUtil(MIN_SPARK_LIFETIME, MAX_SPARK_LIFETIME);
        }

        public void adjustFacing(float amount)
        {
            // One second for a complete 360 degree turn
            float facingChange = (intendedFacing - spark.getFacing()) * amount * 2f;
            spark.setFacing(spark.getFacing() + facingChange);
            spark.getVelocity().set(MathUtils.getPointOnCircumference(NULLVEL,
                    SPARK_SPEED, spark.getFacing()));
        }

        public void chooseNextFacing()
        {
            intendedFacing = spark.getFacing();
            intendedFacing += (MAX_SPARK_FACING_CHANGE * Math.random())
                    - HALF_SPARK_FACING_CHANGE;
        }

        public void doSparkStrike(CombatEntityAPI struck)
        {
            // Spark hit an entity directly, deal regular damage instead of AOE
            Global.getSoundPlayer().playSound(SPARK_EXPLOSION_SOUND,
                    1.5f, 0.66f, spark.getLocation(), NULLVEL);
            engine.spawnExplosion(spark.getLocation(), NULLVEL,
                    SPARK_EXPLOSION_COLOR, 15f, 2f);
            engine.applyDamage(struck, spark.getLocation(),
                    spark.getDamageAmount(), spark.getDamageType(),
                    spark.getEmpAmount(), false, true, spark.getSource());
            engine.removeEntity(spark);            
        }

        public void doSparkExplosion()
        {
            Global.getSoundPlayer().playSound(SPARK_EXPLOSION_SOUND,
                    1f, 1f, spark.getLocation(), NULLVEL);
            engine.spawnExplosion(spark.getLocation(), NULLVEL,
                    SPARK_EXPLOSION_COLOR, SPARK_EXPLOSION_SIZE, 2f);

            // Get all damage-able entities in range of the explosion
            List victims = CombatUtils.getShipsWithinRange(spark.getLocation(),
                    SPARK_EXPLOSION_SIZE);
            victims.addAll(CombatUtils.getMissilesWithinRange(spark.getLocation(),
                    SPARK_EXPLOSION_SIZE));
            victims.addAll(CombatUtils.getAsteroidsWithinRange(spark.getLocation(),
                    SPARK_EXPLOSION_SIZE));
            if (!victims.isEmpty())
            {
                // Calculate AOE damage points
                List damagePoints = MathUtils.getEquidistantPointsInsideCircle(
                        spark.getLocation(), SPARK_EXPLOSION_SIZE, 40F);

                // Go through all victims and apply damage
                CombatEntityAPI victim;
                for (Iterator iter = victims.iterator(); iter.hasNext();)
                {
                    victim = (CombatEntityAPI) iter.next();

                    if (!engine.isEntityInPlay(victim))
                    {
                        continue;
                    }

                    if (victim instanceof ShipAPI
                            && victim.getCollisionRadius() > 50f)
                    {
                        for (int x = 0; x < damagePoints.size(); x++)
                        {
                            if (CollisionUtils.isPointWithinBounds(
                                    (Vector2f) damagePoints.get(x), victim))
                            {
                                engine.applyDamage(victim, (Vector2f) damagePoints.get(x),
                                        spark.getDamageAmount(), spark.getDamageType(),
                                        spark.getEmpAmount(), false, true, spark.getSource());
                                //engine.addHitParticle((Vector2f) damagePoints.get(x),
                                //        NULLVEL, 5f, 1f, 5f, Color.CYAN);
                            }
                        }
                    }
                    else
                    {
                        // No bounds or very small - just apply straight damage
                        engine.applyDamage(victim, victim.getLocation(),
                                spark.getDamageAmount(), spark.getDamageType(),
                                spark.getEmpAmount(), false, true, spark.getSource());
                    }
                }
            }

            engine.removeEntity(spark);
        }

        public CombatEntityAPI checkImpact()
        {
            // Check all entities in range, see if we are impacting any
            CombatEntityAPI tmp;

            // Get all collision-capable entities in range of the spark
            List entities = CombatUtils.getShipsWithinRange(spark.getLocation(),
                    spark.getCollisionRadius());
            entities.addAll(CombatUtils.getMissilesWithinRange(spark.getLocation(),
                    spark.getCollisionRadius()));
            entities.addAll(CombatUtils.getAsteroidsWithinRange(spark.getLocation(),
                    spark.getCollisionRadius()));
            for (Iterator iter = entities.iterator(); iter.hasNext();)
            {
                tmp = (CombatEntityAPI) iter.next();

                // Ignore objects that don't collide
                if (tmp.getCollisionClass().equals(CollisionClass.NONE))
                {
                    continue;
                }

                // Check if we are hitting something
                if ((tmp.getShield() != null && tmp.getShield().isOn()
                        && tmp.getShield().isWithinArc(spark.getLocation()))
                        || CollisionUtils.isPointWithinBounds(spark.getLocation(), tmp))
                {
                    return tmp;
                }
            }

            return null;
        }

        public boolean advance(float amount)
        {
            lifetime += amount;

            // Advance the explosion timer
            explode.advance(amount);
            if (explode.intervalElapsed() || spark.didDamage() || spark.isFizzling())
            {
                doSparkExplosion();
                return true;
            }

            // Enable collision after a certain duration (allows sparks to spread)
            if (!canCollide && lifetime >= SPARK_COLLISION_WAIT)
            {
                canCollide = true;
                engine.spawnExplosion(spark.getLocation(), spark.getVelocity(),
                        Color.YELLOW, 7.5f, .66f);
            }

            // Check for impacts
            if (canCollide)
            {
                CombatEntityAPI struck = checkImpact();
                if (struck != null)
                {
                    doSparkStrike(struck);
                    return true;
                }
            }

            // Advance the turn change timer
            nextTurn.advance(amount);
            if (nextTurn.intervalElapsed())
            {
                chooseNextFacing();
            }

            // Update the facing of the spark
            adjustFacing(amount);
            return false;
        }
    }
}
