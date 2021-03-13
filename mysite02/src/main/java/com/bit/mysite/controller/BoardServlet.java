package com.bit.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.mysite.dao.BoardDao;
import com.bit.mysite.vo.BoardVo;
import com.bit.mysite.vo.UserVo;
import com.bit.web.mvc.WebUtil;


public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("a");
		
		if("writeform".equals(action)) {
			
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
			
			WebUtil.forward("/WEB-INF/views/board/write.jsp", request, response);
			
		} else if("write".equals(action)) {
			
		} else if("view".equals(action)) {
			String no = request.getParameter("no");
			
			BoardVo boardVo = new BoardDao().findNameByNo(Long.parseLong(no));
			
			request.setAttribute("boardVo", boardVo);
			WebUtil.forward("/WEB-INF/views/board/view.jsp", request, response);
		} else {
			List<BoardVo> list = new BoardDao().findAll();
			
			request.setAttribute("list", list);
			
			WebUtil.forward("/WEB-INF/views/board/index.jsp", request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
