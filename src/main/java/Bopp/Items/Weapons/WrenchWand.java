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
import static Bopp.Data.Missions.FIFTEEN;
import Bopp.Data.Unboxing;
import Bopp.Data.UserData;
import Bopp.Gates.Gate;
import static Bopp.Gates.Gate.oRan;
import Bopp.Items.Gear;

/**
 *
 * @author FF6EB4
 */
public class WrenchWand extends Gear{
    public static Field<Long> time = new Field<>("WRENCH","TIME",(long)0);
    public static Field<Integer> slimes = new Field<>("WRENCH","SLIMES",0);
    public WrenchWand(){
        super("Wrench Wand","HIDDEN OBJECT","",5);
        this.name = name;
        this.stars = stars;

        super.description = "";
        this.slot = WEAPON;
        //0 Beast,1 Grem,2 Fire,3 Fiend
        //4 Freeze, 5 Slime, 6 Poison, 7 Construct, 8 Shock, 9 Undead
        
        this.mat1 = -1;
        this.mat2 = -1;
        
        this.prereq = "HIDDEN OBJECT";
    }
    public void effect(UserData UD, Gate G){
        
        if(oRan.nextInt(1+slimes.data) == 0 && slimes.data < 300){
            slimes.data++;
            time.write();
            slimes.write();
            G.send("Your wrench wand summoned an impostocube?! Type !kill to MURDER THE POOR HELPLESS BEING.");
        } else {
            long current = System.currentTimeMillis();
            if(current - time.data < 1000*60*6){
                return;
            } else {
                time.data = current;
            }
            if(oRan.nextInt(1000) < slimes.data){
                G.send("The impostocubes have gifted you a free time based slime box!");
                if(Unboxing.starts.data.containsKey(UD.ID)){
                    time.write();
                    slimes.write();
                    G.send("Except you can't accept it! You're already opening a box!");
                    return;
                }
                time.write();
                slimes.write();
                Unboxing.starts.data.put(UD.ID,System.currentTimeMillis() + (long)1000 * 60 * 60 * 3);
                Unboxing.starts.write();
            }
            
            if(slimes.data == 1){
                G.send("The impostocube is making friendly chit chat.");
                return;
            }
            if(slimes.data == 2){
                G.send("The impostocubes are playing a friendly game of chess.");
                return;
            }
            if(slimes.data == 3){
                G.send("Three is a crowd... even for such kindly gentlecubes.");
                return;
            }
            if(slimes.data < 10){
                G.send("The impostocubes are smoking their pipes merrily.");
                return;
            }
            if(slimes.data < 20){
                G.send("The impostocubes appear to be throwing a house party!");
                return;
            }
            if(slimes.data < 30){
                G.send("The impostocubes are forming soccer teams! They're playing a game!");
                return;
            }
            if(slimes.data < 40){
                G.send("There appears to be an audience forming for the slime teams! SCORE!");
                return;
            }
            if(slimes.data < 60){
                G.send("The colony appears to have moved on to more important issues. What is the meaning of their existence?");
                return;
            }
            if(slimes.data < 100){
                G.send("The impostocubes are starting their own government? They're very left thinkers.");
                return;
            }
            if(slimes.data < 150){
                G.send("The impostocube government has legalized marijuana! Dapper combos and toupees for all!");
                return;
            }
            if(slimes.data < 200){
                G.send("The impostocube government supports gay marriage! Because there's only one slime gender!");
                return;
            }
            if(slimes.data < 300){
                G.send("The impostocube nation is almost complete.");
                return;
            }
            
            G.send("Impostocube society celebrates you as its founder. Slime progress: 100%");
            return;
        }
    }
    
    public String toString(){
        return "null";
    }
}
