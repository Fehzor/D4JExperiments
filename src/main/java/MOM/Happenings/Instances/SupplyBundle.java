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
package MOM.Happenings.Instances;

import MOM.Data.Collective;
import MOM.Data.UserData;
import MOM.Happenings.Happening;
import static MOM.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class SupplyBundle extends Happening{
    public SupplyBundle(){
        super();
        this.flavorText = "As you deconstruct the abandoned level, it becomes apparent that someone else has been here working..."
                + "(+construction material)";
    }
    
    public boolean check(UserData UD){
        String s = UD.task.getData();
        String[] splt = s.split(" ",2);
        return splt[0].equals("Deconstructing");
    }
    
    public boolean actUpon(UserData UD){
        if(super.actUpon(UD)){
            Collective.constructionMaterial.writeData(Collective.constructionMaterial.getData()+230);
            
            return true;
        } else {
            return false;
        }
    }
}
