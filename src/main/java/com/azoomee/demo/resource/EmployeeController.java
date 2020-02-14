package com.azoomee.demo.resource;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.azoomee.demo.model.Employee;
import com.azoomee.demo.model.EmployeeDTO;
import com.azoomee.demo.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@GetMapping(path = "/getAllEmp")
	public @ResponseBody List<Employee> getAllEmployees() {
		return empService.getAllEmployees();
	}

	@GetMapping(path = "/getEmp/{id}")
	public Employee getEmployeeById(@PathVariable long id) {
		return empService.getEmployeeById(id);
	}

	@GetMapping(path = "/getEmp/{hireDate}/{salary}")
	public List<Employee> getEmpWithHireDateAndSalary(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hireDate, @PathVariable Double salary) {
		return empService.getEmpWithHireDateAndSalary(hireDate,salary);
	}

	@PostMapping(path = "/createEmployee")
	public Employee createEmployee(@RequestBody EmployeeDTO employeeDTO) {
		return empService.createEmployee(employeeDTO);
	}

	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody EmployeeDTO employeeDTO) {
		Employee updatedEmployee = empService.updateEmployee(id, employeeDTO);
		return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
	}

	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable long id) {
		empService.deleteEmployeeById(id);
		return ResponseEntity.noContent().build();
	}
}
