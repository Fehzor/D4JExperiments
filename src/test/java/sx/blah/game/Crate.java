package sx.blah.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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

/**
 *
 * @author FF6EB4
 */
public class Crate implements Serializable{
    public String loot = "NULL";
    long startTime;
    
    public static ArrayList<String> allLoot = loadWords();
    public static Random oRan = new Random();
    
    
    
    public Crate(){
        startTime = System.currentTimeMillis();
        loot = allLoot.get(oRan.nextInt(allLoot.size()));
        
    }
    
    public long check(){
        long now = System.currentTimeMillis();
        long dif = now - startTime;
        return (60000*5) - dif;
        //return 100 - dif;
    }
    
    public static ArrayList<String> loadWords(){
        ArrayList<String> ret = new ArrayList<>();
        
        String parse = "Proto Sword \n" +
                    "Beast Basher \n" +
                    "Hatchet \n" +
                    "Robo Wrecker \n" +
                    "Slime Slasher \n" +
                    "Big Beast Basher \n" +
                    "Bolted Blade \n" +
                    "Heavy Hatchet \n" +
                    "Hot Edge  \n" +
                    "Prototype Rocket Hammer \n" +
                    "Rugged Robo Wrecker \n" +
                    "Static Edge  \n" +
                    "Super Slime Slasher \n" +
                    "Thwack Hammer \n" +
                    "Brandish \n" +
                    "Calibur \n" +
                    "Cutter \n" +
                    "Flourish \n" +
                    "Searing Edge  \n" +
                    "Shocking Edge  \n" +
                    "Snarble Barb \n" +
                    "Spur \n" +
                    "Troika \n" +
                    "Wrench Wand \n" +
                    "Arc Razor \n" +
                    "Cautery Sword \n" +
                    "Fireburst Brandish  \n" +
                    "Flamberge \n" +
                    "Grintovec \n" +
                    "Iceburst Brandish  \n" +
                    "Kamarin \n" +
                    "Nightblade  \n" +
                    "Rigadoon \n" +
                    "Sealed Sword \n" +
                    "Shockburst Brandish  \n" +
                    "Stable Rocket Hammer \n" +
                    "Striker \n" +
                    "Swift Flourish \n" +
                    "Tempered Calibur \n" +
                    "Twisted Snarble Barb \n" +
                    "Ascended Calibur \n" +
                    "Avenger  \n" +
                    "Blazebrand  \n" +
                    "Blizzbrand  \n" +
                    "Boltbrand  \n" +
                    "Cold Iron Carver \n" +
                    "Daring Rigadoon \n" +
                    "Dark Thorn Blade \n" +
                    "Faust  \n" +
                    "Fierce Flamberge \n" +
                    "Grand Flourish \n" +
                    "Hunting Blade \n" +
                    "Jalovec \n" +
                    "Khorovod \n" +
                    "Silent Nightblade  \n" +
                    "Vile Striker \n" +
                    "Winmillion \n" +
                    "Acheron  \n" +
                    "Barbarous Thorn Blade \n" +
                    "Cold Iron Vanquisher \n" +
                    "Combuster  \n" +
                    "Divine Avenger  \n" +
                    "Dread Venom Striker \n" +
                    "Fang of Vog  \n" +
                    "Fearless Rigadoon \n" +
                    "Final Flourish \n" +
                    "Furious Flamberge \n" +
                    "Glacius  \n" +
                    "Gran Faust  \n" +
                    "Leviathan Blade \n" +
                    "Scissor Blades \n" +
                    "Sudaruska \n" +
                    "Triglav \n" +
                    "Voltedge  \n" +
                    "Warmaster Rocket Hammer \n" +
                    "Wild Hunting Blade\n"+
                    "Proto Gun \n" +
                    "Punch Gun \n" +
                    "Stun Gun \n" +
                    "Frost Gun \n" +
                    "Pummel Gun \n" +
                    "Super Stun Gun \n" +
                    "Zapper \n" +
                    "Autogun \n" +
                    "Blaster \n" +
                    "Catalyzer \n" +
                    "Cryotech Alchemer \n" +
                    "Firotech Alchemer \n" +
                    "Prismatech Alchemer \n" +
                    "Pulsar \n" +
                    "Shadowtech Alchemer \n" +
                    "Voltech Alchemer \n" +
                    "Antigua \n" +
                    "Cryotech Alchemer Mk II \n" +
                    "Firotech Alchemer Mk II \n" +
                    "Heavy Pulsar \n" +
                    "Industrial Catalyzer \n" +
                    "Kilowatt Pulsar \n" +
                    "Magnus \n" +
                    "Needle Shot \n" +
                    "Pepperbox \n" +
                    "Prismatech Alchemer Mk II \n" +
                    "Shadowtech Alchemer Mk II \n" +
                    "Super Blaster \n" +
                    "Toxic Catalyzer \n" +
                    "Toxic Needle \n" +
                    "Voltech Alchemer Mk II \n" +
                    "Blackhawk \n" +
                    "Blight Needle \n" +
                    "Cryo Driver \n" +
                    "Fiery Pepperbox \n" +
                    "Firo Driver \n" +
                    "Gigawatt Pulsar \n" +
                    "Master Blaster \n" +
                    "Mega Magnus \n" +
                    "Prisma Driver \n" +
                    "Radiant Pulsar \n" +
                    "Shadow Driver \n" +
                    "Silversix \n" +
                    "Strike Needle \n" +
                    "Virulent Catalyzer \n" +
                    "Volatile Catalyzer \n" +
                    "Volt Driver \n" +
                    "Argent Peacemaker \n" +
                    "Biohazard \n" +
                    "Blitz Needle \n" +
                    "Callahan \n" +
                    "Hail Driver \n" +
                    "Iron Slug \n" +
                    "Magma Driver \n" +
                    "Neutralizer \n" +
                    "Nova Driver \n" +
                    "Plague Needle \n" +
                    "Polaris \n" +
                    "Sentenza \n" +
                    "Storm Driver \n" +
                    "Supernova \n" +
                    "Umbra Driver \n" +
                    "Valiance \n" +
                    "Volcanic Pepperbox\n"+
                    "Proto Bomb \n" +
                    "Cold Snap \n" +
                    "Firecracker \n" +
                    "Static Flash \n" +
                    "Blast Bomb \n" +
                    "Crystal Bomb \n" +
                    "Dark Matter Bomb \n" +
                    "Dark Reprisal \n" +
                    "Fiery Vaporizer \n" +
                    "Freezing Vaporizer \n" +
                    "Haze Bomb \n" +
                    "Shard Bomb \n" +
                    "Spine Cone \n" +
                    "Splinter Bomb \n" +
                    "Static Capacitor \n" +
                    "Toxic Vaporizer \n" +
                    "Dark Reprisal Mk II \n" +
                    "Deconstructor \n" +
                    "Electron Charge \n" +
                    "Fiery Vaporizer Mk II \n" +
                    "Freezing Vaporizer Mk II \n" +
                    "Graviton Charge \n" +
                    "Haze Bomb Mk II \n" +
                    "Lightning Capacitor \n" +
                    "Rock Salt Bomb  \n" +
                    "Sun Shards  \n" +
                    "Super Blast Bomb \n" +
                    "Super Crystal Bomb \n" +
                    "Super Dark Matter Bomb \n" +
                    "Super Shard Bomb \n" +
                    "Super Splinter Bomb \n" +
                    "Toxic Vaporizer Mk II \n" +
                    "Twisted Spine Cone \n" +
                    "Electron Bomb \n" +
                    "Fiery Atomizer \n" +
                    "Freezing Atomizer \n" +
                    "Graviton Bomb \n" +
                    "Haze Burst \n" +
                    "Heavy Crystal Bomb \n" +
                    "Heavy Dark Matter Bomb \n" +
                    "Heavy Deconstructor \n" +
                    "Heavy Shard Bomb \n" +
                    "Heavy Splinter Bomb \n" +
                    "Ionized Salt Bomb  \n" +
                    "Irontech Bomb \n" +
                    "Master Blast Bomb \n" +
                    "Plasma Capacitor \n" +
                    "Radiant Sun Shards  \n" +
                    "Spike Shower \n" +
                    "Toxic Atomizer \n" +
                    "Ash of Agni \n" +
                    "Big Angry Bomb \n" +
                    "Dark Briar Barrage \n" +
                    "Dark Retribution \n" +
                    "Deadly Crystal Bomb \n" +
                    "Deadly Dark Matter Bomb \n" +
                    "Deadly Shard Bomb \n" +
                    "Deadly Splinter Bomb \n" +
                    "Electron Vortex \n" +
                    "Graviton Vortex \n" +
                    "Irontech Destroyer \n" +
                    "Nitronome \n" +
                    "Scintillating Sun Shards \n" +
                    "Shivermist Buster \n" +
                    "Shocking Salt Bomb \n" +
                    "Stagger Storm \n" +
                    "Venom Veiler \n" +
                    "Voltaic Tempest \n"+
                    "Spiral Pith Helm\n" +
                    "Spiral Sallet\n" +
                    "Spiral Tailed Helm\n" +
                    "Cyclops Cap\n" +
                    "Groundbreaker Helm\n" +
                    "Mining Hat\n" +
                    "Circuit Breaker Helm\n" +
                    "Emberbreak Helm\n" +
                    "Fractured Mask of Seerus\n" +
                    "Frostbreaker Helm\n" +
                    "Spiral Scale Helm\n" +
                    "Cobalt Helm\n" +
                    "Firebreak Helm\n" +
                    "Icebreaker Helm\n" +
                    "Jelly Helm\n" +
                    "Magic Hood\n" +
                    "Skelly Mask\n" +
                    "Spiral Demo Helm\n" +
                    "Spiral Plate Helm\n" +
                    "Volt Breaker Helm\n" +
                    "Wolver Cap\n" +
                    "Angelic Helm\n" +
                    "Blazebreak Helm\n" +
                    "Blizzbreaker Helm\n" +
                    "Boosted Plate Helm\n" +
                    "Brute Jelly Helm\n" +
                    "Chroma Mask\n" +
                    "Drake Scale Helm\n" +
                    "Dusker Cap\n" +
                    "Elemental Hood\n" +
                    "Flawed Mask of Seerus\n" +
                    "Fused Demo Helm\n" +
                    "Gunslinger Hat\n" +
                    "Quicksilver Helm\n" +
                    "Scary Skelly Mask\n" +
                    "Solid Cobalt Helm\n" +
                    "Surge Breaker Helm\n" +
                    "Ash Tail Cap\n" +
                    "Charged Quicksilver Helm\n" +
                    "Heavy Demo Helm\n" +
                    "Heavy Plate Helm\n" +
                    "Mighty Cobalt Helm\n" +
                    "Miracle Hood\n" +
                    "Rock Jelly Helm\n" +
                    "Salamander Mask\n" +
                    "Seraphic Helm\n" +
                    "Sinister Skelly Mask\n" +
                    "Sunset Stetson\n" +
                    "Virulisk Mask\n" +
                    "Wyvern Scale Helm\n" +
                    "Almirian Crusader Helm\n" +
                    "Ancient Plate Helm\n" +
                    "Arcane Salamander Mask\n" +
                    "Azure Guardian Helm\n" +
                    "Bombastic Demo Helm\n" +
                    "Chaos Cowl\n" +
                    "Crown of the Fallen\n" +
                    "Deadly Virulisk Mask\n" +
                    "Deadshot Chapeau\n" +
                    "Divine Veil\n" +
                    "Dragon Scale Helm\n" +
                    "Dread Skelly Mask\n" +
                    "Grey Feather Cowl\n" +
                    "Heavenly Iron Helm\n" +
                    "Ice Queen Crown\n" +
                    "Ironmight Plate Helm\n" +
                    "Justifier Hat\n" +
                    "Mad Bomber Mask\n" +
                    "Mercurial Demo Helm\n" +
                    "Mercurial Helm\n" +
                    "Nameless Hat\n" +
                    "Perfect Mask of Seerus\n" +
                    "Royal Jelly Crown\n" +
                    "Shadowsun Stetson\n" +
                    "Skolver Cap\n" +
                    "Snarbolax Cap\n" +
                    "Valkyrie Helm\n" +
                    "Vog Cub Cap\n" +
                    "Volcanic Demo Helm\n" +
                    "Volcanic Plate Helm\n" +
                    "Volcanic Salamander Mask"+
                    "Spiral Brigandine\n" +
                    "Spiral Cuirass\n" +
                    "Spiral Culet\n" +
                    "Fencing Jacket\n" +
                    "Groundbreaker Armor\n" +
                    "Vitasuit\n" +
                    "Circuit Breaker Armor\n" +
                    "Emberbreak Armor\n" +
                    "Frostbreaker Armor\n" +
                    "Spiral Scale Mail\n" +
                    "Cobalt Armor\n" +
                    "Firebreak Armor\n" +
                    "Icebreaker Armor\n" +
                    "Jelly Mail\n" +
                    "Magic Cloak\n" +
                    "Skelly Suit\n" +
                    "Spiral Demo Suit\n" +
                    "Spiral Plate Mail\n" +
                    "Vitasuit Plus\n" +
                    "Volt Breaker Armor\n" +
                    "Wolver Coat\n" +
                    "Angelic Raiment\n" +
                    "Blazebreak Armor\n" +
                    "Blizzbreaker Armor\n" +
                    "Boosted Plate Mail\n" +
                    "Brute Jelly Mail\n" +
                    "Chroma Suit\n" +
                    "Drake Scale Mail\n" +
                    "Dusker Coat\n" +
                    "Elemental Cloak\n" +
                    "Fused Demo Suit\n" +
                    "Gunslinger Sash\n" +
                    "Quicksilver Mail\n" +
                    "Scary Skelly Suit\n" +
                    "Solid Cobalt Armor\n" +
                    "Surge Breaker Armor\n" +
                    "Ash Tail Coat\n" +
                    "Charged Quicksilver Mail\n" +
                    "Heavy Demo Suit\n" +
                    "Heavy Plate Mail\n" +
                    "Mighty Cobalt Armor\n" +
                    "Miracle Cloak\n" +
                    "Rock Jelly Mail\n" +
                    "Salamander Suit\n" +
                    "Seraphic Mail\n" +
                    "Silvermail\n" +
                    "Sinister Skelly Suit\n" +
                    "Sunset Duster\n" +
                    "Virulisk Suit\n" +
                    "Vitasuit Deluxe\n" +
                    "Wyvern Scale Mail\n" +
                    "Almirian Crusader Armor\n" +
                    "Ancient Plate Mail\n" +
                    "Arcane Salamander Suit\n" +
                    "Armor of the Fallen\n" +
                    "Azure Guardian Armor\n" +
                    "Bombastic Demo Suit\n" +
                    "Chaos Cloak\n" +
                    "Deadly Virulisk Suit\n" +
                    "Deadshot Mantle\n" +
                    "Divine Mantle\n" +
                    "Dragon Scale Mail\n" +
                    "Dread Skelly Suit\n" +
                    "Grey Feather Mantle\n" +
                    "Heavenly Iron Armor\n" +
                    "Ice Queen Mail\n" +
                    "Ironmight Plate Mail\n" +
                    "Justifier Jacket\n" +
                    "Mad Bomber Suit\n" +
                    "Mercurial Demo Suit\n" +
                    "Mercurial Mail\n" +
                    "Nameless Poncho\n" +
                    "Radiant Silvermail\n" +
                    "Royal Jelly Mail\n" +
                    "Shadowsun Slicker\n" +
                    "Skolver Coat\n" +
                    "Snarbolax Coat\n" +
                    "Valkyrie Mail\n" +
                    "Vog Cub Coat\n" +
                    "Volcanic Demo Suit\n" +
                    "Volcanic Plate Mail\n" +
                    "Volcanic Salamander Suit"+
                    "Proto Shield\n" +
                    "Iron Buckler\n" +
                    "Circuit Breaker Shield\n" +
                    "Emberbreak Shield\n" +
                    "Force Buckler\n" +
                    "Frostbreaker Shield\n" +
                    "Green Ward\n" +
                    "Scale Shield\n" +
                    "Bristling Buckler\n" +
                    "Defender\n" +
                    "Firebreak Shield\n" +
                    "Icebreaker Shield\n" +
                    "Jelly Shield\n" +
                    "Owlite Shield\n" +
                    "Plate Shield\n" +
                    "Skelly Shield\n" +
                    "Volt Breaker Shield\n" +
                    "Blazebreak Shield\n" +
                    "Blizzbreaker Shield\n" +
                    "Boosted Plate Shield\n" +
                    "Brute Jelly Shield\n" +
                    "Drake Scale Shield\n" +
                    "Great Defender\n" +
                    "Horned Owlite Shield\n" +
                    "Scarlet Shield\n" +
                    "Scary Skelly Shield\n" +
                    "Surge Breaker Shield\n" +
                    "Swiftstrike Buckler\n" +
                    "Twisted Targe\n" +
                    "Blackened Crest\n" +
                    "Dark Thorn Shield\n" +
                    "Darkfang Shield\n" +
                    "Heavy Plate Shield\n" +
                    "Mighty Defender\n" +
                    "Rock Jelly Shield\n" +
                    "Sinister Skelly Shield\n" +
                    "Stone Tortoise\n" +
                    "Wise Owlite Shield\n" +
                    "Wyvern Scale Shield\n" +
                    "Aegis\n" +
                    "Ancient Plate Shield\n" +
                    "Barbarous Thorn Shield\n" +
                    "Crest of Almire\n" +
                    "Dragon Scale Shield\n" +
                    "Dread Skelly Shield\n" +
                    "Grey Owlite Shield\n" +
                    "Heater Shield\n" +
                    "Ironmight Plate Shield\n" +
                    "Omega Shell\n" +
                    "Royal Jelly Shield\n" +
                    "Volcanic Plate Shield" +
                    "Heart Pendant\n" +
                    "Crystal Pin\n" +
                    "Driftwood Bracelet\n" +
                    "Dual Heart Pendant\n" +
                    "Hearthstone Pendant\n" +
                    "Jelly Band\n" +
                    "Katnip Pouch\n" +
                    "Silver Amulet\n" +
                    "Skelly Charm\n" +
                    "Wetstone Pendant\n" +
                    "White Laurel\n" +
                    "Blessed Silver Amulet\n" +
                    "Bright White Laurel\n" +
                    "Brute Jelly Band\n" +
                    "Choice Katnip Pouch\n" +
                    "Dewy Wetstone Pendant\n" +
                    "Glowing Crystal Pin\n" +
                    "Redwood Bracelet\n" +
                    "Scale Pendant\n" +
                    "Scary Skelly Charm\n" +
                    "Tri-Heart Pendant\n" +
                    "Warm Hearthstone Pendant\n" +
                    "Bomb Focus Module\n" +
                    "Boom Module\n" +
                    "Glimmering Crystal Pin\n" +
                    "Handgun Focus Module\n" +
                    "Hi-Grade Katnip Pouch\n" +
                    "Ironwood Bracelet\n" +
                    "Quick Draw Module\n" +
                    "Quick Strike Module\n" +
                    "Rock Jelly Band\n" +
                    "Sacred Silver Amulet\n" +
                    "Sinister Skelly Charm\n" +
                    "Slippery Wetstone Pendant\n" +
                    "Slash Module\n" +
                    "Snowy White Laurel\n" +
                    "Sword Focus Module\n" +
                    "Tetra-Heart Pendant\n" +
                    "Toasty Hearthstone Pendant\n" +
                    "Trueshot Module\n" +
                    "Dread Skelly Charm\n" +
                    "Elite Bomb Focus Module\n" +
                    "Elite Boom Module\n" +
                    "Elite Handgun Focus Module\n" +
                    "Elite Quick Draw Module\n" +
                    "Elite Quick Strike Module\n" +
                    "Elite Slash Module\n" +
                    "Elite Sword Focus Module\n" +
                    "Elite Trueshot Module\n" +
                    "Penta-Heart Pendant\n" +
                    "Pure White Laurel\n" +
                    "Purrfect Katnip Pouch\n" +
                    "Radiant Crystal Pin\n" +
                    "Royal Jelly Band\n" +
                    "Saintly Silver Amulet\n" +
                    "Sizzling Hearthstone Pendant\n" +
                    "Soaking Wetstone Pendant\n" +
                    "True Love Locket\n" +
                    "Wyrmwood Bracelet";
        
        String[] strings = parse.split("\n");
        
        for(int i = 0; i < strings.length; ++i){
            ret.add(strings[i]);
        }
        return ret;
    }
}
