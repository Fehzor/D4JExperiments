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
public class Rez extends Command{
    
    public Rez(){
        this.signature = "!revive";
        this.parameters = "<gremlin type>";
        this.description = "Convert all corpses to gremlin type (thwacker, demo, etc.)! Uses Menders!";
        this.category = 2;
    }
    
    public void execute(String params,UserData UD){
        params = params.toLowerCase();
        
        if(UD.corpses.getData() == 0){
            MoM.PM("You need corpses for that!",UD);
            return;
        }
        
        if(UD.task.getData().equals("Nothing doing.")){
            if(params.equals("thwacker")){
                UD.setTimer(UD.corpses.getData() * 15);
                UD.task.writeData("Resurrecting thwackers");
                MoM.PM("The menders start to resurrect thwackers..", UD);
                return;
            }
            if(params.equals("demo")){
                UD.setTimer(UD.corpses.getData() * 15);
                UD.task.writeData("Resurrecting demos");
                MoM.PM("The menders start to resurrect demos..", UD);
                return;
            }
            if(params.equals("knocker")){
                UD.setTimer(UD.corpses.getData() * 15);
                UD.task.writeData("Resurrecting knockers");
                MoM.PM("The menders start to resurrect knockers..", UD);
                return;
            }
            if(params.equals("mender")){
                UD.setTimer(UD.corpses.getData() * 15);
                UD.task.writeData("Resurrecting menders");
                MoM.PM("The menders start to resurrect menders..", UD);
                return;
            }
            MoM.PM("I didn't quite catch that...",UD);
        } else {
            MoM.PM("You want to resurrect, but you're busy! Try !cancel", UD);
        }
    }
}
