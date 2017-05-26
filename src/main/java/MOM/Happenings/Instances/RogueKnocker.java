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

import MOM.Data.UserData;
import MOM.Happenings.Happening;
import static MOM.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class RogueKnocker extends Happening{
    public RogueKnocker(){
        super();
        this.flavorText = "A group of rogue knockers left over from a fight are found cowering, forgotten."
                + "(+knockers)";
    }
    
    public boolean check(UserData UD){
        return true;
    }
    
    public boolean actUpon(UserData UD){
        if(super.actUpon(UD)){
            int totalSubs = UD.knockers.getData()+UD.menders.getData();
            int sqrt= (int)Math.sqrt(totalSubs);
            int num = 2+oRan.nextInt(sqrt);
            
            UD.knockers.writeData(UD.knockers.getData() + num);
            
            return true;
        } else {
            return false;
        }
    }
}
