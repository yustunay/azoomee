package com.azoomee.demo.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
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

import com.azoomee.demo.model.Employee;
import com.azoomee.demo.model.Gender;
import com.google.gson.Gson;

@RunWith(JUnit4.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shouldGetAllEmployees() throws Exception {	
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        //Try exchange
        ResponseEntity<Object> response = restTemplate.exchange("http://localhost:" + port + "/getAllEmp", HttpMethod.GET, entity, Object.class);
		
		//ResponseEntity<Object> entity = this.restTemplate.getForEntity("http://localhost:" + port + "/getAllEmp",Object.class);
		List<Employee> employeeList = (List<Employee>) response.getBody();
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(employeeList.size()>0);
	}

	@Test
	public void shouldGetEmployeeById() throws Exception {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        //Try exchange
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/getEmp/1", HttpMethod.GET, entity, String.class);
        
        Employee employee = new Gson().fromJson(response.getBody(), Employee.class);
		
		//should get our demo record...
		//ResponseEntity<Employee> entity = this.restTemplate.getForEntity("http://localhost:" + port + "/getEmp/1",Employee.class);
		//Employee employee = response.getBody();
		
		//Employee [id=1, birthDate=2019-08-24, firstName=Yahya, lastName=Ustunay, gender=M, hireDate=2020-02-14, 
		//salary=Salary [id=1, salary=2000.0, fromDate=2020-02-14, endDate=null], 
		//title=Title [id=3, titleCode=SRDEV, title=Senior Developer, fromDate=2020-02-14, toDate=null], 
		//department=Department [id=1, deptNo=IT01, deptName=Information Technology,
		//office=Office [id=1, name=Central Office, 
		//address=Address [id=1, line1=10 Downing Street, line2=null, line3=null, city=London, 
		//				  zipOrPostcode=SW1A 2AA, stateProvinceCounty=null, countryId=UK, otherAddressDetails=null]]]]
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1,employee.getId());
		assertEquals(LocalDate.of(1983, 8, 24),employee.getBirthDate());
		assertEquals("Yahya",employee.getFirstName());
		assertEquals("Ustunay",employee.getLastName());
		assertEquals(Gender.M,employee.getGender());
		assertEquals(LocalDate.of(2020, 2, 14),employee.getHireDate());
		assertEquals(2000,employee.getSalary().getSalary());
		assertEquals(LocalDate.of(2020, 2, 14),employee.getSalary().getFromDate());
		assertEquals("SRDEV",employee.getTitle().getTitleCode());
		assertEquals("Senior Developer",employee.getTitle().getTitle());
		assertEquals(LocalDate.of(2020, 2, 14),employee.getTitle().getFromDate());
		assertEquals("IT01",employee.getDepartment().getDeptNo());
		assertEquals("Information Technology",employee.getDepartment().getDeptName());
		//assertEquals("Information Technology",employee.getDepartment().getOffice());
	}
	
}
