package com.virtusa.service;


import com.virtusa.entity.User;
import com.virtusa.exception.UserAlreadyPresentException;
import com.virtusa.exception.UserNotFoundForEmail;

public interface UserService {
	
	User adduser(User theUser) throws UserAlreadyPresentException,UserNotFoundForEmail ;
	
	User getUserByEmail(String email) throws UserNotFoundForEmail ;
	
	User getUserById(Long id);
	
	User updateUser(User u);

}
