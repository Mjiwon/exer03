<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<Map> list = (List<Map>)request.getAttribute("list");
	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
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
				<a href="<%=application.getContextPath()%>/issue/trend.do">이슈</a> 
         [ <b><%=session.getAttribute("id") %></b>,  로그온 |
         <a href="<%=application.getContextPath() %>/logout.do">로그오프</a> ]
			<hr />
		</div>
		<h2>【토론목록】</h2>
		<a href="<%=application.getContextPath()%>/issue/new.do">이슈올리기</a>
		<%for(int i =0; i<list.size();i++){ %>
		<div style="margin-right: 10%; margin-left: 10%; text-align: left">
			<div style="margin-bottom: 15px;" 
					onmouseenter="highlight(this, true);" onmouseleave="highlight(this, false)">
				<p style="text-align: right; color: gray; font-size: small;" >
					생활 / 1,211 의견 / <%=sdf.format(list.get(i).get("REGDATE")) %> 
				</p>
				<p>
					<a href="<%=application.getContextPath() %>/issue/detail.do?no=<%=list.get(i).get("NO") %>"> <b>ISSUE.</b> <%= list.get(i).get("CONTENT") %></a>
				</p>
			</div>
		</div>
	<%} %>
		<script>
			var highlight = function(t, e){
				if(e)
					t.style.background ="Azure";
				else
					t.style.background ="white";
			}
		
		
		</script>
	</div>
</body>
</html>