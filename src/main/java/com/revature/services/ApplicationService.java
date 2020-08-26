package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.AccountDAO;
import com.revature.dao.ApplicationDAO;
import com.revature.dao.IAccountDAO;
import com.revature.dao.IApplicationDAO;
import com.revature.dao.IUserDAO;
import com.revature.models.Account;
import com.revature.models.Application;
import com.revature.models.User;
import com.revature.ui.EmployeeMenu;
import com.revature.ui.MainMenu;

public class ApplicationService {
	private IApplicationDAO appDao = new ApplicationDAO();
	private IAccountDAO accDao = new AccountDAO();
	private AccountService accServ = new AccountService();
	
	public ApplicationService() {
		super();
		this.appDao = new ApplicationDAO();
	}
	
	public ApplicationService(IApplicationDAO appDao) {
		super();
		this.appDao = appDao;
	}
	
	public Application applyForAccount(User u) {
		Application app = new Application(0, u, "pending");
		int new_id = appDao.insert(app);;
		if (new_id == 0) {
			return null;
			// Insert failed
		}
		app.setId(new_id);
		return app;
	}
	
	public boolean checkForDupes(User user) {
		List<Application> app = new ArrayList<>();
		app = appDao.findAll();
		int userId = user.getId();
		for (int i = 0; i < app.size(); i++) {
			if (app.get(i).getOwner().getId() == userId) {
				System.out.println("You have already applied!\n"
						+ "Please wait until it's approved or denied.");
				return true;
			}
		}
		return false;
	}
	
	public boolean checkAppStatus(User user) {
		List<Application> appList = new ArrayList<>();
		List<Account> accList = new ArrayList<>();
		appList = appDao.findAll();
		accList = accDao.findAll();
		int id = user.getId(); // User id 
		int applicantId = 0;
		for (int i = 0; i < appList.size(); i++) {
			if (appList.get(i).getOwner().getId() == id) {
				applicantId = appList.get(i).getOwner().getId();
			}
		}
		for (int i = 0; i < accList.size(); i++) {
			if (accList.get(i).getOwner().getId() == applicantId) {
				return true;
			}
		}
		return false;
	}
	
	public void acceptApps(User user) {
		int id = user.getId(); // User id 
		int applicantId = 0;
		List<Application> appList = new ArrayList<>();
		appList = appDao.findAll();
		for (int i = 0; i < appList.size(); i++) {
			if (appList.get(i).getOwner().getId() == id) {
				applicantId = appList.get(i).getId();
			}
		}
		Application app = new Application(applicantId, user, "accepted");
		if (appDao.update(app)) {
			accServ.createAccount(user);
			System.out.println("Applicant has been accepted!\n");
		} else {
			System.out.println("Applicant was not accepted!\n");
		}
	}
	
	public void denyApps(User user) {
		int id = 0;
		try {
			id = user.getId(); // User id 
		} catch (NullPointerException e) {
			System.out.println("Not a valid username, please try again!");
			MainMenu.mainMenu();
		}
		int applicantId = 0;
		List<Application> appList = new ArrayList<>();
		appList = appDao.findAll();
		for (int i = 0; i < appList.size(); i++) {
			if (appList.get(i).getOwner().getId() == id) {
				applicantId = appList.get(i).getId();
			}
		}
		Application app = new Application(applicantId, user, "denied");
		if (appDao.update(app)) {
			System.out.println("Applicant has been denied!\n");
		} else {
			System.out.println("Applicant was not denied!\n");
		}
	}
	
	public void viewApplications() {
		String username, appstatus;
		int appID;
		List<Application> appList = new ArrayList<>();
		int counter = 1;
		appList = appDao.findAll();
		for (int i = 0; i < appList.size(); i++) {
			System.out.print(counter +". Application ID: ");
			appID = appList.get(i).getId();
			System.out.print(appID);
			System.out.print(" | Username: ");
			username = appList.get(i).getOwner().getUsername();
			System.out.print(username);
			System.out.print(" | Application Status: ");
			appstatus = appList.get(i).getAppStatus();
			System.out.print(appstatus + "\n");
			counter++;
		}
		pressAnyKeyToContinue();
	}
	
	 private void pressAnyKeyToContinue()
	 { 
	        System.out.println("Press Enter key to continue...");
	        try
	        {
	            System.in.read();
	        }  
	        catch(Exception e)
	        {}  
	 }
}
