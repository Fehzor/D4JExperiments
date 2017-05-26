package MOM.CommandCenter.Commands;

import Bopp.Webhandler;
import MOM.CommandCenter.CommandParser;
import MOM.Data.UserData;
import MOM.Decompilable.Stage;
import MOM.MoM;
import static MOM.SuperRandom.oRan;
import java.util.ArrayList;
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
public class Dank extends Command{
    
    public Dank(){
        this.category = 0;
        this.signature = "!dank";
        this.description = "Only usable in Meme Bazaar- displays a dank meme from reddit.";
    }
    
    public void execute(String params,UserData UD){
        if( CommandParser.channel.getName().equals("the_meme_bazaar") ){
                
            ArrayList<String> memes = Webhandler.getDankMemes();
            if(memes == null || memes.size() == 0){
                MoM.send("Unable to find dank memes!",CommandParser.channel);
            } else {
                String meme = memes.get(oRan.nextInt(memes.size()));
                MoM.send(meme,CommandParser.channel);
            }
        } else {
            MoM.PM("You can only use !dank in the meme bazaar!",UD);
        }
    }
}
