package com.java.master.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.java.master.entity.ErrorMessage;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(DepartmentNotFoundException.class)
	public ResponseEntity<Object> departmentNotFoundException(DepartmentNotFoundException exception,WebRequest request) {
		ErrorMessage error = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
		return new ResponseEntity<Object>(error,HttpStatus.NOT_FOUND);
		
	}
}
