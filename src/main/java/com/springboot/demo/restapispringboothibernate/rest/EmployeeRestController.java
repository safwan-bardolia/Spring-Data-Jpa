package com.springboot.demo.restapispringboothibernate.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.restapispringboothibernate.entity.Employee;
import com.springboot.demo.restapispringboothibernate.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	// inject dependency for service layer
	@Autowired
	private EmployeeService employeeService; 
	
	// add mapping for GET "/employees"  	

	@GetMapping("/employees")
	public List<Employee> getEmployyes() {
		
		// return response
		return employeeService.getEmployyes();											// delegate call to service layer
	}
	

	// add mapping for GET "/employees/{employeeId}"

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {							// bind path variable with method parameter
		
		// fetch employee from DB
		Employee employee = employeeService.getSingleEmployee(employeeId);				// delegate call to service layer
		
		
		// return response
		return employee;
	}
	

	// add mapping for POST "/employees"
	
	// in case of POST PK is null/empty
	@PostMapping("/employees") 															// JACKSON will automatically convert request body from JSON to POJO	
	public Employee addEmployee(@RequestBody Employee employee) {						// @RequestBody annotation binds that POJO(request body) to a method parameter
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		employee.setId(0);
		
		employeeService.saveEmployee(employee);											// delegate call to service layer
		
		// return response
		return employee;
	}
	
	
	// add mapping for PUT "/employees"
	
	// in case of PUT PK is present 
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		
		//first fetch the record from DB (if data not found then it throws an exception)
	    employeeService.getSingleEmployee(employee.getId());							// without this, if we directly save the record & if record is not found in DB then 
																						// it will add new record in this case also (i.e for PUT)	
		//then save it to the DB
		employeeService.saveEmployee(employee);											// delegate call to service layer
		
		// return response
		return employee;
	}
	
	
	// add mapping for DELETE "/employees/{employeeId}"
	
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		
		// first check employee exist?
		Employee employee = employeeService.getSingleEmployee(employeeId);				// delegate call to service layer
		
		// throw exception if not exist
		if(employee==null) {
			throw new EmployeeNotFoundException("employee id not found - "+employeeId);
		}
		
		employeeService.delete(employeeId);												// delegate call to service layer
		
		// return response
		return "deleted employee with id - "+employeeId;
	}
	
}
