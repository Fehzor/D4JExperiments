/*
 * Copyright (C) 2017 FF6EB4
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package IDLER;

import static IDLER.SuperRandom.oRan;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author FF6EB4
 */
public class GearMap {
    public static HashMap<String, String> gearMap = new HashMap<>();
    public static HashMap<String, Integer> starMap = new HashMap<>();;
    public static ArrayList<String> gear = loadGear();
    public static ArrayList<String> legends = loadLegends();
    
    public static ArrayList<String> loadGear(){
        ArrayList<String> ret = new ArrayList<>();

        //PULSARS
        String[] line = new String[4];
        line[0] = "pulsar";
        line[1] = "heavy_pulsar";
        line[2] = "radiant_pulsar";
        line[3] = "supernova";
        subLine(ret,line);
        line[1] = "kilowatt_pulsar";
        line[2] = "gigawatt_pulsar";
        line[3] = "polaris";
        subLine(ret,line);
        line[1] = "flaming_pulsar";
        line[2] = "blazing_pulsar";
        line[3] = "wildfire";
        subLine(ret,line);
        line[1] = "freezing_pulsar";
        line[2] = "frozen_pulsar";
        line[3] = "permafroster";
        subLine(ret,line);
        
        if(BoppBot.EVENT == 2){
            line[0] = "autogun";
            line[1] = "wild_buster";
            line[2] = "feral_cannon";
            line[3] = "savage_tortofist";
            subLine(ret,line);
            line[0] = "blaster";
            line[1] = "stoic_buster";
            line[2] = "mighty_cannon";
            line[3] = "grand_tortofist";
            subLine(ret,line);
            line[0] = "prismatech_alchemer";
            line[1] = "primal_buster";
            line[2] = "barrier_cannon";
            line[3] = "omega_tortofist";
            subLine(ret,line);
            line[0] = "shadowtech_alchemer";
            line[1] = "grim_buster";
            line[2] = "nether_cannon";
            line[3] = "gorgofist";
            subLine(ret,line);
        } else {
            putGear(ret,"savage_tortofist","NA",6);
            putGear(ret,"grand_tortofist","NA",6);
            putGear(ret,"omega_tortofist","NA",6);
            putGear(ret,"gorgofist","NA",6);
        }
        
        //CATALYZER
        line[0] = "catalyzer";
        line[1] = "industrial_catalyzer";
        line[2] = "volatile_catalyzer";
        line[3] = "neutralizer";
        subLine(ret,line);
        line[1] = "toxic_catalyzer";
        line[2] = "virulent_catalyzer";
        line[3] = "biohazard";
        subLine(ret,line);
        
        //CATALYZER
        line[0] = "cutter";
        line[1] = "striker";
        line[2] = "vile_striker";
        line[3] = "dread_venom_striker";
        createLine(ret,line);
        putGear(ret,"hunting_blade","striker",4);
        putGear(ret,"wild_hunting_blade","hunting_blade",5);

        //AUTOGUNS
        line = new String[4];
        line[0] = "autogun";
        line[1] = "needle_shot";
        line[2] = "strike_needle";
        line[3] = "blitz_needle";
        createLine(ret,line);
        line[1] = "pepperbox";
        line[2] = "fiery_pepperbox";
        line[3] = "volcanic_pepperbox";
        subLine(ret,line);
        line[1] = "toxic_needle";
        line[2] = "blight_needle";
        line[3] = "plague_needle";
        subLine(ret,line);
        line[1] = "dark_chaingun";
        line[2] = "black_chaingun";
        line[3] = "grim_repeater";
        subLine(ret,line);
        
        //BLASTERS
        line = new String[4];
        line[0] = "blaster";
        line[1] = "super_blsater";
        line[2] = "master_blaster";
        line[3] = "valiance";
        createLine(ret,line);
        line[1] = "pierce_blaster";
        line[2] = "breach_blaster";
        line[3] = "riftlocker";
        subLine(ret,line);
        line[1] = "elemental_blaster";
        line[2] = "fusion_blaster";
        line[3] = "arcana";
        subLine(ret,line);
        line[1] = "shadow_blaster";
        line[2] = "umbral_blsater";
        line[3] = "phantamos";
        subLine(ret,line);
        putGear(ret,"beach_blaster","pierce_blaster",4);
        
        //BLAST BOMB
        line = new String[4];
        line[0] = "blast_bomb";
        line[1] = "super_blast_bomb";
        line[2] = "master_blast_bomb";
        line[3] = "nitronome";
        createLine(ret,line);
        putGear(ret,"deconstructor","blast_bomb",3);
        putGear(ret,"heavy_deconstructor","deconstructor",4);
        putGear(ret,"irontech_bomb","super_blast_bomb",4);
        putGear(ret,"irontech_destroyer","irontech_bomb",5);
        putGear(ret,"big_angry_bomb","master_blast_bomb",5);
        
        //STAGGER STORM
        line[0] = "haze_bomb";
        line[1] = "haze_bomb_mk_ii";
        line[2] = "haze_burst";
        line[3] = "stagger_storm";
        createLine(ret,line);
        
        //ASH OF AGNI
        line[0] = "fiery_vaporizer";
        line[1] = "fiery_vaporizer_mk_ii";
        line[2] = "fiery_atomizer";
        line[3] = "ash_of_agni";
        createLine(ret,line);
        
        //SHIV
        line[0] = "freezing_vaporizer";
        line[1] = "freezing_vaporizer_mk_ii";
        line[2] = "freezing_atomizer";
        line[3] = "shivermist_buster";
        createLine(ret,line);
        
        //Venom Veiler
        line[0] = "toxic_vaporizer";
        line[1] = "toxic_vaporizer_mk_ii";
        line[2] = "toxic_atomizer";
        line[3] = "venom_veiler";
        createLine(ret,line);
        
        //cryo
        line[0] = "cryotech_alchemer";
        line[1] = "cryotech_alchemer_mk_ii";
        line[2] = "cryo_driver";
        line[3] = "hail_driver";
        createLine(ret,line);
        
        //prisma
        line[0] = "prismatech_alchemer";
        line[1] = "prismatech_alchemer_mk_ii";
        line[2] = "prisma_driver";
        line[3] = "nova_driver";
        createLine(ret,line);
        
        //shadowtech
        line[0] = "shadowtech_alchemer";
        line[1] = "shadowtech_alchemer_mk_ii";
        line[2] = "shadow_driver";
        line[3] = "umbra_driver";
        createLine(ret,line);
        
        //MAGNUS
        line[0] = "";
        line[1] = "magnus";
        line[2] = "mega_magnus";
        line[3] = "callahan";
        subLine(ret,line);
        putGear(ret,"iron_slug","mega_magnus",4);
        
        //MAGNUS
        line[0] = "";
        line[1] = "tundrus";
        line[2] = "mega_tundrus";
        line[3] = "winter_grave";
        subLine(ret,line);
        
        
        
        //voltech
        line[0] = "firotech_alchemer";
        line[1] = "firotech_alchemer_mk_ii";
        line[2] = "firo_driver";
        line[3] = "storm_driver";
        createLine(ret,line);
        
        //firotech
        line[0] = "voltech_alchemer";
        line[1] = "voltech_alchemer_mk_ii";
        line[2] = "volt_driver";
        line[3] = "magma_driver";
        createLine(ret,line);
        
        //torpor
        line[0] = "slumber_smogger";
        line[1] = "slumber_smogger_mkii";
        line[2] = "slumber_squall";
        line[3] = "torpor_tantrum";
        createLine(ret,line);
        
        //VOLTAIC TEMPEST
        line[0] = "static_capacitor";
        line[1] = "lightning_capacitor";
        line[2] = "plasma_capacitor";
        line[3] = "voltaic_tempest";
        subLine(ret,line);
        
        //SPINE CONE
        line[0] = "spine_cone";
        line[1] = "twisted_spine_cone";
        line[2] = "spike_shower";
        line[3] = "dark_briar_barrage";
        subLine(ret,line);
        
        //FLOURISH
        line[0] = "flourish";
        line[1] = "swift_flourish";
        line[2] = "grand_flourish";
        line[3] = "final_flourish";
        createLine(ret,line);
        line[1] = "rigadoon";
        line[2] = "daring_rigadoon";
        line[3] = "fearless_rigadoon";
        subLine(ret,line);
        line[1] = "flamberge";
        line[2] = "fierce_flamberge";
        line[3] = "furious_flamberge";
        subLine(ret,line);
        
        line[0] = "snarble_barb";
        line[1] = "twisted_snarble_barb";
        line[2] = "dark_thorn_blade";
        line[3] = "barbarous_thorn_blade";
        subLine(ret,line);
        
        //LEVIATHAN
        line[0] = "calibur";
        line[1] = "tempered_calibur";
        line[2] = "ascended_calibur";
        line[3] = "leviathan_blade";
        createLine(ret,line);
        putGear(ret,"cold_iron_carver","tempered_calibur",4);
        putGear(ret,"cold_iron_vanquisher","cold_iron_carver",5);
        
        //SUDARUSKA
        line[0] = "troika";
        line[1] = "kamarin";
        line[2] = "khorovod";
        line[3] = "sudaruska";
        createLine(ret,line);
        line[1] = "grintovec";
        line[2] = "jalovec";
        line[3] = "triglav";
        subLine(ret,line);
        line[1] = "three_star_gram";
        line[2] = "four_star_gram";
        line[3] = "gram";
        subLine(ret,line);
        
        if(BoppBot.EVENT == 4){
            putGear(ret,"scalding_hot_cocoa","fiery_atomizer",5);
            putGear(ret,"peppermint_repeater","black_chaingun",5);
            putGear(ret,"nog_blaster","prisma_driver",5);
            putGear(ret,"deadly_candy_poker","grand_flourish",5);
            putGear(ret,"jack_froster","freezing_atomizer",5);
            putGear(ret,"humbug_hazer","toxic_atomizer",5);
        } else {
            putGear(ret,"scalding_hot_cocoa","NA",6);
            putGear(ret,"peppermint_repeater","NA",6);
            putGear(ret,"nog_blaster","NA",6);
            putGear(ret,"deadly_candy_poker","NA",6);
            putGear(ret,"jack_froster","NA",6);
            putGear(ret,"humbug_hazer","NA",6);
        }
        
        //GRAVITON VORTEX
        line[0] = "";
        line[1] = "graviton_charge";
        line[2] = "graviton_bomb";
        line[3] = "graviton_vortex";
        subLine(ret,line);
        if(BoppBot.EVENT == 3){
            putGear(ret,"obsidian_crusher","graviton_bomb",5);
        } else {
            putGear(ret,"obsidian_crusher","NA",6);
        }
        
        
        //Electron VORTEX
        line[0] = "";
        line[1] = "electron_charge";
        line[2] = "electron_bomb";
        line[3] = "electron_vortex";
        subLine(ret,line);
        
        //1* garbage
        putGear(ret,"big_beast_basher","",1);
        putGear(ret,"chilling_duelist","",1);
        putGear(ret,"cold_snap","",1);
        putGear(ret,"firecracker","",1);
        putGear(ret,"frost_gun","",1);
        putGear(ret,"hot_edge","",1);
        putGear(ret,"red_saber","",1);
        putGear(ret,"rugged_robo_wrecker","",1);
        putGear(ret,"spitfire","",1);
        putGear(ret,"static_edge","",1);
        putGear(ret,"static_flash","",1);
        putGear(ret,"super_slime_slasher","",1);
        putGear(ret,"super_stun_gun","",1);
        putGear(ret,"thwack_hammer","",1);
        putGear(ret,"zapper","",1);
        putGear(ret,"heavy_hatchet","",1);
        putGear(ret,"super_stun_gun","",1);
        putGear(ret,"rugged_robo_wrecker","",1);
        
        putGear(ret,"wrench_wand","",2);
        
        //WINMILLION
        putGear(ret,"spur","",2);
        putGear(ret,"arc_razor","spur",3);
        putGear(ret,"winmillion","arc_razor",4);        
                
        //SHARDS
        putGear(ret,"shard_bomb","",2);
        putGear(ret,"super_shard_bomb","shard_bomb",3);
        putGear(ret,"heavy_shard_bomb","super_shard_bomb",4);
        putGear(ret,"deadly_shard_bomb","heavy_shard_bomb",5);
        
        
        //ANTIGUAS
        putGear(ret,"raptor","antigua",4);
        putGear(ret,"gilded_griffin","raptor",5);
        putGear(ret,"silversix","antigua",4);
        putGear(ret,"argent_peacemaker","silversix",5);
        putGear(ret,"blackhawk","antigua",4);
        putGear(ret,"sentenza","blackhawk",5);
        if(BoppBot.EVENT == 3){
            putGear(ret,"obsidian_carbine","blackhawk",5);
        } else {
            putGear(ret,"obsidian_carbine","NA",6);
        }
        
        //FAUST AND SEALED SWWORD
        putGear(ret,"faust","sealed_sword",4);
        putGear(ret,"gran_faust","faust",5);
        
        putGear(ret,"avenger","sealed_sword",4);
        putGear(ret,"divine_avenger","avenger",5);

        putGear(ret,"splinter_bomb","",2);
        putGear(ret,"super_splinter_bomb","splinter_bomb",3);
        putGear(ret,"heavy_splinter_bomb","super_splinter_bomb",4);
        putGear(ret,"deadly_splinter_bomb","heavy_splinter_bomb",5);

        putGear(ret,"sun_shards","splinter_bomb",3);
        putGear(ret,"radiant_sun_shards","sun_shards",4);
        putGear(ret,"scintillating_sun_shards","radiant_sun_shards",5);

        putGear(ret,"dark_matter_bomb","",2);
        putGear(ret,"super_dark_matter_bomb","dark_matter_bomb",3);
        putGear(ret,"heavy_dark_matter_bomb","super_dark_matter_bomb",4);
        putGear(ret,"deadly_dark_matter_bomb","heavy_dark_matter_bomb",5);

        putGear(ret,"crystal_bomb","",2);
        putGear(ret,"super_crystal_bomb","crystal_bomb",3);
        putGear(ret,"heavy_crystal_bomb","super_crystal_bomb",4);
        putGear(ret,"deadly_crystal_bomb","heavy_crystal_bomb",5);

        putGear(ret,"salt_bomb","dark_matter_bomb",3);
        putGear(ret,"ionized_salt_bomb","salt_bomb",4);
        putGear(ret,"shocking_salt_bomb","ionized_salt_bomb",5);

        putGear(ret,"old_sun_shards","crystal_bomb",3);
        putGear(ret,"old_radiant_sun_shards","old_sun_shards",4);

        putGear(ret,"old_salt_bomb","crystal_bomb",3);
        putGear(ret,"old_ionized_salt_bomb","old_salt_bomb",4);

        //BRANDISHES
        putGear(ret,"brandish","",2);
        putGear(ret,"cautery_sword","brandish",3);
        putGear(ret,"avanced_cautery_sword","brandish",4);
        putGear(ret,"amputator","avanced_cautery_sword",5);

        putGear(ret,"fireburst_brandish","brandish",3);
        putGear(ret,"blazebrand","fireburst_brandish",4);
        putGear(ret,"combuster","blazebrand",5);

        putGear(ret,"iceburst_brandish","brandish",3);
        putGear(ret,"blizzbrand","iceburst_brandish",4);
        putGear(ret,"glacius","blizzbrand",5);

        putGear(ret,"shockburst_brandish","brandish",3);
        putGear(ret,"boltbrand","shockburst_brandish",4);
        putGear(ret,"voltedge","boltbrand",5);

        putGear(ret,"nightblade","brandish",3);
        putGear(ret,"silent_nightblade","nightblade",4);
        putGear(ret,"acheron","silent_nightblade",5);
        if(BoppBot.EVENT == 3){
            putGear(ret,"obsidian_edge","silent_nightblade",5);
        } else {
            putGear(ret,"obsidian_edge","NA",6);
        }
        
        //BOSS TOKEN REWARDS
        putGear(ret,"fang_of_vog","NA",5);
        putGear(ret,"static_capacitor","NA",2);
        putGear(ret,"antigua","NA",3);
        putGear(ret,"sealed_sword","NA",3);
        putGear(ret,"snarble_barb","NA",2);
        putGear(ret,"spine_cone","NA",2);
        putGear(ret,"pulsar","NA",2);
        putGear(ret,"catalyzer","NA",2);
        
        putGear(ret,"dark_retribution","NA",5);
        putGear(ret,"warmaster_rocket_hammer","NA",5);
        
        //RARE STUFF
        putGear(ret,"scissor_blades","NA",5);
        putGear(ret,"punkin_cinnamug","NA",5);
        putGear(ret,"furious_fork","NA",5);
        putGear(ret,"spiral_soaker","NA",5);
        
        putGear(ret,"node_slime_crusher","NA",5);
        putGear(ret,"deadly_charcoaler","NA",5);
        putGear(ret,"overcharged_mixmaster","NA",5);
        putGear(ret,"celestial_orbitgun","NA",5);
        
        putGear(ret,"celestial_vortex","NA",5);
        putGear(ret,"celestial_saber","NA",5);
        putGear(ret,"frozen_great_cleaver","NA",5);
        putGear(ret,"mighty_great_cleaver","NA",5);
        
        putGear(ret,"pot_o_crowns","NA",5);
        putGear(ret,"caladbolg","NA",5);
        putGear(ret,"teddy_bear_buckler","NA",5);
        putGear(ret,"sweet_dreams","NA",5);
        
        putGear(ret,"black_kat_cowl","NO SOUL",11);
        putGear(ret,"black_kat_raiment","NO SOUL",11);
        
        putGear(ret,"groundbreaker_set","NA",11);
        putGear(ret,"purple_spiral","NA",23);
        
        //LOLOLOLOL- easteregg for grilling things.
        putGear(ret,"cheese_burger","NA",6);
        putGear(ret,"hot_dog","NA",6);
        
        
        putGear(ret,"early_riser_ring","NA",3);
        putGear(ret,"dawn_bracelet","NA",4);
        putGear(ret,"daybreaker_band","NA",5);
        putGear(ret,"somnambulists_totem","NA",5);
        
        putGear(ret,"hatch_handle","NA",5);
        putGear(ret,"stalker_blades","NA",5);
        
        putGear(ret,"ironmight_plate_shield","NA",5);
        putGear(ret,"lost_soul","NA",5);
        
        putGear(ret,"darkfang_shield","NA",4);
        
        putGear(ret,"mug_of_misery","NA",5);
        putGear(ret,"wicked_idol","NA",5);
        
        putGear(ret,"tri_guard_helm","NA",1);
        putGear(ret,"tri_guard_armor","NA",1);
        putGear(ret,"mad_skills","NA",10);
        
        putGear(ret,"dark_leviathan_blade","NA",5);
        putGear(ret,"warden_armor","NA",5);
        putGear(ret,"ruinous_crystal","NA",5);
        putGear(ret,"warden_helm","NA",5);
        
        putGear(ret,"stupid_shinning_crystals","NA",4);
        putGear(ret,"prestige","NA",5);
        
        putGear(ret,"mercurial_mail","NA",5);
        putGear(ret,"mercurial_helm","NA",5);
        
        putGear(ret,"almirian_crusader_armor","NA",5);
        putGear(ret,"almirian_crusader_helm","NA",5);
        
        putGear(ret,"mortafire_mortar","NA",5);
        
        putGear(ret,"perfect_snowball","NA",5);
        putGear(ret,"maulos_maul","NA",5);
        
        putGear(ret,"snipe_feather","NA",1);
        
        putGear(ret,"","NA",5);
        
        return ret;
    }
    
