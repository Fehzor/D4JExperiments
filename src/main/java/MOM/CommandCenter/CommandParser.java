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
package MOM.CommandCenter;

import MOM.Data.UserData;
import MOM.CommandCenter.Commands.*;
import java.util.ArrayList;
import java.util.HashMap;
import sx.blah.discord.handle.obj.IChannel;

/**
 *
 * @author FF6EB4
 */
public class CommandParser {
    public static HashMap<String,Command> commandList = loadCommands();
    public static IChannel channel;
    
    public static void parseCommand(String S, UserData UD){
        String[] split = S.split(" ",2);
        String signature = split[0];
        
        String args = "";
        try{
            args = split[1];
        } catch (Exception E){}
        
        if(commandList.containsKey(signature)){
            commandList.get(signature).execute(args,UD);
        }
    }
    
    private static HashMap<String,Command> loadCommands(){
        HashMap<String,Command> ret = new HashMap<>();
        
        Command commands = new Commands();
        Command help = new Help();
        Command me = new Command();
        Command decon = new Decompile();
        Command cancel = new Cancel();
        Command stuff = new Stuff();
        Command map = new GetMap();
        Command build = new Build();
        Command connect = new Connect();
        Command disconnect = new Disconnect();
        Command carry = new Carry();
        Command rez = new Rez();
        Command tasks = new Tasks();
        Command dank = new Dank();
        Command active = new GetActiveMap();
        
        ret.put(active.signature,active);
        ret.put(dank.signature,dank);
        ret.put(tasks.signature,tasks);
        ret.put(rez.signature,rez);
        ret.put(commands.signature,commands);
        ret.put(help.signature,help);
        ret.put(carry.signature,carry);
        ret.put(connect.signature,connect);
        ret.put(disconnect.signature,disconnect);
        ret.put(build.signature,build);
        ret.put(me.signature,me);
        ret.put(decon.signature,decon);
        ret.put(cancel.signature,cancel);
        ret.put(stuff.signature,stuff);
        ret.put(map.signature,map);
        
        return ret;
    }
}
