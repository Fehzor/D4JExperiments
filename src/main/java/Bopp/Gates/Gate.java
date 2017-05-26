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
package Bopp.Gates;


import Bopp.Bopp;
import Bopp.Data.Dailies;
import Bopp.Data.UserData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import sx.blah.discord.handle.obj.IChannel;
import Bopp.Data.Field;
import Bopp.Data.Missions;
import static Bopp.Data.Missions.FIFTEEN;
import Bopp.Items.Gear;
/**
 * @author FF6EB4
 */
public class Gate {
    public static final long TIME = 1000 * 40;
    public static final long TIME_OUT_TIME = 1000 * 60 * 60 * 24 * 2;//2 days
    public static Random oRan = new Random();
    public int tier;
    String area = "null";
    public IChannel channel;
    
    public Field<ArrayList<String>> userList;
    public Field<HashMap<String,Long>> userTimes;
    public Field<int[]> minerals;
    
    public Gate(IChannel channel, int tier){
        this.tier = tier;
        this.channel = channel;
        
        userList = new Field<>("TIER"+tier,"LIST",new ArrayList<>());
        userTimes = new Field<>("TIER"+tier,"TIMES",new HashMap<>());
        minerals = new Field<>("TIER"+tier,"MINERALS",new int[5]);
        
        rename();
    }
    
    public void handle(UserData UD, String mess){
        if(mess.length() == 0)return;
        if(mess.charAt(0)!='!')return;
        
        if(mess.contains("!area") || mess.contains("!gate")){
            String ret = "Tier "+tier+":\n";
            ret+="minerals: "+minerals.data[0]+" red, "+
                minerals.data[1]+" green, "+
                minerals.data[2]+" blue, "+
                minerals.data[3]+" yellow, "+
                minerals.data[4]+" purple";
            send(ret);
        }
        
        if(mess.contains("!stun")){
            if(UD.gear.data.contains("Stagger Storm") || 
                    UD.gear.data.contains("Haze Burst") || 
                    UD.gear.data.contains("Haze Bomb Mkii") ||
                    UD.gear.data.contains("Haze Bomb")){
                if(UD.materials.data[3] < 10){
                    return;
                }
                String ranID = userList.data.get(oRan.nextInt(userList.data.size()));
                UserData disp = UserData.getUser(ranID);
                send(disp.toString());
                UD.materials.data[3] -= 10;
            }
        }
        
        if(mess.contains("!poison")){
            if(UD.gear.data.contains("Venom Veiler") || 
                    UD.gear.data.contains("Toxic Atomizer") || 
                    UD.gear.data.contains("Toxic Vaporizer Mkii") ||
                    UD.gear.data.contains("Toxic Vaporizer")){
                if(UD.materials.data[6] < 10){
                    return;
                }
                String disp = "User List:\n";
                for(String s : userList.data){
                    UserData get = UserData.getUser(s);
                    disp+= get.name.data+"\n";
                }
                send(disp);
                UD.materials.data[6] -= 100;
            }
        }
        
        if(mess.contains("!freeze")){
            if(UD.gear.data.contains("Shivermist Buster") || 
                    UD.gear.data.contains("Freezing Atomizer") || 
                    UD.gear.data.contains("Freezing Vaporizer Mkii") ||
                    UD.gear.data.contains("Freezing Vaporizer")){
                if(UD.materials.data[4] < 30 || UD.materials.data[3] < 30){
                    return;
                }
                String ranID = userList.data.get(oRan.nextInt(userList.data.size()));
                UserData kick = UserData.getUser(ranID);
                this.removeUser(kick);
                send(kick.name.data+" was kicked!");
                UD.materials.data[3] -= 30;
                UD.materials.data[4] -= 30;
            }
        }
        
        if(mess.contains("!burn")){
            if(UD.gear.data.contains("Ash Of Agni") || 
                    UD.gear.data.contains("Fiery Atomizer") || 
                    UD.gear.data.contains("Fiery Vaporizer Mkii") ||
                    UD.gear.data.contains("Fiery Vaporizer")){
                if(UD.materials.data[2] < 200){
                    return;
                }
                Bopp.removeUser(UD, 0);
                Bopp.A.userList.data.add(UD.ID);
                Bopp.B.userList.data.add(UD.ID);
                Bopp.C.userList.data.add(UD.ID);
                UD.materials.data[2] -= 200;
                send("Added to all gates! Have fun AFK!");
            }
        }
        
        if(mess.contains("!shock")){
            if(UD.gear.data.contains("Voltaic Tempest") || 
                    UD.gear.data.contains("Plasma Capacitor") || 
                    UD.gear.data.contains("Lightening Capacitor") ||
                    UD.gear.data.contains("Static Capacitor")){
                if(UD.materials.data[8] < 100){
                    return;
                }
                Gear get = Gear.gearMap.get(UD.weaponA.data);
                for(int i = 0; i < 10; ++i){
                    get.effect(UD,this);
                }
                get = Gear.gearMap.get(UD.weaponB.data);
                for(int i = 0; i < 10; ++i){
                    get.effect(UD,this);
                }
                get = Gear.gearMap.get(UD.weaponC.data);
                for(int i = 0; i < 10; ++i){
                    get.effect(UD,this);
                }
                UD.materials.data[8] -= 100;
                this.send("BZZZT!!!");
            }
        }
        
        if(mess.contains("!deposit")){
            try{
                String[] arr = mess.split(" ",3);
                String col = arr[2];
                
                int amt = Integer.parseInt(arr[1]);
                
                int colNum = -1;
                switch(col){
                    case "red":
                        colNum = 0;
                        break;
                    case "green":
                        colNum = 1;
                        break;
                    case "blue":
                        colNum = 2;
                        break;
                    case "yellow":
                        colNum = 3;
                        break;
                    case "purple":
                        colNum = 4;
                        break;
                }
                
                if(UD.minerals.data[colNum] < amt){
                    send("You don't have that many minerals..");
                } else {
                    this.minerals.data[colNum] += amt;
                    UD.minerals.data[colNum] -= amt;
                    send("Success!");
                }
            } catch (Exception E){
                    String[] arr = mess.split(" ",2);
                    String col = arr[1];
                    int colNum = -1;
                    try{
                        switch(col){
                        case "red":
                            colNum = 0;
                            break;
                        case "green":
                            colNum = 1;
                            break;
                        case "blue":
                            colNum = 2;
                            break;
                        case "yellow":
                            colNum = 3;
                            break;
                        case "purple":
                            colNum = 4;
                            break;
                        case "all":
                            colNum = 5;
                            break;
                    }
                    if(colNum == 5){
                        for(int i = 0; i < 5; ++i){
                            this.minerals.data[i] += UD.minerals.data[i];
                            UD.minerals.data[i] = 0;
                        }
                        send("Success!");
                    } else {
                        this.minerals.data[colNum] += UD.minerals.data[colNum];
                        UD.minerals.data[colNum] = 0;
                        send("Success!");
                    }
                }catch(Exception E2){
                    send("Try !deposit <amt> <color>");
                }
            }
        }
    }
    
