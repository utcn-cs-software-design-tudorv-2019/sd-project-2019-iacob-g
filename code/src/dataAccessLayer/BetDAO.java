package dataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Bet;
import model.User;

public class BetDAO {
	
	private Connection con = null;
	private Statement stat = null;
	private ResultSet resSet = null;
	private PreparedStatement pStat = null;
	EventDAO eventDAO = new EventDAO();
	
	public BetDAO() {
		connect();
	}
	
	private void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/proiect_final", "root", "1234");
		} catch (Exception e) {
			System.out.println(e);
			close();
		}
	}
	
	public void close() {
		System.out.println("Closing DB connection.");
		try {
			if (con != null)
				con.close();
			if (stat != null)
				stat.close();
			if (resSet != null)
				resSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public Bet getBetById(Integer id) {
		if (id == null)
			return new Bet();
		
		try {
			pStat = con.prepareStatement("select * from bets where id = ?;");
			pStat.setInt(1, id);
			resSet = pStat.executeQuery();
			if (resSet.next()) 
				return new Bet(resSet.getInt("id"), eventDAO.getEventById(resSet.getInt("id_event")), resSet.getBoolean("pro"));
			return new Bet();
		} catch (Exception e) {
			System.out.println(e);
			return new Bet();
		}
	}
	
	
}
