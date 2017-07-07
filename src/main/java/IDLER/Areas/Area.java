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
package IDLER.Areas;

import IDLER.BoppBot;
import java.util.ArrayList;
import java.util.HashMap;
import IDLER.UserData;
import static IDLER.SuperRandom.oRan;


/**
 *
 * @author FF6EB4
 */
public class Area {
    public static HashMap<String,Area> areas = loadAreas();
    public static ArrayList<String> areaNames;
    public static int areaNumber = 27;
    
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
        
        ret.put("haven", new Haven());
        areaNames.add("haven");
        ret.put("s1", new S1());
        areaNames.add("s1");
        
        ret.put("s2", new S2());
        areaNames.add("s2");
        ret.put("starlight", new Starlight_cradle());
        areaNames.add("starlight");
        
        ret.put("snarb", new Snarb());
        areaNames.add("snarb");
        ret.put("recruits", new Recruits());
        areaNames.add("recruits");
        
        ret.put("s3", new S3());
        areaNames.add("s3");
        ret.put("trojan",new Trojans());
        areaNames.add("trojan");
        
        ret.put("s4", new S4());
        areaNames.add("s4");
        ret.put("decon", new Decon());
        areaNames.add("decon");
        
        ret.put("rjp", new RJP());
        areaNames.add("rjp");
        ret.put("sewer", new Sewer());
        areaNames.add("sewer");

        ret.put("rt", new Roarm());
        areaNames.add("rt");
        ret.put("devilites", new Devilites());
        areaNames.add("devilites");
        
        ret.put("s5", new S5());
        areaNames.add("s5");
        ret.put("arena",new Arena());
        areaNames.add("arena");
        
        ret.put("s6", new S6());
        areaNames.add("s6");
        ret.put("gauntlet",new Gauntlet());
        areaNames.add("gauntlet");
        
        ret.put("fsc", new FSC());
        areaNames.add("fsc");
        ret.put("arkus",new Arkus());
        areaNames.add("arkus");

        ret.put("loa", new LOA());
        areaNames.add("loa");
        ret.put("gitm", new GITM());
        areaNames.add("gitm");
        ret.put("c42", new COMPOUND());
        areaNames.add("c42");
        ret.put("hoi", new HOI());
        areaNames.add("hoi");
        ret.put("dan", new DAN());
        areaNames.add("dan");
        
        
        ret.put("core", new Core());
        areaNames.add("core");
        
        return ret;
    }
}
