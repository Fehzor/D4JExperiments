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

import sx.blah.discord.gametwo.UserData;

/**
 *
 * @author FF6EB4
 */
public class BurgerKing extends Area{
    
    public BurgerKing(){
        name = "(EVENT) Alone in the Dark";
        crownPayout = 0;
        timeTillCompletion = 60000 * 60 * 24;
        //timeTillCompletion = 0;
    }
    
    public void giveLoot(UserData UD){
        super.giveLoot(UD);
        UD.gear.add("black_kat_cowl");
        UD.gear.add("black_kat_raiment");
    }
}
