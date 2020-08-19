package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.util.DBConnector;

public class AccountDAO implements IAccountDAO {

	private IUserDAO userDao = new UserDAO();
	
	@Override
	public List<Account> findAll() {
		List<User> allUsers = userDao.findAll(); // Potentially unsorted
		List<Account> allAccounts = new ArrayList<>();
		
		try (Connection conn = DBConnector.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM project0.ACCOUNTS";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				double balance = rs.getDouble("balance");
				int ownerId = rs.getInt("owner");
				
				// find the User object in allUsers that matches the ownerId;
				User owner = null;
				// If there is no owner, then the Account object will have a null value for the owner
				// Which makes sense
				// Alternatively, we could perform encapsulation within the Account class if we
				// anticipate that all accounts should have an owner
				for(int i = 0; i < allUsers.size(); i++) {
					if(allUsers.get(i).getId() == ownerId) {
						owner = allUsers.get(i);
					}
				}
				
				Account a = new Account(id, balance, owner);
				
				allAccounts.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO RETRIEVE ALL ACCOUNTS");
			return null;
		}
		
		return allAccounts;
	}

	@Override
	public Account findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Account a) {
		String sql = "INSERT INTO project0.accounts (balance, owner) VALUES (?, ?) RETURNING project0.accounts.id";
		
		try(Connection conn = DBConnector.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setDouble(1, a.getBalance()); // PreparedStatement will prevent any content from
			// being executed as SQL
			stmt.setInt(2, a.getOwner().getId());
			
			ResultSet rs;
			if((rs = stmt.executeQuery()) != null) {
				rs.next();
				
				int id = rs.getInt(1);
				
				return id;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO INSERT ACCOUNT");
		}
	
		return 0; // Invalid primary key
	}

	@Override
	public boolean update(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