    public void removeUser(UserData UD){
        if(this.userList.data.contains(UD.ID)){
            this.userList.data.remove(UD.ID);
            System.out.println("Tier "+tier+": removed "+UD.ID);
            //this.userTimes.data.remove(UD.ID);
        }
    }
    
    public void ping(UserData UD){
        Dailies.checkDaily(UD,this);
        
        long diff = TIME;
        Bopp.removeUser(UD,tier);
        if(!userList.data.contains(UD.ID)){;
            userTimes.data.put(UD.ID,System.currentTimeMillis());
            userList.data.add(UD.ID);
        } else {
            diff = Math.abs(userTimes.data.get(UD.ID) - System.currentTimeMillis());
        }

        if(diff >= TIME){
            checkBoss();
            Missions.checkMissions(this);
            //REWARD THIS USER WITH (bonus) CROWNS AND MINERALS
            UD.crowns.data += oRan.nextInt(1000);
            UD.minerals.data[oRan.nextInt(5)] += oRan.nextInt(10);
            
            try{
                Gear A = Gear.gearMap.get(UD.weaponA.data);
                A.effect(UD,this);
            } catch (Exception E){
                System.out.println("GEAR A: "+E);
            }
            try{
                Gear B = Gear.gearMap.get(UD.weaponB.data);
                B.effect(UD,this);
            } catch (Exception E){
                System.out.println("GEAR B: "+E);
            }
            try{
                Gear C = Gear.gearMap.get(UD.weaponC.data);
                C.effect(UD,this);
            } catch (Exception E){
                System.out.println("GEAR C: "+E);
            }
            
            
            //REWARD ALL USERS WITH EVERYTHING.
            rewardMats();
            for(String s : userList.data){
                UserData giv = UserData.getUser(s);
                rewardOrb(giv);
                giv.crowns.data += 100;
                giv.minerals.data[oRan.nextInt(5)] += 1;
            }
            
            System.out.println("SAVING DATA");
            userTimes.data.put(UD.ID, System.currentTimeMillis());
            UserData.saveAll();
            userTimes.write();
            minerals.write();
            userList.write();
            this.checkTimeOut();
        }
    }
    
