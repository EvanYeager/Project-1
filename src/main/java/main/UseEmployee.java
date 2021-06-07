package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

public class UseEmployee {
	int choice = 0;
	static EmployeeServiceImpl empService = new EmployeeServiceImpl();
	private static final Logger LOGGER = Logger.getLogger(UseEmployee.class.getName());

	public static void main(String[] args) {
		UseEmployee main = new UseEmployee();
		
		System.out.println("Enter the number to input a command.\n");
		do 
		{
			empService.displayMenu();
			
			if (empService.hasNext())
			{
				main.choice = empService.getInputNumber();					
			}
			else
			{
				LOGGER.warning("Invalid input entered. Please try again. ");
				empService.nextLine(); // advancing the scanner a line prevents an infinite loop
				continue;
			}
			empService.performCommand(main.choice);
		}
		while (main.choice != 6);
		System.out.println("Goodbye");
	}

	
}
