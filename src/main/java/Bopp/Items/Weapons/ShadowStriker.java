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
public class ShadowStriker extends Gear {
    public ShadowStriker(String name, String prereq,int stars){
        super(name,prereq,"",stars);
        this.name = name;
        this.stars = stars;

        super.description = "+Randomly use another equipped item twice!";
        this.slot = WEAPON;
        //0 Beast,1 Grem,2 Fire,3 Fiend
        //4 Freeze, 5 Slime, 6 Poison, 7 Construct, 8 Shock, 9 Undead
        mat1 = -1;
        mat2 = -1;
        this.prereq = prereq;
    }
    public void effect(UserData UD, Gate G){
            int slot = 0;
            if(UD.weaponA.data.equals("Void Striker")){
                slot = 1;
            }
            if(UD.weaponB.data.equals("Void Striker")){
                slot = 2;
            }
            if(UD.weaponC.data.equals("Void Striker")){
                slot = 3;
            }
            
            int choice = oRan.nextInt(2);
            if(slot == 0) choice += oRan.nextInt(3)+1;
            if(slot == 1) choice += 2;//Choice is now 2 or 3
            if(slot == 2 && choice == 0) choice = 3; //choice is now 1 or 3
            if(slot == 3) choice += 1; //Choice is now 1 or 2
            
            if(choice == 1){
                Gear get = Gear.gearMap.get(UD.weaponA.data);
                get.effect(UD, G);
                get.effect(UD, G);
            }
            if(choice == 2){
                Gear get = Gear.gearMap.get(UD.weaponB.data);
                get.effect(UD, G);
                get.effect(UD, G);
            }
            if(choice == 3){
                Gear get = Gear.gearMap.get(UD.weaponC.data);
                get.effect(UD, G);
                get.effect(UD, G);
            }
    }
}
