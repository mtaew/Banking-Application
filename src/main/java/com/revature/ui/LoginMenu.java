package com.revature.ui;

import com.revature.models.User;
import com.revature.services.UserService;

public class LoginMenu {
	static String username = null;
	static String password = null;
	static UserService us = new UserService();
	static User user = new User();
	
	public static void loginMenu() {
		System.out.println("\t\t**************************");
		System.out.println("\t\t*\tLogin Menu\t *");
		System.out.println("\t\t**************************");
		System.out.println("To access your account, please enter your username and password below.");
		System.out.print("Username: ");
		username = MainMenu.scanner.nextLine();
		if (username.isEmpty() || username.equals(" ")) {
			System.out.println("Username field cannot be empty or blank!\n");
			loginMenu();
		}
		if (containsSpecialChar(username)) {
			System.out.println("Username cannot contain any special characters!\n");
			loginMenu();
		}
		System.out.print("Password: ");
		password = MainMenu.scanner.nextLine();
		if (password.isEmpty() || password.equals(" ")) {
			System.out.println("Password field cannot be empty or blank!\n");
			loginMenu();
		}
		if (containsSpecialChar(password)) {
			System.out.println("Password cannot contain any special characters!\n");
			loginMenu();
		}
		user = us.login(username, password);
		System.out.println();
		if (user != null) {
			if (user.getRole().name() == "Customer") {
				CustomerMenu.customerMenu(user);
			}
			if (user.getRole().name() == "Employee") {
				EmployeeMenu.employeeMenu(user);
			}
			if (user.getRole().name() == "Admin") {
				AdminMenu.adminMenu(user);
			}
		}
	}
	
	public static boolean containsSpecialChar(String s) {
	    return (s == null) ? false : s.matches("[^A-Za-z0-9 ]");
	}
}
