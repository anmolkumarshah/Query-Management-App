package com.virtusa.querymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.virtusa.querymanagement.entity.Query;
import com.virtusa.querymanagement.entity.User;
import com.virtusa.querymanagement.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {	
	
	@Autowired
	private UserService theUserService;
	
	@RequestMapping("/queries")
	 String myQueryList(Model m) {		
		User u = theUserService.currentUser();		
		List<Query> queries = u.getQueries();
		m.addAttribute("myList", queries);
		m.addAttribute("page", "myQuery");
		return "Query/myQuery";
	}
	
	@RequestMapping("/account")
	 String account(Model m) {
		User u = theUserService.currentUser();
		m.addAttribute("current", u);
		m.addAttribute("page", "myAccount");
		return "Home/myAccount";
	}
	
	@PostMapping(value = "/update")
	 String update(@ModelAttribute("current") User user,Model m) {
		theUserService.updateUser(user);
		User u = theUserService.getUserById(user.getUserId());
		m.addAttribute("current", u);
		m.addAttribute("msg", "Updated Successfully");
		m.addAttribute("page", "myAccount");
		return "Home/myAccount";
	}
	
}
