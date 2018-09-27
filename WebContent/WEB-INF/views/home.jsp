<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="models.*" %>
<%
	String id = (String)session.getAttribute("id");

	IssueDao is = new IssueDao();
 	List<Map> list = is.yesterIssue();
	
 	OpinionDao od = new OpinionDao();
 	List<Map> oList = od.sumOpinions();
	
 	List<Map> mlist = od.myOpinios(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MVC</title>
<link rel="stylesheet" href="<%=application.getContextPath()%>/css/style.css" />
</head>
<body>
   <div align="center">
      <h1># MVC</h1>
      <div align="right" style="margin-right: 10%; margin-left: 10%; font-size: small;">
      	<a href="<%=application.getContextPath()%>/issue/trend.do">이슈</a> 
         [ <b><%=session.getAttribute("id") %></b>,  로그온 |
         <a href="<%=application.getContextPath() %>/logout.do">로그오프</a> ]
         <hr/>
      </div>
      <div style="margin-right: 10%; margin-left: 10%;">
         <form action="<%=application.getContextPath() %>/search.do">
            <input type="text" style="width:98%;" placeholder="search keyword"/>
         </form>
      </div>
      <div style="margin-right: 10%; margin-left: 10%;" align="left">
         <h3>최근 등록된 새로운 이슈 !</h3>
         <ul>
          <%if(list.size()==0){
        	 %>
        	 <li>24 시간 이내 등록된 이슈가 없습니다</li>
         <%}else{ %>
	         <%for(int i =0; i<list.size();i++){ 
	        	 Map li = list.get(i);
	        	 String cont = (String)li.get("CONTENT");
	        	 String title="";
	        	 if(cont.indexOf("\n")!=-1){
	        		title = cont.substring(0,cont.indexOf("\n"));	
	        	 }else{
	        		title = cont ;
	        	 }  %>
	        	 <li><a href="<%=application.getContextPath() %>/issue/detail.do?no=<%=li.get("NO") %>"> <b>#ISSUE.</b> <%=title %></a></li>
	         <%} %>
        <% }%>
         </ul>
      </div>
      <div style="margin-right: 10%; margin-left: 10%;" align="left">
         <h3>가장 많은 의견이 달린 이슈!</h3>
         <ul>
          <%if(oList.size()==0){
        	 %>
        	 <li>이슈가 없습니다.</li>
         <%}else{ %>
	         <%for(int i =0; i<oList.size();i++){ 
	        	 Map li = oList.get(i);
	        	 String cont = (String)li.get("CONTENT");
	        	 String title="";
	        	 if(cont.indexOf("\n")!=-1){
	        		title = cont.substring(0,cont.indexOf("\n"));	
	        	 }else{
	        		title = cont ;
	        	 }  %>
	        	<li> <a href="<%=application.getContextPath() %>/issue/detail.do?no=<%=li.get("NO") %>"> <b>#ISSUE.</b> <%=title %></a></li>
	         <%} %>
        <% }%>
         </ul>
      </div>
      <div style="margin-right: 10%; margin-left: 10%;" align="left">
         <h3>회원님이 작성하신 이슈</h3>
         <ul>
          <%if(mlist.size()==0){
        	 %>
        	 <li>아직 남기신 댓글이 없습니다.</li>
         <%}else{ %>
	         <%for(int i =0; i<mlist.size();i++){ 
	        	 Map li = mlist.get(i);
	        	 String cont = (String)li.get("CONTENT");
	        	 String title="";
	        	 if(cont.indexOf("\n")!=-1){
	        		title = cont.substring(0,cont.indexOf("\n"));	
	        	 }else{
	        		title = cont ;
	        	 }  %>
	        	<li> <a href="<%=application.getContextPath() %>/issue/detail.do?no=<%=li.get("NO") %>"> <b>#ISSUE.</b> <%=title %></a><br> <small style="color: orange;"> MYCOMENT : <%=li.get("MENT") %></small></li>
	         <%} %>
        <% }%>
         </ul>
      </div>
      
   </div>
   
</body>
</html>