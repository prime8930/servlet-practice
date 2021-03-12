package com.bit.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			
			HttpSession session = request.getSession(true); // 없으면 생성, false - 없으면 null 반환
			session.setAttribute("authUser", authUser);
			
			// response
			WebUtil.redirect(request.getContextPath(), request, response);
			
		} else if("logout".equals(action)) {
			
			HttpSession session = request.getSession();
			
			// logout process
			if( session != null && session.getAttribute("authUser") != null) {
				session.removeAttribute("authUser");
				session.invalidate();
			}
			
			WebUtil.redirect(request.getContextPath(), request, response);
			
		
		} else if( "updateform".equals(action) ) {
			
			// Access Control(접근 제어)
			HttpSession session = request.getSession();
			
			if(session == null) {
				WebUtil.redirect(request.getContextPath(), request, response);
				return;
			}
			
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			
			if(authUser == null) {
				WebUtil.redirect(request.getContextPath(), request, response);
				return;
			}
			
			Long no = authUser.getNo();
			UserVo userVo = new UserDao().findByNo(no);
			
			request.setAttribute("userVo", userVo);
			WebUtil.forward("/WEB-INF/views/user/updateform.jsp", request, response);
			
			
			
			
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
