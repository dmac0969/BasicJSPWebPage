<% 
/*
Author: Dylan MacDermot
Version: 1.0
Purpose: This JSP creates the table with which the data from the form will be input
*/ 
%>

<%@ page import="dataBeans.DatabaseOperations" %>

<jsp:useBean id='database' class='dataBeans.DatabaseOperations' />
<jsp:setProperty name='database' property='username' value='student1' />
<jsp:setProperty name='database' property='password' value='pass' />
<jsp:setProperty name='database' property='table' value='MACDERMOT_7_1' />

<% 
	try{
		database.createTable();
		%> 
		<br> 
		<%
		out.println("<h1>Table Created</h1>");
	}catch(Exception e){
		out.println("<h1>Table already exists!</h1>");
	}
	
%>

<html>
	<head>
		<title>
			Week 7 Create Table JSP
		</title>
	</head>
	<body>

		<br>
		<form>
			<input type="button" value="Return" 
			 onclick="window.location.href='http://localhost:7070/DylanMacDermotWeek7'"
			/>
		</form>
	</body>
</html>
