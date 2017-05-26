package MOM.CommandCenter.Commands;

import MOM.Data.UserData;
import MOM.Decompilable.Stage;
import MOM.Gates.Gate;
import MOM.Gates.Room;
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
public class Connect extends Command{
    
    public Connect(){
        this.signature = "!connect";
        this.parameters = "<room#> <room#>";
        this.description = "Connect room# to room#- uses Thwackers!";
        this.category = 2;
    }
    
    public void execute(String params,UserData UD){
        String[] split = params.toUpperCase().split(" ",2);
        
        if(UD.thwackers.getData() == 0){
            MoM.PM("You need thwackers for that!",UD);
            return;
        }
        
        if(UD.task.getData().equals("Nothing doing.")){
            try{
                int a,b;
                
                if(split[0].equals("START")){
                    a = Room.START;
                } else if(split[0].equals("END")){
                    a = Room.END;
                } else {
                    a = Integer.parseInt(split[0]);
                }

                if(split[1].equals("START")){
                    b = Room.START;
                } else if(split[1].equals("END")){
                    b = Room.END;
                } else {
                    b = Integer.parseInt(split[1]);
                }
                
                Gate data = MoM.inProgress.getData();
                        
                Room A = data.getRoom(a);
                
                if(!A.connections.contains(b)){
                    UD.setTimer(5);
                    UD.task.writeData("Connecting "+a+" to "+b);
                    MoM.PM("Connecting...",UD);
                } else {
                    MoM.PM("Those rooms are already connected...", UD);
                }
            } catch (Exception E){};
        } else {
            MoM.PM("You want to connect rooms, but you're busy! Try !cancel", UD);
        }
        
        
    }
}
