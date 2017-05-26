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
package sx.blah.discord.gametwo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import sx.blah.discord.handle.obj.IUser;

/**
 *
 * @author FF6EB4
 */
public class UserSpace implements Serializable{
    public HashMap<String, UserData> users = new HashMap<>();
    public int areas = 1;
    public int minerals = 0;
    public Calendar reset;
    
    public static UserData getUser(IUser sender){
        String name = sender.getID();
        if(data.users.get(name) == null){
            UserData add = new UserData();
            data.users.put(name, add);
        }
        return data.users.get(name);
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
