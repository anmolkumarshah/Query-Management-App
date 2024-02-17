package com.virtusa.querymanagement.User;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.virtusa.querymanagement.entity.User;
import com.virtusa.querymanagement.service.UserService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class UserTest {
	
	@Autowired
	private UserService theUserService;
	
	static Long generatedUserId = null;

	@Test
	@Order(1)
	void register() {
		User createdUser = new User();
		createdUser.setEmail("test@test.com");
		createdUser.setJoinedOn(new Timestamp(new Date().getTime()));
		createdUser.setName("test");
		createdUser.setRole("USER");
		createdUser.setComments(List.of());
		createdUser.setQueries(List.of());
		createdUser.setPoints(0);
		createdUser.setPassword("test");
		
		User savedUser = theUserService.register(createdUser);
		generatedUserId = savedUser.getUserId();
		assertTrue(savedUser.getUserId() > 0, "New user Created with user id "+savedUser.getUserId());
	}
	
	
	@Test
	@Order(2)
	void updateUser() {
		User old = theUserService.getUserById(generatedUserId);
		old.setName("Test Updated");
		int changes = theUserService.updateUser(old);
		assertNotEquals(0, changes);
	}
	
	@Test
	@Order(3)
	void getuserById() {
		User u = theUserService.getUserById(generatedUserId);
		assertEquals(u.getUserId(), generatedUserId);
	}
	

}
