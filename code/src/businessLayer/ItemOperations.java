package businessLayer;

import java.util.ArrayList;

import dataAccessLayer.ItemDAO;
import model.Item;
import model.User;

public class ItemOperations {
	
	private ItemDAO itemDAO = new ItemDAO();
	
	public ArrayList<Item> getItemsByUserId(Integer id){
		return itemDAO.getItemsByUserId(id);
	}
	
	public ArrayList<Item> getItemsBetByUserId(Integer id){
		return itemDAO.getItemsBetByUserId(id);
	}
	
	public ArrayList<Item> getItemsNotBetByUserId(Integer id){
		return itemDAO.getItemsNotBetByUserId(id);
	}
	
	public void updateItemBet(Integer itemID, Integer betID) {
		itemDAO.updateItemBet(itemID, betID);
	}
	
	public ArrayList<Item> getItems(){
		return itemDAO.getItems();
	}
	
	public void addItem(String name, User user, String value) {
		if (name.equals("") || value.equals(""))
			return;
		
		Integer intValue;
		try {
			intValue = Integer.parseInt(value);
			itemDAO.insertItem(name, user.getId(), intValue);
		}
		catch(Exception e) {
			System.out.println(e);
			return;
		}
	}
	
	public void setBetNull(Integer id) {
		itemDAO.setBetNull(id);
	}
	
	public void reassignItem(Integer itemID, Integer userID) {
		itemDAO.updateItemOwner(itemID, userID);
	}
}
