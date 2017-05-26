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


import MOM.Data.Field;
import MOM.Data.UserData;
import MOM.Gates.Knights.Knight;
import MOM.MoM;
import static MOM.SuperRandom.oRan;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import sx.blah.discord.handle.obj.IChannel;



/**
 * @author FF6EB4
 */
public class Gate implements Serializable{
    private ArrayList<Room> map = new ArrayList<>();
    private long end;
    private HashMap<String, ArrayList<Integer>> visitedMap = new HashMap<>();
    
    public Gate(){
        map.add(new Room(Room.START));
        map.add(new Room(Room.END));
        
        addRoom();
        
        getRoom(Room.START).connections.add(1);
        getRoom(1).connections.add(Room.START);
        getRoom(1).connections.add(Room.END);
        getRoom(Room.END).connections.add(1);
        
        end = System.currentTimeMillis()+(1000*60*60*24*7); //Now+7 days
    }
    
    public Room getRoom(int ID){
        for(Room R : map){
            if(R.id == ID){
                return R;
            }
        }
        return null;
    }
    
    public void addRoom(){
        map.add(map.size()-1,new Room(map.size()-1));
    }
    
    public int getRoomCost(){
        return 100 * (map.size()-2)*(map.size()-2);
    }
    
    public String toString(){
        
        DecimalFormat df = new DecimalFormat("#.##");
        String ret;
        if(checkDate() < 0){
            ret = "**GateMap: Active Gate**\n\n";
        } else {
            ret = "**GateMap: "+df.format(checkDate())+" hours remaining**\n\n";
        }
        
        for(Room R : map){
            ret+= R+"\n";
        }   
        
        if(checkDate() > 0){
            ret+= "\n**To add a room for "+getRoomCost()+" material use !add room**";
            ret+= "\n**To connect existing rooms for 100 material use !connect room# room#**";
        }
        
        return ret;
    }
    
    public double checkDate(){
        double time = end - System.currentTimeMillis();
        
        double hours = (time / (double)(1000*60*60));
        
        if(hours < 0 && MoM.published.getData() != this){
            
            MoM.inProgress.writeData(new Gate());
            MoM.published.writeData(this);
        }
        
        return hours;
    }
    
    public void runKnight(){
        new Thread()
        {
            Gate G = MoM.published.getData();
            int roomNumber = Room.START;
            Knight K = new Knight();
            public void run() {
                roomNumber = G.runKnight(roomNumber,K);
                if(roomNumber == Room.END){
                    MoM.shout(K.name + " has exited the level!");
                    return;
                }
                try{
                    Thread.sleep(1000*60*4+oRan.nextInt(1000*60*2));
                }catch (Exception E){}
                this.run();
            }
        }.start();
    }
    
    public int runKnight(int room, Knight K){
        Room R = this.getRoom(room);
        
        if(room == R.START){
            MoM.shout(K.name + " has entered the level!");
            nap();
            visitedMap.put(K.name,new ArrayList<>());
            
            int rom = R.connections.get(oRan.nextInt(R.connections.size()));
            MoM.shout(K.name + " is heading for room "+rom+"!");
            return rom;
        }
        
        ArrayList<Integer> visited = visitedMap.get(K.name);
        boolean assault = !visited.contains(room);
        if(!assault){
            if(oRan.nextInt(100)<25){
                assault = true;
            }
        }
        
        visited.add(room);
        visitedMap.put(K.name,visited);
        
        if(assault){
            //
            ///This is where the knight fights the baddies.
            //
            ///// 
            int howWell = R.combatRoom(K);
            /////
            //

            int death = oRan.nextInt(100);
            if(death > howWell){
                MoM.shout(K +" has died in room "+room+"! HOORAY! +1 Slime Coin has been produced! Type in chat to get it!");
                nap();
                MoM.slimeCoin();

                return Room.END;
            } else {
                if(howWell < 40){
                    MoM.shout(K + " finished room "+room+" and narrowly escaped death.");
                } else if(howWell < 55){
                    MoM.shout(K + " finished room "+room+" and barely scraped by.");
                } else if(howWell < 65){
                    MoM.shout(K + " finished room "+room+" and managed, albeit poorly.");
                } else if(howWell < 75){
                    MoM.shout(K + " finished room "+room+" and had a tough time...");
                } else if(howWell < 85){
                    MoM.shout(K + " finished room "+room+" and did well enough.");
                } else if(howWell < 95){
                    MoM.shout(K + " finished room "+room+" and crushed it..");
                } else {
                    MoM.shout(K+ " finished room "+room+" and performed perfectly. Too bad.");
                }
            } 

            nap();
        }
        
        int nextRoom = R.connections.get(oRan.nextInt(R.connections.size()));
        for(int con : R.connections){
            if(!visited.contains(con)){
                if(oRan.nextInt(100) < 70){
                    nextRoom = con;
                }
            }
        }
        
        MoM.shout(K.name + " is heading for room "+nextRoom+"!");
        nap();
        
        return nextRoom;
    }
    
    public boolean checkPath(){
        return checkPath(Room.START, new ArrayList<>());
    }
    
    private boolean checkPath(int start,ArrayList<Integer> visited){
        Room R = this.getRoom(start);
        
        if(!visited.contains(start)){
            visited.add(start);
        }
        
        if(start == Room.END){
            return true;
        }
        
        for(int i : R.connections){
            visited.add(i);
        }
        
        for(int i : R.connections){
            if(checkPath(i, visited)) {
                return true;
            }
        }
        
        return false;
    }
    
    private void nap(){
        try{
            Thread.sleep(3500);
        } catch (Exception E){}
    }
}
