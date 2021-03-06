package com.revature.daos;

import com.revature.beans.User;

public interface UserDao {
	public static final UserDao currentUserDao = new UserDaoJdbc();
	
	void createUser(User u);
	User findByUsernameAndPassword(String username, String password);
	User findByUsername(String username);
	void updateUser(User u);
	void deleteUser(User u);
}
