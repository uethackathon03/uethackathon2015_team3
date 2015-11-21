package com.unbelievable.uetsupport.ws.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.unbelievable.uetsupport.common.Constant;
import com.unbelievable.uetsupport.models.Answer;
import com.unbelievable.uetsupport.models.Department;
import com.unbelievable.uetsupport.models.Information;
import com.unbelievable.uetsupport.models.Teacher;
import com.unbelievable.uetsupport.ws.response.ResponseListObjects;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping(value = "/api/data")
@Transactional
public class ApiDataController extends BaseController {
	
	
	
	@RequestMapping(value = "/departments", method = RequestMethod.GET)
	public @ResponseBody ResponseListObjects<Object> listDepartments(HttpSession httpSession) {
		List<Department> listDepartments = getSession()
				.createCriteria(Department.class)
				.list();
		return new ResponseListObjects<Object>(true, getMessage(Constant.msgSuccess, httpSession), listDepartments.size(), listDepartments);
	}
	
	@RequestMapping(value = "/informations", method = RequestMethod.GET)
	public @ResponseBody ResponseListObjects<Object> listInformation(HttpSession httpSession) {
		Criteria criteria = getSession().createCriteria(Information.class);
		criteria.addOrder(Order.desc("createdTime"));
		List<Information> listInformations = criteria.list();
		return new ResponseListObjects<Object>(true, getMessage(Constant.msgSuccess, httpSession), listInformations.size(), listInformations);
		
	}
	
	@RequestMapping(value = "/teachers", method = RequestMethod.GET)
	public @ResponseBody ResponseListObjects<Object> listTeachers(HttpSession httpSession) {
		List<Teacher> listTeacher = getSession().createCriteria(Teacher.class).list();
		return new ResponseListObjects<Object>(true, getMessage(Constant.msgSuccess, httpSession), listTeacher.size(), listTeacher);
	}
	
	@RequestMapping(value = "/question-answers", method = RequestMethod.GET)
	public @ResponseBody ResponseListObjects<Object> listAnswers(HttpSession httpSession) {
		Criteria criteria = getSession().createCriteria(Answer.class);
		criteria.addOrder(Order.asc("care"));
		List<Answer> listAnswers = criteria.list();
		return new ResponseListObjects<Object>(true, getMessage(Constant.msgSuccess, httpSession), listAnswers.size(), listAnswers);
	}
	
}
