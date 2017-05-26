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
package MOM.Data;

import static MOM.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class Collective {
    public static Field<int[]> minerals = new Field<> ("COLLECTIVE","MINERALS",mineralDistribution(400));
    public static Field<Integer> constructionMaterial = new Field<>("COLLECTIVE","MATERIAL",700+oRan.nextInt(600));
    
    
    public static int[] mineralDistribution(int val){
        int[] ret = new int[5];
        
        ret[0] = oRan.nextInt(val);
        ret[1] = oRan.nextInt(val);
        ret[2] = oRan.nextInt(val);
        ret[3] = oRan.nextInt(val);
        ret[4] = oRan.nextInt(val);
        
        return ret;
    }
    
    public static String getString(){
        String ret = "**Collective Resources:**\n";
        
        ret+="minerals: "+minerals.getData()[0]+" red, "+
                minerals.getData()[1]+" green, "+
                minerals.getData()[2]+" blue, "+
                minerals.getData()[3]+" yellow, "+
                minerals.getData()[4]+" purple\n";
        
        ret+="Construction Material: "+constructionMaterial.getData();
        
        return ret;
    }
}
