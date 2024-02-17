package com.virtusa.querymanagement.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.virtusa.querymanagement.entity.Attachment;
import com.virtusa.querymanagement.entity.Query;
import com.virtusa.querymanagement.entity.Tag;
import com.virtusa.querymanagement.entity.User;
import com.virtusa.querymanagement.repository.QueryRepository;
import com.virtusa.querymanagement.repository.TagRepository;
import com.virtusa.querymanagement.repository.UserRepository;

@Service
public class QueryService {
	@Autowired
	QueryRepository theQueryRepository;

	@Autowired
	TagRepository theTagRepository;

	@Autowired
	UserRepository theUserRepository;

	public Query save(MultipartFile[] files, Query theQuery, User optionalUser) {

		List<Attachment> attachments = List.of(files).stream().filter(f -> ! (f.getOriginalFilename().isEmpty()) ).map(a -> {
			try {
				return new Attachment(a.getBytes(), a.getOriginalFilename(), theQuery);
			} catch (IOException e) {
				return null;
			}
		}).collect(Collectors.toList());

		if (optionalUser == null) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentUserEmail = authentication.getName();
			User u = theUserRepository.findByEmail(currentUserEmail);
			theQuery.setUser(u);
		} else {
			theQuery.setUser(optionalUser);
		}

		List<Tag> tags = theQuery.getTagsId().stream().map(s -> theTagRepository.findById(Long.parseLong(s)))
				.collect(Collectors.toList());
		tags.forEach(t -> t.setQueryList(List.of(theQuery)));

		theQuery.setTags(tags);
		theQuery.setAttachemnts(attachments);
		theQuery.setAskedOn(new Timestamp(new Date().getTime()));
		theQuery.setVotes(0);

		return theQueryRepository.save(theQuery);

	}

	public int update(Query q) {
		return theQueryRepository.update(q.getTitle(), q.getContent(), q.getQuery_id());
	}

	public List<Query> allQueryList() {
		return theQueryRepository.findAll();
	}

	public Query getQueryById(Long id) throws NoSuchElementException {
		Optional<Query> findById = theQueryRepository.findById(id);
		if (findById.isPresent()) {
			return findById.get();
		}else {
			throw new NoSuchElementException();
		}
	}

	public void delete(Long id) {
		Optional<Query> findById = theQueryRepository.findById(id);
		if (findById.isPresent()) {
			Query query = findById.get();
			theQueryRepository.delete(query);
		}
	}

	public void upvote(Long id) {
		theQueryRepository.upvote(id);
	}

	public void downvote(Long id) {
		theQueryRepository.downvote(id);
	}

	public List<Query> search(String text) {
		return theQueryRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(text, text);
	}

}
