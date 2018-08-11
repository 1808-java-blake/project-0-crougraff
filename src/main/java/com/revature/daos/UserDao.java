package com.revature.daos;

import com.revature.beans.User;

public interface UserDao {
	public static final UserDao currentUserDao = UserSerializer.us;
	
	/**
	 * Takes in a user object and will persist that user
	 * 
	 * @param u
	 */
	void createUser(User u);
	User findByUsernameAndPassword(String username, String password);
	User findByUsername(String username);
	void updateUser(User u);
	void deleteUser(User u);
}
