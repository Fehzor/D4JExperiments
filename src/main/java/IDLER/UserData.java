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
package IDLER;

import java.io.Serializable;
import java.util.ArrayList;
import IDLER.Areas.Area;
import static IDLER.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class UserData implements Serializable{
    //Basic currency
    public int crowns = 10000;
    public int energy = 45000;
    public int slime_coins = 0;
    public int minerals = 0;
    
    //Boss tokens
    public int jelly_gems = 0;
    public int frum_fangs = 0;
    public int bark_mods = 0;
    public int almire_coins = 0;
    public int snowflakes = 0;
    
    //Orbs
    public int flawed_orbs = 5;
    public int simple_orbs = 3;
    public int adv_orbs = 1;
    public int elite_orbs = 0;
    public int eternal_orbs = 0;
    
    public long slime_timer = 0;
    public long area_start = 0;
    
    public ArrayList<String> gear = new ArrayList<>();
    
    public void giveMinerals(){
        this.minerals += 1 + oRan.nextInt(200);
    }
    
    public String giveSlimeCoin(){
        long time = System.currentTimeMillis();
        time = time - slime_timer;
        if(time > 30000){
            slime_coins+=1;
            
            if(oRan.nextInt(1337) == 23){
                String add = GearMap.legends.get(oRan.nextInt(GearMap.legends.size()));
                gear.add(add);
                slime_timer = System.currentTimeMillis();
                return "Congratulations! You found "+add+" while typing a message!";
            }
            
            slime_timer = System.currentTimeMillis();
        }
        
        return "";
    }
    
    public String farming = "Nowhere";
    
    public String farm(String where){
        if(Area.areas.get(where) != null){
            this.farming = where;

            Area a = Area.areas.get(where);
            this.area_start = System.currentTimeMillis();

            long time = System.currentTimeMillis();
            time = (long)((time - area_start));
            time = ((long)((double)a.timeTillCompletion   / (double)getPower()))- time;
            
            return "Farm on! Each run will take " + (int)Math.ceil((double)time / (double)60000) + " minutes!";
        } else if (where.equals("nowhere")){
            farming = "Nowhere";
            return "You stop farming...";
        }
        return where+" isn't an area code!";
    }
    
    public String check(){
        if(farming.equals("Nowhere")){
            return "You aren't farming anywhere...";
        }
        
        Area a = Area.areas.get(farming);
        
        long time = System.currentTimeMillis();
        time = ((time - area_start));
        
        long areaTime = (long)((double)a.timeTillCompletion   / (double)getPower());
        
        
        if(time >= areaTime){
            //this.farming = "Nowhere";
            int precrowns = crowns;
            
            int runs = (int) Math.floor(time / areaTime);
            for(int i = 0; i< runs; ++i){
                a.giveLoot(this);
            }
            this.area_start+= runs * areaTime;
            
            return "Your knight completed the run "+a.name+" "+runs+" time(s) and earned "+ (crowns - precrowns)+" crowns!";
        }
        
        time = areaTime - time;
        
        return "Farming "+a.name+"! Come back in "+(int)Math.ceil(((double)time / (double)60000))+" minutes!";
    }
    
    public String cancel(){
        if(farming.equals("Nowhere")){
            return "You aren't farming anywhere... Type Bopp farm ____ to farm!";
        }
        
        Area a = Area.areas.get(farming);
        long time = System.currentTimeMillis();
        time = (long)((time - area_start) * getPower());
        time = a.timeTillCompletion - time;
        double percent = (double)time / (double)a.timeTillCompletion;
        percent = 1 - percent;
        int cr = (int)((double)a.crownPayout * (double)percent);
        this.crowns += cr;
        this.farming = "Nowhere";
        return "You quit farming... +"+cr+" crowns.";
    }
    
    public double getPower(){
        int starPower = 2;
        ArrayList<String> taken = new ArrayList<>();
        for(String s : gear){
            if(GearMap.starMap.get(s) == null){
            } else {
                if(taken.contains(s)){
                    starPower += (GearMap.starMap.get(s));
                } else {
                    starPower += GearMap.starMap.get(s)*GearMap.starMap.get(s);
                    taken.add(s);
                }
            }
        }
        
        double multiplier = .93 + ((double)Math.log(starPower) / (double)8.3);
        
        return multiplier;
    }
}
