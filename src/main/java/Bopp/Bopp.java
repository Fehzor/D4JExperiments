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

import Bopp.Data.Dailies;
import Bopp.Data.Missions;
import Bopp.Data.Unboxing;
import Bopp.Data.UserData;
import Bopp.Gates.Gate;
import static Bopp.Gates.Gate.oRan;
import Bopp.Items.Gear;
import Bopp.Items.Weapons.Misery;
import Bopp.Items.Weapons.MonsterBone;
import Bopp.Items.Weapons.Obsidian;
import Bopp.Items.Weapons.Slippers;
import Bopp.Items.Weapons.WrenchWand;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.text.Document;
import org.apache.commons.lang3.text.WordUtils;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

/**
 * This represents a SUPER basic bot (literally all it does is login).
 * This is used as a base for all example bots.
 */
public class Bopp implements IListener<MessageReceivedEvent>{

	public static Bopp INSTANCE; // Singleton instance of the bot.
	public static IDiscordClient client; // The instance of the discord client.
        
        //Area A1 = new Area("gate_a","281840355694084097");
        //Area A2 = new Area("gate_b","281935461688016896");
        //Area A3 = new Area("gate_c","281935872738328597");
        
        public static Gate A;
        public static Gate B;
        public static Gate C;
        public static Gate D;
        
        public static long memetime = 0;
        
	public static void main(String[] args) { // Main method
		INSTANCE = login("Mjc1MzcwMDE2Mjk4MzY5MDI1.C3s07A.qQRhQAp7R61A-NYTvejx1JGWJhw"); // Creates the bot instance and logs it in.
        }

	public Bopp(IDiscordClient client) {
		this.client = client; // Sets the client instance to the one provided
                
                EventDispatcher dispatcher = client.getDispatcher(); // Gets the client's event dispatcher
		dispatcher.registerListener(this);
                
                
	}

	/**
	 * A custom login() method to handle all of the possible exceptions and set the bot instance.
	 */
	public static Bopp login(String token) {
		Bopp bot = null; // Initializing the bot variable

		ClientBuilder builder = new ClientBuilder(); // Creates a new client builder instance
		builder.withToken(token); // Sets the bot token for the client
		try {
			IDiscordClient client = builder.login(); // Builds the IDiscordClient instance and logs it in
			bot = new Bopp(client); // Creating the bot instance
		} catch (DiscordException e) { // Error occurred logging in
			System.err.println("Error occurred while logging in!");
			e.printStackTrace();
		}

		return bot;
	}
        
