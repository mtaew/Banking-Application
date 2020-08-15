package com.revature.main;

import org.apache.log4j.Logger;

public class Main {

	private static Logger log = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		log.info("The applciation has started");
		Menu.mainMenu();
	}
	
}