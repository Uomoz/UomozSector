package data.scripts.uss;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignClockAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import java.awt.Color;

    
@SuppressWarnings("unchecked")
public class UsSFactoryManager implements EveryFrameScript {
        
    long startProductionTime = Long.MIN_VALUE;
    SectorAPI sector = Global.getSector();
    boolean producing = false;
    float productionTimeLength;
    String shipId = "none";
    FleetMemberAPI fmapi;
    CargoAPI privatestash;
    SectorEntityToken station;

    public UsSFactoryManager() {
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
        CampaignClockAPI clock = sector.getClock();
        if (!"none".equals(shipId)) {
            if (producing == false)  {
                startProductionTime = (long) (clock.getTimestamp());
                producing = true;
            }
            
            if (clock.getElapsedDaysSince(startProductionTime) >= productionTimeLength) {
                if (shipId.endsWith("_wing")) {
                    fmapi = Global.getFactory().createFleetMember(FleetMemberType.FIGHTER_WING, shipId);
                } else {
                    fmapi = Global.getFactory().createFleetMember(FleetMemberType.SHIP, shipId);
                }
                fmapi.getRepairTracker().setMothballed(true);
                privatestash.getMothballedShips().addFleetMember(fmapi);
                producing = false;
                shipId = "none";
                Global.getSector().getCampaignUI().addMessage("Your new " + fmapi.getVariant().getFullDesignationWithHullName() + " is ready at the " + station.getFullName(), shipId, Color.lightGray);
            }
        }        
    }
    public boolean isProducing() {
        if (shipId.equals("none")) {
            return false;
        } else {
            return true;
        }
    }
}






