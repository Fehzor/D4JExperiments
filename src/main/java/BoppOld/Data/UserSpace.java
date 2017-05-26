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
package BoppOld.Data;

import BoppOld.Items.Material;
import java.util.ArrayList;
import java.util.HashMap;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

/**
 *
 * @author FF6EB4
 */
public class UserSpace {
    public static HashMap<String,UserData> users = getUsers();
    public static ArrayList<String> userNames;
    
    
    public static UserData getPlayer(IUser user){
        if(!userNames.contains(user.getID())){
            registerUser(user);
        }
        return users.get(user.getID());
    }
    
    
    public static void registerUser(IUser user){
        String ID = user.getID();
        users.put(ID,new UserData(user.getName(),ID));
        userNames.add(ID);
    }
    
    public static HashMap<String,UserData> getUsers(){
        userNames = new ArrayList<>();
        
        try{
            Object o = LoadSaver.load("usernames");
            if(o == null){
                return new HashMap<>();
            }
            
            ArrayList<String> names = (ArrayList<String>) o;
            
            
            HashMap<String,UserData> ret = new HashMap<>();
            
            for(String s : names){
                ret.put(s,loadUser(s));
                userNames.add(s);
            }
            
            return ret;
        } catch (Exception E){
            System.out.println(E);
            return new HashMap<>();
        }
    }
    
    public static UserData loadUser(String ID){
        try{
            UserData ret;
            Object o;
            
            o = LoadSaver.load(ID+"_name");
            String nam = (String) o;
            
            o = LoadSaver.load(ID+"_materials");
            HashMap<String,Integer> materials = (HashMap<String,Integer>) o;
            
            o = LoadSaver.load(ID+"_gear");
            ArrayList<String> gears = (ArrayList<String>) o;
            
            o = LoadSaver.load(ID+"_minerals");
            int[] min = (int[])o;
            
            o = LoadSaver.load(ID+"_crowns");
            int crowns = (int)o;
            
            o = LoadSaver.load(ID+"_orbs");
            int orbs = (int)o;
            
            o = LoadSaver.load(ID+"_slimeCoins");
            int slimeCoins = (int)o;
            
            ret = new UserData(nam,ID,materials,gears,min,crowns,orbs,slimeCoins);
            
            return ret;
        } catch (Exception E){
            return new UserData(ID,ID);
        }
    }
    
    public static void saveUsers(){
        LoadSaver.saveProgress(userNames, "usernames");
        for(String s : userNames){
            LoadSaver.saveProgress(users.get(s).name,s+"_name");
            LoadSaver.saveProgress(users.get(s).materials,s+"_materials");
            LoadSaver.saveProgress(users.get(s).minerals,s+"_minerals");
            LoadSaver.saveProgress(users.get(s).crowns,s+"_crowns");
            LoadSaver.saveProgress(users.get(s).world_cores,s+"_orbs");
            LoadSaver.saveProgress(users.get(s).slimeCoins,s+"_slimeCoins");
            LoadSaver.saveProgress(users.get(s).gear,s+"_gear");
        }
    }
}
