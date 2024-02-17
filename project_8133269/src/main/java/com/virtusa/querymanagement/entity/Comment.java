package com.virtusa.querymanagement.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;
	
	@Column(length = 2000)
	private String content;
	
	private Integer votes;
	
	private Timestamp timestamp;
	
	private Boolean isMarkedRight;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "query_id")
	private Query query;
	
	@OneToOne()
	@JoinColumn(referencedColumnName = "userId")
	private User user;
	
}
