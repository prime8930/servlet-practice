<%@page import="com.bit.guestbook.vo.GuestBookVo"%>
<%@page import="com.bit.guestbook.dao.GuestBookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
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
	
	// insert 후 페이지에 머무르지 않고 바로 홈으로 리다이렉트된다.
	response.sendRedirect("/guestbook01");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
</body>
</html>