       public void handle(MessageReceivedEvent event) {
            IMessage message = event.getMessage(); // Gets the message from the event object NOTE: This is not the content of the message, but the object itself
            IChannel channel = message.getChannel(); // Gets the channel in which this message was sent.
       
            String s = channel.getName();
            
            String ID = channel.getID();
            
            if(A == null){
                A = new Gate(client.getChannelByID("281840355694084097"),1);
            }
            
            if(B == null){
                B = new Gate(client.getChannelByID("281935461688016896"),2);
            }
            
            if(C == null){
                C = new Gate(client.getChannelByID("281935872738328597"),3);
            }
            
            if(D == null){
                D = new Gate(client.getChannelByID("297616250862043137"),1);
            }
            //
            
            //1/20 of bopp checking all the dailies for AFKers.
            if(oRan.nextInt(100) < 5){
                Dailies.checkAllDailies();
            }
            
            UserData UD = UserData.getUser(message.getAuthor());
            String mess = message.getContent();
            mess = mess.toLowerCase();
            
            if(ID == A.channel.getID()){
                A.ping(UD);
                A.handle(UD,mess);
            } else if(ID == B.channel.getID()){
                B.ping(UD);
                B.handle(UD,mess);
            } else if(ID == C.channel.getID()){
                C.ping(UD);
                C.handle(UD,mess);
            } else if(ID == D.channel.getID()){
                D.ping(UD);
                D.handle(UD,mess);
            } 
            
            if(oRan.nextInt(100) == 23){
                UD.slimeCoins.data += 1;
            }
            
            if(mess.length() == 0) return;
            if(mess.charAt(0)!='!')return;
            
            if(mess.contains("!donate")){
                send(Missions.donate(UD),channel);
            }
            
            if(mess.contains("!unbox")){
                send(Unboxing.unbox(UD),channel);
            }
            
            if(mess.contains("!stuff")){
                send(""+UD,channel);
            }
            
            if(mess.contains("!mat")){
                send(UD.mat(),channel);
            }
            if(mess.contains("!min")){
                send(UD.min(),channel);
            }
            if(mess.contains("!orb")){
                send(UD.orb(),channel);
            }
            
            if(mess.contains("!mygear")){
                send(UD.Gear(),channel);
            }
            
            if(mess.contains("!short")){
                send(UD.small(),channel);
            }
            
            if(mess.contains("!gear")){
                send(Gear.bases(),channel);
            }
            
            if(mess.contains("!info")){
                try{
                    String[] splt = mess.split(" ",2);
                    splt[1] = WordUtils.capitalizeFully(splt[1]);
                    Gear G = Gear.gearMap.get(splt[1]);
                    send(""+G,channel);
                } catch (Exception E){}
            }
            
            if(mess.contains("!help")){
                try{
                String[] splt = mess.split(" ",2);
                String help = splt[1];
                send(HelpDesk.parse(help),channel);
                } catch (Exception E){
                    send(HelpDesk.parse(""),channel);
                }
            }
            
            if(mess.contains("!up")){
                try{
                    String[] splt = mess.split(" ",2);
                    String gearname = splt[1];
                    send(Gear.upgrades(gearname),channel);
                } catch (Exception E){}
            }
            
            if(mess.contains("!req")||mess.contains("!down")){
                try{
                    String[] splt = mess.split(" ",2);
                    String gearname = splt[1];
                    gearname = WordUtils.capitalizeFully(gearname);
                    
                    Gear get = Gear.gearMap.get(gearname);
                    
                    String prereq = get.prereq;
                    try{
                        Gear disp = Gear.gearMap.get(prereq);
                        if(disp == null)throw new NullPointerException();
                        send(""+disp,channel);
                    }catch (Exception e){
                        send(""+prereq,channel);
                    }
                    
                    
                    
                } catch (Exception E){}
            }
            
            if(mess.contains("!craft")){
                try{
                    String[] splt = mess.split(" ",2);
                    String tocraft = splt[1];
                    tocraft = WordUtils.capitalizeFully(tocraft);
                    
                    if(Gear.gearList.contains(tocraft)){
                        Gear g = Gear.gearMap.get(tocraft);
                        if(g.prereq.equals("Jelly Gems") ||
                                g.prereq.equals("Frumious Fangs")||
                                g.prereq.equals("Bark Modules")||
                                g.prereq.equals("Almirian Seals")){
                            if(UD.gear.data.contains(g.prereq)){
                                UD.gear.data.remove(g.prereq);
                                UD.gear.data.add(g.name);
                                send("Success!",channel);
                            } else {
                                send("You need boss tokens to craft that!",channel);
                            }
                        } else if((UD.gear.data.contains(g.prereq) || g.prereq.equals(""))&&
                                UD.materials.data[g.mat1] >= Gear.getMatCosts(g.stars) &&
                                UD.materials.data[g.mat2] >= Gear.getMatCosts(g.stars) &&
                                UD.orbs.data[g.stars - 2] >= 3){
                            
                            UD.gear.data.remove(g.prereq);
                            
                            if(UD.weaponA.data.equals(g.prereq)){
                                UD.weaponA.data = g.name;
                            }
                            if(UD.weaponB.data.equals(g.prereq)){
                                UD.weaponB.data = g.name;
                            }
                            if(UD.weaponC.data.equals(g.prereq)){
                                UD.weaponC.data = g.name;
                            }
                            
                            UD.materials.data[g.mat1] -= Gear.getMatCosts(g.stars);
                            UD.materials.data[g.mat2] -= Gear.getMatCosts(g.stars);
                            UD.orbs.data[g.stars - 2] -= 3;
                            
                            UD.gear.data.add(tocraft);
                            send("Success! "+tocraft+" has been crafted!",channel);
                        } else {
                            send("You lack the resources to craft that...",channel);
                        }
                    }
                } catch (Exception E){
                    send("Useage: !craft <gear>",channel);
                }
            }
            
            if(mess.contains("!equip")){
                try{
                    String[] splt = mess.split(" ",3);
                    String slot = splt[1];
                    String wep = splt[2];
                    wep = WordUtils.capitalizeFully(wep);

                    
                    if(UD.gear.data.contains(wep)){
                        if(UD.weaponB.data.equals(wep) ||
                                UD.weaponC.data.equals(wep)||
                                UD.weaponA.data.equals(wep)){
                            send("You already have one of those equipped!",channel);
                        } else {
                            if(slot.toLowerCase().equals("a")){

                                UD.weaponA.data = wep;
                                send(wep+" equipped to slot A",channel);
                            }
                            if(slot.toLowerCase().equals("b")){
                                UD.weaponB.data = wep;
                                send(wep+" equipped to slot B",channel);
                            }
                            if(slot.toLowerCase().equals("c")){
                                UD.weaponC.data = wep;
                                send(wep+" equipped to slot C",channel);
                            }
                        }
                    } else {
                        send("You don't have that!",channel);
                    }
                } catch (Exception E){
                    send("Useage: !equip <A/B/C> <Weapon>",channel);
                }
            }
            
            if(mess.contains("!tier2")){
                try{
                    Gear A = Gear.gearMap.get(UD.weaponA.data);
                    Gear B = Gear.gearMap.get(UD.weaponB.data);
                    Gear C = Gear.gearMap.get(UD.weaponC.data);

                    if(A.stars >= 2 && B.stars >= 2 && C.stars >= 2){
                        send("Congrats! You've reached T2!",channel);
                        IUser adv = message.getAuthor();

                        IRole T2 = client.getRoleByID("290581826689433600");

                        try{
                        adv.addRole(T2);
                        } catch (Exception E){System.out.println(T2);};
                    } else {
                        send("You're not quite ready...",channel);
                    }
                } catch (Exception E){send("You're not quite ready...",channel);};
            }
            
            if(mess.contains("!tier3")){
                try{
                    Gear A = Gear.gearMap.get(UD.weaponA.data);
                    Gear B = Gear.gearMap.get(UD.weaponB.data);
                    Gear C = Gear.gearMap.get(UD.weaponC.data);

                    if(A.stars >= 4 && B.stars >= 4 && C.stars >= 4){
                        send("Congrats! You've reached T3!",channel);
                        IUser adv = message.getAuthor();

                        IRole T2 = client.getRoleByID("290581982604296192");

                        try{
                        adv.addRole(T2);
                        } catch (Exception E){System.out.println(T2);};
                    } else {
                        send("You're not quite ready...",channel);
                    }
                } catch (Exception E){send("You're not quite ready...",channel);};
            }
            
            if(mess.contains("!shop")){
                String ret = "Shop:\n"
                        + "simple orbs: 3/50K\n"
                        + "advanced orbs: 3/100K\n"
                        + "elite orbs: 3/500K\n"
                        + "eternal orbs: 3/1M\n"
                        + "red/green/blue/yellow/purple = 2000/100K\n"
                        + "OCH: Operation crimson hammer = 3M\n"
                        + "Scissor Blades = 1.3M";
                if(UD.gear.data.contains("Obsidian Edge") || UD.gear.data.contains("Obsidian Carbine") || UD.gear.data.contains("Obsidian Crusher")){
                    ret+="\nSouls = 100/1M";
                }
                
                send(ret,channel);
            }
            
            if(mess.contains("!prestige")){
                send(Missions.getData(),channel);
            }
            
            if(mess.contains("!missiona")){
                send(Missions.missionA(UD),channel);
            }
            
            if(mess.contains("!missionb")){
                send(Missions.missionB(UD),channel);
            }
            
            if(mess.contains("!top10")){
                String[] top10 = new String[10];
                long[] top10scores = new long[10];
                
                for(int i = 0; i < 10; ++i){
                    top10scores[i] = -1;
                }
                
                for(String S : UserData.IDLIST.data){
                    UserData check = UserData.getUser(S);
                    boolean still = true;
                    for(int i = 0; i < 10 && still; ++i){
                        if(top10scores[i] < check.prestige.data){
                            
                            for(int j = 9; j > i; --j){
                                top10scores[j] = top10scores[j-1];
                                top10[j] = top10[j-1];
                            }
                            
                            
                            top10scores[i] = check.prestige.data;
                            top10[i] = check.name.data;
                            still = false;
                        }
                    }
                }
                
                String ret = "";

                for(int i = 0; i < 10; ++i){
                    ret+=i+1+". "+top10[i]+": "+top10scores[i]+" prestige\n";
                }

                send(ret,channel);
            }
            
            if(mess.contains("!buy")){
                try{
                    String[] splt = mess.split(" ",2);
                    String obj = splt[1];
                    obj = obj.toLowerCase();
                    
                    if(obj.equals("simple orbs") || obj.equals("simple")){
                        if(UD.crowns.data > 50000){
                            UD.crowns.data -= 50000;
                            UD.orbs.data[0]+=3;
                            send("Success!",channel);
                        } else {
                            send("You can't afford that!",channel);
                        }
                    }
                    if(obj.equals("advanced orbs") || obj.equals("advanced")){
                        if(UD.crowns.data > 100000){
                            UD.crowns.data -= 100000;
                            UD.orbs.data[1]+=3;
                            send("Success!",channel);
                        } else {
                            send("You can't afford that!",channel);
                        }
                    }
                    if(obj.equals("elite orbs") || obj.equals("elite")){
                        if(UD.crowns.data > 500000){
                            UD.crowns.data -= 500000;
                            UD.orbs.data[2]+=3;
                            send("Success!",channel);
                        } else {
                            send("You can't afford that!",channel);
                        }
                    }
                    if(obj.equals("eternal orbs") || obj.equals("eternal")){
                        if(UD.crowns.data > 1000000){
                            UD.crowns.data -= 1000000;
                            UD.orbs.data[3]+=3;
                            send("Success!",channel);
                        } else {
                            send("You can't afford that!",channel);
                        }
                    }
                    if(obj.equals("red")){
                        if(UD.crowns.data > 100000){
                            UD.crowns.data -= 100000;
                            UD.minerals.data[0] += 2000;
                            send("Success!",channel);
                        } else {
                            send("You can't afford that!",channel);
                        }
                    } 
                    if(obj.equals("green")){
                        if(UD.crowns.data > 100000){
                            UD.crowns.data -= 100000;
                            UD.minerals.data[1] += 2000;
                            send("Success!",channel);
                        } else {
                            send("You can't afford that!",channel);
                        }
                    }
                    if(obj.equals("blue")){
                        if(UD.crowns.data > 100000){
                            UD.crowns.data -= 100000;
                            UD.minerals.data[2] += 2000;
                            send("Success!",channel);
                        } else {
                            send("You can't afford that!",channel);
                        }
                    }
                    if(obj.equals("yellow")){
                        if(UD.crowns.data > 100000){
                            UD.crowns.data -= 100000;
                            UD.minerals.data[3] += 2000;
                            send("Success!",channel);
                        } else {
                            send("You can't afford that!",channel);
                        }
                    }
                    if(obj.equals("purple")){
                        if(UD.crowns.data > 100000){
                            UD.crowns.data -= 100000;
                            UD.minerals.data[4] += 2000;
                            send("Success!",channel);
                        } else {
                            send("You can't afford that!",channel);
                        }
                    }
                    if(obj.equals("och")){
                        if(UD.crowns.data > 3000000){
                            UD.crowns.data -= 3000000;
                            UD.gear.data.add("Warmaster Rocket Hammer");
                            UD.gear.data.add("Dark Retribution");
                            send("Success!",channel);
                        } else {
                            send("You can't afford that!",channel);
                        }
                    }
                    if(obj.equals("scissor blades")){
                        if(UD.crowns.data > 1300000){
                            UD.crowns.data -= 1300000;
                            UD.gear.data.add("Scissor Blades");
                            send("Success!",channel);
                        } else {
                            send("You can't afford that!",channel);
                        }
                    }
                    if(UD.gear.data.contains("Obsidian Edge") || UD.gear.data.contains("Obsidian Carbine") || UD.gear.data.contains("Obsidian Crusher")){
                        if(obj.equals("souls")){
                            if(UD.crowns.data > 1000000){
                                UD.crowns.data -= 1000000;
                                Obsidian.souls.data+=100;
                                Obsidian.souls.write();
                                send("Success!",channel);
                            } else {
                                send("You can't afford that!",channel);
                            }
                        }
                    }
                    
                    
                } catch (Exception E){
                    send("Useage: !buy <toBuy>",channel);
                }
            }
            
            if(mess.contains("!remove") && message.getAuthor().getID().equals("144857966816788482")){
                try{
                    String[] splt = message.getContent().split(" ",3);
                    String IDGET = splt[1];
                    String GEARGET = splt[2];
                    
                    if(IDGET.equals("ALL")){
                        for(String id : UserData.IDLIST.data){
                            UserData UDGET = UserData.getUser(id);
                            UDGET.gear.data.remove(GEARGET);
                        }
                    } else {
                        UserData UDGET = UserData.getUser(IDGET);
                        UDGET.gear.data.remove(GEARGET);
                    }
                } catch (Exception E){
                    
                }
            }
            
            if(mess.contains("!grant") && message.getAuthor().getID().equals("144857966816788482")){
                try{
                    String[] splt = message.getContent().split(" ",3);
                    String IDGET = splt[1];
                    String GEARGET = splt[2];
                    
                    if(IDGET.equals("ALL")){
                        for(String id : UserData.IDLIST.data){
                            UserData UDGET = UserData.getUser(id);
                            UDGET.gear.data.add(GEARGET);
                        }
                    } else {
                        UserData UDGET = UserData.getUser(IDGET);
                        UDGET.gear.data.add(GEARGET);
                    }
                } catch (Exception E){
                    
                }
            }
            
            if(mess.contains("!rename") && message.getAuthor().getID().equals("144857966816788482")){
                try{
                    String[] splt = message.getContent().split(" ",3);
                    String IDGET = splt[1];
                    String NEWNAME = splt[2];
                    
                    
                    UserData UDGET = UserData.getUser(IDGET);
                    UDGET.name.data = NEWNAME;
                    
                } catch (Exception E){
                    
                }
            }
            
            if(mess.contains("!kick") && message.getAuthor().getID().equals("144857966816788482")){
                try{
                    String[] splt = message.getContent().split(" ",2);
                    String IDGET = splt[1];
                    
                    
                    UserData UDGET = UserData.getUser(IDGET);
                    this.removeUser(UDGET,0);
                    
                } catch (Exception E){
                    
                }
            }
            
            if(mess.contains("!giveprestige") && message.getAuthor().getID().equals("144857966816788482")){
                try{
                    String[] splt = message.getContent().split(" ",3);
                    String IDGET = splt[1];
                    int amt = Integer.parseInt(splt[2]);
                    
                    UserData UDGET = UserData.getUser(IDGET);
                    UDGET.prestige.data += amt;
                    
                    System.out.println(UDGET.name.data +" "+ amt);
                    
                } catch (Exception E){
                    System.out.println("FAILURE!");
                }
            }
            
            if(mess.contains("!inspect") && message.getAuthor().getID().equals("144857966816788482")){
                try{
                    String[] splt = message.getContent().split(" ",2);
                    String IDGET = splt[1];
                    
                    UserData UDGET = UserData.getUser(IDGET);
                    send(""+UDGET,channel);
                } catch (Exception E){
                    
                }
            }
            
            if(mess.contains("!kill")){
                if(UD.gear.data.contains("Wrench Wand")){
                    if(WrenchWand.slimes.data > 0){
                        send("The poor creature writhes in pain! WHAT HAVE YOU DONE?!?!?! +3 slime coins!",channel);
                        UD.slimeCoins.data+=3;
                        WrenchWand.slimes.data -= 1;
                    } else {
                        send("...But nobody came.",channel);
                    }
                } else {
                    send("Only the chosen few can handle such a burden as to kill such a magnificent creature.",channel);
                }
            }
            
            if(mess.contains("!paperwork")&&UD.gear.data.contains("Mug Of Misery")){
                Misery.mode.data.put(UD.ID,3);
                Misery.mode.write();
                send("The devilites look bored out of their minds... (do wait.)",channel);
            }
            if(mess.contains("!sacrifice")&&UD.gear.data.contains("Mug Of Misery")){
                Misery.mode.data.put(UD.ID,2);
                Misery.mode.write();
                send("The gorgos' throats are being slit! Oh god... (do wait.)",channel);
            }
            if(mess.contains("!lazy")&&UD.gear.data.contains("Mug Of Misery")){
                Misery.mode.data.put(UD.ID,1);
                Misery.mode.write();
                send("Time sheets are being checked and laziness is being measured... (do wait.)",channel);
            }
            if(mess.contains("!summon")&&UD.gear.data.contains("Mug Of Misery")){
                Misery.mode.data.put(UD.ID,0);
                Misery.mode.write();
                send("The devilites begin their ritual... (do wait.)",channel);
            }
            //Dawg
            if(mess.contains("!sit")&&UD.gear.data.contains("Monster Bone")){
                MonsterBone.mode.data.put(UD.ID,0);
                MonsterBone.count.data.put(UD.ID,2);
                send("You tell the wolver to sit.",channel);
            }
            if(mess.contains("!fetch")&&UD.gear.data.contains("Monster Bone")){
                MonsterBone.mode.data.put(UD.ID,1);
                MonsterBone.count.data.put(UD.ID,2);
                send("You throw the bone and tell the wolver to fetch.",channel);
            }
            if(mess.contains("!shake")&&UD.gear.data.contains("Monster Bone")){
                MonsterBone.mode.data.put(UD.ID,2);
                MonsterBone.count.data.put(UD.ID,2);
                send("You tell the wolver to shake.",channel);
            }
            if(mess.contains("!punish")&&UD.gear.data.contains("Monster Bone")){
                if(MonsterBone.valid && !MonsterBone.obey.data.get(UD.ID)){
                    MonsterBone.wolvers.data.put(UD.ID,MonsterBone.wolvers.data.get(UD.ID)+5);
                    MonsterBone.valid = false;
                } else {
                    MonsterBone.wolvers.data.put(UD.ID,MonsterBone.wolvers.data.get(UD.ID)-1);
                    
                }
                send("You smack the wolver upside the head violently!",channel);
            }
            if(mess.contains("!reward")&&UD.gear.data.contains("Monster Bone")){
                if(UD.materials.data[0] < 10){
                    send("You don't have enough beast mats to feed it!",channel);
                    return;
                }
                UD.materials.data[0] -= 10;
                
                if(MonsterBone.valid && MonsterBone.obey.data.get(UD.ID)){
                    MonsterBone.wolvers.data.put(UD.ID,MonsterBone.wolvers.data.get(UD.ID)+5);
                    MonsterBone.valid = false;
                } else {
                    MonsterBone.wolvers.data.put(UD.ID,MonsterBone.wolvers.data.get(UD.ID)-1);
                }
                send("You feed the wolver some beast mats! Those came from other beasts! That's nasty! The wolver lovves it!",channel);
            }
            
            if(UD.gear.data.contains("Obsidian Edge") || UD.gear.data.contains("Obsidian Carbine") || UD.gear.data.contains("Obsidian Crusher")){
                if(mess.contains("!machinate")){
                    try{
                        String[] splt = message.getContent().split(" ",2);
                        String wep = splt[1];
                        wep = WordUtils.capitalizeFully(wep);
                        
                        if(!Obsidian.machines.data.containsKey(UD.ID)){
                            Obsidian.machines.data.put(UD.ID, new ArrayList<>());
                        }
                        
                        if(UD.gear.data.contains(wep)){
                            UD.gear.data.remove(wep);
                            (Obsidian.machines.data.get(UD.ID)).add(wep);
                            Obsidian.machines.write();
                            send("You sacrifice the weapon to your own dark desires!",channel);
                        } else {
                            send("You don't have the weapon, but you want to sacrifice it to your own dark desires! Or you typo'd!",channel);
                        }
                    } catch (Exception E){

                    }
                    
                }
            }
            
            if(UD.gear.data.contains("Obsidian Edge") || UD.gear.data.contains("Obsidian Carbine") || UD.gear.data.contains("Obsidian Crusher")){
                if(mess.contains("!machines")){
                    send(Obsidian.machines.data.get(UD.ID)+"",channel);
                }
            }
            
            if(UD.gear.data.contains("Obsidian Edge") || UD.gear.data.contains("Obsidian Carbine") || UD.gear.data.contains("Obsidian Crusher")){
                if(mess.contains("!souls")){
                    send(Obsidian.souls.data+" souls... go to the shop to buy more!",channel);
                }
            }
            
            
            if(mess.contains("!dank") && (UD.gear.data.contains("Dank Matter Bomb") || channel.getName().equals("the_meme_bazaar")) ){
                
                ArrayList<String> memes = Webhandler.getDankMemes();
                if(memes == null || memes.size() == 0){
                    send("Unable to find dank memes!",channel);
                } else {
                    if(channel.getName().equals("the_meme_bazaar") || System.currentTimeMillis() - memetime > 1000 * 60 * 5){
                        String meme = memes.get(oRan.nextInt(memes.size()));
                        send(meme,channel);
                        memetime = System.currentTimeMillis();
                    } else {
                        send("!dank has a cool down of 5 minutes.",channel);
                    }
                }
            }
            
            if(mess.contains("!dream")&&UD.gear.data.contains("Wolver Slippers")){
                if(!Slippers.wake.data.containsKey(UD.ID)){
                    return;
                } else {
                    if(Slippers.sleep.data.get(UD.ID)){
                        
                        Slippers.wake.data.put(UD.ID,0);
                        Slippers.sleep.data.put(UD.ID,false);
                        
                        send("You dream of a far away world where geometry and madness share meaning...."
                                + "Suddenly you awake. You're naked. I hand you a cheeseburger."
                                + "The world has shifted.",channel);

                        UD.materials.data[10]+=23;
                        
                        UD.saveAll();
                        
                    } else {
                        Slippers.wake.data.put(UD.ID,0);
                        send("You try hard to dream... but somehow only feel more awake. You can't dream when you're awake.",channel);
                    }
                }
            }
            
            
       }
       
       public static void send(String message, IChannel channel){
            try {
			// Builds (sends) and new message in the channel that the original message was sent with the content of the original message.
			new MessageBuilder(INSTANCE.client).withChannel(channel).withContent(message).build();
		} catch (RateLimitException e) { // RateLimitException thrown. The bot is sending messages too quickly!
			System.err.print("Sending messages too quickly!");
			e.printStackTrace();
		} catch (DiscordException e) { // DiscordException thrown. Many possibilities. Use getErrorMessage() to see what went wrong.
			System.err.print(e.getErrorMessage()); // Print the error message sent by Discord
			e.printStackTrace();
		} catch (MissingPermissionsException e) { // MissingPermissionsException thrown. The bot doesn't have permission to send the message!
			System.err.print("Missing permissions for channel!");
			e.printStackTrace();
		}
        }
       
       public static void removeUser(UserData UD, int not){
           
           if(not != 1)A.removeUser(UD);
           if(not != 2)B.removeUser(UD);
           if(not != 3)C.removeUser(UD);
       }
}
