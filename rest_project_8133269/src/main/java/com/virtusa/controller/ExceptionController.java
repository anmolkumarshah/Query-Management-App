package com.virtusa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.virtusa.entity.Response;
import com.virtusa.exception.QueryNotFound;
import com.virtusa.exception.UserAlreadyPresentException;
import com.virtusa.exception.UserNotFoundForEmail;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(UserAlreadyPresentException.class)
	public ResponseEntity<Response> userAlreadyCreatedException(UserAlreadyPresentException exception) {
		String msg = "User Already Present With This Email";
		Response theResponse = new Response("conflict",msg);
		return new ResponseEntity<>(theResponse,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UserNotFoundForEmail.class)
	public ResponseEntity<Response> userNotFoundForEmail(UserNotFoundForEmail exception) {
		String msg = "User Not Found With This Email";
		Response theResponse = new Response("Not Found",msg);
		return new ResponseEntity<>(theResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(QueryNotFound.class)
	public ResponseEntity<Response> queryNotFound(QueryNotFound exception) {
		String msg = exception.getMessage();
		Response theResponse = new Response("Not Found",msg);
		return new ResponseEntity<>(theResponse,HttpStatus.NOT_FOUND);
	}
	
}
