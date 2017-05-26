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
package MOM;


import MOM.CommandCenter.CommandParser;
import MOM.CommandCenter.TimerEnd;
import MOM.Data.Collective;
import MOM.Data.Field;
import MOM.Data.UserData;
import static MOM.Data.UserData.BUMP_TIME;
import MOM.Decompilable.Stage;
import MOM.Gates.Gate;
import MOM.Gates.Room;
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
public class MoM implements IListener<MessageReceivedEvent>{

	public static MoM INSTANCE; // Singleton instance of the bot.
	public static IDiscordClient client; // The instance of the discord client.
        
        public static final String outputID = "300727438642511873";
        
        public static Field<Gate> inProgress = new Field<>("MOM","INPROGRESS",new Gate());
        public static Field<Gate> published = new Field<>("MOM","PUBLISHED",null);
        
        private static boolean slimeCoin = false;
        
	public static void main(String[] args) { // Main method
		INSTANCE = login("Mjc1MzcwMDE2Mjk4MzY5MDI1.C3s07A.qQRhQAp7R61A-NYTvejx1JGWJhw"); // Creates the bot instance and logs it in.
        }

        public static final long KNIGHT_TIMER = 1000*60*40;
        public static long lastCheck = 0;

	public MoM(IDiscordClient client) {
		this.client = client; // Sets the client instance to the one provided
                
                EventDispatcher dispatcher = client.getDispatcher(); // Gets the client's event dispatcher
		dispatcher.registerListener(this);
                
                this.startCheckerThread();
	}

	/**
	 * A custom login() method to handle all of the possible exceptions and set the bot instance.
	 */
	public static MoM login(String token) {
		MoM bot = null; // Initializing the bot variable

		ClientBuilder builder = new ClientBuilder(); // Creates a new client builder instance
		builder.withToken(token); // Sets the bot token for the client
		try {
			IDiscordClient client = builder.login(); // Builds the IDiscordClient instance and logs it in
			bot = new MoM(client); // Creating the bot instance
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
            
            IUser get = message.getAuthor();
            
            
            UserData UD = UserData.getUD(get);
            UD.bump();
            
            if(slimeCoin){
                slimeCoin = false;
                UD.slimecoins.writeData(UD.slimecoins.getData() + 1);
            }
            
            checkTime();
            
            CommandParser.channel = channel;
            CommandParser.parseCommand(message.getContent(),UD);
            /*
            if(UD.checkTimer()){
                String task = UD.task.getData();
                if(task.equals("Nothing doing.")){
                    return;
                }
                
                UD.task.writeData("Nothing doing.");
                String[] split = task.split(" ",2);
                
                TimerEnd.act(split,UD);
            }
            */
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
       
        public static void PM(String message, UserData UD){
            IUser sendto = client.getUserByID(UD.ID);
            try{
                IChannel chan = sendto.getOrCreatePMChannel();
                send(message,chan);
            } catch (Exception E){}
        }
        
        public static void shout(String message){
            IChannel sendto = client.getChannelByID(outputID);
            try{
                send(message,sendto);
            } catch (Exception E){}
        }
        

        private static void checkTime(){
            long check = System.currentTimeMillis() - lastCheck;
            
            if(check > KNIGHT_TIMER){
                lastCheck = System.currentTimeMillis();
                try{
                    MoM.published.getData().runKnight();
                } catch (Exception E){System.out.println("Failure to runKnight() from Mom");};
            }
        }
        
        public static void slimeCoin() {
            slimeCoin = true;
        }
        
        private void startCheckerThread(){
        new Thread()
            {
                public void run() {
                    ArrayList<String> userIDs = UserData.IDList.getData();
                    
                    for (String s : userIDs){
                        try{
                            UserData UD = UserData.getUD(s);
                            if(UD !=null && UD.checkTimer()){
                                String task = UD.task.getData();
                                if(task.equals("Nothing doing.")){
                                    //return;
                                } else if(UD!=null){
                                    UD.task.writeData("Nothing doing.");
                                    String[] split = task.split(" ",2);

                                    TimerEnd.act(split,UD);
                                }
                            }
                        }catch (Exception E){}
                    }
                    try{
                        Thread.sleep(1000*45);
                    }catch (Exception E){}
                    
                    this.run();
                }
            }.start();
        }
}
