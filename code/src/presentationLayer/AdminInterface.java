package presentationLayer;

import java.util.ArrayList;

import businessLayer.BetOperations;
import businessLayer.EventOperations;
import businessLayer.ItemOperations;
import businessLayer.UserOperations;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
		
		/* TODO
		
		Button btnAddUser = new Button("New User");
		Button btnAddEvent = new Button("New Event");
		Button btnAddItem = new Button("New Item");
		
		Button btnConcludeEvent = new Button ("Conclude Event");
		*/
		btnViewEvents.setMinWidth(menu.getPrefWidth());
		btnViewItems.setMinWidth(menu.getPrefWidth());
		btnViewUsers.setMinWidth(menu.getPrefWidth());
		/* TODO
		
		btnAddUser.setMinWidth(menu.getPrefWidth());
		btnAddEvent.setMinWidth(menu.getPrefWidth());
		btnAddItem.setMinWidth(menu.getPrefWidth());
		btnConcludeEvent.setMinWidth(menu.getPrefWidth());
		*/
		
		menu.getChildren().addAll(btnViewUsers, btnViewEvents, btnViewItems);
		
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
		
		
		
		
		Scene scene = new Scene(pane, 1280, 960);
		secondStage.setScene(scene);
        secondStage.show();
	}
}