package com.virtusa.querymanagement.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


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
@ToString(exclude = {"tags","comments","attachemnts"})
public class Query {
	
	@SequenceGenerator(
		allocationSize = 1,
		initialValue = 1000,
		name = "query_sequence",
		sequenceName = "query_seq_table"
	)

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "query_sequence")
	private Long query_id;
	
	@NotNull
	@Size(min = 5,max = 2000,message = "Title length must be between 5 and 2000")
	@Column(length = 2000,nullable = false)
	private String title;
	
	@NotNull
	@Size(min = 10,max = 5000,message = "Content length must be between 5 and 5000")
	@Column(length = 5000,nullable = false)
	private String content;
	
	private Timestamp askedOn;
	
	private Integer votes;
	
	
	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinTable(name = "query_tag_map",
		joinColumns = @JoinColumn(name = "query_id",referencedColumnName = "query_id"),
		inverseJoinColumns = @JoinColumn(name="tag_id",referencedColumnName = "tag_id")
	)
	private List<Tag> tags;
	
	@OneToMany(mappedBy = "query",cascade = CascadeType.ALL)
	private List<Attachment> attachemnts;
	
	@OneToMany(mappedBy = "query",cascade = CascadeType.ALL)
	private List<Comment> comments;
	
	@ManyToOne()
	@JoinColumn(referencedColumnName = "userId")
	private User user;
	
	
	@Size(min = 1,message = "Select atleast 1 Tag")
	@Transient
	private List<String> tagsId;
	
	
}
