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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class GOController implements Initializable{

    @FXML
    private AnchorPane GOCpane;

    @FXML
    private Button Back;

    @FXML
    private Text client;

    @FXML
    private Button B_logout;

    @FXML
    private ListView<HBox> L_forder;

    @FXML
    private Text ftotal;

    @FXML
    private Button B_Confirm;

    @FXML
    private VBox hbcardname;

    @FXML
    private TextField TF_cardname;

    @FXML
    private VBox hbcardnumber;

    @FXML
    private TextField TF_cardnumber;

    @FXML
    private VBox hbaddress;

    @FXML
    private TextField TF_address;

    @FXML
    private RadioButton R_delivery;

    @FXML
    private ToggleGroup Logistic;

    @FXML
    private RadioButton R_pickup;

    @FXML
    private ComboBox<String> CB_payment;

    @FXML
    private VBox hbdate;

    @FXML
    private DatePicker Datepicker;

    @FXML
    private HBox hbcarddetails;

    @FXML
    private PasswordField TF_securitycode;

    @FXML
    private ComboBox<String> CB_cardtype;
	    
	    String cardname, cardnumber, address, date, securitycode, cardtype,
	    logistic, pmethod, transaction, ereason;
	    
	    private Boolean canorder = true;
	    
	    private ArrayList<String> result = new ArrayList<>();
	    
	    private Double total = 0.00;
	    
	    
	    @FXML
	    void setVisual(ActionEvent event) {

	    	if (CB_payment.getValue().toString().equals("Cash") || CB_payment.getValue().toString().equals("Check")) {
	    		
	    		hbcardname.setVisible(false);
	    		hbcardnumber.setVisible(false);
	    		hbcarddetails.setVisible(false);
	    		hbdate.setVisible(false);
	    		
	    			
	    	} else {
	    		
	    		hbcardname.setVisible(true);
	    		hbcardnumber.setVisible(true);
	    		hbcarddetails.setVisible(true);
	    		hbdate.setVisible(true);
	    		
	    	}
	    	
	    }
	    
	    void checkOrder() {
	    	
	    	if(L_forder.getItems().isEmpty() == true) {
	    		
	    		canorder = false;
	    		ereason = "Order is empty! \nPlease return to menu.";
	    		
	    	} else {}
	    	
	    }
	    
	    void readFile() throws FileNotFoundException{
	    	
	    	canorder = true;
	    	
	    	result.clear();
	    	
	    	try {
	      	 
	        Scanner s = new Scanner(new FileReader("C:\\Users\\Public\\Transactions.txt")); 
	        	    
	        	while (s.hasNext()) {
	        	    	
	        	        result.add(s.nextLine());
	        	        
	        	    }
	        	    
	        		s.close();
	        		
	    	} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				
				final Formatter x;
				x = new Formatter("C:\\Users\\Public\\Transactions.txt");
				x.close();
				
				canorder = false;
				ereason = "Database Access Denied. \n Try Again";

				e.printStackTrace();
			} 
	    	
	    }

	    void getLogistic() {
	    	
	    	if(R_delivery.isSelected() == true) {
	    		
	    		logistic = "Delivery";
	    		
	    	} else {
	    		
	    		logistic = "Pickup";
	    		
	    	}
	     		
	    }
	    
 void getPmethod() {
	    	
	    	pmethod = CB_payment.getValue().toString();
	     		
	    }
 
 void validateValues() {
 	
 	 if (TF_cardnumber.getText().length() < 16) {
 		
 		canorder = false;
 		ereason = "The card number is invalid!";
 		
 	} else if (TF_securitycode.getText().length() < 3) {
 		
 		canorder = false;
 		ereason = "The security code is invalid!";
 		
 	}
 	 
 	
 }
 
 void makeString() {
	 
		getLogistic();
    	getPmethod();
    	
    	address = TF_address.getText().toString();
		cardname = TF_cardname.getText().toString();
		cardnumber = TF_cardnumber.getText().toString();
		cardtype = CB_cardtype.getValue().toString();
		securitycode = TF_securitycode.getText().toString();
    	
    	total = Double.parseDouble(ftotal.getText());
	 
    	if (logistic == "Delivery" && pmethod == "Card" && !address.equals("") &&
    			!cardname.equals("") && !cardnumber.equals("") && !cardtype.equals("")
    			&& !securitycode.equals("") && Datepicker.getValue() != null) {
    		
    		date = Datepicker.getValue().toString();
    		
    		validateValues();
    		
    		
    		transaction = "  Name:" + cardname + "  Card Number:" + cardnumber + 
        			"  Card Type:" + cardtype + "  Address:" + address + "  Logistic:" + logistic + 
        			"  Payment Method:" + pmethod + "  Total: $" + total.toString();
    		
    		
    	} else if (!address.equals("") && logistic == "Delivery" && (pmethod == "Cash" || pmethod == "Check")) {
    		
    		address = TF_address.getText().toString();
    		
    		transaction = "  Name:Guest" + "  Address:" + address + "  Logistic:" + logistic + 
        			"  Payment Method:" + pmethod + "  Total: $" + total.toString();
    		
    	} else if (logistic == "Pickup" && pmethod == "Card" && !cardname.equals("") && !cardnumber.equals("") 
    			&& !cardtype.equals("") && !securitycode.equals("") && Datepicker.getValue() != null) {
    		
    		date = Datepicker.getValue().toString();
    		validateValues();
    		
    		transaction = "  Name:" + cardname + "  Card Number:" + cardnumber + 
        			"  Card Type:" + cardtype + "  Logistic:" + logistic + 
        			"  Payment Method:" + pmethod + "  Total: $" + total.toString();
    		
    	} else if (!address.equals("") && logistic == "Pickup" && (pmethod == "Cash" || pmethod == "Check")) {
    		
    		transaction = "  Name:Guest" + "  Address:" + address + "  Logistic:" + logistic + 
        			"  Payment Method:" + pmethod + "  Total: $" + total.toString();
    		
    	} else {
    		
    		canorder = false;
    		ereason = "Please input all valid needed information!";
    		
    	}
    	
    	
 }
	    
    @FXML
    void confirm(ActionEvent event) throws IOException {
    	
    	readFile();
    	makeString();
    	checkOrder();
    	
    	if (canorder == true) {
    	
    	result.add(transaction);
		
		File file = new File("C:\\Users\\Public\\Transactions.txt");
		FileWriter fw = new FileWriter(file);
		
		for (Integer i = 0; i < result.size(); i++) {
			
			fw.write(result.get(i) + "\n");
				
		}
		
		fw.close();
    	
		Stage stage = (Stage) GOCpane.getScene().getWindow();
    	
    	Alert.AlertType type = Alert.AlertType.INFORMATION;

    	Alert alert = new Alert (type, "");
    	
    	alert.initModality(Modality.APPLICATION_MODAL);
    	alert.initOwner(stage);
    	alert.getDialogPane().setContentText("You will be returned to the menu screen");
    	alert.getDialogPane().setHeaderText("Ordered Successfully!");
    	
    	((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("OK");
    	
    	
    	alert.getDialogPane().getStylesheets().add(
    	   getClass().getResource("application.css").toExternalForm());
    	alert.getDialogPane().getStyleClass().add("myDialog");
    	
    	Optional<ButtonType> result = alert.showAndWait();
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("GuestMenu.fxml"));
        Parent mParent = (Parent) loader.load();   
        
    	Scene mScene = new Scene(mParent);
    	
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(mScene);
    	window.show();
    	
    	} else {
    		
    		Stage stage = (Stage) GOCpane.getScene().getWindow();
	    	
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
    	

    @FXML
    void exitProgram(ActionEvent event) throws IOException {

    	Stage stage = (Stage) GOCpane.getScene().getWindow();
    	
    	Alert.AlertType type = Alert.AlertType.CONFIRMATION;

    	Alert alert = new Alert (type, "");
    	
    	alert.initModality(Modality.APPLICATION_MODAL);
    	alert.initOwner(stage);
    	alert.getDialogPane().setContentText("Do you want to logout?\nOrder will be canceled!");
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
    	
    	Stage stage = (Stage) GOCpane.getScene().getWindow();
    	
    	Alert.AlertType type = Alert.AlertType.CONFIRMATION;

    	Alert alert = new Alert (type, "");
    	
    	alert.initModality(Modality.APPLICATION_MODAL);
    	alert.initOwner(stage);
    	alert.getDialogPane().setContentText("Order will be canceled!");
    	alert.getDialogPane().setHeaderText("Return?");
    	
    	((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
    	((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Cancel");
    	
    	
    	alert.getDialogPane().getStylesheets().add(
    	   getClass().getResource("application.css").toExternalForm());
    	alert.getDialogPane().getStyleClass().add("myDialog");
    	
    	Optional<ButtonType> result = alert.showAndWait();
    	
    		if(result.get() == ButtonType.OK) 
    		{

    			
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("GuestMenu.fxml"));
		        Parent mParent = (Parent) loader.load();
		        
		    	Scene mScene = new Scene(mParent);
		    	
		    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		    	
		    	window.setScene(mScene);
		    	window.show();
		    	
    			
    			
    		}
    		else if (result.get() == ButtonType.CANCEL) {}

    }

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		R_delivery.setSelected(true);
		
		CB_payment.getItems().addAll("Card", "Cash", "Check");
		CB_payment.setValue("Card");
		
		CB_cardtype.getItems().addAll("Visa", "Mastercard", "American Express");
		CB_cardtype.setValue("Visa");
		
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
		
		TF_address.textProperty().addListener((observable, oldValue, newValue) -> {
			
        	TF_address.setText(newValue.replaceAll("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+", "")); 
    	
    });
		
	}
	
	public void getOrderedItems(HBox[] hbarray, Double total) {
		// TODO Auto-generated method stub
	
    	Integer hbnum = new Integer (hbarray.length);
    	
    	ftotal.setText(total.toString());
    	
    	for (int i = 0; i < hbnum; i++) {
            
    		HBox hbox = new HBox();
    		hbox = hbarray[i];
    		
    		if (hbox != null) {
    			
    			ObservableList<Node> hbchildren = hbox.getChildren();
	    		Button remove = (Button)hbchildren.get(0);
	    		
	    		remove.setOnAction(new EventHandler<ActionEvent>() {
		    	    @Override public void handle(ActionEvent e) {
		    	    	
		    	    	HBox hbox = (HBox)remove.getParent();
			    		
			    		ObservableList<Node> hbchildren = hbox.getChildren();
			    		VBox fvbox = (VBox)hbchildren.get(1);
			    		
			    		ObservableList<Node> vbchildren = fvbox.getChildren();
			    		Integer vs = new Integer(vbchildren.size());
			    		
			    		if(vs == 1) {
			    			
			    			HBox hbox1 = (HBox)vbchildren.get(0);
				    		
				    		ObservableList<Node> hb1children = hbox1.getChildren();
				    		HBox hbox2 = (HBox)hb1children.get(1);
				    		
				    		ObservableList<Node> hb2children = hbox2.getChildren();
				    		Text dsprice = (Text)hb2children.get(1);
			    			
				    		Double d1 = new Double (Double.parseDouble(dsprice.getText()));
				    		
			    			Double ndstotal = Double.parseDouble(ftotal.getText());
			    			Double newtotal = new Double(ndstotal-d1);
				    		
				    		ftotal.setText(newtotal.toString());
				    		L_forder.getItems().remove(remove.getParent());
				    		
			    		} else {
			    		
			    		HBox hbox1 = (HBox)vbchildren.get(0);
			    		Text toppings = (Text)vbchildren.get(1);
			    		String tp = toppings.getText().toString();
			    		
			    		ObservableList<Node> hb1children = hbox1.getChildren();
			    		HBox hbox2 = (HBox)hb1children.get(1);
			    		
			    		ObservableList<Node> hb2children = hbox2.getChildren();
			    		Text pizzaprice = (Text)hb2children.get(1);
			    		
			    		Double p1 = new Double (Double.parseDouble(pizzaprice.getText()));
			    		Double p2 = new Double (Double.parseDouble(tp.substring(tp.lastIndexOf("$") + 1)));
			    		
			    		
			    		Double nptotal = Double.parseDouble(ftotal.getText());
			    		Double reduce = new Double (p1+p2);
			    		
			    		Double newtotal = new Double(nptotal-reduce);
			    		
			    		ftotal.setText(newtotal.toString());
		    	    	
		    	    	L_forder.getItems().remove(remove.getParent());
		    	    	
			    		}
		    	    	
		    	    }
		    	});
    			
    			L_forder.getItems().add(hbox);
    			
    		}
		
    	} 
	
	}


}
