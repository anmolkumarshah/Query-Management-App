package com.virtusa.querymanagement.Query;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import com.virtusa.querymanagement.entity.Query;
import com.virtusa.querymanagement.entity.User;
import com.virtusa.querymanagement.service.QueryService;
import com.virtusa.querymanagement.service.UserService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class QueryServiceTest {

	@Autowired
	QueryService theQueryService;

	@Autowired
	UserService theUserService;

	static Long generatedQueryId = null;

	@Test
	@Order(1)
	void save() {
		User userById = theUserService.getUserById(99L);

		Query newQuery = new Query();
		newQuery.setAskedOn(new Timestamp(new Date().getTime()));
		newQuery.setAttachemnts(List.of());
		newQuery.setComments(List.of());
		newQuery.setContent(
				"Hi All, Currently I am on bench looking for opportunity In Automation Testing. I had 3+ years of experience in QA Automation Testing (Selenium with java), TestNg, Maven, had knowledge on API testing  using Postman tools. Worked in Agile environment.");
		newQuery.setTitle(
				"Hi All, Currently I am on bench looking for opportunity In Automation Testing. I had 3+ years of experience in QA Automation Testing (Selenium with java), TestNg, Maven, had knowledge on API testing  using Postman tools. Worked in Agile environment.");
		newQuery.setVotes(0);

		newQuery.setTagsId(List.of("4", "5"));

		Query createdQuery = theQueryService.save(new MultipartFile[] {}, newQuery, userById);
		generatedQueryId = createdQuery.getQuery_id();
		assertTrue(createdQuery.getQuery_id() > 0);
	}

	@Test
	@Order(2)
	void update() {
		Query old = theQueryService.getQueryById(generatedQueryId);
		old.setTitle("updated" + old.getTitle());
		int update = theQueryService.update(old);
		assertTrue(update > 0);
	}

	@Test
	@Order(3)
	void get() {
		List<Query> li = theQueryService.allQueryList();
		assertTrue(li.size() > 0);
	}

	@Test
	@Order(4)
	void search() {
		List<Query> li = theQueryService.search("bench");
		assertTrue(li.size() > 0);
	}

	@Test
	@Order(5)
	void getOne() {
		Query q = theQueryService.getQueryById(generatedQueryId);
		assertEquals(q.getQuery_id(), generatedQueryId);
	}

	@Test
	@Order(6)
	void upvote() {
		Query q = theQueryService.getQueryById(generatedQueryId);
		int oldVotes = q.getVotes();
		theQueryService.upvote(generatedQueryId);
		int newVotes = theQueryService.getQueryById(generatedQueryId).getVotes();
		assertTrue(newVotes > oldVotes);
	}

	@Test
	@Order(7)
	void downvote() {
		Query q = theQueryService.getQueryById(generatedQueryId);
		int oldVotes = q.getVotes();
		theQueryService.downvote(generatedQueryId);
		int newVotes = theQueryService.getQueryById(generatedQueryId).getVotes();
		assertTrue(newVotes < oldVotes);
	}

	@Test
	@Order(8)
	void delete() {
		theQueryService.delete(generatedQueryId);
		try {
			theQueryService.getQueryById(generatedQueryId);
		} catch (Exception e) {
			assertEquals(e.getClass(), new NoSuchElementException().getClass());
		}
	}
}
