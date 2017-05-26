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
package Bopp.Data;

import static Bopp.Gates.Gate.oRan;
import Bopp.Items.Gear;
import java.util.HashMap;

/**
 *
 * @author FF6EB4
 */
public class Unboxing {
    public static long UNBOXING_TIME = 1000 * 60 * 3; //3 minutes
    
    public static Field<HashMap<String,Long>> starts =
            new Field<>("BOX","STARTS",new HashMap<>());
    
    public static String unbox(UserData UD){
        starts.write();
        if(check(UD)){
            starts.data.remove(UD.ID);
            starts.write();
            return reward(UD);
        } else if(starts.data.containsKey(UD.ID)) {
            long howlong = System.currentTimeMillis() - starts.data.get(UD.ID);
            long remain = UNBOXING_TIME - howlong;
            
            if(UD.gear.data.contains("Scissor Blades")){
                System.out.println("SCISSORS FOUND");
                remain -= UNBOXING_TIME;
                remain = remain / 2;
            } else {
                System.out.println("SCISSORS NOT FOUND: FULL UNBOX");                
            }
            
            long minutes = (long)Math.ceil((double)remain / (double)(1000*60));
            return "You're already unboxing something!"
                    + "\nTime remaining: " +minutes+" minutes";
        } else if(UD.slimeCoins.data >= 1000) {
            UD.slimeCoins.data -= 1000;
            starts.data.put(UD.ID, System.currentTimeMillis());
            starts.write();
            return "Unboxing! Come back in 3 minutes!";
        } else {
            return "You need 1000 slime coins to unbox something!";
        }
    }
    
    //True if it's time to reward.
    private static boolean check(UserData UD){
        if(starts.data.containsKey(UD.ID)){
            long start = starts.data.get(UD.ID);
            long end = System.currentTimeMillis();
            long time = end - start;
            long remain = UNBOXING_TIME - time;
            
            if(UD.gear.data.contains("Scissor Blades")){
                System.out.println("SCISSORS FOUND");
                remain -= UNBOXING_TIME;
                remain = remain / 2;
            } else {
                System.out.println("SCISSORS NOT FOUND: FULL UNBOX");                
            }
            
            if(remain <= 0){
                return true;
            }
            return false;
        } else {
            return false;
        }
    }
    
    
    private static Field<HashMap<String,Integer>> boxes = new Field("BOX","COUNT",new HashMap<>());
    
    private static int magnitude(UserData UD){
        long prestige = UD.prestige.data;
        int ret = (int)Math.floor(Math.log(prestige));
        
        ret = ret - 6;
        if(ret < 0){
            ret = 0;
        }
        
        try{
            int count = boxes.data.get(UD.ID);
            ret+=(int)Math.floor(Math.log(count));
        } catch(Exception E){}
        
        return ret;
    }
    
    private static boolean isPrime(int n) {
        for(int i=2;2*i<n;i++) {
            if(n%i==0)
                return false;
        }
        return true;
    }
    
    private static String reward(UserData UD){
        if(!boxes.data.containsKey(UD.ID)){
            boxes.data.put(UD.ID, 1);
        }  else {
            boxes.data.put(UD.ID,boxes.data.get(UD.ID)+1);
        }
        boxes.write();
        
        int num = boxes.data.get(UD.ID);
        int magnitude = magnitude(UD);
        boolean prime = isPrime(num);
        
        int loot = 0;
        if(prime){
            loot = oRan.nextInt(1+oRan.nextInt(1000));
        } else {
            loot = oRan.nextInt(1000);
        }
        
        loot -= (magnitude*7);
        if(loot < 0) loot = 5;
        
        System.out.println("LOOT VALUE: "+loot);
        System.out.println("BOX NUMBER: "+num);
        return getLoot(UD,loot);
    }
    
    
    
