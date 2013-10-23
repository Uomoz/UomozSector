package data.scripts.plugins;

import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.EveryFrameCombatPlugin;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.util.IntervalUtil;
import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.lwjgl.util.vector.Vector2f;

public class WeaponDamageSmoke implements EveryFrameCombatPlugin {
	
	private CombatEngineAPI engine;
        private static Map mag = new HashMap();
        static {
        mag.put(WeaponSize.SMALL, 1f);
        mag.put(WeaponSize.MEDIUM, 1.5f);
        mag.put(WeaponSize.LARGE, 2.5f);
        }
   
        private float smokeSize = 0.8f + 0.4f * (float)Math.random();

	public void init(CombatEngineAPI engine) {
		this.engine = engine;
	}
	
	private IntervalUtil interval = new IntervalUtil(0.1f, 0.1f);
        
	public void advance(float amount, List events) {
		if (engine.isPaused()) return;
                if (interval.intervalElapsed()){
                    List ships = engine.getShips();
                    Iterator it = ships.iterator();
                    while (it.hasNext())
                    {
                    ShipAPI ship = (ShipAPI) it.next();      
                    List weapons = ship.getAllWeapons();
                    Iterator it2 = weapons.iterator();
                    while (it2.hasNext())
                    {
                    WeaponAPI weapon = (WeaponAPI) it2.next();
                    if(weapon.isDisabled()){ 

                    float smokeSizeValue = (Float)mag.get(weapon.getSize());

                    float velX = (float)Math.random() * 10f - 5f;
                    float velY = (float)Math.sqrt(25f - velX * velX);
                    if((float)Math.random() >= 0.5f){
                       velY = -velY;
                    }
                    engine.addSmokeParticle(weapon.getLocation(), new Vector2f(velX,velY), 40f * this.smokeSize * smokeSizeValue, 0.05f, 4f, new Color(25,25,25,20));
                    engine.addSmokeParticle(weapon.getLocation(), new Vector2f(velX,velY), 20f * this.smokeSize * smokeSizeValue, 0.05f, 3f, new Color(90,90,90,20));
                    }
                }
            }
         }
    }
}
