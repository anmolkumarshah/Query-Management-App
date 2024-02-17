package com.virtusa.querymanagement.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.virtusa.querymanagement.entity.Comment;
import com.virtusa.querymanagement.entity.Query;
import com.virtusa.querymanagement.entity.User;
import com.virtusa.querymanagement.repository.CommentRepository;
import com.virtusa.querymanagement.repository.UserRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository theCommentRepository;
	
	@Autowired
	UserRepository theUserRepository;

	@Autowired
	private QueryService theQueryService;

	public Comment save(Comment comment, String queryId) {
		Query queryById = theQueryService.getQueryById(Long.parseLong(queryId));

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserEmail = authentication.getName();
		User currentUser = theUserRepository.findByEmail(currentUserEmail);

		comment.setIsMarkedRight(false);
		comment.setVotes(0);
		comment.setTimestamp(new Timestamp(new Date().getTime()));
		comment.setQuery(queryById);
		comment.setUser(currentUser);

		return theCommentRepository.save(comment);
	}
	
	public void upvote(Long id) {
		theCommentRepository.upvote(id);
	}
	
	public void downvote(Long id) {
		theCommentRepository.downvote(id);
	}
	
	public void markCorrect(Long id) {
		theCommentRepository.markCorrect(id);
	}

}
