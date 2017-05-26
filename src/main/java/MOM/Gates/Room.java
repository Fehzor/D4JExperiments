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
package MOM.Gates;

import static Bopp.Gates.Gate.oRan;
import MOM.Data.Field;
import MOM.Gates.Knights.Knight;
import MOM.Gates.Knights.Weapon;
import static MOM.MoM.shout;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author FF6EB4
 */
public class Room implements Serializable{
    int id;
    
    public static final int START = -1;
    public static final int END = -2;
    
    public ArrayList<Integer> connections;
    
    public int[] minerals = new int[5];
    
    public Room(int ID){
        this.id = ID;
        connections = new ArrayList<>();
    }
    
    public String toString(){
        String ret = "**Room #"+id+"**\n";
        
        if(id == START){
            ret = "**Elevator- Start**\n";
        }
        
        if(id == END){
            ret = "**Elevator- End**\n";
        }
        
        ret += "*Connections: ";
        
        if(connections.size() > 0){
            for(int C : connections){
                if(C == START){
                    ret+="START, ";
                } else if(C == END) {
                    ret+="END, ";
                } else {
                    ret+= C+", ";
                }
            }
            ret = ret.substring(0, ret.length() - 2);
            ret+= "*";
        } else  {
            ret += "None.*";
        }
        
        if(id != START && id != END){
            ret+="\n*Minerals: "+minerals[0]+" red, "+minerals[1]+" green, "+
                    minerals[2]+" blue, "+minerals[3]+" yellow, "+minerals[4]+" purple.*";
            
            int total = 0;
            int low = Integer.MAX_VALUE;
            int high = 0;
            for(int min : minerals){
                total += min;
                if(min < low) low = min;
                if(min > high) high = min;
            }
            
            
            
            String amt = "empty";
            
            
            
            if(high - low < 300 && total > 700) {
                amt = "full of traps";
            }if(total > 100 && total < 700){
                amt = "single enemy";
            } else if(total < 1500){
                amt = "small group of enemies";
            } else if(total < 3500){
                amt = "crowded with enemies";
            } else {
                amt = "crowded with enemies and traps";
            }
            
            ret+="\n*("+amt+")*";
        }
        
        return ret;
    }
    
    //100 = the knight got an A+
    //90 = 90% chance of the knight succeeding
    //0 = the knight failed entirely
    //EtCetera
    public int combatRoom(Knight K){
        
        shout(K.name+" is challenging room "+this.id);
        nap();
        
        Weapon A = K.A;
        Weapon B = K.B;
        Weapon C = K.C;
        
        int score = 80 + oRan.nextInt(40);
        boolean utility = utility();
        
        int crowd = crowd();
        if(crowd == 0){
            return 100;
        }
        
        int familyA = chooseFamily();
        int familyB = chooseFamily();
        
        String famAName = this.getFamilyName(familyA);
        String famBName = this.getFamilyName(familyB);
        
        shout("Heroic monstrosities appear before "+K.name+"! They're "+famAName+" and "+famBName+"!");
        nap();
        
        int weakness = selectDamageWeakness(familyA,familyB);
        int resistA = selectDamageResistance(familyA);
        int resistB = selectDamageResistance(familyB);
        
        boolean textFlagA = false;
        if(A.damageKind == 0 && utility){
            
            if(A.damageType == weakness) {
                shout(K.name+" expertly dodges the trap with their utility weapon! It's a one hit KO!");
                nap();
                score += 30;
            }
            shout(K.name+" expertly dodges the trap with their utility weapon!");
            textFlagA = true;
            nap();
            if(A.damageType != resistA) score += 10;
            if(A.damageType != resistB) score += 10;
            
        }
        
        if(B.damageKind == 0 && utility){
            if(B.damageType == weakness){
                shout(K.name+" expertly dodges the trap with their utility weapon! Oh no!");
                nap();
                score += 30;
            }
            if(textFlagA){
                shout(K.name+"'s second weapon adds even more utility to deal with the traps!");
            } else {
                shout(K.name+" expertly dodges the trap with their utility weapon!");
            }
            nap();
            if(B.damageType != resistA) score += 10;
            if(B.damageType != resistB) score += 10;
        }
        
        if(C.damageKind == 0 && utility){
            if(C.damageType == weakness){
                shout(K.name+" expertly dodges the trap with their utility weapon! Oh no!");
                nap();
                return 100;
            }
            if(textFlagA){
                shout(K.name+"'s auxilary weapon adds even more utility to deal with the traps!");
            } else {
                shout(K.name+" expertly dodges the trap with their utility weapon!");
            }
            nap();
            if(C.damageType != resistA) score += 10;
            if(C.damageType != resistB) score += 10;
        }
        
        if(utility){
            if(B.damageKind != 0 && A.damageKind != 0 && C.damageKind != 0){
                score -= 25;
                shout(K.name+" gets stuck on a trap! Devestating!");
                nap();
            }
        }
        
        if(A.damageKind == crowd){
            
            if(A.damageType == weakness){
                shout(K.name+" has the perfect answer for this room! Looks like it's over...");
                nap();
                score += 30;
            }
            if(A.damageType != resistA) score += 20;
            if(A.damageType != resistB) score += 20;
        }
        
        if(B.damageKind == crowd){
            if(B.damageType == weakness){
                shout(K.name+" has the perfect answer for this room with their secondary weapon!");
                nap();
                score += 30;
            }
            if(B.damageType != resistA) score += 20;
            if(B.damageType != resistB) score += 20;
        }
        
        if(C.damageKind == crowd){
            if(C.damageType == weakness){
                shout(K.name+" has the perfect answer for this room with their auxilary weapon!");
                nap();
                score += 30;
            }
            if(C.damageType != resistA) score += 20;
            if(C.damageType != resistB) score += 20;
        }
        
        if(A.damageKind != crowd && B.damageKind != crowd && C.damageKind != crowd){
            shout(K.name+" lacks a good solution for this room's enemies! Ouch!");
            nap();
            score -= 40;
        }
        
        if(score > 100) return 100;
        return score;
    }
    
