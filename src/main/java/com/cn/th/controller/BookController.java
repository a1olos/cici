package com.cn.th.controller;

import java.rmi.ServerException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.th.bean.Book;
import com.cn.th.service.BookService;


@Controller
@RequestMapping("/book")
public class BookController {

	@Resource
	private BookService bookService;
	
	@RequestMapping("/add.do")
	public String add(Book book) throws ServerException{
		System.out.println("bookname:"+book.getName());
		System.out.println("author:"+book.getAuthor());
		bookService.add(book);
		return "common/success";
	}
	@RequestMapping("/update.do")
	public String update(HttpServletRequest request,Book book,HttpServletResponse response, Model model) throws ServerException {
		bookService.update(book);
		model.addAttribute("page", "应用2---");
		model.addAttribute("sessionID",request.getSession().getId());
		return "test";
	}
	
}
