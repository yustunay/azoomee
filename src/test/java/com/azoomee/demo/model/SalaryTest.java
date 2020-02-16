package com.azoomee.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class SalaryTest {

	@Test
	void shouldBeSetSalaryFields() {
		Salary salary = new Salary();
		salary.setId(1);
		salary.setFromDate(LocalDate.of(2020, 2, 12));
		salary.setEndDate(LocalDate.of(2022, 2, 12));
		salary.setSalary(4000);
		
		assertEquals(1, salary.getId());
		assertEquals(LocalDate.of(2020, 2, 12), salary.getFromDate());
		assertEquals(LocalDate.of(2022, 2, 12), salary.getEndDate());
		assertEquals(4000, salary.getSalary(), 0);
	}

}
