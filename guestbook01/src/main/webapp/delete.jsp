<%@page import="com.bit.guestbook.dao.GuestBookDao"%>
<%@page import="com.bit.guestbook.vo.GuestBookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String no = request.getParameter("no");
	String password = request.getParameter("password");
	
	new GuestBookDao().delete(Long.parseLong(no), password);
	
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