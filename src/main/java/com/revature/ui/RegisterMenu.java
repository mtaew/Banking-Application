package com.revature.ui;

import java.util.regex.Pattern;

import com.revature.models.Role;
import com.revature.services.UserService;

public class RegisterMenu {
	static UserService us = new UserService();
	private static String fname = null;
	private static String lname = null;
	private static String email = null;
	private static String username = null;
	private static String password = null;
	private static String vPassword = null;
	
	
	public static void registerMenu() {
		System.out.println("*****************************");
		System.out.println("*\tRegister Menu\t    *");
		System.out.println("*****************************");
		System.out.println("Please fill in your details below to register.");
		
		System.out.print("First Name: ");
		fname = MainMenu.scanner.nextLine();
		if (fname.isEmpty() || fname.equals(" ")) {
			System.out.println("First name field cannot be empty or blank!\n");
			registerMenu();
		}
		if (containsSpecialChar(fname)) {
			System.out.println("First name cannot contain any special characters!\n");
			registerMenu();
		}
		
		System.out.print("Last Name: ");
		lname = MainMenu.scanner.nextLine();
		if (lname.isEmpty() || lname.equals(" ")) {
			System.out.println("Last name field cannot be empty or blank!\n");
			registerMenu();
		}
		if (containsSpecialChar(lname)) {
			System.out.println("Last name cannot contain any special characters!\n");
			registerMenu();
		}
		
		System.out.print("Email: ");
		email = MainMenu.scanner.nextLine();
		if (email.isEmpty() || email.equals(" ")) {
			System.out.println("Email field cannot be empty or blank!\n");
			registerMenu();
		}
		
		if (!(isValidEmail(email))) {
			System.out.println("That is not an email!");
			registerMenu();
		}
		
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
			us.register(username, password, fname, lname, email, Role.Customer);
			System.out.println("Account created sucessfully!");
			MainMenu.mainMenu();
		} else {
			System.out.println("Password did not match, please try again!");
			registerMenu();
		}
	}
	
    public static boolean isValidEmail(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
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
