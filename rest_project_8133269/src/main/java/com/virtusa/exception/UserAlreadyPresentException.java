package com.virtusa.exception;

public class UserAlreadyPresentException extends Exception {

	public UserAlreadyPresentException() {
		super("User Already Present With this Email");
	}
	
}
