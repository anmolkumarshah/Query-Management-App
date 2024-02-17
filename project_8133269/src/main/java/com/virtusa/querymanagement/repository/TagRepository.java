package com.virtusa.querymanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.querymanagement.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
	
	public Tag findById(long id); 
	
}
