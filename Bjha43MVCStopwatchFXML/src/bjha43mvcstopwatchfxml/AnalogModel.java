/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bjha43mvcstopwatchfxml;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Byron Hammann - Bjha43
 */
public class AnalogModel extends AbstractModel {
    
   
    private double angleDeltaPerSeconds = 6.0;
    private double secondsElapsed = 0;     
    private double rotation;
    
    public AnalogModel(){
       super.setupTimer();
    }
// Added an overloaded constructor so that I could adjust the rate at which the 
// model would keep time. 
// could be adjusted for what level of time I woulde need.
    
    public AnalogModel(double tickTimeInSeconds, double angleDeltaPerSeconds){
        
        this.tickTimeInSeconds = tickTimeInSeconds;
        this.angleDeltaPerSeconds = angleDeltaPerSeconds;
        super.setupTimer();
    }
    
    @Override
    public void update() {
        super.update();
        secondsElapsed += tickTimeInSeconds;
        double oldVal = rotation;
        rotation = secondsElapsed * angleDeltaPerSeconds;
        // if the values received from regular contructer are used, then send one property change, otherwise send another.
        // This should allow the creation of a full chronograph of hands for the stopwatch.
        // I only added the minute hand, because the display started to look clunky with to many hands on the 
        // provided images. 
        if(angleDeltaPerSeconds == 6.0 && tickTimeInSeconds == .001 )
        {    
            firePropertyChange("Analog", oldVal, rotation);
        }
        else firePropertyChange("Analog2", oldVal, rotation);
        
      
    } 
     
         
    @Override
    public void reset(){
        
        super.reset();
        secondsElapsed = 0;
        rotation = 0;
      
        
        
    }  
}

    
    

