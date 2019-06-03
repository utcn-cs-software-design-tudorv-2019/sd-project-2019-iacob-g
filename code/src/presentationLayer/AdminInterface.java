package presentationLayer;

import java.util.ArrayList;

import businessLayer.BetOperations;
import businessLayer.EventOperations;
import businessLayer.ItemOperations;
import businessLayer.UserOperations;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Event;
import model.Item;
import model.User;

public class AdminInterface {
	
	private EventOperations eventOperations = new EventOperations();
	private ItemOperations itemOperations = new ItemOperations();
	private BetOperations betOperations = new BetOperations();
	private UserOperations userOperations = new UserOperations();
	
	public AdminInterface(){
		Stage secondStage = new Stage();
		
		BorderPane pane = new BorderPane();
		
		VBox menu = new VBox();
		menu.setSpacing(8);
		menu.setPrefWidth(250);
		menu.setPrefHeight(150);
		Button btnViewUsers = new Button("View Users");
		Button btnViewEvents = new Button("View Events");
		Button btnViewItems = new Button("View Items");
		Button btnAddUser = new Button("New User");
		Button btnAddEvent = new Button("New Event");
		Button btnAddItem = new Button("New Item");
		Button btnConcludeEvent = new Button ("Conclude Event");
		
		
		btnViewEvents.setMinWidth(menu.getPrefWidth());
		btnViewItems.setMinWidth(menu.getPrefWidth());
		btnViewUsers.setMinWidth(menu.getPrefWidth());
		btnAddUser.setMinWidth(menu.getPrefWidth());
		btnAddEvent.setMinWidth(menu.getPrefWidth());
		btnAddItem.setMinWidth(menu.getPrefWidth());
		btnConcludeEvent.setMinWidth(menu.getPrefWidth());
		
		
		menu.getChildren().addAll(btnViewUsers, btnViewEvents, btnViewItems, btnAddUser, btnAddEvent, btnAddItem, btnConcludeEvent);
		
		pane.setLeft(menu);
		
		// VIEW USERS
		
		btnViewUsers.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	
		    	TableView viewMenu = new TableView();
                TableColumn viewID = new TableColumn("ID");
                viewID.setCellValueFactory(new PropertyValueFactory<>("id"));
                TableColumn viewUserName = new TableColumn("Name");
                viewUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
               
                viewMenu.getColumns().addAll(viewID, viewUserName);
               
                ArrayList<User> users = userOperations.getUsers();
                for (User user: users) {
                    viewMenu.getItems().add(user);
                }
               
                pane.setCenter(viewMenu);
		    }
		});
		
		// VIEW EVENTS
		
		btnViewEvents.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	
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
                TableColumn viewOwner = new TableColumn("Owner");
                viewOwner.setCellValueFactory(new PropertyValueFactory<>("owner"));
                TableColumn viewValue = new TableColumn("Value");
                viewValue.setCellValueFactory(new PropertyValueFactory<>("value"));
                TableColumn viewEventTitle = new TableColumn("Event");
                viewEventTitle.setCellValueFactory(new PropertyValueFactory<>("eventTitle"));
                TableColumn viewPossibleWin = new TableColumn("Possible Win");
                viewPossibleWin.setCellValueFactory(new PropertyValueFactory<>("possibleWin"));
               
                viewMenu.getColumns().addAll(viewID, viewName, viewOwner, viewValue, viewEventTitle, viewPossibleWin);
               
                ArrayList<Item> items = itemOperations.getItems();
                for (Item item: items) {
                    viewMenu.getItems().add(item);
                }
               
                pane.setCenter(viewMenu);
		    }
		});
		
		// ADD USER
		
		GridPane menuUser = new GridPane();
		menuUser.setAlignment(Pos.CENTER);
		menuUser.setHgap(10);
		menuUser.setVgap(10);
		menuUser.setPadding(new Insets(25, 25, 25, 25));
		
		menuUser.add(new Label("Name:"), 0, 1);

		TextField nameTextField = new TextField();
		menuUser.add(nameTextField, 1, 1);
		
		Button btnUserConfirm = new Button("GO");
		
		btnUserConfirm.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	userOperations.addUser(nameTextField.getText());
		    }
		});
		
		menuUser.add(btnUserConfirm, 2, 1);
		
		btnAddUser.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	pane.setCenter(menuUser);
		    }
		});
		
		// ADD EVENT
		
		GridPane menuAddEvent = new GridPane();
		menuAddEvent.setAlignment(Pos.CENTER);
		menuAddEvent.setHgap(10);
		menuAddEvent.setVgap(10);
		menuAddEvent.setPadding(new Insets(25, 25, 25, 25));
		
		menuAddEvent.add(new Label("Title:"), 0, 1);

		TextField titleTextField = new TextField();
		menuAddEvent.add(titleTextField, 1, 1);
		
		Button btnAddEventConfirm = new Button("GO");
		
		btnAddEventConfirm.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	eventOperations.addEvent(titleTextField.getText());
		    }
		});
		
		menuAddEvent.add(btnAddEventConfirm, 2, 1);
		
		btnAddEvent.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	pane.setCenter(menuAddEvent);
		    }
		});
		
		// ADD ITEM
		
		GridPane menuAddItem = new GridPane();
		menuAddItem.setAlignment(Pos.CENTER);
		menuAddItem.setHgap(10);
		menuAddItem.setVgap(10);
		menuAddItem.setPadding(new Insets(25, 25, 25, 25));
		
		menuAddItem.add(new Label("Name:"), 0, 1);
		menuAddItem.add(new Label("Owner:"), 1, 1);
		menuAddItem.add(new Label("Value:"), 2, 1);

		TextField itemNameTextField = new TextField();
		TextField itemValueTextField = new TextField();
		
		ArrayList<User> userList = userOperations.getUsers();
		ComboBox<User> userComboBox = new ComboBox<User>(FXCollections.observableArrayList(userList));
		
		Callback<ListView<User>, ListCell<User>> userFactory = lv -> new ListCell<User>() {

		    @Override
		    protected void updateItem(User item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(empty ? "" : item.getName());
		    }

		};

		userComboBox.setCellFactory(userFactory);
		userComboBox.setButtonCell(userFactory.call(null));
		
		menuAddItem.add(itemNameTextField, 0, 2);
		menuAddItem.add(userComboBox, 1, 2);
		menuAddItem.add(itemValueTextField, 2, 2);
		
		
		Button btnAddItemConfirm = new Button("GO");
		
		btnAddItemConfirm.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	itemOperations.addItem(itemNameTextField.getText(), userComboBox.getValue(), itemValueTextField.getText());
		    }
		});
		
		menuAddItem.add(btnAddItemConfirm, 3, 2);
		
		btnAddItem.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	pane.setCenter(menuAddItem);
		    }
		});
		
		// CONCLUDE EVENT
		
		GridPane menuConcludeEvent = new GridPane();
		menuConcludeEvent.setAlignment(Pos.CENTER);
		menuConcludeEvent.setHgap(10);
		menuConcludeEvent.setVgap(10);
		menuConcludeEvent.setPadding(new Insets(25, 25, 25, 25));
		
		menuConcludeEvent.add(new Label("Event:"), 0, 1);
		menuConcludeEvent.add(new Label("Outcome:"), 1, 1);
		
		ArrayList<Event> eventList = eventOperations.getEvents();
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

		String[] options = {"WIN", "LOSS"};
		ComboBox<String> concludeEventComboBox = new ComboBox<String>(FXCollections.observableArrayList(options));

		menuConcludeEvent.add(eventComboBox, 0, 2);
		menuConcludeEvent.add(concludeEventComboBox, 1, 2);
		
		Button btnConcludeEventConfirm = new Button("GO");
		
		btnConcludeEventConfirm.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	boolean won;
		    	
		    	if (concludeEventComboBox.getValue().equals("WIN"))
		    		won = true;
		    	else
		    		won = false;
		    	
		    	eventOperations.concludeEvent(eventComboBox.getValue(), won);
		    }
		});
		
		menuConcludeEvent.add(btnConcludeEventConfirm, 2, 2);
		
		btnConcludeEvent.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	pane.setCenter(menuConcludeEvent);
		    }
		});
		
		
		Scene scene = new Scene(pane, 1280, 960);
		secondStage.setScene(scene);
        secondStage.show();
	}
}