package data.shipsystems.scripts;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.combat.AIUtils;

	// Code originally written in large part by ValkyriaL for his fantastic Valkyrians mod
	// (http://fractalsoftworks.com/forum/index.php?topic=5066.0) used only with permission

public class hauberk_field implements ShipSystemStatsScript
{
    private static final String id = "hauberk_field";
    private static final float RANGE = 3000f;
    private static final Map buffed = new WeakHashMap();
	private static final Map debuffed = new WeakHashMap();
	public static final float SENSOR_RANGE_PERCENT = 25f;
	public static final float WEAPON_RANGE_PERCENT = 20f;

    @Override
    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel)
    {
		float sensorRangePercent = SENSOR_RANGE_PERCENT * effectLevel;
		float weaponRangePercent = WEAPON_RANGE_PERCENT * effectLevel;
        ShipAPI ship = (ShipAPI) stats.getEntity();
        if (ship == null)
        {
            return;
        }
		
		ship.getMutableStats().getSightRadiusMod().modifyPercent(id, sensorRangePercent);
        ship.getMutableStats().getBallisticWeaponRangeBonus().modifyPercent(id, weaponRangePercent);
        ship.getMutableStats().getEnergyWeaponRangeBonus().modifyPercent(id, weaponRangePercent);
		ship.getMutableStats().getMissileWeaponRangeBonus().modifyPercent(id, weaponRangePercent);

        ShipAPI toBuff;
        for (Iterator allies = AIUtils.getAlliesOnMap(ship).iterator(); allies.hasNext();)
        {
            toBuff = (ShipAPI) allies.next();

            if (buffed.containsKey(toBuff) && buffed.get(toBuff) != ship)
            {
                continue;
            }

            if (MathUtils.getDistance(toBuff, ship) <= RANGE)
            {
                applyBuff(toBuff, effectLevel);
                buffed.put(toBuff, ship);
            }
			
            else if (buffed.containsKey(toBuff))
            {
                unapplyBuff(toBuff);
                buffed.remove(toBuff);
            }
        }
		
		ShipAPI victim;
        for (Iterator enemies = AIUtils.getEnemiesOnMap(ship).iterator(); enemies.hasNext();)
        {
            victim = (ShipAPI) enemies.next();

            if (debuffed.containsKey(victim) && debuffed.get(victim) != ship)
            {
                continue;
            }

            if (MathUtils.getDistance(victim, ship) <= RANGE)
            {
                applyDebuff(victim, effectLevel);
                debuffed.put(victim, ship);
            }
			
            else if (debuffed.containsKey(victim))
            {
                unapplyDebuff(victim);
                debuffed.remove(victim);
            }
        }
		
    }

    @Override
    public void unapply(MutableShipStatsAPI stats, String id)
    {
        ShipAPI ship = (ShipAPI) stats.getEntity();
        if (ship == null)
        {
            return;
        }

		ship.getMutableStats().getSightRadiusMod().unmodify(id);
        ship.getMutableStats().getBallisticWeaponRangeBonus().unmodify(id);
        ship.getMutableStats().getEnergyWeaponRangeBonus().unmodify(id);
		ship.getMutableStats().getMissileWeaponRangeBonus().unmodify(id);
		
        Map.Entry toDebuff;
        for (Iterator allBuffs = buffed.entrySet().iterator(); allBuffs.hasNext();)
        {
            toDebuff = (Map.Entry) allBuffs.next();
            if (toDebuff.getValue() == ship)
            {
                unapplyBuff((ShipAPI) toDebuff.getKey());
                allBuffs.remove();
            }
        }
		
		Map.Entry debuff;
        for (Iterator allDebuffs = debuffed.entrySet().iterator(); allDebuffs.hasNext();)
        {
            debuff = (Map.Entry) allDebuffs.next();
            if (debuff.getValue() == ship)
            {
                unapplyDebuff((ShipAPI) debuff.getKey());
                allDebuffs.remove();
            }
        }
    }

    public void applyBuff(ShipAPI ship, float effectLevel)
    {
		float sensorRangePercent = SENSOR_RANGE_PERCENT * effectLevel;
		float weaponRangePercent = WEAPON_RANGE_PERCENT * effectLevel;
        ship.getMutableStats().getSightRadiusMod().modifyPercent(id, sensorRangePercent);
        ship.getMutableStats().getBallisticWeaponRangeBonus().modifyPercent(id, weaponRangePercent);
        ship.getMutableStats().getEnergyWeaponRangeBonus().modifyPercent(id, weaponRangePercent);
		ship.getMutableStats().getMissileWeaponRangeBonus().modifyPercent(id, weaponRangePercent);
    }

    public void unapplyBuff(ShipAPI ship)
    {
	    ship.getMutableStats().getSightRadiusMod().unmodify(id);
        ship.getMutableStats().getBallisticWeaponRangeBonus().unmodify(id);
        ship.getMutableStats().getEnergyWeaponRangeBonus().unmodify(id);
		ship.getMutableStats().getMissileWeaponRangeBonus().unmodify(id);
    }

	public void applyDebuff(ShipAPI victim, float effectLevel)
    {
		float sensorRangePercentDebuff = (SENSOR_RANGE_PERCENT - (SENSOR_RANGE_PERCENT * 2f));
		float weaponRangePercentDebuff = (WEAPON_RANGE_PERCENT - (WEAPON_RANGE_PERCENT * 2f));
        victim.getMutableStats().getSightRadiusMod().modifyPercent(id, sensorRangePercentDebuff);
        victim.getMutableStats().getBallisticWeaponRangeBonus().modifyPercent(id, weaponRangePercentDebuff);
        victim.getMutableStats().getEnergyWeaponRangeBonus().modifyPercent(id, weaponRangePercentDebuff);
		victim.getMutableStats().getMissileWeaponRangeBonus().modifyPercent(id, weaponRangePercentDebuff);
    }

    public void unapplyDebuff(ShipAPI victim)
    {
	    victim.getMutableStats().getSightRadiusMod().unmodify(id);
        victim.getMutableStats().getBallisticWeaponRangeBonus().unmodify(id);
        victim.getMutableStats().getEnergyWeaponRangeBonus().unmodify(id);
		victim.getMutableStats().getMissileWeaponRangeBonus().unmodify(id);
    }
	
    @Override
	public StatusData getStatusData(int index, State state, float effectLevel) {
		float sensorRangePercent = SENSOR_RANGE_PERCENT * effectLevel;
		float weaponRangePercent = WEAPON_RANGE_PERCENT * effectLevel;
		if (index == 0) {
			return new StatusData("Friendly sensor and weapon ranges increased", false);
		} else if (index == 1) {
			return new StatusData("Enemy sensor and weapon ranges decreased", false);
		} 
		return null;
	}

}
