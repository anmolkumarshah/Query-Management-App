package com.virtusa.querymanagement.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.virtusa.querymanagement.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	 User findByEmail(String email);
	 
	 User findByName(String name);
	 
	 @Modifying
	 @Transactional
	 @Query(value = "update user set name = :name, email=:email where user_id=:id",nativeQuery = true)
	 int updateNameAndEmail(@Param("name") String name,@Param("email") String email,@Param("id") Long id);	 
	 
}
