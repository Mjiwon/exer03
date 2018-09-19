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
	<%--
		mvc pattern 의 application 에서는 
		action 이나 link , ajax 목적지를 controller 매핑경로로 설정
	 --%>
	<div align="center">
		<h1>〔회원가입〕</h1>
		<div style="margin-left: 10%; margin-right: 10%; text-align: left;">

			<h3 style="color: blue;">Step1.당신의 계정을 생성하세요.</h3>
			<h3 style="color: silver;">Step2.당신의 관심사항을 설정하세요.</h3>
			<form action="join.do" method="post" autocomplete="off">
				<p>
					<b>아이디(*)</b><br /> <input type="text" name="joinid" value="<%=request.getAttribute("id")%>"
						placeholder="account id" style="width: 99%;" onkeyup="checkid(this)"/>
					<small><span id="newid"></span></small>
				</p>
				<p>
					<b>비밀번호(*)</b><br /> <input type="password" name="joinpass"  value="<%=request.getAttribute("pass")%>"
						placeholder="account pass" style="width: 99%;"  onchange="checkpass(this)"/>
					<small><span id="newpass1"></span></small>
				</p>
				<p>
					<b>비밀번호 재확인(*)</b><br /> <input type="password" name="joinpass2" 
						placeholder="confirm pass" style="width: 99%;" onkeyup="passcheck(this)"/>
					<small><span id="newpass2"></span></small>
				</p>
				<p>
					<b>이름(*)</b><br /> <input type="text" name="joinname"  value="<%=request.getAttribute("name")%>"
						placeholder="user name" style="width: 99%;" onchange="checkname(this)"/>
					<small><span id="newname"></span></small>
				</p>
				<p>
					<b>성별(*)</b><br /> <select name="joingender" style="width: 100%;">
						<option value="M">남성</option>
						<option value="F">여성</option>
					</select>
				</p>
				<p>
					<button ="submit" id="sbt" style="width: 100%; ">가입 신청</button>
				</p>
			</form>
			<script>
			var ar = [false,false,false,false];
			//var ar = [true,true,true,true];
			var vaild = function() {
				console.log(ar);
				if(ar.includes(false)) {
					document.getElementById("sbt").disabled=true;
				}else {
					document.getElementById("sbt").disabled=false;
				}
			};
						
			var checkid =function(a){
				var c = a.value;
				console.log(c);
				var check = /^[a-zA-Z]\w{3,11}$/.test(c);
				if(check){
					var req = new XMLHttpRequest();
		            req.open("get","signup_check.do?id="+c); // 중복아이디검사 ajax로넘기는거 나중에하기
		            req.onreadystatechange =function(){
						var x =document.getElementById("newid");	
		            	if(this.readyState==4){
		            		console.log("여긴오냐?"+this.responseText)
		            		if(this.responseText =="true"){
								x.innerHTML = "사용 가능한 아이디입니다.";
								x.style.color = "blue";
								ar[0] = true;
		            		}else{
		            			x.innerHTML = "이미 사용 중인 아이디 입니다.";
		            			x.style.color = "red";
		            		}
		            	}	
		            };	
		            req.send();
		            vaild();
				}else{
					var x =document.getElementById("newid");
					x.innerHTML = "영문,숫자 4~12자 내외";
					x.style.color = "red";
				}
				vaild();
			};
		/* 	
			var 
			var req = new XMLHttpRequest();
            req.open("get","signup_check.do?"+id); // 중복아이디검사 ajax로넘기는거 나중에하기 */
			var checkname=function(a){
				var c = a.value;
				var check = /^[가-힇]{1,}$/.test(c);
				ar[1] = check;
				if(check){
					var x =document.getElementById("newname");					
					x.innerHTML = "일치합니다";
					x.style.color = "blue";
				}else{
					var x =document.getElementById("newname");
					x.innerHTML = "공백 없이 한글만 설정";
					x.style.color = "red";
				}
				vaild();
			};
			
			var checkpass=function(a){
				var c = a.value;
				var check = /^\w{4,12}$/.test(c);
				ar[2] = check;
				if(check){
					var x =document.getElementById("newpass1");
					x.innerHTML = "일치합니다";
					x.style.color = "blue";
				}else{
					var x =document.getElementById("newpass1");
					x.innerHTML = "4~12자";
					x.style.color = "red";
				}
				vaild();
			};
			
			var passcheck = function(a){
				var p1 = document.getElementsByTagName("input")[1].value;
				var p2 = a.value;
				console.log(p2);
				if(p2.length>0){
					if(p1==p2){
						var x =document.getElementById("newpass2");
						x.innerHTML = "일치합니다";
						x.style.color = "blue";
						ar[3] = true;
					}else{
						var x =document.getElementById("newpass2");
						x.innerHTML = "비밀번호확인해주세요";
						x.style.color = "red";
					}
				}
				vaild();
			};
				
			
			</script>
		</div>
	</div>
</body>
</html>