package com.revature.test.banking.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Rule;
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
	public void testDeposit() {
		User currentUser2 = new User("testUsername2", "testPassword2","testFirst","testLast",21, 0, new HashMap<String, Double>());
		currentUser2.deposit(100);
		ud.createUser(currentUser2);
		assertEquals(ud.findByUsername(currentUser2.getUsername()).getBalance(), currentUser2.getBalance(),0);
	}	
}
