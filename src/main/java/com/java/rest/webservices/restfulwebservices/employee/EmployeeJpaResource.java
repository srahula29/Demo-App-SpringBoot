package com.java.rest.webservices.restfulwebservices.employee;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmployeeJpaResource {

	
	@Autowired
	private EmployeeJpaRepository employeeJpaRepository;
	
	

	@GetMapping("/jpa/users/{username}/employees")
	public List<Employee> getAllEmployees(@PathVariable String username) {

		return employeeJpaRepository.findAll();

	}

	@GetMapping("/jpa/users/{username}/employees/{employeeId}")
	public Employee getEmployee(@PathVariable String username, @PathVariable long employeeId) {

		return employeeJpaRepository.findById(employeeId).get();

	}

	@DeleteMapping("/jpa/users/{username}/employees/{employeeId}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable String username, @PathVariable long employeeId) {
		
		      employeeJpaRepository.deleteById(employeeId);

			  return ResponseEntity.noContent().build();

	}

	@PutMapping("/jpa/users/{username}/employees/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable String username, @PathVariable long employeeId,
			@RequestBody Employee employee) {

		Employee employeeUpdate = employeeJpaRepository.save(employee);

		return new ResponseEntity<Employee>(employee, HttpStatus.OK);

	}

	@PostMapping("/jpa/users/{username}/employees")
	public ResponseEntity<Void> createEmployee(@PathVariable String username,
			@RequestBody Employee employee) {

		Employee createdEmployee = employeeJpaRepository.save(employee);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{employeeId}")
				.buildAndExpand(createdEmployee.getEmployeeId()).toUri();

		return ResponseEntity.created(uri).build();

	}

}
