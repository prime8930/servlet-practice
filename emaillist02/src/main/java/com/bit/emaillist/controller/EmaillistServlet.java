package com.bit.emaillist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.emaillist.dao.EmaillistDao;
import com.bit.emaillist.vo.EmaillistVo;
import com.bit.web.mvc.WebUtil;

public class EmaillistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("a");
		
		if("form".equals(action) ){
			WebUtil.forward("/WEB-INF/views/form.jsp", request, response);
		} else if("add".equals(action) ){
			WebUtil.forward("/WEB-INF/views/add.jsp", request, response);
		} else {
			List<EmaillistVo> list = new EmaillistDao().findAll();

			// forwarding = request dispatch = request extension
			request.setAttribute("list", list);
			WebUtil.forward("/WEB-INF/views/index.jsp", request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
