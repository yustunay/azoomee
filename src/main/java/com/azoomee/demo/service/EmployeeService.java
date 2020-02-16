package com.azoomee.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azoomee.demo.model.Department;
import com.azoomee.demo.model.Employee;
import com.azoomee.demo.model.EmployeeDTO;
import com.azoomee.demo.model.Title;
import com.azoomee.demo.repository.DepartmentRepository;
import com.azoomee.demo.repository.EmployeeRepository;
import com.azoomee.demo.repository.TitleRepository;

@Transactional
@Service
public class EmployeeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

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

	public Employee updateEmployee(long id, EmployeeDTO employeeDTO) {
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
		Employee.EmployeeBuilder builder = new Employee.EmployeeBuilder(); 		
		Employee employeeOnDB = new Employee();

		//Update operation
		if (employeeDTO.getId() > 0) {
			employeeOnDB = empRepository.findById(employeeDTO.getId()).orElse(new Employee());

			if (employeeDTO.getSalary().getId() == 0) {
				employeeDTO.getSalary().setId(employeeOnDB.getSalary().getId());
			}
		}

		builder.setBirthDate(employeeDTO.getBirthDate())
			   .setFirstName(employeeDTO.getFirstName())
			   .setLastName(employeeDTO.getLastName())
			   .setGender(employeeDTO.getGender())
			   .setHireDate(employeeDTO.getHireDate())
			   .setSalary(employeeDTO.getSalary());
						
		if (employeeDTO.getTitleId() > 0) {
			Optional<Title> title = titleRepository.findById(employeeDTO.getTitleId());
			if (title.isPresent())
				builder.setTitle(title.get());
			else
				builder.setTitle(employeeOnDB.getTitle());
		}
		if (employeeDTO.getDepartmentId() > 0) {
			Optional<Department> department = departmentRepository.findById(employeeDTO.getDepartmentId());
			if (department.isPresent())
				builder.setDepartment(department.get());
			else
				builder.setDepartment(employeeOnDB.getDepartment());
		}
		return builder.build();
	}
}
