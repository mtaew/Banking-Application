package com.revature.ui;
import com.revature.dao.UserDAO;

public class LoginMenu {
	static String username = null;
	static String password = null;
	
	public static void loginMenu() {
		System.out.println("**************************");
		System.out.println("*\tLogin Menu\t *");
		System.out.println("**************************");
		System.out.println("To access your account, please enter your username and password below.");
		System.out.print("Username: ");
		username = MainMenu.scanner.nextLine();
		System.out.print("Password: ");
		password = MainMenu.scanner.nextLine();
	}
}
