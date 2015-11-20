package com.unblievable.uetsupport.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.unblievable.uetsupport.dao.AbstractDao;
import com.unblievable.uetsupport.dao.TeacherDao;
import com.unblievable.uetsupport.models.Teacher;

public class TeacherDaoImpl extends AbstractDao implements TeacherDao {

	public TeacherDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> listAllTeachers() {
		Criteria criteria = getSession().createCriteria(Teacher.class);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> listTeacherByFaculty(Long facultyId) {
		List<Teacher> listTeachers = getSession()
				.createCriteria(Teacher.class)
				.add(Restrictions.eqOrIsNull("facultyId", facultyId))
				.list();
		return listTeachers;
	}
	
}
