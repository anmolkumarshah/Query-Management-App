package com.virtusa.querymanagement.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.virtusa.querymanagement.entity.Attachment;
import com.virtusa.querymanagement.entity.Comment;
import com.virtusa.querymanagement.entity.Query;
import com.virtusa.querymanagement.entity.SearchDataTransfer;
import com.virtusa.querymanagement.entity.Tag;
import com.virtusa.querymanagement.entity.User;
import com.virtusa.querymanagement.exception.UserAlreadyPresentException;
import com.virtusa.querymanagement.repository.AttachmentRepository;
import com.virtusa.querymanagement.repository.TagRepository;
import com.virtusa.querymanagement.repository.UserRepository;
import com.virtusa.querymanagement.service.QueryService;
import com.virtusa.querymanagement.service.UserService;

@Controller
public class HomeController {

	@Autowired
	QueryService theQueryService;

	@Autowired
	UserRepository theUserRepository;

	@Autowired
	AttachmentRepository theAttachmentRepository;

	@Autowired
	TagRepository theTagRepository;

	@Autowired
	UserService theUserService;

	static Logger logger = LogManager.getLogger(HomeController.class);
	private static final String HOMEURL = "Home/home";
	private static final String SEARCH_LITERAL = "search";
	private static final String SEARCH_URL = "Home/search";

	@RequestMapping("/")
	String home(Model m, HttpServletRequest req) {

		m.addAttribute("page", "home");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserEmail = authentication.getName();

		HttpSession session = req.getSession();
		if (!currentUserEmail.equals("anonymousUser")) {

			User findByEmailuser = theUserRepository.findByEmail(currentUserEmail);
			if(findByEmailuser ==  null) {
				return "redirect:/logout";
			}
			session.setAttribute("isLoggedIn", true);
			session.setAttribute("currentUserName", findByEmailuser.getName());
			session.setAttribute("currentUserRating", findByEmailuser.getPoints());
			session.setAttribute("currentUserId", findByEmailuser.getUserId());
			logger.info(currentUserEmail);
		} else {
			session.setAttribute("isLogedIn", false);
		}

		return HOMEURL;
	}

	@RequestMapping("/login")
	String login() {
		return "index";
	}

	@RequestMapping("/signup")
	String signup(Model m) {
		m.addAttribute("signup_user", new User());
		return "signup";
	}

	@RequestMapping("/registerUser")
	String registerUser(@Valid @ModelAttribute("signup_user") User user, BindingResult res, Model m)
			throws UserAlreadyPresentException {
		if (res.hasErrors()) {
			return "signup";
		}

		boolean result = theUserService.isUserAlreadyPresent(user).equals(true);

		if (result) {
			throw new UserAlreadyPresentException();
		}

		theUserService.register(user);

		String msg = "Now Please Login";
		m.addAttribute("msg", msg);
		return "index";
	}

	@RequestMapping("/search")
	String search(Model m) {
		List<Query> list = theQueryService.allQueryList();
		List<Tag> tags = theTagRepository.findAll();

		m.addAttribute("list", list);
		m.addAttribute("tags", tags);

		m.addAttribute(SEARCH_LITERAL, new SearchDataTransfer());
		m.addAttribute("page", SEARCH_LITERAL);
		return SEARCH_URL;
	}

	@RequestMapping("/tagSearch")
	String tagSearch(@ModelAttribute("search") SearchDataTransfer s, Model m) {

		Tag t = theTagRepository.findById(Long.parseLong(s.getSelectedTag()));
		if (t == null) {
			return "redirect:/search";
		}
		List<Query> list = t.getQueryList();

		List<Tag> tags = theTagRepository.findAll();

		m.addAttribute("list", list);
		m.addAttribute("tags", tags);

		m.addAttribute(SEARCH_LITERAL, new SearchDataTransfer());
		m.addAttribute("page", SEARCH_LITERAL);
		return SEARCH_URL;
	}

	@RequestMapping("/textSearch")
	String textSearch(@RequestParam("text") String text, Model m) {
		List<Query> list = theQueryService.search(text);
		List<Tag> tags = theTagRepository.findAll();

		m.addAttribute("list", list);
		m.addAttribute("tags", tags);

		m.addAttribute(SEARCH_LITERAL, new SearchDataTransfer());
		m.addAttribute("page", SEARCH_LITERAL);
		return SEARCH_URL;
	}

	@RequestMapping("/read")
	String read(@RequestParam("query") String id, Model m) {

		Query q = theQueryService.getQueryById(Long.parseLong(id));
		List<Tag> tags = q.getTags();
		List<Attachment> attachments = q.getAttachemnts();
		List<Comment> comments = q.getComments();

		User u = q.getUser();

		m.addAttribute("query", q);
		m.addAttribute("tags", tags);
		m.addAttribute("attachments", attachments);
		m.addAttribute("comments", comments);
		m.addAttribute("new_comment", new Comment());
		m.addAttribute("user", u);

		return "Home/readQuery";
	}

	@RequestMapping("image/{id}")
	void showImage(@PathVariable String id, HttpServletResponse response) throws IOException {

		response.setContentType("image/jpeg");

		Optional<Attachment> findById = theAttachmentRepository.findById(Long.parseLong(id));
		if (findById.isPresent()) {

			Attachment attachment = findById.get();
			InputStream is = new ByteArrayInputStream(attachment.getBytes());
			IOUtils.copy(is, response.getOutputStream());
		}

	}
	
}
