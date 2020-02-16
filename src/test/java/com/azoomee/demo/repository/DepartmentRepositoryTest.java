package com.azoomee.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.azoomee.demo.model.Department;

@SpringBootTest
class DepartmentRepositoryTest {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Test
	void testFindByDeptNo() {
		Department department = departmentRepository.findByDeptNo("IT01");
		
		assertEquals(1, department.getId());
		assertEquals("IT01", department.getDeptNo());
		assertEquals("Information Technology", department.getDeptName());
		assertEquals(1, department.getEmployee().size());
		assertEquals("Central Office", department.getOffice().getName());
		assertEquals(1001, department.getOffice().getId());
		assertEquals(1000, department.getOffice().getAddress().getId());
		assertEquals("London", department.getOffice().getAddress().getCity());
		assertEquals("UK", department.getOffice().getAddress().getCountryId());
		assertEquals("10 Downing Street", department.getOffice().getAddress().getLine1());
		assertEquals("SW1A 2AA", department.getOffice().getAddress().getZipOrPostcode());
	}

}
