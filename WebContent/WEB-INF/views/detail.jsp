<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Map issue = (Map)request.getAttribute("issue");
	String cont = (String)issue.get("CONTENT");
	int r = cont.indexOf("\n");
	String title ="";
	if(r>0){
		cont.substring(0, cont.indexOf("\n"));
	}else{
		title=cont;
	}
	System.out.println("제목은 "+title);
	String agree = (String)issue.get("AGREE");
	String disagree = (String)issue.get("DISAGREE");
	Number no = (Number)issue.get("NO");
	
	session.setAttribute("ino", no);
	
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
		<h2>【토론배틀】</h2>
		<small style="font-style: italic;">찬성이냐, 반대냐 그것이 문제로다!</small>
		<div style="margin-right: 10%; margin-left: 10%; text-align: left;">
			<h3 id="nos">#<%=no %>. <%=title %></h3>
			<p>
				<%=cont %>
			</p>
		</div>
		<div style="margin-right: 10%; margin-left: 10%; text-align: left; margin-top: 	55px; font-size: small;">
			<p style="color: blue">
				<b>YES</b> <%=agree %> <span>221</span> 명 
			</p>
			<p style="color: red">
				<b>NO</b> <%=disagree %> <span>721</span> 명 
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
			<input type="text" style="width: 80%" onchange="coment();" id="comen"/>
			</p>
		</div>
		
		<div style="margin-right: 10%; margin-left: 10%; text-align: left; margin-top: 	35px;">
			<p>
				<b>〔전체의견 / <span>942건</span>〕</b><br/>
			</p>
			<ul id="coments" style="list-style: none; font-size: smaller;">
				<li><b style="color:blue">YES</b> 우리나라의 소극적 안락사 정도는 필요하다고 생각한다</li>
				<li><b style="color:blue">YES</b> 삶이 죽음보다 더 큰 고통인 사람들이 많습니다</li>
				<li><b style="color:red">NO</b> 인위적으로 사람을 죽이는 일이 허용되야 합니까?</li>
			</ul>
		</div>
	</div>
	<script>
		var coment = function(){
			var c =  document.getElementById("comen").value;
			var s = document.getElementById("choice").value;
			console.log(c+" / "+s);
			var req = new XMLHttpRequest();
			req.open("post","detail.do?choice="+s+"&coment="+c,true);
			req.onreadystatechange=function(){
				var x = document.getElementById("coments");
				if(this.readyState==4){
					if(this.responseText.trime() == "true"){
						console.log(this.responseText);
						window.alert("등록되었습니다.\n새로고침을 눌러주세요.");
						document.getElementById("comen").innerHTML = "";
					}else{
						window.alert("등록실패하셨습니다.\n다시 시도해주세요");
					}
				//	var obj = JSON.parse(this.responseText);
				//	console.log(obj);
				
				}
			};
			req.send();
		};
	</script>
</body>
</html>