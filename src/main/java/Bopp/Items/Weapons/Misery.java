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
public class Misery extends Gear{
    public static Field<HashMap<String,Integer>> mode = new Field<>("MISERY","MODE",new HashMap<>());
    public static Field<HashMap<String,Long>> time = new Field<>("MISERY","TIME",new HashMap<>());
    public static Field<HashMap<String,Long>> starttime = new Field<>("MISERY","STARTTIME",new HashMap<>());
    public static Field<HashMap<String,Integer>> devilites = new Field<>("MISERY","DEVILITES",new HashMap<>());
    public static Field<HashMap<String,Integer>> lazies = new Field<>("MISERY","LAZIES",new HashMap<>());
    public static Field<HashMap<String,Integer>> gorgos = new Field<>("MISERY","GORGOS",new HashMap<>());
    public Misery(){
        super("Mug Of Misery","HIDDEN OBJECT","",5);
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
        if(!devilites.data.containsKey(UD.ID)){
            mode.data.put(UD.ID,0);
            time.data.put(UD.ID,(long)(1000 * 60 * 60));
            starttime.data.put(UD.ID,System.currentTimeMillis());
            devilites.data.put(UD.ID,3);
            lazies.data.put(UD.ID,0);
            gorgos.data.put(UD.ID,0); 
            
            G.send("3 devilites begin to follow you. What will you command of them?\n"
                    + "!summon = summon more devilite followers; +devilites\n"
                    + "!lazy = turn lazy workers into gorgos; +gorgos; -devilites\n"
                    + "!sacrifice = sacrifice gorgos; +prestige; -gorgos\n"
                    + "!paperwork = produce lazy workers; +lazy devilites");
            return;
        }
        
        int devil = devilites.data.get(UD.ID);
        double productivity = Math.log(devil);
        
        long timecheck = time.data.get(UD.ID);
        
        time.data.put(UD.ID,(long)(time.data.get(UD.ID) - (productivity*500)));
        
        if(System.currentTimeMillis() - starttime.data.get(UD.ID) > timecheck){
            if(mode.data.get(UD.ID) == 0){
                //Summon
                int num = oRan.nextInt(1+(int)Math.sqrt(devil));
                int num2 = oRan.nextInt(1+num);
                devilites.data.put(UD.ID,devil+num-num2);
                lazies.data.put(UD.ID,num2+lazies.data.get(UD.ID));
                int total = lazies.data.get(UD.ID) + devilites.data.get(UD.ID);
                G.send(num+" devilites have joined your cause! Total: "+total);
                time.data.put(UD.ID,(long)(1000 * 60 * 60));
                starttime.data.put(UD.ID, System.currentTimeMillis());
            }
            if(mode.data.get(UD.ID) == 1){
                //Punish
                int num = oRan.nextInt(1+lazies.data.get(UD.ID));
                int num2 = lazies.data.get(UD.ID) - num;
                devilites.data.put(UD.ID,devil+num);
                lazies.data.put(UD.ID,0);
                gorgos.data.put(UD.ID,num2);
                G.send(num2+" devilites were turned into gorgos! Switching to !summon mode");
                time.data.put(UD.ID,(long)(1000 * 60 * 60));
                starttime.data.put(UD.ID, System.currentTimeMillis());
            }
            if(mode.data.get(UD.ID) == 2){
                //Sacrifice
                UD.prestige.data += gorgos.data.get(UD.ID);
                gorgos.data.put(UD.ID,0);
                
                G.send("All gorgos were sacrificed for 1 prestige each! Switching to !summon mode");
                time.data.put(UD.ID,(long)(1000 * 60 * 60));
                starttime.data.put(UD.ID, System.currentTimeMillis());
            }
            if(mode.data.get(UD.ID) == 3){
                //Paperwork
                int num = oRan.nextInt(1+devil);
                lazies.data.put(UD.ID,lazies.data.get(UD.ID)+num);
                devilites.data.put(UD.ID,devilites.data.get(UD.ID) - num);
                G.send("The devilites hate life more than ever!");
                time.data.put(UD.ID,(long)(1000 * 60 * 60));
                starttime.data.put(UD.ID, System.currentTimeMillis());
            }
        }
        
        if(devilites.data.get(UD.ID) < 3){
            devilites.data.put(UD.ID,3);
        }
        devilites.write();
        lazies.write();
        gorgos.write();
        time.write();
        starttime.write();
    }
    
    public String toString(){
        return "null";
    }
}
