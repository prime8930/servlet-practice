<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>take a value</h1>
	${iVal } <br>
	${lVal } <br>
	${fVal } <br>
	${bVal } <br>
	${sVal }
	
	<h1>take a object</h1>
	${vo.no } <br>
	${vo.name } <br>
	${obj } <br>
	
	<h1>arithmetic</h1>
	${3*10+5 } <br>
	${iVal+5 } <br>
	
	<h1>relation</h1>
	${iVal == 10 } <br>
	${iVal < 5 } <br>
	${empty obj } <br>
	${not empty obj } <br>
	
	<h1>logic</h1>
	${iVal == 10 && lVal < 10000 } <br>
	${iVal == 5 || lVal - 10 == 0 } <br>
	
	<h1>request parameter</h1>
	${param.a + 10 } <br>
	${param.email } <br>
	
	<h1>take a Map</h1>
	${map.ival } <br>
	${map.fval } <br>
	${map.sval } <br>
	${map.bval } <br>
	
</body>
</html>