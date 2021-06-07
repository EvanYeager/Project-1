package main;

public class Employee {
	int empNo;
	String empName;
	int salary;
	Address address;
	
	public Employee(int empNo, String empName, int salary, Address add)
	{
		setEmpNo(empNo);
		setEmpName(empName);
		setSalary(salary);
		setAddress(add);
	}
	
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString()
	{
		return String.format("\nEmployee\n--------------------------\nNo = %d\tName = %s\tSalary = %d\tAddress = %s", getEmpNo(), getEmpName(), getSalary(), getAddress().toString());
	}
}
