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
package MOM.Gates.Knights;

import static MOM.SuperRandom.oRan;
import java.util.ArrayList;

/**
 *
 * @author FF6EB4
 */
public class Weapon {
    public static final int NORMAL = 0;
    public static final int PIERCING = 1;
    public static final int ELEMENTAL = 2;
    public static final int SHADOW = 3;
    
    public static final int UTILITY = 0;
    public static final int SINGLE = 1;
    public static final int BASIC = 2;
    public static final int CROWD = 3;
    
    public int damageType = 0;
    public int damageKind = 0;
    
    public Weapon(int type, int kind){
        this.damageType = type;
        this.damageKind = kind;
    }
    
    public Weapon(){
        this(randomizeType(),randomizeKind());
    }
    
    public static int randomizeType(){
        int seed = oRan.nextInt(4);
        
        if(seed == 0){
            if(oRan.nextInt(100) < 50){
                seed = 1+oRan.nextInt(3);
            }
        }
        
        return seed;
    }
    
    public static int randomizeKind(){
        int seed = oRan.nextInt(4);
        
        if(seed == 0 || seed == 3){
            if(oRan.nextInt(100) < 50){
                seed = 1+oRan.nextInt(3);
            }
        }
        
        return seed;
    }
}
