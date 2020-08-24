package com.revature.ui;

import com.revature.models.Role;
import com.revature.services.UserService;

public class RegisterMenu {
	static UserService us = new UserService();
	private static String username = null;
	private static String password = null;
	private static String vPassword = null;
	
	public static void registerMenu() {
		System.out.println("*****************************");
		System.out.println("*\tRegister Menu\t    *");
		System.out.println("*****************************");
		System.out.println("Please fill in your details below to register.");
		
		System.out.print("Username: ");
		username = MainMenu.scanner.nextLine();
		if (username.isEmpty() || username.equals(" ")) {
			System.out.println("Username field cannot be empty or blank!\n");
			registerMenu();
		}
		if (containsSpecialChar(username)) {
			System.out.println("Username cannot contain any special characters!\n");
			registerMenu();
		}

		System.out.print("Password: ");
		password = MainMenu.scanner.nextLine();
		if (password.isEmpty() || password.equals(" ")) {
			System.out.println("Password field cannot be empty or blank!\n");
			registerMenu();
		}
		
		System.out.print("Verify Password: ");
		vPassword = MainMenu.scanner.nextLine();

		
		if (matchPassword(password, vPassword)) {
			us.register(username, password, Role.Customer);
			System.out.println("Account created sucessfully!");
			MainMenu.mainMenu();
		} else {
			System.out.println("Password did not match, please try again!");
			registerMenu();
		}
	}
	
	private static boolean matchPassword(String password, String vPassword) {
		if (password.equals(vPassword)) {
			return true;
		}
		return false;
	}
	
	public static boolean containsSpecialChar(String s) {
	    return (s == null) ? false : s.matches("[^A-Za-z0-9 ]");
	}
}
