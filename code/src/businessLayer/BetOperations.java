package businessLayer;

import dataAccessLayer.BetDAO;
import model.Bet;

public class BetOperations {
	
	private BetDAO betDAO = new BetDAO();
	
	public Integer addBet(Bet bet) {
		return betDAO.insertBet(bet);
	}

}
