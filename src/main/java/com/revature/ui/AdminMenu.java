package com.revature.ui;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.AccountDAO;
import com.revature.dao.ApplicationDAO;
import com.revature.dao.IAccountDAO;
import com.revature.dao.IApplicationDAO;
import com.revature.dao.IUserDAO;
import com.revature.dao.UserDAO;
import com.revature.models.Account;
import com.revature.models.Application;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.ApplicationService;

public class AdminMenu {
	private static IApplicationDAO appDao = new ApplicationDAO();
	private static IUserDAO userDao = new UserDAO();
	private static IAccountDAO accDao = new AccountDAO();
	private static ApplicationService appServ = new ApplicationService();
	private static AccountService accServ = new AccountService();
	private static String strInput;
	private static User userObj;
	private static List<Account> accList = new ArrayList<>();
	private static List<Application>appList = new ArrayList<>();
	
	public static void adminMenu(User user) {
		System.out.println("*****************************");
		System.out.println("*\tAdmin Menu\t    *");
		System.out.println("*****************************");
		System.out.println("Please enter one of the following options using numbers. \n"
				+ "[1] Accept/Deny applications\n"
				+ "[2] Withdrawl From Accounts\n"
				+ "[3] Deposit To Accounts\n"
				+ "[4] Transfer Between Accounts\n"
				+ "[5] Cancel Accounts\n"
				+ "[6] Main Menu\n");
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
		case 2:
			accServ.viewCompleteAccountInfo();
			accList = accDao.findAll();
			int accId = 0;
			Double accBal = 0.0;
			Double bal = 0.0;
			MainMenu.scanner.nextLine(); // Consume /n
			System.out.print("Username to withdrawl from: ");
			String userName = MainMenu.scanner.nextLine();
			User transUser = userDao.findByUsername(userName);
			System.out.println(transUser);
			for (int i = 0; i < accList.size(); i++) {
				if (accList.get(i).getOwner().getId() == transUser.getId()) {
					accId = accList.get(i).getId();
					accBal = accList.get(i).getBalance();
				}
			}
			Account newAcc = new Account(accId, accBal, transUser);
			System.out.print("Amount to withdrawl: ");
			bal = MainMenu.scanner.nextDouble();
			if (accServ.withdrawl(newAcc, bal)) {
				System.out.println("Success!");
				adminMenu(user);

			}
			break;
		case 3:
			accServ.viewCompleteAccountInfo();
			accList = accDao.findAll();
			int accId0 = 0;
			Double accBal0 = 0.0;
			Double bal0 = 0.0;
			MainMenu.scanner.nextLine(); // Consume /n
			System.out.print("Username to deposit to: ");
			String userName0 = MainMenu.scanner.nextLine();
			User transUser0 = userDao.findByUsername(userName0);
			for (int i = 0; i < accList.size(); i++) {
				if (accList.get(i).getOwner().getId() == transUser0.getId()) {
					accId0 = accList.get(i).getId();
					accBal0 = accList.get(i).getBalance();
				}
			}
			Account newAcc0 = new Account(accId0, accBal0, transUser0);
			System.out.print("Amount to deposit: ");
			bal0 = MainMenu.scanner.nextDouble();
			if (accServ.deposit(newAcc0, bal0)) {
				System.out.println("Success!");
				adminMenu(user);

			}
			break;
		case 4: 
			accServ.viewCompleteAccountInfo();
			accList = accDao.findAll();
			int accId1 = 0;
			int accId2 = 0;
			Double accBal1 = 0.0;
			Double accBal2 = 0.0;
			MainMenu.scanner.nextLine(); // Consume /n
			System.out.print("Username to withdrawl from: ");
			String userName1 = MainMenu.scanner.nextLine();
			User transUser1 = userDao.findByUsername(userName1);
			for (int i = 0; i < accList.size(); i++) {
				if (accList.get(i).getOwner().getId() == transUser1.getId()) {
					accId1 = accList.get(i).getId();
					accBal1 = accList.get(i).getBalance();
				}
			}
			Account account1 = new Account(accId1, accBal1, transUser1);
			
			System.out.print("Username to deposit to: ");
			String userName2 = MainMenu.scanner.nextLine();
			User transUser2 = userDao.findByUsername(userName2);
			for (int i = 0; i < accList.size(); i++) {
				if (accList.get(i).getOwner().getId() == transUser2.getId()) {
					accId2 = accList.get(i).getId();
					accBal2 = accList.get(i).getBalance();
				}
			}
			Account account2 = new Account(accId2, accBal2, transUser2);
			System.out.println("How much would you like to transfer?: ");
			double inputBalance = MainMenu.scanner.nextDouble();
			if (accServ.transfer(account1, account2, inputBalance)) {
				System.out.println("Success!");
				adminMenu(user);
			}
			break;
		case 5:
			accServ.viewCompleteAccountInfo();
			System.out.print("Enter the account id to cancel: ");
			int cancelId = MainMenu.scanner.nextInt();
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
		}
	}
}
