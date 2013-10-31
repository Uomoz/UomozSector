package data.scripts.plugins;

import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.combat.EveryFrameCombatPlugin;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import org.lwjgl.util.vector.Vector2f;

public class ArcEffectOnOverload implements EveryFrameCombatPlugin
{
        private CombatEngineAPI engine;
    
        public void init(CombatEngineAPI engine) {
            this.engine = engine;
        }

	private IntervalUtil interval = new IntervalUtil(0.25f, 0.5f); //set the time you want the arc to happen
        
        public void advance(float amount, List events) {
	{ 	
            if (engine.isPaused()) {
                return;
            }

                //Advances the interval.
                interval.advance(amount);	

                //When the interval has elapsed...
                if (interval.intervalElapsed())
                {
                    List ships = engine.getShips();
                    Iterator it = ships.iterator();

                    while (it.hasNext())
                    {
                        ShipAPI ship = (ShipAPI) it.next();
                        
                        if (ship.isHulk()) {
                            continue;
                        }
                        
                        if (ship.getFluxTracker().isOverloaded())
                        {
                            Vector2f point = new Vector2f(ship.getLocation());			
			
                            point.x += (ship.getCollisionRadius() / 3f) * (((float) Math.random() * 2f) - 1);
                            point.y += (ship.getCollisionRadius() / 3f) * (((float) Math.random() * 2f) - 1);
                            
                            //spawns one arc.
                            engine.spawnEmpArc(ship,point,ship,ship,DamageType.OTHER,0f,0f,100000f,"hit_shield_beam_loop",12f,new Color(55,170,245,255),new Color(255,255,255,255));
                        
                    }
                }
            }
	}
    }
}