package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.AccountDAO;
import com.revature.dao.IAccountDAO;
import com.revature.models.Account;
import com.revature.models.User;


public class AccountService {
	private IAccountDAO accDao = new AccountDAO();
	private static List<Account> accList = new ArrayList<>();
	
	public Account createAccount(User user) {
		Account acc = new Account(0, 0, user);
		int new_id = accDao.insert(acc);
		if(new_id == 0) {
			return null;
			// Insert failed
		}
		acc.setId(new_id);
		return acc;
	}
	
	public boolean withdrawl(Account acc, double val) {
		if (acc.getBalance() < 0) {
			System.out.println("Cannot withdrawl, you have less than $0!");
			return false;
		}
		acc.setBalance(acc.getBalance() - val);
		accDao.update(acc);
		return true;
	}
	
	public boolean deposit(Account acc, double val) {
		if (val < 0) {
			System.out.println("You must deposit amount greater than $0!");
			return false;
		}
		acc.setBalance(acc.getBalance() + val);
		accDao.update(acc);
		return true;
	}
	
	public boolean transfer (Account from, Account to, double val) {
		if (val < 0 || from.getBalance() - val < 0) {
			return false;
		}
		withdrawl(from, val);
		deposit(to, val);
		return true;
	}
	public void viewAccInfo() {
		accList = accDao.findAll();
		int prntID;
		String prntUsername;
		System.out.println("====================\n" 
				+ "Account Information\n"
				+ "===================="
		);
		for (int i = 0; i < accList.size(); i++) {
			System.out.print("ID: ");
			prntID = accList.get(i).getOwner().getId();
			System.out.print(prntID);
			System.out.print(" | Username: ");
			prntUsername = accList.get(i).getOwner().getUsername();
			System.out.print(prntUsername + "\n\n");
		}
		
	}
	public void viewAccBal() {
		accList = accDao.findAll();
		String prntUsername;
		double prntBal;
		System.out.println("====================\n" 
				+ "Account Balances\n"
				+ "===================="
		);
		for (int i = 0; i < accList.size(); i++) {
			System.out.print("Username: ");
			prntUsername = accList.get(i).getOwner().getUsername();
			System.out.print(prntUsername);
			System.out.print(" | Balance: ");
			prntBal = accList.get(i).getBalance();
			System.out.print(prntBal + "\n\n");
		}
	}
	public void viewPersonalInfo() {
		int counter = 1;
		accList = accDao.findAll();
		String username, fname, lname, email;
		System.out.println("====================\n" 
				+ "Personal Information\n"
				+ "===================="
		);
		for (int i = 0; i < accList.size(); i++) {
			System.out.print(counter +". Username: ");
			username = accList.get(i).getOwner().getUsername();
			System.out.print(username);
			System.out.print(" | First Name: ");
			fname = accList.get(i).getOwner().getFname();
			System.out.print(fname);
			System.out.print(" | Last Name: ");
			lname = accList.get(i).getOwner().getLname();
			System.out.print(lname);
			System.out.print(" | Email: ");
			email = accList.get(i).getOwner().getEmail();
			System.out.print(email + "\n\n");
			counter++; 
		}
	}
} 
