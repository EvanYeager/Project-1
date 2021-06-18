package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
	private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class.getName());


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
		System.out.println("--------Choose what to change----------");
		System.out.println("1. Salary");
		System.out.println("2. City");
		System.out.println("3. State");
		int choice = UseEmployee.getInputNumber();
		switch(choice)
		{
		default:
			LOGGER.warning("You need to enter a number 1-3. Please try again.");
			return; // exit entire method
		case 1:
			System.out.println("Enter the employee's monthly SALARY");
			try
			{
				emp.setSalary(UseEmployee.getInputNumber());
			} catch (InputMismatchException e)
			{
				LOGGER.warning("Incorrect input entered.");
				return;
			}
			break;
		case 2:
			System.out.println("Enter the employee's CITY of residence.");
			emp.getAddress().setCity(UseEmployee.getInputString());
			break;
		case 3:
			System.out.println("Enter the employee's STATE of residence.");
			emp.getAddress().setState(UseEmployee.getInputString());
			break;
		}
		String detailChanged = choice == 1 ? "salary" : choice == 2 ? "city" : "state";
		LOGGER.info(emp.getName() + "'s " + detailChanged + " changed.");
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
