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
public class KnockerDeath extends Happening{
    public KnockerDeath(){
        super();
        this.flavorText = "Your knockers are being eaten by the very monsters they attract! "
                + "(+corpses, -knockers)";
    }
    
    public boolean check(UserData UD){
        return UD.knockers.getData() > 30;
    }
    
    public boolean actUpon(UserData UD){
        if(super.actUpon(UD)){
            
            int num = 5+oRan.nextInt(10);
            UD.knockers.writeData(UD.knockers.getData() - num);
            UD.corpses.writeData(UD.corpses.getData() + num);
            
            return true;
        } else {
            return false;
        }
    }
}
