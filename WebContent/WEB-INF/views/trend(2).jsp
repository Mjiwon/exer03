<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<Map> list = (List<Map>)request.getAttribute("list");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MVC</title>
<link rel="stylesheet"
	href="<%=application.getContextPath()%>/css/style.css" />
</head>
<body>
	<div align="center">
		<h1># MVC</h1>
		<div align="right"
			style="margin-right: 10%; margin-left: 10%; font-size: small;">
			<b><%=session.getAttribute("id") %></b>, 로그온 | <a
				href="<%=application.getContextPath()%>/logout.do">로그오프</a>
			<hr />
		</div>
		<h2>【토론배틀】</h2>
			<a href="<%=application.getContextPath()%>/issue/new.do">이슈올리기</a>
		<%for(int i = 0; i<list.size();i++){ %>
		<div style="margin-right: 10%; margin-left: 10%; text-align: left">
			<div style="background-color: #EAEAEA; padding: 7px; margin-bottom: 5px;">
				<p style="text-align: right; color: gray; font-size: small;" >
					생활 / 1,211 의견 / 2018.09.18  
				</p>
				<p>
					<a><b>ISSUE.</b><%= list.get(i).get("CONTENT") %></a>
				</p>
		</div>
	</div>
	<%} %>
</body>
</html>