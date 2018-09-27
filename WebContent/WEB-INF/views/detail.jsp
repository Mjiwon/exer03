<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Map issue = (Map)request.getAttribute("issue");
	String cont = (String)issue.get("CONTENT");
	int r = cont.indexOf("\n");
	String title ="";
	if(r!=-1){
		cont.substring(0, cont.indexOf("\n"));
	}else{
		title=cont;
	}
	String agree = (String)issue.get("AGREE");
	String disagree = (String)issue.get("DISAGREE");
	Number no = (Number)issue.get("NO");
	System.out.println("agree "+agree);
	
	session.setAttribute("ino", no);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MVC</title>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/css/style.css" />
</head>
<body>
	<div align="center">
		<h1># MVC</h1>
		<div align="right"
			style="margin-right: 10%; margin-left: 10%; font-size: small;">
			<a href="${pageContext.servletContext.contextPath }/issue/trend.do">이슈</a> 
         [ <b>${sessionScope.id }</b>,  로그온 |
         <a href="${pageContext.servletContext.contextPath }/logout.do">로그오프</a> ]
			<hr />
		</div>
		<h2>【토론배틀】</h2>
		<small style="font-style: italic;">찬성이냐, 반대냐 그것이 문제로다!</small>
		<div style="margin-right: 10%; margin-left: 10%; text-align: left;">
			<h3 id="nos">${issue.NO }. ${issue.REP }</h3>
			<p>
				<%=cont.replace("\n", "<br/>") %>
			</p>
		</div>
		<div style="margin-right: 10%; margin-left: 10%; text-align: left; margin-top: 	55px; font-size: small;">
			<p style="color: blue">
				<b>YES</b> ${issue.AGREE } [<span>${opinionCount[0].CNT}</span>] 명 
			</p>
			<p style="color: red">
				<b>NO</b> ${issue.DISAGREE} [<span>${opinionCount[1].CNT}</span>] 명 
			</p>
		</div>
		
		<div style="margin-right: 10%; margin-left: 10%; text-align: left; margin-top: 	55px;">
			<p>
			<b>〔의견남기기〕</b><br/>
			</p>
			<p>
			<select id="choice">
				<option value="1">YES</option>
				<option value="0">NO</option>
			</select>
			<input type="text" style="width: 80%" onchange="opinionAjax();" id="ment"/>
			</p>
<script>		
				var ino = <%=issue.get("NO")%>;
				var opinionAjax = function() {
					if(document.getElementById("ment").value.trim().length >0) {
						var xhr = new XMLHttpRequest();
						xhr.open("post","<%=application.getContextPath()%>/issue/opinion.do", true);
						xhr.onreadystatechange =function(){
							if(this.readyState==4) {
								var obj = JSON.parse(this.responseText);
								if(obj.rst) {
									// location.reload();
									document.getElementById("ajaxresult").innerHTML = "의견이 등록되었습니다."
									document.getElementById("ajaxresult").innerHTML += "즉시 변경을 원하면 새로고침을 눌러주세요.";
									document.getElementById("ment").value="";
									document.getElementById("choice").value=1;
								} else {
									document.getElementById("ajaxresult").innerHTML = "의견 등록과정에 장애가 발생하였습니다.<br/>"
								}
							}
						}
						var param = {
							"ment":document.getElementById("ment").value, 
							"choice" : document.getElementById("choice").value
							};
						var param =JSON.stringify(param);

						xhr.send(param);
					}
				};
			</script>
			<small id="ajaxresult"></small>
		</div>
		
		<div style="margin-right: 10%; margin-left: 10%; text-align: left; margin-top: 	35px;">
			<p>
				<b>〔전체의견 / <span><%=((List)request.getAttribute("opinions")).size() %></span>〕</b>
				<small id="time">10</small>초 후 갱신<br/>
			</p>
			<%
				List<Map> ops = (List<Map>)request.getAttribute("opinions");
			%>
			<ul style="list-style: none; font-size: smaller;" id="ments">
			<%-- 	<%for(int i=0; i<ops.size(); i++){ 
						Map e = ops.get(i);
					%> --%>
				<c:forEach var="i" items="${opinions }">
				<li>
				<c:choose>
					<c:when test="${i.CHOICE == '1'}">
						<b style="color:blue">YES</b>
					</c:when>
					<c:otherwise>
						<b style="color:red">NO</b>
					</c:otherwise>
					${i.MENT }
				</c:choose>
				</li>
				</c:forEach>
				<%-- <%if(((Number)e.get("CHOICE")).intValue() == 1) {%>
						<b style="color:blue">YES</b>
					<%}else { %>
						<b style="color:red">NO</b>
					<%} %>
					<%=e.get("MENT") %>
				</li>
				<%} %> --%>
			</ul>
			<script>
			var latestAjax = function(){
				var xhr = new XMLHttpRequest();
					xhr.open("get","<%=application.getContextPath()%>/issue/opinion.do?ino=<%=session.getAttribute("ino")%>", true);
					xhr.onreadystatechange =function(){
						if(this.readyState == 4){
							var html = "";
							var obj =  JSON.parse(this.responseText);
							// 받아온 객체 배열을 가지고 원래 찍어두던 형태의 HTML을 만들어서
							// 위영역에 innerHTML로 세팅
							for(var i = 0; i<obj.length;i++){
								 html +="<li>"
								 html +=obj[i].CHOICE == 1? ("<b style=\"color:blue\">YES </b>" +obj[i].MENT) : ("<b style=\"color:red\">NO </b>" +obj[i].MENT ) ;
								 html +="</li>";
							}
							document.getElementById("ments").innerHTML = html;					
							document.getElementById("ajaxresult").innerHTML = "";
							console.log(html);
						}
				}						
					xhr.send();
			};
			
			var time = 10;
			window.setInterval( function(){
				time --;
				document.getElementById("time").innerHTML = time;
				if(time==0){
					latestAjax();
					time=10;
				}
			},1000);	
			// *개인필기 :  whindow.setInterval(latestAjax, 10000) 10초에 한번씩 호출하는 것
			//					136~143은 1초에 한번씩 저방식은 10,9,8,7,6,5,4,3,2,1 을 알려줄 수 있다.
			</script>
		</div>
	</div>
	
</body>
</html>