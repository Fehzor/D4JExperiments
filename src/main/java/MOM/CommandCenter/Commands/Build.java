package MOM.CommandCenter.Commands;

import MOM.Data.UserData;
import MOM.Decompilable.Stage;
import MOM.MoM;
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
public class Build extends Command{
    
    public Build(){
        this.signature = "!add";
        this.parameters = "room";
        this.description = "Construct something! Uses Thwackers! (May have more than room later?)";
        this.category = 2;
    }
    
    public void execute(String params,UserData UD){
        params = params.toLowerCase();
        
        if(UD.thwackers.getData() == 0){
            MoM.PM("You need thwackers for that!",UD);
            return;
        }
        
        if(UD.task.getData().equals("Nothing doing.")){
            if(params.equals("room")){
                UD.setTimer(60);
                UD.task.writeData("Constructing a room.");
                MoM.PM("You start to renovate..", UD);
            }
        } else {
            MoM.PM("You want to build stuff, but you're busy! Try !cancel", UD);
        }
    }
}
