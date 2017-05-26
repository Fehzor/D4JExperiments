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
package sx.blah.discord.gametwo.Areas;

import java.util.ArrayList;
import java.util.HashMap;
import sx.blah.discord.examples.BoppBot;
import sx.blah.game.UserSpace;
import sx.blah.discord.gametwo.UserData;
import static sx.blah.discord.gametwo.SuperRandom.oRan;


/**
 *
 * @author FF6EB4
 */
public class Area {
    public static HashMap<String,Area> areas = loadAreas();
    public static ArrayList<String> areaNames;
    public static int areaNumber = 9;
    
    public String name = "Wherever";
    public int crownPayout = 1000;
    public long timeTillCompletion = 60000; //ONE MINUTE
    public int minerals = 1000;
    
    
    public void giveLoot(UserData UD){
        UD.giveMinerals();
        UD.crowns += crownPayout + oRan.nextInt(500 + oRan.nextInt(1000));
    }
    
    public static HashMap<String,Area> loadAreas(){
        HashMap<String,Area> ret = new HashMap<>();
        areaNames = new ArrayList<>();
        
        if(BoppBot.EVENT == 1){
            ret.put("bk",new BurgerKing());
            areaNames.add("bk");
        } else if (BoppBot.EVENT == 5){
            ret.put("gsc",new Casino());
            areaNames.add("gsc");
        }else {
            ret.put("rc",new RescueCamp());
            areaNames.add("rc");
        } 
        
        ret.put("t1", new T1());
        areaNames.add("t1");
        
        ret.put("snarb", new Snarb());
        areaNames.add("snarb");
        
        ret.put("t2", new T2());
        areaNames.add("t2");
        
        ret.put("rjp", new RJP());
        areaNames.add("rjp");

        ret.put("rt", new Roarm());
        areaNames.add("rt");
        
        ret.put("t3", new T3());
        areaNames.add("t3");
        
        ret.put("fsc", new FSC());
        areaNames.add("fsc");

        ret.put("dan", new DAN());
        areaNames.add("dan");
        
        ret.put("core", new Core());
        areaNames.add("core");
        
        return ret;
    }
}
