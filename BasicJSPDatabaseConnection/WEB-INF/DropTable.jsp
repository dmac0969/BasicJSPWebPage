<% 
/* Author: Dylan MacDermot
 * Version: 1.0
 * Purpose: JSP to drop the table
 */
%>
<%@ page import="dataBeans.DatabaseOperations" %>

<jsp:useBean id='database' class='dataBeans.DatabaseOperations' />
<jsp:setProperty name='database' property='username' value='student1' />
<jsp:setProperty name='database' property='password' value='pass' />
<jsp:setProperty name='database' property='table' value='MACDERMOT_7_1' />
		
<%       
	try{
		database.dropTable();
%> 
		<br> 
<%
		out.println("<h1>Table dropped</h1>");
	}catch(Exception e){
		out.println("<h1>Table does not exist!</h1>");
	}
%>

<html>


	<head>
		<title>
			Week 7 Drop Table JSP
		</title>
	</head>
	<body>
		<form>
			<input type="button" value="Return" 
			 onclick="window.location.href='http://localhost:7070/DylanMacDermotWeek7'"
			/>	
		</form>
	</body>
</html>
