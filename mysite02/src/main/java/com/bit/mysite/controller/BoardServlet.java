package com.bit.mysite.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.mysite.dao.BoardDao;
import com.bit.mysite.vo.BoardVo;
import com.bit.mysite.vo.PagingVo;
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
			
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			
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
			
			Long userNo = authUser.getNo();
			
			if( !(title.equals("") || contents.equals(""))) {
				
				BoardVo vo = new BoardVo();
				
				vo.setTitle(title);
				vo.setContents(contents);
				
				new BoardDao().insert(vo, userNo);
				
			}
			
			WebUtil.redirect(request.getContextPath() +"/board", request, response);
			
		} else if("replyform".equals(action)) {
			
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
			
			WebUtil.forward("/WEB-INF/views/board/reply.jsp", request, response);
			
		} else if("reply".equals(action)) {
			
			String no = request.getParameter("no");
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			
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
			
			Long userNo = authUser.getNo();
			
			if( !(title.equals("") || contents.equals(""))) {
				
				BoardVo vo = new BoardVo();
				
				vo.setNo(Long.parseLong(no));
				vo.setTitle(title);
				vo.setContents(contents);
				
				new BoardDao().replyInsert(vo, userNo);
				
			}
			
			WebUtil.redirect(request.getContextPath() +"/board", request, response);
			
		} else if("view".equals(action)) {
			
			String no = request.getParameter("no");
			
			BoardVo boardVo = new BoardDao().findByNo(Long.parseLong(no));
			
			if(boardVo.istDelete() == true) {
				WebUtil.redirect(request.getContextPath()+"/board", request, response);
				return;
			}
			
			new BoardDao().upCount(Long.parseLong(no));
			
			request.setAttribute("boardVo", boardVo);
			WebUtil.forward("/WEB-INF/views/board/view.jsp", request, response);
			
		} else if("modifyform".equals(action)) {
			
			String no = request.getParameter("no");
			
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
			
			
			BoardVo boardVo = new BoardDao().findByNo(Long.parseLong(no));
			
			request.setAttribute("boardVo", boardVo);
			
			WebUtil.forward("/WEB-INF/views/board/modify.jsp", request, response);
			
		} else if("modify".equals(action)){
			
			String no = request.getParameter("no");
			String userNo = request.getParameter("userNo");
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			
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
			
			Long authNo = authUser.getNo();
			
			if( !(title.equals("") || contents.equals("") || authNo != Long.parseLong(userNo))) {
				
				BoardVo vo = new BoardVo();
				
				vo.setNo(Long.parseLong(no));
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setUserNo(Long.parseLong(userNo));
				
				new BoardDao().update(vo);
				
			}
			
			WebUtil.redirect(request.getContextPath() +"/board", request, response);
			
		} else if("delete".equals(action)) {
			String no = request.getParameter("no");
			
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
			
			new BoardDao().delete(Long.parseLong(no));
//			new BoardDao().clearReply(Long.parseLong(no));
			
			WebUtil.redirect(request.getContextPath() +"/board", request, response);
		} else {
			
			String pageNum = request.getParameter("pageNum");
			String kwd = request.getParameter("kwd");
			
			if (pageNum == null) {
				pageNum = "1";
			}

			int cPageNum = Integer.parseInt(pageNum);

			PagingVo pageVo = new PagingVo(cPageNum);

			List<BoardVo> list = new ArrayList<>();

			if(kwd == null) {
				list = new BoardDao().findAll(cPageNum);
			} else {
				list = new BoardDao().findKwd(cPageNum, kwd);
			}

			int endPageNum = (int) Math.ceil(  (double) list.size() / pageVo.getBoardSize());

			if(cPageNum > endPageNum) {
				WebUtil.redirect(request.getContextPath()+"/board", request, response);
				return;
			}
			
			request.setAttribute("list", list);
			request.setAttribute("pageVo", pageVo);
			request.setAttribute("endPageNum", endPageNum);
			
			WebUtil.forward("/WEB-INF/views/board/index.jsp", request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
