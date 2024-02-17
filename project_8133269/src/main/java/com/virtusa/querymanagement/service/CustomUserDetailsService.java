package com.virtusa.querymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.virtusa.querymanagement.entity.User;
import com.virtusa.querymanagement.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository theUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = theUserRepository.findByEmail(username);
		if(user == null) throw new UsernameNotFoundException("User Not Found");
		return new CustomUserDetails(user);
	}

}