    private static String getLoot(UserData UD, int loot){
        if(loot < 23){
            int which = oRan.nextInt(8);
            if(which == 0){
                UD.gear.data.add("Void Striker");
                return "Unboxed Void Striker!!!!";
            } else if (which == 1){
                UD.gear.data.add("Celestial Orbitgun");
                return "Unboxed Celestial Orbitgun!";
            } else if(which == 2){
                UD.gear.data.add("Celestial Saber");
                return "Unboxed Celestial Saber!";
            } else if(which == 3){
                UD.gear.data.add("Wrench Wand");
                return "Unboxed Wrench Wand?!?!?!";
            } else if(which == 4){
                UD.gear.data.add("Mug Of Misery");
                return"Unboxed Mug Of Misery?!?!";
            } else if(which == 5){
                UD.gear.data.add("The Book Of Dark Rituals");
                return"Unboxed The Book Of Dark Rituals?!?!";
            } else if(which == 6){
                UD.gear.data.add("Monster Bone");
                return"Unboxed Monster Bone?!?!";
            } else if(which == 7){
                UD.gear.data.add("Wolver Slippers");
                return"Unboxed Wolver Slippers?!?!";
            }
        } else if(loot == 29){
                UD.name.data = "±" + UD.name.data;
                
                return "Crown added to name!";
        } else if(loot == 31){
            int which = oRan.nextInt(6);
            if(which == 0){
                UD.gear.data.add("Potato");
            } else if (which == 1){
                UD.gear.data.add("Empty Beer Can");
            } else if(which == 2){
                UD.gear.data.add("Prismatic Bolted Vee");
            } else if(which == 3){
                UD.gear.data.add("Unique Variant Tickets");
            } else if(which == 4){
                UD.gear.data.add("Shodily Reskinned Costumes");
            } else if(which == 5){
                UD.gear.data.add("Dank Matter Bomb");
                return "Unboxed Dank Matter Bomb!";
            }
                return "Unboxed garbage that does nothing!";
        } if(loot < 80){
            int which = oRan.nextInt(5);
            if(which == 0){
                UD.gear.data.add("Overcharged Mixmaster");
                return "Unboxed Overcharged Mixmaster!";
            } else if(which == 1){
                UD.gear.data.add("Caladbolg");
                return "Unboxed Caladbolg!";
            } else if(which == 2){
                UD.gear.data.add("Spiral Soaker");
                return "Unboxed Spiral Soaker!";
            } else if(which == 3){
                UD.gear.data.add("Teddy Bear Buckler");
                return "Teddy Bear Buckler!";
            } else if(which == 4){
                UD.gear.data.add("Sweet Dreams");
                return "Sweet Dreams!";
            }
        }else if(loot < 250){
            String s = Gear.gearList.get(oRan.nextInt(Gear.gearList.size()));
            UD.gear.data.add(s);
            return "Unboxed "+s+"!";
        } else if(loot < 350){
            UD.crowns.data += 100000;
            return "Unboxed 1 hundred thousand crowns!";
        } else if (loot < 450){
            UD.slimeCoins.data += 2000;
            return "Unboxed 2000 slime coins!";
        }  else if(loot < 550){
            UD.orbs.data[0] += 2;
            UD.orbs.data[1] += 2;
            UD.orbs.data[2] += 2;
            UD.orbs.data[3] += 2;
            return "Unboxed 2 of each orb!";
        } else if(loot < 650){
            UD.materials.data[8] += 1337;
            return "Unboxed 1337 shock materials!";
        } else if(loot < 750){
            UD.minerals.data[0] += 3000;
            UD.minerals.data[1] += 3000;
            UD.minerals.data[2] += 3000;
            UD.minerals.data[3] += 3000;
            UD.minerals.data[4] += 3000;
            return "Unboxed 3000 of each mineral!";
        } else if(loot < 850){
            UD.prestige.data += 1001;
            return "Unboxed 1001 prestige!";            
        }else if(loot == 923){
            UD.prestige.data += 2000;
            return "Unboxed 2K prestige!";
        } else if(loot < 950){
            UD.prestige.data += 3500;
            return "Unboxed 3500 prestige!";
        } else {
            UD.weaponA.data = "Proto Sword";
            UD.weaponB.data = "Proto Gun";
            UD.weaponC.data = "Proto Bomb";
            return "Unboxed proto gear!";
        }
        return "";
    }
    
}
