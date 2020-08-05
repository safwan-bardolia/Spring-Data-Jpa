package com.springboot.demo.restapispringboothibernate.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// global exception handler

@ControllerAdvice
public class EmployeeRestExceptionHandler {

	// add an exception handler for EmployeeNotFoundException
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponce> handleEmployeeNotFoundException(EmployeeNotFoundException exception) {
		
		// create custom error response
		EmployeeErrorResponce error = new EmployeeErrorResponce();
		error.setMessage(exception.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setTimeStamp(System.currentTimeMillis());
		
		// return ResponseEntity
		return new ResponseEntity<EmployeeErrorResponce>(error, HttpStatus.NOT_FOUND);
	}
	
	// add an exception handler for any other exception	
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponce> handleAnyOtherException(Exception exception) {
		
		// create custom error response
		EmployeeErrorResponce error = new EmployeeErrorResponce();
		error.setMessage(exception.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTimeStamp(System.currentTimeMillis());
		
		// return ResponseEntity
		return new ResponseEntity<EmployeeErrorResponce>(error, HttpStatus.BAD_REQUEST);
	}

}
