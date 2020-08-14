import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.main.Menu;

public class MenuTest {
	@Before
	public void setUp() throws Exception {
		Menu.mainMenu();
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void userLogin() {
		int expected = 1;
		int actual = Menu.getInput();
		assertEquals(expected, actual);
	}

	@Test
	public void userRegister() {
		int expected = 2;
		int actual = Menu.getInput();
		assertEquals(expected, actual);
	}
	
	@Test
	public void userExit() {
		int expected = 3;
		int actual = Menu.getInput();
		assertEquals(expected, actual);
	}
	
	@Test
	public void isInteger() {
		Integer integer = new Integer(Menu.getInput());
		assertTrue(integer instanceof Integer);
	}
	
	@Test
	public void belowBoundary() {
		boolean expected = true;
		boolean actual = false;
		if (Menu.getInput() <= 0) {
			actual = true;
		}
		assertEquals(expected, actual);
	}
	
	@Test
	public void aboveBoundary() {
		boolean expected = true;
		boolean actual = false;
		if (Menu.getInput() > 3) {
			actual = true;
		}
		assertEquals(expected, actual);
	}
	
}
