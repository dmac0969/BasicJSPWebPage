<%
	/* Assignment 7.1 - JSP to read from and write to a database. This JSP does not itself connect to the database
	 *                  but instead calls an external class to connect to the database.
	 * Dylan MacDermot
	 * November 22nd, 2018
	 * Bellevue University
	 * DylanMacDermotFormPost4.jsp
	 */
%>

<%@ page import="dataBeans.DatabaseOperations" %>
<%@ page import="dataBeans.DatabaseOperations.DatabaseObject" %>
<%@ page import="java.util.ArrayList" %>

<jsp:useBean id='database' class='dataBeans.DatabaseOperations' />
<jsp:setProperty name='database' property='username' value='student1' />
<jsp:setProperty name='database' property='password' value='pass' />
<jsp:setProperty name='database' property='table' value='MACDERMOT_7_1' />

<html>
	<head>
		<title> Hello week 7! </title>
		<link rel="stylesheet" type="text/css" href="Styles.css" />
	</head>
	<body>
	
	<!-- Below starts the GET method of the JSP, which displays the form for the user -->
	
	<% if(request.getMethod().equals("GET")){ %>
		<h1>Personal Information Questionairre</h1>
		<form method='post' action='<% request.getRequestURL(); %>'>
			<label>What is your first name?</label>
			<input type='text' name='firstName' size='40' required />
			<br><br>
			<label>What is your last name?</label>
			<input type='text' name='lastName' size='40' required />
			<br><br>
			<label>What is your phone number?</label>
			<input type='text' name='phoneNumber' size='40' required />
			<br><br>
			<label>What is your email address?</label>
			<input type='text' name='emailAddress' size='40' required />
			<br><br>
			<input type='submit' value='Submit'/>
			<input type="button" value="Return" 
			 onclick="window.location.href='http://localhost:7070/DylanMacDermotWeek7'"
			/>
		</form>	
	<% } %>
	<%
		if(request.getMethod().equals("POST")) {   %>
		
		<h1>Personal Information Questionairre</h1>
		<form method='post' action='<% request.getRequestURL(); %>'>
			<label>What is your first name?</label>
			<input type='text' name='firstName' size='40' required />
			<br><br>
			<label>What is your last name?</label>
			<input type='text' name='lastName' size='40' required />
			<br><br>
			<label>What is your phone number?</label>
			<input type='text' name='phoneNumber' size='40' required />
			<br><br>
			<label>What is your email address?</label>
			<input type='text' name='emailAddress' size='40' required />
			<br><br>
			<input type='submit' value='Submit'/>
			<input type="button" value="Return" 
			 onclick="window.location.href='http://localhost:7070/DylanMacDermotWeek7'"
			/>
		</form>	
	<%
	    // Get each parameter returned by the form above
			String fName = request.getParameter("firstName");
			String lName = request.getParameter("lastName");
			String pNumber = request.getParameter("phoneNumber");
			String eAddress = request.getParameter("emailAddress");
			
			database.updateTable(fName, lName, pNumber, eAddress);
		}
	%>
		<br>
		<table>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Phone Number</th>
				<th>Email Address</th>
			</tr>
	<%
		ArrayList<DatabaseObject> list = database.queryTable();
		if(list.isEmpty()){
			out.println("<td align='center' colspan='4'>No data</td>");
		}else{
			for(int i = 0; i < list.size(); i++){
				out.println("<tr>");
				out.println("<td>");
				out.println(list.get(i).getFirstName());
				out.println("</td>");
				out.println("<td>");
				out.println(list.get(i).getLastName());
				out.println("</td>");
				out.println("<td>");
				out.println(list.get(i).getPhoneNumber());
				out.println("</td>");
				out.println("<td>");
				out.println(list.get(i).getEmailAddress());
				out.println("</td>");
				out.println("</tr>");
			}
		}
		
	
	
	%>
	
		</table>
	</body>
</html>