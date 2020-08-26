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

import com.revature.dao.IApplicationDAO;
import com.revature.models.Application;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.ApplicationService;

public class ApplicationServiceTest {
	
	@Mock
	private IApplicationDAO mockedDao;
	private ApplicationService testInstance = new ApplicationService(mockedDao);
	private User tae;
	private User customer;
	private Application acceptedApp;
	private Application pendingApp;
	private List<Application> val = new ArrayList<>();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		testInstance = new ApplicationService(mockedDao);
		tae = new User(1, "Admin", "pass", "Tae", "Myles", "taeMail@gmail.com", Role.Admin);
		customer = new User(2, "customer", "pass", "cus", "man", "cusMan@gmail.com", Role.Customer);
		acceptedApp = new Application(1, tae, "accepted");
		pendingApp = new Application (1, tae, "pending");
		when(mockedDao.insert(pendingApp)).thenReturn(1);
		when(mockedDao.findAll()).thenReturn(val);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void applyForAccountSuccess() {
		assertNotEquals(pendingApp, (testInstance.applyForAccount(tae)));
	}
	
	@Test
	public void applyForAccountFailure() {
		assertEquals(null, testInstance.applyForAccount(tae));
	}
	
	@Test
	public void checkAppStatusSuccess() {
		assertEquals(true, !(testInstance.checkAppStatus(tae)));
	}
	
	@Test
	public void checkAppStatusFailure() {
		assertEquals(false, testInstance.checkAppStatus(tae));
	}
}
