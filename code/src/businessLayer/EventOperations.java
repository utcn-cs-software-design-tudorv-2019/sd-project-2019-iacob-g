package businessLayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import dataAccessLayer.EventDAO;
import model.Bet;
import model.Event;
import model.Item;

public class EventOperations {
	
	private EventDAO eventDAO = new EventDAO();
	private ItemOperations itemOperations = new ItemOperations();
	private BetOperations betOperations = new BetOperations(); 
	
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
	
	public void addEvent(String title) {
		if (title.equals(""))
			return;
		eventDAO.insertEvent(title);
	}
	
	public void deleteEvent(Integer id) {
		eventDAO.deleteEvent(id);
	}
	
	public void concludeEvent(Event event, boolean won) {
		
		ArrayList<Item> items = itemOperations.getItems();
		ArrayList<Item> itemsWon = new ArrayList<Item>();
		ArrayList<Item> itemsLost = new ArrayList<Item>();
		HashMap<Integer, Integer> payouts = new HashMap<Integer, Integer>();
		
		for (Item item : items) {
			if (item.getBet().getId() == 0)
				continue;
			
			if (item.getBet().getEventId() != event.getId())
				continue;
			
			if ((item.isPro() && won) || (!item.isPro() && !won)) {
				itemsWon.add(item);
				if (payouts.containsKey(item.getUser().getId()))
					payouts.put(item.getUser().getId(), payouts.get(item.getUser().getId()) + (int)item.getPossibleWin());
				else
					payouts.put(item.getUser().getId(), (int)item.getPossibleWin());
			}
			
			else 
				itemsLost.add(item);
			
			itemOperations.setBetNull(item.getId());
			betOperations.deleteBet(item.getBet().getId());
		}
		deleteEvent(event.getId());
		
		if (payouts.isEmpty())
			return;
		
		Collections.sort(itemsLost, new Comparator<Item>(){
            public int compare(Item i1, Item i2) {
              return -i1.getValue().compareTo(i2.getValue()); // returnam cu - ca sa fie descrescator
           }
        });
		
		//itemsLost.forEach(item -> System.out.println("Item " + item.getId() + " value " + item.getValue()));

		while (!itemsLost.isEmpty()) {
			Integer maxUser = 0, maxPayout = 0;
			for (Entry<Integer, Integer> entry : payouts.entrySet())
	            if (entry.getValue() > maxPayout) {
	            	maxPayout = entry.getValue();
	            	maxUser = entry.getKey();
	            }
			
			
			System.out.println("\n---\nNew Iteration:");
			payouts.forEach((key,value) -> System.out.println("User " + key + " should receive ~" + value));
			
			Item givenItem = itemsLost.get(0);
			itemsLost.remove(0);
			System.out.println("\nGiving item " + givenItem.getName() + " of value " + givenItem.getValue() + " to user " + maxUser);
			itemOperations.reassignItem(givenItem.getId(), maxUser);
			
			payouts.put(maxUser, payouts.get(maxUser) - givenItem.getValue());
		}
	}
}
