package com.revature.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.dao.IAccountDAO;
import com.revature.models.Account;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.AccountService;

public class AccountServiceTest {

	@Mock
	private IAccountDAO mockedDao;
	private AccountService testInstance = new AccountService(mockedDao);
	List<Account> val = new ArrayList<>();
	private User tae;
	private User customer;
	private Account acc;
	private Account cusAcc;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		testInstance = new AccountService(mockedDao);
		tae = new User(1, "Admin", "pass", "Tae", "Myles", "taeMail@gmail.com", Role.Admin);
		customer = new User(2, "customer", "pass", "cus", "man", "cusMan@gmail.com", Role.Customer);
		acc = new Account(1, 200, tae);
		cusAcc = new Account(2, 250, customer);
		List<Account> accList = new ArrayList<>();
		accList.add(acc);
		
		when(mockedDao.update(acc)).thenReturn(true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createAccountSuccessful() {
		assertNotEquals(acc, testInstance.createAccount(tae));
	}
	
	@Test
	public void createAccountFailure() {
		assertEquals(null, testInstance.createAccount(tae));
	}
	
	@Test
	public void withdrawlSuccessful() {
		assertEquals(true, testInstance.withdrawl(acc, 50.0));
	}
	
	@Test
	public void withdrawlFailure() {
		assertEquals(false, testInstance.withdrawl(acc, 501.0));
	}
	
	@Test
	public void depositSuccessful() {
		assertEquals(true, testInstance.deposit(acc, 200));
	}
	
	@Test
	public void depositFailure() {
		assertEquals(false, testInstance.deposit(acc, -5));
	}
	
	@Test
	public void transferSuccess() {
		assertEquals(true, testInstance.transfer(acc, cusAcc, 25.0));
	}
	
	@Test
	public void transferFailure() {
		assertEquals(false, testInstance.transfer(acc, cusAcc, 1000));
	}
}
