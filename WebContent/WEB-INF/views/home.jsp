<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MVC</title>
<link rel="stylesheet" href="<%=application.getContextPath()%>/css/style.css" />
</head>
<body>
	<div style="display: flex;">
		<form action="<%=application.getContextPath() %>/search.do">
			<b>SEARCH</b> 
			<input type="text" style="width:100%;" placeholder="search"/>
		</form>
	</div>
</body>
</html>