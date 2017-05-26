package MOM.CommandCenter.Commands;

import MOM.Data.UserData;
import MOM.Decompilable.Stage;
import MOM.Gates.Room;
import MOM.MoM;
import static MOM.MoM.inProgress;
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
public class Carry extends Command{
    
    public static final int COMPLETION_TIME = 45;
    
    public Carry(){
        this.signature = "!carry";
        this.parameters = "<Color> <Room#>";
        this.description = "Move 100 color minerals to room#; uses knockers";
        this.category = 2;
    }
    
    public void execute(String params,UserData UD){
        System.out.println(params);
        
        if(UD.knockers.getData() == 0){
            MoM.PM("You need knockers for that!",UD);
            return;
        }
        
        try{
            params = params.toLowerCase();
            String[] split = params.split(" ",2);
            
            int room = Integer.parseInt(split[1]);
            Room R = MoM.inProgress.getData().getRoom(room);
            
            if(R == null){
                throw new Exception();
            }

            if(UD.task.getData().equals("Nothing doing.")){
                if(split[0].equals("red")){
                    UD.setTimer(COMPLETION_TIME);
                    UD.task.writeData("Carrying red to room "+split[1]);
                    MoM.PM("The knockers begin setting up minerals in the room...", UD);
                    return;
                }
                if(split[0].equals("green")){
                    UD.setTimer(COMPLETION_TIME);
                    UD.task.writeData("Carrying green to room "+split[1]);
                    MoM.PM("The knockers begin setting up minerals in the room...", UD);
                    return;
                }
                if(split[0].equals("blue")){
                    UD.setTimer(COMPLETION_TIME);
                    UD.task.writeData("Carrying blue to room "+split[1]);
                    MoM.PM("The knockers begin setting up minerals in the room...", UD);
                    return;
                }
                if(split[0].equals("yellow")){
                    UD.setTimer(COMPLETION_TIME);
                    UD.task.writeData("Carrying yellow to room "+split[1]);
                    MoM.PM("The knockers begin setting up minerals in the room...", UD);
                    return;
                }
                if(split[0].equals("purple")){
                    UD.setTimer(COMPLETION_TIME);
                    UD.task.writeData("Carrying purple to room "+split[1]);
                    MoM.PM("The knockers begin setting up minerals in the room...", UD);
                    return;
                }
            } else {
                MoM.PM("You want to carry stuff, but you're busy! Try !cancel", UD);
            }
        } catch (Exception E){
            MoM.PM("I didn't quite catch that.", UD);
        }
    }
}
