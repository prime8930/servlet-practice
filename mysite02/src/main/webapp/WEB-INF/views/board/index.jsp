<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<!-- 팝업창 띄우기
				<a href="" onclick="window.open(this.href, '_blank', 'width=400px,height=200px,toolbars=no,scrollbars=no'); return false;">완전 간단하게 팝업 띄우기!!</a> 
				-->
						
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="count" value="${fn:length(list) }" />
					<c:forEach items="${list }" var="vo" varStatus="status">
							<tr>
								<td>${count-status.index}</td>
								<td>
									<a href="${pageContext.request.contextPath }/board?a=view&no=${vo.no }" style="padding-left:${vo.depth *20 }px;">
										<c:if test="${vo.depth gt 0 }">
												<img src="${pageContext.request.contextPath }/assets/images/reply.png" style="width:10px; height:10px;"/>
										</c:if>
										${vo.title }
									</a>
								</td>
								<td>${vo.author }</td>
								<td>${vo.vCount }</td>
								<td>${vo.wDate }</td>
								<c:choose>
									<c:when test="${authUser.no eq vo.userNo }">
										<td><a href="${pageContext.request.contextPath }/board?a=delete&no=${vo.no }" class="del">삭제</a></td>
									</c:when>
									
									<c:otherwise>
										<td></td>
									</c:otherwise>
								</c:choose>
							</tr>
					</c:forEach>
				</table>
						
				<!-- pager 추가 -->
				<div class="pager">
					<c:set var="pageVo" value="${pageVo }"/>
					<c:if test="${pageVo.count > 0}">
						
						<ul>
							<c:if test="${pageVo.currentPage > pageVo.pageSize}">
								<li><a href="${pageContext.request.contextPath }/board?pageNum=${pageVo.prevPageNum}">◀</a></li>
							</c:if>
							
								<c:choose>
									<c:when test="${pageVo.endNum < endPageNum }">
										<c:forEach var="i" begin="${pageVo.startNum }" end="${pageVo.endNum}">
											<li><a href="${pageContext.request.contextPath }/board?pageNum=${i }">${i}</a></li>
										</c:forEach>
									</c:when>
									
									<c:otherwise>
										<c:forEach var="i" begin="${pageVo.startNum }" end="${endPageNum}">
											<li><a href="${pageContext.request.contextPath }/board?pageNum=${i }">${i}</a></li>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							
							<c:if test="${pageVo.endNum < endPageNum }">
								<li><a href="${pageContext.request.contextPath }/board?pageNum=${pageVo.nextPageNum}">▶</a></li>
							</c:if>
						</ul>
					</c:if>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
					<c:if test="${!empty authUser }">
						<a href="${pageContext.request.contextPath }/board?a=writeform" id="new-book">글쓰기</a>
					</c:if>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>