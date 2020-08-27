package mpPizza;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Optional;
import java.util.Scanner;

import javax.net.ssl.SSLContext;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController{

    @FXML
    private AnchorPane Lpane;

    @FXML
    private TextField TF_phonenumber;

    @FXML
    private PasswordField TF_password;

    @FXML
    private Button B_login;

    @FXML
    private Button B_guest;

    @FXML
    private Button B_recover;

    @FXML
    private Button B_register;
    
    private ArrayList<String> result = new ArrayList<>();
    private String phonenumber, password, name, cardname, cardnumber, 
    		address, date, securitycode, cardtype, tresult, temp, ereason, tpass;
    private Boolean exists = false, match = false; 
    private Integer pos;
    
    @FXML
    void gotoMenu(ActionEvent event) throws IOException {

    	Parent MenuParent = FXMLLoader.load(getClass().getResource("GuestMenu.fxml"));
    	Scene MenuScene = new Scene(MenuParent);
    	
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(MenuScene);
    	window.show();
    	
    }
    
    void readFile() throws FileNotFoundException{
    	
    	result.clear();
   	 
		try {
			
			Scanner s;
			s = new Scanner(new FileReader("C:\\Users\\Public\\UserDatabase.txt"));
			
			while (s.hasNext()) {
    	    	
    	        result.add(s.nextLine());
    	        
    	    }
    	    
    		s.close();
    		
    		if(result.isEmpty() == true) { 
    			
    			ereason = "The user doesn't exist! Please check your Phone Number";
    			
    		}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
			ereason = "Database file is now created!" + "\n Try again";
			
			
				
				final Formatter x;
				x = new Formatter("C:\\Users\\Public\\UserDatabase.txt");
				x.close();
		
			
			
			e.printStackTrace();
		} 
        	    
    	
    }
    
    void getPhoneNumber() {
    	
    	exists = false;
    	
    	for(Integer i = 0; i < result.size(); i++) {
	    	
    	    tresult = result.get(i);
    	    	
    	    temp = tresult.substring(0, tresult.lastIndexOf("^"));
    	    
    	    if(temp.equals( TF_phonenumber.getText().toString())) {
    	    	
    	    	pos = i;
    	    	phonenumber = temp;
    	    	System.out.println(phonenumber);
    	    	exists = true;
    	    	
    	    } else {
    	    	
    	    	ereason = "The user doesn't exist! \nPlease check your Phone Number";
    	    	
    	    }
    	    
    	} 
    	
    }
    
    void checkPassword() {
    	
    	match = false;
    	
    	if (pos != null) {
    	
    	tpass = result.get(pos);
    	
    	temp = tpass.substring(tpass.lastIndexOf("^") + 1, tpass.lastIndexOf("!"));
    	
    	if(temp.equals(TF_password.getText().toString())) {
    		
    		password = temp;
    		match = true;
    		System.out.println(password);
    		
    	} else {
    		
    		ereason = "The phone number and password don't match!";
    		
    	}
    	
    	
    }
    	
    	
  }
    
void getData() {
	    	
    	    tresult = result.get(pos);
    	    	
    	    phonenumber = tresult.substring(0, tresult.lastIndexOf("^"));
    	    password = tresult.substring(tpass.lastIndexOf("^") + 1, tpass.lastIndexOf("!"));
    	    name = tresult.substring(tpass.lastIndexOf("!") + 1, tpass.lastIndexOf("|"));
    	    cardname = tresult.substring(tpass.lastIndexOf("|") + 1, tpass.lastIndexOf("*"));
    	    cardnumber = tresult.substring(tpass.lastIndexOf("*") + 1, tpass.lastIndexOf("+"));
    	    address = tresult.substring(tpass.lastIndexOf("+") + 1, tpass.lastIndexOf("["));
    	    date = tresult.substring(tpass.lastIndexOf("[") + 1, tpass.lastIndexOf("]"));
    	    securitycode = tresult.substring(tpass.lastIndexOf("]") + 1, tpass.lastIndexOf("?"));
    	    cardtype = tresult.substring(tpass.lastIndexOf("?") + 1, tpass.lastIndexOf(""));
    
    }
    
    @FXML
    void login(ActionEvent event) throws IOException {

    	    readFile();
    	    getPhoneNumber();
    	    checkPassword();
    	    
    	    if (exists == false || match == false) {
    	    	
    	    	Stage stage = (Stage) Lpane.getScene().getWindow();
    	    	
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
    	    	
    	    } else {
    	    	
    	    	getData();
    	    	
    	    	Stage stage = (Stage) Lpane.getScene().getWindow();
    	    	
    	    	Alert.AlertType type = Alert.AlertType.INFORMATION;

    	    	Alert alert = new Alert (type, "");
    	    	
    	    	alert.initModality(Modality.APPLICATION_MODAL);
    	    	alert.initOwner(stage);
    	    	alert.getDialogPane().setContentText("");
    	    	alert.getDialogPane().setHeaderText("Logged In Successfully!");
    	    	
    	    	((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("GO");
    	    	
    	    	
    	    	alert.getDialogPane().getStylesheets().add(
    	    	   getClass().getResource("application.css").toExternalForm());
    	    	alert.getDialogPane().getStyleClass().add("myDialog");
    	    	
    	    	Optional<ButtonType> result = alert.showAndWait();
    	    	
    	    	
    	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
                Parent MenuParent = (Parent) loader.load();
    			
                SampleController mController = loader.getController();
                mController.getUserData(phonenumber, password, name, cardname, cardnumber, 
                			address, date, securitycode, cardtype);
                
    	    	Scene MenuScene = new Scene(MenuParent);
    	    	
    	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	    	
    	    	window.setScene(MenuScene);
    	    	window.show();
    	    	
    	    	
    	    }
    	
    }

    @FXML
    void gotoRegistration(ActionEvent event) throws IOException {

    	Parent RmenuParent = FXMLLoader.load(getClass().getResource("Registration.fxml"));
    	Scene RmenuScene = new Scene(RmenuParent);
    	
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(RmenuScene);
    	window.show();
    	
    	
    }

    @FXML
    void sendRecoveryPin(ActionEvent event) {

    	
    	
    }

}
