package com.revature.screens;

import java.text.NumberFormat;
import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;

public class AdminScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private User user;
	public AdminScreen() {
	}

	@Override
	public Screen start() {
		System.out.println("Please chose from following options:");
		System.out.println("Enter 1 to view a user's information");
		System.out.println("Enter 2 to view a user's transaction");
		System.out.println("Enter 3 to view a user's balance");
		System.out.println("Enter 4 to delete user");
		System.out.println("Enter 5 to sign out");
		String selection = scan.nextLine();
		String username;
		switch (selection) {
		case "1":
			System.out.println("Which username?");
			username = scan.nextLine();
			user = ud.findByUsername(username);
			System.out.println(username + " information: ");
			System.out.println("Name: " + user.getFirstName() + " " + user.getLastName());
			System.out.println("Age: " + user.getAge());
			System.out.println("Login: " + user.getUsername() + " " + user.getPassword());
	
			break;
		case "2":
			System.out.println("Which username?");
			username = scan.nextLine();
			user = ud.findByUsername(username);
			System.out.println("Transaction History: ");
			user.printTransactionHistory();
			break;
		case "3":
			System.out.println("Which username?");
			username = scan.nextLine();
			user = ud.findByUsername(username);
			System.out.println("Balance: " + NumberFormat.getCurrencyInstance().format(user.getBalance()));
			break;
		case "4":
			System.out.println("Which username?");
			username = scan.nextLine();
			user = ud.findByUsername(username);
			ud.deleteUser(user);
			break;
		case "5":
			return new LoginScreen();
		default:
			break;
		}
		return this;
	}
	
}
