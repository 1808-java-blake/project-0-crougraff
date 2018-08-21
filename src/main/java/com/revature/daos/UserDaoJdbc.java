package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.util.ConnectionUtil;

public class UserDaoJdbc implements UserDao {
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
	public void createUser(User u) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO banking_user (username, password, firstname, lastname, age, balance) VALUES (?,?,?,?,?,?)");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setInt(5, u.getAge());
			ps.setDouble(6, u.getBalance());
			int recordsCreated = ps.executeUpdate();
			log.trace(recordsCreated + " records created");
		} catch (SQLException e) {
			log.error(e.getMessage());
			for (StackTraceElement ste : e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to create new user");
		}
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM banking_user WHERE username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
					
			if(rs.next()) {
				User u = new User();
				u.setUid(rs.getInt("uid"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFirstName(rs.getString("firstname"));
				u.setLastName(rs.getString("lastname"));
				u.setAge(rs.getInt("age"));
				u.setBalance(rs.getDouble("balance"));
				return u;
			} else {
				log.warn("failed to find user with provided credentials from the db");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findByUsername(String username) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM banking_user WHERE username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
					
			if(rs.next()) {
				User u = new User();
				u.setUid(rs.getInt("uid"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFirstName(rs.getString("firstname"));
				u.setLastName(rs.getString("lastname"));
				u.setAge(rs.getInt("age"));
				u.setBalance(rs.getDouble("balance"));
				return u;
			} else {
				log.warn("failed to find user with provided username from the db");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateUser(User u) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE banking_user SET username = ?, password = ?, firstname = ?, lastname = ?, age = ?, balance = ? WHERE uid = ?");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setInt(5, u.getAge());
			ps.setDouble(6, u.getBalance());
			ps.setInt(7, u.getUid());
			int recordsCreated = ps.executeUpdate();
			log.trace(recordsCreated + " record updated");
		} catch (SQLException e) {
			log.error(e.getMessage());
			for (StackTraceElement ste : e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to update user");
		}
	}

	@Override
	public void deleteUser(User u) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"DELETE FROM banking_user WHERE uid = ?");
			ps.setInt(1, u.getUid());
			int recordsCreated = ps.executeUpdate();
			log.trace(recordsCreated + " records deleted");
		} catch (SQLException e) {
			log.error(e.getMessage());
			for (StackTraceElement ste : e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to delete user");
		}
	}

}
