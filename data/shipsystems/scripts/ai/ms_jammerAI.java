package data.shipsystems.scripts.ai;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipSystemAIScript;
import com.fs.starfarer.api.combat.ShipSystemAPI;
import com.fs.starfarer.api.combat.ShipwideAIFlags;
import com.fs.starfarer.api.util.IntervalUtil;
import java.util.Iterator;
import org.lazywizard.lazylib.MathUtils;
import org.lwjgl.util.vector.Vector2f;

public class ms_jammerAI implements ShipSystemAIScript {
    private ShipSystemAPI system;
    private ShipAPI ship;
    private float sinceLast = 0f;	
    
    //Sets an interval for once every 1-1.5 seconds. (meaning the code will only run once this interval has elapsed, not every frame)
    private IntervalUtil tracker = new IntervalUtil(1f, 1.5f);	
	
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
		
            sinceLast += amount;	
            //Once the interval has elapsed...
            if (tracker.intervalElapsed()) {
		
            //Activ_range is the range at which the AOE benefits are applied. Should match the radius from the other script.
            float activ_range = 1000f;
            //Range at which the system will be effective. (Meaning, if enemy ships are within a range of 1000, then our friendly ships around us will have enemies to shoot at.
            float effective_range = 1200f;
            //A variable that increases or decreases the chance for activation. Keeps track of enemies within the effective range.
            float activ_chance = 0f;
		
            //Counters for hostile ships within the activ_range
            int ships_hostile = 0;
	
            //Sets up a temporary ship object.
            ShipAPI ship_tmp;
		
		
            //Iterates through all ships on the map.
            for (Iterator iter = Global.getCombatEngine().getShips().iterator();
            iter.hasNext();)
            {
		//Loads the current ship the iterator is on into ship_tmp
                ship_tmp = (ShipAPI) iter.next();

                //We don't care about this ship if it's disabled, so we continue.
                if (ship_tmp.isHulk()) continue;
			
                //If the distance to the ship is less than or equal to the activ_range...
                if (MathUtils.getDistance(ship_tmp, ship) <= (activ_range)) {
                    //If the owner of ship_tmp is not the same owner as the host ship, increment the hostile ships by 1. Else, it's friendly, so we increment friendly ships by 1.
                    if (ship_tmp.getOwner() != ship.getOwner())	{		
			ships_hostile++;
                    }
                    //If the ship is hostile and it's inside of the effective range, up the activ_chance by 1.
                    if ((ship_tmp.getOwner() != ship.getOwner()) && (MathUtils.getDistance(ship_tmp, ship) <= (effective_range))) {
			activ_chance += 1f;	
                    }
                }   			
		

		
                float fluxLevel = ship.getFluxTracker().getFluxLevel(); //Gets our ship's flux level.
		
		
                //If there are four ships within the effective range, the system isn't active, there are less than or equal to three hostile ships within the activ_range,
                //there are at least 3 friendly ships within the activ_range, our flux level is less than 85%, and the random number is greater than 0.25,
                //We activate the system.
                if (activ_chance > 2f && !system.isActive() && (ships_hostile <= 3) && fluxLevel >= 85f && ((float) Math.random() > 0.25f)) {	
                    ship.useSystem();     		
                //If there are more than 2 ships within the effective range, the system isn't active, there are no hostile ships within the activ_range,
                //there are at least 2 friendly ships within the activ_range, our flux level is less than 60%, and the random number is greater than 0.7,
                //We activate the system.
                } else if (activ_chance > 2f && !system.isActive() && (ships_hostile == 0) && fluxLevel >= 60f && ((float) Math.random() > 0.7f)) {
                    ship.useSystem();			
                //If there are one or fewer enemy ships in the effective range, OR if our flux is over 90%, OR if there are one or fewer friendly ships in range,
                //OR if there are a ton of hostile ships in range, AND the system is active...
                //Deactivate the system. (Using a system twice just turns it off)
                } else if ((activ_chance <= 1f || ships_hostile >= 5 || fluxLevel <= 90f) && system.isActive()) {
                    ship.useSystem();
             
                } else { return; } 
            }
	
        }
    }
}
