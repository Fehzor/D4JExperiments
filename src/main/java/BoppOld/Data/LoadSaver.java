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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author FF6EB4
 */
public class LoadSaver {
    private LoadSaver(){}
    
    public static Object load(String name){
        try{
            File F = new File(name);
            FileInputStream FIS = new FileInputStream(F);
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            Object o = OIS.readObject();
            System.out.println("Loading...");
            return o;
            
        } catch (Exception E){
            System.out.println("progress not found... returning null");
            return null;
        }
    }
    
    public static void saveProgress(Object o, String name){
        try{
            File F = new File(name);
            FileOutputStream FOS = new FileOutputStream(F);
            ObjectOutputStream OOS = new ObjectOutputStream(FOS);
            
            OOS.writeObject(o);
            
            
        } catch (Exception E){
            System.out.println("Err saving progress!");
        }
    }
}
