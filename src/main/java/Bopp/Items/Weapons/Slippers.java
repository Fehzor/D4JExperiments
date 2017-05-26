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
import java.util.HashMap;

/**
 *
 * @author FF6EB4
 */
public class Slippers extends Gear{
    public static Field<HashMap<String,Integer>> wake = new Field<>("SLIPPERS","DATA",new HashMap<>());
    public static Field<HashMap<String,Boolean>> sleep = new Field<>("SLIPPERS","DATA",new HashMap<>());
    
    
    public Slippers(String name, String prereq,int stars){
        super(name,prereq,"",stars);
        this.name = name;
        this.stars = stars;

        super.description = "";
        this.slot = WEAPON;
        //0 Beast,1 Grem,2 Fire,3 Fiend
        //4 Freeze, 5 Slime, 6 Poison, 7 Construct, 8 Shock, 9 Undead
        this.mat1 = -1;
        this.mat2 = -1;
        this.prereq = prereq;
    }
    public void effect(UserData UD, Gate G){
        if(!wake.data.containsKey(UD.ID)){
            wake.data.put(UD.ID,0);
            sleep.data.put(UD.ID,false);
            G.send("The slippers feel cozy... you feel as though you might just have a !dream");
        }
        wake.data.put(UD.ID,wake.data.get(UD.ID)+3);
        
        
        
        if(oRan.nextInt(500) < wake.data.get(UD.ID)){
            sleep.data.put(UD.ID,true);
        } else if(oRan.nextInt(1000) < wake.data.get(UD.ID)){
            wake.data.put(UD.ID,0);
            sleep.data.put(UD.ID,false);
            G.send("What!!??! You suddenly jolt back awake... you'll have to try dreaming faster next time.");
        }
        
        for(String test : UD.gear.data){
            try{
                int get = Integer.parseInt(test);
                
                UD.gear.data.remove(test);
                
                UD.gear.data.add(Gear.gearList.get(oRan.nextInt(Gear.gearList.size())));
                
            } catch(NumberFormatException E){}
        }
        
        wake.write();
        sleep.write();
    }
    
    public String toString(){
        return "null";
    }
}
