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
package MOM.Happenings;

import MOM.Data.UserData;
import MOM.Happenings.Instances.*;
import MOM.MoM;
import static MOM.SuperRandom.oRan;
import java.util.ArrayList;

/**
 *
 * @author FF6EB4
 */
public class Happening {
    private static ArrayList<Happening> happenList = new ArrayList<>();
    
    public String flavorText = "Work continues as normal.";
    
    public Happening(){
        happenList.add(this);
    }
    
    public boolean check(UserData UD){
        return true;
    }
    
    public boolean actUpon(UserData UD){
        if(!check(UD)){ //Is the user applicable for this event?
            return false;
        }
        MoM.PM("**EVENT:** "+flavorText,UD);
        return true;
    }
    
    public static void happen(UserData UD){
        if(happenList.size() < 1){
           new ThwackerDeath();
           new KnockerDeath();
           new DemoDeath();
           new MenderBetrayal();
           
           new FindCorpses(); 
           new RogueKnocker();
           new FastWork();
           
           new DeadColony();
           new SupplyBundle();
        }
        
        Happening hap = happenList.get(oRan.nextInt(happenList.size()));
        
        while(!hap.check(UD)){
            hap = happenList.get(oRan.nextInt(happenList.size()));
        }
        
        hap.actUpon(UD);
    }
}
