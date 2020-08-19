package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.DBConnector;

public class UserDAO implements IUserDAO{
	@Override
	public List<User> findAll() {
		List<User> allUsers = new ArrayList<>();
		
		try (Connection conn = DBConnector.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM project0.USERS";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				Role role = Role.valueOf(rs.getString("role"));
				User u = new User(id, username, password, role);
				allUsers.add(u);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO RETRIEVE ALL USERS");
			return null;
		}
		return allUsers;
	}
	
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(User u) {
		try (Connection conn = DBConnector.getConnection()) {
			String sql = "INSERT INTO project0.users (username, password, role) VALUES (?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	
			stmt.setString(1, u.getUsername()); 
			stmt.setString(2, u.getPassword());
			stmt.setObject(3, u.getRole(), Types.OTHER);
			
			if(stmt.execute()) {
				ResultSet rs = stmt.getGeneratedKeys();
				rs.next();
				int id = (int) rs.getLong(1);
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO INSERT USER");
		}
		return 0; // Invalid primary key
	}
	
	@Override
	public boolean update(User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int userId) {
		// TODO Auto-generated method stub
		return false;
	}
}
