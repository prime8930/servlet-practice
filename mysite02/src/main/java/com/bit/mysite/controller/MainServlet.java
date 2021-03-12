package com.bit.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.web.mvc.WebUtil;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int visitCount = 0;
		
		// read cookie
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				
				if("visitCount".equals(cookie.getName())) {
					visitCount = Integer.parseInt(cookie.getValue());
				}
				
			}
		}
		
		System.out.println(visitCount);
		
		
		// write cookie
		visitCount++;
		Cookie cookie = new Cookie("visitCount", String.valueOf(visitCount));
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(24*60*60); // 1-day
		
		response.addCookie(cookie);
		
		WebUtil.forward("WEB-INF/views/main/index.jsp", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
