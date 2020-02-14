# Azoomee Demo Web Application using Spring Boot and H2 In memory database

Run com.azoomee.demo.AzoomeeApplication as a Java Application.

Runs on port of Spring Boot - 5000 

#### H2 console also can be used to check the database

- http://localhost:5000/h2-console/

## To Get All Employees From API (GET METHOD)

- http://localhost:5000/getAllEmp

### Response
```json
[
   {
      "id":1,
      "birthDate":"1983-08-24",
      "firstName":"User1Name",
      "lastName":"User1LastName",
      "gender":"M",
      "hireDate":"2020-02-12",
      "salary":{
         "id":1,
         "salary":2000.0,
         "fromDate":"2020-02-14"
      },
      "title":{
         "id":3,
         "titleCode":"SRDEV",
         "title":"Senior Developer",
         "fromDate":"2020-02-14"
      },
      "department":{
         "id":1,
         "deptNo":"IT01",
         "deptName":"Information Technology",
         "office":{
            "id":1,
            "name":"Central Office",
            "address":{
               "id":1,
               "line1":"10 Downing Street",
               "city":"London",
               "zipOrPostcode":"SW1A 2AA",
               "countryId":"UK"
            }
         }
      }
   }
]
```

## To Get A Specific Employee From DB (GET METHOD)

- http://localhost:5000/getEmp/{id}
- http://localhost:5000/getEmp/1

```json
{
   "id":1,
   "birthDate":"1983-08-24",
   "firstName":"User1Name",
   "lastName":"User1LastName",
   "gender":"M",
   "hireDate":"2020-02-12",
   "salary":{
      "id":1,
      "salary":2000.0,
      "fromDate":"2020-02-14"
   },
   "title":{
      "id":3,
      "titleCode":"SRDEV",
      "title":"Senior Developer",
      "fromDate":"2020-02-14"
   },
   "department":{
      "id":1,
      "deptNo":"IT01",
      "deptName":"Information Technology",
      "office":{
         "id":1,
         "name":"Central Office",
         "address":{
            "id":1,
            "line1":"10 Downing Street",
            "city":"London",
            "zipOrPostcode":"SW1A 2AA",
            "countryId":"UK"
         }
      }
   }
}
```

## To Create An Employee (POST METHOD)

- http://localhost:5000/createEmployee

#### Request Body 
```json
{
      "birthDate":"2019-08-24",
      "firstName":"Berna",
      "lastName":"Ustunay",
      "gender":"M",
      "hireDate":"2020-02-13",
      "salary":{
         "salary":5000,
         "fromDate":"2020-02-13"
      },
      "titleId":3,
      "departmentId":1
}
```

#### Response Body

```json
{
   "id":2,
   "birthDate":"2019-08-24",
   "firstName":"Berna",
   "lastName":"Ustunay",
   "gender":"M",
   "hireDate":"2020-02-13",
   "salary":{
      "id":2,
      "salary":5000.0,
      "fromDate":"2020-02-13"
   },
   "title":{
      "id":3,
      "titleCode":"SRDEV",
      "title":"Senior Developer",
      "fromDate":"2020-02-14"
   },
   "department":{
      "id":1,
      "deptNo":"IT01",
      "deptName":"Information Technology",
      "office":{
         "id":1,
         "name":"Central Office",
         "address":{
            "id":1,
            "line1":"10 Downing Street",
            "city":"London",
            "zipOrPostcode":"SW1A 2AA",
            "countryId":"UK"
         }
      }
   }
}
```

## To Update An Employee Data (PUT METHOD)

- http://localhost:5000/updateEmployee/{id}

#### Request Body

```json
{
      "id": 2,
      "birthDate":"2019-08-24",
      "firstName":"Berna",
      "lastName":"ÜSTÜNAY",
      "gender":"F",
      "hireDate":"2020-02-13",
      "salary":{
         "salary":5750,
         "fromDate":"2020-02-13"
      },
      "titleId":20,
      "departmentId":2
}
```

#### Response Body

