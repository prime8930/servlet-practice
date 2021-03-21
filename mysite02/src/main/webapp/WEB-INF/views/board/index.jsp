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
				<form id="search_form" action="${pageContext.request.contextPath }/board" method="post">
					<input type="text" id="kwd" name="kwd">
					<input type="submit" value="찾기">
				</form>
						
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${list }" var="vo" varStatus="status">
							<tr>
								<td>${(pageVo.count-status.index)-((pageVo.currentPage-1) * pageVo.boardSize)}</td>
								<td>
									<span style="display:inline-block; width:200px; text-align:left; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
										<c:choose>
											<c:when test="${vo.tDelete eq true }">
												<span style="padding-left:${vo.depth *20 }px;">삭제된 게시글입니다.</span>
											</c:when>
											
											<c:otherwise>
												<a href="${pageContext.request.contextPath }/board?a=view&no=${vo.no }" style="padding-left:${vo.depth *20 }px;">
												<c:if test="${vo.depth gt 0 }">
														<img src="${pageContext.request.contextPath }/assets/images/reply.png" style="width:10px; height:10px;"/>
												</c:if>
												${vo.title }
												</a>
											</c:otherwise>
										</c:choose>
									</span>
								</td>
								<td>${vo.author }</td>
								<td>${vo.vCount }</td>
								<td>${vo.wDate }</td>
								<c:choose>
									<c:when test="${authUser.no eq vo.userNo and vo.tDelete eq false}">
										<td><a href="${pageContext.request.contextPath }/board?a=delete&no=${vo.no }" class="del">삭제</a></td>
									</c:when>
									
									<c:otherwise>
										<td></td>
									</c:otherwise>
								</c:choose>
							</tr>
					</c:forEach>
				</table>

				<div class="pager">
				<c:choose>
					<c:when test="${pageVo.count > 0}">
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

					</c:when>
					<c:otherwise>
						<ul><li><a href="${pageContext.request.contextPath }/board?pageNum=${pageVo.currentPage }">${pageVo.currentPage }</a></li></ul>
					</c:otherwise>
				</c:choose>
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
