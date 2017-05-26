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
package Bopp.Items;

import Bopp.Data.Field;
import Bopp.Data.UserData;
import Bopp.Gates.Gate;
import Bopp.Items.Weapons.*;
import Bopp.Items.Weapons.Obsidian;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author FF6EB4
 */
public class Gear {
    public final int WEAPON = 0;
    public final int SHIELD = 1;
    public final int ARMOR = 2;
    public final int HELM = 3;
    
    public static ArrayList<String> gearList;
    public static HashMap<String,Gear> gearMap;
    
    public String name = "Null";
    public String description = "No effect";
    public int stars = 0;
    public int slot = WEAPON;
    
    public String prereq = "";
    public int mat1 = -1;
    public int mat2 = -1;
    
    private static Gear G = new Gear();
    private Gear(){
        if(gearList == null){
            loadGear();
        }
    }
    
    public Gear(String name, String prereq, String description, int stars){
        this.name = name;
        this.prereq = prereq;
        this.description = description;
        this.stars = stars;
    }

    public void effect(UserData UD, Gate G){
        //Add effect here!
    }
    public String toString(){
        return "**"+this.name + ":** "+this.description +"\n*Mats: "+this.mats()+"*";
    }
    
    private String mats(){
        String ret = "";
        int cost = getMatCosts(stars);
        ret = cost+" ";
        
        //0 Beast,1 Grem,2 Fire,3 Fiend
        //4 Freeze, 5 Slime, 6 Poison, 7 Construct, 8 Shock, 9Undead
        switch(mat1){
            case 0:
                ret+="beast";
                break;
            case 1:
                ret+="gremlin";
                break;
            case 2:
                ret+="fire";
                break;
            case 3:
                ret+="fiend";
                break;
            case 4:
                ret+="freeze";
                break;
            case 5:
                ret+="slime";
                break;
            case 6:
                ret+="poison";
                break;
            case 7:
                ret+="construct";
                break;
            case 8:
                ret+="shock";
                break;
            case 9:
                ret+="undead";
                break;
            case 10:
                ret+="apocrean";
                break;
        }
        
        ret+=", "+cost+" ";
        
        switch(mat2){
            case 0:
                ret+="beast";
                break;
            case 1:
                ret+="gremlin";
                break;
            case 2:
                ret+="fire";
                break;
            case 3:
                ret+="fiend";
                break;
            case 4:
                ret+="freeze";
                break;
            case 5:
                ret+="slime";
                break;
            case 6:
                ret+="poison";
                break;
            case 7:
                ret+="construct";
                break;
            case 8:
                ret+="shock";
                break;
            case 9:
                ret+="undead";
                break;
            case 10:
                ret+="apocrean";
                break;
        }
        
        if(ret.equals(cost+" "+", "+cost+" ")){
            return "None";
        } else {
            return ret;
        }
    }
    
