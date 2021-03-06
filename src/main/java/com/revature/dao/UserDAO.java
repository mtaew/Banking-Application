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
				String fname = rs.getString("firstname");
				String lname = rs.getString("lastname");
				String email = rs.getString("email");
				Role role = Role.valueOf(rs.getString("role"));
				User u = new User(id, username, password, fname, lname, email, role);
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
		User u = null;
		try (Connection conn = DBConnector.getConnection()) {
			String sql = "SELECT * FROM project0.users WHERE project0.users.username = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String user = rs.getString("username");
				String pass = rs.getString("password");
				String fname = rs.getString("firstname");
				String lname = rs.getString("lastname");
				String email = rs.getString("email");
				Role role = Role.valueOf(rs.getString("role"));
				u = new User(id, user, pass, fname, lname, email, role);
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("FAILED TO FIND USER");
			return null;
		}
		return u;
	} 

	@Override
	public int insert(User u) {
		try (Connection conn = DBConnector.getConnection()) {
			String sql = "INSERT INTO project0.users (username, password, firstname, lastname, email, role)"
					+ " VALUES (?, ?, ?, ?, ?, ?) RETURNING project0.users.id";
			PreparedStatement stmt = conn.prepareStatement(sql);
	
			stmt.setString(1, u.getUsername()); 
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getFname());
			stmt.setString(4, u.getLname());
			stmt.setString(5, u.getEmail());
			stmt.setObject(6, u.getRole(), Types.OTHER);
			
			ResultSet rs;
			if((rs = stmt.executeQuery()) != null) {
				rs.next();
				int id = rs.getInt(1);
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO INSERT USER");
		}
		return 0; // Invalid primary key
	}
}
