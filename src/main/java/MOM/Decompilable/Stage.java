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
package MOM.Decompilable;

import MOM.Data.Collective;
import MOM.Data.UserData;
import static MOM.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class Stage {
    public String name = "Compound";
    int[] minerals = Collective.mineralDistribution(40);
    int material = 70+oRan.nextInt(60);
    int corpses = 1+oRan.nextInt(3);
    
    public Stage (String name){
        if(name.equals("Wolver Den")){
            this.name = name;
            minerals[0] += 15;
            minerals[1] += 15;
            minerals[2] = minerals[2] / 2;
            minerals[3] = minerals[3] / 2;
            minerals[4] = minerals[4] / 2;
            corpses = oRan.nextInt(6);
        }
        
        if(name.equals("Devilish Drudgery")){
            this.name = name;
            minerals[0] += 15;
            minerals[1] = minerals[1] / 2;
            minerals[2] = minerals[2] / 2;
            minerals[3] = minerals[3] / 2;
            minerals[4] += 15;
            corpses = oRan.nextInt(4);
        }
        
        if(name.equals("Lichenous Lair")){
            this.name = name;
            minerals[0] = minerals[0] / 2;
            minerals[1] += 15;
            minerals[2] = minerals[2] / 2;
            minerals[3] += 15;
            minerals[4] = minerals[4] / 2;
            corpses = oRan.nextInt(2);
        }
        
        if(name.equals("Jigsaw Valley")){
            this.name = name;
            minerals[0] = minerals[0] / 2;
            minerals[1] += 15;
            minerals[2] += 15;
            minerals[3] = minerals[3] / 2;
            minerals[4] = minerals[4] / 2;
            corpses = oRan.nextInt(7)+2;
        }
    }
    
    public String collect(UserData UD){
        int addto = Collective.constructionMaterial.getData();
        int[] mins = Collective.minerals.getData();
        
        for(int i = 0; i < 5; ++i){
            mins[i] += minerals[i];
        }
        
        Collective.constructionMaterial.writeData(addto + material);
        Collective.minerals.writeData(mins);
        UD.corpses.writeData(UD.corpses.getData() + corpses);
        
        String ret = "**"+name+" Deconstructed!**\n";
        
        ret+="**minerals Gained:** "+minerals[0]+" red, "+
                minerals[1]+" green, "+
                minerals[2]+" blue, "+
                minerals[3]+" yellow, "+
                minerals[4]+" purple\n";
        ret += "**Construction Material Gained:** "+material+"\n";
        ret += "**Corpses Found:**"+corpses;
        return ret;
    }
}
