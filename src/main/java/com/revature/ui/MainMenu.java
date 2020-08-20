package com.revature.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
	public static int input = 0;
	public static Scanner scanner = new Scanner(System.in);
	
	public static void mainMenu() {
	System.out.println("*************************");
	System.out.println("*\tMain Menu\t*");
	System.out.println("*************************");
	
	System.out.println("Please enter one of the following options using numbers. \n"
						+ "[1] Login\n"
						+ "[2] Register\n"
						+ "[3] Exit\n");
	
	System.out.print("Enter here: ");
	try {
		input = scanner.nextInt();
		if (input < 1 || input > 3) {
			System.out.println("Input must be within range of 1 to 3.");
			mainMenu();
		}
	} catch (InputMismatchException e) {
		System.out.println("Input must be an integer.");
		mainMenu();
	}
		switch(input) {
			case 1:
					// Leads to login view
				break;
			case 2:
				System.out.println("___________________________________________________________\n");
				RegisterMenu.registerMenu();
				break;
			case 3: 
				System.out.println("Thank you for using our bank system.");
				System.exit(0);
		}
	}
	public static int getInput() {
		return input;
	}
	
}
