package businessLayer;

import java.util.ArrayList;

import dataAccessLayer.EventDAO;
import model.Bet;
import model.Event;
import model.Item;

public class EventOperations {
	
	private EventDAO eventDAO = new EventDAO();
	
	public ArrayList<Event> getEvents(){
		return eventDAO.getEvents();
	}
	
	public void updateEventOdds(Integer eventID, ArrayList<Item> items) {
		Integer sumPro = 0;
		Integer sumAgainst = 0;
		
		for (Item item : items) {
			Bet bet = item.getBet();
			if (bet.getId() == 0) // daca nu este pariat
				continue;
			if (item.getEventId() != eventID) // nu e pariat pe event-ul asta
				continue;
			
			if (bet.isPro())
				sumPro += item.getValue();
			else
				sumAgainst += item.getValue();
		}
		
		if (sumPro == 0 || sumAgainst == 0)
			return;
		
		float newOdds = (float)sumAgainst / sumPro;
		
		System.out.println("Updating odds of event " + eventID + " to " + sumAgainst + " / " + sumPro + " = " + newOdds);
		
		eventDAO.updateOdds(eventID, newOdds);
	}
}
