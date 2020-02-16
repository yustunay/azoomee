package com.azoomee.demo.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.azoomee.demo.model.Employee;

@SpringBootTest
class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Test
	void testFindByHireDateAndSalary() {
		List<Employee> employeeList = employeeRepository.findByHireDateAndSalary(LocalDate.of(2020, 1, 11), 1500);
		assertTrue(employeeList.size() > 0);
	}

	@Test
	void testFindByFirstName() {
		Employee employee = employeeRepository.findByFirstName("Diana");
		assertTrue(employee!=null);
	}

}
