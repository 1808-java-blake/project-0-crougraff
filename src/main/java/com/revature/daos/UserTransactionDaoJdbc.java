package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.util.ConnectionUtil;

public class UserTransactionDaoJdbc implements UserTransactionDao {
	private ConnectionUtil cu = ConnectionUtil.cu;
	private Logger log = Logger.getRootLogger();
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void addUserTransaction(User u, String type, double amount) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO user_transaction_history (uid, type, amount) VALUES (?,?,?)");
			ps.setInt(1, u.getUid());
			ps.setString(2, type);
			ps.setDouble(3, amount);
			int recordInserted = ps.executeUpdate();
			log.trace(recordInserted + " record inserted");
		} catch (SQLException e) {
			log.error(e.getMessage());
			for (StackTraceElement ste : e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to create new user");
		}
	}
	@Override
	public User printUserTransactions(User u) {
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM user_transaction_history WHERE uid = ?");
			ps.setInt(1, u.getUid());
			ResultSet rs = ps.executeQuery();
			HashMap<String,Double> temp = new HashMap<>();
			temp = u.getTransactionHistory();
			User usr = new User();
			while(rs.next()) {
				String type = rs.getString("type");
				double amount = rs.getDouble("amount");
				System.out.println(type + "  " + NumberFormat.getCurrencyInstance().format(amount));
				temp.put(type, amount);
				usr.setTransactionHistory(temp);
			}		
			return u;

		}catch(SQLException e) {
			log.error(e.getMessage());
			for (StackTraceElement ste : e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to print transactions");
		}
		return null;
	}

}
