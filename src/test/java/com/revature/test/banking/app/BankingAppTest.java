package com.revature.test.banking.app;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import org.junit.Test;
import com.revature.beans.User;
import com.revature.daos.UserDao;

public class BankingAppTest {

	private User currentUser;
	private UserDao ud = UserDao.currentUserDao;
	@Test
	public void createUser(){
		currentUser = new User("testUsername1", "testPassword1","testFirst","testLast",21, 0, new HashMap<String, Double>());
		ud.createUser(currentUser);
		assertEquals(ud.findByUsername(currentUser.getUsername()).getUsername(), currentUser.getUsername());
	}
	@Test
	public void testUserDeposit() {
		User currentUser2 = new User("testUsername2", "testPassword2","testFirst","testLast",21, 0, new HashMap<String, Double>());
		currentUser2.deposit(100);
		ud.createUser(currentUser2);
		assertEquals(ud.findByUsername(currentUser2.getUsername()).getBalance(), currentUser2.getBalance(),0);
	}	
	@Test
	public void testUserWithdraw() {
		User currentUser3 = new User("testUsername3", "testPassword3","testFirst","testLast",21, 100, new HashMap<String, Double>());
		currentUser3.withdraw(100);
		ud.createUser(currentUser3);
		assertEquals(ud.findByUsername(currentUser3.getUsername()).getBalance(), currentUser3.getBalance(),0);
	}
}
