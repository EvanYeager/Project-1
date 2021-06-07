package main;

public interface EmployeeService {
	public void displayAllEmployees();
	public int calculateYearlySalary(Employee emp);
	public Employee findByEmployeeNo(int empNo);
	public void addEmployee(Employee emp);
	public void deleteEmployee(Employee emp);
}
