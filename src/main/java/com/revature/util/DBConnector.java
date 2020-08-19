package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	private static Connection conn = null;
	
	private DBConnector() {
		super();
	}
	
	public static Connection getConnection() {
		try {
			if(conn != null && !conn.isClosed()) {
				return conn;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("FAILED TO REUSE A CONNECTION");
			return null;
		}
		
		String url = System.getenv("DB_URL");
		String username = System.getenv("DB_USERNAME");
		String password = System.getenv("DB_PASSWORD");
		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("FAILED TO GET A CONNECTION!");
			return null;
		}
		return conn;
	}
}
