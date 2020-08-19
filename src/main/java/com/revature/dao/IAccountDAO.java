package com.revature.dao;

import java.util.List;
import com.revature.models.Account;

public interface IAccountDAO {
	public List<Account> findAll();
	public Account findById(int id);
	public int insert(Account a);
	public boolean update(Account a);
	public boolean delete(int id);
}
