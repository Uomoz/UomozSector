package data.shipsystems.scripts;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.ShipAPI;
import org.lazywizard.lazylib.MathUtils;
import java.util.Iterator;

import java.util.HashMap;
import java.util.Map;

public class ms_jammer implements ShipSystemStatsScript {
    
        public static final float RANGE = 1200f;
	public static final float ACCURACY_BONUS = -50f;
	public static final float RANGE_BONUS = -20f;
        
        private static Map jamming = new HashMap();
        
        @Override
	public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
            //Declares two objects of type ShipAPI. 'ship' is just a generic holder for ships that are cycled through. 'host_ship' is the ship that is using the system.	
            ShipAPI ship;	
            ShipAPI host_ship = (ShipAPI) stats.getEntity();	
            
            for (Iterator iter = Global.getCombatEngine().getShips().iterator(); iter.hasNext();)
            {
                ship = (ShipAPI) iter.next(); //Loads the current ship the iterator is on into 'ship'

		if (ship.isHulk()) continue; //We don't want to bother modifying stats of the ship if it's disabled.
                if (ship == host_ship) continue; //Doesn't let the host ship receive the benefits it's giving to others.  Probably redundant.
			
		//If the ship is on the same team as the host ship, and it's within range, and its a fighter...
		if ((host_ship.getOwner() != ship.getOwner()) && (MathUtils.getDistance(ship, host_ship) <= (RANGE)))  {
				
		//Modify this ship's stats.
		ship.getMutableStats().getAutofireAimAccuracy().modifyPercent(id, ACCURACY_BONUS);
		ship.getMutableStats().getBallisticWeaponRangeBonus().modifyPercent(id, RANGE_BONUS);	
		ship.getMutableStats().getEnergyWeaponRangeBonus().modifyPercent(id, RANGE_BONUS);
				
		//Adds the ship to the hashmap, and associates it with the host ship.
		jamming.put(ship, host_ship);
                System.out.println();
			
                //If the ship isn't in range but is contained in the hashmap, and the host ship of the ship is indeed this one...
		} else if ((jamming.containsKey(ship)) && (jamming.get(ship) == host_ship)){

		//removes all benefits
		ship.getMutableStats().getAutofireAimAccuracy().unmodify(id);	
		ship.getMutableStats().getBallisticWeaponRangeBonus().unmodify(id);	
		ship.getMutableStats().getEnergyWeaponRangeBonus().unmodify(id);
				
		//Removes the ship from the hashmap.
		jamming.remove(ship);				
		}        
            }
        }   
        
        @Override
	public void unapply(MutableShipStatsAPI stats, String id) {

		//Removes the effects from the host ship.
		stats.getMaxSpeed().unmodify(id);
		stats.getMaxTurnRate().unmodify(id);
		stats.getTurnAcceleration().unmodify(id);
		stats.getAcceleration().unmodify(id);
		stats.getDeceleration().unmodify(id);	
		
		//same objects as before.
		ShipAPI ship;	
		ShipAPI host_ship  = (ShipAPI) stats.getEntity();
		System.out.println();
		//Loops through all the ships in the hashmap.
            for (Iterator iter = jamming.keySet().iterator(); iter.hasNext();)
            {
                ship = (ShipAPI) iter.next();
		
			//If the ship in the hash map is receiving benefits from this host ship (which is currently powering-down its system):
			//(This makes it so that one host ship bringing down its system doesn't remove benefits that are being applied to other ships by host ships elsewhere.
			if (jamming.get(ship) == host_ship) {
			
				//removes all benefits
				ship.getMutableStats().getAutofireAimAccuracy().unmodify(id);	
				ship.getMutableStats().getBallisticWeaponRangeBonus().unmodify(id);	
				ship.getMutableStats().getEnergyWeaponRangeBonus().unmodify(id);	

				ship.getMutableStats().getMaxSpeed().unmodify(id);
				ship.getMutableStats().getAcceleration().unmodify(id);
			}	
            }
	}
	
        @Override
	public StatusData getStatusData(int index, State state, float effectLevel) {

		if (index == 0) {
			return new StatusData("wide spectrum jamming active", false);
		}
		return null;
	}
}
