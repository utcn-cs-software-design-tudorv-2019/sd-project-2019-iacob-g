package dataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.User;

public class UserDAO {
	
	private Connection con = null;
	private Statement stat = null;
	private ResultSet resSet = null;
	private PreparedStatement pStat = null;
	
	public UserDAO() {
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
	
	public ArrayList<User> getUsers(){
		ArrayList<User> rtn = new ArrayList<User>();
		try {
			stat = con.createStatement();
			resSet = stat.executeQuery("select * from users;");
			while (resSet.next()) {
				rtn.add(new User(resSet.getInt("id"), resSet.getString("name")));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return rtn;
	}
	
	public User getUserById(Integer id) {
		try {
			pStat = con.prepareStatement("select * from users where id = ?;");
			pStat.setInt(1, id);
			resSet = pStat.executeQuery();
			if (resSet.next()) 
				return new User(resSet.getInt("id"), resSet.getString("name"));
			return new User();
		} catch (Exception e) {
			System.out.println(e);
			return new User();
		}
	}
}
