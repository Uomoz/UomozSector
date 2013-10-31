package data.scripts.uss;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignClockAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import data.scripts.UsSUtils;

@SuppressWarnings("unchecked")
public class UsSEveryFrame implements EveryFrameScript {
        
        boolean advanced = false;
	private long lastSpawnTime = Long.MIN_VALUE;
        SectorAPI sector = Global.getSector();
        
        public UsSEveryFrame()    {
            lastSpawnTime = (long) (Global.getSector().getClock().getTimestamp());
        }
        
    @Override
        public boolean isDone() {
		return false;
	}

    @Override
	public boolean runWhilePaused() {
		return false;
	}

    @Override
	public void advance(float amount) {
            if (advanced == false)  {
                UsSUtils.setStart();
                advanced = true;
            }
            CampaignClockAPI clock = sector.getClock();
            if (clock.getElapsedDaysSince(lastSpawnTime) >= 1f) {
                lastSpawnTime = clock.getTimestamp();
                UsSUtils.stationDynamics();
            }
	}
}






