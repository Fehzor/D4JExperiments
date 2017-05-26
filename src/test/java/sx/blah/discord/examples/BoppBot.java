package sx.blah.discord.examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MILLISECOND;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.gametwo.Areas.Area;
import sx.blah.discord.gametwo.GearMap;
import static sx.blah.discord.gametwo.SuperRandom.oRan;
import sx.blah.discord.gametwo.UserData;
import sx.blah.discord.gametwo.UserSpace;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

/**
 * An example to demonstrate event listening and message sending.
 * It will repeat everything said in a channel.
 */
public class BoppBot extends BaseBot implements IListener<MessageReceivedEvent> {
        
        public static HashMap<String,String> names = loadNames();
    
        //0 = none
        //1 = Black Kats
        //2 = Tortodrones
        //3 = Apocrea
        //4 = Winterfest
        //5 = Slime casino
        public static final int EVENT = 2;
        
        
        public void checkDate(){
            if(UserSpace.data.reset == null){
                return;
            }
            Date date = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            
            //long day = 60000 * 60 * 24;
            int day = c.get(DAY_OF_MONTH);
            day = day - UserSpace.data.reset.get(DAY_OF_MONTH);
            
            //System.out.println("DAY: " + day);
            
            if(day > 1){
                UserSpace.data.areas = 1;
                
                ArrayList<String> list = new ArrayList<String>(UserSpace.data.users.keySet());
                for(String s : list){
                    UserData UD = UserSpace.data.users.get(s);
                    UD.check();
                    UD.farm("t1");
                }
                
                UserSpace.data.reset = null;
            }
        }
        
        public void checkUser(IUser user){
            String a = user.getID();
            String b = user.getName();
            names.put(a,b);
        }
    
	public BoppBot(IDiscordClient discordClient) {
		super(discordClient);
		EventDispatcher dispatcher = discordClient.getDispatcher(); // Gets the client's event dispatcher
		dispatcher.registerListener(this); // Registers this bot as an event listener
	}
        
