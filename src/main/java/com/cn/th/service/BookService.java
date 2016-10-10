package com.cn.th.service;

import java.rmi.ServerException;

import com.cn.th.bean.Book;
import com.cn.th.dao.BookDao;


public interface BookService {

	public BookDao getBookDao()throws ServerException;
	
	public void setBookDao(BookDao bookDao)throws ServerException;
	
	public void add(Book book)throws ServerException;
	
	public void update(Book book)throws ServerException;
}


