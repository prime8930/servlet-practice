<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>타이틀 크기</h1>
	<h1>타이틀1</h1>
	<h2>타이틀2</h2>
	<h3>타이틀3</h3>
	<h4>타이틀4</h4>
	<h5>타이틀5</h5>
	<h6>타이틀6</h6>
	
	<br><br>
	
	<h1>테이블(3*3)</h1>
	<table border="1" cellspacing="0" cellpadding="10">
		<tr>
			<th>글 번호</th>
			<th>글 제목</th>
			<th>글 작성자</th>
		</tr>
		<tr>
			<th>1</th>
			<th>가나다</th>
			<th>나고독</th>
		</tr>
		<tr>
			<th>2</th>
			<th>라마바</th>
			<th>홍길동</th>
		</tr>
	</table>
	
	<br><br>
	절대경로<img src='/helloweb/images/search.png' style="width:100px"/><br>
	상대경로<img src='images/search.png' style="width:100px"/>
	
	<br><br>
	<h1>하이퍼링크</h1>
	<a href="form.jsp">폼으로가기</a>
	<a href="index.jsp">메인으로 가기</a>
	
	<br><br>
	<h1>단락 나누기</h1>
	<p>
		웹 표준은 월드 와이드 웹의 측면을 서술하고 정의하는 공식 표준이나 다른 기술 규격을 가리키는 
		일반적인 용어이다.<br/> 
		최근에 이 용어는 웹 사이트를 작성하는 데 중요도가 높아지고 있으며 웹 디자인, 개발과 관계가 있다.
	</p>
	
	<br><br>
	<h1>순서 없는 리스트</h1>
		<ul>
			<li>aaaaa</li>
			<li>bbbbbb</li>
			<li>cccccc</li>
		</ul>
	<br><br>
	<h1>순서 있는 리스트</h1>
		<ol>
			<li>aaaaa</li>
			<li>bbbbbb</li>
			<li>cccccc</li>
		</ol>
	
	
</body>
</html>