package com.virtusa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.entity.*;
import com.virtusa.service.CustomUserDetailsService;
import com.virtusa.util.JWTUtility;

@RestController
public class HomeController {

	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userService;

	@GetMapping("/")
	public String home() {
		return "welcome to protected";
	}

	@PostMapping("/authenticate")
	public ResponseEntity<Object> authenticate(@RequestBody JwtRequest jwtRequest) throws BadCredentialsException {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

			final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
			final String token = jwtUtility.generateToken(userDetails);
			return new ResponseEntity<>(new JwtResponse(token), HttpStatus.ACCEPTED);

		} catch (BadCredentialsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}
