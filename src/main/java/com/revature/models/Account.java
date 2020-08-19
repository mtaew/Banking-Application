package com.revature.models;

import java.util.Objects;

public class Account {

	private int id;
	private double balance;
	private User owner;
	
	public Account() {
		super();
	}

	public Account(int id, double balance, User owner) {
		super();
		this.id = id;
		this.balance = balance;
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public int hashCode() {
		return Objects.hash(balance, id, owner);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Account)) {
			return false;
		}
		Account other = (Account) obj;
		return Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance) && id == other.id
				&& Objects.equals(owner, other.owner);
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", owner=" + owner + "]";
	}
}