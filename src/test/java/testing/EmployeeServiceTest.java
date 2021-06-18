package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import main.Address;
import main.Employee;
import main.EmployeeNotFoundException;
import main.EmployeeServiceImpl;

public class EmployeeServiceTest {
	EmployeeServiceImpl empService;
	
	@BeforeEach
	void initEach()
	{
		empService = new EmployeeServiceImpl();
	}
	
	@Test
	void testEmpSalary()
	{
		Employee emp = new Employee(1, "Albert", 3_750, new Address("Dallas", "Texas"));
		assertEquals(45_000, empService.calculateYearlySalary(emp));
	}
	
	@Test
	void testFindEmployeeByNo() throws EmployeeNotFoundException
	{
		assertEquals(EmployeeServiceImpl.employees.get(0), empService.findByEmployeeNo(1));
		// Because the employee numbers start at 1, employees.get(0) should return the same as finding the employee with the number "1"
		// Basically, I use a different method to check if the other method works
	}
	
	@Test
	void testSetSalary() throws EmployeeNotFoundException
	{
		EmployeeServiceImpl.employees.get(0).setSalary(1_000);
		assertEquals(EmployeeServiceImpl.employees.get(0).getSalary(), 1000);
		
		
		// setSalary() should convert negative numbers to 0
		EmployeeServiceImpl.employees.get(0).setSalary(-6);
		assertEquals(EmployeeServiceImpl.employees.get(0).getSalary(), 0);
	}
	

	
	@Test
	void testModifyDetails()
	{
		System.out.println("Enter: 5, 'String1', 'String2'");
		empService.modifyDetails(EmployeeServiceImpl.employees.get(0));
		assertEquals(EmployeeServiceImpl.employees.get(0).getSalary(), 5);
		assertEquals(EmployeeServiceImpl.employees.get(0).getAddress().getCity(), "String1");
		assertEquals(EmployeeServiceImpl.employees.get(0).getAddress().getState(), "String2");
	}
	
	
	
//	@Test
//	void testGetEmployee()
//	{
//		empService.getEmployee();
//	}
	
//	@Test
//	void testModify()
//	{
//		Employee emp = new Employee(1, "Albert", 3_750, new Address("Moms", "House"));
//		empService.modifyDetails(empService.employees.get(0));
//		assertEquals(true, emp.equals(empService.employees.get(0)));
//	}
}
