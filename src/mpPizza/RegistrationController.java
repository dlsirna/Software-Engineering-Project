package mpPizza;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RegistrationController implements Initializable{

    @FXML
    private AnchorPane Rpane;

    @FXML
    private TextField TF_name;

    @FXML
    private TextField TF_phonenumber;

    @FXML
    private Button B_register;

    @FXML
    private TextField TF_cardname;

    @FXML
    private TextField TF_cardnumber;

    @FXML
    private TextField TF_address;

    @FXML
    private DatePicker Datepicker;

    @FXML
    private PasswordField TF_securitycode;

    @FXML
    private PasswordField TF_password;
    
    @FXML
    private Button Back1;
    
    @FXML
    private ComboBox<String> CB_cardtype;
    
    private String name, phonenumber, cardname, cardnumber,
    		address, date, securitycode, cardtype = "Visa", password, newrow, tresult, temp, ereason;
    
    private Boolean canregister = true;
    
    private ArrayList<String> result = new ArrayList<>();
    
    
    @FXML
    void goBack(ActionEvent event) throws IOException {

    	Parent LmenuParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
    	Scene LmenuScene = new Scene(LmenuParent);
    	
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(LmenuScene);
    	window.show();
    	
    }
    
    void readFile() throws FileNotFoundException{
    	
    	result.clear();
    	
    	try {
      	 
        Scanner s = new Scanner(new FileReader("C:\\Users\\Public\\UserDatabase.txt")); 
        	    
        	while (s.hasNext()) {
        	    	
        	        result.add(s.nextLine());
        	        
        	    }
        	    
        		s.close();
        		
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
			ereason = "Database Access Denied!" + "Try again";
			
			final Formatter x;
			x = new Formatter("C:\\Users\\Public\\UserDatabase.txt");
			x.close();
	

			e.printStackTrace();
		} 
    	
    }
    
    void checkPhoneNumber() {
    	
    	canregister = true;
    	
    	for (Integer i = 0; i < result.size(); i++) {
			
    		tresult = result.get(i);
	    	
    	    temp = tresult.substring(0, tresult.lastIndexOf("^"));
    	    
    	    if(temp.equals( TF_phonenumber.getText().toString())) {
    	    	
    	    	canregister = false;
    	    	
    	    	ereason = "That phone number is already registered!";
    	    	
    	    } 
			
		}
    	
    }
    
    void validateValues() {
    	
    	if (TF_phonenumber.getText().length() < 10) {
    		
    		canregister = false;
    		ereason = "The phone number is invalid!";
    		
    	} else if (TF_cardnumber.getText().length() < 16) {
    		
    		canregister = false;
    		ereason = "The card number is invalid!";
    		
    	} else if (TF_securitycode.getText().length() < 3) {
    		
    		canregister = false;
    		ereason = "The security code is invalid!";
    		
    	}
    	
    }

    @FXML
    void register(ActionEvent event) throws IOException {

    	if(TF_name.getText().isEmpty() == true || TF_phonenumber.getText().isEmpty() == true || TF_cardname.getText().isEmpty() == true || TF_cardnumber.getText().isEmpty() == true || TF_address.getText().isEmpty() == true || 
    			Datepicker.getValue() == null || TF_securitycode.getText().isEmpty() == true || TF_password.getText().isEmpty() == true || cardtype.equals("")) {
    		
    		Stage stage = (Stage) Rpane.getScene().getWindow();
	    	
	    	Alert.AlertType type = Alert.AlertType.ERROR;

	    	Alert alert = new Alert (type, "");
	    	
	    	alert.initModality(Modality.APPLICATION_MODAL);
	    	alert.initOwner(stage);
	    	alert.getDialogPane().setContentText("Please make sure you enter all valid information!");
	    	alert.getDialogPane().setHeaderText("Oops!");
	    	
	    	((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("OK");
	    	
	    	
	    	alert.getDialogPane().getStylesheets().add(
	    	   getClass().getResource("application.css").toExternalForm());
	    	alert.getDialogPane().getStyleClass().add("myDialog");
	    	
	    	Optional<ButtonType> result = alert.showAndWait();
    		
    	} else {
    		
    		readFile();
    		checkPhoneNumber();
    		validateValues();
    		
    		if(canregister == true) {
    		
    		name = TF_name.getText();
    		phonenumber = TF_phonenumber.getText();
    		cardname = TF_cardname.getText();
    		cardnumber = TF_cardnumber.getText();
    		address = TF_address.getText();
    		date = Datepicker.getValue().toString();
    		securitycode = TF_securitycode.getText();
    		password = TF_password.getText();
    		
    		newrow = (phonenumber + "^" + password + "!" + name + "|" + cardname + "*" + cardnumber + "+" + address + "[" + date + "]" + securitycode + "?" + cardtype);
    		
    		result.add(newrow);
    		
    		File file = new File("C:\\Users\\Public\\UserDatabase.txt");
    		FileWriter fw = new FileWriter(file);
    		
    		for (Integer i = 0; i < result.size(); i++) {
    			
    			fw.write(result.get(i) + "\n");
    			
    		}
    		
    		fw.close();
    		
    		Stage stage = (Stage) Rpane.getScene().getWindow();
	    	
	    	Alert.AlertType type = Alert.AlertType.INFORMATION;

	    	Alert alert = new Alert (type, "");
	    	
	    	alert.initModality(Modality.APPLICATION_MODAL);
	    	alert.initOwner(stage);
	    	alert.getDialogPane().setContentText("You will be returned to the login screen");
	    	alert.getDialogPane().setHeaderText("Registered Successfully!");
	    	
	    	((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("OK");
	    	
	    	
	    	alert.getDialogPane().getStylesheets().add(
	    	   getClass().getResource("application.css").toExternalForm());
	    	alert.getDialogPane().getStyleClass().add("myDialog");
	    	
	    	Optional<ButtonType> result = alert.showAndWait();
	    	
	    	Parent LmenuParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
	    	Scene LmenuScene = new Scene(LmenuParent);
	    	
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	
	    	window.setScene(LmenuScene);
	    	window.show();
    		
    		} else {
    			
    			Stage stage = (Stage) Rpane.getScene().getWindow();
    	    	
    	    	Alert.AlertType type = Alert.AlertType.ERROR;

    	    	Alert alert = new Alert (type, "");
    	    	
    	    	alert.initModality(Modality.APPLICATION_MODAL);
    	    	alert.initOwner(stage);
    	    	alert.getDialogPane().setContentText(ereason);
    	    	alert.getDialogPane().setHeaderText("Oops!");
    	    	
    	    	((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("OK");
    	    	
    	    	
    	    	alert.getDialogPane().getStylesheets().add(
    	    	   getClass().getResource("application.css").toExternalForm());
    	    	alert.getDialogPane().getStyleClass().add("myDialog");
    	    	
    	    	Optional<ButtonType> result = alert.showAndWait();
    			
    		}
    		
    	}
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		CB_cardtype.getItems().addAll("Visa", "Mastercard", "American Express");
		CB_cardtype.setValue("Visa");
		
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
          public void handle(ActionEvent e) { 
        	  
              cardtype = (CB_cardtype.getValue().toString()); 
              
          } 
      }; 
      
      CB_cardtype.setOnAction(event); 
		
		
		TF_phonenumber.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		            TF_phonenumber.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		        
		        if (TF_phonenumber.getText().length() > 10) {
		        	
	                String s = TF_phonenumber.getText().substring(0, 10);
	                TF_phonenumber.setText(s);
	                
		        }
		    }
		});
		
		TF_cardnumber.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		            TF_cardnumber.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		        
		        if (TF_cardnumber.getText().length() > 16) {
		        	
	                String s = TF_cardnumber.getText().substring(0, 16);
	                TF_cardnumber.setText(s);
	                
		        }
		        
		    }
		});
		
		
		
		TF_securitycode.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		            TF_securitycode.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		        
		        if (TF_securitycode.getText().length() > 3) {
		        	
	                String s = TF_securitycode.getText().substring(0, 3);
	                TF_securitycode.setText(s);
	                
		        }
		        
		    }
		});
		
		TF_cardname.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\sa-zA-Z*")) {
	        	TF_cardname.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
	        }
	    });
		
		TF_name.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\sa-zA-Z*")) {
	        	TF_name.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
	        }
	    });
		
		TF_address.textProperty().addListener((observable, oldValue, newValue) -> {
	        				
	        	TF_address.setText(newValue.replaceAll("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+", "")); 
	    	
	    });
		
		TF_password.textProperty().addListener((observable, oldValue, newValue) -> {
			
			TF_password.setText(newValue.replaceAll("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+", "")); 
    	
    });

		
	}

}
