package com.virtusa.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.DTO.UserDTO;
import com.virtusa.entity.Response;
import com.virtusa.entity.User;
import com.virtusa.exception.UserAlreadyPresentException;
import com.virtusa.exception.UserNotFoundForEmail;
import com.virtusa.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService theUserService;

	@PostMapping("/save")
	ResponseEntity<Response> save(@Valid @RequestBody UserDTO userDTO) 
			throws UserAlreadyPresentException, UserNotFoundForEmail {

		User newUser = new User();
		newUser.setEmail(userDTO.getEmail());
		newUser.setName(userDTO.getName());
		newUser.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
		newUser.setComments(new ArrayList<>());
		newUser.setJoinedOn(new Timestamp(new Date().getTime()));
		newUser.setQueries(new ArrayList<>());
		newUser.setRole("USER");
		newUser.setPoints(0);

		User adduser = theUserService.adduser(newUser);
		Response theResponse = null;
		if (adduser != null) {
			theResponse = new Response();
			theResponse.setName("Success");
			theResponse.setMessage("User Created Successfully");
		}
		return new ResponseEntity<>(theResponse, HttpStatus.CREATED);

	}

	@PutMapping("/update")
	ResponseEntity<Response> update(@Valid @RequestBody UserDTO userDTO) throws UserNotFoundForEmail {
		User oldUser = theUserService.getUserByEmail(userDTO.getEmail());
		oldUser.setEmail(userDTO.getEmail());
		oldUser.setName(userDTO.getName());
		oldUser.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
		User updateUser = theUserService.updateUser(oldUser);

		Response theResponse = null;
		if (updateUser != null) {
			theResponse = new Response();
			theResponse.setName("Success");
			theResponse.setMessage("User Updated Successfully");
		}

		return new ResponseEntity<>(theResponse, HttpStatus.ACCEPTED);
	}

}
