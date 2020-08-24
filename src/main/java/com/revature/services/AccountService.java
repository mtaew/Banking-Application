package com.revature.services;

import com.revature.dao.AccountDAO;
import com.revature.dao.IAccountDAO;
import com.revature.models.Account;
import com.revature.models.User;

public class AccountService {
	private IAccountDAO accDao = new AccountDAO();
	
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
} 