    public static ArrayList<String> loadLegends(){
        ArrayList<String> ret = new ArrayList<>();
        
        ret.add("scissor_blades");
        ret.add("punkin_cinnamug");
        ret.add("furious_fork");
        ret.add("spiral_soaker");
        
        ret.add("node_slime_crusher");
        ret.add("deadly_charcoaler");
        ret.add("celestial_orbitgun");
        
        ret.add("celestial_saber");
        ret.add("celestial_vortex");
        ret.add("frozen_great_cleaver");
        ret.add("mighty_great_cleaver");
        
        ret.add("caladbolg");
        ret.add("pot_o_crowns");
        ret.add("teddy_bear_buckler");
        ret.add("sweet_dreams");
        
        return ret;
    }
    
    public static void putGear(ArrayList ret, String name, String pre, int star){
        ret.add(name);
        starMap.put(name,star);
        if(!pre.equals("")){
            gearMap.put(name,pre);
        }
    }
    
    public static String craft(UserData user, String gear){
        if((gear.equals("cheese_burger")
                || gear.equals("hot_dog"))
                && !user.gear.contains("deadly_charcoaler")
                && !user.gear.contains("furious_fork")){
            return "You need deadly charcoaler and furious fork to grill patties!";
        };
        if(gearMap.get(gear) == null){
            int stars = starMap.get(gear);
            String check = checkOrbs(user,stars);
            if(check.equals("")){
                user.gear.add(gear);
            } else {
                return check;
            }
        } else {
            String pre = gearMap.get(gear);
            if(user.gear.contains(pre)){
                int stars = starMap.get(gear);
                String check = checkOrbs(user,stars);
                if(check.equals("")){
                    user.gear.remove(pre);
                    user.gear.add(gear);
                    
                } else {
                    return check;
                }
            } else {
                if(pre.equals("NA")){
                    return "you can't craft that!";
                }
                return "you need "+pre+" to craft "+gear+".";
            }
        }
        
        return "Success! You crafted "+gear+"!";
    }
    
