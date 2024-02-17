package com.virtusa.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.entity.User;
import com.virtusa.exception.UserAlreadyPresentException;
import com.virtusa.exception.UserNotFoundForEmail;
import com.virtusa.repository.UserRrepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRrepository theUserRrepository;

	@Override
	public User adduser(User theUser) throws UserAlreadyPresentException, UserNotFoundForEmail {

		User userByEmail = getUserByEmail(theUser.getEmail());
		if (userByEmail != null)
			throw new UserAlreadyPresentException();

		return theUserRrepository.save(theUser) ;
	}

	@Override
	public User getUserByEmail(String email) throws UserNotFoundForEmail {
		User findByEmail = theUserRrepository.findByEmail(email);
		if(findByEmail == null) throw new UserNotFoundForEmail();
		return findByEmail;
	}

	@Override
	public User getUserById(Long id) throws NoSuchElementException {
		Optional<User> optionalUser = theUserRrepository.findById(id);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public User updateUser(User u) {
		return theUserRrepository.save(u);
	}
	
	

}
