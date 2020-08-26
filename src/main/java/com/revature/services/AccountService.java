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
	
	public AccountService() {
		super();
		this.accDao = new AccountDAO();
	}
	public AccountService(IAccountDAO accDao) {
		super();
		this.accDao = accDao;
	}

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
		if ((acc.getBalance() - val) < 0) {
			System.out.println("Cannot withdrawl, you would have less than $0!");
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
			System.out.println("Cannot transfer, source account has insufficient funds!");
			return false;
		}
		withdrawl(from, val);
		deposit(to, val);
		return true;
	}
	
	 private void pressAnyKeyToContinue()
	 { 
	        System.out.println("Press Enter key to continue...");
	        try
	        {
	            System.in.read();
	        }  
	        catch(Exception e)
	        {}  
	 }
	
	public void viewCompleteAccountInfo () {
		int counter = 1;
		accList = accDao.findAll();
		String username, fname, lname, email;
		int userid, accid;
		double bal;
		System.out.println("\t\t\t***************************************");
		System.out.println("\t\t\t*\tAll Registered Accounts       *");
		System.out.println("\t\t\t***************************************");
		for (int i = 0; i < accList.size(); i++) {
			System.out.print(counter +". UserID: ");
			userid = accList.get(i).getOwner().getId();
			System.out.print(userid);
			System.out.print(" | AccountID: ");
			accid = accList.get(i).getId();
			System.out.print(accid);
			System.out.print(" | Username: ");
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
			System.out.print(email);
			System.out.print(" | Balance: ");
			bal = accList.get(i).getBalance();
			System.out.print(bal + "\n");
			counter++; 
		}
		pressAnyKeyToContinue();
	}
} 
