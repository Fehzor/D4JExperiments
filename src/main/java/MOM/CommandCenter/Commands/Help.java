package MOM.CommandCenter.Commands;

import MOM.CommandCenter.CommandParser;
import MOM.Data.UserData;
import MOM.Decompilable.Stage;
import MOM.MoM;
import java.util.List;
import org.apache.commons.lang3.text.WordUtils;

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

/**
 *
 * @author FF6EB4
 */
public class Help extends Command{
    
    public Help(){
        this.category = 0;
        this.signature = "!help";
        this.description = "Displays the help file!";
    }
    
    public void execute(String params,UserData UD){
        String message = "**HELP FILE**\n";
        
        if(UD.thwackers.getData() != 0 || UD.knockers.getData() != 0){
            message += "Now that you have knockers, thwackers or both, you can add onto the level. "
                    + "First and foremost, the level is a graph that can be viewed by typing !map. "
                    + "It consists of rooms, a start and an end, all of which are connected."
                    + "\n\n"
                    + "If you have Thwackers under your command, you can perform the following-\n"
                    + "!connect numberOne numberTwo = connect room number one to room number two.\n"
                    + "!disconnect numberOne numberTwo = disconnect room number one from room number two\n"
                    + "!add room = add a room to the mix.\n"
                    + "Keep in mind that there MUST be a path from the START to the END, and that to connect/disconnect START"
                    + "and END you can specify them by those names."
                    + "\n\n"
                    + "If you have Knockers under your command, you can perform the following command-\n"
                    + "!carry color roomNumber = carry red/blue/green/yellow/purple to the specified room.\n"
                    + "Remember the old gate construction mechanics? Well they're back and better than ever! Rooms randomly"
                    + "choose sets of enemies for knights to fight based on what you put in!\n\n"
                    + ""
                    + "Lastly, for a full list of commands, type !commands.";
        } else {
            if(UD.corpses.getData() == 0){
                message+="Hello and welcome! In this variant of Bopp you'll play as a gremlin schemer "
                        + "building a single clockwork level as part of a collective effort with other schemers! "
                        + "\n\n"
                        + "To do this, you'll first need to deconstruct some levels. Type the following in chat:\n"
                        + "!deconstruct\n\n"
                        + ""
                        + "This will tell your demos (and any other grmelins you command) to begin deconstructing a compounds level. "
                        + "Once complete, you'll receive corpses for yourself and building materials for everyone. Chatting "
                        + "here will speed up your progress based on the number of gremlins you command- the more the merrier!"
                        + "\n\n"
                        + "Once complete, check back here using !help";
            } else {
                message += "Great work! Type !me to see your stuff and !stuff to see the community's collective resources."
                        + "\n\n"
                        + "Each gremlin does something different-\n"
                        + "Demos = !deconstruct levels\n"
                        + "Thwackers = build rooms and tunnels\n"
                        + "Knockers = carry minerals to rooms, which draw in monsters and fuel traps\n"
                        + "Menders = revive corpses into demos, thwackers, knockers or menders.\n\n"
                        + ""
                        + "From here, you can either deconstruct more levels or revive your corpses into one of the above. "
                        + "To revive all corpses using your mender, type !revive demo/thwacker/knocker/mender into chat.";
            }
        }
        
        MoM.PM(message,UD);
    }
}
