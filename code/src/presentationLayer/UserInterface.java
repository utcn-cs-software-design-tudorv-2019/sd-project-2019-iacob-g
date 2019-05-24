package presentationLayer;

import java.util.ArrayList;

import businessLayer.BetOperations;
import businessLayer.EventOperations;
import businessLayer.ItemOperations;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Bet;
import model.Event;
import model.Item;

public class UserInterface {
	
	private EventOperations eventOperations = new EventOperations();
	private ItemOperations itemOperations = new ItemOperations();
	private BetOperations betOperations = new BetOperations();
	
	public UserInterface(Integer userID){
		Stage secondStage = new Stage();
		
		BorderPane pane = new BorderPane();
		
		VBox menu = new VBox();
		menu.setSpacing(8);
		menu.setPrefWidth(250);
		menu.setPrefHeight(150);
		Button btnViewEvents = new Button("View Events");
		Button btnViewItems = new Button("View Items");
		Button btnViewBets = new Button("View Bets");
		Button btnAddBet = new Button("New Bet");
		//Button btnEnroll = new Button("Enroll");
		
		btnViewEvents.setMinWidth(menu.getPrefWidth());
		btnViewItems.setMinWidth(menu.getPrefWidth());
		btnViewBets.setMinWidth(menu.getPrefWidth());
		btnAddBet.setMinWidth(menu.getPrefWidth());
		//btnEnroll.setMinWidth(menu.getPrefWidth());
		
		menu.getChildren().addAll(btnViewEvents, btnViewItems, btnViewBets, btnAddBet);
		
		pane.setLeft(menu);
		
		// VIEW EVENTS
		
		btnViewEvents.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	
		    	itemOperations.getItemsByUserId(userID);
		    	
		    	TableView viewMenu = new TableView();
                TableColumn viewID = new TableColumn("ID");
                viewID.setCellValueFactory(new PropertyValueFactory<>("id"));
                TableColumn viewEventTitle = new TableColumn("Event Title");
                viewEventTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
                TableColumn viewOdds = new TableColumn("Odds");
                viewOdds.setCellValueFactory(new PropertyValueFactory<>("odds"));
               
                viewMenu.getColumns().addAll(viewID, viewEventTitle, viewOdds);
               
                ArrayList<Event> events = eventOperations.getEvents();
                for (Event event: events) {
                    viewMenu.getItems().add(event);
                }
               
                pane.setCenter(viewMenu);
		    }
		});
		
		// VIEW ITEMS
		
		btnViewItems.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	TableView viewMenu = new TableView();
                TableColumn viewID = new TableColumn("ID");
                viewID.setCellValueFactory(new PropertyValueFactory<>("id"));
                TableColumn viewName = new TableColumn("Name");
                viewName.setCellValueFactory(new PropertyValueFactory<>("name"));
                TableColumn viewValue = new TableColumn("Value");
                viewValue.setCellValueFactory(new PropertyValueFactory<>("value"));
                TableColumn viewAvailabile = new TableColumn("Is available?");
                viewAvailabile.setCellValueFactory(new PropertyValueFactory<>("available"));
               
                viewMenu.getColumns().addAll(viewID, viewName, viewValue, viewAvailabile);
               
                ArrayList<Item> items = itemOperations.getItemsByUserId(userID);
                for (Item item: items) {
                    viewMenu.getItems().add(item);
                }
               
                pane.setCenter(viewMenu);
		    }
		});
		
		// VIEW BETS
		
		btnViewBets.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	TableView viewMenu = new TableView();
                TableColumn viewItem = new TableColumn("Item");
                viewItem.setCellValueFactory(new PropertyValueFactory<>("name"));
                TableColumn viewTitle = new TableColumn("Event");
                viewTitle.setCellValueFactory(new PropertyValueFactory<>("eventTitle"));
                TableColumn viewPossibleWin = new TableColumn("Possible Win");
                viewPossibleWin.setCellValueFactory(new PropertyValueFactory<>("possibleWin"));
                TableColumn viewPro = new TableColumn("Pro?");
                viewPro.setCellValueFactory(new PropertyValueFactory<>("pro"));
               
                viewMenu.getColumns().addAll(viewItem, viewTitle, viewPossibleWin, viewPro);
               
                ArrayList<Item> items = itemOperations.getItemsBetByUserId(userID);
                for (Item item: items) {
                    viewMenu.getItems().add(item);
                }
               
                pane.setCenter(viewMenu);
		    }
		});
		
		// ADD BET
		
		GridPane menuBet = new GridPane();
		menuBet.setAlignment(Pos.CENTER);
		menuBet.setHgap(10);
		menuBet.setVgap(10);
		menuBet.setPadding(new Insets(25, 25, 25, 25));
		
		menuBet.add(new Label("Item:"), 1, 0);
		menuBet.add(new Label("Event:"), 2, 0);
		menuBet.add(new Label("Verdict:"), 3, 0);
		
		ArrayList<Item> itemList = itemOperations.getItemsNotBetByUserId(userID);
		ArrayList<Event> eventList = eventOperations.getEvents();
		
		ComboBox<Item> itemComboBox = new ComboBox<Item>(FXCollections.observableArrayList(itemList));
		
		Callback<ListView<Item>, ListCell<Item>> itemFactory = lv -> new ListCell<Item>() {

		    @Override
		    protected void updateItem(Item item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(empty ? "" : item.getName());
		    }

		};

		itemComboBox.setCellFactory(itemFactory);
		itemComboBox.setButtonCell(itemFactory.call(null));
		
		ComboBox<Event> eventComboBox = new ComboBox<Event>(FXCollections.observableArrayList(eventList));
		
		Callback<ListView<Event>, ListCell<Event>> eventFactory = lv -> new ListCell<Event>() {

		    @Override
		    protected void updateItem(Event item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(empty ? "" : item.getTitle());
		    }

		};

		eventComboBox.setCellFactory(eventFactory);
		eventComboBox.setButtonCell(eventFactory.call(null));
		
		String[] verdicts = {"PRO", "AGAINST"};
		ComboBox<String> proComboBox = new ComboBox<String>(FXCollections.observableArrayList(verdicts));
		
		menuBet.add(itemComboBox, 1, 1);
		menuBet.add(eventComboBox, 2, 1);
		menuBet.add(proComboBox, 3, 1);
		
		Button btnBetConfirm = new Button("GO");
		
		btnBetConfirm.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	Item selectedItem = itemComboBox.getValue();
		    	Event selectedEvent = eventComboBox.getValue();
		    	boolean pro;
		    	
		    	if (proComboBox.getValue().equals("AGAINST"))
		    		pro = false;
		    	else 
		    		pro = true;
		    	
		    	Bet bet = new Bet(0, selectedEvent, pro);
		    	Integer insertedBetID = betOperations.addBet(bet);
		    	
		    	itemOperations.updateItemBet(selectedItem.getId(), insertedBetID);
		    	eventOperations.updateEventOdds(selectedEvent.getId(), itemOperations.getItems());
		    	
		    	
		    }
		});
		
		menuBet.add(btnBetConfirm, 4, 1);
		
		btnAddBet.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	pane.setCenter(menuBet);
		    }
		});
		
		
		Scene scene = new Scene(pane, 1280, 960);
		secondStage.setScene(scene);
        secondStage.show();
	}
}
