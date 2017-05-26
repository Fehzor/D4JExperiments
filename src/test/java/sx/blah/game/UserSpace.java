package sx.blah.game;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import sx.blah.discord.handle.obj.IUser;

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

/**
 *
 * @author FF6EB4
 */
public class UserSpace implements Serializable{
    public HashMap<String,Crate> boxes;
    public HashMap<String,ArrayList<String>> stuff;
    public HashMap<String,Integer> coins;
    public HashMap<String,Long> lastTime;
    public int areas = 0;
    public int minerals = 0;
    
    private UserSpace(){
        boxes = new HashMap<>();
        stuff = new HashMap<>();
        coins = new HashMap<>();
        lastTime = new HashMap<>();
        areas = 0;
        minerals = 0;
    }
    
    public void giveCoin(IUser sender){
        if(lastTime.get(sender.getName()) == null){
            lastTime.put(sender.getName(),(long)0);
            coins.put(sender.getName(), 0);
        }
        long time = System.currentTimeMillis() - lastTime.get(sender.getName());
        if(time > 20000){
            coins.put(sender.getName(), coins.get(sender.getName())+1);
            lastTime.put(sender.getName(),System.currentTimeMillis());
        }
    }
    

    
    public int getCoin(IUser sender){
        if(coins.get(sender.getName()) == null){
            lastTime.put(sender.getName(),(long)0);
            coins.put(sender.getName(), 0);
        }
        
        return coins.get(sender.getName());
    }
    
    public void giveBox(IUser user){
        chargeCoin(user,25);
        boxes.put(user.getName(),new Crate());
    }
    
    public Crate checkBox(IUser sender){
        if(boxes.get(sender.getName()) == null)return null;
        return boxes.get(sender.getName());
    }
    
    public void chargeCoin(IUser sender, int amt){
        if(lastTime.get(sender.getName()) == null){
            lastTime.put(sender.getName(),(long)0);
            coins.put(sender.getName(), 0);
        }
        coins.put(sender.getName(), coins.get(sender.getName())-amt);
        lastTime.put(sender.getName(),System.currentTimeMillis());
    }
    
    public void grant(IUser user){
        String s = boxes.get(user.getName()).loot;
        if(stuff.get(user.getName()) == null){
            stuff.put(user.getName(),new ArrayList<>());
        }
        stuff.get(user.getName()).add(s);
        boxes.put(user.getName(), null);
    }
    
    public String checkStuff(IUser user){
        String s = ""+stuff.get(user.getName());
        return s;
    }
    

    
    //SAVING AND LOADING
    
    public static UserSpace data = loadProgress();
    
    public static UserSpace loadProgress(){
        try{
            File F = new File("data");
            FileInputStream FIS = new FileInputStream(F);
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            
            Object o = (OIS.readObject());
            UserSpace P = (UserSpace) o;
            UserSpace.data = P;
            
            System.out.println("Loading success!");
            
            return P;
            
        } catch (Exception E){
            System.out.println("progress not found... starting anew");
            UserSpace P = new UserSpace();
            return P;
        }
    }
    
    public static void saveProgress(){
        try{
            File F = new File("data");
            FileOutputStream FOS = new FileOutputStream(F);
            ObjectOutputStream OOS = new ObjectOutputStream(FOS);
            
            OOS.writeObject(data);
            
            
        } catch (Exception E){
            System.out.println("Err saving progress");
        }    
    }
}
