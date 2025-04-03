import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI implements ActionListener {
    private JFrame frame;
    private JPanel panel; 
    private JLabel instructions;
    private JTextField latInput; 
    private JTextField lonInput;
    private JButton button; 
    private JLabel success; 
    private WeatherFetcher weatherFetcher = new WeatherFetcher();
    
    public GUI() {
        frame = new JFrame("Weather");
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
   
        //Components
        instructions = new JLabel("Enter Latitude and Longitude");
        panel.add(instructions);
        
        latInput = new JTextField(20);
        lonInput = new JTextField(20);
        
        panel.add(latInput);
        panel.add(lonInput);
        
        button = new JButton("Submit");
        button.addActionListener(this);
        panel.add(button);
        
        success = new JLabel();
        panel.add(success);
        
        // FRAME SETTINGS
        frame.add(panel); 
        frame.setSize(400, 300); // w x h in pixels
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent e) {
     String latitude = latInput.getText();
     String longitude = lonInput.getText();
     
     String result = weatherFetcher.fetchCurrentWeather(latitude, longitude);
     
     if (result != null) {
        double temp = WeatherParser.extractTemperature(result);
        String condition = WeatherParser.extractCondition(result);
        
        success.setText("Temperature: " + temp + " Condition: " + condition);
     } else {
         success.setText("Failed to fetch weather data.");
         }
     
    }
    
    
    }
