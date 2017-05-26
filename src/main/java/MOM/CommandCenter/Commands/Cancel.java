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
public class Cancel extends Command{
    
    public Cancel(){
        this.category = 0;
        this.signature = "!cancel";
        this.description = "Cancel current action.";
    }
    
    public void execute(String params,UserData UD){
        params = WordUtils.capitalizeFully(params);
        Stage deconstr = new Stage(params);
        params = deconstr.name;
        
        if(UD.task.getData().equals("Nothing doing.")){
            MoM.PM("You stop doing nothing and in doing so, continue to do nothing?", UD);
        } else {
            UD.task.writeData("Nothing doing.");
            UD.setTimer(0);
            MoM.PM("You stop whatever you're doing..",UD);
        }
    }
}
