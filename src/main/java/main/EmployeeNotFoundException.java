package main;

public class EmployeeNotFoundException extends Exception {
	
	public EmployeeNotFoundException()
	{
		
	}
	
	public EmployeeNotFoundException(String message)
	{
		super(message);
	}
	
	@Override
	public String toString()
	{
		return "Employee not found by that number.";
	}
	
	
}
