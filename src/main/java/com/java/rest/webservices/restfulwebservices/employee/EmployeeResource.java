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
public class EmployeeResource {

	@Autowired
	private EmployeeHardcodedService employeeService;

	@GetMapping("/users/{username}/employees")
	public List<Employee> getAllEmployees(@PathVariable String username) {

		return employeeService.findAll();

	}

	@GetMapping("/users/{username}/employees/{employeeId}")
	public Employee getEmployee(@PathVariable String username, @PathVariable long employeeId) {

		return employeeService.findById(employeeId);

	}

	@DeleteMapping("/users/{username}/employees/{employeeId}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable String username, @PathVariable long employeeId) {

		Employee employee = employeeService.deleteById(employeeId);

		if (employee != null) {

			return ResponseEntity.noContent().build();

		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/users/{username}/employees/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable String username, @PathVariable long employeeId,
			@RequestBody Employee employee) {

		Employee employeeUpdate = employeeService.save(employee);

		return new ResponseEntity<Employee>(employee, HttpStatus.OK);

	}

	@PostMapping("/users/{username}/employees")
	public ResponseEntity<Void> createEmployee(@PathVariable String username,
			@RequestBody Employee employee) {

		Employee createdEmployee = employeeService.save(employee);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{employeeId}")
				.buildAndExpand(createdEmployee.getEmployeeId()).toUri();

		return ResponseEntity.created(uri).build();

	}

}
