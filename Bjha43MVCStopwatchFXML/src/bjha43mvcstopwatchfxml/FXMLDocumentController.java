/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bjha43mvcstopwatchfxml;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 *
 * @author Byron Hammann - bjha43
 */
public class FXMLDocumentController implements Initializable, PropertyChangeListener {
    
    @FXML
    private Label label;
     @FXML
    private Button startButton;
    @FXML
    private Button recButton;
    @FXML
    TextField text;
    @FXML
    TextField lap1;
    @FXML
    TextField lap2;
    @FXML
    TextField lap3;
    @FXML
    ImageView hand;
    @FXML
    ImageView hand1;
    @FXML
    AnalogModel analog2;
    @FXML
    AnalogModel analog;
    @FXML
    DigitalModel digital;
    @FXML
    RecordBoard record;
    
    
    public String title = "Stopwatch";
    public int width = 600; 
    public int height = 400;

    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
     private void startButtonAction(ActionEvent event) {
        
        if("Start".equals(startButton.getText())&& analog.isRunning()== false)
        {
            analog.start();
            analog2.start();
            
            startButton.setText("Stop");
            recButton.setText("Record");
            digital.start();
            record.start();
        }
        else if("Stop".equals(startButton.getText())&& analog.isRunning()== true)
        {
            recButton.setText("Reset");
            analog.pause();
            analog2.pause();
            
            startButton.setText("Start");
            recButton.setText("Reset");
            digital.pause();
            record.pause();
        }
    }
     
     @FXML
     private void recButtonAction(ActionEvent event){
           
           if(startButton.getText() == "Start")
           {
               analog.reset();
               analog2.reset();
               digital.reset();
               record.reset();
               recButton.setText("Record");
               hand.setRotate(0);
               hand1.setRotate(0);
               
           }
           if("Record".equals(recButton.getText())){
               if(analog.isRunning()){
                 record.setLap();
                 recButton.setText("Record");
               }
           }};
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        analog = new AnalogModel();
        analog2 = new AnalogModel(60.0,0.1);
       // analog.setupTimer();
        hand.setRotate(0);
        hand1.setRotate(0);
        digital = new DigitalModel();
        record = new RecordBoard();
        
        analog.addPropertyChangeListener(this);
        analog2.addPropertyChangeListener(this);
        digital.addPropertyChangeListener(this);
        record.addPropertyChangeListener(this);
        
    }    
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // I tried using "else if" statements like shown in class
        // but could not then get all the property change fires to happen.
        
        if(evt.getPropertyName().equals("Analog"))
        {
            hand.setRotate((double) evt.getNewValue());
        }
        if(evt.getPropertyName().equals("Analog2"))
        {
            hand1.setRotate((double) evt.getNewValue());
        }
        if(evt.getPropertyName().equals("Digital"))
        {
                    text.setText((String)evt.getNewValue()); 
        }
        if(evt.getPropertyName().equals("Lap1"))
        {
            lap1.setText((String)evt.getNewValue());
        }
        if(evt.getPropertyName().equals("Lap2"))
        {
            lap2.setText((String)evt.getNewValue());
        }
        if(evt.getPropertyName().equals("Lap3"))
        {
            lap3.setText((String)evt.getNewValue());
        }
        
         
    }
    
}
