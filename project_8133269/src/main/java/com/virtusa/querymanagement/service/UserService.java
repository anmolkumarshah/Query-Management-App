package com.virtusa.querymanagement.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.virtusa.querymanagement.entity.User;
import com.virtusa.querymanagement.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository theUserRepository;

	public User register(User user) {
		user.setPoints(0);
		user.setRole("USER");
		user.setJoinedOn(new Timestamp(new Date().getTime()));
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

		return theUserRepository.save(user);
	}

	public User currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserEmail = authentication.getName();
		return theUserRepository.findByEmail(currentUserEmail);
	}

	public int updateUser(User u) {
		return theUserRepository.updateNameAndEmail(u.getName(), u.getEmail(), u.getUserId());
	}

	public User getUserById(Long id) throws NoSuchElementException {
		Optional<User> findById = theUserRepository.findById(id);
		if (findById.isPresent()) {
			return findById.get();
		} else {
			throw new NoSuchElementException();
		}
	}

	public Boolean isUserAlreadyPresent(User u) {
		User foundUser = theUserRepository.findByEmail(u.getEmail());
		return foundUser != null;
	}

	public List<User> allUser() {
		return theUserRepository.findAll();
	}
}
