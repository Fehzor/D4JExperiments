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
package Bopp;

/**
 *
 * @author FF6EB4
 */
public class HelpDesk {
    public static String parse(String message){
        if(message.equals("")){
            return "Welcome to the chat based version of Spiral Knights!"
                    + " Chatting inside of a tier will reward everyone with"
                    + " goodies, especially you- and by extension, you after"
                    + " you've left from others' messages! For more info, type"
                    + " one of the following:\n\n"
                    + "Starting topics:\n"
                    + "!help gear\n"
                    + "!help gates\n"
                    + "\nOther topics:\n"
                    + "!help shop\n"
                    + "!help boss\n"
                    + "!help advancement\n"
                    + "!help prestige\n"
                    + "!help slimecoins";
        }
        
        if(message.toLowerCase().equals("gear")){
            return "Your gear activates every time you chat (with a 40 second cooldown). Gear"
                    + " costs 3 orbs of the correct rarity (based on those in "
                    + "the actual Spiral Knights game) as well as several "
                    + "materials specified by each gear type. The following"
                    + " commands all relate to gear:\n"
                    + "!stuff = View all your stuff. This one is important.\n"
                    + "!gear = view all base gear.\n"
                    + "!upgrades <gear name> = view all upgrades to that piece "
                    + "of gear.\n"
                    + "!info <gearname> = inspect that piece of gear\n"
                    + "!craft <gear name> = craft a piece of gear with materials and orbs.\n"
                    + "!equip <A/B/C> <gear name> = equip a piece of gear to"
                    + " slot A, B, or C.\n";
        }
        
        if(message.toLowerCase().equals("gates")){
            return "Gates (Tier 1, 2 and 3) are powered by minerals! Remember \n"
                    + "gate building? Deposit the minerals you want to get the \n"
                    + "materials you need out! The more minerals there are in"
                    + "a gate the more materials you'll get out! Pinned to each "
                    + "tier is a list of what minerals are needed for each material."
                    + " Don't be shy about colluding with others! Commands are as follows:\n"
                    + "!gate = check the current gate you're in.\n"
                    + "!deposit <amt> <color> = deposit amt of colored mineral"
                    + "to the gate.";
        }
        
        if(message.toLowerCase().equals("advancement")){
            return "Gates for T2 and T3 are hidden without proper advancement!"
                    + " Once you have a full three 2 star or 4 star weapons, you"
                    + " can type !tier2 or !tier3 into chat respectively."
                    + " This will grant you permissions for those gates!";
        }
        
        if(message.toLowerCase().equals("shop")){
            return "Basically-\n"
                    + "!buy <object> = Buy the thing\n"
                    + "!shop = view all the shop's stuff";
        }
        
        if(message.toLowerCase().equals("boss")){
            return "Bosses can be found by depositing enough minerals into the "
                    + "appropriate tier:\n"
                    + "T1: Snarb = 1K,beast (beast = red/green, so 1K each of red/green need depositing into T1)\n"
                    + "T2: JK = 10K,slime\n"
                    + "T2: Roarm = 10K,construct\n"
                    + "T3: Vanaduke = 100K,fire";
        }
        
        
        if(message.toLowerCase().equals("prestige")){
            return "Prestige can be earned from prestige missions! "
                    + "Prestige increases your daily reward potential as well as"
                    + " increases the quality of your prize boxes!"
                    +" To view prestige missions type !prestige."
                    + "\n\n Prestige is scored as follows:"
                    + "\nMission A: 250 prestige; 250 of each mineral (!missionA)"
                    + "\nMission B: 1200 prestige; 1000 of each mineral (!missionB)"
                    + "\n5 star gear: 12500 prestige (!donate)"
                    + "\n4 star gear: 5000 prestige (!donate)"
                    + "\n3 star gear: 800 prestige (!donate)"
                    + "\n2 star gear: 200 prestige (!donate)"
                    + "\n\n If you're just starting out, don't sweat prestige.";
        }
        
        if(message.toLowerCase().equals("slimecoins")){
            return "1000 slime coins = 1 box."
                    + " Boxes can contain rare items, prestige and more!"
                    + " To open a box, type !unbox.";
        }
        
        if(message.toLowerCase().equals("stun")){
            return "Costs 10 fiend mats to use; Displays a random user's information.";
        }
        
        if(message.toLowerCase().equals("freeze")){
            return "Costs 30 fiend mats and 30 freeze mats to use;"
                    + " Kicks a random user from the tier; they can rejoin "
                    + "instantly if they're on by typing a message!";
        }
        
        if(message.toLowerCase().equals("poison")){
            return "Costs 100 poison mats to use;"
                    + " Displays a list of all users assigned to the gate.";
        }
        
        if(message.toLowerCase().equals("burn")){
            return "Costs 200 fire mats to use;"
                    + " Adds the user to all 3 tiers at once; chatting in any "
                    + "one gate will cancel the effect and move the user to"
                    + " that gate.";
        }
        
        if(message.toLowerCase().equals("shock")){
            return "Costs 100 shock mats to use;"
                    + " Activates the user's weapons 10 times.";
        }
        
        if(message.toLowerCase().equals("apocrea")){
            return "Souls can be bought in the shop; "
                    + "Commands are as follows:\n"
                    + "!souls = check how many souls (which are shared among all apocreans)\n"
                    + "!machinate <weapon> = sacrifice a weapon (that weapon becomes a machine!\n"
                    + "!machines = your list of machines";
        }
        
        if(message.toLowerCase().equals("wolver")){
            return "!fetch = throw and fetch the bone\n"
                    + "!shake = paw shake\n"
                    + "!sit = sit down\n"
                    + "!reward = feed it 10 delicious nutricious canabilicious beast mats\n"
                    + "!punish = gently harass";
        }
        
        if(message.toLowerCase().equals("devilites")){
            return "Devilite commands are:\n"
                + "!summon = summon more devilite followers; +devilites\n" +
                "!lazy = turn lazy workers into gorgos; +gorgos; -devilites\n" +
                "!sacrifice = sacrifice gorgos; +prestige; -gorgos\n" +
                "!paperwork = produce lazy workers; +lazy devilites";
        }
        
        return "I didn't quite catch that.";
    }
}
