package com.virtusa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.entity.User;

@Repository
public interface UserRrepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

}
