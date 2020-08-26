package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Application;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.DBConnector;

public class ApplicationDAO implements IApplicationDAO{
	private IUserDAO userDao = new UserDAO();
	@Override
	public List<Application> findAll() {
		List<Application> allApplication = new ArrayList<>();
		List<User> allUsers = userDao.findAll();
		try (Connection conn = DBConnector.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM project0.applications";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				int ownerID = rs.getInt("owner");
				String status = rs.getString("appstatus");
				User owner = null;
				for (int i = 0; i < allUsers.size(); i++) {
					if (allUsers.get(i).getId() == ownerID) {
						owner = allUsers.get(i);
					}
				}
				
				Application app = new Application(id, owner, status);
				allApplication.add(app);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO RETRIEVE ALL USERS");
			return null;
		}
		return allApplication;
	}

	@Override
	public int insert(Application a) {
		String sql = "INSERT INTO project0.applications (owner) VALUES (?) RETURNING project0.applications.id";
		
		try(Connection conn = DBConnector.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, a.getOwner().getId());
			
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
	public boolean update(Application a) {
		try (Connection conn = DBConnector.getConnection()) {
			String sql = "UPDATE project0.applications SET owner = ?, appstatus = ? WHERE project0.applications.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, a.getOwner().getId());
			stmt.setString(2, a.getAppStatus());
			stmt.setInt(3, a.getId());
			
			if (stmt.executeUpdate() != 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		try (Connection conn = DBConnector.getConnection()) {
			String sql = "DELETE FROM project0.applications WHERE project0.applications.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			if (stmt.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


}
