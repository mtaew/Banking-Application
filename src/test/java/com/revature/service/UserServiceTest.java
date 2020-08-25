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
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
//	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
//		testInstance = new UserService(mockedDao);
//		tae = new User(1, "tae123", "easy123", Role.Customer);
//		when(mockedDao.findByUsername("tae123")).thenReturn(tae);
//		when(mockedDao.findByUsername(anyString())).thenReturn(null);
//	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoginSucessful() {
		assertEquals(testInstance.login("tae123", "easy123"), tae);
	}

}
