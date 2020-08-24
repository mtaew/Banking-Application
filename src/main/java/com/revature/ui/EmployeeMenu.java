package com.revature.ui;

import com.revature.dao.ApplicationDAO;
import com.revature.dao.IApplicationDAO;
import com.revature.dao.IUserDAO;
import com.revature.dao.UserDAO;
import com.revature.models.User;
import com.revature.services.ApplicationService;

public class EmployeeMenu {
	private static IApplicationDAO appDao = new ApplicationDAO();
	private static IUserDAO userDao = new UserDAO();
	private static ApplicationService appServ = new ApplicationService();
	private static String strInput;
	private static User userObj;
	
	public static void employeeMenu(User user) {
		System.out.println("*****************************");
		System.out.println("*\tEmployee Menu\t    *");
		System.out.println("*****************************");
		System.out.println("Please enter one of the following options using numbers. \n"
				+ "[1] Accept/Deny applications\n"
				+ "[2] View Account Information\n"
				+ "[3] View Account Balances\n"
				+ "[4] View Personal Information\n"
				+ "[5] Main Menu");
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
				employeeMenu(user);
				break;
				
			case 2:
				System.out.println("Plese enter the username of applicant you would like to deny: ");
				strInput = MainMenu.scanner.nextLine(); // Calling it twice becuase of nextInt
				strInput = MainMenu.scanner.nextLine();
				userObj = userDao.findByUsername(strInput);
				appServ.denyApps(userObj);
				employeeMenu(user);
				break;
			}
		break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			MainMenu.mainMenu();
			break;
		}
	}
}
