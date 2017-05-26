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
package sx.blah.discord.gametwo.Areas;

import static sx.blah.discord.gametwo.SuperRandom.oRan;
import sx.blah.discord.gametwo.UserData;

/**
 *
 * @author FF6EB4
 */
public class Roarm extends Area{
    
    public Roarm(){
        name = "Roarmulus Twins";
        crownPayout = 2500;
        timeTillCompletion = timeTillCompletion * 25;
        //timeTillCompletion = 0;
    }
    
    public void giveLoot(UserData UD){
        super.giveLoot(UD);
        
        if(oRan.nextInt(100) < 10){
            UD.elite_orbs += 1;
        }
        if(oRan.nextInt(100) < 3){
            UD.adv_orbs += 1;
        }
        if(oRan.nextInt(100) < 23){
            this.giveLoot(UD);
        } else {
            UD.bark_mods+=3;
        }
    }
}