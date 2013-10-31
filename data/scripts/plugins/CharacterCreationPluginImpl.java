package data.scripts.plugins;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI.CrewXPLevel;
import com.fs.starfarer.api.characters.CharacterCreationPlugin;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import data.scripts.UsSData;
import java.util.ArrayList;
import java.util.List;

public class CharacterCreationPluginImpl implements CharacterCreationPlugin {
	
	public static class ResponseImpl implements Response {
		private String text;
		public ResponseImpl(String text) {
			this.text = text;
		}
		public String getText() {
			return text;
		}
	}
        
        boolean mili = false;
        boolean corp = false;
        boolean noma = false;
	
	// Not using an enum for this because Janino doesn't support it.
	// It does, apparently, support using enums defined elsewhere - just can't compile new ones.
//	private ResponseImpl SUPPLY_OFFICER = new ResponseImpl("Served as a junior supply officer in the Askonia");
//	private ResponseImpl GUNNER = new ResponseImpl("Hired out as a gunner on a mercenary Falcon-class");
//	private ResponseImpl ENGINEER = new ResponseImpl("Found employment as an assistant engineer on an Apogee-class");
//	private ResponseImpl COPILOT = new ResponseImpl("Spent time as a co-pilot on a patrol ship in the Anar system");
//	private ResponseImpl SOMETHING_ELSE_1 = new ResponseImpl("Did something else");
//	private ResponseImpl ADJUTANT = new ResponseImpl("Served as an adjutant in the Hegemony Navy");
//	private ResponseImpl QUARTERMASTER = new ResponseImpl("Performed the duties of a quartermaster on an independent Conquest-class");
//	private ResponseImpl HELM = new ResponseImpl("Helmed a patrol ship operating in the Gneiss system");
//	private ResponseImpl COMBAT_ENGINEER = new ResponseImpl("Took over the duties of chief combat engineer during a lengthy campaign");
//	private ResponseImpl SOMETHING_ELSE_2 = new ResponseImpl("Did something else");
        private ResponseImpl LEGACY = new ResponseImpl("You are gifted. Your potential piloting and commanding skills far surpasses everyone in the Sector."
                + " Your ancestry is unknown, but it's deeply entangled with the Pre-Collapse military Elite."
                + " You can learn, fight and react faster then any other human. You are born to be the best.");
        private ResponseImpl QUESTIONS = new ResponseImpl("You sit in front of the local ship dealer. He smells of ship coolant and cigarettes, and watches you with a distracted look."
                + " The ships behind him look rather old and worn."
                + " He asks you a some questions.");
        private ResponseImpl GNEISS = new ResponseImpl("Gneiss.");
        private ResponseImpl ANAR = new ResponseImpl("Anar.");
        private ResponseImpl ASKONIA = new ResponseImpl("Askonia.");
        private ResponseImpl BREH = new ResponseImpl("Breh'Inni.");
        private ResponseImpl CORVUS = new ResponseImpl("Corvus.");
        private ResponseImpl CANIS = new ResponseImpl("Canis.");
        private ResponseImpl NUR = new ResponseImpl("Nur.");
        private ResponseImpl SOLO = new ResponseImpl("I work alone.");
        private ResponseImpl CORP = new ResponseImpl("I had a contract with a corporation.");
        private ResponseImpl MILI = new ResponseImpl("I'm a military contractor.");
        private ResponseImpl NOM = new ResponseImpl("I'm a Son of the sands of Naera, I don't ''work'' for anyone.");
        private ResponseImpl HE = new ResponseImpl("The Hegemony paid for patrols.");
        private ResponseImpl TT = new ResponseImpl("Tritachyon paid for info on the system.");
        private ResponseImpl SIN = new ResponseImpl("The Diktat paid for info on Tritachyon movements here.");
        private ResponseImpl SHI = new ResponseImpl("Shadowyards Industries paid for asteroid scans in the system.");
        private ResponseImpl BR = new ResponseImpl("Blackrock Driveyards paid me for gathering contractors in this system.");
        private ResponseImpl PCK = new ResponseImpl("It's not how it works in the Pack.");
        private ResponseImpl PRICK = new ResponseImpl("That doesn't concern you.");
        private ResponseImpl PIRATE = new ResponseImpl("You know what? I'll take that Lasher with me. *You point your gun at the merchant's face*");
        private ResponseImpl NOMA_F = new ResponseImpl("Corvussian, I earn a living for the Armada, scouting this system.");
	
	private int stage = 0;
        
	private String [] prompts = new String [] {
		"The Legacy",
                "The Deal",
                "''Where do you come from?''",
                "''So, you work for someone?''",
                "''So how do you earn a living?''",
	};
	
	public String getPrompt() {
		if (stage < prompts.length) return prompts[stage];
		return null;
	}

