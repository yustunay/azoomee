package com.azoomee.demo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.azoomee.demo.model.Address;
import com.azoomee.demo.model.Department;
import com.azoomee.demo.model.Employee;
import com.azoomee.demo.model.Gender;
import com.azoomee.demo.model.Office;
import com.azoomee.demo.model.Salary;
import com.azoomee.demo.model.Title;
import com.azoomee.demo.model.Winner;
import com.azoomee.demo.repository.DepartmentRepository;
import com.azoomee.demo.repository.EmployeeRepository;
import com.azoomee.demo.repository.TitleRepository;
import com.azoomee.demo.repository.WinnerRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@EnableScheduling
@SpringBootApplication
public class AzoomeeApplication implements CommandLineRunner {

	@Autowired
	private EmployeeRepository empRepository;

	@Autowired
	private DepartmentRepository deptRepository;

	@Autowired
	private TitleRepository titleRepository;
	
	@Autowired
	private WinnerRepository winnerRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AzoomeeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		    Salary salary = new Salary(2000,LocalDate.now(),null);
		    Department department = deptRepository.findByDeptNo("IT01");
		    Title title = titleRepository.findByTitleCode("SRDEV");
			Employee emp1 = new Employee(LocalDate.of(2019, 8, 24), "Yahya", "Ustunay", Gender.M, LocalDate.now(), salary, title, department);
			empRepository.save(emp1);
			
			Address adress1 = new Address.AddressBuilder().setLine1("10 Downing Street")
														  .setCity("London")
														  .setCountryId("UK")
														  .setZipOrPostcode("SW1A 2AA")
														  .build();
			department.setOffice(new Office("Central Office",adress1));
			deptRepository.save(department);
						
			System.out.println(emp1.getDepartment().getOffice().getAddress());

			Winner winner = new Winner("12020", 1, "Berna Üstünay");
			winnerRepository.save(winner);
			
			winner = new Winner("22020", 1, "Yahya Üstünay");
			winnerRepository.save(winner);
	}
	
	@Autowired(required = true)
	public void configureJackson(ObjectMapper jackson2ObjectMapper) {
	    jackson2ObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	    jackson2ObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
	    jackson2ObjectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    jackson2ObjectMapper.setDateFormat(df);
	}
	
}
