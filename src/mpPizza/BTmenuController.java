package mpPizza;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BTmenuController implements Initializable {
	
	
	 @FXML
	    private AnchorPane BTpane;

	    @FXML
	    private Button B_Continue;

	    @FXML
	    private VBox pBasics;

	    @FXML
	    private ToggleButton B_basics;

	    @FXML
	    private ToggleButton B_toppings;

	    @FXML
	    private VBox pToppings;

	    @FXML
	    private Button B_plus;
	    
	    @FXML
	    private Text pnum;

	    @FXML
	    private Button B_minus;

	    @FXML
	    private Button B_add;

	    @FXML
	    private Button Back1;

	    @FXML
	    private Button B_logout;
	    
	    @FXML
	    private Text BTdescription;
	    
	    @FXML
	    private RadioButton R_thin;

	    @FXML
	    private ToggleGroup Crusts;

	    @FXML
	    private RadioButton R_regular;

	    @FXML
	    private RadioButton R_pan;

	    @FXML
	    private RadioButton R_small;

	    @FXML
	    private ToggleGroup Sizes;

	    @FXML
	    private RadioButton R_medium;

	    @FXML
	    private RadioButton R_large;

	    @FXML
	    private RadioButton R_giant;
	    
	    @FXML
	    private CheckBox tpepperoni;

	    @FXML
	    private CheckBox tsausage;

	    @FXML
	    private CheckBox tbacon;

	    @FXML
	    private CheckBox tcheese;

	    @FXML
	    private CheckBox tpeppers;

	    @FXML
	    private CheckBox tonions;

	    @FXML
	    private CheckBox ttomatoes;

	    @FXML
	    private Text ototal;
	    
	    @FXML
	    private Text client;

	    @FXML
	    private ListView<HBox> L_pizzas;
	    
	    private String crust, size, tquantity, tproduct, t1, t2, t3, t4, txtprice;
	    String phonenumber, password, name, cardname, cardnumber, address, date, securitycode, cardtype;
	    private Double tprice = 0.00, ptotal = 0.00, xtprice = 0.50, txtp = 0.00, bttotal = 0.00;
	    private Integer tcount = 0, pcount = 1;
	    private Boolean adding = false;
	    HBox[] hbarray = new HBox[20];
	    
	    
	 
	 @FXML
 	    void exitProgram(ActionEvent event) throws IOException {
	    	
	    	Stage stage = (Stage) BTpane.getScene().getWindow();
	    	
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


		Stage stage = (Stage) BTpane.getScene().getWindow();
    	
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

    			if(client.getText().toString().equals("Guest") && name != "Guest") {
    				
    				Parent gmParent = FXMLLoader.load(getClass().getResource("GuestMenu.fxml"));
    		    	Scene gmScene = new Scene(gmParent);
    		    	
    		    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    		    	
    		    	window.setScene(gmScene);
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
	
	 @FXML
	    void showBasics(ActionEvent event) {
		 
		 if(B_basics.isSelected() == true) {
			 
			 pBasics.setVisible(true);
			 pToppings.setVisible(false);
			 BTdescription.setText("Customize your pizza however you want!");
			 
		 } else { 
		 
		 B_basics.setSelected(true);
		 
		 }

	    }

	    @FXML
	    void showToppings(ActionEvent event) {
	    	
	    	if(B_toppings.isSelected() == true) {
				 
	    		pBasics.setVisible(false);
				 pToppings.setVisible(true);
				 
				 BTdescription.setText("Cheese and first topping are free! More toppings add extra cost.");
				 
			 } else { 
			 
			 B_toppings.setSelected(true);
			 
			 }

	    }

	    void getOrderItems(){
	    	
	    	Integer listsize = new Integer (L_pizzas.getItems().size());
	    	
	    	
	    	for (int i = 0; i < listsize; i++) {
                
	    		ObservableList<HBox> Lchildren = L_pizzas.getItems();
	    		HBox hbox = (HBox)Lchildren.get(i);
	    		
	    		hbarray[i] = hbox;
	    		System.out.println(i);
	    	}
	    	
	    }
	    
	    @FXML
	    void continue1(ActionEvent event) throws IOException {
	    	
	    	getOrderItems();

	    	Stage stage = (Stage) BTpane.getScene().getWindow();
	    	
	    	Alert.AlertType type = Alert.AlertType.CONFIRMATION;

	    	Alert alert = new Alert (type, "");
	    	
	    	alert.initModality(Modality.APPLICATION_MODAL);
	    	alert.initOwner(stage);
	    	alert.getDialogPane().setContentText("Do you want any drinks or sides?");
	    	alert.getDialogPane().setHeaderText("Continue");
	    	
	    	((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
	    	((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
	    	
	    	
	    	alert.getDialogPane().getStylesheets().add(
	    	   getClass().getResource("application.css").toExternalForm());
	    	alert.getDialogPane().getStyleClass().add("myDialog");
	    	
	    	Optional<ButtonType> result = alert.showAndWait();
	    	
	    		if(result.get() == ButtonType.OK) 
	    		{
	    			
	    			if(client.getText().toString().equals("Guest") && name != "Guest") {
	    				
	    				FXMLLoader loader = new FXMLLoader(getClass().getResource("DSmenu.fxml"));
		    	        Parent dsParent = (Parent) loader.load();
		    			
		    	        DSmenuController dsController = loader.getController();
		    	        
		    	        dsController.getOrderedItems(hbarray, bttotal);
		    	        
		    	    	Scene dsScene = new Scene(dsParent);
		    	    	
		    	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		    	    	
		    	    	window.setScene(dsScene);
		    	    	window.show();
	    				
	    			} else {
	    			
	    			FXMLLoader loader = new FXMLLoader(getClass().getResource("DSmenu.fxml"));
	    	        Parent dsParent = (Parent) loader.load();
	    			
	    	        DSmenuController dsController = loader.getController();
	    	        dsController.getUserData(phonenumber, password, name, cardname, cardnumber, 
	    	        			address, date, securitycode, cardtype);
	    	        
	    	        dsController.getOrderedItems(hbarray, bttotal);
	    	        
	    	    	Scene dsScene = new Scene(dsParent);
	    	    	
	    	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	    	
	    	    	window.setScene(dsScene);
	    	    	window.show();
	    	    	
	    			}
	    			
	    		}
	    		else if (result.get() == ButtonType.CANCEL) {
	    			
	    			if(client.getText().toString().equals("Guest") && name != "Guest") {
	    			
	    			FXMLLoader loader = new FXMLLoader(getClass().getResource("GuestOC.fxml"));
	    	        Parent gocParent = (Parent) loader.load();
	    			
	    	        GOController gocController = loader.getController();
	    	        
	    	        gocController.getOrderedItems(hbarray, bttotal);
	    	        
	    	    	Scene gocScene = new Scene(gocParent);
	    	    	
	    	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	    	
	    	    	window.setScene(gocScene);
	    	    	window.show();
	    	    	
	    			} else {
	    				
	    				FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderConfirmation.fxml"));
		    	        Parent ocParent = (Parent) loader.load();
		    			
		    	        OController ocController = loader.getController();
		    	        ocController.getUserData(phonenumber, password, name, cardname, cardnumber, 
		    	        			address, date, securitycode, cardtype);
		    	        
		    	        ocController.getOrderedItems(hbarray, bttotal);
		    	        
		    	    	Scene ocScene = new Scene(ocParent);
		    	    	
		    	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		    	    	
		    	    	window.setScene(ocScene);
		    	    	window.show();
	    				
	    			}
	    			
	    		}
	    	
	    }
	    
	    String selectedBasics() {
	    	
	    	if(R_thin.isSelected() == true) {
	    		
	    		crust = "Thin";
	    		
	    	} else if (R_regular.isSelected() == true) {
	    		
	    		crust = "Regular";
	    		
	    	} else if (R_pan.isSelected() == true) {
	    		
	    		crust = "Pan";
	    		
	    	}
	    	
	    	if(R_small.isSelected() == true) {
	    		
	    		size = "Small";
	    		tprice = 4.00;
	    		xtprice = 0.50;
	    		
	    	} else if (R_medium.isSelected() == true) {
	    		
	    		size = "Medium";
	    		tprice = 6.00;
	    		xtprice = 0.75;
	    		
	    	} else if (R_large.isSelected() == true) {
	    		
	    		size = "Large";
	    		tprice = 8.00;
	    		xtprice = 1.00;
	    		
	    	} else if (R_giant.isSelected() == true) {
	    		
	    		size = "Giant";
	    		tprice = 10.00;
	    		xtprice = 1.25;
	    		
	    	}
	    	
	    	tproduct = crust + " " + size + " Pizza";
	    	
	    	return tproduct;
	    	
	    }
	    
	    void selectedToppings() {
	    	
	    	tcount = 0;
	    	t1 = "";
	    	t2 = "";
	    	t3 = "";
	    	t4 = "";
	    	
	    	if(tpepperoni.isSelected() == true) {
	    		
	    		tcount = tcount + 1;
	    		
	    		if(t1 == "") {
	    			
	    			t1 = "Pepperoni";
	    			
	    		} else if(t2 == "") {
	    			
	    			t2 = "Pepperoni";
	    			
	    		} else if(t3 == "") {
	    			
	    			t3 = "Pepperoni";
	    			
	    		} else if(t4 == "") {
	    			
	    			t4 = "Pepperoni";
	    			
	    		}
	    		
	    	} if(tsausage.isSelected() == true) {
	    		
	    		tcount = tcount + 1;
	    		
	    		if(t1 == "") {
	    			
	    			t1 = "Sausage";
	    			
	    		} else if(t2 == "") {
	    			
	    			t2 = "Sausage";
	    			
	    		} else if(t3 == "") {
	    			
	    			t3 = "Sausage";
	    			
	    		} else if(t4 == "") {
	    			
	    			t4 = "Sausage";
	    			
	    		}
	    		
	    	} if(tbacon.isSelected() == true) {
	    		
	    		tcount = tcount + 1;
	    		
	    		if(t1 == "") {
	    			
	    			t1 = "Bacon";
	    			
	    		} else if(t2 == "") {
	    			
	    			t2 = "Bacon";
	    			
	    		} else if(t3 == "") {
	    			
	    			t3 = "Bacon";
	    			
	    		} else if(t4 == "") {
	    			
	    			t4 = "Bacon";
	    			
	    		}
	    		
	    	} if(tcheese.isSelected() == true) {
	    		
	    		
	    		if(t1 == "") {
	    			
	    			t1 = "Cheese";
	    			
	    		} else if(t2 == "") {
	    			
	    			t2 = "Cheese";
	    			
	    		} else if(t3 == "") {
	    			
	    			t3 = "Cheese";
	    			
	    		} else if(t4 == "") {
	    			
	    			t4 = "Cheese";
	    			
	    		}
	    		
	    	} if(tpeppers.isSelected() == true) {
	    		
	    		tcount = tcount + 1;
	    		
	    		if(t1 == "") {
	    			
	    			t1 = "Peppers";
	    			
	    		} else if(t2 == "") {
	    			
	    			t2 = "Peppers";
	    			
	    		} else if(t3 == "") {
	    			
	    			t3 = "Peppers";
	    			
	    		} else if(t4 == "") {
	    			
	    			t4 = "Peppers";
	    			
	    		}
	    		
	    	} if(tonions.isSelected() == true) {
	    		
	    		tcount = tcount + 1;
	    		
	    		if(t1 == "") {
	    			
	    			t1 = "Onions";
	    			
	    		} else if(t2 == "") {
	    			
	    			t2 = "Onions";
	    			
	    		} else if(t3 == "") {
	    			
	    			t3 = "Onions";
	    			
	    		} else if(t4 == "") {
	    			
	    			t4 = "Onions";
	    			
	    		}
	    		
	    	} if(ttomatoes.isSelected() == true) {
	    		
	    		tcount = tcount + 1;
	    		
	    		if(t1 == "") {
	    			
	    			t1 = "Tomatoes";
	    			
	    		} else if(t2 == "") {
	    			
	    			t2 = "Tomatoes";
	    			
	    		} else if(t3 == "") {
	    			
	    			t3 = "Tomatoes";
	    			
	    		} else if(t4 == "") {
	    			
	    			t4 = "Tomatoes";
	    			
	    		}
	    		
	    	} 
	    	
	    }
	    
	    String toppingsPrice() {
	    	
	    	if(tcount == 0) { txtprice = "0.00"; } else {
	    	txtp = (xtprice * (tcount - 1));
	    	txtp = txtp * pcount;
	    	
	    	txtprice = txtp.toString();
	    	}
	    	
	    	return txtprice;
	    }
	    
	    void pizzasPrice() {
	    	
	    	ptotal = tprice * pcount;
	    	
	    }
	    
	    void validateToppings(){
	    	
	    	if(tpepperoni.isSelected() == false && tsausage.isSelected() == false && tbacon.isSelected() == false
	    			&& tcheese.isSelected() == false && tpeppers.isSelected() == false && 
	    			tonions.isSelected() == false && ttomatoes.isSelected() == false) {
	    		
	    		Stage stage = (Stage) BTpane.getScene().getWindow();
		    	
		    	Alert.AlertType type = Alert.AlertType.ERROR;

		    	Alert alert = new Alert (type, "");
		    	
		    	alert.initModality(Modality.APPLICATION_MODAL);
		    	alert.initOwner(stage);
		    	alert.getDialogPane().setContentText("You must select at least one topping!");
		    	alert.getDialogPane().setHeaderText("Oops!");
		    	
		    	((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("OK");
		    	
		    	
		    	alert.getDialogPane().getStylesheets().add(
		    	   getClass().getResource("application.css").toExternalForm());
		    	alert.getDialogPane().getStyleClass().add("myDialog");
		    	
		    	Optional<ButtonType> result = alert.showAndWait();
		    	
		    	adding = false;
	    		
	    	} else if ((tcheese.isSelected() == true && tcount > 3) || (tcheese.isSelected() == false && tcount > 4)) {
	    		
	    		Stage stage = (Stage) BTpane.getScene().getWindow();
		    	
		    	Alert.AlertType type = Alert.AlertType.ERROR;

		    	Alert alert = new Alert (type, "");
		    	
		    	alert.initModality(Modality.APPLICATION_MODAL);
		    	alert.initOwner(stage);
		    	alert.getDialogPane().setContentText("No more than 4 toppings can be selected!");
		    	alert.getDialogPane().setHeaderText("Oops!");
		    	
		    	((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("OK");
		    	
		    	
		    	alert.getDialogPane().getStylesheets().add(
		    	   getClass().getResource("application.css").toExternalForm());
		    	alert.getDialogPane().getStyleClass().add("myDialog");
		    	
		    	Optional<ButtonType> result = alert.showAndWait();
		    	
		    	adding = false;
	    		
	    	} else {adding = true;}
	    	
	    }
	    
	    @FXML
	    void minusPizza(ActionEvent event) {

	    	pcount = Integer.parseInt(pnum.getText());
	    	
	    	if(pcount == 1) {
	    		
	    	} else {
	    	
	    	pcount = pcount - 1;
	    	pnum.setText(pcount.toString());
	    	
	    	}
	    	
	    }

	    @FXML
	    void plusPizza(ActionEvent event) {

	    	pcount = Integer.parseInt(pnum.getText());
	    	
	    	if(pcount == 10) {
	    		
	    	} else {
	    	
	    	pcount = pcount + 1;
	    	pnum.setText(pcount.toString());
	    	
	    	}
	    	
	    }
	    
	    void getTotalPrice() {
	    	
	    	Integer listsize = new Integer (L_pizzas.getItems().size());
	    	bttotal = 0.00;
	    	
	    	for (int i = 0; i < listsize; i++) {
                
	    		ObservableList<HBox> Lchildren = L_pizzas.getItems();
	    		HBox hbox = (HBox)Lchildren.get(i);
	    		
	    		ObservableList<Node> hbchildren = hbox.getChildren();
	    		VBox fvbox = (VBox)hbchildren.get(1);
	    		
	    		ObservableList<Node> vbchildren = fvbox.getChildren();
	    		HBox hbox1 = (HBox)vbchildren.get(0);
	    		Text toppings = (Text)vbchildren.get(1);
	    		String tp = toppings.getText().toString();
	    		
	    		ObservableList<Node> hb1children = hbox1.getChildren();
	    		HBox hbox2 = (HBox)hb1children.get(1);
	    		
	    		ObservableList<Node> hb2children = hbox2.getChildren();
	    		Text pizzaprice = (Text)hb2children.get(1);
	    		  		
	    		Double p1 = new Double (Double.parseDouble(pizzaprice.getText()));
	    		Double p2 = new Double (Double.parseDouble(tp.substring(tp.lastIndexOf("$") + 1)));
	    		
	    		bttotal = bttotal + (p1 + p2);
	    		
	    		ototal.setText(bttotal.toString());
	    		
            }
	    	
	    	
	    	
	    }
	    
	    @FXML
	    void addtoOrder(ActionEvent event) {
	    	
	    	selectedBasics();
	    	selectedToppings();
	    	pizzasPrice();
	    	toppingsPrice();
	    	validateToppings();
	    	
	    	if(adding == true) {
	    	
	    	tquantity = pcount.toString();
	    	
	    	ototal.setText(bttotal.toString());
	    	

	    	Button remove = new Button(" X ");
	    	remove.setId("redb");
	    	
	    	remove.setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override public void handle(ActionEvent e) {
	    	    	
	    	    	HBox hbox = (HBox)remove.getParent();
		    		
		    		ObservableList<Node> hbchildren = hbox.getChildren();
		    		VBox fvbox = (VBox)hbchildren.get(1);
		    		
		    		ObservableList<Node> vbchildren = fvbox.getChildren();
		    		HBox hbox1 = (HBox)vbchildren.get(0);
		    		Text toppings = (Text)vbchildren.get(1);
		    		String tp = toppings.getText().toString();
		    		
		    		ObservableList<Node> hb1children = hbox1.getChildren();
		    		HBox hbox2 = (HBox)hb1children.get(1);
		    		
		    		ObservableList<Node> hb2children = hbox2.getChildren();
		    		Text pizzaprice = (Text)hb2children.get(1);
		    		
		    		Double p1 = new Double (Double.parseDouble(pizzaprice.getText()));
		    		Double p2 = new Double (Double.parseDouble(tp.substring(tp.lastIndexOf("$") + 1)));
		    		
		    		
		    		bttotal = Double.parseDouble(ototal.getText());
		    		Double reduce = new Double (p1+p2);
		    		
		    		Double newtotal = new Double(bttotal-reduce);
		    		
		    		ototal.setText(newtotal.toString());
	    	    	
	    	    	L_pizzas.getItems().remove(remove.getParent());
	    	    	
	    	    }
	    	});
	    	
	    	Text quantity = new Text(tquantity);
	    	quantity.setId("list-text");
	    	
	    	Text product = new Text(tproduct);
	    	product.setId("list-text");
	    	
	    	Text dollar = new Text("$");
	    	dollar.setId("list-text");
	    	
	    	Text price = new Text(ptotal.toString());
	    	price.setId("list-text");
	    	
	    	Text top1 = new Text("[ " + t1 + ", " + t2 + ", " + t3 + ", " + t4 + " ] " + "$" + txtprice);
	    	top1.setId("list-text2");
	    	
	    	HBox pbox = new HBox(2, dollar, price);
	    	HBox dbox = new HBox(10, quantity, product);
	    	HBox fbox = new HBox(10, dbox, pbox);
	    	
	    	VBox vbox = new VBox(5, fbox, top1);
	    	
	    	HBox hbox = new HBox(10, remove, vbox);
	    	
	    	L_pizzas.getItems().add(hbox);
	    	
	    	Integer last = new Integer(0);
	    	
	    	last = L_pizzas.getItems().size();
	    	L_pizzas.getFocusModel().focus(last);
	    	L_pizzas.scrollTo(last);
	    	L_pizzas.getSelectionModel().select(hbox);
	    	
	    	} 
	    	
	    	getTotalPrice();
	    	
	    }
	    
	    
	   
	    
		@Override
		public void initialize(URL url, ResourceBundle rb) {
			// TODO Auto-generated method stub
			
			ToggleGroup Tgroup = new ToggleGroup();
			 
			B_basics.setToggleGroup(Tgroup);
			B_toppings.setToggleGroup(Tgroup);
			
			B_basics.setSelected(true);
			
			R_medium.setSelected(true);
			R_regular.setSelected(true);
			tcheese.setSelected(true);
			
			
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
	    	
	    	if (name == null) {
	    		
	    		
	    	} else {
	    	
	    	client.setText(name);
	    	
	    	}
	    	
		}

		
    }
	
