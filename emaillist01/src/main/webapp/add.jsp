<%@page import="com.bit.emaillist.dao.EmaillistDao"%>
<%@page import="com.bit.emaillist.vo.EmaillistVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	String email = request.getParameter("email");
	
	EmaillistVo vo = new EmaillistVo();
	
	vo.setFirstName(firstName);
	vo.setLastName(lastName);
	vo.setEmail(email);
	
	new EmaillistDao().insert(vo);
	
	// insert 후 페이지에 머무르지 않고 바로 홈으로 리다이렉트된다.
	response.sendRedirect("/emaillist01");
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