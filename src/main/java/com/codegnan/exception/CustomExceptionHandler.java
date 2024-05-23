package com.codegnan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(InvalidPatientIdException.class)
	public ResponseEntity<ErrorResponse> meth1(InvalidPatientIdException e1) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String message = e1.getMessage();
		ErrorResponse errorResponse = new ErrorResponse(status, message);
		ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<ErrorResponse>(errorResponse, status);
		return responseEntity;
	}
	
	public ResponseEntity<ErrorResponse> meth2(InvalidDoctorIdException e1){
		HttpStatus status = HttpStatus.NOT_FOUND;
		String message = e1.getMessage();
		ErrorResponse errorResponse = new ErrorResponse(status, message);
		ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, status);
		return responseEntity;
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> meth2(Exception e1) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String message = e1.getMessage();
		ErrorResponse errorResponse = new ErrorResponse(status, message);
		ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<ErrorResponse>(errorResponse, status);
		return responseEntity;
	}
}
