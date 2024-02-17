package com.virtusa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.entity.Query;
import com.virtusa.exception.QueryNotFound;
import com.virtusa.repository.QueryRepository;

@Service
public class QueryServiceImpl implements QueryService {
	
	@Autowired
	private QueryRepository theQueryRepository;

	@Override
	public List<Query> textsearch(String text) {
		return theQueryRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(text, text);
	}

	@Override
	public Query getQueryById(Long queryId) throws QueryNotFound {
		Optional<Query> findById = theQueryRepository.findById(queryId);
		if(findById.isPresent()) {
			return findById.get();
		}else {
			throw new QueryNotFound();
		}
	}

}
