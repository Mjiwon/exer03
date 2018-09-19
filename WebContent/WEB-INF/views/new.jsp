<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<h2>【이슈등록】</h2>
		<div style="margin-right: 10%; margin-left: 10%; text-align: left">
			<p id="msg"
			style="font-size: small; color: red; ">
			<%if(session.getAttribute("fail")!=null) {%>
				게시글 작성을 실패하셨습니다.<b>［x］</b>
			<%} %>
		</p>
			<form action="<%=application.getContextPath()%>/issue/new.do" method="post">
				<p>
					<b>〔분류〕</b><br/>
					<select  name="cate" style="width: 100%">
						<option value="it">IT/컴퓨터</option>
						<option value="game">게임</option>
						<option value="politic">정치/경제</option>
						<option value="sports">운동</option>
						<option value="food">요리/음식</option>
						<option value="culture">영화/문화</option>
						<option value="life">생활</option>
						<option value="etc">기타</option>
					</select>
				</p>
				<p>
					<b>〔토론주제〕</b><br/>
					<small>※첫줄에 작성한 내용이 이슈목록에 표시됩니다.</small><br/>
					<textarea name="content" style="width: 99%; height:100px; resize: none;" 
							placeholder="type subject"></textarea> 
				</p>
				<p>
					<b>〔찬성쪽 의견〕</b><br/>
					<input type="text" style="width: 99%" placeholder="set agree's menu" name="agree" />
				</p>
				<p>
					<b>〔반대쪽 의견〕</b><br/>
					<input type="text" style="width: 99%" placeholder="set disagree's menu" name="disagree"/>
				</p>
				<p>
					<button type="submit" style="width: 100%">이슈 올리기</button>
				</p>
			</form>
		</div>
	</div>
</body>
</html>