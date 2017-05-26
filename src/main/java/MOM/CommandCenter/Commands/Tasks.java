package MOM.CommandCenter.Commands;

import MOM.Data.Collective;
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
public class Tasks extends Command{
    
    public Tasks(){
        this.signature = "!tasks";
        this.description = "Display what everyone is doing!";
        this.category = 1;
    }
    
    public void execute(String params,UserData UD){
        String ret = "**Here's what other people are currently working on:**\n\n";
        
        for(String ID : UserData.IDList.getData()){
            UserData user = UserData.getUD(ID);
            if(user != null && !user.task.getData().equals("Nothing doing.")){
                if(!user.checkTimer()){
                    String name = user.name;
                    String task = user.task.getData();
                    int minutes = user.getTimeRemaining();
                    ret+= "**"+name+":** "+task+"\n*Time remaining: "+minutes+" minutes*\n";
                }
            }
        }
        
        ret+="\n->*Note: This does not include tasks that are completed but not received.*";
        
        MoM.PM(ret,UD);
    }
}
