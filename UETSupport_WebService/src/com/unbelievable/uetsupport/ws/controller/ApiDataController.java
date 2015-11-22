package com.unbelievable.uetsupport.ws.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.android.gcm.server.InvalidRequestException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.unbelievable.uetsupport.common.CommonUtils;
import com.unbelievable.uetsupport.common.Constant;
import com.unbelievable.uetsupport.models.Answer;
import com.unbelievable.uetsupport.models.Department;
import com.unbelievable.uetsupport.models.Device;
import com.unbelievable.uetsupport.models.Information;
import com.unbelievable.uetsupport.models.Teacher;
import com.unbelievable.uetsupport.ws.response.ResponseListObjects;
import com.unbelievable.uetsupport.ws.response.ResponseObjectDetail;

@RestController
@RequestMapping(value = "/api/data")
@Transactional
public class ApiDataController extends BaseController {
	
	public static final String API_KEY = "AIzaSyARJ81qqAHiWHPoB82fm1_BcAf4aSkQpmA";
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/departments", method = RequestMethod.GET)
	public @ResponseBody ResponseListObjects<Object> listDepartments(HttpSession httpSession,
			@RequestParam(value = "keySearch", required = false) String keySearch) {
		Criteria criteria = getSession()
				.createCriteria(Department.class);
		if (CommonUtils.stringIsValid(keySearch)) {
			criteria.add(Restrictions.like("name", "%" + keySearch + "%"));
		}
		List<Department> listDepartments = criteria.list();
		return new ResponseListObjects<Object>(true, getMessage(Constant.msgSuccess, httpSession), listDepartments.size(), listDepartments);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/informations", method = RequestMethod.GET)
	public @ResponseBody ResponseListObjects<Object> listInformation(HttpSession httpSession) {
		Criteria criteria = getSession().createCriteria(Information.class);
		criteria.addOrder(Order.desc("createdTime"));
		List<Information> listInformations = criteria.list();
		return new ResponseListObjects<Object>(true, getMessage(Constant.msgSuccess, httpSession), listInformations.size(), listInformations);
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/teachers", method = RequestMethod.GET)
	public @ResponseBody ResponseListObjects<Object> listTeachers(HttpSession httpSession) {
		List<Teacher> listTeacher = getSession().createCriteria(Teacher.class).list();
		return new ResponseListObjects<Object>(true, getMessage(Constant.msgSuccess, httpSession), listTeacher.size(), listTeacher);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/question-answers", method = RequestMethod.GET)
	public @ResponseBody ResponseListObjects<Object> listAnswers(HttpSession httpSession) {
		Criteria criteria = getSession().createCriteria(Answer.class);
		criteria.addOrder(Order.asc("care"));
		List<Answer> listAnswers = criteria.list();
		return new ResponseListObjects<Object>(true, getMessage(Constant.msgSuccess, httpSession), listAnswers.size(), listAnswers);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/notification", method = RequestMethod.POST)
	public @ResponseBody ResponseObjectDetail<Object> notification(HttpSession httpSession,
			@RequestParam(value = "message", required = false, defaultValue = "") String message) {
		Sender sender = new Sender(API_KEY);
		Message msgNoti = new Message.Builder()
		// If multiple messages are sent using the same .collapseKey()
		// the android target device, if it was offline during earlier message
		// transmissions, will only receive the latest message for that key when
		// it goes back on-line.
		.addData("message", message)
		.build();
		List<String> androidTargets = new ArrayList<String>();
		androidTargets = getSession().createCriteria(Device.class).add(Restrictions.eq("deviceType", "1")).setProjection(Projections.property("registrationId")).list();
			try {
				// use this for multicast messages.  The second parameter
				// of sender.send() will need to be an array of register ids.
				MulticastResult result = sender.send(msgNoti, androidTargets, 2);
				
				if (result.getResults() != null) {
					return new ResponseObjectDetail<Object>(true, getMessage(Constant.msgSuccess, httpSession), null);
				} else {
					int error = result.getFailure();
					return new ResponseObjectDetail<Object>(false, "Error" + ": " + error, null);
				}
			} catch (InvalidRequestException e) {
	            return new ResponseObjectDetail<Object>(false, "Invalid Request" + e.getMessage(), null);
	        } catch (IOException e) {
	        	return new ResponseObjectDetail<Object>(false, "IO Exception" + e.getMessage(), null);
	        }
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody ResponseObjectDetail<Object> testNotification(HttpSession httpSession,
			@RequestParam(value = "registrationId", required = false, defaultValue = "") String registrationId) {
		Device device = new Device();
		device.loginType = 1;
		device.registrationId = registrationId;
		getSession().save(device);
		return new ResponseObjectDetail<Object>(true, getMessage(Constant.msgSuccess, httpSession), null);
	}
}
