package com.revature.services;

import com.revature.models.Role;
import com.revature.models.User;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.IUserDAO;
import com.revature.dao.UserDAO;

public class UserService {
	
	private IUserDAO userDao = new UserDAO();
	
	// Constructor to be used in normal application
	public UserService() {
		super();
		this.userDao = new UserDAO();
	}
	
	// Constructor to be used for Mockito
	public UserService(IUserDAO userDao) {
		super();
		this.userDao = userDao;
	}

	public User login(String username, String password) {
		User user = userDao.findByUsername(username);
		if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
	
	public User register(String username, String password, Role role) {
		User u = new User(0, username, password, role);
		int new_id = userDao.insert(u);
		if(new_id == 0) {
			return null;
			// Insert failed
		}
		u.setId(new_id);
		return u;
	}
}

