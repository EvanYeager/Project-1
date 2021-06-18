package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

public class EmployeeServiceImpl implements EmployeeService{
	public static ArrayList<Employee> employees = new ArrayList<Employee>()
		{
			{
				add(new Employee(1, "Albert", 3_750, new Address("Dallas", "Texas")));
				add(new Employee(2, "Edwin", 4_000, new Address("Nashville", "Tennessee")));
				add(new Employee(3, "Houston", 3_500, new Address("Chicago", "Illinois")));
				add(new Employee(4, "Kenji", 3_750, new Address("Tampa", "Florida")));
			}
		};
	Scanner scan = new Scanner(System.in);
	private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class.getName());


	public int getInputNumber() throws InputMismatchException
	{
		return scan.nextInt();
	}

	public void performCommand(int choice)
	{
		try
		{
			switch (choice)
			{
			default:
				LOGGER.warning("Invalid number entered. Please try again.");
				return;
			case 1:
				listEmployees();
				break;
			case 2:
				displaySalary(getEmployee());
				break;
			case 3: 
				displayDetails(getEmployee());
				break;
			case 4:
				modifyDetails(getEmployee());
				break;
			case 5:
				deleteEmployee(getEmployee());
				break;
			case 6:
				return; // continues the loop; since choice == 6, the program will exit.
			}
		} catch (EmployeeNotFoundException e)
		{
			LOGGER.warning(e.toString());
		}
	}

	// finds employee by the employee number that user enters
	public Employee getEmployee() throws EmployeeNotFoundException
	{
		System.out.println("Enter the employee number to search for");
		int num = getInputNumber();
		
		try
		{
			return findByEmployeeNo(num); 
		} catch (EmployeeNotFoundException e)
		{
			throw e; // pass on the exception from findByEmployeeNum() to the caller of this function
		}
	}

	public void listEmployees()
	{
		employees.forEach(System.out::println);
	}

	public void displaySalary(Employee emp)
	{
		System.out.println(calculateYearlySalary(emp));
	}

	public void displayDetails(Employee emp)
	{
		System.out.println(emp);
	}

	public void modifyDetails(Employee emp)
	{
		System.out.println("Enter the employee's monthly SALARY.");
		try
		{
			emp.setSalary(getInputNumber());			
		} catch (InputMismatchException e)
		{
			LOGGER.warning("You need to input a number for the employee's monthly salary.");
		}
		
		System.out.println("Enter the employee's CITY of residence.");
		emp.getAddress().setCity(scan.nextLine());
		System.out.println("Enter the employee's STATE of residence.");
		emp.getAddress().setState(scan.nextLine());
		
		LOGGER.info(emp.getName() + "'s details changed.");
	}
	
	public void nextLine()
	{
		scan.next(); 
	}
	
	public boolean hasNext()
	{
		return scan.hasNextInt();
	}

	@Override
	public void displayAllEmployees() {
		employees.forEach(System.out::println);
	}

	@Override
	public int calculateYearlySalary(Employee emp) {
		return emp.getSalary() * 12;
	}

	@Override
	public Employee findByEmployeeNo(int empNo) throws EmployeeNotFoundException {
		/*
		 * Steps:
		 * 1. convert employee list to stream
		 * 2. filter stream to only employees that have the specified employee number (should be <= 1)
		 * 3. get the first element of the filtered stream
		 * 4. if there is a match, return that employee
		 * 5. if there is not a match, throw an EmployeeNotFoundException
		 * 
		 */
		
		return employees.stream().filter(e -> e.getEmpNo() == empNo).findFirst().orElseThrow(() -> new EmployeeNotFoundException());
	}

	@Override
	public void addEmployee(Employee emp) {
		employees.add(emp);
		LOGGER.info(emp.getName() + " successfully added to the system.");
	}

	@Override
	public void deleteEmployee(Employee emp) {
		employees.remove(emp);
		LOGGER.info("Employee deleted. ");
	}
}
