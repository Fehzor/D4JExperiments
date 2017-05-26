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

import Bopp.Items.Gear;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import sx.blah.discord.handle.obj.IUser;

/**
 *
 * @author FF6EB4
 */
public class UserData {
    public static Field<ArrayList<String>> IDLIST = new Field("USERDATA","IDLIST",new ArrayList<>());
    private static HashMap<String,UserData> USERMAP = loadUsers();
    
    private ArrayList<Field> fields = new ArrayList<>();
    
    public String ID;
    public Field<String> name;
    public Field<Long> prestige;
    public Field<Integer> crowns;
    public Field<String> weaponA;
    public Field<String> weaponB;
    public Field<String> weaponC;
    public Field<ArrayList<String>> gear;
    public Field<int[]> orbs; //2*,3*,4*,5*
    public Field<int[]> minerals; //Red,Green,Blue,Yellow, Purple
    public Field<int[]> materials; //Beast,Grem,Fire,Fiend,Freeze,Slime,Poison,Construct,Shock,Undead
    public Field<Integer> slimeCoins;
    
    private UserData(IUser key){
        this.ID = key.getID();
        if(!IDLIST.data.contains(this.ID)){
            IDLIST.data.add(ID);
        }
        load();
        name.data = key.getName();
    }
    
    private UserData(String ID){
        this.ID = ID;
        if(!IDLIST.data.contains(this.ID)){
            IDLIST.data.add(ID);
        }
        load();
    }
    
    private void save(){
        for(Field f : fields){
            f.write();
        }
    }
    
    public static void saveAll(){
        IDLIST.write();
        
        for(String ID : IDLIST.data){
            UserData UD = USERMAP.get(ID);
            UD.save();
        }
    }
    
    private void load(){
        name = new Field<>(ID,"name","NA");
        prestige = new Field<>(ID,"prestige",(long)0);
        crowns = new Field<>(ID,"crowns",0);
        orbs = new Field<>(ID,"orbs",new int[4]);
        minerals = new Field<>(ID,"minerals",new int[5]);
        slimeCoins = new Field<>(ID,"slimecoins",0);
        materials = new Field<>(ID,"materialsNEW",new int[14]);
        
        weaponA = new Field<String>(ID,"weaponA","Proto Sword");
        weaponB = new Field<>(ID,"weaponB","Proto Gun");
        weaponC = new Field<>(ID,"weaponC","Proto Bomb");
        gear = new Field<>(ID,"gear",new ArrayList<>());
        
        
        fields.add(name);
        fields.add(prestige);
        fields.add(crowns);
        fields.add(materials);
        fields.add(slimeCoins);
        fields.add(weaponA);
        fields.add(weaponB);
        fields.add(weaponC);
        fields.add(gear);
        fields.add(orbs);
        fields.add(minerals);
        
        
        
    }
    
    private static HashMap<String,UserData> loadUsers(){
        HashMap<String,UserData> ret = new HashMap<>();
        System.out.println("LOADING ALL USERS");
        for(String ID : IDLIST.data){
            ret.put(ID,new UserData(ID));
        }
        
        return ret;
    }
    
    public static UserData getUser(IUser user){
        String ID = user.getID();
        if(!USERMAP.containsKey(ID)){
            USERMAP.put(ID,new UserData(user));
        }
        return USERMAP.get(ID);
    }
    
    public static UserData getUser(String ID){
        if(!USERMAP.containsKey(ID)){
            USERMAP.put(ID,new UserData(ID));
        }
        return USERMAP.get(ID);
    }
    
    public String toString(){
        String ret = "";
        
        Collections.sort(this.gear.data);
        
        for(Field f : fields){
            if(! (f.data instanceof int[])){
            ret+="**"+f.name+":** "+f.data.toString() + "\n";
            }
        }
        ret+="**minerals:** \n"+minerals.data[0]+" red, "+
                minerals.data[1]+" green, "+
                minerals.data[2]+" blue, "+
                minerals.data[3]+" yellow, "+
                minerals.data[4]+" purple";
        
        ret+="\n**orbs:** \n"+orbs.data[0]+" simple"
                + ", "+orbs.data[1]+" advanced"
                + ", "+orbs.data[2]+" elite"
                + ", "+orbs.data[3]+" eternal";
        
        //Beast,Grem,Fire,Fiend,Freeze,Slime,Poison,Construct,Shock,Undead
        ret+="\n**materials:** \n"
                + ""+materials.data[0]+" beast"
                + ", "+materials.data[1]+" gremlin"
                + ", "+materials.data[2]+" fire"
                + ", "+materials.data[3]+" fiend"
                + ", "+materials.data[4]+" freeze"
                + ", "+materials.data[5]+" slime"
                + ", "+materials.data[6]+" poison"
                + ", "+materials.data[7]+" construct"
                + ", "+materials.data[8]+" shock"
                + ", "+materials.data[9]+" undead"
                + ", "+materials.data[10]+" apocrean";
        
        
        return ret;
    }
    
    public String mat(){
        return "**materials:** \n"
                + ""+materials.data[0]+" beast"
                + ", "+materials.data[1]+" gremlin"
                + ", "+materials.data[2]+" fire"
                + ", "+materials.data[3]+" fiend"
                + ", "+materials.data[4]+" freeze"
                + ", "+materials.data[5]+" slime"
                + ", "+materials.data[6]+" poison"
                + ", "+materials.data[7]+" construct"
                + ", "+materials.data[8]+" shock"
                + ", "+materials.data[9]+" undead"
                + ", "+materials.data[10]+" apocrean";
    }
    
    public String orb(){
        return "**orbs:** \n"+orbs.data[0]+" simple"
                + ", "+orbs.data[1]+" advanced"
                + ", "+orbs.data[2]+" elite"
                + ", "+orbs.data[3]+" eternal";
    }
    
    public String min(){
        return "**minerals:** \n"+minerals.data[0]+" red, "+
                minerals.data[1]+" green, "+
                minerals.data[2]+" blue, "+
                minerals.data[3]+" yellow, "+
                minerals.data[4]+" purple";
    }
    
    public String small(){
        String ret = "";
        
        for(Field f : fields){
            if(! (f.data instanceof int[] || f.data instanceof ArrayList)){
            ret+=f.name+": "+f.data.toString() + "\n";
            }
        }
        
        return ret;
    }
    
    public String Gear(){
        String ret = "**Five Star:**\n";
        
        String[] sorted = new String[5];
        sorted[4] = "";
        sorted[3] = "";
        sorted[2] = "";
        sorted[1] = "";
        for(String s : gear.data){
            try{
                Gear get = Gear.gearMap.get(s);
                sorted[get.stars-1] += get.name+", ";
            } catch (Exception E){}
        }
        
        ret+=sorted[4];
        ret += "\n**Four Star:**\n";
        ret+=sorted[3];
        ret += "\n**Three Star:**\n";
        ret+=sorted[2];
        ret += "\n**Two Star:**\n";
        ret+=sorted[1];
        
        return ret;
    }
}
