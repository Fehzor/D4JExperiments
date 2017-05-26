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
package BoppOld;

import java.util.Scanner;

/**
 *
 * @author FF6EB4
 */
public class Help {
    public static String message(Scanner oScan){
        String line = "";
        
        try{
            line = oScan.nextLine();
        } catch (Exception E){}
        
        if(line.contains("gear")){
            return gear();
        }
        
        if(line.contains("gates")){
            return gates();
        }
        
        if(line.contains("overview")){
            return overview();
        }
        
        return "Type one of the following after !help for assistance-\n"
                + "gear, gates, or overview";
                
    }
    
    private static String gear(){
        return "Gear will help you in a variety of ways, just as it does in game!\n"
                + "In our chat based variant of Spiral Knights, all gear is viable in some way.\n"
                + "\nCommands for gear are as follows:\n"
                + "\nCraft gear by typing \"!craft <gear name>\""
                + "\nEquip gear by typing \"!equip <Slot = A or B> <gear name>\"";
    }
    
    private static String gates(){
        return "To join a gate, simply chat in it. Talk about whatever you'd like, "
                + "and your immaginary knight will fight for you.\n"
                + "Gates are built from minerals and controlled by disparity.\n"
                + "\nCommands for gates are as follows:\n"
                + "\nDeposit minerals by typing \"!deposit <number> <mineral color>\""
                + "\nInspect a gate by typing \"!area\"";
    }
    
    private static String overview(){
        return "Welcome to the chat based version of Spiral Knights!"
                + " You will control an imaginary knight by typing in commands.\n"
                + "\nBasic commands are as follows:\n"
                + "\nView your stuff with \"!stuff\""
                + "\n(PLANNED) View the leaderboard with \"!leaderboard\""
                + "\n(PLANNED) View the leaderboard with \"!leaderboard\"";
    }
}
