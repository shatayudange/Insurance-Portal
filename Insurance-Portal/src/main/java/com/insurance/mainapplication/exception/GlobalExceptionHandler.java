package com.insurance.mainapplication.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
		ErrorDetaisl errorDetaisl = new ErrorDetaisl(new Date(),ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetaisl,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandler(Exception ex,WebRequest request){
		ErrorDetaisl errorDetaisl = new ErrorDetaisl(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetaisl,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
