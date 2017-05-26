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
public class Snarb extends Gear{
    int thresh, multi;
    public Snarb(String name, String prereq,int stars, int thresh, int multi){
        super(name,prereq,"",stars);
        this.name = name;
        this.stars = stars;

        super.description = "x"+multi+" crowns when under "+thresh+" crowns!";
        this.slot = WEAPON;
        //0 Beast,1 Grem,2 Fire,3 Fiend
        //4 Freeze, 5 Slime, 6 Poison, 7 Construct, 8 Shock, 9 Undead
        this.mat1 = 0;
        this.mat2 = 2;
        this.prereq = prereq;
        this.thresh = thresh;
        this.multi = multi;
    }
    public void effect(UserData UD, Gate G){
        if(UD.crowns.data < thresh){
            UD.crowns.data += (multi-1)*(100 + oRan.nextInt(1000));
        }
    }
}