        public void handle(MessageReceivedEvent event) {
		IMessage message = event.getMessage(); // Gets the message from the event object NOTE: This is not the content of the message, but the object itself
		IChannel channel = message.getChannel(); // Gets the channel in which this message was sent.

                IUser sent = message.getAuthor();
                String mess = message.getContent();
                mess = mess.toLowerCase();
                
                checkDate();
                
                checkUser(sent);
                
                //System.out.println(sent.getID());
                
                //UserSpace.data.giveCoin(sent);
                
                //checkDate();
                
                try{
                    channel = sent.getOrCreatePMChannel();
                    
                    if(mess.contains("public")){
                        channel = message.getChannel();
                    }
                    
                    if((mess.contains("bopp") && mess.contains("help")) || mess.contains("!help")||mess.contains("/help")||mess.contains("@help") ){
                        send("*Messages should contain one of the following in addition to the word \"Bopp\":*\n\n"
                                + "stuff = view your items\n"
                                + "gear = view your current gear; more gear = faster farming!\n\n"
                                + "areas = view all possible farming areas\n"
                                + "farm = farm in an area\n"
                                + "check = check your progress farming\n\n"
                                + "craft = craft- all one word at the end, gear_name_here\n"
                                + "buy = buy with boss tokens\n\n"
                                + "deposit = put in minerals towards unlocking the next area!\n"
                                + "progress = how much progress towards the next area has been made",channel);
                    }

                
                
                UserData User = UserSpace.getUser(sent);
                String a = User.giveSlimeCoin();
                if(a.equals("")){}else{
                    send(a,channel);
                }
                
                if(!mess.contains("bop")){
                    return;
                }
                
                System.out.println(sent.getName()+": "+mess);
                
                if(mess.contains("stuff")){
                    send("You have "+User.crowns+" crowns.\n"+
                            "You have "+User.flawed_orbs+" flawed, "
                            + ""+User.simple_orbs+" simple, "
                            + ""+User.adv_orbs+" advanced, "
                            + ""+User.elite_orbs+" elite and "
                            + ""+User.eternal_orbs+" eternal orbs.\n"+
                            "You have "+User.minerals+" minerals.\n"+
                            "You have "+User.frum_fangs+" frumious fangs, "
                            + ""+User.jelly_gems+" jelly gems, "
                            + ""+User.bark_mods+" bark modules, "
                            + ""+User.almire_coins+" Almirian seals, "
                            + ""+User.snowflakes+" core tokens",channel);
                }
                
                if(mess.contains("crowns")){
                    send("You have "+User.crowns+" crowns.",channel);
                }
                
                if(mess.contains("gear list") ){
                    send("http://wiki.spiralknights.com/Category:Weapons",channel);
                    return;
                }
                
                if(mess.contains("slime coins") ){
                    send("You have "+User.slime_coins+" slime coins.",channel);
                }
                
                if(mess.contains("orbs")){
                    send("You have "+User.flawed_orbs+" flawed, "
                            + ""+User.simple_orbs+" simple, "
                            + ""+User.adv_orbs+" advanced, "
                            + ""+User.elite_orbs+" elite and "
                            + ""+User.eternal_orbs+" eternal orbs.",channel);
                }
                
                if(mess.contains("minerals")){
                    send("You have "+User.minerals+" minerals.",channel);
                }
                
                if(mess.contains("tokens")){
                    send("You have "+User.frum_fangs+" frumious fangs, "
                            + ""+User.jelly_gems+" jelly gems, "
                            + ""+User.bark_mods+" bark modules, "
                            + ""+User.almire_coins+" Almirian seals, "
                            + ""+User.snowflakes+" core tokens",channel);
                }
                
                if(mess.contains("leaderboard")){
                    ArrayList<String> list = new ArrayList<String>(UserSpace.data.users.keySet());
                    
                    String[] top5 = new String[5];
                    double[] top5int = new double[5];
                    
                    for(int i = 0; i<5; ++i){
                        top5[i] = "";
                        top5int[i] = -1;
                    }
                    
                    for(String s : list){
                        if(names.get(s) != null){
                            UserData UD = UserSpace.data.users.get(s);

                            double sort = UD.getPower();
                            
                            boolean looking = true;
                            for(int i = 0; i < 5; ++i){
                                if(looking && top5int[i] < sort){
                                    for(int j = 4; j > i; --j){
                                        top5int[j] = top5int[j-1];
                                        top5[j] = top5[j-1];
                                    }
                                    top5int[i] = sort;
                                    top5[i] = s;
                                    looking = false;
                                }
                                
                            }
                        }
                    }
                    
                    String toSend = "Leaderboard:\n";
                    for(int i = 0; i < 5; ++i){
                        String name = names.get(top5[i]);
                        
                        toSend+= name+": "+top5int[i]+"\n";
                    }
                    send(toSend,channel);
                    
                    
                }
                
                if(mess.contains("score")){
                    String toSend = "Your score is: "+User.getPower()+".";
                    send(toSend,channel);
                }
                
                if(mess.contains("areas")){
                    String toSend = "The following farming areas exist:\n";
                    for(int i = 0; i < UserSpace.data.areas+1; ++i){
                        String s = Area.areaNames.get(i);
                        String nam = Area.areas.get(s).name;
                        toSend += nam+": \""+s+"\"\n";
                    }
                    send(toSend,channel);
                }
                
                if(mess.contains("gear")){
                    String toSend = "You have crafted the following gear:\n";
                    for(String s : User.gear){
                        String nam = s;
                        toSend += nam+"\n";
                    }
                    send(toSend,channel);
                }
                
                if(mess.contains("run") || mess.contains("farm")){
                    User.check();
                    boolean farmed = false;
                    for(int i = 0; i < UserSpace.data.areas+1; ++i){
                        String s = Area.areaNames.get(i);
                        if(mess.contains(s)){
                            String disp = User.farm(s);
                            send(disp,channel);
                            
                            farmed = true;
                        }
                    }
                    if(!farmed){
                        send("Area not found, try again and check for typos.",channel);
                    }
                }
                
                if(mess.contains("check")){
                    String get = User.check();
                    send(get,channel);
                }
                /*
                if(mess.contains("cancel")){
                    String get = User.cancel();
                    send(get,channel);
                }
                */
                if(mess.contains("craft")){
                    //System.out.println("A");
                    Scanner oScan = new Scanner(mess);
                    //System.out.println("B");
                    while(oScan.hasNext()){
                        mess = oScan.next();
                        //System.out.println("C");
                    }
                    //System.out.println("D");
                    boolean fail = true; 
                    
                    for(String s : GearMap.gear){
                        //System.out.println("E");
                        if(mess.equals(s)){
                            String disp = GearMap.craft(User, s);
                            send(disp,channel);
                            fail = false;
                        }
                    }
                    if(fail){
                        send("I'm not sure what you're trying to craft",channel);
                    }
                }
                
                if(mess.contains("buy")){
                    Scanner oScan = new Scanner(mess);
                    while(oScan.hasNext()){
                        mess = oScan.next();
                    }
                    

                    String disp = GearMap.buy(User, mess);
                    send(disp,channel);
                    
                        
                }
                
                int needed = getNeeded();
                
                if(mess.contains("deposit")){
                    if(UserSpace.data.areas == Area.areaNumber){
                        send("You've maxed out on areas! Farm the core!", channel);
                        return;
                    }
                    
                    int amt = User.minerals;
                    User.minerals = 0;
                    UserSpace.data.minerals+=amt;
                    
                    if(UserSpace.data.minerals > needed){
                        UserSpace.data.areas++;
                        UserSpace.data.minerals = UserSpace.data.minerals - needed;
                        needed = getNeeded();
                        if(UserSpace.data.areas == Area.areaNumber){
                            send("THE CORE HAS OPENED! YOU HAVE ONE DAY TO FARM!",channel);
                            UserSpace.data.reset = Calendar.getInstance();
                            UserSpace.data.reset.setTime(new Date());
                        } else {
                            send("A new area has been created!",channel);
                        }
                    }
                    
                    amt = UserSpace.data.minerals;
                    
                    send("You've deposited "+amt+" out of "+ needed+" minerals.", channel);
                }
                
                if(mess.contains("progress")){
                    if(UserSpace.data.areas == Area.areaNumber){
                        send("You've maxed out on areas!", channel);
                        return;
                    }
                    int amt = UserSpace.data.minerals;
                    
                    send("You've deposited "+amt+" out of "+ needed+" minerals.", channel);
                }
                

               
                
                if(mess.contains("grant")){
                    Scanner oScan = new Scanner(message.getContent());
                    System.out.println(message.getContent());
                    oScan.next();
                    oScan.next();
                    String name = oScan.next();
                    String item = oScan.next();
                    oScan = new Scanner(System.in);
                    String check = oScan.next();
                    if(check.equals("yes")){
                        UserSpace.data.users.get(name).gear.add(item);
                    }
                    
                    
                }
                
                if(mess.contains("remove")){
                    Scanner oScan = new Scanner(message.getContent());
                    System.out.println(message.getContent());
                    oScan.next();
                    oScan.next();
                    String name = oScan.next();
                    String item = oScan.next();
                    oScan = new Scanner(System.in);
                    String check = oScan.next();
                    if(check.equals("yes")){
                        UserSpace.data.users.get(name).gear.remove(item);
                    }
                    
                    
                }
                
                
                } catch (Exception E){System.out.println("Something went wrong PMing!");}
                
                //THESE AREN'T PM"D
                channel = message.getChannel();
                
                if(mess.contains("plz")){
                    send("¯\\_(ツ)_/¯",channel);
                }
                
                if(mess.contains("dat") && mess.contains("boi")){
                    send("https://i.ytimg.com/vi/nytzHVEHLLs/hqdefault.jpg",channel);
                }
                UserSpace.saveProgress();
                this.saveNames();
	}
        
