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
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class OController implements Initializable{

	  @FXML
	    private AnchorPane OCpane;

	    @FXML
	    private Text T_clientname;

	    @FXML
	    private Text T_cardtype;

	    @FXML
	    private Text T_address;

	    @FXML
	    private ComboBox<String> CB_payment;

	    @FXML
	    private Button B_Confirm;

	    @FXML
	    private VBox pDrinks;

	    @FXML
	    private RadioButton R_delivery;

	    @FXML
	    private ToggleGroup Logistic;

	    @FXML
	    private RadioButton R_pickup;

	    @FXML
	    private ListView<HBox> L_forder;

	    @FXML
	    private Text ftotal;

	    @FXML
	    private Button Back;

	    @FXML
	    private Button B_logout;

	    @FXML
	    private Text OCdescription;
	    
	    @FXML
	    private Text client;
	    
	    String phonenumber, password, name, cardname, cardnumber, address, date, securitycode, cardtype,
	    logistic, pmethod, transaction, ereason;
	    
	    private Boolean canorder = true;
	    
	    private ArrayList<String> result = new ArrayList<>();
	    
	    private Double total = 0.00;
	    
	    
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
	    
    @FXML
    void confirmOrder(ActionEvent event) throws IOException {
    	
    	readFile();
    	checkOrder();
    	
    	if (canorder == true) {
    	
    	getLogistic();
    	getPmethod();
    	
    	total = Double.parseDouble(ftotal.getText());
    	
    	if (pmethod == "Card") {
    		
    		transaction = "Phone Number: " + phonenumber + "  Name: " + cardname + "  Card Number: " + cardnumber + 
        			"  Card Type: " + cardtype + "  Address: " + address + "  Logistic: " + logistic + 
        			"  Payment Method: " + pmethod + "  Total: $" + total.toString();
    		
    	} else if (pmethod == "Cash") {
    		
    		transaction = "Phone Number: " + phonenumber + "  Name: " + cardname + "  Address: " + address + "  Logistic: " + logistic + 
        			"  Payment Method: " + pmethod + "  Total: $" + total.toString();
    		
    	} else if (pmethod == "check") {
    		
    		transaction = "Phone Number: " + phonenumber + "  Name: " + cardname + "  Address: " + address + "  Logistic: " + logistic + 
        			"  Payment Method: " + pmethod + "  Total: $" + total.toString();
    		
    	}
    	
    	result.add(transaction);
		
		File file = new File("C:\\Users\\Public\\Transactions.txt");
		FileWriter fw = new FileWriter(file);
		
		for (Integer i = 0; i < result.size(); i++) {
			
			fw.write(result.get(i) + "\n");
				
		}
		
		fw.close();
    	
		Stage stage = (Stage) OCpane.getScene().getWindow();
    	
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
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent mParent = (Parent) loader.load();
		
        SampleController mController = loader.getController();
        mController.getUserData(phonenumber, password, name, cardname, cardnumber, 
        			address, date, securitycode, cardtype);
        
    	Scene mScene = new Scene(mParent);
    	
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(mScene);
    	window.show();
    	
    	} else {
    		
    		Stage stage = (Stage) OCpane.getScene().getWindow();
	    	
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

    	Stage stage = (Stage) OCpane.getScene().getWindow();
    	
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
    	
    	Stage stage = (Stage) OCpane.getScene().getWindow();
    	
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
    		else if (result.get() == ButtonType.CANCEL) {}

    }

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		R_delivery.setSelected(true);
		
		CB_payment.getItems().addAll("Card", "Cash", "Check");
		CB_payment.setValue("Card");
		
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

	public void getUserData(String s1, String s2, String s3, String s4, String s5,
			String s6, String s7, String s8, String s9) {
		
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
    	T_clientname.setText(cardname);
    	T_cardtype.setText(cardtype);
    	T_address.setText(address);
    	
    	
		
	}

}
