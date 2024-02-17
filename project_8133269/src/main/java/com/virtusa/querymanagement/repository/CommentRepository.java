package com.virtusa.querymanagement.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.virtusa.querymanagement.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	@Modifying
	@Transactional
	@Query(value = "call comment_upvote_downvote(:id,1)",nativeQuery = true)
	public int upvote(@Param("id") Long comentId);
	
	@Modifying
	@Transactional
	@Query(value = "call comment_upvote_downvote(:id,2)",nativeQuery = true)
	public int downvote(@Param("id") Long comentId);
	
	@Modifying
	@Transactional
	@Query(value = "call mark_comment_right(:id)",nativeQuery = true)
	public int markCorrect(@Param("id") Long comentId);

}
