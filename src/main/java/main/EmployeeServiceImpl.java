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
	
		
	public void displayMenu() 
	{
		System.out.println("\n1. List all employees");
		System.out.println("2. Display an employee's yearly salary");
		System.out.println("3. Display an employee's details");
		System.out.println("4. Modify an employee's details");
		System.out.println("5. Delete an employee");
		System.out.println("6. Quit the program");
	}

	public int getInputNumber() throws InputMismatchException
	{
		return scan.nextInt();
	}

	public void performCommand(int choice)
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
	}

	public Employee getEmployee()
	{
		System.out.println("Enter the employee number to search for");
		Employee emp = findByEmployeeNo(getInputNumber()); // finds employee by the employee number that user enters
		if (emp != null) return emp;
		else return null;
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
		emp.setSalary(getInputNumber());
		System.out.println("Enter the employee's CITY of residence.");
		emp.address.setCity(scan.nextLine());
		System.out.println("Enter the employee's STATE of residence.");
		emp.address.setState(scan.nextLine());
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
	public Employee findByEmployeeNo(int empNo) {
		return employees.stream().filter(e -> e.getEmpNo() == empNo).findFirst().orElse(null); 
	}

	@Override
	public void addEmployee(Employee emp) {
		employees.add(emp);
	}

	@Override
	public void deleteEmployee(Employee emp) {
		employees.remove(emp);
		LOGGER.info("Employee deleted. ");
	}
}
