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

import BoppOld.Data.UserData;
import BoppOld.Items.Material;
import java.util.ArrayList;

/**
 *
 * @author FF6EB4
 */
public class Gear {
    public static final int CRAFTING_COST_LOW = 10;
    public static final int CRAFTING_COST_MED = 50;
    public static final int CRAFTING_COST_HIGH = 250;
    
    public static final int TYPE_NONE = 0;
    public static final int TYPE_SINGLE = 1;
    public static final int TYPE_CROWD = 2;
    public static final int TYPE_HEAVY_CROWD = 3;
    public static final int TYPE_UTILITY = 4;
    
    public Gear(){}
    
    public String prereq = "";
    
    public String name = "";
    
    public String where1 = "";
    public String where2 = "";
    public String where3 = "";
    
    public String mat1 = ""; //10 of this
    public String mat2 = ""; //50 of this
    public String mat3 = ""; //250 of this
    
    public int stars = 0;
    
    public int type = 0;
    
    private boolean check(UserData UD){
        if(!prereq.equals("")){
            if(!UD.gear.contains(prereq)){
                return false;
            }
        }
        
        if(!mat1.equals("")){
            if(UD.materials.get(mat1) == null){
                return false;
            } else {
                if(UD.materials.get(mat1) < CRAFTING_COST_LOW){
                    return false;
                }
            }
        }
        if(!mat2.equals("")){
            if(UD.materials.get(mat2) == null){
                return false;
            } else {
                if(UD.materials.get(mat2) < CRAFTING_COST_MED){
                    return false;
                }
            }
        }
        if(!mat3.equals("")){
            if(UD.materials.get(mat3) == null){
                return false;
            } else {
                if(UD.materials.get(mat3) < CRAFTING_COST_HIGH){
                    return false;
                }
            }
        }
        
        return true;
    }
    //Removes the materials for this weapon from UD
    public String subtract(UserData UD){
        if(check(UD)){
            if(!this.prereq.equals("")){
                UD.gear.remove(this.prereq);
            }
            if(!mat1.equals("")){
                UD.materials.put(mat1,UD.materials.get(mat1)-CRAFTING_COST_LOW);
            }
            if(!mat2.equals("")){
                UD.materials.put(mat2,UD.materials.get(mat2)-CRAFTING_COST_MED);
            }
            if(!mat3.equals("")){
                UD.materials.put(mat3,UD.materials.get(mat3)-CRAFTING_COST_HIGH);
            }
            
            UD.gear.add(this.name);
            
            return "Crafting complete!";
        }
        
        return craftCosts();
    }

    private String craftCosts(){
        String s = "";
        if(!mat3.equals("")){
            s = "To craft that you'll need "+CRAFTING_COST_LOW+" "+mat1+", "+
                    CRAFTING_COST_MED+" "+mat2+", and "+CRAFTING_COST_HIGH+
                    " "+mat3+".";
        } else if(!mat2.equals("")){
            s = "To craft that you'll need "+CRAFTING_COST_LOW+" "+mat1+" and "+
                    CRAFTING_COST_MED+" "+mat2+".";
        } else if(!mat1.equals("")){
            s = "To craft that you'll need "+CRAFTING_COST_LOW+" "+mat1+".";
        }
        return s;
    }
}
