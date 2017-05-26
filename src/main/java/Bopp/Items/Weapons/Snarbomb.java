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
public class Snarbomb extends Gear{
    double percent;
    public Snarbomb(String name, String prereq,int stars, double percent){
        super(name,prereq,"",stars);
        this.name = name;
        this.stars = stars;

        super.description = "Steal "+(int)(percent*100)+"% of two random minerals from the gate!";
        this.slot = WEAPON;
        //0 Beast,1 Grem,2 Fire,3 Fiend
        //4 Freeze, 5 Slime, 6 Poison, 7 Construct, 8 Shock, 9 Undead
        this.mat1 = 0;
        this.mat2 = 2;
        this.prereq = prereq;
        this.percent = percent;
    }
    public void effect(UserData UD, Gate G){
        int[] ret = new int[2];
        
        int totalMinerals = 0;
        for(int a : G.minerals.data){
            totalMinerals += a;
        }
        
        if(totalMinerals>=10){
            int select = oRan.nextInt(totalMinerals);
            int result = 0;
            for(int a : G.minerals.data){
                select = select - a;
                if(select > 0){
                    result++;
                }
            }

            ret[0] = result;
            
            int trials = 100;
            while(ret[0] == result && trials > 0){
                trials -= 1;
                select = oRan.nextInt(totalMinerals);
                result = 0;
                for(int a : G.minerals.data){
                    select = select - a;
                    if(select > 0){
                        result++;
                    }
                }
            }
            if(ret[0] == result){
                result = oRan.nextInt(5);
                while(ret[0] == result){
                    result = oRan.nextInt(5);
                }
            }

            ret[1] = result;
        } else {
            return;
        }
        
        if(ret[0] > ret[1]){
            int temp = ret[0];
            ret[0] = ret[1];
            ret[1] = temp;
        }
        
        
        
        int DIFFA =(int)Math.floor(G.minerals.data[ret[0]] * percent);
        int DIFFB =(int)Math.floor(G.minerals.data[ret[1]] * percent);
        
        UD.minerals.data[ret[0]] += DIFFA;
        UD.minerals.data[ret[1]] += DIFFB;
        
        G.minerals.data[ret[0]] = (int)Math.floor(G.minerals.data[ret[0]] * (1-percent));
        G.minerals.data[ret[1]] = (int)Math.floor(G.minerals.data[ret[1]] * (1-percent));
    }
}
