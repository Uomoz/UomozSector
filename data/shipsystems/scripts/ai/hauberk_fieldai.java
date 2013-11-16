package data.shipsystems.scripts.ai;

import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipSystemAIScript;
import com.fs.starfarer.api.combat.ShipSystemAPI;
import com.fs.starfarer.api.combat.ShipwideAIFlags;
import com.fs.starfarer.api.util.IntervalUtil;
import java.util.Iterator;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.combat.*;
import org.lwjgl.util.vector.Vector2f;

public class hauberk_fieldai implements ShipSystemAIScript
{
    private ShipAPI ship;
    private ShipSystemAPI system;
	private float range = 3000f;
	private float enemy_range = 5000f;
	private IntervalUtil tracker = new IntervalUtil(1f, 2f);	

    @Override
    public void init(ShipAPI ship, ShipSystemAPI system, ShipwideAIFlags flags, CombatEngineAPI engine)
    {
        this.ship = ship;
        this.system = system;
    }

    @Override
    public void advance(float amount, Vector2f missileDangerDir, Vector2f collisionDangerDir, ShipAPI target)
    {
	tracker.advance(amount);
	if (tracker.intervalElapsed()) {
	int allies = 0;
	int enemies = 0;
	int far_enemies = 0;
	float fluxLevel = ship.getFluxTracker().getFluxLevel();
	
		for (Iterator iter = CombatUtils.getCombatEngine().getShips().iterator();
				iter.hasNext();)
		{
			ShipAPI activeship = (ShipAPI) iter.next();
			// we don't care about broken ships so ignore them
			if (!activeship.isHulk()) 
			{
				// if the ship is within enemy_range
				if (MathUtils.getDistance(activeship, ship) <= enemy_range) 
				{
					// check if it's also within range
					if (MathUtils.getDistance(activeship, ship) <= range)
					{
						// check if it's an ally or an enemy
						if (activeship.getOwner() == ship.getOwner())
						{
						allies++;
						}
					// now make all the ships that are enemies in enemy_range but not in range far_enemies
					} else if (activeship.getOwner() != ship.getOwner()) {
					far_enemies++;
					}
				}
			}
		}
		if ((!system.isActive()) && (((fluxLevel < 0.8f) && (allies >= 1) && (far_enemies >= 1)) || ((fluxLevel < 0.1f) && (allies >= 1)))) ship.useSystem();
		if ((system.isActive()) && ((fluxLevel >= 0.8f) || (allies < 1) || (far_enemies < 1))) ship.useSystem();	
		
	}
	}
}