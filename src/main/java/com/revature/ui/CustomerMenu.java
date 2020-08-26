package com.revature.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDAO;
import com.revature.dao.ApplicationDAO;
import com.revature.dao.IAccountDAO;
import com.revature.dao.IApplicationDAO;
import com.revature.dao.IUserDAO;
import com.revature.dao.UserDAO;
import com.revature.main.Main;
import com.revature.models.Account;
import com.revature.models.Application;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.ApplicationService;

public class CustomerMenu {
	private static Logger log = Logger.getLogger(CustomerMenu.class);
	private static ApplicationService appServ = new ApplicationService();
	private static AccountService accServ = new AccountService();
	private static IAccountDAO accDao = new AccountDAO();
	private static IUserDAO userDao = new UserDAO();
	private static IApplicationDAO appDao = new ApplicationDAO();
	
	public static void customerMenu(User user) {
		System.out.println("\t\t***************************");
		System.out.println("\t\t*\tCustomer Menu\t  *");
		System.out.println("\t\t***************************");
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
			
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println(" Your current balance: " + amount);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
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
					log.info(user.getUsername() + " has withdrawn " + amountIn + " from its account.\n");
					customerMenu(user);
				}
				else {
					customerMenu(user);
				}
				break;
			
			case 2:
				System.out.print("Enter amount to deposit: ");
				amountIn = MainMenu.scanner.nextDouble();
				if (accServ.deposit(acc, amountIn)) {
					System.out.println("Your requested amount has been sucessfully"
							+ " deposited into your bank account!");
					log.info(user.getUsername() + " has deposited " + amountIn + " from its account.\n");
					customerMenu(user);
				}
				else {
					customerMenu(user);
				}
				break;
				
			case 3:
				Account to = new Account();
				System.out.print("Enter amount to transfer: ");
				amountIn = MainMenu.scanner.nextDouble();
				System.out.print("Enter the username that you want to transfer to: ");
				String userName = MainMenu.scanner.nextLine();
				userName = MainMenu.scanner.nextLine();
				User transUser = userDao.findByUsername(userName);
				for (int i = 0; i < accList.size(); i++) {
					if (accList.get(i).getOwner().equals(transUser)) {
						to = accList.get(i);
					}
				}
				try {
					if (accServ.transfer(acc, to, amountIn)) {
						System.out.println("Transfer successful!\n");
						log.info(user.getUsername() + " has transferred to " + userName + " with amount of " + amountIn + " from its account.");
					}
				} catch (NullPointerException e) {
					System.out.println("No such user exists!");
					customerMenu(user);
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
			List<Application> listApp = new ArrayList<>();
			listApp = appDao.findAll();
			System.out.print("Enter here: ");
			int input = MainMenu.scanner.nextInt();
			switch (input) {
			case 1:
				for (int i = 0; i < listApp.size(); i++) {
					if (listApp.get(i).getOwner().getId() == user.getId()) {
						if (listApp.get(i).getAppStatus().equals("denied")) {
							System.out.println("You have been denied, please contact one of our employees for further details.\n");
							customerMenu(user);
						}
					}
				}
				if (appServ.checkForDupes(user)) {
					customerMenu(user);
				}
				appServ.applyForAccount(user);
				System.out.println("You have sucessfully applied for an account!\n"
						+ "Please login at a later time to see if your account has been confirmed or not by one of our employees!\n");
				customerMenu(user);
				break;
			case 2:
				MainMenu.mainMenu();
				break;
			}
		}

	}
}
