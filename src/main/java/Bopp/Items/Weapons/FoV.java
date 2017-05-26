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
public class FoV extends Gear{
    public FoV(String name, String prereq){
        super(name,prereq,"",5);
        this.name = name;
        this.stars = stars;

        super.description = "+5% chance to upgrade an orb!";
        this.slot = WEAPON;
        //0 Beast,1 Grem,2 Fire,3 Fiend
        //4 Freeze, 5 Slime, 6 Poison, 7 Construct, 8 Shock, 9 Undead
        this.mat1 = -1;
        this.mat2 = -1;
        this.prereq = prereq;
    }
    public void effect(UserData UD, Gate G){
        if(oRan.nextInt(100) < 5){
            int orb = oRan.nextInt(3);
            if(UD.orbs.data[orb] < 1){
                return;
            }
            UD.orbs.data[orb] -= 1;
            UD.orbs.data[orb+1] += 1;
        }
    }
}
