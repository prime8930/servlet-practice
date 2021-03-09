<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/helloweb/join" method="post">
		이메일: <input type="text" name="email" value=""/>
		<br><br>

		비밀번호: <input type="password" name="password" value=""/>
		<br><br>

		출생년도:
		<select name="birthYear">
			<option>출생년도</option>
			<option value="1994">1994년</option>
			<option value="1995">1995년</option>
			<option value="1996">1996년</option>
		</select>
		<br><br>
		
		성별:
		여<input type="radio" name="gender" value="female"/>
		남<input type="radio" name="gender" value="male" checked="checked"/>
		<br><br>

		취미:
		코딩<input type="checkbox" name="hobbies" value="coding">
		독서<input type="checkbox" name="hobbies" value="reading">
		요리<input type="checkbox" name="hobbies" value="cooking">
		<br><br>
		
		자기소개:
		<textarea name="desc"></textarea>		
		<br><br>
		
		<input type="submit" value="sign up" />
	</form>
</body>
</html>