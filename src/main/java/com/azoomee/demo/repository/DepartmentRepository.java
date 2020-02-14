package com.azoomee.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.azoomee.demo.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	Department findByDeptNo(String deptNo);
}
