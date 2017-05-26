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
import java.util.HashMap;

/**
 *
 * @author FF6EB4
 */
public class MonsterBone extends Gear{
    public static Field<HashMap<String,Integer>> wolvers = new Field<>("BONE","WOLVERS",new HashMap<>());
    public static Field<HashMap<String,Integer>> mode = new Field<>("BONE","MODE",new HashMap<>());
    public static Field<HashMap<String,Integer>> count = new Field<>("BONE","TIME",new HashMap<>());
    public static Field<HashMap<String,Boolean>> obey = new Field<>("BONE","OBEY",new HashMap<>());
    public static boolean valid = false;
    
    public MonsterBone(){
        super("Monster Bone","HIDDEN OBJECT","",5);
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
        if(!wolvers.data.containsKey(UD.ID)){
            wolvers.data.put(UD.ID,0);
            count.data.put(UD.ID,-1);
            mode.data.put(UD.ID,-1);
            obey.data.put(UD.ID,false);
            G.send("A wolver comes along to play! (!help wolver)");
        }
        
        if(oRan.nextInt(1000)<wolvers.data.get(UD.ID)){
            UD.slimeCoins.data += 100;
            MonsterBone.wolvers.data.put(UD.ID,MonsterBone.wolvers.data.get(UD.ID)-10);
            G.send("The wolver has brought you 100 slime coins! :D");
            if(wolvers.data.get(UD.ID) > 100){
                wolvers.data.put(UD.ID,100);
            }
            
            wolvers.write();
        }
        
        if(count.data.get(UD.ID) > 0){
            count.data.put(UD.ID,count.data.get(UD.ID) - 1);
            count.write();
            return;
        }
        
        int mo = mode.data.get(UD.ID);
        boolean next = false;
        
        if(oRan.nextInt(100) < wolvers.data.get(UD.ID)){
            obey.data.put(UD.ID,true);
            valid = true;
            next = true;
        } else {
            obey.data.put(UD.ID,false);
            valid = true;
        }
        
        if(mo == 0){
            //Sit
            if(next){
                G.send("The wolver sits patiently.");
            } else {
                G.send("The wolver ignores you and runs around.");
            }
        }
        if(mo == 1){
            //fetch
            if(next){
                G.send("The wolver returns the bone without a second glance.");
            } else {
                G.send("The wolver looks at you confused...");
            }
        }
        if(mo == 2){
            //shake
            if(next){
                G.send("The wolver shakes your hand! How nice!");
            } else {
                G.send("The wolver attempts to bit your hand D:");
            }
        }
        
        if(MonsterBone.wolvers.data.get(UD.ID) < 0){
            MonsterBone.wolvers.data.put(UD.ID,0);
        }
        
        obey.write();
        mode.write();
        count.write();
    }
    
    public String toString(){
        return "null";
    }
}
