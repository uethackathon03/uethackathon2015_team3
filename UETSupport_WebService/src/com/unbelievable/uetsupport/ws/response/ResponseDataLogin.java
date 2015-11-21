package com.unbelievable.uetsupport.ws.response;

import com.unbelievable.uetsupport.models.Student;


public class ResponseDataLogin {
	public String token;
	public Student student;

	public ResponseDataLogin() {}
	
	public ResponseDataLogin(String token, Student student) {
		super();
		this.token = token;
		this.student = student;
	}
	
}