        public int getNeeded(){
            switch(UserSpace.data.areas){
                case 1: return 3000;
                case 2: return 8000;
                case 3: return 20000;
                case 4: return 50000;
                case 5: return 100000;
                case 6: return 150000;
                case 7: return 200000;
                case 8: return 400000;
                case 9: return 1000000;
                case 10: return 2500000;
            }
            
            return 19*(UserSpace.data.areas)*(UserSpace.data.areas+1)*(UserSpace.data.areas+2);
        }
        /*
	public void oldhandle(MessageReceivedEvent event) {
		IMessage message = event.getMessage(); // Gets the message from the event object NOTE: This is not the content of the message, but the object itself
		IChannel channel = message.getChannel(); // Gets the channel in which this message was sent.

                IUser sent = message.getAuthor();
                String mess = message.getContent();
                mess = mess.toLowerCase();
                
                UserSpace.data.giveCoin(sent);
                
                if((mess.contains("bopp") && mess.contains("help")) || mess.contains("!help")||mess.contains("/help")||mess.contains("@help") ){
                    send("Messages should contain one of the following in addition to the word \"Bopp\":\n"
                            + "Coins = check your coins\n"
                            + "Check = check your current box status\n"
                            + "Unbox = purchase a box for 25 coins and unbox it\n"
                            + "Stuff = show what you've unboxed\n",channel);
                }
                
                if(!mess.contains("bop")){
                    return;
                }
                //System.out.println("mess");
                
                if(mess.contains("coins") ){
                    send("You have "+UserSpace.data.getCoin(sent)+" golden slime coins.",channel);
                }
                
                
                
                if(mess.contains("check") ){
                    Crate C = UserSpace.data.checkBox(sent);
                    if(C == null){
                        send("You have no box...",channel);
                    } else {
                        long time = C.check();
                        if(time > 0){
                            send("Your box is being opened. It requires "+(1 + C.check() / (long)60000)+" more minutes to open.",channel);
                        } else {
                            send("You unboxed "+C.loot+"!",channel);
                            UserSpace.data.grant(sent);
                        }
                    }
                }
                
                if(mess.contains("unbox")||mess.contains("purchase")||mess.contains("buy")){
                    int coins = UserSpace.data.getCoin(sent);
                    if(coins < 25){
                        send("Not enough coins! Have: " + coins+"; Need: 25",channel);
                    } else {
                        Crate C = UserSpace.data.checkBox(sent);
                        if(C == null){
                            UserSpace.data.giveBox(sent);
                            send("Opening your box... I'm really bad though so it takes five entire minutes...",channel);
                        } else {
                            send("Your box is already being opened. It requires "+(1 + C.check() / (long)60000)+" more minutes to open.",channel);
                        }
                    }
                }
                
                if(mess.contains("goods")||mess.contains("stuff")){
                    send("You have the following list of things: "+UserSpace.data.checkStuff(sent),channel);
                }
                
                if(mess.contains("dat") && mess.contains("boi")){
                    send("https://i.ytimg.com/vi/nytzHVEHLLs/hqdefault.jpg",channel);
                }
                
                UserSpace.saveProgress();
	}
        */
        public void send(String message, IChannel channel){
            try {
			// Builds (sends) and new message in the channel that the original message was sent with the content of the original message.
			new MessageBuilder(this.client).withChannel(channel).withContent(message).build();
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
        
    public static void saveNames(){
        try{
            File F = new File("names");
            FileOutputStream FOS = new FileOutputStream(F);
            ObjectOutputStream OOS = new ObjectOutputStream(FOS);
            
            OOS.writeObject(names);
            
            
        } catch (Exception E){
            System.out.println("Err saving progress");
        }    
    }
    
    public static HashMap<String,String> loadNames(){
        try{
            File F = new File("names");
            FileInputStream FIS = new FileInputStream(F);
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            
            Object o = (OIS.readObject());
            HashMap<String,String> get = (HashMap<String,String>) o;
            
            System.out.println("Loading success! (names)");
            
            return get;
            
        } catch (Exception E){
            System.out.println("progress not found... starting anew (names)");
            HashMap<String,String> get = new HashMap<>();
            return get;
        }
    }
        
        
}
