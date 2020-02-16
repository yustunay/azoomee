package com.azoomee.demo.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDate birthDate;
	private String firstName;
	private String lastName;
	private Gender gender;
	private LocalDate hireDate;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "salary_id")
	private Salary salary;

	@NotNull
	@OneToOne(cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinColumn(name = "title_id")
	private Title title;

	@NotNull
	@ManyToOne(cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	private Department department;
	
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee() {}
	
	private Employee(EmployeeBuilder builder) {
		this.birthDate = builder.birthDate;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.gender = builder.gender;
		this.hireDate = builder.hireDate;
		this.salary = builder.salary;
		this.title = builder.title;
		this.department = builder.department;
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

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public Salary getSalary() {
		return salary;
	}

	public Title getTitle() {
		return title;
	}

	public Department getDepartment() {
		return department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", birthDate=" + birthDate + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", hireDate=" + hireDate + ", " + ", title=" + title
				+ ", ]";
	}
	
	public static class EmployeeBuilder {		
		private long id;
		private LocalDate birthDate;
		private String firstName;
		private String lastName;
		private Gender gender;
		private LocalDate hireDate;
		private Salary salary;
		private Title title;
		private Department department;
		
		public EmployeeBuilder setBirthDate(LocalDate birthDate) {
			this.birthDate = birthDate;
			return this;
		}
		
		public EmployeeBuilder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public EmployeeBuilder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}		
		
		public EmployeeBuilder setGender(Gender gender) {
			this.gender = gender;
			return this;
		}
		
		public EmployeeBuilder setHireDate(LocalDate hireDate) {
			this.hireDate = hireDate;
			return this;
		}
				
		public EmployeeBuilder setSalary(Salary salary) {
			this.salary = salary;
			return this;
		}
		
		public EmployeeBuilder setTitle(Title title) {
			this.title = title;
			return this;
		}
		
		public EmployeeBuilder setDepartment(Department department) {
			this.department = department;
			return this;
		}
		
		public Employee build() {
			return new Employee(this);
		}
	}

}
