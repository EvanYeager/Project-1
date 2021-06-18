package main;

public interface EmployeeService {
	public void displayAllEmployees();
	public int calculateYearlySalary(Employee emp);
	public Employee findByEmployeeNo(int empNo) throws EmployeeNotFoundException;
	public void addEmployee(Employee emp);
	public void deleteEmployee(Employee emp);
}
