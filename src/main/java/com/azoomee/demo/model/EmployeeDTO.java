package com.azoomee.demo.model;

import java.io.Serializable;
import java.time.LocalDate;

public class EmployeeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;	
	private LocalDate birthDate;
	private String firstName;
	private String lastName;
	private Gender gender;
	private LocalDate hireDate;
	private Salary salary;
	private long titleId;
	private long departmentId;

	public EmployeeDTO() {}

	public EmployeeDTO(long id, LocalDate birthDate, String firstName, String lastName, Gender gender,
			LocalDate hireDate, Salary salary, long titleId, long departmentId) {
		this.id = id;
		this.birthDate = birthDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.hireDate = hireDate;
		this.salary = salary;
		this.titleId = titleId;
		this.departmentId = departmentId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public long getTitleId() {
		return titleId;
	}

	public void setTitleId(long titleId) {
		this.titleId = titleId;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [id=" + id + ", birthDate=" + birthDate + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", hireDate=" + hireDate + ", salary=" + salary + ", titleId="
				+ titleId + ", departmentId=" + departmentId + "]";
	}

}
