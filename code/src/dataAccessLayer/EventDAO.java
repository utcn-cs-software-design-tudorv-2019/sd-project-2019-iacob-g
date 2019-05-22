package dataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Bet;
import model.Event;
import model.Item;
import model.User;

public class EventDAO {
	
	private Connection con = null;
	private Statement stat = null;
	private ResultSet resSet = null;
	private PreparedStatement pStat = null;
	
	public EventDAO() {
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
	
	public ArrayList<Event> getEvents(){
		ArrayList<Event> rtn = new ArrayList<Event>();
		try {
			stat = con.createStatement();
			resSet = stat.executeQuery("select * from events;");
			while (resSet.next()) {
				rtn.add(new Event(resSet.getInt("id"), resSet.getString("title"), resSet.getFloat("odds")));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return rtn;
	}
	
	public Event getEventById(Integer id) {
		try {
			pStat = con.prepareStatement("select * from events where id = ?;");
			pStat.setInt(1, id);
			resSet = pStat.executeQuery();
			if (resSet.next()) 
				return new Event(resSet.getInt("id"), resSet.getString("title"), resSet.getFloat("odds"));
			return new Event();
		} catch (Exception e) {
			System.out.println(e);
			return new Event();
		}
	}
}
