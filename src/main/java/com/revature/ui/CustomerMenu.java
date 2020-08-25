package com.revature.ui;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.AccountDAO;
import com.revature.dao.IAccountDAO;
import com.revature.dao.IUserDAO;
import com.revature.dao.UserDAO;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.ApplicationService;

public class CustomerMenu {
	private static ApplicationService appServ = new ApplicationService();
	private static AccountService accServ = new AccountService();
	private static IAccountDAO accDao = new AccountDAO();
	private static IUserDAO userDao = new UserDAO();
	public static void customerMenu(User user) {
		System.out.println("***************************");
		System.out.println("*\tCustomer Menu\t  *");
		System.out.println("***************************");
		//System.out.println(appServ.checkAppStatus(user));
		if (appServ.checkAppStatus(user)) {
			List<Account> accList = new ArrayList<>();
			accList = accDao.findAll();
			int userID = user.getId();
			int accID = 0;
			double amount = 0;
			for (int i = 0; i < accList.size(); i++) {
				if (accList.get(i).getOwner().getId() == userID) {
					accID = accList.get(i).getId();
					amount = accList.get(i).getBalance();
				}
			}
			Account acc = new Account(accID, amount, user);
			System.out.println("Please enter one of the following options using numbers. \n"
				+ "[1] Withdrawl\n"
				+ "[2] Desposit\n"
				+ "[3] Transfer\n"
				+ "[4] Main Menu\n");
			double amountIn;
			System.out.print("Enter here: ");
			int input = MainMenu.scanner.nextInt();
			switch (input) {
			case 1:
				System.out.print("Enter amount to withdrawl: ");
				amountIn = MainMenu.scanner.nextDouble();
				if (accServ.withdrawl(acc, amountIn)) {
					System.out.println("Your requested amount has been sucessfully"
							+ " withdrawn from your bank account!");
					customerMenu(user);
				}
				break;
			
			case 2:
				System.out.print("Enter amount to deposit: ");
				amountIn = MainMenu.scanner.nextDouble();
				if (accServ.deposit(acc, amountIn)) {
					System.out.println("Your requested amount has been sucessfully"
							+ " deposited into your bank account!");
					customerMenu(user);
				}
				break;
				
			case 3:
				Account to = new Account();
				System.out.print("Enter amount to transfer: ");
				amountIn = MainMenu.scanner.nextDouble();
				System.out.print("Enter the username: ");
				String userName = MainMenu.scanner.nextLine();
				userName = MainMenu.scanner.nextLine();
				User transUser = userDao.findByUsername(userName);
				for (int i = 0; i < accList.size(); i++) {
					if (accList.get(i).getOwner().equals(transUser)) {
						to = accList.get(i);
					}
				}
				if (accServ.transfer(acc, to, amountIn)) {
					System.out.println("Transfer successful!\n");
				}
				customerMenu(user);
				break;
			case 4:
				MainMenu.mainMenu();
			}
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
