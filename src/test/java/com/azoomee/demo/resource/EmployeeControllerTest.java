package com.azoomee.demo.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.context.WebApplicationContext;

import com.azoomee.demo.model.Department;
import com.azoomee.demo.model.Employee;
import com.azoomee.demo.model.EmployeeDTO;
import com.azoomee.demo.model.Gender;
import com.azoomee.demo.model.Salary;
import com.azoomee.demo.model.Title;
import com.azoomee.demo.repository.DepartmentRepository;
import com.azoomee.demo.repository.EmployeeRepository;
import com.azoomee.demo.repository.TitleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class EmployeeControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private static ObjectMapper objectMapper;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private TitleRepository titleRepository;

	@Autowired
	private DepartmentRepository deptRepository;

	@BeforeAll
	private static void beforeClass() {
		objectMapper = new ObjectMapper();
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.registerModule(new JavaTimeModule());
	}

	@Test
	public void shouldGetAllEmployees() throws Exception {
		ResponseEntity<Object> response = restTemplate.exchange("http://localhost:" + port + "/getAllEmp",
				HttpMethod.GET, null, Object.class);
		List<Employee> employeeList = (List<Employee>) response.getBody();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(employeeList.size() > 0);
	}

	@Test
	public void shouldGetEmployeeById() throws Exception {
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/getEmp/1",
				HttpMethod.GET, null, String.class);
		Employee employee = objectMapper.readValue(response.getBody(), Employee.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, employee.getId());
		assertEquals(LocalDate.of(1983, 8, 24), employee.getBirthDate());
		assertEquals("User1Name", employee.getFirstName());
		assertEquals("User1LastName", employee.getLastName());
		assertEquals(Gender.M, employee.getGender());
		assertEquals(LocalDate.of(2020, 2, 12), employee.getHireDate());
		assertEquals(2000, employee.getSalary().getSalary(), 0);
		assertEquals(LocalDate.of(2020, 2, 14), employee.getSalary().getFromDate());
		assertEquals("SRDEV", employee.getTitle().getTitleCode());
		assertEquals("Senior Developer", employee.getTitle().getTitle());
		assertEquals(LocalDate.of(2020, 2, 14), employee.getTitle().getFromDate());
		assertEquals("IT01", employee.getDepartment().getDeptNo());
		assertEquals("Information Technology", employee.getDepartment().getDeptName());
	}

	@Test
	public void shouldGetEmpWithHireDateAndSalary() throws JsonMappingException, JsonProcessingException {
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/getEmp/2020-02-11/1500",
				HttpMethod.GET, null, String.class);
		List<Employee> employeeList = objectMapper.readValue(response.getBody(), new TypeReference<List<Employee>>() {
		});

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(employeeList.size() > 0);
	}

	@Test
	@Rollback(true)
	public void shouldCreateEmployee() throws JsonMappingException, JsonProcessingException {
		Department department = deptRepository.findByDeptNo("IT01");
		Title title = titleRepository.findByTitleCode("SRDEV");
		Salary salary = new Salary(3500, LocalDate.of(2020, 2, 13), null);
		EmployeeDTO employeeDTO = new EmployeeDTO(0L, LocalDate.of(1982, 3, 4), "TestUser2", "TestUser2", Gender.F,
				LocalDate.of(2020, 2, 14), salary, title.getId(), department.getId());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(employeeDTO, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/createEmployee",
				HttpMethod.POST, httpEntity, String.class);
		Employee employee = objectMapper.readValue(response.getBody(), Employee.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(employee.getId() > 0);
		assertEquals(LocalDate.of(1982, 3, 4), employee.getBirthDate());
		assertEquals("TestUser2", employee.getFirstName());
		assertEquals("TestUser2", employee.getLastName());
		assertEquals(Gender.F, employee.getGender());
		assertEquals(LocalDate.of(2020, 2, 14), employee.getHireDate());
		assertEquals(3500, employee.getSalary().getSalary(), 0);
		assertEquals(LocalDate.of(2020, 2, 13), employee.getSalary().getFromDate());
		assertEquals("SRDEV", employee.getTitle().getTitleCode());
		assertEquals("Senior Developer", employee.getTitle().getTitle());
		assertEquals("IT01", employee.getDepartment().getDeptNo());
		assertEquals("Information Technology", employee.getDepartment().getDeptName());
	}

	@Test
	@Rollback(true)
	public void shouldUpdateEmployee() throws JsonMappingException, JsonProcessingException {

		Employee employee = employeeRepository.findByFirstName("User1Name");
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId(employee.getId());
		employeeDTO.setBirthDate(employee.getBirthDate());
		employeeDTO.setDepartmentId(employee.getDepartment().getId());
		employeeDTO.setFirstName(employee.getFirstName());

		// UPDATE LASTNAME
		employeeDTO.setLastName("UserNewLastName"); // employee.getLastName()
		employeeDTO.setHireDate(employee.getHireDate());
		// UPDATE SALARY
		employee.getSalary().setSalary(4000);
		employeeDTO.setSalary(employee.getSalary());
		// UPDATE THE TITLE
		Title title = titleRepository.findByTitleCode("PMAN");
		employeeDTO.setTitleId(title.getId());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(employeeDTO, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				"http://localhost:" + port + "/updateEmployee/" + employee.getId(), HttpMethod.PUT, httpEntity,
				String.class);
		Employee updatedEmployee = objectMapper.readValue(response.getBody(), Employee.class);

		assertEquals("UserNewLastName", updatedEmployee.getLastName());
		assertEquals("UserNewLastName", updatedEmployee.getLastName());
		assertEquals(4000, updatedEmployee.getSalary().getSalary(), 0);
		assertEquals("PMAN", updatedEmployee.getTitle().getTitleCode());
		assertEquals("Project Manager", updatedEmployee.getTitle().getTitle());
	}

	@Test
	@Rollback(true)
	public void shouldDeleteEmployee() {
		Employee employee = employeeRepository.findByFirstName("User1Name");
		ResponseEntity<Void> response = restTemplate.exchange(
				"http://localhost:" + port + "/deleteEmployee/" + employee.getId(), HttpMethod.DELETE, null,
				Void.class);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

}