    public void checkBoss(){
        if(tier == 1){
           if(minerals.data[0] > 1000 && minerals.data[1] > 1000){
                for(String ID : userList.data){
                    if(System.currentTimeMillis() - userTimes.data.get(ID) < FIFTEEN){
                        UserData UD = UserData.getUser(ID);
                        UD.gear.data.add("Frumious Fangs");
                    }
                }
                minerals.data[0] = 0;
                minerals.data[1] = 0;
                
                send("The snarbolax has been summoned! Frumious Fangs for all!");
            } 
        }
        if(tier == 2){
            if(minerals.data[1] > 10000 && minerals.data[3] > 10000){
                for(String ID : userList.data){
                    if(System.currentTimeMillis() - userTimes.data.get(ID) < FIFTEEN){
                        UserData UD = UserData.getUser(ID);
                        UD.gear.data.add("Jelly Gems");
                    }
                }
                minerals.data[3] = 0;
                minerals.data[1] = 0;
                
                send("The jelly king has been summoned! Jelly Gems for all!");
            }
            if(minerals.data[2] > 10000 && minerals.data[3] > 10000){
                for(String ID : userList.data){
                    if(System.currentTimeMillis() - userTimes.data.get(ID) < FIFTEEN){
                        UserData UD = UserData.getUser(ID);
                        UD.gear.data.add("Bark Modules");
                    }
                }
                minerals.data[3] = 0;
                minerals.data[2] = 0;
                
                send("The roarmulus twins have been summoned! Bark Modules for all!");
            }
        }
        if(minerals.data[3] > 100000 && minerals.data[0] > 100000){
                for(String ID : userList.data){
                    UserData UD = UserData.getUser(ID);
                    UD.gear.data.add("Almirian Seals");
                }
                minerals.data[3] = 0;
                minerals.data[0] = 0;
                
                send("Lord Vanaduke has been summoned! Almirian Seals for all!");
            }
    }
    
    public void rewardOrb(UserData UD){
        if(this.tier == 1){
            if(oRan.nextInt(20) == 1){
                UD.orbs.data[0] +=1;
            }
            if(oRan.nextInt(100) == 1){
                UD.orbs.data[1] +=1;
            }
        }
        if(this.tier == 2){
            if(oRan.nextInt(60) == 1){
                UD.orbs.data[1] +=1;
            }
            if(oRan.nextInt(100) == 1){
                UD.orbs.data[2] +=1;
            }
        }
        if(this.tier == 3){
            if(oRan.nextInt(180) == 1){
                UD.orbs.data[2] +=1;
            }
            if(oRan.nextInt(180) == 1){
                UD.orbs.data[3] +=1;
            }
        }
    }
    
    
    public void rewardMats(){
        int[] ret = new int[2];
        
        int totalMinerals = 0;
        for(int a : minerals.data){
            totalMinerals += a;
        }
        
        if(totalMinerals>=10){
            int select = oRan.nextInt(totalMinerals);
            int result = 0;
            for(int a : minerals.data){
                select = select - a;
                if(select > 0){
                    result++;
                }
            }

            ret[0] = result;
            
            int trials = 100;
            while(ret[0] == result && trials > 0){
                trials -= 1;
                select = oRan.nextInt(totalMinerals);
                result = 0;
                for(int a : minerals.data){
                    select = select - a;
                    if(select > 0){
                        result++;
                    }
                }
            }
            if(ret[0] == result){
                result = oRan.nextInt(5);
                while(ret[0] == result){
                    result = oRan.nextInt(5);
                }
            }

            ret[1] = result;
        } else {
            return;
        }
        
        if(ret[0] > ret[1]){
            int temp = ret[0];
            ret[0] = ret[1];
            ret[1] = temp;
        }
        
        this.minerals.data[ret[0]] = (int)Math.floor(this.minerals.data[ret[0]] * .93);
        this.minerals.data[ret[1]] = (int)Math.floor(this.minerals.data[ret[1]] * .93);
        
       for(int i = 0; i < 5; ++i){
           if(this.minerals.data[i] < 0){
               this.minerals.data[i] = 0;
           }
       }
        
        //RGBYP
        
        //Beast,Grem,Fire,Fiend,Freeze,Slime,Poison,Construct,Shock,Undead
        
        //0//0,1,2,3
        //1//4,5,6
        //2//7,8
        //3//9
        
        if(ret[1] < ret[0]){
            int temp = ret[0];
            ret[0] = ret[1];
            ret[1] = temp;
        }
        
        int ans = 0;
        switch(ret[0]){
            case 1:
                ans = 4;
                break;
            case 2:
                ans = 7;
                break;
            case 3:
                ans = 9;
        }
        ans += ret[1] - ret[0] - 1;
        
        //System.out.println(ans+"="+ret[0]+"+"+ret[1]);
        
        
        

        
        int extraRewards = 1;
        
        if(tier == 1){
            extraRewards += totalMinerals / 135;
        }
        
        if(tier == 2){
            extraRewards += totalMinerals / 180;
        }
        
        if(tier == 3){
            extraRewards += totalMinerals / 220;
        }
        
        for(int i = 0; i < extraRewards; ++i){
            for( String ID : userList.data){
                UserData UD = UserData.getUser(ID);
                UD.materials.data[ans] += 1;
            }
        }
    }
    
    
    public void send(String s){
        Bopp.send(s,channel);
    }
    
    private void rename(){
        try{
            channel.changeName("tier_"+tier);
            }catch (Exception E){System.out.println("Failed to rename!"+E);}
    }
    
    private void checkTimeOut(){
        for(int i = userList.data.size()-1; i > 0; --i){
            String ID = userList.data.get(i);
            if(System.currentTimeMillis() - userTimes.data.get(ID) > TIME_OUT_TIME){
                this.removeUser(UserData.getUser(ID));
                //send(UserData.getUser(ID).name.data+" has timed out!");
            }
        }
    }
}
