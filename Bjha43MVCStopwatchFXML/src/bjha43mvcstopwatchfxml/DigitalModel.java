/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bjha43mvcstopwatchfxml;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author Byron Hammann - bjha43
 * Digital Stopwatch Model
 * In my original stopwatch I had the record board functionality included with the digital aspect. 
 * I decided to sepperate them them for the MVC.
 */
public class DigitalModel extends AbstractModel {
    
      	
	private int mins = 0;
        private int secs = 0;
        private int millis = 0; 
        private String output = "--:--.00";
        
        
    public DigitalModel(){
        super.setupTimer();
        
    }
    
    @Override
    public void update() {
                super.update();
                String temp = output;
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
               
                output = ((String.format("%02d", mins)+ ":" + String.format("%02d",secs) + "." + String.format("%03d",millis)));
                firePropertyChange("Digital", temp, output);
    }
    
    @Override
    public void reset()
    {
        super.reset();
        String temp = output;
        millis = 0;
        secs = 0;
        mins = 0;
        output =("00:00.000");
        firePropertyChange("Digital", temp, output);          
    }
   

      
    
}
