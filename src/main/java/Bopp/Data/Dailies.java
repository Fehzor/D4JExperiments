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
package Bopp.Data;

import Bopp.Bopp;
import Bopp.Gates.Gate;
import static Bopp.Gates.Gate.oRan;
import Bopp.Items.Gear;
import java.util.HashMap;

/**
 *
 * @author FF6EB4
 */
public class Dailies {
    public static long EIGHTEEN_HOURS = 1000 * 60 * 60 * 18;
    public static long THIRTY_HOURS = 1000 * 60 * 60 * 30;
    public static Field<HashMap<String,Long>> userTimes = new Field<>("DAILIES","USERTIMES",new HashMap<>());
    public static Field<HashMap<String,Boolean>> checked = new Field<>("DAILIES","CHECKED",new HashMap<>());
    
    public static void checkAllDailies(){
        for(String key : userTimes.data.keySet()){
            if(System.currentTimeMillis() - userTimes.data.get(key) > THIRTY_HOURS){
                if(checked.data.get(key)){
                    checkDaily(UserData.getUser(key),Bopp.A);
                    checked.data.put(key,false);
                }
            }
        }
    }
    
    public static void checkDaily(UserData UD, Gate G){
        if(!userTimes.data.containsKey(UD.ID)){
            userTimes.data.put(UD.ID,System.currentTimeMillis());
            
            UD.minerals.data[0] += 200;
            UD.minerals.data[1] += 200;
            UD.minerals.data[2] += 200;
            UD.minerals.data[3] += 200;
            UD.minerals.data[4] += 200;
            
            UD.crowns.data += 10000;
            
            G.send("Welcome to the chat based version of Spiral Knights! To hear"
                    + " that again and start to figure things out, type !help into"
                    + " the chat. Here's some free swag for your first day logging"
                    + " in! +200 of each mineral; +10K crowns");
        } else {
            long current = System.currentTimeMillis();
            long last = userTimes.data.get(UD.ID);
            
            if(current - last > EIGHTEEN_HOURS){
                userTimes.data.put(UD.ID, System.currentTimeMillis());
                int tier = getRewardTier(UD);
                
                int which = oRan.nextInt(tier);
                
                if(which == 0){
                    UD.crowns.data += 10000;
                    G.send(UD.name.data+" has received 10K cr as a daily reward!");
                }
                
                if(which == 1){
                    UD.orbs.data[0] += 3;
                    G.send(UD.name.data+" has received 3 simple orbs as a daily reward!");
                }
                
                if(which == 2){
                    UD.orbs.data[1] += 3;
                    G.send(UD.name.data+" has received 3 advanced orbs as a daily reward!");
                }
                
                if(which == 3){
                    UD.orbs.data[2] += 3;
                    G.send(UD.name.data+" has received 3 elite orbs as a daily reward!");
                }
                
                if(which == 4){
                    UD.orbs.data[3] += 3;
                    G.send(UD.name.data+" has received 3 eternal orbs as a daily reward!");
                }
                
                if(which == 5){
                    UD.minerals.data[0] += 300;
                    UD.minerals.data[1] += 300;
                    UD.minerals.data[2] += 300;
                    UD.minerals.data[3] += 300;
                    UD.minerals.data[4] += 300;
                    G.send(UD.name.data+" has received 300 of each mineral as a daily reward!");
                }
                
                if(which == 6){
                    UD.slimeCoins.data += 300;
                    G.send(UD.name.data+" has received 300 slime coins as a daily reward!");
                }
                
                if(which == 7){
                    UD.slimeCoins.data += 400;
                    G.send(UD.name.data+" has received 400 slime coins as a daily reward!");
                }
                
                if(which == 8){
                    String s = Gear.gearList.get(oRan.nextInt(Gear.gearList.size()));
                    UD.gear.data.add(s);
                    G.send(UD.name.data+" has received "+s+" as a daily reward!");
                }
                
                if(which == 9){
                    G.send(UD.name.data+" has received a timed 30 min unboxing as a daily reward!");
                    if(Unboxing.starts.data.containsKey(UD.ID)){
                        G.send("Except they can't accept it! They're already opening a box!");

                    } else {
                        Unboxing.starts.data.put(UD.ID,System.currentTimeMillis() - (long)1000 * 60 * 30);
                    }
                }
                
                if(which == 10){
                    UD.crowns.data += 20000;
                    G.send(UD.name.data+" has received 20K cr as a daily reward!");
                }
                
                if(which == 11){
                    UD.orbs.data[0] += 5;
                    G.send(UD.name.data+" has received 3 simple orbs as a daily reward!");
                }
                
                if(which == 12){
                    UD.orbs.data[1] += 5;
                    G.send(UD.name.data+" has received 3 advanced orbs as a daily reward!");
                }
                
                if(which == 13){
                    UD.orbs.data[2] += 5;
                    G.send(UD.name.data+" has received 3 elite orbs as a daily reward!");
                }
                
                if(which == 14){
                    UD.orbs.data[3] += 5;
                    G.send(UD.name.data+" has received 3 eternal orbs as a daily reward!");
                }
                
                if(which == 15){
                    UD.minerals.data[0] += 500;
                    UD.minerals.data[1] += 500;
                    UD.minerals.data[2] += 500;
                    UD.minerals.data[3] += 500;
                    UD.minerals.data[4] += 500;
                    G.send(UD.name.data+" has received 200 of each mineral as a daily reward!");
                }
                
                if(which == 16){
                    UD.slimeCoins.data += 500;
                    G.send(UD.name.data+" has received 300 slime coins as a daily reward!");
                }
                
                if(which == 17){
                    UD.slimeCoins.data += 600;
                    G.send(UD.name.data+" has received 400 slime coins as a daily reward!");
                }
                
                if(which == 18){
                    String s = Gear.gearList.get(oRan.nextInt(Gear.gearList.size()));
                    UD.gear.data.add(s);
                    String s2 = Gear.gearList.get(oRan.nextInt(Gear.gearList.size()));
                    UD.gear.data.add(s2);
                    G.send(UD.name.data+" has received "+s+" and "+s2+" as a daily reward!");
                }
                
                if(which == 19){
                    G.send(UD.name.data+" has received a timed 15 min unboxing as a daily reward!");
                    if(Unboxing.starts.data.containsKey(UD.ID)){
                        G.send("Except they can't accept it! They're already opening a box!");

                    } else {
                        Unboxing.starts.data.put(UD.ID,System.currentTimeMillis() - (long)1000 * 60 * 15);
                    }
                }
                
                if(which == 20){
                    G.send(UD.name.data+" has received a timed 15 min unboxing as a daily reward!");
                    if(Unboxing.starts.data.containsKey(UD.ID)){
                        G.send("Except they can't accept it! They're already opening a box!");

                    } else {
                        Unboxing.starts.data.put(UD.ID,System.currentTimeMillis() - (long)1000 * 60 * 15);
                    }
                }
                
                if(which == 21){
                    G.send(UD.name.data+" has received a timed 11 min unboxing as a daily reward!");
                    if(Unboxing.starts.data.containsKey(UD.ID)){
                        G.send("Except they can't accept it! They're already opening a box!");

                    } else {
                        Unboxing.starts.data.put(UD.ID,System.currentTimeMillis() - (long)1000 * 60 * 11);
                    }
                }
                
                if(which == 22){
                    G.send(UD.name.data+" has received a timed 13 min unboxing as a daily reward!");
                    if(Unboxing.starts.data.containsKey(UD.ID)){
                        G.send("Except they can't accept it! They're already opening a box!");

                    } else {
                        Unboxing.starts.data.put(UD.ID,System.currentTimeMillis() - (long)1000 * 60 * 13);
                    }
                }
                
                if(which == 23){
                    G.send(UD.name.data+" has received a timed 23 min unboxing as a daily reward!");
                    if(Unboxing.starts.data.containsKey(UD.ID)){
                        G.send("Except they can't accept it! They're already opening a box!");

                    } else {
                        Unboxing.starts.data.put(UD.ID,System.currentTimeMillis() - (long)1000 * 60 * 23);
                    }
                }
            }
        }
        
        checked.data.put(UD.ID,true);
        checked.write();
        userTimes.write();
    }
    
