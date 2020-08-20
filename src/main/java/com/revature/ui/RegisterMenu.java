package com.revature.ui;

import com.revature.models.Role;
import com.revature.services.UserService;

public class RegisterMenu {
	public static void registerMenu() {
		UserService us = new UserService();
		String username = null;
		String password = null;
		
		System.out.println("*****************************");
		System.out.println("*\tRegister Menu\t    *");
		System.out.println("*****************************");
		MainMenu.scanner.nextLine();
		System.out.println("Username: ");
		username = MainMenu.scanner.nextLine();
		System.out.println("Password: ");
		password = MainMenu.scanner.nextLine();

		us.register(username, password, Role.Customer);
	}
}
