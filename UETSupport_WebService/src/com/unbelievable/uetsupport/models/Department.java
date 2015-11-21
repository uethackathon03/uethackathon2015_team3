package com.unbelievable.uetsupport.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {		// Phòng ban
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long departmentId;
	
	public String name;
	public String description;
	public String address;
	
}
