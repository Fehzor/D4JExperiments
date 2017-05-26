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
package BoppOld;

import BoppOld.Data.UserData;
import BoppOld.Data.UserSpace;
import BoppOld.Game.Area;
import BoppOld.Items.Gear.Gear;
import BoppOld.Items.Gear.GearMap;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.commons.lang3.text.WordUtils;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
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
	public IDiscordClient client; // The instance of the discord client.
        
        Area A1 = new Area("gate_a","281840355694084097");
        Area A2 = new Area("gate_b","281935461688016896");
        Area A3 = new Area("gate_c","281935872738328597");
              
        
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
            
            if(ID.equals(A1.channelID)){
                A1.parse(message);
            }else if(ID.equals(A2.channelID)){
                A2.parse(message);
            }else if(ID.equals(A3.channelID)){
                A3.parse(message);
            }else {
                UserData UD = UserSpace.getPlayer(message.getAuthor());
                UD.slimeCoins+=1;
            }
            
            parse(message);
       }
       
       public static void parse(IMessage message){
           String mess = message.getContent();
           //IChannel channel = message.getChannel();
           //System.out.println(channel.getName()+": "+channel.getID());
           
           UserData UD = UserSpace.getPlayer(message.getAuthor());
           
           Scanner oScan = new Scanner(mess);
           String token = oScan.next();
           
            if(token.equals("!stuff")){
                String disp = UD.getDisp();
                send(disp,message.getChannel());
            }
            
            if(token.equals("!help")){
                try{
                String disp = Help.message(oScan);
                IChannel chan = message.getAuthor().getOrCreatePMChannel();
                send(disp,chan);
                } catch (Exception E){
                    System.out.println("Exception with !help- "+E);
                }
            }
            
            if(token.equals("!equip")){
                String ab = oScan.next();//Which slot
                try{
                    //Which weapon
                    token = oScan.next();
                    while(oScan.hasNext()){
                        token+=" "+oScan.next();
                    }
                    token = WordUtils.capitalizeFully(token);

                    if(UD.gear.contains(token)){
                    if(ab.equals("a")||ab.equals("A")||ab.equals("1")){
                        UD.weaponA = token;
                        send("Equipped!",message.getChannel());
                    } else if(ab.equals("b")||ab.equals("B")||ab.equals("2")){
                        UD.weaponB = token;
                        send("Equipped!",message.getChannel());
                    } else {
                        send("Usage: !equip [slot (a or b)] [weapon name]",message.getChannel());
                    }
                    } else {
                        send("You don't have that!",message.getChannel());
                    }
                } catch(Exception E){
                    send("I didn't quite catch that.",message.getChannel());
                }
            }
            
            if(token.equals("!craft")){
                try{
                    
                    token = oScan.next();
                    while(oScan.hasNext()){
                        token+=" "+oScan.next();
                    }
                    token = WordUtils.capitalizeFully(token);
                    
                    Gear G = null;
                    for(Gear get : GearMap.gearList){
                        if(get.name.equals(token)){
                            G = get;
                        }
                    }
                    
                    String ans = G.subtract(UD);
                    
                    send(ans,message.getChannel());
                } catch (Exception E){
                    send("I didn't quite catch that.",message.getChannel());
                }
            }
           
           if(mess.contains("dat boi")){
               send("https://i.ytimg.com/vi/nytzHVEHLLs/hqdefault.jpg",message.getChannel());
           }
           
           if(mess.contains("Bopp plz")){
               send("/shrug",message.getChannel());
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
}
