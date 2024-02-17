package com.virtusa.querymanagement.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Attachment {

	public Attachment(byte[] bytes, String name, Query query) {
		super();
		this.bytes = bytes;
		this.name = name;
		this.query = query;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attachmentId;
	
	@Lob
	private byte[] bytes;
	
	private String name;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "query_id")
	@JsonIgnore
	private Query query;
	
}