    public static String checkOrbs(UserData user, int stars){
        switch(stars){
                case 1:
                    if(user.flawed_orbs >= 3 && user.crowns >= 450){
                        user.flawed_orbs -= 3;
                        user.crowns -= 450;
                    } else {
                        return "You need 3 flawed orbs and 450 crowns for that!";
                    }
                    break;
                case 2:
                    if(user.simple_orbs >= 3 && user.crowns >= 1400){
                        user.simple_orbs -= 3;
                        user.crowns -= 1400;
                    } else {
                        return "You need 3 simple orbs and 1,400 crowns for that!";
                    }
                    break;
                case 3:
                    if(user.adv_orbs >= 3 && user.crowns >= 5000){
                        user.adv_orbs -= 3;
                        user.crowns -= 5000;
                    } else {
                        return "You need 3 advanced orbs and 5,000 crowns for that!";
                    }
                    break;
                case 4:
                    if(user.elite_orbs >= 3 && user.crowns >= 12500){
                        user.elite_orbs -= 3;
                        user.crowns -= 12500;
                    } else {
                        return "You need 3 elite orbs and 12,500 crowns for that!";
                    }
                    break;
                case 5:
                    if(user.eternal_orbs >= 3 && user.crowns >= 30000){
                        user.eternal_orbs -= 3;
                        user.crowns -= 30000;
                    } else {
                        return "You need 3 eternal orbs and 30,000 crowns for that!";
                    } 
                    break;
            }
        return "";
    }
    
