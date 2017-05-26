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

import MOM.Data.Collective;
import MOM.Data.UserData;
import MOM.Decompilable.Stage;
import MOM.Gates.Gate;
import MOM.Gates.Room;
import static MOM.MoM.PM;
import static MOM.MoM.inProgress;
import org.apache.commons.lang3.text.WordUtils;

/**
 *
 * @author FF6EB4
 */
public class TimerEnd {
    public static void act(String [] split, UserData UD){
                if(split[0].equals("Deconstructing")){
                    deconstructing(split, UD);
                }
                
                if(split[0].equals("Constructing")){
                    constructing(split, UD);
                }
                
                if(split[0].equals("Disconnecting")){
                    disconnecting(split, UD);
                }
                
                if(split[0].equals("Connecting")){
                    connecting(split, UD);
                }
                
                if(split[0].equals("Carrying")){
                    carrying(split, UD);
                }
                
                if(split[0].equals("Resurrecting")){
                    resurrect(split, UD);
                }
    }
    
    private static void resurrect(String[]split, UserData UD){
        try{
            int get = UD.corpses.getData();
            
            boolean worked = true;
            switch(split[1]){
                case "thwackers":
                    UD.thwackers.writeData(UD.thwackers.getData() + get);
                    break;
                case "demos":
                    UD.demos.writeData(UD.demos.getData() + get);
                    break;
                case "knockers":
                    UD.knockers.writeData(UD.knockers.getData() + get);
                    break;
                case "menders":
                    UD.menders.writeData(UD.menders.getData() + get);
                    break;
                default:
                    worked = false;
            }
            if(!worked){
                PM("Failed to resurrect gremlins...",UD);
                return;
            }
            UD.corpses.writeData(0);
            PM("Successfully resurrected corpses!",UD);
        } catch (Exception E){
            PM("Failed to resurrect gremlins...",UD);
        }
    }
    
    private static void carrying(String[]split, UserData UD){
        try{
            split = split[1].split(" ",4);
            
            int room = Integer.parseInt(split[3]);
            Room R = inProgress.getData().getRoom(room);
            
            if(split[0].contains("red")){
                if(Collective.minerals.getData()[0] < 100){
                    PM("Failed to carry red- not enough red!",UD);
                } else {
                    R.minerals[0]+=100;
                    PM("The knockers successfully carried 100 red!",UD);
                }
            }
            if(split[0].contains("green")){
                if(Collective.minerals.getData()[1] < 100){
                    PM("Failed to carry green- not enough green!",UD);
                } else {
                    R.minerals[1]+=100;
                    PM("The knockers successfully carried 100 green!",UD);
                }
            }
            if(split[0].contains("blue")){
                if(Collective.minerals.getData()[2] < 100){
                    PM("Failed to carry blue- not enough blue!",UD);
                } else {
                    R.minerals[2]+=100;
                    PM("The knockers successfully carried 100 blue!",UD);
                }
            }
            if(split[0].contains("yellow")){
                if(Collective.minerals.getData()[3] < 100){
                    PM("Failed to carry yellow- not enough yellow!",UD);
                } else {
                    R.minerals[3]+=100;
                    PM("The knockers successfully carried 100 yellow!",UD);
                }
            }
            if(split[0].contains("purple")){
                if(Collective.minerals.getData()[4] < 100){
                    PM("Failed to carry purple- not enough purple!",UD);
                } else {
                    R.minerals[4]+=100;
                    PM("The knockers successfully carried 100 purple!",UD);
                }
            }
            
            inProgress.writeData(inProgress.getData());
        } catch(Exception E){PM("Something terrible happened while the knockers moved crap.",UD);}
    }
    
    private static void connecting(String []  split, UserData UD){
        if(Collective.constructionMaterial.getData() < 100){
                        PM("Not enough construction material!",UD);
                        return;
                    }
                    
        
                    
                    try{
                        split = split[1].split(" ",3);
                        
                        int a,b;
                        
                        if(split[0].equals("START")){
                            a = Room.START;
                        } else if(split[0].equals("END")){
                            a = Room.END;
                        } else {
                            a = Integer.parseInt(split[0]);
                        }
                        
                        
                        if(split[2].equals("START")){
                            b = Room.START;
                        } else if(split[2].equals("END")){
                            b = Room.END;
                        } else {
                            b = Integer.parseInt(split[2]);
                        }
                        
                        Gate data = inProgress.getData();
                        
                        Room A = data.getRoom(a);
                        Room B = data.getRoom(b);
                        
                        if(!A.connections.contains(b)){
                            A.connections.add(b);
                            B.connections.add(a);
                        }
                        
                        
                        inProgress.writeData(data);
                        
                        PM("The rooms have been connected!",UD);
                        
                        int col = Collective.constructionMaterial.getData();
                        Collective.constructionMaterial.writeData(col - 100);
                    } catch (Exception E){PM("Something went amiss! Did you connect two nonsense rooms?!",UD);}
    }
    
    private static void disconnecting(String []  split, UserData UD){
        
                    try{
                        split = split[1].split(" ",3);
                        
                        int a,b;
                        
                        if(split[0].equals("START")){
                            a = Room.START;
                        } else if(split[0].equals("END")){
                            a = Room.END;
                        } else {
                            a = Integer.parseInt(split[0]);
                        }
                        
                        
                        if(split[2].equals("START")){
                            b = Room.START;
                        } else if(split[2].equals("END")){
                            b = Room.END;
                        } else {
                            b = Integer.parseInt(split[2]);
                        }
                        
                        Gate data = inProgress.getData();
                        
                        Room A = data.getRoom(a);
                        Room B = data.getRoom(b);
                        
                        if(A.connections.contains(b)){
                            A.connections.remove(A.connections.indexOf(b));
                            B.connections.remove(B.connections.indexOf(a));
                        }
                        
                        
                        inProgress.writeData(data);
                        
                        PM("The rooms have been disconnected!",UD);
                        
                        if(!data.checkPath()){
                            A.connections.add(b);
                            B.connections.add(a);
                            
                            PM("Make sure there is a path from start to finish! The rooms have been reconnected! ",UD);
                        }
                        
                    } catch (Exception E){
                        System.out.println(E);
                        PM("Something went amiss disconnecting room! I'm sorry!",UD);
                    }
    }
    
    private static void deconstructing(String [] split, UserData UD){
        try{
                        String level = WordUtils.capitalizeFully(split[1]);
                        Stage S = new Stage(level);
                        PM(S.collect(UD),UD);
                    } catch (Exception E){}
    }
    
    private static void constructing(String [] split, UserData UD){
        int cost = inProgress.getData().getRoomCost();
                    if(Collective.constructionMaterial.getData() < cost){
                        PM("Not enough construction material to finish the job!",UD);
                        return;
                    }
                    
                    try{
                        Gate data = inProgress.getData();
                        
                        data.addRoom();
                        
                        inProgress.writeData(data);
                        
                        PM("Room construction finished! Be sure to conect it!",UD);
                        
                        int col = Collective.constructionMaterial.getData();
                        Collective.constructionMaterial.writeData(col - cost);
                    } catch (Exception E){PM("Failure to construct!",UD);}
    }
}
