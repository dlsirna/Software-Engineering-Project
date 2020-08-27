package mpPizza;


import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class SampleController{

	
    @FXML
    private Button B_logout;
    
    @FXML
    private Button B_createorder;

    @FXML
    private Button B_account;
    
    @FXML
    private AnchorPane Mpane;
    
    @FXML
    private Text client;
    
    String phonenumber, password, name, cardname, cardnumber, address, date, securitycode, cardtype;
    
    
    
    @FXML
    void exitProgram(ActionEvent event) throws IOException {
    	
    	Stage stage = (Stage) Mpane.getScene().getWindow();
    	
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
    void gotoAccount(ActionEvent event) throws IOException, ParseException {

    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Account.fxml"));
        Parent AParent = (Parent) loader.load();
		
        AccountController aController = loader.getController();
        aController.getUserData(phonenumber, password, name, cardname, cardnumber, 
        			address, date, securitycode, cardtype);
        
    	Scene AScene = new Scene(AParent);
    	
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(AScene);
    	window.show();
    	
    }

    @FXML
    void gotoBasics(ActionEvent event) throws IOException {

    	FXMLLoader loader = new FXMLLoader(getClass().getResource("BTmenu.fxml"));
        Parent btParent = (Parent) loader.load();
		
        BTmenuController btController = loader.getController();
        btController.getUserData(phonenumber, password, name, cardname, cardnumber, 
        			address, date, securitycode, cardtype);
        
    	Scene btScene = new Scene(btParent);
    	
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(btScene);
    	window.show();
    	
    }
    
    public void getUserData(String s1, String s2, String s3, String s4,
    		String s5, String s6, String s7, String s8, String s9) {
    	
    	phonenumber = s1;
    	password = s2;
    	name = s3;
    	cardname = s4;
    	cardnumber = s5;
    	address = s6;
    	date = s7;
    	securitycode = s8;
    	cardtype = s9;
    	
    	client.setText(name);
    	
    }


}

	

