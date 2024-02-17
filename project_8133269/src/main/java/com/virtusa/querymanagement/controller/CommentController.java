package com.virtusa.querymanagement.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.virtusa.querymanagement.entity.Comment;
import com.virtusa.querymanagement.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService theCommentService;
	
	static final  String REDIRECTURL = "redirect:/read?query=";

	@RequestMapping("/create")
	public String create(@RequestParam("query") String id, @ModelAttribute("new_comment") Comment comment) {
		
		theCommentService.save(comment,id);
		
		return REDIRECTURL+id;
	}
	
	@RequestMapping("/upvote")
	public String upvote(@RequestParam("comment") Long commentId,@RequestParam("query") Long queryId) {
		theCommentService.upvote(commentId);
		return REDIRECTURL+queryId;
	}
	
	@RequestMapping("/downvote")
	public String downvote(@RequestParam("comment") Long commentId,@RequestParam("query") Long queryId) {
		theCommentService.downvote(commentId);
		return REDIRECTURL+queryId;
	}
	
	@RequestMapping("/correct")
	public String markCorrect(@RequestParam("comment") Long commentId,@RequestParam("query") Long queryId) {
		theCommentService.markCorrect(commentId);
		return REDIRECTURL+queryId;
	}
	
	
}
