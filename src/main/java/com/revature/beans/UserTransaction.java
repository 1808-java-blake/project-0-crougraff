package com.revature.beans;

/**
 * @author Dell
 *
 */
public class UserTransaction {
	private int uid;
	private String type;
	private double amount;
	public UserTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserTransaction(int uid, String type, double amount) {
		super();
		this.uid = uid;
		this.type = type;
		this.amount = amount;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + uid;
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
		UserTransaction other = (UserTransaction) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (uid != other.uid)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserTransaction [uid=" + uid + ", type=" + type + ", amount=" + amount + "]";
	}
	
	
}
