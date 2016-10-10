package com.cn.th.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.cn.th.bean.Book;

public interface BookDao {

	//模拟数据库操作
	public void add(Book book) throws DataAccessException;
	
	public void update(Book book)throws DataAccessException;
}
