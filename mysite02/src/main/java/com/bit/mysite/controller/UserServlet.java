package com.bit.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.mysite.dao.UserDao;
import com.bit.mysite.vo.UserVo;
import com.bit.web.mvc.WebUtil;


public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("a");
		
		if( "joinform".equals(action) ) {
			WebUtil.forward("/WEB-INF/views/user/joinform.jsp", request, response);
		} else if( "joinsuccess".equals(action) ) { 
			WebUtil.forward("/WEB-INF/views/user/joinsuccess.jsp", request, response);
		} else if( "loginform".equals(action) ) { 
			WebUtil.forward("/WEB-INF/views/user/loginform.jsp", request, response);
		} else if( "login".equals(action) ) { 
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			UserVo vo = new UserVo(email, password);
			
			UserVo authUser = new UserDao().findByMailAndPassword(vo);
			
			if(authUser == null) {
				request.setAttribute("authResult", "fail");
				WebUtil.forward("/WEB-INF/views/user/loginform.jsp", request, response);
				return;
			}
			
			// authentication
			
			
		} else if( "join".equals(action) ) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			UserVo userVo = new UserVo(name, email, password, gender);
			
			new UserDao().insert(userVo);
			
			WebUtil.redirect(request.getContextPath() + "/user?a=joinsuccess", request, response);
			
		} else {
			WebUtil.redirect(request.getContextPath(), request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		//
		
		doGet(request, response);
	}

}
