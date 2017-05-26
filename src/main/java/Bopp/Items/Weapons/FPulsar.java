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
public class FPulsar extends Gear {
    int odds;
    public FPulsar(String name, String prereq,int stars, int chance){
        super(name,prereq,"",stars);
        this.name = name;
        this.stars = stars;

        super.description = "+"+chance+"% chance of stealing crowns from others!";
        this.slot = WEAPON;
        //0 Beast,1 Grem,2 Fire,3 Fiend
        //4 Freeze, 5 Slime, 6 Poison, 7 Construct, 8 Shock, 9 Undead
        if(stars == 2){
            this.mat1 = -1;
            this.mat2 = -1;
        } else {
            this.mat1 = 7;
            this.mat2 = 2;
        }
        this.prereq = prereq;
        this.odds = chance;
    }
    public void effect(UserData UD, Gate G){
        if(oRan.nextInt(100) < odds){
            String win = G.userList.data.get(oRan.nextInt(G.userList.data.size()));
            if(UserData.getUser(win).crowns.data < 1000){
                return;
            }
            UserData.getUser(win).crowns.data -= 1000;
            UD.crowns.data += 1000;
        }
    }
}
