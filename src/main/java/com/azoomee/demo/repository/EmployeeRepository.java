package com.azoomee.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.azoomee.demo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e, Salary s WHERE e.salary.id = s.id and e.hireDate >= ?1 AND e.salary.salary >= ?2")
	List<Employee> findByHireDateAndSalary(LocalDate hireDate, double salary);
	Employee findByFirstName(String firstName);
}
