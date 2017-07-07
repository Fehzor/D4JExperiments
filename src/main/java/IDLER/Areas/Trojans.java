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
package IDLER.Areas;

import static IDLER.SuperRandom.oRan;
import IDLER.UserData;

/**
 *
 * @author FF6EB4
 */
public class Trojans extends Area{
    
    public Trojans(){
        name = "Trojan Graveyard";
        crownPayout = 2500;
        timeTillCompletion = timeTillCompletion * 25;
        //timeTillCompletion = 0;
    }
    
    public void giveLoot(UserData UD){
        super.giveLoot(UD);
        for(int i = 0; i < 6; ++i){
            UD.giveMinerals();
        }
        
        if(oRan.nextInt(100) == 23){
            UD.gear.add("ironmight_plate_shield");
        }
        if(oRan.nextInt(100) == 23){
            UD.gear.add("lost_soul");
        }

        if(oRan.nextInt(100) < 5){
            UD.simple_orbs += 1;
        }
        
        if(oRan.nextInt(100) < 15){
            UD.adv_orbs += 1;
        }
        
        if(oRan.nextInt(100) < 23){
            this.giveLoot(UD);
        }
    }
}
