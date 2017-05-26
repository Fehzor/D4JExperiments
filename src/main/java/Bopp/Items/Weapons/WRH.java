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
package Bopp.Items.Weapons;

import Bopp.Data.Field;
import Bopp.Data.UserData;
import Bopp.Gates.Gate;
import static Bopp.Gates.Gate.oRan;
import Bopp.Items.Gear;

/**
 *
 * @author FF6EB4
 */
public class WRH extends Gear{

    public WRH(String name, String prereq){
        super(name,prereq,"",5);
        this.name = name;
        this.stars = stars;

        super.description = "+Generate+Autodeposit 15 red/blue per message; +7 slime coins for all out of 130 random gate minerals.";
        this.slot = WEAPON;
        //0 Beast,1 Grem,2 Fire,3 Fiend
        //4 Freeze, 5 Slime, 6 Poison, 7 Construct, 8 Shock, 9 Undead
        this.mat1 = -1;
        this.mat2 = -1;
        this.prereq = prereq;
    }
    public void effect(UserData UD, Gate G){
        G.minerals.data[0]+=15;
        G.minerals.data[2]+=15;
        
        int which = oRan.nextInt(5);
        if(G.minerals.data[which] < 130){
            return;
        }
        G.minerals.data[which] -= 130;
        
        for(String ID : G.userList.data){
            UserData giv = UserData.getUser(ID);
            giv.slimeCoins.data += 10;
        }
    }
}
