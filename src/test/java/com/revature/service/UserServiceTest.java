package com.revature.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.dao.IUserDAO;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.UserService;

public class UserServiceTest {

	@Mock
	private IUserDAO mockedDao;
	private UserService testInstance = new UserService(mockedDao);
	private User tae;
	private User customer;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		testInstance = new UserService(mockedDao);
		tae = new User(1, "Admin", "pass", "Tae", "Myles", "taeMail@gmail.com", Role.Admin);
		customer = new User(2, "customer", "pass", "cus", "man", "cusMan@gmail.com", Role.Customer);
		
		when(mockedDao.findByUsername("Admin")).thenReturn(tae);
		when(mockedDao.findByUsername("customer")).thenReturn(customer);
		when(mockedDao.insert(tae)).thenReturn(1);
		when(mockedDao.findByUsername(anyString())).thenReturn(null);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoginSucessful() {
		assertEquals(testInstance.login("Admin", "pass"), tae);
	}
	
	@Test
	public void testLoginFailed() {
		assertEquals(testInstance.login("Admin", "lal"), null);
	}
	
	@Test
	public void testRegisterSuccessful() {
		assertEquals(testInstance.register("Admin", "pass", "Tae", "Myles", "taeMail@gmail.com", Role.Admin), tae);
	}
	
	@Test
	public void testRegisterNotSuccessful() {
		assertEquals(testInstance.register("Admin", "pass", "Tae", "Myles", "taeMail@gmail.com", Role.Admin), null);
	}
	
}