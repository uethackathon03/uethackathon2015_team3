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
import com.unbelievable.uetsupport.dao.ThreadDao;
import com.unbelievable.uetsupport.models.LikeThread;
import com.unbelievable.uetsupport.models.Student;
import com.unbelievable.uetsupport.models.Thread;

public class ThreadDaoImpl extends AbstractDao implements ThreadDao {
	
	@Autowired
	CommentDao commentDao;
	
	@Autowired
	StudentDao studentDao;
	
	public ThreadDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Long save(Thread thread) {
		Long autoId = (Long) getSession().save(thread);
		return autoId;
	}

	@Override
	public void update(Thread thread) {
		getSession().update(thread);
	}

	@Override
	public Thread threadDetail(Long threadId) {
		Thread thread = (Thread) getSession().get(Thread.class, threadId);
		if (thread == null) {
			return null;
		}
		thread.comments = commentDao.listAllCommentByThread(threadId);
		thread.totalLike = getSession().createCriteria(LikeThread.class)
				.add(Restrictions
						.and(Restrictions.eq("threadId", threadId), Restrictions.eq("like", 1)))
				.list().size();
		thread.totalLike = getSession().createCriteria(LikeThread.class)
				.add(Restrictions
						.and(Restrictions.eq("threadId", threadId), Restrictions.eq("like", 0)))
				.list().size();
				
		Student student = studentDao.findStudentById(thread.userId);
		if ("1".equals(thread.status)) {
			if (CommonUtils.stringIsValid(student.fullname)) {
				thread.username = student.fullname;
			} else {
				thread.username = student.email;
			}
		} else {
			thread.username = "Anonymous";
		}
		if (CommonUtils.stringIsValid(student.address)) {
			thread.avatar = student.avatar;
		}
		return thread;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Thread> listThreadsByDate() {
		Criteria criteria = getSession().createCriteria(Thread.class);
		criteria.addOrder(Order.desc("createdTime"));
		List<Thread> listThreads = criteria.list();
		for (int i = 0; i < listThreads.size(); i++) {
			listThreads.get(i).totalLike = getSession().createCriteria(LikeThread.class)
					.add(Restrictions
							.and(Restrictions.eq("threadId", listThreads.get(i).threadId), Restrictions.eq("like", 1)))
					.list().size();
			listThreads.get(i).totalLike = getSession().createCriteria(LikeThread.class)
					.add(Restrictions
							.and(Restrictions.eq("threadId", listThreads.get(i).threadId), Restrictions.eq("like", 0)))
					.list().size();
					
			Student student = studentDao.findStudentById(listThreads.get(i).userId);
			if (1 == listThreads.get(i).status) {
				if (CommonUtils.stringIsValid(student.fullname)) {
					listThreads.get(i).username = student.fullname;
				} else {
					listThreads.get(i).username = student.email;
				}
			} else {
				listThreads.get(i).username = "Anonymous";
			}
			if (CommonUtils.stringIsValid(student.address)) {
				listThreads.get(i).avatar = student.avatar;
			}
		}
		
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Thread> listThreadsByHotest() {
		Criteria criteria = getSession().createCriteria(Thread.class);
		criteria.addOrder(Order.desc("totalLike"));
		criteria.addOrder(Order.asc("totalUnlike"));
		List<Thread> listThreads = criteria.list();
		for (int i = 0; i < listThreads.size(); i++) {
			listThreads.get(i).totalLike = getSession().createCriteria(LikeThread.class)
					.add(Restrictions
							.and(Restrictions.eq("threadId", listThreads.get(i).threadId), Restrictions.eq("like", 1)))
					.list().size();
			listThreads.get(i).totalLike = getSession().createCriteria(LikeThread.class)
					.add(Restrictions
							.and(Restrictions.eq("threadId", listThreads.get(i).threadId), Restrictions.eq("like", 0)))
					.list().size();
					
			Student student = studentDao.findStudentById(listThreads.get(i).userId);
			if ("1".equals(listThreads.get(i).status)) {
				if (CommonUtils.stringIsValid(student.fullname)) {
					listThreads.get(i).username = student.fullname;
				} else {
					listThreads.get(i).username = student.email;
				}
			} else {
				listThreads.get(i).username = "Anonymous";
			}
			if (CommonUtils.stringIsValid(student.address)) {
				listThreads.get(i).avatar = student.avatar;
			}
		}
		return criteria.list();
	}

	@Override
	public void delete(Thread thread) {
		getSession().delete(thread);
	}

}
