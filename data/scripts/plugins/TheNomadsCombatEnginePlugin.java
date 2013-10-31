package data.scripts.plugins;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.CombatEnginePlugin;
import com.fs.starfarer.api.combat.CombatFleetManagerAPI;
import com.fs.starfarer.api.combat.EveryFrameCombatPlugin;
import com.fs.starfarer.api.fleet.FleetGoal;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.mission.FleetSide;
import java.util.Iterator;
import java.util.List;
import org.lwjgl.util.vector.Vector2f;

public class TheNomadsCombatEnginePlugin implements CombatEnginePlugin, EveryFrameCombatPlugin
{
	private CombatEngineAPI engine = null;
	private boolean executed = true;
	
	public void init( CombatEngineAPI engine )
	{
		this.engine = engine;
		executed = false;
	}

	public void advance( float amount, List events )
	{
		if( executed )
			return;
		executed = true;
		if( engine.getContext() != null
		&&  engine.getContext().getOtherFleet() != null
		&&  engine.getContext().getOtherFleet().getFaction() != null
		&&  "nomads".equals( engine.getContext().getOtherFleet().getFaction().getId() ))
		{
			CombatFleetManagerAPI fleet_mgr = engine.getFleetManager( FleetSide.ENEMY );
			List reserves = fleet_mgr.getReservesCopy();
			for( Iterator i = reserves.iterator(); i.hasNext(); )
			{
				FleetMemberAPI ship = (FleetMemberAPI)i.next();
				if( !ship.canBeDeployedForCombat() )
					return;
				
				Vector2f spawn_location = new Vector2f();
				if( engine.getContext().getOtherGoal() == FleetGoal.ATTACK )
				{
					spawn_location.x = -0.45f * engine.getMapWidth() + 0.90f * (float)Math.random() * engine.getMapWidth();
					spawn_location.y = 0.50f * engine.getMapHeight() + 0.10f * (float)Math.random() * engine.getMapHeight();
				}
				else if( engine.getContext().getOtherGoal() == FleetGoal.ESCAPE )
				{
					spawn_location.x = -0.30f * engine.getMapWidth() + 0.60f * (float)Math.random() * engine.getMapWidth();
					spawn_location.y = -0.20f * engine.getMapHeight() + 0.10f * (float)Math.random() * engine.getMapHeight();
				}
				try
				{
					fleet_mgr.spawnShipOrWing( ship.getSpecId(), spawn_location, 270.0f );
				}
				catch( NullPointerException e )
				{
				}
			}
		}		
	}


}
