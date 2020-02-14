package com.azoomee.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.azoomee.demo.model.Department;
import com.azoomee.demo.model.Employee;
import com.azoomee.demo.model.EmployeeDTO;
import com.azoomee.demo.model.Title;
import com.azoomee.demo.repository.DepartmentRepository;
import com.azoomee.demo.repository.EmployeeRepository;
import com.azoomee.demo.repository.TitleRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepository;

	@Autowired
	private TitleRepository titleRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	public List<Employee> getAllEmployees() {
		return empRepository.findAll();
	}

	public Employee getEmployeeById(long empId) {
		return empRepository.findById(empId).orElse(new Employee());
	}

	public Employee createEmployee(EmployeeDTO employeeDTO) {
		Employee employee = convertEmployeeDTOToEmployee(employeeDTO);
		return empRepository.save(employee);
	}

	public Employee updateEmployee(@PathVariable long id, @RequestBody EmployeeDTO employeeDTO) {
		Employee employee = convertEmployeeDTOToEmployee(employeeDTO);
		employee.setId(id);
		return empRepository.save(employee);
	}

	public void deleteEmployeeById(long id) {
		empRepository.deleteById(id);
	}
	
	public List<Employee> getEmpWithHireDateAndSalary(LocalDate hireDate, double salary) {
		return empRepository.findByHireDateAndSalary(hireDate, salary);
	}

	private Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		Employee employeeOnDB = new Employee();

		//Update operation
		if (employeeDTO.getId() > 0) {
			employeeOnDB = empRepository.findById(employeeDTO.getId()).orElse(employee);

			if (employeeDTO.getSalary().getId() == 0) {
				employeeDTO.getSalary().setId(employeeOnDB.getSalary().getId());
			}
		}

		employee.setBirthDate(employeeDTO.getBirthDate());
		employee.setFirstName(employeeDTO.getFirstName());
		employee.setLastName(employeeDTO.getLastName());
		employee.setGender(employeeDTO.getGender());
		employee.setHireDate(employeeDTO.getHireDate());
		employee.setSalary(employeeDTO.getSalary());

		if (employeeDTO.getTitleId() > 0) {
			Optional<Title> title = titleRepository.findById(employeeDTO.getTitleId());
			if (title.isPresent())
				employee.setTitle(title.get());
			else
				employee.setTitle(employeeOnDB.getTitle());
		}
		if (employeeDTO.getDepartmentId() > 0) {
			Optional<Department> department = departmentRepository.findById(employeeDTO.getDepartmentId());
			if (department.isPresent())
				employee.setDepartment(department.get());
			else
				employee.setDepartment(employeeOnDB.getDepartment());
		}
		return employee;
	}

}