	@SuppressWarnings("unchecked")
	public List getResponses() {
		List result = new ArrayList();
		if (stage == 0) {
                        result.add(LEGACY);
		} else if (stage == 1) {
			result.add(QUESTIONS);
		} else if (stage == 2) {
			result.add(GNEISS);
                        result.add(ANAR);
                        result.add(ASKONIA);
                        result.add(BREH);
                        result.add(CORVUS);
                        result.add(CANIS);
                        result.add(NUR);
                } else if (stage == 3) {
                    if (noma) {
                        result.add(NOM);
                    }
                    else {
			result.add(SOLO);
                        result.add(CORP);
                        result.add(MILI);
                    }
		} else if (stage == 4) {
                    if (mili) {
                        result.add(HE);
                        result.add(PCK);
                        result.add(SIN);
                    }
                    else if (corp) {
			result.add(BR);
                        result.add(SHI);
                        result.add(TT);
                    }
                    else if (noma) {
			result.add(NOMA_F);
                    }else {
                        result.add(PRICK);
                        result.add(PIRATE);
                    }  
		}
		return result;
	}

	
	public void submit(Response response, CharacterCreationData data) {
		if (stage == 0) { // just in case
			if (Global.getSettings().isDevMode() && true) {
				data.addStartingShipChoice("tempest_Attack");
				data.addStartingFleetMember("odyssey_Balanced", FleetMemberType.SHIP);
				data.addStartingFleetMember("enforcer_Elite", FleetMemberType.SHIP);
				data.addStartingFleetMember("venture_Balanced", FleetMemberType.SHIP);
				data.addStartingFleetMember("crig_Standard", FleetMemberType.SHIP);
				data.addStartingFleetMember("ox_Standard", FleetMemberType.SHIP);
				data.addStartingFleetMember("wasp_wing", FleetMemberType.FIGHTER_WING);
				data.addStartingFleetMember("wasp_wing", FleetMemberType.FIGHTER_WING);
				data.getStartingCargo().getCredits().add(50000f);
				data.getStartingCargo().addFuel(1000);
				data.getStartingCargo().addSupplies(800);
				data.getStartingCargo().addMarines(100);
				data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 800);
			}
		}
		
		stage++;
		
