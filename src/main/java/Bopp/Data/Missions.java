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

import Bopp.Gates.Gate;
import static Bopp.Gates.Gate.oRan;
import Bopp.Items.Gear;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author FF6EB4
 */
public class Missions {
    public static final long HOURS = 1000 * 60 * 60 * 18;
    public static final long FIFTEEN = 1000 * 60 * 15;
    private static Field<Long> time = new Field<>("MISSION","TIME",(long)0);
    
    private static Field<Integer> missionA1 = new Field<>("MISSION","A1",0);
    private static Field<Integer> missionB1 = new Field<>("MISSION","B1",0);
    
    private static Field<Integer> missionA2 = new Field<>("MISSION","A2",1);
    private static Field<Integer> missionB2 = new Field<>("MISSION","B2",1);
    
    private static Field<ArrayList<String>> clearanceA =
            new Field<>("MISSION","CLEARA",new ArrayList<>());
    
    private static Field<ArrayList<String>> clearanceB =
            new Field<>("MISSION","CLEARB",new ArrayList<>());
    
    private static Field<ArrayList<String>> clearanceC =
            new Field<>("MISSION","CLEARC",new ArrayList<>());
    
    private static Field<String> donation = new Field<>("MISSION","GEAR","Brandish");
    
    public static void checkMissions(Gate G){
        long current = System.currentTimeMillis();
        
        
        if((current - time.data) > HOURS){
            missionA1.data = oRan.nextInt(5);
            missionB1.data = oRan.nextInt(5);
            missionA2.data = oRan.nextInt(5);
            missionB2.data = oRan.nextInt(5);
            while(missionA1.data == missionA2.data){
                missionA2.data = oRan.nextInt(5);
            }
            while(missionB1.data == missionB2.data){
                missionB2.data = oRan.nextInt(5);
            }
            time.data = System.currentTimeMillis();
            clearanceA.data = new ArrayList<>();
            clearanceB.data = new ArrayList<>();
            clearanceC.data = new ArrayList<>();
            donation.data = Gear.gearList.get(oRan.nextInt(Gear.gearList.size()));
        }
        
        
        
        
        time.write();
        missionA1.write();
        missionA2.write();
        missionB1.write();
        missionB2.write();
        clearanceA.write();
        clearanceB.write();
        clearanceC.write();
        donation.write();
    }
    
    public static String missionA(UserData UD){
        if(UD.minerals.data[missionA1.data] > 250 &&
                UD.minerals.data[missionA2.data] > 250){
                if(!clearanceA.data.contains(UD.ID)){
                    UD.prestige.data += 250;
                    clearanceA.data.add(UD.ID);
                } else {
                    return "You've already done mission A!";
                }
            
            UD.minerals.data[missionA1.data] -= 250;
            UD.minerals.data[missionA2.data] -= 250;
            
            return "Mission A has been completed! +250 prestige!";
        } else {
            return "You lack the minerals for that!";
        }
    }
    
    public static String missionB(UserData UD){
        if(UD.minerals.data[missionB1.data] > 1000 &&
                UD.minerals.data[missionB2.data] > 1000){
                if(!clearanceB.data.contains(UD.ID)){
                    UD.prestige.data += 1200;
                    clearanceB.data.add(UD.ID);
                } else {
                    return "You've already done mission B!";
                }
            
            UD.minerals.data[missionB1.data] -= 1000;
            UD.minerals.data[missionB2.data] -= 1000;
            
            return "Mission B has been completed! +1200 prestige!";
        } else {
            return "You lack the minerals for that!";
        }
    }
    
    public static String getData(){
        System.out.println("MISSION A"+missionA1.data+" "+missionA2.data);
        int numA = getNum(missionA1.data,missionA2.data);
        String themeA = getTheme(numA);
        System.out.println("MISSION B"+missionB1.data+" "+missionB2.data);
        int numB = getNum(missionB1.data,missionB2.data);
        String themeB = getTheme(numB);
        
        System.out.println(numA + " " + numB);
        System.out.println(themeA + " " + themeB);
        
        
        return "Mission A: "+themeA+"\nMission B: "+themeB+"\nGear wanted: "+donation.data;
    }
    
    private static int getNum(int one, int two){
        int a = one;
        int b = two;
        if(one > two){
            b = one;
            a = two;
        }
        
        int ans = 0;
        switch(a){
            case 1:
                ans = 4;
                break;
            case 2:
                ans = 7;
                break;
            case 3:
                ans = 9;
        }
        ans += b - a - 1;
        
        return ans;
    }
    
    public static String getTheme(int i){
        String ret = "";
        switch(i){
            case 0:
                ret+="beast";
                break;
            case 1:
                ret+="gremlin";
                break;
            case 2:
                ret+="fire";
                break;
            case 3:
                ret+="fiend";
                break;
            case 4:
                ret+="freeze";
                break;
            case 5:
                ret+="slime";
                break;
            case 6:
                ret+="poison";
                break;
            case 7:
                ret+="construct";
                break;
            case 8:
                ret+="shock";
                break;
            case 9:
                ret+="undead";
                break;
        }
        
        return ret;
    }
    
    public static String donate(UserData UD){
        if(clearanceC.data.contains(UD.ID)){
            return "You've already donated today!";
        }
        if(UD.gear.data.contains(donation.data)){
            UD.gear.data.remove(donation.data);
            Gear gear = Gear.gearMap.get(donation.data);
            int prestige = getPrestige(gear.stars);
            UD.prestige.data += prestige;
            
            if(UD.weaponA.data.equals(gear.name)){
                UD.weaponA.data = "Proto Sword";
            }
            if(UD.weaponB.data.equals(gear.name)){
                UD.weaponB.data = "Proto Gun";
            }
            if(UD.weaponC.data.equals(gear.name)){
                UD.weaponC.data = "Proto Bomb";
            }
            
            clearanceC.data.add(UD.ID);
            
            return "+"+prestige+" prestige!";
        } else {
            return "You don't have the correct piece of gear to donate!";
        }
        
    }
    
    public static int getPrestige(int stars){
        switch(stars){
            case 2:
                return 200;
            case 3:
                return 800;
            case 4:
                return 5000;
            case 5:
                return 12500;
                
        }
        return 0;
    }
}