    public boolean utility(){
        int total = 0;
        int low = Integer.MAX_VALUE;
        int high = 0;
        for(int min : minerals){
            total += min;
            if(min < low) low = min;
            if(min > high) high = min;
        }

        if((total > 500 && high - low < 400) || total > 3500){
            return true;
        }
        return false;
    }
    
    public int crowd(){
        int total = 0;
        for(int a : minerals){
            total += a;
        }
        
        if(total == 0){
            return 0;
        }
        
        if(total < 1000){
                return 1;
            } else if(total < 2000){
                return 2;
            } else {
                return 3;
            }
    }
    
    public int selectDamageResistance(int family){
        if(family == 3 || family == 9) return 3;
        if(family == 0 || family == 1) return 2;
        if(family == 5 || family == 7) return 1;
        return 0;
    }
    
    public int selectDamageWeakness(int familyA, int familyB){
        //0 Beast, 1 Grem, 2 Fire, 3 Fiend, 4 Freeze, 5 Slime, 6 Poison, 7 Construct, 8 Shock, 9 Undead

        int weakOne = 0;
        
        if(familyA == 0 || familyA == 3) weakOne = 1;//Piercing
        if(familyA == 7 || familyA == 9) weakOne = 2;//Elemental
        if(familyA == 1 || familyA == 5) weakOne = 3;//Shadow
        
        int weakTwo = 0;
        
        if(familyB == 0 || familyB == 3) weakTwo = 1;
        if(familyB == 7 || familyB == 9) weakTwo = 2;
        if(familyB == 1 || familyB == 5) weakTwo = 3;
        
        if(weakOne == weakTwo) return weakOne;
        if(weakOne == 0) return weakTwo;
        if(weakTwo == 0) return weakOne;
        
        if(weakOne == 1 && (familyB == 5 || familyB == 7) ){
            return 0;
        }
        
        if(weakOne == 2 && (familyB == 0 || familyB == 1) ){
            return 0;
        }
        
        if(weakOne == 3 && (familyB == 3 || familyB == 9) ){
            return 0;
        }
        
        if(weakTwo == 1 && (familyA == 5 || familyA == 7) ){
            return 0;
        }
        
        if(weakTwo == 2 && (familyA == 0 || familyA == 1) ){
            return 0;
        }
        
        if(weakTwo == 3 && (familyA == 3 || familyA == 9) ){
            return 0;
        }
        
        return weakOne;
    }
    
    //Chooses the family
    public int chooseFamily(){
        int totalMinerals = 0;
        for(int a : minerals){
            totalMinerals += a;
        }
        
        //
        //ROLL FOR ALPHA
        //
        int select = oRan.nextInt(totalMinerals);
        int result = 0;
        
        for(int a : minerals){
            select = select - a;
            if(select > 0){
                result++;
            }
        }
        
        int alpha = result;
        
        //
        //ROLL FOR BETA
        //
        result = 0;
        select = oRan.nextInt(totalMinerals);
        
        for(int a : minerals){
            select = select - a;
            if(select > 0){
                result++;
            }
        }
        
        int beta = result;
        
        int trials = 0;
        while(alpha == beta && trials < 100){
            result = 0;
            select = oRan.nextInt(totalMinerals);

            for(int a : minerals){
                select = select - a;
                if(select > 0){
                    result++;
                }
            }
            beta = result;
            trials++;
        }
        
        if(trials == 100) return oRan.nextInt(10);
        
        if(alpha > beta){
            int temp = alpha;
            alpha = beta;
            beta = temp;
        }
        
        //RGBYP
        
        //Beast,Grem,Fire,Fiend,Freeze,Slime,Poison,Construct,Shock,Undead
        
        //0//0,1,2,3
        //1//4,5,6
        //2//7,8
        //3//9
        
        
        int ans = 0;
        switch(alpha){
            case 1:
                ans = 4;
                break;
            case 2:
                ans = 7;
                break;
            case 3:
                ans = 9;
        }
        ans += beta - alpha - 1;
        
        return ans;
    }
    
    private void nap(){
        try{
            Thread.sleep(3500);
        } catch (Exception E){}
    }
    /*
    ""+materials.data[0]+" beast"
                + ", "+materials.data[1]+" gremlin"
                + ", "+materials.data[2]+" fire"
                + ", "+materials.data[3]+" fiend"
                + ", "+materials.data[4]+" freeze"
                + ", "+materials.data[5]+" slime"
                + ", "+materials.data[6]+" poison"
                + ", "+materials.data[7]+" construct"
                + ", "+materials.data[8]+" shock"
                + ", "+materials.data[9]+" undead"
    */
    public String getFamilyName(int fam){
        switch(fam){
            case 0: return "beast";
            case 1: return "gremlin";
            case 2: return "fire";
            case 3: return "fiend";
            case 4: return "freeze";
            case 5: return "slime";
            case 6: return "poison";
            case 7: return "construct";
            case 8: return "shock";
            case 9: return "undead";
        }
        return "none";
    }
}
