package com.unbelievable.uetsupport.dao;

import java.util.List;

import com.unbelievable.uetsupport.models.Thread;

public interface ThreadDao {
	
	Long save(Thread thread);
	
	void update(Thread thread);
	
	Thread threadDetail(Long threadId);
	
	List<Thread> listThreadsByDate();
	
	List<Thread> listThreadsByHotest();
	
	void delete(Thread thread);

}
