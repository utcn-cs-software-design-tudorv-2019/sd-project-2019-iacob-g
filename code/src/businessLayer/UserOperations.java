package businessLayer;

import dataAccessLayer.UserDAO;
import model.User;

public class UserOperations {
	
	UserDAO userDAO = new UserDAO();
	
	public int isValidLogin(String name) {
		for (User user : userDAO.getUsers()) {
			if (user.getName().equals(name))
				return user.getId();
		}
		return 0;
	}
}
