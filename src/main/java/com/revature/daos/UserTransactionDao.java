package com.revature.daos;

import com.revature.beans.User;
import com.revature.beans.UserTransaction;

public interface UserTransactionDao {
	public static final UserTransactionDao userTransactionDao = new UserTransactionDaoJdbc();
	//public void addUserTransaction(UserTransaction ut);
	//public void addUserTransaction(User u);
	public void addUserTransaction(User u, String type, double amount);
	public User printUserTransactions(User u);
}
