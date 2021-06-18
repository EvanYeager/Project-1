package main;

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
			displayMenu(); // display menu options to user
			
			if (empService.hasNext()) // if user enters a new input
			{
				main.choice = empService.getInputNumber(); // get user choice		
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

	private static void displayMenu() 
	{
		System.out.println();
		System.out.println("1. List all employees");
		System.out.println("2. Display an employee's yearly salary");
		System.out.println("3. Display an employee's details");
		System.out.println("4. Modify an employee's details");
		System.out.println("5. Delete an employee");
		System.out.println("6. Quit the program");
	}
	
}
