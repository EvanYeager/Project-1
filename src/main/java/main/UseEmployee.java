package main;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

public class UseEmployee {
	int choice = 0;
	static EmployeeServiceImpl empService = new EmployeeServiceImpl();
	private static final Logger LOGGER = Logger.getLogger(UseEmployee.class.getName());
	static final Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		UseEmployee main = new UseEmployee();
		
		System.out.println("Enter the number to input a command.\n");
		do 
		{
			displayMenu(); // display menu options to user
			
			if (scan.hasNext()) // if user enters a new input
			{
				main.choice = getInputNumber(); // get user choice		
			}
			else
			{
				LOGGER.warning("Invalid input entered. Please try again. ");
				scan.nextLine(); // advancing the scanner a line prevents an infinite loop
				continue;
			}

			try
			{
				switch (main.choice)
				{
				default:
					LOGGER.warning("Invalid number entered. Please try again.");
					return;
				case 1:
					empService.listEmployees();
					break;
				case 2:
					empService.displaySalary(empService.findByEmployeeNo(getEmployeeNumber()));
					break;
				case 3: 
					empService.displayDetails(empService.findByEmployeeNo(getEmployeeNumber()));
					break;
				case 4:
					empService.modifyDetails(empService.findByEmployeeNo(getEmployeeNumber()));
					break;
				case 5:
					empService.deleteEmployee(empService.findByEmployeeNo(getEmployeeNumber()));
					break;
				case 6:
					continue; // continues the loop; since choice == 6, the program will exit.
				}
			} catch (EmployeeNotFoundException e)
			{
				LOGGER.warning(e.toString());
			}
		}
		while (main.choice != 6);
		LOGGER.info("Exiting the program.");
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
	
	public static Integer getEmployeeNumber()
	{
		System.out.println("Enter the employee's ID number.");
		
		return getInputNumber();
	}
	
	public static Integer getInputNumber()
	{
		try
		{
			return scan.nextInt();			
		} catch (InputMismatchException e)
		{
			LOGGER.warning("A number is required. Please try again.");
			return null;
		}
	}
	
	public static String getInputString()
	{
		scan.nextLine();
		return scan.nextLine();
	}
	
}
