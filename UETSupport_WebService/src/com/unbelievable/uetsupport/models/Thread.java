package com.unbelievable.uetsupport.models;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "thread")
public class Thread {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long threadId;
	public String title;
	public String content;
	public Integer status; // 1:show username, 2: anonymous
	@LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection(targetClass = Photo.class)
	public Collection<Photo> photos;
	
	@Transient
	public Integer totalLike;
	@Transient
	public Integer totalUnlike;
	
	@JsonIgnore
	@Column(nullable = false)
	public Long userId;
	
	@Transient
	public String username;
	
	@Transient
	public String avatar;
	
	@Transient
	public List<Comment> comments;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss 'GMT'+0700")
	public Date createdTime;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss 'GMT+0700")
	public Date modifiedTime;
}
