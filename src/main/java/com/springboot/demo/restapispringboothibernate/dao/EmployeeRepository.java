package com.springboot.demo.restapispringboothibernate.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.demo.restapispringboothibernate.entity.Employee;

// by extending JpaRepository we have access to its CRUD method for given Entity without writing actual implementation

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {				// <Entity type, type of PK>
	/*
	 * we have access to the following CRUD methods for "Employee" entity automatically
	 * findAll(), findById(...), save(...), deleteById(...) & others
	 */
}
