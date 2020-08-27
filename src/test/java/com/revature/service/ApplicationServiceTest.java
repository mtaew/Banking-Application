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
	private Application acceptedApp1;
	private Application acceptedApp2;
	private Application pendingApp1;
	private Application pendingApp2;
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
		acceptedApp1 = new Application(1, tae, "accepted");
		acceptedApp2 = new Application (2, customer, "accepted");
		pendingApp1 = new Application (1, tae, "pending");
		pendingApp2 = new Application (2, customer, "pending");
		val.add(acceptedApp1);
		when(mockedDao.insert(acceptedApp1)).thenReturn(1);
		when(mockedDao.insert(acceptedApp2)).thenReturn(1);
		when(mockedDao.findAll()).thenReturn(val);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void applyForAccountSuccess() {
		assertNotEquals(pendingApp1, testInstance.applyForAccount(tae));
	}
	
	@Test
	public void applyForAccountFailure() {
		assertEquals(null, testInstance.applyForAccount(tae));
	}
	
	@Test
	public void checkAppStatusSuccess() {
		assertNotEquals(true, testInstance.checkAppStatus(tae));
	}
	
	@Test
	public void checkAppStatusFailure() {
		assertEquals(false, testInstance.checkAppStatus(tae));
	}
}
