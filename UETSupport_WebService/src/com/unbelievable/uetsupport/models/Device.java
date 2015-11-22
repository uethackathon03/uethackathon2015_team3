package com.unbelievable.uetsupport.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "device")
public class Device {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long Id;
	public Integer loginType;
	public String registrationId;
}
