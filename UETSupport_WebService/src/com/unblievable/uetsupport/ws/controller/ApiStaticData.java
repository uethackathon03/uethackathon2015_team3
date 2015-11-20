package com.unblievable.uetsupport.ws.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.unblievable.uetsupport.common.Constant;
import com.unblievable.uetsupport.models.Course;
import com.unblievable.uetsupport.models.Faculty;
import com.unblievable.uetsupport.ws.response.ResponseListObjects;

@RestController
@RequestMapping(value = "/api/static-data")
@Transactional
public class ApiStaticData extends BaseController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/course/list", method = RequestMethod.GET)
	public @ResponseBody ResponseListObjects<Object> listCourses(HttpSession httpSession) {
		Criteria criteria = getSession().createCriteria(Course.class);
		criteria.addOrder(Order.desc("createdTime"));
		List<Course> listCourses = criteria.list();
		return new ResponseListObjects<Object>(true, getMessage(Constant.msgSuccess, httpSession), listCourses.size(), listCourses);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/faculty/list", method = RequestMethod.GET)
	public @ResponseBody ResponseListObjects<Object> listFaculties(HttpSession httpSession) {
		Criteria criteria = getSession().createCriteria(Faculty.class);
		List<Faculty> listFaculties = criteria.list();
		return new ResponseListObjects<Object>(true, getMessage(Constant.msgSuccess, httpSession), listFaculties.size(), listFaculties);
	}
	
}