```json
{
   "id":3,
   "birthDate":"2019-08-24",
   "firstName":"Berna",
   "lastName":"ÜSTÜNAY",
   "gender":"F",
   "hireDate":"2020-02-13",
   "salary":{
      "id":2,
      "salary":5750.0,
      "fromDate":"2020-02-13"
   },
   "title":{
      "id":3,
      "titleCode":"SRDEV",
      "title":"Senior Developer",
      "fromDate":"2020-02-14"
   },
   "department":{
      "id":2,
      "deptNo":"HR01",
      "deptName":"Human Resources"
   }
}
```

## To Delete An Employee (DELETE METHOD)

- http://localhost:5000/deleteEmployee/{id}


## To Get An Employee According To The Hire Date And Salary (GET METHOD)

- http://localhost:5000//getEmp/{hireDate}/{salary}

#### Response Body

```json
[
   {
      "id":1,
      "birthDate":"1983-08-24",
      "firstName":"User1Name",
      "lastName":"User1LastName",
      "gender":"M",
      "hireDate":"2020-02-12",
      "salary":{
         "id":1,
         "salary":2000,
         "fromDate":"2020-02-14"
      },
      "title":{
         "id":3,
         "titleCode":"SRDEV",
         "title":"Senior Developer",
         "fromDate":"2020-02-14"
      },
      "department":{
         "id":1,
         "deptNo":"IT01",
         "deptName":"Information Technology",
         "office":{
            "id":1,
            "name":"Central Office",
            "address":{
               "id":1,
               "line1":"10 Downing Street",
               "city":"London",
               "zipOrPostcode":"SW1A 2AA",
               "countryId":"UK"
            }
         }
      }
   },
   {
      "id":2,
      "birthDate":"2019-08-24",
      "firstName":"Berna",
      "lastName":"Ustunay",
      "gender":"M",
      "hireDate":"2020-02-13",
      "salary":{
         "id":2,
         "salary":5750,
         "fromDate":"2020-02-13"
      },
      "title":{
         "id":3,
         "titleCode":"SRDEV",
         "title":"Senior Developer",
         "fromDate":"2020-02-14"
      },
      "department":{
         "id":1,
         "deptNo":"IT01",
         "deptName":"Information Technology",
         "office":{
            "id":1,
            "name":"Central Office",
            "address":{
               "id":1,
               "line1":"10 Downing Street",
               "city":"London",
               "zipOrPostcode":"SW1A 2AA",
               "countryId":"UK"
            }
         }
      }
   },
   {
      "id":3,
      "birthDate":"2019-08-24",
      "firstName":"Berna",
      "lastName":"ÜSTÜNAY",
      "gender":"F",
      "hireDate":"2020-02-13",
      "salary":{
         "id":2,
         "salary":5750,
         "fromDate":"2020-02-13"
      },
      "title":{
         "id":3,
         "titleCode":"SRDEV",
         "title":"Senior Developer",
         "fromDate":"2020-02-14"
      },
      "department":{
         "id":2,
         "deptNo":"HR01",
         "deptName":"Human Resources"
      }
   }
]
```

## At every last day of the months a job will run and save the winner employee to the database


```java
	@Scheduled(cron = "0 0 10 28-31 * ?") // Fire at 10:00 AM on the last day of every month
	public void runForWinnerOfTheMonth() {
		final Calendar c = Calendar.getInstance();
		if (c.get(Calendar.DATE) == c.getActualMaximum(Calendar.DATE)) {
			Employee winnerEmp = (Employee) entityManager
					.createNativeQuery("SELECT * FROM EMPLOYEES ORDER BY RAND()", Employee.class)
					.setMaxResults(1)
					.getSingleResult();

			Winner winner = new Winner(LocalDate.now().getMonth().getValue() + "" + LocalDate.now().getYear(),
					winnerEmp.getId(), winnerEmp.getFirstName().concat(" ").concat(winnerEmp.getLastName()));
			// System.out.println(winner.getFirstName());
			winnerRepository.save(winner);
		}

	}
```

## To Get Winner Employee From API (GET METHOD)

- http://localhost:5000/getWinner/{monthYear}
- http://localhost:5000/getAllWinners
