package businessLayer;

import java.util.ArrayList;

import dataAccessLayer.UserDAO;
import model.User;

public class UserOperations {
	
	private UserDAO userDAO = new UserDAO();
	
	public int isValidLogin(String name) {
		for (User user : userDAO.getUsers()) {
			if (user.getName().equals(name))
				return user.getId();
		}
		return 0;
	}
	
	public ArrayList<User> getUsers(){
		return userDAO.getUsers();
	}
	
	public void addUser(String name) {
		if (name.equals(""))
			return;
		userDAO.insertUser(name);
	}
}
