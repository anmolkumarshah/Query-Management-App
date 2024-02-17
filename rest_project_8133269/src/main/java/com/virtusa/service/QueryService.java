package com.virtusa.service;

import java.util.List;

import com.virtusa.entity.Query;
import com.virtusa.exception.QueryNotFound;

public interface QueryService {

	List<Query> textsearch(String text);
	
	Query getQueryById(Long queryId) throws QueryNotFound ;
	
}
