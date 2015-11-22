package com.unbelievable.uetsupport.ws.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.unbelievable.uetsupport.common.CommonUtils;
import com.unbelievable.uetsupport.common.Constant;
import com.unbelievable.uetsupport.dao.CommentDao;
import com.unbelievable.uetsupport.dao.ThreadDao;
import com.unbelievable.uetsupport.models.Comment;
import com.unbelievable.uetsupport.models.LikeThread;
import com.unbelievable.uetsupport.models.Photo;
import com.unbelievable.uetsupport.models.Student;
import com.unbelievable.uetsupport.models.Thread;
import com.unbelievable.uetsupport.ws.response.ResponseListObjects;
import com.unbelievable.uetsupport.ws.response.ResponseObjectDetail;

@RestController
@RequestMapping(value = "/api/thread")
@Transactional
public class ApiThreadController extends BaseController {
	
	@Autowired
	ThreadDao threadDao;
	
	@Autowired
	CommentDao commentDao;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody ResponseObjectDetail<Object> createThread(HttpSession httpSession,
			HttpServletRequest request,
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "content", required = true) String content,
			@RequestParam(value = "status", required = true) Integer status,
			@RequestParam(value = "photos", required = false) MultipartFile[] files) {
		if (!checkToken(httpSession)) {
			return new ResponseObjectDetail<Object>(false, getMessage(Constant.msgInvalidToken, httpSession), null);
		}
		Thread thread = new Thread();
		thread.title = title;
		thread.content = content;
		thread.status = status;
		thread.userId = token.userId;
		thread.photos = new ArrayList<Photo>();
		thread.createdTime = thread.modifiedTime = new Date(System.currentTimeMillis());
		Long autoId = threadDao.save(thread);
		if (autoId > 0) {
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					try {
						if (CommonUtils.checkImage(files[i].getOriginalFilename())) {
							byte[] bytes = files[i].getBytes();
							String nameThreadPhoto = "thread_photo_"
											+ autoId
											+ "_" 
											+ String.valueOf(System.currentTimeMillis())
											+ "."
											+ FilenameUtils.getExtension(files[i].getOriginalFilename());
							File dir = new File(Constant.THREAD_FOLDER);
							if (!dir.exists()) {
								dir.mkdirs();
							}
							File serverFile = new File(dir, nameThreadPhoto);
							BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
							stream.write(bytes);
							stream.close();

							Photo photo = new Photo();
							photo.photoUrl = CommonUtils.getBaseUrl(request) + Constant.THREAD_CONTEXT_PATH + nameThreadPhoto;
							thread.photos.add(photo);
						} else {
							return new ResponseObjectDetail<Object>(false, "File is not image", null);
						}
					} catch (Exception e) {
						return new ResponseObjectDetail<Object>(false, "Exception: " + e.getMessage(), null);
					}
				}
			}
			return new ResponseObjectDetail<Object>(true, getMessage(Constant.msgSuccess, httpSession), thread);
		}
		return new ResponseObjectDetail<Object>(false, getMessage(Constant.msgCannotInsertDB, httpSession), null);
		
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody ResponseObjectDetail<Object> editThread(HttpSession httpSession,
			@RequestParam(value = "threadId", required = true) Long threadId,
			HttpServletRequest request,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "content", required = false, defaultValue = "") String content,
			@RequestParam(value = "status", required = false) Integer status) {
		if (!checkToken(httpSession)) {
			return new ResponseObjectDetail<Object>(false, getMessage(Constant.msgInvalidToken, httpSession), null);
		}
		Thread thread = threadDao.threadDetail(threadId);
		if (thread.userId == token.userId) {
			if (CommonUtils.stringIsValid(title)) thread.title = title;
			if (CommonUtils.stringIsValid(content)) thread.content = content;
			if (status != null) thread.status = status;
			threadDao.update(thread);
		}
		return new ResponseObjectDetail<Object>(true, getMessage(Constant.msgSuccess, httpSession), thread);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody ResponseListObjects<Object> listThreads(HttpSession httpSession) {
		List<Thread> listThreads = threadDao.listThreadsByDate();
		return new ResponseListObjects<Object>(true, getMessage(Constant.msgSuccess, httpSession), listThreads.size(), listThreads);
	}
	
	@RequestMapping(value = "/list/{threadId}", method = RequestMethod.GET)
	public @ResponseBody ResponseObjectDetail<Object> listThreads(HttpSession httpSession,
			@PathVariable Long threadId) {
		Thread thread = threadDao.threadDetail(threadId);
		if (thread == null) {
			return new ResponseObjectDetail<Object>(false, "Thread not found", null);
		}
		return new ResponseObjectDetail<Object>(false, getMessage(Constant.msgSuccess, httpSession), thread);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseObjectDetail<Object> deleteThread(HttpSession httpSession,
			@RequestParam(value = "threadId") Long threadId) {
		if (!checkToken(httpSession)) {
			return new ResponseObjectDetail<Object>(false, getMessage(Constant.msgInvalidToken, httpSession), null);
		}
		Thread thread = threadDao.threadDetail(threadId);
		if (thread == null) {
			return new ResponseObjectDetail<Object>(false, "Thread not found", null);
		} else if (thread.userId == token.userId) {
			Query query = sessionFactory.getCurrentSession()
					.createQuery("delete Comment where threadId = " + String.valueOf(threadId));
			query.executeUpdate();
			threadDao.delete(thread);
			return new ResponseObjectDetail<Object>(true, getMessage(Constant.msgSuccess, httpSession), thread);
		} else {
			return new ResponseObjectDetail<Object>(false, "", null);
		}
		
	}
	
	@RequestMapping(value = "/comment/create", method = RequestMethod.POST)
	public @ResponseBody ResponseObjectDetail<Object> createComment(HttpSession httpSession, HttpServletRequest request,
			@RequestParam(value = "threadId", required = true) Long threadId,
			@RequestParam(value = "content", required = true) String content,
			@RequestParam(value = "photo", required = false) MultipartFile photo) {
		if (!checkToken(httpSession)) {
			return new ResponseObjectDetail<Object>(false, getMessage(Constant.msgInvalidToken, httpSession), null);
		}
		Thread thread = threadDao.threadDetail(threadId);
		if (thread == null) {
			return new ResponseObjectDetail<Object>(false, "Thread not found", null);
		}
		Comment comment = new Comment();
		comment.content = content;
		comment.threadId = threadId;
		comment.isCorrect = false;
		comment.userId = token.userId;
		if (photo != null) {
			if (CommonUtils.checkImage(photo.getOriginalFilename())) {
				try {
					byte[] bytesCoverPhoto = photo.getBytes();
					String covertPhotoName = "comment_photo_"
							+ comment.commentId
							+ "_" 
							+ String.valueOf(System.currentTimeMillis())
							+ "."
							+ FilenameUtils.getExtension(photo.getOriginalFilename());
					File dir = new File(Constant.THREAD_FOLDER);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					File serverFile = new File(dir, covertPhotoName);
	                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
	                stream.write(bytesCoverPhoto);
	                stream.close();
	                comment.photo = CommonUtils.getBaseUrl(request) + Constant.THREAD_CONTEXT_PATH + covertPhotoName;
				} catch (Exception e) {
					return new ResponseObjectDetail<Object>(false, e.getMessage(), null);
				}
			} else {
				return new ResponseObjectDetail<Object>(false, "File is not image", null);
			}
		}
		comment.createdTime = comment.modifiedTime = new Date(System.currentTimeMillis());
		commentDao.save(comment);
		getSession().flush();
		return new ResponseObjectDetail<Object>(true, getMessage(Constant.msgSuccess, httpSession), commentDao.listAllCommentByThread(threadId));
	}
	
	@RequestMapping(value = "/comment/edit", method = RequestMethod.POST)
	public @ResponseBody ResponseObjectDetail<Object> editComment(HttpSession httpSession,
			@RequestParam(value = "commentId", required = true) Long commentId,
			@RequestParam(value = "content", required = true) String content) {
		if (!checkToken(httpSession)) {
			return new ResponseObjectDetail<Object>(false, getMessage(Constant.msgInvalidToken, httpSession), null);
		}
		Comment comment = commentDao.findCommentById(commentId);
		
		if (comment == null) {
			return new ResponseObjectDetail<Object>(false, "comment not found", null);
		}
		Thread thread = threadDao.threadDetail(comment.commentId);
		if (comment.userId == token.userId || thread.userId == token.userId ) {
			comment.content = content;
			comment.modifiedTime = new Date(System.currentTimeMillis());
			commentDao.update(comment);
			getSession().flush();
			return new ResponseObjectDetail<Object>(true, getMessage(Constant.msgSuccess, httpSession), commentDao.listAllCommentByThread(comment.threadId));
		}
		return new ResponseObjectDetail<Object>(false, "", commentDao.listAllCommentByThread(comment.threadId));
	}
	
	

	@RequestMapping(value = "/comment/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseObjectDetail<Object> deleteComment(HttpSession httpSession,
			@RequestParam(value = "commentId", required = true) Long commentId) {
		if (!checkToken(httpSession)) {
			return new ResponseObjectDetail<Object>(false, getMessage(Constant.msgInvalidToken, httpSession), null);
		}
		Comment comment = commentDao.findCommentById(commentId);
		
		if (comment == null) {
			return new ResponseObjectDetail<Object>(false, "comment not found", null);
		}
		Thread thread = threadDao.threadDetail(comment.commentId);
		if (comment.userId == token.userId || thread.userId == token.userId ) {
			File dir = new File(Constant.THREAD_FOLDER);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File serverFile = new File(dir, comment.photo);
			serverFile.delete();
			commentDao.delete(comment);
			getSession().flush();
			return new ResponseObjectDetail<Object>(true, getMessage(Constant.msgSuccess, httpSession), commentDao.listAllCommentByThread(comment.threadId));
		}
		return new ResponseObjectDetail<Object>(false, "", commentDao.listAllCommentByThread(comment.threadId));
	}
	
	@RequestMapping(value = "/like", method = RequestMethod.POST)
	public @ResponseBody ResponseObjectDetail<Object> like(HttpSession httpSession,
			@RequestParam(value = "threadId", required = true) Long threadId,
			@RequestParam(value = "like", required = true) Integer like) {
		if (!checkToken(httpSession)) {
			return new ResponseObjectDetail<Object>(false, getMessage(Constant.msgInvalidToken, httpSession), null);
		}
		Thread thread = threadDao.threadDetail(threadId);
		if (thread == null) {
			return new ResponseObjectDetail<Object>(false, "thread not found", null);
		}
		LikeThread likeObj = (LikeThread) getSession().createCriteria(LikeThread.class)
				.add(Restrictions.and(Restrictions.eq("threadId", thread),Restrictions.eq("userId", token.userId)))
				.uniqueResult();
		if (likeObj != null) {
			likeObj.like = like;
			getSession().update(likeObj);
		} else {
			likeObj = new LikeThread();
			likeObj.like = like;
			likeObj.threadId = threadId;
			likeObj.userId = token.userId;
			getSession().save(likeObj);
		}
		getSession().flush();
		thread = threadDao.threadDetail(threadId);
		return new ResponseObjectDetail<Object>(false, getMessage(Constant.msgSuccess, httpSession), thread);
	}
	
	@RequestMapping(value = "/is-correct", method = RequestMethod.POST)
	public @ResponseBody ResponseObjectDetail<Object> isCorrect(HttpSession httpSession,
			@RequestParam(value = "isCorrect", required = true) Integer isCorrect,
			@RequestParam(value = "threadId", required = true) Long threadId,
			@RequestParam(value = "commentId", required = true) Long commentId) {
		if (!checkToken(httpSession)) {
			return new ResponseObjectDetail<Object>(false, getMessage(Constant.msgInvalidToken, httpSession), null);
		}
		Thread thread = threadDao.threadDetail(threadId);
		if (thread == null) {
			return new ResponseObjectDetail<Object>(false, "thread not found", null);
		}
		Comment comment = (Comment) getSession().createCriteria(Comment.class)
				.add(Restrictions
						.and(Restrictions.eq("commentId", commentId), Restrictions.eq("threadId", thread)))
				.uniqueResult();
		if (comment == null) {
			return new ResponseObjectDetail<Object>(false, "comment not found", null);
		}
		Student student = (Student) getSession().get(Student.class, comment.userId);
		if (isCorrect == 1) {
			comment.isCorrect = true;
			student.score++;
			 
		} else {
			student.score--;
			comment.isCorrect = false;
		}
		getSession().update(student);
		commentDao.update(comment);
		getSession().flush();
		return new ResponseObjectDetail<Object>(true, getMessage(Constant.msgSuccess, httpSession), commentDao.listAllCommentByThread(threadId));
	}
}
