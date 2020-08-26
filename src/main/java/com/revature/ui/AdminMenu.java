package com.revature.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
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

public class AdminMenu {
	private static Logger log = Logger.getLogger(Main.class);
	private static IApplicationDAO appDao = new ApplicationDAO();
	private static IUserDAO userDao = new UserDAO();
	private static IAccountDAO accDao = new AccountDAO();
	private static ApplicationService appServ = new ApplicationService();
	private static AccountService accServ = new AccountService();
	private static String strInput;
	private static User userObj;
	private static List<Account> accList = new ArrayList<>();
	private static List<Application> appList = new ArrayList<>();
	
	public static void adminMenu(User user) {
		System.out.println("\t\t**************************");
		System.out.println("\t\t*\tAdmin Menu\t *");
		System.out.println("\t\t**************************");
		System.out.println("Please enter one of the following options using numbers. \n"
				+ "[1] View All Acount Information\n"
				+ "[2] Accept/Deny applications\n"
				+ "[3] Withdrawl From Accounts\n"
				+ "[4] Deposit To Accounts\n"
				+ "[5] Transfer Between Accounts\n"
				+ "[6] Cancel Accounts\n"
				+ "[7] Main Menu\n");
		System.out.print("Enter here: ");
		int input = 0;
		try {
			input = MainMenu.scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Not an integer");
			adminMenu(user);
		}

		switch (input) {
		case 1:
			accServ.viewCompleteAccountInfo();
			adminMenu(user);
			break;
		case 2:
			appServ.viewApplications();
			System.out.println("[1] Accept applications\n"
					+ "[2] Deny applications\n"
					+ "[3] Exit\n");
			System.out.print("Enter here: ");
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
			case 3:
				adminMenu(user);
			}
			break;
		case 3:
			accServ.viewCompleteAccountInfo();
			MainMenu.scanner.nextLine(); // Consume \n from enter to cont.
			MainMenu.scanner.nextLine(); // Consume \n
			accList = accDao.findAll();
			int accId = 0;
			Double accBal = 0.0;
			Double bal = 0.0;
			System.out.print("Username to withdrawl from: ");
			String userName = MainMenu.scanner.nextLine();
			User transUser = userDao.findByUsername(userName);
			try {
				for (int i = 0; i < accList.size(); i++) {
					if (accList.get(i).getOwner().getId() == transUser.getId()) {
						accId = accList.get(i).getId();
						accBal = accList.get(i).getBalance();
					}
				}
			} catch (NullPointerException e) {
				System.out.println("Invalid username, please try again!\n");
				adminMenu(user);
			}
			Account newAcc = new Account(accId, accBal, transUser);
			System.out.print("Amount to withdrawl: ");
			bal = MainMenu.scanner.nextDouble();
			if (accServ.withdrawl(newAcc, bal)) {
				System.out.println("Withdrawl was sucessful!");
				log.info(user.getUsername() + " has withdrawn " + bal + " from " + transUser.getUsername());
				adminMenu(user);
			}
			else {
				adminMenu(user);
			}
			break;
		case 4:
			accServ.viewCompleteAccountInfo();
			MainMenu.scanner.nextLine();
			MainMenu.scanner.nextLine();
			accList = accDao.findAll();
			int accId0 = 0;
			Double accBal0 = 0.0;
			Double bal0 = 0.0;
			System.out.print("Username to deposit to: ");
			String userName0 = MainMenu.scanner.nextLine();
			User transUser0 = userDao.findByUsername(userName0);
			try {
				for (int i = 0; i < accList.size(); i++) {
					if (accList.get(i).getOwner().getId() == transUser0.getId()) {
						accId0 = accList.get(i).getId();
						accBal0 = accList.get(i).getBalance();
					}
				}
			} catch (NullPointerException e) {
				System.out.println("Invalid username, please try again!\n");
				adminMenu(user);
			}

			Account newAcc0 = new Account(accId0, accBal0, transUser0);
			System.out.print("Amount to deposit: ");
			bal0 = MainMenu.scanner.nextDouble();
			if (accServ.deposit(newAcc0, bal0)) {
				System.out.println("Deposit was successful!");
				log.info(user.getUsername() + " has deposited " + accBal0 + " to " + transUser0.getUsername());
				adminMenu(user);
			}
			else {
				adminMenu(user);
			}
			break;
		case 5: 
			accServ.viewCompleteAccountInfo();
			MainMenu.scanner.nextLine();
			MainMenu.scanner.nextLine();
			accList = accDao.findAll();
			int accId1 = 0;
			int accId2 = 0;
			Double accBal1 = 0.0;
			Double accBal2 = 0.0;
		
			System.out.print("Username to withdrawl from: ");
			String userName1 = MainMenu.scanner.nextLine();
			User transUser1 = userDao.findByUsername(userName1);
			try {
				for (int i = 0; i < accList.size(); i++) {
					if (accList.get(i).getOwner().getId() == transUser1.getId()) {
						accId1 = accList.get(i).getId();
						accBal1 = accList.get(i).getBalance();
					}
				}
			} catch (NullPointerException e) {
				System.out.println("Invalid username, please try again!\n");
				adminMenu(user);
			}

			Account account1 = new Account(accId1, accBal1, transUser1);
			
			System.out.print("Username to deposit to: ");
			String userName2 = MainMenu.scanner.nextLine();
			User transUser2 = userDao.findByUsername(userName2);
			try {
				for (int i = 0; i < accList.size(); i++) {
					if (accList.get(i).getOwner().getId() == transUser2.getId()) {
						accId2 = accList.get(i).getId();
						accBal2 = accList.get(i).getBalance();
					}
				}
			} catch (NullPointerException e) {
				System.out.println("Invalid username, please try again!\n");
				adminMenu(user);
			}
			
			Account account2 = new Account(accId2, accBal2, transUser2);
			System.out.print("How much would you like to transfer?: ");
			double inputBalance = MainMenu.scanner.nextDouble();
			if (accServ.transfer(account1, account2, inputBalance)) {
				System.out.println("Transfer was successful!");
				log.info(user.getUsername() + " has transferred " + inputBalance  + " from " 
						+ account1.getOwner().getUsername() + " to " + account2.getOwner().getUsername());
				adminMenu(user);
			}
			else {
				adminMenu(user);
			}
			break;
		case 6:
			int cancelId = 0;
			accServ.viewCompleteAccountInfo();
			System.out.print("Enter the account id to cancel: ");
			try {
				cancelId = MainMenu.scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Not an integer");
				adminMenu(user);
			}
			int appIdToCancel = 0;
			appList = appDao.findAll();
			accList = accDao.findAll();
			for (int i = 0; i < appList.size(); i++) {
				if (appList.get(i).getOwner().getId() == accList.get(i).getOwner().getId()) {
					appIdToCancel = appList.get(i).getId();
				}
			}
			if (accDao.delete(cancelId)) {
				System.out.println("Account has been successfully deleted!");
				appDao.delete(appIdToCancel);
				adminMenu(user);
			}
			else {
				adminMenu(user);
			}
		case 7:
			MainMenu.mainMenu();
			break;
		}
	}
}
