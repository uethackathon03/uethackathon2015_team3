package com.unblievable.uetsupport.ws.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.unblievable.uetsupport.common.Constant;
import com.unblievable.uetsupport.dao.TeacherDao;
import com.unblievable.uetsupport.models.Teacher;
import com.unblievable.uetsupport.ws.response.ResponseListObjects;

@RestController
@Transactional
@RequestMapping("/api/student")
public class ApiTeacherController extends BaseController{
	
	@Autowired
	TeacherDao teacherDao;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody ResponseListObjects<Object> listTeachers(HttpSession httpSession) {
		List<Teacher> listTeacher = teacherDao.listAllTeachers();
		return new ResponseListObjects<Object>(true, getMessage(Constant.msgSuccess, httpSession), listTeacher.size(), listTeacher);
	}
}
