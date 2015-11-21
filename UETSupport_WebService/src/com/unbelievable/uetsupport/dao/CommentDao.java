package com.unbelievable.uetsupport.dao;

import java.util.List;

import com.unbelievable.uetsupport.models.Comment;

public interface CommentDao {
	
	Long save(Comment comment);
	
	void update(Comment comment);
	
	Comment findCommentById(Long commentId);
	
	List<Comment> listAllCommentByThread(Long threadId);
	
	void delete(Comment comment);
}
