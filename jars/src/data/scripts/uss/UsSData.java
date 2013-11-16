package data.scripts.uss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsSData {     
    
    public static String player_id;
    
    public static final Map All_Variants, Variants_PP, Variants_IND, Variants_HE, Variants_TT, Variants_PCK, Variants_JP, Variants_SHI, Variants_BR, HE_F, HE_D, HE_C, HE_W, HE_CRR,
            HE_TR, HE_CS, PPSHI_CRR, PPSHI_CS, PPSHI_C, PPSHI_D, PPSHI_F, PP_CRR, PP_TR, PP_CS, PP_W, PP_C, PP_D, PP_F, PPSHI_TR, PPSHI_W, BR_U, BR_TR, BR_CRR, BR_W, BR_CS, BR_C, BR_D,
            BR_F, SHI_U, SHI_TR, SHI_CRR, SHI_W, SHI_C, SHI_CS, SHI_D, SHI_F, JP_U, JP_W, JP_TR, JP_CS, JP_C, JP_D, JP_F, PCK_W, PCK_F, PCK_D, PCK_C, PCK_CS, CIV_LG, CIV_W, IND_TR,
            IND_W, IND_CRR, IND_CS, IND_C, IND_F, IND_D, SIN_TR, SIN_CRR, SIN_W, SIN_CS, SIN_C, SIN_D, SIN_F, TT_TR, TT_CRR, TT_W, TT_CS, TT_C, TT_D, TT_F, PPBR_TR, PPBR_CRR, PPBR_W,
            PPBR_CS, PPBR_C, PPBR_D, PPBR_F, PP_B, ALL_D_FINAL, ALL_W_FINAL, ALL_F_FINAL, ALL_C_FINAL, ALL_CS_FINAL, NOM_F, NOM_D, NOM_C, NOM_CS, NOM_W, PCK_TR, KAD_F, KAD_D, KAD_C,
            KAD_CS, KAD_W, KAD_TR, KAD_LG, INS_F, INS_D, INS_C, INS_CS, INS_W, INS_LG, INS_TR, Variants_INS, Variants_KAD, Variants_NOM, MT_C, LT_F, LT_D, LT_C, LT_CS, LT_W, MT_F, MT_D,
            MT_CS, MT_W, HT_F, HT_D, HT_C, HT_CS, HT_W, PCKT_F, PCKT_D, PCKT_C, PCKT_CS, PCKT_W, PCKT_ALL, LT_ALL, HT_ALL, MT_ALL, JPT_F, JPT_C, JPT_D, JPT_CS, JPT_W, JPT_U, JPT_ALL, SHIT_F,
            SHIT_D, SHIT_C, SHIT_CS, SHIT_W, SHIT_U,SHIT_ALL, BRT_F, BRT_D, BRT_C, BRT_CS, BRT_W, BRT_U, BRT_ALL, NOMT_F, NOMT_D, NOMT_C, NOMT_CS, NOMT_W, NOMT_ALL, KADT_F, KADT_D, KADT_C,
            KADT_CS, KADT_W, KADT_ALL;
    
    public static final List PCK_SW_FINAL, PCK_MW_FINAL, PCK_LW_FINAL, JP_SW_FINAL, JP_MW_FINAL, JP_LW_FINAL, SHI_MW_FINAL, SHI_SW_FINAL, SHI_LW_FINAL, BR_SW_FINAL, BR_MW_FINAL, BR_LW_FINAL,
            LT_SW_FINAL, LT_MW_FINAL, LT_LW_FINAL,MT_SW_FINAL, MT_MW_FINAL, MT_LW_FINAL, HT_SW_FINAL, HT_MW_FINAL, HT_LW_FINAL, ALL_SW_FINAL, ALL_MW_FINAL, ALL_LW_FINAL, KAD_SW_FINAL, 
            KAD_MW_FINAL, KAD_LW_FINAL, factions, techs;
    
    public static class BpData {
        public int bpcount = 0;
        public int capitals = 0;
        public int cruisers = 0;
        public int destroyers = 0;
        public int frigates = 0;
        public int wings = 0;
        public int large_weapons = 0;
        public int medium_weapons = 0;
        public int small_weapons = 0;
        public String factionId = "null";
        public List knownBps = new ArrayList();
    }
    
    public static class StationData {
        public boolean privatePersonalStorage = false;
    }
    
    public static final String [] factionsStrings = {"hegemony","pirates","tritachyon","sindrian_diktat","independent","pack","shadow_industry","blackrock_driveyards","junk_pirates","regime","insurgency","nomads"};
    public static final String [] techsStrings = {"LT","MT","HT","BRT","PCKT","SHIT","JPT","KADT","NOMT"};
    
    public static final String [] LOWTECH_SW = {"vulcan","bomb","mininglaser","lightmortar","lightdualmg","lightmg","lightdualac","lightac","fragbomb","clusterbomb","annihilator"};
    public static final String [] LOWTECH_MW = {"miningblaster","annihilatorpod","heavymg","flak","arbalest","heavyac"};
    public static final String [] LOWTECH_LW = {"hellbore","hephag"};
        
    public static final String [] MIDTECH_SW = {"swarmer","lightneedler","pdlaser","lrpdlaser","irpulse","lightag"};
    public static final String [] MIDTECH_MW = {"shredder","hveldriver","heavyneedler","heavymauler","dualflak","chaingun"};
    public static final String [] MIDTECH_LW = {"gauss","multineedler","mark9"};

    public static final String [] HIGHTECH_SW = {"taclaser","railgun","ioncannon","pdburst","atropos","atropos_single","amblaster"};
    public static final String [] HIGHTECH_MW = {"phasebeam","pulselaser","heavyburst","heavyblaster","gravitonbeam","phasecl"};   
    public static final String [] HIGHTECH_LW = {"mjolnir","hurricane","autopulse","guardian","hil","plasma","tachyonlance"};
    
    public static final String [] MISSILES_SW = {"sabot","sabot_single","heatseeker","harpoon_single","harpoon","reaper"};
    public static final String [] MISSILES_MW = {"pilum","typhoon","salamanderpod","sabotpod","harpoonpod"};   
    public static final String [] MISSILES_LW = {"cyclone"};
    
    public static final String [] PCK_MW = {"pack_ripsaw"};
    
    public static final String [] JP_SW = {"junk_pirates_grapeshot_s","junk_pirates_lexcimer","junk_pirates_scatterpd"};
    public static final String [] JP_MW = {"junk_pirates_cutlass","junk_pirates_grapeshot_m"};
    public static final String [] JP_LW = {"junk_pirates_grapeshot"};
    
    public static final String [] SHI_SW = {"ms_cepc","ms_pdcepc","ms_shrike_rack","ms_shrike_single","ms_blackcap_6x","ms_blackcap_3x","ms_tusk_rack","ms_tusk_single"};
    public static final String [] SHI_MW = {"ms_blackcap_pod","ms_shrike_pod","ms_scattercepc","ms_mcepc","ms_chaingang","ms_shrike_pod"};
    public static final String [] SHI_LW = {"ms_wamgun"};
 
    public static final String [] BR_SW = {"br_fpde","brdy_volley","brdy_2xfury","brdy_fury","brvulcan","brdy_ag","achilles_mrm","brdy_quill","brdy_ac","brdy_argussmall","brdy_linear"};
    public static final String [] BR_MW = {"achillespod","brdy_plasma","brburst","brdy_dualac","brdy_solenoid","brdy_squallgun","br_pde","br_iwbattery","brdy_galecannon"};
    public static final String [] BR_LW = {"brdy_solenoidlarge","brdy_squallbattery"};
    
    public static final String [] KAD_SW = {"khs_javelin_vlrms","khs_kinetic_penetrator","khs_assault_penetrator","khs_sling","khs_volley_cannon","khs_strike_cannon","khs_martyrcannon"};
    public static final String [] KAD_MW = {"khs_lance_vlrms","khs_kinetic_violator","khs_assault_violator","khs_partisan"};
    public static final String [] KAD_LW = {"khs_francisca_vlrms","khs_kinetic_demolisher","khs_assault_demolisher","khs_hoplite"};  
    
    static {
        List temp1 = new ArrayList();
        List temp2 = new ArrayList();
        temp1.addAll(Arrays.asList(factionsStrings));
        temp2.addAll(Arrays.asList(techsStrings));
        factions = Collections.unmodifiableList(temp1);
        techs = Collections.unmodifiableList(temp2);
    }
    
    static {
        List SW_list = new ArrayList();
        SW_list.addAll(Arrays.asList(HIGHTECH_SW));
        SW_list.addAll(Arrays.asList(MIDTECH_SW));
        SW_list.addAll(Arrays.asList(LOWTECH_SW));
        SW_list.addAll(Arrays.asList(MISSILES_SW));
        SW_list.addAll(Arrays.asList(JP_SW));
        SW_list.addAll(Arrays.asList(SHI_SW));
        SW_list.addAll(Arrays.asList(BR_SW));
        SW_list.addAll(Arrays.asList(KAD_SW));
        ALL_SW_FINAL = Collections.unmodifiableList(SW_list);

        List MW_list = new ArrayList();
        MW_list.addAll(Arrays.asList(HIGHTECH_MW));
        MW_list.addAll(Arrays.asList(MIDTECH_MW));
        MW_list.addAll(Arrays.asList(LOWTECH_MW));
        MW_list.addAll(Arrays.asList(MISSILES_MW));
        MW_list.addAll(Arrays.asList(PCK_MW));
        MW_list.addAll(Arrays.asList(JP_MW));
        MW_list.addAll(Arrays.asList(SHI_MW));
        MW_list.addAll(Arrays.asList(BR_MW));   
        MW_list.addAll(Arrays.asList(KAD_MW));
        ALL_MW_FINAL = Collections.unmodifiableList(MW_list);

        List LW_list = new ArrayList();
        LW_list.addAll(Arrays.asList(HIGHTECH_LW));
        LW_list.addAll(Arrays.asList(MIDTECH_LW));
        LW_list.addAll(Arrays.asList(LOWTECH_LW));
        LW_list.addAll(Arrays.asList(MISSILES_LW));
        LW_list.addAll(Arrays.asList(JP_LW));
        LW_list.addAll(Arrays.asList(SHI_LW));
        LW_list.addAll(Arrays.asList(BR_LW));
        LW_list.addAll(Arrays.asList(KAD_LW));
        ALL_LW_FINAL = Collections.unmodifiableList(LW_list);
    }
        
    static {
        List SW_list = new ArrayList();
        SW_list.addAll(Arrays.asList(HIGHTECH_SW));
        SW_list.addAll(Arrays.asList(HIGHTECH_SW));
        SW_list.addAll(Arrays.asList(MISSILES_SW));
        HT_SW_FINAL = Collections.unmodifiableList(SW_list);

        List MW_list = new ArrayList();
        MW_list.addAll(Arrays.asList(HIGHTECH_MW));
        MW_list.addAll(Arrays.asList(HIGHTECH_MW));
        MW_list.addAll(Arrays.asList(MISSILES_MW));
        HT_MW_FINAL = Collections.unmodifiableList(MW_list);

        List LW_list = new ArrayList();
        LW_list.addAll(Arrays.asList(HIGHTECH_LW));
        LW_list.addAll(Arrays.asList(HIGHTECH_LW));
        LW_list.addAll(Arrays.asList(MISSILES_LW));
        HT_LW_FINAL = Collections.unmodifiableList(LW_list);
    }
    
    static {
        List SW_list = new ArrayList();
        SW_list.addAll(Arrays.asList(LOWTECH_SW));
        SW_list.addAll(Arrays.asList(LOWTECH_SW));
        SW_list.addAll(Arrays.asList(MISSILES_SW));
        LT_SW_FINAL = Collections.unmodifiableList(SW_list);

        List MW_list = new ArrayList();
        MW_list.addAll(Arrays.asList(LOWTECH_MW));
        MW_list.addAll(Arrays.asList(LOWTECH_MW));
        MW_list.addAll(Arrays.asList(MISSILES_MW));
        LT_MW_FINAL = Collections.unmodifiableList(MW_list);

        List LW_list = new ArrayList();
        LW_list.addAll(Arrays.asList(LOWTECH_LW));
        LW_list.addAll(Arrays.asList(LOWTECH_LW));
        LW_list.addAll(Arrays.asList(MISSILES_LW));
        LT_LW_FINAL = Collections.unmodifiableList(LW_list);
    }
    
    static {
        List SW_list = new ArrayList();
        SW_list.addAll(Arrays.asList(MIDTECH_SW));
        SW_list.addAll(Arrays.asList(MIDTECH_SW));
        SW_list.addAll(Arrays.asList(MISSILES_SW));
        MT_SW_FINAL = Collections.unmodifiableList(SW_list);

        List MW_list = new ArrayList();
        MW_list.addAll(Arrays.asList(MIDTECH_MW));
        MW_list.addAll(Arrays.asList(MIDTECH_MW));
        MW_list.addAll(Arrays.asList(MISSILES_MW));
        MT_MW_FINAL = Collections.unmodifiableList(MW_list);

        List LW_list = new ArrayList();
        LW_list.addAll(Arrays.asList(MIDTECH_LW));
        LW_list.addAll(Arrays.asList(MIDTECH_LW));
        LW_list.addAll(Arrays.asList(MISSILES_LW));
        MT_LW_FINAL = Collections.unmodifiableList(LW_list);
    }
    
    static {
        List SW_list = new ArrayList();
        SW_list.addAll(Arrays.asList(HIGHTECH_SW));
        SW_list.addAll(Arrays.asList(BR_SW));
        SW_list.addAll(Arrays.asList(BR_SW));
        SW_list.addAll(Arrays.asList(MISSILES_SW));
        BR_SW_FINAL = Collections.unmodifiableList(SW_list);

        List MW_list = new ArrayList();
        MW_list.addAll(Arrays.asList(HIGHTECH_MW));
        MW_list.addAll(Arrays.asList(BR_MW));
        MW_list.addAll(Arrays.asList(BR_MW));
        MW_list.addAll(Arrays.asList(MISSILES_MW));
        BR_MW_FINAL = Collections.unmodifiableList(MW_list);

        List LW_list = new ArrayList();
        LW_list.addAll(Arrays.asList(HIGHTECH_LW));
        LW_list.addAll(Arrays.asList(BR_LW));
        LW_list.addAll(Arrays.asList(BR_LW));
        LW_list.addAll(Arrays.asList(MISSILES_LW));
        BR_LW_FINAL = Collections.unmodifiableList(LW_list);
    }
    
    static {
        List SW_list = new ArrayList();
        SW_list.addAll(Arrays.asList(HIGHTECH_SW));
        SW_list.addAll(Arrays.asList(SHI_SW));
        SW_list.addAll(Arrays.asList(SHI_SW));
        SW_list.addAll(Arrays.asList(MISSILES_SW));
        SHI_SW_FINAL = Collections.unmodifiableList(SW_list);

        List MW_list = new ArrayList();
        MW_list.addAll(Arrays.asList(HIGHTECH_MW));
        MW_list.addAll(Arrays.asList(SHI_MW));
        MW_list.addAll(Arrays.asList(SHI_MW));
        MW_list.addAll(Arrays.asList(MISSILES_MW));
        SHI_MW_FINAL = Collections.unmodifiableList(MW_list);

        List LW_list = new ArrayList();
        LW_list.addAll(Arrays.asList(HIGHTECH_LW));
        LW_list.addAll(Arrays.asList(SHI_LW));
        LW_list.addAll(Arrays.asList(SHI_LW));
        LW_list.addAll(Arrays.asList(MISSILES_LW));
        SHI_LW_FINAL = Collections.unmodifiableList(LW_list);
    }
    
    static {
        List SW_list = new ArrayList();
        SW_list.addAll(Arrays.asList(MIDTECH_SW));
        SW_list.addAll(Arrays.asList(LOWTECH_SW));
        SW_list.addAll(Arrays.asList(JP_SW));
        SW_list.addAll(Arrays.asList(JP_SW));
        SW_list.addAll(Arrays.asList(MISSILES_SW));
        JP_SW_FINAL = Collections.unmodifiableList(SW_list);

        List MW_list = new ArrayList();
        MW_list.addAll(Arrays.asList(MIDTECH_MW));
        MW_list.addAll(Arrays.asList(LOWTECH_MW));
        MW_list.addAll(Arrays.asList(JP_MW));
        MW_list.addAll(Arrays.asList(JP_MW));
        MW_list.addAll(Arrays.asList(MISSILES_MW));
        JP_MW_FINAL = Collections.unmodifiableList(MW_list);

        List LW_list = new ArrayList();
        LW_list.addAll(Arrays.asList(MIDTECH_LW));
        LW_list.addAll(Arrays.asList(LOWTECH_LW));
        LW_list.addAll(Arrays.asList(JP_LW));
        LW_list.addAll(Arrays.asList(JP_LW));
        LW_list.addAll(Arrays.asList(MISSILES_LW));
        JP_LW_FINAL = Collections.unmodifiableList(LW_list);
    }
    
    static {
        List SW_list = new ArrayList();
        SW_list.addAll(Arrays.asList(MIDTECH_SW));
        SW_list.addAll(Arrays.asList(LOWTECH_SW));
        SW_list.addAll(Arrays.asList(MISSILES_SW));
        PCK_SW_FINAL = Collections.unmodifiableList(SW_list);

        List MW_list = new ArrayList();
        MW_list.addAll(Arrays.asList(MIDTECH_MW));
        MW_list.addAll(Arrays.asList(LOWTECH_MW));
        MW_list.addAll(Arrays.asList(PCK_MW));
        MW_list.addAll(Arrays.asList(PCK_MW));
        MW_list.addAll(Arrays.asList(MISSILES_MW));
        PCK_MW_FINAL = Collections.unmodifiableList(MW_list);

        List LW_list = new ArrayList();
        LW_list.addAll(Arrays.asList(MIDTECH_LW));
        LW_list.addAll(Arrays.asList(LOWTECH_LW));
        LW_list.addAll(Arrays.asList(MISSILES_LW));
        PCK_LW_FINAL = Collections.unmodifiableList(LW_list);
    }
    
    static {
        List SW_list = new ArrayList();
        SW_list.addAll(Arrays.asList(MIDTECH_SW));
        SW_list.addAll(Arrays.asList(KAD_SW));
        SW_list.addAll(Arrays.asList(KAD_SW));
        SW_list.addAll(Arrays.asList(MISSILES_SW));
        KAD_SW_FINAL = Collections.unmodifiableList(SW_list);

        List MW_list = new ArrayList();
        MW_list.addAll(Arrays.asList(MIDTECH_MW));
        MW_list.addAll(Arrays.asList(KAD_MW));
        MW_list.addAll(Arrays.asList(KAD_MW));
        MW_list.addAll(Arrays.asList(MISSILES_MW));
        KAD_MW_FINAL = Collections.unmodifiableList(MW_list);

        List LW_list = new ArrayList();
        LW_list.addAll(Arrays.asList(MIDTECH_LW));
        LW_list.addAll(Arrays.asList(KAD_LW));
        LW_list.addAll(Arrays.asList(KAD_LW));
        LW_list.addAll(Arrays.asList(MISSILES_LW));
        KAD_LW_FINAL = Collections.unmodifiableList(LW_list);
    }   
        
    static {
        Map F = new HashMap();
        F.put("hound_Hull", 10f); F.put("lasher_Hull", 10f); F.put("dram_Hull", 10f);
        F.put("pp_torch_Hull", 7.5f);
        LT_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.put("enforcer_Hull", 10f); D.put("condor_Hull", 10f); D.put("mule_Hull", 10f);
        D.put("phaeton_Hull", 10f); D.put("tarsus_Hull", 10f); D.put("buffalo2_Hull", 10f);
        D.put("pp_bull_Hull", 10f);
        LT_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.put("dominator_Hull", 10f); C.put("venture_Hull", 10f); C.put("pp_shark_Hull", 10f);
        LT_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.put("onslaught_Hull", 10f); CS.put("prometheus_Hull", 10f); CS.put("pp_renegade_Hull", 10f);
        LT_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.put("mining_drone_wing", 10f); W.put("piranha_wing", 10f); W.put("talon_wing", 10f);
        LT_W = Collections.unmodifiableMap(W);

        Map ALL = new HashMap();
        ALL.putAll(F); ALL.putAll(W); ALL.putAll(D); ALL.putAll(C); ALL.putAll(CS);
        LT_ALL = Collections.unmodifiableMap(ALL);
    }
    
    static {
        Map F = new HashMap();
        F.put("brawler_Hull", 5f); F.put("vigilance_Hull", 5f); F.put("shuttle_Hull", 5f);
        F.put("ox_Standard", 5f); F.put("syndicate_asp_diamondback_Hull", 5f);
        MT_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.put("sunder_Hull", 2.5f); D.put("hammerhead_Hull", 5f); D.put("gemini_Hull", 5f);
        D.put("valkyrie_Hull", 5f); D.put("crig_Standard", 5f); D.put("syndicate_asp_copperhead_Hull", 5f);
        MT_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.put("eagle_Hull", 5f); C.put("falcon_Hull", 5f); C.put("apogee_Hull", 2.5f);
        C.put("syndicate_asp_gigantophis_Hull", 5f);
        MT_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.put("conquest_Hull", 5f); CS.put("atlas_Hull", 5f);
        MT_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.put("gladius_wing", 5f); W.put("warthog_wing", 5f); W.put("thunder_wing", 5f);
        W.put("broadsword_wing", 2.5f); W.put("syndicate_asp_bite_wing", 5f); W.put("syndicate_asp_venom_wing", 5f);
        W.put("syndicate_asp_constrictor_wing", 5f);
        MT_W= Collections.unmodifiableMap(W);

        Map ALL = new HashMap();
        ALL.putAll(F); ALL.putAll(W); ALL.putAll(D); ALL.putAll(C); ALL.putAll(CS);
        MT_ALL = Collections.unmodifiableMap(ALL);
    }
        
    static {
        Map F = new HashMap();
        F.put("shade_Hull", 2.5f); F.put("afflictor_Hull", 2.5f); F.put("omen_Hull", 2.5f);
        F.put("tempest_Hull", 2.5f); F.put("hyperion_Hull", 1f); F.put("wolf_Hull", 2.5f);
        HT_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.put("medusa_Hull", 2.5f); D.put("buffalo_Hull", 2.5f);
        HT_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.put("aurora_Hull", 2.5f); C.put("doom_Hull", 1f);
        HT_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.put("astral_Hull", 1f); CS.put("paragon_Hull", 1f); CS.put("odyssey_Hull", 1f);
        HT_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.put("longbow_wing", 2.5f); W.put("trident_wing", 1.5f); W.put("dagger_wing", 1.5f);
        W.put("wasp_wing", 5f); W.put("xyphos_wing", 2.5f);
        HT_W= Collections.unmodifiableMap(W);

        Map ALL = new HashMap();
        ALL.putAll(F); ALL.putAll(W); ALL.putAll(D); ALL.putAll(C); ALL.putAll(CS);
        HT_ALL = Collections.unmodifiableMap(ALL);
    }
    
    static {
        Map F = new HashMap();
        F.put("pack_wirefox_Hull", 5f); F.put("pack_bedlington_Hull", 3.5f); F.put("pack_samoyed_Hull", 5f);
        F.put("pack_samoyed_decoupled_Hull", 5f);
        PCKT_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.put("pack_ridgeback_Hull", 2.5f); D.put("pack_ridgeback_x_Hull", 3.5f); D.put("pack_pitbull_Hull", 2.5f);
        D.put("pack_BRT_Hull", 5f); D.put("pack_komondor_Hull", 5f);
        PCKT_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        PCKT_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        PCKT_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        PCKT_W= Collections.unmodifiableMap(W);

        Map ALL = new HashMap();
        ALL.putAll(F); ALL.putAll(W); ALL.putAll(D); ALL.putAll(C); ALL.putAll(CS);
        PCKT_ALL = Collections.unmodifiableMap(ALL);
    }
    
    static {
        Map F = new HashMap();
        F.put("junk_pirates_clam_Hull", 5f); F.put("junk_pirates_hammer_Hull", 5f); F.put("junk_pirates_sickle_Hull", 5f);
        F.put("junk_pirates_stoatB_Hull", 5f); F.put("junk_pirates_stoatA_Hull", 5f);
        JPT_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.put("junk_pirates_octopus_Hull", 5f); D.put("junk_pirates_boxer_Hull", 5f); D.put("junk_pirates_boxenstein_Hull", 5f);
        D.put("junk_pirates_scythe_Hull", 5f); D.put("junk_pirates_langoustine_Hull", 5f); D.put("junk_pirates_turbot_Hull", 2.5f);
        JPT_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.put("junk_pirates_dugong_Hull", 5f); C.put("junk_pirates_goat_Hull", 5f); C.put("junk_pirates_orca_Hull", 5f);
        JPT_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.put("junk_pirates_kraken_Hull", 5f);
        JPT_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.put("junk_pirates_cleat_wing", 5f); W.put("junk_pirates_spike_wing", 5f); W.put("junk_pirates_shard_wing", 5f);
        W.put("junk_pirates_splinter_wing", 5f);
        JPT_W= Collections.unmodifiableMap(W);

        Map U = new HashMap();
        U.put("junk_pirates_the_reaper_Hull", 5f);
        JPT_U = Collections.unmodifiableMap(U);

        Map ALL = new HashMap();
        ALL.putAll(F); ALL.putAll(W); ALL.putAll(D); ALL.putAll(C); ALL.putAll(CS);
        JPT_ALL = Collections.unmodifiableMap(ALL);
    }
    
    static {
        Map F = new HashMap();
        F.put("ms_seski_Hull", 2.5f); F.put("ms_enlil_Hull", 2.5f); F.put("ms_shamash_Hull", 1f);
        F.put("ms_inanna_Hull", 2.5f);
        SHIT_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.put("ms_morningstar_Hull", 5f); D.put("ms_sargasso_Hull", 2.5f); D.put("ms_lambent_Hull", 2.5f);
        D.put("ms_solidarity_Hull", 2.5f); D.put("ms_potnia_Hull", 2.5f);
        SHIT_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.put("ms_charybdis_Hull", 5f); C.put("ms_elysium_Hull", 2.5f); C.put("ms_tartarus_Hull", 2.5f);
        C.put("ms_scylla_Hull", 2.5f);
        SHIT_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.put("ms_mimir_Hull", 5f);
        SHIT_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.put("ms_skinwalker_wing", 2.5f); W.put("ms_neriad_wing", 5f); W.put("ms_raksasha_wing", 2.5f);
        SHIT_W= Collections.unmodifiableMap(W);

        Map U = new HashMap();
        U.put("ms_mimirBaus_Hull", 5f);
        SHIT_U = Collections.unmodifiableMap(U);

        Map ALL = new HashMap();
        ALL.putAll(F); ALL.putAll(W); ALL.putAll(D); ALL.putAll(C); ALL.putAll(CS);
        SHIT_ALL = Collections.unmodifiableMap(ALL);
    }
    
    static {
        Map F = new HashMap();
        F.put("brdy_scarab_Hull", 2.5f); F.put("brdy_mantis_Hull", 1f); F.put("brdy_locust_Hull", 2.5f);
        F.put("brdy_robberfly_Hull", 2.5f);
        BRT_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.put("brdy_desdinova_Hull", 1.5f); D.put("brdy_gonodactylus_Hull", 1f); D.put("brdy_typheus_Hull", 1f);
        D.put("brdy_cetonia_Hull", 2.5f);
        BRT_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.put("brdy_stenos_Hull", 1f); C.put("brdy_revenant_Hull", 1f); C.put("brdy_nevermore_Hull", 1.5f);
        BRT_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.put("brdy_kurmaraja_Hull", 2.5f); CS.put("brdy_karkinos_Hull", 1f);
        BRT_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.put("brdy_krait_wing", 2.5f); W.put("brdy_squilla_wing", 2.5f); W.put("brdy_serket_wing", 1f);
        BRT_W= Collections.unmodifiableMap(W);

        Map U = new HashMap();
        U.put("nevermoreB_Hull", 1f); U.put("brdy_stormcrow_Hull", 1f);
        BRT_U = Collections.unmodifiableMap(U);

        Map ALL = new HashMap();
        ALL.putAll(F); ALL.putAll(W); ALL.putAll(D); ALL.putAll(C); ALL.putAll(CS);
        BRT_ALL = Collections.unmodifiableMap(ALL);
    }
    
    static {
        Map F = new HashMap();
        F.put("nom_wurm_assault", 2.5f); F.put("nom_yellowjacket_sniper", 1.5f); F.put("nom_death_bloom_strike", 1f);
        NOMT_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.put("nom_scorpion_assault", 1.5f); D.put("nom_komodo_mk2_assault", 1f); D.put("nom_komodo_assault", 2.5f);
        D.put("nom_flycatcher_carrier", 1.5f); D.put("nom_roadrunner_pursuit", 1.5f);
        NOMT_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.put("nom_rattlesnake_assault", 2.5f);
        NOMT_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.put("nom_sandstorm_assault", 2.5f); CS.put("nom_gila_monster_antibattleship", 1f);
        NOMT_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.put("nom_iguana_wing", 2.5f); W.put("nom_scarab_wing", 2.5f); W.put("nom_toad_wing", 1.5f);
        W.put("nom_fang_wing", 1.5f);
        NOMT_W = Collections.unmodifiableMap(W);

        Map ALL = new HashMap();
        ALL.putAll(F); ALL.putAll(W); ALL.putAll(D); ALL.putAll(C); ALL.putAll(CS);
        NOMT_ALL = Collections.unmodifiableMap(ALL);
    }
    
    static {
        Map F = new HashMap();
        F.put("khs_jackal_Hull", 5f); F.put("khs_hyena_Hull", 5f); F.put("khs_buzzard_Hull", 2.5f);
        F.put("khs_vulture_Hull", 3.5f); F.put("khs_camel_Hull", 3.5f); F.put("khs_mutt_Hull", 5f);
        F.put("khs_mistral_Hull", 3.5f); F.put("khs_dolphin_Hull", 5f);
        KADT_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.put("khs_falchion_Hull", 5f); D.put("khs_targe_Hull", 4f); D.put("khs_hauberk_Hull", 3f);
        D.put("khs_sirocco_Hull", 3.5f); D.put("khs_djinn_Hull", 2.5f);
        KADT_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.put("khs_rukh_Hull", 3.5f); C.put("khs_sphinx_Hull", 2.5f); C.put("khs_golem_Hull", 3.5f);
        C.put("khs_leviathan_Hull", 5f);
        KADT_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.put("khs_caliph_Hull", 1f); CS.put("khs_seraph_Hull", 3.5f); CS.put("khs_behemoth_Hull", 5f);
        KADT_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.put("khs_dervish_wing", 2.5f); W.put("khs_dirk_wing", 5f); W.put("khs_scimitar_wing", 5f);
        W.put("khs_immortal_wing", 3.5f); W.put("khs_phalanx_wing", 3.5f); W.put("khs_myrmidon_wing", 3.5f);
        KADT_W= Collections.unmodifiableMap(W);

        Map ALL = new HashMap();
        ALL.putAll(F); ALL.putAll(W); ALL.putAll(D); ALL.putAll(C); ALL.putAll(CS);
        KADT_ALL = Collections.unmodifiableMap(ALL);
    }
    
    static {
        Map F = new HashMap();
        F.putAll(LT_F); F.remove("dram_Hull"); F.remove("pp_torch_Hull");
        HE_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.putAll(LT_D); D.put("condor_Hull", 5f); D.put("mule_Hull", 2.5f);
        D.remove("phaeton_Hull"); D.remove("tarsus_Hull"); D.remove("buffalo2_Hull");
        D.remove("pp_bull_Hull");
        HE_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.putAll(LT_C); C.remove("venture_Hull"); C.remove("pp_shark_Hull");
        HE_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.putAll(LT_CS); CS.remove("prometheus_Hull"); CS.remove("pp_renegade_Hull");
        HE_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.putAll(LT_W); W.remove("mining_drone_wing"); W.put("broadsword_wing", 5f);
        W.put("piranha_wing", 2.5f); W.put("talon_wing", 2.5f); W.put("warthog_wing", 5f);
        HE_W = Collections.unmodifiableMap(W);

        Map CRR = new HashMap();
        CRR.put("condor_Hull", 10f);
        HE_CRR = Collections.unmodifiableMap(CRR);

        Map TR = new HashMap();
        TR.put("mule_Hull", 7.5f); TR.put("phaeton_Hull", 5f); TR.put("tarsus_Hull", 10f);
        TR.put("atlas_Hull", 2.5f); TR.put("dram_Hull", 10f); TR.put("valkyrie_Hull", 5f);
        HE_TR = Collections.unmodifiableMap(TR);
    }
         
    static {
        Map F = new HashMap();
        F.putAll(LT_F); F.remove("dram_Hull");
        PP_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.putAll(LT_D); D.remove("phaeton_Hull"); D.remove("tarsus_Hull");
        D.put("enforcer_Hull", 5f); D.put("condor_Hull", 5f); D.put("mule_Hull", 7.5f);
        PP_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.putAll(LT_C); C.put("dominator_Hull", 5f); C.put("venture_Hull", 7.5f);    
        PP_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.putAll(LT_CS); CS.remove("prometheus_Hull"); CS.remove("onslaught_Hull");
        PP_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.putAll(LT_W); W.remove("mining_drone_wing"); W.put("piranha_wing", 5f);
        PP_W = Collections.unmodifiableMap(W);

        Map CRR = new HashMap();
        CRR.put("condor_Hull", 10f); CRR.put("venture_Hull", 5f);
        PP_CRR = Collections.unmodifiableMap(CRR);

        Map TR = new HashMap();
        TR.put("tarsus_Hull", 10f);
        PP_TR = Collections.unmodifiableMap(TR);

        Map B = new HashMap();
        B.put("buffalo2_Hull", 10f);
        PP_B = Collections.unmodifiableMap(B);
    }
    
    static {
        Map F = new HashMap();
        F.putAll(PP_F); F.put("ms_seski_Hull", 3.5f); F.put("ms_enlil_Hull", 6.5f);
        PPSHI_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.putAll(PP_D); D.put("ms_sargasso_Hull", 2.5f); D.put("ms_potniaBis_Hull", 10f);
        PPSHI_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.putAll(PP_C);
        PPSHI_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.putAll(PP_CS);
        PPSHI_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.putAll(PP_W);
        PPSHI_W= Collections.unmodifiableMap(W);

        Map CRR = new HashMap();
        CRR.putAll(PP_CRR); CRR.put("ms_sargasso_Hull", 7.5f);
        PPSHI_CRR = Collections.unmodifiableMap(CRR);

        Map TR = new HashMap();
        TR.putAll(PP_TR); TR.put("ms_potniaBis_Hull", 10f); TR.put("ms_solidarity_Hull", 5f);
        PPSHI_TR = Collections.unmodifiableMap(TR);
    } 
    
    static {
        Map F = new HashMap();
        F.putAll(PP_F); F.put("brdy_robberfly_Hull", 10f);
        PPBR_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.putAll(PP_D); D.put("brdy_gonodactylus_Hull", 7.5f); D.put("brdy_typheus_Hull", 2.5f);
        PPBR_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.putAll(PP_C);
        PPBR_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.putAll(PP_CS);
        PPBR_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.putAll(PP_W); W.put("brdy_krait_wing", 10f);
        PPBR_W= Collections.unmodifiableMap(W);

        Map CRR = new HashMap();
        CRR.putAll(PP_CRR); CRR.put("brdy_typheus_Hull", 7.5f);
        PPBR_CRR = Collections.unmodifiableMap(CRR);

        Map TR = new HashMap();
        TR.putAll(PP_TR); TR.put("brdy_cetonia_Hull", 10f);
        PPBR_TR = Collections.unmodifiableMap(TR);
    } 
        
    static {
        Map F = new HashMap();
        F.putAll(LT_F); F.putAll(MT_F); F.putAll(HT_F); F.remove("shuttle_Hull");
        F.remove("ox_Standard"); F.remove("dram_Hull"); F.remove("pp_torch_Hull");
        F.remove("omen_Hull"); F.remove("tempest_Hull"); F.remove("hyperion_Hull");
        F.put("lasher_Hull", 5f); F.put("wolf_Hull", 5f); F.put("hound_Hull", 5f);
        F.put("shade_Hull", 1f); F.put("afflictor_Hull", 1f);
        IND_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.putAll(LT_D); D.putAll(MT_D); D.putAll(HT_D);
        D.remove("valkyrie_Hull"); D.remove("crig_Standard"); D.remove("buffalo_Hull");
        D.remove("enforcer_Hull"); D.remove("condor_Hull"); D.remove("phaeton_Hull");
        D.remove("tarsus_Hull"); D.remove("buffalo2_Hull"); D.remove("pp_bull_Hull");
        D.put("mule_Hull", 5f);
        IND_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.putAll(LT_C); C.putAll(MT_C); C.putAll(HT_C);
        C.remove("pp_shark_Hull"); C.put("dominator_Hull", 2.5f); C.put("venture_Hull", 2.5f);
        IND_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.putAll(LT_CS); CS.putAll(MT_CS); CS.putAll(HT_CS);
        CS.remove("onslaught_Hull"); CS.remove("prometheus_Hull"); CS.remove("pp_renegade_Hull");
        CS.remove("atlas_Hull"); CS.remove("astral_Hull"); CS.remove("paragon_Hull");
        CS.put("odyssey_Hull", 2.5f);
        IND_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.putAll(LT_W); W.putAll(MT_W); W.putAll(HT_W);
        W.remove("mining_drone_wing"); W.remove("piranha_wing"); W.remove("longbow_wing");
        W.remove("trident_wing"); W.remove("dagger_wing"); W.put("talon_wing", 2.5f);
        W.put("wasp_wing", 2.5f);
        IND_W= Collections.unmodifiableMap(W);

        Map CRR = new HashMap();
        CRR.put("gemini_Hull", 5f); CRR.put("condor_Hull", 2.5f); CRR.put("venture_Hull", 2.5f);
        IND_CRR = Collections.unmodifiableMap(CRR);

        Map TR = new HashMap();
        TR.put("valkyrie_Hull", 5f); TR.put("dram_Hull", 5f); TR.put("buffalo_Hull", 1f);
        TR.put("mule_Hull", 2.5f); TR.put("venture_Hull", 2.5f); TR.put("tarsus_Hull", 2.5f);
        TR.put("phaeton_Hull", 1f); TR.put("shuttle_Hull", 2.5f); TR.put("gemini_Hull", 2.5f);
        TR.put("syndicate_asp_gigantophis_Hull", 2.5f); TR.put("syndicate_asp_copperhead_Hull", 2.5f); TR.put("syndicate_asp_diamondback_Hull", 2.5f);
        IND_TR = Collections.unmodifiableMap(TR);
    }
    
    static {
        Map F = new HashMap();
        F.putAll(MT_F); F.remove("shuttle_Hull"); F.remove("ox_Standard");
        F.remove("syndicate_asp_diamondback_Hull"); F.put("lasher_Hull", 2.5f); F.put("wolf_Hull", 2.5f);
        SIN_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.putAll(MT_D); D.remove("valkyrie_Hull"); D.remove("crig_Standard");
        D.remove("syndicate_asp_copperhead_Hull"); D.put("medusa_Hull", 2.5f); D.put("gemini_Hull", 3f);
        SIN_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.putAll(MT_C); C.remove("syndicate_asp_gigantophis_Hull");
        SIN_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.putAll(MT_CS); CS.remove("atlas_Hull");
        SIN_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.putAll(MT_W); W.remove("thunder_wing"); W.remove("syndicate_asp_bite_wing");
        W.remove("syndicate_asp_venom_wing"); W.remove("syndicate_asp_constrictor_wing");
        SIN_W= Collections.unmodifiableMap(W);

        Map CRR = new HashMap();
        CRR.put("gemini_Hull", 5f);
        SIN_CRR = Collections.unmodifiableMap(CRR);

        Map TR = new HashMap();
        TR.put("valkyrie_Hull", 5f); TR.put("dram_Hull", 10f);
        SIN_TR = Collections.unmodifiableMap(TR);
    }
    
    static {
        Map F = new HashMap();
        F.putAll(HT_F); F.put("omen_Hull", 5f); F.put("tempest_Hull", 5f);
        TT_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.putAll(HT_D); D.put("sunder_Hull", 1f); D.remove("buffalo_Hull");
        TT_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.putAll(HT_C);
        TT_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.putAll(HT_CS);
        TT_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.putAll(HT_W);
        TT_W= Collections.unmodifiableMap(W);

        Map CRR = new HashMap();
        CRR.put("astral_Hull", 2.5f);
        TT_CRR = Collections.unmodifiableMap(CRR);

        Map TR = new HashMap();
        TR.put("buffalo_Hull", 10f);
        TT_TR = Collections.unmodifiableMap(TR);
    }
    
    static {
        Map LG = new HashMap();
        LG.put("crig_Standard", 5f); LG.put("ox_Standard", 10f);
        CIV_LG = Collections.unmodifiableMap(LG);

        Map W = new HashMap();
        W.put("mining_drone_wing", 10f);
        CIV_W= Collections.unmodifiableMap(W);
    }
    
    static {
        Map F = new HashMap();
        F.putAll(PCKT_F); F.remove("pack_samoyed_Hull"); F.remove("pack_samoyed_decoupled_Hull");
        PCK_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.putAll(PCKT_D);
        PCK_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        PCK_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        PCK_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        PCK_W= Collections.unmodifiableMap(W);

        Map TR = new HashMap();
        TR.put("pack_samoyed_Hull", 5f); TR.put("pack_samoyed_decoupled_Hull", 2.5f);
        PCK_TR = Collections.unmodifiableMap(TR);
    }
    
    static {
        Map F = new HashMap();
        F.putAll(JPT_F); F.remove("junk_pirates_stoatA_Hull"); F.remove("junk_pirates_stoatB_Hull");
        JP_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.putAll(JPT_D);
        JP_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.putAll(JPT_C);
        JP_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.putAll(JPT_CS);
        JP_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.putAll(JPT_W);
        JP_W= Collections.unmodifiableMap(W);

        Map U = new HashMap();
        U.put("junk_pirates_the_reaper_Hull", 5f);
        JP_U = Collections.unmodifiableMap(U);

        Map TR = new HashMap();
        TR.put("junk_pirates_stoatB_Hull", 5f); TR.put("junk_pirates_stoatA_Hull", 5f);
        JP_TR = Collections.unmodifiableMap(TR);
    }
    
    static {
        Map F = new HashMap();
        F.putAll(SHIT_F);
        SHI_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.putAll(SHIT_D); D.remove("ms_lambent_Hull"); D.remove("ms_solidarity_Hull");
        D.remove("ms_potnia_Hull");
        SHI_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.putAll(SHIT_C);
        SHI_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.putAll(SHIT_CS);
        SHI_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.putAll(SHIT_W);
        SHI_W= Collections.unmodifiableMap(W);

        Map CRR = new HashMap();
        CRR.put("ms_sargasso_Hull", 5f);
        SHI_CRR = Collections.unmodifiableMap(CRR);

        Map TR = new HashMap();
        TR.put("ms_lambent_Hull", 5f); TR.put("ms_solidarity_Hull", 5f); TR.put("ms_potnia_Hull", 5f);
        SHI_TR = Collections.unmodifiableMap(TR);

        Map U = new HashMap();
        U.put("ms_mimirBaus_Hull", 5f);
        SHI_U = Collections.unmodifiableMap(U);
    }
    
    static {
        Map F = new HashMap();
        F.putAll(BRT_F);
        BR_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.putAll(BRT_D);
        D.remove("brdy_cetonia_Hull");
        BR_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.putAll(BRT_C);
        BR_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.putAll(BRT_CS);
        BR_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.putAll(BRT_W);
        BR_W= Collections.unmodifiableMap(W);

        Map CRR = new HashMap();
        CRR.put("brdy_typheus_Hull", 2.5f); CRR.put("brdy_revenant_Hull", 1f);
        BR_CRR = Collections.unmodifiableMap(CRR);

        Map TR = new HashMap();
        TR.put("brdy_cetonia_Hull", 2.5f);
        BR_TR = Collections.unmodifiableMap(TR);

        Map U = new HashMap();
        U.put("nevermoreB_Hull", 1f); U.put("brdy_stormcrow_Hull", 1f);
        BR_U = Collections.unmodifiableMap(U);
    }
    
    static {
        Map F = new HashMap();
        F.putAll(NOMT_F);
        NOM_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.putAll(NOMT_D);
        NOM_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.putAll(NOMT_C);
        NOM_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.putAll(NOMT_CS);
        NOM_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.putAll(NOMT_W);
        NOM_W = Collections.unmodifiableMap(W);
    }
    
    static {
        Map F = new HashMap();
        F.putAll(KADT_F); F.remove("khs_mutt_Hull"); F.remove("khs_mistral_Hull");
        F.remove("khs_dolphin_Hull");
        KAD_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.putAll(KADT_D); D.remove("khs_sirocco_Hull"); D.remove("khs_djinn_Hull");
        KAD_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.putAll(KADT_C);
        KAD_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.putAll(KADT_CS);
        KAD_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.putAll(KADT_W);
        KAD_W= Collections.unmodifiableMap(W);

        Map TR = new HashMap();
        TR.put("khs_mistral_Hull", 3.5f); TR.put("khs_sirocco_Hull", 3.5f); TR.put("khs_djinn_Hull", 2.5f);
        TR.put("khs_mutt_Hull", 5f); TR.put("atlas_Hull", 2.5f); TR.put("prometheus_Hull", 2.5f);
        KAD_TR = Collections.unmodifiableMap(TR);

        Map U = new HashMap();
        U.put("khs_dolphin_Hull", 5f);
        KAD_LG = Collections.unmodifiableMap(U);
    }
    
    static {
        Map F = new HashMap();
        F.putAll(KADT_F); F.remove("khs_mutt_Hull"); F.remove("khs_mistral_Hull");
        F.remove("khs_dolphin_Hull"); F.remove("khs_camel_Hull"); F.remove("khs_jackal_Hull");
        INS_F = Collections.unmodifiableMap(F);

        Map D = new HashMap();
        D.putAll(KADT_D); D.remove("khs_sirocco_Hull"); D.remove("khs_djinn_Hull");
        D.remove("khs_targe_Hull"); D.remove("khs_hauberk_Hull"); D.put("enforcer_Hull", 2.5f);
        D.put("buffalo2_Hull", 2.5f);
        INS_D = Collections.unmodifiableMap(D);

        Map C = new HashMap();
        C.putAll(KADT_C); C.remove("khs_rukh_Hull"); C.put("dominator_Hull", 2.5f);
        INS_C = Collections.unmodifiableMap(C);

        Map CS = new HashMap();
        CS.putAll(KADT_CS); CS.remove("khs_caliph_Hull");
        INS_CS = Collections.unmodifiableMap(CS);

        Map W = new HashMap();
        W.putAll(HE_W);
        INS_W= Collections.unmodifiableMap(W);

        Map TR = new HashMap();
        TR.put("khs_mistral_Hull", 3.5f); TR.put("khs_sirocco_Hull", 3.5f); TR.put("khs_djinn_Hull", 2.5f);
        TR.put("khs_mutt_Hull", 5f);
        INS_TR = Collections.unmodifiableMap(TR);

        Map U = new HashMap();
        U.put("khs_dolphin_Hull", 5f);
        INS_LG = Collections.unmodifiableMap(U);
    }
    
    static {
        Map W_map = new HashMap();
        W_map.putAll(LT_W);
        W_map.putAll(MT_W);
        W_map.putAll(HT_W);
        W_map.putAll(PCKT_W);
        W_map.putAll(JPT_W);
        W_map.putAll(SHIT_W);
        W_map.putAll(BRT_W);  
        W_map.putAll(NOMT_W);
        W_map.putAll(KADT_W);
        ALL_W_FINAL = Collections.unmodifiableMap(W_map);

        Map F_map = new HashMap();
        F_map.putAll(LT_F);
        F_map.putAll(MT_F);
        F_map.putAll(HT_F);
        F_map.putAll(PCKT_F);
        F_map.putAll(JPT_F);
        F_map.putAll(SHIT_F);
        F_map.putAll(BRT_F);  
        F_map.putAll(NOMT_F); 
        F_map.putAll(KADT_F); 
        ALL_F_FINAL = Collections.unmodifiableMap(F_map);

        Map D_map = new HashMap();
        D_map.putAll(LT_D);
        D_map.putAll(MT_D);
        D_map.putAll(HT_D);
        D_map.putAll(PCKT_D);
        D_map.putAll(JPT_D);
        D_map.putAll(SHIT_D);
        D_map.putAll(BRT_D);   
        D_map.putAll(NOMT_D);
        D_map.putAll(KADT_D);
        ALL_D_FINAL = Collections.unmodifiableMap(D_map);

        Map C_map = new HashMap();
        C_map.putAll(LT_C);
        C_map.putAll(MT_C);
        C_map.putAll(HT_C);
        C_map.putAll(PCKT_C);
        C_map.putAll(JPT_C);
        C_map.putAll(SHIT_C);
        C_map.putAll(BRT_C);       
        C_map.putAll(NOMT_C);
        C_map.putAll(KADT_C);
        ALL_C_FINAL = Collections.unmodifiableMap(C_map);

        Map CS_map = new HashMap();
        CS_map.putAll(LT_CS);
        CS_map.putAll(MT_CS);
        CS_map.putAll(HT_CS);
        CS_map.putAll(PCKT_CS);
        CS_map.putAll(JPT_CS);
        CS_map.putAll(SHIT_CS);
        CS_map.putAll(BRT_CS);     
        CS_map.putAll(NOMT_CS); 
        CS_map.putAll(KADT_CS);
        ALL_CS_FINAL = Collections.unmodifiableMap(CS_map);
    }
        
    static {
        Map allVariantsTemp = new HashMap();        
        allVariantsTemp.put("brdy_cetonia",createVariantList(new String[]{"brdy_cetonia_standard"}));
        allVariantsTemp.put("brdy_karkinos",createVariantList(new String[]{"brdy_karkinos_assault", "brdy_karkinos_prototype"}));
        allVariantsTemp.put("brdy_kurmaraja",createVariantList(new String[]{"brdy_kurmaraja_elite"}));
        allVariantsTemp.put("brdy_locust",createVariantList(new String[]{"brdy_locust_patrol", "brdy_locust_strike", "brdy_locust_wing"}));
        allVariantsTemp.put("brdy_mantis",createVariantList(new String[]{"brdy_mantis_elite", "brdy_mantis_attack", "brdy_mantis_strike"}));
        allVariantsTemp.put("brdy_revenant",createVariantList(new String[]{"brdy_revenant_carrier"}));
        allVariantsTemp.put("brdy_robberfly",createVariantList(new String[]{"brdy_robberfly_cs", "brdy_robberfly_light", "brdy_robberfly_strike"}));
        allVariantsTemp.put("brdy_stenos",createVariantList(new String[]{"brdy_stenos_exploration"}));	
        allVariantsTemp.put("brdy_stormcrow",createVariantList(new String[]{"brdy_stormcrow_cyc"}));
        allVariantsTemp.put("brdy_typheus",createVariantList(new String[]{"brdy_typheus_elite", "brdy_typheus_support"}));
        allVariantsTemp.put("brdy_gonodactylus",createVariantList(new String[]{"gonodactylus_assault", "gonodactylus_CS"}));
        allVariantsTemp.put("brdy_nevermore",createVariantList(new String[]{"nevermore_advanced", "nevermore_assault", "nevermore_tac"}));
        allVariantsTemp.put("nevermoreB",createVariantList(new String[]{"nevermoreB_prototype"}));
        allVariantsTemp.put("brdy_desdinova",createVariantList(new String[]{"desdinova_assault", "desdinova_cs", "desdinova_fastattack","desdinova_HK"}));
        allVariantsTemp.put("brdy_scarab",createVariantList(new String[]{"scarab_closesupport", "scarab_firesupport", "scarab_pd","scarab_strike","scarab_attack"}));
        Variants_BR = Collections.unmodifiableMap(allVariantsTemp);
    }
    
    static {
        Map allVariantsTemp = new HashMap();
        allVariantsTemp.put("ms_charybdis",createVariantList(new String[]{"ms_charybdis_Attack", "ms_charybdis_Balanced", "ms_charybdis_CS", "ms_charybdis_PD", "ms_charybdis_Standard"}));
        allVariantsTemp.put("ms_elysium",createVariantList(new String[]{"ms_elysium_Assault", "ms_elysium_CS", "ms_elysium_PD", "ms_elysium_Standard", "ms_elysium_Strike"}));
        allVariantsTemp.put("ms_enlil",createVariantList(new String[]{"ms_enlil_AF", "ms_enlil_Attack", "ms_enlil_Balanced", "ms_enlil_CS", "ms_enlil_LRM", "ms_enlil_PD", "ms_enlil_Standard", "ms_enlil_Strike"}));
        allVariantsTemp.put("ms_inanna",createVariantList(new String[]{"ms_inanna_Assault", "ms_inanna_CS", "ms_inanna_EMP", "ms_inanna_Standard", "ms_inanna_Strike"}));
        allVariantsTemp.put("ms_lambent",createVariantList(new String[]{"ms_lambent_Standard"}));
        allVariantsTemp.put("ms_mimir",createVariantList(new String[]{"ms_mimir_Assault", "ms_mimir_CS", "ms_mimir_PD", "ms_mimir_Standard"}));
        allVariantsTemp.put("ms_mimirBaus",createVariantList(new String[]{"ms_mimirBaus_Baus"}));
        allVariantsTemp.put("ms_morningstar",createVariantList(new String[]{"ms_morningstar_AF", "ms_morningstar_Assault", "ms_morningstar_CS", "ms_morningstar_PD", "ms_morningstar_Standard", "ms_morningstar_Strike"}));
        allVariantsTemp.put("ms_sargasso",createVariantList(new String[]{"ms_sargasso_Assault", "ms_sargasso_Balanced", "ms_sargasso_EMP", "ms_sargasso_LRM", "ms_sargasso_Standard"}));
        allVariantsTemp.put("ms_seski",createVariantList(new String[]{"ms_seski_Attack", "ms_seski_BR", "ms_seski_CS", "ms_seski_Standard"}));
        allVariantsTemp.put("ms_shamash",createVariantList(new String[]{"ms_shamash_Attack", "ms_shamash_CS", "ms_shamash_EMP", "ms_shamash_Standard"}));
        allVariantsTemp.put("ms_tartarus",createVariantList(new String[]{"ms_tartarus_AF", "ms_tartarus_Assault", "ms_tartarus_CS", "ms_tartarus_Standard"}));
        allVariantsTemp.put("ms_scylla",createVariantList(new String[]{"ms_scylla_Assault", "ms_scylla_Beam", "ms_scylla_Standard"}));
        allVariantsTemp.put("ms_solidarity",createVariantList(new String[]{"ms_solidarity_Standard","ms_solidarity_Fast"}));
        allVariantsTemp.put("ms_potnia",createVariantList(new String[]{"ms_potnia_Standard"}));
        Variants_SHI = Collections.unmodifiableMap(allVariantsTemp);
    }
    static {
        Map allVariantsTemp = new HashMap();
        allVariantsTemp.put("atlas",createVariantList(new String[]{"atlas_Standard"}));
        allVariantsTemp.put("dominator",createVariantList(new String[]{"dominator_ass", "dominator_pun", "dominator_sho", "dominator_sni", "dominator_sto","dominatorhe_sup", "dominatorhe_ult"}));
        allVariantsTemp.put("dram",createVariantList(new String[]{"dram_ass", "dram_lig", "dram_sni", "dram_sto", "dram_sup"}));
        allVariantsTemp.put("enforcer",createVariantList(new String[]{"enforcer_ass", "enforcer_out", "enforcer_sho", "enforcer_sni", "enforcer_str","enforcerhe_cru", "enforcerhe_tor"}));
        allVariantsTemp.put("hound",createVariantList(new String[]{"hound_ass", "hound_hvs", "hound_rai", "hound_sni", "hound_sto"}));
        allVariantsTemp.put("lasher",createVariantList(new String[]{"lasher_bul", "lasher_hun", "lasher_nee", "lasher_pd", "lasher_pun", "lasher_sup", "lasher_trr"}));
        allVariantsTemp.put("mule",createVariantList(new String[]{"mulep_ass", "mulep_eli", "mulep_out", "mulep_sta", "mulep_sup"}));
        allVariantsTemp.put("onslaught",createVariantList(new String[]{"onslaught_bul", "onslaught_eli", "onslaught_out", "onslaught_pun", "onslaught_sni", "onslaught_sta", "onslaught_sto","onslaughthe_hur", "onslaughthe_tsu"}));	
        allVariantsTemp.put("phaeton",createVariantList(new String[]{"phaetoni_har", "phaetoni_sni", "phaetoni_sta", "phaetoni_sto", "phaetoni_str"}));
        allVariantsTemp.put("condor",createVariantList(new String[]{"condor_ass", "condor_out", "condor_sni", "condor_sto", "condor_str"}));
        allVariantsTemp.put("tarsus",createVariantList(new String[]{"tarsusi_ass", "tarsusi_com", "tarsusi_sni", "tarsusi_sto", "tarsusi_sup"}));
        allVariantsTemp.put("valkyrie",createVariantList(new String[]{"valkyriei_sto", "valkyriei_def", "valkyriei_eli", "valkyriei_run", "valkyriei_sni"}));
        Variants_HE = Collections.unmodifiableMap(allVariantsTemp);
    }
        
    static {
        Map allVariantsTemp = new HashMap();
        allVariantsTemp.put("phaeton",createVariantList(new String[]{"phaetoni_har", "phaetoni_sni", "phaetoni_sta", "phaetoni_sto", "phaetoni_str"}));
        allVariantsTemp.put("atlas",createVariantList(new String[]{"atlas_Standard"}));
        allVariantsTemp.put("doom",createVariantList(new String[]{"doomi_ass", "doomi_bul", "doomi_pun", "doomi_str"}));
        allVariantsTemp.put("dram",createVariantList(new String[]{"drami_ass", "drami_out", "drami_pd", "drami_pun", "drami_sup"}));
        allVariantsTemp.put("eagle",createVariantList(new String[]{"eaglei_ass", "eaglei_bal", "eaglei_pul", "eaglei_pun", "eaglei_sni", "eaglei_sto", "eaglei_str"}));
        allVariantsTemp.put("falcon",createVariantList(new String[]{"falconi_att", "falconi_bal", "falconi_cs", "falconi_out", "falconi_pun", "falconi_sni", "falconi_sta", "falconi_sto"}));
        allVariantsTemp.put("gemini",createVariantList(new String[]{"geminii_cla", "geminii_cs", "geminii_out", "geminii_sni", "geminii_sta", "geminii_sto", "geminii_str", "geminii_sup"}));
        allVariantsTemp.put("hammerhead",createVariantList(new String[]{"hammerheadi_bal", "hammerheadi_bul", "hammerheadi_eli", "hammerheadi_out", "hammerheadi_pun", "hammerheadi_sni", "hammerheadi_str","hammerheadie_sup", "hammerheadie_twi"}));
        allVariantsTemp.put("hound",createVariantList(new String[]{"houndi_ass", "houndi_hes", "houndi_mac", "houndi_str"}));
        allVariantsTemp.put("lasher",createVariantList(new String[]{"lasheri_def", "lasheri_pus", "lasheri_str"}));
        allVariantsTemp.put("medusa",createVariantList(new String[]{"medusai_att", "medusai_cs", "medusai_pd", "medusai_sho", "medusai_str"}));	
        allVariantsTemp.put("odyssey",createVariantList(new String[]{"odysseyi_bea", "odysseyi_sho", "odysseyi_str"}));	
        allVariantsTemp.put("mule",createVariantList(new String[]{"mulei_ass", "mulei_bur", "mulei_sho", "mulei_sni", "mulei_sto"}));
        allVariantsTemp.put("shade",createVariantList(new String[]{"shadei_ass", "shadei_hun", "shadei_sho", "shadei_sni", "shadei_sto"}));
        allVariantsTemp.put("shuttle",createVariantList(new String[]{"shuttlei_ass", "shuttlei_att", "shuttlei_bur", "shuttlei_pd", "shuttlei_sni", "shuttlei_sta", "shuttlei_sto", "shuttlei_sup"}));
        allVariantsTemp.put("sunder",createVariantList(new String[]{"sunderi_ass", "vbul", "sunderi_cs", "sunderi_hun", "sunderi_pla", "sunderi_pul", "sunderi_str"}));
        allVariantsTemp.put("tarsus",createVariantList(new String[]{"tarsusi_ass", "tarsusi_com", "tarsusi_sni", "tarsusi_sto", "tarsusi_sup"}));
        allVariantsTemp.put("afflictor",createVariantList(new String[]{"afflictori_ass", "afflictori_hun", "afflictori_str"}));
        allVariantsTemp.put("apogee",createVariantList(new String[]{"apogeei_ass", "apogeei_bal", "apogeei_cru", "apogeei_pun", "apogeei_str"}));
        allVariantsTemp.put("aurora",createVariantList(new String[]{"aurorai_ass", "aurorai_att", "aurorai_str"}));
        allVariantsTemp.put("brawler",createVariantList(new String[]{"brawleri_ass", "brawleri_bul", "brawleri_old", "brawleri_out", "brawleri_pun", "brawleri_sni", "brawleri_sto"}));
        allVariantsTemp.put("buffalo",createVariantList(new String[]{"buffaloi_ass", "buffaloi_pd", "buffaloi_sta", "buffaloi_str", "buffaloi_sup"}));//TT
        allVariantsTemp.put("conquest",createVariantList(new String[]{"conquesti_ass", "conquesti_bul", "conquesti_eli", "conquesti_out", "conquesti_sho", "conquesti_sni", "conquesti_sto"}));
        allVariantsTemp.put("dominator",createVariantList(new String[]{"dominatori_ass", "dominatori_sho", "dominatori_sni", "dominatori_str"}));
        allVariantsTemp.put("wolf",createVariantList(new String[]{"wolfi_ass", "wolfi_cs", "wolfi_pd", "wolfi_pom", "wolfi_str"}));
        allVariantsTemp.put("vigilance",createVariantList(new String[]{"vigilancei_ass", "vigilancei_fis", "vigilancei_hun", "vigilancei_inh", "vigilancei_pat", "vigilancei_str", "vigilancei_sup"}));
        allVariantsTemp.put("venture",createVariantList(new String[]{"venturei_pd", "venturei_pun", "venturei_sni", "venturei_sto", "venturei_sup"}));
        allVariantsTemp.put("valkyrie",createVariantList(new String[]{"valkyriei_sto", "valkyriei_def", "valkyriei_eli", "valkyriei_run", "valkyriei_sni"}));
        allVariantsTemp.put("syndicate_asp_copperhead",createVariantList(new String[]{"syndicate_asp_copperhead_Standard"}));
        allVariantsTemp.put("syndicate_asp_diamondback",createVariantList(new String[]{"syndicate_asp_diamondback_Standard"}));
        allVariantsTemp.put("syndicate_asp_gigantophis",createVariantList(new String[]{"syndicate_asp_gigantophis_Standard"}));
        Variants_IND = Collections.unmodifiableMap(allVariantsTemp);
    }
                     
    static {
        Map allVariantsTemp = new HashMap();
        allVariantsTemp.put("nom_wurm", "nom_wurm_assault");
        allVariantsTemp.put("nom_yellowjacket", "nom_yellowjacket_sniper");
        allVariantsTemp.put("nom_death_bloom", "nom_death_bloom_strike");
        allVariantsTemp.put("nom_scorpion", "nom_scorpion_assault");
        allVariantsTemp.put("nom_komodo", "nom_komodo_mk2_assault");
        allVariantsTemp.put("nom_komodo", "nom_komodo_assault");
        allVariantsTemp.put("nom_flycatcher", "nom_flycatcher_carrier");
        allVariantsTemp.put("nom_roadrunner", "nom_roadrunner_pursuit");
        allVariantsTemp.put("nom_rattlesnake", "nom_rattlesnake_assault");
        allVariantsTemp.put("nom_sandstorm", "nom_sandstorm_assault");
        allVariantsTemp.put("nom_gila_monster", "nom_gila_monster_antibattleship");
        Variants_NOM = Collections.unmodifiableMap(allVariantsTemp);
    }
        
    static {
        Map allVariantsTemp = new HashMap();
        allVariantsTemp.put("brdy_cetonia",createVariantList(new String[]{"brdy_cetonia_standard"}));
        allVariantsTemp.put("brdy_robberfly",createVariantList(new String[]{"brdy_robberfly_cs", "brdy_robberfly_light", "brdy_robberfly_strike"}));
        allVariantsTemp.put("brdy_typheus",createVariantList(new String[]{"brdy_typheus_elite", "brdy_typheus_support"}));
        allVariantsTemp.put("brdy_gonodactylus",createVariantList(new String[]{"gonodactylus_assault", "gonodactylus_CS"}));

        allVariantsTemp.put("condor",createVariantList(new String[]{"condorp_ass", "condorp_fis", "condorp_pd", "condorp_str", "condorp_sup"}));
        allVariantsTemp.put("buffalo2",createVariantList(new String[]{"buffalo2p_ass", "buffalo2p_har", "buffalo2p_spa", "buffalo2p_str", "buffalo2p_sup"}));
        allVariantsTemp.put("enforcer",createVariantList(new String[]{"enforcerp_ass", "enforcerp_bal", "enforcerp_cs", "enforcerp_eli", "enforcerp_hun", "enforcerp_out", "enforcerp_str","enforcerpe_blb", "enforcerpe_heh"}));
        allVariantsTemp.put("hound",createVariantList(new String[]{"houndp_ass", "houndp_out", "houndp_rac", "houndp_str"}));
        allVariantsTemp.put("lasher",createVariantList(new String[]{"lasherp_ass", "lasherp_ast", "lasherp_cls", "lasherp_sal", "lasherp_sho", "lasherp_sto", "lasherp_str"}));
        allVariantsTemp.put("mule",createVariantList(new String[]{"mulep_ass", "mulep_eli", "mulep_out", "mulep_sta", "mulep_sup"}));
        allVariantsTemp.put("tarsus",createVariantList(new String[]{"tarsusp_ass", "tarsusp_out", "tarsusp_pd", "tarsusp_sta", "tarsusp_str"}));
        allVariantsTemp.put("dominator",createVariantList(new String[]{"dominatorp_ass", "dominatorp_che", "dominatorp_hun", "dominatorp_out", "dominatorp_sho", "dominatorp_str", "dominatorp_sup","dominatorpe_dev", "dominatorpe_rin"}));
        allVariantsTemp.put("venture",createVariantList(new String[]{"venturep_ass", "venturep_bal", "venturep_out", "venturep_pun", "venturep_sup"}));

        allVariantsTemp.put("ms_enlil",createVariantList(new String[]{"ms_enlil_AF", "ms_enlil_Attack", "ms_enlil_Balanced", "ms_enlil_CS", "ms_enlil_LRM", "ms_enlil_PD", "ms_enlil_Standard", "ms_enlil_Strike"}));
        allVariantsTemp.put("ms_sargasso",createVariantList(new String[]{"ms_sargasso_Assault", "ms_sargasso_Balanced", "ms_sargasso_EMP", "ms_sargasso_LRM", "ms_sargasso_Standard"}));
        allVariantsTemp.put("ms_seski",createVariantList(new String[]{"ms_seski_Attack", "ms_seski_BR", "ms_seski_CS", "ms_seski_Standard"}));
        allVariantsTemp.put("ms_solidarity",createVariantList(new String[]{"ms_solidarity_Standard"}));
        allVariantsTemp.put("ms_potniaBis",createVariantList(new String[]{"ms_potniaBis_AS", "ms_potniaBis_Attack", "ms_potniaBis_CS", "ms_potniaBis_FS"}));

        allVariantsTemp.put("pp_bull",createVariantList(new String[]{"pp_bull_assault", "pp_bull_cs", "pp_bull_raider"}));
        allVariantsTemp.put("pp_renegade",createVariantList(new String[]{"pp_renegade_flagship", "pp_renegade_pillager", "pp_renegade_raider"}));
        allVariantsTemp.put("pp_shark",createVariantList(new String[]{"pp_shark_balanced", "pp_shark_fs", "pp_shark_raider"}));
        allVariantsTemp.put("pp_torch",createVariantList(new String[]{"pp_torch_assault"}));
        Variants_PP = Collections.unmodifiableMap(allVariantsTemp);
    }
        
    static {
        Map allVariantsTemp = new HashMap();
        allVariantsTemp.put("odyssey",createVariantList(new String[]{"odyssey_ass", "odyssey_bul", "odyssey_sni", "odyssey_str","odysseyte_eli", "odysseyte_ult"}));
        allVariantsTemp.put("omen",createVariantList(new String[]{"omen_att", "omen_hun", "omen_pd", "omen_sho", "omen_str"}));
        allVariantsTemp.put("tempest",createVariantList(new String[]{"tempest_att", "tempest_bea", "tempest_hun", "tempest_pd", "tempest_pun", "tempest_sho", "tempest_str"}));
        allVariantsTemp.put("afflictor",createVariantList(new String[]{"afflictor_bea", "afflictor_hun", "afflictor_out", "afflictor_sni", "afflictor_sto", "afflictor_str", "afflictor_sup"}));
        allVariantsTemp.put("astral",createVariantList(new String[]{"astral_att", "astral_eli", "astral_pd"}));
        allVariantsTemp.put("wolf",createVariantList(new String[]{"wolf_ass", "wolf_bea", "wolf_bul", "wolf_pun", "wolf_str"}));
        allVariantsTemp.put("shade",createVariantList(new String[]{"shade_pd", "shade_pun", "shade_str", "shade_sup", "shade_swa"}));
        allVariantsTemp.put("paragon",createVariantList(new String[]{"paragon_ass", "paragon_bul", "paragon_eli", "paragon_hun", "paragon_str","paragonte_sup", "paragonte_ult"}));
        allVariantsTemp.put("medusa",createVariantList(new String[]{"medusa_ass", "medusa_att", "medusa_bul", "medusa_sho", "medusa_str", "medusate_eli", "medusate_sup"}));
        allVariantsTemp.put("hyperion",createVariantList(new String[]{"hyperion_att", "hyperion_hun", "hyperion_out", "hyperion_pun", "hyperion_str"}));
        allVariantsTemp.put("doom",createVariantList(new String[]{"doom_ass", "doom_hun", "doom_sho", "doom_str"}));
        allVariantsTemp.put("aurora",createVariantList(new String[]{"aurora_ass", "aurora_att", "aurora_bal", "aurora_sta", "aurora_str"}));
        allVariantsTemp.put("buffalo",createVariantList(new String[]{"buffaloi_ass", "buffaloi_pd", "buffaloi_sta", "buffaloi_str", "buffaloi_sup"}));
        allVariantsTemp.put("sunder",createVariantList(new String[]{"sunderi_ass", "vbul", "sunderi_cs", "sunderi_hun", "sunderi_pla", "sunderi_pul", "sunderi_str"}));
        Variants_TT = Collections.unmodifiableMap(allVariantsTemp);
    }
        
    static {
        Map allVariantsTemp = new HashMap();
        allVariantsTemp.put("pack_bedlington",createVariantList(new String[]{"pack_bedlington_Standard"}));
        allVariantsTemp.put("pack_BRT",createVariantList(new String[]{"pack_BRT_Guard", "pack_BRT_Strike", "pack_BRT_Support"}));
        allVariantsTemp.put("pack_pitbull",createVariantList(new String[]{"pack_pitbull_Standard"}));
        allVariantsTemp.put("pack_ridgeback",createVariantList(new String[]{"pack_ridgeback_Standard"}));
        allVariantsTemp.put("pack_ridgeback_x",createVariantList(new String[]{"pack_ridgeback_x_Standard"}));
        allVariantsTemp.put("pack_wirefox",createVariantList(new String[]{"pack_wirefox_Assault", "pack_wirefox_Standard"}));
        allVariantsTemp.put("pack_komondor",createVariantList(new String[]{"pack_komondor_Standard", "pack_komondor_Strike", "pack_komondor_Support"}));
        allVariantsTemp.put("pack_samoyed_decoupled",createVariantList(new String[]{"pack_samoyed_decoupled_Standard"}));
        allVariantsTemp.put("pack_samoyed",createVariantList(new String[]{"pack_samoyed_Standard", "pack_samoyed_Support"}));
        Variants_PCK = Collections.unmodifiableMap(allVariantsTemp);
    }
        
    static {
        Map allVariantsTemp = new HashMap();
        allVariantsTemp.put("junk_pirates_sickle",createVariantList(new String[]{"junk_pirates_sickle_Pointdefense","junk_pirates_sickle_Standard","junk_pirates_sickle_Strike"}));
        allVariantsTemp.put("junk_pirates_hammer",createVariantList(new String[]{"junk_pirates_hammer_Assault", "junk_pirates_hammer_Strike"}));
        allVariantsTemp.put("junk_pirates_clam",createVariantList(new String[]{"junk_pirates_clam_CS","junk_pirates_clam_Standard"}));
        allVariantsTemp.put("junk_pirates_stoatB",createVariantList(new String[]{"junk_pirates_stoatB_Standard"}));
        allVariantsTemp.put("junk_pirates_stoatA",createVariantList(new String[]{"junk_pirates_stoatA_Standard"}));
        allVariantsTemp.put("junk_pirates_octopus",createVariantList(new String[]{"junk_pirates_octopus_Standard"}));
        allVariantsTemp.put("junk_pirates_boxenstein",createVariantList(new String[]{"junk_pirates_boxenstein_Slugger", "junk_pirates_boxenstein_Support"}));
        allVariantsTemp.put("junk_pirates_boxer",createVariantList(new String[]{"junk_pirates_boxer_Fighter", "junk_pirates_boxer_Standard"}));
        allVariantsTemp.put("junk_pirates_langoustine",createVariantList(new String[]{"junk_pirates_langoustine_CS", "junk_pirates_langoustine_Standard","junk_pirates_langoustine_Strike"}));
        allVariantsTemp.put("junk_pirates_scythe",createVariantList(new String[]{"junk_pirates_scythe_Assault", "junk_pirates_scythe_Standard"}));
        allVariantsTemp.put("junk_pirates_turbot",createVariantList(new String[]{"junk_pirates_turbot_Assault", "junk_pirates_turbot_Strike"}));
        allVariantsTemp.put("junk_pirates_dugong",createVariantList(new String[]{"junk_pirates_dugong_Standard", "junk_pirates_dugong_Support"}));
        allVariantsTemp.put("junk_pirates_orca",createVariantList(new String[]{"junk_pirates_orca_Assault", "junk_pirates_orca_Standard"}));
        allVariantsTemp.put("junk_pirates_goat",createVariantList(new String[]{"junk_pirates_goat_Standard", "junk_pirates_goat_CS"}));
        allVariantsTemp.put("junk_pirates_kraken",createVariantList(new String[]{"junk_pirates_kraken_CS", "junk_pirates_kraken_Standard"}));
        allVariantsTemp.put("junk_pirates_the_reaper",createVariantList(new String[]{"junk_pirates_the_reaper_Standard"}));
        Variants_JP = Collections.unmodifiableMap(allVariantsTemp);
    }        
        
    static {
        Map allVariantsTemp = new HashMap();
        allVariantsTemp.put("khs_hyena",createVariantList(new String[]{"khs_hyena_Picket"}));
        allVariantsTemp.put("khs_jackal",createVariantList(new String[]{"khs_jackal_Scout"}));
        allVariantsTemp.put("khs_vulture",createVariantList(new String[]{"khs_vulture_Fighter_Support"}));
        allVariantsTemp.put("khs_camel",createVariantList(new String[]{"khs_camel_Combat"}));
        allVariantsTemp.put("khs_rukh",createVariantList(new String[]{"khs_rukh_Fast_Attack"}));
        allVariantsTemp.put("khs_falchion",createVariantList(new String[]{"khs_falchion_firesupport"}));
        allVariantsTemp.put("khs_buzzard",createVariantList(new String[]{"khs_buzzard_Strike"}));
        allVariantsTemp.put("khs_golem",createVariantList(new String[]{"khs_golem_escort"}));
        allVariantsTemp.put("khs_targe",createVariantList(new String[]{"khs_targe_Shield"}));
        allVariantsTemp.put("khs_hauberk",createVariantList(new String[]{"khs_hauberk_EWAR"}));
        allVariantsTemp.put("khs_seraph",createVariantList(new String[]{"khs_seraph_Command"}));
        allVariantsTemp.put("khs_caliph",createVariantList(new String[]{"khs_caliph_Assault"}));
        allVariantsTemp.put("khs_dolphin",createVariantList(new String[]{"khs_dolphin_Ramming"}));
        allVariantsTemp.put("khs_sphinx",createVariantList(new String[]{"khs_sphinx_fastattack"}));
        allVariantsTemp.put("khs_behemoth",createVariantList(new String[]{"khs_behemoth_Support"}));
        allVariantsTemp.put("khs_leviathan",createVariantList(new String[]{"khs_leviathan_outdated"}));
        allVariantsTemp.put("khs_mutt",createVariantList(new String[]{"khs_mutt_Tramp"}));
        allVariantsTemp.put("khs_sirocco",createVariantList(new String[]{"khs_sirocco_Standard"}));
        allVariantsTemp.put("khs_mistral",createVariantList(new String[]{"khs_mistral_Light"}));
        allVariantsTemp.put("khs_djinn",createVariantList(new String[]{"khs_djinn_Standard"}));

        allVariantsTemp.put("atlas",createVariantList(new String[]{"atlas_Standard"}));
        allVariantsTemp.put("prometheus",createVariantList(new String[]{"prometheus_Super"}));
        Variants_KAD = Collections.unmodifiableMap(allVariantsTemp);
    }
        
    static {
        Map allVariantsTemp = new HashMap();
        allVariantsTemp.put("buffalo2",createVariantList(new String[]{"buffalo2p_ass", "buffalo2p_har", "buffalo2p_spa", "buffalo2p_str", "buffalo2p_sup"}));
        allVariantsTemp.put("enforcer",createVariantList(new String[]{"enforcerp_ass", "enforcerp_bal", "enforcerp_cs", "enforcerp_eli", "enforcerp_hun", "enforcerp_out", "enforcerp_str","enforcerpe_blb", "enforcerpe_heh"}));
        allVariantsTemp.put("dominator",createVariantList(new String[]{"dominatorp_ass", "dominatorp_che", "dominatorp_hun", "dominatorp_out", "dominatorp_sho", "dominatorp_str", "dominatorp_sup","dominatorpe_dev", "dominatorpe_rin"}));

        allVariantsTemp.put("khs_hyena",createVariantList(new String[]{"khs_hyena_acehigh"}));
        allVariantsTemp.put("khs_vulture",createVariantList(new String[]{"khs_vulture_acehigh"}));
        allVariantsTemp.put("khs_falchion",createVariantList(new String[]{"khs_falchion_acehigh"}));
        allVariantsTemp.put("khs_buzzard",createVariantList(new String[]{"khs_buzzard_pd"}));
        allVariantsTemp.put("khs_golem",createVariantList(new String[]{"khs_golem_acehigh"}));
        allVariantsTemp.put("khs_seraph",createVariantList(new String[]{"khs_seraph_acehigh"}));
        allVariantsTemp.put("khs_dolphin",createVariantList(new String[]{"khs_dolphin_acehigh"}));
        allVariantsTemp.put("khs_sphinx",createVariantList(new String[]{"khs_sphinx_acehigh"}));
        allVariantsTemp.put("khs_behemoth",createVariantList(new String[]{"khs_behemoth_acehigh"}));
        allVariantsTemp.put("khs_leviathan",createVariantList(new String[]{"khs_leviathan_outdated"}));
        allVariantsTemp.put("khs_mutt",createVariantList(new String[]{"khs_mutt_acehigh"}));
        allVariantsTemp.put("khs_sirocco",createVariantList(new String[]{"khs_sirocco_acehigh"}));
        allVariantsTemp.put("khs_mistral",createVariantList(new String[]{"khs_mistral_acehigh"}));
        allVariantsTemp.put("khs_djinn",createVariantList(new String[]{"khs_djinn_acehigh"}));
        Variants_INS = Collections.unmodifiableMap(allVariantsTemp);
    }

    static {
        Map allVariantsTemp = new HashMap();
        allVariantsTemp.putAll(Variants_PP);
        allVariantsTemp.putAll(Variants_IND);
        allVariantsTemp.putAll(Variants_HE);
        allVariantsTemp.putAll(Variants_TT);
        allVariantsTemp.putAll(Variants_PCK);
        allVariantsTemp.putAll(Variants_SHI);
        allVariantsTemp.putAll(Variants_BR);
        allVariantsTemp.putAll(Variants_JP);
        allVariantsTemp.putAll(Variants_KAD);
        allVariantsTemp.putAll(Variants_INS);
        All_Variants = Collections.unmodifiableMap(allVariantsTemp);
    }
    
    private static List createVariantList(String[] variants) {
        return Collections.unmodifiableList(Arrays.asList(variants));
    }
        
}









