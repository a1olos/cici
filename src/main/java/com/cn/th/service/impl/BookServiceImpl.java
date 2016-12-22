package com.cn.th.service.impl;

import java.rmi.ServerException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cn.th.bean.Book;
import com.cn.th.bean.BuyerInfo;
import com.cn.th.dao.BookDao;
import com.cn.th.dao.BuyerInfoMapper;
import com.cn.th.mq.producer.queue.QueueSender;
import com.cn.th.mq.producer.topic.TopicSender;
import com.cn.th.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Resource
	private BookDao bookDao;
	
	@Resource
	private BuyerInfoMapper buyerInfoMapper;
	
	@Resource 
	QueueSender queueSender;
	
	
	@Resource 
	TopicSender topicSender;
	
	
	@Transactional(propagation =Propagation.REQUIRED,rollbackFor=Exception.class)
	public void add(Book book) throws ServerException{
//		bookDao.add(book);
		
		queueSender.send("测试", "插入成功一条信息，请查收");
		topicSender.send("测试topic", "插入一条信息，请查收");
		
		for(int i = 0 ; i<10 ; i++){
			queueSender.send("test.queue", "插入成功一条信息，请查收");
			topicSender.send("test.topic", "插入一条信息，请查收");
		}
		
		BuyerInfo record = new BuyerInfo();
		record.setMobile(book.getName());
		record.setUserid(book.getId());
//		buyerInfoMapper.insert(record);
		
	}
	
	public void update(Book book){
		bookDao.update(book);
	}
	
}
