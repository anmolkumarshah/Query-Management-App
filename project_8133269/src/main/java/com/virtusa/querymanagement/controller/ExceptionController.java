package com.virtusa.querymanagement.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.virtusa.querymanagement.entity.User;
import com.virtusa.querymanagement.exception.UserAlreadyPresentException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(UserAlreadyPresentException.class)
	public String userAlreadyCreatedException(UserAlreadyPresentException exception, Model m) {
		String msg = "User Already Created With This Email";
		m.addAttribute("msg", msg);
		m.addAttribute("signup_user", new User());
		return "signup";
	}
	
}
