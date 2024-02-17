package com.virtusa.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	private Query query;
	
	@OneToOne()
	@JoinColumn(referencedColumnName = "userId")
	@JsonIgnore
	private User user;
	
}
