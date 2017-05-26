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
public class SwitchShotStatus extends Gear{
    int min1,min2;
   public SwitchShotStatus(String name, String prereq,int stars, int min1, int min2, int mat1, int mat2){
        super(name,prereq,"",stars);
        this.name = name;
        this.stars = stars;

        super.description = "+"+stars+" of 2 certain minerals; Switches other weapons out randomly!";
        this.slot = WEAPON;
        //0 Beast,1 Grem,2 Fire,3 Fiend
        //4 Freeze, 5 Slime, 6 Poison, 7 Construct, 8 Shock, 9 Undead
        this.mat1 = mat1;
        this.mat2 = mat2;
        this.prereq = prereq;
        this.min1 = min1;
        this.min2 = min2;
    }
    public void effect(UserData UD, Gate G){
            UD.minerals.data[min1] += stars;
            UD.minerals.data[min2] += stars;
        
            int slot = 0;
            if(UD.weaponA.data.equals(this.name)){
                slot = 1;
            }
            if(UD.weaponB.data.equals(this.name)){
                slot = 2;
            }
            if(UD.weaponC.data.equals(this.name)){
                slot = 3;
            }
            
            int choice = oRan.nextInt(2);
            if(slot == 1) choice += 2;//Choice is now 2 or 3
            if(slot == 2 && choice == 0) choice = 3; //choice is now 1 or 3
            if(slot == 3) choice += 1; //Choice is now 1 or 2
            
            int nextwepNum = oRan.nextInt(UD.gear.data.size());
           
            String nextwep = UD.gear.data.get(nextwepNum);
            if(nextwep.equals(this.name)){
                return;
            }
            
            if(choice == 1){
                UD.weaponA.data = nextwep;
            }
            if(choice == 2){
                UD.weaponB.data = nextwep;
            }
            if(choice == 3){
                UD.weaponC.data = nextwep;
            }
    }
}
