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

/**
 *
 * @author FF6EB4
 */
public class Knight {
    public String name = getRandomName();
    public Weapon A = new Weapon();
    public Weapon B = new Weapon();
    public Weapon C = new Weapon();
    
    public static String getRandomName(){
        int name = oRan.nextInt(57);
        
        switch(name){
            case 0: return "Bopp";
            case 1: return "Zeddy";
            case 2: return "Krakob";
            case 3: return "Legobuild";
            case 4: return "Fallen-Feces";
            case 5: return "Vohtarak";
            case 6: return "Spuds";
            case 7: return "Eurydice";
            case 8: return "Fangel";
            case 9: return "Meat-Balls";
            case 10: return "Tickle-My-Titan";
            case 11: return "Voofi";
            case 12: return "Paintool";
            case 13: return "Njthug";
            case 14: return "Professional-Help";
            case 15: return "Mayaura";
            case 16: return "Mawashimono";
            case 17: return "Delilah-Of-Sorek";
            case 18: return "Legg";
            case 19: return "Cronus";
            case 20: return "Neometal";
            case 21: return "Petater";
            case 22: return "Glacies";
            case 23: return "Fehzor";
            case 24: return "Arca";
            case 25: return "Jerko";
            case 26: return "Sir-Endipity";
            case 27: return "Java-Latte";
            case 28: return "Magnus";
            case 29: return "Razzbry";
            case 30: return "Free-Dan-Carries";
            case 31: return "Sosheem";
            case 32: return "Tiny-Tato";
            case 33: return "Suwy";
            case 34: return "Xersin";
            case 35: return "Spiral-Market";
            case 36: return "Simhazee";
            case 37: return "Jcaz";
            case 38: return "Darkshadowmage";
            case 39: return "Rostav";
            case 40: return "Swordhands";
            case 41: return "Liu-Kon";
            case 42: return "Acbonecky";
            case 43: return "Tidysnacker";
            case 44: return "Lipsmacker";
            case 45: return "Bearsmacker";
            case 46: return "Cubsmacker";
            case 47: return "Snarbysmacker";
            case 48: return "Tittysmacker";
            case 49: return "Surreptitiously";
            case 50: return "Sexy-Spiral-Girl";
            case 51: return "Bygone";
            case 52: return "Macbirdie";
            case 53: return "Doomiax";
            case 54: return "Chris";
            case 55: return "Skeptic-Raven";
            case 56: return "Zendoku";
        }
        
        return "";
    }
    
    public String toString(){
        return this.name;
    }
}