    public static int getRewardTier(UserData UD){
        long prestige = UD.prestige.data;
        int ret = 1;//0
        
        if(prestige > 1000){//1
            ret++;
        }
        
        if(prestige > 5000){//2
            ret++;
        }
        
        if(prestige > 10000){//3
            ret++;
        }
        
        if(prestige > 25000){//4
            ret++;
        }
        
        if(prestige > 50000){//5
            ret++;
        }
        
        if(prestige > 75000){//6
            ret++;
        }
        
        if(prestige > 100000){//7
            ret++;
        }
        
        if(prestige > 150000){//8
            ret++;
        }
        
        if(prestige > 200000){//9
            ret++;
        }
        
        if(prestige > 300000){//10
            ret++;
        }
        
        if(prestige > 400000){//11
            ret++;
        }
        
        if(prestige > 500000){//12
            ret++;
        }
        
        if(prestige > 750000){//13
            ret++;
        }
        
        if(prestige > 1000000){//14
            ret++;
        }
        
        if(prestige > 1500000){//15
            ret++;
        }
        
        if(prestige > 2000000){//16
            ret++;
        }
        
        if(prestige > 3000000){//17
            ret++;
        }
        
        if(prestige > 4000000){//18
            ret++;
        }
        
        if(prestige > 5000000){//19
            ret++;
        }
        
        if(prestige > 10000000){//20
            ret++;
        }
        
        if(prestige > 15000000){//21
            ret++;
        }
        
        if(prestige > 20000000){//22
            ret++;
        }
        
        if(prestige > 30000000){//23
            ret++;
        }
        
        return ret;
    }
}
