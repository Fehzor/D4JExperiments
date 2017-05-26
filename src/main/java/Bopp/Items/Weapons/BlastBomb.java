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
public class BlastBomb extends Gear{
    int time;
    int num;
    int power;
    public static Field<Integer> set = new Field<>("BLASTBOMB","SET",100);
    public BlastBomb(String name, String prereq,int stars, int time, int num, int power){
        super(name,prereq,"",stars);
        this.name = name;
        this.stars = stars;

        super.description = "+Requires "+time+" shared charge to produce "+num+" mats. Increases charge at a rate of "+power+" per message!";
        this.slot = WEAPON;
        //0 Beast,1 Grem,2 Fire,3 Fiend
        //4 Freeze, 5 Slime, 6 Poison, 7 Construct, 8 Shock, 9 Undead
        this.mat1 = 2;
        this.mat2 = 7;
        this.prereq = prereq;
        this.time = time;
        this.num = num;
        this.power = power;
    }
    public void effect(UserData UD, Gate G){
        if(set.data > time){
            for(int i = 0; i < num ; ++i){
                G.rewardMats();
            }
            set.data = 0;
            set.write();
        } else {
            set.data+=power;
            set.write();
        }
    }
}
