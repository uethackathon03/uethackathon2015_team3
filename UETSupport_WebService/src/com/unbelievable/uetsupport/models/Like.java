package com.unbelievable.uetsupport.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "like")
public class Like {
	
	public Long userId;
	public Long threadId;
	public Integer like;	// 1: like, 0:unlike
}
