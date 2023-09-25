<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<%@ page import="com.cms.SQLHandler,java.sql.*" %>
<%	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma","no-cache");
	
	String user = (String)session.getAttribute("user");
	if(user==null){	
		response.sendRedirect(request.getContextPath()+"/welcome");
	}
		SQLHandler handler = new SQLHandler();
		String agencies = handler.getAgencies();
		%>
		<script type="text/javascript">
			var agency = '<%=handler.getAgencies()%>';
		</script>
		
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
			<title>DSW DL Module | Dashboard</title>
			<link href="https://fonts.googleapis.com/css?family=Monoton|Open+Sans" rel="stylesheet">
			<link rel="stylesheet" href="assets/css/stylize.css">
		</head>
		<body>
			<div class="container">
				<div class="wrapper">
					<div class="logo">
						Content Management System
					</div>
					<div class="controller">
						<p><strong>Hello <% out.print(user); %></strong></p>
						<a href="logout">Logout</a>
					</div>
					<div class="menu">
						<button onClick="goHome();">Home</button>
						<button onClick="showMIS();">Consolidated MIS</button>
						<button onClick="showAdnet();">Adnet Hits</button>
						<button onClick="showChurnMgmt();">Churn Management</button>
						<button onClick="showLogs();">Transaction Logs</button>
						<button onClick="showRenewals();">Renewals</button>
						<button onClick="showActivations();">Activations</button>
						<button onClick="showCallbacks();">Callbacks</button>
						<button onClick="showActiveBase();">Active Base</button>
					</div>
				</div>
				<div id="panel">
					Welcome to Content Management System.
				</div>
			</div>
			<%
					int flag = 0;
					try{
						flag = (int)session.getAttribute("flag");
						
						if(flag==1){
							%>
								<script>
								alert("No Data Found!");
								</script>
							<%
							}
					}
					catch(Exception e){		
					}
					finally{	
						flag=0;
						session.setAttribute("flag",flag);
						
					}
				%>
			<script src="assets/javascript/mainScript.js"></script>
		</body>
	</html>