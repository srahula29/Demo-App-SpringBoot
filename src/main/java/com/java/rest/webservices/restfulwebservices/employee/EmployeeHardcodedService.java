package com.java.rest.webservices.restfulwebservices.employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class EmployeeHardcodedService {

	private static List<Employee> employees = new ArrayList<>();

	private static int idCounter = 0;

	static {

		employees.add(new Employee(++idCounter, "Rahul Singh", new Date(), "JAVA", "Software Engineer"));
		employees.add(new Employee(++idCounter, "Shio Singh", new Date(), "AWS", "Manager"));
		employees.add(new Employee(++idCounter, "Anuradha", new Date(), "TESTING", "Tester"));
		employees.add(new Employee(++idCounter, "Peter1", new Date(), "DOTNET", "Software Developer"));
		employees.add(new Employee(++idCounter, "John", new Date(), "DATABASE", "DB Engineer"));

	}

	public List<Employee> findAll() {

		return employees;

	}
	
	public Employee save(Employee employee) {
		
		if (employee.getEmployeeId()==-1 || employee.getEmployeeId()==0) {
			
			employee.setEmployeeId(++idCounter);
			employees.add(employee);
			
		} else {
			
			deleteById(employee.getEmployeeId());
			employees.add(employee);
		}
		return employee;
	}
	
	
	
	public Employee deleteById(long employeeId) {
		
		Employee employee= findById(employeeId);
		
		if(employee==null) return null;
		
		if (employees.remove(employee)) {
			
			return employee;
		}
		
		return null;
		
	}

	public Employee findById(long employeeId) {

		for(Employee employee: employees) {
			
			if(employee.getEmployeeId() == employeeId) {
				
				return employee;
			}
		}
		
		return null;
	}
	

}
