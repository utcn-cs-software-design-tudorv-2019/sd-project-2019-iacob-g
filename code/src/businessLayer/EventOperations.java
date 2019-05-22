package businessLayer;

import java.util.ArrayList;

import dataAccessLayer.EventDAO;
import model.Event;

public class EventOperations {
	
	private EventDAO eventDAO = new EventDAO();
	
	public ArrayList<Event> getEvents(){
		return eventDAO.getEvents();
	}
}
