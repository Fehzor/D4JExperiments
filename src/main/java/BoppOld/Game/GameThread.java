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
package BoppOld.Game;


/**
 *
 * @author FF6EB4
 */
public class GameThread implements Runnable{
    
    public static final long TICK_TIME = 60000;
    
    long time = System.currentTimeMillis();
    
    Area A;
    
    public GameThread(Area A){
        this.A = A;
    }
    
    public void run(){
        loop();
    }
    
    private void loop(){
        while(true){
            if(tick()){
                A.play();
            } else {
                try{
                    Thread.sleep(20000);
                } catch (Exception E){}
            }
        }
    }
    
    public boolean tick(){
        long difference = System.currentTimeMillis() - time;
        if(difference > TICK_TIME){
            time = System.currentTimeMillis();
            return true;
        }
        return false;
    }
    
    
}
