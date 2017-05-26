package MOM.CommandCenter.Commands;

import MOM.Data.UserData;
import MOM.MoM;

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
public class Command {
    public String signature;
    public String parameters = "";
    public String description = "No Description";
    public int category = 0;
    public Command(){
        this.signature = "!me";
        this.description  = "Displays your personal resources";
        this.category = 1;
    }
    
    public void execute(String params,UserData UD){
        MoM.PM(UD+"", UD);
    }
    
    public String toString(){
        return "**Command: "+signature+"** "+parameters+"\n*Description: "+description+"*";
    }
}
