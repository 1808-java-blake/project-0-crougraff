package com.revature.screens;

import java.text.NumberFormat;
import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;

public class HomeScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private User currentUser;
	private UserDao ud = UserDao.currentUserDao;
	public HomeScreen(User currentUser) {
		this.currentUser = currentUser;
	}

	public Screen start() {
		System.out.println("Please chose from following options:");
		System.out.println("Enter 1 to deposit money");
		System.out.println("Enter 2 to withdraw money");
		System.out.println("Enter 3 to view balance");
		System.out.println("Enter 4 to wire money");
		System.out.println("Enter 5 to view transaction history");
		System.out.println("Enter 6 to sign out");
		String selection = scan.nextLine();
		switch (selection) {
		case "1":
			System.out.println("How much?");
			String deposit = scan.nextLine();
			double amount = Double.parseDouble(deposit);
			currentUser.deposit(amount);
			ud.updateUser(currentUser);
			break;
		case "2":
			System.out.println("How much?");
			String withdraw = scan.nextLine();
		    amount = Double.parseDouble(withdraw);
			currentUser.withdraw(amount);
			ud.updateUser(currentUser);
			break;
		case "3":
			System.out.println("balance = " + NumberFormat.getCurrencyInstance().format(currentUser.getBalance()));
			break;
		case "4":
			System.out.println("What user do you want to send money to?");
			String user = scan.nextLine();
			System.out.println("How much do you want to send?");
			String wireTransfer = scan.nextLine();
			double transfer = Double.parseDouble(wireTransfer);
			User receivingUser = ud.findByUsername(user);
			System.out.println("Sending "+NumberFormat.getCurrencyInstance().format(transfer) + " to " + receivingUser.getUsername());
			if(currentUser.sendTransfer(receivingUser, transfer) == 1) {
				receivingUser.receiveTransfer(currentUser, transfer);
				ud.updateUser(currentUser);
				ud.updateUser(receivingUser);
			}
			break;
		case "5":
			System.out.println("transaction history: " );
			currentUser.printTransactionHistory();
			break;
		case "6":
			return new LoginScreen();
		default:
			break;
		}

		return this;
	}

}
