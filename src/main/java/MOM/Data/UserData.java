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
package MOM.Data;

import MOM.Happenings.Happening;
import MOM.MoM;
import static MOM.SuperRandom.oRan;
import java.util.ArrayList;
import java.util.HashMap;
import sx.blah.discord.handle.obj.IUser;

/**
 *
 * @author FF6EB4
 */
public class UserData {
    public static Field<ArrayList<String>> IDList = new Field<>("USERDATA","IDLIST",new ArrayList<>());
    private static HashMap<String,UserData> UserList = new HashMap<>();
    
    public static UserData getUD(IUser user){
        if(!UserList.containsKey(user.getID())){
            if(!IDList.getData().contains(user.getID())){
                IDList.getData().add(user.getID());
            }
            UserList.put(user.getID(),new UserData(user.getID(),user.getName()));
        }
        
        IDList.writeData(IDList.getData());
        
        //if(UserList.get(user.getID()).name.equals("Clint Eastwood's Character")){
        //    UserList.get(user.getID()).name = user.getName();
        //}
        
        return UserList.get(user.getID());
    }
    
    public static UserData getUD(String ID){
        try{    
            IUser user = MoM.client.getUserByID(ID);
            UserList.put(ID,new UserData(ID,user.getName()));
        
            return getUD(user);
        } catch(Exception E){};
        
        
        return null;
    }
    
    public String ID = "";
    public String name = "";
    
    public Field<Integer> slimecoins;
    
    public Field<Integer> thwackers;
    public Field<Integer> demos;
    public Field<Integer> knockers;
    public Field<Integer> menders;
    public Field<Integer> corpses;
    
    public Field<Long> remainingTime;
    Field<Long> startingTime;
    
    public static final long BUMP_TIME = 1000*30;
    Field<Long> lastMessage;
    
    public Field<String> task;
    
    public UserData(String ID,String name){
        this.name = name;
        this.ID = ID;
        
        slimecoins = new Field<>(ID,"SlimeCoins",0);
        
        this.thwackers = new Field<>(ID,"Thwackers",0);
        this.demos = new Field<>(ID,"Demos",3);
        this.knockers = new Field<>(ID,"Knockers",0);
        this.menders = new Field<>(ID,"Menders",1);
        this.corpses = new Field<>(ID,"Corpses",0);
        
        this.remainingTime = new Field<>(ID,"remainingTime",(long)0);
        this.startingTime = new Field<>(ID,"startingTime",(long)0);
        
        this.lastMessage = new Field<>(ID,"lastMessage",(long)0);
        
        this.task = new Field<>(ID,"task","Nothing doing.");
    }
    
    public int getTimeRemaining(){
        long time = remainingTime.getData();
        long start = startingTime.getData();
        
        long current = System.currentTimeMillis();
        
        long time_passed = current - start;
        
        long remaining = time - time_passed;
        
        if(remaining < 0)return 0;
        
        int ret = toMinutes(remaining);
        
        return ret;
    }
    
    private int toMinutes(long time){
        return (int) Math.ceil(time / (long)(1000*60));
    }
    
    public void setTimer(int minutes){
        long time = minutes * 60 * 1000;
        remainingTime.writeData(time);
        startingTime.writeData(System.currentTimeMillis());
    }
    
    public boolean checkTimer(){
        if(getTimeRemaining() <= 0) return true;
        else return false;
    }
    
    public String toString(){
        String ret = "**"+name+"**\n\n";
        
        int timeRemaining = getTimeRemaining();
        
        ret+="**Task:** "+task.getData()+"\n";
        ret+="*Time remaining: "+timeRemaining+" minutes*\n\n";
        
        int totalSubs = thwackers.getData()+demos.getData()+knockers.getData()+menders.getData();
        ret+="**Total Gremlin Subordinates:** "+totalSubs+"\n";
        ret+="Demos: "+demos.getData()+"\n";
        ret+="Menders: "+menders.getData()+"\n";
        if(thwackers.getData() > 0){
            ret+="Thwackers: "+thwackers.getData()+"\n";
        }
        if(knockers.getData() > 0){
            ret+="Knockers: "+knockers.getData()+"\n";
        }
        if(corpses.getData() > 0){
            ret+="Corpses: "+corpses.getData()+"\n";
        }
        ret+="\n";
        
        if(this.slimecoins.getData() > 0){
            ret+= "**Golden Slime Coins:** "+slimecoins.getData()+"\n\n";
        }
        
        return ret;
    }
    
    public void bump(){
        long check = System.currentTimeMillis() - lastMessage.getData();
        
        if(check > BUMP_TIME){
            lastMessage.writeData(System.currentTimeMillis());
            
            String[] parse = task.getData().split(" ",2);
            
            int totalSubs = thwackers.getData()+demos.getData()+knockers.getData()+menders.getData();
            
            int count = 1;
            switch(parse[0]){
                case "Nothing":
                    return;
                case "Carrying":
                    count = knockers.getData();
                    break;
                case "Deconstructing":
                    count = demos.getData();
                    break;
                case "Constructing": case "Disconnecting": case "Connecting":
                    count = thwackers.getData();
                    break;
                case "Resurrecting":
                    count = menders.getData();
                    break;
            }
            if(count < 1){
                count = 1;
            }
            
            count = count + count + totalSubs;
            //
            // The final count = whatever type * 3 + everyone else.
            //
            // If you had 10 of each, you'd end up with 60.
            // If you had 5 of each and 10 of the one, you'd end up with 45
            // If you had 10 of each and 5 of th eone, you'd end up with 45
            // 
            // This then gets sqrted.
            //
            
            double productivity = Math.sqrt(count);
            long millis = (long)(productivity * 157);
            
            remainingTime.writeData(remainingTime.getData() - millis);
            
            if(oRan.nextInt(100) < 5){
                Happening.happen(this);
            }
        }
    }
}
