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

import BoppOld.Data.UserData;
import BoppOld.Items.Gear.Gear;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author FF6EB4
 */
public class Scenario {
    
    public static final int TYPE_NONE = 0;
    public static final int TYPE_SINGLE = 1;
    public static final int TYPE_CROWD = 2;
    public static final int TYPE_HEAVY_CROWD = 3;
    public static final int TYPE_UTILITY = 4;
    
    public static HashMap<String,ArrayList<Scenario>> scenarios = loadScenarios();
    public static Random oRan = new Random();
    
    public String start = "The party found loot boxes!";
    public String end = "A fistful of crowns for everyone!";
    
    public static String run(ArrayList<UserData> users, String name){
        ArrayList<Scenario> get = scenarios.get(name);
        Scenario ran = get.get(oRan.nextInt(get.size()));
        
        String ret = ran.go(users);
        
        return ret;
    }
    
    public static HashMap<String,ArrayList<Scenario>> loadScenarios(){
        HashMap<String,ArrayList<Scenario>> ret = new HashMap<>();
        
        ret.put("slime_gate",new ArrayList<>());
        ret.put("beast_gate",new ArrayList<>());
        ret.put("fiend_gate",new ArrayList<>());
        ret.put("undead_gate",new ArrayList<>());
        ret.put("construct_gate",new ArrayList<>());
        ret.put("gremlin_gate",new ArrayList<>());
        ret.put("shock_gate",new ArrayList<>());
        ret.put("fire_gate",new ArrayList<>());
        ret.put("freeze_gate",new ArrayList<>());
        ret.put("poison_gate",new ArrayList<>());
        
        Scenario boxes = new Scenario();
        boxes.addSelf(ret);
        
        Scenario_Enemy.loadAll(ret);
        
        return ret;
    }
    
    
    
    
    public void addSelf(HashMap<String,ArrayList<Scenario>> ret){
        ret.get("slime_gate").add(this);
        ret.get("beast_gate").add(this);
        ret.get("fiend_gate").add(this);
        ret.get("undead_gate").add(this);
        ret.get("construct_gate").add(this);
        ret.get("gremlin_gate").add(this);
        ret.get("shock_gate").add(this);
        ret.get("fire_gate").add(this);
        ret.get("freeze_gate").add(this);
        ret.get("poison_gate").add(this);
    }
    
    public String go(ArrayList<UserData> users){
        String  s = start;
                s += "\n"+giveLoot(users);
                s += "\n"+end;
        return s;
    }
    
    public String giveLoot(ArrayList<UserData> users){
        for(UserData UD : users){
            UD.crowns+=223+oRan.nextInt(1000);
            UD.world_cores+=1;
        }
        
        UserData U = users.get(oRan.nextInt(users.size()));
        
        String wep = U.weaponA;
        
        return U.name +" smashed the boxes with "+wep+"!";
    }
}
