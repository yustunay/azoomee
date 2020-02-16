insert into titles(title_id, title_code, title, from_date, to_date)
values(1, 'JUNDEV', 'Junior Developer', sysdate(), null);

insert into titles(title_id, title_code, title, from_date, to_date)
values(2, 'MSRDEV', 'Mid-Senior Developer', sysdate(), null);

insert into titles(title_id, title_code, title, from_date, to_date)
values(3, 'SRDEV', 'Senior Developer', sysdate(), null);

insert into titles(title_id, title_code, title, from_date, to_date)
values(4,'SOLARC', 'Solution Architect', sysdate(), null);

insert into titles(title_id, title_code, title, from_date, to_date)
values(5,'SSARC', 'Senior Solution Architect', sysdate(), null);

insert into titles(title_id, title_code, title, from_date, to_date)
values(6, 'PMAN', 'Project Manager', sysdate(), null);

insert into titles(title_id, title_code, title, from_date, to_date)
values(7,'SPMAN', 'Senior Project Manager', sysdate(), null);

insert into titles(title_id, title_code, title, from_date, to_date)
values(8,'TLEAD','Team Lead', sysdate(), null);

insert into titles(title_id, title_code, title, from_date, to_date)
values(9,'ANL','Analyst', sysdate(), null);

insert into titles(title_id, title_code, title, from_date, to_date)
values(10,'SANL','Senior Analyst', sysdate(), null);

insert into titles(title_id, title_code, title, from_date, to_date)
values(11,'DEVOPS','DevOps Engineer', sysdate(), null);

insert into titles(title_id, title_code, title, from_date, to_date)
values(12,'SDEVOPS','Senior DevOps Engineer', sysdate(), null);

insert into titles(title_id, title_code, title, from_date, to_date)
values(13,'MANL','Marketing analyst', sysdate(), null);

insert into titles(title_id, title_code, title, from_date, to_date)
values(14,'MCOR','Marketing coordinator', sysdate(), null);

insert into titles(title_id, title_code, title, from_date, to_date)
values(15,'MCON','Marketing consultant', sysdate(), null);

insert into titles(title_id, title_code, title, from_date, to_date)
values(16,'MMAN','Marketing manager', sysdate(), null);

insert into titles(title_id, title_code, title, from_date, to_date)
values(17,'MSPE','Marketing specialist', sysdate(), null);

insert into addresses(id, city, country_id, line1, line2, line3, other_address_details, state_province_county, zip_or_postcode)
values(1000, 'London', 'UK', '10 Downing Street', null, null, null, null, 'SW1A 2AA');

insert into offices(id, name, address_id)
values(1001, 'Central Office', 1000);

insert into departments(id, dept_no, dept_name, office_id)
values(1,'IT01', 'Information Technology', 1001);

insert into departments(id, dept_no, dept_name)
values(2,'HR01', 'Human Resources');

insert into departments(id, dept_no, dept_name)
values(3,'AC01', 'Accounting');

insert into departments(id, dept_no, dept_name)
values(4,'M01', 'Marketing');

insert into SALARIES(id,from_date,salary)
values(1,'2020-02-12',2000);

insert into employees(id, birth_date, first_name, last_name, gender, hire_date, salary_id, department_id, title_id)
values(1002,'1983-08-24','Diana','Roberts', 0, '2020-01-12', 1, (SELECT id FROM DEPARTMENTS  WHERE dept_no = 'IT01'),
(SELECT title_id FROM TITLES WHERE title_code = 'SRDEV'));

insert into winners(emp_no, full_name, month_year)	
values(1002, 'Diana Roberts', '12020');