package com.unbelievable.uetsupport.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.unbelievable.uetsupport.common.CommonUtils;
import com.unbelievable.uetsupport.dao.AbstractDao;
import com.unbelievable.uetsupport.dao.CommentDao;
import com.unbelievable.uetsupport.dao.StudentDao;
import com.unbelievable.uetsupport.models.Comment;
import com.unbelievable.uetsupport.models.Student;

public class CommentDaoImpl extends AbstractDao implements CommentDao {

	@Autowired
	StudentDao studentDao;
	
	public CommentDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Long save(Comment comment) {
		Long autoId = (Long) getSession().save(comment);
		return autoId;
	}

	@Override
	public void update(Comment comment) {
		getSession().update(comment);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> listAllCommentByThread(Long threadId) {
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.addOrder(Order.asc("isCorrect"));
		criteria.addOrder(Order.desc("createdTime"));
		criteria.add(Restrictions.eq("threadId", threadId));
		List<Comment> listComments = criteria.list();
		for (int i = 0; i < listComments.size(); i++) {
			Student student = studentDao.findStudentById(listComments.get(i).userId);
			if (CommonUtils.stringIsValid(student.fullname)) {
				listComments.get(i).username = student.fullname;
			} else {
				listComments.get(i).username = student.email;
			}
			if (CommonUtils.stringIsValid(student.avatar)) {
				listComments.get(i).avatar = student.avatar;
			}
		}
		return listComments;
	}

	@Override
	public void delete(Comment comment) {
		getSession().delete(comment);
	}

	@Override
	public Comment findCommentById(Long commentId) {
		Comment comment = (Comment) getSession().get(Comment.class, commentId);
		if (comment != null) {
			return comment;
		}
		return null;
	}

}
