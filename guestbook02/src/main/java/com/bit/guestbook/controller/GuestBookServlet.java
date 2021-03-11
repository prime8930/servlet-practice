package com.bit.guestbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.guestbook.dao.GuestBookDao;
import com.bit.guestbook.vo.GuestBookVo;
import com.bit.web.mvc.WebUtil;


public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("a");
		
		if("deleteform".equals(action) ){
			
			WebUtil.forward("/WEB-INF/views/deleteform.jsp", request, response);
			
		} else if("add".equals(action) ){
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String contents = request.getParameter("contents");

			if( !(name.equals("") || password.equals("") || contents.equals("")) ) {
				
				GuestBookVo vo = new GuestBookVo();
				
				vo.setName(name);
				vo.setPassword(password);
				vo.setContents(contents);
				
				new GuestBookDao().insert(vo);
			}
			
			WebUtil.redirect(request.getContextPath() +"/gb", request, response);
			
		} else if("delete".equals(action) ){
			
			String no = request.getParameter("no");
			String password = request.getParameter("password");
			
			new GuestBookDao().delete(Long.parseLong(no), password);
			
			WebUtil.redirect(request.getContextPath() +"/gb", request, response);
			
		} else {
			
			List<GuestBookVo> list = new GuestBookDao().findAll();

			// forwarding = request dispatch = request extension
			request.setAttribute("list", list);
			WebUtil.forward("/WEB-INF/views/index.jsp", request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
