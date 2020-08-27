package mpPizza;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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

public class AccountController implements Initializable{

	 @FXML
	    private AnchorPane Apane;

	    @FXML
	    private TextField TF_name;

	    @FXML
	    private Button B_save;

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
	    private ComboBox<String> CB_cardtype;

	    @FXML
	    private PasswordField TF_password;

	    @FXML
	    private Button Back1;

	    @FXML
	    private Button B_logout;
	    
	    String phonenumber, password, name, cardname, cardnumber, address, date, securitycode, cardtype,
	    newrow, tresult, temp, ereason;
	    
	    private Boolean cansave = false, elogout = false;
	    Integer pos;
	    
	    private ArrayList<String> result = new ArrayList<>();


    @FXML
    void exitProgram(ActionEvent event) throws IOException {

    	Stage stage = (Stage) Apane.getScene().getWindow();
    	
    	Alert.AlertType type = Alert.AlertType.CONFIRMATION;

    	Alert alert = new Alert (type, "");
    	
    	alert.initModality(Modality.APPLICATION_MODAL);
    	alert.initOwner(stage);
    	alert.getDialogPane().setContentText("Do you want to logout?");
    	alert.getDialogPane().setHeaderText("Loggin Out...");
    	
    	((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
    	((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Cancel");
    	
    	
    	alert.getDialogPane().getStylesheets().add(
    	   getClass().getResource("application.css").toExternalForm());
    	alert.getDialogPane().getStyleClass().add("myDialog");
    	
    	Optional<ButtonType> result = alert.showAndWait();
    	
    		if(result.get() == ButtonType.OK) 
    		{

    			Parent LoginParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
    	    	Scene LoginScene = new Scene(LoginParent);
    	    	
    	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	    	
    	    	window.setScene(LoginScene);
    	    	window.show();
    			
    		}
    		else if (result.get() == ButtonType.CANCEL) {}
    	
    	
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	
Stage stage = (Stage) Apane.getScene().getWindow();
    	
    	Alert.AlertType type = Alert.AlertType.CONFIRMATION;

    	Alert alert = new Alert (type, "");
    	
    	alert.initModality(Modality.APPLICATION_MODAL);
    	alert.initOwner(stage);
    	alert.getDialogPane().setContentText("");
    	alert.getDialogPane().setHeaderText("Return?");
    	
    	((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
    	((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Cancel");
    	
    	
    	alert.getDialogPane().getStylesheets().add(
    	   getClass().getResource("application.css").toExternalForm());
    	alert.getDialogPane().getStyleClass().add("myDialog");
    	
    	Optional<ButtonType> result = alert.showAndWait();
    	
    		if(result.get() == ButtonType.OK) 
    		{
    			
    			if(elogout == true) {
    				
    				Parent LoginParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        	    	Scene LoginScene = new Scene(LoginParent);
        	    	
        	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        	    	
        	    	window.setScene(LoginScene);
        	    	window.show();
    				
    			} else {
    			
    				FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
    		        Parent mParent = (Parent) loader.load();
    				
    		        SampleController mController = loader.getController();
    		        mController.getUserData(phonenumber, password, name, cardname, cardnumber, 
    		        			address, date, securitycode, cardtype);
    		        
    		    	Scene mScene = new Scene(mParent);
    		    	
    		    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    		    	
    		    	window.setScene(mScene);
    		    	window.show();
    	    	
    			}
    			
    		}
    		else if (result.get() == ButtonType.CANCEL) {}

    	
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
			
			ereason = "Database Access Denied!";
			elogout = true;
			
			final Formatter x;
			x = new Formatter("C:\\Users\\Public\\UserDatabase.txt");
			x.close();
	
			

			e.printStackTrace();
		} 
    	
    }
    
    void checkPhoneNumber() {
    	
    	cansave = false;
    	ereason = "Your record was deleted!" + "\nPlease logout";
    	
    	for (Integer i = 0; i < result.size(); i++) {
			
    		tresult = result.get(i);
	    	
    	    temp = tresult.substring(0, tresult.lastIndexOf("^"));
    	    
    	    if(temp.equals(phonenumber)) {
    	    	
    	    	pos = i;
    	    	cansave = true;
    	    	
    	    } 
			
		}
    	
    }
    
    void validateValues() {
    	
    	if (TF_cardnumber.getText().length() < 16) {
    		
    		cansave = false;
    		ereason = "The card number is invalid!";
    		
    	} else if (TF_securitycode.getText().length() < 3) {
    		
    		cansave = false;
    		ereason = "The security code is invalid!";
    		
    	}
    	
    }
    

    @FXML
    void saveInfo(ActionEvent event) throws IOException {

    	if(TF_name.getText().isEmpty() == true || TF_password.getText().isEmpty() == true || TF_cardname.getText().isEmpty() == true || TF_cardnumber.getText().isEmpty() == true || TF_address.getText().isEmpty() == true || 
    			Datepicker.getValue().toString().equals("") || TF_securitycode.getText().isEmpty() == true || cardtype.equals("")) {
    		
    		Stage stage = (Stage) Apane.getScene().getWindow();
	    	
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
    		
    		if(cansave == true) {
    		
    		name = TF_name.getText();
    		password = TF_password.getText();
    		cardname = TF_cardname.getText();
    		cardnumber = TF_cardnumber.getText();
    		address = TF_address.getText();
    		date = Datepicker.getValue().toString();
    		securitycode = TF_securitycode.getText();
    		cardtype = CB_cardtype.getValue();
    		
    		newrow = (phonenumber + "^" + password + "!" + name + "|" + cardname + "*" + cardnumber + "+" + address + "[" + date + "]" + securitycode + "?" + cardtype);
    		
    		result.set(pos, newrow);
    		
    		File file = new File("C:\\Users\\Public\\UserDatabase.txt");
    		FileWriter fw = new FileWriter(file);
    		
    		for (Integer i = 0; i < result.size(); i++) {
    			
    			fw.write(result.get(i) + "\n");
    			
    		}
    		
    		fw.close();
    		
    		Stage stage = (Stage) Apane.getScene().getWindow();
	    	
	    	Alert.AlertType type = Alert.AlertType.INFORMATION;

	    	Alert alert = new Alert (type, "");
	    	
	    	alert.initModality(Modality.APPLICATION_MODAL);
	    	alert.initOwner(stage);
	    	alert.getDialogPane().setContentText("Your data has been updated.");
	    	alert.getDialogPane().setHeaderText("Saved Successfully!");
	    	
	    	((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("OK");
	    	
	    	
	    	alert.getDialogPane().getStylesheets().add(
	    	   getClass().getResource("application.css").toExternalForm());
	    	alert.getDialogPane().getStyleClass().add("myDialog");
	    	
	    	Optional<ButtonType> result = alert.showAndWait();
	    	
    		
    		} else {
    			
    			Stage stage = (Stage) Apane.getScene().getWindow();
    	    	
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
    	    	
    	    	if(ereason.equals("Your record was deleted!" + "\nPlease logout")) {
    	    		
    	    		elogout = true;
    	    		
    	    	}
    			
    		}
    		
    	}
    	
    }

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
		CB_cardtype.getItems().addAll("Visa", "Mastercard", "American Express"); 
		
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
		
	}
	
	public void getUserData(String s1, String s2, String s3, String s4,
    		String s5, String s6, String s7, String s8, String s9) throws ParseException {
    	
		phonenumber = s1;
    	password = s2;
    	name = s3;
    	cardname = s4;
    	cardnumber = s5;
    	address = s6;
    	date = s7;
    	securitycode = s8;
    	cardtype = s9;
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	LocalDate ld = LocalDate.parse(date, formatter);
    	
    	TF_password.setText(password);
    	TF_name.setText(name);
    	TF_cardname.setText(cardname);
    	TF_cardnumber.setText(cardnumber);
    	TF_address.setText(address);
    	Datepicker.setValue(ld);
    	TF_securitycode.setText(securitycode);
    	CB_cardtype.setValue(cardtype);
    	
    	
    }

}

