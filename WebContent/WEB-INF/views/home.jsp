<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="models.*" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MVC</title>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/css/style.css" />
</head>
<body>
   <div align="center">
      <h1># MVC</h1>
      <div align="right" style="margin-right: 10%; margin-left: 10%; font-size: small;">
      	<a href="${pageContext.servletContext.contextPath }/issue/trend.do">이슈</a> 
         [ <b>${sessionScope.id }</b>,  로그온 |
         <a href="${pageContext.servletContext.contextPath }/logout.do">로그오프</a> ]
         <hr/>
      </div>
      <div style="margin-right: 10%; margin-left: 10%;">
         <form action="${pageContext.servletContext.contextPath }/search.do">
            <input type="text" style="width:98%;" placeholder="search keyword"/>
         </form>
      </div>
      <div style="margin-right: 10%; margin-left: 10%;" align="left">
         <h3>최근 등록된 새로운 이슈 !</h3>
         <ul>
         <c:choose>
         	<c:when test="${!empty sessionScope.someRecent }">
         		<c:forEach var="i" begin="0" end="${sessionScop.someRecentEnd }" step="1">
         			<li><a href="${pageContext.servletContext.contextPath }/issue/detail.do?no=${someRecent[i].NO}"> ${someRecent[i].REP }</a> </li>
         		</c:forEach>
         	</c:when>
         	<c:otherwise>
	        	 <li>24 시간 이내 등록된 이슈가 없습니다</li>     	
         	</c:otherwise>
         </c:choose>
         </ul>
      </div>
     <div style="margin-right: 10%; margin-left: 10%;" align="left">
        <h3>가장 HOT한 이슈!</h3>
         <ul>
         	<c:choose>
         		<c:when test="${!empty sessionScope.oneHot }">
         			<c:forEach var="i" begin="0" end="${oneHotEnd }" step="1">
         				<li><a href="${pageContext.servletContext.contextPath }/issue/detail.do?no=${oneHot[i].INO}">${oneHot[i].REP}</a></li>
         			</c:forEach>
         		</c:when>
         		<c:otherwise>         			
         			<li>이슈가 없습니다.</li>	
         		</c:otherwise>
         	</c:choose>  
         </ul>
      </div>
      <div style="margin-right: 10%; margin-left: 10%;" align="left">
         <h3>회원님이 작성하신 이슈</h3>
         <ul>
        	 <c:choose>
         		<c:when test="${!empty sessionScope.getSomeByTalker }">
         			<c:forEach var="i" begin="0" end="${getSomeByTalkerEnd }" step="1">
         				<li><a href="${pageContext.servletContext.contextPath }/issue/detail.do?no=${getSomeByTalker[i].INO}">${getSomeByTalker[i].REP}</a>
         				<br> <small style="color: orange;"> MYCOMENT : ${getSomeByTalker[i].MENT }</small>
         				</li>
         			</c:forEach>
         		</c:when>
         		<c:otherwise>         			
         			<li>이슈가 없습니다.</li>	
         		</c:otherwise>
         	</c:choose>  
         </ul>
      </div>
      
   </div>
   
</body>
</html>