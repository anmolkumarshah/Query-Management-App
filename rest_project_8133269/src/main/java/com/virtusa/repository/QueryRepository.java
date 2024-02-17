package com.virtusa.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.virtusa.entity.Query;

@Repository
public interface QueryRepository extends JpaRepository<Query, Long> {

	public Query findById(long id);

	@Modifying
	@Transactional
	@org.springframework.data.jpa.repository.Query(value = "update query set content=:content, title=:title where query_id=:id", nativeQuery = true)
	public int update(@Param("title") String updatedTitle, @Param("content") String updatedContent,
			@Param("id") Long queryId);

	@Modifying
	@Transactional
	@org.springframework.data.jpa.repository.Query(value = "call query_upvote_downvote(:id,1)", nativeQuery = true)
	public int upvote(@Param("id") Long queryId);

	@Modifying
	@Transactional
	@org.springframework.data.jpa.repository.Query(value = "call query_upvote_downvote(:id,2)", nativeQuery = true)
	public int downvote(@Param("id") Long queryId);

	
	public List<Query> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String text1,String text2);

}
