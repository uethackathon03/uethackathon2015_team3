package com.unblievable.uetsupport.dao;

import java.util.List;

import com.unblievable.uetsupport.models.Teacher;

public interface TeacherDao {
	
	List<Teacher> listAllTeachers();
	
	List<Teacher> listTeacherByFaculty(Long facultyId);
	
	
}
