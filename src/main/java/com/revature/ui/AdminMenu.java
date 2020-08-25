package com.revature.ui;

import com.revature.dao.ApplicationDAO;
import com.revature.dao.IApplicationDAO;
import com.revature.dao.IUserDAO;
import com.revature.dao.UserDAO;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.ApplicationService;

public class AdminMenu {
	private static IApplicationDAO appDao = new ApplicationDAO();
	private static IUserDAO userDao = new UserDAO();
	private static ApplicationService appServ = new ApplicationService();
	private static AccountService accServ = new AccountService();
	private static String strInput;
	private static User userObj;
	public static void adminMenu(User user) {
		System.out.println("*****************************");
		System.out.println("*\tAdmin Menu\t    *");
		System.out.println("*****************************");
		System.out.println("Please enter one of the following options using numbers. \n"
				+ "[1] Accept/Deny applications\n"
				+ "[2] View Account Information\n"
				+ "[3] View Account Balances\n"
				+ "[4] View Personal Information\n"
				+ "[5] Main Menu\n");
		System.out.print("Enter here: ");
		int input = MainMenu.scanner.nextInt();
		switch (input) {
		case 1:
			System.out.println(appDao.findAll());
			System.out.println("_______________________________");
			System.out.println("[1] Accept applications\n"
					+ "[2] Deny applications");
			input = MainMenu.scanner.nextInt();
			switch(input) {
			case 1:
				System.out.println("Plese enter the username of applicant you would like to accept: ");
				strInput = MainMenu.scanner.nextLine(); // Calling it twice becuase of nextInt
				strInput = MainMenu.scanner.nextLine();
				userObj = userDao.findByUsername(strInput);
				appServ.acceptApps(userObj);
				adminMenu(user);
				break;
				
			case 2:
				System.out.println("Plese enter the username of applicant you would like to deny: ");
				strInput = MainMenu.scanner.nextLine(); // Calling it twice becuase of nextInt
				strInput = MainMenu.scanner.nextLine();
				userObj = userDao.findByUsername(strInput);
				appServ.denyApps(userObj);
				adminMenu(user);
				break;
			}
			break;
		}
	}
}
