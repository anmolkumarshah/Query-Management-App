package com.virtusa.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.virtusa.entity.User;
import com.virtusa.exception.UserNotFoundForEmail;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserService theUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User userByEmail;
		try {
			userByEmail = theUserService.getUserByEmail(username);
		} catch (UserNotFoundForEmail e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("No User Found With this Username");
		}
		return new CustomUserDetails(userByEmail);
	}	
	
}


