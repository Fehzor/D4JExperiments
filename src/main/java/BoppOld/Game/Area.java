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
package BoppOld.Game;

import BoppOld.Bopp;
import BoppOld.Data.LoadSaver;
import BoppOld.Data.UserData;
import BoppOld.Data.UserSpace;
import BoppOld.Items.Material;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

/**
 *
 * @author FF6EB4
 */
public class Area {
    public static final long TIME_OUT_TIME = 60000 * 15;
    public static Random oRan = new Random();
    
    public String name = "";
    public IChannel channel;
    public String channelID;
    public int[] minerals;//Red,green,blue,yellow,purple
    
    public ArrayList<Material> mats = new ArrayList<>();
    
    public ArrayList<UserData> users = new ArrayList<>();
    public HashMap<String,Long> time = new HashMap<>();
    
    int completion = 1000;
    
    public Area(String name, String channelID){
        this.name = name;
        this.channelID = channelID;
        
        this.minerals = loadMinerals();
        
        for(int i = 0; i < 5; ++i){
            if(minerals[i] < 10){
                minerals[i] = 100;
            }
        }
        
        GameThread GT = new GameThread(this);
        Thread T = new Thread(GT);
        T.start();
    }
    
    public void play(){
        timeOut();
        if(users.size() == 0){
            return;
        }
        System.out.println("TICK");
        
        if(completion >= 7){
            completion = 0;
            
            int[] mins = selectMinerals();

            minerals[mins[0]] -= mins[0]  / 11;
            minerals[mins[1]] -= mins[1]  / 11;

            if(minerals[mins[0]] < 10){
                minerals[mins[0]] = 10;
            }
            if(minerals[mins[1]] < 10){
                minerals[mins[1]] = 10;
            }
            
            this.saveMinerals();

            grant(mins);
            grant(mins);
            grant(mins);
            
            send("You completed the level! You found-\n "+mats);
            mats = new ArrayList<>();
            
            try{
            channel.changeName(this.name);
            }catch (Exception E){}
        } else {
            if(oRan.nextInt(100)<40){
                send(Scenario.run(users,name));
            }
            completion++;
        }
        
        
        UserSpace.saveUsers();
    }

    
    
    public void grant(int[] mins){
        Material M = null;
        
        
        switch(mins[0]){
            case 0:
                switch(mins[1]){
                    case 1:
                        this.name = "beast_gate";
                        M = Material.get("beast",mins[2]);  
                        break;
                    case 2:
                        this.name = "gremlin_gate";
                        M = Material.get("gremlin",mins[2]);
                        break;
                    case 3:
                        this.name = "fire_gate";
                        M = Material.get("fire",mins[2]);
                        break;
                    case 4:
                        this.name = "fiend_gate";
                        M = Material.get("fiend",mins[2]);
                        break;
                }
                break;
            case 1:
                switch(mins[1]){
                    case 2:
                        this.name = "freeze_gate";
                        M = Material.get("freeze",mins[2]);
                        break;
                    case 3:
                        this.name = "slime_gate";
                        M = Material.get("slime",mins[2]);
                        break;
                    case 4:
                        this.name = "poison_gate";
                        M = Material.get("poison",mins[2]);
                        break;
                }
                break;
            case 2:
                switch(mins[1]){
                    case 3:
                        this.name = "construct_gate";
                        M = Material.get("construct",mins[2]);
                        break;
                    case 4:
                        this.name = "shock_gate";
                        M = Material.get("shock",mins[2]);
                        break;
                }
                break;
            default:
                this.name = "undead_gate";
                M = Material.get("undead",mins[2]);
        }
        
        
        for(UserData U : users){
            U.giveMaterial(M);
            mats.add(M);
            U.crowns += oRan.nextInt(200);
            U.giveMinerals(oRan.nextInt(5));
        }
    }
    
