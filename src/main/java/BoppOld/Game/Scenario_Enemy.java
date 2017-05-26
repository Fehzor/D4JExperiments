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
import BoppOld.Items.Gear.GearMap;
import static BoppOld.Items.Gear.GearMap.gearMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author FF6EB4
 */
public class Scenario_Enemy extends Scenario{
    
    String name = "chromalisk";
    String type = "normal_gate";
    String subType = "normal_gate";
    int kind = TYPE_SINGLE;
    
    public Scenario_Enemy(String enemyName, String type, int kind){
        name = enemyName;
        this.type = type;
        this.kind = kind;
        
        this.start = "A "+name+" appeared!";
        this.end = "The party destroyed the ferocious "+name+"!";
        
        if(this.kind == TYPE_CROWD){
            this.start = "Several "+name+" appeared!";
            this.end = "The party destroyed the crowd of "+name+"s!";
        }
        
        if(this.kind == TYPE_HEAVY_CROWD){
            this.start = "Many "+name+" appeared!";
            this.end = "The party destroyed the multitude of "+name+"s!";
        }
        
        if(this.kind == TYPE_UTILITY){
            this.start = "A wily "+name+" appeared!";
            this.end = "The party destroyed the wily "+name+"!";
        }
    }
    
    public void addSelf(HashMap<String,ArrayList<Scenario>> ret){
        ret.get(type).add(this);
    }
    
    public String giveLoot(ArrayList<UserData> users){
        Gear A = GearMap.gearMap.get(users.get(0).weaponA);
        UserData chosen = users.get(0);
        for(UserData UD : users){
            Gear temp = GearMap.gearMap.get(UD.weaponA);
            A = compare(A,temp);
            temp = GearMap.gearMap.get(UD.weaponB);
            A = compare(A,temp);
        }
        calculateLoot(users,A);
        return chosen.name+" utilized their "+A.name+" effectively!";
    }
    
    public Gear compare(Gear A, Gear B){
        int a = calculate(A);
        int b = calculate(B);
        
        if(b > a){
            return B;
        } else {
            return A;
        }
    }
    
    public void calculateLoot(ArrayList<UserData> users, Gear best){
        int multi = calculate(best);
        for(UserData UD : users){
            UD.crowns += multi * 100 + oRan.nextInt(100);
        }
    }
    
    public int calculate(Gear C){
        int ret = 0;
        
        ret+=C.stars - 2;
        //System.out.println(this.type);
        
        if(C.where1.equals(this.type)){
            ret += 4;
        }else if(C.where2.equals(this.type)){
            ret += 4;
        }else if(C.where3.equals(this.type)){
            ret += 3;
        } else if(C.where1.equals(this.subType)){
            ret += 3;
        } else if(C.where2.equals(this.subType)){
            ret += 3;
        } else if(C.where3.equals(this.subType)){
            ret += 2;
        }
        
        if(C.type == this.kind){
            ret += 4;
        }
        
        if(C.where1.equals("normal_gate") || 
                C.where2.equals("normal_gate") || 
                C.where3.equals("normal_gate")){
            ret += 2;
        }
        
        return ret;
    }
    
    public static void loadAll(HashMap<String,ArrayList<Scenario>> addTo){
        try{
            File f = new File("enemies.txt");
            Scanner oScan = new Scanner(f);
            
            while(oScan.hasNextLine()){
                String area = oScan.next();
                String name = oScan.nextLine().substring(1);
                
                Scenario_Enemy gnuA = new Scenario_Enemy(name, area, TYPE_SINGLE);
                Scenario_Enemy gnuB = new Scenario_Enemy(name, area, TYPE_CROWD);
                Scenario_Enemy gnuC = new Scenario_Enemy(name, area, TYPE_HEAVY_CROWD);
                Scenario_Enemy gnuD = new Scenario_Enemy(name, area, TYPE_UTILITY);
                
                for(int i = 1; i<5; ++i){
                    Scenario_Enemy gnuE = new Scenario_Enemy("icy "+name, "freeze_gate", i);
                    gnuE.subType = area;
                    gnuE.addSelf(addTo);
                }
                
                for(int i = 1; i<5; ++i){
                    Scenario_Enemy gnuE = new Scenario_Enemy("fiery "+name, "fire_gate", i);
                    gnuE.subType = area;
                    gnuE.addSelf(addTo);
                }
                
                for(int i = 1; i<5; ++i){
                    Scenario_Enemy gnuE = new Scenario_Enemy("shocking "+name, "shock_gate", i);
                    gnuE.subType = area;
                    gnuE.addSelf(addTo);
                }
                
                for(int i = 1; i<5; ++i){
                    Scenario_Enemy gnuE = new Scenario_Enemy("toxic "+name, "poison_gate", i);
                    gnuE.subType = area;
                    gnuE.addSelf(addTo);
                }
                
                gnuA.addSelf(addTo);
                gnuB.addSelf(addTo);
                gnuC.addSelf(addTo);
                gnuD.addSelf(addTo);
            }
            
            
        }catch (FileNotFoundException E){
        System.out.println("Enemies failed to load!\n"+E);}
    }
    
    
}
