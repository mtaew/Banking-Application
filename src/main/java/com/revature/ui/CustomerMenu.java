package com.revature.ui;

import com.revature.models.User;
import com.revature.services.ApplicationService;

public class CustomerMenu {
	private static ApplicationService appServ = new ApplicationService();
	public static void customerMenu(User user) {
		System.out.println("***************************");
		System.out.println("*\tCustomer Menu\t  *");
		System.out.println("***************************");
		//System.out.println(appServ.checkAppStatus(user));
		if (appServ.checkAppStatus(user)) {
			System.out.println("Please enter one of the following options using numbers. \n"
				+ "[1] Withdrawl\n"
				+ "[2] Desposit\n"
				+ "[3] Transfer\n"
				+ "[4] Main Menu\n");
		}
		else {
			System.out.println("Please enter one of the following options using numbers. \n"
					+ "[1] Apply For Account\n"
					+ "[2] Main Menu\n");
			int input = MainMenu.scanner.nextInt();
			switch (input) {
			case 1:
				if (appServ.checkForDupes(user)) {
					customerMenu(user);
				}
				appServ.applyForAccount(user);
				System.out.println("You have sucessfully applied for an account!\n"
						+ "Please login at a later time to see if your account has been confirmed or not by one of our employees!");
				customerMenu(user);
				break;
			case 2:
				MainMenu.mainMenu();
				break;
			}
		}

	}
}
