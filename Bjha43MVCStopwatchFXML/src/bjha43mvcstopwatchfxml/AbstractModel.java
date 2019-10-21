/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bjha43mvcstopwatchfxml;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**
 *
 * @author Byron Hammann - bjha43
 */
public abstract class AbstractModel {

    
    protected Timeline timeline;
    protected KeyFrame keyFrame;
    protected double tickTimeInSeconds = 0.001;
    
    
    
    protected PropertyChangeSupport propertyChangeSupport; 
     public AbstractModel(){
        propertyChangeSupport = new PropertyChangeSupport(this); 
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
    
    public void setTickTimeInSeconds(Double tickTimeInSeconds) {
        this.tickTimeInSeconds = tickTimeInSeconds;
        //setupTimer();
    }
    
    public boolean isRunning(){
        if(timeline != null){
            if(timeline.getStatus()== Animation.Status.RUNNING)
                return true;
        }
            
        return false;
    }
    public void pause(){
        
        timeline.pause();
    }
    
    protected void update(){
/* 
        left this blank, since the digital and analog use different versions of what I 
        need to update, but essentially they use the exact same code for timelines and keyframes.
        In my original stopwatch, I had used the method name of "change" for 
        my digital model. Changed its name to update. This way, I can put the
        timeline and keyframe in the abstract, and then use the super keyword to adjust the 
        method to fit each model's needs for updating their respective information.
*/
    }
    
    public void start() {
        timeline.play();
    }
    
    public void setupTimer() {
        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), (ActionEvent event) -> {
            update();
        });
        
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    public void reset(){
        
        timeline.pause();
        timeline.playFromStart();
        timeline.pause();
    }


}
    
