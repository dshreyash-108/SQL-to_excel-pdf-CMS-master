<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<%	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma","no-cache");

	String user = (String)session.getAttribute("user");
	if(user!=null){	
		response.sendRedirect(request.getContextPath()+"/dashboard");
	}
%>
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			 <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
			<title>CMS</title>
			<link href="https://fonts.googleapis.com/css?family=Monoton|Open+Sans" rel="stylesheet">
			<link rel="stylesheet" href="assets/css/myStyle.css">
			
		</head>
		<body>
			<div class="container">
				<span class="logo">Content Management System</span>
				<div class="login_form">
					<form action="doLogin" method="post">
							<h3>Authorization Required!</h3>
							<input type="text" placeholder="User ID" name="uid" required><br>
							<input type="password" placeholder="Password" name="psw" required><br>
							<input type="submit" value="Login">
					</form>
				</div>
			</div>
			<%
				int flag = 0;
				try{
					flag = (int)session.getAttribute("flag");
					
					if(flag==1){
						%>
							<script>
							alert("Password Mismatch!");
							</script>
						<%
						}
					if(flag==2){
						%>
							<script>
							alert("No Account Found!");
							</script>
						<%
						}
				}
				catch(Exception e){	
				}
				finally{	
					flag=0;
				}
			%>
		</body>
	</html>