		MutableCharacterStatsAPI stats = data.getPerson().getStats();
		if (response == GNEISS) {
			data.setStartingLocationName("Gneiss");
                        data.getStartingCoordinates().set(1650, -2925);
                        data.addStartingShipChoice("brdy_robberfly_cs");
                        data.addStartingShipChoice("hound_Assault");
		} else if (response == ANAR) {
                        data.setStartingLocationName("Anar");
                        data.getStartingCoordinates().set(2350, 875);
                        data.addStartingShipChoice("ms_seski_CS");
                        data.addStartingShipChoice("hound_Assault");
		} else if (response == ASKONIA) {
                        data.setStartingLocationName("Askonia");
                        data.getStartingCoordinates().set(-4750, -3750);
                        data.addStartingShipChoice("vigilance_Standard");
		} else if (response == BREH) {
                        data.setStartingLocationName("Breh'Inni");
                        data.getStartingCoordinates().set(-1075, 2875);
                        data.addStartingShipChoice("junk_pirates_sickle_Standard");
		} else if (response == CORVUS) {
                        data.setStartingLocationName("Corvus");
                        data.getStartingCoordinates().set(10150, 25);
                        data.addStartingShipChoice("hound_Assault");
                        data.addStartingShipChoice("lasher_Standard");
                        data.addStartingShipChoice("omen_PD");
		} else if (response == CANIS) {
                        data.setStartingLocationName("Canis");
                        data.getStartingCoordinates().set(2975, 1250);
                        data.addStartingShipChoice("pack_bedlington_Standard");
                } else if (response == NUR) {
                        data.setStartingLocationName("Corvus");
                        data.getStartingCoordinates().set(10150, 25);
                        data.addStartingShipChoice("nom_wurm_assault");
                        noma = true;
		} else if (response == SOLO) {
                        
		} else if (response == CORP) {
                        corp = true;
		} else if (response == MILI) {
                        mili = true;
		} else if (response == HE) {
                        data.addStartingFleetMember("lasher_Standard", FleetMemberType.SHIP);
                        data.getStartingCargo().addFuel(20);
			data.getStartingCargo().addSupplies(20);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 50);
                        UsSData.player_id = "Hegemony Enforcer";
		} else if (response == SIN) {
                        data.addStartingFleetMember("brawler_Assault", FleetMemberType.SHIP);
                        data.getStartingCargo().addFuel(20);
			data.getStartingCargo().addSupplies(20);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 30);
                        UsSData.player_id = "Diktat Lion";
		} else if (response == PCK) {
                        data.addStartingFleetMember("pack_wirefox_Standard", FleetMemberType.SHIP);
                        data.getStartingCargo().addFuel(7);
			data.getStartingCargo().addSupplies(5);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 50);
                        UsSData.player_id = "Pack Wolf";
		} else if (response == TT) {
                        data.addStartingFleetMember("wolf_CS", FleetMemberType.SHIP);
                        data.getStartingCargo().addFuel(20);
			data.getStartingCargo().addSupplies(20);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 30);
                        UsSData.player_id = "Tritachyon Corporate";
		} else if (response == BR) {
                        data.addStartingFleetMember("brdy_locust_patrol", FleetMemberType.SHIP);
                        data.getStartingCargo().addFuel(12);
			data.getStartingCargo().addSupplies(17);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 18);
                        UsSData.player_id = "Blackrock Corporate";
		} else if (response == SHI) {
                        data.addStartingFleetMember("ms_enlil_Balanced", FleetMemberType.SHIP);
                        data.getStartingCargo().addFuel(20);
			data.getStartingCargo().addSupplies(30);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 30);
                        UsSData.player_id = "Shadow Corporate";
		} else if (response == PRICK) {
                        data.getStartingCargo().getCredits().add(7000f);
                        UsSData.player_id = "Independent";
		} else if (response == PIRATE) {
                        data.addStartingFleetMember("lasher_Standard", FleetMemberType.SHIP);
                        data.getStartingCargo().addFuel(20);
			data.getStartingCargo().addSupplies(20);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 50);
                        data.getStartingCargo().getCredits().add(7000f);
                        UsSData.player_id = "Pirate";
		} else if (response == NOMA_F) {
                        data.addStartingFleetMember("nom_wurm_assault", FleetMemberType.SHIP);
                        data.getStartingCargo().addFuel(50);
			data.getStartingCargo().addSupplies(50);
			data.getStartingCargo().addCrew(CrewXPLevel.VETERAN, 20);
                        UsSData.player_id = "Nomad";
		}
	}

	public void startingShipPicked(String variantId, CharacterCreationData data) {
		MutableCharacterStatsAPI stats = data.getPerson().getStats();
		stats.addAptitudePoints(3);
		stats.addSkillPoints(6);
                data.getStartingCargo().getCredits().add(2000f);
		
		if (variantId.equals("vigilance_Standard")) {
			data.getStartingCargo().addFuel(20);
			data.getStartingCargo().addSupplies(30);
                        data.getStartingCargo().addCrew(CrewXPLevel.ELITE, 1);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 24);
		} else
		if (variantId.equals("lasher_Standard")) {
			data.getStartingCargo().addFuel(20);
			data.getStartingCargo().addSupplies(20);
                        data.getStartingCargo().addCrew(CrewXPLevel.ELITE, 1);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 49);
		} else
		if (variantId.equals("wolf_CS")) {
			data.getStartingCargo().addFuel(20);
			data.getStartingCargo().addSupplies(20);
                        data.getStartingCargo().addCrew(CrewXPLevel.ELITE, 1);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 28);
		} else
		if (variantId.equals("shuttle_Attack")) {
			data.getStartingCargo().addFuel(10);
			data.getStartingCargo().addSupplies(10);
                        data.getStartingCargo().addCrew(CrewXPLevel.ELITE, 1);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 12);
		} else				
		if (variantId.equals("hound_Assault")) {
			data.getStartingCargo().addFuel(30);
			data.getStartingCargo().addSupplies(40);
                        data.getStartingCargo().addCrew(CrewXPLevel.ELITE, 1);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 39);
		} else
		if (variantId.equals("tempest_Attack")) {
			data.getStartingCargo().addFuel(20);
			data.getStartingCargo().addSupplies(30);
                        data.getStartingCargo().addCrew(CrewXPLevel.ELITE, 1);
			data.getStartingCargo().addCrew(CrewXPLevel.ELITE, 19);
                } else
		if (variantId.equals("brdy_robberfly_cs")) {
			data.getStartingCargo().addFuel(12);
			data.getStartingCargo().addSupplies(15);
                        data.getStartingCargo().addCrew(CrewXPLevel.ELITE, 1);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 17);
                } else
		if (variantId.equals("ms_seski_CS")) {
			data.getStartingCargo().addFuel(25);
			data.getStartingCargo().addSupplies(17);
                        data.getStartingCargo().addCrew(CrewXPLevel.ELITE, 1);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 17);
                        } else
		if (variantId.equals("junk_pirates_sickle_Standard")) {
			data.getStartingCargo().addFuel(20);
			data.getStartingCargo().addSupplies(15);
                        data.getStartingCargo().addCrew(CrewXPLevel.ELITE, 1);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 24);
                        } else
		if (variantId.equals("omen_PD")) {
			data.getStartingCargo().addFuel(20);
			data.getStartingCargo().addSupplies(15);
                        data.getStartingCargo().addCrew(CrewXPLevel.ELITE, 1);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 19);
                        } else
		if (variantId.equals("pack_bedlington_Standard")) {
			data.getStartingCargo().addFuel(7);
			data.getStartingCargo().addSupplies(5);
                        data.getStartingCargo().addCrew(CrewXPLevel.ELITE, 1);
			data.getStartingCargo().addCrew(CrewXPLevel.REGULAR, 44);
		}
                if (variantId.equals("nom_wurm_assault")) {
			data.getStartingCargo().addFuel(50);
			data.getStartingCargo().addSupplies(50);
			data.getStartingCargo().addCrew(CrewXPLevel.ELITE, 1);
			data.getStartingCargo().addCrew(CrewXPLevel.VETERAN, 19);
		}
	}

}







