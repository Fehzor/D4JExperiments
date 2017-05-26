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

import Bopp.Data.Missions;
import Bopp.Data.UserData;
import Bopp.Gates.Gate;
import static Bopp.Gates.Gate.oRan;
import Bopp.Items.Gear;

/**
 *
 * @author FF6EB4
 */
public class DMGVSX extends Gear{
    int mat;
    
    public DMGVSX(String name, String prereq, int stars, int mat, int mat1, int mat2){
        super(name,prereq,"",stars);
        this.mat = mat;
        this.name = name;
        this.stars = stars;
        this.prereq = prereq;
        this.slot = WEAPON;
        this.mat1 = mat1;
        this.mat2 = mat2;
        super.description = "Get more "+Missions.getTheme(mat)+" materials!";
    }
    
    public void effect(UserData UD, Gate G){
        if(stars == 3){
            UD.materials.data[mat] += 3;
        }
        if(stars == 4){
            UD.materials.data[mat] += 5;
        }
        if(stars == 5){
            UD.materials.data[mat] += 7;
        }
        
        UD.materials.data[mat] += 3;
    }
}
