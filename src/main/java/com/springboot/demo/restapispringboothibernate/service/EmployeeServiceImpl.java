package com.springboot.demo.restapispringboothibernate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.demo.restapispringboothibernate.dao.EmployeeRepository;
import com.springboot.demo.restapispringboothibernate.entity.Employee;
import com.springboot.demo.restapispringboothibernate.rest.EmployeeNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	// inject dependency for EmployeeRepository
	@Autowired
	private EmployeeRepository employeeRepository;  						
	
	
	@Override
	public List<Employee> getEmployyes() {
		return employeeRepository.findAll();						
	}

	@Override
	public Employee getSingleEmployee(int id) {

		Employee employee = null;

		Optional<Employee> result = employeeRepository.findById(id);
		
		
		if(result.isPresent()) {
			employee = result.get();
		}else {
			// we dind't find the employee
			throw new EmployeeNotFoundException("employee id not found - "+id);
		}
		
		return employee;
	}

	@Override
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);				   
	}

	@Override
	public void delete(int id) {
		employeeRepository.deleteById(id);						   
	}

}
