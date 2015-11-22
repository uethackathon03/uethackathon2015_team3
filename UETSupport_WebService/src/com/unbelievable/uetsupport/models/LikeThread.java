package com.unbelievable.uetsupport.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "like_thread")
public class LikeThread {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long likeId;
	public Long userId;
	public Long threadId;
	public Integer like;	// 1: like, 0:unlike
}
