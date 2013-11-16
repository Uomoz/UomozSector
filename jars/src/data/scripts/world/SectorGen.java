package data.scripts.world;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorGeneratorPlugin;
import com.fs.starfarer.api.impl.campaign.CoreCampaignPluginImpl;
import data.scripts.world.systems.Anar;
import data.scripts.world.systems.Askonia;
import data.scripts.world.systems.Breh_Inni;
import data.scripts.world.systems.Canis;
import data.scripts.world.systems.Corvus;
import data.scripts.world.systems.Gneiss;
import data.scripts.world.systems.Hyper;
import data.scripts.world.systems.Kadur;
import data.scripts.world.systems.Mirage;
import data.scripts.world.systems.Nur;
import data.scripts.world.systems.Oasis;


public class SectorGen implements SectorGeneratorPlugin {

	public void generate(SectorAPI sector) {
		
                initFactionRelationships(sector);
		
                new Askonia().generate(sector);
		new Corvus().generate(sector);
                new Canis().generate(sector);
                new Breh_Inni().generate(sector);
                new Anar().generate(sector);
                new Gneiss().generate(sector);
                new Nur().generate(sector);
                new Kadur().generate(sector);
                new Mirage().generate(sector);
                new Oasis().generate(sector);
                        
                new Hyper().generate(sector);
                
		sector.registerPlugin(new CoreCampaignPluginImpl());
	}
	
	private void initFactionRelationships(SectorAPI sector) {
		FactionAPI hegemony = sector.getFaction("hegemony");
		FactionAPI tritachyon = sector.getFaction("tritachyon");
		FactionAPI pirates = sector.getFaction("pirates");
		FactionAPI player = sector.getFaction("player");
		FactionAPI pack = sector.getFaction("pack");
                FactionAPI junk_pirates = sector.getFaction("junk_pirates");
                FactionAPI sindrian_diktat = sector.getFaction("sindrian_diktat");
                FactionAPI blackrock_driveyards = sector.getFaction("blackrock_driveyards");
                FactionAPI shadow_industry = sector.getFaction("shadow_industry");
                FactionAPI nomads = sector.getFaction("nomads");
                FactionAPI regime = sector.getFaction("regime");
                FactionAPI insurgency = sector.getFaction("insurgency");
                
		regime.setRelationship("hegemony", -1);
		regime.setRelationship("tritachyon", -1);
		regime.setRelationship("pirates", -1);
		regime.setRelationship("sindrian_diktat", -1);
		regime.setRelationship("junk_pirates", -1);
		regime.setRelationship("nomads", -1);
                regime.setRelationship("pack", -1);
                
                insurgency.setRelationship("regime", -1);
		insurgency.setRelationship("hegemony", -1);
		insurgency.setRelationship("tritachyon", -1);
		insurgency.setRelationship("sindrian_diktat", -1);
		insurgency.setRelationship("independent", -1);
		insurgency.setRelationship("player", -1);   
		insurgency.setRelationship("blackrock_driveyards", -1);
		insurgency.setRelationship("shadow_industry", -1);
		insurgency.setRelationship("nomads", -1);
                insurgency.setRelationship("pack", -1);
                
                nomads.setRelationship("hegemony", -1);
                nomads.setRelationship("tritachyon", -1);
                nomads.setRelationship("pirates", -1);
                nomads.setRelationship("pack", -1);
                nomads.setRelationship("junk_pirates", -1);
                nomads.setRelationship("sindrian_diktat", -1);
                nomads.setRelationship("blackrock_driveyards", -1);
                nomads.setRelationship("shadow_industry", -1);
                
                shadow_industry.setRelationship("hegemony", -1);
                shadow_industry.setRelationship("pirates", -1);
                shadow_industry.setRelationship("pack", -1);
                shadow_industry.setRelationship("junk_pirates", -1);
                
                blackrock_driveyards.setRelationship("hegemony", -1);
                blackrock_driveyards.setRelationship("tritachyon", -1);
                blackrock_driveyards.setRelationship("pirates", -1);
                blackrock_driveyards.setRelationship("pack", -1);
                blackrock_driveyards.setRelationship("junk_pirates", -1);
                blackrock_driveyards.setRelationship("shadow_industry", -1);
                
                sindrian_diktat.setRelationship("tritachyon", -1);
                sindrian_diktat.setRelationship("pirates", -1);
                sindrian_diktat.setRelationship("pack", -1);
                sindrian_diktat.setRelationship("junk_pirates", -1);
                sindrian_diktat.setRelationship("blackrock_driveyards", -1);
                sindrian_diktat.setRelationship("shadow_industry", -1);
                
                junk_pirates.setRelationship("hegemony", -1);
		junk_pirates.setRelationship("tritachyon", -1);
		junk_pirates.setRelationship("independent", -1);
		junk_pirates.setRelationship("player", -1);
                junk_pirates.setRelationship("pack", -1);
                
                pack.setRelationship("hegemony", -1);
		pack.setRelationship("tritachyon", -1);
		pack.setRelationship("pirates", -1);
                
		player.setRelationship("pirates", -1);
		
                hegemony.setRelationship("tritachyon", -1);
		hegemony.setRelationship("pirates", -1);
		
		tritachyon.setRelationship("pirates", -1);
		
		pirates.setRelationship("independent", -1);	
                pirates.setRelationship("player", -1);
	}
}