    private static void createLine(ArrayList<String> ret, String[] names){
        putGear(ret,names[0],"",2);
        putGear(ret,names[1],names[0],3);
        putGear(ret,names[2],names[1],4);
        putGear(ret,names[3],names[2],5);
    }
    
    private static void subLine(ArrayList<String> ret, String[] names){
        putGear(ret,names[1],names[0],3);
        putGear(ret,names[2],names[1],4);
        putGear(ret,names[3],names[2],5);
    }
    
    public static String buy(UserData UD, String toBuy){
        if(toBuy.equals("fang_of_vog")){
            if(UD.almire_coins >= 40){
                UD.gear.add("fang_of_vog");
                UD.almire_coins -= 40;
                return "Purchased FoV!";
            }
            return "Not enough tokens!";
        }
        
        if(toBuy.equals("static_capacitor")){
            if(UD.bark_mods >= 15){
                UD.gear.add("static_capacitor");
                UD.bark_mods -= 15;
                return "Purchased static capacitor!";
            }
            return "Not enough tokens!";
        }
        
        if(toBuy.equals("pulsar")){
            if(UD.bark_mods >= 15){
                UD.gear.add("pulsar");
                UD.bark_mods -= 15;
                return "Purchased pulsar!";
            }
            return "Not enough tokens!";
        }
        
        if(toBuy.equals("catalyzer")){
            if(UD.bark_mods >= 15){
                UD.gear.add("catalyzer");
                UD.bark_mods -= 15;
                return "Purchased catalyzer!";
            }
            return "Not enough tokens!";
        }
        
        if(toBuy.equals("antigua")){
            if(UD.jelly_gems >= 20){
                UD.gear.add("antigua");
                UD.jelly_gems -= 20;
                return "Purchased antigua!";
            }
            return "Not enough tokens!";
        }
        
        if(toBuy.equals("sealed_sword")){
            if(UD.jelly_gems >= 20){
                UD.gear.add("sealed_sword");
                UD.jelly_gems -= 20;
                return "Purchased sealed sword!";
            }
            return "Not enough tokens!";
        }
        
        if(toBuy.equals("spine_cone")){
            if(UD.frum_fangs >= 10){
                UD.gear.add("spine_cone");
                UD.frum_fangs -= 10;
                return "Purchased spine_cone!";
            }
            return "Not enough tokens!";
        }
        
        if(toBuy.equals("snarble_barb")){
            if(UD.frum_fangs >= 10){
                UD.gear.add("snarble_barb");
                UD.frum_fangs -= 10;
                return "Purchased snarble barb!";
            }
            return "Not enough tokens!";
        }
        
        if(toBuy.equals("operation_crimson_hammer")){
            if(UD.crowns > 1000000){
                UD.gear.add("warmaster_rocket_hammer");
                UD.gear.add("dark_retribution");
                UD.crowns -= 1000000;
                return "Purchased OCH";
            }
            return "OCH costs 1 million crowns.";
        }
        
        if(toBuy.equals("overcharged_mixmaster")){
            if(UD.crowns > 1350000000){
                UD.gear.add("overcharged_mixmaster");
                UD.crowns -= 13500000;
                return "Purchased that fat mixer.";
            }
            return "The fun mixer costs 13500000 crowns. Seems fair.";
        }
        
        if(toBuy.equals("flawed_orbs")){
            if(UD.crowns > 5000){
                UD.flawed_orbs+=3;
                UD.crowns -= 5000;
                return "Purchased 3 flawed orbs";
            }
            return "Flawed orbs cost 5K crowns.";
        }
        if(toBuy.equals("simple_orbs")){
            if(UD.crowns > 25000){
                UD.simple_orbs+=3;
                UD.crowns -= 25000;
                return "Purchased 3 simple orbs";
            }
            return "Simple orbs cost 25K crowns.";
        }
        if(toBuy.equals("advanced_orbs")){
            if(UD.crowns > 50000){
                UD.adv_orbs+=3;
                UD.crowns -= 50000;
                return "Purchased 3 advanced orbs";
            }
            return "Advanced orbs cost 50K crowns.";
        }
        if(toBuy.equals("elite_orbs")){
            if(UD.crowns > 100000){
                UD.elite_orbs+=3;
                UD.crowns -= 100000;
                return "Purchased 3 elite orbs";
            }
            return "Elite orbs cost 100K crowns.";
        }
        if(toBuy.equals("eternal_orbs")){
            if(UD.crowns > 200000){
                UD.eternal_orbs+=3;
                UD.crowns -= 5000;
                return "Purchased 3 eternal orbs";
            }
            return "Eternal orbs cost 200K crowns.";
        }
        
        if(toBuy.equals("purple_spiral")){
            if(UD.snowflakes > 5000){
                UD.snowflakes -= 5000;
                UD.gear.add("purple_spiral");
                return "Purchased purple spiral!";
            }
            return "Purple Spiral requires 3000 core tokens to purchase.";
        }
        
        
        
        if(toBuy.equals("groundbreaker_set")){
            if(UD.snowflakes > 3000){
                UD.snowflakes -= 3000;
                UD.gear.add("groundbreaker_set");
                return "Purchased groundbreaker set!";
            }
            return "Groundbreaker set requires 2000 core tokens to purchase.";
        }
        
        if(toBuy.equals("legendary_equipment")){
            if(UD.snowflakes > 1337){
                UD.snowflakes -= 1337;
                UD.gear.add(legends.get(oRan.nextInt(legends.size())));
                return "Purchased something cool!";
            }
            return "Legendary Equipment costs 1337 tokens and is random.";
        }
        
        return "I didn't quite catch that.";
    }
}
