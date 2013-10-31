package data.scripts.weapons;

import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.EveryFrameWeaponEffectPlugin;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import com.fs.starfarer.api.util.IntervalUtil;

public class WavebeamMaster implements EveryFrameWeaponEffectPlugin
{
    private IntervalUtil weaponCharge = new IntervalUtil(0.25f, 0.25f);
    private IntervalUtil reset = new IntervalUtil(0.75f, 0.75f);
    private boolean fired = false;
    
    @Override
    public void advance(float amount, CombatEngineAPI engine, WeaponAPI weapon)
    {
        
        if (engine.isPaused()) {
            return;
        }
        
        ShipAPI ship = weapon.getShip();
        
        {
            if(weapon.isFiring())
            {
                if(fired == false )
                {
                    engine.spawnProjectile(ship, weapon, "ms_2wave", weapon.getLocation(), weapon.getCurrAngle(), ship.getVelocity());
                
                    fired = true;
                }
                
                weaponCharge.advance(amount);
                reset.advance(amount);
                
                if(reset.intervalElapsed())
                {
                    fired = false;    
                    weaponCharge.forceIntervalElapsed();
                    reset.forceIntervalElapsed();
                }
            }
        }
    }
    
}
