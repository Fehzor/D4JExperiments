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
package BoppOld.Items.Gear;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author FF6EB4
 */
public class GearMap {
    public static HashMap<String, Gear> gearMap;
    public static ArrayList<Gear> gearList;
    public static GearMap GM = new GearMap();
    
    public GearMap(){
        System.out.println("LOADING GEAR");
        gearList = new ArrayList<>();
        gearMap = new HashMap<>();
        //gearList = loadGearList();
        
        try{
            File f = new File("Weapons.txt");
            Scanner oScan = new Scanner(f);
            
            Gear weapon = new Gear();
            int i = 0;
            while(oScan.hasNextLine()){
                System.out.println("LOADING GEAR... LINE:"+i++);
                
                String line = oScan.nextLine();
                String token = "";
                String param = "";
                if(line.charAt(0) == '['){
                    token = line;
                    param = "";
                } else {
                    token = line.substring(0,line.indexOf(" "));
               
                    param = line.substring(line.indexOf(" ")+1);
                    System.out.println(token+" "+param);
                }
                if(token.equals("[WEAPON]")){
                    weapon = new Gear();
                }
                if(token.equals("[END]")){
                    gearList.add(weapon);
                    gearMap.put(weapon.name,weapon);
                }
                if(token.equals("name")){
                    weapon.name = param;
                }
                if(token.equals("star")){
                    
                    weapon.stars = Integer.parseInt(param);
                }
                if(token.equals("type")){
                    weapon.type = Integer.parseInt(param);
                }
                if(token.equals("gate1")){
                    param = param.toLowerCase();
                    weapon.where1 = param;
                }
                if(token.equals("gate2")){
                    param = param.toLowerCase();
                    weapon.where2 = param;
                }
                if(token.equals("gate3")){
                    param = param.toLowerCase();
                    weapon.where3 = param;
                }
                
                if(token.equals("mat1")){
                    param = param.toLowerCase();
                    weapon.mat1 = param;
                }
                if(token.equals("mat2")){
                    param = param.toLowerCase();
                    weapon.mat2 = param;
                }
                if(token.equals("mat3")){
                    param = param.toLowerCase();
                    weapon.mat3 = param;
                }
                
                if(token.equals("prereq")){
                    weapon.prereq = param;
                }
            }
            
            
        }catch (FileNotFoundException E){
        System.out.println("Weapons failed to load!");}
        
        System.out.println("WEAPONS LOADED!");
    }
}
