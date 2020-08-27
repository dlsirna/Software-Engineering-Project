package mpPizza;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DSmenuController implements Initializable{

	@FXML
    private AnchorPane DSpane;

    @FXML
    private Button Back;

    @FXML
    private Button B_logout;

    @FXML
    private ToggleButton B_drinks;

    @FXML
    private ToggleButton B_sides;

    @FXML
    private Text DSdescription;

    @FXML
    private ListView<HBox> L_order;

    @FXML
    private Text ototal;

    @FXML
    private Button B_Continue;

    @FXML
    private VBox pDrinks;

    @FXML
    private ComboBox<String> CB_drinks;

    @FXML
    private RadioButton R_small;

    @FXML
    private RadioButton R_medium;

    @FXML
    private RadioButton R_large;

    @FXML
    private Button B_dplus;

    @FXML
    private Text dnum;

    @FXML
    private Button B_dminus;

    @FXML
    private Button B_addDrinks;

    @FXML
    private VBox pSides;

    @FXML
    private RadioButton R_breadsticks;

    @FXML
    private RadioButton R_breadbites;

    @FXML
    private RadioButton R_cookie;

    @FXML
    private Button B_splus;

    @FXML
    private Text snum;

    @FXML
    private Button B_sminus;

    @FXML
    private Button B_addSides;
    
    @FXML
    private Text client;
    
    private Integer dcount = 1, scount = 1;
    private Double ptotal = 0.00, dprice = 1.00, dsubtotal = 0.00, dtotal = 0.00, sprice = 0.00, ssubtotal = 0.00, stotal = 0.00, total = 0.00;
    private String dquantity, dname = "Pepsi", dsize, dproduct, squantity, sname = "";
    HBox[] hbarray = new HBox[20];
    String phonenumber, password, name, cardname, cardnumber, address, date, securitycode, cardtype;
    
    void getOrderItems(){
    	
    	Integer listsize = new Integer (L_order.getItems().size());
    	
    	
    	for (int i = 0; i < listsize; i++) {
            
    		ObservableList<HBox> Lchildren = L_order.getItems();
    		HBox hbox = (HBox)Lchildren.get(i);
    		
    		hbarray[i] = hbox;
    		
    	}
    	
    }

    @FXML
    void continue1(ActionEvent event) throws IOException {

    	getOrderItems();

    	total = Double.parseDouble(ototal.getText());
    	
    	if(client.getText().toString().equals("Guest") && name != "Guest") {
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("GuestOC.fxml"));
            Parent gocParent = (Parent) loader.load();
    		
            GOController gocController = loader.getController();
            
            gocController.getOrderedItems(hbarray, total);
            
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
        
        ocController.getOrderedItems(hbarray, total);
        
    	Scene ocScene = new Scene(ocParent);
    	
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(ocScene);
    	window.show();
    	
    	}
    	
    	
    }

    @FXML
    void exitProgram(ActionEvent event) throws IOException {

    	Stage stage = (Stage) DSpane.getScene().getWindow();
    	
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


    	Stage stage = (Stage) DSpane.getScene().getWindow();
    	
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
    void showDrinks(ActionEvent event) {
    	
    	if(B_drinks.isSelected() == true) {
			 
			 pDrinks.setVisible(true);
			 pSides.setVisible(false);
			 DSdescription.setText("Get a drink and enjoy your pizza even more!");
			 
		 } else { 
		 
		 B_drinks.setSelected(true);
		 
		 }

    }

    @FXML
    void showSides(ActionEvent event) {
    	
    	if(B_sides.isSelected() == true) {
			 
    		pDrinks.setVisible(false);
			 pSides.setVisible(true);
			 
			 DSdescription.setText("Get a side and be amazed with the taste!");
			 
		 } else { 
		 
		 B_sides.setSelected(true);
		 
		 }

    }

    @FXML
    void minusDrink(ActionEvent event) {
    	
    	dcount = Integer.parseInt(dnum.getText());
    	
    	if(dcount == 1) {
    		
    	} else {
    	
    	dcount = dcount - 1;
    	dnum.setText(dcount.toString());
    	
    	}

    }

    @FXML
    void minusSide(ActionEvent event) {

    	scount = Integer.parseInt(snum.getText());
    	
    	if(scount == 1) {
    		
    	} else {
    	
    	scount = scount - 1;
    	snum.setText(scount.toString());
    	
    	}
    	
    }

    @FXML
    void plusDrink(ActionEvent event) {

    	dcount = Integer.parseInt(dnum.getText());
    	
    	if(dcount == 10) {
    		
    	} else {
    	
    	dcount = dcount + 1;
    	dnum.setText(dcount.toString());
    	
    	}
    	
    }

    @FXML
    void plusside(ActionEvent event) {

    	scount = Integer.parseInt(snum.getText());
    	
    	if(scount == 10) {
    		
    	} else {
    	
    	scount = scount + 1;
    	snum.setText(scount.toString());
    	
    	}
    	
    }
    
    String selectedDrinks() {
    	
    	if(R_small.isSelected() == true) {
    		    		
    		    		dsize = "Small";
    		    		
    		    	} else if (R_medium.isSelected() == true) {
    		    		
    		    		dsize = "Medium";
    		    		
    		    	} else if (R_large.isSelected() == true) {
    		    		
    		    		dsize = "Large";
    		    		
    		    	}
          	
    		    	dproduct = dsize + " " + dname;
    		    	
    		    	return dproduct;
    		    	
    		    }
    
    void drinksPrice() {
    	
    	dsubtotal = dprice * dcount;
    	
    }
    
    @FXML
    void addDrinks(ActionEvent event) {
    	
    	selectedDrinks();
    	drinksPrice();
    	
    	dquantity = dcount.toString();
    	
    	dtotal = dtotal + dsubtotal;
    	
    	total = (ptotal + dtotal + stotal);
    	
    	ototal.setText(total.toString());
    	

    	Button remove = new Button(" X ");
    	remove.setId("redb");
    	
    	remove.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	
    	    	HBox hbox = (HBox)remove.getParent();
	    		
	    		ObservableList<Node> hbchildren = hbox.getChildren();
	    		VBox fvbox = (VBox)hbchildren.get(1);
	    		
	    		ObservableList<Node> vbchildren = fvbox.getChildren();
	    		HBox hbox1 = (HBox)vbchildren.get(0);
	    		
	    		ObservableList<Node> hb1children = hbox1.getChildren();
	    		HBox hbox2 = (HBox)hb1children.get(1);
	    		
	    		ObservableList<Node> hb2children = hbox2.getChildren();
	    		Text drinkprice = (Text)hb2children.get(1);
	    		
	    		Double d1 = new Double (Double.parseDouble(drinkprice.getText()));
	    		
	    		total = Double.parseDouble(ototal.getText());
	    		
	    		dtotal = (dtotal - d1);
	    		Double newtotal = new Double(total-d1);
	    		
	    		ototal.setText(newtotal.toString());
    	    	
    	    	L_order.getItems().remove(remove.getParent());
    	    	
    	    }
    	});
    	
    	Text quantity = new Text(dquantity);
    	quantity.setId("list-text");
    	
    	Text product = new Text(dproduct);
    	product.setId("list-text");
    	
    	Text dollar = new Text("$");
    	dollar.setId("list-text");
    	
    	Text price = new Text(dsubtotal.toString());
    	price.setId("list-text");
    	
    	HBox pbox = new HBox(2, dollar, price);
    	HBox dbox = new HBox(10, quantity, product);
    	HBox fbox = new HBox(10, dbox, pbox);
    	
    	VBox vbox = new VBox(5, fbox);
    	
    	HBox hbox = new HBox(10, remove, vbox);
    	
    	L_order.getItems().add(hbox);
    	
    	Integer last = new Integer(0);
    	
    	last = L_order.getItems().size();
    	L_order.getFocusModel().focus(last);
    	L_order.scrollTo(last);
    	L_order.getSelectionModel().select(hbox);

    }

    String selectedSide() {
    	
    	if(R_breadsticks.isSelected() == true) {
    		    		
    		    		sname = "Bread Sticks";
    		    		sprice = 2.00;
    		    		
    		    	} else if (R_breadbites.isSelected() == true) {
    		    		
    		    		sname = "Bread Bites";
    		    		sprice = 2.00;
    		    		
    		    	} else if (R_cookie.isSelected() == true) {
    		    		
    		    		sname = "Cookie";
    		    		sprice = 4.00;
    		    		
    		    	}
    		    	
    		    	return sname;
    		    	
    		    }
    
    void sidesPrice() {
    	
    	ssubtotal = sprice * scount;
    	
    }
    
    @FXML
    void addSides(ActionEvent event) {

    	selectedSide();
    	sidesPrice();
    	
    	squantity = scount.toString();
    	
    	stotal = stotal + ssubtotal;
    	
    	total = (ptotal + dtotal + stotal);
    	
    	ototal.setText(total.toString());
    	

    	Button remove = new Button(" X ");
    	remove.setId("redb");
    	
    	remove.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	
    	    	HBox hbox = (HBox)remove.getParent();
	    		
	    		ObservableList<Node> hbchildren = hbox.getChildren();
	    		VBox fvbox = (VBox)hbchildren.get(1);
	    		
	    		ObservableList<Node> vbchildren = fvbox.getChildren();
	    		HBox hbox1 = (HBox)vbchildren.get(0);
	    		
	    		ObservableList<Node> hb1children = hbox1.getChildren();
	    		HBox hbox2 = (HBox)hb1children.get(1);
	    		
	    		ObservableList<Node> hb2children = hbox2.getChildren();
	    		Text sideprice = (Text)hb2children.get(1);
	    		
	    		Double s1 = new Double (Double.parseDouble(sideprice.getText()));
	    		
	    		total = Double.parseDouble(ototal.getText());
	    		
	    		stotal = (stotal - s1);
	    		Double newtotal = new Double(total-s1);
	    		
	    		ototal.setText(newtotal.toString());
    	    	
    	    	L_order.getItems().remove(remove.getParent());
    	    	
    	    }
    	});
    	
    	Text quantity = new Text(squantity);
    	quantity.setId("list-text");
    	
    	Text product = new Text(sname);
    	product.setId("list-text");
    	
    	Text dollar = new Text("$");
    	dollar.setId("list-text");
    	
    	Text price = new Text(ssubtotal.toString());
    	price.setId("list-text");
    	
    	HBox pbox = new HBox(2, dollar, price);
    	HBox dbox = new HBox(10, quantity, product);
    	HBox fbox = new HBox(10, dbox, pbox);
    	
    	VBox vbox = new VBox(5, fbox);
    	
    	HBox hbox = new HBox(10, remove, vbox);
    	
    	L_order.getItems().add(hbox);
    	
    	Integer last = new Integer(0);
    	
    	last = L_order.getItems().size();
    	L_order.getFocusModel().focus(last);
    	L_order.scrollTo(last);
    	L_order.getSelectionModel().select(hbox);
    	
    }

    
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		ToggleGroup Tgroup = new ToggleGroup();
		 
		B_drinks.setToggleGroup(Tgroup);
		B_sides.setToggleGroup(Tgroup);
		
		B_drinks.setSelected(true);
		R_medium.setSelected(true);
		
		CB_drinks.getItems().addAll("Pepsi", "Diet Pepsi", "7UP", 
                "Mtn. Dew", "Gatorade", "Brisk Mate");
		CB_drinks.setValue("Pepsi");
		
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
          public void handle(ActionEvent e) { 
        	  
              dname = (CB_drinks.getValue().toString()); 
              
          } 
      }; 
      
      CB_drinks.setOnAction(event); 
      R_breadsticks.setSelected(true);
		
	}
	
	

	public void getOrderedItems(HBox[] hbarray, Double bttotal) {
		// TODO Auto-generated method stub
	
    	Integer hbnum = new Integer (hbarray.length);
    	
    	ptotal = bttotal;
    	ototal.setText(ptotal.toString());
    	
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
			    		HBox hbox1 = (HBox)vbchildren.get(0);
			    		Text toppings = (Text)vbchildren.get(1);
			    		String tp = toppings.getText().toString();
			    		
			    		ObservableList<Node> hb1children = hbox1.getChildren();
			    		HBox hbox2 = (HBox)hb1children.get(1);
			    		
			    		ObservableList<Node> hb2children = hbox2.getChildren();
			    		Text pizzaprice = (Text)hb2children.get(1);
			    		
			    		Double p1 = new Double (Double.parseDouble(pizzaprice.getText()));
			    		Double p2 = new Double (Double.parseDouble(tp.substring(tp.lastIndexOf("$") + 1)));
			    		
			    		
			    		total = Double.parseDouble(ototal.getText());
			    		Double reduce = new Double (p1+p2);
			    		
			    		ptotal = (ptotal - reduce); System.out.println(ptotal);
			    		
			    		Double newtotal = new Double(total-reduce);
			    		
			    		ototal.setText(newtotal.toString());
		    	    	
		    	    	L_order.getItems().remove(remove.getParent());
		    	    	
		    	    }
		    	});
    			
    			L_order.getItems().add(hbox);
    			
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
    	
    	if (name == null) {
    		
    		
    	} else {
    	
    	client.setText(name);
    	
    	}
		
	}
	
}
