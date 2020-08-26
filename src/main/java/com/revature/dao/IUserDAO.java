package com.revature.dao;

import java.util.List;
import com.revature.models.User;

public interface IUserDAO {
	public List<User> findAll();
	public User findByUsername(String username);
	public int insert(User u);
}
