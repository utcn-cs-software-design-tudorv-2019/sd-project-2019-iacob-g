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
}
