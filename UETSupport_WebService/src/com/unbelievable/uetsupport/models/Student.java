package com.unbelievable.uetsupport.models;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "student")
public class Student  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long studentId;
	@Column(unique = true)
	public String username;
	@JsonIgnore
	public String password;
	public String fullname;
	public String gender;
	public String email;
	public String otherEmail;
	public String avatar;
	public String phone;
	@Column(columnDefinition = "DATE")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss 'GMT'Z")
	public String birthday;
	@Column(columnDefinition = "text")
	public String description;
	
	public Integer score;
	public String address;
	public String placeOfBirth;
	public String role;
	public String ethnic;			// Dan toc
	public String religion;			// Ton giao
	public String country;			// Quoc gia
	public String nationality;		// 	Quoc tich
	public String indentityCard;	//	So CMT
	@Column(columnDefinition = "DATE")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss 'GMT'Z")
	public String daysForIdentityCards; // Ngay cap CMT
	public String placeForIdentityCards; // Noi cap CMT
	
	public String course;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection(targetClass = Schedule.class)
	public Collection<Schedule> schedules;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection(targetClass = Reminder.class)
	public Collection<Reminder> reminders;
	
	@Column(name = "createdTime")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss 'GMT'Z")
	public Date createdTime;
	@Column(name = "modifiedTime")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss 'GMT'Z")
	public Date modifiedTime;
	
}
