package BoppOld.Data;


import BoppOld.Data.LoadSaver;
import static BoppOld.Game.Area.oRan;
import BoppOld.Items.Gear.Gear;
import BoppOld.Items.Material;
import java.util.ArrayList;
import java.util.HashMap;

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

/**
 *
 * @author FF6EB4
 */
public class UserData {
    public String name;
    public String ID;
    public HashMap<String,Integer> materials;
    public int[] minerals;//Red,green,blue,yellow,purple
    public int crowns;
    public int world_cores;
    public int slimeCoins;
    
    public String weaponA = "Proto Sword";
    public String weaponB = "Proto Bomb";
    public ArrayList<String> gear = new ArrayList<>();
    
    //Create a new User
    public UserData(String name,String ID){
        this.name = name;
        this.ID = ID;
        minerals = new int[5];
        for(int i = 0; i < 5; ++i){
            minerals[i] = 0;
        }
        crowns = 0;
        slimeCoins = 0;
        materials = new HashMap<>();
        gear.add("Proto Gun");
        gear.add("Proto Sword");
        gear.add("Proto Bomb");
    }
    
    //Create a user based on data
    public UserData(String name,String ID,
            HashMap<String,Integer> materials,
            ArrayList<String> gears,
            int[] min,
            int cr,
            int orbs,
            int sc){
        this.name = name;
        this.gear = gears;
        this.materials = materials;
        this.minerals = min;
        this.world_cores = orbs;
        this.crowns = cr;
        this.slimeCoins = sc;
    }
    
    public void giveMaterial(Material M){
        if(materials.get(M.name) == null){
            materials.put(M.name,1);
        } else {
            int a = materials.get(M.name);
            materials.put(M.name,a+1);
        }
    }
    
    public String getDisp(){
        String s = "Name: "+name;
        s+="\nCrowns: "+crowns;
        //s+="\nWorld Cores: "+world_cores;
        s+="\nSlime Coins: "+slimeCoins;
        s+="\nMinerals: "+minerals[0]+" red, "+minerals[1]+" green, "+minerals[2]+""
                + " blue, "+minerals[3]+" yellow, "+minerals[4]+" purple";
        s+="\nWeapon A: "+weaponA;
        s+="\nWeapon B: "+weaponB;
        s+="\nGear: "+gear;
        s+="\nMaterials: "+materials;
        return s;
    }
    
    public void giveMinerals(int i){
        minerals[i] += 10+oRan.nextInt(3);
    }
    

}
