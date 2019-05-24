package businessLayer;

import java.util.ArrayList;

import dataAccessLayer.ItemDAO;
import model.Item;

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
}
