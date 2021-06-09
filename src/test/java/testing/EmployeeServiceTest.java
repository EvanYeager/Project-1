package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import main.Address;
import main.Employee;
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
	void testFindEmployeeByNo()
	{
		assertEquals(EmployeeServiceImpl.employees.get(0), empService.findByEmployeeNo(1));
		// Because the employee numbers start at 1, employees.get(0) should return the same as finding the employee with the number "1"
		// Basically, I use a different method to check if the other method works
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