    public int[] selectMinerals(){
        int[] ret = new int[3];
        
        int totalMinerals = 0;
        for(int a : minerals){
            totalMinerals += a;
        }
        
        int select = oRan.nextInt(totalMinerals);
        int result = 0;
        for(int a : minerals){
            select = select - a;
            if(select > 0){
                result++;
            }
        }
        
        ret[0] = result;
        
        while(ret[0] == result){
            select = oRan.nextInt(totalMinerals);
            result = 0;
            for(int a : minerals){
                select = select - a;
                if(select > 0){
                    result++;
                }
            }
        }
        
        ret[1] = result;
       
        if(ret[0] > ret[1]){
            int temp = ret[0];
            ret[0] = ret[1];
            ret[1] = temp;
        }
        
        int selectedTotal = ret[0] + ret[1];
        int otherAverage = (totalMinerals - selectedTotal) / 3;
        otherAverage = (int)Math.ceil(Math.sqrt(otherAverage));
        int star = (int)Math.ceil(Math.ceil(Math.log(selectedTotal)) / Math.floor(Math.log(otherAverage)));
        //star = (int)Math.ceil(Math.log(star));
        
        ret[2] = star;

        return ret;
    }
    
    
    //IF IT IS SENT MESSAGES THIS IS CALLED
    
    
    public void parse(IMessage message){
        this.channel = message.getChannel();
        Scanner oScan = new Scanner(message.getContent());
        String token = oScan.next();
        
        IUser sender = message.getAuthor();
        
        UserData UD = UserSpace.getPlayer(sender);

        if(!users.contains(UD)){
            users.add(UD);
            time.put(UD.ID,System.currentTimeMillis());
            send(UD.name+" joined "+this.name+"!");
        }
        
        if(token.charAt(0) != '!'){
            bump(sender);
            return;
        }
        
        if(token.equals("!deposit")){
            int num = oScan.nextInt();
            String type = oScan.next();
            int t = -1;
            if(type.equals("red")){
                t = 0;
            } else if(type.equals("green")){
                t = 1;
            } else if(type.equals("blue")){
                t = 2;
            } else if(type.equals("yellow")){
                t = 3;
            } else if(type.equals("purple")){
                t = 4;
            } else {
                send("Mineral type not found!");
                return;
            }
            if(UD.minerals[t] >= num){
                UD.minerals[t] -= num;
                this.minerals[t]+= num;
                send("Success! You deposited "+num+" "+type+" minerals!");
                this.saveMinerals();
            } else {
                send("You don't have that many minerals!");
                return;
            }
        }
        
        if(token.equals("!area")||token.equals("!gate")){
            String toSend = "Name: "+name;
            toSend+="\nMinerals Remaining: "+minerals[0]+" red, "+minerals[1]+" green, "+minerals[2]+""
                + " blue, "+minerals[3]+" yellow, "+minerals[4]+" purple";
            send(toSend);
        }
        
        if(token.equals("!tick")){
            String ID = sender.getID();
            if(ID.equals("144857966816788482")){
                this.play();
            } else {
                send("Nope.");
            }
        }
        
    }
    
    
    public void bump(IUser user){
        time.put(user.getID(),System.currentTimeMillis());
    }
    
    public void timeOut(){
        long currentTime = System.currentTimeMillis();
        for(int i = users.size()-1; i >= 0; --i){
            String id = users.get(i).ID;
            long lastPing = time.get(id);
            if(currentTime - lastPing > TIME_OUT_TIME){
                send(users.get(i).name+" has been removed!");
                users.remove(i);
            }
        }
    }
    
    public void send(String message){
        Bopp.send(message,channel);
    }
    
    public void saveMinerals(){
        LoadSaver.saveProgress(minerals,channelID+"_minerals");
    }
    
    public int[] loadMinerals(){
        Object o = LoadSaver.load(channelID+"_minerals");
        if(o==null){
           return new int[5]; 
        }
        return (int[])o;
    }
}
