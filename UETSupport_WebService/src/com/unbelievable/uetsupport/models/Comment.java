package com.unbelievable.uetsupport.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long commentId;
	
	@Column(nullable = false)
	@JsonIgnore
	public Long threadId;
	public String content;
	public String photo;
	public boolean isCorrect;
	
	@JsonIgnore
	@Column(nullable = false)
	public Long userId;
	
	@Transient
	public String username;
	
	@Transient
	public String avatar;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss 'GMT'Z")
	public Date createdTime;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss 'GMT'Z")
	public Date modifiedTime;
}
