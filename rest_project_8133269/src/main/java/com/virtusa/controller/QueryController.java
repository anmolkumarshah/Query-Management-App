package com.virtusa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.DTO.SearchDTO;
import com.virtusa.entity.Query;
import com.virtusa.exception.QueryNotFound;
import com.virtusa.service.QueryService;

@RestController
@RequestMapping("/query")
public class QueryController {

	@Autowired
	private QueryService theQueryService;

	@PostMapping("/search")
	public ResponseEntity<Object> allquery(@RequestBody SearchDTO dto) {
		List<Query> searchResult = theQueryService.textsearch(dto.getText());
		return new ResponseEntity<>(searchResult, HttpStatus.OK);
	}
	
	@GetMapping("/{queryId}")
	public ResponseEntity<Object> getQuery(@PathVariable Long queryId) throws QueryNotFound {
		Query foundQuery = theQueryService.getQueryById(queryId);
		return new ResponseEntity<>(foundQuery, HttpStatus.FOUND);
	}
}
