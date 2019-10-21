/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bjha43mvcstopwatchfxml;

/**
 *
 * @author Byron Hammann - bjha43
 * This is by far my largest model, as it took the most code, and I pulled it directly 
 * from my original stopwatch challenge. Because it was so long in my original stopwatch, and part of the digital portion (originally)
 * I decided to make it its own model. In theory I should be able to use the logic for future 
 * record boards depending on what I will be recording. 
 */
public class RecordBoard extends AbstractModel{
    
    private int mins = 0;
    private int secs = 0;
    private int millis = 0;
    private static int lapCount = 0;
    private int lastSec = 0;
    private int lastMin = 0;
    private int lastMili = 0;
    private String output = "--:--.--";
    private String lap;
    
    public RecordBoard()
    {
        super.setupTimer();
    }
    
    
    @Override
    public void update() {
                super.update();
                millis++;
		if(millis == 1000)
                {
                    secs++;
                    millis = 0;
		}
		if(secs == 60) 
                {
                    mins++;
                    secs = 0;
		}
                
    }
    
     public void lastLapSec(int sec){
        lastSec = sec;  
        
    }
    
    public void lastLapMin(int min){
        lastMin = min;  
    }
    
    public void lastLapMili(int mil){
        lastMili = mil;
        
    }
     public int getMilis(){
        return millis;
    }
    
    public int getSecs(){
        return secs;
        
    }
    
    public int getMins(){
        return mins;
    }
    
     public void setLap(){
        lapCount++;
        int sec, y; 
        int milis, x;
        int min, z; 
        milis = getMilis();
        sec = getSecs();
        min = getMins();
        x = milis;
        y = sec;
        z = min;
        String noLap = "--:--.---";
        
        int laps; 
        if(lapCount > 3){
            laps = lapCount%3;
        }else laps = lapCount;
        if(0 == lastMili && lastMin == 0 && lastSec ==0)
        {    
            lap =("Lap " + lapCount + ": " + (String.format("%02d", min)+ ":" + String.format("%02d",sec) + "." + String.format("%03d",milis))) ; 
            lastLapSec(y);
            lastLapMin(z);
            lastLapMili(x);
            firePropertyChange("Lap1", noLap, lap);
        }else{
       
        if((milis-lastMili)< 0)
        {
            if(sec>0){
                sec--;
                milis = milis + 1000;
                milis = milis - lastMili;
            }
        }else milis = milis - lastMili;
        if((sec-lastSec) < 0)
        {
            if(min>0){
            min--;
            sec = sec + 60;
            sec = sec - lastSec;}
        } else sec = sec- lastSec;
        
        min = min - lastMin;
        
              
        if(laps==1)
        {
            
                lap =("Lap " + lapCount + ": " + (String.format("%02d", min)+ ":" + String.format("%02d",sec) + "." + String.format("%03d",milis))) ;
                lastLapSec(y);
                lastLapMin(z);
                lastLapMili(x);
                firePropertyChange("Lap1", noLap, lap);
        }   
        if(laps==2)
        {
            
                lap =("Lap " + lapCount + ": " + (String.format("%02d", min)+ ":" + String.format("%02d",sec) + "." + String.format("%03d",milis))) ;
                lastLapSec(y);
                lastLapMin(z);
                lastLapMili(x);
                firePropertyChange("Lap2", noLap, lap);
        }
        if(laps==0||laps==3)
        {
            
                lap =("Lap " + lapCount + ": " + (String.format("%02d", min)+ ":" + String.format("%02d",sec) + "." + String.format("%03d",milis))) ;
                lastLapSec(y);
                lastLapMin(z);
                lastLapMili(x);
                firePropertyChange("Lap3", noLap, lap);
        }
               
        } 
    }
        
     @Override
    public void reset()
    {
        super.reset();
        millis = 0;
        secs = 0;
        mins = 0;
        output =("--:--.---");
        firePropertyChange("Lap1", lap, output);
        firePropertyChange("Lap2", lap, output);
        firePropertyChange("Lap3", lap, output);
               
    }
   
    
}
