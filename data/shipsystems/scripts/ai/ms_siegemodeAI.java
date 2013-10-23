package data.shipsystems.scripts.ai;


import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipSystemAIScript;
import com.fs.starfarer.api.combat.ShipSystemAPI;
import com.fs.starfarer.api.combat.ShipwideAIFlags;
import java.util.Iterator;
import org.lwjgl.util.vector.Vector2f;
import org.lazywizard.lazylib.combat.*;
import org.lazywizard.lazylib.MathUtils;

public class ms_siegemodeAI implements ShipSystemAIScript {
    private ShipSystemAPI system;
    private ShipAPI ship;
	
    @Override
    public void init(ShipAPI ship, ShipSystemAPI system, ShipwideAIFlags flags, CombatEngineAPI engine)
    {
        this.ship = ship;
        this.system = system;
    }

    @Override
    public void advance(float amount, Vector2f missileDangerDir, Vector2f collisionDangerDir, ShipAPI target)
    {
        float goLive = 1000f; //the AI needs to know (roughly) how big the range of our weapons boost is.
        float fuzzyLogic = 0f; //set our logic counter to 0
        
        int ships = 0;
        ShipAPI ship_tmp;
    
        for (Iterator iter = CombatUtils.getCombatEngine().getShips().iterator();
                iter.hasNext();)
        
        {
            ship_tmp = (ShipAPI) iter.next();
            ship_tmp.getMutableStats();
                
            if ((ship_tmp.getOwner() != ship.getOwner()) && (MathUtils.getDistance(ship_tmp, ship) <= (goLive * 1.05f)))	
            {	//Check hostility and range
                    fuzzyLogic += (ship_tmp.getFluxTracker().getMaxFlux() / 4f); //Watch out for lots of Flux
                    fuzzyLogic += (ship_tmp.getMaxHitpoints() / 4.5f); //And big hitpoint pools - we can generally presume that vessel with both/either would require sieging
                    fuzzyLogic += (ship_tmp.getMutableStats().getMaxSpeed().getModifiedValue() * 5f); //Prioritise defense against flanking maneuvers from fast ships.
            }
        }
        float fluxLevel = ship.getFluxTracker().getFluxLevel();
                
        if (fuzzyLogic > 5000f && !system.isActive() && fluxLevel < 0.95f) {
            //compare our counter to a magic number, make sure the system is already off, and that our flux level isn't too high
            ship.useSystem();
        } else if ((fuzzyLogic <= 2000f || fluxLevel >= 0.95f || goLive >= goLive * 1.15f) && system.isActive()) {
            //if there is little-to-no threat OR if our flux level is too high OR if the enemies are far enough away, turn the system off if it is currently active
            ship.useSystem();
        } else { return; }
    }

}
