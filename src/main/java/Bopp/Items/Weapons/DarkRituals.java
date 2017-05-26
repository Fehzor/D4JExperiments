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
package Bopp.Items.Weapons;

import Bopp.Data.Field;
import static Bopp.Data.Missions.FIFTEEN;
import Bopp.Data.Unboxing;
import Bopp.Data.UserData;
import Bopp.Gates.Gate;
import static Bopp.Gates.Gate.oRan;
import Bopp.Items.Gear;

/**
 *
 * @author FF6EB4
 */
public class DarkRituals extends Gear{
    public static Field<Integer> time = new Field<>("BODR","TIME",0);
    public static Field<Boolean> start = new Field<>("BODR","START",true);
    public DarkRituals(){
        super("The Book Of Dark Rituals","HIDDEN OBJECT","",5);
        this.name = name;
        this.stars = stars;

        super.description = "";
        this.slot = WEAPON;
        //0 Beast,1 Grem,2 Fire,3 Fiend
        //4 Freeze, 5 Slime, 6 Poison, 7 Construct, 8 Shock, 9 Undead
        
        this.mat1 = -1;
        this.mat2 = -1;
        
        this.prereq = "HIDDEN OBJECT";
    }
    public void effect(UserData UD, Gate G){
        if(time.data < 1){
            time.data = 13;
            if(!start.data){
                int event = oRan.nextInt(1+oRan.nextInt(8));
                
                if(event == 0){
                    String id = G.userList.data.get(oRan.nextInt(G.userList.data.size()));
                    
                    UserData chosen = UserData.getUser(id);
                    
                    G.send("Margrel challenges the gate, but a single knight stands up to fight! It's "+chosen.name.data+"! They earn 2000 prestige!");
                    
                    chosen.prestige.data += 2000;
                }
                
                if(event == 1){
                    G.send("Margrel devastates the gate's minerals! You collectively fight off Margrel and get 100 prestige each!");
                    G.minerals.data = new int[5];
                    for(String id : G.userList.data){
                        UserData giv = UserData.getUser(id);
                        giv.prestige.data += 100;
                    }
                }
                
                if(event == 2){
                    G.send("Margrel bites your face off! It hurts really bad, but will grow back. Red minerals + 1000.");
                    UD.minerals.data[0] += 1000;
                }
                
                if(event == 3){
                    G.send("Margrel is sleeping! Nothing happens! +1 prestige!");
                    UD.prestige.data += 1;
                }
                
                
                
                if(event == 4){
                    G.send("Margrel has brought slime coins for every single knight in the gate! We don't know why...");
                    for(String id : G.userList.data){
                        UserData giv = UserData.getUser(id);
                        giv.slimeCoins.data += oRan.nextInt(1000);
                    }
                }
                
                if(event == 5){
                        G.send("Margrel brought you a prize box!!! It thinks it's your birthday!!! Horrifying!! No more yellow in the gate!!");
                    G.minerals.data[3] = 0;
                    if(Unboxing.starts.data.containsKey(UD.ID)){
                        Unboxing.starts.data.put(UD.ID,Unboxing.starts.data.get(UD.ID)+1000*60*10);
                        G.send("But you were already unboxing!!! So it sped it up instead!!!");
                    } else {
                        Unboxing.starts.data.put(UD.ID,System.currentTimeMillis() - (long)1000 * 60 * 30);
                    }
                }
                
                if(event == 6){
                    G.send("Margrel consumes some of one of your materials and turns the gate into undead!!! Way too much undead!!!");
                    int which = oRan.nextInt(10);
                    UD.materials.data[which] = (int)(UD.materials.data[which]*.9);
                    
                    G.minerals.data[3] += 15000;
                    G.minerals.data[4] += 15000;
                }
                
                if(event == 7){
                    G.send("Margrel calmly deposits 500 of each mineral!!! BUT WHY? IT WANTS MATS TOO!");
                    G.minerals.data[0] += 500;
                    G.minerals.data[1] += 500;
                    G.minerals.data[2] += 500;
                    G.minerals.data[3] += 500;
                    G.minerals.data[4] += 500;
                }
                
                
            } else {
                G.send("....you begin to summon margrel!");
            }
        } else {
            start.data = false;
            time.data -= 1;
            if(time.data == 10){
                G.send("You hear a rumbling in the distance...");
            }
            
           
            if(time.data == 2){
                G.send("...you feel uneasy.");
            }
        }
        System.out.println("RITUAL TIME: "+time.data);
        time.write();
        start.write();
    }
    
    public String toString(){
        return "null";
    }
}
