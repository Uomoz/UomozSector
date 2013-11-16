package data.scripts.world.armada;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import data.scripts.uss.UsSUtils;
import data.scripts.world.armada.api.CampaignArmadaAPI;
import data.scripts.world.armada.api.CampaignArmadaEscortFleetPositionerAPI;
import org.lwjgl.util.vector.Vector2f;

public class CampaignArmadaFormationOrbit implements CampaignArmadaEscortFleetPositionerAPI
{
	private final static float TWO_PI = (float)(2.0f*Math.PI);
	
	private SectorAPI sector;
	private float orbit_radius;
	private float orbit_direction;
	private float orbit_period_days;
	private float seconds_per_day;
	
	private float accumulator;
	
	public CampaignArmadaFormationOrbit(
	  SectorAPI sector,
	  float orbit_radius,
	  float orbit_direction, // positive is counter-clockwise
	  float orbit_period_days )
	{
		this.sector = sector;
		this.orbit_radius = orbit_radius;
		this.orbit_direction = Math.signum( orbit_direction );
		this.orbit_period_days = orbit_period_days;

		this.seconds_per_day = sector.getClock().getSecondsPerDay();
	}
	
	private CampaignArmadaAPI armada;
	private CampaignFleetAPI leader_fleet;
	private CampaignFleetAPI[] escort_fleets;
	
	@Override
	public void set_armada( CampaignArmadaAPI armada )
	{
		this.armada = armada;
		leader_fleet = armada.getLeaderFleet();
		escort_fleets = armada.getEscortFleets();
	}

	@Override
	public void update_escort_fleet_positions( float amount )
	{
		accumulator += amount;
		if( leader_fleet == null || escort_fleets == null )
			return;
		Vector2f leader_fleet_location = leader_fleet.getLocation();
		for( int i = 0; i < escort_fleets.length; ++i )
		{
			CampaignFleetAPI escort_fleet = escort_fleets[i];
			if( escort_fleet == null || !escort_fleet.isAlive() || escort_fleet.isInHyperspaceTransition() )
				continue;
			float formation_angle_offset = i * (TWO_PI / escort_fleets.length);
			float orbit_angle = orbit_direction * (accumulator / seconds_per_day) * (orbit_period_days * TWO_PI) + formation_angle_offset;
			escort_fleet.setLocation(
			    (float)(leader_fleet_location.x + orbit_radius * UsSUtils.cos( orbit_angle )),
			    (float)(leader_fleet_location.y + orbit_radius * UsSUtils.sin( orbit_angle )) );
		}
	}
}