    public static void loadGear(){
        gearList = new ArrayList<>();
        gearMap = new HashMap<>();
        
        //String name, String prereq, int amt, int color, int mat1
        add(new ShardBomb("Shard Bomb", "", 2, 0, 2));
        add(new ShardBomb("Super Shard Bomb", "Shard Bomb", 3, 0, 2));
        add(new ShardBomb("Heavy Shard Bomb", "Super Shard Bomb", 4, 0, 2));
        add(new ShardBomb("Deadly Shard Bomb", "Heavy Shard Bomb", 5, 0, 2));
        
        add(new ShardBomb("Crystal Bomb", "", 2, 1, 6));
        add(new ShardBomb("Super Crystal Bomb", "Crystal Bomb", 3, 1, 6));
        add(new ShardBomb("Heavy Crystal Bomb", "Super Crystal Bomb", 4, 1, 6));
        add(new ShardBomb("Deadly Crystal Bomb", "Heavy Crystal Bomb", 5, 1, 6));
        
        add(new ShardBomb("Dark Matter Bomb", "", 2, 4, 9));
        add(new ShardBomb("Super Dark Matter Bomb", "Dark Matter Bomb", 3, 4, 9));
        add(new ShardBomb("Heavy Dark Matter Bomb", "Super Dark Matter Bomb", 4, 4, 9));
        add(new ShardBomb("Deadly Dark Matter Bomb", "Heavy Dark Matter Bomb", 5, 4, 9));
        
        add(new ShardBomb("Rock Salt Bomb", "Dark Matter Bomb", 3, 2, 5));
        add(new ShardBomb("Ionized Salt Bomb", "Rock Salt Bomb", 4, 2, 5));
        add(new ShardBomb("Shocking Salt Bomb", "Ionized Salt Bomb", 5, 2, 5));
        
        add(new ShardBomb("Splinter Bomb", "", 2, 3, 0));
        add(new ShardBomb("Super Splinter Bomb", "Splinter Bomb", 3, 3, 0));
        add(new ShardBomb("Heavy Splinter Bomb", "Super Splinter Bomb", 4, 3, 0));
        add(new ShardBomb("Deadly Splinter Bomb", "Heavy Splinter Bomb", 5, 3, 0));
        
        add(new SunShards("Sun Shards","Splinter Bomb",3));
        add(new SunShards("Radiant Sun Shards","Sun Shards",4));
        add(new SunShards("Scintillating Sun Shards","Radiant Sun Shards",5));
        
        add(new DankMatter("Dank Matter Bomb","Prize Box",5));
        
        //String name, String prereq,int stars, int chance
        add(new Cutter("Cutter","",2,20));
        add(new Cutter("Striker","Cutter",3,30));
        add(new Cutter("Vile Striker","Striker",4,40));
        add(new Cutter("Dread Venom Striker","Vile Striker",5,50));
        
        //String name, String prereq, int stars, int mat, int mat1, int mat2
        add(new DMGVSX("Hunting Blade","Striker",4,0,3,7));
        add(new DMGVSX("Wild Hunting Blade","Hunting Blade",5,0,3,7));
        
        add(new DMGVSX("Deconstructor","Super Blast Bomb",4,7,1,8));
        add(new DMGVSX("Heavy Deconstructor","Deconstructor",5,7,1,8));
        
        add(new DMGVSX("Antigua","Jelly Gems",3,3,-1,-1));
        add(new DMGVSX("Raptor","Antigua",4,3,9,2));
        add(new DMGVSX("Gilded Griffin","Raptor",5,3,9,2));        
        add(new DMGVSX("Blackhawk","Antigua",4,1,9,5));
        add(new DMGVSX("Sentenza","Blackhawk",5,1,9,5));
        add(new DMGVSX("Silversix","Antigua",4,9,9,2));
        add(new DMGVSX("Argent Peacemaker","Silversix",5,9,9,2));
        
        add(new AutoDeposit("Cold Iron Carver","Tempered Calibur",4,"green",1,3));
        add(new AutoDeposit("Cold Iron Vanquisher","Cold Iron Carver",5,"green",1,3));
        
        //String name, String prereq,int stars, int time, int num, int power
        add(new BlastBomb("Blast Bomb","",2,50,5,2));
        add(new BlastBomb("Super Blast Bomb","Blast Bomb",3,33,5,2));
        add(new BlastBomb("Master Blast Bomb","Super Blast Bomb",4,25,5,2));
        add(new BlastBomb("Nitronome","Master Blast Bomb",5,20,5,2));
        add(new BlastBomb("Blast Network Bomb","Master Blast Bomb",5,5,3,1));
        add(new BlastBomb("Irontech Bomb","Super Blast Bomb",4,50,15,3));
        add(new BlastBomb("Irontech Destroyer","Irontech Bomb",5,40,15,3));
        add(new BlastBomb("Big Angry Bomb","Master Blast Bomb",5,80,30,4));
        
        add(new GF("Sealed Sword","Jelly Gems",3,3,50));
        add(new GF("Faust","Sealed Sword",4,4,70));
        add(new GF("Gran Faust","Faust",5,5,80));
        add(new DA("Avenger","Sealed Sword",4,5,70));
        add(new DA("Divine Avenger","Avenger",5,7,100));
        
        //String name, String prereq,int stars, int chance
        add(new Autogun("Autogun","",2,30));
        add(new Autogun("Needle Shot","Autogun",3,40));
        add(new Autogun("Strike Needle","Needle Shot",4,50));
        add(new Autogun("Blitz Needle","Strike Needle",5,70));
        
        add(new PoisonAutogun("Toxic Needle","Autogun",3,60));
        add(new PoisonAutogun("Blight Needle","Toxic Needle",4,80));
        add(new PoisonAutogun("Plague Needle","Blight Needle",5,100));
        
        add(new PBox("Pepperbox","Autogun",3,50));
        add(new PBox("Fiery Pepperbox","Pepperbox",4,55));
        add(new PBox("Volcanic Pepperbox","Fiery Pepperbox",5,60));
        
        add(new GRepeater("Dark Chaingun","Autogun",3,50));
        add(new GRepeater("Black Chaingun","Dark Chaingun",4,55));
        add(new GRepeater("Grim Repeater","Black Chaingun",5,60));
        
        //String name, String prereq,int stars, int chance
        add(new Pulsar("Pulsar","Bark Modules",2,5));
        add(new Pulsar("Kilowatt Pulsar","Pulsar",3,10));
        add(new Pulsar("Gigawatt Pulsar","Kilowatt Pulsar",4,15));
        add(new Pulsar("Polaris","Gigawatt Pulsar",5,20));
        
        add(new NPulsar("Heavy Pulsar","Pulsar",3,1));
        add(new NPulsar("Radiant Pulsar","Heavy Pulsar",4,2));
        add(new NPulsar("Supernova","Radiant Pulsar",5,3));
        
        add(new DPulsar("Freezing Pulsar","Pulsar",3,10));
        add(new DPulsar("Frozen Pulsar","Freezing Pulsar",4,15));
        add(new DPulsar("Permafroster","Frozen Pulsar",5,20));
        
        add(new FPulsar("Flaming Pulsar","Pulsar",3,10));
        add(new FPulsar("Blazing Pulsar","Flaming Pulsar",4,15));
        add(new FPulsar("Wildfire","Blazing Pulsar",5,20));
        
        add(new Spur("Spur","",2,15));
        add(new Spur("Arc Razor","Spur",3,25));
        add(new Spur("Winmillion","Arc Razor",4,35));
        
        add(new Catalyzer("Catalyzer","Bark Modules",2,5));
        add(new Catalyzer("Toxic Catalyzer","Catalyzer",3,10));
        add(new Catalyzer("Virulent Catalyzer","Toxic Catalyzer",4,15));
        add(new Catalyzer("Biohazard","Virulent Catalyzer",5,20));
        
        add(new NCatalyzer("Industrial Catalyzer","Catalyzer",3,25));
        add(new NCatalyzer("Volatile Catalyzer","Industrial Catalyzer",4,50));
        add(new NCatalyzer("Neutralizer","Volatile Catalyzer",5,100));
        
        //String name, String prereq,int stars, String Col, int min, int mat2
        add(new AutoDeposit("Brandish","",2,"red",0,2));
        add(new AutoDeposit("Fireburst Brandish","Brandish",3,"red",0,2));
        add(new AutoDeposit("Blazebrand","Fireburst Brandish",4,"red",0,2));
        add(new AutoDeposit("Combuster","Blazebrand",5,"red",0,2));
        
        add(new AutoDeposit("Iceburst Brandish","Brandish",3,"blue",2,4));
        add(new AutoDeposit("Blizzbrand","Iceburst Brandish",4,"blue",2,4));
        add(new AutoDeposit("Glacius","Blizzbrand",5,"blue",2,4));
        
        add(new AutoDeposit("Shockburst Brandish","Brandish",3,"yellow",3,8));
        add(new AutoDeposit("Boltbrand","Shockburst Brandish",4,"yellow",3,8));
        add(new AutoDeposit("Voltedge","Boltbrand",5,"yellow",3,8));
        
        add(new AutoDeposit("Nightblade","Brandish",3,"purple",4,3));
        add(new AutoDeposit("Silent Nightblade","Nightblade",4,"purple",4,3));
        add(new AutoDeposit("Acheron","Silent Nightblade",5,"purple",4,3));
        
        
        add(new CauteryDeposit("Cautery Sword","Silent Nightblade",5,5));
        add(new Caladbolg("Caladbolg","Prize Box",5));
        
        add(new Levi("Calibur","",2));
        add(new Levi("Tempered Calibur","Calibur",3));
        add(new Levi("Ascended Calibur","Tempered Calibur",4));
        add(new Levi("Leviathan Blade","Ascended Calibur",5));
        
        add(new Troika("Troika","",2));
        add(new Troika("Kamarin","Troika",3));
        add(new Troika("Khorovod","Kamarin",4));
        add(new Troika("Sudaruska","Khorovod",5));
        
        add(new IceTroika("Jalovec","Troika",3));
        add(new IceTroika("Grintovec","Jalovec",4));
        add(new IceTroika("Triglav","Grintovec",5));
        
        add(new Blaster("Blaster","",2,2,2));
        add(new Blaster("Super Blaster","Blaster",3,4,3));
        add(new Blaster("Master Blaster","Super Blaster",4,6,4));
        add(new Blaster("Valiance","Master Blaster",5,8,5));
        
        add(new BlasterP("Pierce Blaster","Blaster",3,5,3));
        add(new BlasterP("Breach Blaster","Pierce Blaster",4,10,4));
        add(new BlasterP("Riftlocker","Breach Blaster",5,15,5));
        
        add(new BlasterE("Elemental Blaster","Blaster",3,5,3));
        add(new BlasterE("Fusion Blaster","Elemental Blaster",4,10,4));
        add(new BlasterE("Arcana","Fusion Blaster",5,15,5));
        
        add(new BlasterS("Shadow Blaster","Blaster",3,10,3));
        add(new BlasterS("Umbral Blaster","Shadow Blaster",4,5,4));
        add(new BlasterS("Phantamos","Umbral Blaster",5,2,5));
        
        //String name, String prereq,int stars, int thresh, int multi
        add(new Snarb("Snarble Barb","Frumious Fangs",2,10000,4));
        add(new Snarb("Twisted Snarble Barb","Snarble Barb",3,50000,4));
        add(new Snarb("Dark Thorn Blade","Twisted Snarble Barb",4,100000,4));
        add(new Snarb("Barbarous Thorn Blade","Dark Thorn Blade",5,100000,5));
        
        add(new Snarbomb("Spine Cone","Frumious Fangs",2,.02));
        add(new Snarbomb("Twisted Spine Cone","Spine Cone",3,.04));
        add(new Snarbomb("Spike Shower","Twisted Spine Cone",4,.05));
        add(new Snarbomb("Dark Briar Barrage","Spike Shower",5,.07));
        
        add(new SwitchShot("Prismatech Alchemer","",2));
        add(new SwitchShot("Prismatech Alchemer Mkii","Prismatech Alchemer",3));
        add(new SwitchShot("Prisma Driver","Prismatech Alchemer Mkii",4));
        add(new SwitchShot("Nova Driver","Prisma Driver",5));
        
        add(new SwitchShotStatus("Firotech Alchemer","",2,0,3,2,5));
        add(new SwitchShotStatus("Firotech Alchemer Mkii","Firotech Alchemer",3,0,3,2,5));
        add(new SwitchShotStatus("Firo Driver","Firotech Alchemer Mkii",4,0,3,2,5));
        add(new SwitchShotStatus("Magma Driver","Firo Driver",5,0,3,2,5));
        
        add(new SwitchShotStatus("Voltech Alchemer","",2,2,4,8,5));
        add(new SwitchShotStatus("Voltech Alchemer Mkii","Voltech Alchemer",3,2,4,8,5));
        add(new SwitchShotStatus("Volt Driver","Voltech Alchemer Mkii",4,2,4,8,5));
        add(new SwitchShotStatus("Storm Driver","Volt Driver",5,2,4,8,5));
        
        add(new SwitchShotStatus("Cryotech Alchemer","",2,2,3,4,5));
        add(new SwitchShotStatus("Cryotech Alchemer Mkii","Cryotech Alchemer",3,2,3,4,5));
        add(new SwitchShotStatus("Cryo Driver","Cryotech Alchemer Mkii",4,2,3,4,5));
        add(new SwitchShotStatus("Hail Driver","Cryo Driver",5,2,3,4,5));
        
        add(new SwitchShotSlime("Shadowtech Alchemer","",2));
        add(new SwitchShotSlime("Shadowtech Alchemer Mkii","Shadowtech Alchemer",3));
        add(new SwitchShotSlime("Shadow Driver","Shadowtech Alchemer Mkii",4));
        add(new SwitchShotSlime("Umbra Driver","Shadow Driver",5));
        
        add(new StaggerStorm("Haze Bomb","",2));
        add(new StaggerStorm("Haze Bomb Mkii","Haze Bomb",3));
        add(new StaggerStorm("Haze Burst","Haze Bomb Mkii",4));
        add(new StaggerStorm("Stagger Storm","Haze Burst",5));
        
        add(new Shivermist("Freezing Vaporizer","",2));
        add(new Shivermist("Freezing Vaporizer Mkii","Freezing Vaporizer",3));
        add(new Shivermist("Freezing Atomizer","Freezing Vaporizer Mkii",4));
        add(new Shivermist("Shivermist Buster","Freezing Atomizer",5));
        
        add(new VenomVeiler("Toxic Vaporizer","",2));
        add(new VenomVeiler("Toxic Vaporizer Mkii","Toxic Vaporizer",3));
        add(new VenomVeiler("Toxic Atomizer","Toxic Vaporizer Mkii",4));
        add(new VenomVeiler("Venom Veiler","Toxic Atomizer",5));
        
        add(new Ash("Fiery Vaporizer","",2));
        add(new Ash("Fiery Vaporizer Mkii","Fiery Vaporizer",3));
        add(new Ash("Fiery Atomizer","Fiery Vaporizer Mkii",4));
        add(new Ash("Ash Of Agni","Fiery Atomizer",5));
        
        add(new Voltaic("Static Capacitor","Bark Modules",2));
        add(new Voltaic("Lightening Capacitor","Static Capacitor",3));
        add(new Voltaic("Plasma Capacitor","Lightening Capacitor",4));
        add(new Voltaic("Voltaic Tempest","Plasma Capacitor",5));
        
        add(new FoV("Fang Of Vog","Almirian Seals"));
        
        add(new Gravy("Graviton Charge","",3));
        add(new Gravy("Graviton Bomb","Graviton Charge",4));
        add(new Gravy("Graviton Vortex","Graviton Bomb",5));
        
        add(new WRH("Warmaster Rocket Hammer","OCH"));
        add(new DarkRetribution("Dark Retribution","OCH"));
        
        add(new ShadowStriker("Void Striker","Prize Box",5));
        
        add(new Mixer("Overcharged Mixmaster","Prize Box",5,10000));
        add(new Mixer("Celestial Orbitgun","Prize Box",5,10000));
        add(new CSaber("Celestial Saber","Prize Box",5));
        
        add(new SpiralSoaker("Spiral Soaker","Prize Box",5,10));
        
        add(new ScissorBlades("Scissor Blades","Shop",5));
        
        add(new WrenchWand());
        add(new Misery());
        add(new DarkRituals());
        add(new MonsterBone());
        add(new Slippers("Wolver Slippers","HIDDEN OBJECT",5));
        
        //Obsidian stuff
        add(new SweetDreams("Sweet Dreams","Prize Box",5));
        add(new TeddyBuckler("Teddy Bear Buckler","Prize Box",5));
        add(new Obsidian("Obsidian Edge","Silent Nightblade",5,"Trade one soul to activate a random machine three times!"));
        add(new Obsidian("Obsidian Carbine","Blackhawk",5,"Activate a random machine once for free, 95% of the time! +15% chance to gain a soul"));
        add(new Obsidian("Obsidian Crusher","Graviton Bomb",5,"Trade one soul for a 5% chance to activate ALL machines!"));
    }
    
    private static void add(Gear G){
        gearList.add(G.name);
        gearMap.put(G.name, G);
    }
    
    public static String bases(){
        String ret ="**Craftable 2 star gear:**\n";
        for(String s : gearList){
            Gear G = gearMap.get(s);
            if(G.prereq.equals("")){
                ret += "**"+G.name+":** "+G.description+"\n";
            }
        }
        ret+="**Type !info <gearname> for more info on a piece of gear, or !up <gearname> to see its upgrades.**";
        return ret;
    }
    
    public static String upgrades(String pre){
        String ret = "";
        for(String s : gearList){
            Gear G = gearMap.get(s);
            if(G.prereq.toLowerCase().equals(pre.toLowerCase())){
                ret += (G + "\n");
            }
        }
        if(ret.equals("")){
            return "None found";
        } else {
            return ret;
        }
    }
    
    public static int getMatCosts(int star){
        switch(star){
            case 2:
                return 25;
            case 3:
                return 75;
            case 4:
                return 200;
            case 5:
                return 500;
            default:
                return 0;
        }
    }
}
