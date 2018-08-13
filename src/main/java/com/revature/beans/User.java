package com.revature.beans;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.HashMap;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2878891003003024330L;
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int age;
	private int routingNumber;
	private int accountNumber;
	private double balance;
	private HashMap<String, Double> transactionHistory = new HashMap<>();
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String password, String firstName, String lastName, int age, double balance,
			HashMap<String, Double> transactionHistory) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.balance = balance;
		this.transactionHistory = transactionHistory;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getBalance() {
		return balance;
	}
	public void deposit(double amount) {
		balance += amount;
		transactionHistory.put("deposit",amount);
	}
	public void withdraw(double amount) {
		if(amount > balance) {
			System.out.println("Insufficient funds: current balance = " + NumberFormat.getCurrencyInstance().format(balance));
			return;
		}
		balance -= amount;
		transactionHistory.put("withdraw",amount);

	}
	public HashMap<String, Double> getTransactionHistory() {
		return transactionHistory;
	}
	public void printTransactionHistory() {
		for (String key : transactionHistory.keySet()) {
			System.out.println(key + " " + NumberFormat.getCurrencyInstance().format(transactionHistory.get(key)) );
		}
	}
	public int sendTransfer(User u, double amount) {
		if (balance > amount) {
			u.receiveTransfer(this, amount);
			balance -= amount;
			transactionHistory.put("wire transfer to "+u.getUsername(), amount);
			return 1;
		}else {
			System.out.println("Insufficient funds: current balance = " + NumberFormat.getCurrencyInstance().format(balance));
			return 0;
		}
	}
	public void receiveTransfer(User u, double amount) {
		balance += amount;
		transactionHistory.put("wire transfer from "+u.getUsername(), amount);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((transactionHistory == null) ? 0 : transactionHistory.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (age != other.age)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (transactionHistory == null) {
			if (other.transactionHistory != null)
				return false;
		} else if (!transactionHistory.equals(other.transactionHistory))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", age=" + age + ", balance=" + balance + ", transactionHistory=" + transactionHistory
				+ "]";
	}
}
