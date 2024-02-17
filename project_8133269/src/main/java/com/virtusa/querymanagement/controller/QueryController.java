package com.virtusa.querymanagement.controller;


import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.virtusa.querymanagement.entity.Query;
import com.virtusa.querymanagement.entity.Tag;
import com.virtusa.querymanagement.repository.AttachmentRepository;
import com.virtusa.querymanagement.repository.TagRepository;
import com.virtusa.querymanagement.service.QueryService;

@Controller
@RequestMapping("/query")
public class QueryController {

	@Autowired
	TagRepository theTagRepository;
	
	@Autowired
	QueryService theQueryService;
	
	@Autowired
	AttachmentRepository theAttachmentRepository;
	
	static Logger logger = LogManager.getLogger(QueryController.class);
	static final String REDIRECT_QUERY =  "redirect:/read?query=";

	@RequestMapping("/create")
	public String createNewQuery(Model m) {

		List<Tag> tags = theTagRepository.findAll();

		m.addAttribute("newQuery", new Query());
		m.addAttribute("tags", tags);

		return "Query/NewQuery";
	}

	@PostMapping(value = "/save")
	public String save(@Valid @ModelAttribute("newQuery") Query theQuery, BindingResult res,
			@RequestParam("images") MultipartFile[] multipartFiles,Model m) {
		
		if(res.hasErrors()) {
			List<Tag> tags = theTagRepository.findAll();
			m.addAttribute("tags", tags);
			return "Query/NewQuery";
		}else {
			
			Query q = theQueryService.save(multipartFiles, theQuery,null);
			
			String log = String.format("Query Saved with id %d", q.getQuery_id());			
			logger.info(log);
			
			return "redirect:/user/queries";
		}
		
	}
	
	
	@GetMapping(value = "/update")
	public String updatePage(Model m,@RequestParam("query") Long id) {
		Query q = theQueryService.getQueryById(id);
		m.addAttribute("oldQuery", q);
		m.addAttribute("tags", q.getTags());
		m.addAttribute("attachments", q.getAttachemnts());
		return "Query/EditQuery";
	}
	
	@PostMapping (value = "/update")
	public String update(@Valid @ModelAttribute("oldQuery") Query updatedQuery,BindingResult res) {
		if(res.hasErrors()) {
			return "Query/EditQuery";
		}		
		int update = theQueryService.update(updatedQuery);
		
		String log = String.format("Query Updated with number of changes %d", update);
		logger.info(log);
		
		return REDIRECT_QUERY+updatedQuery.getQuery_id();
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("query") Long id) {
		theQueryService.delete(id);	
		
		String log = String.format("Query deleted with id %d", id);
		logger.fatal(log);
		
		return "redirect:/user/queries";
	}	
	
	

	@RequestMapping("/upvote")
	public String upvote(@RequestParam("query") String id) {
		
		theQueryService.upvote(Long.parseLong(id));
		return REDIRECT_QUERY+id;
	}
	
	@RequestMapping("/downvote")
	public String downvote(@RequestParam("query") Long id) {
		theQueryService.downvote(id);
		return REDIRECT_QUERY+id;
	}
	

}
