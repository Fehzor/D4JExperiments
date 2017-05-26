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
import Bopp.Data.UserData;
import Bopp.Gates.Gate;
import static Bopp.Gates.Gate.oRan;
import Bopp.Items.Gear;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author FF6EB4
 */
public class Obsidian extends Gear{
    public static Field<Integer> souls = new Field<Integer>("OBSIDIAN","SOULS",10);
    
    public static Field<HashMap<String,ArrayList<String>>> machines = new Field<>("OBSIDIAN","MACHINES",new HashMap<>());
    
    public Obsidian(String name, String prereq,int stars,String effect){
        super(name,prereq,"",stars);
        this.name = name;
        this.stars = stars;

        super.description = "+Dream dark machines into being! "+effect;
        this.slot = WEAPON;
        //0 Beast,1 Grem,2 Fire,3 Fiend
        //4 Freeze, 5 Slime, 6 Poison, 7 Construct, 8 Shock, 9 Undead
        this.mat1 = 9;
        this.mat2 = 10;
        this.prereq = prereq;
    }
    public void effect(UserData UD, Gate G){
        if(!machines.data.containsKey(UD.ID)){
            machines.data.put(UD.ID, new ArrayList<>());
            
            G.send("You begin to dream.");
        }
        
        if(souls.data < 1){
            return;
        }
        ArrayList<String> machineList = machines.data.get(UD.ID);
        if(machineList.size() == 0)return;
        
        souls.data -= 1;
        
        
        if(this.name.equals("Obsidian Edge")){
            int i = oRan.nextInt(machineList.size());
            Gear gear = Gear.gearMap.get(machineList.get(i));
            gear.effect(UD, G);
            gear.effect(UD, G);
            gear.effect(UD, G);
        }
        if(this.name.equals("Obsidian Carbine")){
            souls.data++;
            if(oRan.nextInt(100)<95){
                int i = oRan.nextInt(machineList.size());
                Gear gear = Gear.gearMap.get(machineList.get(i));
                gear.effect(UD, G);
            }
            if(oRan.nextInt(100) < 15){
                souls.data++;
            }
        }
        if(this.name.equals("Obsidian Crusher")){
            if(oRan.nextInt(100) <5){
                for(int i = 0; i<machineList.size();++i){
                    Gear gear = Gear.gearMap.get(machineList.get(i));
                    gear.effect(UD, G);
                }
            }
        }
        souls.write();
    }
}
