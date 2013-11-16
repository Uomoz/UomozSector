package data.scripts.uss;

import java.util.List;
import java.util.Map;

public class UsSDataGrabber
{
    public String source;
    public static List small_w, medium_w, large_w;
    public static Map frigates, destroyers, cruisers, capitals, wings, variants, transports, Tfrigates, Tdestroyers, Tcruisers, Tcapitals, Twings, Tall;
    public static String enginesId, armorId, scrapId, fluxId, techManufacturer, armorName, scrapName, enginesName, fluxName;

    public UsSDataGrabber() {
        }

    public static void setSource(String source) {
        if (source.equals("hegemony")) {
            frigates = UsSData.HE_F;
            destroyers = UsSData.HE_D;
            cruisers = UsSData.HE_C;
            capitals = UsSData.HE_CS;
            wings = UsSData.HE_W;
            Tfrigates = UsSData.LT_F;
            Tdestroyers = UsSData.LT_D;
            Tcruisers = UsSData.LT_C;
            Tcapitals = UsSData.LT_CS;
            Twings = UsSData.LT_W;
            transports = UsSData.HE_TR;
            variants = UsSData.Variants_HE;
            small_w = UsSData.LT_SW_FINAL;
            medium_w = UsSData.LT_MW_FINAL;
            large_w = UsSData.LT_LW_FINAL;
        }
        if (source.equals("pirates")) {
            frigates = UsSData.PP_F;
            destroyers = UsSData.PP_D;
            cruisers = UsSData.PP_C;
            capitals = UsSData.PP_CS;
            wings = UsSData.PP_W;
            Tfrigates = UsSData.LT_F;
            Tdestroyers = UsSData.LT_D;
            Tcruisers = UsSData.LT_C;
            Tcapitals = UsSData.LT_CS;
            Twings = UsSData.LT_W;
            transports = UsSData.PP_TR;
            variants = UsSData.Variants_PP;
            small_w = UsSData.LT_SW_FINAL;
            medium_w = UsSData.LT_MW_FINAL;
            large_w = UsSData.LT_LW_FINAL;
        }
        if (source.equals("tritachyon")) {
            frigates = UsSData.TT_F;
            destroyers = UsSData.TT_D;
            cruisers = UsSData.TT_C;
            capitals = UsSData.TT_CS;
            wings = UsSData.TT_W;
            Tfrigates = UsSData.HT_F;
            Tdestroyers = UsSData.HT_D;
            Tcruisers = UsSData.HT_C;
            Tcapitals = UsSData.HT_CS;
            Twings = UsSData.HT_W;
            transports = UsSData.TT_TR;
            variants = UsSData.Variants_TT;
            small_w = UsSData.HT_SW_FINAL;
            medium_w = UsSData.HT_MW_FINAL;
            large_w = UsSData.HT_LW_FINAL;
        }
        if (source.equals("independent")) {
            frigates = UsSData.IND_F;
            destroyers = UsSData.IND_D;
            cruisers = UsSData.IND_C;
            capitals = UsSData.IND_CS;
            wings = UsSData.IND_W;
            Tfrigates = UsSData.ALL_F_FINAL;
            Tdestroyers = UsSData.ALL_D_FINAL;
            Tcruisers = UsSData.ALL_C_FINAL;
            Tcapitals = UsSData.ALL_CS_FINAL;
            Twings = UsSData.ALL_W_FINAL;
            transports = UsSData.IND_TR;
            variants = UsSData.Variants_IND;
            small_w = UsSData.ALL_SW_FINAL;
            medium_w = UsSData.ALL_MW_FINAL;
            large_w = UsSData.ALL_LW_FINAL;
        }
        if (source.equals("sindrian_diktat")) {
            frigates = UsSData.SIN_F;
            destroyers = UsSData.SIN_D;
            cruisers = UsSData.SIN_C;
            capitals = UsSData.SIN_CS;
            wings = UsSData.SIN_W;
            Tfrigates = UsSData.MT_F;
            Tdestroyers = UsSData.MT_D;
            Tcruisers = UsSData.MT_C;
            Tcapitals = UsSData.MT_CS;
            Twings = UsSData.MT_W;
            transports = UsSData.SIN_TR;
            variants = UsSData.Variants_IND;
            small_w = UsSData.MT_SW_FINAL;
            medium_w = UsSData.MT_MW_FINAL;
            large_w = UsSData.MT_LW_FINAL;
        }
        if (source.equals("pack")) {
            frigates = UsSData.PCK_F;
            destroyers = UsSData.PCK_D;
            cruisers = UsSData.PCK_C;
            capitals = UsSData.PCK_CS;
            wings = UsSData.PCK_W;
            Tfrigates = UsSData.PCKT_F;
            Tdestroyers = UsSData.PCKT_D;
            Tcruisers = UsSData.PCKT_C;
            Tcapitals = UsSData.PCKT_CS;
            Twings = UsSData.PCKT_W;
            transports = UsSData.PCK_TR;
            variants = UsSData.Variants_PCK;
            small_w = UsSData.PCK_SW_FINAL;
            medium_w = UsSData.PCK_MW_FINAL;
            large_w = UsSData.PCK_LW_FINAL;
        }
        if (source.equals("shadow_industry")) {
            frigates = UsSData.SHI_F;
            destroyers = UsSData.SHI_D;
            cruisers = UsSData.SHI_C;
            capitals = UsSData.SHI_CS;
            wings = UsSData.SHI_W;
            Tfrigates = UsSData.SHIT_F;
            Tdestroyers = UsSData.SHIT_D;
            Tcruisers = UsSData.SHIT_C;
            Tcapitals = UsSData.SHIT_CS;
            Twings = UsSData.SHIT_W;
            transports = UsSData.SHI_TR;
            variants = UsSData.Variants_SHI;
            small_w = UsSData.SHI_SW_FINAL;
            medium_w = UsSData.SHI_MW_FINAL;
            large_w = UsSData.SHI_LW_FINAL;
        }
        if (source.equals("blackrock_driveyards")) {
            frigates = UsSData.BR_F;
            destroyers = UsSData.BR_D;
            cruisers = UsSData.BR_C;
            capitals = UsSData.BR_CS;
            wings = UsSData.BR_W;
            Tfrigates = UsSData.BRT_F;
            Tdestroyers = UsSData.BRT_D;
            Tcruisers = UsSData.BRT_C;
            Tcapitals = UsSData.BRT_CS;
            Twings = UsSData.BRT_W;
            transports = UsSData.BR_TR;
            variants = UsSData.Variants_BR;
            small_w = UsSData.BR_SW_FINAL;
            medium_w = UsSData.BR_MW_FINAL;
            large_w = UsSData.BR_LW_FINAL;
        }
        if (source.equals("junk_pirates")) {
            frigates = UsSData.JP_F;
            destroyers = UsSData.JP_D;
            cruisers = UsSData.JP_C;
            capitals = UsSData.JP_CS;
            wings = UsSData.JP_W;
            Tfrigates = UsSData.JPT_F;
            Tdestroyers = UsSData.JPT_D;
            Tcruisers = UsSData.JPT_C;
            Tcapitals = UsSData.JPT_CS;
            Twings = UsSData.JPT_W;
            transports = UsSData.JP_TR;
            variants = UsSData.Variants_JP;
            small_w = UsSData.JP_SW_FINAL;
            medium_w = UsSData.JP_MW_FINAL;
            large_w = UsSData.JP_LW_FINAL;
        }
        if (source.equals("regime")) {
            frigates = UsSData.KAD_F;
            destroyers = UsSData.KAD_D;
            cruisers = UsSData.KAD_C;
            capitals = UsSData.KAD_CS;
            wings = UsSData.KAD_W;
            Tfrigates = UsSData.KADT_F;
            Tdestroyers = UsSData.KADT_D;
            Tcruisers = UsSData.KADT_C;
            Tcapitals = UsSData.KADT_CS;
            Twings = UsSData.KADT_W;
            transports = UsSData.KAD_TR;
            variants = UsSData.Variants_KAD;
            small_w = UsSData.KAD_SW_FINAL;
            medium_w = UsSData.KAD_MW_FINAL;
            large_w = UsSData.KAD_LW_FINAL;
        }
        if (source.equals("insurgency")) {
            frigates = UsSData.INS_F;
            destroyers = UsSData.INS_D;
            cruisers = UsSData.INS_C;
            capitals = UsSData.INS_CS;
            wings = UsSData.INS_W;
            Tfrigates = UsSData.KADT_F;
            Tdestroyers = UsSData.KADT_D;
            Tcruisers = UsSData.KADT_C;
            Tcapitals = UsSData.KADT_CS;
            Twings = UsSData.KADT_W;
            transports = UsSData.INS_TR;
            variants = UsSData.Variants_INS;
            small_w = UsSData.KAD_SW_FINAL;
            medium_w = UsSData.KAD_MW_FINAL;
            large_w = UsSData.KAD_LW_FINAL;
        }
        if (source.equals("nomads")) {
            frigates = UsSData.NOM_F;
            destroyers = UsSData.NOM_D;
            cruisers = UsSData.NOM_C;
            capitals = UsSData.NOM_CS;
            wings = UsSData.NOM_W;
            Tfrigates = UsSData.NOMT_F;
            Tdestroyers = UsSData.NOMT_D;
            Tcruisers = UsSData.NOMT_C;
            Tcapitals = UsSData.NOMT_CS;
            Twings = UsSData.NOMT_W;
            variants = UsSData.Variants_NOM;
        }
        if (source.equals("LT")) {
            Tfrigates = UsSData.LT_F;
            Tdestroyers = UsSData.LT_D;
            Tcruisers = UsSData.LT_C;
            Tcapitals = UsSData.LT_CS;
            Twings = UsSData.LT_W;
            Tall = UsSData.LT_ALL;
            small_w = UsSData.LT_SW_FINAL;
            medium_w = UsSData.LT_MW_FINAL;
            large_w = UsSData.LT_LW_FINAL;
            enginesId = "LTengines";
            armorId = "LTarmor";
            scrapId = "LTscraps";
            fluxId = "LTflux";
            techManufacturer = "Mastery Epoch";

        }
        if (source.equals("HT")) {
            Tfrigates = UsSData.HT_F;
            Tdestroyers = UsSData.HT_D;
            Tcruisers = UsSData.HT_C;
            Tcapitals = UsSData.HT_CS;
            Twings = UsSData.HT_W;
            Tall = UsSData.HT_ALL;
            small_w = UsSData.HT_SW_FINAL;
            medium_w = UsSData.HT_MW_FINAL;
            large_w = UsSData.HT_LW_FINAL;
            enginesId = "HTengines";
            armorId = "HTarmor";
            scrapId = "HTscraps";
            fluxId = "HTflux";
            techManufacturer = "Expansion Epoch";
        }
        if (source.equals("MT")) {
            Tfrigates = UsSData.MT_F;
            Tdestroyers = UsSData.MT_D;
            Tcruisers = UsSData.MT_C;
            Tcapitals = UsSData.MT_CS;
            Twings = UsSData.MT_W;
            Tall = UsSData.MT_ALL;
            small_w = UsSData.MT_SW_FINAL;
            medium_w = UsSData.MT_MW_FINAL;
            large_w = UsSData.MT_LW_FINAL;
            enginesId = "MTengines";
            armorId = "MTarmor";
            scrapId = "MTscraps";
            fluxId = "MTflux";
            techManufacturer = "Core Epoch";
        }
        if (source.equals("PCKT")) {
            Tfrigates = UsSData.PCKT_F;
            Tdestroyers = UsSData.PCKT_D;
            Tcruisers = UsSData.PCKT_C;
            Tcapitals = UsSData.PCKT_CS;
            Twings = UsSData.PCKT_W;
            Tall = UsSData.PCKT_ALL;
            small_w = UsSData.PCK_SW_FINAL;
            medium_w = UsSData.PCK_MW_FINAL;
            large_w = UsSData.PCK_LW_FINAL;
            enginesId = "LTengines";
            armorId = "PCKarmor";
            scrapId = "LTscraps";
            fluxId = "LTflux";
            techManufacturer = "Canis Experimental";
        }
        if (source.equals("SHIT")) {
            Tfrigates = UsSData.SHIT_F;
            Tdestroyers = UsSData.SHIT_D;
            Tcruisers = UsSData.SHIT_C;
            Tcapitals = UsSData.SHIT_CS;
            Twings = UsSData.SHIT_W;
            Tall = UsSData.SHIT_ALL;
            small_w = UsSData.SHI_SW_FINAL;
            medium_w = UsSData.SHI_MW_FINAL;
            large_w = UsSData.SHI_LW_FINAL;
            enginesId = "HTengines";
            armorId = "HTarmor";
            scrapId = "HTscraps";
            fluxId = "SHIflux";
            techManufacturer = "Shadowyards Heavy Industries";
        }
        if (source.equals("BRT")) {
            Tfrigates = UsSData.BRT_F;
            Tdestroyers = UsSData.BRT_D;
            Tcruisers = UsSData.BRT_C;
            Tcapitals = UsSData.BRT_CS;
            Twings = UsSData.BRT_W;
            Tall = UsSData.BRT_ALL;
            small_w = UsSData.BR_SW_FINAL;
            medium_w = UsSData.BR_MW_FINAL;
            large_w = UsSData.BR_LW_FINAL;
            enginesId = "BRengines";
            armorId = "MTarmor";
            scrapId = "HTscraps";
            fluxId = "HTflux";
            techManufacturer = "Blackrock Driveyards";
        }
        if (source.equals("JPT")) {
            Tfrigates = UsSData.JPT_F;
            Tdestroyers = UsSData.JPT_D;
            Tcruisers = UsSData.JPT_C;
            Tcapitals = UsSData.JPT_CS;
            Twings = UsSData.JPT_W;
            Tall = UsSData.JPT_ALL;
            small_w = UsSData.JP_SW_FINAL;
            medium_w = UsSData.JP_MW_FINAL;
            large_w = UsSData.JP_LW_FINAL;
            enginesId = "HTengines";
            armorId = "LTarmor";
            scrapId = "MTscraps";
            fluxId = "JPflux";
            techManufacturer = "Breh'Inni Experimental";
        }
        if (source.equals("KADT")) {
            Tfrigates = UsSData.KADT_F;
            Tdestroyers = UsSData.KADT_D;
            Tcruisers = UsSData.KADT_C;
            Tcapitals = UsSData.KADT_CS;
            Twings = UsSData.KADT_W;
            Tall = UsSData.KADT_ALL;
            small_w = UsSData.KAD_SW_FINAL;
            medium_w = UsSData.KAD_MW_FINAL;
            large_w = UsSData.KAD_LW_FINAL;
            enginesId = "MTengines";
            armorId = "KADarmor";
            scrapId = "MTscraps";
            fluxId = "LTflux";
            techManufacturer = "Kadur Orbital Yards";
        }
        if (source.equals("NOMT")) {
            Tfrigates = UsSData.NOMT_F;
            Tdestroyers = UsSData.NOMT_D;
            Tcruisers = UsSData.NOMT_C;
            Tcapitals = UsSData.NOMT_CS;
            Twings = UsSData.NOMT_W;
            Tall = UsSData.NOMT_ALL;
            enginesId = "HTengines";
            armorId = "HTarmor";
            scrapId = "NOMscraps";
            fluxId = "HTflux";
            techManufacturer = "Nur Mirage Technology";
        } 
    }
}

    
