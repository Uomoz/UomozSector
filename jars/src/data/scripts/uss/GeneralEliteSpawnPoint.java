package data.scripts.uss;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignClockAPI;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class GeneralEliteSpawnPoint implements EveryFrameScript {

	protected float daysInterval, CS_chance, C_chance, D_chance, W_chance, S_chance, random;
	private int startFP, maxFleets;
	private SectorEntityToken anchor;
	
	private List fleets = new ArrayList();
	private long lastSpawnTime = Long.MIN_VALUE;
        
        Map variants, capitals, cruisers, destroyers, frigates, wings, specials;
	
	private SectorAPI sector;
	private LocationAPI location;
        private String faction, fleetType, fleet_name, A_focus1, A_focus2, A_focus3, baseship, rnd_faction_fleet, range;
	
	public GeneralEliteSpawnPoint(SectorAPI sector, LocationAPI location, int daysInterval, int maxFleets, SectorEntityToken anchor, String faction, String rnd_faction_fleet, String fleet_name, String fleetType, String range, int startFP, Map variants,
                        Map capitals, Map cruisers, Map destroyers, Map frigates, Map wings, Map specials, String baseship,
                        float CS_chance, float C_chance, float D_chance, float W_chance, float S_chance,
                        String A_focus1, String A_focus2, String A_focus3) {
		this.daysInterval = daysInterval;
                this.maxFleets = maxFleets;
		this.anchor = anchor;
		this.sector = sector;
		this.location = location;
                this.startFP = startFP;
                this.range = range;
                this.variants = variants;
                this.capitals = capitals;
                this.specials = specials;
                this.cruisers = cruisers;
                this.destroyers = destroyers;
                this.frigates = frigates;
                this.wings = wings;
                this.faction = faction;
                this.fleetType = fleetType;
                this.fleet_name = fleet_name;
                this.CS_chance = CS_chance;
                this.C_chance = C_chance;
                this.D_chance = D_chance;
                this.W_chance = W_chance;
                this.S_chance = S_chance;
                this.A_focus1 = A_focus1;
                this.A_focus2 = A_focus2;
                this.A_focus3 = A_focus3;
                this.baseship = baseship;
                this.rnd_faction_fleet = rnd_faction_fleet;
		
		lastSpawnTime = (long) (Global.getSector().getClock().getTimestamp());
                random = (float) Math.random();
	}

	public void advance(float amount) {
		CampaignClockAPI clock = sector.getClock();
		
		if (clock.getElapsedDaysSince(lastSpawnTime) >= (daysInterval + random)) {
			lastSpawnTime = clock.getTimestamp();
			random = (float) (Math.random() * 2);
			Iterator iter = fleets.iterator();
			while (iter.hasNext()) {
				CampaignFleetAPI fleet = (CampaignFleetAPI) iter.next();
				if (!fleet.isAlive()) iter.remove();
			}
			
			if (fleets.size() < maxFleets) {
				CampaignFleetAPI fleet = spawnElite();
				if (fleet != null) fleets.add(fleet);
			}
		}
	}
		
	public boolean isDone() {
		return false;
	}

	public boolean runWhilePaused() {
		return false;
	}

	public float getDaysInterval() {
		return daysInterval;
	}
        
        public float getMaxFleets() {
		return maxFleets;
	}

	public SectorEntityToken getAnchor() {
		return anchor;
	}

	public List getFleets() {
		return fleets;
	}

	public long getLastSpawnTime() {
		return lastSpawnTime;
	}

	public SectorAPI getSector() {
		return sector;
	}

	public LocationAPI getLocation() {
		return location;
	}
        
        public int getStartFP() {
		return startFP;
	}
        
        public String getRange() {
		return range;
	}
        
        public Map getVariants() {
		return variants;
	}
        
        public Map getCapitals() {
		return capitals;
	}
        
        public float getCapitalsChance() {
		return CS_chance;
	}
        
        public Map getSpecials() {
		return specials;
        }
        
        public float getSpecialsChance() {
		return S_chance;
	}
        
        public Map getCruisers() {
		return cruisers;
	}
        
        public float getCruisersChance() {
		return C_chance;
	}
        
        public Map getDestroyers() {
		return destroyers;
	}
        public float getDestroyersChance() {
		return D_chance;
	}
        
        public Map getFrigates() {
		return frigates;
	}
                
        public Map getWings() {
		return wings;
	}
        public float getWingsChance() {
		return W_chance;
	}
        
        public String getFaction() {
		return faction;
	}
        
        public String getFleetType() {
		return fleetType;
	}
        
        public String getFleetName() {
		return fleet_name;
	}
        
        public String getAptitudeFocus_1() {
		return A_focus1;
	} 
        
        public String getAptitudeFocus_2() {
		return A_focus2;
	} 
        
        public String getAptitudeFocus_3() {
		return A_focus3;
	} 
        
        public String getFlagship() {
		return baseship;
	} 
        
        public String getRndFactionFleet() {
		return rnd_faction_fleet;
	}
        
	protected abstract CampaignFleetAPI spawnElite();
	
}




