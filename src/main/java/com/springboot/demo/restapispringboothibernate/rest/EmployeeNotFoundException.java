package com.springboot.demo.restapispringboothibernate.rest;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException {
	public EmployeeNotFoundException(String message) {
		super(message);
	}
}
