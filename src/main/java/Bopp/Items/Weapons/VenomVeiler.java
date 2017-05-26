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

import Bopp.Data.UserData;
import Bopp.Gates.Gate;
import static Bopp.Gates.Gate.oRan;
import Bopp.Items.Gear;

/**
 *
 * @author FF6EB4
 */
public class VenomVeiler extends Gear{
    public VenomVeiler(String name, String prereq,int stars){
        super(name,prereq,"",stars);
        this.name = name;
        this.stars = stars;

        super.description = "+Access the !poison command! (!help poison); +"+(stars-1)+" slime coins per message for all in exchange for 7 poison mats in T1 only";
        this.slot = WEAPON;
        //0 Beast,1 Grem,2 Fire,3 Fiend
        //4 Freeze, 5 Slime, 6 Poison, 7 Construct, 8 Shock, 9 Undead
        this.mat1 = 6;
        this.mat2 = 9;
        this.prereq = prereq;
    }
    public void effect(UserData UD, Gate G){
        if(G.tier == 1){
            if(UD.materials.data[6] < 7){
                return;
            }
            for(String s : G.userList.data){
                UserData giv = UserData.getUser(s);
                giv.slimeCoins.data += stars-1;
            }
            UD.materials.data[6] -= 7;
        }
    }
}
