package com.virtusa.exception;

public class UserNotFoundForEmail extends Exception {

	public UserNotFoundForEmail() {
		super("User Not Found With this Email");
	}
	
}
