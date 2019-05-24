package dataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import model.Item;
import model.User;

public class ItemDAO {
	
	private Connection con = null;
	private Statement stat = null;
	private ResultSet resSet = null;
	private PreparedStatement pStat = null;
	BetDAO betDAO = new BetDAO();
	UserDAO userDAO = new UserDAO();
	
	
	public ItemDAO() {
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
	
	public ArrayList<Item> getItemsByUserId(Integer id){
		ArrayList<Item> rtn = new ArrayList<Item>();
		try {
			pStat = con.prepareStatement("select * from items where id_user = ?;");
			pStat.setInt(1, id);
			resSet = pStat.executeQuery();
			while (resSet.next()) {
				rtn.add(new Item(resSet.getInt("id"), new User(), betDAO.getBetById(resSet.getInt("id_bet")), resSet.getInt("value"), resSet.getString("name")));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return rtn;
	}
	
	public ArrayList<Item> getItemsBetByUserId(Integer id){
		ArrayList<Item> rtn = new ArrayList<Item>();
		try {
			pStat = con.prepareStatement("select * from items where id_user = ? and id_bet is not null;");
			pStat.setInt(1, id);
			resSet = pStat.executeQuery();
			while (resSet.next()) {
				rtn.add(new Item(resSet.getInt("id"), new User(), betDAO.getBetById(resSet.getInt("id_bet")), resSet.getInt("value"), resSet.getString("name")));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return rtn;
	}
	
	public ArrayList<Item> getItemsNotBetByUserId(Integer id){
		ArrayList<Item> rtn = new ArrayList<Item>();
		try {
			pStat = con.prepareStatement("select * from items where id_user = ? and id_bet is null;");
			pStat.setInt(1, id);
			resSet = pStat.executeQuery();
			while (resSet.next()) {
				rtn.add(new Item(resSet.getInt("id"), new User(), betDAO.getBetById(resSet.getInt("id_bet")), resSet.getInt("value"), resSet.getString("name")));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return rtn;
	}
	
	public ArrayList<Item> getItems(){
		ArrayList<Item> rtn = new ArrayList<Item>();
		try {
			stat = con.createStatement();
			resSet = stat.executeQuery("select * from items;");
			while (resSet.next()) {
				rtn.add(new Item(resSet.getInt("id"), userDAO.getUserById(resSet.getInt("id_user")), betDAO.getBetById(resSet.getInt("id_bet")), resSet.getInt("value"), resSet.getString("name")));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return rtn;
	}
	
	public void updateItemBet(Integer itemID, Integer betID) {
		try {
			pStat = con.prepareStatement("update items set id_bet = ? where id = ?");
			pStat.setInt(2, itemID);
			if (betID == 0)
				pStat.setNull(1, Types.INTEGER);
			else
				pStat.setInt(1, betID);
			
			pStat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertItem(String name, Integer userID, Integer value) {
		try {
			pStat = con.prepareStatement("insert into items (name, id_user, value) values (?, ?, ?)");
			
			pStat.setString(1, name);
			pStat.setInt(2, userID);
			pStat.setInt(3, value);
			pStat.execute();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
