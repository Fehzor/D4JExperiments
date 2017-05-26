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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author FF6EB4
 */
public class Field<Type> implements Serializable {
    String ID;
    String name;
    public Type data;
    
    public Field(String ID,String name, Type def){
        this.name = name;
        this.ID = ID;
        Field copy = load(ID,name);
        if(copy != null){
            try{
                this.data = (Type)copy.data;
            } catch (Exception E){
                this.data = def;
                System.out.println("Bad data loading "+name+"!  "+E);
            }
        } else {
            this.data = def;
        }
    }
    
    public static Field load(String ID,String name){
        try{
            File F = new File(ID+"_"+name);
            FileInputStream FIS = new FileInputStream(F);
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            Object o = OIS.readObject();
            System.out.println("Loading..."+ID+"_"+name);
            FIS.close();
            return (Field)o;
            
            
            
        } catch (Exception E){
            System.out.println("Failed to find "+ID+"_"+name);
            return null;
        }
    }
    
    public void write(){
        try{
            File F = new File(ID+"_"+name);
            FileOutputStream FOS = new FileOutputStream(F);
            ObjectOutputStream OOS = new ObjectOutputStream(FOS);
            
            OOS.writeObject(this);
            
            OOS.close();
        } catch (Exception E){
            System.out.println("Err saving field "+name+"!  "+E);
        }
    }
}
