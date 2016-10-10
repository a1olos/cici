package com.cn.th.service.impl;

import java.rmi.ServerException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.th.bean.Book;
import com.cn.th.bean.BuyerInfo;
import com.cn.th.dao.BookDao;
import com.cn.th.dao.BuyerInfoMapper;
import com.cn.th.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Resource
	private BookDao bookDao;
	
	@Resource
	private BuyerInfoMapper buyerInfoMapper;
	

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	
	public void add(Book book){
//		bookDao.add(book);
		
		BuyerInfo record = new BuyerInfo();
		record.setMobile(book.getName());
		record.setUserid(book.getId());
		buyerInfoMapper.insert(record);
	}
	
	public void update(Book book){
		bookDao.update(book);
	}


	@Override
	public BookDao getBookDao() throws ServerException {
		return bookDao;
	}
	
}
