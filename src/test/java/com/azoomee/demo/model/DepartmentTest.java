package com.azoomee.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

import com.azoomee.demo.repository.TitleRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DepartmentTest {

	@LocalServerPort
	private int port;
		
	@Autowired
	private TitleRepository titleRepository;
	
	@Test
	void shouldBeSetDepartmentFields() {
		
		Title title = titleRepository.findByTitleCode("MCOR");
		
		Address address = new Address.AddressBuilder()
									 .setLine1("82  Greyfriars Road")
									 .setCity("London")
									 .setStateProvinceCounty("CARDESTON")
									 .setCountryId("UK")
									 .setZipOrPostcode("SY5 3XJ")
									 .build();
		
		Office office = new Office("Central Building", address);
		Department department = new Department("M01", "Marketing", office);
		Salary salary = new Salary(3750, LocalDate.of(2020, 1, 18), null);
		
		Employee employee = new Employee.EmployeeBuilder()
										.setBirthDate(LocalDate.of(2010, 4, 27))
										.setFirstName("John")
										.setLastName("Carter")
										.setGender(Gender.M)
										.setHireDate(LocalDate.of(2020, 1, 18))
										.setSalary(salary)
										.setTitle(title)
										.setDepartment(department)
										.build();
		
		department.addEmployee(employee);
		//DEPARTMENT
		assertEquals("Marketing", department.getDeptName());		
		assertEquals("M01", department.getDeptNo());
		//EMPLOYEE BASE INFO
		assertEquals("John", department.getEmployee().get(0).getFirstName());
		assertEquals("Carter", department.getEmployee().get(0).getLastName());
		assertEquals(Gender.M, department.getEmployee().get(0).getGender());
		assertEquals(LocalDate.of(2020, 1, 18), department.getEmployee().get(0).getHireDate());
		//SALARY
		assertEquals(3750, department.getEmployee().get(0).getSalary().getSalary(), 0);
		assertEquals(LocalDate.of(2020, 1, 18), department.getEmployee().get(0).getSalary().getFromDate());
		//TITLE
		assertEquals("Marketing coordinator", department.getEmployee().get(0).getTitle().getTitle());
		assertEquals("MCOR", department.getEmployee().get(0).getTitle().getTitleCode());
		//OFFICE & ADDRESS
		assertEquals("Central Building", department.getOffice().getName());
		assertEquals("82  Greyfriars Road", department.getOffice().getAddress().getLine1());
		assertEquals("London", department.getOffice().getAddress().getCity());
		assertEquals("CARDESTON", department.getOffice().getAddress().getStateProvinceCounty());
		assertEquals("SY5 3XJ", department.getOffice().getAddress().getZipOrPostcode());
		assertEquals("UK", department.getOffice().getAddress().getCountryId());
	}

}
