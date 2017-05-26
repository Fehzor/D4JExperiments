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
package BoppOld.Items;

import static BoppOld.Game.Area.oRan;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author FF6EB4
 */
public class Material implements Serializable{
    public static ArrayList<Material> materialList = loadMaterials();
    
    public String name;
    public String description;
    public int star;
    public ArrayList<String> themes;

    
    
    public Material(String name, String description, int star, ArrayList<String> themes){
        this.name = name;
        this.star = star;
        this.themes = themes;
        this.description = description;
    }
    
    public String toString(){
        return name;
    }
    
    public static Material get(String type, int star){
        Material ret = materialList.get(oRan.nextInt(materialList.size()));
        while(!ret.themes.contains(type) || ret.star > star){
            ret = materialList.get(oRan.nextInt(materialList.size()));
        }
        return ret;
    }
    
    public static ArrayList<Material> loadMaterials(){
        ArrayList<Material> ret = new ArrayList<>();
        
        try{
            File f = new File("materials.txt");
            Scanner oScan = new Scanner(f);
            
            ArrayList<String> themeLoad = new ArrayList<>();
            
            int starLoad = -1;
            String nameLoad = "";
            String descriptionLoad = "";
            
            while(oScan.hasNextLine()){
                String token = oScan.next();
                if(token.equals("name")){
                    nameLoad = oScan.next();
                    nameLoad += oScan.nextLine();
                    nameLoad = nameLoad.toLowerCase();
                }
                if(token.equals("star")){
                    starLoad = oScan.nextInt();
                }
                if(token.equals("theme")){
                    token = oScan.next();
                    token = token.toLowerCase();
                    themeLoad.add(token);
                }
                if(token.equals("description")){
                    descriptionLoad = oScan.nextLine();
                    
                    Material M = new Material(nameLoad,descriptionLoad,starLoad,themeLoad);
                    ret.add(M);
                    
                    themeLoad = new ArrayList<>();
                    starLoad = -1;
                    nameLoad = "";
                    descriptionLoad = "";
                    
                }
            }
            
            
        }catch (FileNotFoundException E){
        System.out.println("Materials fail to load!");}
        
        return ret;
    }
}
