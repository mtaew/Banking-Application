package com.revature.dao;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Application;
import com.revature.models.User;

public interface IApplicationDAO {
	public List<Application> findAll();
	public int insert(Application a);
	public boolean update(Application a);
	public boolean delete(int id);
}
